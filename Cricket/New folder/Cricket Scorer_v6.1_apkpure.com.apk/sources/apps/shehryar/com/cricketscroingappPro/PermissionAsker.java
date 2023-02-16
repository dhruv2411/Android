package apps.shehryar.com.cricketscroingappPro;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class PermissionAsker {
    public static boolean checkAPIVersion() {
        return Build.VERSION.SDK_INT > 22;
    }

    public static boolean checkIfAlreadyhavePermission(Context context) {
        int i;
        try {
            i = ContextCompat.checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE");
        } catch (Exception e) {
            e.printStackTrace();
            i = 0;
        }
        if (i == 0) {
            return true;
        }
        return false;
    }

    public static void requestForSpecificPermission(int i, Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, i);
    }
}
