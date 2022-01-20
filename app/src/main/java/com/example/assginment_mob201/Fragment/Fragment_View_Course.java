package com.example.assginment_mob201.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assginment_mob201.R;


public class Fragment_View_Course extends Fragment {


    public Fragment_View_Course() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Fragment_View_Course newInstance(String param1, String param2) {
        Fragment_View_Course fragment = new Fragment_View_Course();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View view=inflater.inflate(R.layout.fragment_view_course, container, false);
        return view;
    }
}