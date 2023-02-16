package com.google.android.gms.maps.model.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;

public interface zzf extends IInterface {

    public static abstract class zza extends Binder implements zzf {

        /* renamed from: com.google.android.gms.maps.model.internal.zzf$zza$zza  reason: collision with other inner class name */
        private static class C0146zza implements zzf {
            private IBinder zzrk;

            C0146zza(IBinder iBinder) {
                this.zzrk = iBinder;
            }

            public IBinder asBinder() {
                return this.zzrk;
            }

            public float getAlpha() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    this.zzrk.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readFloat();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getId() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    this.zzrk.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public LatLng getPosition() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    this.zzrk.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? LatLng.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public float getRotation() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    this.zzrk.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readFloat();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getSnippet() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    this.zzrk.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IObjectWrapper getTag() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    this.zzrk.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                    return IObjectWrapper.zza.zzcd(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getTitle() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    this.zzrk.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public float getZIndex() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    this.zzrk.transact(28, obtain, obtain2, 0);
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
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    this.zzrk.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void hideInfoWindow() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    this.zzrk.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isDraggable() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    boolean z = false;
                    this.zzrk.transact(10, obtain, obtain2, 0);
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

            public boolean isFlat() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    boolean z = false;
                    this.zzrk.transact(21, obtain, obtain2, 0);
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

            public boolean isInfoWindowShown() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
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

            public boolean isVisible() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
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

            public void remove() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    this.zzrk.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setAlpha(float f) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    obtain.writeFloat(f);
                    this.zzrk.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setAnchor(float f, float f2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    obtain.writeFloat(f);
                    obtain.writeFloat(f2);
                    this.zzrk.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setDraggable(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    obtain.writeInt(z ? 1 : 0);
                    this.zzrk.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setFlat(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    obtain.writeInt(z ? 1 : 0);
                    this.zzrk.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setInfoWindowAnchor(float f, float f2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    obtain.writeFloat(f);
                    obtain.writeFloat(f2);
                    this.zzrk.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setPosition(LatLng latLng) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
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

            public void setRotation(float f) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    obtain.writeFloat(f);
                    this.zzrk.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setSnippet(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    obtain.writeString(str);
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
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    this.zzrk.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setTitle(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    obtain.writeString(str);
                    this.zzrk.transact(5, obtain, obtain2, 0);
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
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    obtain.writeInt(z ? 1 : 0);
                    this.zzrk.transact(14, obtain, obtain2, 0);
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
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    obtain.writeFloat(f);
                    this.zzrk.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void showInfoWindow() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    this.zzrk.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void zzM(IObjectWrapper iObjectWrapper) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    this.zzrk.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean zzj(zzf zzf) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.model.internal.IMarkerDelegate");
                    obtain.writeStrongBinder(zzf != null ? zzf.asBinder() : null);
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
        }

        public static zzf zzen(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.IMarkerDelegate");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzf)) ? new C0146zza(iBinder) : (zzf) queryLocalInterface;
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
                if (r4 == r0) goto L_0x021f
                r0 = 0
                r2 = 0
                switch(r4) {
                    case 1: goto L_0x0213;
                    case 2: goto L_0x0203;
                    case 3: goto L_0x01e8;
                    case 4: goto L_0x01cf;
                    case 5: goto L_0x01bf;
                    case 6: goto L_0x01af;
                    case 7: goto L_0x019f;
                    case 8: goto L_0x018f;
                    case 9: goto L_0x017c;
                    case 10: goto L_0x016c;
                    case 11: goto L_0x0160;
                    case 12: goto L_0x0154;
                    case 13: goto L_0x0144;
                    case 14: goto L_0x0131;
                    case 15: goto L_0x0121;
                    case 16: goto L_0x0109;
                    case 17: goto L_0x00f9;
                    case 18: goto L_0x00e5;
                    case 19: goto L_0x00d1;
                    case 20: goto L_0x00be;
                    case 21: goto L_0x00ae;
                    case 22: goto L_0x009e;
                    case 23: goto L_0x008e;
                    case 24: goto L_0x007a;
                    case 25: goto L_0x006a;
                    case 26: goto L_0x005a;
                    case 27: goto L_0x004a;
                    case 28: goto L_0x003a;
                    case 29: goto L_0x0026;
                    case 30: goto L_0x0010;
                    default: goto L_0x000b;
                }
            L_0x000b:
                boolean r4 = super.onTransact(r4, r5, r6, r7)
                return r4
            L_0x0010:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IMarkerDelegate"
                r5.enforceInterface(r4)
                com.google.android.gms.dynamic.IObjectWrapper r4 = r3.getTag()
                r6.writeNoException()
                if (r4 == 0) goto L_0x0022
                android.os.IBinder r0 = r4.asBinder()
            L_0x0022:
                r6.writeStrongBinder(r0)
                return r1
            L_0x0026:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IMarkerDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.dynamic.IObjectWrapper r4 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzcd(r4)
                r3.setTag(r4)
                r6.writeNoException()
                return r1
            L_0x003a:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IMarkerDelegate"
                r5.enforceInterface(r4)
                float r4 = r3.getZIndex()
                r6.writeNoException()
                r6.writeFloat(r4)
                return r1
            L_0x004a:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IMarkerDelegate"
                r5.enforceInterface(r4)
                float r4 = r5.readFloat()
                r3.setZIndex(r4)
                r6.writeNoException()
                return r1
            L_0x005a:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IMarkerDelegate"
                r5.enforceInterface(r4)
                float r4 = r3.getAlpha()
                r6.writeNoException()
                r6.writeFloat(r4)
                return r1
            L_0x006a:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IMarkerDelegate"
                r5.enforceInterface(r4)
                float r4 = r5.readFloat()
                r3.setAlpha(r4)
                r6.writeNoException()
                return r1
            L_0x007a:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IMarkerDelegate"
                r5.enforceInterface(r4)
                float r4 = r5.readFloat()
                float r5 = r5.readFloat()
                r3.setInfoWindowAnchor(r4, r5)
                r6.writeNoException()
                return r1
            L_0x008e:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IMarkerDelegate"
                r5.enforceInterface(r4)
                float r4 = r3.getRotation()
                r6.writeNoException()
                r6.writeFloat(r4)
                return r1
            L_0x009e:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IMarkerDelegate"
                r5.enforceInterface(r4)
                float r4 = r5.readFloat()
                r3.setRotation(r4)
                r6.writeNoException()
                return r1
            L_0x00ae:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IMarkerDelegate"
                r5.enforceInterface(r4)
                boolean r4 = r3.isFlat()
                r6.writeNoException()
                r6.writeInt(r4)
                return r1
            L_0x00be:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IMarkerDelegate"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x00ca
                r2 = r1
            L_0x00ca:
                r3.setFlat(r2)
                r6.writeNoException()
                return r1
            L_0x00d1:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IMarkerDelegate"
                r5.enforceInterface(r4)
                float r4 = r5.readFloat()
                float r5 = r5.readFloat()
                r3.setAnchor(r4, r5)
                r6.writeNoException()
                return r1
            L_0x00e5:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IMarkerDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.dynamic.IObjectWrapper r4 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzcd(r4)
                r3.zzM(r4)
                r6.writeNoException()
                return r1
            L_0x00f9:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IMarkerDelegate"
                r5.enforceInterface(r4)
                int r4 = r3.hashCodeRemote()
                r6.writeNoException()
                r6.writeInt(r4)
                return r1
            L_0x0109:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IMarkerDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.maps.model.internal.zzf r4 = zzen(r4)
                boolean r4 = r3.zzj(r4)
                r6.writeNoException()
                r6.writeInt(r4)
                return r1
            L_0x0121:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IMarkerDelegate"
                r5.enforceInterface(r4)
                boolean r4 = r3.isVisible()
                r6.writeNoException()
                r6.writeInt(r4)
                return r1
            L_0x0131:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IMarkerDelegate"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x013d
                r2 = r1
            L_0x013d:
                r3.setVisible(r2)
                r6.writeNoException()
                return r1
            L_0x0144:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IMarkerDelegate"
                r5.enforceInterface(r4)
                boolean r4 = r3.isInfoWindowShown()
                r6.writeNoException()
                r6.writeInt(r4)
                return r1
            L_0x0154:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IMarkerDelegate"
                r5.enforceInterface(r4)
                r3.hideInfoWindow()
                r6.writeNoException()
                return r1
            L_0x0160:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IMarkerDelegate"
                r5.enforceInterface(r4)
                r3.showInfoWindow()
                r6.writeNoException()
                return r1
            L_0x016c:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IMarkerDelegate"
                r5.enforceInterface(r4)
                boolean r4 = r3.isDraggable()
                r6.writeNoException()
                r6.writeInt(r4)
                return r1
            L_0x017c:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IMarkerDelegate"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0188
                r2 = r1
            L_0x0188:
                r3.setDraggable(r2)
                r6.writeNoException()
                return r1
            L_0x018f:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IMarkerDelegate"
                r5.enforceInterface(r4)
                java.lang.String r4 = r3.getSnippet()
                r6.writeNoException()
                r6.writeString(r4)
                return r1
            L_0x019f:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IMarkerDelegate"
                r5.enforceInterface(r4)
                java.lang.String r4 = r5.readString()
                r3.setSnippet(r4)
                r6.writeNoException()
                return r1
            L_0x01af:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IMarkerDelegate"
                r5.enforceInterface(r4)
                java.lang.String r4 = r3.getTitle()
                r6.writeNoException()
                r6.writeString(r4)
                return r1
            L_0x01bf:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IMarkerDelegate"
                r5.enforceInterface(r4)
                java.lang.String r4 = r5.readString()
                r3.setTitle(r4)
                r6.writeNoException()
                return r1
            L_0x01cf:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IMarkerDelegate"
                r5.enforceInterface(r4)
                com.google.android.gms.maps.model.LatLng r4 = r3.getPosition()
                r6.writeNoException()
                if (r4 == 0) goto L_0x01e4
                r6.writeInt(r1)
                r4.writeToParcel(r6, r1)
                return r1
            L_0x01e4:
                r6.writeInt(r2)
                return r1
            L_0x01e8:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IMarkerDelegate"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x01fc
                android.os.Parcelable$Creator<com.google.android.gms.maps.model.LatLng> r4 = com.google.android.gms.maps.model.LatLng.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r0 = r4
                com.google.android.gms.maps.model.LatLng r0 = (com.google.android.gms.maps.model.LatLng) r0
            L_0x01fc:
                r3.setPosition(r0)
                r6.writeNoException()
                return r1
            L_0x0203:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IMarkerDelegate"
                r5.enforceInterface(r4)
                java.lang.String r4 = r3.getId()
                r6.writeNoException()
                r6.writeString(r4)
                return r1
            L_0x0213:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IMarkerDelegate"
                r5.enforceInterface(r4)
                r3.remove()
                r6.writeNoException()
                return r1
            L_0x021f:
                java.lang.String r4 = "com.google.android.gms.maps.model.internal.IMarkerDelegate"
                r6.writeString(r4)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.model.internal.zzf.zza.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    float getAlpha() throws RemoteException;

    String getId() throws RemoteException;

    LatLng getPosition() throws RemoteException;

    float getRotation() throws RemoteException;

    String getSnippet() throws RemoteException;

    IObjectWrapper getTag() throws RemoteException;

    String getTitle() throws RemoteException;

    float getZIndex() throws RemoteException;

    int hashCodeRemote() throws RemoteException;

    void hideInfoWindow() throws RemoteException;

    boolean isDraggable() throws RemoteException;

    boolean isFlat() throws RemoteException;

    boolean isInfoWindowShown() throws RemoteException;

    boolean isVisible() throws RemoteException;

    void remove() throws RemoteException;

    void setAlpha(float f) throws RemoteException;

    void setAnchor(float f, float f2) throws RemoteException;

    void setDraggable(boolean z) throws RemoteException;

    void setFlat(boolean z) throws RemoteException;

    void setInfoWindowAnchor(float f, float f2) throws RemoteException;

    void setPosition(LatLng latLng) throws RemoteException;

    void setRotation(float f) throws RemoteException;

    void setSnippet(String str) throws RemoteException;

    void setTag(IObjectWrapper iObjectWrapper) throws RemoteException;

    void setTitle(String str) throws RemoteException;

    void setVisible(boolean z) throws RemoteException;

    void setZIndex(float f) throws RemoteException;

    void showInfoWindow() throws RemoteException;

    void zzM(IObjectWrapper iObjectWrapper) throws RemoteException;

    boolean zzj(zzf zzf) throws RemoteException;
}
