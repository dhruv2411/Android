package com.google.android.exoplayer2.upstream;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.net.Uri;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public final class RawResourceDataSource implements DataSource {
    private static final String RAW_RESOURCE_SCHEME = "rawresource";
    private AssetFileDescriptor assetFileDescriptor;
    private long bytesRemaining;
    private InputStream inputStream;
    private final TransferListener<? super RawResourceDataSource> listener;
    private boolean opened;
    private final Resources resources;
    private Uri uri;

    public static class RawResourceDataSourceException extends IOException {
        public RawResourceDataSourceException(String str) {
            super(str);
        }

        public RawResourceDataSourceException(IOException iOException) {
            super(iOException);
        }
    }

    public static Uri buildRawResourceUri(int i) {
        return Uri.parse("rawresource:///" + i);
    }

    public RawResourceDataSource(Context context) {
        this(context, (TransferListener<? super RawResourceDataSource>) null);
    }

    public RawResourceDataSource(Context context, TransferListener<? super RawResourceDataSource> transferListener) {
        this.resources = context.getResources();
        this.listener = transferListener;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x008d, code lost:
        throw new com.google.android.exoplayer2.upstream.RawResourceDataSource.RawResourceDataSourceException("Resource identifier must be an integer.");
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0086 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long open(com.google.android.exoplayer2.upstream.DataSpec r7) throws com.google.android.exoplayer2.upstream.RawResourceDataSource.RawResourceDataSourceException {
        /*
            r6 = this;
            android.net.Uri r0 = r7.uri     // Catch:{ IOException -> 0x008e }
            r6.uri = r0     // Catch:{ IOException -> 0x008e }
            java.lang.String r0 = "rawresource"
            android.net.Uri r1 = r6.uri     // Catch:{ IOException -> 0x008e }
            java.lang.String r1 = r1.getScheme()     // Catch:{ IOException -> 0x008e }
            boolean r0 = android.text.TextUtils.equals(r0, r1)     // Catch:{ IOException -> 0x008e }
            if (r0 != 0) goto L_0x001a
            com.google.android.exoplayer2.upstream.RawResourceDataSource$RawResourceDataSourceException r7 = new com.google.android.exoplayer2.upstream.RawResourceDataSource$RawResourceDataSourceException     // Catch:{ IOException -> 0x008e }
            java.lang.String r0 = "URI must use scheme rawresource"
            r7.<init>((java.lang.String) r0)     // Catch:{ IOException -> 0x008e }
            throw r7     // Catch:{ IOException -> 0x008e }
        L_0x001a:
            android.net.Uri r0 = r6.uri     // Catch:{ NumberFormatException -> 0x0086 }
            java.lang.String r0 = r0.getLastPathSegment()     // Catch:{ NumberFormatException -> 0x0086 }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ NumberFormatException -> 0x0086 }
            android.content.res.Resources r1 = r6.resources     // Catch:{ IOException -> 0x008e }
            android.content.res.AssetFileDescriptor r0 = r1.openRawResourceFd(r0)     // Catch:{ IOException -> 0x008e }
            r6.assetFileDescriptor = r0     // Catch:{ IOException -> 0x008e }
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ IOException -> 0x008e }
            android.content.res.AssetFileDescriptor r1 = r6.assetFileDescriptor     // Catch:{ IOException -> 0x008e }
            java.io.FileDescriptor r1 = r1.getFileDescriptor()     // Catch:{ IOException -> 0x008e }
            r0.<init>(r1)     // Catch:{ IOException -> 0x008e }
            r6.inputStream = r0     // Catch:{ IOException -> 0x008e }
            java.io.InputStream r0 = r6.inputStream     // Catch:{ IOException -> 0x008e }
            android.content.res.AssetFileDescriptor r1 = r6.assetFileDescriptor     // Catch:{ IOException -> 0x008e }
            long r1 = r1.getStartOffset()     // Catch:{ IOException -> 0x008e }
            r0.skip(r1)     // Catch:{ IOException -> 0x008e }
            java.io.InputStream r0 = r6.inputStream     // Catch:{ IOException -> 0x008e }
            long r1 = r7.position     // Catch:{ IOException -> 0x008e }
            long r0 = r0.skip(r1)     // Catch:{ IOException -> 0x008e }
            long r2 = r7.position     // Catch:{ IOException -> 0x008e }
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 >= 0) goto L_0x0058
            java.io.EOFException r7 = new java.io.EOFException     // Catch:{ IOException -> 0x008e }
            r7.<init>()     // Catch:{ IOException -> 0x008e }
            throw r7     // Catch:{ IOException -> 0x008e }
        L_0x0058:
            long r0 = r7.length     // Catch:{ IOException -> 0x008e }
            r2 = -1
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x0065
            long r0 = r7.length     // Catch:{ IOException -> 0x008e }
            r6.bytesRemaining = r0     // Catch:{ IOException -> 0x008e }
            goto L_0x0077
        L_0x0065:
            android.content.res.AssetFileDescriptor r0 = r6.assetFileDescriptor     // Catch:{ IOException -> 0x008e }
            long r0 = r0.getLength()     // Catch:{ IOException -> 0x008e }
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L_0x0070
            goto L_0x0075
        L_0x0070:
            long r2 = r7.position     // Catch:{ IOException -> 0x008e }
            long r4 = r0 - r2
            r2 = r4
        L_0x0075:
            r6.bytesRemaining = r2     // Catch:{ IOException -> 0x008e }
        L_0x0077:
            r0 = 1
            r6.opened = r0
            com.google.android.exoplayer2.upstream.TransferListener<? super com.google.android.exoplayer2.upstream.RawResourceDataSource> r0 = r6.listener
            if (r0 == 0) goto L_0x0083
            com.google.android.exoplayer2.upstream.TransferListener<? super com.google.android.exoplayer2.upstream.RawResourceDataSource> r0 = r6.listener
            r0.onTransferStart(r6, r7)
        L_0x0083:
            long r0 = r6.bytesRemaining
            return r0
        L_0x0086:
            com.google.android.exoplayer2.upstream.RawResourceDataSource$RawResourceDataSourceException r7 = new com.google.android.exoplayer2.upstream.RawResourceDataSource$RawResourceDataSourceException     // Catch:{ IOException -> 0x008e }
            java.lang.String r0 = "Resource identifier must be an integer."
            r7.<init>((java.lang.String) r0)     // Catch:{ IOException -> 0x008e }
            throw r7     // Catch:{ IOException -> 0x008e }
        L_0x008e:
            r7 = move-exception
            com.google.android.exoplayer2.upstream.RawResourceDataSource$RawResourceDataSourceException r0 = new com.google.android.exoplayer2.upstream.RawResourceDataSource$RawResourceDataSourceException
            r0.<init>((java.io.IOException) r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.upstream.RawResourceDataSource.open(com.google.android.exoplayer2.upstream.DataSpec):long");
    }

    public int read(byte[] bArr, int i, int i2) throws RawResourceDataSourceException {
        if (i2 == 0) {
            return 0;
        }
        if (this.bytesRemaining == 0) {
            return -1;
        }
        try {
            if (this.bytesRemaining != -1) {
                i2 = (int) Math.min(this.bytesRemaining, (long) i2);
            }
            int read = this.inputStream.read(bArr, i, i2);
            if (read != -1) {
                if (this.bytesRemaining != -1) {
                    this.bytesRemaining -= (long) read;
                }
                if (this.listener != null) {
                    this.listener.onBytesTransferred(this, read);
                }
                return read;
            } else if (this.bytesRemaining == -1) {
                return -1;
            } else {
                throw new RawResourceDataSourceException((IOException) new EOFException());
            }
        } catch (IOException e) {
            throw new RawResourceDataSourceException(e);
        }
    }

    public Uri getUri() {
        return this.uri;
    }

    public void close() throws RawResourceDataSourceException {
        this.uri = null;
        try {
            if (this.inputStream != null) {
                this.inputStream.close();
            }
            this.inputStream = null;
            try {
                if (this.assetFileDescriptor != null) {
                    this.assetFileDescriptor.close();
                }
                this.assetFileDescriptor = null;
                if (this.opened) {
                    this.opened = false;
                    if (this.listener != null) {
                        this.listener.onTransferEnd(this);
                    }
                }
            } catch (IOException e) {
                throw new RawResourceDataSourceException(e);
            } catch (Throwable th) {
                this.assetFileDescriptor = null;
                if (this.opened) {
                    this.opened = false;
                    if (this.listener != null) {
                        this.listener.onTransferEnd(this);
                    }
                }
                throw th;
            }
        } catch (IOException e2) {
            throw new RawResourceDataSourceException(e2);
        } catch (Throwable th2) {
            this.inputStream = null;
            try {
                if (this.assetFileDescriptor != null) {
                    this.assetFileDescriptor.close();
                }
                this.assetFileDescriptor = null;
                if (this.opened) {
                    this.opened = false;
                    if (this.listener != null) {
                        this.listener.onTransferEnd(this);
                    }
                }
                throw th2;
            } catch (IOException e3) {
                throw new RawResourceDataSourceException(e3);
            } catch (Throwable th3) {
                this.assetFileDescriptor = null;
                if (this.opened) {
                    this.opened = false;
                    if (this.listener != null) {
                        this.listener.onTransferEnd(this);
                    }
                }
                throw th3;
            }
        }
    }
}
