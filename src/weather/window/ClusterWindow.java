package weather.window;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import weather.collection.ClusterCollection;
import weather.model.FreeClusterArea;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ClusterWindow extends JFrame {

    private final JTextField areaNumberField = new JTextField(12);
    private final JTextField addressField = new JTextField(12);
    private final JTextField clusterCountField = new JTextField(12);
    private final JButton addButton = new JButton("Додати");
    private final JTextArea collectionTextArea = new JTextArea();
    private final JButton sortButton = new JButton("Сортувати за адресою");
    private final JButton findButton = new JButton("Знайти за номером");
    private final JButton longestButton = new JButton("Найдовша ділянка");
    private final JButton loadButton = new JButton("Завантажити з файлу");
    private final JLabel resultLabel = new JLabel("Результат: ");

    private final ClusterCollection clusterCollection = new ClusterCollection();

    public ClusterWindow() {
        setTitle("Вільні ділянки кластерів");
        setSize(850, 560);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        setContentPane(mainPanel);

        JLabel titleLabel = new JLabel(
                "Вільні ділянки кластерів",
                SwingConstants.CENTER
        );

        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridLayout(7, 1, 5, 5));

        JLabel areaNumberLabel = new JLabel("Номер ділянки:");

        JLabel addressLabel = new JLabel("Адреса:");

        JLabel clusterCountLabel = new JLabel("Кількість кластерів:");

        inputPanel.add(areaNumberLabel);
        inputPanel.add(areaNumberField);
        inputPanel.add(addressLabel);
        inputPanel.add(addressField);
        inputPanel.add(clusterCountLabel);
        inputPanel.add(clusterCountField);
        inputPanel.add(addButton);

        JPanel inputWrapper = new JPanel(new BorderLayout());
        inputWrapper.add(inputPanel, BorderLayout.NORTH);

        mainPanel.add(inputWrapper, BorderLayout.WEST);

        collectionTextArea.setEditable(false);
        collectionTextArea.setLineWrap(true);
        collectionTextArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(collectionTextArea);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel();

        actionPanel.add(sortButton);
        actionPanel.add(findButton);
        actionPanel.add(longestButton);
        actionPanel.add(loadButton);

        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));

        bottomPanel.add(actionPanel, BorderLayout.NORTH);
        bottomPanel.add(resultLabel, BorderLayout.SOUTH);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);



        addButton.addActionListener(event -> addArea());
        sortButton.addActionListener(event -> sortAreas());
        findButton.addActionListener(event -> findArea());
        longestButton.addActionListener(event -> showLongestArea());
        loadButton.addActionListener(event -> loadFile());
    }

    private void addArea() {
        try {
            int areaNumber = Integer.parseInt(areaNumberField.getText().trim());
            long address = Long.parseLong(addressField.getText().trim());
            int clusterCount = Integer.parseInt(clusterCountField.getText().trim());

            if (clusterCollection.findByAreaNumber(areaNumber) != null) {
                resultLabel.setText("Результат: ділянка з таким номером вже існує.");
                return;
            }

            if (clusterCollection.findByAddress(address) != null) {
                resultLabel.setText("Результат: ділянка з такою адресою вже існує.");
                return;
            }

            FreeClusterArea area =
                    new FreeClusterArea(areaNumber, address, clusterCount);

            clusterCollection.addArea(area);

            collectionTextArea.setText(clusterCollection.getCollectionText());
        } catch (NumberFormatException exception) {
            resultLabel.setText("Результат: введіть коректні числові значення.");
        }

    }

    private void sortAreas() {
        String beforeSort = clusterCollection.getCollectionText();

        clusterCollection.sortByAddress();

        String afterSort = clusterCollection.getCollectionText();

        collectionTextArea.setText(
                "До сортування:\n"
                        + beforeSort
                        + "\nПісля сортування:\n"
                        + afterSort
        );

    }

    private void findArea() {
        try {
            int areaNumber = Integer.parseInt(areaNumberField.getText().trim());

            FreeClusterArea foundArea =
                    clusterCollection.findByAreaNumber(areaNumber);

            if (foundArea != null) {
                resultLabel.setText("Результат: " + foundArea);
            } else {
                resultLabel.setText("Результат: ділянку не знайдено.");
            }
        } catch (NumberFormatException exception) {
            resultLabel.setText("Результат: введіть коректний номер ділянки.");
        }

    }

    private void showLongestArea() {
        FreeClusterArea longestArea = clusterCollection.findLongestArea();

        if (longestArea != null) {
            resultLabel.setText("Результат: " + longestArea);
        } else {
            resultLabel.setText("Результат: колекція порожня.");
        }

    }

    private void loadFile() {
        JFileChooser fileChooser = new JFileChooser();

        FileNameExtensionFilter filter =
                new FileNameExtensionFilter("Текстові файли (*.txt)", "txt");

        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));

                String line;

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(";");

                    if (parts.length != 3) {
                        resultLabel.setText("Результат: неправильний формат файлу.");
                        reader.close();
                        return;
                    }

                    int areaNumber;
                    long address;
                    int clusterCount;

                    try {
                        areaNumber = Integer.parseInt(parts[0].trim());
                        address = Long.parseLong(parts[1].trim());
                        clusterCount = Integer.parseInt(parts[2].trim());
                    } catch (NumberFormatException exception) {
                        resultLabel.setText("Результат: неправильні числові дані у файлі.");
                        reader.close();
                        return;
                    }

                    if (clusterCollection.findByAreaNumber(areaNumber) != null) {
                        resultLabel.setText("Результат: у файлі є номер ділянки, який вже існує.");
                        reader.close();
                        return;
                    }

                    if (clusterCollection.findByAddress(address) != null) {
                        resultLabel.setText("Результат: у файлі є адреса, яка вже існує.");
                        reader.close();
                        return;
                    }

                    FreeClusterArea area =
                            new FreeClusterArea(areaNumber, address, clusterCount);

                    clusterCollection.addArea(area);
                }

                reader.close();

                collectionTextArea.setText(clusterCollection.getCollectionText());
                resultLabel.setText("Результат: файл успішно завантажено.");

            } catch (IOException exception) {
                resultLabel.setText("Результат: помилка читання файлу.");
            }
        }

    }
}