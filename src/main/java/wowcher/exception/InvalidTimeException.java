package wowcher.exception;

public class InvalidTimeException extends Exception {
    private final IIssue issue;

    public IIssue getIssue() {
        return issue;
    }

    private InvalidTimeException(IIssue issue) {
        this.issue = issue;
    }

    public static InvalidTimeException of(final IIssue issue) {
        return new InvalidTimeException(issue);
    }
}
