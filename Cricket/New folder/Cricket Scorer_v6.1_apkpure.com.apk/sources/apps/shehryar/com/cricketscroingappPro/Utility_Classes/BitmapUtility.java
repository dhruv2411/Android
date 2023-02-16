package apps.shehryar.com.cricketscroingappPro.Utility_Classes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import java.io.ByteArrayOutputStream;

public class BitmapUtility {
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static Bitmap getImage(byte[] bArr) {
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    public static Bitmap getCapturedImageBitmap(Intent intent) throws Exception {
        Bitmap bitmap = (Bitmap) intent.getExtras().get("data");
        try {
            return Bitmap.createScaledBitmap(bitmap, getBitmapWidth(bitmap), getBitmapHeight(bitmap), false);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap getSelectImageFromGallery(Intent intent, Context context) throws Exception {
        if (intent != null) {
            try {
                Bitmap decodeStream = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(intent.getData()));
                return Bitmap.createScaledBitmap(decodeStream, getBitmapWidth(decodeStream), getBitmapHeight(decodeStream), false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static int getBitmapWidth(Bitmap bitmap) {
        if (bitmap == null) {
            return 0;
        }
        if (bitmap.getWidth() <= 1000 || bitmap.getHeight() <= 1000) {
            return bitmap.getWidth();
        }
        return bitmap.getWidth() / 3;
    }

    private static int getBitmapHeight(Bitmap bitmap) {
        if (bitmap == null) {
            return 0;
        }
        if (bitmap.getHeight() <= 1000 || bitmap.getWidth() <= 1000) {
            return bitmap.getHeight();
        }
        return bitmap.getHeight() / 3;
    }

    public static Uri getImageUri(Context context, Bitmap bitmap) {
        String str;
        try {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new ByteArrayOutputStream());
            str = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Title", (String) null);
        } catch (Exception e) {
            e.printStackTrace();
            str = " ";
        }
        try {
            return Uri.parse(str);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
