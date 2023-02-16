package apps.shehryar.com.cricketscroingappPro.Ranking;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import apps.shehryar.com.cricketscroingappPro.Bowler.Bowler;
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

public class DialogBowlerRanking extends MyDialogFragment implements UpgradeToProDialog.UpgradeToProCallBack {
    /* access modifiers changed from: private */
    public ArrayList<Long> allTeamIds;
    Button bShare;
    Bowler bowler;
    ImageView profileImage;
    private Spinner spTeamNames;
    TextView textView;
    String tournament_name;
    TextView tvBowlAverage;
    TextView tvBowlBest;
    TextView tvBowlEco;
    TextView tvBowlMatches;
    TextView tvBowlName;
    TextView tvBowlPoints;
    TextView tvBowlWickets;
    TextView tvFiveWickets;
    TextView tvMaidens;
    TextView tvNoData;
    TextView tvRanking;
    TextView tvTeamName;
    TextView tvThreeWickets;
    TextView tvTitle;
    TextView tvTournamentName;

    public static DialogBowlerRanking newInstance(Bowler bowler2) {
        DialogBowlerRanking dialogBowlerRanking = new DialogBowlerRanking();
        Bundle bundle = new Bundle();
        bundle.putSerializable("bowler", bowler2);
        dialogBowlerRanking.setArguments(bundle);
        return dialogBowlerRanking;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.bowler_ranking_dialog_layout, viewGroup, false);
        this.spTeamNames = (Spinner) inflate.findViewById(R.id.sp_team_names);
        this.tvTournamentName = (TextView) inflate.findViewById(R.id.tournament_name);
        this.textView = (TextView) inflate.findViewById(R.id.tvbatsmanname);
        this.tvNoData = (TextView) inflate.findViewById(R.id.tvNoData);
        this.tvBowlWickets = (TextView) inflate.findViewById(R.id.tv_bowl_wickets);
        this.tvBowlMatches = (TextView) inflate.findViewById(R.id.tv_bowl_matches);
        this.tvBowlBest = (TextView) inflate.findViewById(R.id.tv_bowl_best);
        this.tvBowlAverage = (TextView) inflate.findViewById(R.id.tv_bowl_average);
        this.tvBowlEco = (TextView) inflate.findViewById(R.id.tv_bowl_economy);
        this.tvTeamName = (TextView) inflate.findViewById(R.id.tv_team_name);
        this.tvRanking = (TextView) inflate.findViewById(R.id.tv_bat_ranking);
        this.profileImage = (ImageView) inflate.findViewById(R.id.profile_image);
        this.tvBowlPoints = (TextView) inflate.findViewById(R.id.tv_bat_points);
        this.bShare = (Button) inflate.findViewById(R.id.b_share);
        this.tvTitle = (TextView) inflate.findViewById(R.id.bowler_ranking_dialog_title);
        this.tvMaidens = (TextView) inflate.findViewById(R.id.tv_bowl_maidens);
        this.tvThreeWickets = (TextView) inflate.findViewById(R.id.tv_bowl_three_wickets);
        this.tvFiveWickets = (TextView) inflate.findViewById(R.id.tv_bowl_five_wickets);
        this.bowler = (Bowler) getArguments().getSerializable("bowler");
        this.tournament_name = getArguments().getString("tname");
        this.allTeamIds = (ArrayList) getArguments().getSerializable("allTeamIds");
        if (this.bowler.getTeamNames() != null) {
            this.tvTeamName.setVisibility(8);
            this.spTeamNames.setAdapter(new ArrayAdapter(getActivity(), 17367049, this.bowler.getTeamNames()));
            this.spTeamNames.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onNothingSelected(AdapterView<?> adapterView) {
                }

                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                    String str = (String) adapterView.getItemAtPosition(i);
                    try {
                        DialogBowlerRanking.this.bowler = new DBHelper(DialogBowlerRanking.this.getActivity()).getYourBowlerStats(str, DialogBowlerRanking.this.bowler.getName(), DialogBowlerRanking.this.allTeamIds);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    DialogBowlerRanking.this.showBowlerData(DialogBowlerRanking.this.bowler);
                }
            });
        } else {
            this.spTeamNames.setVisibility(8);
        }
        showBowlerData(this.bowler);
        this.bShare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!SharedPrefsHelper.isPro(DialogBowlerRanking.this.getActivity())) {
                    return;
                }
                if (!PermissionAsker.checkAPIVersion()) {
                    ImageSharer.shareDialogImage(DialogBowlerRanking.this, DialogBowlerRanking.this.getActivity());
                } else if (PermissionAsker.checkIfAlreadyhavePermission(DialogBowlerRanking.this.getActivity())) {
                    ImageSharer.shareDialogImage(DialogBowlerRanking.this, DialogBowlerRanking.this.getActivity());
                } else {
                    PermissionAsker.requestForSpecificPermission(105, DialogBowlerRanking.this.getActivity());
                }
            }
        });
        return inflate;
    }

    /* access modifiers changed from: private */
    public void showBowlerData(Bowler bowler2) {
        this.textView.setText(bowler2.getName());
        if (this.tournament_name != null) {
            this.tvTournamentName.setText(this.tournament_name);
            this.tvTitle.setText("BEST BOWLER");
        } else {
            this.tvTournamentName.setVisibility(8);
        }
        if (bowler2.getRanking() > 0) {
            TextView textView2 = this.tvRanking;
            textView2.setText("RANKING# " + bowler2.getRanking() + "");
        } else {
            this.tvRanking.setVisibility(8);
        }
        TextView textView3 = this.tvBowlPoints;
        textView3.setText(bowler2.getPoints() + "");
        TextView textView4 = this.tvTeamName;
        textView4.setText(bowler2.getTeamName() + "");
        TextView textView5 = this.tvBowlWickets;
        textView5.setText(bowler2.getWickets() + "");
        TextView textView6 = this.tvBowlMatches;
        textView6.setText(bowler2.getMatches() + "");
        TextView textView7 = this.tvBowlBest;
        textView7.setText(bowler2.getBest() + "");
        TextView textView8 = this.tvBowlAverage;
        textView8.setText(bowler2.getAverage() + "");
        TextView textView9 = this.tvBowlEco;
        textView9.setText(bowler2.getEco() + "");
        TextView textView10 = this.tvMaidens;
        textView10.setText(bowler2.getMaidens() + "");
        TextView textView11 = this.tvThreeWickets;
        textView11.setText(bowler2.getThreeFor() + "");
        TextView textView12 = this.tvFiveWickets;
        textView12.setText(bowler2.getFiveFor() + "");
        try {
            FontProvider.setFiraSansFont(getActivity(), this.textView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.textView.setAllCaps(true);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvBowlWickets);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvBowlMatches);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvBowlBest);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvBowlAverage);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvBowlEco);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvBowlPoints);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvMaidens);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvThreeWickets);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvFiveWickets);
        FontProvider.setSeguUIFont(getActivity(), this.tvTitle);
        if (bowler2.getImage() != null) {
            Picasso.with(getActivity()).load(Uri.parse(bowler2.getImage())).into(this.profileImage);
        } else {
            this.profileImage.setImageResource(R.drawable.ic_player_profile);
        }
    }

    private void showUpgradeToProDialog() {
        UpgradeToProDialog upgradeToProDialog = new UpgradeToProDialog();
        upgradeToProDialog.setListener(this);
        upgradeToProDialog.show(getFragmentManager(), "upgrade to PRO dialog");
    }

    public void onPurchaseSuccessfull() {
        ImageSharer.shareDialogImage(this, getActivity());
    }

    public void onPurchaseFailed() {
        MyToast.showToast(getActivity(), "Sorry but you cannot share without Upgrading");
    }
}
