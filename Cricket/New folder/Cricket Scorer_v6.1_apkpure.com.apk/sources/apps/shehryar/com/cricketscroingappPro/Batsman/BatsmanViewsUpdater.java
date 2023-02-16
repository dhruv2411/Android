package apps.shehryar.com.cricketscroingappPro.Batsman;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.FontProvider;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.Formatter;

public class BatsmanViewsUpdater {
    Activity activity;
    Context context;
    RadioButton rbBatsman1;
    RadioButton rbBatsman2;
    RadioGroup rgBatsmanNames;
    TextView tvBat1Score;
    TextView tvBat1Sr;
    TextView tvBat2Score;
    TextView tvBat2Sr;

    public BatsmanViewsUpdater(Context context2, Activity activity2) {
        this.context = context2;
        this.activity = activity2;
        initializeViews(context2, activity2);
    }

    private void initializeViews(Context context2, Activity activity2) {
        Typeface.createFromAsset(context2.getAssets(), "fonts/RobotoCondensed_Bold.ttf");
        this.rgBatsmanNames = (RadioGroup) activity2.findViewById(R.id.rgbatsmans);
        this.rbBatsman1 = (RadioButton) activity2.findViewById(R.id.rbbatsman1);
        this.rbBatsman2 = (RadioButton) activity2.findViewById(R.id.rbbatsman2);
        this.tvBat1Score = (TextView) activity2.findViewById(R.id.tvbat1score);
        this.tvBat1Sr = (TextView) activity2.findViewById(R.id.tvbat1strikerate);
        this.tvBat2Score = (TextView) activity2.findViewById(R.id.tvbat2score);
        this.tvBat2Sr = (TextView) activity2.findViewById(R.id.tvbat2strikerate);
        try {
            Formatter.setFormattedBatsmenScores(this.tvBat1Score, new Batsman(), context2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Formatter.setFormattedBatsmenScores(this.tvBat2Score, new Batsman(), context2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        FontProvider.setDroidSansBoldFont(context2, this.tvBat1Score);
        FontProvider.setDroidSansBoldFont(context2, this.tvBat2Score);
    }

    public void changeBatsmanScoreText(Batsman batsman, int i) {
        if (i == 1) {
            try {
                Formatter.setFormattedBatsmenScores(this.tvBat1Score, batsman, this.context);
            } catch (Exception e) {
                e.printStackTrace();
            }
            TextView textView = this.tvBat1Sr;
            textView.setText("SR:\n" + batsman.getStrikerate());
        } else if (i == 2) {
            try {
                Formatter.setFormattedBatsmenScores(this.tvBat2Score, batsman, this.context);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            TextView textView2 = this.tvBat2Sr;
            textView2.setText("SR:\n" + batsman.getStrikerate());
        }
    }

    public void changeBatsmanName(Batsman batsman, int i) {
        if (i == 1) {
            this.rbBatsman1.setText(batsman.getName());
        } else if (i == 2) {
            this.rbBatsman2.setText(batsman.getName());
        }
    }

    public void unCheckBothRadioButtons() {
        this.rgBatsmanNames.clearCheck();
    }

    public void changeBatsmanNameAndScores(Batsman batsman, int i) {
        changeBatsmanName(batsman, i);
        changeBatsmanScoreText(batsman, i);
    }

    public void switchRadioCheck(int i) {
        int i2 = i % 2;
        if (i2 != 0 && this.rbBatsman1.isChecked()) {
            this.rbBatsman2.setChecked(true);
        } else if (i2 != 0 && this.rbBatsman2.isChecked()) {
            this.rbBatsman1.setChecked(true);
        }
    }

    public void switchRadioCheck() {
        if (this.rbBatsman1.isChecked()) {
            this.rbBatsman2.setChecked(true);
        } else if (this.rbBatsman2.isChecked()) {
            this.rbBatsman1.setChecked(true);
        }
    }

    public int getCheckedRadioButton() {
        if (this.rbBatsman1.isChecked()) {
            return 1;
        }
        return this.rbBatsman2.isChecked() ? 2 : 0;
    }
}
