package apps.shehryar.com.cricketscroingappPro.Team;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.MyToast;
import apps.shehryar.com.cricketscroingappPro.PermissionAsker;
import apps.shehryar.com.cricketscroingappPro.UpgradeToProDialog;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.FontProvider;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.ImageSharer;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.SharedPrefsHelper;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.UtilityFunctions;

public class TeamDetailsDialog extends DialogFragment implements UpgradeToProDialog.UpgradeToProCallBack {
    Button bClose;
    Button bShare;
    Context context;
    ImageView imageView;
    private Match match;
    Team team;
    TextView tvBat1s;
    TextView tvBat2s;
    TextView tvBat3s;
    TextView tvBat4s;
    TextView tvBat6s;
    TextView tvBatdots;
    TextView tvOvers;
    TextView tvTeamName;
    TextView tvTeamProjected;
    TextView tvTeamRr;
    TextView tvTeamScore;
    TextView tvteamExtras;

    public Dialog onCreateDialog(Bundle bundle) {
        Dialog onCreateDialog = super.onCreateDialog(bundle);
        onCreateDialog.getWindow().requestFeature(1);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        onCreateDialog.getWindow().getAttributes().windowAnimations = 16973826;
        layoutParams.copyFrom(onCreateDialog.getWindow().getAttributes());
        layoutParams.width = -1;
        onCreateDialog.getWindow().setAttributes(layoutParams);
        return onCreateDialog;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.team_details_dialog_layout, viewGroup, false);
        this.team = (Team) getArguments().getSerializable(DBHelper.TABLE_TEAM);
        this.match = (Match) getArguments().getSerializable(DBHelper.TABLE_MATCH);
        try {
            UtilityFunctions.setTeamExtras(this.team, this.team.overs_list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        initializeViews(inflate);
        setValues(this.team);
        return inflate;
    }

    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(-1, -2);
    }

    @RequiresApi(api = 23)
    private void setValues(Team team2) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        this.tvTeamName.setText(team2.getName());
        this.tvTeamScore.setText(team2.getScore() + "/" + team2.getWickets());
        this.tvTeamName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!SharedPrefsHelper.isPro(TeamDetailsDialog.this.getActivity())) {
                    TeamDetailsDialog.this.showUpgradeToProDialog();
                } else if (!PermissionAsker.checkAPIVersion()) {
                    ImageSharer.shareDialogImage(TeamDetailsDialog.this, TeamDetailsDialog.this.getActivity());
                } else if (PermissionAsker.checkIfAlreadyhavePermission(TeamDetailsDialog.this.getActivity())) {
                    ImageSharer.shareDialogImage(TeamDetailsDialog.this, TeamDetailsDialog.this.getActivity());
                } else {
                    PermissionAsker.requestForSpecificPermission(105, TeamDetailsDialog.this.getActivity());
                }
            }
        });
        UtilityFunctions.setSpanColor(this.tvTeamRr, "RUN RATE: " + team2.getRunRate() + "", team2.getRunRate() + "", Color.parseColor("#009688"));
        UtilityFunctions.setSpanColor(this.tvTeamProjected, "PROJECTED SCORE: " + team2.getProjectScore() + "", team2.getProjectScore() + "", Color.parseColor("#009688"));
        this.tvteamExtras.setText(team2.getExtrasString());
        if (this.match.isTestMatch) {
            this.tvOvers.setText(team2.getOversStringForTestMatch());
        } else {
            this.tvOvers.setText(team2.getOversString());
        }
        TextView textView = this.tvBatdots;
        if (team2.getDots() == 0) {
            str = "-";
        } else {
            str = team2.getDots() + "";
        }
        textView.setText(str);
        TextView textView2 = this.tvBat1s;
        if (team2.getSingles() == 0) {
            str2 = "-";
        } else {
            str2 = team2.getSingles() + "";
        }
        textView2.setText(str2);
        TextView textView3 = this.tvBat2s;
        if (team2.getDoubles() == 0) {
            str3 = "-";
        } else {
            str3 = team2.getDoubles() + "";
        }
        textView3.setText(str3);
        TextView textView4 = this.tvBat3s;
        if (team2.getTriples() == 0) {
            str4 = "-";
        } else {
            str4 = team2.getTriples() + "";
        }
        textView4.setText(str4);
        TextView textView5 = this.tvBat4s;
        if (team2.getFours() == 0) {
            str5 = "-";
        } else {
            str5 = team2.getFours() + "";
        }
        textView5.setText(str5);
        TextView textView6 = this.tvBat6s;
        if (team2.getSixes() == 0) {
            str6 = "-";
        } else {
            str6 = team2.getSixes() + "";
        }
        textView6.setText(str6);
        if (team2.getOversPlayed() == team2.getMatchTotalOvers() || team2.getWickets() == 10) {
            this.tvTeamProjected.setVisibility(4);
        }
        try {
            this.imageView.setImageURI(Uri.parse(team2.getImage()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    @RequiresApi(api = 17)
    public void showUpgradeToProDialog() {
        UpgradeToProDialog upgradeToProDialog = new UpgradeToProDialog();
        upgradeToProDialog.setListener(this);
        upgradeToProDialog.show(getChildFragmentManager(), "upgrade to PRO dialog");
    }

    /* access modifiers changed from: package-private */
    public void initializeViews(View view) {
        this.tvTeamName = (TextView) view.findViewById(R.id.team_name);
        try {
            FontProvider.setFiraSansFont(getActivity(), this.tvTeamName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.tvTeamName.setAllCaps(true);
        this.tvTeamScore = (TextView) view.findViewById(R.id.team_Score);
        this.tvTeamRr = (TextView) view.findViewById(R.id.team_run_rate);
        this.tvteamExtras = (TextView) view.findViewById(R.id.team_extras);
        this.tvOvers = (TextView) view.findViewById(R.id.team_overs);
        this.tvTeamProjected = (TextView) view.findViewById(R.id.team_projected);
        this.tvBatdots = (TextView) view.findViewById(R.id.tvbatdots);
        this.tvBat1s = (TextView) view.findViewById(R.id.tvbat1s);
        this.tvBat2s = (TextView) view.findViewById(R.id.tvbat2s);
        this.tvBat3s = (TextView) view.findViewById(R.id.tvbat3s);
        this.tvBat4s = (TextView) view.findViewById(R.id.tvbat4s);
        this.tvBat6s = (TextView) view.findViewById(R.id.tvbat6s);
        this.bClose = (Button) view.findViewById(R.id.bdialogClose);
        this.imageView = (ImageView) view.findViewById(R.id.team_image_view);
        this.bClose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TeamDetailsDialog.this.dismiss();
            }
        });
        TextView textView = (TextView) view.findViewById(R.id.team_opponent);
        textView.setText(this.match.getTeam1().getName() + " VS " + this.match.getTeam2().getName());
        FontProvider.setSeguUIFont(getActivity(), textView);
        try {
            if (this.match != null) {
                ((TextView) view.findViewById(R.id.match_venue_date)).setText(this.match.getVenue() + " " + this.match.getDate());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void onPurchaseSuccessfull() {
        ImageSharer.shareDialogImage(this, getActivity());
    }

    public void onPurchaseFailed() {
        MyToast.showToast(getActivity(), "Error, Sharing image requires upgrading to PRO");
    }
}
