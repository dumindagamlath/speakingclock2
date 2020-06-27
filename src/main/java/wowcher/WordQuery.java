package wowcher;

import java.time.LocalTime;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;
import java.util.StringJoiner;

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
            appendWord(t.getHour(), 20, timeString);
            int minute = t.getMinute();
            if (minute > 20) {
                appendWord(minute, Math.floorDiv(minute, 10) * 10, timeString);
            } else if (minute > 0) {
                timeString.add(TimeConstants.timeMap.get(minute));
            }
        }

        return timeString.toString();
    }

    private void appendWord(int number, int div, StringJoiner wordStr) {
        if (number > div) {
            wordStr.add(TimeConstants.timeMap.get(div));
            wordStr.add(TimeConstants.timeMap.get(Math.floorMod(number, div)));
        } else {
            wordStr.add(TimeConstants.timeMap.get(number));
        }
    }
}
