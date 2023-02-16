package apps.shehryar.com.cricketscroingappPro.Utility_Classes;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.FileProvider;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import java.io.File;

public class FileSharer {
    public static void shareMatch(Activity activity, Match match) {
        File writeObjectToFile = ObjectWriterAndReader.writeObjectToFile(match);
        if (writeObjectToFile != null) {
            shareFile(activity, writeObjectToFile);
        }
    }

    public static void shareFile(Activity activity, File file) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("file/*");
        intent.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(activity, "com.CricketScorer.fileprovider", file));
        activity.startActivity(Intent.createChooser(intent, "share file with"));
    }
}
