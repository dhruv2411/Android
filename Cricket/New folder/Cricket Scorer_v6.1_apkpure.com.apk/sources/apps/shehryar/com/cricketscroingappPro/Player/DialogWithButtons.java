package apps.shehryar.com.cricketscroingappPro.Player;

import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.MyDialogFragment;
import java.util.ArrayList;

public class DialogWithButtons extends MyDialogFragment {
    public static final String BUTTONS_TEXT = "btn_text";
    public static final String BUTTONS_VISIBILITY = "btn_visibility";
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    ArrayList<String> buttonsText;
    DialogWithButtonsInteractionListener dialogWithButtonsInteractionListener;
    ArrayList<Boolean> visibilityStatus;

    public interface DialogWithButtonsInteractionListener {
        void onButton1Tapped();

        void onButton2Tapped();

        void onButton3Tapped();

        void onButton4Tapped();

        void onButton5Tapped();
    }

    public static DialogWithButtons newInstance(ArrayList<String> arrayList) {
        DialogWithButtons dialogWithButtons = new DialogWithButtons();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(BUTTONS_TEXT, arrayList);
        dialogWithButtons.setArguments(bundle);
        return dialogWithButtons;
    }

    public void setDialogWithButtonsInteractionListener(DialogWithButtonsInteractionListener dialogWithButtonsInteractionListener2) {
        this.dialogWithButtonsInteractionListener = dialogWithButtonsInteractionListener2;
    }

    @RequiresApi(api = 17)
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_choose_player_type_dialog, viewGroup, false);
        if (this.dialogWithButtonsInteractionListener == null) {
            this.dialogWithButtonsInteractionListener = (DialogWithButtonsInteractionListener) getParentFragment();
            if (this.dialogWithButtonsInteractionListener == null) {
                this.dialogWithButtonsInteractionListener = (DialogWithButtonsInteractionListener) getActivity();
            }
        }
        initializeViews(inflate);
        receiveArgumentsAndSetValues();
        setListeners();
        return inflate;
    }

    private void setListeners() {
        this.button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DialogWithButtons.this.dialogWithButtonsInteractionListener.onButton1Tapped();
                DialogWithButtons.this.dismiss();
            }
        });
        this.button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DialogWithButtons.this.dialogWithButtonsInteractionListener.onButton2Tapped();
                DialogWithButtons.this.dismiss();
            }
        });
        this.button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DialogWithButtons.this.dialogWithButtonsInteractionListener.onButton3Tapped();
                DialogWithButtons.this.dismiss();
            }
        });
        this.button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DialogWithButtons.this.dialogWithButtonsInteractionListener.onButton4Tapped();
                DialogWithButtons.this.dismiss();
            }
        });
        this.button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DialogWithButtons.this.dialogWithButtonsInteractionListener.onButton5Tapped();
                DialogWithButtons.this.dismiss();
            }
        });
    }

    private void receiveArgumentsAndSetValues() {
        this.buttonsText = getArguments().getStringArrayList(BUTTONS_TEXT);
        if (this.buttonsText.get(0) != null) {
            this.button1.setText(this.buttonsText.get(0));
        } else {
            this.button1.setVisibility(8);
        }
        if (this.buttonsText.get(1) != null) {
            this.button2.setText(this.buttonsText.get(1));
        } else {
            this.button2.setVisibility(8);
        }
        if (this.buttonsText.get(2) != null) {
            this.button3.setText(this.buttonsText.get(2));
        } else {
            this.button3.setVisibility(8);
        }
        if (this.buttonsText.get(3) != null) {
            this.button4.setText(this.buttonsText.get(3));
        } else {
            this.button4.setVisibility(8);
        }
        if (this.buttonsText.get(4) != null) {
            this.button5.setText(this.buttonsText.get(4));
        } else {
            this.button5.setVisibility(8);
        }
    }

    private void initializeViews(View view) {
        this.button1 = (Button) view.findViewById(R.id.button1);
        this.button2 = (Button) view.findViewById(R.id.button2);
        this.button3 = (Button) view.findViewById(R.id.button3);
        this.button4 = (Button) view.findViewById(R.id.button4);
        this.button5 = (Button) view.findViewById(R.id.button5);
    }
}
