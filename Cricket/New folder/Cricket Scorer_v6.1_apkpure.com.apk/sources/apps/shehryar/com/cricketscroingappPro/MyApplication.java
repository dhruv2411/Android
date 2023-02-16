package apps.shehryar.com.cricketscroingappPro;

import android.support.multidex.MultiDexApplication;
import apps.shehryar.com.cricketscroingappPro.AnalyticsTracker;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.StandardExceptionParser;
import com.google.android.gms.analytics.Tracker;
import java.util.Collection;

public class MyApplication extends MultiDexApplication {
    public static final String TAG = "MyApplication";
    private static MyApplication mInstance;

    public void onCreate() {
        super.onCreate();
        mInstance = this;
        AnalyticsTracker.initialize(this);
        AnalyticsTracker.getInstance().get(AnalyticsTracker.Target.APP);
    }

    public static synchronized MyApplication getInstance() {
        MyApplication myApplication;
        synchronized (MyApplication.class) {
            myApplication = mInstance;
        }
        return myApplication;
    }

    public synchronized Tracker getGoogleAnalyticsTracker() {
        return AnalyticsTracker.getInstance().get(AnalyticsTracker.Target.APP);
    }

    public void trackScreenView(String str) {
        Tracker googleAnalyticsTracker = getGoogleAnalyticsTracker();
        googleAnalyticsTracker.setScreenName(str);
        googleAnalyticsTracker.send(new HitBuilders.ScreenViewBuilder().build());
        GoogleAnalytics.getInstance(this).dispatchLocalHits();
    }

    public void trackException(Exception exc) {
        if (exc != null) {
            getGoogleAnalyticsTracker().send(new HitBuilders.ExceptionBuilder().setDescription(new StandardExceptionParser(this, (Collection<String>) null).getDescription(Thread.currentThread().getName(), exc)).setFatal(false).build());
        }
    }

    public void trackEvent(String str, String str2, String str3) {
        getGoogleAnalyticsTracker().send(new HitBuilders.EventBuilder().setCategory(str).setAction(str2).setLabel(str3).build());
    }
}
