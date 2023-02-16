package com.google.android.exoplayer2.upstream.cache;

import android.util.SparseArray;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.AtomicFile;
import com.google.android.exoplayer2.util.ReusableBufferedOutputStream;
import com.google.android.exoplayer2.util.Util;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

final class CachedContentIndex {
    public static final String FILE_NAME = "cached_content_index.exi";
    private static final int FLAG_ENCRYPTED_INDEX = 1;
    private static final String TAG = "CachedContentIndex";
    private static final int VERSION = 1;
    private final AtomicFile atomicFile;
    private ReusableBufferedOutputStream bufferedOutputStream;
    private boolean changed;
    private final Cipher cipher;
    private final SparseArray<String> idToKey;
    private final HashMap<String, CachedContent> keyToContent;
    private final SecretKeySpec secretKeySpec;

    public CachedContentIndex(File file) {
        this(file, (byte[]) null);
    }

    public CachedContentIndex(File file, byte[] bArr) {
        if (bArr != null) {
            Assertions.checkArgument(bArr.length == 16);
            try {
                this.cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
                this.secretKeySpec = new SecretKeySpec(bArr, "AES");
            } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
                throw new IllegalStateException(e);
            }
        } else {
            this.cipher = null;
            this.secretKeySpec = null;
        }
        this.keyToContent = new HashMap<>();
        this.idToKey = new SparseArray<>();
        this.atomicFile = new AtomicFile(new File(file, FILE_NAME));
    }

    public void load() {
        Assertions.checkState(!this.changed);
        if (!readFile()) {
            this.atomicFile.delete();
            this.keyToContent.clear();
            this.idToKey.clear();
        }
    }

    public void store() throws Cache.CacheException {
        if (this.changed) {
            writeFile();
            this.changed = false;
        }
    }

    public CachedContent add(String str) {
        CachedContent cachedContent = this.keyToContent.get(str);
        return cachedContent == null ? addNew(str, -1) : cachedContent;
    }

    public CachedContent get(String str) {
        return this.keyToContent.get(str);
    }

    public Collection<CachedContent> getAll() {
        return this.keyToContent.values();
    }

    public int assignIdForKey(String str) {
        return add(str).id;
    }

    public String getKeyForId(int i) {
        return this.idToKey.get(i);
    }

    public void removeEmpty(String str) {
        CachedContent remove = this.keyToContent.remove(str);
        if (remove != null) {
            Assertions.checkState(remove.isEmpty());
            this.idToKey.remove(remove.id);
            this.changed = true;
        }
    }

    public void removeEmpty() {
        LinkedList linkedList = new LinkedList();
        for (CachedContent next : this.keyToContent.values()) {
            if (next.isEmpty()) {
                linkedList.add(next.key);
            }
        }
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            removeEmpty((String) it.next());
        }
    }

    public Set<String> getKeys() {
        return this.keyToContent.keySet();
    }

    public void setContentLength(String str, long j) {
        CachedContent cachedContent = get(str);
        if (cachedContent == null) {
            addNew(str, j);
        } else if (cachedContent.getLength() != j) {
            cachedContent.setLength(j);
            this.changed = true;
        }
    }

    public long getContentLength(String str) {
        CachedContent cachedContent = get(str);
        if (cachedContent == null) {
            return -1;
        }
        return cachedContent.getLength();
    }

    /* JADX WARNING: Removed duplicated region for block: B:53:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00a1  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00a8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean readFile() {
        /*
            r8 = this;
            r0 = 0
            r1 = 0
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ FileNotFoundException -> 0x00a5, IOException -> 0x008e, all -> 0x008b }
            com.google.android.exoplayer2.util.AtomicFile r3 = r8.atomicFile     // Catch:{ FileNotFoundException -> 0x00a5, IOException -> 0x008e, all -> 0x008b }
            java.io.InputStream r3 = r3.openRead()     // Catch:{ FileNotFoundException -> 0x00a5, IOException -> 0x008e, all -> 0x008b }
            r2.<init>(r3)     // Catch:{ FileNotFoundException -> 0x00a5, IOException -> 0x008e, all -> 0x008b }
            java.io.DataInputStream r3 = new java.io.DataInputStream     // Catch:{ FileNotFoundException -> 0x00a5, IOException -> 0x008e, all -> 0x008b }
            r3.<init>(r2)     // Catch:{ FileNotFoundException -> 0x00a5, IOException -> 0x008e, all -> 0x008b }
            int r1 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x00a6, IOException -> 0x0089 }
            r4 = 1
            if (r1 == r4) goto L_0x001f
            if (r3 == 0) goto L_0x001e
            com.google.android.exoplayer2.util.Util.closeQuietly((java.io.Closeable) r3)
        L_0x001e:
            return r0
        L_0x001f:
            int r1 = r3.readInt()     // Catch:{ FileNotFoundException -> 0x00a6, IOException -> 0x0089 }
            r1 = r1 & r4
            if (r1 == 0) goto L_0x0058
            javax.crypto.Cipher r1 = r8.cipher     // Catch:{ FileNotFoundException -> 0x00a6, IOException -> 0x0089 }
            if (r1 != 0) goto L_0x0030
            if (r3 == 0) goto L_0x002f
            com.google.android.exoplayer2.util.Util.closeQuietly((java.io.Closeable) r3)
        L_0x002f:
            return r0
        L_0x0030:
            r1 = 16
            byte[] r1 = new byte[r1]     // Catch:{ FileNotFoundException -> 0x00a6, IOException -> 0x0089 }
            r3.readFully(r1)     // Catch:{ FileNotFoundException -> 0x00a6, IOException -> 0x0089 }
            javax.crypto.spec.IvParameterSpec r5 = new javax.crypto.spec.IvParameterSpec     // Catch:{ FileNotFoundException -> 0x00a6, IOException -> 0x0089 }
            r5.<init>(r1)     // Catch:{ FileNotFoundException -> 0x00a6, IOException -> 0x0089 }
            javax.crypto.Cipher r1 = r8.cipher     // Catch:{ InvalidAlgorithmParameterException | InvalidKeyException -> 0x0051 }
            r6 = 2
            javax.crypto.spec.SecretKeySpec r7 = r8.secretKeySpec     // Catch:{ InvalidAlgorithmParameterException | InvalidKeyException -> 0x0051 }
            r1.init(r6, r7, r5)     // Catch:{ InvalidAlgorithmParameterException | InvalidKeyException -> 0x0051 }
            java.io.DataInputStream r1 = new java.io.DataInputStream     // Catch:{ FileNotFoundException -> 0x00a6, IOException -> 0x0089 }
            javax.crypto.CipherInputStream r5 = new javax.crypto.CipherInputStream     // Catch:{ FileNotFoundException -> 0x00a6, IOException -> 0x0089 }
            javax.crypto.Cipher r6 = r8.cipher     // Catch:{ FileNotFoundException -> 0x00a6, IOException -> 0x0089 }
            r5.<init>(r2, r6)     // Catch:{ FileNotFoundException -> 0x00a6, IOException -> 0x0089 }
            r1.<init>(r5)     // Catch:{ FileNotFoundException -> 0x00a6, IOException -> 0x0089 }
            goto L_0x005f
        L_0x0051:
            r1 = move-exception
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException     // Catch:{ FileNotFoundException -> 0x00a6, IOException -> 0x0089 }
            r2.<init>(r1)     // Catch:{ FileNotFoundException -> 0x00a6, IOException -> 0x0089 }
            throw r2     // Catch:{ FileNotFoundException -> 0x00a6, IOException -> 0x0089 }
        L_0x0058:
            javax.crypto.Cipher r1 = r8.cipher     // Catch:{ FileNotFoundException -> 0x00a6, IOException -> 0x0089 }
            if (r1 == 0) goto L_0x005e
            r8.changed = r4     // Catch:{ FileNotFoundException -> 0x00a6, IOException -> 0x0089 }
        L_0x005e:
            r1 = r3
        L_0x005f:
            int r2 = r1.readInt()     // Catch:{ FileNotFoundException -> 0x00a5, IOException -> 0x008e, all -> 0x008b }
            r3 = r0
            r5 = r3
        L_0x0065:
            if (r3 >= r2) goto L_0x0077
            com.google.android.exoplayer2.upstream.cache.CachedContent r6 = new com.google.android.exoplayer2.upstream.cache.CachedContent     // Catch:{ FileNotFoundException -> 0x00a5, IOException -> 0x008e, all -> 0x008b }
            r6.<init>(r1)     // Catch:{ FileNotFoundException -> 0x00a5, IOException -> 0x008e, all -> 0x008b }
            r8.add((com.google.android.exoplayer2.upstream.cache.CachedContent) r6)     // Catch:{ FileNotFoundException -> 0x00a5, IOException -> 0x008e, all -> 0x008b }
            int r6 = r6.headerHashCode()     // Catch:{ FileNotFoundException -> 0x00a5, IOException -> 0x008e, all -> 0x008b }
            int r5 = r5 + r6
            int r3 = r3 + 1
            goto L_0x0065
        L_0x0077:
            int r2 = r1.readInt()     // Catch:{ FileNotFoundException -> 0x00a5, IOException -> 0x008e, all -> 0x008b }
            if (r2 == r5) goto L_0x0083
            if (r1 == 0) goto L_0x0082
            com.google.android.exoplayer2.util.Util.closeQuietly((java.io.Closeable) r1)
        L_0x0082:
            return r0
        L_0x0083:
            if (r1 == 0) goto L_0x0088
            com.google.android.exoplayer2.util.Util.closeQuietly((java.io.Closeable) r1)
        L_0x0088:
            return r4
        L_0x0089:
            r1 = move-exception
            goto L_0x0091
        L_0x008b:
            r0 = move-exception
            r3 = r1
            goto L_0x009f
        L_0x008e:
            r2 = move-exception
            r3 = r1
            r1 = r2
        L_0x0091:
            java.lang.String r2 = "CachedContentIndex"
            java.lang.String r4 = "Error reading cache content index file."
            android.util.Log.e(r2, r4, r1)     // Catch:{ all -> 0x009e }
            if (r3 == 0) goto L_0x009d
            com.google.android.exoplayer2.util.Util.closeQuietly((java.io.Closeable) r3)
        L_0x009d:
            return r0
        L_0x009e:
            r0 = move-exception
        L_0x009f:
            if (r3 == 0) goto L_0x00a4
            com.google.android.exoplayer2.util.Util.closeQuietly((java.io.Closeable) r3)
        L_0x00a4:
            throw r0
        L_0x00a5:
            r3 = r1
        L_0x00a6:
            if (r3 == 0) goto L_0x00ab
            com.google.android.exoplayer2.util.Util.closeQuietly((java.io.Closeable) r3)
        L_0x00ab:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.upstream.cache.CachedContentIndex.readFile():boolean");
    }

    private void writeFile() throws Cache.CacheException {
        DataOutputStream dataOutputStream;
        IOException e;
        try {
            OutputStream startWrite = this.atomicFile.startWrite();
            if (this.bufferedOutputStream == null) {
                this.bufferedOutputStream = new ReusableBufferedOutputStream(startWrite);
            } else {
                this.bufferedOutputStream.reset(startWrite);
            }
            dataOutputStream = new DataOutputStream(this.bufferedOutputStream);
            try {
                dataOutputStream.writeInt(1);
                int i = 0;
                dataOutputStream.writeInt(this.cipher != null ? 1 : 0);
                if (this.cipher != null) {
                    byte[] bArr = new byte[16];
                    new Random().nextBytes(bArr);
                    dataOutputStream.write(bArr);
                    this.cipher.init(1, this.secretKeySpec, new IvParameterSpec(bArr));
                    dataOutputStream.flush();
                    dataOutputStream = new DataOutputStream(new CipherOutputStream(this.bufferedOutputStream, this.cipher));
                }
                dataOutputStream.writeInt(this.keyToContent.size());
                for (CachedContent next : this.keyToContent.values()) {
                    next.writeToStream(dataOutputStream);
                    i += next.headerHashCode();
                }
                dataOutputStream.writeInt(i);
                this.atomicFile.endWrite(dataOutputStream);
                Util.closeQuietly((Closeable) null);
            } catch (InvalidAlgorithmParameterException | InvalidKeyException e2) {
                throw new IllegalStateException(e2);
            } catch (IOException e3) {
                e = e3;
                try {
                    throw new Cache.CacheException(e);
                } catch (Throwable th) {
                    th = th;
                    Util.closeQuietly((Closeable) dataOutputStream);
                    throw th;
                }
            }
        } catch (IOException e4) {
            IOException iOException = e4;
            dataOutputStream = null;
            e = iOException;
            throw new Cache.CacheException(e);
        } catch (Throwable th2) {
            Throwable th3 = th2;
            dataOutputStream = null;
            th = th3;
            Util.closeQuietly((Closeable) dataOutputStream);
            throw th;
        }
    }

    private void add(CachedContent cachedContent) {
        this.keyToContent.put(cachedContent.key, cachedContent);
        this.idToKey.put(cachedContent.id, cachedContent.key);
    }

    /* access modifiers changed from: package-private */
    public void addNew(CachedContent cachedContent) {
        add(cachedContent);
        this.changed = true;
    }

    private CachedContent addNew(String str, long j) {
        CachedContent cachedContent = new CachedContent(getNewId(this.idToKey), str, j);
        addNew(cachedContent);
        return cachedContent;
    }

    public static int getNewId(SparseArray<String> sparseArray) {
        int i;
        int size = sparseArray.size();
        if (size == 0) {
            i = 0;
        } else {
            i = sparseArray.keyAt(size - 1) + 1;
        }
        if (i < 0) {
            int i2 = 0;
            while (i < size && i == sparseArray.keyAt(i)) {
                i2 = i + 1;
            }
        }
        return i;
    }
}
