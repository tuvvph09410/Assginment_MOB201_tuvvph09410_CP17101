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

public class RecyclerViewViewCourseAdapter extends RecyclerView.Adapter<RecyclerViewViewCourseAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<Course> courseList;
    public static ItemClickListener itemDeleteClickListener;
    public static ItemClickListener itemViewClickListener;

    public void setItemViewClickListener(ItemClickListener itemClickListener) {
        RecyclerViewViewCourseAdapter.itemViewClickListener = itemClickListener;
    }

    public void setOnItemDeleteClickListener(ItemClickListener itemClickListener) {
        RecyclerViewViewCourseAdapter.itemDeleteClickListener = itemClickListener;
    }

    public RecyclerViewViewCourseAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_recyclerview_view_course_adapter, parent, false);
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
            holder.tvCost.setText(decimalFormat.format(course.getCost()) + "đ");
            holder.btnCancelRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemDeleteClickListener != null) {
                        itemDeleteClickListener.onItemClick(position);
                        Toast.makeText(inflater.getContext(), "Huỷ đăng ký thành công", Toast.LENGTH_LONG).show();
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

    public Course getItem(int position) {
        if (this.courseList == null || position >= this.courseList.size()) {
            return null;
        }
        return this.courseList.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvToDate, tvFromDate, tvCost;
        Button btnCancelRegister;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvToDate = itemView.findViewById(R.id.tv_toDate);
            tvFromDate = itemView.findViewById(R.id.tv_fromDate);
            tvCost = itemView.findViewById(R.id.tv_cost);
            btnCancelRegister = itemView.findViewById(R.id.btn_cancelRegister);
            imageView = itemView.findViewById(R.id.iv_imageCourse);
        }
    }
}
