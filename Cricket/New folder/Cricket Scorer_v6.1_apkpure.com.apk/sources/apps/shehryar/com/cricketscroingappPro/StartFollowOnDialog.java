package apps.shehryar.com.cricketscroingappPro;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import apps.shehryar.com.cricketscroingapp.R;

public class StartFollowOnDialog extends DialogFragment implements View.OnClickListener {
    Button bByes;
    Button bLegByes;
    Button bNone;
    int index;
    StartFollowOnDialogListener listener;

    public interface StartFollowOnDialogListener {
        void onStartFollowOnTapped(int i);

        void onStartNextInningsTapped(int i);
    }

    public StartFollowOnDialog() {
    }

    public StartFollowOnDialog(int i, StartFollowOnDialogListener startFollowOnDialogListener) {
        this.index = i;
        this.listener = startFollowOnDialogListener;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Dialog onCreateDialog = super.onCreateDialog(bundle);
        onCreateDialog.getWindow().requestFeature(1);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        onCreateDialog.getWindow().getAttributes().windowAnimations = 16973826;
        layoutParams.copyFrom(onCreateDialog.getWindow().getAttributes());
        layoutParams.width = -1;
        onCreateDialog.getWindow().setAttributes(layoutParams);
        onCreateDialog.setCancelable(false);
        onCreateDialog.setCanceledOnTouchOutside(false);
        return onCreateDialog;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.no_ball_bye_legbye, viewGroup, false);
        initializeViews(inflate);
        return inflate;
    }

    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(-1, -2);
    }

    /* access modifiers changed from: package-private */
    public void initializeViews(View view) {
        this.bByes = (Button) view.findViewById(R.id.bbyes);
        this.bLegByes = (Button) view.findViewById(R.id.blegbyes);
        this.bNone = (Button) view.findViewById(R.id.bnone);
        this.bByes.setText("Start Follow On");
        this.bLegByes.setText("Start Next Innings");
        this.bNone.setVisibility(8);
        this.bByes.setOnClickListener(this);
        this.bLegByes.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.blegbyes /*2131755294*/:
                if (this.listener != null) {
                    this.listener.onStartNextInningsTapped(this.index);
                    return;
                }
                return;
            case R.id.bbyes /*2131755295*/:
                if (this.listener != null) {
                    this.listener.onStartFollowOnTapped(this.index);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
