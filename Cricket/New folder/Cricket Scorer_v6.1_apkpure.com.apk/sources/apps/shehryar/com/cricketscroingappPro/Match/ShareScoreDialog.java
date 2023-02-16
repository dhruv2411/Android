package apps.shehryar.com.cricketscroingappPro.Match;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.MyDialogFragment;
import apps.shehryar.com.cricketscroingappPro.PermissionAsker;
import apps.shehryar.com.cricketscroingappPro.UpgradeToProDialog;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.AppSharerAndRater;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.ImageSharer;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.SharedPrefsHelper;
import com.google.firebase.analytics.FirebaseAnalytics;

public class ShareScoreDialog extends MyDialogFragment implements View.OnClickListener, UpgradeToProDialog.UpgradeToProCallBack {
    Button bImage;
    Button bNone;
    Button btext;
    String score;

    public void onPurchaseFailed() {
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.no_ball_bye_legbye, viewGroup, false);
        this.score = getArguments().getString(FirebaseAnalytics.Param.SCORE);
        initializeViews(inflate);
        return inflate;
    }

    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(-1, -2);
    }

    /* access modifiers changed from: package-private */
    public void initializeViews(View view) {
        this.btext = (Button) view.findViewById(R.id.bbyes);
        this.bImage = (Button) view.findViewById(R.id.blegbyes);
        this.bNone = (Button) view.findViewById(R.id.bnone);
        this.btext.setText("Share as Text");
        this.bImage.setText("Share as Image");
        this.bNone.setVisibility(8);
        this.btext.setOnClickListener(this);
        this.bImage.setOnClickListener(this);
        this.bNone.setOnClickListener(this);
    }

    @RequiresApi(api = 17)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.blegbyes /*2131755294*/:
                if (SharedPrefsHelper.isPro(getActivity())) {
                    if (!PermissionAsker.checkAPIVersion()) {
                        ImageSharer.shareImage(getActivity());
                    } else if (PermissionAsker.checkIfAlreadyhavePermission(getActivity())) {
                        ImageSharer.shareImage(getActivity());
                    } else {
                        PermissionAsker.requestForSpecificPermission(105, getActivity());
                    }
                    dismiss();
                    return;
                }
                dismiss();
                showUpgradeToProDialog();
                return;
            case R.id.bbyes /*2131755295*/:
                AppSharerAndRater.shareCurrentScore(this.score, getActivity());
                dismiss();
                return;
            default:
                return;
        }
    }

    @RequiresApi(api = 17)
    private void showUpgradeToProDialog() {
        new UpgradeToProDialog().show(getFragmentManager(), "upgrade to PRO dialog");
    }

    public void onPurchaseSuccessfull() {
        ImageSharer.shareDialogImage(this, getActivity());
    }
}
