package weather.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.URLConnection;

public class UrlInfo {

    public String getUrlInfo(String urlText) throws MalformedURLException {
        URL url = new URL(urlText);

        return "Протокол: " + url.getProtocol()
                + "\nХост: " + url.getHost()
                + "\nПорт: " + url.getPort()
                + "\nШлях: " + url.getPath();
    }

    public String getPageContent(String urlText) throws IOException {
        URL url = new URL(urlText);

        URLConnection connection = url.openConnection();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
        );

        StringBuilder content = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }

        reader.close();

        return content.toString();
    }
}