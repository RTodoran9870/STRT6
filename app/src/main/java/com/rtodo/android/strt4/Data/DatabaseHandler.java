package com.rtodo.android.strt4.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rtodo.android.strt4.Model.AlarmModel;
import com.rtodo.android.strt4.Util.Constants;

import java.lang.invoke.ConstantCallSite;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private Context ctx;
    public DatabaseHandler(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        this.ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ALARM_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + "(" +
                Constants.KEY_ID + " INTEGER PRIMARY KEY," + Constants.KEY_DAYS + " INTEGER," + Constants.KEY_HOUR + " INTEGER,"
                + Constants.KEY_MINUTE + " INTEGER," + Constants.KEY_RINGTONE + " INTEGER," + Constants.KEY_VIBRATE + " BOOLEAN,"
                + Constants.KEY_ACTIVE + " BOOLEAN);";

        db.execSQL(CREATE_ALARM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ Constants.TABLE_NAME);
        onCreate(db);

    }

    public void AddAlarm(AlarmModel alarm){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.KEY_DAYS, alarm.getModelDaysCode());
        values.put(Constants.KEY_HOUR, alarm.getModelHour());
        values.put(Constants.KEY_MINUTE, alarm.getModelMinute());
        values.put(Constants.KEY_ACTIVE, alarm.isModelActive());
        values.put(Constants.KEY_VIBRATE, alarm.isModelVibrate());
        values.put(Constants.KEY_RINGTONE, alarm.getModelRingtone());

        db.insert(Constants.TABLE_NAME, null, values);

    }

    public AlarmModel getAlarm(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Constants.TABLE_NAME, new String[] {Constants.KEY_ID, Constants.KEY_DAYS, Constants.KEY_HOUR,
        Constants.KEY_MINUTE, Constants.KEY_ACTIVE, Constants.KEY_VIBRATE, Constants.KEY_RINGTONE}, Constants.KEY_ID + "=?",
                new String[] {String.valueOf(id)}, null, null, null, null);

        if (cursor!=null)
            cursor.moveToFirst();
            AlarmModel alarm = new AlarmModel();
            alarm.setModelId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
            alarm.setModelDaysCode(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_DAYS))));
            alarm.setModelHour(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_HOUR))));
            alarm.setModelMinute(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_MINUTE))));
            alarm.setModelRingtone(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_RINGTONE))));
            alarm.setModelVibrate(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(Constants.KEY_VIBRATE))));
            alarm.setModelActive(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(Constants.KEY_ACTIVE))));
        return alarm;
    }

    public List <AlarmModel> getAllAlarms(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<AlarmModel> alarmList = new ArrayList<>();
        Cursor cursor = db.query(Constants.TABLE_NAME, new String[]{Constants.KEY_ID, Constants.KEY_DAYS, Constants.KEY_HOUR,
                Constants.KEY_MINUTE, Constants.KEY_ACTIVE, Constants.KEY_VIBRATE, Constants.KEY_RINGTONE}, null, null, null,
                null, Constants.KEY_ID + " DESC");
        if (cursor.moveToFirst()){
            do {
                AlarmModel alarm = new AlarmModel();
                alarm.setModelId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
                alarm.setModelDaysCode(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_DAYS))));
                alarm.setModelHour(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_HOUR))));
                alarm.setModelMinute(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_MINUTE))));
                alarm.setModelRingtone(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_RINGTONE))));
                alarm.setModelVibrate(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(Constants.KEY_VIBRATE))));
                alarm.setModelActive(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(Constants.KEY_ACTIVE))));
                alarmList.add(alarm);
            }while(cursor.moveToNext());
        }
        return alarmList;
    }

    public int updateAlarm(AlarmModel alarm){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.KEY_DAYS, alarm.getModelDaysCode());
        values.put(Constants.KEY_HOUR, alarm.getModelHour());
        values.put(Constants.KEY_MINUTE, alarm.getModelMinute());
        values.put(Constants.KEY_ACTIVE, alarm.isModelActive());
        values.put(Constants.KEY_VIBRATE, alarm.isModelVibrate());
        values.put(Constants.KEY_RINGTONE, alarm.getModelRingtone());
        return db.update(Constants.TABLE_NAME, values, Constants.KEY_ID + "=?", new String[]{String.valueOf(alarm.getModelId())});
    }

    public void deleteAlarm(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.TABLE_NAME, Constants.KEY_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();

    }

    public int getAlarmsCount(){
        String countQuery = "SELECT * FROM " + Constants.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);
        return cursor.getCount();
    }

}
