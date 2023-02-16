package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzae implements Parcelable.Creator<zzad> {
    static void zza(zzad zzad, Parcel parcel, int i) {
        int zzaZ = zzc.zzaZ(parcel);
        zzc.zzc(parcel, 1, zzad.zzaiI);
        zzc.zza(parcel, 2, (Parcelable) zzad.getAccount(), i, false);
        zzc.zzc(parcel, 3, zzad.getSessionId());
        zzc.zza(parcel, 4, (Parcelable) zzad.zzyf(), i, false);
        zzc.zzJ(parcel, zzaZ);
    }

    /* renamed from: zzaT */
    public zzad createFromParcel(Parcel parcel) {
        int zzaY = zzb.zzaY(parcel);
        Account account = null;
        int i = 0;
        GoogleSignInAccount googleSignInAccount = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzaY) {
            int zzaX = zzb.zzaX(parcel);
            switch (zzb.zzdc(zzaX)) {
                case 1:
                    i = zzb.zzg(parcel, zzaX);
                    break;
                case 2:
                    account = (Account) zzb.zza(parcel, zzaX, Account.CREATOR);
                    break;
                case 3:
                    i2 = zzb.zzg(parcel, zzaX);
                    break;
                case 4:
                    googleSignInAccount = (GoogleSignInAccount) zzb.zza(parcel, zzaX, GoogleSignInAccount.CREATOR);
                    break;
                default:
                    zzb.zzb(parcel, zzaX);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaY) {
            return new zzad(i, account, i2, googleSignInAccount);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzaY);
        throw new zzb.zza(sb.toString(), parcel);
    }

    /* renamed from: zzcY */
    public zzad[] newArray(int i) {
        return new zzad[i];
    }
}
