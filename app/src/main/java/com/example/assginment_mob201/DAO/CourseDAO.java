package com.example.assginment_mob201.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.assginment_mob201.Entity.Course;

import java.util.List;

@Dao
public interface CourseDAO {
    @Query("SELECT * FROM Course")
    LiveData<List<Course>> getAll();

    @Insert
    void insert(Course course);

    @Update
    void update(Course course);

    @Delete
    void delete(Course course);
}
