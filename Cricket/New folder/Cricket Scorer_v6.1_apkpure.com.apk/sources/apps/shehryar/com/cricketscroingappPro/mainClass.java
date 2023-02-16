package apps.shehryar.com.cricketscroingappPro;

public class mainClass {
    static void main(String str) {
    }

    /* access modifiers changed from: package-private */
    public int add(int i, int i2) {
        return i + i2;
    }

    /* access modifiers changed from: package-private */
    public int mult(int i, int i2) {
        return i * i2;
    }

    /* access modifiers changed from: package-private */
    public int sub(int i, int i2) {
        return i - i2;
    }

    /* access modifiers changed from: package-private */
    public float div(int i, int i2) {
        if (i2 == 0) {
            return 0.0f;
        }
        return (float) (i / i2);
    }
}
