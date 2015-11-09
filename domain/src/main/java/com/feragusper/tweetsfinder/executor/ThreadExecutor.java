package com.feragusper.tweetsfinder.executor;

import java.util.concurrent.Executor;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Executor implementation can be based on different frameworks or techniques of asynchronous
 * execution, but every implementation will execute the
 * {@link com.feragusper.tweetsfinder.interactor.UseCase} out of the UI thread.
 */
public interface ThreadExecutor extends Executor {
}
