package com.google.android.gms.maps.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.maps.model.internal.zzg;

public interface zzz extends IInterface {

    public static abstract class zza extends Binder implements zzz {

        /* renamed from: com.google.android.gms.maps.internal.zzz$zza$zza  reason: collision with other inner class name */
        private static class C0138zza implements zzz {
            private IBinder zzrk;

            C0138zza(IBinder iBinder) {
                this.zzrk = iBinder;
            }

            public IBinder asBinder() {
                return this.zzrk;
            }

            public void zza(zzg zzg) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IOnPolygonClickListener");
                    obtain.writeStrongBinder(zzg != null ? zzg.asBinder() : null);
                    this.zzrk.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public zza() {
            attachInterface(this, "com.google.android.gms.maps.internal.IOnPolygonClickListener");
        }

        public static zzz zzdU(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IOnPolygonClickListener");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzz)) ? new C0138zza(iBinder) : (zzz) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.google.android.gms.maps.internal.IOnPolygonClickListener");
                zza(zzg.zza.zzeo(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString("com.google.android.gms.maps.internal.IOnPolygonClickListener");
                return true;
            }
        }
    }

    void zza(zzg zzg) throws RemoteException;
}
