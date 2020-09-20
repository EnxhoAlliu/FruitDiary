package com.example.fruitdiary.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fruitdiary.R;
import com.example.fruitdiary.adapters.AdapterGlue;
import com.example.fruitdiary.adapters.FruitAdapter;
import com.example.fruitdiary.models.Entry;
import com.example.fruitdiary.models.Fruit;


public class FruitFragment extends Fragment implements AdapterGlue {

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        return inflater.inflate(R.layout.fruit_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.fruits_recycler_view);
    }

    @Override
    public void onResume() {
        super.onResume();
        showList();
    }

    private void showList(){
        FruitAdapter fruitAdapter = new FruitAdapter(getActivity(), false, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(fruitAdapter);
    }

    @Override
    public void attachEntry(Entry entry) {

    }

    @Override
    public void attachFruit(Fruit fruit) {

    }
}
