package apps.shehryar.com.cricketscroingappPro.Team;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Overs.Over;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.FontProvider;
import java.util.ArrayList;

public class TeamViewsUpdater {
    Activity activity;
    Context context;
    private TextView tvExtras;
    private TextView tvOverDetails;
    private TextView tvOvers;
    private TextView tvRemaining;
    private TextView tvRunRate;
    private TextView tvScore;
    private TextView tvTeamName;
    private TextView tvrequiredRunRate;

    public TeamViewsUpdater(Context context2, Activity activity2) {
        this.context = context2;
        this.activity = activity2;
        initializeViews(context2, activity2);
    }

    private void initializeViews(Context context2, Activity activity2) {
        Typeface createFromAsset = Typeface.createFromAsset(context2.getAssets(), "fonts/RobotoCondensed_Bold.ttf");
        this.tvScore = (TextView) activity2.findViewById(R.id.tvscore);
        this.tvOvers = (TextView) activity2.findViewById(R.id.tvovers);
        this.tvRemaining = (TextView) activity2.findViewById(R.id.tvremainingscore);
        this.tvTeamName = (TextView) activity2.findViewById(R.id.teamname);
        this.tvExtras = (TextView) activity2.findViewById(R.id.tvextras);
        this.tvRunRate = (TextView) activity2.findViewById(R.id.tvrunrate);
        this.tvOverDetails = (TextView) activity2.findViewById(R.id.tvthisover);
        this.tvrequiredRunRate = (TextView) activity2.findViewById(R.id.tvrequiredrunrate);
        FontProvider.setDroidSansBoldFont(context2, this.tvrequiredRunRate);
        this.tvTeamName.setTypeface(createFromAsset);
    }

    public void changeTeamScoreText(Team team) {
        TextView textView = this.tvScore;
        textView.setText(team.getScore() + "/" + team.getWickets());
        TextView textView2 = this.tvExtras;
        textView2.setText(team.getExtras() + "");
    }

    public void changeTeamOversText(Team team, boolean z) {
        if (z) {
            TextView textView = this.tvOvers;
            textView.setText("(" + team.getOversPlayed() + "." + team.getOverballs() + ")");
            return;
        }
        TextView textView2 = this.tvOvers;
        textView2.setText("(" + team.getOversPlayed() + "." + team.getOverballs() + "/" + team.getMatchTotalOvers() + ")");
    }

    public void changeTeamName(Team team) {
        this.tvTeamName.setText(team.getName());
    }

    public void changeTeamRR(Team team, Team team2, int i, int i2, int i3) {
        float f;
        float f2;
        int i4 = (i * 6) + Over.balls;
        if (i3 == 99) {
            f = ((float) team2.getScore()) / ((float) i4);
            f2 = ((float) ((team.getScore() - team2.getScore()) + 1)) / ((float) ((i2 * 6) - i4));
        } else {
            f = ((float) team.getScore()) / ((float) i4);
            f2 = 0.0f;
        }
        String format = String.format("%.2f", new Object[]{Float.valueOf(f * 6.0f)});
        String format2 = String.format("%.2f", new Object[]{Float.valueOf(f2 * 6.0f)});
        TextView textView = this.tvRunRate;
        textView.setText("" + format);
        TextView textView2 = this.tvrequiredRunRate;
        textView2.setText(format2 + "");
    }

    public void changeTeamRemainingScore(Team team, Team team2, int i, int i2) {
        if (i == 99) {
            TextView textView = this.tvRemaining;
            textView.setText(team2.getName() + " needs " + ((team.getScore() - team2.getScore()) + 1) + " from " + team.getMatchTotalOvers() + "." + (i2 - Over.balls) + " overs");
            return;
        }
        this.tvRemaining.setText("First Innings");
    }

    public void changeOverPerBallScore(ArrayList<Integer> arrayList) {
        this.tvOverDetails.setText("");
        for (int i = 0; i < arrayList.size(); i++) {
            int intValue = arrayList.get(i).intValue();
            if (intValue == 11) {
                this.tvOverDetails.append("0nb ");
            } else if (intValue == 12) {
                this.tvOverDetails.append("1nb ");
            } else if (intValue == 13) {
                this.tvOverDetails.append("2nb ");
            } else if (intValue == 14) {
                this.tvOverDetails.append("3nb ");
            } else if (intValue == 15) {
                this.tvOverDetails.append("4nb ");
            } else if (intValue == 16) {
                this.tvOverDetails.append("5nb ");
            } else if (intValue == 17) {
                this.tvOverDetails.append("6nb ");
            } else if (intValue == 18) {
                this.tvOverDetails.append("7nb ");
            } else if (intValue == 20) {
                this.tvOverDetails.append("W ");
            } else if (intValue == 21) {
                this.tvOverDetails.append("0wd ");
            } else if (intValue == 22) {
                this.tvOverDetails.append("1wd ");
            } else if (intValue == 23) {
                this.tvOverDetails.append("2wd ");
            } else if (intValue == 24) {
                this.tvOverDetails.append("3wd ");
            } else if (intValue == 25) {
                this.tvOverDetails.append("4wd ");
            } else if (intValue == 26) {
                this.tvOverDetails.append("5wd ");
            } else if (intValue == 27) {
                this.tvOverDetails.append("6wd ");
            } else if (intValue == 28) {
                this.tvOverDetails.append("7wd ");
            } else if (intValue == 20) {
                this.tvOverDetails.append("W ");
            } else if (intValue == 41) {
                this.tvOverDetails.append("1b ");
            } else if (intValue == 42) {
                this.tvOverDetails.append("2b ");
            } else if (intValue == 43) {
                this.tvOverDetails.append("3b ");
            } else if (intValue == 44) {
                this.tvOverDetails.append("4b ");
            } else if (intValue == 45) {
                this.tvOverDetails.append("5b ");
            } else if (intValue == 46) {
                this.tvOverDetails.append("6b ");
            } else if (intValue == 47) {
                this.tvOverDetails.append("7b ");
            } else if (intValue == 51) {
                this.tvOverDetails.append("1lb ");
            } else if (intValue == 52) {
                this.tvOverDetails.append("2lb ");
            } else if (intValue == 53) {
                this.tvOverDetails.append("3lb ");
            } else if (intValue == 54) {
                this.tvOverDetails.append("4lb ");
            } else if (intValue == 55) {
                this.tvOverDetails.append("5lb ");
            } else if (intValue == 56) {
                this.tvOverDetails.append("6lb ");
            } else if (intValue == 57) {
                this.tvOverDetails.append("7lb ");
            } else if (intValue == 61) {
                this.tvOverDetails.append("W+wd ");
            } else if (intValue == 62) {
                this.tvOverDetails.append("W+nb ");
            } else {
                TextView textView = this.tvOverDetails;
                textView.append(intValue + " ");
            }
        }
    }
}
