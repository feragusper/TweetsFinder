package com.feragusper.tweetsfinder.presenter;

import com.feragusper.tweetsfinder.model.Tweet;
import com.feragusper.tweetsfinder.view.TweetListView;
import com.feragusper.tweetsfinder.view.fragment.TweetListFragment;

import java.util.ArrayList;

/**
 * @author Fernando.Perez
 */
public class TweetListPresenter {
    private TweetListView view;

    public void resume() {

    }

    public void pause() {

    }

    public void destroy() {

    }

    public void initialize() {
        view.renderTweetList(new ArrayList<Tweet>());
    }

    public void setView(TweetListView view) {
        this.view = view;
    }
}
