package apps.shehryar.com.cricketscroingappPro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman;
import apps.shehryar.com.cricketscroingappPro.Batsman.BatsmanDataUpdater;
import apps.shehryar.com.cricketscroingappPro.Batsman.BatsmanHelper;
import apps.shehryar.com.cricketscroingappPro.Bowler.Bowler;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.FontProvider;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.UtilityFunctions;

public class WicketDialogFramgent extends MyDialogFragment implements View.OnClickListener, View.OnFocusChangeListener {
    int batFacing;
    private Batsman batsman;
    private Bowler bowler;
    private AutoCompleteTextView catcherName;
    private CheckBox cbcrossed;
    Match match;
    private LinearLayout radiogrouplayout;
    private RadioButton rbno;
    private RadioButton rbnone;
    private RadioButton rbwide;
    TextInputLayout tICatcherName;
    private Team teamBat;
    private TextView tvbatballs;
    private TextView tvbatfours;
    private TextView tvbatname;
    private TextView tvbatscore;
    private TextView tvbatsixes;
    private TextView tvstrikerate;
    int wicketCode;
    WicketDialogButtonListener wicketDialogButtonListener;
    private Button wicketdialUndoWicket;
    private Button wicketdialgobnext;
    private AutoCompleteTextView wicketedbatname;

    public interface WicketDialogButtonListener {
        void onNextTapped(Wicket wicket, String str);

        void onUndoButtonTapped();
    }

    public void findViews(View view) {
        this.tvbatname = (TextView) view.findViewById(R.id.tvbatname);
        this.tvbatscore = (TextView) view.findViewById(R.id.tvbatscore);
        this.tvbatballs = (TextView) view.findViewById(R.id.tvbatballs);
        this.tvbatfours = (TextView) view.findViewById(R.id.tvbatfours);
        this.tvbatsixes = (TextView) view.findViewById(R.id.tvbatsixes);
        this.tvstrikerate = (TextView) view.findViewById(R.id.tvstrikerate);
        this.wicketedbatname = (AutoCompleteTextView) view.findViewById(R.id.wicketedbatname);
        this.catcherName = (AutoCompleteTextView) view.findViewById(R.id.catcherName);
        this.tICatcherName = (TextInputLayout) view.findViewById(R.id.text_input_layout_catcher);
        this.wicketedbatname.setOnClickListener(this);
        this.catcherName.setOnClickListener(this);
        this.wicketedbatname.setOnFocusChangeListener(this);
        this.catcherName.setOnFocusChangeListener(this);
        this.cbcrossed = (CheckBox) view.findViewById(R.id.cbcrossed);
        this.radiogrouplayout = (LinearLayout) view.findViewById(R.id.radiogrouplayout);
        this.rbwide = (RadioButton) view.findViewById(R.id.rbwide);
        this.rbno = (RadioButton) view.findViewById(R.id.rbno);
        this.rbnone = (RadioButton) view.findViewById(R.id.rbnone);
        this.wicketdialgobnext = (Button) view.findViewById(R.id.wicketdialgobnext);
        this.wicketdialUndoWicket = (Button) view.findViewById(R.id.wicketdialUndoWicket);
        try {
            FontProvider.setEuroStileFont(getActivity(), this.wicketdialgobnext);
            FontProvider.setEuroStileFont(getActivity(), this.wicketdialUndoWicket);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.rbwide.setOnClickListener(this);
        this.rbno.setOnClickListener(this);
        this.rbnone.setOnClickListener(this);
        this.wicketdialgobnext.setOnClickListener(this);
        this.wicketdialUndoWicket.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view != this.rbwide && view != this.rbno && view != this.rbnone) {
            if (view == this.wicketedbatname) {
                UtilityFunctions.openSuggestionsDialogInDialogFragment(this, this.wicketedbatname, UtilityFunctions.getNotOutBatsmen(this.match.getBattingTeam().getAllPlayerNames(), this.match.getBattingTeam().getBatsmans_list()), false, false);
            } else if (view == this.catcherName) {
                UtilityFunctions.openSuggestionsDialogInDialogFragment(this, this.catcherName, this.match.getBowlingTeam().getAllPlayerNames(), false, false);
            } else if (view == this.wicketdialgobnext) {
                if (this.wicketedbatname.getText().toString().isEmpty()) {
                    MyToast.showToast(getActivity(), "Please Enter the new Batsman Name to continue.");
                } else if (BatsmanHelper.checkBatsmanAlreadyPlayed(this.wicketedbatname.getText().toString(), this.teamBat)) {
                    MyToast.showToast(getActivity(), "This batsman has already played. Please change batsman");
                } else {
                    if (!this.rbwide.isChecked() && !this.rbno.isChecked()) {
                        this.batsman.setBallsfaced(1);
                    }
                    BatsmanDataUpdater.assignOutType(this.batsman, this.wicketCode, this.bowler.getName(), this.catcherName.getText().toString());
                    Wicket wicket = new Wicket();
                    wicket.setBatFacingForRuns(this.batFacing);
                    wicket.setBatFacingOut(this.batFacing);
                    wicket.setCatcherName(this.catcherName.getText().toString());
                    wicket.setCrossed(this.cbcrossed.isChecked());
                    wicket.setBye(false);
                    wicket.setBatsman(this.batsman);
                    wicket.setLegBye(false);
                    wicket.setNo(this.rbno.isChecked());
                    wicket.setWide(this.rbwide.isChecked());
                    wicket.setWicketCode(this.wicketCode);
                    wicket.setRunOut(false);
                    wicket.setBowler(this.bowler);
                    this.wicketDialogButtonListener.onNextTapped(wicket, this.wicketedbatname.getText().toString());
                    dismiss();
                }
            } else if (view == this.wicketdialUndoWicket) {
                dismiss();
            }
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.after_wicket_dialog, viewGroup, false);
        ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(inflate.getWindowToken(), 0);
        findViews(inflate);
        this.batsman = (Batsman) getArguments().getSerializable("batsman");
        setValuesToViews(this.batsman);
        recieveArguments(getArguments());
        return inflate;
    }

    private void recieveArguments(Bundle bundle) {
        this.batFacing = bundle.getInt("batFacing");
        this.wicketCode = bundle.getInt("wicketCode");
        this.wicketDialogButtonListener = (WicketDialogButtonListener) getActivity();
        this.teamBat = (Team) bundle.getSerializable("teamBat");
        this.batsman = (Batsman) bundle.getSerializable("batsman");
        this.bowler = (Bowler) bundle.getSerializable("edBowler");
        this.match = (Match) bundle.getSerializable(DBHelper.TABLE_MATCH);
        if (this.wicketCode == 1) {
            this.cbcrossed.setVisibility(0);
            this.catcherName.setVisibility(0);
            this.tICatcherName.setVisibility(0);
        }
        if (this.wicketCode == 3 || this.wicketCode == 4 || this.wicketCode == 3) {
            this.radiogrouplayout.setVisibility(0);
        }
        if (this.match.getBattingTeam().getWickets() + 1 == this.match.getPerMatchWickets()) {
            this.wicketedbatname.setText("All Out");
            this.wicketedbatname.setEnabled(false);
            this.wicketedbatname.setFocusable(false);
            this.catcherName.setFocusable(false);
        }
    }

    private void setValuesToViews(Batsman batsman2) {
        this.tvbatname.setText(batsman2.getName());
        TextView textView = this.tvbatscore;
        textView.setText(batsman2.getTotalScore() + "");
        TextView textView2 = this.tvbatballs;
        textView2.setText(batsman2.getBallsfaced() + "");
        TextView textView3 = this.tvbatfours;
        textView3.setText(batsman2.getFours() + "");
        TextView textView4 = this.tvbatsixes;
        textView4.setText(batsman2.getSixs() + "");
        TextView textView5 = this.tvstrikerate;
        textView5.setText(batsman2.getStrikerate() + "");
    }

    public void onFocusChange(View view, boolean z) {
        if (view == this.wicketedbatname && z) {
            UtilityFunctions.openSuggestionsDialogInDialogFragment(this, this.wicketedbatname, UtilityFunctions.getNotOutBatsmen(this.match.getBattingTeam().getAllPlayerNames(), this.match.getBattingTeam().getBatsmans_list()), false, false);
        } else if (view == this.catcherName && z) {
            UtilityFunctions.openSuggestionsDialogInDialogFragment(this, this.catcherName, this.match.getBowlingTeam().getAllPlayerNames(), false, false);
        }
    }
}
