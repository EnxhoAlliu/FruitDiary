package com.example.fruitdiary.ui.main;

import android.os.Bundle;
import android.os.Handler;
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
import com.example.fruitdiary.adapters.EditFruitAdapter;
import com.example.fruitdiary.adapters.EntriesAdapter;
import com.example.fruitdiary.adapters.FruitAdapter;
import com.example.fruitdiary.models.Entry;
import com.example.fruitdiary.models.Fruit;
import com.example.fruitdiary.presenters.EntryPresenter;
import com.example.fruitdiary.server.ServerSync;

import java.util.List;

import retrofit2.Response;

public class EntriesFragment extends Fragment implements AdapterGlue, ServerSync {

    private RecyclerView entriesRecyclerView;
    private RecyclerView selectedEntryFruitsRecyclerView;
    private RecyclerView selectFruitRecyclerView;
    private Button newEntry;
    private Button deleteEntry;
    private Button addEntryFruit;
    private Button exitEntryEditing;
    private TextView selectedEntryDate;
    private Entry selectedEntry;
    private View editEntryView;


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
        requestEntriesList();
    }

    private void findViews(View view){
        entriesRecyclerView = view.findViewById(R.id.entries_recycler_view);
        selectedEntryFruitsRecyclerView = view.findViewById(R.id.entry_fruit_full_list);
        selectFruitRecyclerView = view.findViewById(R.id.pick_fruit_list);
        newEntry = view.findViewById(R.id.new_entry);
        deleteEntry = view.findViewById(R.id.delete_entry);
        addEntryFruit = view.findViewById(R.id.add_fruit);
        exitEntryEditing = view.findViewById(R.id.exit_button);
        editEntryView = view.findViewById(R.id.detailed_view);
        selectedEntryDate = view.findViewById(R.id.entry_detail_date);
    }

    private void fillEntriesList(List<Entry> entryList){
        EntriesAdapter entriesAdapter = new EntriesAdapter(getActivity(), entryList, this);
        entriesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        entriesRecyclerView.setAdapter(entriesAdapter);
        //In case their visibility is set to GONE
        entriesRecyclerView.setVisibility(View.VISIBLE);
        newEntry.setVisibility(View.VISIBLE);
    }

    private void requestEntriesList(){
        new EntryPresenter(this).getEntries();
    }

    private void openEntryEditing(Entry entry){
        hideEntriesList();
        fillEditEntryViewWithSelectedEntry(entry);
        setEditModeFunctionality();
        editEntryView.setVisibility(View.VISIBLE);
    }

    private void hideEntriesList(){
        entriesRecyclerView.setVisibility(View.GONE);
        newEntry.setVisibility(View.GONE);
    }

    private void fillEditEntryViewWithSelectedEntry(Entry entry){
        displaySelectedEntryFruitList(entry);
        selectedEntryDate.setText(entry.getDate());
    }

    private void displaySelectedEntryFruitList(Entry entry){
        EditFruitAdapter editFruitAdapter = new EditFruitAdapter(getActivity(), entry, true);
        selectedEntryFruitsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        selectedEntryFruitsRecyclerView.setAdapter(editFruitAdapter);
    }

    private void setEditModeFunctionality(){
        exitEntryEditing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideEditEntryView();
                requestEntriesList();
            }
        });
        deleteEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeEntry();
            }
        });
        addEntryFruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayFruitList();
            }
        });
    }

    private void removeEntry(){
        new EntryPresenter(this).deleteEntry(selectedEntry.getId());
    }

    private void hideEditEntryView(){
        editEntryView.setVisibility(View.GONE);
    }

    private void displayFruitList(){
        FruitAdapter fruitAdapter = new FruitAdapter(getActivity(), true, this);
        selectFruitRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        selectFruitRecyclerView.setAdapter(fruitAdapter);
        selectFruitRecyclerView.setVisibility(View.VISIBLE);
    }


    @Override
    public void sync(Object object) {
        if(object != null){
            hideEditEntryView();
            requestEntriesList();
        }
    }

    @Override
    public void sync(Response response) {

    }

    @Override
    public void syncEntries(final List<Entry> entries) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fillEntriesList(entries);
            }
        });
    }

    @Override
    public void attachEntry(Entry entry) {
        selectedEntry = entry;
        openEntryEditing(entry);
    }

    @Override
    public void attachFruit(Fruit fruit) {
        new EntryPresenter(this).addFruitToEntry(selectedEntry.getId(), fruit.getId(), 1);
        selectFruitRecyclerView.setVisibility(View.GONE);
    }


}
