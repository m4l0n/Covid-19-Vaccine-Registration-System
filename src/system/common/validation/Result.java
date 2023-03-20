package system.common.validation;

import java.util.function.Function;

public class Result<T, E extends Exception> {

    private final T value;
    private final E error;

    Result(T value, E error) {
        this.value = value;
        this.error = error;
    }

    public static <T, E extends Exception> Result<T, E> success(T value) {
        return new Result<>(value, null);
    }

    public static <T, E extends Exception> Result<T, E> failure(E error) {
        return new Result<>(null, error);
    }

    public <R> R fold(Function<T, R> onSuccess, Function<E, R> onFailure) {
        if (value != null) {
            return onSuccess.apply(value);
        } else {
            return onFailure.apply(error);
        }
    }

    public <U> Result<U, E> map(Function<T, U> mapper) {
        if (value != null) {
            return success(mapper.apply(value));
        } else {
            return failure(error);
        }
    }

    public <U> Result<U, E> flatMap(Function<T, Result<U, E>> mapper) {
        if (value != null) {
            return mapper.apply(value);
        } else {
            return failure(error);
        }
    }
}