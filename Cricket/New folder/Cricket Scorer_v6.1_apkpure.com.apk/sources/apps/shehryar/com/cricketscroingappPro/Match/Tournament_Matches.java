package apps.shehryar.com.cricketscroingappPro.Match;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman;
import apps.shehryar.com.cricketscroingappPro.Bowler.Bowler;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.ExpandedListView;
import apps.shehryar.com.cricketscroingappPro.MyToast;
import apps.shehryar.com.cricketscroingappPro.PDFGenerator;
import apps.shehryar.com.cricketscroingappPro.PermissionAsker;
import apps.shehryar.com.cricketscroingappPro.PlayersLoader;
import apps.shehryar.com.cricketscroingappPro.Ranking.DialogBatsmanRanking;
import apps.shehryar.com.cricketscroingappPro.Ranking.DialogBowlerRanking;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import apps.shehryar.com.cricketscroingappPro.Tournament.FragmentPointsTable;
import apps.shehryar.com.cricketscroingappPro.Tournament.Tournament;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.AdsLoader;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.Constants;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.CustomDiealogBuilder;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.ImageSharer;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.PdfSharer;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.SharedPrefsHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import util.IabHelper;
import util.IabResult;
import util.Purchase;

public class Tournament_Matches extends AppCompatActivity {
    ArrayList<Team> allTeamsStats;
    ArrayList<Team> all_team_players;
    ArrayList<Batsman> batsmanstats;
    ExpandedListView batsmenListView;
    ArrayList<Bowler> bowlersStats;
    DBHelper dbHelper;
    int index;
    Intent intent;
    private ProgressDialog loading;
    /* access modifiers changed from: private */
    public IabHelper mHelper;
    /* access modifiers changed from: private */
    public IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener;
    ArrayList<Match> matches;
    TextView noMatches;
    PDFGenerator pdfGenerator;
    ArrayList<String> playerNames;
    ArrayList<String> playernames;
    PlayersLoader playersLoader;
    RecyclerView recyclerView;
    TabLayout tabLayout;
    ArrayList<Team> team1scores;
    ArrayList<Team> team1stats;
    ArrayList<Team> team2scores;
    ArrayList<Team> team2stats;
    ArrayList<Team> teamNames;
    boolean thread1;
    boolean thread2;
    boolean thread3;
    boolean thread4;
    boolean thread5 = false;
    ArrayList<Bowler> topbowlers;
    ArrayList<Batsman> topscorers;
    Tournament tournament;
    ArrayList<Team> tournamentTeams;
    String tournament_name;
    ViewPager viewPager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_tournament__matches);
        Log.i("Activity Method", "onCreate Called");
        AdsLoader.loadBannerAd(this, this, Constants.tournamentActAdId, R.id.tournament_ad);
        this.mHelper = new IabHelper(this, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7u5kRGfbQ2KgIHfyHPw4TRR1KPmNG+PEfaJuOe6ohNhqjCy1iQyfhgvRis3AY6Bs8osPDFxI76G2XzAycB9/v6G0PCVEG4ITVkZCnVtTOx2r8p2f24LW3OQNcZrTFCF0mW7cSA2XnWEe0DYCByC0Z5TG+95FhL5rO1TJECEMum3p54l2dYnOgmJTOrnWp0ry6Jv3xC+26T0D07PFxGQoeUxOGcuL0a+rvi4IyWrwUJCX66jVh5EYnNdZ7trtAjtLEjqyAWAzcc3yUbMPUZtw1Do0S+HpKtyyeruZzpJgCl0Hpgdrz4P+7x6dCLI6QkFkrhngqBQNnGIQcev0sB6UewIDAQAB");
        this.loading = new ProgressDialog(this);
        this.loading.setCancelable(true);
        this.loading.setMessage("Loading Tournament. Please Wait.");
        this.loading.setProgressStyle(0);
        this.loading.show();
        this.mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
            public void onIabPurchaseFinished(IabResult iabResult, Purchase purchase) {
                if (iabResult.isFailure()) {
                    Log.d("purchaising", "Error purchasing: " + iabResult);
                    MyToast.showToast(Tournament_Matches.this, "Billing unsuccessfull");
                } else if (purchase.getSku().equals("all_pro_features")) {
                    MyToast.showToast(Tournament_Matches.this, "App is upgraded to PRO");
                    SharedPrefsHelper.insertProFeatures(Tournament_Matches.this, true);
                }
            }
        };
        this.tournament_name = getIntent().getStringExtra(FragmentPointsTable.TOURNAMENT_NAME);
        this.index = getIntent().getIntExtra("index", 0);
        this.team1stats = new ArrayList<>();
        this.team2stats = new ArrayList<>();
        this.topscorers = new ArrayList<>();
        this.batsmanstats = new ArrayList<>();
        this.bowlersStats = new ArrayList<>();
        this.topbowlers = new ArrayList<>();
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle((CharSequence) this.tournament_name + " ");
        this.all_team_players = (ArrayList) getIntent().getSerializableExtra("all_team_players");
        this.dbHelper = new DBHelper(this);
        this.playersLoader = new PlayersLoader(this);
        startLoadingData();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tournament_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId != R.id.share_image) {
            switch (itemId) {
                case R.id.export_tournament_to_pdf /*2131755792*/:
                    exportTournamentToPDF();
                    return true;
                case R.id.share_tournament_pdf /*2131755793*/:
                    if (!SharedPrefsHelper.isPro(this)) {
                        showUpgradeToProDialog();
                        return true;
                    } else if (PermissionAsker.checkAPIVersion()) {
                        if (PermissionAsker.checkIfAlreadyhavePermission(this)) {
                            if (this.matches == null || this.matches.size() <= 0) {
                                return true;
                            }
                            PDFGenerator pDFGenerator = this.pdfGenerator;
                            PdfSharer.shareTournamentPDF(pDFGenerator, this, "Cricket Scorer/Tournaments/" + this.matches.get(0).getTournament() + ".pdf");
                            return true;
                        } else if (this.matches == null || this.matches.size() <= 0) {
                            return true;
                        } else {
                            PermissionAsker.requestForSpecificPermission(105, this);
                            return true;
                        }
                    } else if (this.matches == null || this.matches.size() <= 0) {
                        return true;
                    } else {
                        PDFGenerator pDFGenerator2 = this.pdfGenerator;
                        PdfSharer.shareTournamentPDF(pDFGenerator2, this, "Cricket Scorer/Tournaments/" + this.matches.get(0).getTournament() + ".pdf");
                        return true;
                    }
                case R.id.bat_of_the_tournament /*2131755794*/:
                    if (this.topscorers == null || this.topscorers.size() <= 0) {
                        return true;
                    }
                    DialogBatsmanRanking dialogBatsmanRanking = new DialogBatsmanRanking();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("batsman", this.topscorers.get(0));
                    bundle.putString("tname", this.tournament_name);
                    dialogBatsmanRanking.setArguments(bundle);
                    dialogBatsmanRanking.show(getFragmentManager(), "batsman ranking dialog");
                    return true;
                case R.id.bowl_of_the_tournament /*2131755795*/:
                    if (this.topbowlers == null || this.topbowlers.size() <= 0) {
                        return true;
                    }
                    DialogBowlerRanking dialogBowlerRanking = new DialogBowlerRanking();
                    Bundle bundle2 = new Bundle();
                    bundle2.putSerializable("bowler", this.topbowlers.get(0));
                    bundle2.putString("tname", this.tournament_name);
                    dialogBowlerRanking.setArguments(bundle2);
                    dialogBowlerRanking.show(getFragmentManager(), "bowler ranking dialog");
                    return true;
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

    private void exportTournamentToPDF() {
        try {
            if (this.pdfGenerator == null) {
                MyToast.showToast(this, "Error. Please wait for a few seconds and try again.");
            } else if (PermissionAsker.checkAPIVersion()) {
                if (!PermissionAsker.checkIfAlreadyhavePermission(this)) {
                    PermissionAsker.requestForSpecificPermission(103, this);
                } else if (this.matches.size() > 0) {
                    this.pdfGenerator.generateTournamentMatchesPDF();
                }
            } else if (this.matches.size() > 0) {
                this.pdfGenerator.generateTournamentMatchesPDF();
            }
        } catch (Exception unused) {
        }
    }

    private void showUpgradeToProDialog() {
        CustomDiealogBuilder.showUpgradeDialog(this, new View.OnClickListener() {
            public void onClick(View view) {
                if (!Tournament_Matches.this.mHelper.isSetupDone()) {
                    Tournament_Matches.this.mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
                        public void onIabSetupFinished(IabResult iabResult) {
                            if (!iabResult.isSuccess()) {
                                Log.d("IAB PROBLEM", "Problem setting up In-app Billing: " + iabResult);
                                MyToast.showToast(Tournament_Matches.this, "Something went wrong. Please make sure that you have an internet connection and working Google Account set up on this phone and try again.");
                                return;
                            }
                            try {
                                Tournament_Matches.this.mHelper.launchPurchaseFlow(Tournament_Matches.this, "all_pro_features", 10001, Tournament_Matches.this.mPurchaseFinishedListener, "bGoa+V7g/yqDXvKRqq+JTFn4uQZbPiQJo4pf9RzJ");
                            } catch (IabHelper.IabAsyncInProgressException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    return;
                }
                try {
                    Tournament_Matches.this.mHelper.launchPurchaseFlow(Tournament_Matches.this, "all_pro_features", 10001, Tournament_Matches.this.mPurchaseFinishedListener, "bGoa+V7g/yqDXvKRqq+JTFn4uQZbPiQJo4pf9RzJ");
                } catch (IabHelper.IabAsyncInProgressException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        Log.i("Activity Method", "onPause Called");
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        Log.i("Activity Method", "onStop Called");
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Log.i("Activity Method", "onResume Called");
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        Log.i("Activity Method", "onDestroy Called");
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        Log.i("Activity Method", "onStart Called");
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        super.onRestart();
        Log.i("Activity Method", "onRestart Called");
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent2) {
        super.onActivityResult(i, i2, intent2);
        this.topscorers = new ArrayList<>();
        this.topbowlers = new ArrayList<>();
        this.batsmanstats = new ArrayList<>();
        this.bowlersStats = new ArrayList<>();
        if (i2 == 1 && i == 0) {
            startLoadingData();
        }
        if (i == 10001 && i2 == -1) {
            SharedPrefsHelper.insertProFeatures(this, true);
            MyToast.showToast(this, "Congratulations on being a PRO Cricket Scorer");
            if (!PermissionAsker.checkAPIVersion()) {
                return;
            }
            if (PermissionAsker.checkIfAlreadyhavePermission(this)) {
                ImageSharer.shareImage(this);
            } else {
                PermissionAsker.requestForSpecificPermission(104, this);
            }
        } else if (i == 10001 && i2 == 0) {
            SharedPrefsHelper.insertProFeatures(this, false);
        }
    }

    class TMatchesLoader extends AsyncTask {
        TMatchesLoader() {
        }

        /* access modifiers changed from: protected */
        public Object doInBackground(Object[] objArr) {
            Tournament_Matches.this.matches = Tournament_Matches.this.dbHelper.getSpecificTournamentMatches(Tournament_Matches.this.tournament_name);
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Object obj) {
            super.onPostExecute(obj);
            Tournament_Matches.this.thread1 = true;
            if (Tournament_Matches.this.matches.size() > 0) {
                Tournament_Matches.this.pdfGenerator = new PDFGenerator((Context) Tournament_Matches.this, Tournament_Matches.this.matches);
            }
            try {
                Tournament_Matches.this.setAdapter();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    class TTEamsLoader extends AsyncTask {
        TTEamsLoader() {
        }

        /* access modifiers changed from: protected */
        public Object doInBackground(Object[] objArr) {
            Tournament_Matches.this.tournamentTeams = Tournament_Matches.this.dbHelper.getTournamentTeams(Tournament_Matches.this.tournament_name);
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Object obj) {
            super.onPostExecute(obj);
            if (Tournament_Matches.this.tournamentTeams != null) {
                new TTopbowlersLoader().execute(new Object[0]);
                new TTopScorerLoader().execute(new Object[0]);
            }
        }
    }

    class TTopScorerLoader extends AsyncTask {
        TTopScorerLoader() {
        }

        /* access modifiers changed from: protected */
        public Object doInBackground(Object[] objArr) {
            Tournament_Matches.this.topscorers = Tournament_Matches.this.dbHelper.getTournamentTopScorers(Tournament_Matches.this.tournament_name, Tournament_Matches.this.tournamentTeams);
            int i = 0;
            while (i < Tournament_Matches.this.topscorers.size()) {
                i++;
                Tournament_Matches.this.topscorers.get(i).setRanking(i);
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Object obj) {
            super.onPostExecute(obj);
            Tournament_Matches.this.thread2 = true;
            if (Tournament_Matches.this.thread2 && Tournament_Matches.this.thread3) {
                new LoadTournamentStats().execute(new Object[0]);
            }
            if (Tournament_Matches.this.tournament != null && Tournament_Matches.this.topscorers.size() > 0) {
                Tournament_Matches.this.tournament.setTopScorer(Tournament_Matches.this.topscorers.get(0));
            }
            try {
                Tournament_Matches.this.setAdapter();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    class TTopbowlersLoader extends AsyncTask {
        TTopbowlersLoader() {
        }

        /* access modifiers changed from: protected */
        public Object doInBackground(Object[] objArr) {
            Tournament_Matches.this.topbowlers = Tournament_Matches.this.dbHelper.getTournamentTopBowlers(Tournament_Matches.this.tournament_name, Tournament_Matches.this.tournamentTeams);
            int i = 0;
            while (i < Tournament_Matches.this.topbowlers.size()) {
                i++;
                Tournament_Matches.this.topbowlers.get(i).setRanking(i);
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Object obj) {
            super.onPostExecute(obj);
            Tournament_Matches.this.thread3 = true;
            if (Tournament_Matches.this.thread2 && Tournament_Matches.this.thread3) {
                new LoadTournamentStats().execute(new Object[0]);
            }
            try {
                Tournament_Matches.this.setAdapter();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    class TTeamStatsLoader extends AsyncTask {
        /* access modifiers changed from: protected */
        public Object doInBackground(Object[] objArr) {
            return null;
        }

        TTeamStatsLoader() {
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Object obj) {
            super.onPostExecute(obj);
            Tournament_Matches.this.thread4 = true;
            try {
                Tournament_Matches.this.setAdapter();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setAdapter() throws IllegalStateException {
        if (!this.thread1 || !this.thread2 || !this.thread3 || !this.thread4 || !this.thread5) {
            return;
        }
        this.loading.dismiss();
        findViewById(R.id.progressBar).setVisibility(8);
        this.viewPager = (ViewPager) findViewById(R.id.viewpager);
        this.viewPager.setOffscreenPageLimit(5);
        this.tabLayout = (TabLayout) findViewById(R.id.tablayout);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        String str = this.tournament_name;
        ArrayList<Match> arrayList = this.matches;
        ArrayList<Team> arrayList2 = this.teamNames;
        ArrayList<Team> arrayList3 = this.team1scores;
        ArrayList<Team> arrayList4 = this.team2scores;
        ArrayList<Team> arrayList5 = this.team1stats;
        ArrayList<Team> arrayList6 = this.team2stats;
        ArrayList<String> arrayList7 = this.playernames;
        ArrayList<Batsman> arrayList8 = this.topscorers;
        ArrayList<Bowler> arrayList9 = this.topbowlers;
        ArrayList<Team> arrayList10 = this.allTeamsStats;
        ArrayList<Team> arrayList11 = arrayList10;
        Tournament_ViewPager_Adapter tournament_ViewPager_Adapter = r0;
        Tournament_ViewPager_Adapter tournament_ViewPager_Adapter2 = new Tournament_ViewPager_Adapter(supportFragmentManager, this, this, str, arrayList, arrayList2, arrayList3, arrayList4, arrayList5, arrayList6, arrayList7, arrayList8, arrayList9, arrayList11, this.tournament);
        this.viewPager.setAdapter(tournament_ViewPager_Adapter);
        this.tabLayout.setupWithViewPager(this.viewPager);
        this.tabLayout.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.white));
        this.tabLayout.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.white));
    }

    /* access modifiers changed from: package-private */
    public void startLoadingData() {
        new TMatchesLoader().execute(new Object[0]);
        new TTEamsLoader().execute(new Object[0]);
        new TTeamStatsLoader().execute(new Object[0]);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        switch (i) {
            case 103:
                if (iArr[0] != 0) {
                    MyToast.showToast(this, "PDF not generated. Permission denied.");
                    break;
                } else {
                    exportTournamentToPDF();
                    break;
                }
            case 104:
                if (iArr[0] == 0) {
                    ImageSharer.shareImage(this);
                    return;
                } else {
                    MyToast.showToast(this, "Image not generated. Permission denied.");
                    return;
                }
        }
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    public class LoadTournamentStats extends AsyncTask {
        public LoadTournamentStats() {
        }

        /* access modifiers changed from: protected */
        public Object doInBackground(Object[] objArr) {
            ArrayList<Team> arrayList;
            Tournament_Matches.this.tournament = new Tournament();
            Tournament_Matches.this.tournament.setNoOfMatches(Tournament_Matches.this.matches.size());
            Iterator<Batsman> it = Tournament_Matches.this.topscorers.iterator();
            while (it.hasNext()) {
                Tournament_Matches.this.tournament.setTotalFours(it.next().getFours());
            }
            Iterator<Bowler> it2 = Tournament_Matches.this.topbowlers.iterator();
            while (it2.hasNext()) {
                Tournament_Matches.this.tournament.setTotalWickets(it2.next().getWickets());
            }
            if (Tournament_Matches.this.topscorers.size() > 0) {
                Tournament_Matches.this.tournament.setTopScorer(Tournament_Matches.this.topscorers.get(0));
            }
            if (Tournament_Matches.this.topbowlers.size() > 0) {
                Tournament_Matches.this.tournament.setTopBowler(Tournament_Matches.this.topbowlers.get(0));
            }
            Tournament_Matches.this.tournament.setMostFoursBatsman(Tournament_Matches.this.getMostFoursBatsman());
            Tournament_Matches.this.tournament.setMostSixesBatsman(Tournament_Matches.this.getMostSixesBatsman());
            if (Tournament_Matches.this.topbowlers.size() > 0) {
                Tournament_Matches.this.tournament.setMostWicketBowler(Tournament_Matches.this.topbowlers.get(0));
            }
            Tournament_Matches.this.tournament.setHighestScorerTeam(getTeamWithHighestScore());
            Tournament_Matches.this.tournament.setLowestScorerTeam(getTeamWithLowestScore());
            try {
                arrayList = Tournament_Matches.this.dbHelper.get_All_Teams_Stats(3, Tournament_Matches.this.tournament_name);
            } catch (Exception e) {
                e.printStackTrace();
                Tournament_Matches.this.allTeamsStats = new ArrayList<>();
                arrayList = null;
            }
            Tournament_Matches.this.tournament.setHighestChaseTeam(getHighestChaseTeam(arrayList));
            Tournament_Matches.this.tournament.setHighestDefendTeam(getHighestDefendedTeam(arrayList));
            Tournament_Matches.this.tournament.setTotalSixes(Tournament_Matches.this.dbHelper.getTotalSixesInTournament(Tournament_Matches.this.tournament_name));
            Tournament_Matches.this.tournament.setTotalFours(Tournament_Matches.this.dbHelper.getTotalFoursInTournament(Tournament_Matches.this.tournament_name));
            return null;
        }

        private Team getHighestChaseTeam(ArrayList<Team> arrayList) {
            ArrayList arrayList2 = new ArrayList();
            if (arrayList == null) {
                return null;
            }
            Iterator<Team> it = arrayList.iterator();
            while (it.hasNext()) {
                Team next = it.next();
                if (next.getHighestChased() != null) {
                    arrayList2.add(next);
                }
            }
            Collections.sort(arrayList2, new Comparator<Team>() {
                public int compare(Team team, Team team2) {
                    return team2.getHighestChased().getScore() - team.getHighestChased().getScore();
                }
            });
            if (arrayList2.size() > 0) {
                return (Team) arrayList2.get(0);
            }
            return null;
        }

        private Team getHighestDefendedTeam(ArrayList<Team> arrayList) {
            ArrayList arrayList2 = new ArrayList();
            if (arrayList == null) {
                return null;
            }
            Iterator<Team> it = arrayList.iterator();
            while (it.hasNext()) {
                Team next = it.next();
                if (next.getHighestDefended() != null) {
                    arrayList2.add(next);
                }
            }
            Collections.sort(arrayList2, new Comparator<Team>() {
                public int compare(Team team, Team team2) {
                    return team2.getHighestDefended().getScore() - team.getHighestDefended().getScore();
                }
            });
            if (arrayList2.size() > 0) {
                return (Team) arrayList2.get(0);
            }
            return null;
        }

        private Team getTeamWithHighestScore() {
            ArrayList arrayList = new ArrayList();
            Iterator<Team> it = Tournament_Matches.this.tournamentTeams.iterator();
            while (it.hasNext()) {
                arrayList.add(Tournament_Matches.this.dbHelper.getTeamStats(it.next().getTeamID(), (Match) null));
            }
            Collections.sort(arrayList, new Comparator<Team>() {
                public int compare(Team team, Team team2) {
                    return team2.getScore() - team.getScore();
                }
            });
            if (arrayList.size() > 0) {
                return (Team) arrayList.get(0);
            }
            return null;
        }

        private Team getTeamWithLowestScore() {
            ArrayList arrayList = new ArrayList();
            Iterator<Team> it = Tournament_Matches.this.tournamentTeams.iterator();
            while (it.hasNext()) {
                arrayList.add(Tournament_Matches.this.dbHelper.getTeamStats(it.next().getTeamID(), (Match) null));
            }
            Collections.sort(arrayList, new Comparator<Team>() {
                public int compare(Team team, Team team2) {
                    return team.getScore() - team2.getScore();
                }
            });
            if (arrayList.size() > 0) {
                return (Team) arrayList.get(0);
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Object obj) {
            super.onPostExecute(obj);
            Tournament_Matches.this.thread5 = true;
            try {
                Tournament_Matches.this.setAdapter();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: private */
    public Batsman getMostFoursBatsman() {
        ArrayList arrayList = new ArrayList(this.topscorers);
        Collections.sort(arrayList, new Comparator<Batsman>() {
            public int compare(Batsman batsman, Batsman batsman2) {
                return batsman2.getFours() - batsman.getFours();
            }
        });
        if (arrayList.size() > 0) {
            return (Batsman) arrayList.get(0);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public Batsman getMostSixesBatsman() {
        ArrayList arrayList = new ArrayList(this.topscorers);
        Collections.sort(arrayList, new Comparator<Batsman>() {
            public int compare(Batsman batsman, Batsman batsman2) {
                return batsman2.getSixs() - batsman.getSixs();
            }
        });
        if (arrayList.size() > 0) {
            return (Batsman) arrayList.get(0);
        }
        return null;
    }
}
