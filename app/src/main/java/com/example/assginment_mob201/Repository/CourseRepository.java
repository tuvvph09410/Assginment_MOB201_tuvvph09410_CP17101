package com.example.assginment_mob201.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.assginment_mob201.DAO.CourseDAO;
import com.example.assginment_mob201.Database.DatabaseRoom;
import com.example.assginment_mob201.Entity.Course;

import java.util.List;

public class CourseRepository {
    private CourseDAO courseDAO;
    private LiveData<List<Course>> mAllCourses;

    public CourseRepository(Application application) {
        this.courseDAO = DatabaseRoom.getDatabaseRoom(application).courseDAO();
        this.mAllCourses = this.courseDAO.getAll();
    }

    public LiveData<List<Course>> getAllCourses() {
        return this.mAllCourses;
    }

    public void insert(Course course) {
        new CourseRepository.InsertAsyncTask(this.courseDAO).execute(course);
    }

    public void delete(Course course) {
        new CourseRepository.DeleteAsyncTask(this.courseDAO).execute(course);
    }

    public class InsertAsyncTask extends AsyncTask<Course, Void, Void> {
        private CourseDAO courseDAO;

        public InsertAsyncTask(CourseDAO courseDAO) {
            this.courseDAO = courseDAO;
        }

        @Override
        protected Void doInBackground(Course... courses) {
            this.courseDAO.insert(courses[0]);
            return null;
        }
    }

    public class DeleteAsyncTask extends AsyncTask<Course, Void, Void> {
        private CourseDAO courseDAO;

        public DeleteAsyncTask(CourseDAO courseDAO) {
            this.courseDAO = courseDAO;
        }

        @Override
        protected Void doInBackground(Course... courses) {
            this.courseDAO.delete(courses[0]);
            return null;
        }
    }
}
