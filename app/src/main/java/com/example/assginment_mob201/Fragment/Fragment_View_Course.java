package com.example.assginment_mob201.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assginment_mob201.Adapter.RecyclerViewViewCourseAdapter;
import com.example.assginment_mob201.DiaLog.DiaLogDetailCourseView;
import com.example.assginment_mob201.Entity.Course;
import com.example.assginment_mob201.ItemClickListener.ItemClickListener;
import com.example.assginment_mob201.R;

import java.util.List;


public class Fragment_View_Course extends Fragment {
    private RecyclerView rvViewCourse;
    private CourseViewModel courseViewModel;
    private RecyclerViewViewCourseAdapter recyclerViewViewCourseAdapter;

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
        View view = inflater.inflate(R.layout.fragment_view_course, container, false);
        this.initViewByID(view);
        this.initRecyclerView();
        this.setOnClickItemRecyclerView();
        return view;
    }

    private void setOnClickItemRecyclerView() {
        this.recyclerViewViewCourseAdapter.setOnItemDeleteClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Course course = recyclerViewViewCourseAdapter.getItem(position);
                courseViewModel.delete(course);
            }
        });
        this.recyclerViewViewCourseAdapter.setItemViewClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Course course = recyclerViewViewCourseAdapter.getItem(position);
                DiaLogDetailCourseView diaLogDetailCourse = new DiaLogDetailCourseView(getContext(), course);
                diaLogDetailCourse.showDialog();
            }
        });
    }

    private void initRecyclerView() {
        this.recyclerViewViewCourseAdapter = new RecyclerViewViewCourseAdapter(getActivity());
        this.rvViewCourse.setHasFixedSize(true);
        this.rvViewCourse.setLayoutManager(new GridLayoutManager(getContext(), 1));
        this.rvViewCourse.setBackgroundColor(Color.parseColor("#f7f7fe"));
        this.rvViewCourse.setAdapter(recyclerViewViewCourseAdapter);
    }

    private void initViewByID(View view) {
        this.rvViewCourse = view.findViewById(R.id.rv_viewCourse);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        this.courseViewModel.getAllCourses().observe(getActivity(), new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                recyclerViewViewCourseAdapter.setCourseList(courses);
            }
        });
    }
}