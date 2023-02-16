package apps.shehryar.com.cricketscroingappPro.Team;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.MyToast;
import apps.shehryar.com.cricketscroingappPro.OtherTeamsViewPagerAdapter;
import apps.shehryar.com.cricketscroingappPro.PermissionAsker;
import apps.shehryar.com.cricketscroingappPro.UpgradeToProDialog;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.ImageSharer;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.SharedPrefsHelper;
import com.itextpdf.text.xml.xmp.XmpMMProperties;
import java.util.ArrayList;
import util.IabHelper;
import util.IabResult;
import util.Purchase;

public class Other_Team_Activity extends AppCompatActivity implements UpgradeToProDialog.UpgradeToProCallBack {
    static TabLayout tabLayout;
    ArrayList<Long> allTeamsIds;
    DBHelper dbHelper;
    int index;
    private IabHelper mHelper;
    private IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener;
    ArrayList<String> playernames;
    ArrayList<Team> teamPlayers;
    ArrayList<String> team_names;
    ViewPager viewPager;
    Team_players yourteam;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_other_team);
        getSupportActionBar().setTitle((CharSequence) XmpMMProperties.HISTORY);
        this.mHelper = new IabHelper(this, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7u5kRGfbQ2KgIHfyHPw4TRR1KPmNG+PEfaJuOe6ohNhqjCy1iQyfhgvRis3AY6Bs8osPDFxI76G2XzAycB9/v6G0PCVEG4ITVkZCnVtTOx2r8p2f24LW3OQNcZrTFCF0mW7cSA2XnWEe0DYCByC0Z5TG+95FhL5rO1TJECEMum3p54l2dYnOgmJTOrnWp0ry6Jv3xC+26T0D07PFxGQoeUxOGcuL0a+rvi4IyWrwUJCX66jVh5EYnNdZ7trtAjtLEjqyAWAzcc3yUbMPUZtw1Do0S+HpKtyyeruZzpJgCl0Hpgdrz4P+7x6dCLI6QkFkrhngqBQNnGIQcev0sB6UewIDAQAB");
        this.mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
            public void onIabPurchaseFinished(IabResult iabResult, Purchase purchase) {
                if (iabResult.isFailure()) {
                    Log.d("purchaising", "Error purchasing: " + iabResult);
                    MyToast.showToast(Other_Team_Activity.this, "Billing unsuccessfull");
                    SharedPrefsHelper.insertProFeatures(Other_Team_Activity.this, false);
                } else if (purchase.getSku().equals("all_pro_features")) {
                    MyToast.showToast(Other_Team_Activity.this, "App is upgraded to PRO");
                    SharedPrefsHelper.insertProFeatures(Other_Team_Activity.this, true);
                }
            }
        };
        this.team_names = getIntent().getStringArrayListExtra("team_names");
        this.index = getIntent().getIntExtra("index", 0);
        this.teamPlayers = (ArrayList) getIntent().getSerializableExtra("team_players");
        this.allTeamsIds = (ArrayList) getIntent().getSerializableExtra("allTeamsIds");
        this.dbHelper = new DBHelper(this);
        String str = this.team_names.get(this.index);
        getSupportActionBar().setTitle((CharSequence) str);
        if (!str.isEmpty()) {
            try {
                this.playernames = this.teamPlayers.get(this.index).getAllPlayersNames();
            } catch (Exception e) {
                e.printStackTrace();
                this.playernames = new ArrayList<>();
            }
        } else {
            this.playernames = new ArrayList<>();
        }
        this.viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        this.viewPager.setAdapter(new OtherTeamsViewPagerAdapter(getSupportFragmentManager(), this, this.playernames, this.team_names, str, this.allTeamsIds));
        tabLayout.setupWithViewPager(this.viewPager);
        tabLayout.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.white));
    }

    public void onBackPressed() {
        setResult(2);
        finish();
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

    private void showUpgradeToProDialog() {
        UpgradeToProDialog upgradeToProDialog = new UpgradeToProDialog();
        upgradeToProDialog.setListener(this);
        upgradeToProDialog.show(getFragmentManager(), UpgradeToProDialog.class.toString());
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
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

    public void onPurchaseSuccessfull() {
        Toast.makeText(getApplicationContext(), "Purchase Successful", 0).show();
    }

    public void onPurchaseFailed() {
        Toast.makeText(getApplicationContext(), "Purchase UnSuccessful", 0).show();
    }
}
