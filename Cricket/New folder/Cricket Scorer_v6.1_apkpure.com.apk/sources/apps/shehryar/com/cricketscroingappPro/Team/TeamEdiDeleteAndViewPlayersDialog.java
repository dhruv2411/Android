package apps.shehryar.com.cricketscroingappPro.Team;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import apps.shehryar.com.cricketscroingapp.R;

public class TeamEdiDeleteAndViewPlayersDialog extends DialogFragment implements View.OnClickListener {
    Button bChooseCaptain;
    Button bChooseWicketKeeper;
    Button bDelete;
    Button bEdit;
    Button bViewPlayers;
    TeamEditDeleteCallBack editDeleteCallBack;
    private int index;

    public interface TeamEditDeleteCallBack {
        void onChooseCaptainTapped(int i);

        void onChooseWicketKeeperTapped(int i);

        void onTeamDeleteTapped(int i);

        void onTeamEditTapped(int i);

        void onTeamViewPlayersTapped(int i);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof TeamEditDeleteCallBack) {
            this.editDeleteCallBack = (TeamEditDeleteCallBack) context;
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Dialog onCreateDialog = super.onCreateDialog(bundle);
        onCreateDialog.getWindow().requestFeature(1);
        this.index = getArguments().getInt("index");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        onCreateDialog.getWindow().getAttributes().windowAnimations = 16973826;
        layoutParams.copyFrom(onCreateDialog.getWindow().getAttributes());
        layoutParams.width = -1;
        onCreateDialog.getWindow().setAttributes(layoutParams);
        return onCreateDialog;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.no_ball_bye_legbye, viewGroup, false);
        if (this.editDeleteCallBack == null) {
            this.editDeleteCallBack = (TeamEditDeleteCallBack) getActivity();
        }
        initializeViews(inflate);
        if (getArguments().getBoolean("showChooseOptions")) {
            this.bChooseCaptain.setVisibility(0);
        }
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
        this.bViewPlayers = (Button) view.findViewById(R.id.bnone);
        this.bChooseCaptain = (Button) view.findViewById(R.id.b_choose_captain);
        this.bChooseWicketKeeper = (Button) view.findViewById(R.id.b_choose_wk);
        this.bEdit.setText("Edit");
        this.bDelete.setText("Delete");
        this.bViewPlayers.setText("View and Add Players");
        this.bEdit.setOnClickListener(this);
        this.bDelete.setOnClickListener(this);
        this.bViewPlayers.setOnClickListener(this);
        this.bChooseWicketKeeper.setOnClickListener(this);
        this.bChooseCaptain.setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.blegbyes /*2131755294*/:
                this.editDeleteCallBack.onTeamDeleteTapped(this.index);
                dismiss();
                return;
            case R.id.bbyes /*2131755295*/:
                this.editDeleteCallBack.onTeamEditTapped(this.index);
                dismiss();
                return;
            default:
                switch (id) {
                    case R.id.bnone /*2131755635*/:
                        this.editDeleteCallBack.onTeamViewPlayersTapped(this.index);
                        dismiss();
                        return;
                    case R.id.b_choose_captain /*2131755636*/:
                        this.editDeleteCallBack.onChooseCaptainTapped(this.index);
                        dismiss();
                        return;
                    case R.id.b_choose_wk /*2131755637*/:
                        this.editDeleteCallBack.onChooseWicketKeeperTapped(this.index);
                        dismiss();
                        return;
                    default:
                        return;
                }
        }
    }
}
