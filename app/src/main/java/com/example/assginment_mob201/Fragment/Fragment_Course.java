package com.example.assginment_mob201.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assginment_mob201.R;

public class Fragment_Course extends Fragment {



    public Fragment_Course() {
        // Required empty public constructor
    }



    public static Fragment_Course newInstance() {
        Fragment_Course fragment = new Fragment_Course();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course, container, false);
    }
}