package apps.shehryar.com.cricketscroingappPro.BackupRestore;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;
import apps.shehryar.com.cricketscroingappPro.MyToast;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.SharedPrefsHelper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

public class BackupAndRestore {
    public static void importDB(Context context) {
        try {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            File dataDirectory = Environment.getDataDirectory();
            if (externalStorageDirectory.canWrite()) {
                File file = new File(dataDirectory, "//data/apps.shehryar.com.cricketscroingapp/databases/cricketscores");
                File file2 = new File(externalStorageDirectory, "//Cricket Scorer/Backup/cricketscores");
                if (file.exists()) {
                    FileChannel channel = new FileInputStream(file2).getChannel();
                    FileChannel channel2 = new FileOutputStream(file).getChannel();
                    channel2.transferFrom(channel, 0, channel.size());
                    channel.close();
                    channel2.close();
                    importSharedPrefs(context);
                    importOverSharedPrefs(context);
                }
            }
        } catch (Exception unused) {
            MyToast.showToast(context, "No Backup found.");
        }
    }

    public static void importSharedPrefs(Context context) {
        try {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            File dataDirectory = Environment.getDataDirectory();
            if (externalStorageDirectory.canWrite()) {
                File file = new File(dataDirectory, "//data/apps.shehryar.com.cricketscroingapp/shared_prefs/team.xml");
                File file2 = new File(externalStorageDirectory, "//Cricket Scorer/Backup/team.xml");
                if (file.exists()) {
                    FileChannel channel = new FileInputStream(file2).getChannel();
                    FileChannel channel2 = new FileOutputStream(file).getChannel();
                    channel2.transferFrom(channel, 0, channel.size());
                    channel.close();
                    channel2.close();
                    Toast.makeText(context, "Database Restored successfully", 0).show();
                }
            }
        } catch (Exception unused) {
        }
    }

    public static void importOverSharedPrefs(Context context) {
        try {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            File dataDirectory = Environment.getDataDirectory();
            if (externalStorageDirectory.canWrite()) {
                File file = new File(dataDirectory, "//data/apps.shehryar.com.cricketscroingapp/shared_prefs/overs.xml");
                File file2 = new File(externalStorageDirectory, "//Cricket Scorer/Backup/overs.xml");
                SharedPrefsHelper.insertOversString(context, 0, 0, new ArrayList());
                if (file2.exists()) {
                    FileChannel channel = new FileInputStream(file2).getChannel();
                    FileChannel channel2 = new FileOutputStream(file).getChannel();
                    channel2.transferFrom(channel, 0, channel.size());
                    channel.close();
                    channel2.close();
                }
            }
        } catch (Exception unused) {
        }
    }

    public static void exportDB(Context context) {
        try {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            File dataDirectory = Environment.getDataDirectory();
            if (externalStorageDirectory.canWrite()) {
                File file = new File(dataDirectory, "//data//apps.shehryar.com.cricketscroingapp//databases//cricketscores");
                File file2 = new File(externalStorageDirectory, "/Cricket Scorer/Backup/cricketscores");
                File file3 = new File(Environment.getExternalStorageDirectory() + "/Cricket Scorer/Backup");
                if (!file3.exists()) {
                    file3.mkdirs();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                FileChannel channel = new FileInputStream(file).getChannel();
                FileChannel channel2 = fileOutputStream.getChannel();
                channel2.transferFrom(channel, 0, channel.size());
                channel.close();
                channel2.close();
                exportSharedPrefs(context);
                exportOverSharedPrefs(context);
            }
        } catch (Exception e) {
            Log.e("backup exception", e.toString());
            Toast.makeText(context, "Backup Failed", 0).show();
        }
    }

    public static void exportSharedPrefs(Context context) {
        try {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            File dataDirectory = Environment.getDataDirectory();
            if (externalStorageDirectory.canWrite()) {
                File file = new File(dataDirectory, "//data//apps.shehryar.com.cricketscroingapp//shared_prefs//team.xml");
                File file2 = new File(externalStorageDirectory, "/Cricket Scorer/Backup/team.xml");
                File file3 = new File(Environment.getExternalStorageDirectory() + "/Cricket Scorer/Backup");
                if (!file3.exists()) {
                    file3.mkdirs();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                FileChannel channel = new FileInputStream(file).getChannel();
                FileChannel channel2 = fileOutputStream.getChannel();
                channel2.transferFrom(channel, 0, channel.size());
                channel.close();
                channel2.close();
                Toast.makeText(context, "Backup Successful!", 0).show();
            }
        } catch (Exception e) {
            Log.e("backup exception", e.toString());
            Toast.makeText(context, "Backup Failed", 0).show();
        }
    }

    public static void exportOverSharedPrefs(Context context) {
        try {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            File dataDirectory = Environment.getDataDirectory();
            if (externalStorageDirectory.canWrite()) {
                File file = new File(dataDirectory, "//data//apps.shehryar.com.cricketscroingapp//shared_prefs//overs.xml");
                File file2 = new File(externalStorageDirectory, "/Cricket Scorer/Backup/overs.xml");
                File file3 = new File(Environment.getExternalStorageDirectory() + "/Cricket Scorer/Backup");
                if (!file3.exists()) {
                    file3.mkdirs();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                FileChannel channel = new FileInputStream(file).getChannel();
                FileChannel channel2 = fileOutputStream.getChannel();
                channel2.transferFrom(channel, 0, channel.size());
                channel.close();
                channel2.close();
                Toast.makeText(context, "Backup Successful!", 0).show();
            }
        } catch (Exception e) {
            Log.e("backup exception", e.toString());
            Toast.makeText(context, "Backup Failed", 0).show();
        }
    }
}
