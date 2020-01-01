package com.example.innomid;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.location.Geocoder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Locale;

public class FetchAddressIntentService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public FetchAddressIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

    }
}
