package apps.shehryar.com.cricketscroingappPro.Player;

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

public class PlayerEditDeleteDialog extends BaseDialogFragment implements View.OnClickListener {
    Button bDelete;
    Button bEdit;
    Button bNone;
    Button bViewStats;
    EditDeleteCallBack editDeleteCallBack;
    int extra;
    boolean isInPlayingXI;

    public interface EditDeleteCallBack {
        void onAddInPlayingXiTapped(boolean z) throws Exception;

        void onChooseCaptainTapped();

        void onDeleteTapped();

        void onEditTapped();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof EditDeleteCallBack) {
            this.editDeleteCallBack = (EditDeleteCallBack) context;
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
        this.isInPlayingXI = getArguments().getBoolean("inPlayingXI", false);
        if (this.editDeleteCallBack == null) {
            this.editDeleteCallBack = (EditDeleteCallBack) getActivity();
        }
        initializeViews(inflate);
        return inflate;
    }

    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(-1, -2);
    }

    /* access modifiers changed from: package-private */
    public void initializeViews(View view) {
        this.bEdit = (Button) view.findViewById(R.id.bbyes);
        this.bDelete = (Button) view.findViewById(R.id.blegbyes);
        this.bNone = (Button) view.findViewById(R.id.bnone);
        this.bViewStats = (Button) view.findViewById(R.id.b_choose_captain);
        this.bViewStats.setVisibility(0);
        this.bEdit.setText("Edit");
        this.bDelete.setText("Delete");
        if (this.isInPlayingXI) {
            this.bNone.setText("Remove from Playing XI");
        } else {
            this.bNone.setText("Add to Playing XI");
        }
        this.bViewStats.setText("View Player Stats");
        this.bEdit.setOnClickListener(this);
        this.bDelete.setOnClickListener(this);
        this.bNone.setOnClickListener(this);
        this.bViewStats.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.blegbyes /*2131755294*/:
                this.editDeleteCallBack.onDeleteTapped();
                dismiss();
                return;
            case R.id.bbyes /*2131755295*/:
                this.editDeleteCallBack.onEditTapped();
                dismiss();
                return;
            case R.id.bnone /*2131755635*/:
                try {
                    this.editDeleteCallBack.onAddInPlayingXiTapped(this.isInPlayingXI);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dismiss();
                return;
            case R.id.b_choose_captain /*2131755636*/:
                this.editDeleteCallBack.onChooseCaptainTapped();
                dismiss();
                return;
            default:
                return;
        }
    }
}
