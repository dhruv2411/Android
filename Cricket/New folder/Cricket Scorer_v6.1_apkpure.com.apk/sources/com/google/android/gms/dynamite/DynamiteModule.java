package com.google.android.gms.dynamite;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.common.zze;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamite.zza;
import com.google.android.gms.dynamite.zzb;
import dalvik.system.PathClassLoader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public final class DynamiteModule {
    private static Boolean zzaRO;
    private static zza zzaRP;
    private static zzb zzaRQ;
    private static final HashMap<String, byte[]> zzaRR = new HashMap<>();
    private static String zzaRS;
    private static final zzb.zza zzaRT = new zzb.zza() {
        public int zzH(Context context, String str) {
            return DynamiteModule.zzH(context, str);
        }

        public int zzb(Context context, String str, boolean z) throws zza {
            return DynamiteModule.zzb(context, str, z);
        }
    };
    public static final zzb zzaRU = new zzb() {
        public zzb.C0026zzb zza(Context context, String str, zzb.zza zza) throws zza {
            zzb.C0026zzb zzb = new zzb.C0026zzb();
            zzb.zzaSc = zza.zzb(context, str, true);
            if (zzb.zzaSc != 0) {
                zzb.zzaSd = 1;
                return zzb;
            }
            zzb.zzaSb = zza.zzH(context, str);
            if (zzb.zzaSb != 0) {
                zzb.zzaSd = -1;
            }
            return zzb;
        }
    };
    public static final zzb zzaRV = new zzb() {
        public zzb.C0026zzb zza(Context context, String str, zzb.zza zza) throws zza {
            zzb.C0026zzb zzb = new zzb.C0026zzb();
            zzb.zzaSb = zza.zzH(context, str);
            if (zzb.zzaSb != 0) {
                zzb.zzaSd = -1;
                return zzb;
            }
            zzb.zzaSc = zza.zzb(context, str, true);
            if (zzb.zzaSc != 0) {
                zzb.zzaSd = 1;
            }
            return zzb;
        }
    };
    public static final zzb zzaRW = new zzb() {
        public zzb.C0026zzb zza(Context context, String str, zzb.zza zza) throws zza {
            int i;
            zzb.C0026zzb zzb = new zzb.C0026zzb();
            zzb.zzaSb = zza.zzH(context, str);
            zzb.zzaSc = zza.zzb(context, str, true);
            if (zzb.zzaSb == 0 && zzb.zzaSc == 0) {
                i = 0;
            } else if (zzb.zzaSb >= zzb.zzaSc) {
                i = -1;
            } else {
                zzb.zzaSd = 1;
                return zzb;
            }
            zzb.zzaSd = i;
            return zzb;
        }
    };
    public static final zzb zzaRX = new zzb() {
        public zzb.C0026zzb zza(Context context, String str, zzb.zza zza) throws zza {
            int i;
            zzb.C0026zzb zzb = new zzb.C0026zzb();
            zzb.zzaSb = zza.zzH(context, str);
            zzb.zzaSc = zza.zzb(context, str, true);
            if (zzb.zzaSb == 0 && zzb.zzaSc == 0) {
                i = 0;
            } else if (zzb.zzaSc >= zzb.zzaSb) {
                zzb.zzaSd = 1;
                return zzb;
            } else {
                i = -1;
            }
            zzb.zzaSd = i;
            return zzb;
        }
    };
    public static final zzb zzaRY = new zzb() {
        public zzb.C0026zzb zza(Context context, String str, zzb.zza zza) throws zza {
            zzb.C0026zzb zzb = new zzb.C0026zzb();
            zzb.zzaSb = zza.zzH(context, str);
            zzb.zzaSc = zzb.zzaSb != 0 ? zza.zzb(context, str, false) : zza.zzb(context, str, true);
            if (zzb.zzaSb == 0 && zzb.zzaSc == 0) {
                zzb.zzaSd = 0;
                return zzb;
            } else if (zzb.zzaSc >= zzb.zzaSb) {
                zzb.zzaSd = 1;
                return zzb;
            } else {
                zzb.zzaSd = -1;
                return zzb;
            }
        }
    };
    private final Context zzaRZ;

    @DynamiteApi
    public static class DynamiteLoaderClassLoader {
        public static ClassLoader sClassLoader;
    }

    public static class zza extends Exception {
        private zza(String str) {
            super(str);
        }

        private zza(String str, Throwable th) {
            super(str, th);
        }
    }

    public interface zzb {

        public interface zza {
            int zzH(Context context, String str);

            int zzb(Context context, String str, boolean z) throws zza;
        }

        /* renamed from: com.google.android.gms.dynamite.DynamiteModule$zzb$zzb  reason: collision with other inner class name */
        public static class C0026zzb {
            public int zzaSb = 0;
            public int zzaSc = 0;
            public int zzaSd = 0;
        }

        C0026zzb zza(Context context, String str, zza zza2) throws zza;
    }

    private DynamiteModule(Context context) {
        this.zzaRZ = (Context) zzac.zzw(context);
    }

    private static ClassLoader zzBT() {
        return new PathClassLoader(zzaRS, ClassLoader.getSystemClassLoader()) {
            /* access modifiers changed from: protected */
            public Class<?> loadClass(String str, boolean z) throws ClassNotFoundException {
                if (!str.startsWith("java.") && !str.startsWith("android.")) {
                    try {
                        return findClass(str);
                    } catch (ClassNotFoundException unused) {
                    }
                }
                return super.loadClass(str, z);
            }
        };
    }

    public static int zzH(Context context, String str) {
        try {
            ClassLoader classLoader = context.getApplicationContext().getClassLoader();
            String valueOf = String.valueOf("com.google.android.gms.dynamite.descriptors.");
            String valueOf2 = String.valueOf("ModuleDescriptor");
            StringBuilder sb = new StringBuilder(1 + String.valueOf(valueOf).length() + String.valueOf(str).length() + String.valueOf(valueOf2).length());
            sb.append(valueOf);
            sb.append(str);
            sb.append(".");
            sb.append(valueOf2);
            Class<?> loadClass = classLoader.loadClass(sb.toString());
            Field declaredField = loadClass.getDeclaredField("MODULE_ID");
            Field declaredField2 = loadClass.getDeclaredField("MODULE_VERSION");
            if (declaredField.get((Object) null).equals(str)) {
                return declaredField2.getInt((Object) null);
            }
            String valueOf3 = String.valueOf(declaredField.get((Object) null));
            StringBuilder sb2 = new StringBuilder(51 + String.valueOf(valueOf3).length() + String.valueOf(str).length());
            sb2.append("Module descriptor id '");
            sb2.append(valueOf3);
            sb2.append("' didn't match expected id '");
            sb2.append(str);
            sb2.append("'");
            Log.e("DynamiteModule", sb2.toString());
            return 0;
        } catch (ClassNotFoundException unused) {
            StringBuilder sb3 = new StringBuilder(45 + String.valueOf(str).length());
            sb3.append("Local module descriptor class for ");
            sb3.append(str);
            sb3.append(" not found.");
            Log.w("DynamiteModule", sb3.toString());
            return 0;
        } catch (Exception e) {
            String valueOf4 = String.valueOf(e.getMessage());
            Log.e("DynamiteModule", valueOf4.length() != 0 ? "Failed to load module descriptor class: ".concat(valueOf4) : new String("Failed to load module descriptor class: "));
            return 0;
        }
    }

    public static int zzI(Context context, String str) {
        return zzb(context, str, false);
    }

    private static DynamiteModule zzJ(Context context, String str) {
        String valueOf = String.valueOf(str);
        Log.i("DynamiteModule", valueOf.length() != 0 ? "Selected local version of ".concat(valueOf) : new String("Selected local version of "));
        return new DynamiteModule(context.getApplicationContext());
    }

    private static Context zza(Context context, String str, byte[] bArr, zzb zzb2) {
        try {
            return (Context) zzd.zzF(zzb2.zza(zzd.zzA(context), str, bArr));
        } catch (Exception e) {
            String valueOf = String.valueOf(e.toString());
            Log.e("DynamiteModule", valueOf.length() != 0 ? "Failed to load DynamiteLoader: ".concat(valueOf) : new String("Failed to load DynamiteLoader: "));
            return null;
        }
    }

    public static DynamiteModule zza(Context context, zzb zzb2, String str) throws zza {
        zzb.C0026zzb zza2 = zzb2.zza(context, str, zzaRT);
        int i = zza2.zzaSb;
        int i2 = zza2.zzaSc;
        StringBuilder sb = new StringBuilder(68 + String.valueOf(str).length() + String.valueOf(str).length());
        sb.append("Considering local module ");
        sb.append(str);
        sb.append(":");
        sb.append(i);
        sb.append(" and remote module ");
        sb.append(str);
        sb.append(":");
        sb.append(i2);
        Log.i("DynamiteModule", sb.toString());
        if (zza2.zzaSd == 0 || ((zza2.zzaSd == -1 && zza2.zzaSb == 0) || (zza2.zzaSd == 1 && zza2.zzaSc == 0))) {
            int i3 = zza2.zzaSb;
            int i4 = zza2.zzaSc;
            StringBuilder sb2 = new StringBuilder(91);
            sb2.append("No acceptable module found. Local version is ");
            sb2.append(i3);
            sb2.append(" and remote version is ");
            sb2.append(i4);
            sb2.append(".");
            throw new zza(sb2.toString());
        } else if (zza2.zzaSd == -1) {
            return zzJ(context, str);
        } else {
            if (zza2.zzaSd == 1) {
                try {
                    return zza(context, str, zza2.zzaSc);
                } catch (zza e) {
                    String valueOf = String.valueOf(e.getMessage());
                    Log.w("DynamiteModule", valueOf.length() != 0 ? "Failed to load remote module: ".concat(valueOf) : new String("Failed to load remote module: "));
                    if (zza2.zzaSb != 0) {
                        final int i5 = zza2.zzaSb;
                        if (zzb2.zza(context, str, new zzb.zza() {
                            public int zzH(Context context, String str) {
                                return i5;
                            }

                            public int zzb(Context context, String str, boolean z) {
                                return 0;
                            }
                        }).zzaSd == -1) {
                            return zzJ(context, str);
                        }
                    }
                    throw new zza("Remote load failed. No local fallback found.", e);
                }
            } else {
                int i6 = zza2.zzaSd;
                StringBuilder sb3 = new StringBuilder(47);
                sb3.append("VersionPolicy returned invalid code:");
                sb3.append(i6);
                throw new zza(sb3.toString());
            }
        }
    }

    private static DynamiteModule zza(Context context, String str, int i) throws zza {
        Boolean bool;
        synchronized (DynamiteModule.class) {
            bool = zzaRO;
        }
        if (bool != null) {
            return bool.booleanValue() ? zzc(context, str, i) : zzb(context, str, i);
        }
        throw new zza("Failed to determine which loading route to use.");
    }

    private static void zza(ClassLoader classLoader) throws zza {
        try {
            zzaRQ = zzb.zza.zzcf((IBinder) classLoader.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(new Class[0]).newInstance(new Object[0]));
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new zza("Failed to instantiate dynamite loader", e);
        }
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:22:0x0050=Splitter:B:22:0x0050, B:34:0x0072=Splitter:B:34:0x0072, B:17:0x0035=Splitter:B:17:0x0035} */
    public static int zzb(android.content.Context r6, java.lang.String r7, boolean r8) {
        /*
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule> r0 = com.google.android.gms.dynamite.DynamiteModule.class
            monitor-enter(r0)
            java.lang.Boolean r1 = zzaRO     // Catch:{ all -> 0x00e0 }
            if (r1 != 0) goto L_0x00ad
            android.content.Context r1 = r6.getApplicationContext()     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x0083 }
            java.lang.ClassLoader r1 = r1.getClassLoader()     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x0083 }
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule$DynamiteLoaderClassLoader> r2 = com.google.android.gms.dynamite.DynamiteModule.DynamiteLoaderClassLoader.class
            java.lang.String r2 = r2.getName()     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x0083 }
            java.lang.Class r1 = r1.loadClass(r2)     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x0083 }
            java.lang.String r2 = "sClassLoader"
            java.lang.reflect.Field r2 = r1.getDeclaredField(r2)     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x0083 }
            monitor-enter(r1)     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x0083 }
            r3 = 0
            java.lang.Object r4 = r2.get(r3)     // Catch:{ all -> 0x0080 }
            java.lang.ClassLoader r4 = (java.lang.ClassLoader) r4     // Catch:{ all -> 0x0080 }
            if (r4 == 0) goto L_0x0038
            java.lang.ClassLoader r2 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x0080 }
            if (r4 != r2) goto L_0x0032
        L_0x002f:
            java.lang.Boolean r2 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x0080 }
            goto L_0x007d
        L_0x0032:
            zza(r4)     // Catch:{ zza -> 0x0035 }
        L_0x0035:
            java.lang.Boolean r2 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x0080 }
            goto L_0x007d
        L_0x0038:
            java.lang.String r4 = "com.google.android.gms"
            android.content.Context r5 = r6.getApplicationContext()     // Catch:{ all -> 0x0080 }
            java.lang.String r5 = r5.getPackageName()     // Catch:{ all -> 0x0080 }
            boolean r4 = r4.equals(r5)     // Catch:{ all -> 0x0080 }
            if (r4 == 0) goto L_0x0050
            java.lang.ClassLoader r4 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x0080 }
            r2.set(r3, r4)     // Catch:{ all -> 0x0080 }
            goto L_0x002f
        L_0x0050:
            int r4 = zzd(r6, r7, r8)     // Catch:{ zza -> 0x0075 }
            java.lang.String r5 = zzaRS     // Catch:{ zza -> 0x0075 }
            if (r5 == 0) goto L_0x0072
            java.lang.String r5 = zzaRS     // Catch:{ zza -> 0x0075 }
            boolean r5 = r5.isEmpty()     // Catch:{ zza -> 0x0075 }
            if (r5 == 0) goto L_0x0061
            goto L_0x0072
        L_0x0061:
            java.lang.ClassLoader r5 = zzBT()     // Catch:{ zza -> 0x0075 }
            zza(r5)     // Catch:{ zza -> 0x0075 }
            r2.set(r3, r5)     // Catch:{ zza -> 0x0075 }
            java.lang.Boolean r5 = java.lang.Boolean.TRUE     // Catch:{ zza -> 0x0075 }
            zzaRO = r5     // Catch:{ zza -> 0x0075 }
            monitor-exit(r1)     // Catch:{ all -> 0x0080 }
            monitor-exit(r0)     // Catch:{ all -> 0x00e0 }
            return r4
        L_0x0072:
            monitor-exit(r1)     // Catch:{ all -> 0x0080 }
            monitor-exit(r0)     // Catch:{ all -> 0x00e0 }
            return r4
        L_0x0075:
            java.lang.ClassLoader r4 = java.lang.ClassLoader.getSystemClassLoader()     // Catch:{ all -> 0x0080 }
            r2.set(r3, r4)     // Catch:{ all -> 0x0080 }
            goto L_0x002f
        L_0x007d:
            monitor-exit(r1)     // Catch:{ all -> 0x0080 }
            r1 = r2
            goto L_0x00ab
        L_0x0080:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0080 }
            throw r2     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchFieldException -> 0x0083 }
        L_0x0083:
            r1 = move-exception
            java.lang.String r2 = "DynamiteModule"
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x00e0 }
            r3 = 30
            java.lang.String r4 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x00e0 }
            int r4 = r4.length()     // Catch:{ all -> 0x00e0 }
            int r3 = r3 + r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00e0 }
            r4.<init>(r3)     // Catch:{ all -> 0x00e0 }
            java.lang.String r3 = "Failed to load module via V2: "
            r4.append(r3)     // Catch:{ all -> 0x00e0 }
            r4.append(r1)     // Catch:{ all -> 0x00e0 }
            java.lang.String r1 = r4.toString()     // Catch:{ all -> 0x00e0 }
            android.util.Log.w(r2, r1)     // Catch:{ all -> 0x00e0 }
            java.lang.Boolean r1 = java.lang.Boolean.FALSE     // Catch:{ all -> 0x00e0 }
        L_0x00ab:
            zzaRO = r1     // Catch:{ all -> 0x00e0 }
        L_0x00ad:
            monitor-exit(r0)     // Catch:{ all -> 0x00e0 }
            boolean r0 = r1.booleanValue()
            if (r0 == 0) goto L_0x00db
            int r6 = zzd(r6, r7, r8)     // Catch:{ zza -> 0x00b9 }
            return r6
        L_0x00b9:
            r6 = move-exception
            java.lang.String r7 = "DynamiteModule"
            java.lang.String r8 = "Failed to retrieve remote module version: "
            java.lang.String r6 = r6.getMessage()
            java.lang.String r6 = java.lang.String.valueOf(r6)
            int r0 = r6.length()
            if (r0 == 0) goto L_0x00d1
            java.lang.String r6 = r8.concat(r6)
            goto L_0x00d6
        L_0x00d1:
            java.lang.String r6 = new java.lang.String
            r6.<init>(r8)
        L_0x00d6:
            android.util.Log.w(r7, r6)
            r6 = 0
            return r6
        L_0x00db:
            int r6 = zzc((android.content.Context) r6, (java.lang.String) r7, (boolean) r8)
            return r6
        L_0x00e0:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00e0 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zzb(android.content.Context, java.lang.String, boolean):int");
    }

    private static DynamiteModule zzb(Context context, String str, int i) throws zza {
        StringBuilder sb = new StringBuilder(51 + String.valueOf(str).length());
        sb.append("Selected remote version of ");
        sb.append(str);
        sb.append(", version >= ");
        sb.append(i);
        Log.i("DynamiteModule", sb.toString());
        zza zzbm = zzbm(context);
        if (zzbm == null) {
            throw new zza("Failed to create IDynamiteLoader.");
        }
        try {
            IObjectWrapper zza2 = zzbm.zza(zzd.zzA(context), str, i);
            if (zzd.zzF(zza2) != null) {
                return new DynamiteModule((Context) zzd.zzF(zza2));
            }
            throw new zza("Failed to load remote module.");
        } catch (RemoteException e) {
            throw new zza("Failed to load remote module.", e);
        }
    }

    private static zza zzbm(Context context) {
        synchronized (DynamiteModule.class) {
            if (zzaRP != null) {
                zza zza2 = zzaRP;
                return zza2;
            } else if (zze.zzuY().isGooglePlayServicesAvailable(context) != 0) {
                return null;
            } else {
                try {
                    zza zzce = zza.C0027zza.zzce((IBinder) context.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance());
                    if (zzce != null) {
                        zzaRP = zzce;
                        return zzce;
                    }
                } catch (Exception e) {
                    String valueOf = String.valueOf(e.getMessage());
                    Log.e("DynamiteModule", valueOf.length() != 0 ? "Failed to load IDynamiteLoader from GmsCore: ".concat(valueOf) : new String("Failed to load IDynamiteLoader from GmsCore: "));
                }
            }
        }
        return null;
    }

    private static int zzc(Context context, String str, boolean z) {
        zza zzbm = zzbm(context);
        if (zzbm == null) {
            return 0;
        }
        try {
            return zzbm.zza(zzd.zzA(context), str, z);
        } catch (RemoteException e) {
            String valueOf = String.valueOf(e.getMessage());
            Log.w("DynamiteModule", valueOf.length() != 0 ? "Failed to retrieve remote module version: ".concat(valueOf) : new String("Failed to retrieve remote module version: "));
            return 0;
        }
    }

    private static DynamiteModule zzc(Context context, String str, int i) throws zza {
        byte[] bArr;
        zzb zzb2;
        StringBuilder sb = new StringBuilder(51 + String.valueOf(str).length());
        sb.append("Selected remote version of ");
        sb.append(str);
        sb.append(", version >= ");
        sb.append(i);
        Log.i("DynamiteModule", sb.toString());
        synchronized (DynamiteModule.class) {
            HashMap<String, byte[]> hashMap = zzaRR;
            StringBuilder sb2 = new StringBuilder(12 + String.valueOf(str).length());
            sb2.append(str);
            sb2.append(":");
            sb2.append(i);
            bArr = hashMap.get(sb2.toString());
            zzb2 = zzaRQ;
        }
        if (bArr == null) {
            throw new zza("Module implementation could not be found.");
        } else if (zzb2 == null) {
            throw new zza("DynamiteLoaderV2 was not cached.");
        } else {
            Context zza2 = zza(context.getApplicationContext(), str, bArr, zzb2);
            if (zza2 != null) {
                return new DynamiteModule(zza2);
            }
            throw new zza("Failed to get module context");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:41:0x007d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int zzd(android.content.Context r6, java.lang.String r7, boolean r8) throws com.google.android.gms.dynamite.DynamiteModule.zza {
        /*
            r0 = 0
            android.database.Cursor r6 = zze(r6, r7, r8)     // Catch:{ Exception -> 0x006b, all -> 0x0068 }
            if (r6 == 0) goto L_0x0059
            boolean r8 = r6.moveToFirst()     // Catch:{ Exception -> 0x0057 }
            if (r8 != 0) goto L_0x000e
            goto L_0x0059
        L_0x000e:
            r8 = 0
            int r1 = r6.getInt(r8)     // Catch:{ Exception -> 0x0057 }
            if (r1 <= 0) goto L_0x0051
            java.lang.Class<com.google.android.gms.dynamite.DynamiteModule> r2 = com.google.android.gms.dynamite.DynamiteModule.class
            monitor-enter(r2)     // Catch:{ Exception -> 0x0057 }
            r3 = 3
            java.lang.String r3 = r6.getString(r3)     // Catch:{ all -> 0x004e }
            byte[] r8 = android.util.Base64.decode(r3, r8)     // Catch:{ all -> 0x004e }
            java.util.HashMap<java.lang.String, byte[]> r3 = zzaRR     // Catch:{ all -> 0x004e }
            r4 = 12
            java.lang.String r5 = java.lang.String.valueOf(r7)     // Catch:{ all -> 0x004e }
            int r5 = r5.length()     // Catch:{ all -> 0x004e }
            int r4 = r4 + r5
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x004e }
            r5.<init>(r4)     // Catch:{ all -> 0x004e }
            r5.append(r7)     // Catch:{ all -> 0x004e }
            java.lang.String r7 = ":"
            r5.append(r7)     // Catch:{ all -> 0x004e }
            r5.append(r1)     // Catch:{ all -> 0x004e }
            java.lang.String r7 = r5.toString()     // Catch:{ all -> 0x004e }
            r3.put(r7, r8)     // Catch:{ all -> 0x004e }
            r7 = 2
            java.lang.String r7 = r6.getString(r7)     // Catch:{ all -> 0x004e }
            zzaRS = r7     // Catch:{ all -> 0x004e }
            monitor-exit(r2)     // Catch:{ all -> 0x004e }
            goto L_0x0051
        L_0x004e:
            r7 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x004e }
            throw r7     // Catch:{ Exception -> 0x0057 }
        L_0x0051:
            if (r6 == 0) goto L_0x0056
            r6.close()
        L_0x0056:
            return r1
        L_0x0057:
            r7 = move-exception
            goto L_0x006d
        L_0x0059:
            java.lang.String r7 = "DynamiteModule"
            java.lang.String r8 = "Failed to retrieve remote module version."
            android.util.Log.w(r7, r8)     // Catch:{ Exception -> 0x0057 }
            com.google.android.gms.dynamite.DynamiteModule$zza r7 = new com.google.android.gms.dynamite.DynamiteModule$zza     // Catch:{ Exception -> 0x0057 }
            java.lang.String r8 = "Failed to connect to dynamite module ContentResolver."
            r7.<init>((java.lang.String) r8)     // Catch:{ Exception -> 0x0057 }
            throw r7     // Catch:{ Exception -> 0x0057 }
        L_0x0068:
            r7 = move-exception
            r6 = r0
            goto L_0x007b
        L_0x006b:
            r7 = move-exception
            r6 = r0
        L_0x006d:
            boolean r8 = r7 instanceof com.google.android.gms.dynamite.DynamiteModule.zza     // Catch:{ all -> 0x007a }
            if (r8 == 0) goto L_0x0072
            throw r7     // Catch:{ all -> 0x007a }
        L_0x0072:
            com.google.android.gms.dynamite.DynamiteModule$zza r8 = new com.google.android.gms.dynamite.DynamiteModule$zza     // Catch:{ all -> 0x007a }
            java.lang.String r1 = "V2 version check failed"
            r8.<init>(r1, r7)     // Catch:{ all -> 0x007a }
            throw r8     // Catch:{ all -> 0x007a }
        L_0x007a:
            r7 = move-exception
        L_0x007b:
            if (r6 == 0) goto L_0x0080
            r6.close()
        L_0x0080:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteModule.zzd(android.content.Context, java.lang.String, boolean):int");
    }

    public static Cursor zze(Context context, String str, boolean z) {
        String str2 = z ? "api_force_staging" : "api";
        String valueOf = String.valueOf("content://com.google.android.gms.chimera/");
        StringBuilder sb = new StringBuilder(1 + String.valueOf(valueOf).length() + String.valueOf(str2).length() + String.valueOf(str).length());
        sb.append(valueOf);
        sb.append(str2);
        sb.append("/");
        sb.append(str);
        return context.getContentResolver().query(Uri.parse(sb.toString()), (String[]) null, (String) null, (String[]) null, (String) null);
    }

    public Context zzBS() {
        return this.zzaRZ;
    }

    public IBinder zzdT(String str) throws zza {
        try {
            return (IBinder) this.zzaRZ.getClassLoader().loadClass(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            String valueOf = String.valueOf(str);
            throw new zza(valueOf.length() != 0 ? "Failed to instantiate module class: ".concat(valueOf) : new String("Failed to instantiate module class: "), e);
        }
    }
}
