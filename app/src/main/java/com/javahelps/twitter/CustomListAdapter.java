package com.javahelps.twitter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.Timeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;

/**
 * Created by Sneha on 04-11-2017.
 */

public class CustomListAdapter extends TweetTimelineListAdapter {
    Context mContext;
    /**
     * Constructs a TweetTimelineListAdapter for the given Tweet Timeline.
     *
     * @param context  the context for row views.
     * @param timeline a Timeline&lt;Tweet&gt; providing access to Tweet data items.
     * @throws IllegalArgumentException if context is null
     */
    public CustomListAdapter(Context context, Timeline<Tweet> timeline) {
        super(context, timeline);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "clicked", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
