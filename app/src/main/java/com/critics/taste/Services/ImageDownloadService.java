package com.critics.taste.Services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;


public class ImageDownloadService extends IntentService {

    public ImageDownloadService() {
        super("ImageDownloadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.i("Service", "onHanndleIntent");
        String searchQuery = intent.getStringExtra("searchQuery");
    }
}
