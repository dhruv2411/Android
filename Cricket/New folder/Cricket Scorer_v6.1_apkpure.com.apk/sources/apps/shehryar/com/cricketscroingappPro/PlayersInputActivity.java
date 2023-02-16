package apps.shehryar.com.cricketscroingappPro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman;
import apps.shehryar.com.cricketscroingappPro.Bowler.Bowler;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.SuggestionDialog;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.AdsLoader;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.Constants;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.SharedPrefsHelper;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.UtilityFunctions;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.itextpdf.text.pdf.PdfBoolean;
import java.util.ArrayList;

public class PlayersInputActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {
    public static ArrayList<String> oppPlayers;
    public static ArrayList<String> yourplayers;
    AutoCompleteTextView bat1;
    AutoCompleteTextView bat2;
    private Button bnext;
    Bowler bowler;
    private Batsman[] currentBatsmans;
    private DBHelper dbHelper;
    AutoCompleteTextView edBowler;
    private int index;
    private InterstitialAd mInterstitialAd;
    Match match;
    boolean matchContinued;
    String overs;
    PlayersLoader playersLoader;
    boolean resumeMatch;
    ArrayList<Team> teams;
    TextView tvTeamBatsmen;
    TextView tvTeamBowler;

    /* access modifiers changed from: protected */
    @RequiresApi(api = 21)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_players_input);
        this.mInterstitialAd = AdsLoader.initializeInterstitialAd(this, this.mInterstitialAd, new AdListener() {
            public void onAdClosed() {
                super.onAdClosed();
                PlayersInputActivity.this.startnextActivityForResumeMatch();
            }
        }, Constants.resumeMatchInterstitalAdId);
        this.resumeMatch = getIntent().getBooleanExtra("resumeMatch", false);
        this.matchContinued = getIntent().getBooleanExtra("resume_check", false);
        this.bat1 = (AutoCompleteTextView) findViewById(R.id.edinputbat1);
        this.bat2 = (AutoCompleteTextView) findViewById(R.id.edinputbat2);
        this.edBowler = (AutoCompleteTextView) findViewById(R.id.edinputbowler);
        this.tvTeamBatsmen = (TextView) findViewById(R.id.tv_team_batsmen);
        this.tvTeamBowler = (TextView) findViewById(R.id.tv_team_bowlers);
        this.bat1.setOnClickListener(this);
        this.bat2.setOnClickListener(this);
        this.edBowler.setOnClickListener(this);
        this.bat1.setOnFocusChangeListener(this);
        this.bat2.setOnFocusChangeListener(this);
        this.edBowler.setOnFocusChangeListener(this);
        if (this.resumeMatch) {
            this.currentBatsmans = new Batsman[2];
            this.dbHelper = new DBHelper(this);
            this.playersLoader = new PlayersLoader(this);
            this.bnext = (Button) findViewById(R.id.bplayersinputnext);
            Intent intent = getIntent();
            this.bnext.setText("Resume Match");
            this.match = (Match) intent.getSerializableExtra(DBHelper.TABLE_MATCH);
            if (this.match.getResumeMatch() != null) {
                this.match.setInnings(this.match.getResumeMatch().getInnings());
            }
            this.index = intent.getIntExtra("index", 0);
            this.teams = (ArrayList) getIntent().getSerializableExtra("teams");
            if (!this.match.firstInningsEnd && !this.match.secondInningsEnd && !this.match.thirdInningsEnd) {
                getNotOutBatsmen(this.match.getBattingTeam().getBatsmans_list());
                this.bowler = getBowler(this.match);
            }
            if (this.currentBatsmans[0] == null) {
                this.bat1.setEnabled(true);
                this.bat1.setEnabled(true);
                this.bat1.setHint("Batsman 1 Name");
            } else if (this.currentBatsmans[0].getName() != null) {
                this.bat1.setText(this.currentBatsmans[0].getName());
                this.bat1.setEnabled(false);
                this.bat1.setFocusable(false);
            }
            if (this.currentBatsmans[1] == null) {
                this.bat2.setEnabled(true);
                this.bat2.setEnabled(true);
                this.bat2.setHint("Batsman 2 Name");
            } else if (this.currentBatsmans[1].getName() != null) {
                this.bat2.setText(this.currentBatsmans[1].getName());
                this.bat2.setEnabled(false);
                this.bat2.setFocusable(false);
            }
            if (this.bowler == null || this.bowler.getName() == null || this.bowler.getBalls() <= 0) {
                this.edBowler.setEnabled(true);
                this.edBowler.setEnabled(true);
                this.edBowler.setHint("Bowler Name");
            } else {
                this.edBowler.setText(this.bowler.getName());
                this.edBowler.setEnabled(false);
                this.edBowler.setFocusable(false);
            }
            getSupportActionBar().setTitle((CharSequence) "Players Names");
            requestNewInterstitial();
            if (this.bat1.getText().toString().isEmpty() || this.bat1.getText().toString().equals("Batsman 1 Name")) {
                this.bat1.setEnabled(true);
                this.bat1.setFocusable(true);
            }
            if (this.bat2.getText().toString().isEmpty() || this.bat2.getText().toString().equals("Batsman 1 Name")) {
                this.bat2.setEnabled(true);
                this.bat2.setFocusable(true);
            }
            if (this.edBowler.getText().toString().isEmpty() || this.edBowler.getText().toString().equals("Batsman 1 Name")) {
                this.edBowler.setEnabled(true);
                this.edBowler.setFocusable(true);
            }
            if (this.match.firstInningsEnd || this.match.secondInningsEnd || this.match.thirdInningsEnd) {
                this.match.switchInnings();
            }
        } else {
            this.playersLoader = new PlayersLoader(this);
            Intent intent2 = getIntent();
            this.match = (Match) intent2.getSerializableExtra(DBHelper.TABLE_MATCH);
            this.teams = (ArrayList) intent2.getSerializableExtra("teams");
            yourplayers = new ArrayList<>();
            oppPlayers = new ArrayList<>();
            getSupportActionBar().setTitle((CharSequence) "Enter your Players Names");
        }
        if (!this.matchContinued) {
            this.match.getTeam1().setAllPlayerNames(this.playersLoader.getSpecificTeamPlayers(this.teams, this.match.getYourteam()));
            this.match.getTeam2().setAllPlayerNames(this.playersLoader.getSpecificTeamPlayers(this.teams, this.match.getOpponent()));
            if (!this.match.followedOn) {
                this.match.getTeam3().setAllPlayerNames(this.match.getTeam1().getAllPlayerNames());
                this.match.getTeam4().setAllPlayerNames(this.match.getTeam2().getAllPlayerNames());
            } else {
                this.match.getTeam3().setAllPlayerNames(this.match.getTeam2().getAllPlayerNames());
                this.match.getTeam4().setAllPlayerNames(this.match.getTeam1().getAllPlayerNames());
            }
        }
        TextView textView = this.tvTeamBatsmen;
        textView.setText(this.match.getBattingTeam().getName() + " Batsmen");
        TextView textView2 = this.tvTeamBowler;
        textView2.setText(this.match.getBowlingTeam().getName() + " Bowler");
    }

    private Bowler getBowler(Match match2) {
        Bowler bowler2 = match2.getBowlingTeam().getBowlers_list().size() > 0 ? match2.getBowlingTeam().getBowlers_list().get(match2.getBowlingTeam().getBowlers_list().size() - 1) : null;
        if (bowler2 == null) {
            return null;
        }
        if (((bowler2.getBalls() == 0 || bowler2.getBalls() == match2.getPerOverBalls()) && match2.getBattingTeam().getOversPlayed() == match2.getBattingTeam().getOvers_list().size()) || bowler2.getBalls() >= match2.getPerOverBalls()) {
            return null;
        }
        return bowler2;
    }

    private void setSuggestionsOnEditTexts() {
        try {
            if ((this.match.secondInnings && oppPlayers.size() > 0) || (this.match.firstInnings && yourplayers.size() > 0)) {
                showSuggestionsInADialog(this.bat1, 1);
                showSuggestionsInADialog(this.bat2, 1);
            }
            if ((this.match.secondInnings && yourplayers.size() > 0) || (this.match.firstInnings && oppPlayers.size() > 0)) {
                showSuggestionsInADialog(this.edBowler, 2);
            }
            if (this.match.isTestMatch) {
                if ((this.match.thirdInnings && yourplayers.size() > 0 && !this.match.followedOn) || ((this.match.thirdInnings && oppPlayers.size() > 0 && this.match.followedOn) || ((this.match.fourthInnings && yourplayers.size() > 0 && this.match.followedOn) || (this.match.fourthInnings && oppPlayers.size() > 0 && !this.match.followedOn)))) {
                    showSuggestionsInADialog(this.bat1, 1);
                    showSuggestionsInADialog(this.bat2, 1);
                }
                if ((this.match.thirdInnings && oppPlayers.size() > 0 && !this.match.followedOn) || ((this.match.thirdInnings && yourplayers.size() > 0 && this.match.followedOn) || ((this.match.fourthInnings && oppPlayers.size() > 0 && this.match.followedOn) || (this.match.fourthInnings && yourplayers.size() > 0 && !this.match.followedOn)))) {
                    showSuggestionsInADialog(this.edBowler, 2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void requestNewInterstitial() {
        new AdRequest.Builder().build();
    }

    /* access modifiers changed from: package-private */
    public void getNotOutBatsmen(ArrayList<Batsman> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        int i = 0;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (arrayList.get(i2).isOut().equals(PdfBoolean.FALSE)) {
                arrayList2.add(arrayList.get(i2));
            }
        }
        while (i < arrayList2.size() && i <= 1) {
            this.currentBatsmans[i] = (Batsman) arrayList2.get(i);
            i++;
        }
    }

    public void buttonClick(View view) {
        if (this.bat1.getText().toString().isEmpty() || this.bat2.getText().toString().isEmpty() || this.edBowler.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please fill all the fields to continue", 0).show();
        } else if (this.bat1.getText().toString().equals(this.bat2.getText().toString())) {
            Toast.makeText(this, "Both Batsmen Names cannot be same", 0).show();
        } else if (!this.resumeMatch) {
            startnextActivity();
        } else if (!this.mInterstitialAd.isLoaded() || SharedPrefsHelper.isPro(this)) {
            startnextActivityForResumeMatch();
        } else {
            this.mInterstitialAd.show();
        }
    }

    /* access modifiers changed from: package-private */
    public void startnextActivity() {
        Intent intent = new Intent(this, MainActivity_Test.class);
        intent.putExtra("bat1name", this.bat1.getText().toString());
        intent.putExtra("bat2name", this.bat2.getText().toString());
        intent.putExtra("bowlername", this.edBowler.getText().toString());
        intent.putExtra(DBHelper.TABLE_MATCH, this.match);
        intent.putExtra("yourPlayers", yourplayers);
        intent.putExtra("oppPlayers", oppPlayers);
        intent.setFlags(PagedChannelRandomAccessSource.DEFAULT_TOTAL_BUFSIZE);
        startActivity(intent);
    }

    /* access modifiers changed from: package-private */
    public void startnextActivityForResumeMatch() {
        Intent intent = new Intent(this, MainActivity_Test.class);
        intent.putExtra(DBHelper.TABLE_MATCH, this.match);
        intent.putExtra("index", this.index);
        if (this.currentBatsmans[0] == null) {
            this.currentBatsmans[0] = new Batsman();
            this.currentBatsmans[0].setName(this.bat1.getText().toString());
            this.currentBatsmans[0].setBatsmanID(-1);
        }
        if (this.currentBatsmans[1] == null) {
            this.currentBatsmans[1] = new Batsman();
            this.currentBatsmans[1].setName(this.bat2.getText().toString());
            this.currentBatsmans[1].setBatsmanID(-1);
        }
        intent.putExtra("bowler", this.edBowler.getText().toString());
        intent.putExtra("bat1", this.currentBatsmans[0]);
        intent.putExtra("bat2", this.currentBatsmans[1]);
        intent.putExtra("resumeMatch", true);
        startActivity(intent);
    }

    private Bowler checkBowler(String str, Team team) {
        Bowler bowler2;
        String str2 = str;
        Team team2 = team;
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= team2.bowlers_list.size()) {
                bowler2 = null;
                break;
            } else if (team2.bowlers_list.get(i).getName().equals(str2)) {
                bowler2 = team2.bowlers_list.get(i);
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
        bowler3.setBowlerID(this.dbHelper.insertPlayer(bowler3.getName(), team.getTeamID(), "edBowler"));
        this.dbHelper.insertBowlerScore(team.getTeamID(), this.match.getMatchID(), bowler3.getBowlerID(), 0, 0, 0, 0, 0);
        this.match.getBowlingTeam().bowlers_list.add(bowler3);
        return bowler3;
    }

    /* access modifiers changed from: private */
    public void openSuggestionsDialog(AutoCompleteTextView autoCompleteTextView, ArrayList<String> arrayList, SuggestionDialog.SuggestionsClickCallBack suggestionsClickCallBack) {
        new SuggestionDialog(this, autoCompleteTextView, arrayList, suggestionsClickCallBack).show(getFragmentManager(), "suggestion Dialog");
    }

    @RequiresApi(api = 21)
    private void showSuggestionsInADialog(final AutoCompleteTextView autoCompleteTextView, final int i) {
        autoCompleteTextView.setShowSoftInputOnFocus(false);
        final AnonymousClass2 r0 = new SuggestionDialog.SuggestionsClickCallBack() {
            public void onSuggestionClicked(EditText editText, String str) {
                editText.setText(str);
            }

            public void onTypeYourOwnClick(EditText editText) {
                editText.setText("");
                editText.setFocusable(true);
                editText.requestFocus();
                PlayersInputActivity.this.getWindow().setSoftInputMode(5);
            }
        };
        autoCompleteTextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PlayersInputActivity.this.getWindow().setSoftInputMode(3);
                if (i == 1) {
                    if (PlayersInputActivity.this.match.secondInnings || ((PlayersInputActivity.this.match.thirdInnings && PlayersInputActivity.this.match.followedOn) || (PlayersInputActivity.this.match.fourthInnings && !PlayersInputActivity.this.match.followedOn))) {
                        PlayersInputActivity.this.openSuggestionsDialog(autoCompleteTextView, PlayersInputActivity.oppPlayers, r0);
                    } else {
                        PlayersInputActivity.this.openSuggestionsDialog(autoCompleteTextView, PlayersInputActivity.yourplayers, r0);
                    }
                } else if (i != 2) {
                } else {
                    if (PlayersInputActivity.this.match.secondInnings || ((PlayersInputActivity.this.match.thirdInnings && PlayersInputActivity.this.match.followedOn) || (PlayersInputActivity.this.match.fourthInnings && !PlayersInputActivity.this.match.followedOn))) {
                        PlayersInputActivity.this.openSuggestionsDialog(autoCompleteTextView, PlayersInputActivity.yourplayers, r0);
                    } else {
                        PlayersInputActivity.this.openSuggestionsDialog(autoCompleteTextView, PlayersInputActivity.oppPlayers, r0);
                    }
                }
            }
        });
        autoCompleteTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    PlayersInputActivity.this.getWindow().setSoftInputMode(3);
                    if (i == 1) {
                        if (PlayersInputActivity.this.match.secondInnings || ((PlayersInputActivity.this.match.thirdInnings && PlayersInputActivity.this.match.followedOn) || (PlayersInputActivity.this.match.fourthInnings && !PlayersInputActivity.this.match.followedOn))) {
                            PlayersInputActivity.this.openSuggestionsDialog(autoCompleteTextView, PlayersInputActivity.oppPlayers, r0);
                        } else {
                            PlayersInputActivity.this.openSuggestionsDialog(autoCompleteTextView, PlayersInputActivity.yourplayers, r0);
                        }
                    } else if (i != 2) {
                    } else {
                        if (PlayersInputActivity.this.match.secondInnings || ((PlayersInputActivity.this.match.thirdInnings && PlayersInputActivity.this.match.followedOn) || (PlayersInputActivity.this.match.fourthInnings && !PlayersInputActivity.this.match.followedOn))) {
                            PlayersInputActivity.this.openSuggestionsDialog(autoCompleteTextView, PlayersInputActivity.yourplayers, r0);
                        } else {
                            PlayersInputActivity.this.openSuggestionsDialog(autoCompleteTextView, PlayersInputActivity.oppPlayers, r0);
                        }
                    }
                }
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edinputbat1 /*2131755372*/:
                UtilityFunctions.openSuggestionsDialog(this, this.bat1, this.match.getBattingTeam().getAllPlayerNames(), false, false);
                return;
            case R.id.edinputbat2 /*2131755373*/:
                UtilityFunctions.openSuggestionsDialog(this, this.bat2, this.match.getBattingTeam().getAllPlayerNames(), false, false);
                return;
            case R.id.edinputbowler /*2131755375*/:
                UtilityFunctions.openSuggestionsDialog(this, this.edBowler, this.match.getBowlingTeam().getAllPlayerNames(), false, false);
                return;
            default:
                return;
        }
    }

    public void onFocusChange(View view, boolean z) {
        switch (view.getId()) {
            case R.id.edinputbat1 /*2131755372*/:
                if (z) {
                    UtilityFunctions.openSuggestionsDialog(this, this.bat1, this.match.getBattingTeam().getAllPlayerNames(), false, false);
                    return;
                }
                return;
            case R.id.edinputbat2 /*2131755373*/:
                if (z) {
                    UtilityFunctions.openSuggestionsDialog(this, this.bat2, this.match.getBattingTeam().getAllPlayerNames(), false, false);
                    return;
                }
                return;
            case R.id.edinputbowler /*2131755375*/:
                if (z) {
                    UtilityFunctions.openSuggestionsDialog(this, this.edBowler, this.match.getBowlingTeam().getAllPlayerNames(), false, false);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
