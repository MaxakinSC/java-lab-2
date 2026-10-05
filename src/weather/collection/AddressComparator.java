package weather.collection;

import weather.model.FreeClusterArea;

import java.util.Comparator;

public class AddressComparator implements Comparator<FreeClusterArea> {
    @Override
    public int compare(FreeClusterArea first, FreeClusterArea second) {
        return Long.compare(first.getAddress(), second.getAddress());
    }
}
