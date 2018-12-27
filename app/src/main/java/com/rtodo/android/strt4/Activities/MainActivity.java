package com.rtodo.android.strt4.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.rtodo.android.strt4.Data.DatabaseHandler;
import com.rtodo.android.strt4.Model.AlarmModel;
import com.rtodo.android.strt4.R;
import com.rtodo.android.strt4.UI.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<AlarmModel> alarms;
    private List<AlarmModel> listItems;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView floating;
        floating = findViewById(R.id.floatingID);
        floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SetAlarmActivity.class);
                startActivity(intent);
            }
        });

        db = new DatabaseHandler(this);
        recyclerView = findViewById(R.id.RecyclerViewID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        alarms = new ArrayList<>();
        listItems = new ArrayList<>();

        alarms = db.getAllAlarms();

        for(AlarmModel c : alarms){
            AlarmModel alarm = new AlarmModel();
            alarm.setModelDaysCode(c.getModelDaysCode());
            alarm.setModelActive(c.isModelActive());
            alarm.setModelRingtone(c.getModelRingtone());
            alarm.setModelVibrate(c.isModelVibrate());
            alarm.setModelMinute(c.getModelMinute());
            alarm.setModelHour(c.getModelHour());
            alarm.setModelId(c.getModelId());

            listItems.add(alarm);
        }

        recyclerViewAdapter = new RecyclerViewAdapter(this, listItems);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();
    }

}
