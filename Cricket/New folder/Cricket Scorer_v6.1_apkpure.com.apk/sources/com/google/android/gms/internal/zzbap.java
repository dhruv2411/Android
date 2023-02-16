package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;

public interface zzbap extends IInterface {

    public static abstract class zza extends Binder implements zzbap {

        /* renamed from: com.google.android.gms.internal.zzbap$zza$zza  reason: collision with other inner class name */
        private static class C0040zza implements zzbap {
            private IBinder zzrk;

            C0040zza(IBinder iBinder) {
                this.zzrk = iBinder;
            }

            public IBinder asBinder() {
                return this.zzrk;
            }

            public void zza(ConnectionResult connectionResult, zzbak zzbak) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInCallbacks");
                    if (connectionResult != null) {
                        obtain.writeInt(1);
                        connectionResult.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (zzbak != null) {
                        obtain.writeInt(1);
                        zzbak.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(Status status, GoogleSignInAccount googleSignInAccount) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInCallbacks");
                    if (status != null) {
                        obtain.writeInt(1);
                        status.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (googleSignInAccount != null) {
                        obtain.writeInt(1);
                        googleSignInAccount.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzb(zzbaw zzbaw) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInCallbacks");
                    if (zzbaw != null) {
                        obtain.writeInt(1);
                        zzbaw.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzbL(Status status) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInCallbacks");
                    if (status != null) {
                        obtain.writeInt(1);
                        status.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzbM(Status status) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInCallbacks");
                    if (status != null) {
                        obtain.writeInt(1);
                        status.writeToParcel(obtain, 0);
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
        }

        public zza() {
            attachInterface(this, "com.google.android.gms.signin.internal.ISignInCallbacks");
        }

        public static zzbap zzfe(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.signin.internal.ISignInCallbacks");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzbap)) ? new C0040zza(iBinder) : (zzbap) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: com.google.android.gms.internal.zzbak} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: com.google.android.gms.common.api.Status} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: com.google.android.gms.common.api.Status} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v10, resolved type: com.google.android.gms.auth.api.signin.GoogleSignInAccount} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v13, resolved type: com.google.android.gms.internal.zzbaw} */
        /* JADX WARNING: type inference failed for: r1v0 */
        /* JADX WARNING: type inference failed for: r1v16 */
        /* JADX WARNING: type inference failed for: r1v17 */
        /* JADX WARNING: type inference failed for: r1v18 */
        /* JADX WARNING: type inference failed for: r1v19 */
        /* JADX WARNING: type inference failed for: r1v20 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r3, android.os.Parcel r4, android.os.Parcel r5, int r6) throws android.os.RemoteException {
            /*
                r2 = this;
                r0 = 1
                r1 = 0
                switch(r3) {
                    case 3: goto L_0x008c;
                    case 4: goto L_0x0071;
                    case 6: goto L_0x0056;
                    case 7: goto L_0x002b;
                    case 8: goto L_0x0010;
                    case 1598968902: goto L_0x000a;
                    default: goto L_0x0005;
                }
            L_0x0005:
                boolean r3 = super.onTransact(r3, r4, r5, r6)
                return r3
            L_0x000a:
                java.lang.String r3 = "com.google.android.gms.signin.internal.ISignInCallbacks"
                r5.writeString(r3)
                return r0
            L_0x0010:
                java.lang.String r3 = "com.google.android.gms.signin.internal.ISignInCallbacks"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                if (r3 == 0) goto L_0x0024
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzbaw> r3 = com.google.android.gms.internal.zzbaw.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r4)
                r1 = r3
                com.google.android.gms.internal.zzbaw r1 = (com.google.android.gms.internal.zzbaw) r1
            L_0x0024:
                r2.zzb(r1)
                r5.writeNoException()
                return r0
            L_0x002b:
                java.lang.String r3 = "com.google.android.gms.signin.internal.ISignInCallbacks"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                if (r3 == 0) goto L_0x003f
                android.os.Parcelable$Creator<com.google.android.gms.common.api.Status> r3 = com.google.android.gms.common.api.Status.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r4)
                com.google.android.gms.common.api.Status r3 = (com.google.android.gms.common.api.Status) r3
                goto L_0x0040
            L_0x003f:
                r3 = r1
            L_0x0040:
                int r6 = r4.readInt()
                if (r6 == 0) goto L_0x004f
                android.os.Parcelable$Creator<com.google.android.gms.auth.api.signin.GoogleSignInAccount> r6 = com.google.android.gms.auth.api.signin.GoogleSignInAccount.CREATOR
                java.lang.Object r4 = r6.createFromParcel(r4)
                r1 = r4
                com.google.android.gms.auth.api.signin.GoogleSignInAccount r1 = (com.google.android.gms.auth.api.signin.GoogleSignInAccount) r1
            L_0x004f:
                r2.zza((com.google.android.gms.common.api.Status) r3, (com.google.android.gms.auth.api.signin.GoogleSignInAccount) r1)
                r5.writeNoException()
                return r0
            L_0x0056:
                java.lang.String r3 = "com.google.android.gms.signin.internal.ISignInCallbacks"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                if (r3 == 0) goto L_0x006a
                android.os.Parcelable$Creator<com.google.android.gms.common.api.Status> r3 = com.google.android.gms.common.api.Status.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r4)
                r1 = r3
                com.google.android.gms.common.api.Status r1 = (com.google.android.gms.common.api.Status) r1
            L_0x006a:
                r2.zzbM(r1)
                r5.writeNoException()
                return r0
            L_0x0071:
                java.lang.String r3 = "com.google.android.gms.signin.internal.ISignInCallbacks"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                if (r3 == 0) goto L_0x0085
                android.os.Parcelable$Creator<com.google.android.gms.common.api.Status> r3 = com.google.android.gms.common.api.Status.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r4)
                r1 = r3
                com.google.android.gms.common.api.Status r1 = (com.google.android.gms.common.api.Status) r1
            L_0x0085:
                r2.zzbL(r1)
                r5.writeNoException()
                return r0
            L_0x008c:
                java.lang.String r3 = "com.google.android.gms.signin.internal.ISignInCallbacks"
                r4.enforceInterface(r3)
                int r3 = r4.readInt()
                if (r3 == 0) goto L_0x00a0
                android.os.Parcelable$Creator<com.google.android.gms.common.ConnectionResult> r3 = com.google.android.gms.common.ConnectionResult.CREATOR
                java.lang.Object r3 = r3.createFromParcel(r4)
                com.google.android.gms.common.ConnectionResult r3 = (com.google.android.gms.common.ConnectionResult) r3
                goto L_0x00a1
            L_0x00a0:
                r3 = r1
            L_0x00a1:
                int r6 = r4.readInt()
                if (r6 == 0) goto L_0x00b0
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzbak> r6 = com.google.android.gms.internal.zzbak.CREATOR
                java.lang.Object r4 = r6.createFromParcel(r4)
                r1 = r4
                com.google.android.gms.internal.zzbak r1 = (com.google.android.gms.internal.zzbak) r1
            L_0x00b0:
                r2.zza((com.google.android.gms.common.ConnectionResult) r3, (com.google.android.gms.internal.zzbak) r1)
                r5.writeNoException()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbap.zza.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    void zza(ConnectionResult connectionResult, zzbak zzbak) throws RemoteException;

    void zza(Status status, GoogleSignInAccount googleSignInAccount) throws RemoteException;

    void zzb(zzbaw zzbaw) throws RemoteException;

    void zzbL(Status status) throws RemoteException;

    void zzbM(Status status) throws RemoteException;
}
