package wowcher.constants;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TimeConstants {

    public static final String TIME_LABEL_START = "It's";

    public static final String MIDNIGHT = "Midnight";

    public static final String MIDDAY = "Midday";

    public static final Map<Integer, String> timeMap = Stream.of(new Object[][] {
            { 0, "zero"},
            { 1, "one"},
            { 2, "two"},
            { 3, "three"},
            { 4, "four"},
            { 5, "five"},
            { 6, "six"},
            { 7, "seven"},
            { 8, "eight"},
            { 9, "nine"},
            { 10, "ten"},
            { 11, "eleven"},
            { 12, "twelve"},
            { 13, "thirteen"},
            { 14, "fourteen"},
            { 15, "fifteen"},
            { 16, "sixteen"},
            { 17, "seventeen"},
            { 18, "eigteen"},
            { 19, "ninteen"},
            { 20, "twenty"},
            { 30, "thirty"},
            { 40, "forty"},
            { 50, "fifty"},
    }).collect(Collectors.toMap(data -> (Integer)data[0], data -> (String)data[1]));
}
