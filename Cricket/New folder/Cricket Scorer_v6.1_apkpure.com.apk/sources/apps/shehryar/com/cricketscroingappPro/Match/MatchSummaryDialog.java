package apps.shehryar.com.cricketscroingappPro.Match;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.MyDialogFragment;
import apps.shehryar.com.cricketscroingappPro.TopThreePlayersFragment;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.FontProvider;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.UtilityFunctions;

public class MatchSummaryDialog extends MyDialogFragment {
    Button bshare;
    LinearLayout main_layout;
    Match match;
    LinearLayout momlayout;
    TextView result;
    TextView team1name;
    TextView team1scores;
    TextView team1sovers;
    TextView team2name;
    TextView team2scores;
    TextView team2sovers;
    TextView tournament;
    TextView tvTeamNames;
    TextView tvToss;
    TextView tvmom;
    TextView tvvenue;

    @Nullable
    @RequiresApi(api = 17)
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.match_summary_layout, viewGroup, false);
        this.main_layout = (LinearLayout) inflate.findViewById(R.id.main_layout);
        this.team1name = (TextView) inflate.findViewById(R.id.team1_name);
        this.team2name = (TextView) inflate.findViewById(R.id.team2_name);
        this.team1scores = (TextView) inflate.findViewById(R.id.team1score);
        this.team2scores = (TextView) inflate.findViewById(R.id.team2score);
        this.team1sovers = (TextView) inflate.findViewById(R.id.team1overs);
        this.team2sovers = (TextView) inflate.findViewById(R.id.team2overs);
        this.result = (TextView) inflate.findViewById(R.id.match_result);
        this.tvvenue = (TextView) inflate.findViewById(R.id.tvvenueanddate);
        this.tournament = (TextView) inflate.findViewById(R.id.tvtournament);
        this.tvmom = (TextView) inflate.findViewById(R.id.mom);
        this.tvToss = (TextView) inflate.findViewById(R.id.tvToss);
        this.momlayout = (LinearLayout) inflate.findViewById(R.id.momLayout);
        this.bshare = (Button) inflate.findViewById(R.id.b_share);
        this.tvTeamNames = (TextView) inflate.findViewById(R.id.tv_team_names);
        this.bshare.setVisibility(0);
        this.match = (Match) getArguments().getSerializable(DBHelper.TABLE_MATCH);
        FontProvider.setDroidSansBoldFont(getActivity(), this.team1scores);
        FontProvider.setDroidSansBoldFont(getActivity(), this.team2scores);
        FontProvider.setRobotoCondensedFont(getActivity(), this.team1sovers);
        FontProvider.setRobotoCondensedFont(getActivity(), this.team2sovers);
        FontProvider.setRobotoCondensedFont(getActivity(), this.team1name);
        FontProvider.setRobotoCondensedFont(getActivity(), this.team2name);
        FontProvider.setRobotoCondensedFont(getActivity(), this.tvmom);
        FontProvider.setRobotoCondensedFont(getActivity(), this.result);
        String[] split = this.match.getResult().split(":");
        this.team1name.setText(this.match.getYourteam());
        this.team2name.setText(this.match.getOpponent());
        TextView textView = this.tvTeamNames;
        textView.setText(this.match.getYourteam() + " VS " + this.match.getOpponent());
        TextView textView2 = this.tvvenue;
        textView2.setText(this.match.getVenue() + " " + this.match.getDate() + " " + this.match.getTime());
        if (this.match.getTossResult().equals("")) {
            this.tvToss.setVisibility(8);
        } else {
            this.tvToss.setText(this.match.getTossResult());
        }
        TextView textView3 = this.result;
        textView3.setText(split[0] + "");
        TextView textView4 = this.tournament;
        textView4.setText(this.match.getTournament() + "");
        if (split.length > 1) {
            TextView textView5 = this.tvmom;
            textView5.setText("MOTM: " + split[1]);
        } else {
            this.tvmom.setText("");
        }
        if (!this.match.isTestMatch) {
            TextView textView6 = this.team1scores;
            textView6.setText(this.match.getTeam1().getScore() + "/" + this.match.getTeam1().getWickets());
            TextView textView7 = this.team2scores;
            textView7.setText(this.match.getTeam2().getScore() + "/" + this.match.getTeam2().getWickets());
        } else {
            TextView textView8 = this.team1scores;
            textView8.setText(this.match.getTeam1().getScore() + "/" + this.match.getTeam1().getWickets() + " & " + this.match.getTeam3().getScore() + "/" + this.match.getTeam3().getWickets());
            TextView textView9 = this.team2scores;
            textView9.setText(this.match.getTeam2().getScore() + "/" + this.match.getTeam2().getWickets() + " & " + this.match.getTeam4().getScore() + "/" + this.match.getTeam4().getWickets());
        }
        if (!this.match.isTestMatch) {
            TextView textView10 = this.team1sovers;
            textView10.setText("(" + this.match.getTeam1().getOversPlayed() + "." + this.match.getTeam1().getOverballs() + "/" + this.match.getOvers() + ")");
            TextView textView11 = this.team2sovers;
            textView11.setText("(" + this.match.getTeam2().getOversPlayed() + "." + this.match.getTeam2().getOverballs() + "/" + this.match.getOvers() + ")");
        } else {
            this.team1sovers.setVisibility(8);
            this.team2sovers.setVisibility(8);
        }
        this.bshare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UtilityFunctions.shareDialogImage(MatchSummaryDialog.this, MatchSummaryDialog.this.getActivity());
            }
        });
        getChildFragmentManager().beginTransaction().replace(R.id.team1_players_container, TopThreePlayersFragment.newInstance(this.match.getTeam1().getBatsmans_list(), this.match.getTeam1().getBowlers_list())).commit();
        getChildFragmentManager().beginTransaction().replace(R.id.team2_players_container, TopThreePlayersFragment.newInstance(this.match.getTeam2().getBatsmans_list(), this.match.getTeam2().getBowlers_list())).commit();
        return inflate;
    }
}
