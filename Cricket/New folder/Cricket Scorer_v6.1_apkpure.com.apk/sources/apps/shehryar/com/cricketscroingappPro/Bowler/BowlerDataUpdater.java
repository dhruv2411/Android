package apps.shehryar.com.cricketscroingappPro.Bowler;

import android.app.Activity;
import android.content.Context;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Overs.Over;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import io.fabric.sdk.android.services.common.IdManager;

public class BowlerDataUpdater {
    BowlerViewsUpdater bowlerViewsUpdater;

    public BowlerDataUpdater(Context context, Activity activity) {
        this.bowlerViewsUpdater = new BowlerViewsUpdater(context, activity);
    }

    public Bowler changeBowlerOverAndBalls(Match match, Bowler bowler, int i, int i2) {
        if (bowler.getBalls() == i2 || bowler.getBalls() == 6) {
            bowler.setBowlerovers(1);
            bowler.resetBalls();
        }
        this.bowlerViewsUpdater.changeBowlerOverText(bowler);
        return bowler;
    }

    public Bowler changeBowlerMaiderOvers(Bowler bowler, Over over) {
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= over.perballScore.size()) {
                z = true;
                break;
            }
            int intValue = over.perballScore.get(i).intValue();
            if ((intValue > 0 && intValue <= 8) || ((intValue >= 11 && intValue <= 18) || (intValue >= 21 && intValue <= 62))) {
                break;
            }
            i++;
        }
        if (z) {
            bowler.setMaidens(1);
            over.maiden = true;
        }
        return bowler;
    }

    public Bowler getBowlerFromPreviousBowlers(Team team, String str) {
        for (int i = 0; i < team.bowlers_list.size(); i++) {
            if (team.bowlers_list.get(i).getName().equals(str)) {
                return team.bowlers_list.get(i);
            }
        }
        return null;
    }

    public void decrementBowlerBalls(Bowler bowler, int i) {
        if (bowler.getBalls() == 0) {
            bowler.setBalls(i - 1);
            bowler.setBowlerovers(-1);
            return;
        }
        bowler.setBalls(-1);
        if (bowler.getBalls() < 0) {
            bowler.setBowlerovers(-1);
            bowler.resetBalls();
        }
    }

    public static void setPointsToBowler(Bowler bowler) {
        double d;
        bowler.setPoints(bowler.getSingleMatchWickets() * 20);
        bowler.setPoints(bowler.getSingleMatchMaidens() * 40);
        try {
            d = Double.parseDouble(bowler.getSingleMatchEco());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            d = 0.0d;
        }
        if (bowler.getSingleMatchEco().equals(IdManager.DEFAULT_VERSION_NAME)) {
            bowler.setPoints(0);
        } else if (d >= 0.0d && d < 2.0d) {
            bowler.setPoints(30);
        } else if (d >= 2.0d && d < 4.0d) {
            bowler.setPoints(20);
        } else if (d >= 4.0d && d < 5.99d) {
            bowler.setPoints(10);
        } else if (d >= 8.0d && d < 9.99d) {
            bowler.setPoints(-10);
        } else if (d >= 10.0d && d < 11.99d) {
            bowler.setPoints(-20);
        } else if (d > 12.0d) {
            bowler.setPoints(-30);
        }
        int singleMatchWickets = bowler.getSingleMatchWickets();
        if (singleMatchWickets == 2) {
            bowler.setPoints(10);
        } else if (singleMatchWickets == 3) {
            bowler.setPoints(20);
        } else if (singleMatchWickets == 4) {
            bowler.setPoints(40);
        } else if (singleMatchWickets == 5) {
            bowler.setPoints(80);
        } else if (singleMatchWickets > 5) {
            bowler.setPoints(100);
        }
    }

    public static void setThreeFourAndFiveWicektsToTheBowler(Bowler bowler, int i) {
        if (i == 3) {
            bowler.setThreeFor(1);
        } else if (i == 4) {
            bowler.setFourFor(1);
        } else if (i >= 5) {
            bowler.setFiveFor(1);
        }
    }
}
