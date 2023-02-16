package com.itextpdf.text.factories;

import com.itextpdf.text.error_messages.MessageLocalization;

public class RomanAlphabetFactory {
    public static final String getString(int i) {
        if (i < 1) {
            throw new NumberFormatException(MessageLocalization.getComposedMessage("you.can.t.translate.a.negative.number.into.an.alphabetical.value", new Object[0]));
        }
        int i2 = i - 1;
        int i3 = 1;
        int i4 = 0;
        int i5 = 26;
        while (true) {
            int i6 = i5 + i4;
            if (i2 < i6) {
                break;
            }
            i3++;
            i5 *= 26;
            i4 = i6;
        }
        int i7 = i2 - i4;
        char[] cArr = new char[i3];
        while (i3 > 0) {
            i3--;
            cArr[i3] = (char) (97 + (i7 % 26));
            i7 /= 26;
        }
        return new String(cArr);
    }

    public static final String getLowerCaseString(int i) {
        return getString(i);
    }

    public static final String getUpperCaseString(int i) {
        return getString(i).toUpperCase();
    }

    public static final String getString(int i, boolean z) {
        if (z) {
            return getLowerCaseString(i);
        }
        return getUpperCaseString(i);
    }
}
