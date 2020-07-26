package com.example.trafficreduction.ui.addPlace;

import com.example.trafficreduction.model.PopularTimesModel;
import com.example.trafficreduction.model.PopularTimesModelBodyItem;

import java.math.BigDecimal;

public class PopularTimes {
    int[][] popularTimes;

    public enum Day {
        Monday, Tuesday, Wednesday,Thursday,Friday,Saturday,Sunday
    }
//    public enum Time {
//        AM12,AM1,AM2,AM3,AM4,AM5,AM6,AM7,AM8,AM9,AM10,AM11,PM12,PM1,PM2,PM3,PM4,PM5,PM6,PM7,PM8,PM9,PM10,PM11
//    }

    public PopularTimes(int[][] popTimesInfo) {
        popularTimes = popTimesInfo;
    }
    public PopularTimes(PopularTimesModel model) {
        popularTimes = new int [7][24];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 24; j++) {
                PopularTimesModelBodyItem item = model.getBody().get(i);
                BigDecimal popularity = item.getData().get(j);
                popularTimes[i][j] = popularity.intValue();
            }
        }
    }

    public int[] getPopularTimes(int day) {
        return popularTimes[day];
    }
    public int getPopularity(int day, int hour) {
        return popularTimes[day][hour];
    }
    public int getPopularity(Day day, int hour) {
        return popularTimes[day.ordinal()][hour];
    }

}