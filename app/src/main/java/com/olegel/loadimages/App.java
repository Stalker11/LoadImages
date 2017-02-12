package com.olegel.loadimages;

import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        SaveImages.setContext(getApplicationContext());
        super.onCreate();
    }
}
