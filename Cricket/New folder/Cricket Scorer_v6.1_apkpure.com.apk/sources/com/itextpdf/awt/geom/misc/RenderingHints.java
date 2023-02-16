package com.itextpdf.awt.geom.misc;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class RenderingHints implements Map<Object, Object>, Cloneable {
    public static final Key KEY_ALPHA_INTERPOLATION = new KeyImpl(1);
    public static final Key KEY_ANTIALIASING = new KeyImpl(2);
    public static final Key KEY_COLOR_RENDERING = new KeyImpl(3);
    public static final Key KEY_DITHERING = new KeyImpl(4);
    public static final Key KEY_FRACTIONALMETRICS = new KeyImpl(5);
    public static final Key KEY_INTERPOLATION = new KeyImpl(6);
    public static final Key KEY_RENDERING = new KeyImpl(7);
    public static final Key KEY_STROKE_CONTROL = new KeyImpl(8);
    public static final Key KEY_TEXT_ANTIALIASING = new KeyImpl(9);
    public static final Object VALUE_ALPHA_INTERPOLATION_DEFAULT = new KeyValue(KEY_ALPHA_INTERPOLATION);
    public static final Object VALUE_ALPHA_INTERPOLATION_QUALITY = new KeyValue(KEY_ALPHA_INTERPOLATION);
    public static final Object VALUE_ALPHA_INTERPOLATION_SPEED = new KeyValue(KEY_ALPHA_INTERPOLATION);
    public static final Object VALUE_ANTIALIAS_DEFAULT = new KeyValue(KEY_ANTIALIASING);
    public static final Object VALUE_ANTIALIAS_OFF = new KeyValue(KEY_ANTIALIASING);
    public static final Object VALUE_ANTIALIAS_ON = new KeyValue(KEY_ANTIALIASING);
    public static final Object VALUE_COLOR_RENDER_DEFAULT = new KeyValue(KEY_COLOR_RENDERING);
    public static final Object VALUE_COLOR_RENDER_QUALITY = new KeyValue(KEY_COLOR_RENDERING);
    public static final Object VALUE_COLOR_RENDER_SPEED = new KeyValue(KEY_COLOR_RENDERING);
    public static final Object VALUE_DITHER_DEFAULT = new KeyValue(KEY_DITHERING);
    public static final Object VALUE_DITHER_DISABLE = new KeyValue(KEY_DITHERING);
    public static final Object VALUE_DITHER_ENABLE = new KeyValue(KEY_DITHERING);
    public static final Object VALUE_FRACTIONALMETRICS_DEFAULT = new KeyValue(KEY_FRACTIONALMETRICS);
    public static final Object VALUE_FRACTIONALMETRICS_OFF = new KeyValue(KEY_FRACTIONALMETRICS);
    public static final Object VALUE_FRACTIONALMETRICS_ON = new KeyValue(KEY_FRACTIONALMETRICS);
    public static final Object VALUE_INTERPOLATION_BICUBIC = new KeyValue(KEY_INTERPOLATION);
    public static final Object VALUE_INTERPOLATION_BILINEAR = new KeyValue(KEY_INTERPOLATION);
    public static final Object VALUE_INTERPOLATION_NEAREST_NEIGHBOR = new KeyValue(KEY_INTERPOLATION);
    public static final Object VALUE_RENDER_DEFAULT = new KeyValue(KEY_RENDERING);
    public static final Object VALUE_RENDER_QUALITY = new KeyValue(KEY_RENDERING);
    public static final Object VALUE_RENDER_SPEED = new KeyValue(KEY_RENDERING);
    public static final Object VALUE_STROKE_DEFAULT = new KeyValue(KEY_STROKE_CONTROL);
    public static final Object VALUE_STROKE_NORMALIZE = new KeyValue(KEY_STROKE_CONTROL);
    public static final Object VALUE_STROKE_PURE = new KeyValue(KEY_STROKE_CONTROL);
    public static final Object VALUE_TEXT_ANTIALIAS_DEFAULT = new KeyValue(KEY_TEXT_ANTIALIASING);
    public static final Object VALUE_TEXT_ANTIALIAS_OFF = new KeyValue(KEY_TEXT_ANTIALIASING);
    public static final Object VALUE_TEXT_ANTIALIAS_ON = new KeyValue(KEY_TEXT_ANTIALIASING);
    private HashMap<Object, Object> map = new HashMap<>();

    public RenderingHints(Map<Key, ?> map2) {
        if (map2 != null) {
            putAll(map2);
        }
    }

    public RenderingHints(Key key, Object obj) {
        put(key, obj);
    }

    public void add(RenderingHints renderingHints) {
        this.map.putAll(renderingHints.map);
    }

    public Object put(Object obj, Object obj2) {
        if (((Key) obj).isCompatibleValue(obj2)) {
            return this.map.put(obj, obj2);
        }
        throw new IllegalArgumentException();
    }

    public Object remove(Object obj) {
        return this.map.remove(obj);
    }

    public Object get(Object obj) {
        return this.map.get(obj);
    }

    public Set<Object> keySet() {
        return this.map.keySet();
    }

    public Set<Map.Entry<Object, Object>> entrySet() {
        return this.map.entrySet();
    }

    public void putAll(Map<?, ?> map2) {
        if (map2 instanceof RenderingHints) {
            this.map.putAll(((RenderingHints) map2).map);
            return;
        }
        Set<Map.Entry<?, ?>> entrySet = map2.entrySet();
        if (entrySet != null) {
            for (Map.Entry next : entrySet) {
                put((Key) next.getKey(), next.getValue());
            }
        }
    }

    public Collection<Object> values() {
        return this.map.values();
    }

    public boolean containsValue(Object obj) {
        return this.map.containsValue(obj);
    }

    public boolean containsKey(Object obj) {
        if (obj != null) {
            return this.map.containsKey(obj);
        }
        throw new NullPointerException();
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public void clear() {
        this.map.clear();
    }

    public int size() {
        return this.map.size();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map2 = (Map) obj;
        Set<Object> keySet = keySet();
        if (!keySet.equals(map2.keySet())) {
            return false;
        }
        Iterator<Object> it = keySet.iterator();
        while (it.hasNext()) {
            Key key = (Key) it.next();
            Object obj2 = get(key);
            Object obj3 = map2.get(key);
            if (obj2 == null) {
                if (obj3 == null) {
                }
            } else if (!obj2.equals(obj3)) {
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.map.hashCode();
    }

    public Object clone() {
        RenderingHints renderingHints = new RenderingHints((Map<Key, ?>) null);
        renderingHints.map = (HashMap) this.map.clone();
        return renderingHints;
    }

    public String toString() {
        return "RenderingHints[" + this.map.toString() + "]";
    }

    public static abstract class Key {
        private final int key;

        public final boolean equals(Object obj) {
            return this == obj;
        }

        public abstract boolean isCompatibleValue(Object obj);

        protected Key(int i) {
            this.key = i;
        }

        public final int hashCode() {
            return System.identityHashCode(this);
        }

        /* access modifiers changed from: protected */
        public final int intKey() {
            return this.key;
        }
    }

    private static class KeyImpl extends Key {
        protected KeyImpl(int i) {
            super(i);
        }

        public boolean isCompatibleValue(Object obj) {
            if ((obj instanceof KeyValue) && ((KeyValue) obj).key == this) {
                return true;
            }
            return false;
        }
    }

    private static class KeyValue {
        /* access modifiers changed from: private */
        public final Key key;

        protected KeyValue(Key key2) {
            this.key = key2;
        }
    }
}
