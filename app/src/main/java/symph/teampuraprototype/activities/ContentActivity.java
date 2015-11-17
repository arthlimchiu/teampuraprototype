package symph.teampuraprototype.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;

import java.util.ArrayList;
import java.util.List;

import symph.teampuraprototype.R;
import symph.teampuraprototype.fragments.ChannelsFragment;
import symph.teampuraprototype.fragments.ContentFragment;
import symph.teampuraprototype.fragments.DirectMessagesFragment;
import symph.teampuraprototype.fragments.NavigationDrawerFragment;

public class ContentActivity extends AppCompatActivity implements NavigationDrawerFragment.OnNavItemSelectedListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private DrawerLayout mDrawerLayout;

    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

//        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        buildGoogleApiClient();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(MenuItem menuItem) {
//                if (menuItem.getItemId() == R.id.nav_direct_messages) {
//                    Intent intent = new Intent(ChannelActivity.this, DirectMessagesActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//                menuItem.setChecked(true);
//                mDrawerLayout.closeDrawers();
//                return true;
//            }
//        });

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.nav_drawer_fragment_container);

        if (fragment == null) {
            fragment = new NavigationDrawerFragment();
            fm.beginTransaction()
                    .add(R.id.nav_drawer_fragment_container, fragment)
                    .commit();
        }

        Fragment contentFrag = fm.findFragmentById(R.id.fragment_content_container);
        if (contentFrag == null) {
            contentFrag = new ContentFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_content_container, contentFrag)
                    .commit();
        }

//        Adapter adapter = new Adapter(getSupportFragmentManager(), this);
//        adapter.addFragment(new ChannelsFragment(), "CHAT");
//        adapter.addFragment(new TodoFragment(), "TASKS TO DO");
//        adapter.addFragment(new CompletedFragment(), "RECENTLY COMPLETED TASKS");
//        adapter.addFragment(new FileFragment(), "FILES");
//        viewPager.setAdapter(adapter);
//
//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(viewPager);
//
//        for (int i = 0; i < tabLayout.getTabCount(); i++) {
//            TabLayout.Tab tab = tabLayout.getTabAt(i);
//            tab.setCustomView(adapter.getTabView(i));
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_channel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onNavItemSelected(int position) {
        switch (position) {
            case 0:
                getSupportFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.fragment_content_container, new ContentFragment())
                        .commit();
                mDrawerLayout.closeDrawers();
                break;
            case 1:
                Toast.makeText(this, "Private Groups touched.", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.fragment_content_container, new DirectMessagesFragment())
                        .commit();
                mDrawerLayout.closeDrawers();
                break;
            case 3:
                Toast.makeText(this, "Settings touched.", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(this, "About touched.", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(this, "Sign Out touched.", Toast.LENGTH_SHORT).show();
                if (mGoogleApiClient.isConnected()) {
                    Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
                    mGoogleApiClient.disconnect();
                    mGoogleApiClient.connect();

                    Intent i = new Intent(this, LoginActivity.class);
//                i.putExtra("signed_out", true);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    finish();
                }
                break;
        }
    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    private void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .build();
    }


    static class Adapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mTitles = new ArrayList<>();
        private Context context;

        public Adapter(FragmentManager fragmentManager, Context context) {
            super(fragmentManager);
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mTitles.add(title);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }

        public View getTabView(int position) {
            View v = LayoutInflater.from(context).inflate(R.layout.custom_tab, null);
            TextView tv = (TextView) v.findViewById(R.id.tab_name);
            tv.setText(mTitles.get(position));
            return v;
        }
    }
}
