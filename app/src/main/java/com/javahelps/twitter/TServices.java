package com.javahelps.twitter;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.services.ListService;

/**
 * Created by Sneha on 31-10-2017.
 */

public class TServices  extends TwitterApiClient{
    public TServices(TwitterSession session) {
        super(session);
    }

    public ListService getListService(){
        return getService(ListService.class);
    }
}
