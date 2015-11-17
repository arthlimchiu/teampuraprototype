package symph.teampuraprototype.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import symph.teampuraprototype.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFragment extends Fragment {


    public ContentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Home");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_content, container, false);
        ViewPager viewPager = (ViewPager) v.findViewById(R.id.content_pager);

        Adapter adapter = new Adapter(getChildFragmentManager(), getActivity());
        adapter.addFragment(new ChannelsFragment(), "Channels");
        adapter.addFragment(new UsersFragment(), "Direct Messages");
//        adapter.addFragment(new CompletedFragment(), "RECENTLY COMPLETED TASKS");
//        adapter.addFragment(new FileFragment(), "FILES");
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.content_tabs);
        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(adapter.getTabView(i));
        }

        return v;
    }

    static class Adapter extends FragmentPagerAdapter {

        private Context context;
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mTitles = new ArrayList<>();
        private int[] mIcons = new int[]{
                R.drawable.ic_forum_white_24dp,
                R.drawable.ic_email_white_24dp
        };

        public Adapter(FragmentManager fragmentManager, Context context) {
            super(fragmentManager);
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mTitles.add(title);
        }

        public View getTabView(int position) {
            View v = LayoutInflater.from(context).inflate(R.layout.tab_view, null);

            ImageView image = (ImageView) v.findViewById(R.id.tab_image);
            image.setImageResource(mIcons[position]);
            return v;
        }
    }
}
