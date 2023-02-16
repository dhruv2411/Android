package com.example.cricketscore;


public class Tournament {
    int _id;
    String _name;
    String _venue;
    int _matches;
    String _start_date;
    String _end_date;
    public Tournament(){}
    public Tournament(int id, String name, String venue, String start_date, String end_date, int matches){
        this._end_date = end_date;
        this._start_date = start_date;
        this._id = id;
        this._matches = matches;
        this._name = name;
        this._venue = venue;

    }

    public int get_matches() {
        return _matches;
    }

    public void set_matches(int _matches) {
        this._matches = _matches;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_venue() {
        return _venue;
    }

    public void set_venue(String _venue) {
        this._venue = _venue;
    }

    public String get_start_date() {
        return _start_date;
    }

    public void set_start_date(String _start_date) {
        this._start_date = _start_date;
    }

    public String get_end_date() {
        return _end_date;
    }

    public void set_end_date(String _end_date) {
        this._end_date = _end_date;
    }
}
