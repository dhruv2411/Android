package apps.shehryar.com.cricketscroingappPro.Batsman;

import android.app.Activity;
import android.content.Context;
import apps.shehryar.com.cricketscroingappPro.Bowler.Bowler;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.Formatter;
import com.google.android.exoplayer2.C;
import com.itextpdf.text.pdf.PdfBoolean;
import java.util.ArrayList;
import java.util.Iterator;

public class BatsmanDataUpdater {
    BatsmanViewsUpdater batsmanViewsUpdater;

    public BatsmanDataUpdater(Context context, Activity activity) {
        this.batsmanViewsUpdater = new BatsmanViewsUpdater(context, activity);
    }

    public Batsman updateBatsmanScore(Batsman batsman, int i) {
        batsman.setBallsfaced(1);
        batsman.allscoreslist.add(Integer.valueOf(i));
        batsman.setTotalScore(i);
        Batsman assignBatsmanScore = assignBatsmanScore(batsman, i);
        if (this.batsmanViewsUpdater.getCheckedRadioButton() == 1) {
            this.batsmanViewsUpdater.changeBatsmanScoreText(assignBatsmanScore, 1);
        } else if (this.batsmanViewsUpdater.getCheckedRadioButton() == 2) {
            this.batsmanViewsUpdater.changeBatsmanScoreText(assignBatsmanScore, 2);
        }
        this.batsmanViewsUpdater.switchRadioCheck(i);
        return assignBatsmanScore;
    }

    public Batsman updateBatsmanBallsFaced(Batsman batsman, int i) {
        batsman.setBallsfaced(1);
        if (this.batsmanViewsUpdater.getCheckedRadioButton() == 1) {
            this.batsmanViewsUpdater.changeBatsmanScoreText(batsman, 1);
        } else if (this.batsmanViewsUpdater.getCheckedRadioButton() == 2) {
            this.batsmanViewsUpdater.changeBatsmanScoreText(batsman, 2);
        }
        this.batsmanViewsUpdater.switchRadioCheck(i);
        return batsman;
    }

    /* access modifiers changed from: package-private */
    public Batsman assignBatsmanScore(Batsman batsman, int i) {
        switch (i) {
            case 0:
                batsman.setDots(1);
                break;
            case 1:
                batsman.setSingles(1);
                break;
            case 2:
                batsman.setDoubles(1);
                break;
            case 3:
                batsman.setThrees(1);
                break;
            case 4:
                batsman.setFours(1);
                break;
            case 5:
                batsman.setFives(1);
                break;
            case 6:
                batsman.setSixs(1);
                break;
        }
        return batsman;
    }

    public Batsman setBatsmanOut(Batsman batsman, Batsman batsman2) {
        if (this.batsmanViewsUpdater.getCheckedRadioButton() == 1) {
            batsman.setBallsfaced(1);
            batsman.setOut(PdfBoolean.TRUE);
            return batsman;
        } else if (this.batsmanViewsUpdater.getCheckedRadioButton() != 2) {
            return null;
        } else {
            batsman2.setBallsfaced(1);
            batsman2.setOut(PdfBoolean.TRUE);
            return batsman2;
        }
    }

    public static void assignOutType(Batsman batsman, int i, String str, String str2) {
        switch (i) {
            case 0:
                batsman.setOutType("Bowled");
                batsman.setOut("b " + str);
                return;
            case 1:
                batsman.setOutType("Caught");
                batsman.setOut("c " + str2 + " b " + str);
                return;
            case 2:
                batsman.setOutType("LBW");
                batsman.setOut("lbw b " + str);
                return;
            case 3:
                batsman.setOutType("Stumped");
                batsman.setOut("stumped b " + str);
                return;
            case 4:
                batsman.setOutType("Hit Wicket");
                batsman.setOut("hit wicket");
                return;
            case 5:
                batsman.setOutType("RUN OUT");
                batsman.setOut("Run Out (" + Formatter.cutNameHalf(str2) + ")");
                return;
            case 6:
                batsman.setOutType("Caught and Bowled");
                batsman.setOut("c & b " + str);
                return;
            case 7:
                batsman.setOutType("Obstructing the field");
                batsman.setOut("obstructing the field");
                return;
            case 8:
                batsman.setOutType("Handling the ball");
                batsman.setOut("Handling the ball");
                return;
            default:
                return;
        }
    }

    public void addBatsmanToListAndDB(DBHelper dBHelper, Batsman batsman, Team team, Match match) {
        Batsman batsman2 = batsman;
        team.batsmans_list.add(batsman2);
        DBHelper dBHelper2 = dBHelper;
        batsman2.setBatsmanID(dBHelper2.insertPlayer(batsman.getName(), team.getTeamID(), "batsman"));
        dBHelper2.insertBatsmanScore(team.getTeamID(), match.getMatchID(), batsman.getBatsmanID(), 0, 0, 0, 0, 0, 0, 0, 0, PdfBoolean.FALSE);
    }

    public static void setPointsToBatsman(Batsman batsman) {
        float f;
        batsman.setPoints(batsman.getTotalScore());
        batsman.setPoints(batsman.getSixs() * 2);
        batsman.setPoints(batsman.getFours() * 2);
        try {
            f = Float.parseFloat(batsman.getSingleMatchSR());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            f = 0.0f;
        }
        if (f > 0.0f && f < 50.0f) {
            batsman.setPoints(-10);
        } else if (f > 100.0f && f < 125.0f) {
            batsman.setPoints(10);
        } else if (f > 125.0f && f < 150.0f) {
            batsman.setPoints(20);
        } else if (f >= 150.0f) {
            batsman.setPoints(30);
        }
        int singleMatchRuns = batsman.getSingleMatchRuns();
        if (singleMatchRuns >= 10 && singleMatchRuns < 20) {
            batsman.setPoints(10);
        } else if (singleMatchRuns >= 20 && singleMatchRuns < 30) {
            batsman.setPoints(20);
        } else if (singleMatchRuns >= 30 && singleMatchRuns < 40) {
            batsman.setPoints(30);
        } else if (singleMatchRuns >= 40 && singleMatchRuns < 50) {
            batsman.setPoints(40);
        } else if (singleMatchRuns >= 50) {
            batsman.setPoints(singleMatchRuns);
        }
        batsman.setPoints(batsman.getWickets() * 10);
    }

    public static void setWicketsToBatsman(Batsman batsman, ArrayList<Bowler> arrayList) {
        Iterator<Bowler> it = arrayList.iterator();
        while (it.hasNext()) {
            Bowler next = it.next();
            if (next.getName().equals(batsman.getName())) {
                batsman.setWickets(next.getWickets());
                return;
            }
        }
    }

    public static void assignScoresToBatsman(Batsman batsman, int i) {
        switch (i) {
            case -6:
                batsman.setSixs(-1);
                return;
            case C.RESULT_FORMAT_READ /*-5*/:
                batsman.setFives(-1);
                return;
            case -4:
                batsman.setFours(-1);
                return;
            case -3:
                batsman.setThrees(-1);
                return;
            case -2:
                batsman.setDoubles(-1);
                return;
            case -1:
                batsman.setSingles(-1);
                return;
            case 0:
                batsman.setDots(1);
                return;
            case 1:
                batsman.setSingles(1);
                return;
            case 2:
                batsman.setDoubles(1);
                return;
            case 3:
                batsman.setThrees(1);
                return;
            case 4:
                batsman.setFours(1);
                return;
            case 5:
                batsman.setFives(1);
                return;
            case 6:
                batsman.setSixs(1);
                return;
            default:
                return;
        }
    }
}
