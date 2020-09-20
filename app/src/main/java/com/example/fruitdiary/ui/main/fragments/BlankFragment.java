package com.example.fruitdiary.ui.main.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class BlankFragment extends Fragment {

    public static BlankFragment newInstance() {

        Bundle args = new Bundle();

        BlankFragment fragment = new BlankFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
