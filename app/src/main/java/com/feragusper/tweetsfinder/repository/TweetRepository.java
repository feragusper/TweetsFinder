package com.feragusper.tweetsfinder.repository;

import com.feragusper.tweetsfinder.datasource.TwitterDataSource;
import com.feragusper.tweetsfinder.model.Tweet;

import java.util.List;

import twitter4j.TwitterException;

/**
 * @author Fernando.Perez
 * @since 1.0
 */
public class TweetRepository {

    private static TweetRepository INSTANCE = new TweetRepository();
    private TwitterDataSource datasource;

    private TweetRepository() {
        datasource = new TwitterDataSource();
    }

    public static TweetRepository getInstance() {
        return INSTANCE;
    }


    public List<Tweet> searchTweets(String term) throws TwitterException {
        return datasource.searchTweets(term);
    }

    public List<Tweet> getNextResults() throws TwitterException {
        return datasource.nextResults();
    }
}
