package com.feragusper.tweetsfinder.executor;

import com.feragusper.tweetsfinder.interactor.UseCase;

import java.util.concurrent.Executor;

/**
 * @author Fernando.Perez
 * @since 1.0
 * <p>
 * Executor implementation can be based on different frameworks or techniques of asynchronous
 * execution, but every implementation will execute the
 * {@link UseCase} out of the UI thread.
 */
public interface ThreadExecutor extends Executor {
}
