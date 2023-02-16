package com.itextpdf.text.pdf;

import com.itextpdf.text.DocWriter;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.error_messages.MessageLocalization;
import com.itextpdf.text.exceptions.BadPasswordException;
import com.itextpdf.text.pdf.crypto.AESCipherCBCnoPad;
import com.itextpdf.text.pdf.crypto.ARCFOUREncryption;
import com.itextpdf.text.pdf.crypto.IVGenerator;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.cert.Certificate;

public class PdfEncryption {
    public static final int AES_128 = 4;
    public static final int AES_256 = 5;
    private static final int KEY_SALT_OFFSET = 40;
    private static final int OU_LENGHT = 48;
    private static final int SALT_LENGHT = 8;
    public static final int STANDARD_ENCRYPTION_128 = 3;
    public static final int STANDARD_ENCRYPTION_40 = 2;
    private static final int VALIDATION_SALT_OFFSET = 32;
    private static final byte[] metadataPad = {-1, -1, -1, -1};
    private static final byte[] pad = {40, -65, 78, 94, 78, 117, -118, 65, 100, 0, 78, 86, -1, -6, 1, 8, 46, 46, 0, -74, -48, 104, DocWriter.GT, Byte.MIN_VALUE, DocWriter.FORWARD, BidiOrder.CS, -87, -2, 100, 83, 105, 122};
    private static final byte[] salt = {115, 65, 108, 84};
    static long seq = System.currentTimeMillis();
    private ARCFOUREncryption arcfour;
    private int cryptoMode;
    byte[] documentID;
    private boolean embeddedFilesOnly;
    private boolean encryptMetadata;
    byte[] extra;
    byte[] key;
    private int keyLength;
    int keySize;
    MessageDigest md5;
    byte[] mkey;
    byte[] oeKey;
    byte[] ownerKey;
    long permissions;
    byte[] perms;
    protected PdfPublicKeySecurityHandler publicKeyHandler;
    private int revision;
    byte[] ueKey;
    byte[] userKey;

    public PdfEncryption() {
        this.mkey = new byte[0];
        this.ownerKey = new byte[32];
        this.userKey = new byte[32];
        this.publicKeyHandler = null;
        this.extra = new byte[5];
        this.arcfour = new ARCFOUREncryption();
        try {
            this.md5 = MessageDigest.getInstance("MD5");
            this.publicKeyHandler = new PdfPublicKeySecurityHandler();
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    public PdfEncryption(PdfEncryption pdfEncryption) {
        this();
        if (pdfEncryption.key != null) {
            this.key = (byte[]) pdfEncryption.key.clone();
        }
        this.keySize = pdfEncryption.keySize;
        this.mkey = (byte[]) pdfEncryption.mkey.clone();
        this.ownerKey = (byte[]) pdfEncryption.ownerKey.clone();
        this.userKey = (byte[]) pdfEncryption.userKey.clone();
        this.permissions = pdfEncryption.permissions;
        if (pdfEncryption.documentID != null) {
            this.documentID = (byte[]) pdfEncryption.documentID.clone();
        }
        this.revision = pdfEncryption.revision;
        this.keyLength = pdfEncryption.keyLength;
        this.encryptMetadata = pdfEncryption.encryptMetadata;
        this.embeddedFilesOnly = pdfEncryption.embeddedFilesOnly;
        this.publicKeyHandler = pdfEncryption.publicKeyHandler;
        if (pdfEncryption.ueKey != null) {
            this.ueKey = (byte[]) pdfEncryption.ueKey.clone();
        }
        if (pdfEncryption.oeKey != null) {
            this.oeKey = (byte[]) pdfEncryption.oeKey.clone();
        }
        if (pdfEncryption.perms != null) {
            this.perms = (byte[]) pdfEncryption.perms.clone();
        }
    }

    public void setCryptoMode(int i, int i2) {
        this.cryptoMode = i;
        this.encryptMetadata = (i & 8) != 8;
        this.embeddedFilesOnly = (i & 24) == 24;
        switch (i & 7) {
            case 0:
                this.encryptMetadata = true;
                this.embeddedFilesOnly = false;
                this.keyLength = 40;
                this.revision = 2;
                return;
            case 1:
                this.embeddedFilesOnly = false;
                if (i2 > 0) {
                    this.keyLength = i2;
                } else {
                    this.keyLength = 128;
                }
                this.revision = 3;
                return;
            case 2:
                this.keyLength = 128;
                this.revision = 4;
                return;
            case 3:
                this.keyLength = 256;
                this.keySize = 32;
                this.revision = 5;
                return;
            default:
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("no.valid.encryption.mode", new Object[0]));
        }
    }

    public int getCryptoMode() {
        return this.cryptoMode;
    }

    public boolean isMetadataEncrypted() {
        return this.encryptMetadata;
    }

    public long getPermissions() {
        return this.permissions;
    }

    public boolean isEmbeddedFilesOnly() {
        return this.embeddedFilesOnly;
    }

    private byte[] padPassword(byte[] bArr) {
        byte[] bArr2 = new byte[32];
        if (bArr == null) {
            System.arraycopy(pad, 0, bArr2, 0, 32);
        } else {
            System.arraycopy(bArr, 0, bArr2, 0, Math.min(bArr.length, 32));
            if (bArr.length < 32) {
                System.arraycopy(pad, 0, bArr2, bArr.length, 32 - bArr.length);
            }
        }
        return bArr2;
    }

    private byte[] computeOwnerKey(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[32];
        byte[] digest = this.md5.digest(bArr2);
        if (this.revision == 3 || this.revision == 4) {
            byte[] bArr4 = new byte[(this.keyLength / 8)];
            for (int i = 0; i < 50; i++) {
                this.md5.update(digest, 0, bArr4.length);
                System.arraycopy(this.md5.digest(), 0, digest, 0, bArr4.length);
            }
            System.arraycopy(bArr, 0, bArr3, 0, 32);
            for (int i2 = 0; i2 < 20; i2++) {
                for (int i3 = 0; i3 < bArr4.length; i3++) {
                    bArr4[i3] = (byte) (digest[i3] ^ i2);
                }
                this.arcfour.prepareARCFOURKey(bArr4);
                this.arcfour.encryptARCFOUR(bArr3);
            }
        } else {
            this.arcfour.prepareARCFOURKey(digest, 0, 5);
            this.arcfour.encryptARCFOUR(bArr, bArr3);
        }
        return bArr3;
    }

    private void setupGlobalEncryptionKey(byte[] bArr, byte[] bArr2, byte[] bArr3, long j) {
        this.documentID = bArr;
        this.ownerKey = bArr3;
        this.permissions = j;
        this.mkey = new byte[(this.keyLength / 8)];
        this.md5.reset();
        this.md5.update(bArr2);
        this.md5.update(bArr3);
        this.md5.update(new byte[]{(byte) ((int) j), (byte) ((int) (j >> 8)), (byte) ((int) (j >> 16)), (byte) ((int) (j >> 24))}, 0, 4);
        if (bArr != null) {
            this.md5.update(bArr);
        }
        if (!this.encryptMetadata) {
            this.md5.update(metadataPad);
        }
        byte[] bArr4 = new byte[this.mkey.length];
        System.arraycopy(this.md5.digest(), 0, bArr4, 0, this.mkey.length);
        if (this.revision == 3 || this.revision == 4) {
            for (int i = 0; i < 50; i++) {
                System.arraycopy(this.md5.digest(bArr4), 0, bArr4, 0, this.mkey.length);
            }
        }
        System.arraycopy(bArr4, 0, this.mkey, 0, this.mkey.length);
    }

    private void setupUserKey() {
        if (this.revision == 3 || this.revision == 4) {
            this.md5.update(pad);
            byte[] digest = this.md5.digest(this.documentID);
            System.arraycopy(digest, 0, this.userKey, 0, 16);
            for (int i = 16; i < 32; i++) {
                this.userKey[i] = 0;
            }
            for (int i2 = 0; i2 < 20; i2++) {
                for (int i3 = 0; i3 < this.mkey.length; i3++) {
                    digest[i3] = (byte) (this.mkey[i3] ^ i2);
                }
                this.arcfour.prepareARCFOURKey(digest, 0, this.mkey.length);
                this.arcfour.encryptARCFOUR(this.userKey, 0, 16);
            }
            return;
        }
        this.arcfour.prepareARCFOURKey(this.mkey);
        this.arcfour.encryptARCFOUR(pad, this.userKey);
    }

    public void setupAllKeys(byte[] bArr, byte[] bArr2, int i) {
        byte[] bArr3;
        byte[] bArr4 = bArr2;
        if (bArr4 == null || bArr4.length == 0) {
            bArr4 = this.md5.digest(createDocumentId());
        }
        int i2 = (i | ((this.revision == 3 || this.revision == 4 || this.revision == 5) ? -3904 : -64)) & -4;
        long j = (long) i2;
        this.permissions = j;
        if (this.revision == 5) {
            if (bArr == null) {
                try {
                    bArr3 = new byte[0];
                } catch (Exception e) {
                    throw new ExceptionConverter(e);
                }
            } else {
                bArr3 = bArr;
            }
            this.documentID = createDocumentId();
            byte[] iv = IVGenerator.getIV(8);
            byte[] iv2 = IVGenerator.getIV(8);
            this.key = IVGenerator.getIV(32);
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            instance.update(bArr3, 0, Math.min(bArr3.length, 127));
            instance.update(iv);
            this.userKey = new byte[48];
            instance.digest(this.userKey, 0, 32);
            System.arraycopy(iv, 0, this.userKey, 32, 8);
            System.arraycopy(iv2, 0, this.userKey, 40, 8);
            instance.update(bArr3, 0, Math.min(bArr3.length, 127));
            instance.update(iv2);
            this.ueKey = new AESCipherCBCnoPad(true, instance.digest()).processBlock(this.key, 0, this.key.length);
            byte[] iv3 = IVGenerator.getIV(8);
            byte[] iv4 = IVGenerator.getIV(8);
            instance.update(bArr4, 0, Math.min(bArr4.length, 127));
            instance.update(iv3);
            instance.update(this.userKey);
            this.ownerKey = new byte[48];
            instance.digest(this.ownerKey, 0, 32);
            System.arraycopy(iv3, 0, this.ownerKey, 32, 8);
            System.arraycopy(iv4, 0, this.ownerKey, 40, 8);
            instance.update(bArr4, 0, Math.min(bArr4.length, 127));
            instance.update(iv4);
            instance.update(this.userKey);
            this.oeKey = new AESCipherCBCnoPad(true, instance.digest()).processBlock(this.key, 0, this.key.length);
            byte[] iv5 = IVGenerator.getIV(16);
            iv5[0] = (byte) i2;
            iv5[1] = (byte) (i2 >> 8);
            iv5[2] = (byte) (i2 >> 16);
            iv5[3] = (byte) (i2 >> 24);
            iv5[4] = -1;
            iv5[5] = -1;
            iv5[6] = -1;
            iv5[7] = -1;
            iv5[8] = this.encryptMetadata ? (byte) 84 : 70;
            iv5[9] = 97;
            iv5[10] = 100;
            iv5[11] = 98;
            this.perms = new AESCipherCBCnoPad(true, this.key).processBlock(iv5, 0, iv5.length);
            return;
        }
        byte[] padPassword = padPassword(bArr);
        this.ownerKey = computeOwnerKey(padPassword, padPassword(bArr4));
        this.documentID = createDocumentId();
        setupByUserPad(this.documentID, padPassword, this.ownerKey, j);
    }

    public boolean readKey(PdfDictionary pdfDictionary, byte[] bArr) throws BadPasswordException {
        boolean z = false;
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } catch (BadPasswordException e) {
                throw e;
            } catch (Exception e2) {
                throw new ExceptionConverter(e2);
            }
        }
        byte[] iSOBytes = DocWriter.getISOBytes(pdfDictionary.get(PdfName.O).toString());
        byte[] iSOBytes2 = DocWriter.getISOBytes(pdfDictionary.get(PdfName.U).toString());
        byte[] iSOBytes3 = DocWriter.getISOBytes(pdfDictionary.get(PdfName.OE).toString());
        byte[] iSOBytes4 = DocWriter.getISOBytes(pdfDictionary.get(PdfName.UE).toString());
        byte[] iSOBytes5 = DocWriter.getISOBytes(pdfDictionary.get(PdfName.PERMS).toString());
        this.oeKey = iSOBytes3;
        this.ueKey = iSOBytes4;
        this.perms = iSOBytes5;
        this.ownerKey = iSOBytes;
        this.userKey = iSOBytes2;
        this.permissions = ((PdfNumber) pdfDictionary.get(PdfName.P)).longValue();
        MessageDigest instance = MessageDigest.getInstance("SHA-256");
        instance.update(bArr, 0, Math.min(bArr.length, 127));
        instance.update(iSOBytes, 32, 8);
        instance.update(iSOBytes2, 0, 48);
        boolean compareArray = compareArray(instance.digest(), iSOBytes, 32);
        if (compareArray) {
            instance.update(bArr, 0, Math.min(bArr.length, 127));
            instance.update(iSOBytes, 40, 8);
            instance.update(iSOBytes2, 0, 48);
            this.key = new AESCipherCBCnoPad(false, instance.digest()).processBlock(iSOBytes3, 0, iSOBytes3.length);
        } else {
            instance.update(bArr, 0, Math.min(bArr.length, 127));
            instance.update(iSOBytes2, 32, 8);
            if (!compareArray(instance.digest(), iSOBytes2, 32)) {
                throw new BadPasswordException(MessageLocalization.getComposedMessage("bad.user.password", new Object[0]));
            }
            instance.update(bArr, 0, Math.min(bArr.length, 127));
            instance.update(iSOBytes2, 40, 8);
            this.key = new AESCipherCBCnoPad(false, instance.digest()).processBlock(iSOBytes4, 0, iSOBytes4.length);
        }
        byte[] processBlock = new AESCipherCBCnoPad(false, this.key).processBlock(iSOBytes5, 0, iSOBytes5.length);
        if (processBlock[9] == 97 && processBlock[10] == 100) {
            if (processBlock[11] == 98) {
                this.permissions = (long) ((processBlock[0] & 255) | ((processBlock[1] & 255) << 8) | ((processBlock[2] & 255) << BidiOrder.S) | ((processBlock[2] & 255) << 24));
                if (processBlock[8] == 84) {
                    z = true;
                }
                this.encryptMetadata = z;
                return compareArray;
            }
        }
        throw new BadPasswordException(MessageLocalization.getComposedMessage("bad.user.password", new Object[0]));
    }

    private static boolean compareArray(byte[] bArr, byte[] bArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (bArr[i2] != bArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    public static byte[] createDocumentId() {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            long currentTimeMillis = System.currentTimeMillis();
            long freeMemory = Runtime.getRuntime().freeMemory();
            StringBuilder sb = new StringBuilder();
            sb.append(currentTimeMillis);
            sb.append("+");
            sb.append(freeMemory);
            sb.append("+");
            long j = seq;
            seq = j + 1;
            sb.append(j);
            return instance.digest(sb.toString().getBytes());
        } catch (Exception e) {
            throw new ExceptionConverter(e);
        }
    }

    public void setupByUserPassword(byte[] bArr, byte[] bArr2, byte[] bArr3, long j) {
        setupByUserPad(bArr, padPassword(bArr2), bArr3, j);
    }

    private void setupByUserPad(byte[] bArr, byte[] bArr2, byte[] bArr3, long j) {
        setupGlobalEncryptionKey(bArr, bArr2, bArr3, j);
        setupUserKey();
    }

    public void setupByOwnerPassword(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, long j) {
        setupByOwnerPad(bArr, padPassword(bArr2), bArr3, bArr4, j);
    }

    private void setupByOwnerPad(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, long j) {
        setupGlobalEncryptionKey(bArr, computeOwnerKey(bArr4, bArr2), bArr4, j);
        setupUserKey();
    }

    public void setKey(byte[] bArr) {
        this.key = bArr;
    }

    public void setupByEncryptionKey(byte[] bArr, int i) {
        this.mkey = new byte[(i / 8)];
        System.arraycopy(bArr, 0, this.mkey, 0, this.mkey.length);
    }

    public void setHashKey(int i, int i2) {
        if (this.revision != 5) {
            this.md5.reset();
            this.extra[0] = (byte) i;
            this.extra[1] = (byte) (i >> 8);
            this.extra[2] = (byte) (i >> 16);
            this.extra[3] = (byte) i2;
            this.extra[4] = (byte) (i2 >> 8);
            this.md5.update(this.mkey);
            this.md5.update(this.extra);
            if (this.revision == 4) {
                this.md5.update(salt);
            }
            this.key = this.md5.digest();
            this.keySize = this.mkey.length + 5;
            if (this.keySize > 16) {
                this.keySize = 16;
            }
        }
    }

    public static PdfObject createInfoId(byte[] bArr, boolean z) throws IOException {
        ByteBuffer byteBuffer = new ByteBuffer(90);
        if (bArr.length == 0) {
            bArr = createDocumentId();
        }
        byteBuffer.append('[').append('<');
        for (byte appendHex : bArr) {
            byteBuffer.appendHex(appendHex);
        }
        byteBuffer.append('>').append('<');
        if (z) {
            bArr = createDocumentId();
        }
        for (byte appendHex2 : bArr) {
            byteBuffer.appendHex(appendHex2);
        }
        byteBuffer.append('>').append(']');
        byteBuffer.close();
        return new PdfLiteral(byteBuffer.toByteArray());
    }

    public PdfDictionary getEncryptionDictionary() {
        MessageDigest messageDigest;
        PdfDictionary pdfDictionary = new PdfDictionary();
        if (this.publicKeyHandler.getRecipientsSize() > 0) {
            pdfDictionary.put(PdfName.FILTER, PdfName.PUBSEC);
            pdfDictionary.put(PdfName.R, new PdfNumber(this.revision));
            try {
                PdfArray encodedRecipients = this.publicKeyHandler.getEncodedRecipients();
                if (this.revision == 2) {
                    pdfDictionary.put(PdfName.V, new PdfNumber(1));
                    pdfDictionary.put(PdfName.SUBFILTER, PdfName.ADBE_PKCS7_S4);
                    pdfDictionary.put(PdfName.RECIPIENTS, encodedRecipients);
                } else if (this.revision != 3 || !this.encryptMetadata) {
                    if (this.revision == 5) {
                        pdfDictionary.put(PdfName.R, new PdfNumber(5));
                        pdfDictionary.put(PdfName.V, new PdfNumber(5));
                    } else {
                        pdfDictionary.put(PdfName.R, new PdfNumber(4));
                        pdfDictionary.put(PdfName.V, new PdfNumber(4));
                    }
                    pdfDictionary.put(PdfName.SUBFILTER, PdfName.ADBE_PKCS7_S5);
                    PdfDictionary pdfDictionary2 = new PdfDictionary();
                    pdfDictionary2.put(PdfName.RECIPIENTS, encodedRecipients);
                    if (!this.encryptMetadata) {
                        pdfDictionary2.put(PdfName.ENCRYPTMETADATA, PdfBoolean.PDFFALSE);
                    }
                    if (this.revision == 4) {
                        pdfDictionary2.put(PdfName.CFM, PdfName.AESV2);
                        pdfDictionary2.put(PdfName.LENGTH, new PdfNumber(128));
                    } else if (this.revision == 5) {
                        pdfDictionary2.put(PdfName.CFM, PdfName.AESV3);
                        pdfDictionary2.put(PdfName.LENGTH, new PdfNumber(256));
                    } else {
                        pdfDictionary2.put(PdfName.CFM, PdfName.V2);
                    }
                    PdfDictionary pdfDictionary3 = new PdfDictionary();
                    pdfDictionary3.put(PdfName.DEFAULTCRYPTFILTER, pdfDictionary2);
                    pdfDictionary.put(PdfName.CF, pdfDictionary3);
                    if (this.embeddedFilesOnly) {
                        pdfDictionary.put(PdfName.EFF, PdfName.DEFAULTCRYPTFILTER);
                        pdfDictionary.put(PdfName.STRF, PdfName.IDENTITY);
                        pdfDictionary.put(PdfName.STMF, PdfName.IDENTITY);
                    } else {
                        pdfDictionary.put(PdfName.STRF, PdfName.DEFAULTCRYPTFILTER);
                        pdfDictionary.put(PdfName.STMF, PdfName.DEFAULTCRYPTFILTER);
                    }
                } else {
                    pdfDictionary.put(PdfName.V, new PdfNumber(2));
                    pdfDictionary.put(PdfName.LENGTH, new PdfNumber(128));
                    pdfDictionary.put(PdfName.SUBFILTER, PdfName.ADBE_PKCS7_S4);
                    pdfDictionary.put(PdfName.RECIPIENTS, encodedRecipients);
                }
                try {
                    if (this.revision == 5) {
                        messageDigest = MessageDigest.getInstance("SHA-256");
                    } else {
                        messageDigest = MessageDigest.getInstance("SHA-1");
                    }
                    messageDigest.update(this.publicKeyHandler.getSeed());
                    for (int i = 0; i < this.publicKeyHandler.getRecipientsSize(); i++) {
                        messageDigest.update(this.publicKeyHandler.getEncodedRecipient(i));
                    }
                    if (!this.encryptMetadata) {
                        messageDigest.update(new byte[]{-1, -1, -1, -1});
                    }
                    byte[] digest = messageDigest.digest();
                    if (this.revision == 5) {
                        this.key = digest;
                    } else {
                        setupByEncryptionKey(digest, this.keyLength);
                    }
                } catch (Exception e) {
                    throw new ExceptionConverter(e);
                }
            } catch (Exception e2) {
                throw new ExceptionConverter(e2);
            }
        } else {
            pdfDictionary.put(PdfName.FILTER, PdfName.STANDARD);
            pdfDictionary.put(PdfName.O, new PdfLiteral(StringUtils.escapeString(this.ownerKey)));
            pdfDictionary.put(PdfName.U, new PdfLiteral(StringUtils.escapeString(this.userKey)));
            pdfDictionary.put(PdfName.P, new PdfNumber(this.permissions));
            pdfDictionary.put(PdfName.R, new PdfNumber(this.revision));
            if (this.revision == 2) {
                pdfDictionary.put(PdfName.V, new PdfNumber(1));
            } else if (this.revision == 3 && this.encryptMetadata) {
                pdfDictionary.put(PdfName.V, new PdfNumber(2));
                pdfDictionary.put(PdfName.LENGTH, new PdfNumber(128));
            } else if (this.revision == 5) {
                if (!this.encryptMetadata) {
                    pdfDictionary.put(PdfName.ENCRYPTMETADATA, PdfBoolean.PDFFALSE);
                }
                pdfDictionary.put(PdfName.OE, new PdfLiteral(StringUtils.escapeString(this.oeKey)));
                pdfDictionary.put(PdfName.UE, new PdfLiteral(StringUtils.escapeString(this.ueKey)));
                pdfDictionary.put(PdfName.PERMS, new PdfLiteral(StringUtils.escapeString(this.perms)));
                pdfDictionary.put(PdfName.V, new PdfNumber(this.revision));
                pdfDictionary.put(PdfName.LENGTH, new PdfNumber(256));
                PdfDictionary pdfDictionary4 = new PdfDictionary();
                pdfDictionary4.put(PdfName.LENGTH, new PdfNumber(32));
                if (this.embeddedFilesOnly) {
                    pdfDictionary4.put(PdfName.AUTHEVENT, PdfName.EFOPEN);
                    pdfDictionary.put(PdfName.EFF, PdfName.STDCF);
                    pdfDictionary.put(PdfName.STRF, PdfName.IDENTITY);
                    pdfDictionary.put(PdfName.STMF, PdfName.IDENTITY);
                } else {
                    pdfDictionary4.put(PdfName.AUTHEVENT, PdfName.DOCOPEN);
                    pdfDictionary.put(PdfName.STRF, PdfName.STDCF);
                    pdfDictionary.put(PdfName.STMF, PdfName.STDCF);
                }
                pdfDictionary4.put(PdfName.CFM, PdfName.AESV3);
                PdfDictionary pdfDictionary5 = new PdfDictionary();
                pdfDictionary5.put(PdfName.STDCF, pdfDictionary4);
                pdfDictionary.put(PdfName.CF, pdfDictionary5);
            } else {
                if (!this.encryptMetadata) {
                    pdfDictionary.put(PdfName.ENCRYPTMETADATA, PdfBoolean.PDFFALSE);
                }
                pdfDictionary.put(PdfName.R, new PdfNumber(4));
                pdfDictionary.put(PdfName.V, new PdfNumber(4));
                pdfDictionary.put(PdfName.LENGTH, new PdfNumber(128));
                PdfDictionary pdfDictionary6 = new PdfDictionary();
                pdfDictionary6.put(PdfName.LENGTH, new PdfNumber(16));
                if (this.embeddedFilesOnly) {
                    pdfDictionary6.put(PdfName.AUTHEVENT, PdfName.EFOPEN);
                    pdfDictionary.put(PdfName.EFF, PdfName.STDCF);
                    pdfDictionary.put(PdfName.STRF, PdfName.IDENTITY);
                    pdfDictionary.put(PdfName.STMF, PdfName.IDENTITY);
                } else {
                    pdfDictionary6.put(PdfName.AUTHEVENT, PdfName.DOCOPEN);
                    pdfDictionary.put(PdfName.STRF, PdfName.STDCF);
                    pdfDictionary.put(PdfName.STMF, PdfName.STDCF);
                }
                if (this.revision == 4) {
                    pdfDictionary6.put(PdfName.CFM, PdfName.AESV2);
                } else {
                    pdfDictionary6.put(PdfName.CFM, PdfName.V2);
                }
                PdfDictionary pdfDictionary7 = new PdfDictionary();
                pdfDictionary7.put(PdfName.STDCF, pdfDictionary6);
                pdfDictionary.put(PdfName.CF, pdfDictionary7);
            }
        }
        return pdfDictionary;
    }

    public PdfObject getFileID(boolean z) throws IOException {
        return createInfoId(this.documentID, z);
    }

    public OutputStreamEncryption getEncryptionStream(OutputStream outputStream) {
        return new OutputStreamEncryption(outputStream, this.key, 0, this.keySize, this.revision);
    }

    public int calculateStreamSize(int i) {
        return (this.revision == 4 || this.revision == 5) ? (i & 2147483632) + 32 : i;
    }

    public byte[] encryptByteArray(byte[] bArr) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            OutputStreamEncryption encryptionStream = getEncryptionStream(byteArrayOutputStream);
            encryptionStream.write(bArr);
            encryptionStream.finish();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new ExceptionConverter(e);
        }
    }

    public StandardDecryption getDecryptor() {
        return new StandardDecryption(this.key, 0, this.keySize, this.revision);
    }

    public byte[] decryptByteArray(byte[] bArr) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            StandardDecryption decryptor = getDecryptor();
            byte[] update = decryptor.update(bArr, 0, bArr.length);
            if (update != null) {
                byteArrayOutputStream.write(update);
            }
            byte[] finish = decryptor.finish();
            if (finish != null) {
                byteArrayOutputStream.write(finish);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new ExceptionConverter(e);
        }
    }

    public void addRecipient(Certificate certificate, int i) {
        this.documentID = createDocumentId();
        this.publicKeyHandler.addRecipient(new PdfPublicKeyRecipient(certificate, i));
    }

    public byte[] computeUserPassword(byte[] bArr) {
        boolean z;
        byte[] computeOwnerKey = computeOwnerKey(this.ownerKey, padPassword(bArr));
        int i = 0;
        while (i < computeOwnerKey.length) {
            int i2 = 0;
            while (true) {
                if (i2 >= computeOwnerKey.length - i) {
                    z = true;
                    break;
                } else if (computeOwnerKey[i + i2] != pad[i2]) {
                    z = false;
                    break;
                } else {
                    i2++;
                }
            }
            if (!z) {
                i++;
            } else {
                byte[] bArr2 = new byte[i];
                System.arraycopy(computeOwnerKey, 0, bArr2, 0, i);
                return bArr2;
            }
        }
        return computeOwnerKey;
    }
}
