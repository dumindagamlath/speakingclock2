package wowcher;

import java.time.LocalTime;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;
import java.util.StringJoiner;
import java.util.function.BiPredicate;

import wowcher.constants.TimeConstants;

public class WordQuery implements TemporalQuery<String> {

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

    private static String wordFinder(
            int a, int b, BiPredicate<Integer, Integer> tester) {
        StringBuilder word = new StringBuilder();
        if (tester.test(a, b)) {
            word.append(TimeConstants.timeMap.get(a));
        } else {
            word.append(TimeConstants.timeMap.get(b));
            word.append(" ");
            word.append(TimeConstants.timeMap.get(Math.floorMod(a, b)));
        }
        return word.toString();
    }
}


