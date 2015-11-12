package com.feragusper.tweetsfinder.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.feragusper.tweetsfinder.R;
import com.feragusper.tweetsfinder.model.Tweet;
import com.feragusper.tweetsfinder.presenter.TweetListPresenter;
import com.feragusper.tweetsfinder.view.TweetListView;
import com.feragusper.tweetsfinder.view.adapter.TweetListAdapter;
import com.feragusper.tweetsfinder.view.adapter.TweetsLayoutManager;

import java.util.ArrayList;
import java.util.Collection;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author Fernando.Perez
 * @since 1.0
 * <p/>
 * Fragment that shows a tweet list based on a search term
 */
public class TweetListFragment extends Fragment implements TweetListView {

    private static final String EXTRA_SEARCH_TERMS = "com.feragusper.tweetsfinder.extra.search.terms";

    @InjectView(R.id.rv_tweets)
    RecyclerView rv_tweets;
    @InjectView(R.id.rl_progress)
    RelativeLayout rl_progress;
    @InjectView(R.id.rl_empty_view)
    View emptyView;

    private TweetListPresenter presenter;
    private TweetListAdapter tweetsAdapter;
    private String searchTerms;

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
        if (savedInstanceState == null) {
            presenter = new TweetListPresenter();
            searchTerms = getArguments().getString(EXTRA_SEARCH_TERMS);
        }
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_tweet_list, container, false);

        ButterKnife.inject(this, fragmentView);

        final TweetsLayoutManager tweetsLayoutManager = new TweetsLayoutManager(getActivity());
        rv_tweets.setLayoutManager(tweetsLayoutManager);

        tweetsAdapter = new TweetListAdapter(getActivity(), new ArrayList<Tweet>());
        tweetsAdapter.setOnItemClickListener(new TweetListAdapter.OnItemClickListener() {
            @Override
            public void onTweetItemClicked(Tweet tweet) {
                showToastMessage(getString(R.string.not_implemented));
            }
        });
        rv_tweets.setAdapter(tweetsAdapter);
        rv_tweets.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int visibleItemCount = tweetsLayoutManager.getChildCount();
                int totalItemCount = tweetsLayoutManager.getItemCount();
                int pastVisiblesItems = tweetsLayoutManager.findFirstVisibleItemPosition();
                if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                    presenter.onLastItemDisplayed();
                }
            }
        });

        if (savedInstanceState != null) {
            tweetsAdapter.setTweetCollection(presenter.getTweetCollection(), false);
        }

        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.setView(this);

        if (savedInstanceState == null) {
            presenter.initialize(searchTerms);
        }
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
    public void renderTweetList(Collection<Tweet> tweetCollection, boolean clear) {
        tweetsAdapter.setTweetCollection(tweetCollection, clear);
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
    public void showError(String message) {
        showToastMessage(message);
    }

    @Override
    public void showEmptyView() {
        emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyView() {
        emptyView.setVisibility(View.GONE);
    }

    /**
     * Shows a {@link Toast} message.
     *
     * @param message An string representing a message to be shown.
     */

    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    public void newSearch(String searchTerms) {
        this.searchTerms = searchTerms;
        presenter.initialize(searchTerms);
    }
}