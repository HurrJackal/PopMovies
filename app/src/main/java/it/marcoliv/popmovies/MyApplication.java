package it.marcoliv.popmovies;

import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by marcoliv on 10/6/2016.
 */

public class MyApplication extends Application {

    private int displayHeight;
    private int displayWidth;

    @Override
    public void onCreate() {
        super.onCreate();

        getDimensions();
    }

    private void getDimensions(){
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        displayWidth = size.x;
        displayHeight = size.y;

        Log.d(getClass().getSimpleName(), "display height:" + displayHeight + ", display width:" + displayWidth);
    }

    public int getDisplayWidth(){ return displayWidth; }

    public int getDisplayHeight(){ return  displayHeight; }
}
