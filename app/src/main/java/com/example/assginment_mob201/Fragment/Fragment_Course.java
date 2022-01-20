package com.example.assginment_mob201.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.assginment_mob201.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Fragment_Course extends Fragment {

    private BottomNavigationView bottomNavigationView;
    private static final int FRAGMENT_REGISTER_COURSE = 0;
    private static final int FRAGMENT_VIEW_COURSE = 1;
    private int currentFragment = FRAGMENT_REGISTER_COURSE;

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
        View view = inflater.inflate(R.layout.fragment_course, container, false);
        this.initViewById(view);

        return view;
    }

    private void initBottomNavView() {
        this.bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        this.checkedPositionNav(R.id.nav_registerCourse);
        this.initFragmnet(new Fragment_Register_Course());
    }

    private void initViewById(View view) {
        this.bottomNavigationView = view.findViewById(R.id.bottom_NavCourseView);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_registerCourse:
                    Fragment_Register_Course fragment_register_course = new Fragment_Register_Course();
                    openFragment(FRAGMENT_REGISTER_COURSE, fragment_register_course);
                    checkedPositionNav(R.id.nav_registerCourse);
                    break;
                case R.id.nav_viewCourse:
                    Fragment_View_Course fragment_view_course = new Fragment_View_Course();
                    openFragment(FRAGMENT_VIEW_COURSE, fragment_view_course);
                    checkedPositionNav(R.id.nav_viewCourse);
                    break;
            }
            return true;
        }
    };

    private void initFragmnet(Fragment fragment) {
        this.currentFragment = FRAGMENT_REGISTER_COURSE;
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameLayout_Course, fragment);
        fragmentTransaction.addToBackStack(fragment.getClass().getName());
        fragmentTransaction.commit();
    }

    private void loadFragments(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout_Course, fragment);
        fragmentTransaction.addToBackStack(fragment.getClass().getName());
        fragmentTransaction.commit();
    }

    private void openFragment(int currentFragment, Fragment fragment) {
        if (this.currentFragment != currentFragment) {
            loadFragments(fragment);
            this.currentFragment = currentFragment;
        }
    }

    private void checkedPositionNav(int item) {
        this.bottomNavigationView.getMenu().findItem(item).setChecked(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.initBottomNavView();
        super.onViewCreated(view, savedInstanceState);
    }
}