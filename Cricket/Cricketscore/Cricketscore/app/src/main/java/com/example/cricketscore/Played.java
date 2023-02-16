package com.example.cricketscore;

/**
 * Created by vishal on 10/23/2015.
 */
public class Played {
    String _result;
    int _team_id;
    int _match_id;
    Boolean _toss;
    Boolean _opted_bat;
    public Played(){}
    public Played(int team_id, int match_id, String result, Boolean toss, Boolean opted_bat){
        this._result = result;
        this._team_id = team_id;
        this._match_id = match_id;
        this._toss = toss;
        this._opted_bat = opted_bat;

    }


    public String get_result() {
        return _result;
    }

    public void set_result(String _result) {
        this._result = _result;
    }

    public int get_match_id() {
        return _match_id;
    }

    public void set_match_id(int _match_id) {
        this._match_id = _match_id;
    }

    public int get_team_id() {
        return _team_id;
    }

    public void set_team_id(int _team_id) {
        this._team_id = _team_id;
    }

    public Boolean get_toss() {
        return _toss;
    }

    public void set_toss(Boolean _toss) {
        this._toss = _toss;
    }

    public Boolean get_opted_bat() {
        return _opted_bat;
    }

    public void set_opted_bat(Boolean _opted_bat) {
        this._opted_bat = _opted_bat;
    }
}
