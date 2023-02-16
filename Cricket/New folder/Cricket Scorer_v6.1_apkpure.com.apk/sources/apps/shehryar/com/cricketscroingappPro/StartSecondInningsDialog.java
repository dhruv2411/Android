package apps.shehryar.com.cricketscroingappPro;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import apps.shehryar.com.cricketscroingapp.R;

public class StartSecondInningsDialog extends MyDialogFragment {
    public static final String TOTAL_OVERS = "total_overs";
    EditText balls;
    Button btnStartFirstInns;
    Button btnStartSecondInns;
    EditText overs;
    EditText score;
    StartSecondInningsDialogListener startSecondInningsDialogListener;
    int totalOvers;
    TextInputLayout txtInBalls;
    TextInputLayout txtInOvers;
    TextInputLayout txtInScore;
    TextInputLayout txtInWickets;
    EditText wickets;

    public interface StartSecondInningsDialogListener {
        void onStartFirstInningsTapped();

        void onStartSecondInningsTapped(int i, int i2, int i3, int i4);
    }

    public static StartSecondInningsDialog newInstance(int i) {
        StartSecondInningsDialog startSecondInningsDialog = new StartSecondInningsDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(TOTAL_OVERS, i);
        startSecondInningsDialog.setArguments(bundle);
        return startSecondInningsDialog;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.totalOvers = getArguments().getInt(TOTAL_OVERS);
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof StartSecondInningsDialogListener) {
            this.startSecondInningsDialogListener = (StartSecondInningsDialogListener) activity;
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_start_second_innings_dialog, viewGroup, false);
        this.score = (EditText) inflate.findViewById(R.id.ed_first_inns_score);
        this.wickets = (EditText) inflate.findViewById(R.id.ed_first_inns_wickets);
        this.overs = (EditText) inflate.findViewById(R.id.ed_first_inns_overs);
        this.balls = (EditText) inflate.findViewById(R.id.ed_firs_inns_balls);
        this.txtInScore = (TextInputLayout) inflate.findViewById(R.id.ti_first_inns_score);
        this.txtInWickets = (TextInputLayout) inflate.findViewById(R.id.ti_first_inns_wickets);
        this.txtInOvers = (TextInputLayout) inflate.findViewById(R.id.ti_first_inns_overs);
        this.txtInBalls = (TextInputLayout) inflate.findViewById(R.id.ti_first_inns_balls);
        this.btnStartFirstInns = (Button) inflate.findViewById(R.id.b_start_first_innings);
        this.btnStartSecondInns = (Button) inflate.findViewById(R.id.b_start_second_innings);
        this.overs.setFilters(new InputFilter[]{new InputFilterMinMax("1", "500")});
        this.btnStartFirstInns.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (StartSecondInningsDialog.this.startSecondInningsDialogListener != null) {
                    StartSecondInningsDialog.this.startSecondInningsDialogListener.onStartFirstInningsTapped();
                }
            }
        });
        this.btnStartSecondInns.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (StartSecondInningsDialog.this.score.getText().toString().isEmpty()) {
                    StartSecondInningsDialog.this.txtInScore.setError("Score is required");
                } else if (StartSecondInningsDialog.this.wickets.getText().toString().isEmpty()) {
                    StartSecondInningsDialog.this.txtInWickets.setError("Wickets are required");
                } else if (StartSecondInningsDialog.this.overs.getText().toString().isEmpty()) {
                    StartSecondInningsDialog.this.txtInOvers.setError("Overs are required");
                } else if (Integer.parseInt(StartSecondInningsDialog.this.overs.getText().toString()) > StartSecondInningsDialog.this.totalOvers) {
                    StartSecondInningsDialog.this.txtInOvers.setError("Entered overs are greater than match total overs");
                } else {
                    StartSecondInningsDialog.this.startSecondInningsDialogListener.onStartSecondInningsTapped(Integer.parseInt(StartSecondInningsDialog.this.score.getText().toString()), Integer.parseInt(StartSecondInningsDialog.this.wickets.getText().toString()), StartSecondInningsDialog.this.overs.getText().toString().isEmpty() ? StartSecondInningsDialog.this.totalOvers : Integer.parseInt(StartSecondInningsDialog.this.overs.getText().toString()), 0);
                }
            }
        });
        return inflate;
    }
}
