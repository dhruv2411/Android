package apps.shehryar.com.cricketscroingappPro;

import android.app.Activity;
import android.os.Bundle;
import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman;
import apps.shehryar.com.cricketscroingappPro.Bowler.Bowler;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Team.Team;

public class Wicket {
    public static final int WICKET_BOWLED = 0;
    public static final int WICKET_CAUGHT = 1;
    public static final int WICKET_CAUGHTANDBOWLED = 6;
    public static final int WICKET_HANDLINGTHEBALL = 8;
    public static final int WICKET_LBW = 2;
    public static final int WICKET_OBSTRUCTINGTHEFIELD = 7;
    public static final int WICKET_RUNOUT = 5;
    public static final int WICKET_STUMPED = 3;
    public static final int WIKET_HITWICKET = 4;
    public static final long serialVersionUID = 42;
    private int batFacingForRuns;
    private int batFacingOut;
    private Batsman batsman;
    private Bowler bowler;
    private String catcherName;
    private boolean crossed;
    private boolean isBye;
    private boolean isLegBye;
    private boolean isNo;
    private boolean isRunOut;
    private boolean isWide;
    private int runsScored;
    private int wicketCode;
    private String wicketType;

    public int getRunsScored() {
        return this.runsScored;
    }

    public void setRunsScored(int i) {
        this.runsScored = i;
    }

    public Batsman getBatsman() {
        return this.batsman;
    }

    public void setBatsman(Batsman batsman2) {
        this.batsman = batsman2;
    }

    public Bowler getBowler() {
        return this.bowler;
    }

    public void setBowler(Bowler bowler2) {
        this.bowler = bowler2;
    }

    public int getWicketCode() {
        return this.wicketCode;
    }

    public void setWicketCode(int i) {
        this.wicketCode = i;
    }

    public int getBatFacingForRuns() {
        return this.batFacingForRuns;
    }

    public void setBatFacingForRuns(int i) {
        this.batFacingForRuns = i;
    }

    public int getBatFacingOut() {
        return this.batFacingOut;
    }

    public void setBatFacingOut(int i) {
        this.batFacingOut = i;
    }

    public boolean isWide() {
        return this.isWide;
    }

    public void setWide(boolean z) {
        this.isWide = z;
    }

    public boolean isNo() {
        return this.isNo;
    }

    public void setNo(boolean z) {
        this.isNo = z;
    }

    public boolean isBye() {
        return this.isBye;
    }

    public void setBye(boolean z) {
        this.isBye = z;
    }

    public boolean isLegBye() {
        return this.isLegBye;
    }

    public void setLegBye(boolean z) {
        this.isLegBye = z;
    }

    public boolean isRunOut() {
        return this.isRunOut;
    }

    public void setRunOut(boolean z) {
        this.isRunOut = z;
    }

    public boolean isCrossed() {
        return this.crossed;
    }

    public void setCrossed(boolean z) {
        this.crossed = z;
    }

    public String getCatcherName() {
        return this.catcherName;
    }

    public void setCatcherName(String str) {
        this.catcherName = str;
    }

    public String getWicketType() {
        switch (this.wicketCode) {
            case 0:
                return "Bowled";
            case 1:
                return "Caught";
            case 2:
                return "LBW";
            case 3:
                return "Stumped";
            case 4:
                return "Hit Wicket";
            case 5:
                return "Caught And Bowled";
            case 6:
                return "Obstructing the field";
            case 7:
                return "Handling the ball";
            default:
                return "";
        }
    }

    public void setWicketType(String str) {
        this.wicketType = str;
    }

    public static void showWicketDialog(Activity activity, int i, int i2, Team team, Batsman batsman2, Bowler bowler2, Match match) {
        WicketDialogFramgent wicketDialogFramgent = new WicketDialogFramgent();
        Bundle bundle = new Bundle();
        bundle.putInt("batFacing", i);
        bundle.putInt("wicketCode", i2);
        bundle.putSerializable("teamBat", team);
        bundle.putSerializable("batsman", batsman2);
        bundle.putSerializable("edBowler", bowler2);
        bundle.putSerializable(DBHelper.TABLE_MATCH, match);
        wicketDialogFramgent.setArguments(bundle);
        wicketDialogFramgent.show(activity.getFragmentManager(), "wicket dialog fragment");
    }

    public static void showRunOutDialog(Activity activity, int i, int i2, Team team, Batsman batsman2, Batsman batsman3, Bowler bowler2, Match match) {
        RunOutDialogFragment runOutDialogFragment = new RunOutDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("batFacing", i);
        bundle.putInt("wicketCode", i2);
        bundle.putSerializable("teamBat", team);
        bundle.putSerializable("batsman1", batsman2);
        bundle.putSerializable("batsman2", batsman3);
        bundle.putSerializable("edBowler", bowler2);
        bundle.putSerializable(DBHelper.TABLE_MATCH, match);
        runOutDialogFragment.setArguments(bundle);
        runOutDialogFragment.show(activity.getFragmentManager(), "wicket dialog fragment");
    }
}
