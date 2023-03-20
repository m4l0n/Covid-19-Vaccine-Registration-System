package system.common.validation;

public class Success<T> extends Result<T, Exception> {
    private final T value;

    public Success(T value) {
        super(value, null);
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}