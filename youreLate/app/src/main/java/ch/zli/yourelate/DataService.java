package ch.zli.yourelate;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import java.time.LocalTime;

public class DataService extends Service {
    GPSService gpsService = new GPSService();
    PlaceFragment placeFragment = new PlaceFragment();
    MainFragment mainFragment = new MainFragment();

    private final IBinder binder = new LocalBinder();

    private static final String CHANNEL_ID = "defaultChannel";
    private static final String CHANNEL_NAME = "Default Channel";

    private NotificationManager notificationManager;

    private double xcoordinate;
    private double ycoordinate;
    private LocalTime time;

    public class LocalBinder extends Binder {
        DataService getService() {
            // Return this instance of LocalService so clients can call public methods
            return DataService.this;
        }
    }

    public DataService() {
        this.notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public double getXcoordinate() {
        return xcoordinate;
    }
    public void setXcoordinate(double xcoordinate) {
        this.xcoordinate = xcoordinate;
    }
    public double getYcoordinate() {
        return ycoordinate;
    }
    public void setYcoordinate(double ycoordinate) {
        this.ycoordinate = ycoordinate;
    }
    public LocalTime getTime() {
        return time;
    }
    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        checkLocation();
        return super.onStartCommand(intent, flags, startId);
    }

    private void checkLocation(){
        String coordinates = placeFragment.getData(mainFragment.getName());
        String[] splitcoordinates = coordinates.split(",");
        setXcoordinate(Double.parseDouble(splitcoordinates[0]));
        setYcoordinate(Double.parseDouble(splitcoordinates[1]));

        if (gpsService.getLongitude() == (xcoordinate + 0.005) && gpsService.getLongitude() == (xcoordinate - 0.005) && gpsService.getLatitude() == (ycoordinate + 0.005) && gpsService.getLatitude() == (ycoordinate - 0.005)) {
            sendNotification();
        }

    }

    private void sendNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_media_play)
                .setContentTitle("Late")
                .setContentText("You are Late!!!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        notificationManager.notify(0, builder.build());
    }

}