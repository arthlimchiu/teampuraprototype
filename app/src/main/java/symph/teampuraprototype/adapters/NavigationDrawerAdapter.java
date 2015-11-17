package symph.teampuraprototype.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import symph.teampuraprototype.R;

public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.NavItemHolder> {

    private String[] mTitles = new String[]{
            "Channels",
            "Private Groups",
            "Direct Messages",
            "Settings",
            "About",
            "Sign Out"
    };

    private int[] mIcons = new int[]{
            R.drawable.ic_chat_bubble_black_24dp,
            R.drawable.ic_group_black_24dp,
            R.drawable.ic_send_black_24dp,
            R.drawable.ic_settings_black_24dp,
            R.drawable.ic_build_black_24dp,
            R.drawable.ic_close_black_24dp
    };

    public interface OnNavAdapterItemSelectedListener {
        void onNavAdapterItemSelected(int position);
    }

    OnNavAdapterItemSelectedListener mCallback;

    private int mCurrentSelectionPosition = 0;

    public NavigationDrawerAdapter(OnNavAdapterItemSelectedListener listener) {
        mCallback = listener;
    }

    @Override
    public NavItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_list_item, parent, false);
        return new NavItemHolder(v);
    }

    @Override
    public void onBindViewHolder(NavItemHolder holder, final int position) {
        holder.image.setImageResource(mIcons[position]);
        holder.name.setText(mTitles[position]);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.onNavAdapterItemSelected(position);
            }
        });
        holder.setActivated(mCurrentSelectionPosition == position);

    }

    @Override
    public int getItemCount() {
        return mTitles.length;
    }

    public void selectItem(int position) {
        mCurrentSelectionPosition = position;
        notifyDataSetChanged();
    }

    public static class NavItemHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;
        View parent;

        public NavItemHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.nav_list_item_image);
            name = (TextView) view.findViewById(R.id.nav_list_item_name);
            parent = view;
        }

        public void setActivated(boolean isActivated) {
            if (isActivated) {
                parent.setBackgroundColor(parent.getContext().getResources().getColor(R.color.nav_item_highlight));
            } else {
                parent.setBackgroundColor(parent.getContext().getResources().getColor(android.R.color.white));
            }
        }
    }
}
