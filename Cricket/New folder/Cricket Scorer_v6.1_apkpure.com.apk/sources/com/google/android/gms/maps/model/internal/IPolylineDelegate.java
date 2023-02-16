package com.google.android.gms.maps.model.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.Cap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.List;

public interface IPolylineDelegate extends IInterface {

    public static abstract class zza extends Binder implements IPolylineDelegate {

        /* renamed from: com.google.android.gms.maps.model.internal.IPolylineDelegate$zza$zza  reason: collision with other inner class name */
        private static class C0139zza implements IPolylineDelegate {
            private IBinder zzrk;

            C0139zza(IBinder iBinder) {
                this.zzrk = iBinder;
            }

            public IBinder asBinder() {
                return this.zzrk;
            }

            public boolean equalsRemote(IPolylineDelegate iPolylineDelegate) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    obtain.writeStrongBinder(iPolylineDelegate != null ? iPolylineDelegate.asBinder() : null);
                    boolean z = false;
                    this.zzrk.transact(15, obtain, obtain2, 0);
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

            public int getColor() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    this.zzrk.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Cap getEndCap() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    this.zzrk.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? Cap.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getId() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    this.zzrk.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getJointType() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    this.zzrk.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<PatternItem> getPattern() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    this.zzrk.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(PatternItem.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<LatLng> getPoints() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    this.zzrk.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(LatLng.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Cap getStartCap() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    this.zzrk.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? Cap.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IObjectWrapper getTag() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    this.zzrk.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                    return IObjectWrapper.zza.zzcd(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public float getWidth() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    this.zzrk.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readFloat();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public float getZIndex() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    this.zzrk.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readFloat();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int hashCodeRemote() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    this.zzrk.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isClickable() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    boolean z = false;
                    this.zzrk.transact(18, obtain, obtain2, 0);
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

            public boolean isGeodesic() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    boolean z = false;
                    this.zzrk.transact(14, obtain, obtain2, 0);
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

            public boolean isVisible() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    boolean z = false;
                    this.zzrk.transact(12, obtain, obtain2, 0);
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

            public void remove() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    this.zzrk.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setClickable(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    obtain.writeInt(z ? 1 : 0);
                    this.zzrk.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setColor(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    obtain.writeInt(i);
                    this.zzrk.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setEndCap(Cap cap) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    if (cap != null) {
                        obtain.writeInt(1);
                        cap.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setGeodesic(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    obtain.writeInt(z ? 1 : 0);
                    this.zzrk.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setJointType(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    obtain.writeInt(i);
                    this.zzrk.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setPattern(List<PatternItem> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    obtain.writeTypedList(list);
                    this.zzrk.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setPoints(List<LatLng> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    obtain.writeTypedList(list);
                    this.zzrk.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setStartCap(Cap cap) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    if (cap != null) {
                        obtain.writeInt(1);
                        cap.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setTag(IObjectWrapper iObjectWrapper) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    this.zzrk.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setVisible(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    obtain.writeInt(z ? 1 : 0);
                    this.zzrk.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setWidth(float f) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    obtain.writeFloat(f);
                    this.zzrk.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setZIndex(float f) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IPolylineDelegate");
                    obtain.writeFloat(f);
                    this.zzrk.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static IPolylineDelegate zzep(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.IPolylineDelegate");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IPolylineDelegate)) ? new C0139zza(iBinder) : (IPolylineDelegate) queryLocalInterface;
        }

        /* JADX WARNING: type inference failed for: r0v1 */
        /* JADX WARNING: type inference failed for: r0v2, types: [com.google.android.gms.maps.model.Cap] */
        /* JADX WARNING: type inference failed for: r0v5, types: [com.google.android.gms.maps.model.Cap] */
        /* JADX WARNING: type inference failed for: r0v8, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r0v10 */
        /* JADX WARNING: type inference failed for: r0v11 */
        /* JADX WARNING: type inference failed for: r0v12 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r4, android.os.Parcel r5, android.os.Parcel r6, int r7) throws android.os.RemoteException {
            /*
                r3 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                if (r4 == r0) goto L_0x0213
                r0 = 0
                r2 = 0
                switch(r4) {
                    case 1: goto L_0x0207;
                    case 2: goto L_0x01f7;
                    case 3: goto L_0x01e5;
                    case 4: goto L_0x01d5;
                    case 5: goto L_0x01c5;
                    case 6: goto L_0x01b5;
                    case 7: goto L_0x01a5;
                    case 8: goto L_0x0195;
                    case 9: goto L_0x0185;
                    case 10: goto L_0x0175;
                    case 11: goto L_0x0162;
                    case 12: goto L_0x0152;
                    case 13: goto L_0x013f;
                    case 14: goto L_0x012f;
                    case 15: goto L_0x0117;
                    case 16: goto L_0x0107;
                    case 17: goto L_0x00f4;
                    case 18: goto L_0x00e4;
                    case 19: goto L_0x00c9;
                    case 20: goto L_0x00b0;
                    case 21: goto L_0x0095;
                    case 22: goto L_0x007c;
                    case 23: goto L_0x006c;
                    case 24: goto L_0x005c;
                    case 25: goto L_0x004a;
                    case 26: goto L_0x003a;
                    case 27: goto L_0x0026;
                    case 28: goto L_0x0010;
                    default: goto L_0x000b;
                }
            L_0x000b:
                boolean r4 = super.onTransact(r4, r5, r6, r7)
                return r4
            L_0x0010:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IPolylineDelegate"
                r5.enforceInterface(r4)
                com.google.android.gms.dynamic.IObjectWrapper r4 = r3.getTag()
                r6.writeNoException()
                if (r4 == 0) goto L_0x0022
                android.os.IBinder r0 = r4.asBinder()
            L_0x0022:
                r6.writeStrongBinder(r0)
                return r1
            L_0x0026:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IPolylineDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.dynamic.IObjectWrapper r4 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzcd(r4)
                r3.setTag(r4)
                r6.writeNoException()
                return r1
            L_0x003a:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IPolylineDelegate"
                r5.enforceInterface(r4)
                java.util.List r4 = r3.getPattern()
                r6.writeNoException()
                r6.writeTypedList(r4)
                return r1
            L_0x004a:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IPolylineDelegate"
                r5.enforceInterface(r4)
                android.os.Parcelable$Creator<com.google.android.gms.maps.model.PatternItem> r4 = com.google.android.gms.maps.model.PatternItem.CREATOR
                java.util.ArrayList r4 = r5.createTypedArrayList(r4)
                r3.setPattern(r4)
                r6.writeNoException()
                return r1
            L_0x005c:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IPolylineDelegate"
                r5.enforceInterface(r4)
                int r4 = r3.getJointType()
                r6.writeNoException()
                r6.writeInt(r4)
                return r1
            L_0x006c:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IPolylineDelegate"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                r3.setJointType(r4)
                r6.writeNoException()
                return r1
            L_0x007c:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IPolylineDelegate"
                r5.enforceInterface(r4)
                com.google.android.gms.maps.model.Cap r4 = r3.getEndCap()
                r6.writeNoException()
                if (r4 == 0) goto L_0x0091
                r6.writeInt(r1)
                r4.writeToParcel(r6, r1)
                return r1
            L_0x0091:
                r6.writeInt(r2)
                return r1
            L_0x0095:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IPolylineDelegate"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x00a9
                android.os.Parcelable$Creator<com.google.android.gms.maps.model.Cap> r4 = com.google.android.gms.maps.model.Cap.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r0 = r4
                com.google.android.gms.maps.model.Cap r0 = (com.google.android.gms.maps.model.Cap) r0
            L_0x00a9:
                r3.setEndCap(r0)
                r6.writeNoException()
                return r1
            L_0x00b0:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IPolylineDelegate"
                r5.enforceInterface(r4)
                com.google.android.gms.maps.model.Cap r4 = r3.getStartCap()
                r6.writeNoException()
                if (r4 == 0) goto L_0x00c5
                r6.writeInt(r1)
                r4.writeToParcel(r6, r1)
                return r1
            L_0x00c5:
                r6.writeInt(r2)
                return r1
            L_0x00c9:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IPolylineDelegate"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x00dd
                android.os.Parcelable$Creator<com.google.android.gms.maps.model.Cap> r4 = com.google.android.gms.maps.model.Cap.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r0 = r4
                com.google.android.gms.maps.model.Cap r0 = (com.google.android.gms.maps.model.Cap) r0
            L_0x00dd:
                r3.setStartCap(r0)
                r6.writeNoException()
                return r1
            L_0x00e4:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IPolylineDelegate"
                r5.enforceInterface(r4)
                boolean r4 = r3.isClickable()
                r6.writeNoException()
                r6.writeInt(r4)
                return r1
            L_0x00f4:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IPolylineDelegate"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0100
                r2 = r1
            L_0x0100:
                r3.setClickable(r2)
                r6.writeNoException()
                return r1
            L_0x0107:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IPolylineDelegate"
                r5.enforceInterface(r4)
                int r4 = r3.hashCodeRemote()
                r6.writeNoException()
                r6.writeInt(r4)
                return r1
            L_0x0117:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IPolylineDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.maps.model.internal.IPolylineDelegate r4 = zzep(r4)
                boolean r4 = r3.equalsRemote(r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r1
            L_0x012f:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IPolylineDelegate"
                r5.enforceInterface(r4)
                boolean r4 = r3.isGeodesic()
                r6.writeNoException()
                r6.writeInt(r4)
                return r1
            L_0x013f:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IPolylineDelegate"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x014b
                r2 = r1
            L_0x014b:
                r3.setGeodesic(r2)
                r6.writeNoException()
                return r1
            L_0x0152:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IPolylineDelegate"
                r5.enforceInterface(r4)
                boolean r4 = r3.isVisible()
                r6.writeNoException()
                r6.writeInt(r4)
                return r1
            L_0x0162:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IPolylineDelegate"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x016e
                r2 = r1
            L_0x016e:
                r3.setVisible(r2)
                r6.writeNoException()
                return r1
            L_0x0175:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IPolylineDelegate"
                r5.enforceInterface(r4)
                float r4 = r3.getZIndex()
                r6.writeNoException()
                r6.writeFloat(r4)
                return r1
            L_0x0185:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IPolylineDelegate"
                r5.enforceInterface(r4)
                float r4 = r5.readFloat()
                r3.setZIndex(r4)
                r6.writeNoException()
                return r1
            L_0x0195:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IPolylineDelegate"
                r5.enforceInterface(r4)
                int r4 = r3.getColor()
                r6.writeNoException()
                r6.writeInt(r4)
                return r1
            L_0x01a5:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IPolylineDelegate"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                r3.setColor(r4)
                r6.writeNoException()
                return r1
            L_0x01b5:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IPolylineDelegate"
                r5.enforceInterface(r4)
                float r4 = r3.getWidth()
                r6.writeNoException()
                r6.writeFloat(r4)
                return r1
            L_0x01c5:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IPolylineDelegate"
                r5.enforceInterface(r4)
                float r4 = r5.readFloat()
                r3.setWidth(r4)
                r6.writeNoException()
                return r1
            L_0x01d5:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IPolylineDelegate"
                r5.enforceInterface(r4)
                java.util.List r4 = r3.getPoints()
                r6.writeNoException()
                r6.writeTypedList(r4)
                return r1
            L_0x01e5:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IPolylineDelegate"
                r5.enforceInterface(r4)
                android.os.Parcelable$Creator<com.google.android.gms.maps.model.LatLng> r4 = com.google.android.gms.maps.model.LatLng.CREATOR
                java.util.ArrayList r4 = r5.createTypedArrayList(r4)
                r3.setPoints(r4)
                r6.writeNoException()
                return r1
            L_0x01f7:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IPolylineDelegate"
                r5.enforceInterface(r4)
                java.lang.String r4 = r3.getId()
                r6.writeNoException()
                r6.writeString(r4)
                return r1
            L_0x0207:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IPolylineDelegate"
                r5.enforceInterface(r4)
                r3.remove()
                r6.writeNoException()
                return r1
            L_0x0213:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IPolylineDelegate"
                r6.writeString(r4)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.model.internal.IPolylineDelegate.zza.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    boolean equalsRemote(IPolylineDelegate iPolylineDelegate) throws RemoteException;

    int getColor() throws RemoteException;

    Cap getEndCap() throws RemoteException;

    String getId() throws RemoteException;

    int getJointType() throws RemoteException;

    List<PatternItem> getPattern() throws RemoteException;

    List<LatLng> getPoints() throws RemoteException;

    Cap getStartCap() throws RemoteException;

    IObjectWrapper getTag() throws RemoteException;

    float getWidth() throws RemoteException;

    float getZIndex() throws RemoteException;

    int hashCodeRemote() throws RemoteException;

    boolean isClickable() throws RemoteException;

    boolean isGeodesic() throws RemoteException;

    boolean isVisible() throws RemoteException;

    void remove() throws RemoteException;

    void setClickable(boolean z) throws RemoteException;

    void setColor(int i) throws RemoteException;

    void setEndCap(Cap cap) throws RemoteException;

    void setGeodesic(boolean z) throws RemoteException;

    void setJointType(int i) throws RemoteException;

    void setPattern(List<PatternItem> list) throws RemoteException;

    void setPoints(List<LatLng> list) throws RemoteException;

    void setStartCap(Cap cap) throws RemoteException;

    void setTag(IObjectWrapper iObjectWrapper) throws RemoteException;

    void setVisible(boolean z) throws RemoteException;

    void setWidth(float f) throws RemoteException;

    void setZIndex(float f) throws RemoteException;
}
