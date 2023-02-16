package apps.shehryar.com.cricketscroingappPro.Bowler;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
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
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.FontProvider;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.ImageSharer;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.PdfSharer;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.SharedPrefsHelper;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.TinyDB;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.UtilityFunctions;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import util.IabHelper;
import util.IabResult;
import util.Purchase;

public class BowlerDetailsActiivty extends AppCompatActivity implements FragmentChooseItemListener, UpgradeToProDialog.UpgradeToProCallBack {
    ArrayList<Long> allTeamIds;
    @BindView(2131755205)
    Button bChooseTeams;
    String batname;
    Bowler bowler;
    private String bowlerTeams;
    ArrayList<Bowler_Details> bowler_detailsArrayList;
    DBHelper dbHelper;
    /* access modifiers changed from: private */
    public ImageView details_button_arrow;
    PDFGenerator generatePDF;
    private IabHelper mHelper;
    private IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener;
    Player player;
    private PlayersLoader playersLoader;
    ImageView profileImage;
    private ArrayList<String> selectedTeamNames;
    private LinearLayout statsButtonLayout;
    /* access modifiers changed from: private */
    public LinearLayout statsLayout;
    @BindView(2131755206)
    TabLayout tabLayout;
    private ArrayList<String> teamNames;
    ArrayList<CheckBoxListItem> teamNamesCheckBoxListItems;
    String teamname;
    TextView textView;
    TextView tvNoData;
    @BindView(2131755207)
    ViewPager viewPager;
    private ViewPagerAdapterBatsmanDetails viewPagerAdapterBatsmanDetails;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_bowler_details_actiivty);
        ButterKnife.bind((Activity) this);
        this.generatePDF = new PDFGenerator();
        AdsLoader.loadBannerAd(this, this, "ca-app-pub-7751307421283386/4086505351", R.id.bowler_details_ad);
        this.mHelper = new IabHelper(this, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7u5kRGfbQ2KgIHfyHPw4TRR1KPmNG+PEfaJuOe6ohNhqjCy1iQyfhgvRis3AY6Bs8osPDFxI76G2XzAycB9/v6G0PCVEG4ITVkZCnVtTOx2r8p2f24LW3OQNcZrTFCF0mW7cSA2XnWEe0DYCByC0Z5TG+95FhL5rO1TJECEMum3p54l2dYnOgmJTOrnWp0ry6Jv3xC+26T0D07PFxGQoeUxOGcuL0a+rvi4IyWrwUJCX66jVh5EYnNdZ7trtAjtLEjqyAWAzcc3yUbMPUZtw1Do0S+HpKtyyeruZzpJgCl0Hpgdrz4P+7x6dCLI6QkFkrhngqBQNnGIQcev0sB6UewIDAQAB");
        this.mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
            public void onIabPurchaseFinished(IabResult iabResult, Purchase purchase) {
                if (iabResult.isFailure()) {
                    Log.d("purchaising", "Error purchasing: " + iabResult);
                    MyToast.showToast(BowlerDetailsActiivty.this, "Billing unsuccessfull");
                } else if (purchase.getSku().equals("all_pro_features")) {
                    MyToast.showToast(BowlerDetailsActiivty.this, "App is upgraded to PRO");
                    SharedPrefsHelper.insertProFeatures(BowlerDetailsActiivty.this, true);
                }
            }
        };
        this.bowler_detailsArrayList = new ArrayList<>();
        this.textView = (TextView) findViewById(R.id.tvbatname);
        this.tvNoData = (TextView) findViewById(R.id.tvNoData);
        FontProvider.setRobotoCondensedFont(this, this.textView);
        this.profileImage = (ImageView) findViewById(R.id.profileImage);
        this.batname = getIntent().getStringExtra("batname");
        this.teamname = getIntent().getStringExtra("teamname");
        this.bowler = (Bowler) getIntent().getSerializableExtra("bowler");
        this.player = (Player) getIntent().getSerializableExtra(DBHelper.TABLE_PLAYERS);
        this.allTeamIds = (ArrayList) getIntent().getSerializableExtra("allTeamIds");
        this.dbHelper = new DBHelper(this);
        this.playersLoader = new PlayersLoader(this);
        this.teamNames = this.playersLoader.getTeamNames(this.playersLoader.getAllTeamPlayers(3));
        this.teamNamesCheckBoxListItems = UtilityFunctions.getCheckBoxListItemsFromStrings(this.teamNames, this.bowlerTeams);
        TinyDB tinyDB = new TinyDB(this);
        this.bowlerTeams = tinyDB.getString("bowler " + this.bowler.getName() + " " + this.teamname);
        try {
            this.selectedTeamNames = UtilityFunctions.getStringArrayListFromStrings(this.bowlerTeams);
        } catch (Exception e) {
            e.printStackTrace();
            this.selectedTeamNames = new ArrayList<>();
        }
        if (!this.selectedTeamNames.contains(this.teamname)) {
            this.selectedTeamNames.add(0, this.teamname);
        }
        setUpViewPager();
        this.textView.setText(this.batname);
        this.statsButtonLayout = (LinearLayout) findViewById(R.id.details_button_layout);
        this.statsLayout = (LinearLayout) findViewById(R.id.stats_layout);
        this.details_button_arrow = (ImageView) findViewById(R.id.details_button);
        this.statsButtonLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (BowlerDetailsActiivty.this.statsLayout.getVisibility() == 0) {
                    BowlerDetailsActiivty.this.details_button_arrow.setImageDrawable(ContextCompat.getDrawable(BowlerDetailsActiivty.this, R.drawable.ic_arrow_drop_down_black_24dp));
                } else if (BowlerDetailsActiivty.this.statsLayout.getVisibility() == 8) {
                    BowlerHelper.showBowlerRankingDialog(BowlerDetailsActiivty.this.bowler, BowlerDetailsActiivty.this, BowlerDetailsActiivty.this.allTeamIds);
                }
            }
        });
        this.textView.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/RobotoCondensed_Bold.ttf"));
        if (this.bowler == null) {
            this.profileImage.setVisibility(8);
        } else if (this.bowler.getImage() != null) {
            this.profileImage.setImageURI(Uri.parse(this.bowler.getImage()));
        } else {
            this.profileImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_player_profile));
        }
        this.bChooseTeams.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FragmentChooseItems.newInstance(BowlerDetailsActiivty.this.getResources().getString(R.string.choose_teams), BowlerDetailsActiivty.this.teamNamesCheckBoxListItems).show(BowlerDetailsActiivty.this.getSupportFragmentManager());
            }
        });
    }

    private void setUpViewPager() {
        this.viewPagerAdapterBatsmanDetails = new ViewPagerAdapterBatsmanDetails(getSupportFragmentManager(), this.bowler.getName(), this.selectedTeamNames, new DBHelper(this).getMatches(SharedData.getInstance().getSelectedMatchType()), false);
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
                        if (!PermissionAsker.checkAPIVersion()) {
                            this.generatePDF.generateBowlerPDF();
                            return true;
                        } else if (PermissionAsker.checkIfAlreadyhavePermission(this)) {
                            this.generatePDF.generateBowlerPDF();
                            return true;
                        } else {
                            PermissionAsker.requestForSpecificPermission(104, this);
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
                        PDFGenerator pDFGenerator = this.generatePDF;
                        PdfSharer.shareBowlerPdf(pDFGenerator, this, "Cricket Scorer/Bowlers Stats/" + this.bowler.getName() + " ( " + this.bowler.getTeamName() + ").pdf");
                        return true;
                    } else if (PermissionAsker.checkIfAlreadyhavePermission(this)) {
                        PDFGenerator pDFGenerator2 = this.generatePDF;
                        PdfSharer.shareBowlerPdf(pDFGenerator2, this, "Cricket Scorer/Bowlers Stats/" + this.bowler.getName() + " ( " + this.bowler.getTeamName() + ").pdf");
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
            PermissionAsker.requestForSpecificPermission(104, this);
            return true;
        }
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
            if (!PermissionAsker.checkAPIVersion()) {
                return;
            }
            if (PermissionAsker.checkIfAlreadyhavePermission(this)) {
                PDFGenerator pDFGenerator = this.generatePDF;
                PdfSharer.shareBatsmanPdf(pDFGenerator, this, "Cricket Scorer/Bowlers Stats/" + this.bowler.getName() + " ( " + this.bowler.getTeamName() + ").pdf");
                return;
            }
            PermissionAsker.requestForSpecificPermission(104, this);
        } else if (i == 10001 && i2 == 0) {
            SharedPrefsHelper.insertProFeatures(this, false);
        }
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
        if (this.selectedTeamNames.size() == 0) {
            this.bowler.setTeamNames((ArrayList<String>) null);
        } else {
            this.bowler.setTeamNames(this.selectedTeamNames);
        }
        TinyDB tinyDB = new TinyDB(this);
        tinyDB.putString("bowler " + this.bowler.getName() + " " + this.teamname, stringBuffer.toString());
        this.viewPagerAdapterBatsmanDetails.setTeamNames(this.selectedTeamNames);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        switch (i) {
            case 103:
                if (iArr[0] == 0) {
                    try {
                        this.generatePDF.generateBowlerPDF();
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
                    PDFGenerator pDFGenerator = this.generatePDF;
                    PdfSharer.shareBowlerPdf(pDFGenerator, this, "Cricket Scorer/Bowlers Stats/" + this.bowler.getName() + " ( " + this.bowler.getTeamName() + ").pdf");
                    return;
                }
                MyToast.showToast(this, "Pdf not generated. Permission denied.");
                return;
            default:
                super.onRequestPermissionsResult(i, strArr, iArr);
                return;
        }
    }

    public void onPurchaseSuccessfull() {
        Toast.makeText(getApplicationContext(), "Purchase Successful", 0).show();
    }

    public void onPurchaseFailed() {
        Toast.makeText(getApplicationContext(), "Purchase UnSuccessful", 0).show();
    }
}
