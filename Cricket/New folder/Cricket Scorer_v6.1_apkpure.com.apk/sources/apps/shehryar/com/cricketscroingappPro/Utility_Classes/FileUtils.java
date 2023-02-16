package apps.shehryar.com.cricketscroingappPro.Utility_Classes;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import java.net.URI;
import java.net.URISyntaxException;

public class FileUtils {
    public static String getRealPathFromURI(Context context, Uri uri) {
        Cursor query = context.getContentResolver().query(uri, (String[]) null, (String) null, (String[]) null, (String) null);
        if (query == null) {
            return uri.getPath();
        }
        query.moveToFirst();
        String string = query.getString(query.getColumnIndex("_data"));
        query.close();
        return string;
    }

    public static URI getPath(Context context, Uri uri) throws URISyntaxException {
        return new URI(uri.getPath());
    }
}
