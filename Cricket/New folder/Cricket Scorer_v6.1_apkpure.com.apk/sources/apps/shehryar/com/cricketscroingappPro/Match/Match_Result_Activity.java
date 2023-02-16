package apps.shehryar.com.cricketscroingappPro.Match;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Batsman.FragmentBatsmanHistoryRecyclerView;
import apps.shehryar.com.cricketscroingappPro.Bowler.BowlerViewsUpdater;
import apps.shehryar.com.cricketscroingappPro.Bowler.Dialog_Custom_ListView_Adapter;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.FirstPage;
import apps.shehryar.com.cricketscroingappPro.MyToast;
import apps.shehryar.com.cricketscroingappPro.Overs.Over;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.AdsLoader;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.Constants;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.FontProvider;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.SharedPrefsHelper;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import java.util.ArrayList;

public class Match_Result_Activity extends AppCompatActivity implements View.OnClickListener {
    Button bfinish;
    Button bstartnewmatch;
    Button bviewdetails;
    DBHelper dbHelper;
    private InterstitialAd mInterstitialAd;
    Match match;
    Team team1;
    Team team2;
    TextView tvextras;
    TextView tvresult;
    TextView tvscore;
    TextView tvteamname;
    TextView tvteamovers;
    TextView tvteamscore;
    TextView tvwickets;
    Team winningTeam;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_match__result_);
        this.mInterstitialAd = AdsLoader.initializeInterstitialAd(this, this.mInterstitialAd, new AdListener() {
            public void onAdClosed() {
                super.onAdClosed();
                Intent intent = new Intent(Match_Result_Activity.this, Previous_Match_Result.class);
                intent.putExtra("team1name", Match_Result_Activity.this.team1.getName());
                intent.putExtra("team2name", Match_Result_Activity.this.team2.getName());
                intent.putExtra("team1stats", Match_Result_Activity.this.team1);
                intent.putExtra("team2stats", Match_Result_Activity.this.team2);
                intent.putExtra(DBHelper.TABLE_MATCH, Match_Result_Activity.this.match);
                intent.putExtra("code", "thismatch");
                Match_Result_Activity.this.startActivity(intent);
            }
        }, Constants.matchResultInterstitialAdId);
        this.dbHelper = new DBHelper(getApplicationContext());
        this.tvscore = (TextView) findViewById(R.id.tvscore);
        this.tvwickets = (TextView) findViewById(R.id.tvwickets);
        this.bstartnewmatch = (Button) findViewById(R.id.bstartnewmatch);
        this.bviewdetails = (Button) findViewById(R.id.bviewdetails);
        this.bfinish = (Button) findViewById(R.id.bfinish);
        this.tvextras = (TextView) findViewById(R.id.tvextras);
        this.tvteamname = (TextView) findViewById(R.id.tvinnteamname);
        this.tvteamscore = (TextView) findViewById(R.id.tvscore);
        this.tvteamovers = (TextView) findViewById(R.id.tvinnovers);
        this.bstartnewmatch.setOnClickListener(this);
        this.bviewdetails.setOnClickListener(this);
        this.bfinish.setOnClickListener(this);
        try {
            FontProvider.setEuroStileFont(this, this.bstartnewmatch);
            FontProvider.setEuroStileFont(this, this.bviewdetails);
            FontProvider.setEuroStileFont(this, this.bfinish);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.tvresult = (TextView) findViewById(R.id.tvresult);
        Typeface createFromAsset = Typeface.createFromAsset(getAssets(), "fonts/RobotoCondensed_Bold.ttf");
        this.match = (Match) getIntent().getSerializableExtra(DBHelper.TABLE_MATCH);
        this.team2 = this.match.getBattingTeam();
        this.team1 = this.match.getBowlingTeam();
        showChooseManOfTheMatchDialog();
        int overs = this.match.getOvers();
        this.tvteamname.setText(this.team2.getName());
        TextView textView = this.tvteamscore;
        textView.setText(this.team2.getScore() + "");
        this.tvteamscore.setTypeface(createFromAsset);
        this.tvwickets.setTypeface(createFromAsset);
        TextView textView2 = this.tvwickets;
        textView2.setText(this.team2.getWickets() + "");
        if (!this.match.isTestMatch) {
            TextView textView3 = this.tvteamovers;
            textView3.setText("(" + this.team2.getOversPlayed() + "." + this.team2.getOverballs() + "/" + overs + ")");
        } else {
            TextView textView4 = this.tvteamovers;
            textView4.setText("(" + this.team2.getOversPlayed() + "." + this.team2.getOverballs() + ")");
        }
        TextView textView5 = this.tvextras;
        textView5.setText("Extras:" + this.team2.getExtras());
        this.tvresult.setText(this.match.getResult());
        this.dbHelper.UpdateMatchResult(this.match.getMatchID(), this.match.getResult());
        FragmentBatsmanHistoryRecyclerView fragmentBatsmanHistoryRecyclerView = new FragmentBatsmanHistoryRecyclerView();
        Bundle bundle2 = new Bundle();
        bundle2.putSerializable("batsmen", this.team2.batsmans_list);
        bundle2.putSerializable(DBHelper.TABLE_MATCH, this.match);
        fragmentBatsmanHistoryRecyclerView.setArguments(bundle2);
        getSupportFragmentManager().beginTransaction().add((int) R.id.batsman_recycler_view_fragment, (Fragment) fragmentBatsmanHistoryRecyclerView).commit();
        Log.i("bowlers list size", this.team1.bowlers_list.size() + "");
        BowlerViewsUpdater.showBowlerRecyclerViewFragment(getSupportFragmentManager(), this.team1.bowlers_list, (Dialog_Custom_ListView_Adapter.LinearLayoutClickLister) null, this.team2.overs_list, this.match);
    }

    private void showChooseManOfTheMatchDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.choose_mom_dialog_layout);
        dialog.setCancelable(false);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = -1;
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_2;
        dialog.show();
        ListView listView = (ListView) dialog.findViewById(R.id.mom_list);
        final ArrayList<ManOfTheMatchModel> allCandidates = ManOfTheMatch.getAllCandidates(this.match.getWinningTeam());
        ArrayList<String> stringsForMOMCandidates = ManOfTheMatchModel.getStringsForMOMCandidates(allCandidates);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, 17367043, stringsForMOMCandidates);
        if (stringsForMOMCandidates.size() == 0) {
            MyToast.showLongToast(this, "No player to be selected as Man of the Match");
            dialog.dismiss();
            return;
        }
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ManOfTheMatchModel manOfTheMatchModel = (ManOfTheMatchModel) allCandidates.get(i);
                manOfTheMatchModel.setMatchId(Match_Result_Activity.this.match.getMatchID());
                manOfTheMatchModel.setTeamId(Match_Result_Activity.this.match.getWinningTeam().getTeamID());
                Match_Result_Activity.this.match.setManOfTheMatchModel(manOfTheMatchModel);
                Match_Result_Activity.this.dbHelper.insertManOfTheMatch(manOfTheMatchModel);
                new ManOfTheMatchDialogFragment(Match_Result_Activity.this, Match_Result_Activity.this.match).show(Match_Result_Activity.this.getFragmentManager(), (String) null);
                MyToast.showToast(Match_Result_Activity.this, "Man of the Match selected successfully");
                dialog.dismiss();
            }
        });
    }

    public void onBackPressed() {
        final AlertDialog create = new AlertDialog.Builder(this).setTitle(getResources().getString(R.string.exit)).setMessage(getResources().getString(R.string.exit_message)).setPositiveButton("Yes", (DialogInterface.OnClickListener) null).setNegativeButton("No", (DialogInterface.OnClickListener) null).create();
        create.setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                Button button = create.getButton(-1);
                Button button2 = create.getButton(-2);
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Match_Result_Activity.this.finishAffinity();
                        System.exit(0);
                    }
                });
                button2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                    }
                });
            }
        });
        create.show();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.bstartnewmatch) {
            Over.balls = 0;
            startActivity(new Intent(this, FirstPage.class));
        } else if (id != R.id.bviewdetails) {
            if (id == R.id.bfinish) {
                final AlertDialog create = new AlertDialog.Builder(this).setTitle("Rate Our App").setMessage("Please provide your feedback so that we could improve the app.").setPositiveButton("Rate", (DialogInterface.OnClickListener) null).setNegativeButton("Exit", (DialogInterface.OnClickListener) null).create();
                create.setOnShowListener(new DialogInterface.OnShowListener() {
                    public void onShow(DialogInterface dialogInterface) {
                        Button button = create.getButton(-1);
                        Button button2 = create.getButton(-2);
                        button.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + Match_Result_Activity.this.getApplicationContext().getPackageName()));
                                intent.addFlags(1275592704);
                                try {
                                    Match_Result_Activity.this.startActivity(intent);
                                } catch (ActivityNotFoundException unused) {
                                    Match_Result_Activity match_Result_Activity = Match_Result_Activity.this;
                                    match_Result_Activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + Match_Result_Activity.this.getApplicationContext().getPackageName())));
                                }
                            }
                        });
                        button2.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                Match_Result_Activity.this.finishAffinity();
                                System.exit(0);
                            }
                        });
                    }
                });
                create.show();
            }
        } else if (!this.mInterstitialAd.isLoaded() || SharedPrefsHelper.isPro(this)) {
            Intent intent = new Intent(this, Previous_Match_Result.class);
            intent.putExtra(DBHelper.TABLE_MATCH, this.match);
            intent.putExtra("code", "thismatch");
            startActivityForResult(intent, 0);
        } else {
            this.mInterstitialAd.show();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 0 && i2 == 1) {
            startActivity(new Intent(this, FirstPage.class));
        }
    }
}
