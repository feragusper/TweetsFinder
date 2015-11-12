package com.feragusper.tweetsfinder.exception;

/**
 * @author Fernando.Perez
 * @since 1.0
 * <p>
 * Wrapper around Exceptions used to manage default errors.
 */
public class DefaultErrorBundle implements ErrorBundle {

    private final Exception exception;

    public DefaultErrorBundle(Exception exception) {
        this.exception = exception;
    }

    @Override
    public Exception getException() {
        return exception;
    }

    @Override
    public String getErrorMessage() {
        String message = "";
        if (this.exception != null) {
            this.exception.getMessage();
        }
        return message;
    }
}
