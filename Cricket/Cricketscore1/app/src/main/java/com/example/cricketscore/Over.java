package com.example.cricketscore;

/**
 * Created by vishal on 10/23/2015.
 */
public class Over {
    int _id;
    int _runs;
    int _wickets;
    int _bowler_id;
    int _match_id;
    int _inning;
    public Over(){}
    public Over(int match_id, int inning, int id,int runs,int wickets,int bowler_id){
        this._id = id;
        this._runs = runs;
        this._wickets = wickets;
        this._bowler_id = bowler_id;
        this._match_id = match_id;
        this._inning = inning;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_runs() {
        return _runs;
    }

    public void set_runs(int _runs) {
        this._runs = _runs;
    }

    public int get_wickets() {
        return _wickets;
    }

    public void set_wickets(int _wickets) {
        this._wickets = _wickets;
    }

    public int get_bowler_id() {
        return _bowler_id;
    }

    public void set_bowler_id(int _bowler_id) {
        this._bowler_id = _bowler_id;
    }

    public int get_match_id() {
        return _match_id;
    }

    public void set_match_id(int _match_id) {
        this._match_id = _match_id;
    }

    public int get_inning() {
        return _inning;
    }

    public void set_inning(int _inning) {
        this._inning = _inning;
    }

}
