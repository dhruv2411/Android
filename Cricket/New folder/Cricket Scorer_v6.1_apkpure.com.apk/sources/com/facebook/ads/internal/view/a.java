package com.facebook.ads.internal.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.j.d;

public interface a {

    /* renamed from: com.facebook.ads.internal.view.a$a  reason: collision with other inner class name */
    public interface C0008a {
        void a(View view);

        void a(String str);

        void a(String str, d dVar);
    }

    void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity);

    void a(Bundle bundle);

    void i();

    void j();

    void onDestroy();

    void setListener(C0008a aVar);
}
