package com.example.assginment_mob201.BroadCast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class BroadCastCheckRegistered extends BroadcastReceiver {
    private int getIdCourse, getIdCourseView;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        this.getIdCourse = bundle.getInt("idCourse");
        this.getIdCourseView = bundle.getInt("idCourseView");
        this.check(context);
    }

    private void check(Context context) {
        if (this.getIdCourseView == this.getIdCourse) {
            Toast.makeText(context, "Bạn đã đăng ký khoá học này rồi, vui lòng đăng ký khoá học khác", Toast.LENGTH_LONG).show();
        }
    }
}
