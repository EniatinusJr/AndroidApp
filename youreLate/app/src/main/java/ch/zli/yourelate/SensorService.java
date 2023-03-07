package ch.zli.yourelate;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class SensorService extends Service {

    private final IBinder binder = new LocalBinder();

    public class LocalBinder extends Binder {
        SensorService getService() {
            // Return this instance of LocalService so clients can call public methods
            return SensorService.this;
        }
    }

    public SensorService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}