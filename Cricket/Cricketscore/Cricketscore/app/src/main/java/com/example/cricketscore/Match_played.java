package com.example.cricketscore;

/**
 * Created by Aditya Sathe on 10/23/2015.
 */
public class Match_played {


    int _run_scored;
    int _catches;
    int _wickets;
    int _player_id;
    int _match_id;
    public Match_played(){}
    public Match_played( int match_id,  int player_id, int run_scored, int catches, int wickets){
            this._match_id = match_id;
            this._player_id = player_id;
            this._run_scored = run_scored;
            this._catches = catches;
            this._wickets = wickets;
    }
    public int get_run_scored() {
        return _run_scored;
    }

    public void set_run_scored(int _run_scored) {
        this._run_scored = _run_scored;
    }

    public int get_catches() {
        return _catches;
    }

    public void set_catches(int _catches) {
        this._catches = _catches;
    }

    public int get_wickets() {
        return _wickets;
    }

    public void set_wickets(int _wickets) {
        this._wickets = _wickets;
    }

    public int get_player_id() {
        return _player_id;
    }

    public void set_player_id(int _player_id) {
        this._player_id = _player_id;
    }

    public int get_match_id() {
        return _match_id;
    }

    public void set_match_id(int _match_id) {
        this._match_id = _match_id;
    }

}
