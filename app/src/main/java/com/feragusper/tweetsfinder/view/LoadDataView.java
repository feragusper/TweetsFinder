package com.feragusper.tweetsfinder.view;

import android.content.Context;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Interface representing a View that will use to load data.
 */
public interface LoadDataView {
    /**
     * Show a view with a progress bar indicating a loading process.
     */
    void showLoading();

    /**
     * Hide a loading view.
     */
    void hideLoading();

    /**
     * Show an error message
     *
     * @param message A string representing an error.
     */
    void showError(String message);

    /**
     * Show an empty view.
     */
    void showEmptyView();

    /**
     * Hide empty view.
     */
    void hideEmptyView();
}