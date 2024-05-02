package com.example.myapp_230604;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> implements Filterable {

    private ArrayList<Item> items;
    private ArrayList<Item> itemsFull;

    public VideoAdapter() {
        this.items = new ArrayList<>();
        this.itemsFull = new ArrayList<>();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView id, time, processing;

        public ViewHolder(View view) {
            super(view);
            id = view.findViewById(R.id.textView);
            time = view.findViewById(R.id.textView2);
            processing = view.findViewById(R.id.textView7);
        }

        public void setItem(Item item) {
            id.setText(item.id);
            time.setText(item.time);
            if(item.processing.equals("처리완료")) {
                processing.setTextColor(Color.rgb(60, 60, 255));
            }
            if(item.processing.equals("처리필요")){
                processing.setTextColor(Color.rgb(255, 60, 60));
            }
            processing.setText(item.processing);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.video_layout, viewGroup, false);

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
                        if (!item.printItem().toLowerCase().contains(keyword)) {
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
        String id, time, processing;

        public Item(String id, String time, String processing) {
            this.id = id;
            this.time = time;
            this.processing = processing;
        }

        public String printItem() {
            return id + ", " + time + ", " + processing;
        }
    }
}
