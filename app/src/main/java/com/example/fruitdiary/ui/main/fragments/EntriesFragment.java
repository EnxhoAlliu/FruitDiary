package com.example.fruitdiary.ui.main.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fruitdiary.R;
import com.example.fruitdiary.ui.main.adapters.AdapterGlue;
import com.example.fruitdiary.ui.main.adapters.EditFruitAdapter;
import com.example.fruitdiary.ui.main.adapters.EntriesAdapter;
import com.example.fruitdiary.ui.main.adapters.FruitAdapter;
import com.example.fruitdiary.models.Entry;
import com.example.fruitdiary.models.Fruit;
import com.example.fruitdiary.presenters.EntryPresenter;
import com.example.fruitdiary.server.ServerSync;

import java.util.List;

public class EntriesFragment extends Fragment implements AdapterGlue, ServerSync {


    private RecyclerView entriesRecyclerView;
    private Button newEntry;
    private Button deleteAllEntries;

    private RecyclerView selectedEntryFruitsRecyclerView;
    private RecyclerView selectFruitRecyclerView;
    private Button addEntryFruit;
    private Button exitEntryEditing;
    private Button deleteEntry;
    private TextView selectedEntryDate;
    private View editEntryView;
    private Entry selectedEntry;

    private Button createEntry;
    private Button exitNewEntry;
    private EditText addDateText;
    private View newEntryView;


    public static EntriesFragment newInstance() {

        Bundle args = new Bundle();
        EntriesFragment fragment = new EntriesFragment();
        fragment.setArguments(args);
        return fragment;
    }

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
        setEditModeFunctionality();
    }

    private void findViews(View view) {
        entriesRecyclerView = view.findViewById(R.id.entries_recycler_view);
        deleteAllEntries = view.findViewById(R.id.delete_all_btn);
        newEntry = view.findViewById(R.id.new_entry);

        editEntryModeViews(view);
        newEntryModeViews(view);
    }

    private void fillEntriesList(List<Entry> entryList) {
        EntriesAdapter entriesAdapter = new EntriesAdapter(getActivity(), entryList, this);
        entriesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        entriesRecyclerView.setAdapter(entriesAdapter);
        //In case their visibility is set to GONE
        showEntriesList();
    }

    private void requestEntriesList() {
        hideEditEntryView();
        new EntryPresenter(this).getEntries();
    }

    private void openEntryEditing(Entry entry) {
        hideEntriesList();
        fillEditEntryViewWithSelectedEntry(entry);
        editEntryView.setVisibility(View.VISIBLE);
    }

    private void hideEntriesList() {
        entriesRecyclerView.setVisibility(View.GONE);
        newEntry.setVisibility(View.GONE);
        deleteAllEntries.setVisibility(View.GONE);
    }

    public void showEntriesList() {
        entriesRecyclerView.setVisibility(View.VISIBLE);
        newEntry.setVisibility(View.VISIBLE);
        deleteAllEntries.setVisibility(View.VISIBLE);
    }


    private void fillEditEntryViewWithSelectedEntry(Entry entry) {
        displaySelectedEntryFruitList(entry);
        selectedEntryDate.setText(entry.getDate());
    }

    private void displaySelectedEntryFruitList(Entry entry) {
        EditFruitAdapter editFruitAdapter = new EditFruitAdapter(getActivity(), entry, true);
        selectedEntryFruitsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        selectedEntryFruitsRecyclerView.setAdapter(editFruitAdapter);
    }

    private void setEditModeFunctionality() {
        entryEditMOde();
        newEntryMode();
        deleteAllEntries.setOnClickListener(v -> deleteAllEntries());
    }

    private void entryEditMOde() {
        exitEntryEditing.setOnClickListener((v) -> requestEntriesList());
        deleteEntry.setOnClickListener(v -> removeEntry());
        addEntryFruit.setOnClickListener(v -> displayFruitList());
    }

    private void editEntryModeViews(View view){
        editEntryView = view.findViewById(R.id.detailed_view);
        selectedEntryFruitsRecyclerView = view.findViewById(R.id.entry_fruit_full_list);
        selectFruitRecyclerView = view.findViewById(R.id.pick_fruit_list);
        addEntryFruit = view.findViewById(R.id.add_fruit);
        exitEntryEditing = view.findViewById(R.id.exit_button);
        deleteEntry = view.findViewById(R.id.delete_entry);
        selectedEntryDate = view.findViewById(R.id.entry_detail_date);
    }

    private void newEntryMode() {
        newEntry.setOnClickListener(v -> {
            createNewEntryDialog();
            hideEntriesList();
        });
        exitNewEntry.setOnClickListener(v -> requestEntriesList());
        createEntry.setOnClickListener(v -> addNewEntry());
    }

    private void newEntryModeViews(View view){
        newEntryView = view.findViewById(R.id.create_entry);
        createEntry = view.findViewById(R.id.create_entry_button);
        exitNewEntry = view.findViewById(R.id.exit_new_entry_button);
        addDateText = view.findViewById(R.id.add_entry_date);
    }

    private void createNewEntryDialog() {
        newEntryView.setVisibility(View.VISIBLE);
    }

    private void removeEntry() {
        new EntryPresenter(this).deleteEntry(selectedEntry.getId());
    }

    public void hideEditEntryView() {
        editEntryView.setVisibility(View.GONE);
        newEntryView.setVisibility(View.GONE);
    }

    private void displayFruitList() {
        FruitAdapter fruitAdapter = new FruitAdapter(getActivity(), true, this);
        selectFruitRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        selectFruitRecyclerView.setAdapter(fruitAdapter);
        selectFruitRecyclerView.setVisibility(View.VISIBLE);
    }

    private void addNewEntry() {
        String date = addDateText.getText().toString();
        if (date != null && date.length() != 0) {
            new EntryPresenter(this).addNewEntry(date);
        }
    }

    private void deleteAllEntries() {
        new EntryPresenter(this).deleteAllEntries();
    }


    @Override
    public void sync(Object object, int requestCode) {
        if (requestCode != 0) {
            Toast.makeText(getContext(), "Server response was succesfull", Toast.LENGTH_SHORT).show();
            requestEntriesList();
        }
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
