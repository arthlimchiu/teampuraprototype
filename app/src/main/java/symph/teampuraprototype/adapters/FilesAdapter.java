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
 * Created by shang on 7/13/2015.
 */
public class FilesAdapter extends RecyclerView.Adapter<FilesAdapter.FileViewHolder> {

    private List<String> files = new ArrayList<>();

    public FilesAdapter() {
        files.add("noooo.gif");
        files.add("Screen Shot 2015-07-09");
        files.add("Sample.jpg");
        files.add("Happy.png");
        files.add("Sad.jpg");
        files.add("moweh.jpg");
        files.add("Screen Shot 2015-07-10");
        files.add("Screen Shot 2015-07-11");
        files.add("Screen Shot 2015-07-12");
        files.add("Screen Shot 2015-07-13");
        files.add("Screen Shot 2015-07-14");
        files.add("Screen Shot 2015-07-15");
        files.add("LEADERSHIP_SKILLS.jpg");
        files.add("wd_quote_0031.jpg");
        files.add("wd_quote_0028.jpg");
        files.add("Office-Hallway-Posters.jpg");
        files.add("a-preview");
        files.add("no.jpg");
        files.add("IMG_20150624.jpg");
        files.add("1.3min.jpg");
        files.add("Good_Noodles.jpg");
    }

    @Override
    public FileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.file_list_item, parent, false);
        return new FileViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FileViewHolder holder, int position) {
        holder.image.setImageResource(R.mipmap.ic_launcher);
        holder.filename.setText(files.get(position));
    }

    @Override
    public int getItemCount() {
        return files.size();
    }

    public static class FileViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView filename;

        public FileViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.file_list_item_image);
            filename = (TextView) view.findViewById(R.id.file_list_item_name);
        }
    }
}
