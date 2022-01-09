package com.example.assginment_mob201.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assginment_mob201.R;


public class Fragment_News extends Fragment {



    public Fragment_News() {
        // Required empty public constructor
    }


    public static Fragment_News newInstance(String param1, String param2) {
        Fragment_News fragment = new Fragment_News();

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
        return inflater.inflate(R.layout.fragment_news, container, false);
    }
}