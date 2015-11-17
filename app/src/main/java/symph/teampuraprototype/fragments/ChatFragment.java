package symph.teampuraprototype.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import symph.teampuraprototype.R;
import symph.teampuraprototype.adapters.ChatAdapter;
import symph.teampuraprototype.adapters.TodoAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {

    EditText message;
    List<String> messages = new ArrayList<>();
    ChatAdapter adapter;
    RecyclerView list;

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        messages.add("Hi");
        messages.add("Hello");
        messages.add("Good Morning!");
        messages.add("You too!");
        messages.add("How are you?");
        messages.add("Fine, thank you.");
        messages.add("Are you doing okay?");
        messages.add("Yes!");
        messages.add("Get Well Soon!");
        messages.add("Happy Birthday!");
        messages.add("Thanks!");
        messages.add("Merry Christmas!");
        messages.add("Merry Christmas too!");
        messages.add("Happy New Year!");
        messages.add("What's your new year's resolution?");
        messages.add("I'll be a good person.");
        messages.add("That's really hard. HAHA!");
        messages.add("Oh come on!");
        messages.add("I'm just kidding. haha");
        messages.add("Yeah right...");
        messages.add("Really!");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chat, container, false);

        list = (RecyclerView) v.findViewById(R.id.chat_list);
        list.setLayoutManager(new LinearLayoutManager(list.getContext()));
        ((LinearLayoutManager)list.getLayoutManager()).setStackFromEnd(true);

        message = (EditText) v.findViewById(R.id.et_chat_message);
        Button send = (Button) v.findViewById(R.id.btn_send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!message.getText().toString().equals("")) {
                    messages.add(message.getText().toString());
                    adapter.notifyDataSetChanged();
                    message.setText("");
                    list.scrollToPosition(adapter.getItemCount() - 1);
                }
            }
        });

        adapter = new ChatAdapter(messages);
        list.setAdapter(adapter);
        return v;
    }


}
