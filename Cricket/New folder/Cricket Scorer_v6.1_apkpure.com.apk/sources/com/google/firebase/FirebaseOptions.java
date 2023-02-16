package com.google.firebase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.internal.zzam;
import com.google.android.gms.common.util.zzw;

public final class FirebaseOptions {
    /* access modifiers changed from: private */
    public final String zzamX;
    /* access modifiers changed from: private */
    public final String zzbWN;
    /* access modifiers changed from: private */
    public final String zzbWO;
    /* access modifiers changed from: private */
    public final String zzbWP;
    /* access modifiers changed from: private */
    public final String zzbWQ;
    /* access modifiers changed from: private */
    public final String zzbWR;

    public static final class Builder {
        private String zzamX;
        private String zzbWN;
        private String zzbWO;
        private String zzbWP;
        private String zzbWQ;
        private String zzbWR;

        public Builder() {
        }

        public Builder(FirebaseOptions firebaseOptions) {
            this.zzamX = firebaseOptions.zzamX;
            this.zzbWN = firebaseOptions.zzbWN;
            this.zzbWO = firebaseOptions.zzbWO;
            this.zzbWP = firebaseOptions.zzbWP;
            this.zzbWQ = firebaseOptions.zzbWQ;
            this.zzbWR = firebaseOptions.zzbWR;
        }

        public FirebaseOptions build() {
            return new FirebaseOptions(this.zzamX, this.zzbWN, this.zzbWO, this.zzbWP, this.zzbWQ, this.zzbWR);
        }

        public Builder setApiKey(@NonNull String str) {
            this.zzbWN = zzac.zzh(str, "ApiKey must be set.");
            return this;
        }

        public Builder setApplicationId(@NonNull String str) {
            this.zzamX = zzac.zzh(str, "ApplicationId must be set.");
            return this;
        }

        public Builder setDatabaseUrl(@Nullable String str) {
            this.zzbWO = str;
            return this;
        }

        public Builder setGcmSenderId(@Nullable String str) {
            this.zzbWQ = str;
            return this;
        }

        public Builder setStorageBucket(@Nullable String str) {
            this.zzbWR = str;
            return this;
        }
    }

    private FirebaseOptions(@NonNull String str, @NonNull String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6) {
        zzac.zza(!zzw.zzdz(str), (Object) "ApplicationId must be set.");
        this.zzamX = str;
        this.zzbWN = str2;
        this.zzbWO = str3;
        this.zzbWP = str4;
        this.zzbWQ = str5;
        this.zzbWR = str6;
    }

    public static FirebaseOptions fromResource(Context context) {
        zzam zzam = new zzam(context);
        String string = zzam.getString("google_app_id");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return new FirebaseOptions(string, zzam.getString("google_api_key"), zzam.getString("firebase_database_url"), zzam.getString("ga_trackingId"), zzam.getString("gcm_defaultSenderId"), zzam.getString("google_storage_bucket"));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FirebaseOptions)) {
            return false;
        }
        FirebaseOptions firebaseOptions = (FirebaseOptions) obj;
        return zzaa.equal(this.zzamX, firebaseOptions.zzamX) && zzaa.equal(this.zzbWN, firebaseOptions.zzbWN) && zzaa.equal(this.zzbWO, firebaseOptions.zzbWO) && zzaa.equal(this.zzbWP, firebaseOptions.zzbWP) && zzaa.equal(this.zzbWQ, firebaseOptions.zzbWQ) && zzaa.equal(this.zzbWR, firebaseOptions.zzbWR);
    }

    public String getApiKey() {
        return this.zzbWN;
    }

    public String getApplicationId() {
        return this.zzamX;
    }

    public String getDatabaseUrl() {
        return this.zzbWO;
    }

    public String getGcmSenderId() {
        return this.zzbWQ;
    }

    public String getStorageBucket() {
        return this.zzbWR;
    }

    public int hashCode() {
        return zzaa.hashCode(this.zzamX, this.zzbWN, this.zzbWO, this.zzbWP, this.zzbWQ, this.zzbWR);
    }

    public String toString() {
        return zzaa.zzv(this).zzg("applicationId", this.zzamX).zzg("apiKey", this.zzbWN).zzg("databaseUrl", this.zzbWO).zzg("gcmSenderId", this.zzbWQ).zzg("storageBucket", this.zzbWR).toString();
    }
}
