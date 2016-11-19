package com.hhzmy.app;

import android.app.Application;

/**
 * Created by a on 2016/11/14.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderUtils.initConfiguration(getApplicationContext());
    }
}

