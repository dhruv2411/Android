package apps.shehryar.com.cricketscroingappPro.Match;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
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
import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman;
import apps.shehryar.com.cricketscroingappPro.Bowler.Bowler;
import apps.shehryar.com.cricketscroingappPro.MyDialogFragment;
import apps.shehryar.com.cricketscroingappPro.PermissionAsker;
import apps.shehryar.com.cricketscroingappPro.Player.Player;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import apps.shehryar.com.cricketscroingappPro.UpgradeToProDialog;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.FontProvider;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.ImageSharer;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.PlayerImageLoader;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.SharedPrefsHelper;
import de.hdodenhof.circleimageview.CircleImageView;

public class ManOfTheMatchDialogFragment extends MyDialogFragment implements View.OnClickListener, UpgradeToProDialog.UpgradeToProCallBack {
    Activity activity;
    Button bCancel;
    LinearLayout battingLayout1;
    LinearLayout battingLayout2;
    LinearLayout bowlingLayout1;
    LinearLayout bowlingLayout2;
    Context context;
    Player manOfTheMatch;
    Match match;
    ImageView playerImage;
    Team team;
    Team team1;
    Team team2;
    TextView tvBatEco;
    TextView tvBatRuns;
    TextView tvBatStrikeRate;
    TextView tvBatWickets;
    TextView tvDialogTitle;
    TextView tvMatchVenueDate;
    TextView tvTeamName;
    TextView tvTeamOpponent;
    TextView tvplayername;

    public void onPurchaseFailed() {
    }

    public ManOfTheMatchDialogFragment(Context context2, Match match2) {
        this.context = context2;
        this.match = match2;
        this.activity = (Activity) context2;
    }

    public ManOfTheMatchDialogFragment() {
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.man_of_the_match_dialog, viewGroup, false);
        initializeViews(inflate);
        try {
            setFontsToViews();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.match.getManOfTheMatchModel() != null) {
            setValuesToTheViews((Player) null);
        } else {
            new LoadManOfTheMatch().execute(new Match[]{this.match});
        }
        this.tvplayername.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!SharedPrefsHelper.isPro(ManOfTheMatchDialogFragment.this.getActivity())) {
                    ManOfTheMatchDialogFragment.this.showUpgradeToProDialog();
                } else if (!PermissionAsker.checkAPIVersion()) {
                    ImageSharer.shareDialogImage(ManOfTheMatchDialogFragment.this, ManOfTheMatchDialogFragment.this.getActivity());
                } else if (PermissionAsker.checkIfAlreadyhavePermission(ManOfTheMatchDialogFragment.this.getActivity())) {
                    ImageSharer.shareDialogImage(ManOfTheMatchDialogFragment.this, ManOfTheMatchDialogFragment.this.getActivity());
                } else {
                    PermissionAsker.requestForSpecificPermission(105, ManOfTheMatchDialogFragment.this.getActivity());
                }
            }
        });
        this.bCancel.setOnClickListener(this);
        return inflate;
    }

    private void initializeViews(View view) {
        this.tvDialogTitle = (TextView) view.findViewById(R.id.motm_dialog_title);
        this.tvTeamOpponent = (TextView) view.findViewById(R.id.team_opponent);
        this.tvMatchVenueDate = (TextView) view.findViewById(R.id.match_venue_date);
        this.playerImage = (CircleImageView) view.findViewById(R.id.motm_player_image);
        this.tvplayername = (TextView) view.findViewById(R.id.momName);
        this.tvTeamName = (TextView) view.findViewById(R.id.momTeam);
        this.tvBatRuns = (TextView) view.findViewById(R.id.motm_batsman_runs);
        this.battingLayout1 = (LinearLayout) view.findViewById(R.id.batsman_stats_layout);
        this.battingLayout2 = (LinearLayout) view.findViewById(R.id.batsman_stats_layout_2);
        this.tvBatStrikeRate = (TextView) view.findViewById(R.id.motm_strike_rate);
        this.bowlingLayout1 = (LinearLayout) view.findViewById(R.id.bowling_stats_layout);
        this.tvBatWickets = (TextView) view.findViewById(R.id.motm_batsman_wickets);
        this.bowlingLayout2 = (LinearLayout) view.findViewById(R.id.bowling_stats_layout_2);
        this.tvBatEco = (TextView) view.findViewById(R.id.motm_batsman_eco);
        this.bCancel = (Button) view.findViewById(R.id.bdialogClose);
        this.bCancel.setOnClickListener(this);
        this.tvplayername.setOnClickListener(this);
    }

    private void setFontsToViews() throws Exception {
        try {
            FontProvider.setSeguUIFont(getActivity(), this.tvTeamOpponent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FontProvider.setSeguUIFont(getActivity(), this.tvDialogTitle);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        FontProvider.setSeguUIFont(getActivity(), this.tvplayername);
        FontProvider.setFiraSansFont(getActivity(), this.tvTeamName);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvBatRuns);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvBatEco);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvBatStrikeRate);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvBatWickets);
    }

    /* access modifiers changed from: private */
    public void setValuesToTheViews(Player player) {
        if (this.match.getManOfTheMatchModel() != null) {
            Batsman batsman = this.match.getManOfTheMatchModel().getBatsman();
            Bowler bowler = this.match.getManOfTheMatchModel().getBowler();
            if (bowler != null) {
                this.tvplayername.setText(bowler.getName());
                this.tvTeamName.setText(bowler.getTeamName());
            }
            if (batsman != null) {
                this.tvplayername.setText(batsman.getName());
                this.tvTeamName.setText(batsman.getTeam_Name());
            }
            try {
                TextView textView = this.tvTeamOpponent;
                textView.setText(this.match.getYourteam() + " VS " + this.match.getOpponent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (this.match != null) {
                    TextView textView2 = this.tvMatchVenueDate;
                    textView2.setText(this.match.getVenue() + " " + this.match.getDate());
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (batsman != null) {
                TextView textView3 = this.tvBatRuns;
                textView3.setText(batsman.getTotalScore() + "(" + batsman.getBallsfaced() + ")");
                TextView textView4 = this.tvBatStrikeRate;
                StringBuilder sb = new StringBuilder();
                sb.append(batsman.getStrikerate());
                sb.append("");
                textView4.setText(sb.toString());
                if (batsman.getTotalScore() == 0) {
                    this.battingLayout1.setVisibility(8);
                    this.battingLayout2.setVisibility(8);
                }
            } else {
                this.battingLayout1.setVisibility(8);
                this.battingLayout2.setVisibility(8);
            }
            if (bowler != null) {
                TextView textView5 = this.tvBatWickets;
                textView5.setText(bowler.getWickets() + "");
                TextView textView6 = this.tvBatEco;
                textView6.setText(bowler.getEco() + "");
                if (bowler.getWickets() == 0) {
                    this.bowlingLayout2.setVisibility(8);
                    this.bowlingLayout1.setVisibility(8);
                }
            } else {
                this.bowlingLayout2.setVisibility(8);
                this.bowlingLayout1.setVisibility(8);
            }
            this.tvDialogTitle.setText("Man of The Match");
        } else if (player != null) {
            this.tvplayername.setText(player.getName());
            this.tvTeamName.setText(player.getTeamName());
            try {
                TextView textView7 = this.tvTeamOpponent;
                textView7.setText(this.match.getYourteam() + " VS " + this.match.getOpponent());
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            try {
                if (this.match != null) {
                    TextView textView8 = this.tvMatchVenueDate;
                    textView8.setText(this.match.getVenue() + " " + this.match.getDate());
                }
            } catch (Exception e4) {
                e4.printStackTrace();
            }
            TextView textView9 = this.tvBatRuns;
            textView9.setText(player.getScore() + "(" + player.getBalls() + ")");
            TextView textView10 = this.tvBatStrikeRate;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(String.format("%.2f", new Object[]{Double.valueOf(player.getBattingStrikeRate())}));
            sb2.append("");
            textView10.setText(sb2.toString());
            TextView textView11 = this.tvBatWickets;
            textView11.setText(player.getWickets() + "");
            TextView textView12 = this.tvBatEco;
            textView12.setText(String.format("%.2f", new Object[]{Double.valueOf(player.getDoubleBowlingEco())}) + "");
            this.tvDialogTitle.setText("Man of The Match");
            if (player.getScore() == 0) {
                this.battingLayout1.setVisibility(8);
                this.battingLayout2.setVisibility(8);
            }
            if (player.getWickets() == 0) {
                this.bowlingLayout2.setVisibility(8);
                this.bowlingLayout1.setVisibility(8);
            }
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.bdialogClose) {
            dismiss();
        }
    }

    /* access modifiers changed from: private */
    @RequiresApi(api = 17)
    public void showUpgradeToProDialog() {
        UpgradeToProDialog upgradeToProDialog = new UpgradeToProDialog();
        upgradeToProDialog.setListener(this);
        upgradeToProDialog.show(getChildFragmentManager(), "upgrade to PRO dialog");
    }

    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(-1, -2);
    }

    public void onPurchaseSuccessfull() {
        ImageSharer.shareDialogImage(this, getActivity());
    }

    private class LoadManOfTheMatch extends AsyncTask<Match, Void, Player> {
        private LoadManOfTheMatch() {
        }

        /* access modifiers changed from: protected */
        public Player doInBackground(Match... matchArr) {
            try {
                return ManOfTheMatch.selectManOfTheMatch(matchArr[0].getWinningTeam());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Player player) {
            super.onPostExecute(player);
            if (player != null) {
                ManOfTheMatchDialogFragment.this.setValuesToTheViews(player);
                new PlayerImageLoader(ManOfTheMatchDialogFragment.this.getActivity(), ManOfTheMatchDialogFragment.this.playerImage, player).execute(new Object[0]);
            }
        }
    }
}
