package com.google.android.gms.internal;

import android.accounts.Account;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzad;
import com.google.android.gms.common.internal.zzd;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.internal.zzx;

public interface zzbaq extends IInterface {

    public static abstract class zza extends Binder implements zzbaq {

        /* renamed from: com.google.android.gms.internal.zzbaq$zza$zza  reason: collision with other inner class name */
        private static class C0041zza implements zzbaq {
            private IBinder zzrk;

            C0041zza(IBinder iBinder) {
                this.zzrk = iBinder;
            }

            public IBinder asBinder() {
                return this.zzrk;
            }

            public void zza(int i, Account account, zzbap zzbap) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                    obtain.writeInt(i);
                    if (account != null) {
                        obtain.writeInt(1);
                        account.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(zzbap != null ? zzbap.asBinder() : null);
                    this.zzrk.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzad zzad, zzx zzx) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                    if (zzad != null) {
                        obtain.writeInt(1);
                        zzad.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(zzx != null ? zzx.asBinder() : null);
                    this.zzrk.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzd zzd, zzbap zzbap) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                    if (zzd != null) {
                        obtain.writeInt(1);
                        zzd.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(zzbap != null ? zzbap.asBinder() : null);
                    this.zzrk.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzr zzr, int i, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                    obtain.writeStrongBinder(zzr != null ? zzr.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    this.zzrk.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzban zzban) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                    if (zzban != null) {
                        obtain.writeInt(1);
                        zzban.writeToParcel(obtain, 0);
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

            public void zza(zzbar zzbar, zzbap zzbap) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                    if (zzbar != null) {
                        obtain.writeInt(1);
                        zzbar.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(zzbap != null ? zzbap.asBinder() : null);
                    this.zzrk.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzbau zzbau, zzbap zzbap) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                    if (zzbau != null) {
                        obtain.writeInt(1);
                        zzbau.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(zzbap != null ? zzbap.asBinder() : null);
                    this.zzrk.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzaP(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                    obtain.writeInt(z ? 1 : 0);
                    this.zzrk.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzaQ(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                    obtain.writeInt(z ? 1 : 0);
                    this.zzrk.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzb(zzbap zzbap) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                    obtain.writeStrongBinder(zzbap != null ? zzbap.asBinder() : null);
                    this.zzrk.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zznv(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.signin.internal.ISignInService");
                    obtain.writeInt(i);
                    this.zzrk.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static zzbaq zzff(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.signin.internal.ISignInService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzbaq)) ? new C0041zza(iBinder) : (zzbaq) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: com.google.android.gms.common.internal.zzd} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: com.google.android.gms.internal.zzban} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: com.google.android.gms.common.internal.zzad} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: android.accounts.Account} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v13, resolved type: com.google.android.gms.internal.zzbar} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v16, resolved type: com.google.android.gms.internal.zzbau} */
        /* JADX WARNING: type inference failed for: r2v0 */
        /* JADX WARNING: type inference failed for: r2v19 */
        /* JADX WARNING: type inference failed for: r2v20 */
        /* JADX WARNING: type inference failed for: r2v21 */
        /* JADX WARNING: type inference failed for: r2v22 */
        /* JADX WARNING: type inference failed for: r2v23 */
        /* JADX WARNING: type inference failed for: r2v24 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r4, android.os.Parcel r5, android.os.Parcel r6, int r7) throws android.os.RemoteException {
            /*
                r3 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                if (r4 == r0) goto L_0x014a
                r0 = 0
                r2 = 0
                switch(r4) {
                    case 2: goto L_0x0127;
                    case 3: goto L_0x010c;
                    case 4: goto L_0x00f9;
                    case 5: goto L_0x00d6;
                    default: goto L_0x000b;
                }
            L_0x000b:
                switch(r4) {
                    case 7: goto L_0x00c6;
                    case 8: goto L_0x009f;
                    case 9: goto L_0x0080;
                    case 10: goto L_0x005d;
                    case 11: goto L_0x0049;
                    case 12: goto L_0x0026;
                    case 13: goto L_0x0013;
                    default: goto L_0x000e;
                }
            L_0x000e:
                boolean r4 = super.onTransact(r4, r5, r6, r7)
                return r4
            L_0x0013:
                java.lang.String r4 = "com.google.android.gms.signin.internal.ISignInService"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x001f
                r0 = r1
            L_0x001f:
                r3.zzaQ(r0)
                r6.writeNoException()
                return r1
            L_0x0026:
                java.lang.String r4 = "com.google.android.gms.signin.internal.ISignInService"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x003a
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzbau> r4 = com.google.android.gms.internal.zzbau.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r2 = r4
                com.google.android.gms.internal.zzbau r2 = (com.google.android.gms.internal.zzbau) r2
            L_0x003a:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.internal.zzbap r4 = com.google.android.gms.internal.zzbap.zza.zzfe(r4)
                r3.zza((com.google.android.gms.internal.zzbau) r2, (com.google.android.gms.internal.zzbap) r4)
                r6.writeNoException()
                return r1
            L_0x0049:
                java.lang.String r4 = "com.google.android.gms.signin.internal.ISignInService"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.internal.zzbap r4 = com.google.android.gms.internal.zzbap.zza.zzfe(r4)
                r3.zzb(r4)
                r6.writeNoException()
                return r1
            L_0x005d:
                java.lang.String r4 = "com.google.android.gms.signin.internal.ISignInService"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0071
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzbar> r4 = com.google.android.gms.internal.zzbar.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r2 = r4
                com.google.android.gms.internal.zzbar r2 = (com.google.android.gms.internal.zzbar) r2
            L_0x0071:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.internal.zzbap r4 = com.google.android.gms.internal.zzbap.zza.zzfe(r4)
                r3.zza((com.google.android.gms.internal.zzbar) r2, (com.google.android.gms.internal.zzbap) r4)
                r6.writeNoException()
                return r1
            L_0x0080:
                java.lang.String r4 = "com.google.android.gms.signin.internal.ISignInService"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.common.internal.zzr r4 = com.google.android.gms.common.internal.zzr.zza.zzbr(r4)
                int r7 = r5.readInt()
                int r5 = r5.readInt()
                if (r5 == 0) goto L_0x0098
                r0 = r1
            L_0x0098:
                r3.zza((com.google.android.gms.common.internal.zzr) r4, (int) r7, (boolean) r0)
                r6.writeNoException()
                return r1
            L_0x009f:
                java.lang.String r4 = "com.google.android.gms.signin.internal.ISignInService"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                int r7 = r5.readInt()
                if (r7 == 0) goto L_0x00b7
                android.os.Parcelable$Creator r7 = android.accounts.Account.CREATOR
                java.lang.Object r7 = r7.createFromParcel(r5)
                r2 = r7
                android.accounts.Account r2 = (android.accounts.Account) r2
            L_0x00b7:
                android.os.IBinder r5 = r5.readStrongBinder()
                com.google.android.gms.internal.zzbap r5 = com.google.android.gms.internal.zzbap.zza.zzfe(r5)
                r3.zza((int) r4, (android.accounts.Account) r2, (com.google.android.gms.internal.zzbap) r5)
                r6.writeNoException()
                return r1
            L_0x00c6:
                java.lang.String r4 = "com.google.android.gms.signin.internal.ISignInService"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                r3.zznv(r4)
                r6.writeNoException()
                return r1
            L_0x00d6:
                java.lang.String r4 = "com.google.android.gms.signin.internal.ISignInService"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x00ea
                android.os.Parcelable$Creator<com.google.android.gms.common.internal.zzad> r4 = com.google.android.gms.common.internal.zzad.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r2 = r4
                com.google.android.gms.common.internal.zzad r2 = (com.google.android.gms.common.internal.zzad) r2
            L_0x00ea:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.common.internal.zzx r4 = com.google.android.gms.common.internal.zzx.zza.zzbw(r4)
                r3.zza((com.google.android.gms.common.internal.zzad) r2, (com.google.android.gms.common.internal.zzx) r4)
                r6.writeNoException()
                return r1
            L_0x00f9:
                java.lang.String r4 = "com.google.android.gms.signin.internal.ISignInService"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0105
                r0 = r1
            L_0x0105:
                r3.zzaP(r0)
                r6.writeNoException()
                return r1
            L_0x010c:
                java.lang.String r4 = "com.google.android.gms.signin.internal.ISignInService"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0120
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzban> r4 = com.google.android.gms.internal.zzban.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r2 = r4
                com.google.android.gms.internal.zzban r2 = (com.google.android.gms.internal.zzban) r2
            L_0x0120:
                r3.zza(r2)
                r6.writeNoException()
                return r1
            L_0x0127:
                java.lang.String r4 = "com.google.android.gms.signin.internal.ISignInService"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x013b
                android.os.Parcelable$Creator<com.google.android.gms.common.internal.zzd> r4 = com.google.android.gms.common.internal.zzd.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r2 = r4
                com.google.android.gms.common.internal.zzd r2 = (com.google.android.gms.common.internal.zzd) r2
            L_0x013b:
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.internal.zzbap r4 = com.google.android.gms.internal.zzbap.zza.zzfe(r4)
                r3.zza((com.google.android.gms.common.internal.zzd) r2, (com.google.android.gms.internal.zzbap) r4)
                r6.writeNoException()
                return r1
            L_0x014a:
                java.lang.String r4 = "com.google.android.gms.signin.internal.ISignInService"
                r6.writeString(r4)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbaq.zza.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    void zza(int i, Account account, zzbap zzbap) throws RemoteException;

    void zza(zzad zzad, zzx zzx) throws RemoteException;

    void zza(zzd zzd, zzbap zzbap) throws RemoteException;

    void zza(zzr zzr, int i, boolean z) throws RemoteException;

    void zza(zzban zzban) throws RemoteException;

    void zza(zzbar zzbar, zzbap zzbap) throws RemoteException;

    void zza(zzbau zzbau, zzbap zzbap) throws RemoteException;

    void zzaP(boolean z) throws RemoteException;

    void zzaQ(boolean z) throws RemoteException;

    void zzb(zzbap zzbap) throws RemoteException;

    void zznv(int i) throws RemoteException;
}
