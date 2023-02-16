package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.internal.IProjectionDelegate;
import com.google.android.gms.maps.internal.IUiSettingsDelegate;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.internal.IPolylineDelegate;
import com.google.android.gms.maps.model.internal.zzb;
import com.google.android.gms.maps.model.internal.zzc;
import com.google.android.gms.maps.model.internal.zzd;
import com.google.android.gms.maps.model.internal.zzf;
import com.google.android.gms.maps.model.internal.zzg;
import com.google.android.gms.maps.model.internal.zzh;

public interface IGoogleMapDelegate extends IInterface {

    public static abstract class zza extends Binder implements IGoogleMapDelegate {

        /* renamed from: com.google.android.gms.maps.internal.IGoogleMapDelegate$zza$zza  reason: collision with other inner class name */
        private static class C0098zza implements IGoogleMapDelegate {
            private IBinder zzrk;

            C0098zza(IBinder iBinder) {
                this.zzrk = iBinder;
            }

            public zzb addCircle(CircleOptions circleOptions) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (circleOptions != null) {
                        obtain.writeInt(1);
                        circleOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(35, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzb.zza.zzej(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzc addGroundOverlay(GroundOverlayOptions groundOverlayOptions) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (groundOverlayOptions != null) {
                        obtain.writeInt(1);
                        groundOverlayOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzc.zza.zzek(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzf addMarker(MarkerOptions markerOptions) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (markerOptions != null) {
                        obtain.writeInt(1);
                        markerOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzf.zza.zzen(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzg addPolygon(PolygonOptions polygonOptions) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (polygonOptions != null) {
                        obtain.writeInt(1);
                        polygonOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzg.zza.zzeo(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IPolylineDelegate addPolyline(PolylineOptions polylineOptions) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (polylineOptions != null) {
                        obtain.writeInt(1);
                        polylineOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return IPolylineDelegate.zza.zzep(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzh addTileOverlay(TileOverlayOptions tileOverlayOptions) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (tileOverlayOptions != null) {
                        obtain.writeInt(1);
                        tileOverlayOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzh.zza.zzeq(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void animateCamera(IObjectWrapper iObjectWrapper) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    this.zzrk.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void animateCameraWithCallback(IObjectWrapper iObjectWrapper, zzb zzb) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    IBinder iBinder = null;
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    if (zzb != null) {
                        iBinder = zzb.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.zzrk.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void animateCameraWithDurationAndCallback(IObjectWrapper iObjectWrapper, int i, zzb zzb) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    IBinder iBinder = null;
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    obtain.writeInt(i);
                    if (zzb != null) {
                        iBinder = zzb.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.zzrk.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.zzrk;
            }

            public void clear() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public CameraPosition getCameraPosition() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? CameraPosition.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzd getFocusedBuilding() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(44, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzd.zza.zzel(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void getMapAsync(zzt zzt) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(zzt != null ? zzt.asBinder() : null);
                    this.zzrk.transact(53, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getMapType() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public float getMaxZoomLevel() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readFloat();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public float getMinZoomLevel() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readFloat();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public Location getMyLocation() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Location) Location.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IProjectionDelegate getProjection() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                    return IProjectionDelegate.zza.zzeb(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IUiSettingsDelegate getUiSettings() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                    return IUiSettingsDelegate.zza.zzeg(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isBuildingsEnabled() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    boolean z = false;
                    this.zzrk.transact(40, obtain, obtain2, 0);
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

            public boolean isIndoorEnabled() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    boolean z = false;
                    this.zzrk.transact(19, obtain, obtain2, 0);
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

            public boolean isMyLocationEnabled() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
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

            public boolean isTrafficEnabled() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
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

            public void moveCamera(IObjectWrapper iObjectWrapper) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(iObjectWrapper != null ? iObjectWrapper.asBinder() : null);
                    this.zzrk.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onCreate(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(54, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onDestroy() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(57, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onEnterAmbient(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(81, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onExitAmbient() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(82, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onLowMemory() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(58, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onPause() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(56, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onResume() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(55, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onSaveInstanceState(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(60, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        bundle.readFromParcel(obtain2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onStart() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(101, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void onStop() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(102, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void resetMinMaxZoomPreference() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(94, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setBuildingsEnabled(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeInt(z ? 1 : 0);
                    this.zzrk.transact(41, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setContentDescription(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeString(str);
                    this.zzrk.transact(61, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean setIndoorEnabled(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeInt(z ? 1 : 0);
                    boolean z2 = false;
                    this.zzrk.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z2 = true;
                    }
                    return z2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setInfoWindowAdapter(zzd zzd) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(zzd != null ? zzd.asBinder() : null);
                    this.zzrk.transact(33, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setLatLngBoundsForCameraTarget(LatLngBounds latLngBounds) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    if (latLngBounds != null) {
                        obtain.writeInt(1);
                        latLngBounds.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(95, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setLocationSource(ILocationSourceDelegate iLocationSourceDelegate) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(iLocationSourceDelegate != null ? iLocationSourceDelegate.asBinder() : null);
                    this.zzrk.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean setMapStyle(MapStyleOptions mapStyleOptions) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    boolean z = true;
                    if (mapStyleOptions != null) {
                        obtain.writeInt(1);
                        mapStyleOptions.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zzrk.transact(91, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setMapType(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeInt(i);
                    this.zzrk.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setMaxZoomPreference(float f) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeFloat(f);
                    this.zzrk.transact(93, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setMinZoomPreference(float f) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeFloat(f);
                    this.zzrk.transact(92, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setMyLocationEnabled(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeInt(z ? 1 : 0);
                    this.zzrk.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setOnCameraChangeListener(zze zze) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(zze != null ? zze.asBinder() : null);
                    this.zzrk.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setOnCameraIdleListener(zzf zzf) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(zzf != null ? zzf.asBinder() : null);
                    this.zzrk.transact(99, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setOnCameraMoveCanceledListener(zzg zzg) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(zzg != null ? zzg.asBinder() : null);
                    this.zzrk.transact(98, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setOnCameraMoveListener(zzh zzh) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(zzh != null ? zzh.asBinder() : null);
                    this.zzrk.transact(97, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setOnCameraMoveStartedListener(zzi zzi) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(zzi != null ? zzi.asBinder() : null);
                    this.zzrk.transact(96, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setOnCircleClickListener(zzj zzj) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(zzj != null ? zzj.asBinder() : null);
                    this.zzrk.transact(89, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setOnGroundOverlayClickListener(zzk zzk) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(zzk != null ? zzk.asBinder() : null);
                    this.zzrk.transact(83, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setOnIndoorStateChangeListener(zzl zzl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(zzl != null ? zzl.asBinder() : null);
                    this.zzrk.transact(45, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setOnInfoWindowClickListener(zzm zzm) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(zzm != null ? zzm.asBinder() : null);
                    this.zzrk.transact(32, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setOnInfoWindowCloseListener(zzn zzn) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(zzn != null ? zzn.asBinder() : null);
                    this.zzrk.transact(86, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setOnInfoWindowLongClickListener(zzo zzo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(zzo != null ? zzo.asBinder() : null);
                    this.zzrk.transact(84, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setOnMapClickListener(zzq zzq) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(zzq != null ? zzq.asBinder() : null);
                    this.zzrk.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setOnMapLoadedCallback(zzr zzr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(zzr != null ? zzr.asBinder() : null);
                    this.zzrk.transact(42, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setOnMapLongClickListener(zzs zzs) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(zzs != null ? zzs.asBinder() : null);
                    this.zzrk.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setOnMarkerClickListener(zzu zzu) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(zzu != null ? zzu.asBinder() : null);
                    this.zzrk.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setOnMarkerDragListener(zzv zzv) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(zzv != null ? zzv.asBinder() : null);
                    this.zzrk.transact(31, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setOnMyLocationButtonClickListener(zzw zzw) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(zzw != null ? zzw.asBinder() : null);
                    this.zzrk.transact(37, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setOnMyLocationChangeListener(zzx zzx) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(zzx != null ? zzx.asBinder() : null);
                    this.zzrk.transact(36, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setOnPoiClickListener(zzy zzy) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(zzy != null ? zzy.asBinder() : null);
                    this.zzrk.transact(80, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setOnPolygonClickListener(zzz zzz) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(zzz != null ? zzz.asBinder() : null);
                    this.zzrk.transact(85, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setOnPolylineClickListener(zzaa zzaa) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(zzaa != null ? zzaa.asBinder() : null);
                    this.zzrk.transact(87, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setPadding(int i, int i2, int i3, int i4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeInt(i4);
                    this.zzrk.transact(39, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setTrafficEnabled(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeInt(z ? 1 : 0);
                    this.zzrk.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void setWatermarkEnabled(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeInt(z ? 1 : 0);
                    this.zzrk.transact(51, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void snapshot(zzag zzag, IObjectWrapper iObjectWrapper) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    IBinder iBinder = null;
                    obtain.writeStrongBinder(zzag != null ? zzag.asBinder() : null);
                    if (iObjectWrapper != null) {
                        iBinder = iObjectWrapper.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.zzrk.transact(38, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void snapshotForTest(zzag zzag) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    obtain.writeStrongBinder(zzag != null ? zzag.asBinder() : null);
                    this.zzrk.transact(71, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public void stopAnimation() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    this.zzrk.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean useViewLifecycleWhenInFragment() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.maps.internal.IGoogleMapDelegate");
                    boolean z = false;
                    this.zzrk.transact(59, obtain, obtain2, 0);
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

        public static IGoogleMapDelegate zzdu(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IGoogleMapDelegate");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IGoogleMapDelegate)) ? new C0098zza(iBinder) : (IGoogleMapDelegate) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v19, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v22, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v25, resolved type: android.os.Bundle} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v28, resolved type: com.google.android.gms.maps.model.MapStyleOptions} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v31, resolved type: com.google.android.gms.maps.model.LatLngBounds} */
        /* JADX WARNING: type inference failed for: r1v0 */
        /* JADX WARNING: type inference failed for: r1v1, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r1v3, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r1v5, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r1v7, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r1v9, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r1v11, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r1v13, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r1v15, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r1v17, types: [android.os.IBinder] */
        /* JADX WARNING: type inference failed for: r1v34 */
        /* JADX WARNING: type inference failed for: r1v35 */
        /* JADX WARNING: type inference failed for: r1v36 */
        /* JADX WARNING: type inference failed for: r1v37 */
        /* JADX WARNING: type inference failed for: r1v38 */
        /* JADX WARNING: type inference failed for: r1v39 */
        /* JADX WARNING: type inference failed for: r1v40 */
        /* JADX WARNING: type inference failed for: r1v41 */
        /* JADX WARNING: type inference failed for: r1v42 */
        /* JADX WARNING: type inference failed for: r1v43 */
        /* JADX WARNING: type inference failed for: r1v44 */
        /* JADX WARNING: type inference failed for: r1v45 */
        /* JADX WARNING: type inference failed for: r1v46 */
        /* JADX WARNING: type inference failed for: r1v47 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r4, android.os.Parcel r5, android.os.Parcel r6, int r7) throws android.os.RemoteException {
            /*
                r3 = this;
                r0 = 0
                r1 = 0
                r2 = 1
                switch(r4) {
                    case 1: goto L_0x0627;
                    case 2: goto L_0x0617;
                    case 3: goto L_0x0607;
                    case 4: goto L_0x05f3;
                    case 5: goto L_0x05df;
                    case 6: goto L_0x05c3;
                    case 7: goto L_0x05a3;
                    case 8: goto L_0x0597;
                    case 9: goto L_0x0571;
                    case 10: goto L_0x054b;
                    case 11: goto L_0x0525;
                    case 12: goto L_0x04ff;
                    case 13: goto L_0x04d9;
                    case 14: goto L_0x04cd;
                    case 15: goto L_0x04bd;
                    case 16: goto L_0x04ad;
                    case 17: goto L_0x049d;
                    case 18: goto L_0x048a;
                    case 19: goto L_0x047a;
                    case 20: goto L_0x0463;
                    case 21: goto L_0x0453;
                    case 22: goto L_0x0440;
                    case 23: goto L_0x0427;
                    case 24: goto L_0x0413;
                    case 25: goto L_0x03fd;
                    case 26: goto L_0x03e7;
                    case 27: goto L_0x03d3;
                    case 28: goto L_0x03bf;
                    case 29: goto L_0x03ab;
                    case 30: goto L_0x0397;
                    case 31: goto L_0x0383;
                    case 32: goto L_0x036f;
                    case 33: goto L_0x035b;
                    default: goto L_0x0006;
                }
            L_0x0006:
                switch(r4) {
                    case 35: goto L_0x0335;
                    case 36: goto L_0x0321;
                    case 37: goto L_0x030d;
                    case 38: goto L_0x02f1;
                    case 39: goto L_0x02d5;
                    case 40: goto L_0x02c5;
                    case 41: goto L_0x02b2;
                    case 42: goto L_0x029e;
                    default: goto L_0x0009;
                }
            L_0x0009:
                switch(r4) {
                    case 44: goto L_0x0288;
                    case 45: goto L_0x0274;
                    default: goto L_0x000c;
                }
            L_0x000c:
                switch(r4) {
                    case 53: goto L_0x0260;
                    case 54: goto L_0x0245;
                    case 55: goto L_0x0239;
                    case 56: goto L_0x022d;
                    case 57: goto L_0x0221;
                    case 58: goto L_0x0215;
                    case 59: goto L_0x0205;
                    case 60: goto L_0x01de;
                    case 61: goto L_0x01ce;
                    default: goto L_0x000f;
                }
            L_0x000f:
                switch(r4) {
                    case 80: goto L_0x01ba;
                    case 81: goto L_0x019f;
                    case 82: goto L_0x0193;
                    case 83: goto L_0x017f;
                    case 84: goto L_0x016b;
                    case 85: goto L_0x0157;
                    case 86: goto L_0x0143;
                    case 87: goto L_0x012f;
                    default: goto L_0x0012;
                }
            L_0x0012:
                switch(r4) {
                    case 91: goto L_0x0110;
                    case 92: goto L_0x0100;
                    case 93: goto L_0x00f0;
                    case 94: goto L_0x00e4;
                    case 95: goto L_0x00c9;
                    case 96: goto L_0x00b5;
                    case 97: goto L_0x00a1;
                    case 98: goto L_0x008d;
                    case 99: goto L_0x0079;
                    default: goto L_0x0015;
                }
            L_0x0015:
                switch(r4) {
                    case 101: goto L_0x006d;
                    case 102: goto L_0x0061;
                    default: goto L_0x0018;
                }
            L_0x0018:
                switch(r4) {
                    case 51: goto L_0x004e;
                    case 71: goto L_0x003a;
                    case 89: goto L_0x0026;
                    case 1598968902: goto L_0x0020;
                    default: goto L_0x001b;
                }
            L_0x001b:
                boolean r4 = super.onTransact(r4, r5, r6, r7)
                return r4
            L_0x0020:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r6.writeString(r4)
                return r2
            L_0x0026:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.maps.internal.zzj r4 = com.google.android.gms.maps.internal.zzj.zza.zzdE(r4)
                r3.setOnCircleClickListener(r4)
                r6.writeNoException()
                return r2
            L_0x003a:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.maps.internal.zzag r4 = com.google.android.gms.maps.internal.zzag.zza.zzec(r4)
                r3.snapshotForTest(r4)
                r6.writeNoException()
                return r2
            L_0x004e:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x005a
                r0 = r2
            L_0x005a:
                r3.setWatermarkEnabled(r0)
                r6.writeNoException()
                return r2
            L_0x0061:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                r3.onStop()
                r6.writeNoException()
                return r2
            L_0x006d:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                r3.onStart()
                r6.writeNoException()
                return r2
            L_0x0079:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.maps.internal.zzf r4 = com.google.android.gms.maps.internal.zzf.zza.zzdA(r4)
                r3.setOnCameraIdleListener(r4)
                r6.writeNoException()
                return r2
            L_0x008d:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.maps.internal.zzg r4 = com.google.android.gms.maps.internal.zzg.zza.zzdB(r4)
                r3.setOnCameraMoveCanceledListener(r4)
                r6.writeNoException()
                return r2
            L_0x00a1:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.maps.internal.zzh r4 = com.google.android.gms.maps.internal.zzh.zza.zzdC(r4)
                r3.setOnCameraMoveListener(r4)
                r6.writeNoException()
                return r2
            L_0x00b5:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.maps.internal.zzi r4 = com.google.android.gms.maps.internal.zzi.zza.zzdD(r4)
                r3.setOnCameraMoveStartedListener(r4)
                r6.writeNoException()
                return r2
            L_0x00c9:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x00dd
                android.os.Parcelable$Creator<com.google.android.gms.maps.model.LatLngBounds> r4 = com.google.android.gms.maps.model.LatLngBounds.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.google.android.gms.maps.model.LatLngBounds r1 = (com.google.android.gms.maps.model.LatLngBounds) r1
            L_0x00dd:
                r3.setLatLngBoundsForCameraTarget(r1)
                r6.writeNoException()
                return r2
            L_0x00e4:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                r3.resetMinMaxZoomPreference()
                r6.writeNoException()
                return r2
            L_0x00f0:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                float r4 = r5.readFloat()
                r3.setMaxZoomPreference(r4)
                r6.writeNoException()
                return r2
            L_0x0100:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                float r4 = r5.readFloat()
                r3.setMinZoomPreference(r4)
                r6.writeNoException()
                return r2
            L_0x0110:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0124
                android.os.Parcelable$Creator<com.google.android.gms.maps.model.MapStyleOptions> r4 = com.google.android.gms.maps.model.MapStyleOptions.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                com.google.android.gms.maps.model.MapStyleOptions r1 = (com.google.android.gms.maps.model.MapStyleOptions) r1
            L_0x0124:
                boolean r4 = r3.setMapStyle(r1)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x012f:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.maps.internal.zzaa r4 = com.google.android.gms.maps.internal.zzaa.zza.zzdV(r4)
                r3.setOnPolylineClickListener(r4)
                r6.writeNoException()
                return r2
            L_0x0143:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.maps.internal.zzn r4 = com.google.android.gms.maps.internal.zzn.zza.zzdI(r4)
                r3.setOnInfoWindowCloseListener(r4)
                r6.writeNoException()
                return r2
            L_0x0157:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.maps.internal.zzz r4 = com.google.android.gms.maps.internal.zzz.zza.zzdU(r4)
                r3.setOnPolygonClickListener(r4)
                r6.writeNoException()
                return r2
            L_0x016b:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.maps.internal.zzo r4 = com.google.android.gms.maps.internal.zzo.zza.zzdJ(r4)
                r3.setOnInfoWindowLongClickListener(r4)
                r6.writeNoException()
                return r2
            L_0x017f:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.maps.internal.zzk r4 = com.google.android.gms.maps.internal.zzk.zza.zzdF(r4)
                r3.setOnGroundOverlayClickListener(r4)
                r6.writeNoException()
                return r2
            L_0x0193:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                r3.onExitAmbient()
                r6.writeNoException()
                return r2
            L_0x019f:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x01b3
                android.os.Parcelable$Creator r4 = android.os.Bundle.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                android.os.Bundle r1 = (android.os.Bundle) r1
            L_0x01b3:
                r3.onEnterAmbient(r1)
                r6.writeNoException()
                return r2
            L_0x01ba:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.maps.internal.zzy r4 = com.google.android.gms.maps.internal.zzy.zza.zzdT(r4)
                r3.setOnPoiClickListener(r4)
                r6.writeNoException()
                return r2
            L_0x01ce:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                java.lang.String r4 = r5.readString()
                r3.setContentDescription(r4)
                r6.writeNoException()
                return r2
            L_0x01de:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x01f2
                android.os.Parcelable$Creator r4 = android.os.Bundle.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                android.os.Bundle r1 = (android.os.Bundle) r1
            L_0x01f2:
                r3.onSaveInstanceState(r1)
                r6.writeNoException()
                if (r1 == 0) goto L_0x0201
                r6.writeInt(r2)
                r1.writeToParcel(r6, r2)
                return r2
            L_0x0201:
                r6.writeInt(r0)
                return r2
            L_0x0205:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                boolean r4 = r3.useViewLifecycleWhenInFragment()
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x0215:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                r3.onLowMemory()
                r6.writeNoException()
                return r2
            L_0x0221:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                r3.onDestroy()
                r6.writeNoException()
                return r2
            L_0x022d:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                r3.onPause()
                r6.writeNoException()
                return r2
            L_0x0239:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                r3.onResume()
                r6.writeNoException()
                return r2
            L_0x0245:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0259
                android.os.Parcelable$Creator r4 = android.os.Bundle.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                r1 = r4
                android.os.Bundle r1 = (android.os.Bundle) r1
            L_0x0259:
                r3.onCreate(r1)
                r6.writeNoException()
                return r2
            L_0x0260:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.maps.internal.zzt r4 = com.google.android.gms.maps.internal.zzt.zza.zzdO(r4)
                r3.getMapAsync(r4)
                r6.writeNoException()
                return r2
            L_0x0274:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.maps.internal.zzl r4 = com.google.android.gms.maps.internal.zzl.zza.zzdG(r4)
                r3.setOnIndoorStateChangeListener(r4)
                r6.writeNoException()
                return r2
            L_0x0288:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                com.google.android.gms.maps.model.internal.zzd r4 = r3.getFocusedBuilding()
                r6.writeNoException()
                if (r4 == 0) goto L_0x029a
                android.os.IBinder r1 = r4.asBinder()
            L_0x029a:
                r6.writeStrongBinder(r1)
                return r2
            L_0x029e:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.maps.internal.zzr r4 = com.google.android.gms.maps.internal.zzr.zza.zzdM(r4)
                r3.setOnMapLoadedCallback(r4)
                r6.writeNoException()
                return r2
            L_0x02b2:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x02be
                r0 = r2
            L_0x02be:
                r3.setBuildingsEnabled(r0)
                r6.writeNoException()
                return r2
            L_0x02c5:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                boolean r4 = r3.isBuildingsEnabled()
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x02d5:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                int r7 = r5.readInt()
                int r0 = r5.readInt()
                int r5 = r5.readInt()
                r3.setPadding(r4, r7, r0, r5)
                r6.writeNoException()
                return r2
            L_0x02f1:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.maps.internal.zzag r4 = com.google.android.gms.maps.internal.zzag.zza.zzec(r4)
                android.os.IBinder r5 = r5.readStrongBinder()
                com.google.android.gms.dynamic.IObjectWrapper r5 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzcd(r5)
                r3.snapshot(r4, r5)
                r6.writeNoException()
                return r2
            L_0x030d:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.maps.internal.zzw r4 = com.google.android.gms.maps.internal.zzw.zza.zzdR(r4)
                r3.setOnMyLocationButtonClickListener(r4)
                r6.writeNoException()
                return r2
            L_0x0321:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.maps.internal.zzx r4 = com.google.android.gms.maps.internal.zzx.zza.zzdS(r4)
                r3.setOnMyLocationChangeListener(r4)
                r6.writeNoException()
                return r2
            L_0x0335:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0349
                android.os.Parcelable$Creator<com.google.android.gms.maps.model.CircleOptions> r4 = com.google.android.gms.maps.model.CircleOptions.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                com.google.android.gms.maps.model.CircleOptions r4 = (com.google.android.gms.maps.model.CircleOptions) r4
                goto L_0x034a
            L_0x0349:
                r4 = r1
            L_0x034a:
                com.google.android.gms.maps.model.internal.zzb r4 = r3.addCircle(r4)
                r6.writeNoException()
                if (r4 == 0) goto L_0x0357
                android.os.IBinder r1 = r4.asBinder()
            L_0x0357:
                r6.writeStrongBinder(r1)
                return r2
            L_0x035b:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.maps.internal.zzd r4 = com.google.android.gms.maps.internal.zzd.zza.zzdv(r4)
                r3.setInfoWindowAdapter(r4)
                r6.writeNoException()
                return r2
            L_0x036f:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.maps.internal.zzm r4 = com.google.android.gms.maps.internal.zzm.zza.zzdH(r4)
                r3.setOnInfoWindowClickListener(r4)
                r6.writeNoException()
                return r2
            L_0x0383:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.maps.internal.zzv r4 = com.google.android.gms.maps.internal.zzv.zza.zzdQ(r4)
                r3.setOnMarkerDragListener(r4)
                r6.writeNoException()
                return r2
            L_0x0397:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.maps.internal.zzu r4 = com.google.android.gms.maps.internal.zzu.zza.zzdP(r4)
                r3.setOnMarkerClickListener(r4)
                r6.writeNoException()
                return r2
            L_0x03ab:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.maps.internal.zzs r4 = com.google.android.gms.maps.internal.zzs.zza.zzdN(r4)
                r3.setOnMapLongClickListener(r4)
                r6.writeNoException()
                return r2
            L_0x03bf:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.maps.internal.zzq r4 = com.google.android.gms.maps.internal.zzq.zza.zzdL(r4)
                r3.setOnMapClickListener(r4)
                r6.writeNoException()
                return r2
            L_0x03d3:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.maps.internal.zze r4 = com.google.android.gms.maps.internal.zze.zza.zzdz(r4)
                r3.setOnCameraChangeListener(r4)
                r6.writeNoException()
                return r2
            L_0x03e7:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                com.google.android.gms.maps.internal.IProjectionDelegate r4 = r3.getProjection()
                r6.writeNoException()
                if (r4 == 0) goto L_0x03f9
                android.os.IBinder r1 = r4.asBinder()
            L_0x03f9:
                r6.writeStrongBinder(r1)
                return r2
            L_0x03fd:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                com.google.android.gms.maps.internal.IUiSettingsDelegate r4 = r3.getUiSettings()
                r6.writeNoException()
                if (r4 == 0) goto L_0x040f
                android.os.IBinder r1 = r4.asBinder()
            L_0x040f:
                r6.writeStrongBinder(r1)
                return r2
            L_0x0413:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.maps.internal.ILocationSourceDelegate r4 = com.google.android.gms.maps.internal.ILocationSourceDelegate.zza.zzdw(r4)
                r3.setLocationSource(r4)
                r6.writeNoException()
                return r2
            L_0x0427:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                android.location.Location r4 = r3.getMyLocation()
                r6.writeNoException()
                if (r4 == 0) goto L_0x043c
                r6.writeInt(r2)
                r4.writeToParcel(r6, r2)
                return r2
            L_0x043c:
                r6.writeInt(r0)
                return r2
            L_0x0440:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x044c
                r0 = r2
            L_0x044c:
                r3.setMyLocationEnabled(r0)
                r6.writeNoException()
                return r2
            L_0x0453:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                boolean r4 = r3.isMyLocationEnabled()
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x0463:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x046f
                r0 = r2
            L_0x046f:
                boolean r4 = r3.setIndoorEnabled(r0)
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x047a:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                boolean r4 = r3.isIndoorEnabled()
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x048a:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0496
                r0 = r2
            L_0x0496:
                r3.setTrafficEnabled(r0)
                r6.writeNoException()
                return r2
            L_0x049d:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                boolean r4 = r3.isTrafficEnabled()
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x04ad:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                r3.setMapType(r4)
                r6.writeNoException()
                return r2
            L_0x04bd:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                int r4 = r3.getMapType()
                r6.writeNoException()
                r6.writeInt(r4)
                return r2
            L_0x04cd:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                r3.clear()
                r6.writeNoException()
                return r2
            L_0x04d9:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x04ed
                android.os.Parcelable$Creator<com.google.android.gms.maps.model.TileOverlayOptions> r4 = com.google.android.gms.maps.model.TileOverlayOptions.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                com.google.android.gms.maps.model.TileOverlayOptions r4 = (com.google.android.gms.maps.model.TileOverlayOptions) r4
                goto L_0x04ee
            L_0x04ed:
                r4 = r1
            L_0x04ee:
                com.google.android.gms.maps.model.internal.zzh r4 = r3.addTileOverlay(r4)
                r6.writeNoException()
                if (r4 == 0) goto L_0x04fb
                android.os.IBinder r1 = r4.asBinder()
            L_0x04fb:
                r6.writeStrongBinder(r1)
                return r2
            L_0x04ff:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0513
                android.os.Parcelable$Creator<com.google.android.gms.maps.model.GroundOverlayOptions> r4 = com.google.android.gms.maps.model.GroundOverlayOptions.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                com.google.android.gms.maps.model.GroundOverlayOptions r4 = (com.google.android.gms.maps.model.GroundOverlayOptions) r4
                goto L_0x0514
            L_0x0513:
                r4 = r1
            L_0x0514:
                com.google.android.gms.maps.model.internal.zzc r4 = r3.addGroundOverlay(r4)
                r6.writeNoException()
                if (r4 == 0) goto L_0x0521
                android.os.IBinder r1 = r4.asBinder()
            L_0x0521:
                r6.writeStrongBinder(r1)
                return r2
            L_0x0525:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0539
                android.os.Parcelable$Creator<com.google.android.gms.maps.model.MarkerOptions> r4 = com.google.android.gms.maps.model.MarkerOptions.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                com.google.android.gms.maps.model.MarkerOptions r4 = (com.google.android.gms.maps.model.MarkerOptions) r4
                goto L_0x053a
            L_0x0539:
                r4 = r1
            L_0x053a:
                com.google.android.gms.maps.model.internal.zzf r4 = r3.addMarker(r4)
                r6.writeNoException()
                if (r4 == 0) goto L_0x0547
                android.os.IBinder r1 = r4.asBinder()
            L_0x0547:
                r6.writeStrongBinder(r1)
                return r2
            L_0x054b:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x055f
                android.os.Parcelable$Creator<com.google.android.gms.maps.model.PolygonOptions> r4 = com.google.android.gms.maps.model.PolygonOptions.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                com.google.android.gms.maps.model.PolygonOptions r4 = (com.google.android.gms.maps.model.PolygonOptions) r4
                goto L_0x0560
            L_0x055f:
                r4 = r1
            L_0x0560:
                com.google.android.gms.maps.model.internal.zzg r4 = r3.addPolygon(r4)
                r6.writeNoException()
                if (r4 == 0) goto L_0x056d
                android.os.IBinder r1 = r4.asBinder()
            L_0x056d:
                r6.writeStrongBinder(r1)
                return r2
            L_0x0571:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                int r4 = r5.readInt()
                if (r4 == 0) goto L_0x0585
                android.os.Parcelable$Creator<com.google.android.gms.maps.model.PolylineOptions> r4 = com.google.android.gms.maps.model.PolylineOptions.CREATOR
                java.lang.Object r4 = r4.createFromParcel(r5)
                com.google.android.gms.maps.model.PolylineOptions r4 = (com.google.android.gms.maps.model.PolylineOptions) r4
                goto L_0x0586
            L_0x0585:
                r4 = r1
            L_0x0586:
                com.google.android.gms.maps.model.internal.IPolylineDelegate r4 = r3.addPolyline(r4)
                r6.writeNoException()
                if (r4 == 0) goto L_0x0593
                android.os.IBinder r1 = r4.asBinder()
            L_0x0593:
                r6.writeStrongBinder(r1)
                return r2
            L_0x0597:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                r3.stopAnimation()
                r6.writeNoException()
                return r2
            L_0x05a3:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.dynamic.IObjectWrapper r4 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzcd(r4)
                int r7 = r5.readInt()
                android.os.IBinder r5 = r5.readStrongBinder()
                com.google.android.gms.maps.internal.zzb r5 = com.google.android.gms.maps.internal.zzb.zza.zzds(r5)
                r3.animateCameraWithDurationAndCallback(r4, r7, r5)
                r6.writeNoException()
                return r2
            L_0x05c3:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.dynamic.IObjectWrapper r4 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzcd(r4)
                android.os.IBinder r5 = r5.readStrongBinder()
                com.google.android.gms.maps.internal.zzb r5 = com.google.android.gms.maps.internal.zzb.zza.zzds(r5)
                r3.animateCameraWithCallback(r4, r5)
                r6.writeNoException()
                return r2
            L_0x05df:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.dynamic.IObjectWrapper r4 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzcd(r4)
                r3.animateCamera(r4)
                r6.writeNoException()
                return r2
            L_0x05f3:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                android.os.IBinder r4 = r5.readStrongBinder()
                com.google.android.gms.dynamic.IObjectWrapper r4 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzcd(r4)
                r3.moveCamera(r4)
                r6.writeNoException()
                return r2
            L_0x0607:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                float r4 = r3.getMinZoomLevel()
                r6.writeNoException()
                r6.writeFloat(r4)
                return r2
            L_0x0617:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                float r4 = r3.getMaxZoomLevel()
                r6.writeNoException()
                r6.writeFloat(r4)
                return r2
            L_0x0627:
                java.lang.String r4 = "com.google.android.gms.maps.internal.IGoogleMapDelegate"
                r5.enforceInterface(r4)
                com.google.android.gms.maps.model.CameraPosition r4 = r3.getCameraPosition()
                r6.writeNoException()
                if (r4 == 0) goto L_0x063c
                r6.writeInt(r2)
                r4.writeToParcel(r6, r2)
                return r2
            L_0x063c:
                r6.writeInt(r0)
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.internal.IGoogleMapDelegate.zza.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    zzb addCircle(CircleOptions circleOptions) throws RemoteException;

    zzc addGroundOverlay(GroundOverlayOptions groundOverlayOptions) throws RemoteException;

    zzf addMarker(MarkerOptions markerOptions) throws RemoteException;

    zzg addPolygon(PolygonOptions polygonOptions) throws RemoteException;

    IPolylineDelegate addPolyline(PolylineOptions polylineOptions) throws RemoteException;

    zzh addTileOverlay(TileOverlayOptions tileOverlayOptions) throws RemoteException;

    void animateCamera(IObjectWrapper iObjectWrapper) throws RemoteException;

    void animateCameraWithCallback(IObjectWrapper iObjectWrapper, zzb zzb) throws RemoteException;

    void animateCameraWithDurationAndCallback(IObjectWrapper iObjectWrapper, int i, zzb zzb) throws RemoteException;

    void clear() throws RemoteException;

    CameraPosition getCameraPosition() throws RemoteException;

    zzd getFocusedBuilding() throws RemoteException;

    void getMapAsync(zzt zzt) throws RemoteException;

    int getMapType() throws RemoteException;

    float getMaxZoomLevel() throws RemoteException;

    float getMinZoomLevel() throws RemoteException;

    Location getMyLocation() throws RemoteException;

    IProjectionDelegate getProjection() throws RemoteException;

    IUiSettingsDelegate getUiSettings() throws RemoteException;

    boolean isBuildingsEnabled() throws RemoteException;

    boolean isIndoorEnabled() throws RemoteException;

    boolean isMyLocationEnabled() throws RemoteException;

    boolean isTrafficEnabled() throws RemoteException;

    void moveCamera(IObjectWrapper iObjectWrapper) throws RemoteException;

    void onCreate(Bundle bundle) throws RemoteException;

    void onDestroy() throws RemoteException;

    void onEnterAmbient(Bundle bundle) throws RemoteException;

    void onExitAmbient() throws RemoteException;

    void onLowMemory() throws RemoteException;

    void onPause() throws RemoteException;

    void onResume() throws RemoteException;

    void onSaveInstanceState(Bundle bundle) throws RemoteException;

    void onStart() throws RemoteException;

    void onStop() throws RemoteException;

    void resetMinMaxZoomPreference() throws RemoteException;

    void setBuildingsEnabled(boolean z) throws RemoteException;

    void setContentDescription(String str) throws RemoteException;

    boolean setIndoorEnabled(boolean z) throws RemoteException;

    void setInfoWindowAdapter(zzd zzd) throws RemoteException;

    void setLatLngBoundsForCameraTarget(LatLngBounds latLngBounds) throws RemoteException;

    void setLocationSource(ILocationSourceDelegate iLocationSourceDelegate) throws RemoteException;

    boolean setMapStyle(MapStyleOptions mapStyleOptions) throws RemoteException;

    void setMapType(int i) throws RemoteException;

    void setMaxZoomPreference(float f) throws RemoteException;

    void setMinZoomPreference(float f) throws RemoteException;

    void setMyLocationEnabled(boolean z) throws RemoteException;

    void setOnCameraChangeListener(zze zze) throws RemoteException;

    void setOnCameraIdleListener(zzf zzf) throws RemoteException;

    void setOnCameraMoveCanceledListener(zzg zzg) throws RemoteException;

    void setOnCameraMoveListener(zzh zzh) throws RemoteException;

    void setOnCameraMoveStartedListener(zzi zzi) throws RemoteException;

    void setOnCircleClickListener(zzj zzj) throws RemoteException;

    void setOnGroundOverlayClickListener(zzk zzk) throws RemoteException;

    void setOnIndoorStateChangeListener(zzl zzl) throws RemoteException;

    void setOnInfoWindowClickListener(zzm zzm) throws RemoteException;

    void setOnInfoWindowCloseListener(zzn zzn) throws RemoteException;

    void setOnInfoWindowLongClickListener(zzo zzo) throws RemoteException;

    void setOnMapClickListener(zzq zzq) throws RemoteException;

    void setOnMapLoadedCallback(zzr zzr) throws RemoteException;

    void setOnMapLongClickListener(zzs zzs) throws RemoteException;

    void setOnMarkerClickListener(zzu zzu) throws RemoteException;

    void setOnMarkerDragListener(zzv zzv) throws RemoteException;

    void setOnMyLocationButtonClickListener(zzw zzw) throws RemoteException;

    void setOnMyLocationChangeListener(zzx zzx) throws RemoteException;

    void setOnPoiClickListener(zzy zzy) throws RemoteException;

    void setOnPolygonClickListener(zzz zzz) throws RemoteException;

    void setOnPolylineClickListener(zzaa zzaa) throws RemoteException;

    void setPadding(int i, int i2, int i3, int i4) throws RemoteException;

    void setTrafficEnabled(boolean z) throws RemoteException;

    void setWatermarkEnabled(boolean z) throws RemoteException;

    void snapshot(zzag zzag, IObjectWrapper iObjectWrapper) throws RemoteException;

    void snapshotForTest(zzag zzag) throws RemoteException;

    void stopAnimation() throws RemoteException;

    boolean useViewLifecycleWhenInFragment() throws RemoteException;
}
