package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.internal.zzaax;

public class GoogleApiActivity extends Activity implements DialogInterface.OnCancelListener {
    protected int zzazb = 0;

    public static PendingIntent zza(Context context, PendingIntent pendingIntent, int i) {
        return zza(context, pendingIntent, i, true);
    }

    public static PendingIntent zza(Context context, PendingIntent pendingIntent, int i, boolean z) {
        return PendingIntent.getActivity(context, 0, zzb(context, pendingIntent, i, z), 134217728);
    }

    private void zza(int i, zzaax zzaax) {
        switch (i) {
            case -1:
                zzaax.zzvx();
                return;
            case 0:
                zzaax.zza(new ConnectionResult(13, (PendingIntent) null), getIntent().getIntExtra("failing_client_id", -1));
                return;
            default:
                return;
        }
    }

    public static Intent zzb(Context context, PendingIntent pendingIntent, int i, boolean z) {
        Intent intent = new Intent(context, GoogleApiActivity.class);
        intent.putExtra("pending_intent", pendingIntent);
        intent.putExtra("failing_client_id", i);
        intent.putExtra("notify_manager", z);
        return intent;
    }

    private void zzvl() {
        String str;
        String str2;
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            str = "GoogleApiActivity";
            str2 = "Activity started without extras";
        } else {
            PendingIntent pendingIntent = (PendingIntent) extras.get("pending_intent");
            Integer num = (Integer) extras.get("error_code");
            if (pendingIntent == null && num == null) {
                str = "GoogleApiActivity";
                str2 = "Activity started without resolution";
            } else if (pendingIntent != null) {
                try {
                    startIntentSenderForResult(pendingIntent.getIntentSender(), 1, (Intent) null, 0, 0, 0);
                    this.zzazb = 1;
                    return;
                } catch (IntentSender.SendIntentException e) {
                    Log.e("GoogleApiActivity", "Failed to launch pendingIntent", e);
                    finish();
                    return;
                }
            } else {
                GoogleApiAvailability.getInstance().showErrorDialogFragment(this, num.intValue(), 2, this);
                this.zzazb = 1;
                return;
            }
        }
        Log.e(str, str2);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            boolean booleanExtra = getIntent().getBooleanExtra("notify_manager", true);
            this.zzazb = 0;
            setResultCode(i2);
            if (booleanExtra) {
                zza(i2, zzaax.zzaP(this));
            }
        } else if (i == 2) {
            this.zzazb = 0;
            setResultCode(i2);
        }
        finish();
    }

    public void onCancel(DialogInterface dialogInterface) {
        this.zzazb = 0;
        setResult(0);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.zzazb = bundle.getInt("resolution");
        }
        if (this.zzazb != 1) {
            zzvl();
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("resolution", this.zzazb);
        super.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void setResultCode(int i) {
        setResult(i);
    }
}
