package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzzr;

public interface zzzs extends IInterface {

    public static abstract class zza extends Binder implements zzzs {

        /* renamed from: com.google.android.gms.internal.zzzs$zza$zza  reason: collision with other inner class name */
        private static class C0096zza implements zzzs {
            private IBinder zzrk;

            C0096zza(IBinder iBinder) {
                this.zzrk = iBinder;
            }

            public IBinder asBinder() {
                return this.zzrk;
            }

            public void zza(zzzr zzzr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.clearcut.internal.IClearcutLoggerService");
                    obtain.writeStrongBinder(zzzr != null ? zzzr.asBinder() : null);
                    this.zzrk.transact(2, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void zza(zzzr zzzr, zzzm zzzm) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.clearcut.internal.IClearcutLoggerService");
                    obtain.writeStrongBinder(zzzr != null ? zzzr.asBinder() : null);
                    if (zzzm != null) {
                        obtain.writeInt(1);
                        zzzm.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(1, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void zzb(zzzr zzzr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.clearcut.internal.IClearcutLoggerService");
                    obtain.writeStrongBinder(zzzr != null ? zzzr.asBinder() : null);
                    this.zzrk.transact(3, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void zzc(zzzr zzzr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.clearcut.internal.IClearcutLoggerService");
                    obtain.writeStrongBinder(zzzr != null ? zzzr.asBinder() : null);
                    this.zzrk.transact(4, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }

            public void zzd(zzzr zzzr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.clearcut.internal.IClearcutLoggerService");
                    obtain.writeStrongBinder(zzzr != null ? zzzr.asBinder() : null);
                    this.zzrk.transact(5, obtain, (Parcel) null, 1);
                } finally {
                    obtain.recycle();
                }
            }
        }

        public static zzzs zzbo(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.clearcut.internal.IClearcutLoggerService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzzs)) ? new C0096zza(iBinder) : (zzzs) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface("com.google.android.gms.clearcut.internal.IClearcutLoggerService");
                        zza(zzzr.zza.zzbn(parcel.readStrongBinder()), parcel.readInt() != 0 ? zzzm.CREATOR.createFromParcel(parcel) : null);
                        return true;
                    case 2:
                        parcel.enforceInterface("com.google.android.gms.clearcut.internal.IClearcutLoggerService");
                        zza(zzzr.zza.zzbn(parcel.readStrongBinder()));
                        return true;
                    case 3:
                        parcel.enforceInterface("com.google.android.gms.clearcut.internal.IClearcutLoggerService");
                        zzb(zzzr.zza.zzbn(parcel.readStrongBinder()));
                        return true;
                    case 4:
                        parcel.enforceInterface("com.google.android.gms.clearcut.internal.IClearcutLoggerService");
                        zzc(zzzr.zza.zzbn(parcel.readStrongBinder()));
                        return true;
                    case 5:
                        parcel.enforceInterface("com.google.android.gms.clearcut.internal.IClearcutLoggerService");
                        zzd(zzzr.zza.zzbn(parcel.readStrongBinder()));
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString("com.google.android.gms.clearcut.internal.IClearcutLoggerService");
                return true;
            }
        }
    }

    void zza(zzzr zzzr) throws RemoteException;

    void zza(zzzr zzzr, zzzm zzzm) throws RemoteException;

    void zzb(zzzr zzzr) throws RemoteException;

    void zzc(zzzr zzzr) throws RemoteException;

    void zzd(zzzr zzzr) throws RemoteException;
}
