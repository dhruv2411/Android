package com.itextpdf.text.pdf;

import com.itextpdf.text.SplitCharacter;

public class DefaultSplitCharacter implements SplitCharacter {
    public static final SplitCharacter DEFAULT = new DefaultSplitCharacter();
    protected char[] characters;

    public DefaultSplitCharacter() {
    }

    public DefaultSplitCharacter(char c) {
        this(new char[]{c});
    }

    public DefaultSplitCharacter(char[] cArr) {
        this.characters = cArr;
    }

    public boolean isSplitCharacter(int i, int i2, int i3, char[] cArr, PdfChunk[] pdfChunkArr) {
        char currentCharacter = getCurrentCharacter(i2, cArr, pdfChunkArr);
        if (this.characters != null) {
            for (char c : this.characters) {
                if (currentCharacter == c) {
                    return true;
                }
            }
            return false;
        } else if (currentCharacter <= ' ' || currentCharacter == '-' || currentCharacter == 8208) {
            return true;
        } else {
            if (currentCharacter < 8194) {
                return false;
            }
            if (currentCharacter >= 8194 && currentCharacter <= 8203) {
                return true;
            }
            if (currentCharacter >= 11904 && currentCharacter < 55200) {
                return true;
            }
            if (currentCharacter >= 63744 && currentCharacter < 64256) {
                return true;
            }
            if (currentCharacter >= 65072 && currentCharacter < 65104) {
                return true;
            }
            if (currentCharacter < 65377 || currentCharacter >= 65440) {
                return false;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public char getCurrentCharacter(int i, char[] cArr, PdfChunk[] pdfChunkArr) {
        if (pdfChunkArr == null) {
            return cArr[i];
        }
        return (char) pdfChunkArr[Math.min(i, pdfChunkArr.length - 1)].getUnicodeEquivalent(cArr[i]);
    }
}
