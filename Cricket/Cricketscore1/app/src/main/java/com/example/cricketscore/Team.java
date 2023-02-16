package com.example.cricketscore;

/**
 * Created by vishal on 10/23/2015.
 */
public class Team {
    String _name;
    int _won;
    int _lost;
    int _matches;
    int _no_result;
    int _id;
    public Team(){}
    public Team(int id, String name, int won, int lost, int matches, int no_result){
        this._id = id;
        this._lost = lost;
        this._matches = matches;
        this._won = won;
        this._no_result = no_result;
        this._name = name;

    }
    public int get_matches() {
        return _matches;
    }

    public void set_matches(int _matches) {
        this._matches = _matches;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public int get_won() {
        return _won;
    }

    public void set_won(int _won) {
        this._won = _won;
    }

    public int get_lost() {
        return _lost;
    }

    public void set_lost(int _lost) {
        this._lost = _lost;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_no_result() {
        return _no_result;
    }

    public void set_no_result(int _no_result) {
        this._no_result = _no_result;
    }


}
