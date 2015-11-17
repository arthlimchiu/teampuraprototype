package symph.teampuraprototype.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import symph.teampuraprototype.R;

/**
 * Created by shang on 7/9/2015.
 */
public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.ImageTextViewHolder> {

    @Override
    public ImageTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ImageTextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageTextViewHolder holder, int position) {
        holder.image.setImageResource(R.mipmap.ic_launcher);
        holder.label.setText("Sample Text " + position);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public static class ImageTextViewHolder extends RecyclerView.ViewHolder  {
        ImageView image;
        TextView label;

        public ImageTextViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.list_item_image);
            label = (TextView) view.findViewById(R.id.list_item_label);
        }
    }
}
