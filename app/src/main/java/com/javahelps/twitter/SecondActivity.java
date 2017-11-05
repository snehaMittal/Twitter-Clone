package com.javahelps.twitter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import retrofit2.Call;

public class SecondActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView ProfilrPic;
    TextView fullname,followers,following;
    User userresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TwitterSession session=  TwitterCore.getInstance().getSessionManager().getActiveSession();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Intent intent = new Intent(SecondActivity.this , PosttweetActivity.class);

                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent = getIntent();


        View hview=navigationView.getHeaderView(0);
        ProfilrPic=(ImageView) hview.findViewById(R.id.ProfilePic);
        followers=(TextView) hview.findViewById(R.id.followers);
        following=(TextView) hview.findViewById(R.id.following);
        fullname=(TextView) hview.findViewById(R.id.FullName_nav);
//        Log.i("Name",FullName.getText().toString());
//        UserId=(TextView) findViewById(R.id.UserId);
//
//        UserId.setText(session.getUserName());


        Call<User> user=TwitterCore.getInstance().getApiClient(session).getAccountService().verifyCredentials(true ,true,false);
        user.enqueue(new com.twitter.sdk.android.core.Callback<User>() {
            @Override
            public void success(Result<User> result) {
                Log.i("success",result.response.code()+"");
                userresult = result.data;
                fullname.setText(userresult.name);
                followers.setText(userresult.followersCount+" Followers");
                following.setText(userresult.friendsCount+" Following");
                String url=userresult.profileImageUrl;
                Picasso.with(getApplicationContext()).load(url).into(ProfilrPic);

            }

            @Override
            public void failure(TwitterException exception) {

            }
        });

        CustomFragmentAdapter customFragmentAdapter=new CustomFragmentAdapter(getSupportFragmentManager());
        ViewPager viewPager = (ViewPager) findViewById(R.id.view);
        TabLayout tabLayout= (TabLayout) findViewById(R.id.tablayout);
        viewPager.setAdapter(customFragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.profile) {
            // Handle the c
            // amera action
        }  else if (id == R.id.help) {
            Uri uri = Uri.parse("http://support.twitter.com/#topic_223");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);

        } else if (id == R.id.logout) {

            Intent intent = new Intent(this , MainActivity.class);
            finish();
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
