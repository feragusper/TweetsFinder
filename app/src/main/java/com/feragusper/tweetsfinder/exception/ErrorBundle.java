package com.feragusper.tweetsfinder.exception;

/**
 * @author Fernando.Perez
 * @since 1.0
 * <p>
 * Interface to represent a wrapper around an {@link Exception} to manage errors.
 */
public interface ErrorBundle {
  Exception getException();

  String getErrorMessage();
}
