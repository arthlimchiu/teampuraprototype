package symph.teampuraprototype.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import symph.teampuraprototype.R;

/**
 * Created by shang on 7/16/2015.
 */
public class ChannelsAdapter extends RecyclerView.Adapter<ChannelsAdapter.ChannelViewHolder> {

    private List<String> channels = new ArrayList<>();
    private List<String> recents = new ArrayList<>();

    public interface OnChannelClickListener {
        void onChannelClick(String title, int position);
    }

    OnChannelClickListener mCallback;

    public ChannelsAdapter(OnChannelClickListener listener) {
        channels.add("general");
        channels.add("klik");
        channels.add("random");
        channels.add("sports-activities");
        channels.add("android");
        channels.add("java");
        channels.add("javascript");
        channels.add("html5");
        channels.add("wordpress");
        channels.add("php");
        channels.add("python");
        channels.add("angular");
        channels.add("laravel");
        channels.add("ios");
        channels.add("objective-c");
        channels.add("swift");
        channels.add("css");
        channels.add("drupal");
        channels.add("apis");
        channels.add("c");
        channels.add("c++");

        recents.add("Hi");
        recents.add("Hello");
        recents.add("Good Morning!");
        recents.add("You too!");
        recents.add("How are you?");
        recents.add("Fine, thank you.");
        recents.add("Are you doing okay?");
        recents.add("Yes!");
        recents.add("Get Well Soon!");
        recents.add("Happy Birthday!");
        recents.add("Thanks!");
        recents.add("Merry Christmas!");
        recents.add("Merry Christmas too!");
        recents.add("Happy New Year!");
        recents.add("What's your new year's resolution?");
        recents.add("I'll be a good person.");
        recents.add("That's really hard. HAHA!");
        recents.add("Oh come on!");
        recents.add("I'm just kidding. haha");
        recents.add("Yeah right...");
        recents.add("Really!");

        mCallback = listener;
    }

    @Override
    public ChannelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_channel, parent, false);
        return new ChannelViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ChannelViewHolder holder, final int position) {
        holder.name.setText(channels.get(position));
        holder.recent.setText(recents.get(position));
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.onChannelClick(channels.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return channels.size();
    }

    public static class ChannelViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView recent;
        View parent;

        public ChannelViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.item_channel_name);
            recent = (TextView) view.findViewById(R.id.item_channel_recent);
            parent = view;
        }
    }
}
