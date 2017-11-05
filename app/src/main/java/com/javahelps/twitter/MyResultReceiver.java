package com.javahelps.twitter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.twitter.sdk.android.tweetcomposer.TweetUploadService;

/**
 * Created by Sneha on 05-11-2017.
// */
//public class MyResultReceiver extends BroadcastReceiver {
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        if (TweetUploadService.UPLOAD_SUCCESS.equals(intent.getAction())) {
//            // success
//            final Long tweetId = intentExtras.getLong(TweetUploadService.EXTRA_TWEET_ID);
//        } else if (TweetUploadService.UPLOAD_FAILURE.equals(intent.getAction())) {
//            // failure
//            final Intent retryIntent = intentExtras.getParcelable(TweetUploadService.EXTRA_RETRY_INTENT);
//        } else if (TweetUploadService.TWEET_COMPOSE_CANCEL.equals(intent.getAction())) {
//            // cancel
//        }
//    }
//}