package weather.model;

public class FreeClusterArea {
    private final int areaNumber;
    private final long address;
    private final int clusterCount;

    public FreeClusterArea(int areaNumber, long address, int clusterCount) {
        this.areaNumber = areaNumber;
        this.address = address;
        this.clusterCount = clusterCount;
    }

    public int getAreaNumber() {
        return areaNumber;
    }

    public long getAddress() {
        return address;
    }

    public int getClusterCount() {
        return clusterCount;
    }

    @Override
    public String toString() {
        return "Ділянка №" + areaNumber
                + ", адреса: " + address
                + ", кількість кластерів: " + clusterCount;
    }
}
