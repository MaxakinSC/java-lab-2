package weather.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkInfo {

    public String getLocalComputerInfo() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();

        return "IP: " + address.getHostAddress()
                + "\nІм'я вузла: " + address.getHostName();
    }

    public String getHostInfo(String host) throws UnknownHostException {
        InetAddress address = InetAddress.getByName(host);

        return "IP: " + address.getHostAddress()
                + "\nІм'я вузла: " + address.getHostName();
    }
}