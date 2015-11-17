package symph.teampuraprototype.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import symph.teampuraprototype.R;
import symph.teampuraprototype.adapters.DirectMessagesAdapter;

public class CreateChannelActivity extends AppCompatActivity {

    EditText mChannelName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_channel);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mChannelName = (EditText) findViewById(R.id.et_create_channel);

        RecyclerView list = (RecyclerView) findViewById(R.id.friends_list);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(new DirectMessagesAdapter());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_channel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_create_channel:
                Intent i = new Intent(this, ChannelActivity.class);
                i.putExtra("title", mChannelName.getText().toString());
                startActivity(i);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
