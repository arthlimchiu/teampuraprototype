package symph.teampuraprototype.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import symph.teampuraprototype.R;

/**
 * Created by shang on 7/21/2015.
 */
public class NotJoinedChannelsAdapter extends RecyclerView.Adapter<NotJoinedChannelsAdapter.ChannelViewHolder> {

    List<String> channels = new ArrayList<>();

    ChannelsAdapter.OnChannelClickListener mCallback;

    public NotJoinedChannelsAdapter(ChannelsAdapter.OnChannelClickListener listener) {
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
        View parent;

        public ChannelViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.item_channel_name);
            parent = view;
        }
    }
}
