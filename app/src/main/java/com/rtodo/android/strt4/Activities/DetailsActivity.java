package com.rtodo.android.strt4.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.rtodo.android.strt4.Data.DatabaseHandler;
import com.rtodo.android.strt4.Model.AlarmModel;
import com.rtodo.android.strt4.R;
import com.rtodo.android.strt4.UI.RecyclerViewAdapter;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DetailsActivity extends AppCompatActivity {

    int kMonday = 0;
    int kTuesday = 0;
    int kWednesday = 0;
    int kThursday = 0;
    int kFriday = 0;
    int kSaturday = 0;
    int kSunday = 0;
    int kDaysCode;
    boolean timePickerUsed;
    int alarmHour;
    int alarmMinute;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        db = new DatabaseHandler(this);
        timePickerUsed = false;
        final Bundle bundle = getIntent().getExtras();

        Calendar nowCalendar = new GregorianCalendar();
        int now_day = bundle.getInt("days");
        kDaysCode = now_day;
        final int now_hour = bundle.getInt("hour");
        final int now_minute = bundle.getInt("minute");

        final String alarmHourString;
        final String alarmMinuteString;
        if (now_hour<10){
            alarmHourString = "0" + String.valueOf(now_hour);
        } else {
            alarmHourString = String.valueOf(now_hour);
        }

        if (now_minute<10){
            alarmMinuteString = "0" + String.valueOf(now_minute);
        } else {
            alarmMinuteString = String.valueOf(now_minute);
        }

        setContentView(R.layout.activity_set_alarm);
        final TextView clockText;
        clockText = findViewById(R.id.HourTextID);
        clockText.setText(alarmHourString + ":" + alarmMinuteString);
        ImageView setHourImage;
        setHourImage = findViewById(R.id.SetHourID);
        ImageView saveButton;
        saveButton = findViewById(R.id.SaveButtonID);
        TextView repeatText;
        repeatText = findViewById(R.id.RepeatID);
        Typeface italic_font = Typeface.createFromAsset(getAssets(), "fonts/AvenirNextLTPro-It.otf");
        repeatText.setTypeface(italic_font);
        TextView ringtoneText;
        ringtoneText = findViewById(R.id.RingtoneText);
        ringtoneText.setTypeface(italic_font);
        final ImageView mondayImage;
        mondayImage = findViewById(R.id.MondayImage);
        final TextView mondayText;
        mondayText = findViewById(R.id.MondayText);
        mondayText.setTypeface(italic_font);
        final ImageView tuesdayImage;
        tuesdayImage = findViewById(R.id.TuesdayImage);
        final TextView tuesdayText;
        tuesdayText = findViewById(R.id.TuesdayText);
        tuesdayText.setTypeface(italic_font);
        final ImageView wednesdayImage;
        wednesdayImage = findViewById(R.id.WednesdayImage);
        final TextView wednesdayText;
        wednesdayText = findViewById(R.id.WednesdayText);
        wednesdayText.setTypeface(italic_font);
        final ImageView thursdayImage;
        thursdayImage = findViewById(R.id.ThursdayImage);
        final TextView thursdayText;
        thursdayText = findViewById(R.id.ThursdayText);
        thursdayText.setTypeface(italic_font);
        final ImageView fridayImage;
        fridayImage = findViewById(R.id.FridayImage);
        final TextView fridayText;
        fridayText = findViewById(R.id.FridayText);
        fridayText.setTypeface(italic_font);
        final ImageView saturdayImage;
        saturdayImage = findViewById(R.id.SaturdayImage);
        final TextView saturdayText;
        saturdayText = findViewById(R.id.SaturdayText);
        saturdayText.setTypeface(italic_font);
        final ImageView sundayImage;
        sundayImage = findViewById(R.id.SundayImage);
        final TextView sundayText;
        sundayText = findViewById(R.id.SundayText);
        sundayText.setTypeface(italic_font);
        TextView cancelText;
        cancelText = findViewById(R.id.CancelTextID);
        cancelText.setText("Delete");

        setHourImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog dialog;
                LayoutInflater inflater;
                AlertDialog.Builder dialogBuilder;
                final TimePicker timePicker;
                Button confirm;
                Button cancel;
                dialogBuilder = new AlertDialog.Builder(DetailsActivity.this);
                inflater = LayoutInflater.from(DetailsActivity.this);
                View view1 = inflater.inflate(R.layout.popup, null);
                timePicker = view1.findViewById(R.id.TimePickerID);
                confirm = view1.findViewById(R.id.ConfirmButtonID);
                cancel = view1.findViewById(R.id.CancelButtonID);
                dialogBuilder.setView(view1);
                dialog = dialogBuilder.create();
                dialog.show();

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alarmHour = timePicker.getCurrentHour();
                        alarmMinute = timePicker.getCurrentMinute();
                        timePickerUsed = true;

                        String alarmHourString;
                        String alarmMinuteString;
                        if (alarmHour<10){
                            alarmHourString = "0" + String.valueOf(alarmHour);
                        } else {
                            alarmHourString = String.valueOf(alarmHour);
                        }

                        if (alarmMinute<10){
                            alarmMinuteString = "0" + String.valueOf(alarmMinute);
                        } else {
                            alarmMinuteString = String.valueOf(alarmMinute);
                        }
                        clockText.setText(alarmHourString + ":" + alarmMinuteString);

                        dialog.dismiss();
                    }
                });




            }
        });

        if ((now_day%100)/10 == 1){
            saturdayImage.setImageResource(R.drawable.ic_smallellipseon);
            saturdayText.setTextColor(Color.parseColor("#14223B"));
            kSaturday = 1;
        }
        if (now_day%10 == 1){
            sundayImage.setImageResource(R.drawable.ic_smallellipseon);
            sundayText.setTextColor(Color.parseColor("#14223B"));
            kSunday = 1;
        }
        if (now_day/1000000 == 1){
            mondayImage.setImageResource(R.drawable.ic_smallellipseon);
            mondayText.setTextColor(Color.parseColor("#14223B"));
            kMonday = 1;
        }
        if ((now_day%1000000)/100000 == 1){
            tuesdayImage.setImageResource(R.drawable.ic_smallellipseon);
            tuesdayText.setTextColor(Color.parseColor("#14223B"));
            kTuesday = 1;
        }
        if ((now_day%100000)/10000 == 1){
            wednesdayImage.setImageResource(R.drawable.ic_smallellipseon);
            wednesdayText.setTextColor(Color.parseColor("#14223B"));
            kWednesday = 1;
        }
        if ((now_day%10000)/1000 == 1){
            thursdayImage.setImageResource(R.drawable.ic_smallellipseon);
            thursdayText.setTextColor(Color.parseColor("#14223B"));
            kThursday = 1;
        }
        if ((now_day%1000)/100 == 1){
            fridayImage.setImageResource(R.drawable.ic_smallellipseon);
            fridayText.setTextColor(Color.parseColor("#14223B"));
            kFriday = 1;
        }

        mondayImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (kMonday == 0){
                    kMonday = 1;
                    mondayImage.setImageResource(R.drawable.ic_smallellipseon);
                    mondayText.setTextColor(Color.parseColor("#14223B"));
                }
                else {
                    kMonday = 0;
                    mondayImage.setImageResource(R.drawable.ic_smallellipseoff);
                    mondayText.setTextColor(Color.parseColor("#ffffff"));
                }
            }
        });

        tuesdayImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (kTuesday == 0){
                    kTuesday = 1;
                    tuesdayImage.setImageResource(R.drawable.ic_smallellipseon);
                    tuesdayText.setTextColor(Color.parseColor("#14223B"));
                }
                else {
                    kTuesday = 0;
                    tuesdayImage.setImageResource(R.drawable.ic_smallellipseoff);
                    tuesdayText.setTextColor(Color.parseColor("#ffffff"));
                }
            }
        });

        wednesdayImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (kWednesday == 0){
                    kWednesday = 1;
                    wednesdayImage.setImageResource(R.drawable.ic_smallellipseon);
                    wednesdayText.setTextColor(Color.parseColor("#14223B"));
                }
                else {
                    kWednesday = 0;
                    wednesdayImage.setImageResource(R.drawable.ic_smallellipseoff);
                    wednesdayText.setTextColor(Color.parseColor("#ffffff"));
                }
            }
        });

        thursdayImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (kThursday == 0){
                    kThursday = 1;
                    thursdayImage.setImageResource(R.drawable.ic_smallellipseon);
                    thursdayText.setTextColor(Color.parseColor("#14223B"));
                }
                else {
                    kThursday = 0;
                    thursdayImage.setImageResource(R.drawable.ic_smallellipseoff);
                    thursdayText.setTextColor(Color.parseColor("#ffffff"));
                }
            }
        });

        fridayImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (kFriday == 0){
                    kFriday = 1;
                    fridayImage.setImageResource(R.drawable.ic_smallellipseon);
                    fridayText.setTextColor(Color.parseColor("#14223B"));
                }
                else {
                    kFriday = 0;
                    fridayImage.setImageResource(R.drawable.ic_smallellipseoff);
                    fridayText.setTextColor(Color.parseColor("#ffffff"));
                }
            }
        });

        saturdayImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (kSaturday == 0){
                    kSaturday = 1;
                    saturdayImage.setImageResource(R.drawable.ic_smallellipseon);
                    saturdayText.setTextColor(Color.parseColor("#14223B"));
                }
                else {
                    kSaturday = 0;
                    saturdayImage.setImageResource(R.drawable.ic_smallellipseoff);
                    saturdayText.setTextColor(Color.parseColor("#ffffff"));
                }
            }
        });

        sundayImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (kSunday == 0){
                    kSunday = 1;
                    sundayImage.setImageResource(R.drawable.ic_smallellipseon);
                    sundayText.setTextColor(Color.parseColor("#14223B"));
                }
                else {
                    kSunday = 0;
                    sundayImage.setImageResource(R.drawable.ic_smallellipseoff);
                    sundayText.setTextColor(Color.parseColor("#ffffff"));
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kDaysCode = kFriday * 1000000 + kThursday * 100000 + kWednesday * 10000 + kTuesday * 1000 + kMonday * 100 + kSunday * 10 + kSaturday;
                if (kDaysCode == 0)
                    Toast.makeText(DetailsActivity.this, "Please pick a day!", Toast.LENGTH_LONG).show();
                else{
                    AlarmModel alarm = new AlarmModel();
                    alarm.setModelDaysCode(kDaysCode);
                    if (timePickerUsed) {
                        alarm.setModelHour(alarmHour);
                        alarm.setModelMinute(alarmMinute);
                    }
                    else {
                        alarm.setModelHour(now_hour);
                        alarm.setModelMinute(now_minute);
                    }
                    alarm.setModelVibrate(true);
                    alarm.setModelRingtone(0);
                    alarm.setModelActive(false);
                    int id = bundle.getInt("id");

                    db.deleteAlarm(id);
                    db.AddAlarm(alarm);
                    Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                    startActivity(intent);

                }
            }
        });

        cancelText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = bundle.getInt("id");
                db.deleteAlarm(id);

                Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        }
}
