package symph.teampuraprototype.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import symph.teampuraprototype.R;
import symph.teampuraprototype.adapters.DirectMessagesAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class DirectMessagesFragment extends Fragment {


    public DirectMessagesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getActivity().setTitle("Direct Messages");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_direct_messages, container, false);
        RecyclerView list = (RecyclerView) v.findViewById(R.id.fragment_direct_messages_list);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        ((LinearLayoutManager)list.getLayoutManager()).setStackFromEnd(true);
        list.setAdapter(new DirectMessagesAdapter());

        return v;
    }


}
