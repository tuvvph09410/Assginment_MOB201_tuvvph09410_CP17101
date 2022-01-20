package com.example.assginment_mob201.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assginment_mob201.Entity.SpinnerCourse;
import com.example.assginment_mob201.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SpinnerListCourseAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<SpinnerCourse> spinnerCourseList;

    public SpinnerListCourseAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setList(List<SpinnerCourse> spinnerCourseList) {
        this.spinnerCourseList = spinnerCourseList;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        if (this.spinnerCourseList == null) {
            return 0;
        }
        return this.spinnerCourseList.size();
    }



    @Override
    public Object getItem(int position) {
        return this.spinnerCourseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_spinner_course, null, false);
            viewHolder.ivUrl = convertView.findViewById(R.id.image);
            viewHolder.tvName = convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SpinnerCourse spinnerCourse = (SpinnerCourse) getItem(position);
        Picasso.get().load(spinnerCourse
                .getUrl())
                .placeholder(R.drawable.ic_baseline_android_24)
                .error(R.drawable.ic_baseline_report_gmailerrorred_24)
                .into(viewHolder.ivUrl);
        viewHolder.tvName.setText(spinnerCourse.getName());
        return convertView;
    }

    public class ViewHolder {
        ImageView ivUrl;
        TextView tvName;
    }
}
