package apps.shehryar.com.cricketscroingappPro.Utility_Classes;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

public class ObjectWriterAndReader {
    public static File writeObjectToFile(Match match) {
        File file = new File(Environment.getExternalStorageDirectory().toString() + "/Cricket Scorer/Matches/");
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(file, Formatter.replaceForwardSlashWithBackSlash(match.getTeam1().getName()) + " VS " + Formatter.replaceForwardSlashWithBackSlash(match.getTeam2().getName()) + " " + match.getDate() + ".srl");
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file2));
            objectOutputStream.writeObject(match);
            objectOutputStream.close();
            Log.i("file write", "completed");
            return file2;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.i("file write", "Exception " + e.toString());
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            Log.i("file write", "Exception " + e2.toString());
            return null;
        }
    }

    public static Match readObjectFromUri(Context context, Uri uri) {
        Match match;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(context.getContentResolver().openInputStream(uri));
            match = (Match) objectInputStream.readObject();
            try {
                objectInputStream.close();
            } catch (StreamCorruptedException e) {
                e = e;
            } catch (FileNotFoundException e2) {
                e = e2;
                e.printStackTrace();
                return match;
            } catch (IOException e3) {
                e = e3;
                e.printStackTrace();
                return match;
            } catch (ClassNotFoundException e4) {
                e = e4;
                e.printStackTrace();
                return match;
            }
        } catch (StreamCorruptedException e5) {
            e = e5;
            match = null;
            e.printStackTrace();
            return match;
        } catch (FileNotFoundException e6) {
            e = e6;
            match = null;
            e.printStackTrace();
            return match;
        } catch (IOException e7) {
            e = e7;
            match = null;
            e.printStackTrace();
            return match;
        } catch (ClassNotFoundException e8) {
            e = e8;
            match = null;
            e.printStackTrace();
            return match;
        }
        return match;
    }

    public static Match readObjectFromFile(String str) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File(str)));
            objectInputStream.close();
            return (Match) objectInputStream.readObject();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        } catch (ClassNotFoundException e4) {
            e4.printStackTrace();
        }
        return null;
    }
}
