package com.javahelps.twitter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

public class PosttweetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composer);

        final TwitterSession session = TwitterCore.getInstance().getSessionManager()
                .getActiveSession();

//        TweetComposer.Builder builder = new TweetComposer.Builder(getBaseContext())
//                .text("just setting up my Twitter Kit.");
//        builder.show();

    }
}
