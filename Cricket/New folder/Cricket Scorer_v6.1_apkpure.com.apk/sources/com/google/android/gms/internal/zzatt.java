package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface zzatt extends IInterface {

    public static abstract class zza extends Binder implements zzatt {

        /* renamed from: com.google.android.gms.internal.zzatt$zza$zza  reason: collision with other inner class name */
        private static class C0039zza implements zzatt {
            private IBinder zzrk;

            C0039zza(IBinder iBinder) {
                this.zzrk = iBinder;
            }

            public IBinder asBinder() {
                return this.zzrk;
            }

            public List<zzauq> zza(zzatd zzatd, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                    if (zzatd != null) {
                        obtain.writeInt(1);
                        zzatd.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(z ? 1 : 0);
                    this.zzrk.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(zzauq.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<zzatg> zza(String str, String str2, zzatd zzatd) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (zzatd != null) {
                        obtain.writeInt(1);
                        zzatd.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(zzatg.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<zzauq> zza(String str, String str2, String str3, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeInt(z ? 1 : 0);
                    this.zzrk.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(zzauq.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<zzauq> zza(String str, String str2, boolean z, zzatd zzatd) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(z ? 1 : 0);
                    if (zzatd != null) {
                        obtain.writeInt(1);
                        zzatd.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(zzauq.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(long j, String str, String str2, String str3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    this.zzrk.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzatd zzatd) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                    if (zzatd != null) {
                        obtain.writeInt(1);
                        zzatd.writeToParcel(obtain, 0);
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

            public void zza(zzatg zzatg, zzatd zzatd) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                    if (zzatg != null) {
                        obtain.writeInt(1);
                        zzatg.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (zzatd != null) {
                        obtain.writeInt(1);
                        zzatd.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzatq zzatq, zzatd zzatd) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                    if (zzatq != null) {
                        obtain.writeInt(1);
                        zzatq.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (zzatd != null) {
                        obtain.writeInt(1);
                        zzatd.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzatq zzatq, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                    if (zzatq != null) {
                        obtain.writeInt(1);
                        zzatq.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.zzrk.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zza(zzauq zzauq, zzatd zzatd) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                    if (zzauq != null) {
                        obtain.writeInt(1);
                        zzauq.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (zzatd != null) {
                        obtain.writeInt(1);
                        zzatd.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public byte[] zza(zzatq zzatq, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                    if (zzatq != null) {
                        obtain.writeInt(1);
                        zzatq.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    this.zzrk.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createByteArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzb(zzatd zzatd) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                    if (zzatd != null) {
                        obtain.writeInt(1);
                        zzatd.writeToParcel(obtain, 0);
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

            public void zzb(zzatg zzatg) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                    if (zzatg != null) {
                        obtain.writeInt(1);
                        zzatg.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String zzc(zzatd zzatd) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                    if (zzatd != null) {
                        obtain.writeInt(1);
                        zzatd.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<zzatg> zzn(String str, String str2, String str3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.measurement.internal.IMeasurementService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    this.zzrk.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(zzatg.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public zza() {
            attachInterface(this, "com.google.android.gms.measurement.internal.IMeasurementService");
        }

        public static zzatt zzes(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.measurement.internal.IMeasurementService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzatt)) ? new C0039zza(iBinder) : (zzatt) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: com.google.android.gms.internal.zzatd} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: com.google.android.gms.internal.zzatd} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: com.google.android.gms.internal.zzatd} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v11, resolved type: com.google.android.gms.internal.zzatq} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v14, resolved type: com.google.android.gms.internal.zzatd} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v17, resolved type: com.google.android.gms.internal.zzatd} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v20, resolved type: com.google.android.gms.internal.zzatq} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v23, resolved type: com.google.android.gms.internal.zzatd} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v26, resolved type: com.google.android.gms.internal.zzatd} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v29, resolved type: com.google.android.gms.internal.zzatg} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v32, resolved type: com.google.android.gms.internal.zzatd} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v37, resolved type: com.google.android.gms.internal.zzatd} */
        /* JADX WARNING: type inference failed for: r0v1 */
        /* JADX WARNING: type inference failed for: r0v41 */
        /* JADX WARNING: type inference failed for: r0v42 */
        /* JADX WARNING: type inference failed for: r0v43 */
        /* JADX WARNING: type inference failed for: r0v44 */
        /* JADX WARNING: type inference failed for: r0v45 */
        /* JADX WARNING: type inference failed for: r0v46 */
        /* JADX WARNING: type inference failed for: r0v47 */
        /* JADX WARNING: type inference failed for: r0v48 */
        /* JADX WARNING: type inference failed for: r0v49 */
        /* JADX WARNING: type inference failed for: r0v50 */
        /* JADX WARNING: type inference failed for: r0v51 */
        /* JADX WARNING: type inference failed for: r0v52 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r9, android.os.Parcel r10, android.os.Parcel r11, int r12) throws android.os.RemoteException {
            /*
                r8 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                if (r9 == r0) goto L_0x0224
                r0 = 0
                switch(r9) {
                    case 1: goto L_0x01f9;
                    case 2: goto L_0x01ce;
                    default: goto L_0x000a;
                }
            L_0x000a:
                r2 = 0
                switch(r9) {
                    case 4: goto L_0x01b3;
                    case 5: goto L_0x0190;
                    case 6: goto L_0x0175;
                    case 7: goto L_0x014f;
                    default: goto L_0x000e;
                }
            L_0x000e:
                switch(r9) {
                    case 9: goto L_0x012c;
                    case 10: goto L_0x010f;
                    case 11: goto L_0x00f0;
                    case 12: goto L_0x00c5;
                    case 13: goto L_0x00aa;
                    case 14: goto L_0x007c;
                    case 15: goto L_0x0059;
                    case 16: goto L_0x0032;
                    case 17: goto L_0x0016;
                    default: goto L_0x0011;
                }
            L_0x0011:
                boolean r9 = super.onTransact(r9, r10, r11, r12)
                return r9
            L_0x0016:
                java.lang.String r9 = "com.google.android.gms.measurement.internal.IMeasurementService"
                r10.enforceInterface(r9)
                java.lang.String r9 = r10.readString()
                java.lang.String r12 = r10.readString()
                java.lang.String r10 = r10.readString()
                java.util.List r9 = r8.zzn(r9, r12, r10)
                r11.writeNoException()
                r11.writeTypedList(r9)
                return r1
            L_0x0032:
                java.lang.String r9 = "com.google.android.gms.measurement.internal.IMeasurementService"
                r10.enforceInterface(r9)
                java.lang.String r9 = r10.readString()
                java.lang.String r12 = r10.readString()
                int r2 = r10.readInt()
                if (r2 == 0) goto L_0x004e
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzatd> r0 = com.google.android.gms.internal.zzatd.CREATOR
                java.lang.Object r10 = r0.createFromParcel(r10)
                r0 = r10
                com.google.android.gms.internal.zzatd r0 = (com.google.android.gms.internal.zzatd) r0
            L_0x004e:
                java.util.List r9 = r8.zza((java.lang.String) r9, (java.lang.String) r12, (com.google.android.gms.internal.zzatd) r0)
                r11.writeNoException()
                r11.writeTypedList(r9)
                return r1
            L_0x0059:
                java.lang.String r9 = "com.google.android.gms.measurement.internal.IMeasurementService"
                r10.enforceInterface(r9)
                java.lang.String r9 = r10.readString()
                java.lang.String r12 = r10.readString()
                java.lang.String r0 = r10.readString()
                int r10 = r10.readInt()
                if (r10 == 0) goto L_0x0071
                r2 = r1
            L_0x0071:
                java.util.List r9 = r8.zza((java.lang.String) r9, (java.lang.String) r12, (java.lang.String) r0, (boolean) r2)
                r11.writeNoException()
                r11.writeTypedList(r9)
                return r1
            L_0x007c:
                java.lang.String r9 = "com.google.android.gms.measurement.internal.IMeasurementService"
                r10.enforceInterface(r9)
                java.lang.String r9 = r10.readString()
                java.lang.String r12 = r10.readString()
                int r3 = r10.readInt()
                if (r3 == 0) goto L_0x0090
                r2 = r1
            L_0x0090:
                int r3 = r10.readInt()
                if (r3 == 0) goto L_0x009f
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzatd> r0 = com.google.android.gms.internal.zzatd.CREATOR
                java.lang.Object r10 = r0.createFromParcel(r10)
                r0 = r10
                com.google.android.gms.internal.zzatd r0 = (com.google.android.gms.internal.zzatd) r0
            L_0x009f:
                java.util.List r9 = r8.zza((java.lang.String) r9, (java.lang.String) r12, (boolean) r2, (com.google.android.gms.internal.zzatd) r0)
                r11.writeNoException()
                r11.writeTypedList(r9)
                return r1
            L_0x00aa:
                java.lang.String r9 = "com.google.android.gms.measurement.internal.IMeasurementService"
                r10.enforceInterface(r9)
                int r9 = r10.readInt()
                if (r9 == 0) goto L_0x00be
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzatg> r9 = com.google.android.gms.internal.zzatg.CREATOR
                java.lang.Object r9 = r9.createFromParcel(r10)
                r0 = r9
                com.google.android.gms.internal.zzatg r0 = (com.google.android.gms.internal.zzatg) r0
            L_0x00be:
                r8.zzb((com.google.android.gms.internal.zzatg) r0)
                r11.writeNoException()
                return r1
            L_0x00c5:
                java.lang.String r9 = "com.google.android.gms.measurement.internal.IMeasurementService"
                r10.enforceInterface(r9)
                int r9 = r10.readInt()
                if (r9 == 0) goto L_0x00d9
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzatg> r9 = com.google.android.gms.internal.zzatg.CREATOR
                java.lang.Object r9 = r9.createFromParcel(r10)
                com.google.android.gms.internal.zzatg r9 = (com.google.android.gms.internal.zzatg) r9
                goto L_0x00da
            L_0x00d9:
                r9 = r0
            L_0x00da:
                int r12 = r10.readInt()
                if (r12 == 0) goto L_0x00e9
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzatd> r12 = com.google.android.gms.internal.zzatd.CREATOR
                java.lang.Object r10 = r12.createFromParcel(r10)
                r0 = r10
                com.google.android.gms.internal.zzatd r0 = (com.google.android.gms.internal.zzatd) r0
            L_0x00e9:
                r8.zza((com.google.android.gms.internal.zzatg) r9, (com.google.android.gms.internal.zzatd) r0)
                r11.writeNoException()
                return r1
            L_0x00f0:
                java.lang.String r9 = "com.google.android.gms.measurement.internal.IMeasurementService"
                r10.enforceInterface(r9)
                int r9 = r10.readInt()
                if (r9 == 0) goto L_0x0104
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzatd> r9 = com.google.android.gms.internal.zzatd.CREATOR
                java.lang.Object r9 = r9.createFromParcel(r10)
                r0 = r9
                com.google.android.gms.internal.zzatd r0 = (com.google.android.gms.internal.zzatd) r0
            L_0x0104:
                java.lang.String r9 = r8.zzc(r0)
                r11.writeNoException()
                r11.writeString(r9)
                return r1
            L_0x010f:
                java.lang.String r9 = "com.google.android.gms.measurement.internal.IMeasurementService"
                r10.enforceInterface(r9)
                long r3 = r10.readLong()
                java.lang.String r5 = r10.readString()
                java.lang.String r6 = r10.readString()
                java.lang.String r7 = r10.readString()
                r2 = r8
                r2.zza((long) r3, (java.lang.String) r5, (java.lang.String) r6, (java.lang.String) r7)
                r11.writeNoException()
                return r1
            L_0x012c:
                java.lang.String r9 = "com.google.android.gms.measurement.internal.IMeasurementService"
                r10.enforceInterface(r9)
                int r9 = r10.readInt()
                if (r9 == 0) goto L_0x0140
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzatq> r9 = com.google.android.gms.internal.zzatq.CREATOR
                java.lang.Object r9 = r9.createFromParcel(r10)
                r0 = r9
                com.google.android.gms.internal.zzatq r0 = (com.google.android.gms.internal.zzatq) r0
            L_0x0140:
                java.lang.String r9 = r10.readString()
                byte[] r9 = r8.zza((com.google.android.gms.internal.zzatq) r0, (java.lang.String) r9)
                r11.writeNoException()
                r11.writeByteArray(r9)
                return r1
            L_0x014f:
                java.lang.String r9 = "com.google.android.gms.measurement.internal.IMeasurementService"
                r10.enforceInterface(r9)
                int r9 = r10.readInt()
                if (r9 == 0) goto L_0x0163
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzatd> r9 = com.google.android.gms.internal.zzatd.CREATOR
                java.lang.Object r9 = r9.createFromParcel(r10)
                r0 = r9
                com.google.android.gms.internal.zzatd r0 = (com.google.android.gms.internal.zzatd) r0
            L_0x0163:
                int r9 = r10.readInt()
                if (r9 == 0) goto L_0x016a
                r2 = r1
            L_0x016a:
                java.util.List r9 = r8.zza((com.google.android.gms.internal.zzatd) r0, (boolean) r2)
                r11.writeNoException()
                r11.writeTypedList(r9)
                return r1
            L_0x0175:
                java.lang.String r9 = "com.google.android.gms.measurement.internal.IMeasurementService"
                r10.enforceInterface(r9)
                int r9 = r10.readInt()
                if (r9 == 0) goto L_0x0189
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzatd> r9 = com.google.android.gms.internal.zzatd.CREATOR
                java.lang.Object r9 = r9.createFromParcel(r10)
                r0 = r9
                com.google.android.gms.internal.zzatd r0 = (com.google.android.gms.internal.zzatd) r0
            L_0x0189:
                r8.zzb((com.google.android.gms.internal.zzatd) r0)
                r11.writeNoException()
                return r1
            L_0x0190:
                java.lang.String r9 = "com.google.android.gms.measurement.internal.IMeasurementService"
                r10.enforceInterface(r9)
                int r9 = r10.readInt()
                if (r9 == 0) goto L_0x01a4
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzatq> r9 = com.google.android.gms.internal.zzatq.CREATOR
                java.lang.Object r9 = r9.createFromParcel(r10)
                r0 = r9
                com.google.android.gms.internal.zzatq r0 = (com.google.android.gms.internal.zzatq) r0
            L_0x01a4:
                java.lang.String r9 = r10.readString()
                java.lang.String r10 = r10.readString()
                r8.zza((com.google.android.gms.internal.zzatq) r0, (java.lang.String) r9, (java.lang.String) r10)
                r11.writeNoException()
                return r1
            L_0x01b3:
                java.lang.String r9 = "com.google.android.gms.measurement.internal.IMeasurementService"
                r10.enforceInterface(r9)
                int r9 = r10.readInt()
                if (r9 == 0) goto L_0x01c7
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzatd> r9 = com.google.android.gms.internal.zzatd.CREATOR
                java.lang.Object r9 = r9.createFromParcel(r10)
                r0 = r9
                com.google.android.gms.internal.zzatd r0 = (com.google.android.gms.internal.zzatd) r0
            L_0x01c7:
                r8.zza(r0)
                r11.writeNoException()
                return r1
            L_0x01ce:
                java.lang.String r9 = "com.google.android.gms.measurement.internal.IMeasurementService"
                r10.enforceInterface(r9)
                int r9 = r10.readInt()
                if (r9 == 0) goto L_0x01e2
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzauq> r9 = com.google.android.gms.internal.zzauq.CREATOR
                java.lang.Object r9 = r9.createFromParcel(r10)
                com.google.android.gms.internal.zzauq r9 = (com.google.android.gms.internal.zzauq) r9
                goto L_0x01e3
            L_0x01e2:
                r9 = r0
            L_0x01e3:
                int r12 = r10.readInt()
                if (r12 == 0) goto L_0x01f2
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzatd> r12 = com.google.android.gms.internal.zzatd.CREATOR
                java.lang.Object r10 = r12.createFromParcel(r10)
                r0 = r10
                com.google.android.gms.internal.zzatd r0 = (com.google.android.gms.internal.zzatd) r0
            L_0x01f2:
                r8.zza((com.google.android.gms.internal.zzauq) r9, (com.google.android.gms.internal.zzatd) r0)
                r11.writeNoException()
                return r1
            L_0x01f9:
                java.lang.String r9 = "com.google.android.gms.measurement.internal.IMeasurementService"
                r10.enforceInterface(r9)
                int r9 = r10.readInt()
                if (r9 == 0) goto L_0x020d
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzatq> r9 = com.google.android.gms.internal.zzatq.CREATOR
                java.lang.Object r9 = r9.createFromParcel(r10)
                com.google.android.gms.internal.zzatq r9 = (com.google.android.gms.internal.zzatq) r9
                goto L_0x020e
            L_0x020d:
                r9 = r0
            L_0x020e:
                int r12 = r10.readInt()
                if (r12 == 0) goto L_0x021d
                android.os.Parcelable$Creator<com.google.android.gms.internal.zzatd> r12 = com.google.android.gms.internal.zzatd.CREATOR
                java.lang.Object r10 = r12.createFromParcel(r10)
                r0 = r10
                com.google.android.gms.internal.zzatd r0 = (com.google.android.gms.internal.zzatd) r0
            L_0x021d:
                r8.zza((com.google.android.gms.internal.zzatq) r9, (com.google.android.gms.internal.zzatd) r0)
                r11.writeNoException()
                return r1
            L_0x0224:
                java.lang.String r9 = "com.google.android.gms.measurement.internal.IMeasurementService"
                r11.writeString(r9)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzatt.zza.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    List<zzauq> zza(zzatd zzatd, boolean z) throws RemoteException;

    List<zzatg> zza(String str, String str2, zzatd zzatd) throws RemoteException;

    List<zzauq> zza(String str, String str2, String str3, boolean z) throws RemoteException;

    List<zzauq> zza(String str, String str2, boolean z, zzatd zzatd) throws RemoteException;

    void zza(long j, String str, String str2, String str3) throws RemoteException;

    void zza(zzatd zzatd) throws RemoteException;

    void zza(zzatg zzatg, zzatd zzatd) throws RemoteException;

    void zza(zzatq zzatq, zzatd zzatd) throws RemoteException;

    void zza(zzatq zzatq, String str, String str2) throws RemoteException;

    void zza(zzauq zzauq, zzatd zzatd) throws RemoteException;

    byte[] zza(zzatq zzatq, String str) throws RemoteException;

    void zzb(zzatd zzatd) throws RemoteException;

    void zzb(zzatg zzatg) throws RemoteException;

    String zzc(zzatd zzatd) throws RemoteException;

    List<zzatg> zzn(String str, String str2, String str3) throws RemoteException;
}
