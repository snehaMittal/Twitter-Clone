package com.javahelps.twitter;


import android.os.Bundle;
import android.os.StatFs;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.internal.TwitterApi;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.ListService;
import com.twitter.sdk.android.core.services.StatusesService;
import com.twitter.sdk.android.tweetui.FixedTweetTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    ListView listView ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        listView = (ListView) view.findViewById(R.id.homelist);
        getActivity().setTitle("Home");
        Retrofit retrofit=new Retrofit.Builder().baseUrl("https://api.twitter.com")
                .addConverterFactory(GsonConverterFactory.create()).build();
        ListService service=retrofit.create(ListService.class);
        TwitterSession session=  TwitterCore.getInstance().getSessionManager().getActiveSession();
        TwitterApiClient client = new TwitterApiClient(session);
        StatusesService statusService = client.getStatusesService();
        Call<List<Tweet>> tweets = statusService.homeTimeline(null,
                null, null, null, null, null, null);

        tweets.enqueue(new com.twitter.sdk.android.core.Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result) {
                List<Tweet> tweetList = result.data ;
                Tweet tweet=tweetList.get(0);

                final FixedTweetTimeline timeline = new FixedTweetTimeline.Builder()
                        .setTweets(result.data)
                        .build();
                final CustomListAdapter adapter = new CustomListAdapter(getContext(), timeline);

                listView.setAdapter(adapter);
            }

            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view ;
    }

}
