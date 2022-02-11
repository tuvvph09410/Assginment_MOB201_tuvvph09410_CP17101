package com.example.assginment_mob201.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.assginment_mob201.DAO.CourseDAO;
import com.example.assginment_mob201.Entity.Course;

@Database(entities = {Course.class}, version = 2)
public abstract class DatabaseRoom extends RoomDatabase {
    public abstract CourseDAO courseDAO();

    public static DatabaseRoom INSTANCE;

    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateData(INSTANCE).execute();
        }
    };

    public static DatabaseRoom getDatabaseRoom(final Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseRoom.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DatabaseRoom.class, "Assginment_MOB201")
                        .fallbackToDestructiveMigration()
                        .addCallback(callback)
                        .build();

            }
        }
        return INSTANCE;
    }

    public static class PopulateData extends AsyncTask<Void, Void, Void> {
        private CourseDAO courseDAO;

        public PopulateData(DatabaseRoom databaseRoom) {
            this.courseDAO = databaseRoom.courseDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
