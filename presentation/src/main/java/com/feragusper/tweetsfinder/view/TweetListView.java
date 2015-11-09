package com.feragusper.tweetsfinder.view;

import com.feragusper.tweetsfinder.model.Tweet;

import java.util.Collection;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p/>
 * Interface representing a View in a model view presenter (MVP) pattern.
 * In this case is used as a view representing a list of {@link Tweet}.
 */
public interface TweetListView extends LoadDataView {

    /**
     * Render a HistoricalRecord list in the UI.
     *
     * @param tweetCollection The collection of {@link Tweet} that will be shown.
     */
    void renderTweetList(Collection<Tweet> tweetCollection);

}
