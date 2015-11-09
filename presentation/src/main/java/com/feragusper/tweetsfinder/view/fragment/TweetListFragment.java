package com.feragusper.tweetsfinder.view.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.feragusper.tweetsfinder.R;
import com.feragusper.tweetsfinder.model.Tweet;
import com.feragusper.tweetsfinder.presenter.TweetListPresenter;
import com.feragusper.tweetsfinder.view.TweetListView;
import com.feragusper.tweetsfinder.view.adapter.TweetListAdapter;
import com.feragusper.tweetsfinder.view.adapter.TweetsLayoutManager;

import org.androidannotations.annotations.Extra;

import java.util.ArrayList;
import java.util.Collection;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author Fernando.Perez
 * @since 1.0
 */
public class TweetListFragment extends Fragment implements TweetListView {

    private static final String EXTRA_SEARCH_TERMS = "com.feragusper.tweetsfinder.extra.search.terms";

    @InjectView(R.id.rv_tweets)
    RecyclerView rv_tweets;
    @InjectView(R.id.rl_progress)
    RelativeLayout rl_progress;
    @InjectView(R.id.rl_retry)
    RelativeLayout rl_retry;
    @InjectView(R.id.bt_retry)
    Button bt_retry;

    @Extra(EXTRA_SEARCH_TERMS)
    String searchTerms;

    private TweetListPresenter presenter;

    public static TweetListFragment newInstance(String searchTerms) {
        final TweetListFragment tweetListFragment = new TweetListFragment();

        Bundle arguments = new Bundle();
        arguments.putString(EXTRA_SEARCH_TERMS, searchTerms);
        tweetListFragment.setArguments(arguments);

        return tweetListFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new TweetListPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_tweet_list, container, false);

        ButterKnife.inject(this, fragmentView);
        rv_tweets.setLayoutManager(new TweetsLayoutManager(getActivity()));

        TweetListAdapter tweetsAdapter = new TweetListAdapter(getActivity(), new ArrayList<Tweet>());
        tweetsAdapter.setOnItemClickListener(new TweetListAdapter.OnItemClickListener() {
            @Override
            public void onTweetItemClicked(Tweet tweet) {
                // TODO
            }
        });
        rv_tweets.setAdapter(tweetsAdapter);

        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.setView(this);
        presenter.initialize();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void renderTweetList(Collection<Tweet> tweetCollection) {

    }

    @Override
    public void showLoading() {
        rl_progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        rl_progress.setVisibility(View.GONE);
    }

    @Override
    public void showRetry() {
        rl_retry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        rl_retry.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        showToastMessage(message);
    }

    /**
     * Shows a {@link Toast} message.
     *
     * @param message An string representing a message to be shown.
     */
    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}