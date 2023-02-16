package apps.shehryar.com.cricketscroingappPro;

import android.app.Activity;
import android.support.annotation.RequiresApi;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman;
import apps.shehryar.com.cricketscroingappPro.Bowler.Bowler;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Overs.Over;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.FontProvider;
import java.util.ArrayList;

public class ViewsUpdater implements CompoundButton.OnCheckedChangeListener {
    Activity activity;
    private LinearLayout buttonfirstrow;
    private LinearLayout linearLayout4;
    /* access modifiers changed from: private */
    public RadioButton rbbatsman1;
    /* access modifiers changed from: private */
    public RadioButton rbbatsman2;
    private TextView teamname;
    private LinearLayout textView19;
    private TextView tvBat1Fours;
    private TextView tvBat1Sixes;
    private TextView tvBat1balls;
    private TextView tvBat2Fours;
    private TextView tvBat2Sixes;
    private TextView tvBat2balls;
    private TextView tvBowlerEco;
    private TextView tvBowlerMaidens;
    private TextView tvBowlerWickets;
    private TextView tvTeamNameTwo;
    private TextView tvTeamOversTwo;
    private TextView tvTeamScoreTwo;
    private TextView tvbat1score;
    private TextView tvbat1strikerate;
    private TextView tvbat2score;
    private TextView tvbat2strikerate;
    private TextView tvbowlername;
    private TextView tvbowlerovers;
    private TextView tvbowlerscore;
    private TextView tvextras;
    private TextView tvovers;
    private TextView tvremainingscore;
    private TextView tvrequiredrunrate;
    private TextView tvrunrate;
    private TextView tvscore;
    private TextView tvthisover;

    public ViewsUpdater(Activity activity2) {
        this.activity = activity2;
        findViews();
        this.rbbatsman1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    ViewsUpdater.this.rbbatsman2.setChecked(!z);
                }
            }
        });
        this.rbbatsman2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    ViewsUpdater.this.rbbatsman1.setChecked(!z);
                }
            }
        });
    }

    @RequiresApi(api = 14)
    private void findViews() {
        this.teamname = (TextView) this.activity.findViewById(R.id.teamname);
        this.tvscore = (TextView) this.activity.findViewById(R.id.tvscore);
        this.tvovers = (TextView) this.activity.findViewById(R.id.tvovers);
        this.tvTeamNameTwo = (TextView) this.activity.findViewById(R.id.team_2_name);
        this.tvTeamScoreTwo = (TextView) this.activity.findViewById(R.id.tvscore_team_2);
        this.tvTeamOversTwo = (TextView) this.activity.findViewById(R.id.tvovers_team_2);
        this.textView19 = (LinearLayout) this.activity.findViewById(R.id.textView19);
        this.rbbatsman1 = (RadioButton) this.activity.findViewById(R.id.rbbatsman1);
        this.rbbatsman2 = (RadioButton) this.activity.findViewById(R.id.rbbatsman2);
        this.tvbowlername = (TextView) this.activity.findViewById(R.id.tvbowlername);
        this.tvbat1score = (TextView) this.activity.findViewById(R.id.tvbat1score);
        this.tvBat1balls = (TextView) this.activity.findViewById(R.id.tvbat1balls);
        this.tvBat1Fours = (TextView) this.activity.findViewById(R.id.tvBat1Fours);
        this.tvBat1Sixes = (TextView) this.activity.findViewById(R.id.tvBat1Sixes);
        this.tvbat1strikerate = (TextView) this.activity.findViewById(R.id.tvbat1strikerate);
        this.tvbat2score = (TextView) this.activity.findViewById(R.id.tvbat2score);
        this.tvBat2balls = (TextView) this.activity.findViewById(R.id.tvbat2balls);
        this.tvBat2Fours = (TextView) this.activity.findViewById(R.id.tvbat2fours);
        this.tvBat2Sixes = (TextView) this.activity.findViewById(R.id.tvbat2sixes);
        this.tvbat2strikerate = (TextView) this.activity.findViewById(R.id.tvbat2strikerate);
        this.tvbowlerscore = (TextView) this.activity.findViewById(R.id.tv_bowler_score);
        this.tvbowlerovers = (TextView) this.activity.findViewById(R.id.tvbowlerovers);
        this.tvBowlerMaidens = (TextView) this.activity.findViewById(R.id.tv_bowler_maidens);
        this.tvBowlerWickets = (TextView) this.activity.findViewById(R.id.tv_bowler_wickets);
        this.tvBowlerEco = (TextView) this.activity.findViewById(R.id.tv_bowler_eco);
        this.linearLayout4 = (LinearLayout) this.activity.findViewById(R.id.linearLayout4);
        this.tvthisover = (TextView) this.activity.findViewById(R.id.tvthisover);
        this.tvremainingscore = (TextView) this.activity.findViewById(R.id.tvremainingscore);
        this.buttonfirstrow = (LinearLayout) this.activity.findViewById(R.id.buttonfirstrow);
        this.tvextras = (TextView) this.activity.findViewById(R.id.tvextras);
        this.tvrunrate = (TextView) this.activity.findViewById(R.id.tvrunrate);
        this.tvrequiredrunrate = (TextView) this.activity.findViewById(R.id.tvrequiredrunrate);
        this.rbbatsman1 = (RadioButton) this.activity.findViewById(R.id.rbbatsman1);
        this.rbbatsman2 = (RadioButton) this.activity.findViewById(R.id.rbbatsman2);
        try {
            FontProvider.setRobotoCondensedFont(this.activity, this.tvremainingscore);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.tvremainingscore.setAllCaps(true);
        try {
            FontProvider.setFiraSansFont(this.activity, this.teamname);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public Activity getActivity() {
        return this.activity;
    }

    public void setActivity(Activity activity2) {
        this.activity = activity2;
    }

    public TextView getTeamname() {
        return this.teamname;
    }

    public void setTeamname(TextView textView) {
        this.teamname = textView;
    }

    public TextView getTvscore() {
        return this.tvscore;
    }

    public void setTvscore(TextView textView) {
        this.tvscore = textView;
    }

    public TextView getTvovers() {
        return this.tvovers;
    }

    public void setTvovers(TextView textView) {
        this.tvovers = textView;
    }

    public LinearLayout getTextView19() {
        return this.textView19;
    }

    public void setTextView19(LinearLayout linearLayout) {
        this.textView19 = linearLayout;
    }

    public RadioButton getRbbatsman1() {
        return this.rbbatsman1;
    }

    public void setRbbatsman1(RadioButton radioButton) {
        this.rbbatsman1 = radioButton;
    }

    public RadioButton getRbbatsman2() {
        return this.rbbatsman2;
    }

    public void setRbbatsman2(RadioButton radioButton) {
        this.rbbatsman2 = radioButton;
    }

    public TextView getTvbowlername() {
        return this.tvbowlername;
    }

    public void setTvbowlername(TextView textView) {
        this.tvbowlername = textView;
    }

    public TextView getTvbat1score() {
        return this.tvbat1score;
    }

    public void setTvbat1score(TextView textView) {
        this.tvbat1score = textView;
    }

    public TextView getTvbat1strikerate() {
        return this.tvbat1strikerate;
    }

    public void setTvbat1strikerate(TextView textView) {
        this.tvbat1strikerate = textView;
    }

    public TextView getTvbat2score() {
        return this.tvbat2score;
    }

    public void setTvbat2score(TextView textView) {
        this.tvbat2score = textView;
    }

    public TextView getTvbat2strikerate() {
        return this.tvbat2strikerate;
    }

    public void setTvbat2strikerate(TextView textView) {
        this.tvbat2strikerate = textView;
    }

    public TextView getTvbowlerscore() {
        return this.tvbowlerscore;
    }

    public void setTvbowlerscore(TextView textView) {
        this.tvbowlerscore = textView;
    }

    public TextView getTvbowlerovers() {
        return this.tvbowlerovers;
    }

    public void setTvbowlerovers(TextView textView) {
        this.tvbowlerovers = textView;
    }

    public LinearLayout getLinearLayout4() {
        return this.linearLayout4;
    }

    public void setLinearLayout4(LinearLayout linearLayout) {
        this.linearLayout4 = linearLayout;
    }

    public TextView getTvthisover() {
        return this.tvthisover;
    }

    public void setTvthisover(TextView textView) {
        this.tvthisover = textView;
    }

    public TextView getTvremainingscore() {
        return this.tvremainingscore;
    }

    public void setTvremainingscore(TextView textView) {
        this.tvremainingscore = textView;
    }

    public LinearLayout getButtonfirstrow() {
        return this.buttonfirstrow;
    }

    public void setButtonfirstrow(LinearLayout linearLayout) {
        this.buttonfirstrow = linearLayout;
    }

    public TextView getTvextras() {
        return this.tvextras;
    }

    public void setTvextras(TextView textView) {
        this.tvextras = textView;
    }

    public TextView getTvrunrate() {
        return this.tvrunrate;
    }

    public void setTvrunrate(TextView textView) {
        this.tvrunrate = textView;
    }

    public TextView getTvrequiredrunrate() {
        return this.tvrequiredrunrate;
    }

    public void setTvrequiredrunrate(TextView textView) {
        this.tvrequiredrunrate = textView;
    }

    public void updateViews(Match match, Batsman[] batsmanArr, Bowler bowler, Over over) {
        if (!match.isTestMatch) {
            TextView textView = this.teamname;
            textView.setText(match.getTeam1().getName() + "");
            TextView textView2 = this.tvscore;
            textView2.setText(match.getTeam1().getScore() + "/" + match.getTeam1().getWickets());
            TextView textView3 = this.tvTeamNameTwo;
            textView3.setText(match.getTeam2().getName() + "");
            TextView textView4 = this.tvTeamScoreTwo;
            textView4.setText(match.getTeam2().getScore() + "/" + match.getTeam2().getWickets());
        } else {
            TextView textView5 = this.teamname;
            textView5.setText(match.getTeam1().getName() + "");
            TextView textView6 = this.tvscore;
            textView6.setText(match.getTeam1().getScore() + "/" + match.getTeam1().getWickets() + "&" + match.getTeam3().getScore() + "/" + match.getTeam3().getWickets());
            TextView textView7 = this.tvTeamNameTwo;
            StringBuilder sb = new StringBuilder();
            sb.append(match.getTeam2().getName());
            sb.append("");
            textView7.setText(sb.toString());
            TextView textView8 = this.tvTeamScoreTwo;
            textView8.setText(match.getTeam2().getScore() + "/" + match.getTeam2().getWickets() + "&" + match.getTeam4().getScore() + "/" + match.getTeam4().getWickets());
        }
        TextView textView9 = this.tvextras;
        textView9.setText(match.getBattingTeam().getExtras() + "");
        if (match.isTestMatch) {
            TextView textView10 = this.tvovers;
            textView10.setText("(" + match.getBattingTeam().getOversPlayed() + "." + (match.getBattingTeam().getOverballs() + over.getOverExtraBalls()) + ")");
            TextView textView11 = this.tvTeamOversTwo;
            textView11.setText("(" + match.getBattingTeam().getOversPlayed() + "." + (match.getBattingTeam().getOverballs() + over.getOverExtraBalls()) + ")");
        } else {
            TextView textView12 = this.tvovers;
            textView12.setText("(" + match.getTeam1().getOversPlayed() + "." + (match.getTeam1().getOverballs() + over.getOverExtraBalls()) + "/" + match.getOvers() + ")");
            TextView textView13 = this.tvTeamOversTwo;
            textView13.setText("(" + match.getTeam2().getOversPlayed() + "." + (match.getTeam2().getOverballs() + over.getOverExtraBalls()) + "/" + match.getOvers() + ")");
        }
        this.rbbatsman1.setText(batsmanArr[0].getName());
        this.rbbatsman2.setText(batsmanArr[1].getName());
        this.tvbowlername.setText(bowler.getName());
        TextView textView14 = this.tvbat1score;
        textView14.setText(batsmanArr[0].getTotalScore() + "");
        TextView textView15 = this.tvBat1balls;
        textView15.setText(batsmanArr[0].getBallsfaced() + "");
        TextView textView16 = this.tvBat1Fours;
        textView16.setText(batsmanArr[0].getFours() + "");
        TextView textView17 = this.tvBat1Sixes;
        textView17.setText(batsmanArr[0].getSixs() + "");
        TextView textView18 = this.tvbat1strikerate;
        textView18.setText(batsmanArr[0].getStrikerate() + "");
        TextView textView20 = this.tvbat2score;
        textView20.setText(batsmanArr[1].getTotalScore() + "");
        TextView textView21 = this.tvBat2balls;
        textView21.setText(batsmanArr[1].getBallsfaced() + "");
        TextView textView22 = this.tvBat2Fours;
        textView22.setText(batsmanArr[1].getFours() + "");
        TextView textView23 = this.tvBat2Sixes;
        textView23.setText(batsmanArr[1].getSixs() + "");
        TextView textView24 = this.tvbat2strikerate;
        textView24.setText(batsmanArr[1].getStrikerate() + "");
        TextView textView25 = this.tvbowlerscore;
        textView25.setText(bowler.getTotalscore() + "");
        TextView textView26 = this.tvbowlerovers;
        textView26.setText(bowler.getBowlerovers() + "." + bowler.getBalls());
        TextView textView27 = this.tvBowlerMaidens;
        textView27.setText(bowler.getMaidens() + "");
        TextView textView28 = this.tvBowlerEco;
        textView28.setText(bowler.getEco() + "");
        TextView textView29 = this.tvBowlerWickets;
        textView29.setText(bowler.getWickets() + "");
        changeOverPerBallScore(over.getPerballScore(), this.tvthisover);
        this.tvrunrate.setText(match.getBattingTeam().getRunRate());
        this.tvremainingscore.setText(match.getRemainingScoreText(over));
        this.tvrequiredrunrate.setText(match.getRequiredRunRate());
    }

    public static void changeOverPerBallScore(ArrayList<Integer> arrayList, TextView textView) {
        textView.setText("");
        for (int i = 0; i < arrayList.size(); i++) {
            int intValue = arrayList.get(i).intValue();
            if (intValue == 11) {
                textView.append("0nb ");
            } else if (intValue == 12) {
                textView.append("1nb ");
            } else if (intValue == 13) {
                textView.append("2nb ");
            } else if (intValue == 14) {
                textView.append("3nb ");
            } else if (intValue == 15) {
                textView.append("4nb ");
            } else if (intValue == 16) {
                textView.append("5nb ");
            } else if (intValue == 17) {
                textView.append("6nb ");
            } else if (intValue == 18) {
                textView.append("7nb ");
            } else if (intValue == 20) {
                textView.append("W ");
            } else if (intValue == 21) {
                textView.append("0wd ");
            } else if (intValue == 22) {
                textView.append("1wd ");
            } else if (intValue == 23) {
                textView.append("2wd ");
            } else if (intValue == 24) {
                textView.append("3wd ");
            } else if (intValue == 25) {
                textView.append("4wd ");
            } else if (intValue == 26) {
                textView.append("5wd ");
            } else if (intValue == 27) {
                textView.append("6wd ");
            } else if (intValue == 28) {
                textView.append("7wd ");
            } else if (intValue == 20) {
                textView.append("W ");
            } else if (intValue == 41) {
                textView.append("1b ");
            } else if (intValue == 42) {
                textView.append("2b ");
            } else if (intValue == 43) {
                textView.append("3b ");
            } else if (intValue == 44) {
                textView.append("4b ");
            } else if (intValue == 45) {
                textView.append("5b ");
            } else if (intValue == 46) {
                textView.append("6b ");
            } else if (intValue == 47) {
                textView.append("7b ");
            } else if (intValue == 51) {
                textView.append("1lb ");
            } else if (intValue == 52) {
                textView.append("2lb ");
            } else if (intValue == 53) {
                textView.append("3lb ");
            } else if (intValue == 54) {
                textView.append("4lb ");
            } else if (intValue == 55) {
                textView.append("5lb ");
            } else if (intValue == 56) {
                textView.append("6lb ");
            } else if (intValue == 57) {
                textView.append("7lb ");
            } else if (intValue == 60) {
                textView.append("W ");
            } else if (intValue == 61) {
                textView.append("W+wd ");
            } else if (intValue == 62) {
                textView.append("W+nb ");
            } else {
                textView.append(intValue + " ");
            }
        }
    }

    public void switchBatsmen() {
        if (this.rbbatsman1.isChecked()) {
            this.rbbatsman2.setChecked(true);
        } else {
            this.rbbatsman1.setChecked(true);
        }
    }

    public void switchRadioCheck(int i, boolean z) {
        if (z) {
            if ((i + 1) % 2 == 0) {
                switchBatsmen();
            }
        } else if (i % 2 == 1) {
            switchBatsmen();
        }
    }

    public void setBothRadiosUnchecked() {
        this.rbbatsman1.setChecked(false);
        this.rbbatsman2.setChecked(false);
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (!z) {
            return;
        }
        if (compoundButton.getId() == R.id.rbbatsman1) {
            this.rbbatsman2.setChecked(false);
        } else if (compoundButton.getId() == R.id.rbbatsman2) {
            this.rbbatsman1.setChecked(false);
        }
    }

    public int getBatsmanFacing() {
        if (this.rbbatsman1.isChecked()) {
            return 0;
        }
        return this.rbbatsman2.isChecked() ? 1 : -1;
    }
}
