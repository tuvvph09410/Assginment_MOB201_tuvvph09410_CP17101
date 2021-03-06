package com.example.assginment_mob201.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assginment_mob201.Entity.Course;
import com.example.assginment_mob201.ItemClickListener.ItemClickListener;
import com.example.assginment_mob201.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class RecyclerViewCourseApdater extends RecyclerView.Adapter<RecyclerViewCourseApdater.ViewHolder> {
    private LayoutInflater inflater;
    private List<Course> courseList;

    public static ItemClickListener itemInsertClickListener;
    public static ItemClickListener itemViewClickListener;

    public void setOnItemViewClickListener(ItemClickListener itemClickListener) {
        RecyclerViewCourseApdater.itemViewClickListener = itemClickListener;
    }

    public void setOnItemInsertClickListener(ItemClickListener onItemInsertClickListener) {
        RecyclerViewCourseApdater.itemInsertClickListener = onItemInsertClickListener;
    }

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
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        if (this.courseList != null) {
            Course course = this.courseList.get(position);
            holder.tvTitle.setText(course.getTitle());
            Picasso.get().load(course.getUrl()).error(R.drawable.ic_baseline_report_gmailerrorred_24).into(holder.imageView);
            holder.tvToDate.setText(course.getToDate());
            holder.tvFromDate.setText(course.getFromDate());
            holder.tvCost.setText(decimalFormat.format(course.getCost()) + "??");
            holder.btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemInsertClickListener != null) {
                        itemInsertClickListener.onItemClick(position);
                    }

                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemViewClickListener != null) {
                        itemViewClickListener.onItemClick(position);
                    }
                }
            });
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
        TextView tvTitle, tvToDate, tvFromDate, tvCost;
        Button btnRegister;
        ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvToDate = itemView.findViewById(R.id.tv_toDate);
            tvFromDate = itemView.findViewById(R.id.tv_fromDate);
            tvCost = itemView.findViewById(R.id.tv_cost);
            btnRegister = itemView.findViewById(R.id.btn_register);
            imageView = itemView.findViewById(R.id.iv_imageCourse);
        }

    }
}
