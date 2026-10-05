package weather.window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import weather.drawing.WeatherCanvas;
import weather.model.WeatherState;

public class WeatherWindow extends JFrame implements ActionListener {
    //private final WeatherState weatherState;
    private final JRadioButton rainRadioButton = new JRadioButton("Дощ");
    private final JRadioButton snowRadioButton = new JRadioButton("Сніг");
    private final JTextField precipitationCountField = new JTextField(3);
    private final JCheckBox sunCheckBox = new JCheckBox("Сонце");
    private final JCheckBox cloudCheckBox = new JCheckBox("Хмари");
    private final WeatherCanvas weatherCanvas;

    public WeatherWindow(WeatherState weatherState) {
        //this.weatherState = weatherState;

        setTitle("Стан погоди");
        setSize(750, 500);
        //setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ButtonGroup precipitationGroup = new ButtonGroup();
        precipitationGroup.add(rainRadioButton);
        precipitationGroup.add(snowRadioButton);

        boolean initialRain = weatherState.isRain();
        boolean initialSnow = !initialRain && weatherState.isSnow();

        WeatherState initialWeatherState = new WeatherState(
                weatherState.isSun(),
                initialRain,
                initialSnow,
                weatherState.isCloud()
        );

        weatherCanvas = new WeatherCanvas(initialWeatherState);
        setLayout(new BorderLayout());
        JPanel controlPanel = new JPanel();

        controlPanel.setLayout(new GridLayout(1, 0));

        add(controlPanel, BorderLayout.NORTH);
        add(weatherCanvas, BorderLayout.CENTER);

        JLabel precipitationCountLabel = new JLabel("Кількість:");
        JButton applyButton = new JButton("Застосувати");

        controlPanel.add(precipitationCountLabel);
        controlPanel.add(precipitationCountField);
        controlPanel.add(rainRadioButton);
        controlPanel.add(snowRadioButton);
        controlPanel.add(sunCheckBox);
        controlPanel.add(cloudCheckBox);
        controlPanel.add(applyButton);

        sunCheckBox.setSelected(initialWeatherState.isSun());
        cloudCheckBox.setSelected(initialWeatherState.isCloud());
        rainRadioButton.setSelected(initialWeatherState.isRain());
        snowRadioButton.setSelected(initialWeatherState.isSnow());
        precipitationCountField.setText("6");

        applyButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        boolean sun = sunCheckBox.isSelected();
        boolean cloud = cloudCheckBox.isSelected();
        boolean rain = rainRadioButton.isSelected();
        boolean snow = snowRadioButton.isSelected();

        WeatherState newWeatherState = new WeatherState(sun, rain, snow, cloud);

        String countText = precipitationCountField.getText();
        int precipitationCount;
        try {
            precipitationCount = Integer.parseInt(countText.trim());
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(
                    this,
                    "Введіть ціле число."
            );
            return;
        }

        if (precipitationCount < 1 || precipitationCount > 50) {
            JOptionPane.showMessageDialog(
                    this,
                    "Кількість опадів має бути від 1 до 50."
            );
            return;
        }

        weatherCanvas.setWeatherState(newWeatherState);
        weatherCanvas.setPrecipitationCount(precipitationCount);
    }
}
