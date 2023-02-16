package apps.shehryar.com.cricketscroingappPro.Match;

import android.util.Log;
import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman;
import apps.shehryar.com.cricketscroingappPro.Bowler.Bowler;
import apps.shehryar.com.cricketscroingappPro.Overs.Over;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.UtilityFunctions;
import io.fabric.sdk.android.services.common.IdManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Match implements Serializable {
    public static final int ALL_MATCHES = 3;
    public static final int FIRST_INNS = 1;
    public static final int FIRST_INNS_END = 5;
    public static final int FOURTH_INNS = 4;
    public static final int LIMITED_OVER_MATCHES = 2;
    public static final int SECOND_INNS = 2;
    public static final int SECOND_INNS_END = 6;
    public static final int TEST_MATCHES = 1;
    public static final int THIRD_INNS = 3;
    public static final int THIRD_INNS_END = 7;
    public static final int UNFINISHED_MATCHES = 4;
    public static final long serialVersionUID = 42;
    public String ManOfTheMatch;
    public ArrayList allscoreslist = new ArrayList();
    private String date;
    private int extras;
    public boolean firstInnings = true;
    public boolean firstInningsEnd = false;
    public boolean followedOn = false;
    public boolean fourthInnings = false;
    public boolean isTestMatch = false;
    ManOfTheMatchModel manOfTheMatchModel;
    private long matchID;
    private boolean maxBallsFeature;
    private boolean noRunForNo;
    private boolean noRunForWide;
    private int noballs;
    private String opponent;
    public int overs;
    private int perMatchWickets = 10;
    private int perOverBalls = 6;
    private String result = "No result";
    String resumeBatNo;
    String resumeBowler;
    String resumeInnings;
    public ResumeMatch resumeMatch = new ResumeMatch();
    String resumeOverPerBallScore;
    public int score;
    public boolean secondInnings = false;
    public boolean secondInningsEnd = false;
    private boolean selectMOMmanually;
    public Team team1 = new Team();
    public Team team2 = new Team();
    public Team team3 = new Team();
    public Team team4 = new Team();
    private long team_Yours_id;
    private long team_Yours_id2;
    private long team_opp_id;
    private long team_opp_id2;
    public boolean thirdInnings = false;
    public boolean thirdInningsEnd = false;
    public String time;
    public String tossResult;
    private String tournament;
    private String tournamentStage;
    private String venue;
    private int wickets;
    private int wides;
    Team winningTeam;
    private String yourteam;

    public static long getSerialVersionUID() {
        return 42;
    }

    public ManOfTheMatchModel getManOfTheMatchModel() {
        return this.manOfTheMatchModel;
    }

    public void setManOfTheMatchModel(ManOfTheMatchModel manOfTheMatchModel2) {
        this.manOfTheMatchModel = manOfTheMatchModel2;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public String getManOfTheMatch() {
        return this.ManOfTheMatch;
    }

    public void setManOfTheMatch(String str) {
        this.ManOfTheMatch = str;
    }

    public String getTossResult() {
        return this.tossResult != null ? this.tossResult : "";
    }

    public void setTossResult(String str) {
        this.tossResult = str;
    }

    public Team getWinningTeam() {
        int i = 0;
        if (!this.isTestMatch) {
            if (this.result.equals("No result") || this.result.equals("Match Drawn")) {
                return combineWinningTeams(this.team1, this.team2, false);
            }
            String[] split = this.result.split(" ");
            String str = "";
            while (i < split.length && !split[i].equals("won")) {
                str = str + split[i] + " ";
                i++;
            }
            if (this.team1.getName().concat(" ").equals(str)) {
                return new Team(this.team1);
            }
            return new Team(this.team2);
        } else if (this.result.equals("No result") || this.result.equals("Match Drawn")) {
            getWinningTeamInCaseOfDraw();
            return this.winningTeam;
        } else {
            String[] split2 = this.result.split(" ");
            String str2 = "";
            while (i < split2.length && !split2[i].equals("won")) {
                str2 = str2 + split2[i] + " ";
                i++;
            }
            if (this.team1.getName().concat(" ").equals(str2)) {
                if (this.followedOn) {
                    return combineWinningTeams(this.team1, this.team4, true);
                }
                return combineWinningTeams(this.team1, this.team3, true);
            } else if (this.followedOn) {
                return combineWinningTeams(this.team2, this.team3, true);
            } else {
                return combineWinningTeams(this.team2, this.team4, true);
            }
        }
    }

    public void setWinningTeam(Team team) {
        this.winningTeam = team;
    }

    public ResumeMatch getResumeMatch() {
        return this.resumeMatch;
    }

    public void setResumeMatch(ResumeMatch resumeMatch2) {
        this.resumeMatch = resumeMatch2;
    }

    public boolean isNoRunForWide() {
        return this.noRunForWide;
    }

    public void setNoRunForWide(boolean z) {
        this.noRunForWide = z;
    }

    public boolean isNoRunForNo() {
        return this.noRunForNo;
    }

    public void setNoRunForNo(boolean z) {
        this.noRunForNo = z;
    }

    public void setNoRunForNo(int i) {
        if (i == 1) {
            setNoRunForNo(true);
        } else {
            setNoRunForNo(false);
        }
    }

    public void setNoRunForWide(int i) {
        if (i == 1) {
            setNoRunForWide(true);
        } else {
            setNoRunForWide(false);
        }
    }

    public void switchInnings() {
        if (!this.isTestMatch) {
            if (this.firstInnings || this.firstInningsEnd) {
                setInnings(2);
            } else if (this.secondInnings) {
                setInnings(1);
            }
        } else if (this.firstInnings) {
            setInnings(2);
        } else if (this.secondInnings) {
            setInnings(3);
        } else if (this.thirdInnings) {
            setInnings(4);
        } else if (this.firstInningsEnd) {
            setInnings(2);
        } else if (this.secondInningsEnd) {
            setInnings(3);
        } else if (this.thirdInningsEnd) {
            setInnings(4);
        }
    }

    public void setInnings(int i) {
        switch (i) {
            case 1:
                setInnings(true, false, false, false, false, false, false);
                return;
            case 2:
                setInnings(false, true, false, false, false, false, false);
                return;
            case 3:
                setInnings(false, false, true, false, false, false, false);
                return;
            case 4:
                setInnings(false, false, false, true, false, false, false);
                return;
            case 5:
                setInnings(false, false, false, false, true, false, false);
                return;
            case 6:
                setInnings(false, false, false, false, false, true, false);
                return;
            case 7:
                setInnings(false, false, false, false, false, false, true);
                return;
            default:
                return;
        }
    }

    public void setInnings(boolean z) {
        this.firstInnings = z;
        this.secondInnings = !z;
    }

    public void setInnings(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        this.firstInnings = z;
        this.secondInnings = z2;
        this.thirdInnings = z3;
        this.fourthInnings = z4;
        this.firstInningsEnd = z5;
        this.secondInningsEnd = z6;
        this.thirdInningsEnd = z7;
    }

    public void swapTeams() {
        Team team = new Team(this.team3);
        this.team3 = this.team4;
        this.team4 = team;
        Log.i("team 3 name", this.team3.getName());
    }

    public Team getBattingTeam() {
        if (!this.isTestMatch) {
            if (this.firstInnings) {
                return this.team1;
            }
            return this.team2;
        } else if (this.firstInnings) {
            return this.team1;
        } else {
            if (this.secondInnings) {
                return this.team2;
            }
            if (this.thirdInnings) {
                return this.team3;
            }
            if (this.fourthInnings) {
                return this.team4;
            }
            if (this.firstInningsEnd) {
                return this.team2;
            }
            if (this.secondInningsEnd) {
                return this.team3;
            }
            if (this.thirdInningsEnd) {
                return this.team4;
            }
            return null;
        }
    }

    public Team combineWinningTeams(Team team, Team team5, boolean z) {
        Team team6 = new Team(team);
        Team team7 = new Team(team5);
        Team team8 = new Team();
        ArrayList arrayList = new ArrayList();
        if (z) {
            team8.setBatsmans_list(team6.getBatsmans_list());
            Iterator<Batsman> it = team8.getBatsmans_list().iterator();
            while (it.hasNext()) {
                Batsman next = it.next();
                Iterator<Batsman> it2 = team7.getBatsmans_list().iterator();
                while (it2.hasNext()) {
                    Batsman next2 = it2.next();
                    if (next.getName().equals(next2.getName())) {
                        next.setTotalScore(next2.getTotalScore());
                        next.setBallsfaced(next2.getBallsfaced());
                    } else if (!UtilityFunctions.checkBatsman(team8.getBatsmans_list(), next2)) {
                        arrayList.add(next2);
                    }
                }
            }
            Iterator it3 = arrayList.iterator();
            while (it3.hasNext()) {
                team8.getBatsmans_list().add((Batsman) it3.next());
            }
            ArrayList arrayList2 = new ArrayList();
            team8.setBowlers_list(team6.getBowlers_list());
            Iterator<Bowler> it4 = team8.getBowlers_list().iterator();
            while (it4.hasNext()) {
                Bowler next3 = it4.next();
                Iterator<Bowler> it5 = team7.getBowlers_list().iterator();
                while (it5.hasNext()) {
                    Bowler next4 = it5.next();
                    if (next3.getName() != null && next3.getName().equals(next4.getName())) {
                        next3.setTotalwickets(next4.getTotalwickets());
                    } else if (!UtilityFunctions.checkBowler(team8.getBowlers_list(), next4)) {
                        arrayList2.add(next4);
                    }
                }
            }
            Iterator it6 = arrayList2.iterator();
            while (it6.hasNext()) {
                team8.getBowlers_list().add((Bowler) it6.next());
            }
        } else {
            Iterator<Batsman> it7 = team6.getBatsmans_list().iterator();
            while (it7.hasNext()) {
                team8.getBatsmans_list().add(it7.next());
            }
            Iterator<Batsman> it8 = team7.getBatsmans_list().iterator();
            while (it8.hasNext()) {
                team8.getBatsmans_list().add(it8.next());
            }
            Iterator<Bowler> it9 = team7.getBowlers_list().iterator();
            while (it9.hasNext()) {
                team8.getBowlers_list().add(it9.next());
            }
            Iterator<Bowler> it10 = team6.getBowlers_list().iterator();
            while (it10.hasNext()) {
                team8.getBowlers_list().add(it10.next());
            }
        }
        return team8;
    }

    public Team getBowlingTeam() {
        if (!this.isTestMatch) {
            if (this.firstInnings) {
                return this.team2;
            }
            return this.team1;
        } else if (this.firstInnings) {
            return this.team2;
        } else {
            if (this.secondInnings) {
                return this.team1;
            }
            if (this.thirdInnings) {
                return this.team4;
            }
            if (this.fourthInnings) {
                return this.team3;
            }
            if (this.firstInningsEnd) {
                return this.team1;
            }
            if (this.secondInningsEnd) {
                return this.team4;
            }
            if (this.thirdInningsEnd) {
                return this.team3;
            }
            return null;
        }
    }

    public int getWideExtras() {
        return this.noRunForWide ? 0 : 1;
    }

    public int getInnings() {
        if (this.firstInnings) {
            return 1;
        }
        if (this.secondInnings) {
            return 2;
        }
        return this.thirdInnings ? 3 : 4;
    }

    public String getInningsString() {
        return !this.isTestMatch ? this.firstInnings ? "First Innings" : "Second Innings" : (this.firstInnings || this.secondInnings) ? "First Innings" : "Second Innings";
    }

    public int getAfterInnings() {
        if (this.firstInnings || this.secondInnings) {
            return 5;
        }
        if (this.thirdInnings) {
            return 6;
        }
        return this.fourthInnings ? 7 : 0;
    }

    public int getNoExtras() {
        return this.noRunForNo ? 0 : 1;
    }

    public boolean isMaxBallsFeature() {
        return this.maxBallsFeature;
    }

    public void setMaxBallsFeature(boolean z) {
        this.maxBallsFeature = z;
    }

    public void setMaxBallsFeature(int i) {
        if (i == 1) {
            setMaxBallsFeature(true);
        } else {
            setMaxBallsFeature(false);
        }
    }

    public boolean isSelectMOMmanually() {
        return this.selectMOMmanually;
    }

    public void setSelectMOMmanually(boolean z) {
        this.selectMOMmanually = z;
    }

    public void setSelectMOMmanually(int i) {
        if (i == 1) {
            setSelectMOMmanually(true);
        } else {
            setSelectMOMmanually(false);
        }
    }

    public int getPerOverBalls() {
        return this.perOverBalls;
    }

    public void setPerOverBalls(int i) {
        this.perOverBalls = i;
    }

    public int getPerMatchWickets() {
        return this.perMatchWickets;
    }

    public void setPerMatchWickets(int i) {
        this.perMatchWickets = i;
    }

    public String getTournament() {
        return this.tournament;
    }

    public void setTournament(String str) {
        this.tournament = str;
    }

    public String getTournamentStage() {
        return this.tournamentStage;
    }

    public void setTournamentStage(String str) {
        this.tournamentStage = str;
    }

    public long getTeam_Yours_id() {
        return this.team_Yours_id;
    }

    public void setTeam_Yours_id(long j) {
        this.team_Yours_id = j;
    }

    public long getTeam_opp_id() {
        return this.team_opp_id;
    }

    public void setTeam_opp_id(long j) {
        this.team_opp_id = j;
    }

    public long getTeam_Yours_id2() {
        return this.team_Yours_id2;
    }

    public void setTeam_Yours_id2(long j) {
        this.team_Yours_id2 = j;
    }

    public long getTeam_opp_id2() {
        return this.team_opp_id2;
    }

    public void setTeam_opp_id2(long j) {
        this.team_opp_id2 = j;
    }

    public long getMatchID() {
        return this.matchID;
    }

    public int getExtraRuns(boolean z, boolean z2) {
        if (z) {
            return getWideExtras();
        }
        if (z2) {
            return getNoExtras();
        }
        return 0;
    }

    public void setMatchID(long j) {
        this.matchID = j;
    }

    public ArrayList getAllscoreslist() {
        return this.allscoreslist;
    }

    public void setAllscoreslist(ArrayList arrayList) {
        this.allscoreslist = arrayList;
    }

    public String getYourteam() {
        return this.yourteam;
    }

    public void setYourteam(String str) {
        this.yourteam = str;
    }

    public int getOvers() {
        return this.overs;
    }

    public void setOvers(int i) {
        this.overs = i;
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
        this.extras = i;
    }

    public int getNoballs() {
        return this.noballs;
    }

    public void setNoballs(int i) {
        this.noballs = i;
    }

    public int getWides() {
        return this.wides;
    }

    public void setWides(int i) {
        this.wides = i;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public String getOpponent() {
        return this.opponent;
    }

    public void setOpponent(String str) {
        this.opponent = str;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String str) {
        this.result = str;
    }

    public String getVenue() {
        return this.venue;
    }

    public void setVenue(String str) {
        this.venue = str;
    }

    public Team getTeam1() {
        return this.team1;
    }

    public void setTeam1(Team team) {
        this.team1 = team;
    }

    public Team getTeam2() {
        return this.team2;
    }

    public void setTeam2(Team team) {
        this.team2 = team;
    }

    public Team getTeam3() {
        return this.team3;
    }

    public void setTeam3(Team team) {
        this.team3 = team;
    }

    public Team getTeam4() {
        return this.team4;
    }

    public void setTeam4(Team team) {
        this.team4 = team;
    }

    public String getResumeInnings() {
        return this.resumeInnings;
    }

    public void setResumeInnings(String str) {
        this.resumeInnings = str;
    }

    public String getResumeBowler() {
        return this.resumeBowler;
    }

    public void setResumeBowler(String str) {
        this.resumeBowler = str;
    }

    public String getResumeBatNo() {
        return this.resumeBatNo;
    }

    public void setResumeBatNo(String str) {
        this.resumeBatNo = str;
    }

    public String getResumeOverPerBallScore() {
        return this.resumeOverPerBallScore;
    }

    public void setResumeOverPerBallScore(String str) {
        this.resumeOverPerBallScore = str;
    }

    public int isTestMatch() {
        return this.isTestMatch ? 1 : 0;
    }

    public void setTestMatch(int i) {
        if (i == 1) {
            this.isTestMatch = true;
        } else {
            this.isTestMatch = false;
        }
    }

    public int isFollowedOn() {
        return this.followedOn ? 1 : 0;
    }

    public void setFollowedOn(int i) {
        if (i == 1) {
            this.followedOn = true;
        } else {
            this.followedOn = false;
        }
    }

    public String getRemainingScoreText(Over over) {
        if (!this.isTestMatch) {
            if (this.firstInnings) {
                return "First Innings";
            }
            StringBuilder sb = new StringBuilder();
            sb.append("2nd Innings: ");
            sb.append(getTeam2().getName());
            sb.append(" needs ");
            sb.append((getTeam1().getScore() - getTeam2().getScore()) + 1);
            sb.append(" from ");
            sb.append((getOvers() - getBattingTeam().getOversPlayed()) - 1);
            sb.append(".");
            sb.append(getPerOverBalls() - over.getOverBalls());
            sb.append(" overs(");
            sb.append(UtilityFunctions.getNumberOfBallsFromOvers(getPerOverBalls(), (getOvers() - getBattingTeam().getOversPlayed()) - 1, getPerOverBalls() - over.getOverBalls()));
            sb.append(" balls)");
            return sb.toString();
        } else if (getFinishStatus(false) != null) {
            return getFinishStatus(false);
        } else {
            if (this.secondInnings) {
                if (this.team2.getScore() > this.team1.getScore()) {
                    return "1st Innings: " + this.team2.getName() + " lead by " + (this.team2.getScore() - this.team1.getScore()) + " runs";
                } else if (this.team2.getScore() >= this.team1.getScore()) {
                    return "1st Innings: Scores are level";
                } else {
                    return "1st Innings: " + this.team2.getName() + " trail by " + (this.team1.getScore() - this.team2.getScore()) + " runs";
                }
            } else if (this.thirdInnings) {
                if (this.followedOn) {
                    if (this.team1.getScore() > this.team2.getScore() + this.team3.getScore()) {
                        return "2nd Innings: " + this.team3.getName() + " trail by " + (this.team1.getScore() - (this.team2.getScore() + this.team3.getScore())) + " runs";
                    } else if (this.team1.getScore() >= this.team2.getScore() + this.team3.getScore()) {
                        return "2nd Innings: Scores are level";
                    } else {
                        return "2nd Innings: " + this.team3.getName() + " lead by " + ((this.team2.getScore() + this.team3.getScore()) - this.team1.getScore()) + " runs";
                    }
                } else if (this.team2.getScore() > this.team1.getScore() + this.team3.getScore()) {
                    return "2nd Innings: " + this.team3.getName() + " trail by " + (this.team2.getScore() - (this.team1.getScore() + this.team3.getScore())) + " runs";
                } else if (this.team2.getScore() >= this.team1.getScore() + this.team3.getScore()) {
                    return "2nd Innings: Scores are level";
                } else {
                    return "2nd Innings: " + this.team3.getName() + " lead by " + ((this.team1.getScore() + this.team3.getScore()) - this.team2.getScore()) + " runs";
                }
            } else if (!this.fourthInnings) {
                return "First Innings";
            } else {
                if (this.followedOn) {
                    if (this.team2.getScore() + this.team3.getScore() <= this.team1.getScore() + this.team4.getScore()) {
                        return "2nd Innings: Scores are level";
                    }
                    return "2nd Innings: " + this.team4.getName() + " need " + (((this.team2.getScore() + this.team3.getScore()) - (this.team1.getScore() + this.team4.getScore())) + 1) + " runs to win";
                } else if (this.team1.getScore() + this.team3.getScore() <= this.team2.getScore() + this.team4.getScore()) {
                    return "2nd Innings: Scores are level";
                } else {
                    return "2nd Innings: " + this.team4.getName() + " need " + (((this.team1.getScore() + this.team3.getScore()) - (this.team2.getScore() + this.team4.getScore())) + 1) + " runs to win";
                }
            }
        }
    }

    public double calculateRequiredRunRate() throws Exception {
        return ((((double) (getTeam1().getScore() + 1)) - ((double) getTeam2().getScore())) / ((double) ((getOvers() * this.perOverBalls) - ((getTeam2().getOversPlayed() * 6) + getTeam2().getOverballs())))) * ((double) this.perOverBalls);
    }

    public String getRequiredRunRate() {
        if (this.isTestMatch) {
            return "N/A";
        }
        if (this.firstInnings) {
            return IdManager.DEFAULT_VERSION_NAME;
        }
        try {
            return UtilityFunctions.convertDoubleToTwoValuesAfterPoint(calculateRequiredRunRate());
        } catch (Exception e) {
            e.printStackTrace();
            return IdManager.DEFAULT_VERSION_NAME;
        }
    }

    public String getFinishStatus(boolean z) {
        if (this.firstInnings || this.secondInnings) {
            return null;
        }
        if (this.thirdInnings) {
            if (this.followedOn) {
                if ((this.team3.getWickets() == this.perMatchWickets || this.team3.getOversPlayed() == getOvers() || z) && this.team3.getScore() + this.team2.getScore() < this.team1.getScore()) {
                    this.winningTeam = new Team(this.team1);
                    return this.team1.getName() + " won the match by an innings and " + (this.team1.getScore() - (this.team3.getScore() + this.team2.getScore())) + " runs";
                }
            } else if ((this.team3.getWickets() == this.perMatchWickets || this.team3.getOversPlayed() == getOvers() || z) && this.team3.getScore() + this.team1.getScore() < this.team2.getScore()) {
                this.winningTeam = new Team(this.team2);
                return this.team2.getName() + " won the match by an innings and " + (this.team2.getScore() - (this.team1.getScore() + this.team3.getScore())) + " runs";
            }
        } else if (this.fourthInnings) {
            if (!this.followedOn) {
                if ((this.team4.getWickets() == this.perMatchWickets || this.team4.getOversPlayed() == getOvers() || z) && this.team4.getScore() + this.team2.getScore() < this.team1.getScore() + this.team3.getScore()) {
                    this.winningTeam = combineWinningTeams(this.team1, this.team3, true);
                    return this.team1.getName() + " won the match by  " + ((this.team1.getScore() + this.team3.getScore()) - (this.team4.getScore() + this.team2.getScore())) + " runs";
                } else if (this.team4.getScore() + this.team2.getScore() > this.team1.getScore() + this.team3.getScore()) {
                    this.winningTeam = combineWinningTeams(this.team2, this.team4, true);
                    return this.team2.getName() + " won the match by " + (getPerMatchWickets() - this.team2.getWickets()) + " wickets";
                } else if ((this.team4.getWickets() == this.perMatchWickets || z) && this.team4.getScore() + this.team2.getScore() == this.team1.getScore() + this.team3.getScore()) {
                    getWinningTeamInCaseOfDraw();
                    return "Match Drawn";
                }
            } else if ((this.team4.getWickets() == this.perMatchWickets || z || this.team4.getOversPlayed() == getOvers()) && this.team3.getScore() + this.team2.getScore() > this.team1.getScore() + this.team4.getScore()) {
                this.winningTeam = combineWinningTeams(this.team1, this.team4, true);
                return this.team2.getName() + " won the match by  " + ((this.team2.getScore() + this.team3.getScore()) - (this.team1.getScore() + this.team4.getScore())) + " runs";
            } else if (this.team4.getScore() + this.team1.getScore() > this.team2.getScore() + this.team3.getScore()) {
                this.winningTeam = combineWinningTeams(this.team2, this.team4, true);
                return this.team1.getName() + " won the match by" + (getPerMatchWickets() - this.team4.getWickets()) + " wickets";
            } else if ((this.team4.getWickets() == this.perMatchWickets || z || this.team4.getOversPlayed() == getOvers()) && this.team4.getScore() + this.team1.getScore() == this.team2.getScore() + this.team3.getScore()) {
                getWinningTeamInCaseOfDraw();
                return "Match Drawn";
            }
        }
        return null;
    }

    public void getWinningTeamInCaseOfDraw() {
        if (this.followedOn) {
            this.winningTeam = combineWinningTeams(combineWinningTeams(new Team(this.team1), new Team(this.team4), true), combineWinningTeams(new Team(this.team2), new Team(this.team3), true), true);
        } else {
            this.winningTeam = combineWinningTeams(combineWinningTeams(new Team(this.team1), new Team(this.team3), true), combineWinningTeams(new Team(this.team2), new Team(this.team4), true), true);
        }
    }

    public String checkMatchFinished(boolean z) {
        if (this.isTestMatch) {
            return getFinishStatus(z);
        }
        if (!this.firstInnings && this.secondInnings) {
            if (this.team2.getScore() > this.team1.getScore()) {
                this.winningTeam = new Team(this.team2);
                return this.team2.getName() + " won the match by " + (this.perMatchWickets - this.team2.getWickets()) + " wickets";
            } else if ((this.team2.getWickets() == this.perMatchWickets || z || this.team2.getOversPlayed() == getOvers()) && this.team1.getScore() > this.team2.getScore()) {
                this.winningTeam = new Team(this.team1);
                return this.team1.getName() + " won the match by " + (this.team1.getScore() - this.team2.getScore()) + " runs";
            } else if ((this.team2.getWickets() == this.perMatchWickets || z || this.team2.getOversPlayed() == getOvers()) && this.team1.getScore() == this.team2.getScore()) {
                this.winningTeam = combineWinningTeams(this.team1, this.team2, false);
                return "Match Drawn";
            }
        }
        return null;
    }
}
