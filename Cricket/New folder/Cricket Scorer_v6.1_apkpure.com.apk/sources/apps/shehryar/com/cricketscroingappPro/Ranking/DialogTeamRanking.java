package apps.shehryar.com.cricketscroingappPro.Ranking;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.MyDialogFragment;
import apps.shehryar.com.cricketscroingappPro.MyToast;
import apps.shehryar.com.cricketscroingappPro.PermissionAsker;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import apps.shehryar.com.cricketscroingappPro.UpgradeToProDialog;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.FontProvider;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.ImageSharer;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.SharedPrefsHelper;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.UtilityFunctions;
import com.squareup.picasso.Picasso;

public class DialogTeamRanking extends MyDialogFragment implements UpgradeToProDialog.UpgradeToProCallBack {
    private Button bShare;
    public ImageView profileImage;
    private LinearLayout statsLayout;
    private Team team;
    private TextView teamDialogName;
    private TextView tvTeamChaseOpponent;
    private TextView tvTeamDefendOpponent;
    private TextView tvTeamHighestChased;
    private TextView tvTeamHighestDefended;
    private TextView tvTeamHighestOpponent;
    private TextView tvTeamHighestTotal;
    private TextView tvTeamMatches;
    private TextView tvTeamMatchesDrawn;
    private TextView tvTeamMatchesLost;
    private TextView tvTeamMatchesWon;
    private TextView tvTeamNetRunrate;
    private TextView tvTeamPoints;
    private TextView tvTeamRanking;
    private TextView tvTeamStatsText;

    private void findViews(View view) {
        this.teamDialogName = (TextView) view.findViewById(R.id.team_dialog_name);
        this.tvTeamRanking = (TextView) view.findViewById(R.id.tv_team_ranking);
        this.statsLayout = (LinearLayout) view.findViewById(R.id.stats_layout);
        this.tvTeamPoints = (TextView) view.findViewById(R.id.tv_team_points);
        this.tvTeamMatches = (TextView) view.findViewById(R.id.tv_team_matches);
        this.tvTeamMatchesWon = (TextView) view.findViewById(R.id.tv_team_matches_won);
        this.tvTeamMatchesLost = (TextView) view.findViewById(R.id.tv_team_matches_lost);
        this.tvTeamMatchesDrawn = (TextView) view.findViewById(R.id.tv_team_matches_drawn);
        this.tvTeamHighestTotal = (TextView) view.findViewById(R.id.tv_team_highest_total);
        this.tvTeamHighestOpponent = (TextView) view.findViewById(R.id.tv_team_highest_opponent);
        this.tvTeamHighestChased = (TextView) view.findViewById(R.id.tv_team_highest_chased);
        this.tvTeamChaseOpponent = (TextView) view.findViewById(R.id.tv_team_chase_opponent);
        this.tvTeamHighestDefended = (TextView) view.findViewById(R.id.tv_team_highest_defended);
        this.tvTeamDefendOpponent = (TextView) view.findViewById(R.id.tv_team_defend_opponent);
        this.tvTeamNetRunrate = (TextView) view.findViewById(R.id.tv_team_net_runrate);
        this.tvTeamStatsText = (TextView) view.findViewById(R.id.tv_team_stats_text);
        this.profileImage = (ImageView) view.findViewById(R.id.profile_image);
    }

    public void setFontToViews() {
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvTeamHighestChased);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvTeamHighestDefended);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvTeamHighestTotal);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvTeamMatches);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvTeamMatchesDrawn);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvTeamMatchesLost);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvTeamMatchesWon);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvTeamNetRunrate);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvTeamPoints);
        FontProvider.setSeguUIFont(getActivity(), this.teamDialogName);
    }

    public void setValuesToViews(Team team2) {
        if (team2.getImage() == null) {
            this.profileImage.setVisibility(8);
        } else if (!team2.getImage().isEmpty()) {
            Picasso.with(getActivity()).load(Uri.parse(team2.getImage())).into(this.profileImage);
        } else {
            this.profileImage.setVisibility(8);
        }
        this.teamDialogName.setText(team2.getName());
        if (team2.getHighestChased() != null) {
            TextView textView = this.tvTeamChaseOpponent;
            textView.setText("VS " + team2.getHighestChased().getOpponent());
            TextView textView2 = this.tvTeamHighestChased;
            textView2.setText(team2.getHighestChased().getScore() + "");
        } else {
            this.tvTeamHighestChased.setText("-");
        }
        if (team2.getHighestTotal() != null) {
            TextView textView3 = this.tvTeamHighestTotal;
            textView3.setText(team2.getHighestTotal().getScore() + "");
            TextView textView4 = this.tvTeamHighestOpponent;
            textView4.setText("VS " + team2.getHighestTotal().getOpponent());
        } else {
            this.tvTeamHighestTotal.setText("-");
        }
        if (team2.getHighestDefended() != null) {
            TextView textView5 = this.tvTeamHighestDefended;
            textView5.setText(team2.getHighestDefended().getScore() + "");
            TextView textView6 = this.tvTeamDefendOpponent;
            textView6.setText("VS " + team2.getHighestDefended().getOpponent());
        } else {
            this.tvTeamHighestDefended.setText("-");
        }
        TextView textView7 = this.tvTeamMatches;
        textView7.setText(team2.getMatches_Played() + "");
        TextView textView8 = this.tvTeamMatchesDrawn;
        textView8.setText(team2.getMatches_tied() + "");
        TextView textView9 = this.tvTeamMatchesLost;
        textView9.setText(team2.getMatches_lost() + "");
        TextView textView10 = this.tvTeamMatchesWon;
        textView10.setText(team2.getMatches_won() + "");
        TextView textView11 = this.tvTeamPoints;
        textView11.setText(team2.getPoints() + "");
        TextView textView12 = this.tvTeamRanking;
        textView12.setText("RANKING # " + team2.getRanking());
        if (Double.isNaN(team2.getNet_Run_Rate())) {
            this.tvTeamNetRunrate.setText("-");
            return;
        }
        TextView textView13 = this.tvTeamNetRunrate;
        textView13.setText(UtilityFunctions.convertDoubleToTwoValuesAfterPoint(team2.getNet_Run_Rate()) + "");
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.team_ranking_dialog_layout, viewGroup, false);
        this.team = (Team) getArguments().getSerializable(DBHelper.TABLE_TEAM);
        try {
            this.team.setImage(SharedPrefsHelper.getTeamImage(getActivity(), this.team));
        } catch (Exception e) {
            e.printStackTrace();
            this.team.setImage((String) null);
        }
        findViews(inflate);
        setFontToViews();
        setValuesToViews(this.team);
        this.tvTeamStatsText.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = 17)
            public void onClick(View view) {
                if (!SharedPrefsHelper.isPro(DialogTeamRanking.this.getActivity())) {
                    DialogTeamRanking.this.showUpgradeToProDialog();
                } else if (!PermissionAsker.checkAPIVersion()) {
                    ImageSharer.shareDialogImage(DialogTeamRanking.this, DialogTeamRanking.this.getActivity());
                } else if (PermissionAsker.checkIfAlreadyhavePermission(DialogTeamRanking.this.getActivity())) {
                    ImageSharer.shareDialogImage(DialogTeamRanking.this, DialogTeamRanking.this.getActivity());
                } else {
                    PermissionAsker.requestForSpecificPermission(105, DialogTeamRanking.this.getActivity());
                }
            }
        });
        return inflate;
    }

    /* access modifiers changed from: private */
    @RequiresApi(api = 17)
    public void showUpgradeToProDialog() {
        UpgradeToProDialog upgradeToProDialog = new UpgradeToProDialog();
        upgradeToProDialog.setListener(this);
        upgradeToProDialog.show(getChildFragmentManager(), "upgrade to PRO dialog");
    }

    public void onPurchaseSuccessfull() {
        ImageSharer.shareDialogImage(this, getActivity());
    }

    public void onPurchaseFailed() {
        MyToast.showToast(getActivity(), "Billing Unsuccessful");
    }
}
