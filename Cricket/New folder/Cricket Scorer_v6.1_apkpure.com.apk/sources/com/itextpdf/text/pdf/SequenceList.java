package com.itextpdf.text.pdf;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class SequenceList {
    protected static final int COMMA = 1;
    private static final int DIGIT = 1;
    private static final int DIGIT2 = 3;
    protected static final int END = 6;
    protected static final char EOT = '￿';
    private static final int FIRST = 0;
    protected static final int MINUS = 2;
    protected static final int NOT = 3;
    private static final String NOT_OTHER = "-,!0123456789";
    protected static final int NUMBER = 5;
    private static final int OTHER = 2;
    protected static final int TEXT = 4;
    protected boolean even;
    protected int high;
    protected boolean inverse;
    protected int low;
    protected int number;
    protected boolean odd;
    protected String other;
    protected int ptr = 0;
    protected char[] text;

    protected SequenceList(String str) {
        this.text = str.toCharArray();
    }

    /* access modifiers changed from: protected */
    public char nextChar() {
        while (this.ptr < this.text.length) {
            char[] cArr = this.text;
            int i = this.ptr;
            this.ptr = i + 1;
            char c = cArr[i];
            if (c > ' ') {
                return c;
            }
        }
        return EOT;
    }

    /* access modifiers changed from: protected */
    public void putBack() {
        this.ptr--;
        if (this.ptr < 0) {
            this.ptr = 0;
        }
    }

    /* access modifiers changed from: protected */
    public int getType() {
        StringBuffer stringBuffer = new StringBuffer();
        boolean z = false;
        while (true) {
            char nextChar = nextChar();
            if (nextChar != 65535) {
                switch (z) {
                    case false:
                        if (nextChar != '!') {
                            switch (nextChar) {
                                case ',':
                                    return 1;
                                case '-':
                                    return 2;
                                default:
                                    stringBuffer.append(nextChar);
                                    if (nextChar >= '0' && nextChar <= '9') {
                                        z = true;
                                        break;
                                    } else {
                                        z = true;
                                        break;
                                    }
                            }
                        } else {
                            return 3;
                        }
                    case true:
                        if (nextChar >= '0' && nextChar <= '9') {
                            stringBuffer.append(nextChar);
                            break;
                        } else {
                            putBack();
                            String stringBuffer2 = stringBuffer.toString();
                            this.other = stringBuffer2;
                            this.number = Integer.parseInt(stringBuffer2);
                            break;
                        }
                        break;
                    case true:
                        if (NOT_OTHER.indexOf(nextChar) < 0) {
                            stringBuffer.append(nextChar);
                            break;
                        } else {
                            putBack();
                            this.other = stringBuffer.toString().toLowerCase();
                            return 4;
                        }
                }
            } else if (z) {
                String stringBuffer3 = stringBuffer.toString();
                this.other = stringBuffer3;
                this.number = Integer.parseInt(stringBuffer3);
                return 5;
            } else if (!z) {
                return 6;
            } else {
                this.other = stringBuffer.toString().toLowerCase();
                return 4;
            }
        }
        putBack();
        String stringBuffer22 = stringBuffer.toString();
        this.other = stringBuffer22;
        this.number = Integer.parseInt(stringBuffer22);
        return 5;
    }

    private void otherProc() {
        if (this.other.equals("odd") || this.other.equals("o")) {
            this.odd = true;
            this.even = false;
        } else if (this.other.equals("even") || this.other.equals("e")) {
            this.odd = false;
            this.even = true;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0058  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean getAttributes() {
        /*
            r7 = this;
            r0 = -1
            r7.low = r0
            r7.high = r0
            r0 = 0
            r7.inverse = r0
            r7.even = r0
            r7.odd = r0
            r1 = 2
        L_0x000d:
            r2 = r1
        L_0x000e:
            int r3 = r7.getType()
            r4 = 6
            r5 = 1
            if (r3 == r4) goto L_0x0056
            if (r3 != r5) goto L_0x0019
            goto L_0x0056
        L_0x0019:
            r4 = 3
            r6 = 5
            switch(r2) {
                case 1: goto L_0x0042;
                case 2: goto L_0x0030;
                case 3: goto L_0x001f;
                default: goto L_0x001e;
            }
        L_0x001e:
            goto L_0x000e
        L_0x001f:
            if (r3 == r6) goto L_0x002b
            switch(r3) {
                case 2: goto L_0x000e;
                case 3: goto L_0x0028;
                default: goto L_0x0024;
            }
        L_0x0024:
            r7.otherProc()
            goto L_0x000d
        L_0x0028:
            r7.inverse = r5
            goto L_0x000d
        L_0x002b:
            int r2 = r7.number
            r7.high = r2
            goto L_0x000d
        L_0x0030:
            switch(r3) {
                case 2: goto L_0x0054;
                case 3: goto L_0x003b;
                default: goto L_0x0033;
            }
        L_0x0033:
            if (r3 != r6) goto L_0x003e
            int r2 = r7.number
            r7.low = r2
            r2 = r5
            goto L_0x000e
        L_0x003b:
            r7.inverse = r5
            goto L_0x000e
        L_0x003e:
            r7.otherProc()
            goto L_0x000e
        L_0x0042:
            switch(r3) {
                case 2: goto L_0x0054;
                case 3: goto L_0x004d;
                default: goto L_0x0045;
            }
        L_0x0045:
            int r2 = r7.low
            r7.high = r2
            r7.otherProc()
            goto L_0x000d
        L_0x004d:
            r7.inverse = r5
            int r2 = r7.low
            r7.high = r2
            goto L_0x000d
        L_0x0054:
            r2 = r4
            goto L_0x000e
        L_0x0056:
            if (r2 != r5) goto L_0x005c
            int r1 = r7.low
            r7.high = r1
        L_0x005c:
            if (r3 != r4) goto L_0x005f
            r0 = r5
        L_0x005f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.SequenceList.getAttributes():boolean");
    }

    public static List<Integer> expand(String str, int i) {
        int i2;
        SequenceList sequenceList = new SequenceList(str);
        LinkedList linkedList = new LinkedList();
        boolean z = false;
        while (!z) {
            z = sequenceList.getAttributes();
            int i3 = -1;
            if (sequenceList.low != -1 || sequenceList.high != -1 || sequenceList.even || sequenceList.odd) {
                int i4 = 1;
                if (sequenceList.low < 1) {
                    sequenceList.low = 1;
                }
                if (sequenceList.high < 1 || sequenceList.high > i) {
                    sequenceList.high = i;
                }
                if (sequenceList.low > i) {
                    sequenceList.low = i;
                }
                if (sequenceList.inverse) {
                    if (sequenceList.low > sequenceList.high) {
                        int i5 = sequenceList.low;
                        sequenceList.low = sequenceList.high;
                        sequenceList.high = i5;
                    }
                    ListIterator listIterator = linkedList.listIterator();
                    while (listIterator.hasNext()) {
                        int intValue = ((Integer) listIterator.next()).intValue();
                        if ((!sequenceList.even || (intValue & 1) != 1) && ((!sequenceList.odd || (intValue & 1) != 0) && intValue >= sequenceList.low && intValue <= sequenceList.high)) {
                            listIterator.remove();
                        }
                    }
                } else if (sequenceList.low > sequenceList.high) {
                    if (sequenceList.odd || sequenceList.even) {
                        if (sequenceList.even) {
                            sequenceList.low &= -2;
                        } else {
                            int i6 = sequenceList.low;
                            if ((sequenceList.low & 1) == 1) {
                                i4 = 0;
                            }
                            sequenceList.low = i6 - i4;
                        }
                        i3 = -2;
                    }
                    for (int i7 = sequenceList.low; i7 >= sequenceList.high; i7 += i3) {
                        linkedList.add(Integer.valueOf(i7));
                    }
                } else {
                    if (sequenceList.odd || sequenceList.even) {
                        i2 = 2;
                        if (sequenceList.odd) {
                            sequenceList.low |= 1;
                        } else {
                            int i8 = sequenceList.low;
                            if ((sequenceList.low & 1) != 1) {
                                i4 = 0;
                            }
                            sequenceList.low = i8 + i4;
                        }
                    } else {
                        i2 = 1;
                    }
                    for (int i9 = sequenceList.low; i9 <= sequenceList.high; i9 += i2) {
                        linkedList.add(Integer.valueOf(i9));
                    }
                }
            }
        }
        return linkedList;
    }
}
