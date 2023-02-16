package apps.shehryar.com.cricketscroingappPro.Team;

import java.io.Serializable;
import java.util.ArrayList;

public class Team_players implements Serializable {
    public static final long serialVersionUID = 42;
    ArrayList<String> players;
    Team team;
    String teamname = " ";
    String teamside;

    public Team getTeam() {
        return this.team;
    }

    public void setTeam(Team team2) {
        this.team = team2;
    }

    public String getTeamname() {
        return this.teamname;
    }

    public void setTeamname(String str) {
        this.teamname = str;
    }

    public String getTeamside() {
        return this.teamside;
    }

    public void setTeamside(String str) {
        this.teamside = str;
    }

    public ArrayList<String> getPlayers() {
        return this.players;
    }

    public void setPlayers(ArrayList<String> arrayList) {
        this.players = arrayList;
    }
}
