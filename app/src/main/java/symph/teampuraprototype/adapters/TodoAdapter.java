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
public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder>   {

    private List<String> messages = new ArrayList<>();

    public TodoAdapter() {
        messages.add("@albert @richard @keyrr ang mga tasks na ako gibuhat");
        messages.add("#bug so you cannot delete a task?");
        messages.add("@albert #bug di ma change ang ako gi-edit na task.");
        messages.add("#bug switching channels doesn't work");
        messages.add("#bug changing username will disable highlighting");
        messages.add("#bug @albert cgeg alert nga nai ni message nako bsag wala");
        messages.add("#bug when new user is invited in a channel the user cannont click");
        messages.add("#bug when new channel is created, uninvited users receive notifications");
        messages.add("#bug same sa taas pero for private groups");
        messages.add("#bug @albert when scanning for the previous messages it heads me back");
        messages.add("#bug fireworks");
        messages.add("#bug I'm seeing some weird stuff in mozilla :)");
        messages.add("#bug when looking at a long task, dili siya complete kong imo na e view");
        messages.add("@albert #do filter action items, by assigned user :)");
        messages.add("#do implement reply feature");
        messages.add("#do @albert indicate below who is or are currently typing");
        messages.add("#do quote a message from a different user.");
        messages.add("#do polling system for teampura @albert @richard @keyrr @kent");
        messages.add("@albert #do ability to delete an uploaded file");
        messages.add("#do make teampura responsive");
        messages.add("#do there are few errors in the console");
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_list_item, parent, false);
        return new TodoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        holder.image.setImageResource(R.mipmap.ic_launcher);
        holder.todo.setText(messages.get(position));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public static class TodoViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView todo;

        public TodoViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.todo_list_item_image);
            todo = (TextView) view.findViewById(R.id.todo_list_item_message);
        }

    }
}
