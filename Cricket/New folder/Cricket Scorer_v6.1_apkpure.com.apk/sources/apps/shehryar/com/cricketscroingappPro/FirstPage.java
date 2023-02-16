package apps.shehryar.com.cricketscroingappPro;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteException;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.BackupRestore.BackupRestoreActivity;
import apps.shehryar.com.cricketscroingappPro.LoadMatchDialog;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Match.MatchDetailsActivity;
import apps.shehryar.com.cricketscroingappPro.Match.MatchLoader;
import apps.shehryar.com.cricketscroingappPro.Player.Player;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import apps.shehryar.com.cricketscroingappPro.Team.Team_players;
import apps.shehryar.com.cricketscroingappPro.Team.YourTeamStats;
import apps.shehryar.com.cricketscroingappPro.UnsupportedFileDialog;
import apps.shehryar.com.cricketscroingappPro.UpgradeToProDialog;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.AdsLoader;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.AlertDialogBuilder;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.Constants;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.Dialog;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.ObjectWriterAndReader;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.SharedPrefsHelper;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.TinyDB;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.UtilityFunctions;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import com.itextpdf.text.pdf.PdfBoolean;
import io.fabric.sdk.android.Fabric;
import java.util.ArrayList;
import java.util.Iterator;
import util.IabHelper;
import util.IabResult;
import util.Inventory;
import util.Purchase;

public class FirstPage extends Activity implements View.OnClickListener, UnsupportedFileDialog.UnsupportedFileDialogHandler, LoadMatchDialog.LoadMatchDialogHandler, UpgradeToProDialog.UpgradeToProCallBack {
    AdListener adListener;
    String base64EncodedPublicKey;
    Button bbakcuprestore;
    Button bprevious;
    Button bresume;
    Button bstart;
    Button byourteam;
    DBHelper dbHelper;
    private IabHelper.QueryInventoryFinishedListener mGotInventoryListener;
    private IabHelper mHelper;
    private InterstitialAd mInterstitialAd;
    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener;
    private boolean playersLoaded = false;
    PlayersLoader playersLoader;
    ArrayList<String> teamnames;
    ArrayList<Team> teams;
    ArrayList<Team> userAddedAndHistoryTeams;
    ArrayList<String> userAddedAndHistoryTeamsNames;
    ArrayList<String> userAddedTeamNames;
    ArrayList<Team> userAddedTeams;

    public void onPurchaseFailed() {
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    @RequiresApi(api = 11)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_first_page);
        Fabric.with(this, new Crashlytics());
        getWindow().getDecorView().setSystemUiVisibility(1280);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(0);
        }
        this.dbHelper = new DBHelper(this);
        try {
            insertSampleTeamsAndPlayers(this.dbHelper);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        copyTeamsAndPlayersFromPreviousTables();
        UtilityFunctions.copyOversFromSharedPrefsIntoDB(this);
        this.adListener = new AdListener() {
            public void onAdClosed() {
                super.onAdClosed();
                FirstPage.this.startMatchesHistoryActivity();
                FirstPage.this.startMatchesHistoryActivity();
            }
        };
        this.mInterstitialAd = AdsLoader.initializeInterstitialAd(this, this.mInterstitialAd, this.adListener, Constants.matchesHistoryInterstitialAdId);
        this.base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7u5kRGfbQ2KgIHfyHPw4TRR1KPmNG+PEfaJuOe6ohNhqjCy1iQyfhgvRis3AY6Bs8osPDFxI76G2XzAycB9/v6G0PCVEG4ITVkZCnVtTOx2r8p2f24LW3OQNcZrTFCF0mW7cSA2XnWEe0DYCByC0Z5TG+95FhL5rO1TJECEMum3p54l2dYnOgmJTOrnWp0ry6Jv3xC+26T0D07PFxGQoeUxOGcuL0a+rvi4IyWrwUJCX66jVh5EYnNdZ7trtAjtLEjqyAWAzcc3yUbMPUZtw1Do0S+HpKtyyeruZzpJgCl0Hpgdrz4P+7x6dCLI6QkFkrhngqBQNnGIQcev0sB6UewIDAQAB";
        this.mHelper = new IabHelper(this, this.base64EncodedPublicKey);
        new ArrayList().add("all_pro_features");
        queryPurchasedItems();
        this.mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
            public void onIabPurchaseFinished(IabResult iabResult, Purchase purchase) {
                if (iabResult.getResponse() == 7) {
                    MyToast.showToast(FirstPage.this, "App is upgraded to PRO");
                    SharedPrefsHelper.insertProFeatures(FirstPage.this, true);
                } else if (iabResult.isFailure()) {
                    Log.d("purchaising", "Error purchasing: " + iabResult);
                    MyToast.showToast(FirstPage.this, "Billing unsuccessfull");
                    SharedPrefsHelper.insertProFeatures(FirstPage.this, false);
                } else if (purchase.getSku().equals("all_pro_features")) {
                    MyToast.showToast(FirstPage.this, "App is upgraded to PRO");
                    SharedPrefsHelper.insertProFeatures(FirstPage.this, true);
                }
            }
        };
        this.mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
            public void onQueryInventoryFinished(IabResult iabResult, Inventory inventory) {
                if (inventory.getPurchase("all_pro_features") != null) {
                    SharedPrefsHelper.insertProFeatures(FirstPage.this, true);
                }
                if (!iabResult.isFailure()) {
                    SharedPrefsHelper.insertProFeatures(FirstPage.this, inventory.hasPurchase("all_pro_features"));
                }
            }
        };
        initializeViews();
        String string = getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("test", "");
        if (string != null) {
            Log.i("test", string);
        }
        this.userAddedAndHistoryTeamsNames = new ArrayList<>();
        this.userAddedAndHistoryTeams = new ArrayList<>();
        this.playersLoader = new PlayersLoader(this);
        new playersLoader().execute(new Object[0]);
        updateMOMOnceDueToMistake();
        if (!SharedPrefsHelper.checkBowlerTypeUpdated(this)) {
            try {
                this.dbHelper.changeBowlerTypeOnce();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void copyManOfTheMatchesToTheRespectiveTable() {
        if (!SharedPrefsHelper.checkMOMCopied(this)) {
            this.dbHelper.getMatches(3);
        }
    }

    private void insertSampleTeamsAndPlayers(DBHelper dBHelper) throws SQLiteException {
        DBHelper dBHelper2 = dBHelper;
        if (!new TinyDB(this).getBoolean(Constants.SAMPLE_TEAMS_ADDED) && dBHelper.getUserAddedTeams().size() == 0) {
            Team team = new Team();
            team.setTeamSide(0);
            team.setName("Your Team");
            team.setTeamID(dBHelper2.insertUserAddedTeam(team));
            String[] strArr = {"Your Player 1", "Your Player 2", "Your Player 3", "Your Player 4", "Your Player 5", "Your Player 6", "Your Player 7", "Your Player 7", "Your Player 8", "Your Player 9", "Your Player 10", "Your Player 11"};
            for (String name : strArr) {
                Player player = new Player();
                player.setName(name);
                player.setTeamId(team.getTeamID());
                dBHelper2.insertUserAddedPlayer(player);
            }
            Team team2 = new Team();
            team2.setTeamSide(1);
            team2.setName("Opponent Team");
            team2.setTeamID(dBHelper2.insertUserAddedTeam(team2));
            String[] strArr2 = {"Opp Player 1", "Opp Player 2", "Opp Player 3", "Opp Player 4", "OPp Player 5", "Opp Player 6", "Opp Player 7", "Opp Player 7", "Opp Player 8", "Opp Player 9", "Opp Player 10", "Opp Player 11"};
            for (String name2 : strArr2) {
                Player player2 = new Player();
                player2.setName(name2);
                player2.setTeamId(team2.getTeamID());
                dBHelper2.insertUserAddedPlayer(player2);
            }
            new TinyDB(this).putBoolean(Constants.SAMPLE_TEAMS_ADDED, true);
        }
    }

    private void copyTeamsAndPlayersFromPreviousTables() {
        if (!SharedPrefsHelper.checkTeamsCopied(this)) {
            try {
                ArrayList<Team_players> allTeamPlayers = this.dbHelper.getAllTeamPlayers();
                ArrayList arrayList = new ArrayList();
                Iterator<Team_players> it = allTeamPlayers.iterator();
                while (it.hasNext()) {
                    Team_players next = it.next();
                    Team team = new Team();
                    ArrayList arrayList2 = new ArrayList();
                    if (next.getTeamside().equals("yours")) {
                        team.setTeamSide(0);
                    } else {
                        team.setTeamSide(1);
                    }
                    team.setName(next.getTeamname());
                    Iterator<String> it2 = next.getPlayers().iterator();
                    while (it2.hasNext()) {
                        Player player = new Player();
                        player.setName(it2.next());
                        arrayList2.add(player);
                    }
                    team.setPlayers(arrayList2);
                    arrayList.add(team);
                }
                Iterator it3 = arrayList.iterator();
                while (it3.hasNext()) {
                    Team team2 = (Team) it3.next();
                    team2.setTeamID(this.dbHelper.insertUserAddedTeam(team2));
                    Iterator<Player> it4 = team2.getPlayers().iterator();
                    while (it4.hasNext()) {
                        Player next2 = it4.next();
                        next2.setTeamId(team2.getTeamID());
                        this.dbHelper.insertUserAddedPlayer(next2);
                    }
                }
                SharedPrefsHelper.insertTeamsCopiedBoolean(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void queryPurchasedItems() {
        if (this.mHelper.isSetupDone() && !this.mHelper.isAsyncInProgress()) {
            try {
                this.mHelper.queryInventoryAsync(this.mGotInventoryListener);
            } catch (IabHelper.IabAsyncInProgressException e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        queryPurchasedItems();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        queryPurchasedItems();
        AnonymousClass4 r6 = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Match readObjectFromUri = ObjectWriterAndReader.readObjectFromUri(FirstPage.this, FirstPage.this.getIntent().getData());
                new MatchLoader(FirstPage.this).execute(new Match[]{readObjectFromUri});
            }
        };
        if (getIntent() != null && getIntent().getData() != null) {
            AlertDialogBuilder.showAlertDialog(new Dialog(this, "Load Match", "Are you sure you want to load this match?", "Yes", "No", r6));
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mHelper != null) {
            try {
                this.mHelper.dispose();
            } catch (IabHelper.IabAsyncInProgressException e) {
                e.printStackTrace();
            }
        }
        this.mHelper = null;
    }

    /* access modifiers changed from: private */
    public void startMatchesHistoryActivity() {
        Intent intent = new Intent(this, YourTeamStats.class);
        intent.putExtra("team_names", this.teamnames);
        intent.putExtra("teams", this.teams);
        startActivityForResult(intent, 1);
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        SharedPreferences.Editor edit = getSharedPreferences(DBHelper.TABLE_TEAM, 0).edit();
        edit.putString("test", "First Page");
        edit.commit();
    }

    private void initializeViews() {
        Typeface createFromAsset = Typeface.createFromAsset(getAssets(), "fonts/FiraSans_Bold.ttf");
        this.byourteam = (Button) findViewById(R.id.bsetupyourteam);
        this.bstart = (Button) findViewById(R.id.bstartnewmatch);
        this.bprevious = (Button) findViewById(R.id.bloadprevious);
        this.bresume = (Button) findViewById(R.id.bresumeMatch);
        this.bbakcuprestore = (Button) findViewById(R.id.b_backup_restore);
        this.byourteam.setTypeface(createFromAsset);
        this.bstart.setTypeface(createFromAsset);
        this.bprevious.setTypeface(createFromAsset);
        this.bresume.setTypeface(createFromAsset);
        this.bbakcuprestore.setTypeface(createFromAsset);
        ((Button) findViewById(R.id.b_load_match)).setTypeface(createFromAsset);
        ((Button) findViewById(R.id.bstartnewTestmatch)).setTypeface(createFromAsset);
    }

    public void buttonClick(View view) {
        switch (view.getId()) {
            case R.id.bsetupyourteam /*2131755247*/:
                try {
                    Intent intent = new Intent(this, ManageTeamsActivity.class);
                    intent.putExtra("team_players", this.userAddedTeams);
                    startActivityForResult(intent, 1);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            case R.id.bstartnewmatch /*2131755248*/:
                Intent intent2 = new Intent(this, MatchDetailsActivity.class);
                intent2.putExtra("team_names", this.userAddedAndHistoryTeamsNames);
                intent2.putExtra("teams", this.userAddedAndHistoryTeams);
                startActivity(intent2);
                return;
            case R.id.bstartnewTestmatch /*2131755249*/:
                Intent intent3 = new Intent(this, MatchDetailsActivity.class);
                intent3.putExtra("team_names", this.userAddedAndHistoryTeamsNames);
                intent3.putExtra("teams", this.userAddedAndHistoryTeams);
                intent3.putExtra("testMatch", true);
                startActivity(intent3);
                return;
            case R.id.bresumeMatch /*2131755250*/:
                Intent intent4 = new Intent(this, ResumeMatchActivity.class);
                intent4.putExtra("teams", this.userAddedAndHistoryTeams);
                startActivity(intent4);
                return;
            case R.id.bloadprevious /*2131755251*/:
                startMatchesHistoryActivity();
                return;
            case R.id.b_backup_restore /*2131755252*/:
                if (SharedPrefsHelper.isPro(this)) {
                    startActivity(new Intent(this, BackupRestoreActivity.class));
                    return;
                } else {
                    showUpgradeToProDialog();
                    return;
                }
            case R.id.b_load_match /*2131755253*/:
                new LoadMatchDialog().show(getFragmentManager(), "load match dialog");
                return;
            default:
                return;
        }
    }

    private void loadMatch() {
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction("android.intent.action.GET_CONTENT");
        startActivityForResult(Intent.createChooser(intent, "Select File with .srl extension"), 101);
    }

    private void showUpgradeToProDialog() {
        UpgradeToProDialog upgradeToProDialog = new UpgradeToProDialog();
        upgradeToProDialog.setListener(this);
        upgradeToProDialog.show(getFragmentManager(), UpgradeToProDialog.class.toString());
    }

    private void updateMOMOnceDueToMistake() {
        if (getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("momCheck", PdfBoolean.FALSE).equals(PdfBoolean.FALSE)) {
            new Thread(new Runnable() {
                public void run() {
                    FirstPage.this.dbHelper.updateMOMdueToMistake();
                }
            }).start();
            SharedPreferences.Editor edit = getSharedPreferences(DBHelper.TABLE_TEAM, 0).edit();
            edit.putString("momCheck", PdfBoolean.TRUE);
            edit.commit();
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.bupgrade) {
            MyToast.showToast(this, "Upgrade was clicked");
        }
    }

    public void onOpenAgain() {
        loadMatch();
    }

    public void onChooseFilePressed() {
        loadMatch();
    }

    public void onPurchaseSuccessfull() {
        startActivity(new Intent(this, BackupRestoreActivity.class));
    }

    class playersLoader extends AsyncTask {
        playersLoader() {
        }

        /* access modifiers changed from: protected */
        public Object doInBackground(Object[] objArr) {
            try {
                FirstPage.this.teams = FirstPage.this.playersLoader.getAllTeamPlayers(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                FirstPage.this.teamnames = FirstPage.this.playersLoader.getAddedTeamNmaes(FirstPage.this.teams);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                FirstPage.this.userAddedTeams = FirstPage.this.playersLoader.getAddedTeamPlayers();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            try {
                FirstPage.this.userAddedTeamNames = FirstPage.this.playersLoader.getAddedTeamNmaes(FirstPage.this.userAddedTeams);
            } catch (Exception e4) {
                e4.printStackTrace();
            }
            try {
                FirstPage.this.userAddedAndHistoryTeamsNames.addAll(FirstPage.this.userAddedTeamNames);
                FirstPage.this.userAddedAndHistoryTeams.addAll(FirstPage.this.userAddedTeams);
                return null;
            } catch (Exception e5) {
                e5.printStackTrace();
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Object obj) {
            super.onPostExecute(obj);
            int i = 0;
            while (i < FirstPage.this.teamnames.size()) {
                try {
                    if (!FirstPage.this.userAddedTeamNames.contains(FirstPage.this.teamnames.get(i))) {
                        FirstPage.this.userAddedAndHistoryTeams.add(FirstPage.this.teams.get(i));
                        FirstPage.this.userAddedAndHistoryTeamsNames.add(FirstPage.this.teamnames.get(i));
                    }
                    i++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (!FirstPage.this.getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("upgraded", "").equals(PdfBoolean.TRUE)) {
                SharedPreferences.Editor edit = FirstPage.this.getSharedPreferences(DBHelper.TABLE_TEAM, 0).edit();
                edit.putString("upgraded", PdfBoolean.FALSE);
                edit.commit();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.mInterstitialAd = AdsLoader.initializeInterstitialAd(this, this.mInterstitialAd, this.adListener, Constants.matchesHistoryInterstitialAdId);
        if (i == 1 && i2 == 2) {
            this.userAddedAndHistoryTeams.clear();
            this.userAddedAndHistoryTeamsNames.clear();
            this.userAddedTeams = this.playersLoader.getAddedTeamPlayers();
            this.userAddedTeamNames = this.playersLoader.getAddedTeamNmaes(this.userAddedTeams);
            this.userAddedAndHistoryTeamsNames.addAll(this.userAddedTeamNames);
            this.userAddedAndHistoryTeams.addAll(this.userAddedTeams);
            int i3 = 0;
            while (i3 < this.teamnames.size()) {
                try {
                    if (!this.userAddedTeamNames.contains(this.teamnames.get(i3))) {
                        this.userAddedAndHistoryTeams.add(this.teams.get(i3));
                        this.userAddedAndHistoryTeamsNames.add(this.teamnames.get(i3));
                    }
                    i3++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (i == 10001 && i2 == -1) {
            SharedPrefsHelper.insertProFeatures(this, true);
            MyToast.showToast(this, "Congratulations on being a PRO Cricket Scorer");
            startActivity(new Intent(this, BackupRestoreActivity.class));
        } else if (i == 10001 && i2 == 0) {
            SharedPrefsHelper.insertProFeatures(this, false);
        }
        if (i == 101 && i2 == -1) {
            Match readObjectFromUri = ObjectWriterAndReader.readObjectFromUri(this, intent.getData());
            if (readObjectFromUri != null) {
                new MatchLoader(this).execute(new Match[]{readObjectFromUri});
                return;
            }
            new UnsupportedFileDialog().show(getFragmentManager(), "unsupported dialog");
        }
    }

    public void onBackPressed() {
        final AlertDialog create = new AlertDialog.Builder(this).setTitle("Rate Our App").setMessage("Please provide your feedback so that we could improve the app.").setPositiveButton("Rate", (DialogInterface.OnClickListener) null).setNegativeButton("Exit", (DialogInterface.OnClickListener) null).create();
        create.setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                Button button = create.getButton(-1);
                Button button2 = create.getButton(-2);
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + FirstPage.this.getApplicationContext().getPackageName()));
                        intent.addFlags(1275592704);
                        try {
                            FirstPage.this.startActivity(intent);
                        } catch (ActivityNotFoundException unused) {
                            FirstPage firstPage = FirstPage.this;
                            firstPage.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + FirstPage.this.getApplicationContext().getPackageName())));
                        }
                    }
                });
                button2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        FirstPage.this.finishAffinity();
                        System.exit(0);
                    }
                });
            }
        });
        create.show();
    }
}
