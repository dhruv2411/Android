package com.google.android.gms.maps.model.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.List;

public interface zzb extends IInterface {

    public static abstract class zza extends Binder implements zzb {

        /* renamed from: com.google.android.gms.maps.model.internal.zzb$zza$zza  reason: collision with other inner class name */
        private static class C0142zza implements zzb {
            private IBinder zzrk;

            C0142zza(IBinder iBinder) {
                this.zzrk = iBinder;
            }

            public IBinder asBinder() {
                return this.zzrk;
            }

            public LatLng getCenter() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    this.zzrk.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? LatLng.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getFillColor() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    this.zzrk.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getId() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    this.zzrk.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public double getRadius() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    this.zzrk.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readDouble();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getStrokeColor() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    this.zzrk.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public List<PatternItem> getStrokePattern() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    this.zzrk.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createTypedArrayList(PatternItem.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public float getStrokeWidth() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    this.zzrk.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readFloat();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IObjectWrapper getTag() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    this.zzrk.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                    return IObjectWrapper.zza.zzcd(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public float getZIndex() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    this.zzrk.transact(14, obtain, obtain2, 0);
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
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    this.zzrk.transact(18, obtain, obtain2, 0);
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
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    boolean z = false;
                    this.zzrk.transact(20, obtain, obtain2, 0);
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
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    boolean z = false;
                    this.zzrk.transact(16, obtain, obtain2, 0);
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
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    this.zzrk.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setCenter(LatLng latLng) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    if (latLng != null) {
                        obtain.writeInt(1);
                        latLng.writeToParcel(obtain, 0);
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

            public void setClickable(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    obtain.writeInt(z ? 1 : 0);
                    this.zzrk.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setFillColor(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    obtain.writeInt(i);
                    this.zzrk.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setRadius(double d) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    obtain.writeDouble(d);
                    this.zzrk.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setStrokeColor(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    obtain.writeInt(i);
                    this.zzrk.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setStrokePattern(List<PatternItem> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    obtain.writeTypedList(list);
                    this.zzrk.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setStrokeWidth(float f) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    obtain.writeFloat(f);
                    this.zzrk.transact(7, obtain, obtain2, 0);
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
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    this.zzrk.transact(23, obtain, obtain2, 0);
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
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    obtain.writeInt(z ? 1 : 0);
                    this.zzrk.transact(15, obtain, obtain2, 0);
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
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    obtain.writeFloat(f);
                    this.zzrk.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean zzb(zzb zzb) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.ICircleDelegate");
                    obtain.writeStrongBinder(zzb != null ? zzb.asBinder() : null);
                    boolean z = false;
                    this.zzrk.transact(17, obtain, obtain2, 0);
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
        }

        public static zzb zzej(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzb)) ? new C0142zza(iBinder) : (zzb) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: com.google.android.gms.maps.model.LatLng} */
        /* JADX WARNING: type inference failed for: r0v1 */
        /* JADX WARNING: type inference failed for: r0v5, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r0v7 */
        /* JADX WARNING: type inference failed for: r0v8 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r4, android.os.Parcel r5, android.os.Parcel r6, int r7) throws android.os.RemoteException {
            /*
                r3 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                if (r4 == r0) goto L_0x01ba
                r0 = 0
                r2 = 0
                switch(r4) {
                    case 1: goto L_0x01ae;
                    case 2: goto L_0x019e;
                    case 3: goto L_0x0183;
                    case 4: goto L_0x016a;
                    case 5: goto L_0x015a;
                    case 6: goto L_0x014a;
                    case 7: goto L_0x013a;
                    case 8: goto L_0x012a;
                    case 9: goto L_0x011a;
                    case 10: goto L_0x010a;
                    case 11: goto L_0x00fa;
                    case 12: goto L_0x00ea;
                    case 13: goto L_0x00da;
                    case 14: goto L_0x00ca;
                    case 15: goto L_0x00b7;
                    case 16: goto L_0x00a7;
                    case 17: goto L_0x008f;
                    case 18: goto L_0x007f;
                    case 19: goto L_0x006c;
                    case 20: goto L_0x005c;
                    case 21: goto L_0x004a;
                    case 22: goto L_0x003a;
                    case 23: goto L_0x0026;
                    case 24: goto L_0x0010;
                    default: goto L_0x000b;
                }
            L_0x000b:
                boolean r4 = super.onTransact(r4, r5, r6, r7)
                return r4
            L_0x0010:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r5.enforceInterface(r4)
                com.google.android.gms.dynamic.IObjectWrapper r4 = r3.getTag()
                r6.writeNoException()
                if (r4 == 0) goto L_0x0022
                android.os.IBinder r0 = r4.asBinder()
            L_0x0022:
                r6.writeStrongBinder(r0)
                return r1
            L_0x0026:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.dynamic.IObjectWrapper r4 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzcd(r4)
                r3.setTag(r4)
                r6.writeNoException()
                return r1
            L_0x003a:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r5.enforceInterface(r4)
                java.util.List r4 = r3.getStrokePattern()
                r6.writeNoException()
                r6.writeTypedList(r4)
                return r1
            L_0x004a:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r5.enforceInterface(r4)
                android.os.Parcelable$Creator<com.google.android.gms.maps.model.PatternItem> r4 = com.google.android.gms.maps.model.PatternItem.CREATOR
                java.util.ArrayList r4 = r5.createTypedArrayList(r4)
                r3.setStrokePattern(r4)
                r6.writeNoException()
                return r1
            L_0x005c:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r5.enforceInterface(r4)
                boolean r4 = r3.isClickable()
                r6.writeNoException()
                r6.writeInt(r4)
                return r1
            L_0x006c:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0078
                r2 = r1
            L_0x0078:
                r3.setClickable(r2)
                r6.writeNoException()
                return r1
            L_0x007f:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r5.enforceInterface(r4)
                int r4 = r3.hashCodeRemote()
                r6.writeNoException()
                r6.writeInt(r4)
                return r1
            L_0x008f:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.maps.model.internal.zzb r4 = zzej(r4)
                boolean r4 = r3.zzb(r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r1
            L_0x00a7:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r5.enforceInterface(r4)
                boolean r4 = r3.isVisible()
                r6.writeNoException()
                r6.writeInt(r4)
                return r1
            L_0x00b7:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x00c3
                r2 = r1
            L_0x00c3:
                r3.setVisible(r2)
                r6.writeNoException()
                return r1
            L_0x00ca:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r5.enforceInterface(r4)
                float r4 = r3.getZIndex()
                r6.writeNoException()
                r6.writeFloat(r4)
                return r1
            L_0x00da:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r5.enforceInterface(r4)
                float r4 = r5.readFloat()
                r3.setZIndex(r4)
                r6.writeNoException()
                return r1
            L_0x00ea:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r5.enforceInterface(r4)
                int r4 = r3.getFillColor()
                r6.writeNoException()
                r6.writeInt(r4)
                return r1
            L_0x00fa:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                r3.setFillColor(r4)
                r6.writeNoException()
                return r1
            L_0x010a:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r5.enforceInterface(r4)
                int r4 = r3.getStrokeColor()
                r6.writeNoException()
                r6.writeInt(r4)
                return r1
            L_0x011a:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                r3.setStrokeColor(r4)
                r6.writeNoException()
                return r1
            L_0x012a:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r5.enforceInterface(r4)
                float r4 = r3.getStrokeWidth()
                r6.writeNoException()
                r6.writeFloat(r4)
                return r1
            L_0x013a:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r5.enforceInterface(r4)
                float r4 = r5.readFloat()
                r3.setStrokeWidth(r4)
                r6.writeNoException()
                return r1
            L_0x014a:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r5.enforceInterface(r4)
                double r4 = r3.getRadius()
                r6.writeNoException()
                r6.writeDouble(r4)
                return r1
            L_0x015a:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r5.enforceInterface(r4)
                double r4 = r5.readDouble()
                r3.setRadius(r4)
                r6.writeNoException()
                return r1
            L_0x016a:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r5.enforceInterface(r4)
                com.google.android.gms.maps.model.LatLng r4 = r3.getCenter()
                r6.writeNoException()
                if (r4 == 0) goto L_0x017f
                r6.writeInt(r1)
                r4.writeToParcel(r6, r1)
                return r1
            L_0x017f:
                r6.writeInt(r2)
                return r1
            L_0x0183:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0197
                android.os.Parcelable$Creator<com.google.android.gms.maps.model.LatLng> r4 = com.google.android.gms.maps.model.LatLng.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r0 = r4
                com.google.android.gms.maps.model.LatLng r0 = (com.google.android.gms.maps.model.LatLng) r0
            L_0x0197:
                r3.setCenter(r0)
                r6.writeNoException()
                return r1
            L_0x019e:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r5.enforceInterface(r4)
                java.lang.String r4 = r3.getId()
                r6.writeNoException()
                r6.writeString(r4)
                return r1
            L_0x01ae:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r5.enforceInterface(r4)
                r3.remove()
                r6.writeNoException()
                return r1
            L_0x01ba:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.ICircleDelegate"
                r6.writeString(r4)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.model.internal.zzb.zza.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    LatLng getCenter() throws RemoteException;

    int getFillColor() throws RemoteException;

    String getId() throws RemoteException;

    double getRadius() throws RemoteException;

    int getStrokeColor() throws RemoteException;

    List<PatternItem> getStrokePattern() throws RemoteException;

    float getStrokeWidth() throws RemoteException;

    IObjectWrapper getTag() throws RemoteException;

    float getZIndex() throws RemoteException;

    int hashCodeRemote() throws RemoteException;

    boolean isClickable() throws RemoteException;

    boolean isVisible() throws RemoteException;

    void remove() throws RemoteException;

    void setCenter(LatLng latLng) throws RemoteException;

    void setClickable(boolean z) throws RemoteException;

    void setFillColor(int i) throws RemoteException;

    void setRadius(double d) throws RemoteException;

    void setStrokeColor(int i) throws RemoteException;

    void setStrokePattern(List<PatternItem> list) throws RemoteException;

    void setStrokeWidth(float f) throws RemoteException;

    void setTag(IObjectWrapper iObjectWrapper) throws RemoteException;

    void setVisible(boolean z) throws RemoteException;

    void setZIndex(float f) throws RemoteException;

    boolean zzb(zzb zzb) throws RemoteException;
}
