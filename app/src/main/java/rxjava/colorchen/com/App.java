package rxjava.colorchen.com;

import android.app.Application;

/**
 * 统一的app
 * Created by color on 22/10/2016 18:31.
 */

public class App extends Application {
    private static App self;
    public static App getInstance(){
        return self;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        self = this;
    }
}
