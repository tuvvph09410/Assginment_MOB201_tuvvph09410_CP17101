package com.example.assginment_mob201.DiaLog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.assginment_mob201.Entity.Course;
import com.example.assginment_mob201.R;

import java.text.DecimalFormat;

public class DialogDetailCourse {
    private AlertDialog alertDialog;
    private LayoutInflater inflater;
    private TextView tvDialogTitle, tvDialogCost, tvDialogDate;
    private Button btnDialogCancel;

    public DialogDetailCourse(Context context, Course... courses) {
        this.inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.detail_dialog_course, null);

        this.initViewByID(view);
        this.initDialog(view, context);
        this.addDetailDialog(courses);
    }

    private void addDetailDialog(Course... courses) {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        this.tvDialogCost.setText("Chí Phí: " + decimalFormat.format(courses[0].getCost()) + "đ");
        this.tvDialogTitle.setText(String.valueOf(courses[0].getTitle()));
        this.tvDialogDate.setText("Bắt đầu ngày: " + courses[0].getToDate() + " Đến ngày " + courses[0].getFromDate());
        this.btnDialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    private void initDialog(View view, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context).setView(view);
        this.alertDialog = builder.create();
    }

    private void initViewByID(View view) {
        this.tvDialogCost = view.findViewById(R.id.tv_dialogCost);
        this.tvDialogDate = view.findViewById(R.id.tv_dialogDate);
        this.tvDialogTitle = view.findViewById(R.id.tv_dialogTitle);
        this.btnDialogCancel = view.findViewById(R.id.btn_dialogCancel);
    }

    public void showDialog() {
        this.alertDialog.show();
    }
}
