package apps.shehryar.com.cricketscroingappPro.Ranking;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.MyDialogFragment;
import apps.shehryar.com.cricketscroingappPro.MyToast;
import apps.shehryar.com.cricketscroingappPro.PermissionAsker;
import apps.shehryar.com.cricketscroingappPro.UpgradeToProDialog;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.FontProvider;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.ImageSharer;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.SharedPrefsHelper;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class DialogBatsmanRanking extends MyDialogFragment implements UpgradeToProDialog.UpgradeToProCallBack {
    ArrayList<Long> allTeamIds;
    Button bShare;
    Batsman batsman;
    ImageView profileImage;
    Spinner spTeamNames;
    TextView textView;
    String tournamentName;
    TextView tvNoData;
    TextView tvPoints;
    TextView tvRanking;
    TextView tvTeamName;
    TextView tvTitle;
    TextView tvTournamentName;
    TextView tvbatavg;
    TextView tvbatbest;
    TextView tvbatfifties;
    TextView tvbathundreds;
    TextView tvbatmatches;
    TextView tvbatscore;
    TextView tvbatsr;
    TextView tvbatthirtes;
    TextView tvfours;
    TextView tvnotOuts;
    TextView tvsixes;

    public static DialogBatsmanRanking newInstance(Batsman batsman2) {
        DialogBatsmanRanking dialogBatsmanRanking = new DialogBatsmanRanking();
        Bundle bundle = new Bundle();
        bundle.putSerializable("batsman", batsman2);
        dialogBatsmanRanking.setArguments(bundle);
        return dialogBatsmanRanking;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.batsman_ranking_dialog_layout, viewGroup, false);
        this.spTeamNames = (Spinner) inflate.findViewById(R.id.sp_team_names);
        this.tvTournamentName = (TextView) inflate.findViewById(R.id.tournament_name);
        this.textView = (TextView) inflate.findViewById(R.id.tvbatsmanname);
        this.tvbatthirtes = (TextView) inflate.findViewById(R.id.tvbatthirties);
        this.tvbatfifties = (TextView) inflate.findViewById(R.id.tvbatfifties);
        this.tvbathundreds = (TextView) inflate.findViewById(R.id.tvbathundreds);
        this.profileImage = (ImageView) inflate.findViewById(R.id.profile_image);
        this.tvbatscore = (TextView) inflate.findViewById(R.id.tv_bat_runs);
        this.tvbatavg = (TextView) inflate.findViewById(R.id.tv_bat_average);
        this.tvbatmatches = (TextView) inflate.findViewById(R.id.tv_bat_matches);
        this.tvbatbest = (TextView) inflate.findViewById(R.id.tv_bat_best);
        this.tvbatsr = (TextView) inflate.findViewById(R.id.tv_bat_sr);
        this.tvRanking = (TextView) inflate.findViewById(R.id.tv_bat_ranking);
        this.tvPoints = (TextView) inflate.findViewById(R.id.tv_bat_points);
        this.bShare = (Button) inflate.findViewById(R.id.b_share);
        this.tvTeamName = (TextView) inflate.findViewById(R.id.tv_team_name);
        this.tvnotOuts = (TextView) inflate.findViewById(R.id.tvbatnotOut);
        this.tvfours = (TextView) inflate.findViewById(R.id.tvbatfours);
        this.tvsixes = (TextView) inflate.findViewById(R.id.tvbatsixes);
        this.tvTitle = (TextView) inflate.findViewById(R.id.batsman_ranking_dialog_title);
        this.batsman = (Batsman) getArguments().getSerializable("batsman");
        this.tournamentName = getArguments().getString("tname");
        this.allTeamIds = (ArrayList) getArguments().getSerializable("allTeamIds");
        if (this.batsman.getTeamNames() != null) {
            this.tvTeamName.setVisibility(8);
            this.spTeamNames.setAdapter(new ArrayAdapter(getActivity(), 17367049, this.batsman.getTeamNames()));
            this.spTeamNames.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onNothingSelected(AdapterView<?> adapterView) {
                }

                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                    DialogBatsmanRanking.this.batsman = new DBHelper(DialogBatsmanRanking.this.getActivity()).getYourBatsmanStats((String) adapterView.getItemAtPosition(i), DialogBatsmanRanking.this.batsman.getName(), DialogBatsmanRanking.this.allTeamIds);
                    DialogBatsmanRanking.this.showBatsmanData(DialogBatsmanRanking.this.batsman);
                }
            });
        } else {
            this.spTeamNames.setVisibility(8);
        }
        if (this.tournamentName != null) {
            this.tvTournamentName.setText(this.tournamentName);
            this.tvTitle.setText("Best Batsman");
        } else {
            this.tvTournamentName.setVisibility(8);
        }
        try {
            FontProvider.setFiraSansFont(getActivity(), this.textView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.textView.setAllCaps(true);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvbatthirtes);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvbatfifties);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvbathundreds);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvbatscore);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvbatsr);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvbatavg);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvbatmatches);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvbatbest);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvPoints);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvRanking);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvnotOuts);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvfours);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvsixes);
        FontProvider.setSeguUIFont(getActivity(), this.tvTitle);
        showBatsmanData(this.batsman);
        this.bShare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!SharedPrefsHelper.isPro(DialogBatsmanRanking.this.getActivity())) {
                    DialogBatsmanRanking.this.showUpgradeToProDialog();
                } else if (!PermissionAsker.checkAPIVersion()) {
                    ImageSharer.shareDialogImage(DialogBatsmanRanking.this, DialogBatsmanRanking.this.getActivity());
                } else if (PermissionAsker.checkIfAlreadyhavePermission(DialogBatsmanRanking.this.getActivity())) {
                    ImageSharer.shareDialogImage(DialogBatsmanRanking.this, DialogBatsmanRanking.this.getActivity());
                } else {
                    PermissionAsker.requestForSpecificPermission(105, DialogBatsmanRanking.this.getActivity());
                }
            }
        });
        return inflate;
    }

    /* access modifiers changed from: private */
    public void showBatsmanData(Batsman batsman2) {
        this.textView.setText(batsman2.getName());
        TextView textView2 = this.tvbatfifties;
        textView2.setText(batsman2.getFifties() + "");
        TextView textView3 = this.tvbatthirtes;
        textView3.setText(batsman2.getThirties() + "");
        TextView textView4 = this.tvbathundreds;
        textView4.setText(batsman2.getHundreds() + "");
        TextView textView5 = this.tvbatscore;
        textView5.setText(batsman2.getTotalScore() + "");
        TextView textView6 = this.tvbatmatches;
        textView6.setText(batsman2.getMatches() + "");
        TextView textView7 = this.tvbatbest;
        textView7.setText(batsman2.getBest() + "");
        TextView textView8 = this.tvbatavg;
        textView8.setText(batsman2.getAverage() + "");
        TextView textView9 = this.tvbatsr;
        textView9.setText(batsman2.getStrikerate() + "");
        if (batsman2.getRanking() > 0) {
            TextView textView10 = this.tvRanking;
            textView10.setText("RANKING # " + batsman2.getRanking() + "");
        } else {
            this.tvRanking.setVisibility(8);
        }
        TextView textView11 = this.tvPoints;
        textView11.setText(batsman2.getPoints() + "");
        TextView textView12 = this.tvTeamName;
        textView12.setText(batsman2.getTeam_Name() + "");
        TextView textView13 = this.tvnotOuts;
        textView13.setText(batsman2.getNoOfNotOuts() + "");
        TextView textView14 = this.tvfours;
        textView14.setText(batsman2.getFours() + "");
        TextView textView15 = this.tvsixes;
        textView15.setText(batsman2.getSixs() + "");
        if (batsman2.getImage() != null) {
            Picasso.with(getActivity()).load(Uri.parse(batsman2.getImage())).into(this.profileImage);
        } else {
            this.profileImage.setImageResource(R.drawable.ic_player_profile);
        }
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
        MyToast.showToast(getActivity(), "Sorry but you cannot share without Upgrading");
    }
}
