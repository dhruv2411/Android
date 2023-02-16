package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzeq;

public interface zzer extends IInterface {

    public static abstract class zza extends Binder implements zzer {

        /* renamed from: com.google.android.gms.internal.zzer$zza$zza  reason: collision with other inner class name */
        private static class C0049zza implements zzer {
            private IBinder zzrk;

            C0049zza(IBinder iBinder) {
                this.zzrk = iBinder;
            }

            public IBinder asBinder() {
                return this.zzrk;
            }

            public void zza(zzhc zzhc) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
                    if (zzhc != null) {
                        obtain.writeInt(1);
                        zzhc.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzhp zzhp) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
                    obtain.writeStrongBinder(zzhp != null ? zzhp.asBinder() : null);
                    this.zzrk.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzhq zzhq) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
                    obtain.writeStrongBinder(zzhq != null ? zzhq.asBinder() : null);
                    this.zzrk.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(String str, zzhs zzhs, zzhr zzhr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
                    obtain.writeString(str);
                    IBinder iBinder = null;
                    obtain.writeStrongBinder(zzhs != null ? zzhs.asBinder() : null);
                    if (zzhr != null) {
                        iBinder = zzhr.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.zzrk.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzb(zzep zzep) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
                    obtain.writeStrongBinder(zzep != null ? zzep.asBinder() : null);
                    this.zzrk.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzb(zzex zzex) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
                    obtain.writeStrongBinder(zzex != null ? zzex.asBinder() : null);
                    this.zzrk.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzeq zzck() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
                    this.zzrk.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzeq.zza.zzn(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public zza() {
            attachInterface(this, "com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
        }

        public static zzer zzo(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzer)) ? new C0049zza(iBinder) : (zzer) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: com.google.android.gms.internal.zzhc} */
        /* JADX WARNING: type inference failed for: r0v1 */
        /* JADX WARNING: type inference failed for: r0v2, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r0v7 */
        /* JADX WARNING: type inference failed for: r0v8 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r3, android.os.Parcel r4, android.os.Parcel r5, int r6) throws android.os.RemoteException {
            /*
                r2 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                if (r3 == r0) goto L_0x00b0
                r0 = 0
                switch(r3) {
                    case 1: goto L_0x009a;
                    case 2: goto L_0x0086;
                    case 3: goto L_0x0072;
                    case 4: goto L_0x005e;
                    case 5: goto L_0x003e;
                    case 6: goto L_0x0023;
                    case 7: goto L_0x000f;
                    default: goto L_0x000a;
                }
            L_0x000a:
                boolean r3 = super.onTransact(r3, r4, r5, r6)
                return r3
            L_0x000f:
                java.lang.String r3 = "com.google.android.gms.ads.internal.client.IAdLoaderBuilder"
                r4.enforceInterface(r3)
                android.os.IBinder r3 = r4.readStrongBinder()
                com.google.android.gms.internal.zzex r3 = com.google.android.gms.internal.zzex.zza.zzt(r3)
                r2.zzb((com.google.android.gms.internal.zzex) r3)
                r5.writeNoException()
                return r1
            L_0x0023:
                java.lang.String r3 = "com.google.android.gms.ads.internal.client.IAdLoaderBuilder"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                if (r3 == 0) goto L_0x0037
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzhc> r3 = com.google.android.gms.internal.zzhc.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r4)
                r0 = r3
                com.google.android.gms.internal.zzhc r0 = (com.google.android.gms.internal.zzhc) r0
            L_0x0037:
                r2.zza((com.google.android.gms.internal.zzhc) r0)
                r5.writeNoException()
                return r1
            L_0x003e:
                java.lang.String r3 = "com.google.android.gms.ads.internal.client.IAdLoaderBuilder"
                r4.enforceInterface(r3)
                java.lang.String r3 = r4.readString()
                android.os.IBinder r6 = r4.readStrongBinder()
                com.google.android.gms.internal.zzhs r6 = com.google.android.gms.internal.zzhs.zza.zzK(r6)
                android.os.IBinder r4 = r4.readStrongBinder()
                com.google.android.gms.internal.zzhr r4 = com.google.android.gms.internal.zzhr.zza.zzJ(r4)
                r2.zza(r3, r6, r4)
                r5.writeNoException()
                return r1
            L_0x005e:
                java.lang.String r3 = "com.google.android.gms.ads.internal.client.IAdLoaderBuilder"
                r4.enforceInterface(r3)
                android.os.IBinder r3 = r4.readStrongBinder()
                com.google.android.gms.internal.zzhq r3 = com.google.android.gms.internal.zzhq.zza.zzI(r3)
                r2.zza((com.google.android.gms.internal.zzhq) r3)
                r5.writeNoException()
                return r1
            L_0x0072:
                java.lang.String r3 = "com.google.android.gms.ads.internal.client.IAdLoaderBuilder"
                r4.enforceInterface(r3)
                android.os.IBinder r3 = r4.readStrongBinder()
                com.google.android.gms.internal.zzhp r3 = com.google.android.gms.internal.zzhp.zza.zzH(r3)
                r2.zza((com.google.android.gms.internal.zzhp) r3)
                r5.writeNoException()
                return r1
            L_0x0086:
                java.lang.String r3 = "com.google.android.gms.ads.internal.client.IAdLoaderBuilder"
                r4.enforceInterface(r3)
                android.os.IBinder r3 = r4.readStrongBinder()
                com.google.android.gms.internal.zzep r3 = com.google.android.gms.internal.zzep.zza.zzm(r3)
                r2.zzb((com.google.android.gms.internal.zzep) r3)
                r5.writeNoException()
                return r1
            L_0x009a:
                java.lang.String r3 = "com.google.android.gms.ads.internal.client.IAdLoaderBuilder"
                r4.enforceInterface(r3)
                com.google.android.gms.internal.zzeq r3 = r2.zzck()
                r5.writeNoException()
                if (r3 == 0) goto L_0x00ac
                android.os.IBinder r0 = r3.asBinder()
            L_0x00ac:
                r5.writeStrongBinder(r0)
                return r1
            L_0x00b0:
                java.lang.String r3 = "com.google.android.gms.ads.internal.client.IAdLoaderBuilder"
                r5.writeString(r3)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzer.zza.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    void zza(zzhc zzhc) throws RemoteException;

    void zza(zzhp zzhp) throws RemoteException;

    void zza(zzhq zzhq) throws RemoteException;

    void zza(String str, zzhs zzhs, zzhr zzhr) throws RemoteException;

    void zzb(zzep zzep) throws RemoteException;

    void zzb(zzex zzex) throws RemoteException;

    zzeq zzck() throws RemoteException;
}
