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
 * Created by shang on 7/21/2015.
 */
public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    private List<String> users = new ArrayList<>();

    public interface OnUserClickListener {
        void onUserClick(String name, int position);
    }

    OnUserClickListener mCallback;

    public UsersAdapter(OnUserClickListener listener) {
        users.add("me");
        users.add("albert");
        users.add("ashley");
        users.add("bryant");
        users.add("chip");
        users.add("clint");
        users.add("dan");
        users.add("dave");
        users.add("erika");
        users.add("faye");
        users.add("francis");
        users.add("garri");
        users.add("george");
        users.add("irene");
        users.add("jan");
        users.add("johhan");
        users.add("kent");
        users.add("keyrr");
        users.add("lawrence");
        users.add("leonard");
        users.add("lotlot");
        users.add("michael");
        users.add("nell");
        users.add("nicole");
        users.add("raff");
        users.add("richard");
        users.add("richmond");
        users.add("rodelm");
        users.add("ronnel");

        mCallback = listener;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.direct_message_list_item, parent, false);
        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, final int position) {
        holder.image.setImageResource(R.mipmap.ic_launcher);
        holder.name.setText(users.get(position));
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.onUserClick(users.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;
        View parent;

        public UserViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.direct_message_list_item_image);
            name = (TextView) view.findViewById(R.id.direct_message_list_item_message);
            parent = view;
        }
    }
}
