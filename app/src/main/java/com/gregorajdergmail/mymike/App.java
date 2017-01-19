package com.gregorajdergmail.mymike;

import android.app.Application;
import android.content.Context;

import com.gregorajdergmail.mymike.di.AppComponent;
import com.gregorajdergmail.mymike.di.DaggerAppComponent;
import com.gregorajdergmail.mymike.util.Log;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;


public class App extends Application {

    private static AppComponent appComponent;
    private static Context context;
    private RefWatcher refWatcher;

    public static Context getContext() {
        return context;
    }

    public static AppComponent getComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        refWatcher  = LeakCanary.install(this);
        context = getApplicationContext();

        appComponent = DaggerAppComponent.
                builder().build();
    }

    public static RefWatcher getRefWatcher(Context context) {
        App application = (App) context.getApplicationContext();
        return application.refWatcher;
    }

}
