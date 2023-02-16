package apps.shehryar.com.cricketscroingappPro.Overs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import apps.shehryar.com.cricketscroingappPro.Bowler.BowlerViewsUpdater;
import apps.shehryar.com.cricketscroingappPro.Bowler.Dialog_Custom_ListView_Adapter;
import apps.shehryar.com.cricketscroingappPro.Custom_Suggestions_Adapter;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.Interfaces.AfterOverDialogButtonsListener;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.R;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.UtilityFunctions;
import com.google.android.gms.ads.AdListener;

public class AfterOverDialogFragment extends DialogFragment implements View.OnClickListener {
    AdListener adListener = new AdListener() {
        public void onAdClosed() {
            super.onAdClosed();
            if (AfterOverDialogFragment.this.listener != null) {
                try {
                    AfterOverDialogFragment.this.listener.onNextOverTapped(AfterOverDialogFragment.this.edBowlerName.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                AfterOverDialogFragment.this.dismiss();
            }
        }
    };
    Button bNextOver;
    Button bOverHistory;
    Button bviewBatsmanHistory;
    AutoCompleteTextView edBowlerName;
    int inningsCode;
    AfterOverDialogButtonsListener listener;
    Match match;
    Team teamBat;
    Team teamBowl;
    TextView teamname;
    TextView tvScore;
    TextView tvWickets;
    TextView tvovers;

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setStyle(0, R.style.MY_DIALOG);
    }

    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(-1, -1);
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.listener = (AfterOverDialogButtonsListener) getActivity();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Dialog onCreateDialog = super.onCreateDialog(bundle);
        new RelativeLayout(getActivity()).setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        onCreateDialog.getWindow().getAttributes().windowAnimations = apps.shehryar.com.cricketscroingapp.R.style.DialogAnimation_2;
        this.match = (Match) getArguments().getSerializable(DBHelper.TABLE_MATCH);
        this.teamBat = this.match.getBattingTeam();
        this.teamBowl = this.match.getBowlingTeam();
        return onCreateDialog;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(apps.shehryar.com.cricketscroingapp.R.layout.after_over_dialog2, viewGroup, false);
        this.bNextOver = (Button) inflate.findViewById(apps.shehryar.com.cricketscroingapp.R.id.bnextover);
        this.bOverHistory = (Button) inflate.findViewById(apps.shehryar.com.cricketscroingapp.R.id.bviewhistory);
        this.bviewBatsmanHistory = (Button) inflate.findViewById(apps.shehryar.com.cricketscroingapp.R.id.bviewbathistory);
        this.edBowlerName = (AutoCompleteTextView) inflate.findViewById(apps.shehryar.com.cricketscroingapp.R.id.edbowlerinput);
        this.teamname = (TextView) inflate.findViewById(apps.shehryar.com.cricketscroingapp.R.id.tvteamname);
        this.tvovers = (TextView) inflate.findViewById(apps.shehryar.com.cricketscroingapp.R.id.tvovers);
        this.tvScore = (TextView) inflate.findViewById(apps.shehryar.com.cricketscroingapp.R.id.tvscore);
        this.tvWickets = (TextView) inflate.findViewById(apps.shehryar.com.cricketscroingapp.R.id.tvwickets);
        BowlerViewsUpdater.showBowlerRecyclerViewFragment(getChildFragmentManager(), this.teamBowl.bowlers_list, new Dialog_Custom_ListView_Adapter.LinearLayoutClickLister() {
            public void onBowlerNameTapped(int i) {
                AfterOverDialogFragment.this.edBowlerName.setText(AfterOverDialogFragment.this.teamBowl.getBowlers_list().get(i).getName());
            }
        }, this.teamBat.overs_list, this.match);
        TextView textView = this.teamname;
        textView.setText(this.teamBat.getName() + "");
        TextView textView2 = this.tvScore;
        textView2.setText(this.teamBat.getScore() + "");
        TextView textView3 = this.tvWickets;
        textView3.setText(this.teamBat.getWickets() + "");
        TextView textView4 = this.tvovers;
        textView4.setText(this.teamBat.getOversPlayed() + "");
        this.edBowlerName.setAdapter(new Custom_Suggestions_Adapter(getActivity(), apps.shehryar.com.cricketscroingapp.R.layout.custom_suggestions_layout, this.match.getBowlingTeam().getAllPlayerNames()));
        UtilityFunctions.showDropDownOnEditTextFocus(this.edBowlerName);
        UtilityFunctions.showDropDownOntouchingEditText(this.edBowlerName);
        UtilityFunctions.hideKeyBoardOnNextAction(getActivity(), this.edBowlerName);
        this.bNextOver.setOnClickListener(this);
        this.bviewBatsmanHistory.setOnClickListener(this);
        this.bOverHistory.setOnClickListener(this);
        return inflate;
    }

    public void onClick(View view) {
        String str;
        int id = view.getId();
        if (id != apps.shehryar.com.cricketscroingapp.R.id.bviewhistory) {
            switch (id) {
                case apps.shehryar.com.cricketscroingapp.R.id.bviewbathistory /*2131755428*/:
                    try {
                        this.listener.onUndoPreviousBallTapped();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    dismiss();
                    return;
                case apps.shehryar.com.cricketscroingapp.R.id.bnextover /*2131755429*/:
                    try {
                        str = this.teamBat.overs_list.get(this.teamBat.overs_list.size() - 1).getBowler();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        str = " ";
                    }
                    if (this.edBowlerName.getText().toString().isEmpty()) {
                        this.edBowlerName.setHint("Bowler Name is Required");
                        this.edBowlerName.setError("New Bowler Name is required");
                        Toast.makeText(getActivity(), "Please Enter the name of new Bowler or Select from the list", 0).show();
                        return;
                    } else if (this.edBowlerName.getText().toString().equals(str)) {
                        Toast.makeText(getActivity(), "This bowler has bowled the previous over. Please change the bowler.", 0).show();
                        return;
                    } else {
                        try {
                            this.listener.onNextOverTapped(this.edBowlerName.getText().toString());
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                        dismiss();
                        return;
                    }
                default:
                    return;
            }
        } else {
            try {
                this.listener.onViewOversTapped();
            } catch (Exception e4) {
                e4.printStackTrace();
            }
            dismiss();
        }
    }

    public void onResume() {
        super.onResume();
        try {
            getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    if (i == 4) {
                        return keyEvent.getAction() != 0 ? true : true;
                    }
                    return false;
                }
            });
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void show(FragmentManager fragmentManager, String str) {
        try {
            super.show(fragmentManager, str);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), getResources().getString(apps.shehryar.com.cricketscroingapp.R.string.something_went_wrong), 0).show();
        }
    }
}
