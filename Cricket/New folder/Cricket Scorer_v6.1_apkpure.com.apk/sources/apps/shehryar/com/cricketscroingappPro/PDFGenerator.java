package apps.shehryar.com.cricketscroingappPro;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Environment;
import android.text.Html;
import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman;
import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman_Details;
import apps.shehryar.com.cricketscroingappPro.Bowler.Bowler;
import apps.shehryar.com.cricketscroingappPro.Bowler.BowlerHelper;
import apps.shehryar.com.cricketscroingappPro.Bowler.Bowler_Details;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Overs.Over;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.Formatter;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.UtilityFunctions;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfBoolean;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class PDFGenerator {
    private int EXTERNAL_STORAGE_PERMISSION_CONSTANT;
    String FILE_NAME;
    private int REQUEST_PERMISSION_SETTING;
    Batsman batsman;
    ArrayList<Batsman_Details> batsman_details;
    File batsmanfolder;
    Bowler bowler;
    File bowlerFolder;
    ArrayList<Bowler_Details> bowler_detailses;
    BaseColor color_green;
    Context context;
    Date date;
    int index;
    Match match;
    ArrayList<Match> matches;
    File mydir;
    Font nameFontBold;
    Font nameFontCondensed;
    Font nameFontNormal;
    Font numberFontCondensed;
    Font numberFontsNormal;
    Font numbersFontBold;
    private SharedPreferences permissionStatus;
    private boolean sentToSettings;
    Font tableHeadingFont;
    ArrayList<Batsman> team1batsmen;
    ArrayList<Bowler> team1bowler;
    ArrayList<FallOfWickets> team1fallofWickets;
    String team1name;
    ArrayList<Over> team1overs;
    Team team1stats;
    ArrayList<Batsman> team2batsmen;
    ArrayList<Bowler> team2bowler;
    ArrayList<FallOfWickets> team2fallofWickets;
    String team2name;
    ArrayList<Over> team2overs;
    Team team2stats;
    String timeStamp;
    File tournamentFolder;

    public PDFGenerator() {
        this.date = new Date();
        this.timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(this.date);
        this.REQUEST_PERMISSION_SETTING = 10;
        this.EXTERNAL_STORAGE_PERMISSION_CONSTANT = 100;
        this.nameFontBold = new Font(Font.FontFamily.UNDEFINED, 13.0f, 1);
        this.nameFontNormal = new Font(Font.FontFamily.UNDEFINED, 10.0f, 0);
        this.tableHeadingFont = new Font(Font.FontFamily.UNDEFINED, 14.0f, 1);
        this.numbersFontBold = new Font(Font.FontFamily.HELVETICA, 13.0f, 1);
        this.numberFontsNormal = new Font(Font.FontFamily.HELVETICA, 13.0f, 0);
        BaseFont baseFont = null;
        try {
            baseFont = BaseFont.createFont("assets/fonts/RobotoCondensed_Regular.ttf", "UTF-8", true);
        } catch (DocumentException e) {
            try {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
                this.numberFontCondensed = new Font(Font.FontFamily.COURIER, 12.0f);
                return;
            }
        }
        this.nameFontCondensed = new Font(baseFont, 12.0f);
        this.numberFontCondensed = this.nameFontCondensed;
    }

    public PDFGenerator(Context context2, ArrayList<Batsman_Details> arrayList, Batsman batsman2) {
        this();
        this.context = context2;
        this.batsman_details = arrayList;
        batsman2.setName(Formatter.replaceForwardSlashWithBackSlash(batsman2.getName()));
        batsman2.setTeam_Name(Formatter.replaceForwardSlashWithBackSlash(batsman2.getTeam_Name()));
        this.FILE_NAME = batsman2.getName() + " ( " + batsman2.getTeam_Name() + ").pdf";
        this.batsman = batsman2;
        this.color_green = new BaseColor(0, 100, 0);
        try {
            BaseFont.createFont("assets/subFolder/fontName.TTF", "UTF-8", true);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public PDFGenerator(Context context2, ArrayList<Bowler_Details> arrayList, Bowler bowler2) {
        this();
        this.context = context2;
        try {
            this.bowler_detailses = arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            new ArrayList();
        }
        bowler2.setName(Formatter.replaceForwardSlashWithBackSlash(bowler2.getName()));
        bowler2.setTeamName(Formatter.replaceForwardSlashWithBackSlash(bowler2.getTeamName()));
        this.FILE_NAME = bowler2.getName() + " ( " + bowler2.getTeamName() + ").pdf";
        this.bowler = bowler2;
        this.color_green = new BaseColor(0, 100, 0);
    }

    public PDFGenerator(Context context2, Match match2) {
        this();
        this.context = context2;
        this.match = match2;
        Iterator<Bowler> it = match2.getTeam1().getBowlers_list().iterator();
        while (it.hasNext()) {
            try {
                BowlerHelper.setStatsToBowlerFromOver(it.next(), match2.getTeam1().getOvers_list());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Iterator<Bowler> it2 = match2.getTeam2().getBowlers_list().iterator();
        while (it2.hasNext()) {
            try {
                BowlerHelper.setStatsToBowlerFromOver(it2.next(), match2.getTeam2().getOvers_list());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (match2.isTestMatch) {
            Iterator<Bowler> it3 = match2.getTeam3().getBowlers_list().iterator();
            while (it3.hasNext()) {
                try {
                    BowlerHelper.setStatsToBowlerFromOver(it3.next(), match2.getTeam3().getOvers_list());
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
            Iterator<Bowler> it4 = match2.getTeam4().getBowlers_list().iterator();
            while (it4.hasNext()) {
                try {
                    BowlerHelper.setStatsToBowlerFromOver(it4.next(), match2.getTeam4().getOvers_list());
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
        }
        this.team1name = Formatter.replaceForwardSlashWithBackSlash(match2.getTeam1().getName());
        this.team2name = Formatter.replaceForwardSlashWithBackSlash(match2.getTeam2().getName());
        this.FILE_NAME = this.team1name + " VS " + this.team2name + " " + match2.getDate() + ".pdf";
        this.index = this.index;
        this.color_green = new BaseColor(0, 100, 0);
    }

    public PDFGenerator(Context context2, ArrayList<Match> arrayList) {
        this();
        this.context = context2;
        this.matches = arrayList;
        Formatter.replaceForwardSlashWithBackSlash(arrayList.get(0).getTournament());
        this.FILE_NAME = arrayList.get(0).getTournament() + ".pdf";
        this.color_green = new BaseColor(0, 100, 0);
    }

    /* access modifiers changed from: package-private */
    public void createBatsmanFolder() {
        this.batsmanfolder = new File(Environment.getExternalStorageDirectory() + "/Cricket Scorer/Batsman Stats");
        if (!this.batsmanfolder.exists()) {
            this.batsmanfolder.mkdirs();
        }
    }

    /* access modifiers changed from: package-private */
    public void createBowlerFolder() {
        this.bowlerFolder = new File(Environment.getExternalStorageDirectory() + "/Cricket Scorer/Bowlers Stats");
        if (!this.bowlerFolder.exists()) {
            this.bowlerFolder.mkdirs();
        }
    }

    public void createFolderAndFile() throws FileNotFoundException {
        this.mydir = new File(Environment.getExternalStorageDirectory() + File.separator + "Cricket Scorer");
        if (!this.mydir.exists()) {
            this.mydir.mkdirs();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0122, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r3.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0126, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x014f, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r4.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0197, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0198, code lost:
        r0.printStackTrace();
        apps.shehryar.com.cricketscroingappPro.MyToast.showToast(r10.context, r0.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x00c2, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        r2.printStackTrace();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0197 A[ExcHandler: DocumentException | FileNotFoundException (r0v1 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:1:0x0005] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writePDF() {
        /*
            r10 = this;
            com.itextpdf.text.Document r0 = new com.itextpdf.text.Document
            r0.<init>()
            java.io.File r1 = new java.io.File     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            java.io.File r2 = r10.mydir     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            java.lang.String r3 = r10.FILE_NAME     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            r1.<init>(r2, r3)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            r2.<init>(r1)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            com.itextpdf.text.pdf.PdfWriter.getInstance(r0, r2)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            com.itextpdf.text.Font r2 = new com.itextpdf.text.Font     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            com.itextpdf.text.Font$FontFamily r3 = com.itextpdf.text.Font.FontFamily.TIMES_ROMAN     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            r4 = 1099956224(0x41900000, float:18.0)
            r5 = 1
            r2.<init>((com.itextpdf.text.Font.FontFamily) r3, (float) r4, (int) r5)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            com.itextpdf.text.Font r2 = new com.itextpdf.text.Font     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            com.itextpdf.text.Font$FontFamily r3 = com.itextpdf.text.Font.FontFamily.TIMES_ROMAN     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            r6 = 1090519040(0x41000000, float:8.0)
            r2.<init>((com.itextpdf.text.Font.FontFamily) r3, (float) r6)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            com.itextpdf.text.Font r3 = new com.itextpdf.text.Font     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            com.itextpdf.text.Font$FontFamily r7 = com.itextpdf.text.Font.FontFamily.TIMES_ROMAN     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            r8 = 1103626240(0x41c80000, float:25.0)
            r3.<init>((com.itextpdf.text.Font.FontFamily) r7, (float) r8, (int) r5)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            com.itextpdf.text.Font r7 = new com.itextpdf.text.Font     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            com.itextpdf.text.Font$FontFamily r8 = com.itextpdf.text.Font.FontFamily.TIMES_ROMAN     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            r7.<init>((com.itextpdf.text.Font.FontFamily) r8, (float) r4)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            com.itextpdf.text.Font r8 = new com.itextpdf.text.Font     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            com.itextpdf.text.Font$FontFamily r9 = com.itextpdf.text.Font.FontFamily.TIMES_ROMAN     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            r8.<init>((com.itextpdf.text.Font.FontFamily) r9, (float) r4, (int) r5)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            com.itextpdf.text.BaseColor r4 = r10.color_green     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            r8.setColor(r4)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            r0.open()     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            com.itextpdf.text.Font r4 = new com.itextpdf.text.Font     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            r4.<init>()     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            r4.setStyle((int) r5)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            r4.setSize(r6)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            com.itextpdf.text.Paragraph r4 = new com.itextpdf.text.Paragraph     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            apps.shehryar.com.cricketscroingappPro.Match.Match r6 = r10.match     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            java.lang.String r6 = r6.getTournament()     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            r4.<init>((java.lang.String) r6, (com.itextpdf.text.Font) r7)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            r4.setAlignment(r5)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            com.itextpdf.text.Paragraph r6 = new com.itextpdf.text.Paragraph     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            r8.<init>()     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            java.lang.String r9 = r10.team1name     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            r8.append(r9)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            java.lang.String r9 = " VS "
            r8.append(r9)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            java.lang.String r9 = r10.team2name     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            r8.append(r9)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            java.lang.String r8 = r8.toString()     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            r6.<init>((java.lang.String) r8, (com.itextpdf.text.Font) r3)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            r6.setAlignment(r5)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            r4.add((com.itextpdf.text.Element) r6)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            com.itextpdf.text.Paragraph r3 = new com.itextpdf.text.Paragraph     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            r6.<init>()     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            apps.shehryar.com.cricketscroingappPro.Match.Match r8 = r10.match     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            java.lang.String r8 = r8.getVenue()     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            r6.append(r8)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            java.lang.String r8 = " "
            r6.append(r8)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            apps.shehryar.com.cricketscroingappPro.Match.Match r8 = r10.match     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            java.lang.String r8 = r8.getDate()     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            r6.append(r8)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            java.lang.String r6 = r6.toString()     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            r3.<init>((java.lang.String) r6, (com.itextpdf.text.Font) r2)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            r3.setAlignment(r5)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            r4.add((com.itextpdf.text.Element) r3)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            apps.shehryar.com.cricketscroingappPro.Team.Team r2 = r10.team1stats     // Catch:{ Exception -> 0x00c2, DocumentException | FileNotFoundException -> 0x0197 }
            apps.shehryar.com.cricketscroingappPro.Team.Team r3 = r10.team1stats     // Catch:{ Exception -> 0x00c2, DocumentException | FileNotFoundException -> 0x0197 }
            java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.Overs.Over> r3 = r3.overs_list     // Catch:{ Exception -> 0x00c2, DocumentException | FileNotFoundException -> 0x0197 }
            apps.shehryar.com.cricketscroingappPro.Utility_Classes.UtilityFunctions.setTeamExtras(r2, r3)     // Catch:{ Exception -> 0x00c2, DocumentException | FileNotFoundException -> 0x0197 }
            apps.shehryar.com.cricketscroingappPro.Team.Team r2 = r10.team2stats     // Catch:{ Exception -> 0x00c2, DocumentException | FileNotFoundException -> 0x0197 }
            apps.shehryar.com.cricketscroingappPro.Team.Team r3 = r10.team2stats     // Catch:{ Exception -> 0x00c2, DocumentException | FileNotFoundException -> 0x0197 }
            java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.Overs.Over> r3 = r3.overs_list     // Catch:{ Exception -> 0x00c2, DocumentException | FileNotFoundException -> 0x0197 }
            apps.shehryar.com.cricketscroingappPro.Utility_Classes.UtilityFunctions.setTeamExtras(r2, r3)     // Catch:{ Exception -> 0x00c2, DocumentException | FileNotFoundException -> 0x0197 }
            goto L_0x00c6
        L_0x00c2:
            r2 = move-exception
            r2.printStackTrace()     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
        L_0x00c6:
            r0.add(r4)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            apps.shehryar.com.cricketscroingappPro.Match.Match r2 = r10.match     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            apps.shehryar.com.cricketscroingappPro.Team.Team r2 = r2.getTeam1()     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            java.lang.String r3 = "First Innings"
            r10.addTeamDataInPDF(r2, r0, r4, r3)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            apps.shehryar.com.cricketscroingappPro.Match.Match r2 = r10.match     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            apps.shehryar.com.cricketscroingappPro.Team.Team r2 = r2.getTeam2()     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            java.lang.String r3 = "Second Innings"
            r10.addTeamDataInPDF(r2, r0, r4, r3)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            apps.shehryar.com.cricketscroingappPro.Match.Match r2 = r10.match     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            boolean r2 = r2.isTestMatch     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            if (r2 == 0) goto L_0x00fb
            apps.shehryar.com.cricketscroingappPro.Match.Match r2 = r10.match     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            apps.shehryar.com.cricketscroingappPro.Team.Team r2 = r2.getTeam3()     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            java.lang.String r3 = "Third Innings"
            r10.addTeamDataInPDF(r2, r0, r4, r3)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            apps.shehryar.com.cricketscroingappPro.Match.Match r2 = r10.match     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            apps.shehryar.com.cricketscroingappPro.Team.Team r2 = r2.getTeam4()     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            java.lang.String r3 = "Fourth Innings"
            r10.addTeamDataInPDF(r2, r0, r4, r3)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
        L_0x00fb:
            r2 = 0
            com.itextpdf.text.Paragraph r3 = new com.itextpdf.text.Paragraph     // Catch:{ Exception -> 0x0122, DocumentException | FileNotFoundException -> 0x0197 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0122, DocumentException | FileNotFoundException -> 0x0197 }
            r4.<init>()     // Catch:{ Exception -> 0x0122, DocumentException | FileNotFoundException -> 0x0197 }
            java.lang.String r6 = "Result:  "
            r4.append(r6)     // Catch:{ Exception -> 0x0122, DocumentException | FileNotFoundException -> 0x0197 }
            apps.shehryar.com.cricketscroingappPro.Match.Match r6 = r10.match     // Catch:{ Exception -> 0x0122, DocumentException | FileNotFoundException -> 0x0197 }
            java.lang.String r6 = r6.getResult()     // Catch:{ Exception -> 0x0122, DocumentException | FileNotFoundException -> 0x0197 }
            java.lang.String r8 = ":"
            java.lang.String[] r6 = r6.split(r8)     // Catch:{ Exception -> 0x0122, DocumentException | FileNotFoundException -> 0x0197 }
            r8 = 0
            r6 = r6[r8]     // Catch:{ Exception -> 0x0122, DocumentException | FileNotFoundException -> 0x0197 }
            r4.append(r6)     // Catch:{ Exception -> 0x0122, DocumentException | FileNotFoundException -> 0x0197 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0122, DocumentException | FileNotFoundException -> 0x0197 }
            r3.<init>((java.lang.String) r4, (com.itextpdf.text.Font) r7)     // Catch:{ Exception -> 0x0122, DocumentException | FileNotFoundException -> 0x0197 }
            goto L_0x0127
        L_0x0122:
            r3 = move-exception
            r3.printStackTrace()     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            r3 = r2
        L_0x0127:
            com.itextpdf.text.Paragraph r4 = new com.itextpdf.text.Paragraph     // Catch:{ Exception -> 0x014f, DocumentException | FileNotFoundException -> 0x0197 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x014f, DocumentException | FileNotFoundException -> 0x0197 }
            r6.<init>()     // Catch:{ Exception -> 0x014f, DocumentException | FileNotFoundException -> 0x0197 }
            java.lang.String r8 = "Man of the Match:  "
            r6.append(r8)     // Catch:{ Exception -> 0x014f, DocumentException | FileNotFoundException -> 0x0197 }
            apps.shehryar.com.cricketscroingappPro.Match.Match r8 = r10.match     // Catch:{ Exception -> 0x014f, DocumentException | FileNotFoundException -> 0x0197 }
            java.lang.String r8 = r8.getResult()     // Catch:{ Exception -> 0x014f, DocumentException | FileNotFoundException -> 0x0197 }
            java.lang.String r9 = ":"
            java.lang.String[] r8 = r8.split(r9)     // Catch:{ Exception -> 0x014f, DocumentException | FileNotFoundException -> 0x0197 }
            r5 = r8[r5]     // Catch:{ Exception -> 0x014f, DocumentException | FileNotFoundException -> 0x0197 }
            r6.append(r5)     // Catch:{ Exception -> 0x014f, DocumentException | FileNotFoundException -> 0x0197 }
            java.lang.String r5 = r6.toString()     // Catch:{ Exception -> 0x014f, DocumentException | FileNotFoundException -> 0x0197 }
            com.itextpdf.text.Font r6 = r10.nameFontBold     // Catch:{ Exception -> 0x014f, DocumentException | FileNotFoundException -> 0x0197 }
            r4.<init>((java.lang.String) r5, (com.itextpdf.text.Font) r6)     // Catch:{ Exception -> 0x014f, DocumentException | FileNotFoundException -> 0x0197 }
            r2 = r4
            goto L_0x0153
        L_0x014f:
            r4 = move-exception
            r4.printStackTrace()     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
        L_0x0153:
            r0.add(r3)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            r0.add(r2)     // Catch:{ DocumentException -> 0x015a }
            goto L_0x015e
        L_0x015a:
            r2 = move-exception
            r2.printStackTrace()     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
        L_0x015e:
            com.itextpdf.text.Paragraph r2 = new com.itextpdf.text.Paragraph     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            java.lang.String r3 = "Generated by Cricket Scorer"
            r2.<init>((java.lang.String) r3, (com.itextpdf.text.Font) r7)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            r3 = 2
            r2.setAlignment(r3)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            r0.add(r2)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            r0.close()     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            java.io.PrintStream r0 = java.lang.System.out     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            java.lang.String r2 = "Done"
            r0.println(r2)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            apps.shehryar.com.cricketscroingappPro.PDFGenerator$1 r8 = new apps.shehryar.com.cricketscroingappPro.PDFGenerator$1     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            r8.<init>(r1)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            android.content.Context r3 = r10.context     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            java.lang.String r4 = "Pdf Saved"
            java.lang.String r5 = "Pdf saved in /sdcard/Cricket Scorer"
            java.lang.String r6 = "Open"
            java.lang.String r7 = "Ok"
            apps.shehryar.com.cricketscroingappPro.Utility_Classes.UtilityFunctions.showAlertDialog(r3, r4, r5, r6, r7, r8)     // Catch:{ DocumentException | FileNotFoundException -> 0x0197, IOException -> 0x0189 }
            goto L_0x01a4
        L_0x0189:
            r0 = move-exception
            r0.printStackTrace()
            android.content.Context r1 = r10.context
            java.lang.String r0 = r0.toString()
            apps.shehryar.com.cricketscroingappPro.MyToast.showToast(r1, r0)
            goto L_0x01a4
        L_0x0197:
            r0 = move-exception
            r0.printStackTrace()
            android.content.Context r1 = r10.context
            java.lang.String r0 = r0.toString()
            apps.shehryar.com.cricketscroingappPro.MyToast.showToast(r1, r0)
        L_0x01a4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: apps.shehryar.com.cricketscroingappPro.PDFGenerator.writePDF():void");
    }

    public void addTeamDataInPDF(Team team, Document document, Paragraph paragraph, String str) {
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 18.0f);
        Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 18.0f, 1);
        font2.setColor(this.color_green);
        Paragraph paragraph2 = new Paragraph(str + "-Batting", font2);
        paragraph2.setAlignment(1);
        paragraph2.setPaddingTop(20.0f);
        try {
            document.add(paragraph2);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        try {
            document.add(new Paragraph(team.getName() + ":" + team.getScore() + "/" + team.getWickets() + "     Overs:" + team.getOversPlayed() + "." + team.getOverballs() + "           " + team.getExtrasString(), font));
        } catch (DocumentException e2) {
            e2.printStackTrace();
        }
        PdfPTable pdfPTable = null;
        try {
            pdfPTable = createTeam1BattingTableHeader();
        } catch (DocumentException e3) {
            e3.printStackTrace();
        }
        try {
            document.add(pdfPTable);
        } catch (DocumentException e4) {
            e4.printStackTrace();
        }
        for (int i = 0; i < team.getBatsmans_list().size(); i++) {
            try {
                document.add(createTeam1BattingTable(team.getBatsmans_list().get(i), i));
            } catch (DocumentException e5) {
                e5.printStackTrace();
            }
        }
        Paragraph paragraph3 = new Paragraph(str + "-Bowling", font2);
        paragraph3.setAlignment(1);
        paragraph3.setPaddingTop(40.0f);
        try {
            document.add(paragraph3);
        } catch (DocumentException e6) {
            e6.printStackTrace();
        }
        try {
            pdfPTable = createTeam1BowlingTableHeader();
        } catch (DocumentException e7) {
            e7.printStackTrace();
        }
        try {
            document.add(pdfPTable);
        } catch (DocumentException e8) {
            e8.printStackTrace();
        }
        for (int i2 = 0; i2 < team.getBowlers_list().size(); i2++) {
            try {
                document.add(createTeam1BowlingTable(team.getBowlers_list().get(i2), i2));
            } catch (DocumentException e9) {
                e9.printStackTrace();
            }
        }
        Paragraph paragraph4 = new Paragraph(str + "-Fall of Wickets", font2);
        paragraph4.setAlignment(1);
        paragraph4.setPaddingTop(40.0f);
        try {
            document.add(paragraph4);
        } catch (DocumentException e10) {
            e10.printStackTrace();
        }
        try {
            pdfPTable = createTeam1FowTableHeader();
        } catch (DocumentException e11) {
            e11.printStackTrace();
        }
        try {
            document.add(pdfPTable);
        } catch (DocumentException e12) {
            e12.printStackTrace();
        }
        for (int i3 = 0; i3 < team.getFallOfWicketses().size(); i3++) {
            try {
                document.add(createTeam1FowTable(team.getFallOfWicketses().get(i3), i3));
            } catch (DocumentException e13) {
                e13.printStackTrace();
            }
        }
        Paragraph paragraph5 = new Paragraph(str + "-OVERS", font2);
        paragraph5.setAlignment(1);
        paragraph5.setPaddingTop(40.0f);
        try {
            document.add(paragraph5);
        } catch (DocumentException e14) {
            e14.printStackTrace();
        }
        try {
            pdfPTable = createOversTableHeader();
        } catch (DocumentException e15) {
            e15.printStackTrace();
        }
        try {
            document.add(pdfPTable);
        } catch (DocumentException e16) {
            e16.printStackTrace();
        }
        for (int i4 = 0; i4 < team.getOvers_list().size(); i4++) {
            try {
                document.add(createOversTable(i4, team.getOvers_list()));
            } catch (DocumentException e17) {
                e17.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void writeBatsmanStatsPDF() {
        Document document = new Document();
        try {
            File file = new File(this.batsmanfolder, this.FILE_NAME);
            PdfWriter.getInstance(document, new FileOutputStream(file));
            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 18.0f, 1);
            new Font(Font.FontFamily.TIMES_ROMAN, 11.0f);
            new Font(Font.FontFamily.TIMES_ROMAN, 25.0f, 1);
            Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 18.0f);
            new Font(Font.FontFamily.TIMES_ROMAN, 23.0f, 1).setColor(this.color_green);
            document.open();
            Font font3 = new Font();
            font3.setStyle(1);
            font3.setSize(8.0f);
            Paragraph paragraph = new Paragraph(" ", font2);
            paragraph.setAlignment(1);
            Paragraph paragraph2 = new Paragraph(this.batsman.getName() + " Batting Stats", font);
            paragraph2.setAlignment(1);
            paragraph.add((Element) paragraph2);
            Paragraph paragraph3 = new Paragraph("Team:" + this.batsman.getTeam_Name() + "");
            paragraph3.setAlignment(1);
            paragraph.add((Element) paragraph3);
            paragraph.add((Element) new Paragraph(" "));
            paragraph.add((Element) new Paragraph(" "));
            document.add(paragraph);
            Chunk chunk = new Chunk("Runs: ", new Font(Font.FontFamily.TIMES_ROMAN, 16.0f, 0));
            Chunk chunk2 = new Chunk(this.batsman.getTotalScore() + "   ", new Font(Font.FontFamily.HELVETICA, 16.0f, 1));
            Chunk chunk3 = new Chunk("Matches: ", new Font(Font.FontFamily.TIMES_ROMAN, 16.0f, 0));
            Chunk chunk4 = new Chunk(this.batsman.getMatches() + "   ", new Font(Font.FontFamily.HELVETICA, 16.0f, 1));
            Chunk chunk5 = new Chunk("Average: ", new Font(Font.FontFamily.TIMES_ROMAN, 16.0f, 0));
            Chunk chunk6 = new Chunk(this.batsman.getAverage() + "   ", new Font(Font.FontFamily.HELVETICA, 16.0f, 1));
            File file2 = file;
            Chunk chunk7 = new Chunk("Best: ", new Font(Font.FontFamily.TIMES_ROMAN, 16.0f, 0));
            Font font4 = font2;
            Chunk chunk8 = new Chunk(this.batsman.getBest() + "   ", new Font(Font.FontFamily.HELVETICA, 16.0f, 1));
            Document document2 = document;
            Chunk chunk9 = new Chunk("SR: ", new Font(Font.FontFamily.TIMES_ROMAN, 16.0f, 0));
            Chunk chunk10 = chunk9;
            Chunk chunk11 = new Chunk(this.batsman.getStrikerate() + "   ", new Font(Font.FontFamily.HELVETICA, 16.0f, 1));
            Chunk chunk12 = new Chunk("30s: ", new Font(Font.FontFamily.TIMES_ROMAN, 16.0f, 0));
            Chunk chunk13 = chunk12;
            Chunk chunk14 = new Chunk(this.batsman.getThirties() + "   ", new Font(Font.FontFamily.HELVETICA, 16.0f, 1));
            Chunk chunk15 = new Chunk("50s: ", new Font(Font.FontFamily.TIMES_ROMAN, 16.0f, 0));
            Chunk chunk16 = chunk15;
            Chunk chunk17 = new Chunk(this.batsman.getFifties() + "   ", new Font(Font.FontFamily.HELVETICA, 16.0f, 1));
            Chunk chunk18 = new Chunk("100s: ", new Font(Font.FontFamily.TIMES_ROMAN, 16.0f, 0));
            Chunk chunk19 = new Chunk(this.batsman.getHundreds() + "   ", new Font(Font.FontFamily.HELVETICA, 16.0f, 1));
            Document document3 = document2;
            document3.add(createBatsmanStatsTopTable(chunk, chunk2, chunk3, chunk4, chunk5, chunk6, chunk7, chunk8));
            document3.add(createBatsmanStatsTopTable(chunk10, chunk11, chunk13, chunk14, chunk16, chunk17, chunk18, chunk19));
            document3.add(createBatsmanStatsTableHeader());
            int i = 0;
            while (i < this.batsman_details.size()) {
                int i2 = i + 1;
                document3.add(createBatsmanStatsTable(i2, this.batsman_details.get(i)));
                i = i2;
            }
            Paragraph paragraph4 = new Paragraph("Generated by Cricket Scorer", font4);
            paragraph4.setAlignment(2);
            document3.add(paragraph4);
            document3.close();
            System.out.println("Done");
            final File file3 = file2;
            UtilityFunctions.showAlertDialog(this.context, "Pdf Saved", "Pdf saved in /sdcard/Cricket Scorer/Batsmen Stats", "Open", "Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    UtilityFunctions.openDocument(PDFGenerator.this.context, file3);
                }
            });
        } catch (DocumentException | FileNotFoundException e) {
            Exception exc = e;
            exc.printStackTrace();
            MyToast.showToast(this.context, exc.toString());
        } catch (IOException e2) {
            IOException iOException = e2;
            iOException.printStackTrace();
            MyToast.showToast(this.context, iOException.toString());
        }
    }

    /* access modifiers changed from: package-private */
    public void writeBowlerStatsPdf() {
        Document document = new Document();
        try {
            File file = new File(this.bowlerFolder, this.FILE_NAME);
            PdfWriter.getInstance(document, new FileOutputStream(file));
            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 18.0f, 1);
            new Font(Font.FontFamily.TIMES_ROMAN, 11.0f);
            new Font(Font.FontFamily.TIMES_ROMAN, 25.0f, 1);
            Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 18.0f);
            new Font(Font.FontFamily.TIMES_ROMAN, 23.0f, 1).setColor(this.color_green);
            document.open();
            Font font3 = new Font();
            font3.setStyle(1);
            font3.setSize(8.0f);
            Paragraph paragraph = new Paragraph(" ", font2);
            paragraph.setAlignment(1);
            Paragraph paragraph2 = new Paragraph(this.bowler.getName() + " Bowling Stats", font);
            paragraph2.setAlignment(1);
            paragraph.add((Element) paragraph2);
            Paragraph paragraph3 = new Paragraph("Team:" + this.bowler.getTeamName() + "");
            paragraph3.setAlignment(1);
            paragraph.add((Element) paragraph3);
            paragraph.add((Element) new Paragraph(" "));
            paragraph.add((Element) new Paragraph(" "));
            document.add(paragraph);
            Chunk chunk = new Chunk("Wickets: ", new Font(Font.FontFamily.TIMES_ROMAN, 16.0f, 0));
            Chunk chunk2 = new Chunk(this.bowler.getWickets() + "   ", new Font(Font.FontFamily.HELVETICA, 16.0f, 1));
            Chunk chunk3 = new Chunk("Matches: ", new Font(Font.FontFamily.TIMES_ROMAN, 16.0f, 0));
            Chunk chunk4 = new Chunk(this.bowler.getMatches() + "   ", new Font(Font.FontFamily.HELVETICA, 16.0f, 1));
            Chunk chunk5 = new Chunk("Average: ", new Font(Font.FontFamily.TIMES_ROMAN, 16.0f, 0));
            Chunk chunk6 = new Chunk(this.bowler.getAverage() + "   ", new Font(Font.FontFamily.HELVETICA, 16.0f, 1));
            File file2 = file;
            Chunk chunk7 = new Chunk("Best: ", new Font(Font.FontFamily.TIMES_ROMAN, 16.0f, 0));
            Font font4 = font2;
            Chunk chunk8 = new Chunk(this.bowler.getBest() + "   ", new Font(Font.FontFamily.HELVETICA, 16.0f, 1));
            Document document2 = document;
            Chunk chunk9 = new Chunk("Economy: ", new Font(Font.FontFamily.TIMES_ROMAN, 16.0f, 0));
            int i = 0;
            PdfPTable createBowlerStatsTopTable = createBowlerStatsTopTable(chunk, chunk2, chunk3, chunk4, chunk5, chunk6, chunk7, chunk8, chunk9, new Chunk(this.bowler.getEco() + "   ", new Font(Font.FontFamily.HELVETICA, 16.0f, 1)));
            Document document3 = document2;
            document3.add(createBowlerStatsTopTable);
            document3.add(createBowlerStatsTableHeader());
            while (i < this.bowler_detailses.size()) {
                int i2 = i + 1;
                document3.add(createBowlerStatsTable(i2, this.bowler_detailses.get(i)));
                i = i2;
            }
            Paragraph paragraph4 = new Paragraph("Generated by Cricket Scorer", font4);
            paragraph4.setAlignment(2);
            document3.add(paragraph4);
            document3.close();
            System.out.println("Done");
            final File file3 = file2;
            UtilityFunctions.showAlertDialog(this.context, "Pdf Saved", "Pdf saved in /sdcard/Cricket Scorer", "Open", "Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    UtilityFunctions.openDocument(PDFGenerator.this.context, file3);
                }
            });
        } catch (DocumentException | FileNotFoundException e) {
            Exception exc = e;
            exc.printStackTrace();
            MyToast.showToast(this.context, exc.toString());
        } catch (IOException e2) {
            IOException iOException = e2;
            iOException.printStackTrace();
            MyToast.showToast(this.context, iOException.toString());
        }
    }

    /* access modifiers changed from: package-private */
    public void addSecondTeamRecord(Document document) throws DocumentException {
        new Font(Font.FontFamily.TIMES_ROMAN, 18.0f);
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 18.0f, 1);
        font.setColor(this.color_green);
        Font font2 = new Font();
        font2.setStyle(1);
        font2.setSize(8.0f);
        Paragraph paragraph = new Paragraph("Second Innings-Batting", font);
        paragraph.setAlignment(1);
        paragraph.setPaddingTop(20.0f);
        paragraph.add((Element) new Paragraph(this.team2name + ":" + this.team2stats.getScore() + "/" + this.team2stats.getWickets() + "     Overs:" + this.team2stats.getOversPlayed() + "." + this.team2stats.getOverballs() + "           " + this.team2stats.getExtrasString(), this.numberFontsNormal));
        document.add(paragraph);
        document.add(createTeam1BattingTableHeader());
        for (int i = 0; i < this.team2batsmen.size(); i++) {
            document.add(createTeam1BattingTable(this.team2batsmen.get(i), i));
        }
        Paragraph paragraph2 = new Paragraph("Second Innings-Bowling", font);
        paragraph2.setAlignment(1);
        paragraph2.setPaddingTop(40.0f);
        document.add(paragraph2);
        document.add(createTeam1BowlingTableHeader());
        for (int i2 = 0; i2 < this.team2bowler.size(); i2++) {
            document.add(createTeam1BowlingTable(this.team2bowler.get(i2), i2));
        }
        document.newPage();
        Paragraph paragraph3 = new Paragraph("Second Innings-Fall of Wickets", font);
        paragraph3.setAlignment(1);
        paragraph3.setPaddingTop(40.0f);
        document.add(paragraph3);
        document.add(createTeam1FowTableHeader());
        for (int i3 = 0; i3 < this.team2fallofWickets.size(); i3++) {
            document.add(createTeam1FowTable(this.team2fallofWickets.get(i3), i3));
        }
        Paragraph paragraph4 = new Paragraph("Second Innings-OVERS", font);
        paragraph4.setAlignment(1);
        paragraph4.setPaddingTop(40.0f);
        document.add(paragraph4);
        document.add(createOversTableHeader());
        for (int i4 = 0; i4 < this.team2overs.size(); i4++) {
            document.add(createOversTable(i4, this.team2overs));
        }
    }

    public PdfPTable createTeam1BattingTable(Batsman batsman2, int i) throws DocumentException {
        String str;
        Font font = this.numberFontsNormal;
        PdfPTable pdfPTable = new PdfPTable(8);
        pdfPTable.setWidths(new int[]{8, 6, 2, 2, 2, 2, 2, 3});
        PdfPCell pdfPCell = new PdfPCell(new Phrase(batsman2.getName(), this.nameFontBold));
        pdfPCell.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i, pdfPCell));
        if (batsman2.isOut().equals(PdfBoolean.FALSE)) {
            str = "not out";
        } else if (batsman2.isOut().equals(PdfBoolean.TRUE)) {
            str = "out";
        } else {
            str = batsman2.isOut();
        }
        PdfPCell changeCellBackgroundColor = changeCellBackgroundColor(i, new PdfPCell(new Phrase(str, this.nameFontNormal)));
        changeCellBackgroundColor.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor);
        PdfPCell pdfPCell2 = new PdfPCell(new Phrase(batsman2.getTotalScore() + "", this.numbersFontBold));
        pdfPCell2.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i, pdfPCell2));
        PdfPCell pdfPCell3 = new PdfPCell(new Phrase(batsman2.getBallsfaced() + "", font));
        pdfPCell3.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i, pdfPCell3));
        PdfPCell pdfPCell4 = new PdfPCell(new Phrase(batsman2.getDots() + "", font));
        pdfPCell4.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i, pdfPCell4));
        PdfPCell pdfPCell5 = new PdfPCell(new Phrase(batsman2.getFours() + "", font));
        pdfPCell5.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i, pdfPCell5));
        PdfPCell pdfPCell6 = new PdfPCell(new Phrase(batsman2.getSixs() + "", font));
        pdfPCell6.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i, pdfPCell6));
        PdfPCell pdfPCell7 = new PdfPCell(new Phrase(batsman2.getStrikerate() + "", font));
        pdfPCell7.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i, pdfPCell7));
        pdfPTable.setWidthPercentage(100.0f);
        pdfPTable.setSpacingAfter(5.0f);
        return pdfPTable;
    }

    private PdfPCell changeCellBackgroundColor(int i, PdfPCell pdfPCell) {
        if (i % 2 != 0) {
            pdfPCell.setBackgroundColor(new BaseColor(224, 224, 224));
        }
        return pdfPCell;
    }

    public PdfPTable createTeam1BattingTableHeader() throws DocumentException {
        Font font = this.tableHeadingFont;
        font.setColor(BaseColor.WHITE);
        PdfPTable pdfPTable = new PdfPTable(8);
        pdfPTable.setWidths(new int[]{8, 6, 2, 2, 2, 2, 2, 3});
        PdfPCell changeCellBackGroundColor = changeCellBackGroundColor(new PdfPCell(new Phrase("Batsman", font)));
        changeCellBackGroundColor.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor);
        PdfPCell pdfPCell = new PdfPCell(new Phrase("       "));
        pdfPCell.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell));
        PdfPCell pdfPCell2 = new PdfPCell(new Phrase("R", font));
        pdfPCell2.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell2));
        PdfPCell pdfPCell3 = new PdfPCell(new Phrase("B", font));
        pdfPCell3.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell3));
        PdfPCell pdfPCell4 = new PdfPCell(new Phrase("0s", font));
        pdfPCell4.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell4));
        PdfPCell pdfPCell5 = new PdfPCell(new Phrase("4s", font));
        pdfPCell5.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell5));
        PdfPCell pdfPCell6 = new PdfPCell(new Phrase("6s", font));
        pdfPCell6.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell6));
        PdfPCell pdfPCell7 = new PdfPCell(new Phrase("SR", font));
        pdfPCell7.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell7));
        pdfPTable.setSpacingBefore(20.0f);
        pdfPTable.setSpacingAfter(20.0f);
        pdfPTable.setWidthPercentage(100.0f);
        return pdfPTable;
    }

    private static PdfPCell changeCellBackGroundColor(PdfPCell pdfPCell) {
        pdfPCell.setBackgroundColor(new BaseColor(0, 100, 0));
        pdfPCell.setVerticalAlignment(1);
        return pdfPCell;
    }

    public PdfPTable createTeam1BowlingTableHeader() throws DocumentException {
        Font font = this.tableHeadingFont;
        font.setColor(BaseColor.WHITE);
        PdfPTable pdfPTable = new PdfPTable(12);
        pdfPTable.setWidths(new int[]{8, 2, 3, 2, 2, 2, 3, 2, 2, 2, 2, 2});
        PdfPCell changeCellBackGroundColor = changeCellBackGroundColor(new PdfPCell(new Phrase("Bowler", font)));
        changeCellBackGroundColor.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor);
        PdfPCell changeCellBackGroundColor2 = changeCellBackGroundColor(new PdfPCell(new Phrase("   ")));
        changeCellBackGroundColor2.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor2);
        PdfPCell changeCellBackGroundColor3 = changeCellBackGroundColor(new PdfPCell(new Phrase("O", font)));
        changeCellBackGroundColor3.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor3);
        PdfPCell changeCellBackGroundColor4 = changeCellBackGroundColor(new PdfPCell(new Phrase("R", font)));
        changeCellBackGroundColor4.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor4);
        PdfPCell changeCellBackGroundColor5 = changeCellBackGroundColor(new PdfPCell(new Phrase("W", font)));
        changeCellBackGroundColor5.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor5);
        PdfPCell changeCellBackGroundColor6 = changeCellBackGroundColor(new PdfPCell(new Phrase("M", font)));
        changeCellBackGroundColor6.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor6);
        PdfPCell pdfPCell = new PdfPCell(new Phrase("ECO", font));
        pdfPCell.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell));
        PdfPCell pdfPCell2 = new PdfPCell(new Phrase("0s", font));
        pdfPCell2.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell2));
        PdfPCell pdfPCell3 = new PdfPCell(new Phrase("WD", font));
        pdfPCell3.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell3));
        PdfPCell pdfPCell4 = new PdfPCell(new Phrase("NB", font));
        pdfPCell4.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell4));
        PdfPCell pdfPCell5 = new PdfPCell(new Phrase("4s", font));
        pdfPCell5.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell5));
        PdfPCell pdfPCell6 = new PdfPCell(new Phrase("6s", font));
        pdfPCell6.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell6));
        pdfPTable.setSpacingBefore(20.0f);
        pdfPTable.setSpacingAfter(20.0f);
        pdfPTable.setWidthPercentage(100.0f);
        return pdfPTable;
    }

    public PdfPTable createTeam1BowlingTable(Bowler bowler2, int i) throws DocumentException {
        Font font = this.numberFontsNormal;
        PdfPTable pdfPTable = new PdfPTable(12);
        pdfPTable.setWidths(new int[]{8, 2, 3, 2, 2, 2, 3, 2, 2, 2, 2, 2});
        PdfPCell changeCellBackgroundColor = changeCellBackgroundColor(i, new PdfPCell(new Phrase(bowler2.getName(), this.nameFontBold)));
        changeCellBackgroundColor.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor);
        PdfPCell changeCellBackgroundColor2 = changeCellBackgroundColor(i, new PdfPCell(new Phrase(" ", font)));
        changeCellBackgroundColor2.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor2);
        PdfPCell pdfPCell = new PdfPCell(new Phrase(bowler2.getBowlerovers() + "." + bowler2.getBalls() + "", this.numbersFontBold));
        pdfPCell.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i, pdfPCell));
        PdfPCell pdfPCell2 = new PdfPCell(new Phrase(bowler2.getTotalscore() + "", font));
        pdfPCell2.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i, pdfPCell2));
        PdfPCell pdfPCell3 = new PdfPCell(new Phrase(bowler2.getWickets() + "", font));
        pdfPCell3.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i, pdfPCell3));
        PdfPCell pdfPCell4 = new PdfPCell(new Phrase(bowler2.getMaidens() + "", font));
        pdfPCell4.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i, pdfPCell4));
        PdfPCell pdfPCell5 = new PdfPCell(new Phrase(bowler2.getEco() + "", font));
        pdfPCell5.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i, pdfPCell5));
        PdfPCell pdfPCell6 = new PdfPCell(new Phrase(bowler2.getDots() + "", font));
        pdfPCell6.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i, pdfPCell6));
        PdfPCell pdfPCell7 = new PdfPCell(new Phrase(bowler2.getWides() + "", font));
        pdfPCell7.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i, pdfPCell7));
        PdfPCell pdfPCell8 = new PdfPCell(new Phrase(bowler2.getNos() + "", font));
        pdfPCell8.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i, pdfPCell8));
        PdfPCell pdfPCell9 = new PdfPCell(new Phrase(bowler2.getFours() + "", font));
        pdfPCell9.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i, pdfPCell9));
        PdfPCell pdfPCell10 = new PdfPCell(new Phrase(bowler2.getSixes() + "", font));
        pdfPCell10.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i, pdfPCell10));
        pdfPTable.setWidthPercentage(100.0f);
        pdfPTable.setSpacingAfter(5.0f);
        return pdfPTable;
    }

    public PdfPTable createTeam1FowTableHeader() throws DocumentException {
        Font font = this.tableHeadingFont;
        font.setColor(BaseColor.WHITE);
        PdfPTable pdfPTable = new PdfPTable(5);
        pdfPTable.setWidths(new int[]{1, 2, 2, 4, 4});
        PdfPCell pdfPCell = new PdfPCell(new Phrase("No", font));
        pdfPCell.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell));
        PdfPCell pdfPCell2 = new PdfPCell(new Phrase("Runs", font));
        pdfPCell2.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell2));
        PdfPCell pdfPCell3 = new PdfPCell(new Phrase("Over", font));
        pdfPCell3.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell3));
        PdfPCell pdfPCell4 = new PdfPCell(new Phrase("Batsman", font));
        pdfPCell4.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell4));
        PdfPCell pdfPCell5 = new PdfPCell(new Phrase("Bowler", font));
        pdfPCell5.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell5));
        pdfPTable.setSpacingBefore(20.0f);
        pdfPTable.setSpacingAfter(20.0f);
        pdfPTable.setWidthPercentage(100.0f);
        return pdfPTable;
    }

    public PdfPTable createTeam1FowTable(FallOfWickets fallOfWickets, int i) throws DocumentException {
        Font font = this.numberFontsNormal;
        PdfPTable pdfPTable = new PdfPTable(5);
        pdfPTable.setWidths(new int[]{1, 2, 2, 4, 4});
        PdfPCell pdfPCell = new PdfPCell(new Phrase(fallOfWickets.getWicketNo() + "", font));
        pdfPCell.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i, pdfPCell));
        PdfPCell pdfPCell2 = new PdfPCell(new Phrase(fallOfWickets.getScore() + "", font));
        pdfPCell2.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i, pdfPCell2));
        PdfPCell pdfPCell3 = new PdfPCell(new Phrase(fallOfWickets.getOverno() + "." + fallOfWickets.getBall() + "", this.numbersFontBold));
        pdfPCell3.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i, pdfPCell3));
        PdfPCell pdfPCell4 = new PdfPCell(new Phrase(fallOfWickets.getBatsmanName() + "", this.nameFontBold));
        pdfPCell4.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i, pdfPCell4));
        PdfPCell pdfPCell5 = new PdfPCell(new Phrase(fallOfWickets.getBowlerName() + "", this.nameFontBold));
        pdfPCell5.setBorder(0);
        PdfPCell changeCellBackgroundColor = changeCellBackgroundColor(i, pdfPCell5);
        pdfPTable.addCell(changeCellBackgroundColor);
        pdfPTable.addCell(changeCellBackgroundColor);
        pdfPTable.setWidthPercentage(100.0f);
        pdfPTable.setSpacingAfter(5.0f);
        return pdfPTable;
    }

    public static PdfPTable createBatsmanStatsTableHeader() throws DocumentException {
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 18.0f, 1);
        font.setColor(BaseColor.WHITE);
        new BaseColor(0, 100, 0);
        PdfPTable pdfPTable = new PdfPTable(8);
        pdfPTable.setWidths(new int[]{2, 3, 3, 4, 5, 5, 2, 2});
        PdfPCell pdfPCell = new PdfPCell(new Phrase("No", font));
        pdfPCell.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell));
        PdfPCell pdfPCell2 = new PdfPCell(new Phrase("Runs", font));
        pdfPCell2.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell2));
        PdfPCell pdfPCell3 = new PdfPCell(new Phrase("Balls", font));
        pdfPCell3.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell3));
        PdfPCell pdfPCell4 = new PdfPCell(new Phrase("Opp", font));
        pdfPCell4.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell4));
        PdfPCell pdfPCell5 = new PdfPCell(new Phrase("Venue", font));
        pdfPCell5.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell5));
        PdfPCell pdfPCell6 = new PdfPCell(new Phrase(HttpRequest.HEADER_DATE, font));
        pdfPCell6.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell6));
        PdfPCell pdfPCell7 = new PdfPCell(new Phrase("4s", font));
        pdfPCell7.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell7));
        PdfPCell pdfPCell8 = new PdfPCell(new Phrase("6s", font));
        pdfPCell8.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell8));
        pdfPTable.setSpacingBefore(20.0f);
        pdfPTable.setSpacingAfter(20.0f);
        pdfPTable.setWidthPercentage(100.0f);
        return pdfPTable;
    }

    public static PdfPTable createTournamentMatchesTableHeader() throws DocumentException {
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 16.0f, 1);
        font.setColor(BaseColor.WHITE);
        new BaseColor(0, 100, 0);
        PdfPTable pdfPTable = new PdfPTable(6);
        pdfPTable.setWidths(new int[]{1, 3, 3, 3, 3, 7});
        PdfPCell pdfPCell = new PdfPCell(new Phrase("No", font));
        pdfPCell.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell));
        PdfPCell pdfPCell2 = new PdfPCell(new Phrase("Team1", font));
        pdfPCell2.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell2));
        PdfPCell pdfPCell3 = new PdfPCell(new Phrase("Team2", font));
        pdfPCell3.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell3));
        PdfPCell pdfPCell4 = new PdfPCell(new Phrase("Venue", font));
        pdfPCell4.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell4));
        PdfPCell pdfPCell5 = new PdfPCell(new Phrase(HttpRequest.HEADER_DATE, font));
        pdfPCell5.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell5));
        PdfPCell pdfPCell6 = new PdfPCell(new Phrase("Result", font));
        pdfPCell6.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell6));
        pdfPTable.setSpacingBefore(20.0f);
        pdfPTable.setSpacingAfter(20.0f);
        pdfPTable.setWidthPercentage(100.0f);
        return pdfPTable;
    }

    public static PdfPTable createOversTableHeader() throws DocumentException {
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 18.0f, 1);
        font.setColor(BaseColor.WHITE);
        new BaseColor(0, 100, 0);
        PdfPTable pdfPTable = new PdfPTable(5);
        pdfPTable.setWidths(new int[]{2, 6, 2, 2, 10});
        PdfPCell pdfPCell = new PdfPCell(new Phrase("No", font));
        pdfPCell.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell));
        PdfPCell pdfPCell2 = new PdfPCell(new Phrase("Bowler", font));
        pdfPCell2.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell2));
        PdfPCell pdfPCell3 = new PdfPCell(new Phrase("Runs", font));
        pdfPCell3.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell3));
        PdfPCell pdfPCell4 = new PdfPCell(new Phrase("Wkts", font));
        pdfPCell4.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell4));
        PdfPCell pdfPCell5 = new PdfPCell(new Phrase("Per Ball Score", font));
        pdfPCell5.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell5));
        pdfPTable.setSpacingBefore(20.0f);
        pdfPTable.setSpacingAfter(20.0f);
        pdfPTable.setWidthPercentage(100.0f);
        return pdfPTable;
    }

    public PdfPTable createOversTable(int i, ArrayList<Over> arrayList) throws DocumentException {
        Phrase phrase;
        Font font = new Font(Font.FontFamily.HELVETICA, 18.0f, 1);
        Font font2 = new Font(Font.FontFamily.HELVETICA, 18.0f);
        PdfPTable pdfPTable = new PdfPTable(5);
        pdfPTable.setWidths(new int[]{2, 6, 2, 2, 10});
        try {
            phrase = new Phrase((i + 1) + "", font);
        } catch (Exception e) {
            e.printStackTrace();
            phrase = new Phrase("1", font);
        }
        PdfPCell pdfPCell = new PdfPCell(phrase);
        pdfPCell.setBorder(0);
        int i2 = i - 1;
        pdfPTable.addCell(changeCellBackgroundColor(i2, pdfPCell));
        PdfPCell pdfPCell2 = new PdfPCell(new Phrase(Formatter.cutNameHalf(arrayList.get(i).getBowler()) + "", this.nameFontBold));
        pdfPCell2.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i2, pdfPCell2));
        PdfPCell pdfPCell3 = new PdfPCell(new Phrase(arrayList.get(i).getOverscore() + "", this.numbersFontBold));
        pdfPCell3.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i2, pdfPCell3));
        PdfPCell pdfPCell4 = new PdfPCell(new Phrase(arrayList.get(i).getWickets() + "", this.numbersFontBold));
        pdfPCell4.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i2, pdfPCell4));
        PdfPCell pdfPCell5 = new PdfPCell(new Phrase(Formatter.getActualPerBallScore(arrayList.get(i).getPerballScore()), font2));
        pdfPCell5.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i2, pdfPCell5));
        pdfPTable.setWidthPercentage(100.0f);
        pdfPTable.setSpacingAfter(5.0f);
        return pdfPTable;
    }

    public static PdfPTable createBowlerStatsTableHeader() throws DocumentException {
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 18.0f, 1);
        font.setColor(BaseColor.WHITE);
        PdfPTable pdfPTable = new PdfPTable(7);
        pdfPTable.setWidths(new int[]{2, 3, 3, 3, 4, 5, 5});
        PdfPCell pdfPCell = new PdfPCell(new Phrase("No", font));
        pdfPCell.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell));
        PdfPCell pdfPCell2 = new PdfPCell(new Phrase("Wkts", font));
        pdfPCell2.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell2));
        PdfPCell pdfPCell3 = new PdfPCell(new Phrase("Runs", font));
        pdfPCell3.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell3));
        PdfPCell pdfPCell4 = new PdfPCell(new Phrase("Overs", font));
        pdfPCell4.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell4));
        PdfPCell pdfPCell5 = new PdfPCell(new Phrase("Opp", font));
        pdfPCell5.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell5));
        PdfPCell pdfPCell6 = new PdfPCell(new Phrase(HttpRequest.HEADER_DATE, font));
        pdfPCell6.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell6));
        PdfPCell pdfPCell7 = new PdfPCell(new Phrase("Venue", font));
        pdfPCell7.setBorder(0);
        pdfPTable.addCell(changeCellBackGroundColor(pdfPCell7));
        pdfPTable.setSpacingBefore(20.0f);
        pdfPTable.setSpacingAfter(20.0f);
        pdfPTable.setWidthPercentage(100.0f);
        return pdfPTable;
    }

    /* access modifiers changed from: package-private */
    public PdfPTable createBatsmanStatsTopTable(Chunk chunk, Chunk chunk2, Chunk chunk3, Chunk chunk4, Chunk chunk5, Chunk chunk6, Chunk chunk7, Chunk chunk8) throws DocumentException {
        PdfPTable pdfPTable = new PdfPTable(4);
        pdfPTable.setWidthPercentage(100.0f);
        PdfPCell pdfPCell = new PdfPCell();
        pdfPCell.setBorder(0);
        PdfPTable pdfPTable2 = new PdfPTable(2);
        pdfPTable2.setWidthPercentage(100.0f);
        PdfPCell pdfPCell2 = new PdfPCell(new Phrase(chunk));
        pdfPCell2.setBorder(0);
        pdfPTable2.addCell(pdfPCell2);
        PdfPCell pdfPCell3 = new PdfPCell(new Phrase(chunk2));
        pdfPCell3.setBorder(0);
        pdfPTable2.addCell(pdfPCell3);
        pdfPCell.addElement(pdfPTable2);
        pdfPTable.addCell(pdfPCell);
        PdfPCell pdfPCell4 = new PdfPCell();
        pdfPCell4.setBorder(0);
        PdfPTable pdfPTable3 = new PdfPTable(2);
        pdfPTable3.setWidthPercentage(100.0f);
        PdfPCell pdfPCell5 = new PdfPCell(new Phrase(chunk3));
        pdfPCell5.setBorder(0);
        pdfPTable3.addCell(pdfPCell5);
        PdfPCell pdfPCell6 = new PdfPCell(new Phrase(chunk4));
        pdfPCell6.setBorder(0);
        pdfPTable3.addCell(pdfPCell6);
        pdfPCell4.addElement(pdfPTable3);
        pdfPTable.addCell(pdfPCell4);
        PdfPCell pdfPCell7 = new PdfPCell();
        pdfPCell7.setBorder(0);
        PdfPTable pdfPTable4 = new PdfPTable(2);
        pdfPTable4.setWidthPercentage(100.0f);
        PdfPCell pdfPCell8 = new PdfPCell(new Phrase(chunk5));
        pdfPCell8.setBorder(0);
        pdfPTable4.addCell(pdfPCell8);
        PdfPCell pdfPCell9 = new PdfPCell(new Phrase(chunk6));
        pdfPCell9.setBorder(0);
        pdfPTable4.addCell(pdfPCell9);
        pdfPCell7.addElement(pdfPTable4);
        pdfPTable.addCell(pdfPCell7);
        PdfPCell pdfPCell10 = new PdfPCell();
        pdfPCell10.setBorder(0);
        PdfPTable pdfPTable5 = new PdfPTable(2);
        pdfPTable5.setWidthPercentage(100.0f);
        PdfPCell pdfPCell11 = new PdfPCell(new Phrase(chunk7));
        pdfPCell11.setBorder(0);
        pdfPTable5.addCell(pdfPCell11);
        PdfPCell pdfPCell12 = new PdfPCell(new Phrase(chunk8));
        pdfPCell12.setBorder(0);
        pdfPTable5.addCell(pdfPCell12);
        pdfPCell10.addElement(pdfPTable5);
        pdfPTable.addCell(pdfPCell10);
        return pdfPTable;
    }

    /* access modifiers changed from: package-private */
    public PdfPTable createBowlerStatsTopTable(Chunk chunk, Chunk chunk2, Chunk chunk3, Chunk chunk4, Chunk chunk5, Chunk chunk6, Chunk chunk7, Chunk chunk8, Chunk chunk9, Chunk chunk10) throws DocumentException {
        PdfPTable pdfPTable = new PdfPTable(5);
        pdfPTable.setWidthPercentage(100.0f);
        PdfPCell pdfPCell = new PdfPCell();
        pdfPCell.setBorder(0);
        PdfPTable pdfPTable2 = new PdfPTable(2);
        pdfPTable2.setWidthPercentage(100.0f);
        PdfPCell pdfPCell2 = new PdfPCell(new Phrase(chunk));
        pdfPCell2.setBorder(0);
        pdfPTable2.addCell(pdfPCell2);
        PdfPCell pdfPCell3 = new PdfPCell(new Phrase(chunk2));
        pdfPCell3.setBorder(0);
        pdfPTable2.addCell(pdfPCell3);
        pdfPCell.addElement(pdfPTable2);
        pdfPTable.addCell(pdfPCell);
        PdfPCell pdfPCell4 = new PdfPCell();
        pdfPCell4.setBorder(0);
        PdfPTable pdfPTable3 = new PdfPTable(2);
        pdfPTable3.setWidthPercentage(100.0f);
        PdfPCell pdfPCell5 = new PdfPCell(new Phrase(chunk3));
        pdfPCell5.setBorder(0);
        pdfPTable3.addCell(pdfPCell5);
        PdfPCell pdfPCell6 = new PdfPCell(new Phrase(chunk4));
        pdfPCell6.setBorder(0);
        pdfPTable3.addCell(pdfPCell6);
        pdfPCell4.addElement(pdfPTable3);
        pdfPTable.addCell(pdfPCell4);
        PdfPCell pdfPCell7 = new PdfPCell();
        pdfPCell7.setBorder(0);
        PdfPTable pdfPTable4 = new PdfPTable(2);
        pdfPTable4.setWidthPercentage(100.0f);
        PdfPCell pdfPCell8 = new PdfPCell(new Phrase(chunk5));
        pdfPCell8.setBorder(0);
        pdfPTable4.addCell(pdfPCell8);
        PdfPCell pdfPCell9 = new PdfPCell(new Phrase(chunk6));
        pdfPCell9.setBorder(0);
        pdfPTable4.addCell(pdfPCell9);
        pdfPCell7.addElement(pdfPTable4);
        pdfPTable.addCell(pdfPCell7);
        PdfPCell pdfPCell10 = new PdfPCell();
        pdfPCell10.setBorder(0);
        PdfPTable pdfPTable5 = new PdfPTable(2);
        pdfPTable5.setWidthPercentage(100.0f);
        PdfPCell pdfPCell11 = new PdfPCell(new Phrase(chunk7));
        pdfPCell11.setBorder(0);
        pdfPTable5.addCell(pdfPCell11);
        PdfPCell pdfPCell12 = new PdfPCell(new Phrase(chunk8));
        pdfPCell12.setBorder(0);
        pdfPTable5.addCell(pdfPCell12);
        pdfPCell10.addElement(pdfPTable5);
        pdfPTable.addCell(pdfPCell10);
        PdfPCell pdfPCell13 = new PdfPCell();
        pdfPCell13.setBorder(0);
        PdfPTable pdfPTable6 = new PdfPTable(2);
        pdfPTable6.setWidthPercentage(100.0f);
        PdfPCell pdfPCell14 = new PdfPCell(new Phrase(chunk9));
        pdfPCell14.setBorder(0);
        pdfPTable6.addCell(pdfPCell14);
        PdfPCell pdfPCell15 = new PdfPCell(new Phrase(chunk10));
        pdfPCell15.setBorder(0);
        pdfPTable6.addCell(pdfPCell15);
        pdfPCell13.addElement(pdfPTable6);
        pdfPTable.addCell(pdfPCell13);
        return pdfPTable;
    }

    /* access modifiers changed from: package-private */
    public PdfPTable createBatsmanStatsTopTable(Chunk chunk, Chunk chunk2) throws DocumentException {
        PdfPTable pdfPTable = new PdfPTable(1);
        Phrase phrase = new Phrase();
        phrase.add((Element) chunk);
        PdfPCell pdfPCell = new PdfPCell(phrase);
        pdfPCell.setBorder(0);
        pdfPTable.addCell(pdfPCell);
        PdfPCell pdfPCell2 = new PdfPCell(new Phrase(chunk2));
        pdfPCell2.setBorder(0);
        pdfPTable.addCell(pdfPCell2);
        pdfPTable.setWidthPercentage(100.0f);
        pdfPTable.setSpacingAfter(2.0f);
        return pdfPTable;
    }

    public PdfPTable createBatsmanStatsTable(int i, Batsman_Details batsman_Details) throws DocumentException {
        Font font = new Font(Font.FontFamily.HELVETICA, 18.0f, 1);
        Font font2 = this.nameFontCondensed;
        PdfPTable pdfPTable = new PdfPTable(8);
        pdfPTable.setWidths(new int[]{2, 3, 3, 4, 5, 5, 2, 2});
        int i2 = i - 1;
        PdfPCell changeCellBackgroundColor = changeCellBackgroundColor(i2, new PdfPCell(new Phrase(i + "", font2)));
        changeCellBackgroundColor.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor);
        Phrase phrase = new Phrase(batsman_Details.getScore() + "", font);
        String valueOf = String.valueOf(Html.fromHtml("<sup>*</sup>"));
        if (batsman_Details.getIsout() != null) {
            if (batsman_Details.getIsout().equals(PdfBoolean.FALSE)) {
                StringBuilder sb = new StringBuilder();
                sb.append(batsman_Details.getScore());
                sb.append("");
                sb.append(Html.fromHtml("<small>" + valueOf + "</small>"));
                phrase = new Phrase(sb.toString(), font);
            } else {
                phrase = new Phrase(batsman_Details.getScore() + "", font);
            }
        }
        PdfPCell pdfPCell = new PdfPCell(phrase);
        pdfPCell.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i2, pdfPCell));
        PdfPCell pdfPCell2 = new PdfPCell(new Phrase(batsman_Details.getBalls() + "", font));
        pdfPCell2.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i2, pdfPCell2));
        PdfPCell pdfPCell3 = new PdfPCell(new Phrase(Formatter.cutNameHalf(batsman_Details.getTeamoppname()), font2));
        pdfPCell3.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i2, pdfPCell3));
        PdfPCell pdfPCell4 = new PdfPCell(new Phrase(batsman_Details.getVenue(), font2));
        pdfPCell4.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i2, pdfPCell4));
        PdfPCell pdfPCell5 = new PdfPCell(new Phrase(batsman_Details.getDate() + "", font2));
        pdfPCell5.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i2, pdfPCell5));
        PdfPCell pdfPCell6 = new PdfPCell(new Phrase(batsman_Details.getFours() + "", font2));
        pdfPCell6.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i2, pdfPCell6));
        PdfPCell pdfPCell7 = new PdfPCell(new Phrase(batsman_Details.getSixes() + "", font2));
        pdfPCell7.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i2, pdfPCell7));
        pdfPTable.setWidthPercentage(100.0f);
        pdfPTable.setSpacingAfter(5.0f);
        return pdfPTable;
    }

    public PdfPTable createTournamentMatchesTable(int i, Match match2) throws DocumentException {
        Font font = this.numberFontCondensed;
        PdfPTable pdfPTable = new PdfPTable(6);
        pdfPTable.setWidths(new int[]{1, 3, 3, 3, 3, 7});
        int i2 = i - 1;
        PdfPCell changeCellBackgroundColor = changeCellBackgroundColor(i2, new PdfPCell(new Phrase(i + "", font)));
        changeCellBackgroundColor.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor);
        PdfPCell pdfPCell = new PdfPCell(new Phrase(Formatter.cutNameHalf(match2.getYourteam()) + "", font));
        pdfPCell.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i2, pdfPCell));
        PdfPCell pdfPCell2 = new PdfPCell(new Phrase(Formatter.cutNameHalf(match2.getOpponent()) + "", font));
        pdfPCell2.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i2, pdfPCell2));
        PdfPCell pdfPCell3 = new PdfPCell(new Phrase(match2.getVenue(), font));
        pdfPCell3.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i2, pdfPCell3));
        PdfPCell pdfPCell4 = new PdfPCell(new Phrase(match2.getDate(), font));
        pdfPCell4.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i2, pdfPCell4));
        try {
            PdfPCell pdfPCell5 = new PdfPCell(new Phrase(match2.getResult().split(":")[0] + "", font));
            pdfPCell5.setBorder(0);
            pdfPTable.addCell(changeCellBackgroundColor(i2, pdfPCell5));
        } catch (Exception e) {
            e.printStackTrace();
        }
        pdfPTable.setWidthPercentage(100.0f);
        pdfPTable.setSpacingAfter(5.0f);
        return pdfPTable;
    }

    public PdfPTable createBowlerStatsTable(int i, Bowler_Details bowler_Details) throws DocumentException {
        Font font = this.nameFontCondensed;
        Font font2 = new Font(Font.FontFamily.HELVETICA, 18.0f, 1);
        PdfPTable pdfPTable = new PdfPTable(7);
        pdfPTable.setWidths(new int[]{2, 3, 3, 3, 4, 5, 5});
        PdfPCell pdfPCell = new PdfPCell(new Phrase(i + "", font));
        pdfPCell.setBorder(0);
        int i2 = i - 1;
        pdfPTable.addCell(changeCellBackgroundColor(i2, pdfPCell));
        PdfPCell pdfPCell2 = new PdfPCell(new Phrase(bowler_Details.getWickets() + "", font2));
        pdfPCell2.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i2, pdfPCell2));
        PdfPCell pdfPCell3 = new PdfPCell(new Phrase(bowler_Details.getScore() + "", font2));
        pdfPCell3.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i2, pdfPCell3));
        PdfPCell pdfPCell4 = new PdfPCell(new Phrase(bowler_Details.getOver() + "." + bowler_Details.getBall(), font));
        pdfPCell4.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i2, pdfPCell4));
        PdfPCell pdfPCell5 = new PdfPCell(new Phrase(Formatter.cutNameHalf(bowler_Details.getOppname()), font));
        pdfPCell5.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i2, pdfPCell5));
        PdfPCell pdfPCell6 = new PdfPCell(new Phrase(bowler_Details.getDate() + "", font));
        pdfPCell6.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i2, pdfPCell6));
        PdfPCell pdfPCell7 = new PdfPCell(new Phrase(bowler_Details.getVenue() + "", font));
        pdfPCell7.setBorder(0);
        pdfPTable.addCell(changeCellBackgroundColor(i2, pdfPCell7));
        pdfPTable.setWidthPercentage(100.0f);
        pdfPTable.setSpacingAfter(5.0f);
        return pdfPTable;
    }

    public void generateBowlerPDF() throws FileNotFoundException {
        createBowlerFolder();
        writeBowlerStatsPdf();
    }

    public void generateBatsmanPDF() throws FileNotFoundException {
        createBatsmanFolder();
        writeBatsmanStatsPDF();
    }

    public void generateTournamentMatchesPDF() {
        createTournamentFolder();
        generateTournamentPDF(this.matches);
    }

    private void generateTournamentPDF(ArrayList<Match> arrayList) {
        Document document = new Document();
        try {
            final File file = new File(this.tournamentFolder, this.FILE_NAME);
            PdfWriter.getInstance(document, new FileOutputStream(file));
            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 18.0f, 1);
            Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 18.0f);
            new Font(Font.FontFamily.TIMES_ROMAN, 23.0f, 1).setColor(this.color_green);
            document.open();
            Font font3 = new Font();
            font3.setStyle(1);
            font3.setSize(8.0f);
            Paragraph paragraph = new Paragraph(" ", font2);
            paragraph.setAlignment(1);
            int i = 0;
            Paragraph paragraph2 = new Paragraph(arrayList.get(0).getTournament(), font);
            paragraph2.setAlignment(1);
            paragraph.add((Element) paragraph2);
            Paragraph paragraph3 = new Paragraph("Matches");
            paragraph3.setAlignment(1);
            paragraph.add((Element) paragraph3);
            paragraph.add((Element) new Paragraph(" "));
            paragraph.add((Element) new Paragraph(" "));
            document.add(paragraph);
            document.add(createTournamentMatchesTableHeader());
            while (i < arrayList.size()) {
                int i2 = i + 1;
                document.add(createTournamentMatchesTable(i2, arrayList.get(i)));
                i = i2;
            }
            Paragraph paragraph4 = new Paragraph("Generated by Cricket Scorer", font2);
            paragraph4.setAlignment(2);
            document.add(paragraph4);
            document.close();
            System.out.println("Done");
            UtilityFunctions.showAlertDialog(this.context, "Pdf Saved", "Pdf saved in /sdcard/Cricket Scorer/Tournaments", "Open", "Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    UtilityFunctions.openDocument(PDFGenerator.this.context, file);
                }
            });
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
            MyToast.showToast(this.context, e.toString());
        } catch (IOException e2) {
            e2.printStackTrace();
            MyToast.showToast(this.context, e2.toString());
        }
    }

    private void createTournamentFolder() {
        this.tournamentFolder = new File(Environment.getExternalStorageDirectory() + "/Cricket Scorer/Tournaments");
        if (!this.tournamentFolder.exists()) {
            this.tournamentFolder.mkdirs();
        }
    }
}
