package apps.shehryar.com.cricketscroingappPro.Utility_Classes;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.RequiresApi;

public class AppSharerAndRater {
    public static void openAsadGameDialog(Activity activity) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.gamesgalleria.realworldsoccerworldcup"));
        intent.addFlags(1275592704);
        try {
            activity.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.gamesgalleria.realworldsoccerworldcup")));
        }
    }

    public static void rateThisApp(Context context, Activity activity) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + context.getApplicationContext().getPackageName()));
        intent.addFlags(1275592704);
        try {
            activity.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }

    @RequiresApi(api = 19)
    public static void shareThisApp(Activity activity) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", "Hey, If you love to play Cricket and tired of scoring on your old traditional scorebook or your current scoring app, Check out this app on Play Store. It is easy to use, featuristic and its free. " + System.lineSeparator() + "https://play.google.com/store/apps/details?id=apps.shehryar.com.cricketscroingapp");
        activity.startActivity(Intent.createChooser(intent, "Share this App"));
    }

    public static void shareCurrentScore(String str, Activity activity) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", str);
        activity.startActivity(Intent.createChooser(intent, "Share score"));
    }
}
