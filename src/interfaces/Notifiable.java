package interfaces;

public interface Notifiable {

    void sendNotification(String message);

    String getNotificationChannel();

}