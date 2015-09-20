package com.appex.android.inquisitor.application;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by anuraag on 20/9/15.
 */
public class InitApplication extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "RRnfWamihhrfa7vxpz5XRuqc4kbnj4zzpYavMOlE", "QV9rrmlks6xtSK1koPY5YzVJTMwqL5alv1cMRC2o");

    }
}
