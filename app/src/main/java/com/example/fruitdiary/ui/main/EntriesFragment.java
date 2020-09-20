package com.example.fruitdiary.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fruitdiary.R;
import com.example.fruitdiary.adapters.AdapterGlue;
import com.example.fruitdiary.adapters.EntriesAdapter;
import com.example.fruitdiary.adapters.FruitAdapter;
import com.example.fruitdiary.models.Entry;
import com.example.fruitdiary.presenters.EntryPresenter;
import com.example.fruitdiary.server.ServerSync;

import java.util.List;

import retrofit2.Response;

public class EntriesFragment extends Fragment implements AdapterGlue, ServerSync {

    private RecyclerView entriesRecyclerView;
    private RecyclerView selectedEntryFruitsRecyclerView;
    private Button newEntry;
    private Button deleteEntry;
    private Button addEntryFruit;
    private TextView selectedEntryDate;
    private Entry selectedEntry;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        return inflater.inflate(R.layout.entries_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        new EntryPresenter(this).getEntries();
    }

    private void findViews(View view){
        entriesRecyclerView = view.findViewById(R.id.entries_recycler_view);
        selectedEntryFruitsRecyclerView = view.findViewById(R.id.entry_fruit_full_list);
        newEntry = view.findViewById(R.id.new_entry);
        deleteEntry = view.findViewById(R.id.delete_entry);
        addEntryFruit = view.findViewById(R.id.add_fruit);
    }

    private void showEntriesList(List<Entry> entryList){
        EntriesAdapter entriesAdapter = new EntriesAdapter(getActivity(), entryList, this);
        entriesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        entriesRecyclerView.setAdapter(entriesAdapter);
    }

    @Override
    public void getEntry(Entry entry) {

    }

    @Override
    public void sync(Object object) {

    }

    @Override
    public void sync(Response response) {

    }

    @Override
    public void syncEntries(final List<Entry> entries) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showEntriesList(entries);
            }
        });
    }
}
