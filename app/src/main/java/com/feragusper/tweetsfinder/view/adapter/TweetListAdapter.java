package com.feragusper.tweetsfinder.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.feragusper.tweetsfinder.R;
import com.feragusper.tweetsfinder.model.Tweet;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p/>
 * Adapter that manages a collection of {@link Tweet}.
 */
public class TweetListAdapter extends RecyclerView.Adapter<TweetListAdapter.TweetViewHolder> {

    private final Context context;

    public interface OnItemClickListener {
        void onTweetItemClicked(Tweet tweet);
    }

    private List<Tweet> tweetCollection;
    private final LayoutInflater layoutInflater;

    private OnItemClickListener onItemClickListener;

    public TweetListAdapter(Context context, @NonNull Collection<Tweet> tweetCollection) {
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.tweetCollection = (List<Tweet>) tweetCollection;
    }

    @Override
    public int getItemCount() {
        return tweetCollection.size();
    }

    @Override
    public TweetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TweetViewHolder(layoutInflater.inflate(R.layout.item_tweet, parent, false));
    }

    @Override
    public void onBindViewHolder(TweetViewHolder holder, final int position) {
        final Tweet tweet = this.tweetCollection.get(position);
        holder.message.setText(tweet.getMessage());
        holder.username.setText(tweet.getUser());
        holder.screenname.setText("@" + tweet.getUserScreenName());
        Picasso.with(context).load(tweet.getUserThumbnailURL()).into(holder.thumbnail);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TweetListAdapter.this.onItemClickListener.onTweetItemClicked(tweet);
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setTweetCollection(Collection<Tweet> tweetCollection, boolean clear) {
        this.validateTweetCollection(tweetCollection);
        if (clear) {
            this.tweetCollection.clear();
        }
        this.tweetCollection.addAll(tweetCollection);
        this.notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private void validateTweetCollection(Collection<Tweet> tweetCollection) {
        if (tweetCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    static class TweetViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.message)
        TextView message;

        @InjectView(R.id.username)
        TextView username;

        @InjectView(R.id.screenname)
        TextView screenname;

        @InjectView(R.id.thumbnail)
        ImageView thumbnail;

        public TweetViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
