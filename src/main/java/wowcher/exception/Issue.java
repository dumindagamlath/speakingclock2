package wowcher.exception;

public enum Issue implements wowcher.exception.IIssue {
    /** The invalid input. */
    EMPTY_TIME("EMPTY_TIME", "The time is not given." ),
    /** The wrong time format. */
    WRONG_TIME_FORMAT("WRONG_TIME_FORMAT", "The format for the time is wrong. Format is HH:MM" );

    private final String errorCode;

    private final String description;

    Issue(String errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
