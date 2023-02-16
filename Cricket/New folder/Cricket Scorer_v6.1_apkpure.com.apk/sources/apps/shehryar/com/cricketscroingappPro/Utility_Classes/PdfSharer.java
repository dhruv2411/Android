package apps.shehryar.com.cricketscroingappPro.Utility_Classes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import apps.shehryar.com.cricketscroingappPro.PDFGenerator;
import java.io.File;
import java.io.FileNotFoundException;

public class PdfSharer {
    public static void shareTournamentPDF(PDFGenerator pDFGenerator, Context context, String str) {
        File file;
        Intent intent;
        Activity activity = (Activity) context;
        try {
            pDFGenerator.generateTournamentMatchesPDF();
            file = new File(Environment.getExternalStorageDirectory().toString() + "/" + str);
            intent = new Intent("android.intent.action.SEND");
        } catch (Exception e) {
            e.printStackTrace();
            file = new File(Environment.getExternalStorageDirectory().toString() + "/" + str);
            intent = new Intent("android.intent.action.SEND");
        } catch (Throwable th) {
            File file2 = new File(Environment.getExternalStorageDirectory().toString() + "/" + str);
            Intent intent2 = new Intent("android.intent.action.SEND");
            intent2.setType("application/pdf");
            intent2.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(activity, "com.CricketScorer.fileprovider", file2));
            activity.startActivity(Intent.createChooser(intent2, "share file with"));
            throw th;
        }
        intent.setType("application/pdf");
        intent.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(activity, "com.CricketScorer.fileprovider", file));
        activity.startActivity(Intent.createChooser(intent, "share file with"));
    }

    public static void shareBatsmanPdf(PDFGenerator pDFGenerator, Context context, String str) {
        File file;
        Intent intent;
        Activity activity = (Activity) context;
        try {
            pDFGenerator.generateBatsmanPDF();
            file = new File(Environment.getExternalStorageDirectory().toString() + "/" + str);
            intent = new Intent("android.intent.action.SEND");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            file = new File(Environment.getExternalStorageDirectory().toString() + "/" + str);
            intent = new Intent("android.intent.action.SEND");
        } catch (Throwable th) {
            File file2 = new File(Environment.getExternalStorageDirectory().toString() + "/" + str);
            Intent intent2 = new Intent("android.intent.action.SEND");
            intent2.setType("application/pdf");
            intent2.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(activity, "com.CricketScorer.fileprovider", file2));
            activity.startActivity(Intent.createChooser(intent2, "share file with"));
            throw th;
        }
        intent.setType("application/pdf");
        intent.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(activity, "com.CricketScorer.fileprovider", file));
        activity.startActivity(Intent.createChooser(intent, "share file with"));
    }

    public static void shareBowlerPdf(PDFGenerator pDFGenerator, Context context, String str) {
        File file;
        Intent intent;
        Activity activity = (Activity) context;
        try {
            pDFGenerator.generateBowlerPDF();
            file = new File(Environment.getExternalStorageDirectory().toString() + "/" + str);
            intent = new Intent("android.intent.action.SEND");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            file = new File(Environment.getExternalStorageDirectory().toString() + "/" + str);
            intent = new Intent("android.intent.action.SEND");
        } catch (Throwable th) {
            File file2 = new File(Environment.getExternalStorageDirectory().toString() + "/" + str);
            Intent intent2 = new Intent("android.intent.action.SEND");
            intent2.setType("application/pdf");
            intent2.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(activity, "com.CricketScorer.fileprovider", file2));
            activity.startActivity(Intent.createChooser(intent2, "share file with"));
            throw th;
        }
        intent.setType("application/pdf");
        intent.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(activity, "com.CricketScorer.fileprovider", file));
        activity.startActivity(Intent.createChooser(intent, "share file with"));
    }
}
