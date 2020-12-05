package wowcher;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import wowcher.constants.TimeConstants;
import wowcher.exception.InvalidTimeException;
import wowcher.exception.Issue;

public class SpeakingClockTest {
    private final String INVALID_TIME_FORMAT = "88.23";

    private final String MID_DAY_TIME = "12:00";

    private final String MID_NIGHT_TIME = "00:00";

    private final String TIME_1 = "01:18";

    private final String TIME_2 = "21:20";

    private final String TIME_3 = "25:10";

    private final String TIME_4 = "23:71";

    private final String TIME_5 = "23:21";

    private final String TIME_6 = "10:56";

    private final String TIME_7 = "00:59";

    private final String TIME_8 = "03:00";

    SpeakingClock speakingClock;

    @Before
    public void init() {
        speakingClock = new SpeakingClock();
    }

    @Test
    public void getTime_throw_InvalidTimeException_WRONG_TIME_FORMAT_when_time_format_is_invalid()
            throws InvalidTimeException {
        try {
            speakingClock.getTime(INVALID_TIME_FORMAT);
        } catch (InvalidTimeException ite) {
            Assert.assertEquals(ite.getIssue().getErrorCode(), Issue.WRONG_TIME_FORMAT.getErrorCode());
        }
    }

    @Test
    public void getTime_return_MIDNIGHT_when_time_is_0000()
            throws InvalidTimeException {
        final String timeString = speakingClock.getTime(MID_NIGHT_TIME);

        Assert.assertTrue(timeString.contains(TimeConstants.MIDNIGHT));
    }

    @Test
    public void getTime_return_NOON_when_time_is_1200()
            throws InvalidTimeException {
        final String timeString = speakingClock.getTime(MID_DAY_TIME);

        Assert.assertTrue(timeString.contains(TimeConstants.MIDDAY));
    }

    @Test
    public void getTime_return_in_words_when_time_and_minute_digits_are_less_than_twenty()
            throws InvalidTimeException {
        final String timeString = speakingClock.getTime(TIME_1);

        final LocalTime time = LocalTime.parse(TIME_1);
        Assert.assertTrue(timeString.contains("one eighteen"));
    }

    @Test
    public void getTime_return_in_words_when_hour_is_greater_than_twenty()
            throws InvalidTimeException {
        final String timeString = speakingClock.getTime(TIME_2);

        Assert.assertTrue(timeString.contains("twenty one twenty"));
    }

    @Test
    public void getTime_throw_InvalidTimeException_WRONG_TIME_FORMAT_when_hour_is_out_of_range()
            throws InvalidTimeException {
        try {
            speakingClock.getTime(TIME_3);
        } catch (InvalidTimeException ite) {
            Assert.assertEquals(ite.getIssue().getErrorCode(), Issue.WRONG_TIME_FORMAT.getErrorCode());
        }
    }

    @Test
    public void getTime_throw_InvalidTimeException_WRONG_TIME_FORMAT_when_minute_is_out_of_range()
            throws InvalidTimeException {
        try {
            speakingClock.getTime(TIME_4);
        } catch (InvalidTimeException ite) {
            Assert.assertEquals(ite.getIssue().getErrorCode(), Issue.WRONG_TIME_FORMAT.getErrorCode());
        }
    }

    @Test
    public void getTime_return_in_words_when_minutes_is_greater_than_twenty()
        throws InvalidTimeException {
        final String timeString = speakingClock.getTime(TIME_5);

        Assert.assertTrue(timeString.contains("twenty three twenty one"));
    }

    @Test
    public void getTime_return_in_words_when_minutes_is_greater_than_thirty()
            throws InvalidTimeException {
        final String timeString = speakingClock.getTime(TIME_6);

        Assert.assertTrue(timeString.contains("ten fifty six"));
    }

    @Test
    public void getTime_return_in_words_when_hour_is_past_midnight()
            throws InvalidTimeException {
        final String timeString = speakingClock.getTime(TIME_7);

        Assert.assertTrue(timeString.contains("zero fifty nine"));
    }

    @Test
    public void getTime_return_in_words_when_minute_is_zero()
            throws InvalidTimeException {
        final String timeString = speakingClock.getTime(TIME_8);

        Assert.assertTrue(timeString.endsWith("three"));
    }
}
