package foody.com;

import android.app.Application;
import android.content.Context;

import com.google.firebase.FirebaseApp;

public class MySuperAppApplication extends Application {
    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        FirebaseApp.initializeApp(MySuperAppApplication.getContext());
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }
}