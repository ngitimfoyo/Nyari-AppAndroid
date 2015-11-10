package stb.com.nyari.models.adapters;

import android.os.Looper;

import stb.com.nyari.BuildConfig;


/**
 * Created by ziddan on 5/10/14.
 */
public class ThreadPreconditions {
    public static void checkOnMainThread() {
        if (BuildConfig.DEBUG) {
            if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
                throw new IllegalStateException("This method should be called from the Main Thread");
            }
        }
    }
}
