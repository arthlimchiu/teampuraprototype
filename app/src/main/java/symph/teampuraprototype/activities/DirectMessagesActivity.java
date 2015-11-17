package symph.teampuraprototype.activities;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import symph.teampuraprototype.R;
import symph.teampuraprototype.adapters.DirectMessagesAdapter;
import symph.teampuraprototype.fragments.NavigationDrawerFragment;

public class DirectMessagesActivity extends AppCompatActivity implements NavigationDrawerFragment.OnNavItemSelectedListener {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_messages);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(MenuItem menuItem) {
//                if (menuItem.getItemId() == R.id.nav_channels) {
//                    Intent i = new Intent(DirectMessagesActivity.this, ChannelActivity.class);
//                    startActivity(i);
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

        RecyclerView list = (RecyclerView) findViewById(R.id.direct_messages_list);
        list.setLayoutManager(new LinearLayoutManager(list.getContext()));
        list.setAdapter(new DirectMessagesAdapter());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_direct_messages, menu);
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
    public void onNavItemSelected(int position) {
        switch (position) {
            case 0:
                Intent intent = new Intent(DirectMessagesActivity.this, ContentActivity.class);
                startActivity(intent);
                finish();
            case 1:
                Toast.makeText(this, "Private Groups touched.", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "Direct Messages touched.", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this, "Settings touched.", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(this, "About touched.", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                Toast.makeText(this, "Sign Out touched.", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
