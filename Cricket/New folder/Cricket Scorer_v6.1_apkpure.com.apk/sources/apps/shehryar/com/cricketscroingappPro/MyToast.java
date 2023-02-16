package apps.shehryar.com.cricketscroingappPro;

import android.content.Context;
import android.widget.Toast;

public class MyToast {
    public static void showToast(Context context, String str) {
        if (context != null && str != null) {
            Toast.makeText(context, str + "", 0).show();
        }
    }

    public static void showLongToast(Context context, String str) {
        if (context != null && str != null) {
            Toast.makeText(context, str + "", 1).show();
        }
    }
}
