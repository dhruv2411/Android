package apps.shehryar.com.cricketscroingappPro.Team;

import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman;
import apps.shehryar.com.cricketscroingappPro.Bowler.Bowler;
import apps.shehryar.com.cricketscroingappPro.FallOfWickets;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Model.SuggestionListItem;
import apps.shehryar.com.cricketscroingappPro.Overs.Over;
import apps.shehryar.com.cricketscroingappPro.Partenership.Partenership;
import apps.shehryar.com.cricketscroingappPro.Player.Player;
import apps.shehryar.com.cricketscroingappPro.Score;
import apps.shehryar.com.cricketscroingappPro.Tournament.TournamentEntity;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.UtilityFunctions;
import io.fabric.sdk.android.services.common.IdManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Team extends Score implements Serializable, TournamentEntity, SuggestionListItem {
    public static final int TEAM_OPPONENT = 1;
    public static final int TEAM_YOURS = 0;
    public static final long serialVersionUID = 42;
    ArrayList<String> allPlayerNames;
    public ArrayList<Batsman> batsmans_list;
    public ArrayList<Bowler> bowlers_list;
    int byes;
    long captainId;
    int extras;
    public ArrayList<FallOfWickets> fallOfWicketses;
    HighestTotal highestChased;
    HighestTotal highestDefended;
    HighestTotal highestTotal;
    public String image;
    long keeperId;
    int legbyes;
    Match match;
    int matchTotalOvers;
    int matches_Played;
    int matches_lost;
    int matches_tied;
    int matches_won;
    String name;
    double net_Run_Rate;
    int nos;
    private String oldName;
    Team opponentTeam;
    int overballs;
    double oversDotBalls;
    int oversPlayed;
    public ArrayList<Over> overs_list;
    public ArrayList<Partenership> partenerships;
    private ArrayList<Player> players;
    int points;
    public int ranking;
    int score;
    public ArrayList<Long> teamAllIDs;
    long teamID;
    int teamSide;
    public String viewHeading;
    int wickets;
    int wides;

    public int getStatsType() {
        return 0;
    }

    public SuggestionListItem getSuggestionListItem() {
        return this;
    }

    public void setStatsType(int i) {
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public String getOldName() {
        return this.oldName;
    }

    public void setOldName(String str) {
        this.oldName = str;
    }

    public HighestTotal getHighestTotal() {
        return this.highestTotal;
    }

    public void setHighestTotal(HighestTotal highestTotal2) {
        this.highestTotal = highestTotal2;
    }

    public HighestTotal getHighestDefended() {
        return this.highestDefended;
    }

    public void setHighestDefended(HighestTotal highestTotal2) {
        this.highestDefended = highestTotal2;
    }

    public static ArrayList<SuggestionListItem> getSuggestionListItems(ArrayList<Team> arrayList) {
        ArrayList<SuggestionListItem> arrayList2 = new ArrayList<>();
        Iterator<Team> it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(it.next());
        }
        return arrayList2;
    }

    public HighestTotal getHighestChased() {
        return this.highestChased;
    }

    public void setHighestChased(HighestTotal highestTotal2) {
        this.highestChased = highestTotal2;
    }

    public int getRanking() {
        return this.ranking;
    }

    public void setRanking(int i) {
        this.ranking = i;
    }

    public Match getMatch() {
        return this.match;
    }

    public void setMatch(Match match2) {
        this.match = match2;
    }

    public double getOversDotBalls() {
        return ((double) getOversPlayed()) + ((double) (getOverballs() / 6));
    }

    public void setOversDotBalls(double d) {
        this.oversDotBalls += d;
    }

    public Team() {
        this.points = 0;
        this.teamAllIDs = new ArrayList<>();
        this.batsmans_list = new ArrayList<>();
        this.bowlers_list = new ArrayList<>();
        this.overs_list = new ArrayList<>();
        this.fallOfWicketses = new ArrayList<>();
        this.partenerships = new ArrayList<>();
        this.players = new ArrayList<>();
        this.allPlayerNames = new ArrayList<>();
    }

    public Team(Team team) {
        this.points = 0;
        this.teamAllIDs = new ArrayList<>();
        this.batsmans_list = new ArrayList<>();
        this.bowlers_list = new ArrayList<>();
        this.overs_list = new ArrayList<>();
        this.fallOfWicketses = new ArrayList<>();
        this.partenerships = new ArrayList<>();
        this.captainId = team.captainId;
        this.keeperId = team.keeperId;
        this.opponentTeam = team.opponentTeam;
        this.name = team.name;
        this.score += team.getScore();
        this.oversPlayed += team.oversPlayed;
        this.overballs += team.overballs;
        this.points += team.getPoints();
        this.matches_lost += team.matches_lost;
        this.matches_won += team.matches_won;
        this.matches_Played += team.matches_Played;
        this.matches_tied += team.matches_tied;
        this.matchTotalOvers += team.matchTotalOvers;
        this.teamID = team.teamID;
        this.wickets += team.wickets;
        this.wides += team.wides;
        this.nos += team.nos;
        this.fallOfWicketses = new ArrayList<>(team.fallOfWicketses);
        this.byes = team.getByes();
        this.legbyes = team.getLegbyes();
        this.opponentTeam = team.getOpponentTeam();
        this.players = new ArrayList<>(team.getPlayers());
        this.overs_list = new ArrayList<>(team.overs_list);
        this.bowlers_list = new ArrayList<>(team.bowlers_list);
        this.batsmans_list = new ArrayList<>(team.batsmans_list);
        this.batsmans_list = new ArrayList<>(team.getBatsmans_list().size());
        Iterator<Batsman> it = team.getBatsmans_list().iterator();
        while (it.hasNext()) {
            this.batsmans_list.add(new Batsman(it.next()));
        }
        this.oversDotBalls = ((double) team.getOversPlayed()) + ((double) (team.getOverballs() / 6));
        this.highestTotal = team.getHighestTotal();
        this.highestChased = team.getHighestChased();
        this.highestDefended = team.getHighestDefended();
        this.partenerships = new ArrayList<>(team.partenerships);
        this.allPlayerNames = new ArrayList<>(team.allPlayerNames);
    }

    public long getCaptainId() {
        return this.captainId;
    }

    public void setCaptainId(long j) {
        this.captainId = j;
    }

    public long getKeeperId() {
        return this.keeperId;
    }

    public void setKeeperId(long j) {
        this.keeperId = j;
    }

    public void combineTeamStats(Team team) {
        this.score += team.getScore();
        this.oversPlayed += team.oversPlayed;
        this.overballs += team.overballs;
        this.matches_lost += team.matches_lost;
        this.matches_won += team.matches_won;
        this.matches_Played += team.matches_Played;
        this.matches_tied += team.matches_tied;
        this.matchTotalOvers += team.matchTotalOvers;
        this.points += team.getPoints();
        this.teamID = team.teamID;
        this.wickets += team.wickets;
        this.wides += team.wides;
        this.nos += team.nos;
        this.ranking = team.ranking;
        if (this.highestTotal == null && team.getHighestTotal() != null) {
            this.highestTotal = team.getHighestTotal();
        } else if (!(this.highestTotal == null || team.getHighestTotal() == null)) {
            this.highestTotal = this.highestTotal.compareHighestChased(team.getHighestTotal());
        }
        if (this.highestChased == null && team.getHighestChased() != null) {
            this.highestChased = team.getHighestChased();
        } else if (!(this.highestChased == null || team.getHighestChased() == null)) {
            this.highestChased = this.highestChased.compareHighestChased(team.getHighestChased());
        }
        if (this.highestDefended == null && team.highestDefended != null) {
            this.highestDefended = team.highestDefended;
        } else if (this.highestDefended != null && team.highestDefended != null) {
            this.highestDefended = this.highestDefended.compareHighestDefended(team.getHighestChased());
        }
    }

    public ArrayList<String> getAllPlayerNames() {
        return this.allPlayerNames;
    }

    public void setAllPlayerNames(ArrayList<String> arrayList) {
        this.allPlayerNames = arrayList;
    }

    public Team getOpponentTeam() {
        return this.opponentTeam;
    }

    public void setOpponentTeam(Team team) {
        this.opponentTeam = team;
    }

    public int getTeamSide() {
        return this.teamSide;
    }

    public void setTeamSide(int i) {
        this.teamSide = i;
    }

    public ArrayList<Long> getTeamAllIDs() {
        return this.teamAllIDs;
    }

    public void setTeamAllIDs(ArrayList<Long> arrayList) {
        this.teamAllIDs = arrayList;
    }

    public ArrayList<Over> getOvers_list() {
        return this.overs_list;
    }

    public void setOvers_list(ArrayList<Over> arrayList) {
        this.overs_list = arrayList;
    }

    public ArrayList<FallOfWickets> getFallOfWicketses() {
        return this.fallOfWicketses;
    }

    public void setFallOfWicketses(ArrayList<FallOfWickets> arrayList) {
        this.fallOfWicketses = arrayList;
    }

    public ArrayList<Partenership> getPartenerships() {
        return this.partenerships;
    }

    public void setPartenerships(ArrayList<Partenership> arrayList) {
        this.partenerships = arrayList;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public ArrayList<String> getAllPlayersNames() {
        ArrayList<String> arrayList = new ArrayList<>();
        if (this.players != null) {
            Iterator<Player> it = this.players.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().getName());
            }
        }
        return arrayList;
    }

    public ArrayList<String> getAllPlayingXIPlayers() {
        int i;
        try {
            i = UtilityFunctions.getNoOfPlayersWithPlayingXI(this);
        } catch (Exception e) {
            e.printStackTrace();
            i = 0;
        }
        if (i < 11) {
            return getAllPlayersNames();
        }
        ArrayList<String> arrayList = new ArrayList<>();
        if (this.players != null) {
            Iterator<Player> it = this.players.iterator();
            while (it.hasNext()) {
                Player next = it.next();
                if (next.isInPlayingXI()) {
                    arrayList.add(next.getName());
                }
            }
        }
        return arrayList == null ? new ArrayList<>() : arrayList;
    }

    public void setPlayers(ArrayList<Player> arrayList) {
        this.players = arrayList;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int i) {
        this.points += i;
    }

    public int getMatchTotalOvers() {
        return this.matchTotalOvers;
    }

    public void setMatchTotalOvers(int i) {
        this.matchTotalOvers = i;
    }

    public long getTeamID() {
        return this.teamID;
    }

    public void setTeamID(long j) {
        this.teamID = j;
    }

    public int getMatches_Played() {
        return this.matches_Played;
    }

    public void setMatches_Played(int i) {
        this.matches_Played += i;
    }

    public int getMatches_won() {
        return this.matches_won;
    }

    public void setMatches_won(int i) {
        this.matches_won += i;
    }

    public int getMatches_lost() {
        return this.matches_lost;
    }

    public void setMatches_lost(int i) {
        this.matches_lost += i;
    }

    public int getMatches_tied() {
        return this.matches_tied;
    }

    public void setMatches_tied(int i) {
        this.matches_tied += i;
    }

    public double getNet_Run_Rate() {
        double d;
        double d2;
        if (this.match != null && this.match.getResult() != null && (this.match.getResult().equals("No result") || this.match.getTeam1().getScore() == 0 || this.match.getTeam2().getScore() == 0)) {
            return 0.0d;
        }
        if (getWickets() == 10) {
            d = (double) getMatchTotalOvers();
        } else {
            d = getOversDotBalls();
        }
        if (this.opponentTeam.getWickets() == 10) {
            d2 = (double) this.opponentTeam.getMatchTotalOvers();
        } else {
            d2 = this.opponentTeam.getOversDotBalls();
        }
        double score2 = ((double) this.opponentTeam.getScore()) / d2;
        double score3 = (((double) getScore()) / d) - score2;
        if (score2 == 0.0d) {
            score3 = 0.0d;
        }
        if (Double.isInfinite(score3)) {
            return 0.0d;
        }
        return score3;
    }

    public void setNet_Run_Rate(double d) {
        this.net_Run_Rate = d;
    }

    public int getOversPlayed() {
        return this.oversPlayed;
    }

    public void setOversPlayed(int i) {
        this.oversPlayed += i;
    }

    public int getOverballs() {
        return this.overballs;
    }

    public void setOverballs(int i) {
        this.overballs = i;
    }

    public int getByes() {
        return this.byes;
    }

    public void setByes(int i) {
        this.byes += i;
    }

    public int getLegbyes() {
        return this.legbyes;
    }

    public void setLegbyes(int i) {
        this.legbyes += i;
    }

    public String getName() {
        return this.name;
    }

    public String getStats(int i) {
        return getScore() + "";
    }

    public String getImgUrl() {
        return getImage() + "";
    }

    public void setHeading(String str) {
        this.viewHeading = str;
    }

    public String getHeading() {
        return this.viewHeading;
    }

    public void setName(String str) {
        this.name = str;
    }

    public ArrayList<Batsman> getBatsmans_list() {
        return this.batsmans_list;
    }

    public void setBatsmans_list(ArrayList<Batsman> arrayList) {
        this.batsmans_list = arrayList;
    }

    public ArrayList<Bowler> getBowlers_list() {
        return this.bowlers_list;
    }

    public void setBowlers_list(ArrayList<Bowler> arrayList) {
        this.bowlers_list = arrayList;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int i) {
        this.score += i;
    }

    public int getWickets() {
        return this.wickets;
    }

    public void setWickets(int i) {
        this.wickets += i;
    }

    public int getExtras() {
        return this.extras;
    }

    public void setExtras(int i) {
        this.extras += i;
    }

    public int getWides() {
        return this.wides;
    }

    public void setWides(int i) {
        this.wides += i;
    }

    public int getNos() {
        return this.nos;
    }

    public void setNos(int i) {
        this.nos += i;
    }

    public void clearNos() {
        this.nos = 0;
    }

    public void clearWides() {
        this.wides = 0;
    }

    public void clearByes() {
        this.byes = 0;
    }

    public void clearLegByes() {
        this.legbyes = 0;
    }

    public String getExtrasString() {
        return "EXTRAS: " + getExtras() + "(B:" + getByes() + " LB:" + getLegbyes() + " NB:" + getNos() + " WD:" + getWides() + ")";
    }

    public String getRunRate() {
        try {
            return String.format("%.2f", new Object[]{Float.valueOf((((float) getScore()) / ((float) ((getOversPlayed() * 6) + getOverballs()))) * 6.0f)});
        } catch (Exception e) {
            e.printStackTrace();
            return IdManager.DEFAULT_VERSION_NAME;
        }
    }

    public int getProjectScore() {
        try {
            return (int) (Float.parseFloat(getRunRate()) * ((float) getMatchTotalOvers()));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getOversString() {
        return "(" + getOversPlayed() + "." + getOverballs() + "/" + getMatchTotalOvers() + ")";
    }

    public String getOversStringForTestMatch() {
        return "(" + getOversPlayed() + "." + getOverballs() + ")";
    }

    public String getSuggestionText() {
        return getName();
    }
}
