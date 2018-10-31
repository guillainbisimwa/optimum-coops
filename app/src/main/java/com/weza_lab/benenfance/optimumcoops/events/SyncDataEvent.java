package com.weza_lab.benenfance.optimumcoops.events;

public class SyncDataEvent {
    private String message;
    private boolean isSuccess;

    public SyncDataEvent() {
    }

    public SyncDataEvent(String message, boolean isSucess) {
        this.message = message;
        this.isSuccess = isSucess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}