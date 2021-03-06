package wowcher;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalQuery;
import java.util.Optional;
import java.util.StringJoiner;

import wowcher.constants.TimeConstants;
import wowcher.exception.InvalidTimeException;
import wowcher.exception.Issue;

public class SpeakingClock {

    private final WordQuery wordQuery = new WordQuery();

    public String getTime(final String time) throws InvalidTimeException {
        String timeString = null;
        try {
            LocalTime localTime = LocalTime.parse(time);
            timeString = localTime.query(wordQuery);
        } catch (DateTimeParseException dtpe) {
            throw InvalidTimeException.of(Issue.WRONG_TIME_FORMAT);
        }

        return timeString;
    }
}
