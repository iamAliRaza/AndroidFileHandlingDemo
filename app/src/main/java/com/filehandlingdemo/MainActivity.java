package com.filehandlingdemo;

import android.Manifest;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity  {


    static boolean dateInfo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);

    }

    @Override
    protected void onStart() {
        super.onStart();

        CalendarView calendarView = findViewById(R.id.calendar);

        calendarView.setOnDateChangeListener((calendar, year, month, day) -> {

            String date = day + "-" + month + "-" + year;

            DateRecords dateRecords = new DateRecords(date);
            dateRecords.start();
            try {
                dateRecords.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

           if (dateInfo)
                Toast.makeText(getApplicationContext(),"Date already Booked",Toast.LENGTH_SHORT).show();
        });
    }
}
