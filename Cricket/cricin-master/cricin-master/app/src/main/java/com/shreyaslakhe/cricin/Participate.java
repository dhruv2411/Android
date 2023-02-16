package com.shreyaslakhe.cricin;

/**
 * Created by vishal on 10/23/2015.
 */
public class Participate {
    int _rank;
    int _team_id;
    int _tournament_id;
    public Participate(){}
    public Participate(int tournament_id,int team_id,int rank) {

        this._rank = rank;
        this._tournament_id = tournament_id;
        this._team_id = team_id;

    }
    public int getTournament_id() {
        return _tournament_id;
    }

    public void setTournament_id(int tournament_id) {
        this._tournament_id = tournament_id;
    }

    public int getTeam_id() {
        return _team_id;
    }

    public void setTeam_id(int team_id) {
        this._team_id = team_id;
    }

    public int get_rank() {
        return _rank;
    }

    public void set_rank(int _rank) {
        this._rank = _rank;
    }
}
