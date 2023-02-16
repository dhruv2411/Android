package apps.shehryar.com.cricketscroingappPro;

import android.content.Context;
import apps.shehryar.com.cricketscroingapp.R;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import java.util.HashMap;
import java.util.Map;

public final class AnalyticsTracker {
    private static AnalyticsTracker sInstance;
    private final Context mContext;
    private final Map<Target, Tracker> mTrackers = new HashMap();

    public enum Target {
        APP
    }

    public static synchronized void initialize(Context context) {
        synchronized (AnalyticsTracker.class) {
            if (sInstance != null) {
                throw new IllegalStateException("Extra call to initialize analytics trackers");
            }
            sInstance = new AnalyticsTracker(context);
        }
    }

    public static synchronized AnalyticsTracker getInstance() {
        AnalyticsTracker analyticsTracker;
        synchronized (AnalyticsTracker.class) {
            if (sInstance == null) {
                throw new IllegalStateException("Call initialize() before getInstance()");
            }
            analyticsTracker = sInstance;
        }
        return analyticsTracker;
    }

    private AnalyticsTracker(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public synchronized Tracker get(Target target) {
        if (!this.mTrackers.containsKey(target)) {
            if (AnonymousClass1.$SwitchMap$apps$shehryar$com$cricketscroingappPro$AnalyticsTracker$Target[target.ordinal()] != 1) {
                throw new IllegalArgumentException("Unhandled analytics target " + target);
            }
            this.mTrackers.put(target, GoogleAnalytics.getInstance(this.mContext).newTracker((int) R.xml.app_tracker));
        }
        return this.mTrackers.get(target);
    }

    /* renamed from: apps.shehryar.com.cricketscroingappPro.AnalyticsTracker$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$apps$shehryar$com$cricketscroingappPro$AnalyticsTracker$Target = new int[Target.values().length];

        static {
            try {
                $SwitchMap$apps$shehryar$com$cricketscroingappPro$AnalyticsTracker$Target[Target.APP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }
}
