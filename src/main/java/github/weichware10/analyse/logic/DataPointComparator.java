package github.weichware10.analyse.logic;

import github.weichware10.util.data.DataPoint;
import java.util.Comparator;

/**
 * DataPointComparator.
 */
public class DataPointComparator implements Comparator<DataPoint> {

    @Override
    public int compare(DataPoint dp1, DataPoint dp2) {
        int area1 = (int) (dp1.viewport.getWidth() * dp1.viewport.getHeight());
        int area2 = (int) (dp2.viewport.getWidth() * dp2.viewport.getHeight());
        return area2 - area1;
    }
}
