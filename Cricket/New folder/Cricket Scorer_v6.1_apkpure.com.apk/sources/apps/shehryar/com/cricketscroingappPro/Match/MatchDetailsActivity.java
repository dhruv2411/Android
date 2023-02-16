package apps.shehryar.com.cricketscroingappPro.Match;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Custom_Suggestions_Adapter;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.InputFilterMinMax;
import apps.shehryar.com.cricketscroingappPro.MyToast;
import apps.shehryar.com.cricketscroingappPro.PlayersInputActivity;
import apps.shehryar.com.cricketscroingappPro.StartSecondInningsDialog;
import apps.shehryar.com.cricketscroingappPro.SuggestionDialog;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.SharedPrefsHelper;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.UtilityFunctions;
import com.itextpdf.text.pdf.PdfBoolean;
import java.util.ArrayList;
import java.util.Calendar;

public class MatchDetailsActivity extends AppCompatActivity implements View.OnFocusChangeListener, View.OnClickListener, StartSecondInningsDialog.StartSecondInningsDialogListener {
    Custom_Suggestions_Adapter adapter;
    Custom_Suggestions_Adapter adapter1;
    DatePicker datePicker;
    DBHelper dbHelper;
    AutoCompleteTextView edMatchType;
    AutoCompleteTextView edTossWonBy;
    AutoCompleteTextView edopteam;
    EditText edovers;
    AutoCompleteTextView edtournament;
    AutoCompleteTextView edtournamentStage;
    AutoCompleteTextView edvenue;
    AutoCompleteTextView edyourteam;
    int focusedittext;
    Custom_Suggestions_Adapter matchTypeAdapter;
    ArrayList<String> matchTypes;
    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            Button button = (Button) view;
            switch (motionEvent.getAction()) {
                case 0:
                    button.setTextColor(ContextCompat.getColor(MatchDetailsActivity.this, R.color.white));
                    return false;
                case 1:
                    button.setTextColor(ContextCompat.getColor(MatchDetailsActivity.this, R.color.tealdark));
                    return false;
                default:
                    return false;
            }
        }
    };
    ArrayList<String> playingTeams;
    LinearLayout radioButtonsLayout;
    RadioButton rbNo;
    RadioButton rbYes;
    Custom_Suggestions_Adapter stageAdapter;
    ArrayList<String> teamname;
    ArrayList<Team> teams;
    boolean testMatch;
    TextInputLayout textInputLayoutOvers;
    Custom_Suggestions_Adapter tossWonByAdapter;
    ArrayList<String> tournamentnames;
    ArrayList<String> venues;
    Custom_Suggestions_Adapter venuesAdapter;

    /* access modifiers changed from: protected */
    @RequiresApi(api = 21)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_match_details);
        this.dbHelper = new DBHelper(this);
        this.matchTypes = new ArrayList<>();
        this.matchTypes.add("Test Match");
        this.matchTypes.add("Limited Overs Match");
        this.matchTypeAdapter = new Custom_Suggestions_Adapter(this, R.layout.custom_suggestions_layout, this.matchTypes);
        ArrayList arrayList = new ArrayList();
        arrayList.add("Single Match");
        arrayList.add("Group Stage");
        arrayList.add("Quarter Finals");
        arrayList.add("Semi Finals");
        arrayList.add("Final");
        this.stageAdapter = new Custom_Suggestions_Adapter(this, R.layout.custom_suggestions_layout, arrayList);
        this.edopteam = (AutoCompleteTextView) findViewById(R.id.edopteam);
        this.edyourteam = (AutoCompleteTextView) findViewById(R.id.edyourteam);
        this.edtournament = (AutoCompleteTextView) findViewById(R.id.edtuarnament);
        this.edvenue = (AutoCompleteTextView) findViewById(R.id.edvenue);
        this.edTossWonBy = (AutoCompleteTextView) findViewById(R.id.ed_toss_won_by);
        this.edovers = (EditText) findViewById(R.id.editText);
        this.edMatchType = (AutoCompleteTextView) findViewById(R.id.ed_match_type);
        this.edtournamentStage = (AutoCompleteTextView) findViewById(R.id.edtuarnamentStage);
        this.rbYes = (RadioButton) findViewById(R.id.rb_yes);
        this.rbNo = (RadioButton) findViewById(R.id.rb_no);
        this.radioButtonsLayout = (LinearLayout) findViewById(R.id.radio_buttons_layout);
        this.textInputLayoutOvers = (TextInputLayout) findViewById(R.id.text_input_layout_overs);
        new LoadTournaments().execute(new Object[0]);
        this.teamname = new ArrayList<>();
        this.teamname = getIntent().getStringArrayListExtra("team_names");
        this.teams = (ArrayList) getIntent().getSerializableExtra("teams");
        this.testMatch = getIntent().getBooleanExtra("testMatch", false);
        this.adapter = new Custom_Suggestions_Adapter(this, R.layout.custom_suggestions_layout, this.teamname);
        this.edopteam.setAdapter(this.adapter);
        this.edyourteam.setAdapter(this.adapter);
        this.adapter1 = new Custom_Suggestions_Adapter(this, R.layout.custom_suggestions_layout, this.tournamentnames);
        this.edtournament.setAdapter(this.adapter1);
        this.edtournamentStage.setAdapter(this.stageAdapter);
        this.edovers.setFilters(new InputFilter[]{new InputFilterMinMax("1", "500")});
        getSupportActionBar().setTitle((CharSequence) "Match Details");
        this.edtournament.setOnFocusChangeListener(this);
        this.edopteam.setOnFocusChangeListener(this);
        this.edyourteam.setOnFocusChangeListener(this);
        this.edvenue.setOnFocusChangeListener(this);
        this.edovers.setOnFocusChangeListener(this);
        this.edTossWonBy.setOnFocusChangeListener(this);
        this.edMatchType.setOnFocusChangeListener(this);
        this.edtournamentStage.setOnFocusChangeListener(this);
        this.edopteam.setOnClickListener(this);
        this.edyourteam.setOnClickListener(this);
        this.edtournament.setOnClickListener(this);
        this.edTossWonBy.setOnClickListener(this);
        this.edMatchType.setOnClickListener(this);
        this.edtournamentStage.setOnClickListener(this);
        UtilityFunctions.disableSoftInputFromAppearing(this.edTossWonBy);
        handleFocusChange(this.focusedittext);
    }

    private void handleFocusChange(int i) {
        if (i == 1) {
            this.edyourteam.clearFocus();
            this.edopteam.requestFocus();
        } else if (i == 2) {
            this.edopteam.clearFocus();
            this.edvenue.requestFocus();
        } else if (i == 3) {
            this.edvenue.clearFocus();
            this.edovers.requestFocus();
        } else if (i == 4) {
            handleNextAction(this.edovers);
        }
    }

    private void showDialog() {
        final CheckBox checkBox = new CheckBox(this);
        checkBox.setText("Don't show again.");
        checkBox.setChecked(false);
        checkBox.setPadding(0, 0, 0, 0);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle((CharSequence) "Enter Your teams");
        builder.setMessage((CharSequence) "Enter your team details in the first activity to see the suggestions about player names while scoring.");
        builder.setCancelable(true);
        builder.setView((View) checkBox);
        builder.setPositiveButton((CharSequence) "OK", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (checkBox.isChecked()) {
                    SharedPreferences.Editor edit = MatchDetailsActivity.this.getSharedPreferences(DBHelper.TABLE_TEAM, 0).edit();
                    edit.putString("dialog", PdfBoolean.TRUE);
                    edit.commit();
                }
                dialogInterface.dismiss();
            }
        });
        if (!getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("dialog", "").equals(PdfBoolean.TRUE)) {
            builder.show();
        }
    }

    public void buttonClick(View view) {
        if (this.edopteam.getText().toString().isEmpty() || this.edvenue.getText().toString().isEmpty() || ((this.edovers.getText().toString().isEmpty() && this.edMatchType.getText().toString().equalsIgnoreCase("Limited Overs Match")) || this.edTossWonBy.getText().toString().isEmpty())) {
            Toast.makeText(this, "Please fill all the fields to continue", 0).show();
        } else if (this.edyourteam.getText().toString().equals(this.edopteam.getText().toString())) {
            Toast.makeText(this, "Both team names cannot be same", 0).show();
        } else if (!this.edMatchType.getText().toString().equals("Test Match")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("START SECOND INNINGS").setMessage("Do you want to start second innings by setting up a target?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    try {
                        StartSecondInningsDialog.newInstance(Integer.parseInt(MatchDetailsActivity.this.edovers.getText().toString())).show(MatchDetailsActivity.this.getFragmentManager(), StartSecondInningsDialog.class.toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        Toast.makeText(MatchDetailsActivity.this.getApplicationContext(), "Please enter valid number of overs", 0).show();
                    }
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    MatchDetailsActivity.this.startPlayersInputActivity(false, 0, 0, 0, 0);
                }
            });
            builder.create();
            builder.show();
        } else {
            startPlayersInputActivity(false, 0, 0, 0, 0);
        }
    }

    /* access modifiers changed from: private */
    public void startPlayersInputActivity(boolean z, int i, int i2, int i3, int i4) {
        Intent intent = new Intent(this, PlayersInputActivity.class);
        Match match = new Match();
        if (this.edMatchType.getText().toString().equalsIgnoreCase("Test Match")) {
            match.setTestMatch(1);
        } else {
            match.setTestMatch(0);
        }
        match.setYourteam(this.edyourteam.getText().toString());
        match.setOpponent(this.edopteam.getText().toString());
        match.setVenue(this.edvenue.getText().toString());
        if (!match.isTestMatch) {
            try {
                match.setOvers(Integer.parseInt(this.edovers.getText().toString()));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Invalid number of overs", 0).show();
            }
        } else {
            match.setOvers(1000);
        }
        match.setTournament(this.edtournament.getText().toString());
        match.setTournamentStage(this.edtournamentStage.getText().toString());
        String str = "";
        if (this.edyourteam.getText().toString().equals(this.edTossWonBy.getText().toString())) {
            str = this.edyourteam.getText().toString() + " won the toss and elected to bat first";
        } else if (this.edopteam.getText().toString().equals(this.edTossWonBy.getText().toString())) {
            str = this.edopteam.getText().toString() + " won the toss and elected to bowl first";
        }
        match.setTossResult(str);
        match.getTeam1().setName(match.getYourteam());
        match.getTeam2().setName(match.getOpponent());
        if (z) {
            int i5 = i;
            match.getBattingTeam().setScore(i5);
            int i6 = i2;
            match.getBattingTeam().setWickets(i6);
            match.getBattingTeam().setOversPlayed(i3);
            match.getTeam1().setTeamID(this.dbHelper.insertTeam(match.getYourteam()));
            match.getTeam2().setTeamID(this.dbHelper.insertTeam(match.getOpponent()));
            match.setTeam_Yours_id(match.getTeam1().getTeamID());
            match.setTeam_opp_id(match.getTeam2().getTeamID());
            Calendar instance = Calendar.getInstance();
            match.setDate(instance.get(5) + "." + instance.get(2) + "." + instance.get(1));
            match.setTime(UtilityFunctions.getCurrentTime());
            match.setMatchID(this.dbHelper.insertMatch(match));
            this.dbHelper.insertTeamScore(match.getTeam1().getTeamID(), match.getMatchID(), i5, i6, 0, 0, i3, 0, i4);
            this.dbHelper.insertTeamScore(match.getTeam2().getTeamID(), match.getMatchID(), 0, 0, 0, 0, 0, 0, 0);
            match.switchInnings();
        }
        intent.putExtra(DBHelper.TABLE_MATCH, match);
        intent.putExtra("teams", this.teams);
        Log.e("tournament name", this.edtournament.getText().toString());
        startActivity(intent);
    }

    private void addPlayingXIinSharedPrefs(Match match) throws Exception {
        if (match.getBattingTeam().getAllPlayerNames().size() == 11) {
            SharedPrefsHelper.addPlayingXI(this, match, match.getBattingTeam(), match.getBattingTeam().getAllPlayerNames());
        }
        if (match.getBowlingTeam().getAllPlayerNames().size() == 11) {
            SharedPrefsHelper.addPlayingXI(this, match, match.getBowlingTeam(), match.getBowlingTeam().getAllPlayerNames());
        }
    }

    /* access modifiers changed from: package-private */
    public void handleNextAction(EditText editText) {
        editText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == 66) {
                    if (MatchDetailsActivity.this.edopteam.getText().toString().isEmpty() || MatchDetailsActivity.this.edvenue.getText().toString().isEmpty() || MatchDetailsActivity.this.edovers.getText().toString().isEmpty()) {
                        Toast.makeText(MatchDetailsActivity.this.getApplicationContext(), "Please fill all the fields to continue", 0).show();
                    } else {
                        Intent intent = new Intent(MatchDetailsActivity.this, PlayersInputActivity.class);
                        intent.putExtra("opteam", MatchDetailsActivity.this.edopteam.getText().toString());
                        intent.putExtra("venue", MatchDetailsActivity.this.edvenue.getText().toString());
                        intent.putExtra(DBHelper.TABLE_OVERS, MatchDetailsActivity.this.edovers.getText().toString());
                        intent.putExtra("tournament", MatchDetailsActivity.this.edtournament.getText().toString());
                        intent.putExtra("yourteam", MatchDetailsActivity.this.edyourteam.getText().toString());
                        MatchDetailsActivity.this.startActivity(intent);
                    }
                }
                return false;
            }
        });
    }

    public void onFocusChange(View view, boolean z) {
        switch (view.getId()) {
            case R.id.edtuarnament /*2131755348*/:
                if (z) {
                    this.focusedittext = 0;
                }
                showDropDownAfterChecking(this.edtournament, this.adapter1);
                return;
            case R.id.edtuarnamentStage /*2131755349*/:
                if (z) {
                    showDropDownAfterChecking(this.edtournamentStage, this.stageAdapter);
                    return;
                }
                return;
            case R.id.edyourteam /*2131755350*/:
                if (z) {
                    this.focusedittext = 1;
                }
                showDropDownAfterChecking(this.edyourteam, this.adapter);
                return;
            case R.id.edopteam /*2131755351*/:
                if (z) {
                    this.focusedittext = 2;
                }
                showDropDownAfterChecking(this.edopteam, this.adapter);
                return;
            case R.id.ed_toss_won_by /*2131755352*/:
                if (z) {
                    openSuggestionForToss();
                    return;
                }
                return;
            case R.id.edvenue /*2131755353*/:
                if (z) {
                    this.focusedittext = 3;
                }
                try {
                    showDropDownAfterChecking(this.edvenue, this.venuesAdapter);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            case R.id.ed_match_type /*2131755354*/:
                if (z) {
                    openSuggestionForMatchType();
                    return;
                }
                return;
            case R.id.editText /*2131755356*/:
                if (z) {
                    this.focusedittext = 4;
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: package-private */
    public void showDropDownAfterChecking(AutoCompleteTextView autoCompleteTextView, Custom_Suggestions_Adapter custom_Suggestions_Adapter) {
        if (custom_Suggestions_Adapter != null) {
            try {
                autoCompleteTextView.showDropDown();
            } catch (Exception unused) {
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edtuarnament /*2131755348*/:
                try {
                    this.edtournament.showDropDown();
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            case R.id.edtuarnamentStage /*2131755349*/:
                this.edtournamentStage.showDropDown();
                return;
            case R.id.edyourteam /*2131755350*/:
                this.edyourteam.showDropDown();
                return;
            case R.id.edopteam /*2131755351*/:
                this.edopteam.showDropDown();
                return;
            case R.id.ed_toss_won_by /*2131755352*/:
                openSuggestionForToss();
                return;
            case R.id.edvenue /*2131755353*/:
                this.edvenue.showDropDown();
                return;
            case R.id.ed_match_type /*2131755354*/:
                openSuggestionForMatchType();
                return;
            default:
                return;
        }
    }

    private void openSuggestionForMatchType() {
        AnonymousClass6 r5 = new SuggestionDialog.SuggestionsClickCallBack() {
            public void onTypeYourOwnClick(EditText editText) {
            }

            public void onSuggestionClicked(EditText editText, String str) {
                editText.setText(str);
                editText.clearFocus();
                MatchDetailsActivity.this.edvenue.requestFocus();
                if (str.equalsIgnoreCase("Test Match")) {
                    MatchDetailsActivity.this.textInputLayoutOvers.setVisibility(8);
                } else {
                    MatchDetailsActivity.this.textInputLayoutOvers.setVisibility(0);
                }
                if (Build.VERSION.SDK_INT >= 19) {
                    TransitionManager.beginDelayedTransition((ViewGroup) MatchDetailsActivity.this.findViewById(R.id.main_layout));
                }
            }
        };
        ArrayList arrayList = new ArrayList();
        arrayList.add("Test Match");
        arrayList.add("Limited Overs Match");
        UtilityFunctions.openSuggestionsDialog(this, this.edMatchType, arrayList, false, true, r5);
    }

    private void openSuggestionForToss() {
        if (this.edyourteam.getText().toString().isEmpty() || this.edopteam.getText().toString().isEmpty()) {
            MyToast.showToast(this, "Please Enter both teams names first");
            return;
        }
        new SuggestionDialog.SuggestionsClickCallBack() {
            public void onTypeYourOwnClick(EditText editText) {
            }

            public void onSuggestionClicked(EditText editText, String str) {
                editText.setText(str);
                editText.clearFocus();
                MatchDetailsActivity.this.edvenue.requestFocus();
            }
        };
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.edyourteam.getText().toString());
        arrayList.add(this.edopteam.getText().toString());
        UtilityFunctions.openSuggestionsDialog(this, this.edTossWonBy, arrayList, true, false);
    }

    public void onStartFirstInningsTapped() {
        startPlayersInputActivity(false, 0, 0, 0, 0);
    }

    public void onStartSecondInningsTapped(int i, int i2, int i3, int i4) {
        startPlayersInputActivity(true, i, i2, i3, i4);
    }

    class LoadTournaments extends AsyncTask {
        LoadTournaments() {
        }

        /* access modifiers changed from: protected */
        public Object doInBackground(Object[] objArr) {
            try {
                MatchDetailsActivity.this.tournamentnames = MatchDetailsActivity.this.dbHelper.getTournament(3);
                MatchDetailsActivity.this.tournamentnames.add(0, "No Tournament");
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                MatchDetailsActivity.this.venues = MatchDetailsActivity.this.dbHelper.getAllVenues();
                return null;
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Object obj) {
            super.onPostExecute(obj);
            MatchDetailsActivity.this.edtournament.setAdapter(new Custom_Suggestions_Adapter(MatchDetailsActivity.this, R.layout.custom_suggestions_layout, MatchDetailsActivity.this.tournamentnames));
            MatchDetailsActivity.this.venuesAdapter = new Custom_Suggestions_Adapter(MatchDetailsActivity.this, R.layout.custom_suggestions_layout, MatchDetailsActivity.this.venues);
            MatchDetailsActivity.this.edvenue.setAdapter(MatchDetailsActivity.this.venuesAdapter);
        }
    }

    /* access modifiers changed from: package-private */
    public ArrayList<String> getTeamNames() {
        ArrayList<Team> team = this.dbHelper.getTeam();
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < team.size(); i++) {
            if (!arrayList.contains(team.get(i).getName())) {
                arrayList.add(team.get(i).getName());
            }
        }
        return arrayList;
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
}
