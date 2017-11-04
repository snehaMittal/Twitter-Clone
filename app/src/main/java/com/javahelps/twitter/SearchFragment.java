package com.javahelps.twitter;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;
import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    ListView listView ;
    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_search, container, false);
        listView = (ListView) view.findViewById(R.id.searchlist);


        TwitterSession session=  TwitterCore.getInstance().getSessionManager().getActiveSession();
        TwitterApiClient client = new TwitterApiClient(session);
        StatusesService statusService = client.getStatusesService();
        Call<List<Tweet>> tweets = statusService.mentionsTimeline(null , null ,
                null , null , null, null );

        tweets.enqueue(new com.twitter.sdk.android.core.Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result) {

                final SearchTimeline searchTimeline = new SearchTimeline.Builder()
                        .query("#hiking")
                        .maxItemsPerRequest(50)
                        .build();

                final TweetTimelineListAdapter adapter =
                        new TweetTimelineListAdapter.Builder(getContext())
                                .setTimeline(searchTimeline)
                                .setViewStyle(R.style.tw__TweetLightWithActionsStyle)
                                .build();

                listView.setAdapter(adapter);
            }

            @Override
            public void failure(TwitterException exception) {

            }
        });
//
//        final SearchTimeline searchTimeline = new SearchTimeline.Builder()
//                .query("#hiking")
//                .maxItemsPerRequest(50)
//                .build();
//
//        final TweetTimelineListAdapter adapter =
//                new TweetTimelineListAdapter.Builder(getContext())
//                        .setTimeline(searchTimeline)
//                        .setViewStyle(R.style.tw__TweetLightWithActionsStyle)
//                        .build();
//
//        listView.setAdapter(adapter);
        return view ;
    }

}