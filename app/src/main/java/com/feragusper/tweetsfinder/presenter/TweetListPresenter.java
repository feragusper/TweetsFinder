package com.feragusper.tweetsfinder.presenter;

import com.feragusper.tweetsfinder.exception.DefaultErrorBundle;
import com.feragusper.tweetsfinder.interactor.DefaultSubscriber;
import com.feragusper.tweetsfinder.interactor.SearchNextResultsUseCase;
import com.feragusper.tweetsfinder.interactor.SearchTweetsByTermUseCase;
import com.feragusper.tweetsfinder.interactor.UseCase;
import com.feragusper.tweetsfinder.model.Tweet;
import com.feragusper.tweetsfinder.view.TweetListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Fernando.Perez
 * @since 1.0
 *
 * Presenter of the TweetList
 */
public class TweetListPresenter {

    private TweetListView view;
    private List<Tweet> tweets = new ArrayList<>();
    private List<UseCase> useCases = new ArrayList<>();

    public void destroy() {
        for (UseCase usecase : useCases) {
            usecase.unsubscribe();
        }
    }

    public void initialize(String searchTerms) {
        view.showLoading();
        final SearchTweetsByTermUseCase searchTweetsByTermUseCase = new SearchTweetsByTermUseCase(searchTerms);
        useCases.add(searchTweetsByTermUseCase);
        searchTweetsByTermUseCase.execute(new SearchTweetListSubscriber());
    }

    public void onLastItemDisplayed() {
        final SearchNextResultsUseCase searchNextResultsUseCase = new SearchNextResultsUseCase();
        useCases.add(searchNextResultsUseCase);
        searchNextResultsUseCase.execute(new NextResultsSubscriber());
    }

    public void setView(TweetListView view) {
        this.view = view;
    }

    public Collection<Tweet> getTweetCollection() {
        return tweets;
    }

    private void showTweetCollectionInView(List<Tweet> tweets, boolean clear) {
        view.renderTweetList(tweets, clear);
    }

    private void showErrorMessage(DefaultErrorBundle defaultErrorBundle) {
        view.showError(defaultErrorBundle.getErrorMessage());
    }

    private void hideViewLoading() {
        view.hideLoading();
    }

    private void showEmptyView() {
        view.showEmptyView();
    }

    private void hideEmptyView() {
        view.hideEmptyView();
    }

    private final class NextResultsSubscriber extends DefaultSubscriber<List<Tweet>> {

        @Override
        public void onError(Throwable e) {
            TweetListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
        }

        @Override
        public void onNext(List<Tweet> tweets) {
            TweetListPresenter.this.tweets.addAll(tweets);
            TweetListPresenter.this.showTweetCollectionInView(tweets, false);
        }
    }

    private final class SearchTweetListSubscriber extends DefaultSubscriber<List<Tweet>> {

        @Override
        public void onError(Throwable e) {
            TweetListPresenter.this.hideViewLoading();
            TweetListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
        }

        @Override
        public void onNext(List<Tweet> tweets) {
            TweetListPresenter.this.tweets = tweets;
            TweetListPresenter.this.hideViewLoading();
            TweetListPresenter.this.hideEmptyView();
            if (tweets.size() == 0) {
                TweetListPresenter.this.showEmptyView();
            } else {
                TweetListPresenter.this.showTweetCollectionInView(tweets, true);
            }
        }
    }

}