package wowcher;

import java.time.LocalTime;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import wowcher.constants.TimeConstants;

public class WordQuery implements TemporalQuery<String> {

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
            { 18, "eighteen"},
            { 19, "ninteen"},
            { 20, "twenty"},
            { 30, "thirty"},
            { 40, "forty"},
            { 50, "fifty"},
    }).collect(Collectors.toMap(data -> (Integer)data[0], data -> (String)data[1]));

    @Override
    public String queryFrom(TemporalAccessor temporal) {
        LocalTime t = LocalTime.from(temporal);
        StringJoiner timeString = new StringJoiner(" ");
        timeString.add(TimeConstants.TIME_LABEL_START);
        if (t.compareTo(LocalTime.MIDNIGHT) == 0) {
            timeString.add(TimeConstants.MIDNIGHT);
        } else if (t.compareTo(LocalTime.NOON) == 0) {
            timeString.add(TimeConstants.MIDDAY);
        } else {
            timeString.add(wordFinder(t.getHour(), 20, (a, b) -> (a <= b)));
            int minute = t.getMinute();
            if (minute > 0) {
                timeString.add(wordFinder(minute, minute > 20 ? Math.floorDiv(minute, 10) * 10 : 0, (a, b) -> (a <= b || b == 0)));
            }
        }

        return timeString.toString();
    }

    private static Function<Object, String> getWord = timeMap::get;

    private static String wordFinder(
            int a, int b, BiPredicate<Integer, Integer> tester) {
        StringBuilder word = new StringBuilder();
        if (tester.test(a, b)) {
            word.append(getWord.apply(a));
        } else {
            word.append(getWord.apply(b)).append(" ").append(getWord.apply(Math.floorMod(a, b)));
        }
        return word.toString();
    }
}


