package com.example.assginment_mob201.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assginment_mob201.Entity.Course;
import com.example.assginment_mob201.R;

import java.util.List;

public class RecyclerViewCourseApdater extends RecyclerView.Adapter<RecyclerViewCourseApdater.ViewHolder> {
    private LayoutInflater inflater;
    private List<Course> courseList;

    public RecyclerViewCourseApdater(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public void setList(List<Course> courseList) {
        this.courseList = courseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_recyclerview_course_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (this.courseList != null) {
            Course course = this.courseList.get(position);

            holder.tvTitle.setText(course.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        if (this.courseList == null) {
            return 0;
        }
        return this.courseList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }
    }
}
