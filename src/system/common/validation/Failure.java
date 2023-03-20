package system.common.validation;

public class Failure<T> extends Result<T, Exception> {
    private final Exception error;

    public Failure(Exception error) {
        super(null, error);
        this.error = error;
    }

    public Exception getError() {
        return error;
    }
}

