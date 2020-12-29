package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Responsible for displaying data from the model into a row in the recycler view
 */
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    public interface OnClickListener{
        void onItemClicked(int position);
    }

    public interface OnLongClickListener{
        void onItemLongClicked(int position);
    }

    List<String> items;
    OnLongClickListener longClickListener;
    OnClickListener clickListener;

    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener,
                        OnClickListener clickListener){
        this.items = items;
        this.longClickListener = longClickListener;
        this.clickListener = clickListener;
    }

    /**
     * Responsible for creating a view holder. Uses layoout inflater to inflate view
     * @param parent The parent in the context to get the layout inflater
     * @param viewType An integer representing the view type
     * @return a view holder that is the inflated
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//        Use LayoutInflater to inflate view
        View toDoView = LayoutInflater.from(parent.getContext()).inflate
                (android.R.layout.simple_list_item_1,
                parent, false);

//        Wrap it inside a ViewHolder and return
        return new ViewHolder(toDoView);
    }

    /**
     * Responsible for binding data to a particular view holder
     * @param holder A view holder item
     * @param position an integer representing the position
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Grab the item at the position
        String item = items.get(position);

//        Bind the item into the specified view holder
        holder.bind(item);
    }

    /**
     * Tells the recycler view how many items there are in the list
     * @return
     */
    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * Container to provide easy access to views that represent each row of the list
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        /**
         * Updates the view inside the view holder with the given data
         * @param item a string representing the data
         */
        public void bind(String item) {
            tvItem.setText(item);

            tvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClicked(getAdapterPosition());
                }
            });

            tvItem.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v){
//                    Remove the item from the recycler view
//                    Notify the listener about which item is long pressed
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
          }
          );
        }
    }
}
