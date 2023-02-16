package com.itextpdf.text.pdf;

import com.itextpdf.text.DocWriter;
import com.itextpdf.text.error_messages.MessageLocalization;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class ByteBuffer extends OutputStream {
    public static boolean HIGH_PRECISION = false;
    public static final byte ZERO = 48;
    private static byte[][] byteCache = new byte[byteCacheSize][];
    private static int byteCacheSize;
    private static final byte[] bytes = {ZERO, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
    private static final char[] chars = {'0', '1', PdfWriter.VERSION_1_2, PdfWriter.VERSION_1_3, PdfWriter.VERSION_1_4, PdfWriter.VERSION_1_5, PdfWriter.VERSION_1_6, PdfWriter.VERSION_1_7, '8', '9'};
    private static final DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.US);
    protected byte[] buf;
    protected int count;

    public ByteBuffer() {
        this(128);
    }

    public ByteBuffer(int i) {
        this.buf = new byte[(i < 1 ? 128 : i)];
    }

    public static void setCacheSize(int i) {
        if (i > 3276700) {
            i = 3276700;
        }
        if (i > byteCacheSize) {
            byte[][] bArr = new byte[i][];
            System.arraycopy(byteCache, 0, bArr, 0, byteCacheSize);
            byteCache = bArr;
            byteCacheSize = i;
        }
    }

    public static void fillCache(int i) {
        int i2;
        switch (i) {
            case 0:
                i2 = 100;
                break;
            case 1:
                i2 = 10;
                break;
            default:
                i2 = 1;
                break;
        }
        for (int i3 = 1; i3 < byteCacheSize; i3 += i2) {
            if (byteCache[i3] == null) {
                byteCache[i3] = convertToBytes(i3);
            }
        }
    }

    private static byte[] convertToBytes(int i) {
        double d = (double) i;
        int floor = (int) Math.floor(Math.log(d) / Math.log(10.0d));
        int i2 = i % 100;
        if (i2 != 0) {
            floor += 2;
        }
        int i3 = i % 10;
        if (i3 != 0) {
            floor++;
        }
        if (i < 100) {
            floor++;
            if (i < 10) {
                floor++;
            }
        }
        int i4 = floor - 1;
        byte[] bArr = new byte[i4];
        int i5 = i4 - 1;
        if (i < 100) {
            bArr[0] = ZERO;
        }
        if (i3 != 0) {
            bArr[i5] = bytes[i3];
            i5--;
        }
        if (i2 != 0) {
            bArr[i5] = bytes[(i / 10) % 10];
            bArr[i5 - 1] = 46;
        }
        int floor2 = ((int) Math.floor(Math.log(d) / Math.log(10.0d))) - 1;
        for (int i6 = 0; i6 < floor2; i6++) {
            bArr[i6] = bytes[(i / ((int) Math.pow(10.0d, (double) ((floor2 - i6) + 1)))) % 10];
        }
        return bArr;
    }

    public ByteBuffer append_i(int i) {
        int i2 = this.count + 1;
        if (i2 > this.buf.length) {
            byte[] bArr = new byte[Math.max(this.buf.length << 1, i2)];
            System.arraycopy(this.buf, 0, bArr, 0, this.count);
            this.buf = bArr;
        }
        this.buf[this.count] = (byte) i;
        this.count = i2;
        return this;
    }

    public ByteBuffer append(byte[] bArr, int i, int i2) {
        int i3;
        if (i < 0 || i > bArr.length || i2 < 0 || (i3 = i + i2) > bArr.length || i3 < 0 || i2 == 0) {
            return this;
        }
        int i4 = this.count + i2;
        if (i4 > this.buf.length) {
            byte[] bArr2 = new byte[Math.max(this.buf.length << 1, i4)];
            System.arraycopy(this.buf, 0, bArr2, 0, this.count);
            this.buf = bArr2;
        }
        System.arraycopy(bArr, i, this.buf, this.count, i2);
        this.count = i4;
        return this;
    }

    public ByteBuffer append(byte[] bArr) {
        return append(bArr, 0, bArr.length);
    }

    public ByteBuffer append(String str) {
        return str != null ? append(DocWriter.getISOBytes(str)) : this;
    }

    public ByteBuffer append(char c) {
        return append_i(c);
    }

    public ByteBuffer append(ByteBuffer byteBuffer) {
        return append(byteBuffer.buf, 0, byteBuffer.count);
    }

    public ByteBuffer append(int i) {
        return append((double) i);
    }

    public ByteBuffer append(long j) {
        return append(Long.toString(j));
    }

    public ByteBuffer append(byte b) {
        return append_i(b);
    }

    public ByteBuffer appendHex(byte b) {
        append(bytes[(b >> 4) & 15]);
        return append(bytes[b & BidiOrder.B]);
    }

    public ByteBuffer append(float f) {
        return append((double) f);
    }

    public ByteBuffer append(double d) {
        append(formatDouble(d, this));
        return this;
    }

    public static String formatDouble(double d) {
        return formatDouble(d, (ByteBuffer) null);
    }

    public static String formatDouble(double d, ByteBuffer byteBuffer) {
        boolean z;
        int i;
        double d2 = d;
        ByteBuffer byteBuffer2 = byteBuffer;
        if (HIGH_PRECISION) {
            String format = new DecimalFormat("0.######", dfs).format(d2);
            if (byteBuffer2 == null) {
                return format;
            }
            byteBuffer2.append(format);
            return null;
        } else if (Math.abs(d) >= 1.5E-5d) {
            int i2 = 0;
            if (d2 < 0.0d) {
                d2 = -d2;
                z = true;
            } else {
                z = false;
            }
            int i3 = (d2 > 1.0d ? 1 : (d2 == 1.0d ? 0 : -1));
            int i4 = DefaultOggSeeker.MATCH_BYTE_RANGE;
            if (i3 < 0) {
                double d3 = d2 + 5.0E-6d;
                if (d3 >= 1.0d) {
                    if (z) {
                        if (byteBuffer2 == null) {
                            return "-1";
                        }
                        byteBuffer2.append((byte) 45);
                        byteBuffer2.append((byte) 49);
                        return null;
                    } else if (byteBuffer2 == null) {
                        return "1";
                    } else {
                        byteBuffer2.append((byte) 49);
                        return null;
                    }
                } else if (byteBuffer2 != null) {
                    int i5 = (int) (d3 * 100000.0d);
                    if (z) {
                        byteBuffer2.append((byte) 45);
                    }
                    byteBuffer2.append((byte) ZERO);
                    byteBuffer2.append((byte) 46);
                    byteBuffer2.append((byte) ((i5 / 10000) + 48));
                    if (i5 % 10000 != 0) {
                        byteBuffer2.append((byte) (((i5 / 1000) % 10) + 48));
                        if (i5 % 1000 != 0) {
                            byteBuffer2.append((byte) (((i5 / 100) % 10) + 48));
                            if (i5 % 100 != 0) {
                                byteBuffer2.append((byte) (((i5 / 10) % 10) + 48));
                                int i6 = i5 % 10;
                                if (i6 != 0) {
                                    byteBuffer2.append((byte) (i6 + 48));
                                }
                            }
                        }
                    }
                    return null;
                } else {
                    int i7 = (int) (d3 * ((double) DefaultOggSeeker.MATCH_BYTE_RANGE));
                    StringBuilder sb = new StringBuilder();
                    if (z) {
                        sb.append('-');
                    }
                    sb.append("0.");
                    while (true) {
                        i4 /= 10;
                        if (i7 >= i4) {
                            break;
                        }
                        sb.append('0');
                    }
                    sb.append(i7);
                    int length = sb.length() - 1;
                    while (sb.charAt(length) == '0') {
                        length--;
                    }
                    sb.setLength(length + 1);
                    return sb.toString();
                }
            } else if (d2 <= 32767.0d) {
                int i8 = (int) ((d2 + 0.005d) * 100.0d);
                if (i8 >= byteCacheSize || byteCache[i8] == null) {
                    if (byteBuffer2 != null) {
                        if (i8 < byteCacheSize) {
                            int i9 = i8 >= 1000000 ? 5 : i8 >= 100000 ? 4 : i8 >= 10000 ? 3 : i8 >= 1000 ? 2 : i8 >= 100 ? 1 : 0;
                            int i10 = i8 % 100;
                            if (i10 != 0) {
                                i9 += 2;
                            }
                            int i11 = i8 % 10;
                            if (i11 != 0) {
                                i9++;
                            }
                            byte[] bArr = new byte[i9];
                            if (i8 >= 1000000) {
                                bArr[0] = bytes[i8 / 1000000];
                                i2 = 1;
                            }
                            if (i8 >= 100000) {
                                bArr[i2] = bytes[(i8 / DefaultOggSeeker.MATCH_BYTE_RANGE) % 10];
                                i2++;
                            }
                            if (i8 >= 10000) {
                                bArr[i2] = bytes[(i8 / 10000) % 10];
                                i2++;
                            }
                            if (i8 >= 1000) {
                                i = i2 + 1;
                                bArr[i2] = bytes[(i8 / 1000) % 10];
                            } else {
                                i = i2;
                            }
                            if (i8 >= 100) {
                                bArr[i] = bytes[(i8 / 100) % 10];
                                i++;
                            }
                            if (i10 != 0) {
                                int i12 = i + 1;
                                bArr[i] = 46;
                                int i13 = i12 + 1;
                                bArr[i12] = bytes[(i8 / 10) % 10];
                                if (i11 != 0) {
                                    bArr[i13] = bytes[i11];
                                }
                            }
                            byteCache[i8] = bArr;
                        }
                        if (z) {
                            byteBuffer2.append((byte) 45);
                        }
                        if (i8 >= 1000000) {
                            byteBuffer2.append(bytes[i8 / 1000000]);
                        }
                        if (i8 >= 100000) {
                            byteBuffer2.append(bytes[(i8 / DefaultOggSeeker.MATCH_BYTE_RANGE) % 10]);
                        }
                        if (i8 >= 10000) {
                            byteBuffer2.append(bytes[(i8 / 10000) % 10]);
                        }
                        if (i8 >= 1000) {
                            byteBuffer2.append(bytes[(i8 / 1000) % 10]);
                        }
                        if (i8 >= 100) {
                            byteBuffer2.append(bytes[(i8 / 100) % 10]);
                        }
                        if (i8 % 100 == 0) {
                            return null;
                        }
                        byteBuffer2.append((byte) 46);
                        byteBuffer2.append(bytes[(i8 / 10) % 10]);
                        int i14 = i8 % 10;
                        if (i14 == 0) {
                            return null;
                        }
                        byteBuffer2.append(bytes[i14]);
                        return null;
                    }
                    StringBuilder sb2 = new StringBuilder();
                    if (z) {
                        sb2.append('-');
                    }
                    if (i8 >= 1000000) {
                        sb2.append(chars[i8 / 1000000]);
                    }
                    if (i8 >= 100000) {
                        sb2.append(chars[(i8 / DefaultOggSeeker.MATCH_BYTE_RANGE) % 10]);
                    }
                    if (i8 >= 10000) {
                        sb2.append(chars[(i8 / 10000) % 10]);
                    }
                    if (i8 >= 1000) {
                        sb2.append(chars[(i8 / 1000) % 10]);
                    }
                    if (i8 >= 100) {
                        sb2.append(chars[(i8 / 100) % 10]);
                    }
                    if (i8 % 100 != 0) {
                        sb2.append('.');
                        sb2.append(chars[(i8 / 10) % 10]);
                        int i15 = i8 % 10;
                        if (i15 != 0) {
                            sb2.append(chars[i15]);
                        }
                    }
                    return sb2.toString();
                } else if (byteBuffer2 != null) {
                    if (z) {
                        byteBuffer2.append((byte) 45);
                    }
                    byteBuffer2.append(byteCache[i8]);
                    return null;
                } else {
                    String convertToString = PdfEncodings.convertToString(byteCache[i8], (String) null);
                    if (!z) {
                        return convertToString;
                    }
                    return "-" + convertToString;
                }
            } else {
                long j = (long) (d2 + 0.5d);
                if (!z) {
                    return Long.toString(j);
                }
                return "-" + Long.toString(j);
            }
        } else if (byteBuffer2 == null) {
            return "0";
        } else {
            byteBuffer2.append((byte) ZERO);
            return null;
        }
    }

    public void reset() {
        this.count = 0;
    }

    public byte[] toByteArray() {
        byte[] bArr = new byte[this.count];
        System.arraycopy(this.buf, 0, bArr, 0, this.count);
        return bArr;
    }

    public int size() {
        return this.count;
    }

    public void setSize(int i) {
        if (i > this.count || i < 0) {
            throw new IndexOutOfBoundsException(MessageLocalization.getComposedMessage("the.new.size.must.be.positive.and.lt.eq.of.the.current.size", new Object[0]));
        }
        this.count = i;
    }

    public String toString() {
        return new String(this.buf, 0, this.count);
    }

    public String toString(String str) throws UnsupportedEncodingException {
        return new String(this.buf, 0, this.count, str);
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        outputStream.write(this.buf, 0, this.count);
    }

    public void write(int i) throws IOException {
        append((byte) i);
    }

    public void write(byte[] bArr, int i, int i2) {
        append(bArr, i, i2);
    }

    public byte[] getBuffer() {
        return this.buf;
    }
}
