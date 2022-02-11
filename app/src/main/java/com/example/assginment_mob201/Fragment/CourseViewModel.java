package com.example.assginment_mob201.Fragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.assginment_mob201.Entity.Course;
import com.example.assginment_mob201.Repository.CourseRepository;

import java.util.List;

public class CourseViewModel extends AndroidViewModel {
    private CourseRepository courseRepository;
    private LiveData<List<Course>> mGetAllCourses;

    public CourseViewModel(@NonNull Application application) {
        super(application);
        this.courseRepository = new CourseRepository(application);
        this.mGetAllCourses = this.courseRepository.getAllCourses();
    }

    public LiveData<List<Course>> getAllCourses() {
        return this.mGetAllCourses;
    }

    public void insert(Course course) {
        this.courseRepository.insert(course);
    }
    public void delete(Course course){
        this.courseRepository.delete(course);
    }
}
