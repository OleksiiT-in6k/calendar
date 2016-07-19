package com.interlink;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Алекс on 18.07.2016.
 */
public class Aggregator {
    int horizontalQuantity;
    int verticalQuantity;

    public Aggregator(int horizontalQuantity, int verticalQuantity) {
        this.horizontalQuantity = horizontalQuantity;
        this.verticalQuantity = verticalQuantity;
    }

    String generateFormattedStringForSingleRow(List<String> monthViews) {
        String result = "";
        int format = 28;
        for (int i = 0; i < monthViews.get(0).split("\n").length; i++) {
            for (String monthView : monthViews) {
                if (i < monthView.split("\n").length)
                    result += String.format("%-" + format + "s", monthView.split("\n")[i]) + " ";
            }
            result += "\n";
        }
        return result;
    }

    String generateFormattedString(List<String> montViews) {
        String result = "";
        if (montViews.size() == 1) {
            result = generateFormattedStringForSingleRow(montViews);
        } else {
            List newMonthViews = new ArrayList<>();
            for (int i = 0; i < horizontalQuantity; i++)
                newMonthViews.addAll(montViews.subList(0, 4));
            result += generateFormattedStringForSingleRow(newMonthViews) + "\n";
            newMonthViews = new ArrayList<>();
            newMonthViews.addAll(montViews.subList(4, 8));
            result += generateFormattedStringForSingleRow(newMonthViews) + "\n";
            newMonthViews = new ArrayList<>();
            newMonthViews.addAll(montViews.subList(8, 12));
            result += generateFormattedStringForSingleRow(newMonthViews) + "\n";
        }
        return result;
    }

}
