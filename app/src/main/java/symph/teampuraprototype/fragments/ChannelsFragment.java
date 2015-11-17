package symph.teampuraprototype.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import symph.teampuraprototype.R;
import symph.teampuraprototype.activities.ChannelActivity;
import symph.teampuraprototype.activities.CreateChannelActivity;
import symph.teampuraprototype.activities.JoinChannelActivity;
import symph.teampuraprototype.adapters.ChannelsAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChannelsFragment extends Fragment implements ChannelsAdapter.OnChannelClickListener{


    public ChannelsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getActivity().setTitle("Channels");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_channels, container, false);
        RecyclerView mRecyclerView = (RecyclerView) v.findViewById(R.id.channels_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new ChannelsAdapter(this));

//        final FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
//        fab.hide(false);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                fab.show(true);
////                fab.setShowAnimation(AnimationUtils.loadAnimation(getParentFragment().getActivity(), )));
////                fab.setHideAnimation(AnimationUtils.loadAnimation(getParentFragment().getActivity(), R.anim.hide_to_bottom));
//            }
//        }, 300);

        FloatingActionButton createChannelFab = (FloatingActionButton) v.findViewById(R.id.fab_create_channel);
        createChannelFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), CreateChannelActivity.class);
                startActivity(i);
            }
        });

        FloatingActionButton joinChannelFab = (FloatingActionButton) v.findViewById(R.id.fab_join_channel);
        joinChannelFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), JoinChannelActivity.class);
                startActivity(i);
            }
        });

        final FloatingActionMenu menu = (FloatingActionMenu) v.findViewById(R.id.channel_fab);
        menu.setClosedOnTouchOutside(true);
        menu.hideMenuButton(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                menu.showMenuButton(true);
            }
        }, 300);
        return v;
    }


    @Override
    public void onChannelClick(String title, int position) {
        Intent i = new Intent(getActivity(), ChannelActivity.class);
        i.putExtra("title", title);
        i.putExtra("position", position);
        startActivity(i);
    }
}
