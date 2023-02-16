package apps.shehryar.com.cricketscroingappPro.Team;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.Toast;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Model.SharedData;
import apps.shehryar.com.cricketscroingappPro.MyToast;
import apps.shehryar.com.cricketscroingappPro.PermissionAsker;
import apps.shehryar.com.cricketscroingappPro.PlayersLoader;
import apps.shehryar.com.cricketscroingappPro.UpgradeToProDialog;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.AdsLoader;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.Constants;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.ImageSharer;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.SharedPrefsHelper;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.UtilityFunctions;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import com.itextpdf.text.xml.xmp.XmpMMProperties;
import java.util.ArrayList;
import util.IabHelper;
import util.IabResult;
import util.Purchase;

public class YourTeamStats extends AppCompatActivity implements UpgradeToProDialog.UpgradeToProCallBack {
    DBHelper dbHelper;
    /* access modifiers changed from: private */
    public Runnable displayAd;
    Handler handler;
    /* access modifiers changed from: private */
    public ProgressDialog loading;
    private IabHelper mHelper;
    InterstitialAd mInterstitialAd;
    private IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener;
    /* access modifiers changed from: private */
    public int matchType;
    ArrayList<Match> matches;
    ImageButton optionsMenu;
    ArrayList<String> playernames;
    Spinner spinner;
    TabLayout tabLayout;
    ArrayList<String> team_names;
    /* access modifiers changed from: private */
    public ArrayList<Team> teams;
    ViewPager viewPager;
    Team yourteam;

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.mInterstitialAd = AdsLoader.initializeInterstitialAd(this, this.mInterstitialAd, (AdListener) null, Constants.matchesHistoryInterstitialAdId);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_your_team_stats);
        AdsLoader.loadBannerAd(this, this, "ca-app-pub-7751307421283386/4086505351", R.id.fragmentmatchesad);
        this.handler = new Handler(Looper.getMainLooper());
        this.displayAd = new Runnable() {
            public void run() {
                YourTeamStats.this.runOnUiThread(new Runnable() {
                    public void run() {
                        if (YourTeamStats.this.mInterstitialAd.isLoaded() && !SharedPrefsHelper.isPro(YourTeamStats.this)) {
                            YourTeamStats.this.mInterstitialAd.show();
                        }
                    }
                });
            }
        };
        this.mInterstitialAd = AdsLoader.initializeInterstitialAd(this, this.mInterstitialAd, new AdListener() {
            public void onAdLoaded() {
                super.onAdLoaded();
                if (!SharedPrefsHelper.isPro(YourTeamStats.this)) {
                    YourTeamStats.this.handler.post(YourTeamStats.this.displayAd);
                }
            }

            public void onAdClosed() {
                super.onAdClosed();
                AdsLoader.requestNewInterstitial(YourTeamStats.this.mInterstitialAd);
            }
        }, Constants.matchesHistoryInterstitialAdId);
        this.mHelper = new IabHelper(this, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7u5kRGfbQ2KgIHfyHPw4TRR1KPmNG+PEfaJuOe6ohNhqjCy1iQyfhgvRis3AY6Bs8osPDFxI76G2XzAycB9/v6G0PCVEG4ITVkZCnVtTOx2r8p2f24LW3OQNcZrTFCF0mW7cSA2XnWEe0DYCByC0Z5TG+95FhL5rO1TJECEMum3p54l2dYnOgmJTOrnWp0ry6Jv3xC+26T0D07PFxGQoeUxOGcuL0a+rvi4IyWrwUJCX66jVh5EYnNdZ7trtAjtLEjqyAWAzcc3yUbMPUZtw1Do0S+HpKtyyeruZzpJgCl0Hpgdrz4P+7x6dCLI6QkFkrhngqBQNnGIQcev0sB6UewIDAQAB");
        this.mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
            public void onIabPurchaseFinished(IabResult iabResult, Purchase purchase) {
                if (iabResult.isFailure()) {
                    Log.d("purchaising", "Error purchasing: " + iabResult);
                    MyToast.showToast(YourTeamStats.this, "Billing unsuccessfull");
                    SharedPrefsHelper.insertProFeatures(YourTeamStats.this, false);
                } else if (purchase.getSku().equals("all_pro_features")) {
                    MyToast.showToast(YourTeamStats.this, "App is upgraded to PRO");
                    SharedPrefsHelper.insertProFeatures(YourTeamStats.this, true);
                }
            }
        };
        getSupportActionBar().hide();
        getSupportActionBar().setTitle((CharSequence) XmpMMProperties.HISTORY);
        this.dbHelper = new DBHelper(this);
        if (!SharedPrefsHelper.checkBowlerTypeUpdated(this)) {
            try {
                this.dbHelper.changeBowlerTypeOnce();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        UtilityFunctions.copyOversFromSharedPrefsIntoDB(this);
        this.viewPager = (ViewPager) findViewById(R.id.viewpager);
        this.viewPager.setOffscreenPageLimit(6);
        this.tabLayout = (TabLayout) findViewById(R.id.tablayout);
        this.spinner = (Spinner) findViewById(R.id.spinner);
        this.optionsMenu = (ImageButton) findViewById(R.id.options_menu);
        ArrayList arrayList = new ArrayList();
        arrayList.add("All Matches");
        arrayList.add("Test Matches");
        arrayList.add("Limited Over Matches");
        this.spinner.setAdapter(new ArrayAdapter(this, R.layout.spinner_text_view, arrayList));
        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                switch (i) {
                    case 0:
                        int unused = YourTeamStats.this.matchType = 3;
                        SharedData.getInstance().setSelectedMatchType(YourTeamStats.this.matchType);
                        new loadData().execute(new Integer[]{3});
                        return;
                    case 1:
                        int unused2 = YourTeamStats.this.matchType = 1;
                        SharedData.getInstance().setSelectedMatchType(YourTeamStats.this.matchType);
                        new loadData().execute(new Integer[]{1});
                        return;
                    case 2:
                        int unused3 = YourTeamStats.this.matchType = 2;
                        SharedData.getInstance().setSelectedMatchType(YourTeamStats.this.matchType);
                        new loadData().execute(new Integer[]{2});
                        return;
                    default:
                        return;
                }
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                YourTeamStats.this.loading.dismiss();
            }
        });
        this.optionsMenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(YourTeamStats.this, YourTeamStats.this.optionsMenu);
                popupMenu.getMenuInflater().inflate(R.menu.share_image_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() != R.id.share_image) {
                            return true;
                        }
                        if (!SharedPrefsHelper.isPro(YourTeamStats.this)) {
                            YourTeamStats.this.showUpgradeToProDialog();
                            return true;
                        } else if (!PermissionAsker.checkAPIVersion()) {
                            ImageSharer.shareImage(YourTeamStats.this);
                            return true;
                        } else if (PermissionAsker.checkIfAlreadyhavePermission(YourTeamStats.this)) {
                            ImageSharer.shareImage(YourTeamStats.this);
                            return true;
                        } else {
                            PermissionAsker.requestForSpecificPermission(105, YourTeamStats.this);
                            return true;
                        }
                    }
                });
                popupMenu.show();
            }
        });
        if (PermissionAsker.checkAPIVersion() && !PermissionAsker.checkIfAlreadyhavePermission(this)) {
            PermissionAsker.requestForSpecificPermission(0, this);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share_image_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.share_image) {
            return true;
        }
        if (!SharedPrefsHelper.isPro(this)) {
            showUpgradeToProDialog();
            return true;
        } else if (!PermissionAsker.checkAPIVersion()) {
            ImageSharer.shareImage(this);
            return true;
        } else if (PermissionAsker.checkIfAlreadyhavePermission(this)) {
            ImageSharer.shareImage(this);
            return true;
        } else {
            PermissionAsker.requestForSpecificPermission(105, this);
            return true;
        }
    }

    public void onPurchaseSuccessfull() {
        Toast.makeText(getApplicationContext(), "Purchase Successful", 0).show();
    }

    public void onPurchaseFailed() {
        Toast.makeText(getApplicationContext(), "Purchase UnSuccessful", 0).show();
    }

    /* access modifiers changed from: private */
    public void showUpgradeToProDialog() {
        UpgradeToProDialog upgradeToProDialog = new UpgradeToProDialog();
        upgradeToProDialog.setListener(this);
        upgradeToProDialog.show(getFragmentManager(), UpgradeToProDialog.class.toString());
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 0 && i2 == 1) {
            new loadData().execute(new Integer[]{3});
        }
        if (i == 10001 && i2 == -1) {
            SharedPrefsHelper.insertProFeatures(this, true);
            MyToast.showToast(this, "Congratulations on being a PRO Cricket Scorer");
            if (PermissionAsker.checkAPIVersion()) {
                if (PermissionAsker.checkIfAlreadyhavePermission(this)) {
                    ImageSharer.shareImage(this);
                } else {
                    PermissionAsker.requestForSpecificPermission(104, this);
                }
            }
        } else if (i == 10001 && i2 == 0) {
            SharedPrefsHelper.insertProFeatures(this, false);
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i != 104) {
            super.onRequestPermissionsResult(i, strArr, iArr);
        } else if (iArr[0] == 0) {
            ImageSharer.shareImage(this);
        } else {
            MyToast.showToast(this, "Image not generated. Permission denied.");
        }
    }

    public void onBackPressed() {
        setResult(2);
        finish();
    }

    class loadData extends AsyncTask<Integer, Void, Integer> {
        private PlayersLoader playersLoader;
        private ArrayList<String> teamnames;

        loadData() {
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
            try {
                ProgressDialog unused = YourTeamStats.this.loading = new ProgressDialog(YourTeamStats.this);
                YourTeamStats.this.loading.setCancelable(true);
                YourTeamStats.this.loading.setMessage("Loading History");
                YourTeamStats.this.loading.setProgressStyle(0);
                YourTeamStats.this.loading.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /* access modifiers changed from: protected */
        public Integer doInBackground(Integer[] numArr) {
            YourTeamStats.this.dbHelper = new DBHelper(YourTeamStats.this);
            if (!YourTeamStats.this.dbHelper.getYourTeamName().isEmpty()) {
                YourTeamStats.this.yourteam = YourTeamStats.this.dbHelper.getAddedTeamsAndPlayers(0);
                YourTeamStats.this.playernames = YourTeamStats.this.yourteam.getAllPlayersNames();
            } else {
                YourTeamStats.this.yourteam = new Team();
                YourTeamStats.this.playernames = new ArrayList<>();
            }
            this.playersLoader = new PlayersLoader(YourTeamStats.this);
            ArrayList unused = YourTeamStats.this.teams = new ArrayList();
            ArrayList unused2 = YourTeamStats.this.teams = this.playersLoader.getAllTeamPlayers(numArr[0].intValue());
            this.teamnames = this.playersLoader.getTeamNames(YourTeamStats.this.teams);
            YourTeamStats.this.matches = YourTeamStats.this.dbHelper.getMatches(numArr[0].intValue());
            return numArr[0];
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Integer num) {
            super.onPostExecute(num);
            YourTeamStats.this.loading.dismiss();
            YourTeamStats.this.viewPager = (ViewPager) YourTeamStats.this.findViewById(R.id.viewpager);
            YourTeamStats.this.tabLayout = (TabLayout) YourTeamStats.this.findViewById(R.id.tablayout);
            try {
                YourTeamStats.this.viewPager.setAdapter(new YourTeamViewPagerAdapter(YourTeamStats.this.getSupportFragmentManager(), YourTeamStats.this, YourTeamStats.this, YourTeamStats.this.yourteam, YourTeamStats.this.playernames, this.teamnames, YourTeamStats.this.teams, YourTeamStats.this.matches, num.intValue()));
                YourTeamStats.this.tabLayout.setupWithViewPager(YourTeamStats.this.viewPager);
                YourTeamStats.this.tabLayout.setTabTextColors(ContextCompat.getColor(YourTeamStats.this, R.color.white), ContextCompat.getColor(YourTeamStats.this, R.color.white));
            } catch (Resources.NotFoundException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException | IllegalStateException unused) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResumeFragments() {
        super.onResumeFragments();
    }
}
