package com.facebook.ads.internal.q.a;

import android.app.Activity;
import android.support.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.Map;

public class a {
    @Nullable
    public static Activity a() {
        try {
            return b();
        } catch (Exception unused) {
            return null;
        }
    }

    private static Activity b() {
        Class<?> cls = Class.forName("android.app.ActivityThread");
        Object invoke = cls.getMethod("currentActivityThread", new Class[0]).invoke((Object) null, new Object[0]);
        Field declaredField = cls.getDeclaredField("mActivities");
        declaredField.setAccessible(true);
        Map map = (Map) declaredField.get(invoke);
        if (map == null) {
            return null;
        }
        for (Object next : map.values()) {
            Class<?> cls2 = next.getClass();
            Field declaredField2 = cls2.getDeclaredField("paused");
            declaredField2.setAccessible(true);
            if (!declaredField2.getBoolean(next)) {
                Field declaredField3 = cls2.getDeclaredField("activity");
                declaredField3.setAccessible(true);
                return (Activity) declaredField3.get(next);
            }
        }
        return null;
    }
}
