package apps.shehryar.com.cricketscroingappPro;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Fragments.BaseDialogFragment;

public class NoBallByeLegByeDialog extends BaseDialogFragment implements View.OnClickListener {
    public static final String EXTRA = "EXTRA";
    Button bByes;
    Button bLegByes;
    Button bNone;
    int extra;
    MainActivity_Test obj;

    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof MainActivity_Test) {
            this.obj = (MainActivity_Test) getActivity();
        }
    }

    public static NoBallByeLegByeDialog newInstance(int i) {
        NoBallByeLegByeDialog noBallByeLegByeDialog = new NoBallByeLegByeDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA, i);
        noBallByeLegByeDialog.setArguments(bundle);
        return noBallByeLegByeDialog;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.extra = getArguments().getInt(EXTRA);
        }
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
        this.bByes.setOnClickListener(this);
        this.bLegByes.setOnClickListener(this);
        this.bNone.setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id != R.id.bnone) {
            switch (id) {
                case R.id.blegbyes /*2131755294*/:
                    if (this.obj != null) {
                        this.obj.manageExtranos(this.extra, true);
                    }
                    dismiss();
                    return;
                case R.id.bbyes /*2131755295*/:
                    if (this.obj != null) {
                        this.obj.manageExtranos(this.extra, true);
                    }
                    dismiss();
                    return;
                default:
                    return;
            }
        } else {
            if (this.obj != null) {
                this.obj.manageExtranos(this.extra, false);
            }
            dismiss();
        }
    }
}
