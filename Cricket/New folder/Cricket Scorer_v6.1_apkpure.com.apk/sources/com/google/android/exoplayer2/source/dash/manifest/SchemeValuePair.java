package com.google.android.exoplayer2.source.dash.manifest;

import com.google.android.exoplayer2.util.Util;

public class SchemeValuePair {
    public final String schemeIdUri;
    public final String value;

    public SchemeValuePair(String str, String str2) {
        this.schemeIdUri = str;
        this.value = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SchemeValuePair schemeValuePair = (SchemeValuePair) obj;
        if (!Util.areEqual(this.schemeIdUri, schemeValuePair.schemeIdUri) || !Util.areEqual(this.value, schemeValuePair.value)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = 31 * (this.schemeIdUri != null ? this.schemeIdUri.hashCode() : 0);
        if (this.value != null) {
            i = this.value.hashCode();
        }
        return hashCode + i;
    }
}
