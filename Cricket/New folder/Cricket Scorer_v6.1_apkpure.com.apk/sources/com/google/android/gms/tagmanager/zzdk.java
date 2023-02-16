package com.google.android.gms.tagmanager;

class zzdk extends Number implements Comparable<zzdk> {
    private long zzbIA;
    private boolean zzbIB = false;
    private double zzbIz;

    private zzdk(double d) {
        this.zzbIz = d;
    }

    private zzdk(long j) {
        this.zzbIA = j;
    }

    public static zzdk zza(Double d) {
        return new zzdk(d.doubleValue());
    }

    public static zzdk zzaA(long j) {
        return new zzdk(j);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0013, code lost:
        return new com.google.android.gms.tagmanager.zzdk(java.lang.Double.parseDouble(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0023, code lost:
        throw new java.lang.NumberFormatException(java.lang.String.valueOf(r3).concat(" is not a valid TypedNumber"));
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x000a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.tagmanager.zzdk zzhv(java.lang.String r3) throws java.lang.NumberFormatException {
        /*
            com.google.android.gms.tagmanager.zzdk r0 = new com.google.android.gms.tagmanager.zzdk     // Catch:{ NumberFormatException -> 0x000a }
            long r1 = java.lang.Long.parseLong(r3)     // Catch:{ NumberFormatException -> 0x000a }
            r0.<init>((long) r1)     // Catch:{ NumberFormatException -> 0x000a }
            return r0
        L_0x000a:
            com.google.android.gms.tagmanager.zzdk r0 = new com.google.android.gms.tagmanager.zzdk     // Catch:{ NumberFormatException -> 0x0014 }
            double r1 = java.lang.Double.parseDouble(r3)     // Catch:{ NumberFormatException -> 0x0014 }
            r0.<init>((double) r1)     // Catch:{ NumberFormatException -> 0x0014 }
            return r0
        L_0x0014:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r3 = java.lang.String.valueOf(r3)
            java.lang.String r1 = " is not a valid TypedNumber"
            java.lang.String r3 = r3.concat(r1)
            r0.<init>(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzdk.zzhv(java.lang.String):com.google.android.gms.tagmanager.zzdk");
    }

    public byte byteValue() {
        return (byte) ((int) longValue());
    }

    public double doubleValue() {
        return zzRH() ? (double) this.zzbIA : this.zzbIz;
    }

    public boolean equals(Object obj) {
        return (obj instanceof zzdk) && compareTo((zzdk) obj) == 0;
    }

    public float floatValue() {
        return (float) doubleValue();
    }

    public int hashCode() {
        return new Long(longValue()).hashCode();
    }

    public int intValue() {
        return zzRJ();
    }

    public long longValue() {
        return zzRI();
    }

    public short shortValue() {
        return zzRK();
    }

    public String toString() {
        return zzRH() ? Long.toString(this.zzbIA) : Double.toString(this.zzbIz);
    }

    public boolean zzRG() {
        return !zzRH();
    }

    public boolean zzRH() {
        return this.zzbIB;
    }

    public long zzRI() {
        return zzRH() ? this.zzbIA : (long) this.zzbIz;
    }

    public int zzRJ() {
        return (int) longValue();
    }

    public short zzRK() {
        return (short) ((int) longValue());
    }

    /* renamed from: zza */
    public int compareTo(zzdk zzdk) {
        return (!zzRH() || !zzdk.zzRH()) ? Double.compare(doubleValue(), zzdk.doubleValue()) : new Long(this.zzbIA).compareTo(Long.valueOf(zzdk.zzbIA));
    }
}
