package apps.shehryar.com.cricketscroingappPro;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman;
import apps.shehryar.com.cricketscroingappPro.Batsman.BatsmanDataUpdater;
import apps.shehryar.com.cricketscroingappPro.Batsman.BatsmanHelper;
import apps.shehryar.com.cricketscroingappPro.Bowler.Bowler;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.FontProvider;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.UtilityFunctions;
import apps.shehryar.com.cricketscroingappPro.WicketDialogFramgent;

public class RunOutDialogFragment extends MyDialogFragment implements View.OnClickListener, View.OnFocusChangeListener {
    Button bRunOutSubmit;
    Button bRunOutUndo;
    int batFacing;
    int batsmanOut;
    private Batsman[] batsmen;
    private Bowler bowler;
    LinearLayout byesLayout;
    AutoCompleteTextView edFielderName;
    AutoCompleteTextView edbatsmanname;
    EditText edruns;
    private Match match;
    RadioButton rbByes;
    RadioButton rbLegByes;
    RadioButton rbdbatsman1;
    RadioButton rbdbatsman2;
    RadioButton rbno;
    RadioButton rbnone;
    RadioButton rbwide;
    private Team teamBat;
    int wicketCode;
    WicketDialogFramgent.WicketDialogButtonListener wicketDialogButtonListener;

    public void findViews(final View view) {
        this.rbdbatsman1 = (RadioButton) view.findViewById(R.id.rbdbatsman1);
        this.rbdbatsman2 = (RadioButton) view.findViewById(R.id.rbdbatsman2);
        this.edruns = (EditText) view.findViewById(R.id.edrunsscored);
        this.edbatsmanname = (AutoCompleteTextView) view.findViewById(R.id.ednewbatsman);
        this.rbwide = (RadioButton) view.findViewById(R.id.rbwide);
        this.rbno = (RadioButton) view.findViewById(R.id.rbno);
        this.rbnone = (RadioButton) view.findViewById(R.id.rbnone);
        this.edruns.setFilters(new InputFilter[]{new InputFilterMinMax("0", "3")});
        this.rbByes = (RadioButton) view.findViewById(R.id.rbByes);
        this.rbLegByes = (RadioButton) view.findViewById(R.id.rblegByes);
        this.byesLayout = (LinearLayout) view.findViewById(R.id.byesLayout);
        this.bRunOutSubmit = (Button) view.findViewById(R.id.brunoutsubmit);
        this.bRunOutUndo = (Button) view.findViewById(R.id.brunoutUndo);
        this.edFielderName = (AutoCompleteTextView) view.findViewById(R.id.edFilderName);
        try {
            FontProvider.setEuroStileFont(getActivity(), this.bRunOutSubmit);
            FontProvider.setEuroStileFont(getActivity(), this.bRunOutUndo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.bRunOutSubmit.setOnClickListener(this);
        this.bRunOutUndo.setOnClickListener(this);
        this.edbatsmanname.setOnClickListener(this);
        this.edFielderName.setOnClickListener(this);
        this.edbatsmanname.setOnFocusChangeListener(this);
        this.edFielderName.setOnFocusChangeListener(this);
        this.rbwide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (Build.VERSION.SDK_INT >= 19) {
                    TransitionManager.beginDelayedTransition((ViewGroup) view.findViewById(R.id.main_layout));
                }
                if (z) {
                    RunOutDialogFragment.this.byesLayout.setVisibility(8);
                } else {
                    RunOutDialogFragment.this.byesLayout.setVisibility(0);
                }
            }
        });
    }

    public void onClick(View view) {
        if (view == this.bRunOutSubmit) {
            if (this.edbatsmanname.getText().toString().isEmpty()) {
                MyToast.showToast(getActivity(), "Please Enter the new Batsman Name to continue.");
            } else if (BatsmanHelper.checkBatsmanAlreadyPlayed(this.edbatsmanname.getText().toString(), this.teamBat)) {
                MyToast.showToast(getActivity(), "This batsman has already played. Please change batsman");
            } else if (!this.rbdbatsman1.isChecked() && !this.rbdbatsman2.isChecked()) {
                MyToast.showToast(getActivity(), "Please select the batsman out");
            } else if (this.edruns.getText().toString().isEmpty()) {
                MyToast.showToast(getActivity(), "Please enter runs scored with this Run Out");
            } else {
                if (this.rbdbatsman1.isChecked()) {
                    this.batsmanOut = 0;
                } else if (this.rbdbatsman2.isChecked()) {
                    this.batsmanOut = 1;
                }
                if (!this.rbwide.isChecked() && !this.rbno.isChecked()) {
                    this.batsmen[this.batFacing].setBallsfaced(1);
                }
                if (!this.rbByes.isChecked() && !this.rbLegByes.isChecked() && !this.rbwide.isChecked()) {
                    this.batsmen[this.batFacing].setTotalScore(Integer.parseInt(this.edruns.getText().toString()));
                    BatsmanDataUpdater.assignScoresToBatsman(this.batsmen[this.batFacing], Integer.parseInt(this.edruns.getText().toString()));
                }
                BatsmanDataUpdater.assignOutType(this.batsmen[this.batsmanOut], 5, this.bowler.getName(), this.edFielderName.getText().toString());
                Wicket wicket = new Wicket();
                wicket.setBatFacingForRuns(this.batFacing);
                wicket.setBatFacingOut(this.batsmanOut);
                wicket.setCatcherName(this.edFielderName.getText().toString());
                wicket.setCrossed(false);
                wicket.setRunsScored(Integer.parseInt(this.edruns.getText().toString()));
                wicket.setBye(this.rbByes.isChecked());
                wicket.setBatsman(this.batsmen[this.batsmanOut]);
                wicket.setLegBye(this.rbByes.isChecked());
                wicket.setNo(this.rbno.isChecked());
                wicket.setWide(this.rbwide.isChecked());
                wicket.setWicketCode(5);
                wicket.setRunOut(true);
                wicket.setBowler(this.bowler);
                this.wicketDialogButtonListener.onNextTapped(wicket, this.edbatsmanname.getText().toString());
                dismiss();
            }
        } else if (view == this.edbatsmanname) {
            UtilityFunctions.openSuggestionsDialogInDialogFragment(this, this.edbatsmanname, UtilityFunctions.getNotOutBatsmen(this.match.getBattingTeam().getAllPlayerNames(), this.match.getBattingTeam().getBatsmans_list()), false, false);
        } else if (view == this.edFielderName) {
            UtilityFunctions.openSuggestionsDialogInDialogFragment(this, this.edFielderName, this.match.getBowlingTeam().getAllPlayerNames(), false, false);
        } else if (view == this.bRunOutUndo) {
            dismiss();
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.runout_dialog, viewGroup, false);
        findViews(inflate);
        recieveArguments(getArguments());
        this.rbdbatsman1.setText(this.batsmen[0].getName());
        this.rbdbatsman2.setText(this.batsmen[1].getName());
        return inflate;
    }

    private void recieveArguments(Bundle bundle) {
        this.batFacing = bundle.getInt("batFacing");
        this.wicketCode = bundle.getInt("wicketCode");
        this.wicketDialogButtonListener = (WicketDialogFramgent.WicketDialogButtonListener) getActivity();
        this.teamBat = (Team) bundle.getSerializable("teamBat");
        this.match = (Match) bundle.getSerializable(DBHelper.TABLE_MATCH);
        this.batsmen = new Batsman[2];
        this.batsmen[0] = (Batsman) bundle.getSerializable("batsman1");
        this.batsmen[1] = (Batsman) bundle.getSerializable("batsman2");
        this.bowler = (Bowler) bundle.getSerializable("edBowler");
        if (this.match.getBattingTeam().getWickets() + 1 == this.match.getPerMatchWickets()) {
            this.edbatsmanname.setText("All Out");
            this.edbatsmanname.setEnabled(false);
            this.edbatsmanname.setFocusable(false);
        }
    }

    public void onFocusChange(View view, boolean z) {
        if (view == this.edbatsmanname && z) {
            UtilityFunctions.openSuggestionsDialogInDialogFragment(this, this.edbatsmanname, UtilityFunctions.getNotOutBatsmen(this.match.getBattingTeam().getAllPlayerNames(), this.match.getBattingTeam().getBatsmans_list()), false, false);
        } else if (view == this.edFielderName && z) {
            UtilityFunctions.openSuggestionsDialogInDialogFragment(this, this.edFielderName, this.match.getBowlingTeam().getAllPlayerNames(), false, false);
        }
    }
}
