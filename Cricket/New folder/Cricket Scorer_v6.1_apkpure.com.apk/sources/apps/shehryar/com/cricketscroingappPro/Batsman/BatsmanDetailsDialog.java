package apps.shehryar.com.cricketscroingappPro.Batsman;

import android.app.Dialog;
import android.app.DialogFragment;
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
import apps.shehryar.com.cricketscroingappPro.PermissionAsker;
import apps.shehryar.com.cricketscroingappPro.UpgradeToProDialog;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.AppSharerAndRater;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.FontProvider;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.ImageSharer;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.PlayerImageLoader;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.SharedPrefsHelper;

public class BatsmanDetailsDialog extends DialogFragment implements UpgradeToProDialog.UpgradeToProCallBack {
    ImageView asadGameBanner;
    Button bClose;
    Batsman batsman;
    Button bshare;
    private Match match;
    ImageView profileImage;
    TextView tvBat1s;
    TextView tvBat2s;
    TextView tvBat3s;
    TextView tvBat4s;
    TextView tvBat6s;
    TextView tvBatSr;
    TextView tvBatdots;
    TextView tvbatName;
    TextView tvbatScore;

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
        View inflate = layoutInflater.inflate(R.layout.batsmain_details_dialog_layout, viewGroup, false);
        this.batsman = (Batsman) getArguments().getSerializable("batsman");
        this.match = (Match) getArguments().getSerializable(DBHelper.TABLE_MATCH);
        initializeViews(inflate);
        setValues(this.batsman);
        new PlayerImageLoader(getActivity(), this.profileImage, this.batsman).execute(new Object[0]);
        return inflate;
    }

    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(-1, -2);
    }

    private void setValues(Batsman batsman2) {
        this.tvbatName.setText(batsman2.getName());
        TextView textView = this.tvbatScore;
        textView.setText(batsman2.getTotalScore() + "(" + batsman2.getBallsfaced() + ")");
        TextView textView2 = this.tvBatSr;
        StringBuilder sb = new StringBuilder();
        sb.append(batsman2.getStrikerate());
        sb.append("");
        textView2.setText(sb.toString());
        TextView textView3 = this.tvBatdots;
        textView3.setText(batsman2.getDots() + "");
        TextView textView4 = this.tvBat1s;
        textView4.setText(batsman2.getSingles() + "");
        TextView textView5 = this.tvBat2s;
        textView5.setText(batsman2.getDoubles() + "");
        TextView textView6 = this.tvBat3s;
        textView6.setText(batsman2.getThrees() + "");
        TextView textView7 = this.tvBat4s;
        textView7.setText(batsman2.getFours() + "");
        TextView textView8 = this.tvBat6s;
        textView8.setText(batsman2.getSixs() + "");
    }

    /* access modifiers changed from: package-private */
    @RequiresApi(api = 14)
    public void initializeViews(View view) {
        this.tvbatName = (TextView) view.findViewById(R.id.batsman_name);
        this.tvbatScore = (TextView) view.findViewById(R.id.batsmanScore);
        this.tvBatSr = (TextView) view.findViewById(R.id.team_run_rate);
        this.tvBatdots = (TextView) view.findViewById(R.id.tvbatdots);
        this.tvBat1s = (TextView) view.findViewById(R.id.tvbat1s);
        this.tvBat2s = (TextView) view.findViewById(R.id.tvbat2s);
        this.tvBat3s = (TextView) view.findViewById(R.id.tvbat3s);
        this.tvBat4s = (TextView) view.findViewById(R.id.tvbat4s);
        this.tvBat6s = (TextView) view.findViewById(R.id.tvbat6s);
        this.bClose = (Button) view.findViewById(R.id.bdialogClose);
        this.bClose.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BatsmanDetailsDialog.this.dismiss();
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
                AppSharerAndRater.openAsadGameDialog(BatsmanDetailsDialog.this.getActivity());
            }
        });
        view.findViewById(R.id.b_share).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!SharedPrefsHelper.isPro(BatsmanDetailsDialog.this.getActivity())) {
                    BatsmanDetailsDialog.this.showUpgradeToProDialog();
                } else if (!PermissionAsker.checkAPIVersion()) {
                    ImageSharer.shareDialogImage(BatsmanDetailsDialog.this, BatsmanDetailsDialog.this.getActivity());
                } else if (PermissionAsker.checkIfAlreadyhavePermission(BatsmanDetailsDialog.this.getActivity())) {
                    ImageSharer.shareDialogImage(BatsmanDetailsDialog.this, BatsmanDetailsDialog.this.getActivity());
                } else {
                    PermissionAsker.requestForSpecificPermission(105, BatsmanDetailsDialog.this.getActivity());
                }
            }
        });
        try {
            FontProvider.setFiraSansFont(getActivity(), this.tvbatName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.tvbatName.setAllCaps(true);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvbatScore);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvBatdots);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvBatSr);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvBat1s);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvBat2s);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvBat3s);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvBat4s);
        FontProvider.setDroidSansBoldFont(getActivity(), this.tvBat6s);
        TextView textView = (TextView) view.findViewById(R.id.team_opponent);
        FontProvider.setSeguUIFont(getActivity(), textView);
        textView.setText(this.match.getTeam1().getName() + " VS " + this.match.getTeam2().getName());
        try {
            if (this.match != null) {
                ((TextView) view.findViewById(R.id.match_venue_date)).setText(this.match.getVenue() + " " + this.match.getDate());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void showUpgradeToProDialog() {
        UpgradeToProDialog upgradeToProDialog = new UpgradeToProDialog();
        upgradeToProDialog.setListener(this);
        upgradeToProDialog.show(getFragmentManager(), "upgrade to PRO dialog");
    }

    public void onPurchaseSuccessfull() {
        ImageSharer.shareDialogImage(this, getActivity());
    }
}
