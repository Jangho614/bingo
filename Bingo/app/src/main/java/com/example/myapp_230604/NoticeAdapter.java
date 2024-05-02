package com.example.myapp_230604;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ViewHolder> implements Filterable {

    private ArrayList<Item> items;
    private ArrayList<Item> itemsFull;

    public NoticeAdapter() {
        this.items = new ArrayList<>();
        this.itemsFull = new ArrayList<>();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, contents;

        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.textView);
            contents = view.findViewById(R.id.textView2);
        }

        public void setItem(Item item) {
            title.setText(item.title);
            contents.setText(item.contents);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.notice_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Item item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Item item) {
        items.add(item);
        itemsFull.add(item);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return itemFilter;
    }

    private Filter itemFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Item> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(itemsFull);
            } else {
                String[] keywords = constraint.toString().toLowerCase().split("\\s+");

                for (Item item : itemsFull) {
                    boolean containsAllKeywords = true;
                    for (String keyword : keywords) {
                        if (!item.title.toLowerCase().contains(keyword)) {
                            containsAllKeywords = false;
                            break;
                        }
                    }
                    if (containsAllKeywords) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            items.clear();
            items.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public static class Item {
        String title, contents;

        public Item(String title, String contents) {
            this.title = title;
            this.contents = contents;
        }
    }
}
