package com.facebook.ads.internal.q.a;

import android.content.Context;
import android.media.AudioManager;
import com.facebook.ads.AudienceNetworkActivity;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.Map;

public class w {
    public static float a(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        if (audioManager == null) {
            return 0.0f;
        }
        int streamVolume = audioManager.getStreamVolume(3);
        int streamMaxVolume = audioManager.getStreamMaxVolume(3);
        if (streamMaxVolume > 0) {
            return (((float) streamVolume) * 1.0f) / ((float) streamMaxVolume);
        }
        return 0.0f;
    }

    public static void a(Map<String, String> map, boolean z, boolean z2) {
        map.put(AudienceNetworkActivity.AUTOPLAY, z ? "1" : "0");
        map.put("inline", z2 ? "1" : "0");
    }
}
