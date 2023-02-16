package com.google.android.exoplayer2.drm;

import android.annotation.TargetApi;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.drm.ExoMediaDrm;
import com.google.android.exoplayer2.upstream.DataSourceInputStream;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@TargetApi(18)
public final class HttpMediaDrmCallback implements MediaDrmCallback {
    private static final Map<String, String> PLAYREADY_KEY_REQUEST_PROPERTIES = new HashMap();
    private final HttpDataSource.Factory dataSourceFactory;
    private final String defaultUrl;
    private final Map<String, String> keyRequestProperties;

    static {
        PLAYREADY_KEY_REQUEST_PROPERTIES.put(HttpRequest.HEADER_CONTENT_TYPE, "text/xml");
        PLAYREADY_KEY_REQUEST_PROPERTIES.put("SOAPAction", "http://schemas.microsoft.com/DRM/2007/03/protocols/AcquireLicense");
    }

    public HttpMediaDrmCallback(String str, HttpDataSource.Factory factory) {
        this(str, factory, (Map<String, String>) null);
    }

    @Deprecated
    public HttpMediaDrmCallback(String str, HttpDataSource.Factory factory, Map<String, String> map) {
        this.dataSourceFactory = factory;
        this.defaultUrl = str;
        this.keyRequestProperties = new HashMap();
        if (map != null) {
            this.keyRequestProperties.putAll(map);
        }
    }

    public void setKeyRequestProperty(String str, String str2) {
        Assertions.checkNotNull(str);
        Assertions.checkNotNull(str2);
        synchronized (this.keyRequestProperties) {
            this.keyRequestProperties.put(str, str2);
        }
    }

    public void clearKeyRequestProperty(String str) {
        Assertions.checkNotNull(str);
        synchronized (this.keyRequestProperties) {
            this.keyRequestProperties.remove(str);
        }
    }

    public void clearAllKeyRequestProperties() {
        synchronized (this.keyRequestProperties) {
            this.keyRequestProperties.clear();
        }
    }

    public byte[] executeProvisionRequest(UUID uuid, ExoMediaDrm.ProvisionRequest provisionRequest) throws IOException {
        return executePost(this.dataSourceFactory, provisionRequest.getDefaultUrl() + "&signedRequest=" + new String(provisionRequest.getData()), new byte[0], (Map<String, String>) null);
    }

    public byte[] executeKeyRequest(UUID uuid, ExoMediaDrm.KeyRequest keyRequest) throws Exception {
        String defaultUrl2 = keyRequest.getDefaultUrl();
        if (TextUtils.isEmpty(defaultUrl2)) {
            defaultUrl2 = this.defaultUrl;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(HttpRequest.HEADER_CONTENT_TYPE, "application/octet-stream");
        if (C.PLAYREADY_UUID.equals(uuid)) {
            hashMap.putAll(PLAYREADY_KEY_REQUEST_PROPERTIES);
        }
        synchronized (this.keyRequestProperties) {
            hashMap.putAll(this.keyRequestProperties);
        }
        return executePost(this.dataSourceFactory, defaultUrl2, keyRequest.getData(), hashMap);
    }

    private static byte[] executePost(HttpDataSource.Factory factory, String str, byte[] bArr, Map<String, String> map) throws IOException {
        HttpDataSource createDataSource = factory.createDataSource();
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                createDataSource.setRequestProperty((String) next.getKey(), (String) next.getValue());
            }
        }
        DataSourceInputStream dataSourceInputStream = new DataSourceInputStream(createDataSource, new DataSpec(Uri.parse(str), bArr, 0, 0, -1, (String) null, 1));
        try {
            return Util.toByteArray(dataSourceInputStream);
        } finally {
            Util.closeQuietly((Closeable) dataSourceInputStream);
        }
    }
}
