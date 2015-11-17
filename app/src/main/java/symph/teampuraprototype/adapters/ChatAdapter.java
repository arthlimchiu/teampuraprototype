package symph.teampuraprototype.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import symph.teampuraprototype.R;

/**
 * Created by shang on 7/9/2015.
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private List<String> messages;

    public ChatAdapter(List<String> messages) {
        this.messages = messages;
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_list_item, parent, false);
        return new ChatViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        holder.image.setImageResource(R.mipmap.ic_launcher);
        holder.message.setText(messages.get(position));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }



    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView message;

        public ChatViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.chat_list_item_image);
            message = (TextView) view.findViewById(R.id.chat_list_item_message);
        }
    }
}
