package com.weza_lab.benenfance.optimumcoops.events;


public class SaveDataEvent {

    private String message;
    private boolean isSuccess;

    public SaveDataEvent() {
    }

    public SaveDataEvent(String message, boolean isSuccess) {
        this.message = message;
        this.isSuccess = isSuccess;
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
