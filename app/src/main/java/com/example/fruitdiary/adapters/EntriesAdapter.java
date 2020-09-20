package com.example.fruitdiary.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fruitdiary.R;
import com.example.fruitdiary.models.Entry;

import java.util.List;

public class EntriesAdapter extends  RecyclerView.Adapter<EntriesAdapter.MyViewHolder> {

    Activity activity;
    List<Entry> entryList;
    AdapterGlue adapterGlue;

    public EntriesAdapter(Activity activity, List<Entry> entryList, AdapterGlue adapterGlue){
        this.activity = activity;
        this.entryList = entryList;
        this.adapterGlue = adapterGlue;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = activity.getLayoutInflater().inflate(R.layout.entry_item, parent, false);
        return new EntriesAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setEntry(entryList.get(position));
    }

    @Override
    public int getItemCount() {
        return entryList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private RecyclerView fruitRecyclerView;
        private TextView entryDate;
        private Entry entry;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            fruitRecyclerView = itemView.findViewById(R.id.entry_fruit_list);
            entryDate = itemView.findViewById(R.id.entry_date);
        }

        @Override
        public void onClick(View v) {
            adapterGlue.getEntry(entry);
        }

        public void setEntry(Entry entry){
            this.entry = entry;
            entryDate.setText(entry.getDate());
            EditFruitAdapter editFruitAdapter = new EditFruitAdapter(activity, entry.getFruitList(), false);
            fruitRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
            fruitRecyclerView.setAdapter(editFruitAdapter);
        }
    }
}
