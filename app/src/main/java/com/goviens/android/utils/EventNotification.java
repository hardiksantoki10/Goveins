package com.goviens.android.utils;

public class EventNotification {
    public String NotificationTittle;
    public String NotificationMessage;
    public String NotificationDATA;
    public String type;

    public EventNotification(String notificationTittle, String notificationMessage, String notificationDATA, String type) {
        NotificationTittle = notificationTittle;
        NotificationMessage = notificationMessage;
        NotificationDATA = notificationDATA;
        this.type = type;
    }
}
