package apps.shehryar.com.cricketscroingappPro.Utility_Classes;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v4.content.FileProvider;
import java.io.File;

public class ImageSharer {
    public static void shareImage(Activity activity) {
        String takeScreenshot = ScreenShotTaker.takeScreenshot(activity.getWindow().getDecorView().getRootView());
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("image/*");
        intent.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(activity, "com.CricketScorer.fileprovider", new File(takeScreenshot)));
        activity.startActivity(Intent.createChooser(intent, "share file with"));
    }

    public static void shareDialogImage(DialogFragment dialogFragment, Activity activity) {
        String takeScreenshot = ScreenShotTaker.takeScreenshot(dialogFragment.getDialog().getWindow().getDecorView().getRootView());
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("image/*");
        intent.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(activity, "com.CricketScorer.fileprovider", new File(takeScreenshot)));
        activity.startActivity(Intent.createChooser(intent, "share file with"));
    }
}
