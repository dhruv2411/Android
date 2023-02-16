package apps.shehryar.com.cricketscroingappPro.Bowler;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
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
import apps.shehryar.com.cricketscroingappPro.Overs.Over;
import apps.shehryar.com.cricketscroingappPro.PermissionAsker;
import apps.shehryar.com.cricketscroingappPro.UpgradeToProDialog;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.AppSharerAndRater;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.FontProvider;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.ImageSharer;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.PlayerImageLoader;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.SharedPrefsHelper;
import java.util.ArrayList;

public class BowlerDetailsDialog extends DialogFragment implements UpgradeToProDialog.UpgradeToProCallBack {
    ImageView asadGameBanner;
    Button bClose;
    TextView bowelrMaidens;
    Bowler bowler;
    TextView bowlerDots;
    TextView bowlerEconomy;
    TextView bowlerFours;
    TextView bowlerNos;
    TextView bowlerOvers;
    TextView bowlerScore;
    TextView bowlerSixes;
    TextView bowlerWickets;
    TextView bowlerWides;
    Button bshare;
    private Match match;
    ArrayList<Over> overs;
    ImageView profileImage;
    TextView tvbatName;

    public void onPurchaseFailed() {
    }

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
        View inflate = layoutInflater.inflate(R.layout.bowler_details_layout, viewGroup, false);
        this.bowler = (Bowler) getArguments().getSerializable("bowler");
        this.overs = (ArrayList) getArguments().getSerializable(DBHelper.TABLE_OVERS);
        this.match = (Match) getArguments().getSerializable(DBHelper.TABLE_MATCH);
        Log.i("bowler overs", this.bowler.getBowlerOvers() + "." + this.bowler.getBalls());
        initializeViews(inflate);
        new LoadBowlerStats().execute(new Object[0]);
        new PlayerImageLoader(getActivity(), this.profileImage, this.bowler).execute(new Object[0]);
        return inflate;
    }

    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(-1, -2);
    }

    public class LoadBowlerStats extends AsyncTask {
        public LoadBowlerStats() {
        }

        /* access modifiers changed from: protected */
        public Object doInBackground(Object[] objArr) {
            try {
                BowlerHelper.setStatsToBowlerFromOver(BowlerDetailsDialog.this.bowler, BowlerDetailsDialog.this.overs);
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Object obj) {
            super.onPostExecute(obj);
            BowlerDetailsDialog.this.setValues(BowlerDetailsDialog.this.bowler);
        }
    }

    /* access modifiers changed from: private */
    public void setValues(Bowler bowler2) {
        this.tvbatName.setText(bowler2.getName());
        TextView textView = this.bowlerScore;
        textView.setText(bowler2.getTotalscore() + "");
        TextView textView2 = this.bowlerOvers;
        textView2.setText(bowler2.getBowlerovers() + "." + bowler2.getBalls());
        TextView textView3 = this.bowlerWickets;
        textView3.setText(bowler2.getWickets() + "");
        TextView textView4 = this.bowelrMaidens;
        textView4.setText(bowler2.getMaidens() + "");
        TextView textView5 = this.bowlerEconomy;
        textView5.setText(bowler2.getEco() + "");
        TextView textView6 = this.bowlerDots;
        textView6.setText(bowler2.getDots() + "");
        TextView textView7 = this.bowlerFours;
        textView7.setText(bowler2.getFours() + "");
        TextView textView8 = this.bowlerSixes;
        textView8.setText(bowler2.getSixes() + "");
        TextView textView9 = this.bowlerWides;
        textView9.setText(bowler2.getWides() + "");
        TextView textView10 = this.bowlerNos;
        textView10.setText(bowler2.getNos() + "");
    }

    /* access modifiers changed from: package-private */
    @RequiresApi(api = 14)
    public void initializeViews(View view) {
        this.tvbatName = (TextView) view.findViewById(R.id.batsman_name);
        this.bowlerOvers = (TextView) view.findViewById(R.id.bowler_overs);
        this.bowlerScore = (TextView) view.findViewById(R.id.bowler_score);
        this.bowlerWickets = (TextView) view.findViewById(R.id.bowler_wickets);
        this.bowlerEconomy = (TextView) view.findViewById(R.id.bowler_economy);
        this.bowelrMaidens = (TextView) view.findViewById(R.id.bowler_maidens);
        this.bowlerDots = (TextView) view.findViewById(R.id.bowler_dots);
        this.bowlerFours = (TextView) view.findViewById(R.id.bowler_fours);
        this.bowlerSixes = (TextView) view.findViewById(R.id.bowler_sixes);
        this.bowlerWides = (TextView) view.findViewById(R.id.bowler_wides);
        this.bowlerNos = (TextView) view.findViewById(R.id.bowler_nos);
        this.bClose = (Button) view.findViewById(R.id.bdialogClose);
        this.bClose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BowlerDetailsDialog.this.dismiss();
            }
        });
        this.profileImage = (ImageView) view.findViewById(R.id.profile_image);
        this.asadGameBanner = (ImageView) view.findViewById(R.id.asad_game_banner);
        if (SharedPrefsHelper.isPro(getActivity())) {
            this.asadGameBanner.setVisibility(8);
        } else {
            this.asadGameBanner.setVisibility(0);
        }
        this.asadGameBanner.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AppSharerAndRater.openAsadGameDialog(BowlerDetailsDialog.this.getActivity());
            }
        });
        view.findViewById(R.id.b_share).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!SharedPrefsHelper.isPro(BowlerDetailsDialog.this.getActivity())) {
                    BowlerDetailsDialog.this.showUpgradeToProDialog();
                } else if (!PermissionAsker.checkAPIVersion()) {
                    ImageSharer.shareDialogImage(BowlerDetailsDialog.this, BowlerDetailsDialog.this.getActivity());
                } else if (PermissionAsker.checkIfAlreadyhavePermission(BowlerDetailsDialog.this.getActivity())) {
                    ImageSharer.shareDialogImage(BowlerDetailsDialog.this, BowlerDetailsDialog.this.getActivity());
                } else {
                    PermissionAsker.requestForSpecificPermission(105, BowlerDetailsDialog.this.getActivity());
                }
            }
        });
        try {
            FontProvider.setFiraSansFont(getActivity(), this.tvbatName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.tvbatName.setAllCaps(true);
        FontProvider.setDroidSansBoldFont(getActivity(), this.bowlerScore);
        FontProvider.setDroidSansBoldFont(getActivity(), this.bowlerOvers);
        FontProvider.setDroidSansBoldFont(getActivity(), this.bowlerWickets);
        FontProvider.setDroidSansBoldFont(getActivity(), this.bowelrMaidens);
        FontProvider.setDroidSansBoldFont(getActivity(), this.bowlerEconomy);
        FontProvider.setDroidSansBoldFont(getActivity(), this.bowlerDots);
        FontProvider.setDroidSansBoldFont(getActivity(), this.bowlerFours);
        FontProvider.setDroidSansBoldFont(getActivity(), this.bowlerSixes);
        FontProvider.setDroidSansBoldFont(getActivity(), this.bowlerWides);
        FontProvider.setDroidSansBoldFont(getActivity(), this.bowlerNos);
        TextView textView = (TextView) view.findViewById(R.id.team_opponent);
        FontProvider.setSeguUIFont(getActivity(), textView);
        textView.setText(this.match.getYourteam() + " VS " + this.match.getOpponent());
        try {
            if (this.match != null) {
                ((TextView) view.findViewById(R.id.match_venue_date)).setText(this.match.getVenue() + " " + this.match.getDate());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
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
}
