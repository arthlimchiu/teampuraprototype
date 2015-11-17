package symph.teampuraprototype.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import symph.teampuraprototype.R;
import symph.teampuraprototype.adapters.FilesAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FileFragment extends Fragment {


    public FileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_file, container, false);
        RecyclerView list = (RecyclerView) v.findViewById(R.id.file_list);
        list.setLayoutManager(new LinearLayoutManager(list.getContext()));
        list.setAdapter(new FilesAdapter());
        return v;
    }


}
