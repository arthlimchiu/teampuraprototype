package symph.teampuraprototype.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import symph.teampuraprototype.R;
import symph.teampuraprototype.fragments.ChatFragment;
import symph.teampuraprototype.fragments.CompletedFragment;
import symph.teampuraprototype.fragments.FileFragment;
import symph.teampuraprototype.fragments.TodoFragment;

public class ChannelActivity extends AppCompatActivity {

    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);

        fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.tab_content_container, new ChatFragment())
                .commit();
        //ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        setTitle(intent.getStringExtra("title"));

        Adapter adapter = new Adapter(getSupportFragmentManager(), this);
        adapter.addFragment(new ChatFragment(), "CHAT");
        adapter.addFragment(new TodoFragment(), "TASKS TO DO");
        adapter.addFragment(new CompletedFragment(), "RECENTLY COMPLETED TASKS");
        adapter.addFragment(new FileFragment(), "FILES");
        //viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        //tabLayout.setupWithViewPager(viewPager);

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_chat_white_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_assignment_white_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_assignment_turned_in_white_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_folder_white_24dp));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        fm.beginTransaction()
                                .replace(R.id.tab_content_container, new ChatFragment()).commit();
                        break;
                    case 1:
                        fm.beginTransaction()
                                .replace(R.id.tab_content_container, new TodoFragment()).commit();
                        break;
                    case 2:
                        fm.beginTransaction()
                                .replace(R.id.tab_content_container, new CompletedFragment()).commit();
                        break;
                    case 3:
                        fm.beginTransaction()
                                .replace(R.id.tab_content_container, new FileFragment()).commit();
                        break;
                }
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_channel, menu);
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

    static class Adapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mTitles = new ArrayList<>();
        private Context context;
        private int[] mIcons = new int[]{
                R.drawable.ic_chat_white_24dp,
                R.drawable.ic_assignment_white_24dp,
                R.drawable.ic_assignment_turned_in_white_24dp,
                R.drawable.ic_folder_white_24dp
        };

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
//            Drawable image = context.getResources().getDrawable(mIcons[position]);
//            image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
//            SpannableString sb = new SpannableString(" ");
//            ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
//            sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return mTitles.get(position);
        }

        public View getTabView(int position) {
            View v = LayoutInflater.from(context).inflate(R.layout.tab_view, null);
//            TextView tv = (TextView) v.findViewById(R.id.tab_name);
//            tv.setText(mTitles.get(position));
            ImageView image = (ImageView) v.findViewById(R.id.tab_image);
            image.setImageResource(mIcons[position]);
            return v;
        }
    }
}
