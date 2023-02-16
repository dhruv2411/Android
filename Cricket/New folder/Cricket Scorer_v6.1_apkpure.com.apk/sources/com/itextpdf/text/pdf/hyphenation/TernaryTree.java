package com.itextpdf.text.pdf.hyphenation;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.Stack;

public class TernaryTree implements Cloneable, Serializable {
    protected static final int BLOCK_SIZE = 2048;
    private static final long serialVersionUID = 5313366505322983510L;
    protected char[] eq;
    protected char freenode;
    protected char[] hi;
    protected CharVector kv;
    protected int length;
    protected char[] lo;
    protected char root;
    protected char[] sc;

    TernaryTree() {
        init();
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.root = 0;
        this.freenode = 1;
        this.length = 0;
        this.lo = new char[2048];
        this.hi = new char[2048];
        this.eq = new char[2048];
        this.sc = new char[2048];
        this.kv = new CharVector();
    }

    public void insert(String str, char c) {
        int length2 = str.length() + 1;
        if (this.freenode + length2 > this.eq.length) {
            redimNodeArrays(this.eq.length + 2048);
        }
        int i = length2 - 1;
        char[] cArr = new char[length2];
        str.getChars(0, i, cArr, 0);
        cArr[i] = 0;
        this.root = insert(this.root, cArr, 0, c);
    }

    public void insert(char[] cArr, int i, char c) {
        if (this.freenode + strlen(cArr) + 1 > this.eq.length) {
            redimNodeArrays(this.eq.length + 2048);
        }
        this.root = insert(this.root, cArr, i, c);
    }

    private char insert(char c, char[] cArr, int i, char c2) {
        int strlen = strlen(cArr, i);
        if (c == 0) {
            char c3 = this.freenode;
            this.freenode = (char) (c3 + 1);
            this.eq[c3] = c2;
            this.length++;
            this.hi[c3] = 0;
            if (strlen > 0) {
                this.sc[c3] = 65535;
                this.lo[c3] = (char) this.kv.alloc(strlen + 1);
                strcpy(this.kv.getArray(), this.lo[c3], cArr, i);
            } else {
                this.sc[c3] = 0;
                this.lo[c3] = 0;
            }
            return c3;
        }
        if (this.sc[c] == 65535) {
            char c4 = this.freenode;
            this.freenode = (char) (c4 + 1);
            this.lo[c4] = this.lo[c];
            this.eq[c4] = this.eq[c];
            this.lo[c] = 0;
            if (strlen > 0) {
                this.sc[c] = this.kv.get(this.lo[c4]);
                this.eq[c] = c4;
                char[] cArr2 = this.lo;
                cArr2[c4] = (char) (cArr2[c4] + 1);
                if (this.kv.get(this.lo[c4]) == 0) {
                    this.lo[c4] = 0;
                    this.sc[c4] = 0;
                    this.hi[c4] = 0;
                } else {
                    this.sc[c4] = 65535;
                }
            } else {
                this.sc[c4] = 65535;
                this.hi[c] = c4;
                this.sc[c] = 0;
                this.eq[c] = c2;
                this.length++;
                return c;
            }
        }
        char c5 = cArr[i];
        if (c5 < this.sc[c]) {
            this.lo[c] = insert(this.lo[c], cArr, i, c2);
        } else if (c5 != this.sc[c]) {
            this.hi[c] = insert(this.hi[c], cArr, i, c2);
        } else if (c5 != 0) {
            this.eq[c] = insert(this.eq[c], cArr, i + 1, c2);
        } else {
            this.eq[c] = c2;
        }
        return c;
    }

    public static int strcmp(char[] cArr, int i, char[] cArr2, int i2) {
        while (cArr[i] == cArr2[i2]) {
            if (cArr[i] == 0) {
                return 0;
            }
            i++;
            i2++;
        }
        return cArr[i] - cArr2[i2];
    }

    public static int strcmp(String str, char[] cArr, int i) {
        int length2 = str.length();
        int i2 = 0;
        while (i2 < length2) {
            int i3 = i + i2;
            int charAt = str.charAt(i2) - cArr[i3];
            if (charAt != 0 || cArr[i3] == 0) {
                return charAt;
            }
            i2++;
        }
        int i4 = i + i2;
        if (cArr[i4] != 0) {
            return -cArr[i4];
        }
        return 0;
    }

    public static void strcpy(char[] cArr, int i, char[] cArr2, int i2) {
        while (cArr2[i2] != 0) {
            cArr[i] = cArr2[i2];
            i++;
            i2++;
        }
        cArr[i] = 0;
    }

    public static int strlen(char[] cArr, int i) {
        int i2 = 0;
        while (i < cArr.length && cArr[i] != 0) {
            i2++;
            i++;
        }
        return i2;
    }

    public static int strlen(char[] cArr) {
        return strlen(cArr, 0);
    }

    public int find(String str) {
        int length2 = str.length();
        char[] cArr = new char[(length2 + 1)];
        str.getChars(0, length2, cArr, 0);
        cArr[length2] = 0;
        return find(cArr, 0);
    }

    public int find(char[] cArr, int i) {
        char c = this.root;
        while (c != 0) {
            if (this.sc[c] != 65535) {
                char c2 = cArr[i];
                int i2 = c2 - this.sc[c];
                if (i2 == 0) {
                    if (c2 == 0) {
                        return this.eq[c];
                    }
                    i++;
                    c = this.eq[c];
                } else if (i2 < 0) {
                    c = this.lo[c];
                } else {
                    c = this.hi[c];
                }
            } else if (strcmp(cArr, i, this.kv.getArray(), this.lo[c]) == 0) {
                return this.eq[c];
            } else {
                return -1;
            }
        }
        return -1;
    }

    public boolean knows(String str) {
        return find(str) >= 0;
    }

    private void redimNodeArrays(int i) {
        int length2 = i < this.lo.length ? i : this.lo.length;
        char[] cArr = new char[i];
        System.arraycopy(this.lo, 0, cArr, 0, length2);
        this.lo = cArr;
        char[] cArr2 = new char[i];
        System.arraycopy(this.hi, 0, cArr2, 0, length2);
        this.hi = cArr2;
        char[] cArr3 = new char[i];
        System.arraycopy(this.eq, 0, cArr3, 0, length2);
        this.eq = cArr3;
        char[] cArr4 = new char[i];
        System.arraycopy(this.sc, 0, cArr4, 0, length2);
        this.sc = cArr4;
    }

    public int size() {
        return this.length;
    }

    public Object clone() {
        TernaryTree ternaryTree = new TernaryTree();
        ternaryTree.lo = (char[]) this.lo.clone();
        ternaryTree.hi = (char[]) this.hi.clone();
        ternaryTree.eq = (char[]) this.eq.clone();
        ternaryTree.sc = (char[]) this.sc.clone();
        ternaryTree.kv = (CharVector) this.kv.clone();
        ternaryTree.root = this.root;
        ternaryTree.freenode = this.freenode;
        ternaryTree.length = this.length;
        return ternaryTree;
    }

    /* access modifiers changed from: protected */
    public void insertBalanced(String[] strArr, char[] cArr, int i, int i2) {
        if (i2 >= 1) {
            int i3 = i2 >> 1;
            int i4 = i3 + i;
            insert(strArr[i4], cArr[i4]);
            insertBalanced(strArr, cArr, i, i3);
            insertBalanced(strArr, cArr, i4 + 1, (i2 - i3) - 1);
        }
    }

    public void balance() {
        int i = this.length;
        String[] strArr = new String[i];
        char[] cArr = new char[i];
        Iterator iterator = new Iterator();
        int i2 = 0;
        while (iterator.hasMoreElements()) {
            cArr[i2] = iterator.getValue();
            strArr[i2] = iterator.nextElement();
            i2++;
        }
        init();
        insertBalanced(strArr, cArr, 0, i);
    }

    public void trimToSize() {
        balance();
        redimNodeArrays(this.freenode);
        CharVector charVector = new CharVector();
        charVector.alloc(1);
        compact(charVector, new TernaryTree(), this.root);
        this.kv = charVector;
        this.kv.trimToSize();
    }

    private void compact(CharVector charVector, TernaryTree ternaryTree, char c) {
        if (c != 0) {
            if (this.sc[c] == 65535) {
                int find = ternaryTree.find(this.kv.getArray(), this.lo[c]);
                if (find < 0) {
                    find = charVector.alloc(strlen(this.kv.getArray(), this.lo[c]) + 1);
                    strcpy(charVector.getArray(), find, this.kv.getArray(), this.lo[c]);
                    ternaryTree.insert(charVector.getArray(), find, (char) find);
                }
                this.lo[c] = (char) find;
                return;
            }
            compact(charVector, ternaryTree, this.lo[c]);
            if (this.sc[c] != 0) {
                compact(charVector, ternaryTree, this.eq[c]);
            }
            compact(charVector, ternaryTree, this.hi[c]);
        }
    }

    public Enumeration<String> keys() {
        return new Iterator();
    }

    public class Iterator implements Enumeration<String> {
        int cur = -1;
        String curkey;
        StringBuffer ks = new StringBuffer();
        Stack<Item> ns = new Stack<>();

        private class Item implements Cloneable {
            char child;
            char parent;

            public Item() {
                this.parent = 0;
                this.child = 0;
            }

            public Item(char c, char c2) {
                this.parent = c;
                this.child = c2;
            }

            public Item clone() {
                return new Item(this.parent, this.child);
            }
        }

        public Iterator() {
            rewind();
        }

        public void rewind() {
            this.ns.removeAllElements();
            this.ks.setLength(0);
            this.cur = TernaryTree.this.root;
            run();
        }

        public String nextElement() {
            String str = this.curkey;
            this.cur = up();
            run();
            return str;
        }

        public char getValue() {
            if (this.cur >= 0) {
                return TernaryTree.this.eq[this.cur];
            }
            return 0;
        }

        public boolean hasMoreElements() {
            return this.cur != -1;
        }

        private int up() {
            new Item();
            if (this.ns.empty()) {
                return -1;
            }
            if (this.cur != 0 && TernaryTree.this.sc[this.cur] == 0) {
                return TernaryTree.this.lo[this.cur];
            }
            char c = 0;
            do {
                for (boolean z = true; z; z = false) {
                    Item pop = this.ns.pop();
                    pop.child = (char) (pop.child + 1);
                    switch (pop.child) {
                        case 1:
                            if (TernaryTree.this.sc[pop.parent] == 0) {
                                pop.child = (char) (pop.child + 1);
                                this.ns.push(pop.clone());
                                c = TernaryTree.this.hi[pop.parent];
                                break;
                            } else {
                                c = TernaryTree.this.eq[pop.parent];
                                this.ns.push(pop.clone());
                                this.ks.append(TernaryTree.this.sc[pop.parent]);
                                break;
                            }
                        case 2:
                            c = TernaryTree.this.hi[pop.parent];
                            this.ns.push(pop.clone());
                            if (this.ks.length() <= 0) {
                                break;
                            } else {
                                this.ks.setLength(this.ks.length() - 1);
                                break;
                            }
                    }
                }
                return c;
            } while (!this.ns.empty());
            return -1;
        }

        /* JADX WARNING: type inference failed for: r4v1, types: [int] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private int run() {
            /*
                r8 = this;
                int r0 = r8.cur
                r1 = -1
                if (r0 != r1) goto L_0x0006
                return r1
            L_0x0006:
                r0 = 0
                r2 = r0
            L_0x0008:
                int r3 = r8.cur
                r4 = 65535(0xffff, float:9.1834E-41)
                r5 = 1
                if (r3 == 0) goto L_0x003f
                com.itextpdf.text.pdf.hyphenation.TernaryTree r3 = com.itextpdf.text.pdf.hyphenation.TernaryTree.this
                char[] r3 = r3.sc
                int r6 = r8.cur
                char r3 = r3[r6]
                if (r3 != r4) goto L_0x001c
            L_0x001a:
                r2 = r5
                goto L_0x003f
            L_0x001c:
                java.util.Stack<com.itextpdf.text.pdf.hyphenation.TernaryTree$Iterator$Item> r3 = r8.ns
                com.itextpdf.text.pdf.hyphenation.TernaryTree$Iterator$Item r6 = new com.itextpdf.text.pdf.hyphenation.TernaryTree$Iterator$Item
                int r7 = r8.cur
                char r7 = (char) r7
                r6.<init>(r7, r0)
                r3.push(r6)
                com.itextpdf.text.pdf.hyphenation.TernaryTree r3 = com.itextpdf.text.pdf.hyphenation.TernaryTree.this
                char[] r3 = r3.sc
                int r6 = r8.cur
                char r3 = r3[r6]
                if (r3 != 0) goto L_0x0034
                goto L_0x001a
            L_0x0034:
                com.itextpdf.text.pdf.hyphenation.TernaryTree r3 = com.itextpdf.text.pdf.hyphenation.TernaryTree.this
                char[] r3 = r3.lo
                int r4 = r8.cur
                char r3 = r3[r4]
                r8.cur = r3
                goto L_0x0008
            L_0x003f:
                if (r2 == 0) goto L_0x007e
                java.lang.StringBuffer r1 = new java.lang.StringBuffer
                java.lang.StringBuffer r2 = r8.ks
                java.lang.String r2 = r2.toString()
                r1.<init>(r2)
                com.itextpdf.text.pdf.hyphenation.TernaryTree r2 = com.itextpdf.text.pdf.hyphenation.TernaryTree.this
                char[] r2 = r2.sc
                int r3 = r8.cur
                char r2 = r2[r3]
                if (r2 != r4) goto L_0x0077
                com.itextpdf.text.pdf.hyphenation.TernaryTree r2 = com.itextpdf.text.pdf.hyphenation.TernaryTree.this
                char[] r2 = r2.lo
                int r3 = r8.cur
                char r2 = r2[r3]
            L_0x005e:
                com.itextpdf.text.pdf.hyphenation.TernaryTree r3 = com.itextpdf.text.pdf.hyphenation.TernaryTree.this
                com.itextpdf.text.pdf.hyphenation.CharVector r3 = r3.kv
                char r3 = r3.get(r2)
                if (r3 == 0) goto L_0x0077
                com.itextpdf.text.pdf.hyphenation.TernaryTree r3 = com.itextpdf.text.pdf.hyphenation.TernaryTree.this
                com.itextpdf.text.pdf.hyphenation.CharVector r3 = r3.kv
                int r4 = r2 + 1
                char r2 = r3.get(r2)
                r1.append(r2)
                r2 = r4
                goto L_0x005e
            L_0x0077:
                java.lang.String r1 = r1.toString()
                r8.curkey = r1
                return r0
            L_0x007e:
                int r3 = r8.up()
                r8.cur = r3
                int r3 = r8.cur
                if (r3 != r1) goto L_0x0008
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.itextpdf.text.pdf.hyphenation.TernaryTree.Iterator.run():int");
        }
    }

    public void printStats() {
        PrintStream printStream = System.out;
        printStream.println("Number of keys = " + Integer.toString(this.length));
        PrintStream printStream2 = System.out;
        printStream2.println("Node count = " + Integer.toString(this.freenode));
        PrintStream printStream3 = System.out;
        printStream3.println("Key Array length = " + Integer.toString(this.kv.length()));
    }
}
