package com.feragusper.tweetsfinder.datasource;

import com.feragusper.tweetsfinder.model.Tweet;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * @author Fernando.Perez
 * @since 1.0
 */
public class TwitterDataSource {

    final static String TWITTER_API_KEY = "vMU46H2kwxPThrHGNJwt8wru1";
    final static String TWITTER_API_SECRET = "vhJLR5gsBO8AF5MkJV6lL8hotizmgeOR39tQau3eVIGcZmgn1P";
    private final Twitter twitter;
    private QueryResult result;

    public TwitterDataSource() {
        final ConfigurationBuilder configurationBuilder = new ConfigurationBuilder()
                .setOAuthConsumerKey("vMU46H2kwxPThrHGNJwt8wru1")
                .setOAuthConsumerSecret("vhJLR5gsBO8AF5MkJV6lL8hotizmgeOR39tQau3eVIGcZmgn1P")
                .setOAuthAccessToken("388502124-vQcoSLVfZ5BpAQFPphsFagdutRRFwh0g7HpiCwv6")
                .setOAuthAccessTokenSecret("03L4QssLl9k1cMawuQLq6o7wrE4FLhDHrrZBk4V8BnEmn");
        twitter = new TwitterFactory(configurationBuilder.build()).getInstance();
    }

    public List<Tweet> searchTweets(String term) throws TwitterException {
        return doSearch(new Query(term + " +exclude:retweets"));
    }

    public List<Tweet> nextResults() throws TwitterException {
        return doSearch(result.nextQuery());
    }

    private List<Tweet> doSearch(Query query) throws TwitterException {
        result = twitter.search(query);

        ArrayList<Tweet> tweets = new ArrayList<>();
        for (Status status : result.getTweets()) {
            Tweet tweet = new Tweet();
            tweet.setMessage(status.getText());
            tweet.setUserName(status.getUser().getName());
            tweet.setUserScreenName(status.getUser().getScreenName());
            tweet.setUserURL(status.getUser().getURL());
            tweet.setUserThumbnailURL(status.getUser().getBiggerProfileImageURL());
            tweets.add(tweet);
        }
        return tweets;
    }

}
