package apps.shehryar.com.cricketscroingappPro.Overs;

import android.app.Activity;
import android.content.Context;
import apps.shehryar.com.cricketscroingappPro.Team.TeamViewsUpdater;

public class OversDataUpdater {
    TeamViewsUpdater teamViewsUpdater;

    public Over updateOverScore(Over over, int i) {
        if (i != 0) {
            over.setOverscore(i);
            over.maiden = false;
        }
        return over;
    }

    public OversDataUpdater(Context context, Activity activity) {
        this.teamViewsUpdater = new TeamViewsUpdater(context, activity);
    }

    public Over addOverPerBallByesScore(Over over, int i) {
        switch (i) {
            case 1:
                over.perballScore.add(41);
                this.teamViewsUpdater.changeOverPerBallScore(over.perballScore);
                break;
            case 2:
                over.perballScore.add(42);
                this.teamViewsUpdater.changeOverPerBallScore(over.perballScore);
                break;
            case 3:
                over.perballScore.add(43);
                this.teamViewsUpdater.changeOverPerBallScore(over.perballScore);
                break;
            case 4:
                over.perballScore.add(44);
                this.teamViewsUpdater.changeOverPerBallScore(over.perballScore);
                break;
            case 5:
                over.perballScore.add(45);
                this.teamViewsUpdater.changeOverPerBallScore(over.perballScore);
                break;
            case 6:
                over.perballScore.add(46);
                this.teamViewsUpdater.changeOverPerBallScore(over.perballScore);
                break;
            case 7:
                over.perballScore.add(47);
                this.teamViewsUpdater.changeOverPerBallScore(over.perballScore);
                break;
        }
        return over;
    }

    public Over addOverPerBallLegByesScore(Over over, int i) {
        switch (i) {
            case 1:
                over.perballScore.add(51);
                this.teamViewsUpdater.changeOverPerBallScore(over.perballScore);
                break;
            case 2:
                over.perballScore.add(52);
                this.teamViewsUpdater.changeOverPerBallScore(over.perballScore);
                break;
            case 3:
                over.perballScore.add(53);
                this.teamViewsUpdater.changeOverPerBallScore(over.perballScore);
                break;
            case 4:
                over.perballScore.add(54);
                this.teamViewsUpdater.changeOverPerBallScore(over.perballScore);
                break;
            case 5:
                over.perballScore.add(55);
                this.teamViewsUpdater.changeOverPerBallScore(over.perballScore);
                break;
            case 6:
                over.perballScore.add(56);
                this.teamViewsUpdater.changeOverPerBallScore(over.perballScore);
                break;
            case 7:
                over.perballScore.add(57);
                this.teamViewsUpdater.changeOverPerBallScore(over.perballScore);
                break;
        }
        return over;
    }
}
