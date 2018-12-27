package com.rtodo.android.strt4.Model;

public class AlarmModel {
    private int modelId;
    private int modelDaysCode;
    private int modelMinute;
    private int modelHour;
    private boolean modelVibrate;
    private int modelRingtone;
    private boolean modelActive;

    public AlarmModel() {
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public int getModelDaysCode() {
        return modelDaysCode;
    }

    public void setModelDaysCode(int modelDaysCode) {
        this.modelDaysCode = modelDaysCode;
    }

    public int getModelMinute() {
        return modelMinute;
    }

    public void setModelMinute(int modelMinute) {
        this.modelMinute = modelMinute;
    }

    public int getModelHour() {
        return modelHour;
    }

    public void setModelHour(int modelHour) {
        this.modelHour = modelHour;
    }

    public boolean isModelVibrate() {
        return modelVibrate;
    }

    public void setModelVibrate(boolean modelVibrate) {
        this.modelVibrate = modelVibrate;
    }

    public int getModelRingtone() {
        return modelRingtone;
    }

    public void setModelRingtone(int modelRingtone) {
        this.modelRingtone = modelRingtone;
    }

    public boolean isModelActive() {
        return modelActive;
    }

    public void setModelActive(boolean modelActive) {
        this.modelActive = modelActive;
    }
}
