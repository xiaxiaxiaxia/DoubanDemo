package run.demo01.doubandemo;

import android.app.Application;

public class MyApplication extends Application {
    private  static MyApplication myApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }

    synchronized public static MyApplication getInstance(){
        if (myApplication == null){
            myApplication = new MyApplication();
        }
        return myApplication;
    }
}
