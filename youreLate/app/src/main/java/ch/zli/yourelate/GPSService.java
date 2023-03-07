package ch.zli.yourelate;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class GPSService extends Service {

    private double longitude;
    private double latitude;

    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    private final IBinder binder = new LocalBinder();
    public class LocalBinder extends Binder {
        GPSService getService() {
            // Return this instance of LocalService so clients can call public methods
            return GPSService.this;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}