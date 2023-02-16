package apps.shehryar.com.cricketscroingappPro.Batsman;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import apps.shehryar.com.CheckBoxListItem;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Adapters.ViewPagerAdapterBatsmanDetails;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.FragmentChooseItems;
import apps.shehryar.com.cricketscroingappPro.Listeners.FragmentChooseItemListener;
import apps.shehryar.com.cricketscroingappPro.Model.SharedData;
import apps.shehryar.com.cricketscroingappPro.MyToast;
import apps.shehryar.com.cricketscroingappPro.PDFGenerator;
import apps.shehryar.com.cricketscroingappPro.PermissionAsker;
import apps.shehryar.com.cricketscroingappPro.Player.Player;
import apps.shehryar.com.cricketscroingappPro.PlayersLoader;
import apps.shehryar.com.cricketscroingappPro.UpgradeToProDialog;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.AdsLoader;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.Constants;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.FontProvider;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.ImageSharer;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.PdfSharer;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.SharedPrefsHelper;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.TinyDB;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.UtilityFunctions;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import util.IabHelper;
import util.IabResult;
import util.Purchase;

public class BatsmanDetailActivity extends AppCompatActivity implements UpgradeToProDialog.UpgradeToProCallBack, FragmentChooseItemListener {
    /* access modifiers changed from: private */
    public ArrayList<Long> allTeamIds;
    @BindView(2131755205)
    Button bChooseTeams;
    Batsman batsman;
    String batsmanTeams;
    ArrayList<Batsman_Details> batsmen;
    DBHelper dbHelper;
    ImageView details_button_arrow;
    private IabHelper mHelper;
    InterstitialAd mInterstitialAd;
    private IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener;
    PDFGenerator pdfGenerator;
    Player player;
    PlayersLoader playersLoader;
    ImageView profileImage;
    ArrayList<String> selectedTeamNames;
    LinearLayout statsButtonLayout;
    LinearLayout statsLayout;
    @BindView(2131755206)
    TabLayout tabLayout;
    ArrayList<String> teamNames;
    ArrayList<CheckBoxListItem> teamNamesCheckBoxListItems;
    String teamname;
    TextView textView;
    TextView tvBatFours;
    TextView tvBatSixes;
    TextView tvbatNotOUts;
    TextView tvbatPoints;
    TextView tvbatavg;
    TextView tvbatbest;
    TextView tvbatfifties;
    TextView tvbathundreds;
    TextView tvbatmatches;
    TextView tvbatscore;
    TextView tvbatsr;
    TextView tvbatthirtes;
    @BindView(2131755207)
    ViewPager viewPager;
    ViewPagerAdapterBatsmanDetails viewPagerAdapterBatsmanDetails;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_batsman_detail);
        ButterKnife.bind((Activity) this);
        this.batsman = (Batsman) getIntent().getSerializableExtra("batsman");
        this.teamname = getIntent().getStringExtra("teamname");
        this.player = (Player) getIntent().getSerializableExtra(DBHelper.TABLE_PLAYERS);
        this.allTeamIds = (ArrayList) getIntent().getSerializableExtra("allTeamIds");
        TinyDB tinyDB = new TinyDB(this);
        this.batsmanTeams = tinyDB.getString("batsman " + this.batsman.getName() + " " + this.teamname);
        try {
            this.selectedTeamNames = UtilityFunctions.getStringArrayListFromStrings(this.batsmanTeams);
        } catch (Exception e) {
            e.printStackTrace();
            this.selectedTeamNames = new ArrayList<>();
        }
        if (!this.selectedTeamNames.contains(this.teamname)) {
            this.selectedTeamNames.add(0, this.teamname);
        }
        if (this.selectedTeamNames.isEmpty()) {
            this.batsman.setTeamNames((ArrayList<String>) null);
        } else {
            this.batsman.setTeamNames(this.selectedTeamNames);
        }
        setUpViewPager();
        AdsLoader.loadBannerAd(this, this, Constants.batsmanDetailsAdId, R.id.batsmanDetailAd);
        this.mInterstitialAd = AdsLoader.initializeInterstitialAd(this, this.mInterstitialAd, new AdListener() {
            public void onAdClosed() {
                super.onAdClosed();
                BatsmanDetailActivity.super.onBackPressed();
            }
        }, Constants.resumeMatchInterstitalAdId);
        this.mHelper = new IabHelper(this, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7u5kRGfbQ2KgIHfyHPw4TRR1KPmNG+PEfaJuOe6ohNhqjCy1iQyfhgvRis3AY6Bs8osPDFxI76G2XzAycB9/v6G0PCVEG4ITVkZCnVtTOx2r8p2f24LW3OQNcZrTFCF0mW7cSA2XnWEe0DYCByC0Z5TG+95FhL5rO1TJECEMum3p54l2dYnOgmJTOrnWp0ry6Jv3xC+26T0D07PFxGQoeUxOGcuL0a+rvi4IyWrwUJCX66jVh5EYnNdZ7trtAjtLEjqyAWAzcc3yUbMPUZtw1Do0S+HpKtyyeruZzpJgCl0Hpgdrz4P+7x6dCLI6QkFkrhngqBQNnGIQcev0sB6UewIDAQAB");
        this.mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
            public void onIabPurchaseFinished(IabResult iabResult, Purchase purchase) {
                if (iabResult.isFailure()) {
                    Log.d("purchaising", "Error purchasing: " + iabResult);
                    MyToast.showToast(BatsmanDetailActivity.this, "fBilling unsuccessfull");
                } else if (purchase.getSku().equals("all_pro_features")) {
                    MyToast.showToast(BatsmanDetailActivity.this, "App is upgraded to PRO");
                    SharedPrefsHelper.insertProFeatures(BatsmanDetailActivity.this, true);
                }
            }
        };
        AnimationUtils.loadAnimation(getApplicationContext(), R.anim.layout_slide_down);
        AnimationUtils.loadAnimation(getApplicationContext(), R.anim.layout_slide_up);
        this.textView = (TextView) findViewById(R.id.tvbatname);
        this.tvbatthirtes = (TextView) findViewById(R.id.tvbatthirties);
        this.tvbatfifties = (TextView) findViewById(R.id.tvbatfifties);
        this.tvbathundreds = (TextView) findViewById(R.id.tvbathundreds);
        this.profileImage = (ImageView) findViewById(R.id.profileImage);
        this.tvbatscore = (TextView) findViewById(R.id.tv_bat_runs);
        this.tvbatavg = (TextView) findViewById(R.id.tv_bat_average);
        this.tvbatmatches = (TextView) findViewById(R.id.tv_bat_matches);
        this.tvbatbest = (TextView) findViewById(R.id.tv_bat_best);
        this.tvbatsr = (TextView) findViewById(R.id.tv_bat_sr);
        this.details_button_arrow = (ImageView) findViewById(R.id.details_button);
        this.tvbatPoints = (TextView) findViewById(R.id.tv_bat_points);
        this.tvbatNotOUts = (TextView) findViewById(R.id.tvbatnotOut);
        this.tvBatFours = (TextView) findViewById(R.id.tvbatfours);
        this.tvBatSixes = (TextView) findViewById(R.id.tvbatsixes);
        this.statsButtonLayout = (LinearLayout) findViewById(R.id.details_button_layout);
        this.statsLayout = (LinearLayout) findViewById(R.id.stats_layout);
        FontProvider.setRobotoCondensedFont(this, this.textView);
        FontProvider.setDroidSansBoldFont(this, this.tvbatthirtes);
        FontProvider.setDroidSansBoldFont(this, this.tvbatfifties);
        FontProvider.setDroidSansBoldFont(this, this.tvbathundreds);
        FontProvider.setDroidSansBoldFont(this, this.tvbatscore);
        FontProvider.setDroidSansBoldFont(this, this.tvbatsr);
        FontProvider.setDroidSansBoldFont(this, this.tvbatavg);
        FontProvider.setDroidSansBoldFont(this, this.tvbatmatches);
        FontProvider.setDroidSansBoldFont(this, this.tvbatbest);
        FontProvider.setDroidSansBoldFont(this, this.tvbatPoints);
        FontProvider.setDroidSansBoldFont(this, this.tvBatFours);
        FontProvider.setDroidSansBoldFont(this, this.tvBatSixes);
        FontProvider.setDroidSansBoldFont(this, this.tvbatNotOUts);
        this.statsButtonLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (BatsmanDetailActivity.this.statsLayout.getVisibility() == 0) {
                    BatsmanDetailActivity.this.details_button_arrow.setImageDrawable(ContextCompat.getDrawable(BatsmanDetailActivity.this, R.drawable.ic_arrow_drop_down_black_24dp));
                } else if (BatsmanDetailActivity.this.statsLayout.getVisibility() == 8) {
                    BatsmanHelper.showBatmsanRankingDialog(BatsmanDetailActivity.this, BatsmanDetailActivity.this.batsman, BatsmanDetailActivity.this.allTeamIds);
                }
            }
        });
        this.playersLoader = new PlayersLoader(this);
        this.teamNames = this.playersLoader.getTeamNames(this.playersLoader.getAllTeamPlayers(3));
        this.teamNamesCheckBoxListItems = UtilityFunctions.getCheckBoxListItemsFromStrings(this.teamNames, this.batsmanTeams);
        this.dbHelper = new DBHelper(this);
        this.batsmen = new ArrayList<>();
        this.textView.setText(this.batsman.getName());
        TextView textView2 = this.tvbatfifties;
        textView2.setText(this.batsman.getFifties() + "");
        TextView textView3 = this.tvbatthirtes;
        textView3.setText(this.batsman.getThirties() + "");
        TextView textView4 = this.tvbathundreds;
        textView4.setText(this.batsman.getHundreds() + "");
        TextView textView5 = this.tvbatscore;
        textView5.setText(this.batsman.getTotalScore() + "");
        TextView textView6 = this.tvbatmatches;
        textView6.setText(this.batsman.getMatches() + "");
        TextView textView7 = this.tvbatbest;
        textView7.setText(this.batsman.getBest() + "");
        TextView textView8 = this.tvbatavg;
        textView8.setText(this.batsman.getAverage() + "");
        TextView textView9 = this.tvbatsr;
        textView9.setText(this.batsman.getStrikerate() + "");
        TextView textView10 = this.tvbatPoints;
        textView10.setText(this.batsman.getPoints() + "");
        TextView textView11 = this.tvBatFours;
        textView11.setText(this.batsman.getFours() + "");
        TextView textView12 = this.tvBatSixes;
        textView12.setText(this.batsman.getSixs() + "");
        TextView textView13 = this.tvbatNotOUts;
        textView13.setText((this.batsman.getMatches() - this.batsman.getInnings()) + "");
        if (this.batsman.getImage() != null) {
            this.profileImage.setImageURI(Uri.parse(this.batsman.getImage()));
        } else {
            this.profileImage.setImageResource(R.drawable.ic_player_profile);
        }
        this.textView.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/RobotoCondensed_Bold.ttf"));
        this.bChooseTeams.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FragmentChooseItems.newInstance(BatsmanDetailActivity.this.getResources().getString(R.string.choose_teams), BatsmanDetailActivity.this.teamNamesCheckBoxListItems).show(BatsmanDetailActivity.this.getSupportFragmentManager());
            }
        });
    }

    private void setUpViewPager() {
        this.viewPagerAdapterBatsmanDetails = new ViewPagerAdapterBatsmanDetails(getSupportFragmentManager(), this.batsman.getName(), this.selectedTeamNames, new DBHelper(this).getMatches(SharedData.getInstance().getSelectedMatchType()), true);
        this.viewPager.setAdapter(this.viewPagerAdapterBatsmanDetails);
        this.tabLayout.setupWithViewPager(this.viewPager);
        this.tabLayout.setTabTextColors(ContextCompat.getColor(this, R.color.white), ContextCompat.getColor(this, R.color.white));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.batsman_bowler_generate_pdf, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId != R.id.share_image) {
            switch (itemId) {
                case R.id.generate_pdf /*2131755751*/:
                    try {
                        if (this.pdfGenerator == null) {
                            MyToast.showToast(this, "Error. Please wait for a few seconds and try again.");
                            return true;
                        } else if (!PermissionAsker.checkAPIVersion()) {
                            this.pdfGenerator.generateBatsmanPDF();
                            return true;
                        } else if (PermissionAsker.checkIfAlreadyhavePermission(this)) {
                            this.pdfGenerator.generateBatsmanPDF();
                            return true;
                        } else {
                            PermissionAsker.requestForSpecificPermission(103, this);
                            return true;
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        return true;
                    }
                case R.id.share_pdf /*2131755752*/:
                    if (!SharedPrefsHelper.isPro(this)) {
                        showUpgradeToProDialog();
                        return true;
                    } else if (!PermissionAsker.checkAPIVersion()) {
                        PDFGenerator pDFGenerator = this.pdfGenerator;
                        PdfSharer.shareBatsmanPdf(pDFGenerator, this, "Cricket Scorer/Batsman Stats/" + this.batsman.getName() + " ( " + this.batsman.getTeam_Name() + ").pdf");
                        return true;
                    } else if (PermissionAsker.checkIfAlreadyhavePermission(this)) {
                        PDFGenerator pDFGenerator2 = this.pdfGenerator;
                        PdfSharer.shareBatsmanPdf(pDFGenerator2, this, "Cricket Scorer/Batsman Stats/" + this.batsman.getName() + " ( " + this.batsman.getTeam_Name() + ").pdf");
                        return true;
                    } else {
                        PermissionAsker.requestForSpecificPermission(104, this);
                        return true;
                    }
                default:
                    return true;
            }
        } else if (!SharedPrefsHelper.isPro(this)) {
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

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 10001 && i2 == -1) {
            SharedPrefsHelper.insertProFeatures(this, true);
            MyToast.showToast(this, "Congratulations on being a PRO Cricket Scorer");
            if (!PermissionAsker.checkAPIVersion()) {
                PDFGenerator pDFGenerator = this.pdfGenerator;
                PdfSharer.shareBatsmanPdf(pDFGenerator, this, "Cricket Scorer/Batsman Stats/" + this.batsman.getName() + " ( " + this.batsman.getTeam_Name() + ").pdf");
            } else if (PermissionAsker.checkIfAlreadyhavePermission(this)) {
                PDFGenerator pDFGenerator2 = this.pdfGenerator;
                PdfSharer.shareBatsmanPdf(pDFGenerator2, this, "Cricket Scorer/Batsman Stats/" + this.batsman.getName() + " ( " + this.batsman.getTeam_Name() + ").pdf");
            } else {
                PermissionAsker.requestForSpecificPermission(104, this);
            }
        } else if (i == 10001 && i2 == 0) {
            SharedPrefsHelper.insertProFeatures(this, false);
        }
    }

    private void showUpgradeToProDialog() {
        UpgradeToProDialog upgradeToProDialog = new UpgradeToProDialog();
        upgradeToProDialog.setListener(this);
        upgradeToProDialog.show(getFragmentManager(), "upgrade to PRO dialog");
    }

    public void onPurchaseSuccessfull() {
        if (!PermissionAsker.checkAPIVersion()) {
            return;
        }
        if (PermissionAsker.checkIfAlreadyhavePermission(this)) {
            PDFGenerator pDFGenerator = this.pdfGenerator;
            PdfSharer.shareBatsmanPdf(pDFGenerator, this, "Cricket Scorer/Batsman Stats/" + this.batsman.getName() + " ( " + this.batsman.getTeam_Name() + ").pdf");
            return;
        }
        PermissionAsker.requestForSpecificPermission(104, this);
    }

    public void onPurchaseFailed() {
        MyToast.showToast(this, "Failed to purchase");
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        switch (i) {
            case 103:
                if (iArr[0] == 0) {
                    try {
                        this.pdfGenerator.generateBatsmanPDF();
                        return;
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        return;
                    }
                } else {
                    MyToast.showToast(this, "Pdf not generated. Permission denied.");
                    return;
                }
            case 104:
                if (iArr[0] == 0) {
                    PDFGenerator pDFGenerator = this.pdfGenerator;
                    PdfSharer.shareBatsmanPdf(pDFGenerator, this, "Cricket Scorer/Batsman Stats/" + this.batsman.getName() + " ( " + this.batsman.getTeam_Name() + ").pdf");
                    return;
                }
                MyToast.showToast(this, "Pdf not generated. Permission denied.");
                return;
            case 105:
                if (iArr[0] == 0) {
                    ImageSharer.shareImage(this);
                    return;
                } else {
                    MyToast.showToast(this, "Image not generated. Permission denied.");
                    return;
                }
            default:
                super.onRequestPermissionsResult(i, strArr, iArr);
                return;
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onItemsChosen(ArrayList<Integer> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        this.selectedTeamNames = new ArrayList<>();
        Iterator<Integer> it = arrayList.iterator();
        while (it.hasNext()) {
            Integer next = it.next();
            stringBuffer.append(this.teamNames.get(next.intValue()) + ":");
            this.selectedTeamNames.add(this.teamNames.get(next.intValue()));
        }
        if (this.selectedTeamNames.isEmpty()) {
            this.batsman.setTeamNames((ArrayList<String>) null);
        } else {
            this.batsman.setTeamNames(this.selectedTeamNames);
        }
        TinyDB tinyDB = new TinyDB(this);
        tinyDB.putString("batsman " + this.batsman.getName() + " " + this.teamname, stringBuffer.toString());
        this.viewPagerAdapterBatsmanDetails.setTeamNames(this.selectedTeamNames);
    }
}
