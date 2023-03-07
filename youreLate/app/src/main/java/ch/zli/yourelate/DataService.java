package ch.zli.yourelate;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class DataService extends Service {
    private final IBinder binder = new LocalBinder();

    public class LocalBinder extends Binder {
        DataService getService() {
            // Return this instance of LocalService so clients can call public methods
            return DataService.this;
        }
    }

    public DataService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}