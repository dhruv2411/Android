package io.fabric.sdk.android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import io.fabric.sdk.android.ActivityLifecycleManager;
import io.fabric.sdk.android.services.common.FirebaseInfo;
import io.fabric.sdk.android.services.common.IdManager;
import io.fabric.sdk.android.services.concurrency.DependsOn;
import io.fabric.sdk.android.services.concurrency.PriorityThreadPoolExecutor;
import io.fabric.sdk.android.services.concurrency.Task;
import io.fabric.sdk.android.services.concurrency.UnmetDependencyException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

public class Fabric {
    static final String ANSWERS_KIT_IDENTIFIER = "com.crashlytics.sdk.android:answers";
    static final String CRASHLYTICS_KIT_IDENTIFIER = "com.crashlytics.sdk.android:crashlytics";
    static final boolean DEFAULT_DEBUGGABLE = false;
    static final Logger DEFAULT_LOGGER = new DefaultLogger();
    static final String ROOT_DIR = ".Fabric";
    public static final String TAG = "Fabric";
    static volatile Fabric singleton;
    private WeakReference<Activity> activity;
    private ActivityLifecycleManager activityLifecycleManager;
    private final Context context;
    final boolean debuggable;
    private final ExecutorService executorService;
    private final IdManager idManager;
    /* access modifiers changed from: private */
    public final InitializationCallback<Fabric> initializationCallback;
    /* access modifiers changed from: private */
    public AtomicBoolean initialized = new AtomicBoolean(false);
    private final InitializationCallback<?> kitInitializationCallback;
    private final Map<Class<? extends Kit>, Kit> kits;
    final Logger logger;
    private final Handler mainHandler;

    public String getIdentifier() {
        return "io.fabric.sdk.android:fabric";
    }

    public String getVersion() {
        return "1.4.4.27";
    }

    public static class Builder {
        private String appIdentifier;
        private String appInstallIdentifier;
        private final Context context;
        private boolean debuggable;
        private Handler handler;
        private InitializationCallback<Fabric> initializationCallback;
        private Kit[] kits;
        private Logger logger;
        private PriorityThreadPoolExecutor threadPoolExecutor;

        @Deprecated
        public Builder executorService(ExecutorService executorService) {
            return this;
        }

        @Deprecated
        public Builder handler(Handler handler2) {
            return this;
        }

        public Builder(Context context2) {
            if (context2 == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            this.context = context2;
        }

        public Builder kits(Kit... kitArr) {
            if (this.kits != null) {
                throw new IllegalStateException("Kits already set.");
            }
            if (!new FirebaseInfo().isDataCollectionDefaultEnabled(this.context)) {
                ArrayList arrayList = new ArrayList();
                boolean z = false;
                for (Kit kit : kitArr) {
                    String identifier = kit.getIdentifier();
                    char c = 65535;
                    int hashCode = identifier.hashCode();
                    if (hashCode != 607220212) {
                        if (hashCode == 1830452504 && identifier.equals(Fabric.CRASHLYTICS_KIT_IDENTIFIER)) {
                            c = 0;
                        }
                    } else if (identifier.equals(Fabric.ANSWERS_KIT_IDENTIFIER)) {
                        c = 1;
                    }
                    switch (c) {
                        case 0:
                        case 1:
                            arrayList.add(kit);
                            break;
                        default:
                            if (z) {
                                break;
                            } else {
                                Fabric.getLogger().w(Fabric.TAG, "Fabric will not initialize any kits when Firebase automatic data collection is disabled; to use Third-party kits with automatic data collection disabled, initialize these kits via non-Fabric means.");
                                z = true;
                                break;
                            }
                    }
                }
                kitArr = (Kit[]) arrayList.toArray(new Kit[0]);
            }
            this.kits = kitArr;
            return this;
        }

        public Builder threadPoolExecutor(PriorityThreadPoolExecutor priorityThreadPoolExecutor) {
            if (priorityThreadPoolExecutor == null) {
                throw new IllegalArgumentException("PriorityThreadPoolExecutor must not be null.");
            } else if (this.threadPoolExecutor != null) {
                throw new IllegalStateException("PriorityThreadPoolExecutor already set.");
            } else {
                this.threadPoolExecutor = priorityThreadPoolExecutor;
                return this;
            }
        }

        public Builder logger(Logger logger2) {
            if (logger2 == null) {
                throw new IllegalArgumentException("Logger must not be null.");
            } else if (this.logger != null) {
                throw new IllegalStateException("Logger already set.");
            } else {
                this.logger = logger2;
                return this;
            }
        }

        public Builder appIdentifier(String str) {
            if (str == null) {
                throw new IllegalArgumentException("appIdentifier must not be null.");
            } else if (this.appIdentifier != null) {
                throw new IllegalStateException("appIdentifier already set.");
            } else {
                this.appIdentifier = str;
                return this;
            }
        }

        public Builder appInstallIdentifier(String str) {
            if (str == null) {
                throw new IllegalArgumentException("appInstallIdentifier must not be null.");
            } else if (this.appInstallIdentifier != null) {
                throw new IllegalStateException("appInstallIdentifier already set.");
            } else {
                this.appInstallIdentifier = str;
                return this;
            }
        }

        public Builder debuggable(boolean z) {
            this.debuggable = z;
            return this;
        }

        public Builder initializationCallback(InitializationCallback<Fabric> initializationCallback2) {
            if (initializationCallback2 == null) {
                throw new IllegalArgumentException("initializationCallback must not be null.");
            } else if (this.initializationCallback != null) {
                throw new IllegalStateException("initializationCallback already set.");
            } else {
                this.initializationCallback = initializationCallback2;
                return this;
            }
        }

        public Fabric build() {
            Map access$000;
            if (this.threadPoolExecutor == null) {
                this.threadPoolExecutor = PriorityThreadPoolExecutor.create();
            }
            if (this.handler == null) {
                this.handler = new Handler(Looper.getMainLooper());
            }
            if (this.logger == null) {
                if (this.debuggable) {
                    this.logger = new DefaultLogger(3);
                } else {
                    this.logger = new DefaultLogger();
                }
            }
            if (this.appIdentifier == null) {
                this.appIdentifier = this.context.getPackageName();
            }
            if (this.initializationCallback == null) {
                this.initializationCallback = InitializationCallback.EMPTY;
            }
            if (this.kits == null) {
                access$000 = new HashMap();
            } else {
                access$000 = Fabric.getKitMap(Arrays.asList(this.kits));
            }
            Map map = access$000;
            Context applicationContext = this.context.getApplicationContext();
            return new Fabric(applicationContext, map, this.threadPoolExecutor, this.handler, this.logger, this.debuggable, this.initializationCallback, new IdManager(applicationContext, this.appIdentifier, this.appInstallIdentifier, map.values()), Fabric.extractActivity(this.context));
        }
    }

    static Fabric singleton() {
        if (singleton != null) {
            return singleton;
        }
        throw new IllegalStateException("Must Initialize Fabric before using singleton()");
    }

    Fabric(Context context2, Map<Class<? extends Kit>, Kit> map, PriorityThreadPoolExecutor priorityThreadPoolExecutor, Handler handler, Logger logger2, boolean z, InitializationCallback initializationCallback2, IdManager idManager2, Activity activity2) {
        this.context = context2;
        this.kits = map;
        this.executorService = priorityThreadPoolExecutor;
        this.mainHandler = handler;
        this.logger = logger2;
        this.debuggable = z;
        this.initializationCallback = initializationCallback2;
        this.kitInitializationCallback = createKitInitializationCallback(map.size());
        this.idManager = idManager2;
        setCurrentActivity(activity2);
    }

    public static Fabric with(Context context2, Kit... kitArr) {
        if (singleton == null) {
            synchronized (Fabric.class) {
                if (singleton == null) {
                    setFabric(new Builder(context2).kits(kitArr).build());
                }
            }
        }
        return singleton;
    }

    public static Fabric with(Fabric fabric) {
        if (singleton == null) {
            synchronized (Fabric.class) {
                if (singleton == null) {
                    setFabric(fabric);
                }
            }
        }
        return singleton;
    }

    private static void setFabric(Fabric fabric) {
        singleton = fabric;
        fabric.init();
    }

    public Fabric setCurrentActivity(Activity activity2) {
        this.activity = new WeakReference<>(activity2);
        return this;
    }

    public Activity getCurrentActivity() {
        if (this.activity != null) {
            return (Activity) this.activity.get();
        }
        return null;
    }

    private void init() {
        this.activityLifecycleManager = new ActivityLifecycleManager(this.context);
        this.activityLifecycleManager.registerCallbacks(new ActivityLifecycleManager.Callbacks() {
            public void onActivityCreated(Activity activity, Bundle bundle) {
                Fabric.this.setCurrentActivity(activity);
            }

            public void onActivityStarted(Activity activity) {
                Fabric.this.setCurrentActivity(activity);
            }

            public void onActivityResumed(Activity activity) {
                Fabric.this.setCurrentActivity(activity);
            }
        });
        initializeKits(this.context);
    }

    /* access modifiers changed from: package-private */
    public void initializeKits(Context context2) {
        StringBuilder sb;
        Future<Map<String, KitInfo>> kitsFinderFuture = getKitsFinderFuture(context2);
        Collection<Kit> kits2 = getKits();
        Onboarding onboarding = new Onboarding(kitsFinderFuture, kits2);
        ArrayList<Kit> arrayList = new ArrayList<>(kits2);
        Collections.sort(arrayList);
        onboarding.injectParameters(context2, this, InitializationCallback.EMPTY, this.idManager);
        for (Kit injectParameters : arrayList) {
            injectParameters.injectParameters(context2, this, this.kitInitializationCallback, this.idManager);
        }
        onboarding.initialize();
        if (getLogger().isLoggable(TAG, 3)) {
            sb = new StringBuilder("Initializing ");
            sb.append(getIdentifier());
            sb.append(" [Version: ");
            sb.append(getVersion());
            sb.append("], with the following kits:\n");
        } else {
            sb = null;
        }
        for (Kit kit : arrayList) {
            kit.initializationTask.addDependency((Task) onboarding.initializationTask);
            addAnnotatedDependencies(this.kits, kit);
            kit.initialize();
            if (sb != null) {
                sb.append(kit.getIdentifier());
                sb.append(" [Version: ");
                sb.append(kit.getVersion());
                sb.append("]\n");
            }
        }
        if (sb != null) {
            getLogger().d(TAG, sb.toString());
        }
    }

    /* access modifiers changed from: package-private */
    public void addAnnotatedDependencies(Map<Class<? extends Kit>, Kit> map, Kit kit) {
        DependsOn dependsOn = kit.dependsOnAnnotation;
        if (dependsOn != null) {
            for (Class cls : dependsOn.value()) {
                if (cls.isInterface()) {
                    for (Kit next : map.values()) {
                        if (cls.isAssignableFrom(next.getClass())) {
                            kit.initializationTask.addDependency((Task) next.initializationTask);
                        }
                    }
                } else if (map.get(cls) == null) {
                    throw new UnmetDependencyException("Referenced Kit was null, does the kit exist?");
                } else {
                    kit.initializationTask.addDependency((Task) map.get(cls).initializationTask);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static Activity extractActivity(Context context2) {
        if (context2 instanceof Activity) {
            return (Activity) context2;
        }
        return null;
    }

    public ActivityLifecycleManager getActivityLifecycleManager() {
        return this.activityLifecycleManager;
    }

    public ExecutorService getExecutorService() {
        return this.executorService;
    }

    public Handler getMainHandler() {
        return this.mainHandler;
    }

    public Collection<Kit> getKits() {
        return this.kits.values();
    }

    public static <T extends Kit> T getKit(Class<T> cls) {
        return (Kit) singleton().kits.get(cls);
    }

    public static Logger getLogger() {
        if (singleton == null) {
            return DEFAULT_LOGGER;
        }
        return singleton.logger;
    }

    public static boolean isDebuggable() {
        if (singleton == null) {
            return false;
        }
        return singleton.debuggable;
    }

    public static boolean isInitialized() {
        return singleton != null && singleton.initialized.get();
    }

    public String getAppIdentifier() {
        return this.idManager.getAppIdentifier();
    }

    public String getAppInstallIdentifier() {
        return this.idManager.getAppInstallIdentifier();
    }

    /* access modifiers changed from: private */
    public static Map<Class<? extends Kit>, Kit> getKitMap(Collection<? extends Kit> collection) {
        HashMap hashMap = new HashMap(collection.size());
        addToKitMap(hashMap, collection);
        return hashMap;
    }

    private static void addToKitMap(Map<Class<? extends Kit>, Kit> map, Collection<? extends Kit> collection) {
        for (Kit kit : collection) {
            map.put(kit.getClass(), kit);
            if (kit instanceof KitGroup) {
                addToKitMap(map, ((KitGroup) kit).getKits());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public InitializationCallback<?> createKitInitializationCallback(final int i) {
        return new InitializationCallback() {
            final CountDownLatch kitInitializedLatch = new CountDownLatch(i);

            public void success(Object obj) {
                this.kitInitializedLatch.countDown();
                if (this.kitInitializedLatch.getCount() == 0) {
                    Fabric.this.initialized.set(true);
                    Fabric.this.initializationCallback.success(Fabric.this);
                }
            }

            public void failure(Exception exc) {
                Fabric.this.initializationCallback.failure(exc);
            }
        };
    }

    /* access modifiers changed from: package-private */
    public Future<Map<String, KitInfo>> getKitsFinderFuture(Context context2) {
        return getExecutorService().submit(new FabricKitsFinder(context2.getPackageCodePath()));
    }
}
