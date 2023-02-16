package apps.shehryar.com.cricketscroingappPro;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman;
import apps.shehryar.com.cricketscroingappPro.Batsman.BatsmanDataUpdater;
import apps.shehryar.com.cricketscroingappPro.Batsman.BatsmanDetailsDialog;
import apps.shehryar.com.cricketscroingappPro.Batsman.BatsmanHelper;
import apps.shehryar.com.cricketscroingappPro.Batsman.BatsmanHistoryActivity;
import apps.shehryar.com.cricketscroingappPro.Batsman.BatsmanUtilityFunctionsClass;
import apps.shehryar.com.cricketscroingappPro.Batsman.BatsmanViewsUpdater;
import apps.shehryar.com.cricketscroingappPro.Bowler.Bowler;
import apps.shehryar.com.cricketscroingappPro.Bowler.BowlerDataUpdater;
import apps.shehryar.com.cricketscroingappPro.Bowler.BowlerHelper;
import apps.shehryar.com.cricketscroingappPro.Bowler.BowlerViewsUpdater;
import apps.shehryar.com.cricketscroingappPro.Bowler.BowlersHisotryActivity;
import apps.shehryar.com.cricketscroingappPro.Fragments.FragmentNavigationDrawer;
import apps.shehryar.com.cricketscroingappPro.Interfaces.AfterOverDialogButtonsListener;
import apps.shehryar.com.cricketscroingappPro.Listeners.OnListItemClickListener;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Match.Previous_Match_Result;
import apps.shehryar.com.cricketscroingappPro.Match.ResumeMatch;
import apps.shehryar.com.cricketscroingappPro.Match.ShareScoreDialog;
import apps.shehryar.com.cricketscroingappPro.Overs.AfterOverDialogFragment;
import apps.shehryar.com.cricketscroingappPro.Overs.Over;
import apps.shehryar.com.cricketscroingappPro.Overs.OverHelper;
import apps.shehryar.com.cricketscroingappPro.Overs.OversDataUpdater;
import apps.shehryar.com.cricketscroingappPro.Partenership.PartenershipsActivity;
import apps.shehryar.com.cricketscroingappPro.SuggestionDialog;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import apps.shehryar.com.cricketscroingappPro.Team.TeamDataUpdater;
import apps.shehryar.com.cricketscroingappPro.Team.TeamDetailsDialog;
import apps.shehryar.com.cricketscroingappPro.Team.TeamViewsUpdater;
import apps.shehryar.com.cricketscroingappPro.UpgradeToProDialog;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.ActivityStarter;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.AdsLoader;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.AlertDialogBuilder;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.AppSharerAndRater;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.Constants;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.FontProvider;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.Formatter;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.SharedPrefsHelper;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.UtilityFunctions;
import apps.shehryar.com.cricketscroingappPro.WicketDialogFramgent;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.itextpdf.text.pdf.PdfBoolean;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import util.IabHelper;
import util.IabResult;
import util.Purchase;

public class MainActivity_Test extends AppCompatActivity implements View.OnClickListener, SuggestionDialog.SuggestionsClickCallBack, CompoundButton.OnCheckedChangeListener, AfterOverDialogButtonsListener, WicketDialogFramgent.WicketDialogButtonListener, UpgradeToProDialog.UpgradeToProCallBack, OnListItemClickListener {
    static int over_counter;
    Activity activity;
    AdListener adListener = new AdListener() {
        public void onAdClosed() {
            super.onAdClosed();
            AdsLoader.requestNewInterstitial(MainActivity_Test.this.interstitialAd);
        }
    };
    Button b0;
    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button b5;
    Button b6;
    Button b7;
    Button bW;
    String bat1name;
    String bat2name;
    ArrayList<Batsman> batsmanArrayList;
    BatsmanDataUpdater batsmanDataUpdater;
    BatsmanViewsUpdater batsmanViewsUpdater;
    Batsman[] batsmans;
    Button bbyes;
    Button bhistory;
    Button blegbyes;
    Button bnb;
    Bowler bowler;
    BowlerDataUpdater bowlerDataUpdater;
    BowlerViewsUpdater bowlerViewsUpdater;
    String bowlername;
    ArrayList<Bowler> bowlersArrayList;
    Button bundo;
    Button bwd;
    int code;
    Context context;
    Batsman[] currentBatsmans;
    Over currentOver;
    String date;
    DBHelper dbHelper;
    private DrawerLayout dl;
    Handler handler;
    private int innings;
    InterstitialAd interstitialAd = null;
    ArrayList<LastBall> lastballslist;
    private IabHelper mHelper;
    private IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener;
    Match match;
    long matchid;
    private NavigationView nv;
    ArrayList<String> oppPlayers;
    String opteam;
    ArrayList<Over> oversArraylist;
    OversDataUpdater oversDataUpdater;
    RadioButton rbbatsman1;
    RadioButton rbbatsman2;
    private int rbbatsmancheck;
    private ActionBarDrawerToggle t;
    Team team1;
    Team team2;
    TeamDataUpdater teamDataUpdater;
    TeamViewsUpdater teamViewsUpdater;
    long team_opp_id;
    long team_yours_id;
    Team teambat;
    Team teambowl;
    int totalovers;
    String tournament;
    TextView tvTeamNameTwo;
    TextView tvballs;
    TextView tvbat1balls;
    TextView tvbat1score;
    TextView tvbat1sr;
    TextView tvbat2balls;
    TextView tvbat2score;
    TextView tvbat2sr;
    TextView tvbowlername;
    TextView tvbowlerovers;
    TextView tvbowlerscore;
    TextView tvbowlerwickets;
    TextView tvextras;
    TextView tvoverdetails;
    TextView tvovers;
    TextView tvremaining;
    TextView tvrunrate;
    TextView tvscore;
    TextView tvteamname;
    TextView tvwickets;
    String venue;
    ViewsUpdater viewsUpdater;
    ArrayList<String> yourPlayers;
    String yourteam;

    private void changeTeamRunRate() {
    }

    public void onUndoButtonTapped() {
    }

    private void showUpgradeToProDialog() {
        UpgradeToProDialog upgradeToProDialog = new UpgradeToProDialog();
        upgradeToProDialog.setListener(this);
        upgradeToProDialog.show(getFragmentManager(), UpgradeToProDialog.class.toString());
    }

    public void onPurchaseSuccessfull() {
        Toast.makeText(getApplicationContext(), "Purchase Successful", 0).show();
    }

    public void onPurchaseFailed() {
        Toast.makeText(getApplicationContext(), "Purchase UnSuccessful", 0).show();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 10001 && i2 == -1) {
            SharedPrefsHelper.insertProFeatures(this, true);
            MyToast.showToast(this, "Congratulations on being a PRO Cricket Scorer");
            AdsLoader.hideBannerAd(this, R.id.adView);
        } else if (i == 10001 && i2 == 0) {
            SharedPrefsHelper.insertProFeatures(this, false);
        }
        if (i == 100 && i2 == -1) {
            showAfterOverDialog();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_main_v2);
        this.dl = (DrawerLayout) findViewById(R.id.main_layout);
        this.t = new ActionBarDrawerToggle(this, this.dl, R.string.Open, R.string.Close);
        this.dl.addDrawerListener(this.t);
        this.t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.nv = (NavigationView) findViewById(R.id.nv);
        getSupportFragmentManager().beginTransaction().replace(R.id.nv, FragmentNavigationDrawer.newInstance(UtilityFunctions.getNavigationMenuItems())).commit();
        this.nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                switch (itemId) {
                    case R.id.viewovershistory /*2131755739*/:
                        Intent intent = new Intent(MainActivity_Test.this, OverHistoryActivity.class);
                        intent.putExtra(DBHelper.TABLE_TEAM, MainActivity_Test.this.match.getBattingTeam());
                        MainActivity_Test.this.startActivity(intent);
                        break;
                    case R.id.viewfallofwickets /*2131755740*/:
                        Intent intent2 = new Intent(MainActivity_Test.this, FallOfWicketsActivity.class);
                        intent2.putExtra("wickets", MainActivity_Test.this.match.getBattingTeam().fallOfWicketses);
                        MainActivity_Test.this.startActivity(intent2);
                        break;
                    default:
                        switch (itemId) {
                            case R.id.viewbatsmanhistory /*2131755758*/:
                                Intent intent3 = new Intent(MainActivity_Test.this, BatsmanHistoryActivity.class);
                                intent3.putExtra("team2name", MainActivity_Test.this.match.getBowlingTeam().getName());
                                intent3.putExtra(DBHelper.TABLE_MATCH, MainActivity_Test.this.match);
                                Log.e("team id", MainActivity_Test.this.match.getTeam_Yours_id() + " " + MainActivity_Test.this.match.getTeam_opp_id());
                                MainActivity_Test.this.startActivity(intent3);
                                break;
                            case R.id.viewbowlershistory /*2131755759*/:
                                Intent intent4 = new Intent(MainActivity_Test.this, BowlersHisotryActivity.class);
                                intent4.putExtra(DBHelper.TABLE_MATCH, MainActivity_Test.this.match);
                                intent4.putExtra(DBHelper.TABLE_TEAM, MainActivity_Test.this.match.getBowlingTeam());
                                intent4.putExtra(DBHelper.TABLE_OVERS, MainActivity_Test.this.match.getBattingTeam().overs_list);
                                intent4.putExtra("fullact", true);
                                MainActivity_Test.this.startActivity(intent4);
                                break;
                            case R.id.viewPartenerships /*2131755760*/:
                                Intent intent5 = new Intent(MainActivity_Test.this, PartenershipsActivity.class);
                                intent5.putExtra("batsmen", MainActivity_Test.this.match.getBattingTeam().batsmans_list);
                                intent5.putExtra("fow", MainActivity_Test.this.match.getBattingTeam().fallOfWicketses);
                                intent5.putExtra(FirebaseAnalytics.Param.SCORE, MainActivity_Test.this.match.getBattingTeam().getScore());
                                intent5.putExtra(DBHelper.TABLE_MATCH, MainActivity_Test.this.match);
                                intent5.putExtra(DBHelper.TABLE_TEAM, MainActivity_Test.this.match.getBattingTeam());
                                MainActivity_Test.this.startActivity(intent5);
                                break;
                            case R.id.finishInnings /*2131755761*/:
                                new AlertDialog.Builder(MainActivity_Test.this.context).setTitle("Finish this Innings").setMessage("Are you sure you want to finish this innings?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        MainActivity_Test.over_counter = 0;
                                        Over.balls = 0;
                                        MainActivity_Test.this.updateDataInDB();
                                        String checkMatchFinished = MainActivity_Test.this.match.checkMatchFinished(true);
                                        if (MainActivity_Test.this.match.isTestMatch) {
                                            if (checkMatchFinished != null) {
                                                ActivityStarter.startMatchResultActivity(MainActivity_Test.this.viewsUpdater.getActivity(), MainActivity_Test.this.match);
                                            } else {
                                                ActivityStarter.startAfterInningsActivity(MainActivity_Test.this.viewsUpdater.getActivity(), MainActivity_Test.this.match);
                                            }
                                        } else if (MainActivity_Test.this.match.secondInnings) {
                                            ActivityStarter.startMatchResultActivity(MainActivity_Test.this.viewsUpdater.getActivity(), MainActivity_Test.this.match);
                                        } else {
                                            ActivityStarter.startAfterInningsActivity(MainActivity_Test.this.viewsUpdater.getActivity(), MainActivity_Test.this.match);
                                        }
                                    }
                                }).setNegativeButton("No", (DialogInterface.OnClickListener) null).show();
                                break;
                            case R.id.viewMatchDetails /*2131755762*/:
                                Intent intent6 = new Intent(MainActivity_Test.this, Previous_Match_Result.class);
                                intent6.putExtra("team1name", MainActivity_Test.this.match.getTeam1().getName());
                                intent6.putExtra("team2name", MainActivity_Test.this.match.getTeam2().getName());
                                intent6.putExtra("team1stats", MainActivity_Test.this.match.getTeam1());
                                intent6.putExtra("team2stats", MainActivity_Test.this.match.getTeam2());
                                intent6.putExtra(DBHelper.TABLE_MATCH, MainActivity_Test.this.match);
                                intent6.putExtra("code", "thismatch");
                                MainActivity_Test.this.startActivityForResult(intent6, 0);
                                break;
                        }
                }
                return true;
            }
        });
        this.handler = new Handler();
        this.currentBatsmans = new Batsman[2];
        this.dbHelper = new DBHelper(this);
        this.viewsUpdater = new ViewsUpdater(this);
        this.lastballslist = new ArrayList<>();
        this.mHelper = new IabHelper(this, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7u5kRGfbQ2KgIHfyHPw4TRR1KPmNG+PEfaJuOe6ohNhqjCy1iQyfhgvRis3AY6Bs8osPDFxI76G2XzAycB9/v6G0PCVEG4ITVkZCnVtTOx2r8p2f24LW3OQNcZrTFCF0mW7cSA2XnWEe0DYCByC0Z5TG+95FhL5rO1TJECEMum3p54l2dYnOgmJTOrnWp0ry6Jv3xC+26T0D07PFxGQoeUxOGcuL0a+rvi4IyWrwUJCX66jVh5EYnNdZ7trtAjtLEjqyAWAzcc3yUbMPUZtw1Do0S+HpKtyyeruZzpJgCl0Hpgdrz4P+7x6dCLI6QkFkrhngqBQNnGIQcev0sB6UewIDAQAB");
        this.mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
            public void onIabPurchaseFinished(IabResult iabResult, Purchase purchase) {
                if (iabResult.isFailure()) {
                    Log.d("purchaising", "Error purchasing: " + iabResult);
                    MyToast.showToast(MainActivity_Test.this, "Billing unsuccessfull");
                } else if (purchase.getSku().equals("all_pro_features")) {
                    MyToast.showToast(MainActivity_Test.this, "App is upgraded to PRO");
                    SharedPrefsHelper.insertProFeatures(MainActivity_Test.this, true);
                }
            }
        };
        if (SharedPrefsHelper.isPro(this)) {
            findViewById(R.id.adView).setVisibility(4);
        } else {
            AdsLoader.loadBannerAd(this, this, Constants.mainActAdId, R.id.adView);
        }
        boolean booleanExtra = getIntent().getBooleanExtra("resumeMatch", false);
        AlertDialogBuilder.showTipDialog(this);
        this.activity = this;
        this.context = this;
        this.batsmanDataUpdater = new BatsmanDataUpdater(this.context, this.activity);
        this.batsmanViewsUpdater = new BatsmanViewsUpdater(this.context, this.activity);
        this.teamViewsUpdater = new TeamViewsUpdater(this.context, this.activity);
        this.teamDataUpdater = new TeamDataUpdater(this.context, this.activity);
        this.bowlerViewsUpdater = new BowlerViewsUpdater(this.context, this.activity);
        this.bowlerDataUpdater = new BowlerDataUpdater(this.context, this.activity);
        this.oversDataUpdater = new OversDataUpdater(this.context, this.activity);
        if (booleanExtra) {
            this.oversArraylist = new ArrayList<>();
            Intent intent = getIntent();
            this.match = (Match) getIntent().getSerializableExtra(DBHelper.TABLE_MATCH);
            this.currentBatsmans[0] = (Batsman) intent.getSerializableExtra("bat1");
            this.currentBatsmans[1] = (Batsman) intent.getSerializableExtra("bat2");
            try {
                this.currentBatsmans[0].setTeam_Name(this.match.getBattingTeam().getName());
                this.currentBatsmans[1].setTeam_Name(this.match.getBattingTeam().getName());
                this.currentBatsmans[0].setTeamName(this.match.getBattingTeam().getName());
                this.currentBatsmans[1].setTeamName(this.match.getBattingTeam().getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.bowlername = intent.getStringExtra("bowler");
            try {
                this.bowler = checkBowler(this.bowlername, this.match.getBowlingTeam());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            this.match.getTeam1().setMatchTotalOvers(this.match.getOvers());
            this.match.getTeam2().setMatchTotalOvers(this.match.getOvers());
            this.innings = this.match.getResumeMatch().getInnings();
            this.rbbatsmancheck = this.match.getResumeMatch().getBatFacing();
            this.dbHelper = new DBHelper(this);
            initializeViews();
            if (this.rbbatsmancheck == 0) {
                this.rbbatsman1.setChecked(true);
            } else if (this.rbbatsmancheck == 1) {
                this.rbbatsman2.setChecked(true);
            }
            Log.e("code value", this.code + "");
            over_counter = this.match.getBattingTeam().getOversPlayed();
            Over.balls = this.match.getBattingTeam().getOverballs();
            try {
                Iterator<Batsman> it = this.match.getBattingTeam().getBatsmans_list().iterator();
                while (it.hasNext()) {
                    Batsman next = it.next();
                    if (next.getName().equals(this.currentBatsmans[0].getName())) {
                        this.currentBatsmans[0] = next;
                    }
                    if (next.getName().equals(this.currentBatsmans[1].getName())) {
                        this.currentBatsmans[1] = next;
                    }
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            this.totalovers = this.match.getOvers();
            this.tournament = this.match.getTournament();
            Calendar instance = Calendar.getInstance();
            this.date = instance.get(5) + "." + instance.get(2) + "." + instance.get(1);
            this.match.setDate(this.date);
            this.batsmans = new Batsman[11];
            if (this.match.firstInningsEnd || this.match.secondInningsEnd || this.match.thirdInningsEnd) {
                this.currentOver = new Over();
            } else {
                try {
                    this.currentOver = OverHelper.getCurrentOver(this.match);
                } catch (Exception e4) {
                    e4.printStackTrace();
                    this.currentOver = new Over();
                    this.match.getBattingTeam().overs_list.add(this.currentOver);
                }
            }
            this.currentOver.setBowler(this.bowler.getName());
            this.currentOver.setOverBalls(Over.balls);
            if (this.match.firstInningsEnd || this.match.secondInningsEnd || this.match.thirdInningsEnd) {
                this.match.getBattingTeam().overs_list.add(this.currentOver);
            }
            Log.i("current over balls", this.currentOver.getOverBalls() + "");
            this.batsmans[0] = this.currentBatsmans[0];
            this.batsmans[1] = this.currentBatsmans[1];
            this.match.getTeam1().setTeamID(this.match.getTeam_Yours_id());
            this.match.getTeam2().setTeamID(this.match.getTeam_opp_id());
            if (this.match.isTestMatch && this.match.followedOn) {
                this.match.getTeam3().setTeamID(this.match.getTeam_opp_id2());
                this.match.getTeam4().setTeamID(this.match.getTeam_Yours_id2());
            } else if (this.match.isTestMatch) {
                this.match.getTeam3().setTeamID(this.match.getTeam_Yours_id2());
                this.match.getTeam4().setTeamID(this.match.getTeam_opp_id2());
            }
            if (this.currentBatsmans[0].getBatsmanID() < 0) {
                this.currentBatsmans[0].setBatsmanID(this.dbHelper.insertPlayer(this.currentBatsmans[0].getName(), this.match.getBattingTeam().getTeamID(), "batsman"));
                this.dbHelper.insertBatsmanScore(this.match.getBattingTeam().getTeamID(), this.match.getMatchID(), this.currentBatsmans[0].getBatsmanID(), 0, 0, 0, 0, 0, 0, 0, 0, PdfBoolean.FALSE);
                this.match.getBattingTeam().getBatsmans_list().add(this.currentBatsmans[0]);
            }
            if (this.currentBatsmans[1].getBatsmanID() < 0) {
                this.currentBatsmans[1].setBatsmanID(this.dbHelper.insertPlayer(this.currentBatsmans[1].getName(), this.match.getBattingTeam().getTeamID(), "batsman"));
                this.dbHelper.insertBatsmanScore(this.match.getBattingTeam().getTeamID(), this.match.getMatchID(), this.currentBatsmans[1].getBatsmanID(), 0, 0, 0, 0, 0, 0, 0, 0, PdfBoolean.FALSE);
                this.match.getBattingTeam().getBatsmans_list().add(this.currentBatsmans[1]);
            }
            updateViews();
        } else {
            this.dbHelper = new DBHelper(this);
            initializeViews();
            this.rbbatsman1.setChecked(true);
            Intent intent2 = getIntent();
            this.match = (Match) intent2.getSerializableExtra(DBHelper.TABLE_MATCH);
            this.bat1name = intent2.getStringExtra("bat1name");
            this.bat2name = intent2.getStringExtra("bat2name");
            this.bowlername = intent2.getStringExtra("bowlername");
            this.yourPlayers = PlayersInputActivity.yourplayers;
            this.oppPlayers = PlayersInputActivity.oppPlayers;
            if (this.match != null) {
                getSupportActionBar().setTitle((CharSequence) this.match.getTournament());
            }
            this.date = new SimpleDateFormat("dd-MMM-yyyy").format(Calendar.getInstance().getTime());
            this.code = intent2.getIntExtra("code", 0);
            Log.e("code value", this.code + "");
            this.match.getTeam1().setName(this.match.getYourteam());
            this.match.getTeam2().setName(this.match.getOpponent());
            if (this.match.isTestMatch && this.match.firstInnings) {
                this.match.getTeam3().setName(this.match.getYourteam());
                this.match.getTeam4().setName(this.match.getOpponent());
            }
            this.match.setDate(this.date);
            this.tvteamname.setText(Formatter.cutNameHalf(this.match.getYourteam()));
            this.match.getTeam1().setMatchTotalOvers(this.match.getOvers());
            this.match.getTeam2().setMatchTotalOvers(this.match.getOvers());
            if (this.match.isTestMatch) {
                this.match.getTeam3().setMatchTotalOvers(this.match.getOvers());
                this.match.getTeam4().setMatchTotalOvers(this.match.getOvers());
            }
            this.bowler = new Bowler();
            this.currentBatsmans = new Batsman[2];
            this.batsmans = new Batsman[11];
            this.currentOver = new Over();
            for (int i = 0; i < 2; i++) {
                this.batsmans[i] = new Batsman();
            }
            for (int i2 = 0; i2 < 2; i2++) {
                this.currentBatsmans[i2] = new Batsman();
            }
            this.batsmans[0].setName(this.bat1name);
            this.batsmans[1].setName(this.bat2name);
            this.bowler.setName(this.bowlername);
            this.currentOver.setBowler(this.bowlername);
            this.currentBatsmans[0] = this.batsmans[0];
            this.currentBatsmans[1] = this.batsmans[1];
            this.match.getBowlingTeam().bowlers_list.add(this.bowler);
            this.match.getBattingTeam().batsmans_list.add(this.currentBatsmans[0]);
            this.match.getBattingTeam().batsmans_list.add(this.currentBatsmans[1]);
            try {
                this.batsmans[0].setTeam_Name(this.match.getBattingTeam().getName());
                this.batsmans[1].setTeam_Name(this.match.getBattingTeam().getName());
                this.batsmans[0].setTeamName(this.match.getBattingTeam().getName());
                this.batsmans[1].setTeamName(this.match.getBattingTeam().getName());
            } catch (Exception e5) {
                e5.printStackTrace();
            }
            if (over_counter != this.match.getOvers()) {
                this.match.getBattingTeam().overs_list.add(this.currentOver);
            }
            this.batsmanViewsUpdater.changeBatsmanName(this.currentBatsmans[0], 1);
            this.batsmanViewsUpdater.changeBatsmanName(this.currentBatsmans[1], 2);
            this.tvbowlername.setText(Formatter.cutNameHalf(this.bowler.getName()));
            if (!this.match.firstInnings) {
                this.team_yours_id = this.match.getTeam_Yours_id();
                this.team_opp_id = this.match.getTeam_opp_id();
                this.matchid = this.match.getMatchID();
            } else {
                this.team_yours_id = this.dbHelper.insertTeam(this.match.getTeam1().getName());
                this.team_opp_id = this.dbHelper.insertTeam(this.match.getTeam2().getName());
                this.match.setTeam_Yours_id(this.team_yours_id);
                this.match.setTeam_opp_id(this.team_opp_id);
                this.match.setResult("No result");
                this.match.setTime(UtilityFunctions.getCurrentTime());
                this.matchid = this.dbHelper.insertMatch(this.match);
            }
            if (this.match.firstInnings) {
                this.match.setMatchID(this.matchid);
                this.match.setTeam_Yours_id(this.team_yours_id);
                this.match.setTeam_opp_id(this.team_opp_id);
                this.match.getTeam1().setTeamID(this.team_yours_id);
                this.match.getTeam2().setTeamID(this.team_opp_id);
                if (this.match.isTestMatch) {
                    this.match.getTeam3().setTeamID(this.dbHelper.insertTeam(this.match.getTeam3().getName()));
                    this.match.getTeam4().setTeamID(this.dbHelper.insertTeam(this.match.getTeam4().getName()));
                    this.match.setTeam_Yours_id2(this.match.getTeam3().getTeamID());
                    this.match.setTeam_opp_id2(this.match.getTeam4().getTeamID());
                    this.dbHelper.updateMatch(this.match);
                }
                this.dbHelper.insertTeamScore(this.match.getBattingTeam().getTeamID(), this.match.getMatchID(), 0, 0, 0, 0, 0, 0, 0);
                this.dbHelper.insertTeamScore(this.match.getTeam2().getTeamID(), this.match.getMatchID(), 0, 0, 0, 0, 0, 0, 0);
                this.dbHelper.insertTeamScore(this.match.getTeam3().getTeamID(), this.match.getMatchID(), 0, 0, 0, 0, 0, 0, 0);
                this.dbHelper.insertTeamScore(this.match.getTeam4().getTeamID(), this.match.getMatchID(), 0, 0, 0, 0, 0, 0, 0);
                this.match.getTeam1().setTeamID(this.team_yours_id);
                this.match.getTeam2().setTeamID(this.team_opp_id);
                this.match.setTime(UtilityFunctions.getCurrentTime());
                this.teambat = this.team1;
                this.teambowl = this.team2;
            }
            this.currentBatsmans[0].setBatsmanID(this.dbHelper.insertPlayer(this.batsmans[0].getName(), this.match.getBattingTeam().getTeamID(), "batsman"));
            this.currentBatsmans[1].setBatsmanID(this.dbHelper.insertPlayer(this.batsmans[1].getName(), this.match.getBattingTeam().getTeamID(), "batsman"));
            this.bowler.setBowlerID(this.dbHelper.insertPlayer(this.bowler.getName(), this.match.getBowlingTeam().getTeamID(), "bowler"));
            this.dbHelper.insertBatsmanScore(this.match.getBattingTeam().getTeamID(), this.match.getMatchID(), this.currentBatsmans[0].getBatsmanID(), 0, 0, 0, 0, 0, 0, 0, 0, PdfBoolean.FALSE);
            this.dbHelper.insertBatsmanScore(this.match.getBattingTeam().getTeamID(), this.match.getMatchID(), this.currentBatsmans[1].getBatsmanID(), 0, 0, 0, 0, 0, 0, 0, 0, PdfBoolean.FALSE);
            this.dbHelper.insertBowlerScore(this.match.getBowlingTeam().getTeamID(), this.match.getMatchID(), this.bowler.getBowlerID(), 0, 0, 0, 0, 0);
            updateViews();
            if (SharedPrefsHelper.isPro(this)) {
                boolean z = getSharedPreferences(DBHelper.TABLE_TEAM, 0).getBoolean("dont_show_final_settings", false);
                if (this.match.firstInnings && !z) {
                    showFinalSettingsDialog();
                }
                if (z) {
                    this.match.setPerOverBalls(6);
                    this.match.setPerMatchWickets(10);
                    this.match.setSelectMOMmanually(false);
                    this.match.setNoRunForNo(false);
                    this.match.setNoRunForWide(false);
                    this.dbHelper.insertMatchSettings(this.match);
                }
            } else {
                this.match.setPerOverBalls(6);
                this.match.setPerMatchWickets(10);
                this.match.setSelectMOMmanually(false);
                this.match.setNoRunForNo(false);
                this.match.setNoRunForWide(false);
                this.dbHelper.insertMatchSettings(this.match);
            }
            if (this.match.firstInnings) {
                try {
                    addPlayingXIinSharedPrefs();
                } catch (Exception e6) {
                    e6.printStackTrace();
                }
            }
        }
        getSupportActionBar().setTitle((CharSequence) this.match.getTournament() + "-" + this.match.getInningsString());
        if (this.match.followedOn) {
            boolean z2 = this.match.thirdInnings;
        }
        ImageView imageView = (ImageView) findViewById(R.id.profile_image);
        ImageView imageView2 = (ImageView) findViewById(R.id.profile_image_team_two);
        try {
            this.match.getTeam1().setImage(SharedPrefsHelper.getTeamImage(this.context, this.match.getTeam1()));
            this.match.getTeam2().setImage(SharedPrefsHelper.getTeamImage(this.context, this.match.getTeam2()));
        } catch (Exception e7) {
            e7.printStackTrace();
            this.match.getBattingTeam().setImage((String) null);
        }
        if (this.match.getTeam1().getImage() != null && !this.match.getTeam1().getImage().isEmpty()) {
            Picasso.with(this).load(Uri.parse(this.match.getTeam1().getImage())).into(imageView);
        }
        if (this.match.getTeam2().getImage() != null && !this.match.getTeam2().getImage().isEmpty()) {
            Picasso.with(this).load(Uri.parse(this.match.getTeam2().getImage())).into(imageView2);
        }
    }

    private void addPlayingXIinSharedPrefs() throws Exception {
        if (this.match.getBattingTeam().getAllPlayerNames().size() == 11) {
            SharedPrefsHelper.addPlayingXI(this, this.match, this.match.getBattingTeam(), this.match.getBattingTeam().getAllPlayerNames());
        }
        if (this.match.getBowlingTeam().getAllPlayerNames().size() == 11) {
            SharedPrefsHelper.addPlayingXI(this, this.match, this.match.getBowlingTeam(), this.match.getBowlingTeam().getAllPlayerNames());
        }
    }

    private void showFinalSettingsDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.final_settings_layout);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_2;
        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 17;
        attributes.width = -1;
        window.setAttributes(attributes);
        dialog.show();
        final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) dialog.findViewById(R.id.settings_ed_balls_per_over);
        final AutoCompleteTextView autoCompleteTextView2 = (AutoCompleteTextView) dialog.findViewById(R.id.setting_ed_total_wickets);
        final CheckBox checkBox = (CheckBox) dialog.findViewById(R.id.cb_mom);
        final CheckBox checkBox2 = (CheckBox) dialog.findViewById(R.id.cb_no_extra_no);
        final CheckBox checkBox3 = (CheckBox) dialog.findViewById(R.id.cb_no_extra_wide);
        final CheckBox checkBox4 = (CheckBox) dialog.findViewById(R.id.cb_balls_max);
        final CheckBox checkBox5 = (CheckBox) dialog.findViewById(R.id.cb_dont_show);
        ((Button) dialog.findViewById(R.id.setting_dialog_bnext)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int i = 10;
                int i2 = 6;
                if (checkBox5.isChecked()) {
                    SharedPreferences.Editor edit = MainActivity_Test.this.getSharedPreferences(DBHelper.TABLE_TEAM, 0).edit();
                    edit.putBoolean("dont_show_final_settings", true);
                    edit.commit();
                    MainActivity_Test.this.match.setPerOverBalls(6);
                    MainActivity_Test.this.match.setPerMatchWickets(10);
                    MainActivity_Test.this.match.setSelectMOMmanually(false);
                    MainActivity_Test.this.match.setMaxBallsFeature(0);
                    dialog.dismiss();
                    return;
                }
                if (!autoCompleteTextView.getText().toString().isEmpty()) {
                    try {
                        i2 = Integer.parseInt(autoCompleteTextView.getText().toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        i2 = 0;
                    }
                }
                if (!autoCompleteTextView2.getText().toString().isEmpty()) {
                    i = Integer.parseInt(autoCompleteTextView2.getText().toString());
                }
                boolean isChecked = checkBox.isChecked();
                boolean isChecked2 = checkBox3.isChecked();
                boolean isChecked3 = checkBox2.isChecked();
                if (i == 0 || i2 == 0) {
                    MyToast.showToast(MainActivity_Test.this.context, "Balls or Wickets cannot be 0");
                    return;
                }
                MainActivity_Test.this.match.setPerOverBalls(i2);
                MainActivity_Test.this.match.setPerMatchWickets(i);
                MainActivity_Test.this.match.setSelectMOMmanually(isChecked);
                MainActivity_Test.this.match.setNoRunForWide(isChecked2);
                MainActivity_Test.this.match.setNoRunForNo(isChecked3);
                MainActivity_Test.this.match.setMaxBallsFeature(checkBox4.isChecked());
                MainActivity_Test.this.batsmans = new Batsman[MainActivity_Test.this.match.getPerMatchWickets()];
                MainActivity_Test.this.dbHelper.insertMatchSettings(MainActivity_Test.this.match);
                dialog.dismiss();
            }
        });
    }

    private void saveThisMatchSettingsInSharedPrefs(int i, int i2, boolean z, boolean z2, boolean z3) {
        SharedPreferences.Editor edit = getSharedPreferences(DBHelper.TABLE_TEAM, 0).edit();
        edit.putBoolean("match_settings " + this.match.getMatchID(), true);
        edit.putInt("match_balls " + this.match.getMatchID(), i);
        edit.putInt("match_wickets " + this.match.getMatchID(), i2);
        edit.putBoolean("select_mom " + this.match.getMatchID(), z);
        edit.putBoolean("no_run_for_wide " + this.match.getMatchID(), z2);
        edit.putBoolean("no_run_for_no " + this.match.getMatchID(), z3);
        edit.commit();
    }

    /* access modifiers changed from: package-private */
    public void setCurrentBatsmen(ArrayList<Batsman> arrayList, String str, String str2) {
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).isOut().equals(PdfBoolean.FALSE)) {
                arrayList2.add(arrayList.get(i));
            }
        }
        if (this.match.firstInningsEnd || this.match.secondInningsEnd || this.match.thirdInningsEnd || arrayList2.isEmpty()) {
            this.currentBatsmans[0] = new Batsman();
            this.currentBatsmans[1] = new Batsman();
            this.currentBatsmans[0].setName(str);
            this.currentBatsmans[1].setName(str2);
            this.match.getBattingTeam().batsmans_list.add(this.currentBatsmans[0]);
            this.match.getBattingTeam().batsmans_list.add(this.currentBatsmans[1]);
            return;
        }
        this.currentBatsmans[0] = (Batsman) arrayList2.get(0);
        try {
            this.currentBatsmans[1] = (Batsman) arrayList2.get(1);
        } catch (Exception e) {
            e.printStackTrace();
            this.currentBatsmans[1] = new Batsman();
            this.currentBatsmans[1].setName(str2);
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        saveDatainSharedPrefs();
        updateDataInDB();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        if (!SharedPrefsHelper.isPro(this)) {
            return true;
        }
        menu.getItem(4).setVisible(false);
        return true;
    }

    @RequiresApi(api = 19)
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (this.t.onOptionsItemSelected(menuItem)) {
            return true;
        }
        switch (menuItem.getItemId()) {
            case R.id.shareThisApp /*2131755771*/:
                AppSharerAndRater.shareThisApp(this);
                break;
            case R.id.rateThisApp /*2131755772*/:
                AppSharerAndRater.rateThisApp(this, this);
                break;
            case R.id.help /*2131755773*/:
                startActivity(new Intent(this, HelpActivity.class));
                break;
            case R.id.share_current_score /*2131755774*/:
                String currentScoreString = UtilityFunctions.getCurrentScoreString(this.match, this.match.getTeam1(), this.match.getTeam2(), this.currentBatsmans[0], this.currentBatsmans[1], this.bowler);
                ShareScoreDialog shareScoreDialog = new ShareScoreDialog();
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.SCORE, currentScoreString);
                shareScoreDialog.setArguments(bundle);
                shareScoreDialog.show(getFragmentManager(), "share score dialog");
                break;
            case R.id.upgrade /*2131755775*/:
                showUpgradeToProDialog();
                break;
            case R.id.contact_us /*2131755776*/:
                Intent intent = new Intent("android.intent.action.SENDTO", Uri.fromParts("mailto", "shehryarzaheer112@gmail.com", (String) null));
                intent.putExtra("android.intent.extra.SUBJECT", "Cricket Scorer Query");
                intent.putExtra("android.intent.extra.TEXT", "");
                startActivity(Intent.createChooser(intent, "Send email..."));
                break;
            case R.id.privacy_policy /*2131755777*/:
                showPrivacyPolicy();
                break;
        }
        return true;
    }

    private Bowler checkBowler(String str, Team team) throws Exception {
        Bowler bowler2;
        String str2 = str;
        Team team3 = team;
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= team3.bowlers_list.size()) {
                bowler2 = null;
                break;
            } else if (team3.bowlers_list.get(i).getName().equals(str2)) {
                bowler2 = team3.bowlers_list.get(i);
                z = true;
                break;
            } else {
                i++;
            }
        }
        if (z) {
            return bowler2;
        }
        Bowler bowler3 = new Bowler();
        bowler3.setName(str2);
        try {
            bowler3.setTeamName(this.match.getBowlingTeam().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        bowler3.setBowlerID(this.dbHelper.insertPlayer(bowler3.getName(), team.getTeamID(), "bowler"));
        this.dbHelper.insertBowlerScore(team.getTeamID(), this.match.getMatchID(), bowler3.getBowlerID(), 0, 0, 0, 0, 0);
        this.match.getBowlingTeam().bowlers_list.add(bowler3);
        return bowler3;
    }

    /* access modifiers changed from: package-private */
    public void changeBowler() {
        if (this.currentOver.getOverBalls() > 0) {
            MyToast.showToast(getApplicationContext(), "Bowler cannot be changed now.");
            return;
        }
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.change_bowler_dialog_layout);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_2;
        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 17;
        attributes.width = -1;
        window.setAttributes(attributes);
        dialog.show();
        final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) dialog.findViewById(R.id.edchangebowlername);
        autoCompleteTextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UtilityFunctions.openSuggestionsDialog(MainActivity_Test.this, autoCompleteTextView, MainActivity_Test.this.match.getBowlingTeam().getAllPlayingXIPlayers(), false, false);
            }
        });
        ((Button) dialog.findViewById(R.id.wicketdialgobnext)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (autoCompleteTextView.getText().toString().isEmpty()) {
                    MyToast.showToast(MainActivity_Test.this.getApplicationContext(), "Enter the name of the edBowler first");
                } else if (autoCompleteTextView.getText().toString().equals(MainActivity_Test.this.bowler.getName())) {
                    dialog.dismiss();
                } else if (MainActivity_Test.this.checkPreviousBowler(autoCompleteTextView.getText().toString())) {
                    MyToast.showToast(MainActivity_Test.this.getApplicationContext(), "This bolwer has bowled the previous over. Please change the Bowler.");
                } else {
                    String obj = autoCompleteTextView.getText().toString();
                    boolean z = false;
                    if (MainActivity_Test.this.bowler.getBowlerovers() > 0 || MainActivity_Test.this.bowler.getBalls() > 0) {
                        z = true;
                    }
                    int access$100 = MainActivity_Test.this.getBowlerIndexFromList(obj);
                    int unused = MainActivity_Test.this.getBowlerIndexFromList(MainActivity_Test.this.bowler.getName());
                    if (!z) {
                        MainActivity_Test.this.dbHelper.DeletePlayer(MainActivity_Test.this.match.getBowlingTeam().getTeamID(), MainActivity_Test.this.bowler.getBowlerID());
                        MainActivity_Test.this.dbHelper.DeleteBowlerScore(MainActivity_Test.this.match.getMatchID(), MainActivity_Test.this.match.getBowlingTeam().getTeamID(), MainActivity_Test.this.bowler.getBowlerID());
                        MainActivity_Test.this.match.getBowlingTeam().bowlers_list.remove(MainActivity_Test.this.match.getBowlingTeam().bowlers_list.size() - 1);
                    }
                    if (access$100 != -1) {
                        try {
                            MainActivity_Test.this.bowler = MainActivity_Test.this.match.getBowlingTeam().bowlers_list.get(access$100);
                        } catch (Exception e) {
                            e.printStackTrace();
                            MainActivity_Test.this.bowler = new Bowler();
                            MainActivity_Test.this.match.getBowlingTeam().getBowlers_list().add(MainActivity_Test.this.bowler);
                        }
                        MainActivity_Test.this.currentOver.setBowler(MainActivity_Test.this.bowler.getName());
                    } else {
                        MainActivity_Test.this.bowler = new Bowler();
                        MainActivity_Test.this.bowler.setName(obj);
                        MainActivity_Test.this.bowler.setTeamName(MainActivity_Test.this.match.getBowlingTeam().getName());
                        MainActivity_Test.this.match.getBowlingTeam().bowlers_list.add(MainActivity_Test.this.bowler);
                        MainActivity_Test.this.currentOver.setBowler(MainActivity_Test.this.bowler.getName());
                        MainActivity_Test.this.bowler.setBowlerID(MainActivity_Test.this.dbHelper.insertPlayer(MainActivity_Test.this.bowler.getName(), MainActivity_Test.this.match.getBowlingTeam().getTeamID(), "bowler"));
                        MainActivity_Test.this.dbHelper.insertBowlerScore(MainActivity_Test.this.match.getBowlingTeam().getTeamID(), MainActivity_Test.this.matchid, MainActivity_Test.this.bowler.getBowlerID(), 0, 0, 0, 0, 0);
                    }
                    MainActivity_Test.this.tvbowlername.setText(MainActivity_Test.this.bowler.getName());
                    MainActivity_Test.this.changeBowlerScoreText();
                    dialog.dismiss();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean checkPreviousBowler(String str) {
        try {
            return this.match.getBattingTeam().overs_list.get(this.match.getBattingTeam().overs_list.size() - 2).getBowler().equals(str);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* access modifiers changed from: private */
    public int getBowlerIndexFromList(String str) {
        for (int i = 0; i < this.match.getBowlingTeam().bowlers_list.size(); i++) {
            if (this.match.getBowlingTeam().bowlers_list.get(i).getName().equals(str)) {
                return i;
            }
        }
        return -1;
    }

    private void initializeViews() {
        Typeface.createFromAsset(getAssets(), "fonts/RobotoCondensed_Bold.ttf");
        this.rbbatsman1 = (RadioButton) findViewById(R.id.rbbatsman1);
        this.rbbatsman2 = (RadioButton) findViewById(R.id.rbbatsman2);
        this.tvscore = (TextView) findViewById(R.id.tvscore);
        this.tvovers = (TextView) findViewById(R.id.tvovers);
        this.tvbowlername = (TextView) findViewById(R.id.tvbowlername);
        this.tvscore = (TextView) findViewById(R.id.tvscore);
        this.tvbat1score = (TextView) findViewById(R.id.tvbat1score);
        this.tvbat1sr = (TextView) findViewById(R.id.tvbat1strikerate);
        this.tvbat2score = (TextView) findViewById(R.id.tvbat2score);
        this.tvbat2sr = (TextView) findViewById(R.id.tvbat2strikerate);
        this.tvextras = (TextView) findViewById(R.id.tvextras);
        this.tvbowlerscore = (TextView) findViewById(R.id.tv_bowler_score);
        this.tvoverdetails = (TextView) findViewById(R.id.tvthisover);
        this.tvoverdetails.setSelected(true);
        this.tvrunrate = (TextView) findViewById(R.id.tvrunrate);
        this.b0 = (Button) findViewById(R.id.b0);
        this.b1 = (Button) findViewById(R.id.b1);
        this.b2 = (Button) findViewById(R.id.b2);
        this.b3 = (Button) findViewById(R.id.b3);
        this.b4 = (Button) findViewById(R.id.b4);
        this.b5 = (Button) findViewById(R.id.b5);
        this.b6 = (Button) findViewById(R.id.b6);
        this.b7 = (Button) findViewById(R.id.b7);
        this.bW = (Button) findViewById(R.id.bW);
        this.bwd = (Button) findViewById(R.id.bwd);
        this.bnb = (Button) findViewById(R.id.bnb);
        this.bundo = (Button) findViewById(R.id.bundo);
        this.bbyes = (Button) findViewById(R.id.bbyes);
        this.blegbyes = (Button) findViewById(R.id.blegbyes);
        this.bhistory = (Button) findViewById(R.id.bhistory);
        this.tvremaining = (TextView) findViewById(R.id.tvremainingscore);
        this.tvteamname = (TextView) findViewById(R.id.teamname);
        this.tvTeamNameTwo = (TextView) findViewById(R.id.team_2_name);
        this.tvbowlerovers = (TextView) findViewById(R.id.tvbowlerovers);
        this.tvteamname.setOnClickListener(this);
        this.tvTeamNameTwo.setOnClickListener(this);
        this.tvscore.setOnClickListener(this);
        this.tvbat1score.setOnClickListener(this);
        this.tvbat2score.setOnClickListener(this);
        this.tvbowlerscore.setOnClickListener(this);
        this.tvbowlername.setOnClickListener(this);
        this.tvbat1sr.setOnClickListener(this);
        this.tvbat2sr.setOnClickListener(this);
        this.rbbatsman1.setOnCheckedChangeListener(this);
        this.rbbatsman2.setOnCheckedChangeListener(this);
    }

    @RequiresApi(api = 11)
    public void buttonClick(View view) {
        switch (view.getId()) {
            case R.id.b7 /*2131755281*/:
                manageScore(7);
                break;
            case R.id.b5 /*2131755282*/:
                manageScore(5);
                break;
            case R.id.b6 /*2131755283*/:
                manageScore(6);
                break;
            case R.id.b3 /*2131755284*/:
                manageScore(3);
                break;
            case R.id.b4 /*2131755285*/:
                manageScore(4);
                break;
            case R.id.b1 /*2131755286*/:
                manageScore(1);
                break;
            case R.id.b0 /*2131755287*/:
                manageScore(0);
                break;
            case R.id.bW /*2131755289*/:
                PopupMenu popupMenu = new PopupMenu(this, this.bW);
                popupMenu.getMenuInflater().inflate(R.menu.wicket_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        int itemId = menuItem.getItemId();
                        if (itemId != R.id.mretiredhurt) {
                            switch (itemId) {
                                case R.id.mwicket /*2131755804*/:
                                    MainActivity_Test.this.afterWicket(0);
                                    break;
                                case R.id.mwicketcaught /*2131755805*/:
                                    MainActivity_Test.this.afterWicket(1);
                                    break;
                                case R.id.mwicketlbw /*2131755806*/:
                                    MainActivity_Test.this.afterWicket(2);
                                    break;
                                case R.id.mwicketstumped /*2131755807*/:
                                    MainActivity_Test.this.afterWicket(3);
                                    break;
                                case R.id.mhitwicket /*2131755808*/:
                                    MainActivity_Test.this.afterWicket(4);
                                    break;
                                case R.id.mrunout /*2131755809*/:
                                    MainActivity_Test.this.handleRunOut2();
                                    break;
                                case R.id.mcaughtandbowled /*2131755810*/:
                                    MainActivity_Test.this.afterWicket(6);
                                    break;
                                case R.id.mobstruct /*2131755811*/:
                                    MainActivity_Test.this.afterWicket(7);
                                    break;
                                case R.id.mhandling /*2131755812*/:
                                    MainActivity_Test.this.afterWicket(8);
                                    break;
                            }
                        } else {
                            MainActivity_Test.this.handleRetired();
                        }
                        return true;
                    }
                });
                popupMenu.show();
                break;
            case R.id.b2 /*2131755290*/:
                manageScore(2);
                break;
            case R.id.bhistory /*2131755291*/:
                PopupMenu popupMenu2 = new PopupMenu(this, this.bhistory);
                popupMenu2.getMenuInflater().inflate(R.menu.undo_menu, popupMenu2.getMenu());
                popupMenu2.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.changeBowlerHurted /*2131755796*/:
                                try {
                                    MainActivity_Test.this.changeBowlerHurted();
                                    return true;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    MyToast.showToast(MainActivity_Test.this, "Some thing went wrong while changing edBowler");
                                    return true;
                                }
                            case R.id.mretiredhurt /*2131755797*/:
                                MainActivity_Test.this.handleRetired();
                                return true;
                            case R.id.changeMatchDetails /*2131755798*/:
                                if (MainActivity_Test.this.code != 99) {
                                    MainActivity_Test.this.changeMatchDetails();
                                    return true;
                                }
                                MyToast.showToast(MainActivity_Test.this, "Match details cannot be changed in 2nd Innings");
                                return true;
                            case R.id.changeBatsmanMenu /*2131755799*/:
                                MainActivity_Test.this.changeBatsman();
                                return true;
                            case R.id.changeBowlerMenu /*2131755800*/:
                                MainActivity_Test.this.changeBowler();
                                return true;
                            default:
                                return true;
                        }
                    }
                });
                popupMenu2.show();
                break;
            case R.id.bundo /*2131755293*/:
                if (this.tvovers.getText().toString().equals("0") && this.tvballs.getText().toString().equals("0") && this.tvoverdetails.getText().toString().isEmpty()) {
                    over_counter = 0;
                    this.lastballslist.clear();
                    Toast.makeText(getApplicationContext(), "No ball bowled yet", 0).show();
                    break;
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("UNDO").setMessage("Are you sure you want to undo").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            MainActivity_Test.this.manageUndo();
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    builder.create();
                    builder.show();
                    break;
                }
                break;
            case R.id.blegbyes /*2131755294*/:
                PopupMenu popupMenu3 = new PopupMenu(this, this.blegbyes);
                popupMenu3.getMenuInflater().inflate(R.menu.legbyesmenu, popupMenu3.getMenu());
                popupMenu3.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.legbyes1 /*2131755763*/:
                                MainActivity_Test.this.manageLegByes(1);
                                break;
                            case R.id.legbyes2 /*2131755764*/:
                                MainActivity_Test.this.manageLegByes(2);
                                break;
                            case R.id.legbyes3 /*2131755765*/:
                                MainActivity_Test.this.manageLegByes(3);
                                break;
                            case R.id.legbyes4 /*2131755766*/:
                                MainActivity_Test.this.manageLegByes(4);
                                break;
                            case R.id.legbyes5 /*2131755767*/:
                                MainActivity_Test.this.manageLegByes(5);
                                break;
                            case R.id.legbyes6 /*2131755768*/:
                                MainActivity_Test.this.manageLegByes(6);
                                break;
                            case R.id.legbyes7 /*2131755769*/:
                                MainActivity_Test.this.manageLegByes(7);
                                break;
                            case R.id.legbyesWicket /*2131755770*/:
                                AlertDialogBuilder.showWicketInfoDialog(MainActivity_Test.this, "Leg Byes");
                                break;
                        }
                        return true;
                    }
                });
                popupMenu3.show();
                break;
            case R.id.bbyes /*2131755295*/:
                PopupMenu popupMenu4 = new PopupMenu(this, this.bbyes);
                popupMenu4.getMenuInflater().inflate(R.menu.byes_menu, popupMenu4.getMenu());
                popupMenu4.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.byes1 /*2131755742*/:
                                MainActivity_Test.this.managebyes(1);
                                break;
                            case R.id.byes2 /*2131755743*/:
                                MainActivity_Test.this.managebyes(2);
                                break;
                            case R.id.byes3 /*2131755744*/:
                                MainActivity_Test.this.managebyes(3);
                                break;
                            case R.id.byes4 /*2131755745*/:
                                MainActivity_Test.this.managebyes(4);
                                break;
                            case R.id.byes5 /*2131755746*/:
                                MainActivity_Test.this.managebyes(5);
                                break;
                            case R.id.byes6 /*2131755747*/:
                                MainActivity_Test.this.managebyes(6);
                                break;
                            case R.id.byes7 /*2131755748*/:
                                MainActivity_Test.this.managebyes(7);
                                break;
                            case R.id.byesWicket /*2131755749*/:
                                AlertDialogBuilder.showWicketInfoDialog(MainActivity_Test.this, "Byes");
                                break;
                        }
                        return true;
                    }
                });
                popupMenu4.show();
                break;
            case R.id.bnb /*2131755296*/:
                PopupMenu popupMenu5 = new PopupMenu(this, this.bnb);
                popupMenu5.getMenuInflater().inflate(R.menu.noball_menu, popupMenu5.getMenu());
                popupMenu5.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.nb0 /*2131755783*/:
                                MainActivity_Test.this.manageExtranos(0, false);
                                break;
                            case R.id.nb1 /*2131755784*/:
                                MainActivity_Test.this.showByesLegByesDialog(1);
                                break;
                            case R.id.nb2 /*2131755785*/:
                                MainActivity_Test.this.showByesLegByesDialog(2);
                                break;
                            case R.id.nb3 /*2131755786*/:
                                MainActivity_Test.this.showByesLegByesDialog(3);
                                break;
                            case R.id.nb4 /*2131755787*/:
                                MainActivity_Test.this.showByesLegByesDialog(4);
                                break;
                            case R.id.nb5 /*2131755788*/:
                                MainActivity_Test.this.showByesLegByesDialog(5);
                                break;
                            case R.id.nb6 /*2131755789*/:
                                MainActivity_Test.this.showByesLegByesDialog(6);
                                break;
                            case R.id.nb7 /*2131755790*/:
                                MainActivity_Test.this.showByesLegByesDialog(7);
                                break;
                            case R.id.noBallWicket /*2131755791*/:
                                AlertDialogBuilder.showWicketInfoDialog(MainActivity_Test.this, "No Ball");
                                break;
                        }
                        return true;
                    }
                });
                popupMenu5.show();
                break;
            case R.id.bwd /*2131755297*/:
                PopupMenu popupMenu6 = new PopupMenu(this, this.bwd);
                popupMenu6.getMenuInflater().inflate(R.menu.wide_menu, popupMenu6.getMenu());
                popupMenu6.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.wd0 /*2131755813*/:
                                MainActivity_Test.this.manageExtraWides(0);
                                break;
                            case R.id.wd1 /*2131755814*/:
                                MainActivity_Test.this.manageExtraWides(1);
                                break;
                            case R.id.wd2 /*2131755815*/:
                                MainActivity_Test.this.manageExtraWides(2);
                                break;
                            case R.id.wd3 /*2131755816*/:
                                MainActivity_Test.this.manageExtraWides(3);
                                break;
                            case R.id.wd4 /*2131755817*/:
                                MainActivity_Test.this.manageExtraWides(4);
                                break;
                            case R.id.wd5 /*2131755818*/:
                                MainActivity_Test.this.manageExtraWides(5);
                                break;
                            case R.id.wd6 /*2131755819*/:
                                MainActivity_Test.this.manageExtraWides(6);
                                break;
                            case R.id.wd7 /*2131755820*/:
                                MainActivity_Test.this.manageExtraWides(7);
                                break;
                            case R.id.wide_Wicket /*2131755821*/:
                                AlertDialogBuilder.showWicketInfoDialog(MainActivity_Test.this, "Wide Ball");
                                break;
                        }
                        return true;
                    }
                });
                popupMenu6.show();
                break;
        }
        changeRemainingScoreText();
    }

    /* access modifiers changed from: private */
    public void changeBowlerHurted() throws Exception {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.retired_bowler_dialog);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_2;
        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.gravity = 48;
        window.setAttributes(attributes);
        dialog.show();
        Button button = (Button) dialog.findViewById(R.id.retired_dialog_go_next);
        final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) dialog.findViewById(R.id.retirededbowlername);
        try {
            FontProvider.setEuroStileFont(this, button);
        } catch (Exception e) {
            e.printStackTrace();
        }
        UtilityFunctions.openSuggestionsDialog(this, autoCompleteTextView, this.match.getBowlingTeam().getAllPlayerNames(), false, false);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String str;
                if (autoCompleteTextView.getText().toString().isEmpty()) {
                    MyToast.showToast(MainActivity_Test.this.getApplicationContext(), "Please Enter the name of Bowler first");
                    return;
                }
                boolean z = true;
                try {
                    str = MainActivity_Test.this.match.getBattingTeam().overs_list.get(MainActivity_Test.this.match.getBattingTeam().overs_list.size() - 1).getBowler();
                } catch (Exception e) {
                    e.printStackTrace();
                    str = " ";
                }
                String obj = autoCompleteTextView.getText().toString();
                if (MainActivity_Test.over_counter > 1 ? autoCompleteTextView.getText().toString().equals(str) : false) {
                    MyToast.showToast(MainActivity_Test.this.getApplicationContext(), "Please change Bowler. This edBowler bowled previous over");
                } else {
                    MainActivity_Test.this.updateDataInDB();
                    Team bowlingTeam = MainActivity_Test.this.match.getBowlingTeam();
                    Log.e("bowlers list", bowlingTeam.bowlers_list.size() + "");
                    int i = 0;
                    while (true) {
                        if (i >= bowlingTeam.bowlers_list.size()) {
                            z = false;
                            break;
                        } else if (bowlingTeam.bowlers_list.get(i).getName().equals(obj)) {
                            MainActivity_Test.this.bowler = bowlingTeam.bowlers_list.get(i);
                            break;
                        } else {
                            i++;
                        }
                    }
                    if (!z) {
                        MainActivity_Test.this.bowler = new Bowler();
                        MainActivity_Test.this.bowler.setName(obj);
                        MainActivity_Test.this.bowler.setBowlerID(MainActivity_Test.this.dbHelper.insertPlayer(MainActivity_Test.this.bowler.getName(), MainActivity_Test.this.match.getBowlingTeam().getTeamID(), "bowler"));
                        MainActivity_Test.this.dbHelper.insertBowlerScore(MainActivity_Test.this.match.getBowlingTeam().getTeamID(), MainActivity_Test.this.match.getMatchID(), MainActivity_Test.this.bowler.getBowlerID(), 0, 0, 0, 0, 0);
                        bowlingTeam.bowlers_list.add(MainActivity_Test.this.bowler);
                        MainActivity_Test.this.bowlerViewsUpdater.changeBowlerNameScoreAndOversText(MainActivity_Test.this.bowler);
                    } else {
                        MainActivity_Test.this.bowlerViewsUpdater.changeBowlerNameScoreAndOversText(MainActivity_Test.this.bowler);
                    }
                }
                dialog.dismiss();
            }
        });
    }

    @RequiresApi(api = 21)
    private void showSuggestionsInADialog(final Dialog dialog, AutoCompleteTextView autoCompleteTextView, int i) throws Exception {
        autoCompleteTextView.setShowSoftInputOnFocus(false);
        final AnonymousClass15 r6 = new SuggestionDialog.SuggestionsClickCallBack() {
            public void onSuggestionClicked(EditText editText, String str) {
                editText.setText(str);
            }

            public void onTypeYourOwnClick(EditText editText) {
                editText.setText("");
                editText.setFocusable(true);
                editText.requestFocus();
                dialog.getWindow().setSoftInputMode(5);
            }
        };
        final Dialog dialog2 = dialog;
        final int i2 = i;
        final AutoCompleteTextView autoCompleteTextView2 = autoCompleteTextView;
        autoCompleteTextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dialog2.getWindow().setSoftInputMode(3);
                if (i2 == 1) {
                    if (MainActivity_Test.this.code == 99) {
                        MainActivity_Test.this.showSuggestionsDialog(autoCompleteTextView2, UtilityFunctions.getNotOutBatsmen(PlayersInputActivity.oppPlayers, MainActivity_Test.this.match.getBattingTeam().batsmans_list), r6);
                    } else {
                        MainActivity_Test.this.showSuggestionsDialog(autoCompleteTextView2, UtilityFunctions.getNotOutBatsmen(PlayersInputActivity.yourplayers, MainActivity_Test.this.match.getBattingTeam().batsmans_list), r6);
                    }
                } else if (i2 != 2) {
                } else {
                    if (MainActivity_Test.this.code == 99) {
                        MainActivity_Test.this.showSuggestionsDialog(autoCompleteTextView2, PlayersInputActivity.yourplayers, r6);
                    } else {
                        MainActivity_Test.this.showSuggestionsDialog(autoCompleteTextView2, PlayersInputActivity.oppPlayers, r6);
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void changeMatchDetails() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.update_match_details_layout);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_2;
        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 17;
        attributes.width = -1;
        window.setAttributes(attributes);
        dialog.show();
        final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) dialog.findViewById(R.id.edchangetournamentname);
        final AutoCompleteTextView autoCompleteTextView2 = (AutoCompleteTextView) dialog.findViewById(R.id.edchangevenue);
        final AutoCompleteTextView autoCompleteTextView3 = (AutoCompleteTextView) dialog.findViewById(R.id.edchangeovers);
        autoCompleteTextView.setText(this.match.getTournament());
        autoCompleteTextView2.setText(this.match.getVenue());
        autoCompleteTextView3.setText(this.match.getOvers() + "");
        hideKeyBoard(autoCompleteTextView);
        hideKeyBoard(autoCompleteTextView2);
        hideKeyBoard(autoCompleteTextView3);
        autoCompleteTextView3.setFilters(new InputFilter[]{new InputFilterMinMax("1", "50")});
        ((Button) dialog.findViewById(R.id.wicketdialgobnext)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (autoCompleteTextView.getText().toString().isEmpty() || autoCompleteTextView2.getText().toString().isEmpty() || autoCompleteTextView3.getText().toString().isEmpty()) {
                    MyToast.showToast(MainActivity_Test.this.getApplicationContext(), "Please fill all the fields first");
                } else if (Integer.parseInt(autoCompleteTextView3.getText().toString()) <= MainActivity_Test.over_counter) {
                    MyToast.showToast(MainActivity_Test.this.getApplicationContext(), "Please choose a bigger number for overs.");
                } else {
                    MainActivity_Test.this.tournament = autoCompleteTextView.getText().toString();
                    MainActivity_Test.this.venue = autoCompleteTextView2.getText().toString();
                    MainActivity_Test.this.totalovers = Integer.parseInt(autoCompleteTextView3.getText().toString());
                    MainActivity_Test.this.match.setOvers(Integer.parseInt(autoCompleteTextView3.getText().toString()));
                    MainActivity_Test.this.match.getTeam1().setMatchTotalOvers(MainActivity_Test.this.match.getOvers());
                    MainActivity_Test.this.match.getTeam2().setMatchTotalOvers(MainActivity_Test.this.match.getOvers());
                    MainActivity_Test.this.match.getTeam3().setMatchTotalOvers(MainActivity_Test.this.match.getOvers());
                    MainActivity_Test.this.match.getTeam4().setMatchTotalOvers(MainActivity_Test.this.match.getOvers());
                    MainActivity_Test.this.viewsUpdater.updateViews(MainActivity_Test.this.match, MainActivity_Test.this.currentBatsmans, MainActivity_Test.this.bowler, MainActivity_Test.this.currentOver);
                    MainActivity_Test.this.dbHelper.UpdateMatchDetails(MainActivity_Test.this.match.getMatchID(), autoCompleteTextView.getText().toString(), autoCompleteTextView2.getText().toString(), Integer.parseInt(autoCompleteTextView3.getText().toString()));
                    Toast.makeText(MainActivity_Test.this.getApplicationContext(), "Match updated Successfully", 0).show();
                    dialog.dismiss();
                }
            }
        });
    }

    private void showPrivacyPolicy() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        WebView webView = new WebView(this);
        webView.loadUrl("file:///android_asset/privacypolicy.html");
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                webView.loadUrl(str);
                return true;
            }
        });
        builder.setView(webView);
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    /* access modifiers changed from: private */
    public void changeBatsman() {
        if ((this.currentBatsmans[0].getBallsfaced() > 0 || this.currentBatsmans[0].getTotalScore() > 0) && (this.currentBatsmans[1].getBallsfaced() > 0 || this.currentBatsmans[1].getTotalScore() > 0)) {
            MyToast.showToast(getApplicationContext(), "No Batsman can be changed after playing a ball.");
            return;
        }
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.change_batsman_layout);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_2;
        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 17;
        attributes.width = -1;
        window.setAttributes(attributes);
        dialog.show();
        Button button = (Button) dialog.findViewById(R.id.wicketdialgobnext);
        final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) dialog.findViewById(R.id.edchangebat1name);
        final AutoCompleteTextView autoCompleteTextView2 = (AutoCompleteTextView) dialog.findViewById(R.id.edchangebat2name);
        new SuggestionDialog.SuggestionsClickCallBack() {
            public void onSuggestionClicked(EditText editText, String str) {
                editText.setText(str);
            }

            public void onTypeYourOwnClick(EditText editText) {
                editText.setText("");
                editText.setFocusable(true);
                editText.requestFocus();
                dialog.getWindow().setSoftInputMode(5);
            }
        };
        autoCompleteTextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UtilityFunctions.openSuggestionsDialog(MainActivity_Test.this, autoCompleteTextView, MainActivity_Test.this.match.getBattingTeam().getAllPlayingXIPlayers(), false, false);
            }
        });
        autoCompleteTextView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UtilityFunctions.openSuggestionsDialog(MainActivity_Test.this, autoCompleteTextView2, MainActivity_Test.this.match.getBattingTeam().getAllPlayingXIPlayers(), false, false);
            }
        });
        autoCompleteTextView.setText(this.currentBatsmans[0].getName());
        autoCompleteTextView2.setText(this.currentBatsmans[1].getName());
        if (this.currentBatsmans[0].getBallsfaced() > 0 || this.currentBatsmans[0].getTotalScore() > 0) {
            autoCompleteTextView.setEnabled(false);
            autoCompleteTextView.setFocusable(false);
        } else if (this.currentBatsmans[1].getBallsfaced() > 0 || this.currentBatsmans[1].getTotalScore() > 0) {
            autoCompleteTextView2.setEnabled(false);
            autoCompleteTextView2.setFocusable(false);
        }
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (autoCompleteTextView.getText().toString().isEmpty() || autoCompleteTextView2.getText().toString().isEmpty()) {
                    MyToast.showToast(MainActivity_Test.this.getApplicationContext(), "Enter the name of the bothe batsmen first");
                } else if (autoCompleteTextView.getText().toString().equals(autoCompleteTextView2.getText().toString())) {
                    MyToast.showToast(MainActivity_Test.this.getApplicationContext(), "Both names cannot be same");
                } else if (autoCompleteTextView.getText().toString().equals(MainActivity_Test.this.currentBatsmans[0].getName()) && autoCompleteTextView2.getText().toString().equals(MainActivity_Test.this.currentBatsmans[1].getName())) {
                    dialog.dismiss();
                } else if (BatsmanHelper.checkBatsmanAlreadyPlayed(autoCompleteTextView.getText().toString(), MainActivity_Test.this.match.getBattingTeam())) {
                    MainActivity_Test mainActivity_Test = MainActivity_Test.this;
                    MyToast.showToast(mainActivity_Test, autoCompleteTextView.getText().toString() + " has already played. Please change Batsman.");
                } else if (BatsmanHelper.checkBatsmanAlreadyPlayed(autoCompleteTextView2.getText().toString(), MainActivity_Test.this.match.getBattingTeam())) {
                    MainActivity_Test mainActivity_Test2 = MainActivity_Test.this;
                    MyToast.showToast(mainActivity_Test2, autoCompleteTextView2.getText().toString() + " has already played. Please change Batsman.");
                } else if (MainActivity_Test.this.currentOver.getOverBalls() == 0 && MainActivity_Test.this.match.getBattingTeam().getOversPlayed() == 0) {
                    MainActivity_Test.this.currentBatsmans[0].setName(autoCompleteTextView.getText().toString());
                    MainActivity_Test.this.currentBatsmans[1].setName(autoCompleteTextView2.getText().toString());
                    MainActivity_Test.this.dbHelper.UpdateBatsmanNames(MainActivity_Test.this.match.getMatchID(), MainActivity_Test.this.match.getBattingTeam().getTeamID(), MainActivity_Test.this.currentBatsmans[0].getBatsmanID(), autoCompleteTextView.getText().toString());
                    MainActivity_Test.this.dbHelper.UpdateBatsmanNames(MainActivity_Test.this.matchid, MainActivity_Test.this.match.getBattingTeam().getTeamID(), MainActivity_Test.this.currentBatsmans[1].getBatsmanID(), autoCompleteTextView2.getText().toString());
                    dialog.dismiss();
                    MainActivity_Test.this.rbbatsman1.setText(autoCompleteTextView.getText().toString());
                    MainActivity_Test.this.rbbatsman2.setText(autoCompleteTextView2.getText().toString());
                } else if (MainActivity_Test.this.checkBatsman(autoCompleteTextView.getText().toString()) && autoCompleteTextView.isEnabled()) {
                    MyToast.showToast(MainActivity_Test.this.getApplicationContext(), "Batsman 1 has already Played. Please change the Batsman.");
                } else if (!MainActivity_Test.this.checkBatsman(autoCompleteTextView2.getText().toString()) || !autoCompleteTextView2.isEnabled()) {
                    MainActivity_Test.this.currentBatsmans[0].setName(autoCompleteTextView.getText().toString());
                    MainActivity_Test.this.currentBatsmans[1].setName(autoCompleteTextView2.getText().toString());
                    MainActivity_Test.this.dbHelper.UpdateBatsmanNames(MainActivity_Test.this.match.getMatchID(), MainActivity_Test.this.match.getBattingTeam().getTeamID(), MainActivity_Test.this.currentBatsmans[0].getBatsmanID(), autoCompleteTextView.getText().toString());
                    MainActivity_Test.this.dbHelper.UpdateBatsmanNames(MainActivity_Test.this.match.getMatchID(), MainActivity_Test.this.match.getBattingTeam().getTeamID(), MainActivity_Test.this.currentBatsmans[1].getBatsmanID(), autoCompleteTextView.getText().toString());
                    dialog.dismiss();
                    MainActivity_Test.this.rbbatsman1.setText(autoCompleteTextView.getText().toString());
                    MainActivity_Test.this.rbbatsman2.setText(autoCompleteTextView2.getText().toString());
                } else {
                    MyToast.showToast(MainActivity_Test.this.getApplicationContext(), "Batsman 2 has already Played. Please change the Batsman.");
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleRetired() {
        if (this.batsmanViewsUpdater.getCheckedRadioButton() == 0) {
            MyToast.showToast(getApplicationContext(), "Please Select the batsman facing");
        } else if (this.match.getTeam1().getWickets() == this.match.getPerMatchWickets() - 1 || this.match.getTeam2().getWickets() == this.match.getPerMatchWickets() - 1) {
            MyToast.showToast(getApplicationContext(), "Player cannot be changed");
        } else {
            final Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(1);
            dialog.setContentView(R.layout.retired_dialog);
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_2;
            Window window = dialog.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = -1;
            attributes.gravity = 48;
            window.setAttributes(attributes);
            dialog.show();
            final AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) dialog.findViewById(R.id.retirededbatname);
            autoCompleteTextView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    UtilityFunctions.openSuggestionsDialog(MainActivity_Test.this, autoCompleteTextView, UtilityFunctions.getNotOutBatsmen(MainActivity_Test.this.match.getBattingTeam().getAllPlayerNames(), MainActivity_Test.this.match.getBattingTeam().getBatsmans_list()), false, false);
                }
            });
            ((Button) dialog.findViewById(R.id.retired_dialog_go_next)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (autoCompleteTextView.getText().toString().isEmpty()) {
                        MyToast.showToast(MainActivity_Test.this.getApplicationContext(), "Please Enter the name of Batsman first");
                        return;
                    }
                    String obj = autoCompleteTextView.getText().toString();
                    if (MainActivity_Test.this.checkBatsman(obj)) {
                        MyToast.showToast(MainActivity_Test.this.getApplicationContext(), "This player has already played. Please change it.");
                    } else {
                        if (MainActivity_Test.this.batsmanViewsUpdater.getCheckedRadioButton() == 1) {
                            MainActivity_Test.this.currentBatsmans[0].setOut("Ret");
                            MainActivity_Test.this.updateDataInDB();
                            MainActivity_Test.this.currentBatsmans[0] = new Batsman();
                            MainActivity_Test.this.currentBatsmans[0].setName(obj);
                            MainActivity_Test.this.currentBatsmans[0].setBatsmanID(MainActivity_Test.this.dbHelper.insertPlayer(MainActivity_Test.this.currentBatsmans[0].getName(), MainActivity_Test.this.match.getBattingTeam().getTeamID(), "batsman"));
                            MainActivity_Test.this.match.getBattingTeam().batsmans_list.add(MainActivity_Test.this.currentBatsmans[0]);
                            MainActivity_Test.this.dbHelper.insertBatsmanScore(MainActivity_Test.this.match.getBattingTeam().getTeamID(), MainActivity_Test.this.match.getMatchID(), MainActivity_Test.this.currentBatsmans[0].getBatsmanID(), 0, 0, 0, 0, 0, 0, 0, 0, PdfBoolean.FALSE);
                            MainActivity_Test.this.changeViewsData();
                        } else {
                            MainActivity_Test.this.currentBatsmans[1].setOut("Ret");
                            MainActivity_Test.this.dbHelper.UpdateTeamScore(MainActivity_Test.this.match.getBattingTeam().getTeamID(), MainActivity_Test.this.match.getMatchID(), MainActivity_Test.this.match.getBattingTeam().getScore(), MainActivity_Test.this.match.getBattingTeam().getWickets(), MainActivity_Test.this.match.getBattingTeam().getWides(), MainActivity_Test.this.match.getBattingTeam().getNos(), MainActivity_Test.this.match.getBattingTeam().getOversPlayed(), MainActivity_Test.this.match.getBattingTeam().getExtras(), MainActivity_Test.this.match.getBattingTeam().getOverballs());
                            MainActivity_Test.this.dbHelper.UpdateBatsmanScore(MainActivity_Test.this.match.getBattingTeam().getTeamID(), MainActivity_Test.this.match.getMatchID(), MainActivity_Test.this.currentBatsmans[0]);
                            MainActivity_Test.this.dbHelper.UpdateBatsmanScore(MainActivity_Test.this.match.getBattingTeam().getTeamID(), MainActivity_Test.this.match.getMatchID(), MainActivity_Test.this.currentBatsmans[1]);
                            MainActivity_Test.this.currentBatsmans[1] = new Batsman();
                            MainActivity_Test.this.currentBatsmans[1].setName(obj);
                            MainActivity_Test.this.currentBatsmans[1].setBatsmanID(MainActivity_Test.this.dbHelper.insertPlayer(MainActivity_Test.this.currentBatsmans[1].getName(), MainActivity_Test.this.match.getBattingTeam().getTeamID(), "batsman"));
                            MainActivity_Test.this.match.getBattingTeam().batsmans_list.add(MainActivity_Test.this.currentBatsmans[1]);
                            MainActivity_Test.this.dbHelper.insertBatsmanScore(MainActivity_Test.this.match.getBattingTeam().getTeamID(), MainActivity_Test.this.match.getMatchID(), MainActivity_Test.this.currentBatsmans[1].getBatsmanID(), 0, 0, 0, 0, 0, 0, 0, 0, PdfBoolean.FALSE);
                        }
                        MainActivity_Test.this.updateViews();
                    }
                    dialog.dismiss();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x003d, code lost:
        if ((r11.currentOver.getOverExtraBalls() + r0) != r11.match.getPerOverBalls()) goto L_0x003f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void manageLegByes(final int r12) {
        /*
            r11 = this;
            android.widget.RadioButton r0 = r11.rbbatsman1
            boolean r0 = r0.isChecked()
            r1 = 0
            if (r0 != 0) goto L_0x001b
            android.widget.RadioButton r0 = r11.rbbatsman2
            boolean r0 = r0.isChecked()
            if (r0 != 0) goto L_0x001b
            java.lang.String r12 = "Please Select The batsman facing"
            android.widget.Toast r12 = android.widget.Toast.makeText(r11, r12, r1)
            r12.show()
            goto L_0x0088
        L_0x001b:
            apps.shehryar.com.cricketscroingappPro.Overs.Over r0 = r11.currentOver
            int r0 = r0.getOverBalls()
            apps.shehryar.com.cricketscroingappPro.Match.Match r2 = r11.match
            boolean r2 = r2.isMaxBallsFeature()
            if (r2 == 0) goto L_0x003f
            int r0 = r0 + 1
            r2 = 6
            if (r0 == r2) goto L_0x0051
            int r0 = r0 + 1
            apps.shehryar.com.cricketscroingappPro.Overs.Over r2 = r11.currentOver
            int r2 = r2.getOverExtraBalls()
            int r2 = r2 + r0
            apps.shehryar.com.cricketscroingappPro.Match.Match r3 = r11.match
            int r3 = r3.getPerOverBalls()
            if (r2 == r3) goto L_0x0051
        L_0x003f:
            apps.shehryar.com.cricketscroingappPro.Match.Match r2 = r11.match
            boolean r2 = r2.isMaxBallsFeature()
            if (r2 != 0) goto L_0x007b
            int r0 = r0 + 1
            apps.shehryar.com.cricketscroingappPro.Match.Match r2 = r11.match
            int r2 = r2.getPerOverBalls()
            if (r0 != r2) goto L_0x007b
        L_0x0051:
            android.app.AlertDialog$Builder r0 = new android.app.AlertDialog$Builder
            r0.<init>(r11)
            java.lang.String r2 = "Over"
            r0.setTitle(r2)
            java.lang.String r2 = "Press OK to proceed to the next over. Undo to bowl this delivery again."
            r0.setMessage(r2)
            r0.setCancelable(r1)
            java.lang.String r1 = "OK"
            apps.shehryar.com.cricketscroingappPro.MainActivity_Test$26 r2 = new apps.shehryar.com.cricketscroingappPro.MainActivity_Test$26
            r2.<init>(r12)
            r0.setPositiveButton(r1, r2)
            java.lang.String r12 = "Undo"
            apps.shehryar.com.cricketscroingappPro.MainActivity_Test$27 r1 = new apps.shehryar.com.cricketscroingappPro.MainActivity_Test$27
            r1.<init>()
            r0.setNegativeButton(r12, r1)
            r0.show()
            goto L_0x0088
        L_0x007b:
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 1
            apps.shehryar.com.cricketscroingappPro.ViewsUpdater r8 = r11.viewsUpdater
            r9 = 0
            r10 = 0
            r2 = r11
            r3 = r12
            r2.manageScore(r3, r4, r5, r6, r7, r8, r9, r10)
        L_0x0088:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: apps.shehryar.com.cricketscroingappPro.MainActivity_Test.manageLegByes(int):void");
    }

    private void manLegByes(int i) {
        int i2 = i;
        this.currentOver.setOverscore(i2);
        this.currentOver.maiden = false;
        LastBall lastBall = new LastBall();
        lastBall.setBowler(this.bowler);
        lastBall.setScore(i2);
        lastBall.setOver(this.currentOver);
        lastBall.setIslegbye(true);
        lastBall.setLegbyescore(i2);
        this.currentOver = this.oversDataUpdater.addOverPerBallLegByesScore(this.currentOver, i2);
        Over.balls++;
        this.teamDataUpdater.changeTeamOversAndBalls(this.bowler, this.match.getBattingTeam(), 1, Over.balls, this.match.getPerOverBalls(), this.match.isTestMatch);
        Over.ballscounter++;
        if (this.batsmanViewsUpdater.getCheckedRadioButton() == 1) {
            this.currentBatsmans[0] = this.batsmanDataUpdater.updateBatsmanBallsFaced(this.currentBatsmans[0], i2);
            lastBall.setBatsman(this.currentBatsmans[0]);
        } else {
            this.currentBatsmans[1] = this.batsmanDataUpdater.updateBatsmanBallsFaced(this.currentBatsmans[1], i2);
            lastBall.setBatsman(this.currentBatsmans[1]);
        }
        this.teamDataUpdater.updateTeamScoreBallsAndExtras(this.match.getBattingTeam(), i2, i2, 0, i2);
        this.match.allscoreslist.add(Integer.valueOf(i));
        this.lastballslist.add(lastBall);
        changeRemainingScoreText();
        if (this.code == 99 && this.match.getTeam2().getScore() > this.match.getTeam1().getScore()) {
            this.dbHelper.UpdateTeamScore(this.team_opp_id, this.matchid, this.match.getTeam2().getScore(), this.match.getTeam2().getWickets(), this.match.getTeam2().getWides(), this.match.getTeam2().getNos(), this.match.getTeam2().getOversPlayed(), this.match.getTeam2().getExtras(), this.match.getTeam2().getOverballs());
            this.dbHelper.UpdateBatsmanScore(this.team_opp_id, this.matchid, this.currentBatsmans[0]);
            this.dbHelper.UpdateBatsmanScore(this.team_opp_id, this.matchid, this.currentBatsmans[1]);
            this.dbHelper.UpdateBowlerScore(this.team_yours_id, this.matchid, this.bowler.getBowlerID(), this.bowler.getTotalscore(), this.bowler.getWickets(), this.bowler.getBowlerovers(), this.bowler.getMaidens(), this.bowler.getBalls());
            ActivityStarter.startMatchResultActivity(this, this.match);
            over_counter = 0;
            Over.balls = 0;
        } else if (Over.balls == this.match.getPerOverBalls()) {
            Over.setBalls(0);
            Over over = this.currentOver;
            Over.setBallscounter(over_counter);
            this.currentOver.setBowler(this.bowler.getName());
            over_counter++;
            if (over_counter != this.match.getOvers()) {
                this.match.getBattingTeam().overs_list.add(this.currentOver);
            }
            Over.ballscounter = 0;
            this.batsmanViewsUpdater.switchRadioCheck();
            this.tvoverdetails.setText("");
            if (over_counter != this.match.getOvers()) {
                showAfterOverDialog();
            } else if (this.code == 99) {
                updateDataInDB();
                ActivityStarter.startMatchResultActivity(this, this.match);
                over_counter = 0;
                Over.balls = 0;
            } else {
                updateDataInDB();
                this.match.getScore();
                TargetScore.overs = this.match.getOvers();
                Over.balls = 0;
                Intent intent = new Intent(this, After_Innings_Activity.class);
                intent.putExtra("team1", this.team1);
                intent.putExtra("team2", this.team2);
                intent.putExtra(DBHelper.TABLE_MATCH, this.match);
                intent.putExtra(DBHelper.TABLE_OVERS, this.totalovers);
                startActivity(intent);
            }
        }
    }

    private void changeRemainingScoreText() {
        if (this.code == 99) {
            TextView textView = this.tvremaining;
            StringBuilder sb = new StringBuilder();
            sb.append(this.match.getTeam2().getName());
            sb.append(" needs ");
            sb.append((this.match.getTeam1().getScore() - this.match.getTeam2().getScore()) + 1);
            sb.append(" from ");
            sb.append((this.match.getOvers() - over_counter) - 1);
            sb.append(".");
            sb.append(this.match.getPerOverBalls() - Over.balls);
            sb.append(" overs(");
            sb.append(UtilityFunctions.getNumberOfBallsFromOvers(this.match.getPerOverBalls(), (this.match.getOvers() - over_counter) - 1, this.match.getPerOverBalls() - Over.balls));
            sb.append(" balls)");
            textView.setText(sb.toString());
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x003d, code lost:
        if ((r11.currentOver.getOverExtraBalls() + r0) != r11.match.getPerOverBalls()) goto L_0x003f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void managebyes(final int r12) {
        /*
            r11 = this;
            android.widget.RadioButton r0 = r11.rbbatsman1
            boolean r0 = r0.isChecked()
            r1 = 0
            if (r0 != 0) goto L_0x001b
            android.widget.RadioButton r0 = r11.rbbatsman2
            boolean r0 = r0.isChecked()
            if (r0 != 0) goto L_0x001b
            java.lang.String r12 = "Please Select The batsman facing"
            android.widget.Toast r12 = android.widget.Toast.makeText(r11, r12, r1)
            r12.show()
            goto L_0x0088
        L_0x001b:
            apps.shehryar.com.cricketscroingappPro.Overs.Over r0 = r11.currentOver
            int r0 = r0.getOverBalls()
            apps.shehryar.com.cricketscroingappPro.Match.Match r2 = r11.match
            boolean r2 = r2.isMaxBallsFeature()
            if (r2 == 0) goto L_0x003f
            int r0 = r0 + 1
            r2 = 6
            if (r0 == r2) goto L_0x0051
            int r0 = r0 + 1
            apps.shehryar.com.cricketscroingappPro.Overs.Over r2 = r11.currentOver
            int r2 = r2.getOverExtraBalls()
            int r2 = r2 + r0
            apps.shehryar.com.cricketscroingappPro.Match.Match r3 = r11.match
            int r3 = r3.getPerOverBalls()
            if (r2 == r3) goto L_0x0051
        L_0x003f:
            apps.shehryar.com.cricketscroingappPro.Match.Match r2 = r11.match
            boolean r2 = r2.isMaxBallsFeature()
            if (r2 != 0) goto L_0x007b
            int r0 = r0 + 1
            apps.shehryar.com.cricketscroingappPro.Match.Match r2 = r11.match
            int r2 = r2.getPerOverBalls()
            if (r0 != r2) goto L_0x007b
        L_0x0051:
            android.app.AlertDialog$Builder r0 = new android.app.AlertDialog$Builder
            r0.<init>(r11)
            java.lang.String r2 = "Over"
            r0.setTitle(r2)
            java.lang.String r2 = "Press OK to proceed to the next over. Undo to bowl this delivery again."
            r0.setMessage(r2)
            r0.setCancelable(r1)
            java.lang.String r1 = "OK"
            apps.shehryar.com.cricketscroingappPro.MainActivity_Test$28 r2 = new apps.shehryar.com.cricketscroingappPro.MainActivity_Test$28
            r2.<init>(r12)
            r0.setPositiveButton(r1, r2)
            java.lang.String r12 = "Undo"
            apps.shehryar.com.cricketscroingappPro.MainActivity_Test$29 r1 = new apps.shehryar.com.cricketscroingappPro.MainActivity_Test$29
            r1.<init>()
            r0.setNegativeButton(r12, r1)
            r0.show()
            goto L_0x0088
        L_0x007b:
            r4 = 0
            r5 = 0
            r6 = 1
            r7 = 0
            apps.shehryar.com.cricketscroingappPro.ViewsUpdater r8 = r11.viewsUpdater
            r9 = 0
            r10 = 0
            r2 = r11
            r3 = r12
            r2.manageScore(r3, r4, r5, r6, r7, r8, r9, r10)
        L_0x0088:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: apps.shehryar.com.cricketscroingappPro.MainActivity_Test.managebyes(int):void");
    }

    private ArrayList<String> getPlayers() {
        ArrayList<String> arrayList = new ArrayList<>();
        if (!getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("edp1", "").equals("")) {
            arrayList.add(getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("edp1", ""));
        }
        if (!getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("edp2", "").equals("")) {
            arrayList.add(getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("edp2", ""));
        }
        if (!getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("edp3", "").equals("")) {
            arrayList.add(getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("edp3", ""));
        }
        if (!getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("edp4", "").equals("")) {
            arrayList.add(getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("edp4", ""));
        }
        if (!getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("edp5", "").equals("")) {
            arrayList.add(getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("edp5", ""));
        }
        if (!getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("edp6", "").equals("")) {
            arrayList.add(getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("edp6", ""));
        }
        if (!getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("edp7", "").equals("")) {
            arrayList.add(getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("edp7", ""));
        }
        if (!getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("edp8", "").equals("")) {
            arrayList.add(getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("edp8", ""));
        }
        if (!getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("edp9", "").equals("")) {
            arrayList.add(getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("edp9", ""));
        }
        if (!getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("edp10", "").equals("")) {
            arrayList.add(getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("edp10", ""));
        }
        if (!getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("edp11", "").equals("")) {
            arrayList.add(getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("edp11", ""));
        }
        if (!getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("edp12", "").equals("")) {
            arrayList.add(getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("edp12", ""));
        }
        if (!getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("edp13", "").equals("")) {
            arrayList.add(getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("edp13", ""));
        }
        if (!getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("edp14", "").equals("")) {
            arrayList.add(getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("edp14", ""));
        }
        if (!getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("edp15", "").equals("")) {
            arrayList.add(getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("edp15", ""));
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public void showByesLegByesDialog(int i) {
        if (this.rbbatsman1.isChecked() || this.rbbatsman2.isChecked()) {
            NoBallByeLegByeDialog.newInstance(i).show(getSupportFragmentManager());
        } else {
            MyToast.showToast(this, "Please select the batsman facing");
        }
    }

    public void manageExtranos(final int i, final boolean z) {
        if (!this.rbbatsman1.isChecked() && !this.rbbatsman2.isChecked()) {
            Toast.makeText(this, "Please Select The batsman facing", 0).show();
        } else if (!this.match.isMaxBallsFeature()) {
            manageScore(i, false, true, z, z, this.viewsUpdater, false, (Wicket) null);
        } else {
            int overExtraBalls = this.currentOver.getOverExtraBalls();
            if ((!this.match.isMaxBallsFeature() || !(this.currentOver.getOverBalls() == 6 || overExtraBalls + 1 + this.currentOver.getOverBalls() == this.match.getPerOverBalls())) && (this.match.isMaxBallsFeature() || this.currentOver.getOverBalls() != this.match.getPerOverBalls())) {
                manageScore(i, false, true, z, z, this.viewsUpdater, false, (Wicket) null);
                return;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Over");
            builder.setMessage("Press OK to proceed to the next over. Undo to bowl this delivery again.");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    MainActivity_Test.this.manageScore(i, false, true, z, z, MainActivity_Test.this.viewsUpdater, false, (Wicket) null);
                }
            });
            builder.setNegativeButton("Undo", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();
        }
    }

    private void changeTeamsScoreText() {
        TextView textView = this.tvscore;
        textView.setText(this.match.getBattingTeam().getScore() + "/" + this.match.getBattingTeam().getWickets());
        TextView textView2 = this.tvextras;
        textView2.setText(this.match.getBattingTeam().getExtras() + "");
    }

    public void manageExtraWides(final int i) {
        if (!this.rbbatsman1.isChecked() && !this.rbbatsman2.isChecked()) {
            Toast.makeText(this, "Please Select The batsman facing", 0).show();
        } else if (!this.match.isMaxBallsFeature()) {
            manageScore(i, true, false, false, false, this.viewsUpdater, false, (Wicket) null);
        } else {
            int overExtraBalls = this.currentOver.getOverExtraBalls();
            if ((!this.match.isMaxBallsFeature() || !(this.currentOver.getOverBalls() == 6 || overExtraBalls + 1 + this.currentOver.getOverBalls() == this.match.getPerOverBalls())) && (this.match.isMaxBallsFeature() || this.currentOver.getOverBalls() != this.match.getPerOverBalls())) {
                manageScore(i, true, false, false, false, this.viewsUpdater, false, (Wicket) null);
                return;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Over");
            builder.setMessage("Press OK to proceed to the next over. Undo to bowl this delivery again.");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    MainActivity_Test.this.manageScore(i, true, false, false, false, MainActivity_Test.this.viewsUpdater, false, (Wicket) null);
                }
            });
            builder.setNegativeButton("Undo", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x003d, code lost:
        if ((r11.currentOver.getOverExtraBalls() + r0) != r11.match.getPerOverBalls()) goto L_0x003f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void manageScore(final int r12) {
        /*
            r11 = this;
            android.widget.RadioButton r0 = r11.rbbatsman1
            boolean r0 = r0.isChecked()
            r1 = 0
            if (r0 != 0) goto L_0x001b
            android.widget.RadioButton r0 = r11.rbbatsman2
            boolean r0 = r0.isChecked()
            if (r0 != 0) goto L_0x001b
            java.lang.String r12 = "Please Select The batsman facing"
            android.widget.Toast r12 = android.widget.Toast.makeText(r11, r12, r1)
            r12.show()
            goto L_0x0088
        L_0x001b:
            apps.shehryar.com.cricketscroingappPro.Overs.Over r0 = r11.currentOver
            int r0 = r0.getOverBalls()
            apps.shehryar.com.cricketscroingappPro.Match.Match r2 = r11.match
            boolean r2 = r2.isMaxBallsFeature()
            if (r2 == 0) goto L_0x003f
            int r0 = r0 + 1
            r2 = 6
            if (r0 == r2) goto L_0x0051
            int r0 = r0 + 1
            apps.shehryar.com.cricketscroingappPro.Overs.Over r2 = r11.currentOver
            int r2 = r2.getOverExtraBalls()
            int r2 = r2 + r0
            apps.shehryar.com.cricketscroingappPro.Match.Match r3 = r11.match
            int r3 = r3.getPerOverBalls()
            if (r2 == r3) goto L_0x0051
        L_0x003f:
            apps.shehryar.com.cricketscroingappPro.Match.Match r2 = r11.match
            boolean r2 = r2.isMaxBallsFeature()
            if (r2 != 0) goto L_0x007b
            int r0 = r0 + 1
            apps.shehryar.com.cricketscroingappPro.Match.Match r2 = r11.match
            int r2 = r2.getPerOverBalls()
            if (r0 != r2) goto L_0x007b
        L_0x0051:
            android.app.AlertDialog$Builder r0 = new android.app.AlertDialog$Builder
            r0.<init>(r11)
            java.lang.String r2 = "Over"
            r0.setTitle(r2)
            java.lang.String r2 = "Press OK to proceed to the next over. Undo to bowl this delivery again."
            r0.setMessage(r2)
            r0.setCancelable(r1)
            java.lang.String r1 = "OK"
            apps.shehryar.com.cricketscroingappPro.MainActivity_Test$34 r2 = new apps.shehryar.com.cricketscroingappPro.MainActivity_Test$34
            r2.<init>(r12)
            r0.setPositiveButton(r1, r2)
            java.lang.String r12 = "Undo"
            apps.shehryar.com.cricketscroingappPro.MainActivity_Test$35 r1 = new apps.shehryar.com.cricketscroingappPro.MainActivity_Test$35
            r1.<init>()
            r0.setNegativeButton(r12, r1)
            r0.show()
            goto L_0x0088
        L_0x007b:
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            apps.shehryar.com.cricketscroingappPro.ViewsUpdater r8 = r11.viewsUpdater
            r9 = 0
            r10 = 0
            r2 = r11
            r3 = r12
            r2.manageScore(r3, r4, r5, r6, r7, r8, r9, r10)
        L_0x0088:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: apps.shehryar.com.cricketscroingappPro.MainActivity_Test.manageScore(int):void");
    }

    public void showAfterOverDialog() {
        try {
            if (!this.lastballslist.get(this.lastballslist.size() - 1).isLastBallOfOver()) {
                this.bowlerDataUpdater.changeBowlerMaiderOvers(this.bowler, this.currentOver);
            }
            this.lastballslist.get(this.lastballslist.size() - 1).setLastBallOfOver(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AfterOverDialogFragment afterOverDialogFragment = new AfterOverDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(DBHelper.TABLE_MATCH, this.match);
        afterOverDialogFragment.setArguments(bundle);
        afterOverDialogFragment.show(getSupportFragmentManager(), "after over dialog");
    }

    public void onNextOverTapped(String str) {
        updateDataInDB();
        this.bowler = this.bowlerDataUpdater.getBowlerFromPreviousBowlers(this.match.getBowlingTeam(), str);
        if (!(this.bowler != null)) {
            this.bowler = new Bowler();
            this.bowler.setName(str);
            this.bowler.setBalls(0);
            this.bowler.setBowlerID(this.dbHelper.insertPlayer(this.bowler.getName(), this.match.getBowlingTeam().getTeamID(), "bowler"));
            this.dbHelper.insertBowlerScore(this.match.getBowlingTeam().getTeamID(), this.match.getMatchID(), this.bowler.getBowlerID(), 0, 0, 0, 0, 0);
            this.match.getBowlingTeam().bowlers_list.add(this.bowler);
        }
        boolean z = this.currentOver.maiden;
        this.currentOver = new Over();
        this.currentOver.setBowler(this.bowler.getName());
        this.match.getBattingTeam().overs_list.add(this.currentOver);
        this.currentOver.maiden = z;
        updateViews();
        this.viewsUpdater.switchBatsmen();
        if (this.match.getBattingTeam().overs_list.size() % 3 == 0 && this.interstitialAd.isLoaded() && !SharedPrefsHelper.isPro(getApplicationContext())) {
            this.interstitialAd.show();
        }
    }

    /* access modifiers changed from: private */
    public void updateViews() {
        this.viewsUpdater.updateViews(this.match, this.currentBatsmans, this.bowler, this.currentOver);
    }

    public void onViewOversTapped() {
        Intent intent = new Intent(this, OverHistoryActivity.class);
        intent.putExtra(DBHelper.TABLE_TEAM, this.match.getBattingTeam());
        startActivityForResult(intent, 100);
    }

    public void onUndoPreviousBallTapped() {
        this.currentOver = new Over();
        this.match.getBattingTeam().overs_list.add(this.currentOver);
        manageUndo();
    }

    public void onBackPressed() {
        final AlertDialog create = new AlertDialog.Builder(this).setTitle(getResources().getString(R.string.exit)).setMessage(getResources().getString(R.string.exit_message)).setPositiveButton("Yes", (DialogInterface.OnClickListener) null).setNegativeButton("No", (DialogInterface.OnClickListener) null).create();
        create.setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                Button button = create.getButton(-1);
                Button button2 = create.getButton(-2);
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        MainActivity_Test.this.updateDataInDB();
                        MainActivity_Test.this.updateDataInDB();
                        MainActivity_Test.this.saveDatainSharedPrefs();
                        MainActivity_Test.this.finishAffinity();
                        System.exit(0);
                    }
                });
                button2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        create.dismiss();
                    }
                });
            }
        });
        create.show();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        updateDataInDB();
        saveDatainSharedPrefs();
    }

    /* access modifiers changed from: private */
    public void saveDatainSharedPrefs() {
        ResumeMatch resumeMatch = new ResumeMatch();
        resumeMatch.setInnings(this.match.getInnings());
        resumeMatch.setBowlerName(this.bowler.getName());
        if (this.rbbatsman1.isChecked()) {
            resumeMatch.setBatFacing(0);
        } else {
            resumeMatch.setBatFacing(1);
        }
        resumeMatch.setOverScore(this.tvoverdetails.getText().toString());
        SharedPrefsHelper.insertResumeMatchInSharedPrefs(this, resumeMatch, this.match);
        this.dbHelper.insertOvers(this.match.getMatchID(), this.match.getBattingTeam().getTeamID(), this.match.getBattingTeam().getOvers_list());
    }

    public void displayPerBallScore() {
        this.tvoverdetails.setText("");
        for (int i = 0; i < this.currentOver.perballScore.size(); i++) {
            int intValue = this.currentOver.perballScore.get(i).intValue();
            if (intValue == 11) {
                this.tvoverdetails.append("0nb ");
            } else if (intValue == 12) {
                this.tvoverdetails.append("1nb ");
            } else if (intValue == 13) {
                this.tvoverdetails.append("2nb ");
            } else if (intValue == 14) {
                this.tvoverdetails.append("3nb ");
            } else if (intValue == 15) {
                this.tvoverdetails.append("4nb ");
            } else if (intValue == 16) {
                this.tvoverdetails.append("5nb ");
            } else if (intValue == 17) {
                this.tvoverdetails.append("6nb ");
            } else if (intValue == 18) {
                this.tvoverdetails.append("7nb ");
            } else if (intValue == 20) {
                this.tvoverdetails.append("W ");
            } else if (intValue == 21) {
                this.tvoverdetails.append("0wd ");
            } else if (intValue == 22) {
                this.tvoverdetails.append("1wd ");
            } else if (intValue == 23) {
                this.tvoverdetails.append("2wd ");
            } else if (intValue == 24) {
                this.tvoverdetails.append("3wd ");
            } else if (intValue == 25) {
                this.tvoverdetails.append("4wd ");
            } else if (intValue == 26) {
                this.tvoverdetails.append("5wd ");
            } else if (intValue == 27) {
                this.tvoverdetails.append("6wd ");
            } else if (intValue == 28) {
                this.tvoverdetails.append("7wd ");
            } else if (intValue == 20) {
                this.tvoverdetails.append("W ");
            } else if (intValue == 41) {
                this.tvoverdetails.append("1b ");
            } else if (intValue == 42) {
                this.tvoverdetails.append("2b ");
            } else if (intValue == 43) {
                this.tvoverdetails.append("3b ");
            } else if (intValue == 44) {
                this.tvoverdetails.append("4b ");
            } else if (intValue == 45) {
                this.tvoverdetails.append("5b ");
            } else if (intValue == 46) {
                this.tvoverdetails.append("6b ");
            } else if (intValue == 47) {
                this.tvoverdetails.append("7b ");
            } else if (intValue == 51) {
                this.tvoverdetails.append("1lb ");
            } else if (intValue == 52) {
                this.tvoverdetails.append("2lb ");
            } else if (intValue == 53) {
                this.tvoverdetails.append("3lb ");
            } else if (intValue == 54) {
                this.tvoverdetails.append("4lb ");
            } else if (intValue == 55) {
                this.tvoverdetails.append("5lb ");
            } else if (intValue == 56) {
                this.tvoverdetails.append("6lb ");
            } else if (intValue == 57) {
                this.tvoverdetails.append("7lb ");
            } else if (intValue == 61) {
                this.tvoverdetails.append("W+wd ");
            } else if (intValue == 62) {
                this.tvoverdetails.append("W+nb ");
            } else {
                TextView textView = this.tvoverdetails;
                textView.append(intValue + " ");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void checkAndUndoOverLastBall(LastBall lastBall) {
        if (lastBall.isLastBallOfOver()) {
            undoOverLastBall(lastBall);
        }
    }

    /* access modifiers changed from: package-private */
    public void undoOverLastBall(LastBall lastBall) {
        this.bowler = lastBall.getBowler();
        Over.setBalls(this.match.getPerOverBalls());
        this.match.getBattingTeam().getOvers_list().remove(this.match.getBattingTeam().getOvers_list().size() - 1);
        try {
            this.currentOver = this.match.getBattingTeam().getOvers_list().get(this.match.getBattingTeam().getOvers_list().size() - 1);
            if (this.currentOver.maiden) {
                Log.i("edBowler maidens", this.bowler.getMaidens() + "");
                this.bowler.setMaidens(-1);
                this.currentOver.maiden = false;
            }
            this.currentOver.setOverBalls(OverHelper.getValidBallsInAnOver(this.currentOver));
            this.match.getBattingTeam().setOverballs(this.currentOver.getOverBalls() + this.currentOver.getOverExtraBalls());
            updateDataInDB();
            this.match.getBattingTeam().setOversPlayed(-1);
            over_counter--;
            Over.ballscounter = this.match.getPerOverBalls() - 1;
            updateViews();
        } catch (Exception e) {
            e.printStackTrace();
            MyToast.showToast(this, "Some thing went wrong while undoing.");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:56:0x0190  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x01bc  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x01f7  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x01ff  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0216  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0255  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void manageUndo() {
        /*
            r13 = this;
            apps.shehryar.com.cricketscroingappPro.LastBall r0 = new apps.shehryar.com.cricketscroingappPro.LastBall
            r0.<init>()
            java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.LastBall> r1 = r13.lastballslist
            boolean r1 = r1.isEmpty()
            r2 = 1
            if (r1 != 0) goto L_0x001d
            java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.LastBall> r0 = r13.lastballslist
            java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.LastBall> r1 = r13.lastballslist
            int r1 = r1.size()
            int r1 = r1 - r2
            java.lang.Object r0 = r0.get(r1)
            apps.shehryar.com.cricketscroingappPro.LastBall r0 = (apps.shehryar.com.cricketscroingappPro.LastBall) r0
        L_0x001d:
            java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.LastBall> r1 = r13.lastballslist
            boolean r1 = r1.isEmpty()
            r3 = 0
            if (r1 != r2) goto L_0x0035
            android.content.Context r0 = r13.getApplicationContext()
            java.lang.String r1 = "No ball bowled yet"
            android.widget.Toast r0 = android.widget.Toast.makeText(r0, r1, r3)
            r0.show()
            goto L_0x0791
        L_0x0035:
            boolean r1 = r0.iswicket()
            r4 = 2
            r5 = -1
            if (r1 != r2) goto L_0x033f
            r13.checkAndUndoOverLastBall(r0)
            apps.shehryar.com.cricketscroingappPro.FallOfWickets r0 = r0.getFallOfWickets()
            java.lang.String r1 = "lastball list size:"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.LastBall> r7 = r13.lastballslist
            int r7 = r7.size()
            r6.append(r7)
            java.lang.String r7 = ""
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            android.util.Log.e(r1, r6)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r1 = r0.getBatsman()
            boolean r6 = r0.isRunOut
            if (r6 != 0) goto L_0x0087
            boolean r6 = r0.isWide
            if (r6 != 0) goto L_0x0087
            boolean r6 = r0.isNo
            if (r6 != 0) goto L_0x0087
            int r6 = apps.shehryar.com.cricketscroingappPro.Overs.Over.balls
            int r6 = r6 - r2
            apps.shehryar.com.cricketscroingappPro.Overs.Over.balls = r6
            apps.shehryar.com.cricketscroingappPro.Overs.Over r6 = r13.currentOver
            r6.decrementOverBalls()
            apps.shehryar.com.cricketscroingappPro.Bowler.BowlerDataUpdater r6 = r13.bowlerDataUpdater
            apps.shehryar.com.cricketscroingappPro.Bowler.Bowler r7 = r13.bowler
            apps.shehryar.com.cricketscroingappPro.Match.Match r8 = r13.match
            int r8 = r8.getPerOverBalls()
            r6.decrementBowlerBalls(r7, r8)
        L_0x0087:
            boolean r6 = r0.isRunOut
            if (r6 == 0) goto L_0x00d0
            boolean r6 = r0.isWide
            if (r6 != 0) goto L_0x00ac
            boolean r6 = r0.isNo
            if (r6 != 0) goto L_0x00ac
            int r6 = apps.shehryar.com.cricketscroingappPro.Overs.Over.balls
            int r6 = r6 - r2
            apps.shehryar.com.cricketscroingappPro.Overs.Over.balls = r6
            apps.shehryar.com.cricketscroingappPro.Overs.Over r6 = r13.currentOver
            r6.decrementOverBalls()
            apps.shehryar.com.cricketscroingappPro.Bowler.BowlerDataUpdater r6 = r13.bowlerDataUpdater
            apps.shehryar.com.cricketscroingappPro.Bowler.Bowler r7 = r13.bowler
            apps.shehryar.com.cricketscroingappPro.Match.Match r8 = r13.match
            int r8 = r8.getPerOverBalls()
            r6.decrementBowlerBalls(r7, r8)
            goto L_0x0133
        L_0x00ac:
            apps.shehryar.com.cricketscroingappPro.Match.Match r6 = r13.match
            boolean r6 = r6.isMaxBallsFeature()
            if (r6 == 0) goto L_0x00ce
            apps.shehryar.com.cricketscroingappPro.Match.Match r6 = r13.match
            boolean r6 = r6.isMaxBallsFeature()
            if (r6 == 0) goto L_0x00c9
            apps.shehryar.com.cricketscroingappPro.Bowler.BowlerDataUpdater r6 = r13.bowlerDataUpdater
            apps.shehryar.com.cricketscroingappPro.Bowler.Bowler r7 = r13.bowler
            apps.shehryar.com.cricketscroingappPro.Match.Match r8 = r13.match
            int r8 = r8.getPerOverBalls()
            r6.decrementBowlerBalls(r7, r8)
        L_0x00c9:
            apps.shehryar.com.cricketscroingappPro.Overs.Over r6 = r13.currentOver
            r6.decrementOverExtraBalls()
        L_0x00ce:
            r6 = r2
            goto L_0x0134
        L_0x00d0:
            apps.shehryar.com.cricketscroingappPro.Bowler.Bowler r6 = r13.bowler
            r6.setWickets(r5)
            boolean r6 = r0.isWide
            if (r6 != 0) goto L_0x00e0
            boolean r6 = r0.isNo
            if (r6 != 0) goto L_0x00e0
            r1.setBallsfaced(r5)
        L_0x00e0:
            boolean r6 = r0.isWide
            if (r6 != 0) goto L_0x00f7
            boolean r6 = r0.isNo
            if (r6 == 0) goto L_0x00e9
            goto L_0x00f7
        L_0x00e9:
            apps.shehryar.com.cricketscroingappPro.Match.Match r6 = r13.match
            boolean r6 = r6.isMaxBallsFeature()
            if (r6 == 0) goto L_0x0133
            apps.shehryar.com.cricketscroingappPro.Overs.Over r6 = r13.currentOver
            r6.decrementOverBalls()
            goto L_0x0133
        L_0x00f7:
            apps.shehryar.com.cricketscroingappPro.Match.Match r6 = r13.match
            apps.shehryar.com.cricketscroingappPro.Team.Team r6 = r6.getBattingTeam()
            r6.setScore(r5)
            r13.changeTeamsScoreText()
            apps.shehryar.com.cricketscroingappPro.Match.Match r6 = r13.match
            apps.shehryar.com.cricketscroingappPro.Team.Team r6 = r6.getBattingTeam()
            r6.setExtras(r5)
            apps.shehryar.com.cricketscroingappPro.Match.Match r6 = r13.match
            boolean r6 = r6.isMaxBallsFeature()
            if (r6 == 0) goto L_0x012e
            apps.shehryar.com.cricketscroingappPro.Match.Match r6 = r13.match
            boolean r6 = r6.isMaxBallsFeature()
            if (r6 == 0) goto L_0x0129
            apps.shehryar.com.cricketscroingappPro.Bowler.BowlerDataUpdater r6 = r13.bowlerDataUpdater
            apps.shehryar.com.cricketscroingappPro.Bowler.Bowler r7 = r13.bowler
            apps.shehryar.com.cricketscroingappPro.Match.Match r8 = r13.match
            int r8 = r8.getPerOverBalls()
            r6.decrementBowlerBalls(r7, r8)
        L_0x0129:
            apps.shehryar.com.cricketscroingappPro.Overs.Over r6 = r13.currentOver
            r6.decrementOverExtraBalls()
        L_0x012e:
            apps.shehryar.com.cricketscroingappPro.Bowler.Bowler r6 = r13.bowler
            r6.setTotalscore(r5)
        L_0x0133:
            r6 = r3
        L_0x0134:
            apps.shehryar.com.cricketscroingappPro.Match.Match r7 = r13.match
            apps.shehryar.com.cricketscroingappPro.Team.Team r7 = r7.getBattingTeam()
            java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.FallOfWickets> r7 = r7.fallOfWicketses
            apps.shehryar.com.cricketscroingappPro.Match.Match r8 = r13.match
            apps.shehryar.com.cricketscroingappPro.Team.Team r8 = r8.getBattingTeam()
            java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.FallOfWickets> r8 = r8.fallOfWicketses
            int r8 = r8.size()
            int r8 = r8 - r2
            r7.remove(r8)
            apps.shehryar.com.cricketscroingappPro.Bowler.Bowler r7 = r0.getBowler()
            apps.shehryar.com.cricketscroingappPro.Match.Match r8 = r13.match
            apps.shehryar.com.cricketscroingappPro.Team.Team r8 = r8.getBattingTeam()
            r8.setWickets(r5)
            java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.LastBall> r8 = r13.lastballslist
            java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.LastBall> r9 = r13.lastballslist
            int r9 = r9.size()
            int r9 = r9 - r2
            r8.remove(r9)
            apps.shehryar.com.cricketscroingappPro.Overs.Over r8 = r13.currentOver     // Catch:{ Exception -> 0x0176 }
            java.util.ArrayList<java.lang.Integer> r8 = r8.perballScore     // Catch:{ Exception -> 0x0176 }
            apps.shehryar.com.cricketscroingappPro.Overs.Over r9 = r13.currentOver     // Catch:{ Exception -> 0x0176 }
            java.util.ArrayList<java.lang.Integer> r9 = r9.perballScore     // Catch:{ Exception -> 0x0176 }
            int r9 = r9.size()     // Catch:{ Exception -> 0x0176 }
            int r9 = r9 - r2
            r8.remove(r9)     // Catch:{ Exception -> 0x0176 }
            goto L_0x017a
        L_0x0176:
            r8 = move-exception
            r8.printStackTrace()
        L_0x017a:
            apps.shehryar.com.cricketscroingappPro.Match.Match r8 = r13.match
            apps.shehryar.com.cricketscroingappPro.Team.Team r8 = r8.getBattingTeam()
            int r9 = apps.shehryar.com.cricketscroingappPro.Overs.Over.balls
            r8.setOverballs(r9)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r8 = new apps.shehryar.com.cricketscroingappPro.Batsman.Batsman
            r8.<init>()
            int r9 = r0.getBatOutCode()
            if (r9 != r2) goto L_0x01bc
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman[] r8 = r13.currentBatsmans
            r8 = r8[r3]
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman[] r9 = r13.currentBatsmans
            r9[r3] = r1
            android.widget.RadioButton r1 = r13.rbbatsman1
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman[] r9 = r13.currentBatsmans
            r9 = r9[r3]
            java.lang.String r9 = r9.getName()
            r1.setText(r9)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman[] r1 = r13.currentBatsmans
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman[] r9 = r13.currentBatsmans
            r9 = r9[r3]
            apps.shehryar.com.cricketscroingappPro.DBHelper r10 = r13.dbHelper
            apps.shehryar.com.cricketscroingappPro.Match.Match r11 = r13.match
            apps.shehryar.com.cricketscroingappPro.Team.Team r11 = r11.getBattingTeam()
            apps.shehryar.com.cricketscroingappPro.Match.Match r12 = r13.match
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r9 = apps.shehryar.com.cricketscroingappPro.Batsman.BatsmanUtilityFunctionsClass.setBatsmanOutStatusAsNotOut(r9, r10, r11, r12)
            r1[r3] = r9
            goto L_0x01ed
        L_0x01bc:
            int r9 = r0.getBatOutCode()
            if (r9 != r4) goto L_0x01ed
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman[] r8 = r13.currentBatsmans
            r8 = r8[r2]
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman[] r9 = r13.currentBatsmans
            r9[r2] = r1
            android.widget.RadioButton r1 = r13.rbbatsman2
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman[] r9 = r13.currentBatsmans
            r9 = r9[r2]
            java.lang.String r9 = r9.getName()
            r1.setText(r9)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman[] r1 = r13.currentBatsmans
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman[] r9 = r13.currentBatsmans
            r9 = r9[r2]
            apps.shehryar.com.cricketscroingappPro.DBHelper r10 = r13.dbHelper
            apps.shehryar.com.cricketscroingappPro.Match.Match r11 = r13.match
            apps.shehryar.com.cricketscroingappPro.Team.Team r11 = r11.getBattingTeam()
            apps.shehryar.com.cricketscroingappPro.Match.Match r12 = r13.match
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r9 = apps.shehryar.com.cricketscroingappPro.Batsman.BatsmanUtilityFunctionsClass.setBatsmanOutStatusAsNotOut(r9, r10, r11, r12)
            r1[r2] = r9
        L_0x01ed:
            boolean r1 = r0.isRunOut
            if (r1 == 0) goto L_0x020a
            if (r6 != 0) goto L_0x020a
            int r1 = r0.batFacingCode
            if (r1 != r2) goto L_0x01ff
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman[] r1 = r13.currentBatsmans
            r1 = r1[r3]
            r1.setBallsfaced(r5)
            goto L_0x020a
        L_0x01ff:
            int r1 = r0.batFacingCode
            if (r1 != r4) goto L_0x020a
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman[] r1 = r13.currentBatsmans
            r1 = r1[r2]
            r1.setBallsfaced(r5)
        L_0x020a:
            boolean r1 = r0.isRunOut
            if (r1 == 0) goto L_0x0255
            boolean r1 = r0.isWide
            if (r1 != 0) goto L_0x0255
            boolean r1 = r0.isNo
            if (r1 != 0) goto L_0x0255
            boolean r1 = r0.isBye
            if (r1 != 0) goto L_0x0247
            int r1 = r0.getBatFacingCode()
            if (r1 != r2) goto L_0x022e
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman[] r1 = r13.currentBatsmans
            r1 = r1[r3]
            int r4 = r0.runsScored
            int r4 = -r4
            r1.setTotalScore(r4)
            r13.changeBatsman1ScoreText()
            goto L_0x0241
        L_0x022e:
            int r1 = r0.getBatFacingCode()
            if (r1 != r4) goto L_0x0241
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman[] r1 = r13.currentBatsmans
            r1 = r1[r2]
            int r4 = r0.runsScored
            int r4 = -r4
            r1.setTotalScore(r4)
            r13.changeBatsman2ScoreText()
        L_0x0241:
            int r1 = r0.runsScored
            int r1 = -r1
            r7.setTotalscore(r1)
        L_0x0247:
            apps.shehryar.com.cricketscroingappPro.Match.Match r1 = r13.match
            apps.shehryar.com.cricketscroingappPro.Team.Team r1 = r1.getBattingTeam()
            int r4 = r0.runsScored
            int r4 = -r4
            r1.setScore(r4)
            goto L_0x030d
        L_0x0255:
            boolean r1 = r0.isRunOut
            if (r1 == 0) goto L_0x030d
            boolean r1 = r0.isWide
            if (r1 != 0) goto L_0x0261
            boolean r1 = r0.isNo
            if (r1 == 0) goto L_0x030d
        L_0x0261:
            apps.shehryar.com.cricketscroingappPro.Match.Match r1 = r13.match
            apps.shehryar.com.cricketscroingappPro.Team.Team r1 = r1.getBattingTeam()
            int r9 = r0.runsScored
            int r9 = r9 + r2
            int r9 = -r9
            r1.setScore(r9)
            boolean r1 = r0.isBye
            if (r1 == 0) goto L_0x0276
            r7.setTotalscore(r5)
            goto L_0x027d
        L_0x0276:
            int r1 = r0.runsScored
            int r1 = r1 + r2
            int r1 = -r1
            r7.setTotalscore(r1)
        L_0x027d:
            apps.shehryar.com.cricketscroingappPro.Match.Match r1 = r13.match
            apps.shehryar.com.cricketscroingappPro.Team.Team r1 = r1.getBattingTeam()
            r1.setExtras(r5)
            boolean r1 = r0.isNo
            if (r1 == 0) goto L_0x02e1
            apps.shehryar.com.cricketscroingappPro.Match.Match r1 = r13.match
            apps.shehryar.com.cricketscroingappPro.Team.Team r1 = r1.getBattingTeam()
            r1.setNos(r5)
            boolean r1 = r0.isBye
            if (r1 != 0) goto L_0x02ee
            int r1 = r0.getBatFacingCode()
            if (r1 != r2) goto L_0x02bc
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman[] r1 = r13.currentBatsmans
            r1 = r1[r3]
            int r7 = r0.runsScored
            int r7 = -r7
            r1.setTotalScore(r7)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman[] r1 = r13.currentBatsmans
            r1 = r1[r3]
            int r1 = r1.getTotalScore()
            if (r1 >= 0) goto L_0x02b8
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman[] r1 = r13.currentBatsmans
            r1 = r1[r3]
            r1.setTotalScore(r3)
        L_0x02b8:
            r13.changeBatsman1ScoreText()
            goto L_0x02ee
        L_0x02bc:
            int r1 = r0.getBatFacingCode()
            if (r1 != r4) goto L_0x02ee
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman[] r1 = r13.currentBatsmans
            r1 = r1[r2]
            int r7 = r0.runsScored
            int r7 = -r7
            r1.setTotalScore(r7)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman[] r1 = r13.currentBatsmans
            r1 = r1[r2]
            int r1 = r1.getTotalScore()
            if (r1 >= 0) goto L_0x02dd
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman[] r1 = r13.currentBatsmans
            r1 = r1[r2]
            r1.setTotalScore(r3)
        L_0x02dd:
            r13.changeBatsman2ScoreText()
            goto L_0x02ee
        L_0x02e1:
            boolean r1 = r0.isWide
            if (r1 == 0) goto L_0x02ee
            apps.shehryar.com.cricketscroingappPro.Match.Match r1 = r13.match
            apps.shehryar.com.cricketscroingappPro.Team.Team r1 = r1.getBattingTeam()
            r1.setWides(r5)
        L_0x02ee:
            int r1 = r0.getBatFacingCode()
            if (r1 != r2) goto L_0x02fe
            if (r6 != 0) goto L_0x02fe
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman[] r1 = r13.currentBatsmans
            r1 = r1[r3]
            r1.setBallsfaced(r2)
            goto L_0x030d
        L_0x02fe:
            int r1 = r0.getBatFacingCode()
            if (r1 != r4) goto L_0x030d
            if (r6 != 0) goto L_0x030d
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman[] r1 = r13.currentBatsmans
            r1 = r1[r2]
            r1.setBallsfaced(r2)
        L_0x030d:
            android.widget.RadioButton r1 = r13.rbbatsman1
            r1.setChecked(r3)
            android.widget.RadioButton r1 = r13.rbbatsman2
            r1.setChecked(r3)
            r13.removeFallofWicketFromDB(r0)
            r13.removeNewBatsmanFromDB(r8)
            apps.shehryar.com.cricketscroingappPro.Match.Match r0 = r13.match
            apps.shehryar.com.cricketscroingappPro.Team.Team r0 = r0.getBattingTeam()
            java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.Batsman.Batsman> r0 = r0.batsmans_list
            apps.shehryar.com.cricketscroingappPro.Match.Match r1 = r13.match
            apps.shehryar.com.cricketscroingappPro.Team.Team r1 = r1.getBattingTeam()
            java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.Batsman.Batsman> r1 = r1.batsmans_list
            int r1 = r1.size()
            int r1 = r1 - r2
            r0.remove(r1)
            apps.shehryar.com.cricketscroingappPro.Overs.Over r0 = r13.currentOver
            r0.setWickets(r5)
            r13.updateViews()
            goto L_0x0791
        L_0x033f:
            boolean r1 = r0.islegbye()
            r6 = 6
            if (r1 != r2) goto L_0x043a
            boolean r1 = r0.isno()
            if (r1 != 0) goto L_0x043a
            r13.checkAndUndoOverLastBall(r0)
            int r1 = apps.shehryar.com.cricketscroingappPro.Overs.Over.balls
            int r1 = r1 - r2
            apps.shehryar.com.cricketscroingappPro.Overs.Over.balls = r1
            apps.shehryar.com.cricketscroingappPro.Overs.Over r1 = r13.currentOver
            r1.decrementOverBalls()
            apps.shehryar.com.cricketscroingappPro.Match.Match r1 = r13.match
            boolean r1 = r1.isMaxBallsFeature()
            if (r1 == 0) goto L_0x0369
            apps.shehryar.com.cricketscroingappPro.Bowler.BowlerDataUpdater r1 = r13.bowlerDataUpdater
            apps.shehryar.com.cricketscroingappPro.Bowler.Bowler r3 = r13.bowler
            r1.decrementBowlerBalls(r3, r6)
            goto L_0x0376
        L_0x0369:
            apps.shehryar.com.cricketscroingappPro.Bowler.BowlerDataUpdater r1 = r13.bowlerDataUpdater
            apps.shehryar.com.cricketscroingappPro.Bowler.Bowler r3 = r13.bowler
            apps.shehryar.com.cricketscroingappPro.Match.Match r6 = r13.match
            int r6 = r6.getPerOverBalls()
            r1.decrementBowlerBalls(r3, r6)
        L_0x0376:
            apps.shehryar.com.cricketscroingappPro.Match.Match r1 = r13.match
            apps.shehryar.com.cricketscroingappPro.Team.Team r1 = r1.getBattingTeam()
            int r3 = apps.shehryar.com.cricketscroingappPro.Overs.Over.balls
            r1.setOverballs(r3)
            r0.getBowler()
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r1 = r0.getBatsman()
            r1.setBallsfaced(r5)
            java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.LastBall> r1 = r13.lastballslist
            java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.LastBall> r3 = r13.lastballslist
            int r3 = r3.size()
            int r3 = r3 - r2
            r1.remove(r3)
            apps.shehryar.com.cricketscroingappPro.Overs.Over r1 = r13.currentOver     // Catch:{ Exception -> 0x03a8 }
            java.util.ArrayList<java.lang.Integer> r1 = r1.perballScore     // Catch:{ Exception -> 0x03a8 }
            apps.shehryar.com.cricketscroingappPro.Overs.Over r3 = r13.currentOver     // Catch:{ Exception -> 0x03a8 }
            java.util.ArrayList<java.lang.Integer> r3 = r3.perballScore     // Catch:{ Exception -> 0x03a8 }
            int r3 = r3.size()     // Catch:{ Exception -> 0x03a8 }
            int r3 = r3 - r2
            r1.remove(r3)     // Catch:{ Exception -> 0x03a8 }
            goto L_0x03ac
        L_0x03a8:
            r1 = move-exception
            r1.printStackTrace()
        L_0x03ac:
            android.widget.TextView r1 = r13.tvovers
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r6 = "("
            r3.append(r6)
            apps.shehryar.com.cricketscroingappPro.Match.Match r6 = r13.match
            apps.shehryar.com.cricketscroingappPro.Team.Team r6 = r6.getBattingTeam()
            int r6 = r6.getOversPlayed()
            r3.append(r6)
            java.lang.String r6 = "."
            r3.append(r6)
            int r6 = apps.shehryar.com.cricketscroingappPro.Overs.Over.balls
            r3.append(r6)
            java.lang.String r6 = "/"
            r3.append(r6)
            apps.shehryar.com.cricketscroingappPro.Match.Match r6 = r13.match
            int r6 = r6.getOvers()
            r3.append(r6)
            java.lang.String r6 = ")"
            r3.append(r6)
            java.lang.String r3 = r3.toString()
            r1.setText(r3)
            apps.shehryar.com.cricketscroingappPro.Overs.Over r1 = r13.currentOver
            int r3 = r0.getByescore()
            int r3 = -r3
            r1.setOverscore(r3)
            apps.shehryar.com.cricketscroingappPro.Match.Match r1 = r13.match
            apps.shehryar.com.cricketscroingappPro.Team.Team r1 = r1.getBattingTeam()
            r1.setLegbyes(r5)
            apps.shehryar.com.cricketscroingappPro.Match.Match r1 = r13.match
            apps.shehryar.com.cricketscroingappPro.Team.Team r1 = r1.getBattingTeam()
            int r3 = r0.getLegbyescore()
            int r3 = -r3
            r1.setScore(r3)
            apps.shehryar.com.cricketscroingappPro.Match.Match r1 = r13.match
            apps.shehryar.com.cricketscroingappPro.Team.Team r1 = r1.getBattingTeam()
            int r3 = r0.getLegbyescore()
            int r3 = -r3
            r1.setExtras(r3)
            r13.updateViews()
            int r0 = r0.getScore()
            int r0 = r0 % r4
            if (r0 == 0) goto L_0x0435
            android.widget.RadioButton r0 = r13.rbbatsman1
            boolean r0 = r0.isChecked()
            if (r0 == 0) goto L_0x0430
            android.widget.RadioButton r0 = r13.rbbatsman2
            r0.setChecked(r2)
            goto L_0x0435
        L_0x0430:
            android.widget.RadioButton r0 = r13.rbbatsman1
            r0.setChecked(r2)
        L_0x0435:
            r13.changeRemainingScoreText()
            goto L_0x0791
        L_0x043a:
            boolean r1 = r0.isno()
            if (r1 != r2) goto L_0x052f
            r13.checkAndUndoOverLastBall(r0)
            apps.shehryar.com.cricketscroingappPro.Match.Match r1 = r13.match
            boolean r1 = r1.isMaxBallsFeature()
            if (r1 == 0) goto L_0x045d
            apps.shehryar.com.cricketscroingappPro.Bowler.BowlerDataUpdater r1 = r13.bowlerDataUpdater
            apps.shehryar.com.cricketscroingappPro.Bowler.Bowler r3 = r13.bowler
            apps.shehryar.com.cricketscroingappPro.Match.Match r6 = r13.match
            int r6 = r6.getPerOverBalls()
            r1.decrementBowlerBalls(r3, r6)
            apps.shehryar.com.cricketscroingappPro.Overs.Over r1 = r13.currentOver
            r1.decrementOverExtraBalls()
        L_0x045d:
            apps.shehryar.com.cricketscroingappPro.Bowler.Bowler r1 = r13.bowler
            apps.shehryar.com.cricketscroingappPro.Match.Match r3 = r13.match
            int r3 = r3.getNoExtras()
            int r3 = -r3
            r1.setNoballs(r3)
            boolean r1 = r0.isbye()
            if (r1 != 0) goto L_0x0480
            boolean r1 = r0.islegbye()
            if (r1 != 0) goto L_0x0480
            apps.shehryar.com.cricketscroingappPro.Bowler.Bowler r1 = r13.bowler
            int r3 = r0.getNoscore()
            int r3 = -r3
            r1.setTotalscore(r3)
            goto L_0x0485
        L_0x0480:
            apps.shehryar.com.cricketscroingappPro.Bowler.Bowler r1 = r13.bowler
            r1.setTotalscore(r5)
        L_0x0485:
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r1 = r0.getBatsman()
            r13.assignBatsmanScores(r0)
            int r3 = r0.getNoscore()     // Catch:{ Exception -> 0x0495 }
            int r3 = r3 - r2
            r13.decreaseBatsmanBoundaries(r1, r3, r0)     // Catch:{ Exception -> 0x0495 }
            goto L_0x0499
        L_0x0495:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0499:
            java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.LastBall> r1 = r13.lastballslist
            java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.LastBall> r3 = r13.lastballslist
            int r3 = r3.size()
            int r3 = r3 - r2
            r1.remove(r3)
            apps.shehryar.com.cricketscroingappPro.Overs.Over r1 = r13.currentOver     // Catch:{ Exception -> 0x04b6 }
            java.util.ArrayList<java.lang.Integer> r1 = r1.perballScore     // Catch:{ Exception -> 0x04b6 }
            apps.shehryar.com.cricketscroingappPro.Overs.Over r3 = r13.currentOver     // Catch:{ Exception -> 0x04b6 }
            java.util.ArrayList<java.lang.Integer> r3 = r3.perballScore     // Catch:{ Exception -> 0x04b6 }
            int r3 = r3.size()     // Catch:{ Exception -> 0x04b6 }
            int r3 = r3 - r2
            r1.remove(r3)     // Catch:{ Exception -> 0x04b6 }
            goto L_0x04ba
        L_0x04b6:
            r1 = move-exception
            r1.printStackTrace()
        L_0x04ba:
            r13.changeOversText()
            apps.shehryar.com.cricketscroingappPro.Overs.Over r1 = r13.currentOver
            int r3 = r0.getNoscore()
            int r3 = -r3
            r1.setOverscore(r3)
            apps.shehryar.com.cricketscroingappPro.Match.Match r1 = r13.match
            apps.shehryar.com.cricketscroingappPro.Team.Team r1 = r1.getBattingTeam()
            apps.shehryar.com.cricketscroingappPro.Match.Match r3 = r13.match
            int r3 = r3.getNoExtras()
            int r3 = -r3
            r1.setNos(r3)
            apps.shehryar.com.cricketscroingappPro.Match.Match r1 = r13.match
            apps.shehryar.com.cricketscroingappPro.Team.Team r1 = r1.getBattingTeam()
            int r3 = r0.getNoscore()
            int r3 = -r3
            r1.setScore(r3)
            boolean r1 = r0.isbye()
            if (r1 != 0) goto L_0x04fc
            apps.shehryar.com.cricketscroingappPro.Match.Match r1 = r13.match
            apps.shehryar.com.cricketscroingappPro.Team.Team r1 = r1.getBattingTeam()
            apps.shehryar.com.cricketscroingappPro.Match.Match r3 = r13.match
            int r3 = r3.getNoExtras()
            int r3 = -r3
            r1.setExtras(r3)
            goto L_0x050a
        L_0x04fc:
            apps.shehryar.com.cricketscroingappPro.Match.Match r1 = r13.match
            apps.shehryar.com.cricketscroingappPro.Team.Team r1 = r1.getBattingTeam()
            int r3 = r0.getNoscore()
            int r3 = -r3
            r1.setExtras(r3)
        L_0x050a:
            r13.changeTeamsScoreText()
            apps.shehryar.com.cricketscroingappPro.Match.Match r1 = r13.match
            boolean r1 = r1.isNoRunForNo()
            if (r1 == 0) goto L_0x0520
            int r0 = r0.getNoscore()
            int r0 = r0 % r4
            if (r0 == 0) goto L_0x052a
            r13.switchBatsmen()
            goto L_0x052a
        L_0x0520:
            int r0 = r0.getNoscore()
            int r0 = r0 % r4
            if (r0 == r2) goto L_0x052a
            r13.switchBatsmen()
        L_0x052a:
            r13.updateViews()
            goto L_0x0791
        L_0x052f:
            boolean r1 = r0.isbye()
            if (r1 != r2) goto L_0x05ea
            boolean r1 = r0.iswide()
            if (r1 != 0) goto L_0x05ea
            r13.checkAndUndoOverLastBall(r0)
            int r1 = apps.shehryar.com.cricketscroingappPro.Overs.Over.balls
            int r1 = r1 - r2
            apps.shehryar.com.cricketscroingappPro.Overs.Over.balls = r1
            apps.shehryar.com.cricketscroingappPro.Overs.Over r1 = r13.currentOver
            r1.decrementOverBalls()
            apps.shehryar.com.cricketscroingappPro.Match.Match r1 = r13.match
            boolean r1 = r1.isMaxBallsFeature()
            if (r1 == 0) goto L_0x0558
            apps.shehryar.com.cricketscroingappPro.Bowler.BowlerDataUpdater r1 = r13.bowlerDataUpdater
            apps.shehryar.com.cricketscroingappPro.Bowler.Bowler r3 = r13.bowler
            r1.decrementBowlerBalls(r3, r6)
            goto L_0x0565
        L_0x0558:
            apps.shehryar.com.cricketscroingappPro.Bowler.BowlerDataUpdater r1 = r13.bowlerDataUpdater
            apps.shehryar.com.cricketscroingappPro.Bowler.Bowler r3 = r13.bowler
            apps.shehryar.com.cricketscroingappPro.Match.Match r6 = r13.match
            int r6 = r6.getPerOverBalls()
            r1.decrementBowlerBalls(r3, r6)
        L_0x0565:
            r0.getBowler()
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r1 = r0.getBatsman()
            r1.setBallsfaced(r5)
            java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.LastBall> r1 = r13.lastballslist
            java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.LastBall> r3 = r13.lastballslist
            int r3 = r3.size()
            int r3 = r3 - r2
            r1.remove(r3)
            apps.shehryar.com.cricketscroingappPro.Overs.Over r1 = r13.currentOver     // Catch:{ Exception -> 0x058c }
            java.util.ArrayList<java.lang.Integer> r1 = r1.perballScore     // Catch:{ Exception -> 0x058c }
            apps.shehryar.com.cricketscroingappPro.Overs.Over r3 = r13.currentOver     // Catch:{ Exception -> 0x058c }
            java.util.ArrayList<java.lang.Integer> r3 = r3.perballScore     // Catch:{ Exception -> 0x058c }
            int r3 = r3.size()     // Catch:{ Exception -> 0x058c }
            int r3 = r3 - r2
            r1.remove(r3)     // Catch:{ Exception -> 0x058c }
            goto L_0x0590
        L_0x058c:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0590:
            apps.shehryar.com.cricketscroingappPro.Match.Match r1 = r13.match
            apps.shehryar.com.cricketscroingappPro.Team.Team r1 = r1.getBattingTeam()
            int r3 = apps.shehryar.com.cricketscroingappPro.Overs.Over.balls
            r1.setOverballs(r3)
            apps.shehryar.com.cricketscroingappPro.Overs.Over r1 = r13.currentOver
            int r3 = r0.getLegbyescore()
            int r3 = -r3
            r1.setOverscore(r3)
            apps.shehryar.com.cricketscroingappPro.Match.Match r1 = r13.match
            apps.shehryar.com.cricketscroingappPro.Team.Team r1 = r1.getBattingTeam()
            r1.setByes(r5)
            apps.shehryar.com.cricketscroingappPro.Match.Match r1 = r13.match
            apps.shehryar.com.cricketscroingappPro.Team.Team r1 = r1.getBattingTeam()
            int r3 = r0.getByescore()
            int r3 = -r3
            r1.setScore(r3)
            apps.shehryar.com.cricketscroingappPro.Match.Match r1 = r13.match
            apps.shehryar.com.cricketscroingappPro.Team.Team r1 = r1.getBattingTeam()
            int r3 = r0.getByescore()
            int r3 = -r3
            r1.setExtras(r3)
            r13.updateViews()
            int r0 = r0.getScore()
            int r0 = r0 % r4
            if (r0 == 0) goto L_0x0791
            android.widget.RadioButton r0 = r13.rbbatsman1
            boolean r0 = r0.isChecked()
            if (r0 == 0) goto L_0x05e3
            android.widget.RadioButton r0 = r13.rbbatsman2
            r0.setChecked(r2)
            goto L_0x0791
        L_0x05e3:
            android.widget.RadioButton r0 = r13.rbbatsman1
            r0.setChecked(r2)
            goto L_0x0791
        L_0x05ea:
            boolean r1 = r0.iswide()
            if (r1 != r2) goto L_0x069e
            r13.checkAndUndoOverLastBall(r0)
            apps.shehryar.com.cricketscroingappPro.Bowler.Bowler r1 = r0.getBowler()
            apps.shehryar.com.cricketscroingappPro.Match.Match r3 = r13.match
            boolean r3 = r3.isMaxBallsFeature()
            if (r3 == 0) goto L_0x060f
            apps.shehryar.com.cricketscroingappPro.Overs.Over r3 = r13.currentOver
            r3.decrementOverExtraBalls()
            apps.shehryar.com.cricketscroingappPro.Bowler.BowlerDataUpdater r3 = r13.bowlerDataUpdater
            apps.shehryar.com.cricketscroingappPro.Match.Match r5 = r13.match
            int r5 = r5.getPerOverBalls()
            r3.decrementBowlerBalls(r1, r5)
        L_0x060f:
            apps.shehryar.com.cricketscroingappPro.Match.Match r3 = r13.match
            int r3 = r3.getWideExtras()
            int r3 = -r3
            r1.setWides(r3)
            int r3 = r0.getWidescore()
            int r3 = -r3
            r1.setTotalscore(r3)
            r13.changeOversText()
            apps.shehryar.com.cricketscroingappPro.Overs.Over r1 = r13.currentOver
            int r3 = r0.getWidescore()
            int r3 = -r3
            r1.setOverscore(r3)
            apps.shehryar.com.cricketscroingappPro.Match.Match r1 = r13.match
            apps.shehryar.com.cricketscroingappPro.Team.Team r1 = r1.getBattingTeam()
            int r3 = r0.getWidescore()
            int r3 = -r3
            r1.setScore(r3)
            apps.shehryar.com.cricketscroingappPro.Match.Match r1 = r13.match
            apps.shehryar.com.cricketscroingappPro.Team.Team r1 = r1.getBattingTeam()
            apps.shehryar.com.cricketscroingappPro.Match.Match r3 = r13.match
            int r3 = r3.getWideExtras()
            int r3 = -r3
            r1.setWides(r3)
            apps.shehryar.com.cricketscroingappPro.Match.Match r1 = r13.match
            apps.shehryar.com.cricketscroingappPro.Team.Team r1 = r1.getBattingTeam()
            int r3 = r0.getWidescore()
            int r3 = -r3
            r1.setExtras(r3)
            java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.LastBall> r1 = r13.lastballslist
            java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.LastBall> r3 = r13.lastballslist
            int r3 = r3.size()
            int r3 = r3 - r2
            r1.remove(r3)
            apps.shehryar.com.cricketscroingappPro.Overs.Over r1 = r13.currentOver     // Catch:{ Exception -> 0x0677 }
            java.util.ArrayList<java.lang.Integer> r1 = r1.perballScore     // Catch:{ Exception -> 0x0677 }
            apps.shehryar.com.cricketscroingappPro.Overs.Over r3 = r13.currentOver     // Catch:{ Exception -> 0x0677 }
            java.util.ArrayList<java.lang.Integer> r3 = r3.perballScore     // Catch:{ Exception -> 0x0677 }
            int r3 = r3.size()     // Catch:{ Exception -> 0x0677 }
            int r3 = r3 - r2
            r1.remove(r3)     // Catch:{ Exception -> 0x0677 }
            goto L_0x067b
        L_0x0677:
            r1 = move-exception
            r1.printStackTrace()
        L_0x067b:
            r13.updateViews()
            apps.shehryar.com.cricketscroingappPro.Match.Match r1 = r13.match
            boolean r1 = r1.isNoRunForWide()
            if (r1 == 0) goto L_0x0692
            int r0 = r0.getWidescore()
            int r0 = r0 % r4
            if (r0 == 0) goto L_0x0791
            r13.switchBatsmen()
            goto L_0x0791
        L_0x0692:
            int r0 = r0.getWidescore()
            int r0 = r0 % r4
            if (r0 == r2) goto L_0x0791
            r13.switchBatsmen()
            goto L_0x0791
        L_0x069e:
            android.widget.TextView r1 = r13.tvovers
            java.lang.CharSequence r1 = r1.getText()
            java.lang.String r1 = r1.toString()
            java.lang.String r7 = "0"
            boolean r1 = r1.equals(r7)
            if (r1 == 0) goto L_0x06d8
            android.widget.TextView r1 = r13.tvballs
            java.lang.CharSequence r1 = r1.getText()
            java.lang.String r1 = r1.toString()
            java.lang.String r7 = "0"
            boolean r1 = r1.equals(r7)
            if (r1 == 0) goto L_0x06d8
            over_counter = r3
            java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.LastBall> r0 = r13.lastballslist
            r0.clear()
            android.content.Context r0 = r13.getApplicationContext()
            java.lang.String r1 = "No ball bowled yet"
            android.widget.Toast r0 = android.widget.Toast.makeText(r0, r1, r3)
            r0.show()
            goto L_0x0791
        L_0x06d8:
            r13.checkAndUndoOverLastBall(r0)
            java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.LastBall> r1 = r13.lastballslist
            java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.LastBall> r3 = r13.lastballslist
            int r3 = r3.size()
            int r3 = r3 - r2
            java.lang.Object r1 = r1.get(r3)
            apps.shehryar.com.cricketscroingappPro.LastBall r1 = (apps.shehryar.com.cricketscroingappPro.LastBall) r1
            apps.shehryar.com.cricketscroingappPro.Bowler.Bowler r3 = r1.getBowler()
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r7 = r1.getBatsman()
            apps.shehryar.com.cricketscroingappPro.Overs.Over r8 = r13.currentOver     // Catch:{ Exception -> 0x0703 }
            java.util.ArrayList<java.lang.Integer> r8 = r8.perballScore     // Catch:{ Exception -> 0x0703 }
            apps.shehryar.com.cricketscroingappPro.Overs.Over r9 = r13.currentOver     // Catch:{ Exception -> 0x0703 }
            java.util.ArrayList<java.lang.Integer> r9 = r9.perballScore     // Catch:{ Exception -> 0x0703 }
            int r9 = r9.size()     // Catch:{ Exception -> 0x0703 }
            int r9 = r9 - r2
            r8.remove(r9)     // Catch:{ Exception -> 0x0703 }
            goto L_0x0707
        L_0x0703:
            r8 = move-exception
            r8.printStackTrace()
        L_0x0707:
            int r8 = r1.getScore()
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r0 = r13.decreaseBatsmanBoundaries(r7, r8, r0)
            int r7 = r1.getScore()
            int r7 = -r7
            r0.setTotalScore(r7)
            r0.setBallsfaced(r5)
            int r0 = r1.getScore()
            int r0 = -r0
            r3.setTotalscore(r0)
            int r0 = apps.shehryar.com.cricketscroingappPro.Overs.Over.balls
            int r0 = r0 - r2
            apps.shehryar.com.cricketscroingappPro.Overs.Over.balls = r0
            apps.shehryar.com.cricketscroingappPro.Overs.Over r0 = r13.currentOver
            r0.decrementOverBalls()
            apps.shehryar.com.cricketscroingappPro.Match.Match r0 = r13.match
            boolean r0 = r0.isMaxBallsFeature()
            if (r0 == 0) goto L_0x073a
            apps.shehryar.com.cricketscroingappPro.Bowler.BowlerDataUpdater r0 = r13.bowlerDataUpdater
            r0.decrementBowlerBalls(r3, r6)
            goto L_0x0745
        L_0x073a:
            apps.shehryar.com.cricketscroingappPro.Bowler.BowlerDataUpdater r0 = r13.bowlerDataUpdater
            apps.shehryar.com.cricketscroingappPro.Match.Match r5 = r13.match
            int r5 = r5.getPerOverBalls()
            r0.decrementBowlerBalls(r3, r5)
        L_0x0745:
            apps.shehryar.com.cricketscroingappPro.Match.Match r0 = r13.match
            apps.shehryar.com.cricketscroingappPro.Team.Team r0 = r0.getBattingTeam()
            int r3 = apps.shehryar.com.cricketscroingappPro.Overs.Over.balls
            r0.setOverballs(r3)
            apps.shehryar.com.cricketscroingappPro.Overs.Over r0 = r13.currentOver
            int r3 = r1.getScore()
            int r3 = -r3
            r0.setOverscore(r3)
            apps.shehryar.com.cricketscroingappPro.Match.Match r0 = r13.match
            apps.shehryar.com.cricketscroingappPro.Team.Team r0 = r0.getBattingTeam()
            int r3 = r1.getScore()
            int r3 = -r3
            r0.setScore(r3)
            r13.updateViews()
            int r0 = r1.getScore()
            int r0 = r0 % r4
            if (r0 == 0) goto L_0x0785
            android.widget.RadioButton r0 = r13.rbbatsman1
            boolean r0 = r0.isChecked()
            if (r0 == 0) goto L_0x0780
            android.widget.RadioButton r0 = r13.rbbatsman2
            r0.setChecked(r2)
            goto L_0x0785
        L_0x0780:
            android.widget.RadioButton r0 = r13.rbbatsman1
            r0.setChecked(r2)
        L_0x0785:
            java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.LastBall> r0 = r13.lastballslist
            java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.LastBall> r1 = r13.lastballslist
            int r1 = r1.size()
            int r1 = r1 - r2
            r0.remove(r1)
        L_0x0791:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: apps.shehryar.com.cricketscroingappPro.MainActivity_Test.manageUndo():void");
    }

    private void changeOversText() {
        if (!this.match.isTestMatch) {
            TextView textView = this.tvovers;
            textView.setText("(" + this.match.getBattingTeam().getOversPlayed() + "." + this.match.getBattingTeam().getOverballs() + "/" + this.match.getOvers() + ")");
            return;
        }
        TextView textView2 = this.tvovers;
        textView2.setText("(" + this.match.getBattingTeam().getOversPlayed() + "." + this.match.getBattingTeam().getOverballs() + ")");
    }

    private void switchBatsmen() {
        if (this.rbbatsman1.isChecked()) {
            this.rbbatsman2.setChecked(true);
        } else {
            this.rbbatsman1.setChecked(true);
        }
    }

    private Batsman decreaseBatsmanBoundaries(Batsman batsman, int i, LastBall lastBall) {
        if (!lastBall.isbye()) {
            if (i == 0) {
                batsman.setDots(-1);
            } else if (i == 1) {
                batsman.setSingles(-1);
            } else if (i == 2) {
                batsman.setDoubles(-1);
            } else if (i == 3) {
                batsman.setThrees(-1);
            } else if (i == 4) {
                batsman.setFours(-1);
            } else if (i == 6) {
                batsman.setSixs(-1);
            }
        }
        return batsman;
    }

    private void assignBatsmanScores(LastBall lastBall) {
        if (this.currentBatsmans[0].equals(lastBall.getBatsman())) {
            if (!lastBall.isbye()) {
                if (!this.match.isNoRunForNo()) {
                    this.currentBatsmans[0].setTotalScore(-(lastBall.getNoscore() - 1));
                } else {
                    this.currentBatsmans[0].setTotalScore(-lastBall.getNoscore());
                }
            }
            if (!lastBall.isno() && !lastBall.iswide()) {
                this.currentBatsmans[0].setBallsfaced(-1);
            }
            changeBatsman1ScoreText();
            return;
        }
        if (!lastBall.isbye()) {
            if (!this.match.isNoRunForNo()) {
                this.currentBatsmans[1].setTotalScore(-(lastBall.getNoscore() - 1));
            } else {
                this.currentBatsmans[1].setTotalScore(-lastBall.getNoscore());
            }
        }
        if (!lastBall.isno() && !lastBall.iswide()) {
            this.currentBatsmans[1].setBallsfaced(-1);
        }
        changeBatsman2ScoreText();
    }

    private void removeFallofWicketFromDB(FallOfWickets fallOfWickets) {
        this.dbHelper.deleteFallOfWicket(fallOfWickets.getId());
    }

    private void removeNewBatsmanFromDB(Batsman batsman) {
        long j;
        if (this.code == 99) {
            j = this.team_opp_id;
        } else {
            j = this.team_yours_id;
        }
        this.dbHelper.deleteBatsmanScore(this.matchid, j, batsman.getBatsmanID());
        this.dbHelper.DeletePlayer(j, batsman.getBatsmanID());
    }

    private void changeBatsman1ScoreText() {
        try {
            this.batsmanViewsUpdater.changeBatsmanScoreText(this.currentBatsmans[0], 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void changeBatsman2ScoreText() {
        try {
            this.batsmanViewsUpdater.changeBatsmanScoreText(this.currentBatsmans[1], 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: package-private */
    public void handleRunOut2() {
        new AlertDialog.Builder(this).setTitle("Wicket").setMessage("Are you Sure it was a Wicket?").setPositiveButton(17039370, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (MainActivity_Test.this.rbbatsman1.isChecked() || MainActivity_Test.this.rbbatsman2.isChecked()) {
                    Wicket.showRunOutDialog(MainActivity_Test.this, MainActivity_Test.this.viewsUpdater.getBatsmanFacing(), 5, MainActivity_Test.this.match.getBattingTeam(), MainActivity_Test.this.currentBatsmans[0], MainActivity_Test.this.currentBatsmans[1], MainActivity_Test.this.bowler, MainActivity_Test.this.match);
                } else {
                    Toast.makeText(MainActivity_Test.this.getApplicationContext(), "Please Select the facing Batsman first", 0).show();
                }
            }
        }).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).create().show();
    }

    /* access modifiers changed from: package-private */
    public void updateDataInDB() {
        this.dbHelper.UpdateBatsmanScore(this.match.getBattingTeam().getTeamID(), this.match.getMatchID(), this.currentBatsmans[0]);
        this.dbHelper.UpdateBatsmanScore(this.match.getBattingTeam().getTeamID(), this.match.getMatchID(), this.currentBatsmans[1]);
        this.dbHelper.UpdateTeamScore(this.match.getBattingTeam().getTeamID(), this.match.getMatchID(), this.match.getBattingTeam().getScore(), this.match.getBattingTeam().getWickets(), this.match.getBattingTeam().getWides(), this.match.getBattingTeam().getNos(), this.match.getBattingTeam().getOversPlayed(), this.match.getBattingTeam().getExtras(), this.match.getBattingTeam().getOverballs());
        this.dbHelper.UpdateBowlerScore(this.match.getBowlingTeam().getTeamID(), this.match.getMatchID(), this.bowler.getBowlerID(), this.bowler.getTotalscore(), this.bowler.getWickets(), this.bowler.getBowlerovers(), this.bowler.getMaidens(), this.bowler.getBalls());
        Log.i("bowling team id", this.match.getBowlingTeam().getTeamID() + "");
    }

    /* access modifiers changed from: package-private */
    public void changeViewsData() {
        changeTeamsScoreText();
        TextView textView = this.tvovers;
        textView.setText("(" + over_counter + "." + Over.balls + "/" + this.match.getOvers() + ")");
        String format = String.format("%.2f", new Object[]{Float.valueOf((((float) this.match.getBattingTeam().getScore()) / ((float) ((over_counter * 6) + Over.balls))) * 6.0f)});
        TextView textView2 = this.tvrunrate;
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(format);
        textView2.setText(sb.toString());
        changeBatsman1ScoreText();
        changeBatsman2ScoreText();
        changeBowlerScoreText();
    }

    /* access modifiers changed from: private */
    public void changeBowlerScoreText() {
        TextView textView = this.tvbowlerscore;
        textView.setText(this.bowler.getTotalscore() + "/" + this.bowler.getWickets());
        TextView textView2 = this.tvbowlerovers;
        textView2.setText("(" + this.bowler.getBowlerovers() + "." + this.bowler.getBalls() + ")");
    }

    /* access modifiers changed from: package-private */
    public void afterWicket(final int i) {
        new AlertDialog.Builder(this).setTitle("Wicket").setMessage("Are you Sure it was a Wicket?").setPositiveButton(17039370, new DialogInterface.OnClickListener() {
            @RequiresApi(api = 21)
            public void onClick(DialogInterface dialogInterface, int i) {
                if (MainActivity_Test.this.batsmanViewsUpdater.getCheckedRadioButton() == 0) {
                    Toast.makeText(MainActivity_Test.this.getApplicationContext(), "Please Select the facing Batsman first", 0).show();
                } else {
                    Wicket.showWicketDialog(MainActivity_Test.this, MainActivity_Test.this.viewsUpdater.getBatsmanFacing(), i, MainActivity_Test.this.match.getBattingTeam(), MainActivity_Test.this.currentBatsmans[MainActivity_Test.this.viewsUpdater.getBatsmanFacing()], MainActivity_Test.this.bowler, MainActivity_Test.this.match);
                }
            }
        }).setNegativeButton("Cancel", (DialogInterface.OnClickListener) null).show();
    }

    /* access modifiers changed from: private */
    public void showSuggestionsDialog(AutoCompleteTextView autoCompleteTextView, ArrayList<String> arrayList, SuggestionDialog.SuggestionsClickCallBack suggestionsClickCallBack) {
        new SuggestionDialog(this, autoCompleteTextView, arrayList, suggestionsClickCallBack).show(getFragmentManager(), "suggestion Dialog");
    }

    /* access modifiers changed from: package-private */
    public boolean checkBatsman(String str) {
        boolean z;
        int i = 0;
        if (this.code == 99) {
            z = false;
            while (i < this.match.getTeam2().batsmans_list.size()) {
                if (this.match.getTeam2().batsmans_list.get(i).getName().equals(str) && !this.match.getTeam2().batsmans_list.get(i).isOut().equals("Ret")) {
                    z = true;
                }
                i++;
            }
        } else {
            z = false;
            while (i < this.match.getTeam1().batsmans_list.size()) {
                if (this.match.getTeam1().batsmans_list.get(i).getName().equals(str) && !this.match.getTeam1().batsmans_list.get(i).isOut().equals("Ret")) {
                    z = true;
                }
                i++;
            }
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public void hideKeyBoard(AutoCompleteTextView autoCompleteTextView) {
        autoCompleteTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 5) {
                    return false;
                }
                ((InputMethodManager) MainActivity_Test.this.getSystemService("input_method")).hideSoftInputFromWindow(textView.getWindowToken(), 0);
                return true;
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void showDropDown(final AutoCompleteTextView autoCompleteTextView) {
        autoCompleteTextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    autoCompleteTextView.showDropDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void setDropDownAdapter(AutoCompleteTextView autoCompleteTextView) {
        Custom_Suggestions_Adapter custom_Suggestions_Adapter;
        getPlayers();
        if (this.code == 99) {
            custom_Suggestions_Adapter = new Custom_Suggestions_Adapter(this, R.layout.custom_suggestions_layout, PlayersInputActivity.oppPlayers);
        } else {
            custom_Suggestions_Adapter = new Custom_Suggestions_Adapter(this, R.layout.custom_suggestions_layout, PlayersInputActivity.yourplayers);
        }
        autoCompleteTextView.setAdapter(custom_Suggestions_Adapter);
    }

    private void showBatmsanDetailDialog(Batsman batsman) throws Exception {
        BatsmanDetailsDialog batsmanDetailsDialog = new BatsmanDetailsDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("batsman", batsman);
        bundle.putSerializable(DBHelper.TABLE_MATCH, this.match);
        batsmanDetailsDialog.setArguments(bundle);
        batsmanDetailsDialog.show(getFragmentManager(), "batsman");
    }

    private void showTeamDetailsDialog(Team team) throws IllegalStateException {
        TeamDetailsDialog teamDetailsDialog = new TeamDetailsDialog();
        team.setMatchTotalOvers(this.match.getOvers());
        Bundle bundle = new Bundle();
        bundle.putSerializable(DBHelper.TABLE_TEAM, team);
        bundle.putSerializable(DBHelper.TABLE_MATCH, this.match);
        teamDetailsDialog.setArguments(bundle);
        teamDetailsDialog.show(getFragmentManager(), DBHelper.TABLE_TEAM);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvbowlername /*2131755237*/:
            case R.id.tvbowlerscore /*2131755269*/:
            case R.id.ll_bowler /*2131755325*/:
                try {
                    BowlerHelper.showBowlerDetailsDialog(this, this.bowler, this.match.getBattingTeam().overs_list, this.match);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            case R.id.teamname /*2131755256*/:
            case R.id.tvscore /*2131755257*/:
                try {
                    showTeamDetailsDialog(this.match.getTeam1());
                    return;
                } catch (IllegalStateException e2) {
                    e2.printStackTrace();
                    return;
                }
            case R.id.tvbat1score /*2131755265*/:
            case R.id.tvbat1strikerate /*2131755266*/:
            case R.id.ll_batsman_one /*2131755319*/:
                try {
                    showBatmsanDetailDialog(this.currentBatsmans[0]);
                    return;
                } catch (Exception e3) {
                    e3.printStackTrace();
                    return;
                }
            case R.id.tvbat2score /*2131755267*/:
            case R.id.tvbat2strikerate /*2131755268*/:
            case R.id.ll_batsman_two /*2131755330*/:
                try {
                    showBatmsanDetailDialog(this.currentBatsmans[1]);
                    return;
                } catch (Exception e4) {
                    e4.printStackTrace();
                    return;
                }
            case R.id.team_2_name /*2131755305*/:
                try {
                    showTeamDetailsDialog(this.match.getTeam2());
                    return;
                } catch (IllegalStateException e5) {
                    e5.printStackTrace();
                    return;
                }
            default:
                return;
        }
    }

    public void onSuggestionClicked(EditText editText, String str) {
        editText.setText(str);
    }

    public void onTypeYourOwnClick(EditText editText) {
        editText.setText("");
        editText.requestFocus();
        getWindow().setSoftInputMode(4);
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z) {
            if (compoundButton.getId() == R.id.rbbatsman1) {
                this.rbbatsman2.setChecked(false);
            }
            if (compoundButton.getId() == R.id.rbbatsman2) {
                this.rbbatsman1.setChecked(false);
            }
        }
    }

    public void manageScore(int i, boolean z, boolean z2, boolean z3, boolean z4, ViewsUpdater viewsUpdater2, boolean z5, Wicket wicket) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        FallOfWickets fallOfWickets;
        boolean z6;
        ViewsUpdater viewsUpdater3;
        int i8;
        int i9;
        int i10;
        int i11 = i;
        boolean z7 = z;
        boolean z8 = z2;
        boolean z9 = z3;
        boolean z10 = z4;
        ViewsUpdater viewsUpdater4 = viewsUpdater2;
        Activity activity2 = viewsUpdater2.getActivity();
        int i12 = 0;
        if (i11 > 0 || z7 || z8) {
            this.currentOver.maiden = false;
        }
        int i13 = 1;
        if (wicket == null) {
            i2 = viewsUpdater2.getRbbatsman1().isChecked() ? 0 : 1;
        } else {
            i2 = wicket.getBatFacingForRuns();
        }
        if (z8 || z7) {
            if (this.match.isMaxBallsFeature()) {
                this.currentOver.incerementOverExtraBalls();
                i9 = 1;
            } else {
                i9 = 0;
            }
            i6 = this.match.getExtraRuns(z7, z8) + i11;
            if (z9 || z10) {
                i10 = this.match.getExtraRuns(z7, z8);
            } else {
                i10 = this.match.getExtraRuns(z7, z8) + i11;
            }
            if (!z8 || z9 || z10 || z7) {
                i3 = i9;
                i7 = i6;
                i5 = 0;
                i4 = i10;
                i13 = 0;
                i12 = -1;
            } else {
                i3 = i9;
                i7 = i6;
                i4 = i10;
                i5 = 1;
                i13 = 0;
                i12 = i11;
            }
        } else {
            this.currentOver.incrementOverBalls();
            if (z9 || z10) {
                this.currentOver.maiden = true;
                i7 = i11;
                i6 = i7;
                i4 = 0;
                i3 = 0;
                i5 = 1;
            } else {
                i6 = i11;
                i4 = i6;
                i7 = 0;
                i3 = 0;
                i5 = 1;
                i12 = i4;
            }
        }
        Over.balls += i13;
        this.match.getBattingTeam().setScore(i6);
        this.match.getBattingTeam().setExtras(i7);
        this.match.getBattingTeam().setOverballs(this.currentOver.getOverBalls());
        if (i12 != -1 && (wicket == null || !wicket.isRunOut())) {
            this.currentBatsmans[i2].setTotalScore(i12);
            BatsmanDataUpdater.assignScoresToBatsman(this.currentBatsmans[i2], i12);
        }
        if (wicket == null) {
            this.currentBatsmans[i2].setBallsfaced(i5);
        }
        this.bowler.setTotalscore(i4);
        this.bowler.setBalls(i13 + i3);
        if (i11 != 0) {
            this.currentOver.maiden = false;
        }
        this.currentOver.setOverscore(i6);
        String str = null;
        if (wicket != null) {
            if (!wicket.isRunOut()) {
                i8 = 1;
                this.bowler.setWickets(1);
            } else {
                i8 = 1;
            }
            this.match.getBattingTeam().setWickets(i8);
            fallOfWickets = new FallOfWickets();
            fallOfWickets.setRunOut(wicket.isRunOut());
            fallOfWickets.setBye(wicket.isBye() || wicket.isLegBye());
            fallOfWickets.setWicketNo(this.match.getBattingTeam().getWickets());
            fallOfWickets.setScore(this.match.getBattingTeam().getScore());
            fallOfWickets.setOverno(this.match.getBattingTeam().getOversPlayed());
            fallOfWickets.setBall(this.currentOver.getOverBalls());
            fallOfWickets.setBatsman(wicket.getBatsman());
            fallOfWickets.setBowler(wicket.getBowler());
            fallOfWickets.setWide(wicket.isWide());
            fallOfWickets.setNo(wicket.isNo());
            fallOfWickets.setRunsScored(wicket.getRunsScored());
            fallOfWickets.setBatsmanName(wicket.getBatsman().getName());
            fallOfWickets.setBowlerName(wicket.getBowler().getName());
            fallOfWickets.setBatFacingCode(wicket.getBatFacingForRuns() + 1);
            fallOfWickets.setBatOutCode(wicket.getBatFacingOut() + 1);
            fallOfWickets.setId(new DBHelper(activity2).insertFallofWicket(this.match.getMatchID(), this.match.getBattingTeam().getTeamID(), fallOfWickets));
            this.match.getBattingTeam().fallOfWicketses.add(fallOfWickets);
        } else {
            fallOfWickets = null;
        }
        LastBall lastBall = new LastBall();
        lastBall.setBowler(this.bowler);
        lastBall.setScore(i11);
        lastBall.setBatsman(this.currentBatsmans[i2]);
        lastBall.setOver(this.currentOver);
        lastBall.setIsbye(z9);
        lastBall.setIslegbye(z10);
        lastBall.setIsno(z8);
        lastBall.setIswide(z7);
        boolean z11 = z5;
        lastBall.setIswicket(z11);
        if (this.currentOver.getOverBalls() == this.match.getPerOverBalls()) {
            lastBall.setLastBallOfOver(true);
        }
        lastBall.setWidescore(i7);
        lastBall.setNoscore(i7);
        lastBall.setByescore(i7);
        lastBall.setLegbyescore(i7);
        if (fallOfWickets != null) {
            lastBall.setFallOfWickets(fallOfWickets);
        }
        this.lastballslist.add(lastBall);
        OverHelper.addOverPerBallScore(this.match, this.currentOver, i6, z7, z8, z9, z10, z11);
        if (z7 || z8) {
            viewsUpdater3 = viewsUpdater2;
            z6 = true;
        } else {
            viewsUpdater3 = viewsUpdater2;
            z6 = false;
        }
        viewsUpdater3.switchRadioCheck(i11, z6);
        if ((this.match.isMaxBallsFeature() && this.bowler.getBalls() == this.match.getPerOverBalls()) || (!this.match.isMaxBallsFeature() && this.bowler.getBalls() == this.match.getPerOverBalls())) {
            this.bowlerDataUpdater.changeBowlerOverAndBalls(this.match, this.bowler, this.bowler.getBalls(), this.match.getPerOverBalls());
        }
        if (this.match.getBattingTeam().getWickets() == this.match.getPerMatchWickets()) {
            str = this.match.checkMatchFinished(false);
            if (this.currentOver.getOverBalls() == this.match.getPerOverBalls()) {
                this.match.getBattingTeam().setOversPlayed(1);
                this.match.getBattingTeam().setOverballs(0);
                this.currentOver.resetOverBalls();
            }
            updateDataInDB();
            checkAndStartNextActivity(str);
        }
        if ((this.match.isMaxBallsFeature() && (this.currentOver.getOverBalls() == 6 || this.currentOver.getOverBalls() + this.currentOver.getOverExtraBalls() == this.match.getPerOverBalls())) || (!this.match.isMaxBallsFeature() && this.currentOver.getOverBalls() == this.match.getPerOverBalls())) {
            this.match.getBattingTeam().setOversPlayed(1);
            this.match.getBattingTeam().setOverballs(0);
            str = this.match.checkMatchFinished(false);
            this.currentOver.resetOverBalls();
            if (this.match.getBattingTeam().getOversPlayed() != this.match.getOvers()) {
                try {
                    this.lastballslist.get(this.lastballslist.size() - 1).setLastBallOfOver(true);
                    if (this.lastballslist.get(this.lastballslist.size() - 1).isLastBallOfOver()) {
                        BowlerHelper.changeBowlerMaiderOvers(this.bowler, this.currentOver);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                AfterOverDialogFragment afterOverDialogFragment = new AfterOverDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable(DBHelper.TABLE_MATCH, this.match);
                afterOverDialogFragment.setArguments(bundle);
                afterOverDialogFragment.show(getSupportFragmentManager(), "after over dialog");
            } else {
                updateDataInDB();
                checkAndStartNextActivity(str);
            }
        }
        viewsUpdater3.updateViews(this.match, this.currentBatsmans, this.bowler, this.currentOver);
        if (str == null) {
            str = this.match.checkMatchFinished(false);
        }
        if (str != null) {
            updateDataInDB();
            this.match.setResult(str);
            ActivityStarter.startMatchResultActivity(this, this.match);
        }
    }

    private void checkAndStartNextActivity(String str) {
        if (this.match.isTestMatch) {
            if (str != null) {
                ActivityStarter.startMatchResultActivity(this.viewsUpdater.getActivity(), this.match);
            } else {
                ActivityStarter.startAfterInningsActivity(this.viewsUpdater.getActivity(), this.match);
            }
        } else if (this.match.secondInnings) {
            ActivityStarter.startMatchResultActivity(this.viewsUpdater.getActivity(), this.match);
        } else {
            ActivityStarter.startAfterInningsActivity(this.viewsUpdater.getActivity(), this.match);
        }
    }

    public void undoScore(LastBall lastBall, ViewsUpdater viewsUpdater2) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        ViewsUpdater viewsUpdater3 = viewsUpdater2;
        viewsUpdater2.getActivity();
        int i9 = -lastBall.getScore();
        boolean iswide = lastBall.iswide();
        boolean isno = lastBall.isno();
        boolean isbye = lastBall.isbye();
        boolean islegbye = lastBall.islegbye();
        Wicket wicket = lastBall.getWicket();
        if (i9 > 0 || iswide || isno) {
            this.currentOver.maiden = false;
        }
        if (wicket == null) {
            i = viewsUpdater2.getRbbatsman1().isChecked() ? 0 : 1;
        } else {
            i = wicket.getBatFacingForRuns();
        }
        if (isno || iswide) {
            i4 = i9 - this.match.getExtraRuns(iswide, isno);
            if (isbye || islegbye) {
                i8 = 0 - this.match.getExtraRuns(iswide, isno);
            } else {
                i8 = i9 - this.match.getExtraRuns(iswide, isno);
            }
            if (!isno || isbye || islegbye) {
                i6 = 0;
                i5 = i4;
                i2 = i8;
                i3 = 1;
            } else {
                i6 = 0;
                i5 = i4;
                i2 = i8;
                i3 = i9;
            }
        } else {
            this.currentOver.incrementOverBalls();
            if (isbye || islegbye) {
                i5 = i9;
                i4 = i5;
                i3 = 0;
                i2 = 0;
            } else {
                i4 = i9;
                i3 = i4;
                i2 = i3;
                i5 = 0;
            }
            i6 = -1;
        }
        Over.balls -= i6;
        this.match.getBattingTeam().setScore(i4);
        this.match.getBattingTeam().setExtras(i5);
        this.match.getBattingTeam().setOverballs(this.currentOver.getOverBalls());
        if (i3 != -1) {
            if (wicket == null) {
                this.currentBatsmans[i].setTotalScore(i3);
                BatsmanDataUpdater.assignScoresToBatsman(this.currentBatsmans[i], i3);
            } else if (!wicket.isRunOut()) {
                this.currentBatsmans[i].setTotalScore(i3);
                BatsmanDataUpdater.assignScoresToBatsman(this.currentBatsmans[i], i3);
            }
        }
        if (wicket == null) {
            this.currentBatsmans[i].setBallsfaced(i6);
        }
        this.bowler.setTotalscore(i2);
        this.bowler.setBalls(i6);
        if (i9 != 0) {
            this.currentOver.maiden = false;
            this.currentOver.setOverscore(i9);
        }
        if (wicket != null) {
            if (!wicket.isRunOut()) {
                this.bowler.setWickets(-1);
            }
            this.match.getBattingTeam().setWickets(-1);
            this.dbHelper.deleteFallOfWicket(lastBall.getFallOfWickets().getId());
            i7 = 1;
            this.match.getBattingTeam().fallOfWicketses.remove(this.match.getBattingTeam().getFallOfWicketses().size() - 1);
        } else {
            i7 = 1;
        }
        this.lastballslist.remove(this.lastballslist.size() - i7);
        this.currentOver.perballScore.remove(this.currentOver.perballScore.size() - i7);
        viewsUpdater3.switchRadioCheck(i9, iswide || isno);
        if (this.bowler.getBalls() == this.match.getPerOverBalls()) {
            this.bowler.setBowlerovers(-1);
            this.bowler.resetBalls();
        }
        if (this.currentOver.getOverBalls() == this.match.getPerOverBalls()) {
            this.match.getBattingTeam().setOversPlayed(-1);
            this.match.getBattingTeam().setOverballs(0);
            this.currentOver.resetOverBalls();
            if (this.match.getBattingTeam().getOversPlayed() != this.match.getOvers()) {
                try {
                    if (!this.lastballslist.get(this.lastballslist.size() - 1).isLastBallOfOver()) {
                        BowlerHelper.changeBowlerMaiderOvers(this.bowler, this.currentOver);
                    }
                    this.lastballslist.get(this.lastballslist.size() - 1).setLastBallOfOver(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                AfterOverDialogFragment afterOverDialogFragment = new AfterOverDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable(DBHelper.TABLE_MATCH, this.match);
                afterOverDialogFragment.setArguments(bundle);
                afterOverDialogFragment.show(getSupportFragmentManager(), "after over dialog");
            } else {
                updateDataInDB();
                if (this.match.isTestMatch) {
                    if (this.match.fourthInnings) {
                        ActivityStarter.startMatchResultActivity(viewsUpdater2.getActivity(), this.match);
                    } else {
                        ActivityStarter.startAfterInningsActivity(viewsUpdater2.getActivity(), this.match);
                    }
                } else if (this.match.secondInnings) {
                    ActivityStarter.startMatchResultActivity(viewsUpdater2.getActivity(), this.match);
                } else {
                    ActivityStarter.startAfterInningsActivity(viewsUpdater2.getActivity(), this.match);
                }
            }
        }
        this.lastballslist.add(lastBall);
        viewsUpdater3.updateViews(this.match, this.currentBatsmans, this.bowler, this.currentOver);
        String checkMatchFinished = this.match.checkMatchFinished(false);
        if (checkMatchFinished != null) {
            updateDataInDB();
            this.match.setResult(checkMatchFinished);
            ActivityStarter.startMatchResultActivity(this, this.match);
        }
    }

    public void onNextTapped(Wicket wicket, String str) {
        updateDataInDB();
        this.currentOver.setWickets(1);
        if (!str.equals("All Out")) {
            this.currentBatsmans[wicket.getBatFacingOut()] = BatsmanUtilityFunctionsClass.getNewOrRetiredBatsman(str, this.match.getBattingTeam(), this.dbHelper, this.match);
        }
        if (wicket.isCrossed()) {
            this.viewsUpdater.switchBatsmen();
        }
        manageScore(wicket.getRunsScored(), wicket.isWide(), wicket.isNo(), wicket.isBye(), wicket.isLegBye(), this.viewsUpdater, true, wicket);
        if (wicket.isRunOut()) {
            this.viewsUpdater.getRbbatsman1().setChecked(false);
            this.viewsUpdater.getRbbatsman2().setChecked(false);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.interstitialAd = AdsLoader.initializeInterstitialAd(this, this.interstitialAd, this.adListener, Constants.batsmanDetailsInterstitalAdId);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    public void onListItemClicked(int i) {
        this.dl.closeDrawers();
        switch (i) {
            case R.id.viewovershistory /*2131755739*/:
                Intent intent = new Intent(this, OverHistoryActivity.class);
                intent.putExtra(DBHelper.TABLE_TEAM, this.match.getBattingTeam());
                startActivity(intent);
                return;
            case R.id.viewfallofwickets /*2131755740*/:
                Intent intent2 = new Intent(this, FallOfWicketsActivity.class);
                intent2.putExtra("wickets", this.match.getBattingTeam().fallOfWicketses);
                startActivity(intent2);
                return;
            default:
                switch (i) {
                    case R.id.viewbatsmanhistory /*2131755758*/:
                        Intent intent3 = new Intent(this, BatsmanHistoryActivity.class);
                        intent3.putExtra("team2name", this.match.getBowlingTeam().getName());
                        intent3.putExtra(DBHelper.TABLE_MATCH, this.match);
                        Log.e("team id", this.match.getTeam_Yours_id() + " " + this.match.getTeam_opp_id());
                        startActivity(intent3);
                        return;
                    case R.id.viewbowlershistory /*2131755759*/:
                        Intent intent4 = new Intent(this, BowlersHisotryActivity.class);
                        intent4.putExtra(DBHelper.TABLE_MATCH, this.match);
                        intent4.putExtra(DBHelper.TABLE_TEAM, this.match.getBowlingTeam());
                        intent4.putExtra(DBHelper.TABLE_OVERS, this.match.getBattingTeam().overs_list);
                        intent4.putExtra("fullact", true);
                        startActivity(intent4);
                        return;
                    case R.id.viewPartenerships /*2131755760*/:
                        Intent intent5 = new Intent(this, PartenershipsActivity.class);
                        intent5.putExtra("batsmen", this.match.getBattingTeam().batsmans_list);
                        intent5.putExtra("fow", this.match.getBattingTeam().fallOfWicketses);
                        intent5.putExtra(FirebaseAnalytics.Param.SCORE, this.match.getBattingTeam().getScore());
                        intent5.putExtra(DBHelper.TABLE_MATCH, this.match);
                        intent5.putExtra(DBHelper.TABLE_TEAM, this.match.getBattingTeam());
                        startActivity(intent5);
                        return;
                    case R.id.finishInnings /*2131755761*/:
                        new AlertDialog.Builder(this.context).setTitle("Finish this Innings").setMessage("Are you sure you want to finish this innings?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                MainActivity_Test.over_counter = 0;
                                Over.balls = 0;
                                MainActivity_Test.this.updateDataInDB();
                                String checkMatchFinished = MainActivity_Test.this.match.checkMatchFinished(true);
                                if (MainActivity_Test.this.match.isTestMatch) {
                                    if (checkMatchFinished != null) {
                                        ActivityStarter.startMatchResultActivity(MainActivity_Test.this.viewsUpdater.getActivity(), MainActivity_Test.this.match);
                                    } else {
                                        ActivityStarter.startAfterInningsActivity(MainActivity_Test.this.viewsUpdater.getActivity(), MainActivity_Test.this.match);
                                    }
                                } else if (MainActivity_Test.this.match.secondInnings) {
                                    ActivityStarter.startMatchResultActivity(MainActivity_Test.this.viewsUpdater.getActivity(), MainActivity_Test.this.match);
                                } else {
                                    ActivityStarter.startAfterInningsActivity(MainActivity_Test.this.viewsUpdater.getActivity(), MainActivity_Test.this.match);
                                }
                            }
                        }).setNegativeButton("No", (DialogInterface.OnClickListener) null).show();
                        return;
                    case R.id.viewMatchDetails /*2131755762*/:
                        Intent intent6 = new Intent(this, Previous_Match_Result.class);
                        intent6.putExtra("team1name", this.match.getTeam1().getName());
                        intent6.putExtra("team2name", this.match.getTeam2().getName());
                        intent6.putExtra("team1stats", this.match.getTeam1());
                        intent6.putExtra("team2stats", this.match.getTeam2());
                        intent6.putExtra(DBHelper.TABLE_MATCH, this.match);
                        intent6.putExtra("code", "thismatch");
                        startActivityForResult(intent6, 0);
                        return;
                    default:
                        return;
                }
        }
    }
}
