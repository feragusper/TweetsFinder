package com.feragusper.tweetsfinder.model;

/**
 * @author Fernando.Perez
 * @since 1.0
 *
 * Domain class that represents a Tweet
 */
public class Tweet {
    private String message;
    private String user;
    private String userThumbnailURL;
    private String userScreenName;
    private String userURL;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser() {
        return user;
    }

    public void setUserName(String user) {
        this.user = user;
    }

    public void setUserThumbnailURL(String userThumbnailURL) {
        this.userThumbnailURL = userThumbnailURL;
    }

    public String getUserThumbnailURL() {
        return userThumbnailURL;
    }

    public void setUserScreenName(String userScreenName) {
        this.userScreenName = userScreenName;
    }

    public void setUserURL(String userURL) {
        this.userURL = userURL;
    }

    public String getUserScreenName() {
        return userScreenName;
    }

    public String getUserURL() {
        return userURL;
    }
}
