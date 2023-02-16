package apps.shehryar.com.cricketscroingappPro.Utility_Classes;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Environment;
import android.text.format.DateFormat;
import android.view.View;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

public class ScreenShotTaker {
    public static String takeScreenshot(View view) {
        Date date = new Date();
        DateFormat.format("yyyy-MM-dd_hh:mm:ss", date);
        try {
            String replace = (date + ".jpg").replace(":", "");
            File file = new File(Environment.getExternalStorageDirectory() + "/Cricket Scorer/Images/");
            if (!file.exists()) {
                file.mkdirs();
            }
            view.setDrawingCacheEnabled(true);
            Bitmap createBitmap = Bitmap.createBitmap(view.getDrawingCache());
            view.setDrawingCacheEnabled(false);
            File file2 = new File(file, replace);
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            createBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return file2.getAbsolutePath();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static String takeDialogScreenshot(Dialog dialog) {
        Date date = new Date();
        DateFormat.format("yyyy-MM-dd_hh:mm:ss", date);
        try {
            String str = Environment.getExternalStorageDirectory() + "/Cricket Scorer/Images/" + date + ".jpg";
            View rootView = dialog.getWindow().getDecorView().getRootView();
            rootView.setDrawingCacheEnabled(true);
            Bitmap createBitmap = Bitmap.createBitmap(rootView.getDrawingCache());
            rootView.setDrawingCacheEnabled(false);
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str));
            createBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return str;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }
}
