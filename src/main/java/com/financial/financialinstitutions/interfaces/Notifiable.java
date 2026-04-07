package com.financial.financialinstitutions.interfaces;

public interface Notifiable {

    void sendNotification(String message);

    String getNotificationChannel();

}