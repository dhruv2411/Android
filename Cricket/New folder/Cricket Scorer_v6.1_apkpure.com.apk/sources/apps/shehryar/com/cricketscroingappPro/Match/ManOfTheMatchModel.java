package apps.shehryar.com.cricketscroingappPro.Match;

import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman;
import apps.shehryar.com.cricketscroingappPro.Bowler.Bowler;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class ManOfTheMatchModel implements Serializable {
    public static final long serialVersionUID = 42;
    Batsman batsman;
    long batsmanId;
    Bowler bowler;
    long bowlerId;
    long id;
    long matchId;
    long teamId;

    public long getBatsmanId() {
        return this.batsmanId;
    }

    public void setBatsmanId(long j) {
        this.batsmanId = j;
    }

    public long getBowlerId() {
        return this.bowlerId;
    }

    public void setBowlerId(long j) {
        this.bowlerId = j;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public long getMatchId() {
        return this.matchId;
    }

    public void setMatchId(long j) {
        this.matchId = j;
    }

    public long getTeamId() {
        return this.teamId;
    }

    public void setTeamId(long j) {
        this.teamId = j;
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

    public static ArrayList<String> getStringsForMOMCandidates(ArrayList<ManOfTheMatchModel> arrayList) {
        ArrayList<String> arrayList2 = new ArrayList<>();
        Iterator<ManOfTheMatchModel> it = arrayList.iterator();
        while (it.hasNext()) {
            ManOfTheMatchModel next = it.next();
            if (next.getBatsman() != null && next.getBowler() != null) {
                arrayList2.add(next.getBatsman().getName() + " for his " + next.getBatsman().getTotalScore() + " runs and " + next.getBowler().getWickets() + " wickets");
            } else if (next.getBatsman() != null) {
                arrayList2.add(next.getBatsman().getName() + " for his " + next.getBatsman().getTotalScore() + " runs ");
            } else if (next.getBowler() != null) {
                arrayList2.add(next.getBowler().getName() + " for his " + next.getBowler().getWickets() + " wickets");
            }
        }
        return arrayList2;
    }

    public String getManOfTheMatchString(ManOfTheMatchModel manOfTheMatchModel) throws Exception {
        if (manOfTheMatchModel == null) {
            manOfTheMatchModel = this;
        }
        if (manOfTheMatchModel.getBatsman() != null && manOfTheMatchModel.getBowler() != null) {
            return "MOTM:" + manOfTheMatchModel.getBatsman().getName() + " for his " + manOfTheMatchModel.getBatsman().getTotalScore() + " runs and " + manOfTheMatchModel.getBowler().getWickets() + " wickets";
        } else if (manOfTheMatchModel.getBatsman() != null) {
            return "MOTM:" + manOfTheMatchModel.getBatsman().getName() + " for his " + manOfTheMatchModel.getBatsman().getTotalScore() + " runs ";
        } else if (manOfTheMatchModel.getBowler() == null) {
            return null;
        } else {
            return "MOTM:" + manOfTheMatchModel.getBowler().getName() + " for his " + manOfTheMatchModel.getBowler().getWickets() + " wickets";
        }
    }

    public Batsman getManOfTheMatchBatsman(Team team) {
        Iterator<Batsman> it = team.getBatsmans_list().iterator();
        while (it.hasNext()) {
            Batsman next = it.next();
            if (next.getBatsmanID() == this.batsmanId) {
                return next;
            }
        }
        return null;
    }

    public Bowler getManOfTheMatchBowler(Team team) {
        Iterator<Bowler> it = team.getBowlers_list().iterator();
        while (it.hasNext()) {
            Bowler next = it.next();
            if (this.bowler.getBowlerID() == this.bowlerId) {
                return this.bowler;
            }
        }
        return null;
    }
}
