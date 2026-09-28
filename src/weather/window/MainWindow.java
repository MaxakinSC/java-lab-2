package weather.window;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import weather.drawing.LogoCanvas;
import weather.generator.RandomWeatherGenerator;
import weather.model.WeatherState;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame implements ActionListener {

    private final JMenuItem authorItem = new JMenuItem("Author");
    private final JMenuItem infoItem = new JMenuItem("Info");
    private final JMenuItem runItem = new JMenuItem("Run");

    private final JPopupMenu popupMenu = new JPopupMenu();
    private final JMenuItem popupAuthorItem = new JMenuItem("Author");
    private final JMenuItem popupInfoItem = new JMenuItem("Info");
    private final JMenuItem popupRunItem = new JMenuItem("Run");

    public MainWindow() {
        setTitle("Погодний інформер");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Меню");
        authorItem.addActionListener(this);
        infoItem.addActionListener(this);
        runItem.addActionListener(this);
        menu.add(authorItem);
        menu.add(infoItem);
        menu.add(runItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        popupAuthorItem.addActionListener(this);
        popupInfoItem.addActionListener(this);
        popupRunItem.addActionListener(this);
        popupMenu.add(popupAuthorItem);
        popupMenu.add(popupInfoItem);
        popupMenu.add(popupRunItem);

        LogoCanvas logoCanvas = new LogoCanvas();
        add(logoCanvas);

        logoCanvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                if (event.isPopupTrigger()) {
                    popupMenu.show(
                            event.getComponent(),
                            event.getX(),
                            event.getY()
                    );
                }
            }

            @Override
            public void mouseReleased(MouseEvent event) {
                if (event.isPopupTrigger()) {
                    popupMenu.show(
                            event.getComponent(),
                            event.getX(),
                            event.getY()
                    );
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == authorItem
                || event.getSource() == popupAuthorItem) {
            AuthorWindow authorWindow = new AuthorWindow();
            authorWindow.setLocation(570, 50);
            authorWindow.setVisible(true);
        } else if (event.getSource() == infoItem
                        || event.getSource() == popupInfoItem) {
            InfoWindow infoWindow = new InfoWindow();
            infoWindow.setLocation(570, 50);
            infoWindow.setVisible(true);
        } else if (event.getSource() == runItem
                        || event.getSource() == popupRunItem) {
            RandomWeatherGenerator generator = new RandomWeatherGenerator();
            WeatherState weatherState = generator.generate();

            WeatherWindow weatherWindow = new WeatherWindow(weatherState);
            weatherWindow.setLocation(570, 50);
            weatherWindow.setVisible(true);
        }
    }
}
