package com.rtodo.android.strt4.UI;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rtodo.android.strt4.Activities.DetailsActivity;
import com.rtodo.android.strt4.Model.AlarmModel;
import com.rtodo.android.strt4.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<AlarmModel> alarmModelList;

    public RecyclerViewAdapter(Context context, List<AlarmModel> alarmModelList) {
        this.context = context;
        this.alarmModelList = alarmModelList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.llist_row, parent, false);

        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewAdapter.ViewHolder holder, int position) {
        final AlarmModel alarmModel = alarmModelList.get(position);
        String alarmHourString;
        String alarmMinuteString;
        if (alarmModel.getModelHour()<10){
            alarmHourString = "0" + String.valueOf(alarmModel.getModelHour());
        } else {
            alarmHourString = String.valueOf(alarmModel.getModelHour());
        }

        if (alarmModel.getModelMinute()<10){
            alarmMinuteString = "0" + String.valueOf(alarmModel.getModelMinute());
        } else {
            alarmMinuteString = String.valueOf(alarmModel.getModelMinute());
        }
        holder.hoursText.setText(alarmHourString + ":" + alarmMinuteString);
        String alarmDaysString = "";
        int alarmcode = alarmModel.getModelDaysCode();
        int k = 0;
        if ((alarmcode%1000)/100 == 1) {
            alarmDaysString = alarmDaysString + "Mon";
            k = 1;
        }
        if ((alarmcode%10000)/1000 == 1){
            if (k==1)
                alarmDaysString = alarmDaysString + ", Tue";
            else{
                alarmDaysString = alarmDaysString + "Tue";
                k=1;
            }
        }
        if ((alarmcode%100000)/10000 == 1){
            if (k==1)
                alarmDaysString = alarmDaysString + ", Wed";
            else{
                alarmDaysString = alarmDaysString + "Wed";
                k=1;
            }
        }
        if ((alarmcode%1000000)/100000 == 1){
            if (k==1)
                alarmDaysString = alarmDaysString + ", Thu";
            else{
                alarmDaysString = alarmDaysString + "Thu";
                k=1;
            }
        }
        if (alarmcode/1000000 == 1){
            if (k==1)
                alarmDaysString = alarmDaysString + ", Fri";
            else{
                alarmDaysString = alarmDaysString + "Fri";
                k=1;
            }
        }
        if ((alarmcode%10)/1 == 1){
            if (k==1)
                alarmDaysString = alarmDaysString + ", Sat";
            else{
                alarmDaysString = alarmDaysString + "Sat";
                k=1;
            }
        }
        if ((alarmcode%100)/10 == 1){
            if (k==1)
                alarmDaysString = alarmDaysString + ", Sun";
            else
                alarmDaysString = alarmDaysString + "Sun";
        }
        holder.daysText.setText(alarmDaysString);
        if (alarmModel.isModelActive()) {
            holder.enabledImage.setImageResource(R.drawable.ic_sheepon);
            holder.cardView.setCardBackgroundColor(Color.parseColor("#70ffffff"));
        }
        else {
            holder.enabledImage.setImageResource(R.drawable.ic_sheepoff);
            holder.cardView.setCardBackgroundColor(Color.parseColor("#40ffffff"));
        }
        holder.enabledImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (alarmModel.isModelActive()){
                    alarmModel.setModelActive(false);
                    holder.enabledImage.setImageResource(R.drawable.ic_sheepoff);
                    holder.cardView.setCardBackgroundColor(Color.parseColor("#40ffffff"));
                }
                else {
                  alarmModel.setModelActive(true);
                  holder.enabledImage.setImageResource(R.drawable.ic_sheepon);
                  holder.cardView.setCardBackgroundColor(Color.parseColor("#70ffffff"));
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return alarmModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView daysText;
        public TextView hoursText;
        public ImageView enabledImage;
        public CardView cardView;
        public int id;


        public ViewHolder(@NonNull View view, Context ctx) {
            super(view);

            context = ctx;
            daysText = view.findViewById(R.id.daysTextId);
            hoursText = view.findViewById(R.id.hourTextId);
            enabledImage = view.findViewById(R.id.enabledImageID);
            cardView = view.findViewById(R.id.cardViewID);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    AlarmModel alarm = alarmModelList.get(position);
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("hour", alarm.getModelHour());
                    intent.putExtra("minute", alarm.getModelMinute());
                    intent.putExtra("vibrate", alarm.isModelVibrate());
                    intent.putExtra("active", alarm.isModelActive());
                    intent.putExtra("id", alarm.getModelId());
                    intent.putExtra("ringtone", alarm.getModelRingtone());
                    intent.putExtra("days",alarm.getModelDaysCode());
                    context.startActivity(intent);

                }
            });
        }
    }
}
