package com.feragusper.tweetsfinder.view.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.feragusper.tweetsfinder.R;
import com.feragusper.tweetsfinder.view.fragment.TweetListFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author Fernando.Perez
 * @since 1.0
 *
 * Main Activity of the Application
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onNewIntent(Intent intent) {
        handleSearchIntent(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        handleSearchIntent(getIntent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);

        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchMenuItem);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint(getString(R.string.search_hint));

        return super.onCreateOptionsMenu(menu);
    }

    private void handleSearchIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            final TweetListFragment fragment = (TweetListFragment) getSupportFragmentManager().findFragmentByTag(TweetListFragment.class.getSimpleName());
            if (fragment == null) {
                final TweetListFragment tweetListFragment = TweetListFragment.newInstance(intent.getStringExtra(SearchManager.QUERY));
                getSupportFragmentManager().beginTransaction().add(R.id.fm_content_view, tweetListFragment, TweetListFragment.class.getSimpleName()).commit();
            } else {
                fragment.newSearch(intent.getStringExtra(SearchManager.QUERY));
            }
        }
    }

}