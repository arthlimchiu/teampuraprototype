package symph.teampuraprototype.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import symph.teampuraprototype.R;
import symph.teampuraprototype.adapters.ChannelsAdapter;
import symph.teampuraprototype.adapters.NotJoinedChannelsAdapter;
import symph.teampuraprototype.fragments.ChannelsFragment;

public class JoinChannelActivity extends AppCompatActivity implements ChannelsAdapter.OnChannelClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_channel);
        setTitle("Join Channel");

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.not_joined_channels_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new NotJoinedChannelsAdapter(this));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_join_channel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onChannelClick(String title, int position) {
        Intent i = new Intent(this, ChannelActivity.class);
        i.putExtra("title", title);
        i.putExtra("position", position);
        startActivity(i);
    }
}
