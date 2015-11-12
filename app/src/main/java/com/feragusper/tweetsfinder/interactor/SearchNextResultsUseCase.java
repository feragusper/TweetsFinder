package com.feragusper.tweetsfinder.interactor;

import com.feragusper.tweetsfinder.model.Tweet;
import com.feragusper.tweetsfinder.repository.TweetRepository;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import twitter4j.TwitterException;

/**
 * @author Fernando.Perez
 * @since 1.0
 */
public class SearchNextResultsUseCase extends UseCase {

    @Override
    protected Observable buildUseCaseObservable() {
        return Observable.create(new Observable.OnSubscribe<List<Tweet>>() {
            @Override
            public void call(Subscriber<? super List<Tweet>> subscriber) {
                try {
                    subscriber.onNext(TweetRepository.getInstance().getNextResults());
                } catch (TwitterException e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
