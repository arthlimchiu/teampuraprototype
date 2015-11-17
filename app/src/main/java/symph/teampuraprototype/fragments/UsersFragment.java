package symph.teampuraprototype.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import symph.teampuraprototype.R;
import symph.teampuraprototype.activities.ChannelActivity;
import symph.teampuraprototype.adapters.DirectMessagesAdapter;
import symph.teampuraprototype.adapters.UsersAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsersFragment extends Fragment implements UsersAdapter.OnUserClickListener{


    public UsersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_users, container, false);
        RecyclerView list = (RecyclerView) v.findViewById(R.id.fragment_users_list);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        ((LinearLayoutManager)list.getLayoutManager()).setStackFromEnd(true);
        list.setAdapter(new UsersAdapter(this));
        return v;
    }


    @Override
    public void onUserClick(String name, int position) {
        Intent i = new Intent(getActivity(), ChannelActivity.class);
        i.putExtra("title", name);
        i.putExtra("position", position);
        startActivity(i);
    }
}
