package weather.collection;

import weather.model.FreeClusterArea;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

public class ClusterCollection {
    private final List<FreeClusterArea> areas = new LinkedList<>();

    public void addArea(FreeClusterArea area) {
        areas.add(area);
    }

    public FreeClusterArea findLongestArea() {
        if (areas.isEmpty()) {
            return null;
        }

        Iterator<FreeClusterArea> iterator = areas.iterator();

        FreeClusterArea longestArea = iterator.next();

        while (iterator.hasNext()) {
            FreeClusterArea currentArea = iterator.next();

            if(currentArea.getClusterCount() > longestArea.getClusterCount()) {
                longestArea = currentArea;
            }
        }

        return longestArea;
    }

    public FreeClusterArea findByAreaNumber(int areaNumber) {
        Iterator<FreeClusterArea> iterator = areas.iterator();

        while (iterator.hasNext()) {
            FreeClusterArea currentArea = iterator.next();

            if (currentArea.getAreaNumber() == areaNumber) {
                return currentArea;
            }
        }

        return null;
    }

    public FreeClusterArea findByAddress(long address) {
        Iterator<FreeClusterArea> iterator = areas.iterator();

        while (iterator.hasNext()) {
            FreeClusterArea currentArea = iterator.next();

            if (currentArea.getAddress() == address) {
                return currentArea;
            }
        }

        return null;
    }

    public void sortByAddress() {
        areas.sort(new AddressComparator());
    }

    public String getCollectionText() {
        StringBuilder text = new StringBuilder();

        Iterator<FreeClusterArea> iterator = areas.iterator();

        while (iterator.hasNext()) {
            FreeClusterArea currentArea = iterator.next();

            text.append(currentArea.toString());
            text.append("\n");
        }

        return text.toString();
    }
}
