package apps.shehryar.com.cricketscroingappPro;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.WindowManager;

public class MyDialogFragment extends DialogFragment {
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog onCreateDialog = super.onCreateDialog(bundle);
        onCreateDialog.getWindow().requestFeature(1);
        onCreateDialog.setCancelable(true);
        onCreateDialog.getWindow().getAttributes().windowAnimations = 16973826;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(onCreateDialog.getWindow().getAttributes());
        layoutParams.width = -1;
        onCreateDialog.getWindow().setAttributes(layoutParams);
        return onCreateDialog;
    }

    public void onResume() {
        super.onResume();
        try {
            getDialog().getWindow().setLayout(-1, -2);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
