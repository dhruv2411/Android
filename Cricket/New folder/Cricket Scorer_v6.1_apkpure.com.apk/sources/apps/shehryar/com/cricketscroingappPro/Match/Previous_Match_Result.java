package apps.shehryar.com.cricketscroingappPro.Match;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman;
import apps.shehryar.com.cricketscroingappPro.Bowler.Bowler;
import apps.shehryar.com.cricketscroingappPro.Custom_Suggestions_Adapter;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.MyToast;
import apps.shehryar.com.cricketscroingappPro.PDFGenerator;
import apps.shehryar.com.cricketscroingappPro.ShareThisMatchDialog;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import apps.shehryar.com.cricketscroingappPro.UpgradeToProDialog;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.FragmentLoader;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.ImageSharer;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.SharedPrefsHelper;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import util.IabHelper;
import util.IabResult;
import util.Purchase;

public class Previous_Match_Result extends AppCompatActivity implements UpgradeToProDialog.UpgradeToProCallBack {
    private static final int EXTERNAL_STORAGE_PERMISSION_CONSTANT = 100;
    private static final int REQUEST_PERMISSION_SETTING = 101;
    DBHelper dbHelper;
    boolean hideDeleteMatch = false;
    /* access modifiers changed from: private */
    public int index;
    private IabHelper mHelper;
    private IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener;
    Match match;
    private SharedPreferences permissionStatus;
    /* access modifiers changed from: private */
    public boolean sentToSettings = false;
    TabLayout tabLayout;
    ViewPager viewPager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.previous_match);
        this.dbHelper = new DBHelper(this);
        this.mHelper = new IabHelper(this, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7u5kRGfbQ2KgIHfyHPw4TRR1KPmNG+PEfaJuOe6ohNhqjCy1iQyfhgvRis3AY6Bs8osPDFxI76G2XzAycB9/v6G0PCVEG4ITVkZCnVtTOx2r8p2f24LW3OQNcZrTFCF0mW7cSA2XnWEe0DYCByC0Z5TG+95FhL5rO1TJECEMum3p54l2dYnOgmJTOrnWp0ry6Jv3xC+26T0D07PFxGQoeUxOGcuL0a+rvi4IyWrwUJCX66jVh5EYnNdZ7trtAjtLEjqyAWAzcc3yUbMPUZtw1Do0S+HpKtyyeruZzpJgCl0Hpgdrz4P+7x6dCLI6QkFkrhngqBQNnGIQcev0sB6UewIDAQAB");
        this.mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
            public void onIabPurchaseFinished(IabResult iabResult, Purchase purchase) {
                if (iabResult.isFailure()) {
                    Log.d("purchaising", "Error purchasing: " + iabResult);
                    MyToast.showToast(Previous_Match_Result.this, "Billing unsuccessfull");
                } else if (purchase.getSku().equals("all_pro_features")) {
                    MyToast.showToast(Previous_Match_Result.this, "App is upgraded to PRO");
                    SharedPrefsHelper.insertProFeatures(Previous_Match_Result.this, true);
                }
            }
        };
        this.tabLayout = (TabLayout) findViewById(R.id.tablayout);
        this.viewPager = (ViewPager) findViewById(R.id.viewpager);
        this.match = (Match) getIntent().getSerializableExtra(DBHelper.TABLE_MATCH);
        this.index = getIntent().getIntExtra("index", 0);
        if (this.match.isTestMatch) {
            this.viewPager.setOffscreenPageLimit(4);
        } else {
            this.viewPager.setOffscreenPageLimit(2);
        }
        if ("thismatch".equals(getIntent().getStringExtra("code"))) {
            this.hideDeleteMatch = true;
        }
        new LoadTeamStats().execute(new Void[0]);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.generate_pdf_menu, menu);
        if (!this.hideDeleteMatch) {
            return true;
        }
        menu.getItem(2).setVisible(false);
        menu.getItem(5).setVisible(false);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId != R.id.share_image) {
            switch (itemId) {
                case R.id.generate_pdf /*2131755751*/:
                    try {
                        generatePDF();
                        return true;
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        return true;
                    }
                case R.id.share_pdf /*2131755752*/:
                    if (SharedPrefsHelper.isPro(this)) {
                        sharePdf();
                        return true;
                    }
                    showUpgradeToProDialog();
                    return true;
                case R.id.share_match /*2131755753*/:
                    ShareThisMatchDialog shareThisMatchDialog = new ShareThisMatchDialog();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(DBHelper.TABLE_MATCH, this.match);
                    shareThisMatchDialog.setArguments(bundle);
                    shareThisMatchDialog.show(getFragmentManager(), "share this match dialog");
                    return true;
                case R.id.view_match_summary /*2131755754*/:
                    Bundle bundle2 = new Bundle();
                    bundle2.putSerializable(DBHelper.TABLE_MATCH, this.match);
                    FragmentLoader.showDialogFragmet(this, new MatchSummaryDialog(), bundle2, "match summary dialog");
                    return true;
                case R.id.delete_match /*2131755755*/:
                    deleteMatch(this.match);
                    return true;
                case R.id.update_match_result /*2131755756*/:
                    updateMatchResult(this.match);
                    return true;
                case R.id.view_motm /*2131755757*/:
                    if (this.match.getResult().isEmpty() || this.match.getResult().equals("No result")) {
                        MyToast.showToast(this, "Man of the match cannot be selected. Please finish match first");
                        return true;
                    }
                    new ManOfTheMatchDialogFragment(this, this.match).show(getFragmentManager(), "motm dialog");
                    return true;
                default:
                    return true;
            }
        } else if (SharedPrefsHelper.isPro(this)) {
            ImageSharer.shareImage(this);
            return true;
        } else {
            showUpgradeToProDialog();
            return true;
        }
    }

    public void onPurchaseSuccessfull() {
        Toast.makeText(getApplicationContext(), "Purchase Successful", 0).show();
    }

    public void onPurchaseFailed() {
        Toast.makeText(getApplicationContext(), "Purchase UnSuccessful", 0).show();
    }

    private void showUpgradeToProDialog() {
        UpgradeToProDialog upgradeToProDialog = new UpgradeToProDialog();
        upgradeToProDialog.setListener(this);
        upgradeToProDialog.show(getFragmentManager(), UpgradeToProDialog.class.toString());
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 10001 && i2 == -1) {
            SharedPrefsHelper.insertProFeatures(this, true);
            MyToast.showToast(this, "Congratulations on being a PRO Cricket Scorer");
        } else if (i == 10001 && i2 == 0) {
            SharedPrefsHelper.insertProFeatures(this, false);
        }
    }

    private void sharePdf() {
        File file;
        Intent intent;
        try {
            generatePDF();
            file = new File(Environment.getExternalStorageDirectory().toString() + "/Cricket Scorer/" + this.match.getTeam1().getName() + " VS " + this.match.getTeam2().getName() + " " + this.match.getDate() + ".pdf");
            intent = new Intent("android.intent.action.SEND");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            file = new File(Environment.getExternalStorageDirectory().toString() + "/Cricket Scorer/" + this.match.getTeam1().getName() + " VS " + this.match.getTeam2().getName() + " " + this.match.getDate() + ".pdf");
            intent = new Intent("android.intent.action.SEND");
        } catch (Throwable th) {
            File file2 = new File(Environment.getExternalStorageDirectory().toString() + "/Cricket Scorer/" + this.match.getTeam1().getName() + " VS " + this.match.getTeam2().getName() + " " + this.match.getDate() + ".pdf");
            Intent intent2 = new Intent("android.intent.action.SEND");
            intent2.setType("application/pdf");
            intent2.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(getApplicationContext(), "com.CricketScorer.fileprovider", file2));
            startActivity(Intent.createChooser(intent2, "share file with"));
            throw th;
        }
        intent.setType("application/pdf");
        intent.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(getApplicationContext(), "com.CricketScorer.fileprovider", file));
        startActivity(Intent.createChooser(intent, "share file with"));
    }

    private void updateMatchResult(final Match match2) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.update_match_result_layout);
        dialog.setCancelable(true);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_2;
        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 17;
        attributes.width = -1;
        window.setAttributes(attributes);
        dialog.show();
        final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) dialog.findViewById(R.id.edResult);
        ArrayList arrayList = new ArrayList();
        arrayList.add("Match stopped due to rain");
        arrayList.add("Match stopped due to bad lights");
        autoCompleteTextView.setAdapter(new Custom_Suggestions_Adapter(this, R.layout.custom_suggestions_layout, arrayList));
        ((Button) dialog.findViewById(R.id.wicketdialgobnext)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String str;
                autoCompleteTextView.showDropDown();
                String[] split = match2.getResult().split(":");
                if (autoCompleteTextView.getText().toString().isEmpty()) {
                    str = null;
                } else if (split.length > 1) {
                    str = autoCompleteTextView.getText().toString() + ":" + split[1];
                } else {
                    str = autoCompleteTextView.getText().toString();
                }
                if (str != null) {
                    new DBHelper(Previous_Match_Result.this).UpdateMatchResult(match2.getMatchID(), str);
                }
                MyToast.showToast(Previous_Match_Result.this, "Result Updated Successfully");
                dialog.dismiss();
            }
        });
    }

    private void deleteMatch(final Match match2) {
        new AlertDialog.Builder(this).setTitle("Delete this match").setMessage("Are you sure you want to delete this match.").setPositiveButton(17039370, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Previous_Match_Result.this.dbHelper.DeleteMatch(match2);
                Intent intent = new Intent();
                intent.putExtra("index", Previous_Match_Result.this.index);
                Previous_Match_Result.this.setResult(1, intent);
                Previous_Match_Result.this.finish();
            }
        }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).create().show();
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (i == 100 && iArr.length > 0 && iArr[0] == 0) {
            PDFGenerator pDFGenerator = new PDFGenerator((Context) this, this.match);
            try {
                pDFGenerator.createFolderAndFile();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            pDFGenerator.writePDF();
        }
    }

    private void generatePDF() throws FileNotFoundException {
        this.permissionStatus = getSharedPreferences("permissionStatus", 0);
        if (ActivityCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Need Storage Permission");
                builder.setMessage("This app needs storage permission.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        ActivityCompat.requestPermissions(Previous_Match_Result.this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 100);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();
            } else if (this.permissionStatus.getBoolean("android.permission.WRITE_EXTERNAL_STORAGE", false)) {
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle("Need Storage Permission");
                builder2.setMessage("This app needs storage permission.");
                builder2.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        boolean unused = Previous_Match_Result.this.sentToSettings = true;
                        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                        intent.setData(Uri.fromParts("package", Previous_Match_Result.this.getPackageName(), (String) null));
                        Previous_Match_Result.this.startActivityForResult(intent, 101);
                        Toast.makeText(Previous_Match_Result.this.getBaseContext(), "Go to Permissions to Grant Storage", 1).show();
                    }
                });
                builder2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder2.show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 100);
            }
            SharedPreferences.Editor edit = this.permissionStatus.edit();
            edit.putBoolean("android.permission.WRITE_EXTERNAL_STORAGE", true);
            edit.commit();
            return;
        }
        PDFGenerator pDFGenerator = new PDFGenerator((Context) this, this.match);
        pDFGenerator.createFolderAndFile();
        pDFGenerator.writePDF();
    }

    class LoadTeamStats extends AsyncTask<Void, Void, ArrayList<Batsman>> {
        private ProgressDialog loading;

        LoadTeamStats() {
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
            this.loading = new ProgressDialog(Previous_Match_Result.this);
            this.loading.setCancelable(false);
            this.loading.setMessage("Loading Match. Please Wait...");
            this.loading.setProgressStyle(0);
            this.loading.setIndeterminate(true);
            this.loading.show();
        }

        /* access modifiers changed from: protected */
        public ArrayList<Batsman> doInBackground(Void... voidArr) {
            if (!Previous_Match_Result.this.hideDeleteMatch) {
                Previous_Match_Result.this.match.getTeam1().setBatsmans_list(Previous_Match_Result.this.dbHelper.getBatsmans(Previous_Match_Result.this.match.getTeam_Yours_id(), Previous_Match_Result.this.match.getMatchID()));
                Previous_Match_Result.this.match.getTeam1().setBowlers_list(Previous_Match_Result.this.dbHelper.getBowlers(Previous_Match_Result.this.match.getTeam_opp_id(), Previous_Match_Result.this.match.getMatchID()));
                Previous_Match_Result.this.match.getTeam1().setFallOfWicketses(Previous_Match_Result.this.dbHelper.getFallOfWickets(Previous_Match_Result.this.match.getMatchID(), Previous_Match_Result.this.match.getTeam_Yours_id()));
                Previous_Match_Result.this.match.getTeam1().setOvers_list(Previous_Match_Result.this.dbHelper.getOvers(Previous_Match_Result.this.match.getMatchID(), Previous_Match_Result.this.match.getTeam1().getTeamID()));
                Previous_Match_Result.this.match.getTeam2().setBatsmans_list(Previous_Match_Result.this.dbHelper.getBatsmans(Previous_Match_Result.this.match.getTeam_opp_id(), Previous_Match_Result.this.match.getMatchID()));
                Previous_Match_Result.this.match.getTeam2().setBowlers_list(Previous_Match_Result.this.dbHelper.getBowlers(Previous_Match_Result.this.match.getTeam_Yours_id(), Previous_Match_Result.this.match.getMatchID()));
                Previous_Match_Result.this.match.getTeam2().setFallOfWicketses(Previous_Match_Result.this.dbHelper.getFallOfWickets(Previous_Match_Result.this.match.getMatchID(), Previous_Match_Result.this.match.getTeam_opp_id()));
                Previous_Match_Result.this.match.getTeam2().setOvers_list(Previous_Match_Result.this.dbHelper.getOvers(Previous_Match_Result.this.match.getMatchID(), Previous_Match_Result.this.match.getTeam2().getTeamID()));
                if (Previous_Match_Result.this.match.isTestMatch) {
                    Previous_Match_Result.this.match.getTeam3().setBatsmans_list(Previous_Match_Result.this.dbHelper.getBatsmans(Previous_Match_Result.this.match.getTeam3().getTeamID(), Previous_Match_Result.this.match.getMatchID()));
                    Previous_Match_Result.this.match.getTeam3().setBowlers_list(Previous_Match_Result.this.dbHelper.getBowlers(Previous_Match_Result.this.match.getTeam4().getTeamID(), Previous_Match_Result.this.match.getMatchID()));
                    Previous_Match_Result.this.match.getTeam3().setFallOfWicketses(Previous_Match_Result.this.dbHelper.getFallOfWickets(Previous_Match_Result.this.match.getMatchID(), Previous_Match_Result.this.match.getTeam3().getTeamID()));
                    Previous_Match_Result.this.match.getTeam3().setOvers_list(Previous_Match_Result.this.dbHelper.getOvers(Previous_Match_Result.this.match.getMatchID(), Previous_Match_Result.this.match.getTeam3().getTeamID()));
                    Previous_Match_Result.this.match.getTeam4().setBatsmans_list(Previous_Match_Result.this.dbHelper.getBatsmans(Previous_Match_Result.this.match.getTeam4().getTeamID(), Previous_Match_Result.this.match.getMatchID()));
                    Previous_Match_Result.this.match.getTeam4().setBowlers_list(Previous_Match_Result.this.dbHelper.getBowlers(Previous_Match_Result.this.match.getTeam3().getTeamID(), Previous_Match_Result.this.match.getMatchID()));
                    Previous_Match_Result.this.match.getTeam4().setFallOfWicketses(Previous_Match_Result.this.dbHelper.getFallOfWickets(Previous_Match_Result.this.match.getMatchID(), Previous_Match_Result.this.match.getTeam4().getTeamID()));
                    Previous_Match_Result.this.match.getTeam4().setOvers_list(Previous_Match_Result.this.dbHelper.getOvers(Previous_Match_Result.this.match.getMatchID(), Previous_Match_Result.this.match.getTeam4().getTeamID()));
                }
            } else {
                Previous_Match_Result.this.swapBowlers(Previous_Match_Result.this.match.getTeam1(), Previous_Match_Result.this.match.getTeam2());
                Previous_Match_Result.this.swapBowlers(Previous_Match_Result.this.match.getTeam3(), Previous_Match_Result.this.match.getTeam4());
            }
            return new ArrayList<>();
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(ArrayList<Batsman> arrayList) {
            super.onPostExecute(arrayList);
            this.loading.dismiss();
            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(Previous_Match_Result.this.getSupportFragmentManager(), Previous_Match_Result.this, Previous_Match_Result.this.match);
            Previous_Match_Result.this.tabLayout.setTabTextColors(Previous_Match_Result.this.getResources().getColor(R.color.white), Previous_Match_Result.this.getResources().getColor(R.color.white));
            try {
                Previous_Match_Result.this.viewPager.setAdapter(viewPagerAdapter);
                Previous_Match_Result.this.tabLayout.setupWithViewPager(Previous_Match_Result.this.viewPager);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: private */
    public void swapBowlers(Team team, Team team2) {
        ArrayList<Bowler> bowlers_list = team.getBowlers_list();
        team.setBowlers_list(team2.getBowlers_list());
        team2.setBowlers_list(bowlers_list);
    }
}
