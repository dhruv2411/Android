package com.google.android.gms.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzke;
import com.google.android.gms.internal.zzkf;
import java.util.List;

public interface zzkb extends IInterface {

    public static abstract class zza extends Binder implements zzkb {

        /* renamed from: com.google.android.gms.internal.zzkb$zza$zza  reason: collision with other inner class name */
        private static class C0073zza implements zzkb {
            private IBinder zzrk;

            C0073zza(IBinder iBinder) {
                this.zzrk = iBinder;
            }

            public IBinder asBinder() {
                return this.zzrk;
            }

            public void destroy() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrk.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle getInterstitialAdapterInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrk.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IObjectWrapper getView() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrk.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return IObjectWrapper.zza.zzcd(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isInitialized() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    boolean z = false;
                    this.zzrk.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void pause() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrk.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void resume() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrk.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void showInterstitial() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrk.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void showVideo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrk.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(IObjectWrapper iObjectWrapper, zzec zzec, String str, zzkc zzkc) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    IBinder iBinder = null;
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    if (zzec != null) {
                        obtain.writeInt(1);
                        zzec.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    if (zzkc != null) {
                        iBinder = zzkc.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.zzrk.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(IObjectWrapper iObjectWrapper, zzec zzec, String str, zzom zzom, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    IBinder iBinder = null;
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    if (zzec != null) {
                        obtain.writeInt(1);
                        zzec.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    if (zzom != null) {
                        iBinder = zzom.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeString(str2);
                    this.zzrk.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(IObjectWrapper iObjectWrapper, zzec zzec, String str, String str2, zzkc zzkc) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    IBinder iBinder = null;
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    if (zzec != null) {
                        obtain.writeInt(1);
                        zzec.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (zzkc != null) {
                        iBinder = zzkc.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.zzrk.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(IObjectWrapper iObjectWrapper, zzec zzec, String str, String str2, zzkc zzkc, zzhc zzhc, List<String> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    IBinder iBinder = null;
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    if (zzec != null) {
                        obtain.writeInt(1);
                        zzec.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (zzkc != null) {
                        iBinder = zzkc.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (zzhc != null) {
                        obtain.writeInt(1);
                        zzhc.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStringList(list);
                    this.zzrk.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(IObjectWrapper iObjectWrapper, zzeg zzeg, zzec zzec, String str, zzkc zzkc) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    IBinder iBinder = null;
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    if (zzeg != null) {
                        obtain.writeInt(1);
                        zzeg.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (zzec != null) {
                        obtain.writeInt(1);
                        zzec.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    if (zzkc != null) {
                        iBinder = zzkc.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.zzrk.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(IObjectWrapper iObjectWrapper, zzeg zzeg, zzec zzec, String str, String str2, zzkc zzkc) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    IBinder iBinder = null;
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    if (zzeg != null) {
                        obtain.writeInt(1);
                        zzeg.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (zzec != null) {
                        obtain.writeInt(1);
                        zzec.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (zzkc != null) {
                        iBinder = zzkc.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.zzrk.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(IObjectWrapper iObjectWrapper, zzom zzom, List<String> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    IBinder iBinder = null;
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    if (zzom != null) {
                        iBinder = zzom.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeStringList(list);
                    this.zzrk.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzec zzec, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    if (zzec != null) {
                        obtain.writeInt(1);
                        zzec.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.zzrk.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzd(zzec zzec, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    if (zzec != null) {
                        obtain.writeInt(1);
                        zzec.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    this.zzrk.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzke zzhc() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrk.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzke.zza.zzQ(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzkf zzhd() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrk.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzkf.zza.zzR(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle zzhe() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrk.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Bundle zzhf() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    this.zzrk.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean zzhg() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    boolean z = false;
                    this.zzrk.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzk(IObjectWrapper iObjectWrapper) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    this.zzrk.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public zza() {
            attachInterface(this, "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
        }

        public static zzkb zzN(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IMediationAdapter");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzkb)) ? new C0073zza(iBinder) : (zzkb) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: com.google.android.gms.internal.zzec} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v18, resolved type: com.google.android.gms.internal.zzec} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v28, resolved type: com.google.android.gms.internal.zzec} */
        /* JADX WARNING: type inference failed for: r2v0 */
        /* JADX WARNING: type inference failed for: r2v1 */
        /* JADX WARNING: type inference failed for: r2v4, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r2v9 */
        /* JADX WARNING: type inference failed for: r2v12 */
        /* JADX WARNING: type inference failed for: r2v15 */
        /* JADX WARNING: type inference failed for: r2v21 */
        /* JADX WARNING: type inference failed for: r2v24, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r2v26, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r2v31 */
        /* JADX WARNING: type inference failed for: r2v32 */
        /* JADX WARNING: type inference failed for: r2v33 */
        /* JADX WARNING: type inference failed for: r2v34 */
        /* JADX WARNING: type inference failed for: r2v35 */
        /* JADX WARNING: type inference failed for: r2v36 */
        /* JADX WARNING: type inference failed for: r2v37 */
        /* JADX WARNING: type inference failed for: r2v38 */
        /* JADX WARNING: type inference failed for: r2v39 */
        /* JADX WARNING: type inference failed for: r2v40 */
        /* JADX WARNING: type inference failed for: r2v41 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r12, android.os.Parcel r13, android.os.Parcel r14, int r15) throws android.os.RemoteException {
            /*
                r11 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                if (r12 == r0) goto L_0x02da
                r0 = 0
                r2 = 0
                switch(r12) {
                    case 1: goto L_0x0298;
                    case 2: goto L_0x0282;
                    case 3: goto L_0x0253;
                    case 4: goto L_0x0247;
                    case 5: goto L_0x023b;
                    case 6: goto L_0x01f5;
                    case 7: goto L_0x01c0;
                    case 8: goto L_0x01b4;
                    case 9: goto L_0x01a8;
                    case 10: goto L_0x0173;
                    case 11: goto L_0x0154;
                    case 12: goto L_0x0148;
                    case 13: goto L_0x0138;
                    case 14: goto L_0x00ee;
                    case 15: goto L_0x00d8;
                    case 16: goto L_0x00c2;
                    case 17: goto L_0x00a9;
                    case 18: goto L_0x0090;
                    case 19: goto L_0x0077;
                    case 20: goto L_0x0054;
                    case 21: goto L_0x0040;
                    case 22: goto L_0x0030;
                    case 23: goto L_0x0010;
                    default: goto L_0x000b;
                }
            L_0x000b:
                boolean r12 = super.onTransact(r12, r13, r14, r15)
                return r12
            L_0x0010:
                java.lang.String r12 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r13.enforceInterface(r12)
                android.os.IBinder r12 = r13.readStrongBinder()
                com.google.android.gms.dynamic.IObjectWrapper r12 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzcd(r12)
                android.os.IBinder r15 = r13.readStrongBinder()
                com.google.android.gms.internal.zzom r15 = com.google.android.gms.internal.zzom.zza.zzal(r15)
                java.util.ArrayList r13 = r13.createStringArrayList()
                r11.zza((com.google.android.gms.dynamic.IObjectWrapper) r12, (com.google.android.gms.internal.zzom) r15, (java.util.List<java.lang.String>) r13)
                r14.writeNoException()
                return r1
            L_0x0030:
                java.lang.String r12 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r13.enforceInterface(r12)
                boolean r12 = r11.zzhg()
                r14.writeNoException()
                r14.writeInt(r12)
                return r1
            L_0x0040:
                java.lang.String r12 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r13.enforceInterface(r12)
                android.os.IBinder r12 = r13.readStrongBinder()
                com.google.android.gms.dynamic.IObjectWrapper r12 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzcd(r12)
                r11.zzk(r12)
                r14.writeNoException()
                return r1
            L_0x0054:
                java.lang.String r12 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r13.enforceInterface(r12)
                int r12 = r13.readInt()
                if (r12 == 0) goto L_0x0068
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzec> r12 = com.google.android.gms.internal.zzec.CREATOR
                java.lang.Object r12 = r12.createFromParcel(r13)
                r2 = r12
                com.google.android.gms.internal.zzec r2 = (com.google.android.gms.internal.zzec) r2
            L_0x0068:
                java.lang.String r12 = r13.readString()
                java.lang.String r13 = r13.readString()
                r11.zza((com.google.android.gms.internal.zzec) r2, (java.lang.String) r12, (java.lang.String) r13)
                r14.writeNoException()
                return r1
            L_0x0077:
                java.lang.String r12 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r13.enforceInterface(r12)
                android.os.Bundle r12 = r11.zzhf()
                r14.writeNoException()
                if (r12 == 0) goto L_0x008c
                r14.writeInt(r1)
                r12.writeToParcel(r14, r1)
                return r1
            L_0x008c:
                r14.writeInt(r0)
                return r1
            L_0x0090:
                java.lang.String r12 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r13.enforceInterface(r12)
                android.os.Bundle r12 = r11.getInterstitialAdapterInfo()
                r14.writeNoException()
                if (r12 == 0) goto L_0x00a5
                r14.writeInt(r1)
                r12.writeToParcel(r14, r1)
                return r1
            L_0x00a5:
                r14.writeInt(r0)
                return r1
            L_0x00a9:
                java.lang.String r12 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r13.enforceInterface(r12)
                android.os.Bundle r12 = r11.zzhe()
                r14.writeNoException()
                if (r12 == 0) goto L_0x00be
                r14.writeInt(r1)
                r12.writeToParcel(r14, r1)
                return r1
            L_0x00be:
                r14.writeInt(r0)
                return r1
            L_0x00c2:
                java.lang.String r12 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r13.enforceInterface(r12)
                com.google.android.gms.internal.zzkf r12 = r11.zzhd()
                r14.writeNoException()
                if (r12 == 0) goto L_0x00d4
                android.os.IBinder r2 = r12.asBinder()
            L_0x00d4:
                r14.writeStrongBinder(r2)
                return r1
            L_0x00d8:
                java.lang.String r12 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r13.enforceInterface(r12)
                com.google.android.gms.internal.zzke r12 = r11.zzhc()
                r14.writeNoException()
                if (r12 == 0) goto L_0x00ea
                android.os.IBinder r2 = r12.asBinder()
            L_0x00ea:
                r14.writeStrongBinder(r2)
                return r1
            L_0x00ee:
                java.lang.String r12 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r13.enforceInterface(r12)
                android.os.IBinder r12 = r13.readStrongBinder()
                com.google.android.gms.dynamic.IObjectWrapper r4 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzcd(r12)
                int r12 = r13.readInt()
                if (r12 == 0) goto L_0x010b
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzec> r12 = com.google.android.gms.internal.zzec.CREATOR
                java.lang.Object r12 = r12.createFromParcel(r13)
                com.google.android.gms.internal.zzec r12 = (com.google.android.gms.internal.zzec) r12
                r5 = r12
                goto L_0x010c
            L_0x010b:
                r5 = r2
            L_0x010c:
                java.lang.String r6 = r13.readString()
                java.lang.String r7 = r13.readString()
                android.os.IBinder r12 = r13.readStrongBinder()
                com.google.android.gms.internal.zzkc r8 = com.google.android.gms.internal.zzkc.zza.zzO(r12)
                int r12 = r13.readInt()
                if (r12 == 0) goto L_0x012b
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzhc> r12 = com.google.android.gms.internal.zzhc.CREATOR
                java.lang.Object r12 = r12.createFromParcel(r13)
                r2 = r12
                com.google.android.gms.internal.zzhc r2 = (com.google.android.gms.internal.zzhc) r2
            L_0x012b:
                r9 = r2
                java.util.ArrayList r10 = r13.createStringArrayList()
                r3 = r11
                r3.zza(r4, r5, r6, r7, r8, r9, r10)
                r14.writeNoException()
                return r1
            L_0x0138:
                java.lang.String r12 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r13.enforceInterface(r12)
                boolean r12 = r11.isInitialized()
                r14.writeNoException()
                r14.writeInt(r12)
                return r1
            L_0x0148:
                java.lang.String r12 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r13.enforceInterface(r12)
                r11.showVideo()
                r14.writeNoException()
                return r1
            L_0x0154:
                java.lang.String r12 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r13.enforceInterface(r12)
                int r12 = r13.readInt()
                if (r12 == 0) goto L_0x0168
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzec> r12 = com.google.android.gms.internal.zzec.CREATOR
                java.lang.Object r12 = r12.createFromParcel(r13)
                r2 = r12
                com.google.android.gms.internal.zzec r2 = (com.google.android.gms.internal.zzec) r2
            L_0x0168:
                java.lang.String r12 = r13.readString()
                r11.zzd(r2, r12)
                r14.writeNoException()
                return r1
            L_0x0173:
                java.lang.String r12 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r13.enforceInterface(r12)
                android.os.IBinder r12 = r13.readStrongBinder()
                com.google.android.gms.dynamic.IObjectWrapper r4 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzcd(r12)
                int r12 = r13.readInt()
                if (r12 == 0) goto L_0x018f
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzec> r12 = com.google.android.gms.internal.zzec.CREATOR
                java.lang.Object r12 = r12.createFromParcel(r13)
                r2 = r12
                com.google.android.gms.internal.zzec r2 = (com.google.android.gms.internal.zzec) r2
            L_0x018f:
                r5 = r2
                java.lang.String r6 = r13.readString()
                android.os.IBinder r12 = r13.readStrongBinder()
                com.google.android.gms.internal.zzom r7 = com.google.android.gms.internal.zzom.zza.zzal(r12)
                java.lang.String r8 = r13.readString()
                r3 = r11
                r3.zza((com.google.android.gms.dynamic.IObjectWrapper) r4, (com.google.android.gms.internal.zzec) r5, (java.lang.String) r6, (com.google.android.gms.internal.zzom) r7, (java.lang.String) r8)
                r14.writeNoException()
                return r1
            L_0x01a8:
                java.lang.String r12 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r13.enforceInterface(r12)
                r11.resume()
                r14.writeNoException()
                return r1
            L_0x01b4:
                java.lang.String r12 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r13.enforceInterface(r12)
                r11.pause()
                r14.writeNoException()
                return r1
            L_0x01c0:
                java.lang.String r12 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r13.enforceInterface(r12)
                android.os.IBinder r12 = r13.readStrongBinder()
                com.google.android.gms.dynamic.IObjectWrapper r4 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzcd(r12)
                int r12 = r13.readInt()
                if (r12 == 0) goto L_0x01dc
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzec> r12 = com.google.android.gms.internal.zzec.CREATOR
                java.lang.Object r12 = r12.createFromParcel(r13)
                r2 = r12
                com.google.android.gms.internal.zzec r2 = (com.google.android.gms.internal.zzec) r2
            L_0x01dc:
                r5 = r2
                java.lang.String r6 = r13.readString()
                java.lang.String r7 = r13.readString()
                android.os.IBinder r12 = r13.readStrongBinder()
                com.google.android.gms.internal.zzkc r8 = com.google.android.gms.internal.zzkc.zza.zzO(r12)
                r3 = r11
                r3.zza((com.google.android.gms.dynamic.IObjectWrapper) r4, (com.google.android.gms.internal.zzec) r5, (java.lang.String) r6, (java.lang.String) r7, (com.google.android.gms.internal.zzkc) r8)
                r14.writeNoException()
                return r1
            L_0x01f5:
                java.lang.String r12 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r13.enforceInterface(r12)
                android.os.IBinder r12 = r13.readStrongBinder()
                com.google.android.gms.dynamic.IObjectWrapper r4 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzcd(r12)
                int r12 = r13.readInt()
                if (r12 == 0) goto L_0x0212
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzeg> r12 = com.google.android.gms.internal.zzeg.CREATOR
                java.lang.Object r12 = r12.createFromParcel(r13)
                com.google.android.gms.internal.zzeg r12 = (com.google.android.gms.internal.zzeg) r12
                r5 = r12
                goto L_0x0213
            L_0x0212:
                r5 = r2
            L_0x0213:
                int r12 = r13.readInt()
                if (r12 == 0) goto L_0x0222
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzec> r12 = com.google.android.gms.internal.zzec.CREATOR
                java.lang.Object r12 = r12.createFromParcel(r13)
                r2 = r12
                com.google.android.gms.internal.zzec r2 = (com.google.android.gms.internal.zzec) r2
            L_0x0222:
                r6 = r2
                java.lang.String r7 = r13.readString()
                java.lang.String r8 = r13.readString()
                android.os.IBinder r12 = r13.readStrongBinder()
                com.google.android.gms.internal.zzkc r9 = com.google.android.gms.internal.zzkc.zza.zzO(r12)
                r3 = r11
                r3.zza(r4, r5, r6, r7, r8, r9)
                r14.writeNoException()
                return r1
            L_0x023b:
                java.lang.String r12 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r13.enforceInterface(r12)
                r11.destroy()
                r14.writeNoException()
                return r1
            L_0x0247:
                java.lang.String r12 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r13.enforceInterface(r12)
                r11.showInterstitial()
                r14.writeNoException()
                return r1
            L_0x0253:
                java.lang.String r12 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r13.enforceInterface(r12)
                android.os.IBinder r12 = r13.readStrongBinder()
                com.google.android.gms.dynamic.IObjectWrapper r12 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzcd(r12)
                int r15 = r13.readInt()
                if (r15 == 0) goto L_0x026f
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzec> r15 = com.google.android.gms.internal.zzec.CREATOR
                java.lang.Object r15 = r15.createFromParcel(r13)
                r2 = r15
                com.google.android.gms.internal.zzec r2 = (com.google.android.gms.internal.zzec) r2
            L_0x026f:
                java.lang.String r15 = r13.readString()
                android.os.IBinder r13 = r13.readStrongBinder()
                com.google.android.gms.internal.zzkc r13 = com.google.android.gms.internal.zzkc.zza.zzO(r13)
                r11.zza(r12, r2, r15, r13)
                r14.writeNoException()
                return r1
            L_0x0282:
                java.lang.String r12 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r13.enforceInterface(r12)
                com.google.android.gms.dynamic.IObjectWrapper r12 = r11.getView()
                r14.writeNoException()
                if (r12 == 0) goto L_0x0294
                android.os.IBinder r2 = r12.asBinder()
            L_0x0294:
                r14.writeStrongBinder(r2)
                return r1
            L_0x0298:
                java.lang.String r12 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r13.enforceInterface(r12)
                android.os.IBinder r12 = r13.readStrongBinder()
                com.google.android.gms.dynamic.IObjectWrapper r4 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzcd(r12)
                int r12 = r13.readInt()
                if (r12 == 0) goto L_0x02b5
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzeg> r12 = com.google.android.gms.internal.zzeg.CREATOR
                java.lang.Object r12 = r12.createFromParcel(r13)
                com.google.android.gms.internal.zzeg r12 = (com.google.android.gms.internal.zzeg) r12
                r5 = r12
                goto L_0x02b6
            L_0x02b5:
                r5 = r2
            L_0x02b6:
                int r12 = r13.readInt()
                if (r12 == 0) goto L_0x02c5
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzec> r12 = com.google.android.gms.internal.zzec.CREATOR
                java.lang.Object r12 = r12.createFromParcel(r13)
                r2 = r12
                com.google.android.gms.internal.zzec r2 = (com.google.android.gms.internal.zzec) r2
            L_0x02c5:
                r6 = r2
                java.lang.String r7 = r13.readString()
                android.os.IBinder r12 = r13.readStrongBinder()
                com.google.android.gms.internal.zzkc r8 = com.google.android.gms.internal.zzkc.zza.zzO(r12)
                r3 = r11
                r3.zza((com.google.android.gms.dynamic.IObjectWrapper) r4, (com.google.android.gms.internal.zzeg) r5, (com.google.android.gms.internal.zzec) r6, (java.lang.String) r7, (com.google.android.gms.internal.zzkc) r8)
                r14.writeNoException()
                return r1
            L_0x02da:
                java.lang.String r12 = "com.google.android.gms.ads.internal.mediation.client.IMediationAdapter"
                r14.writeString(r12)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzkb.zza.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    void destroy() throws RemoteException;

    Bundle getInterstitialAdapterInfo() throws RemoteException;

    IObjectWrapper getView() throws RemoteException;

    boolean isInitialized() throws RemoteException;

    void pause() throws RemoteException;

    void resume() throws RemoteException;

    void showInterstitial() throws RemoteException;

    void showVideo() throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzec zzec, String str, zzkc zzkc) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzec zzec, String str, zzom zzom, String str2) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzec zzec, String str, String str2, zzkc zzkc) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzec zzec, String str, String str2, zzkc zzkc, zzhc zzhc, List<String> list) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzeg zzeg, zzec zzec, String str, zzkc zzkc) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzeg zzeg, zzec zzec, String str, String str2, zzkc zzkc) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzom zzom, List<String> list) throws RemoteException;

    void zza(zzec zzec, String str, String str2) throws RemoteException;

    void zzd(zzec zzec, String str) throws RemoteException;

    zzke zzhc() throws RemoteException;

    zzkf zzhd() throws RemoteException;

    Bundle zzhe() throws RemoteException;

    Bundle zzhf() throws RemoteException;

    boolean zzhg() throws RemoteException;

    void zzk(IObjectWrapper iObjectWrapper) throws RemoteException;
}
