package com.itextpdf.text.pdf;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.error_messages.MessageLocalization;
import java.io.IOException;
import java.util.HashMap;

class EnumerateTTC extends TrueTypeFont {
    protected String[] names;

    EnumerateTTC(String str) throws DocumentException, IOException {
        this.fileName = str;
        this.rf = new RandomAccessFileOrArray(str);
        findNames();
    }

    EnumerateTTC(byte[] bArr) throws DocumentException, IOException {
        this.fileName = "Byte array TTC";
        this.rf = new RandomAccessFileOrArray(bArr);
        findNames();
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public void findNames() throws DocumentException, IOException {
        this.tables = new HashMap();
        try {
            if (!readStandardString(4).equals("ttcf")) {
                throw new DocumentException(MessageLocalization.getComposedMessage("1.is.not.a.valid.ttc.file", this.fileName));
            }
            this.rf.skipBytes(4);
            int readInt = this.rf.readInt();
            this.names = new String[readInt];
            int filePointer = (int) this.rf.getFilePointer();
            for (int i = 0; i < readInt; i++) {
                this.tables.clear();
                this.rf.seek((long) filePointer);
                this.rf.skipBytes(i * 4);
                this.directoryOffset = this.rf.readInt();
                this.rf.seek((long) this.directoryOffset);
                if (this.rf.readInt() != 65536) {
                    throw new DocumentException(MessageLocalization.getComposedMessage("1.is.not.a.valid.ttf.file", this.fileName));
                }
                int readUnsignedShort = this.rf.readUnsignedShort();
                this.rf.skipBytes(6);
                for (int i2 = 0; i2 < readUnsignedShort; i2++) {
                    String readStandardString = readStandardString(4);
                    this.rf.skipBytes(4);
                    this.tables.put(readStandardString, new int[]{this.rf.readInt(), this.rf.readInt()});
                }
                this.names[i] = getBaseFont();
            }
            if (this.rf != null) {
                this.rf.close();
            }
        } catch (Throwable th) {
            if (this.rf != null) {
                this.rf.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public String[] getNames() {
        return this.names;
    }
}
