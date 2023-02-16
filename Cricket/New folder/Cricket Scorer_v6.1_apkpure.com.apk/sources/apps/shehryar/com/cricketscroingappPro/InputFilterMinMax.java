package apps.shehryar.com.cricketscroingappPro;

import android.text.InputFilter;
import android.text.Spanned;

public class InputFilterMinMax implements InputFilter {
    private int max;
    private int min;

    private boolean isInRange(int i, int i2, int i3) {
        if (i2 > i) {
            if (i3 < i || i3 > i2) {
                return false;
            }
        } else if (i3 < i2 || i3 > i) {
            return false;
        }
        return true;
    }

    public InputFilterMinMax(int i, int i2) {
        this.min = i;
        this.max = i2;
    }

    public InputFilterMinMax(String str, String str2) {
        this.min = Integer.parseInt(str);
        this.max = Integer.parseInt(str2);
    }

    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        try {
            if (isInRange(this.min, this.max, Integer.parseInt(spanned.toString() + charSequence.toString()))) {
                return null;
            }
            return "";
        } catch (NumberFormatException unused) {
            return "";
        }
    }
}
