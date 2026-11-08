package weather.window;

import javax.swing.*;

import weather.network.NetworkInfo;
import weather.network.UrlInfo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.awt.BorderLayout;

public class NetworkWindow extends JFrame {

    public NetworkWindow() {
        setTitle("Мережева інформація");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);

        NetworkInfo networkInfo = new NetworkInfo();
        UrlInfo urlInfo = new UrlInfo();

        try {
            infoArea.setText(networkInfo.getLocalComputerInfo());
        } catch (UnknownHostException exception) {
            infoArea.setText("Не вдалося отримати мережеву інформацію");
        }

        JPanel inputPanel = new JPanel();

        JLabel hostLabel = new JLabel("Сайт:");
        JTextField hostField = new JTextField(20);
        JButton getButton = new JButton("Отримати");
        JButton urlButton = new JButton("URL");

        inputPanel.add(hostLabel);
        inputPanel.add(hostField);
        inputPanel.add(getButton);
        inputPanel.add(urlButton);

        add(inputPanel, BorderLayout.SOUTH);
        JScrollPane scrollPane = new JScrollPane(infoArea);
        add(scrollPane, BorderLayout.CENTER);

        getButton.addActionListener(event -> {
            String host = hostField.getText().trim();

            try {
                String localInfo = networkInfo.getLocalComputerInfo();
                String hostInfo = networkInfo.getHostInfo(host);

                infoArea.setText(
                        "Локальний комп'ютер:\n"
                                + localInfo
                                + "\n\nСайт:\n"
                                + hostInfo
                );
            } catch (UnknownHostException exception) {
                infoArea.setText("Не вдалося знайти вказаний вузол");
            }
        });

        urlButton.addActionListener(event -> {
            String urlText = hostField.getText().trim();

            try {
                String urlInformation = urlInfo.getUrlInfo(urlText);
                String pageContent = urlInfo.getPageContent(urlText);

                infoArea.setText(
                        urlInformation
                                + "\n\nВміст сторінки:\n"
                                + pageContent
                );
            } catch (IOException exception) {
                infoArea.setText("Не вдалося отримати дані за URL");
            }
        });
    }
}