package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.util.Iterator;

public class zzato extends zza implements Iterable<String> {
    public static final Parcelable.Creator<zzato> CREATOR = new zzatp();
    /* access modifiers changed from: private */
    public final Bundle zzbrD;

    zzato(Bundle bundle) {
        this.zzbrD = bundle;
    }

    /* access modifiers changed from: package-private */
    public Object get(String str) {
        return this.zzbrD.get(str);
    }

    public Iterator<String> iterator() {
        return new Iterator<String>() {
            Iterator<String> zzbrE = zzato.this.zzbrD.keySet().iterator();

            public boolean hasNext() {
                return this.zzbrE.hasNext();
            }

            public String next() {
                return this.zzbrE.next();
            }

            public void remove() {
                throw new UnsupportedOperationException("Remove not supported");
            }
        };
    }

    public int size() {
        return this.zzbrD.size();
    }

    public String toString() {
        return this.zzbrD.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzatp.zza(this, parcel, i);
    }

    public Bundle zzLW() {
        return new Bundle(this.zzbrD);
    }
}
