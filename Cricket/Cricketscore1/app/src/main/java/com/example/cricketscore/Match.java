package com.example.cricketscore;


public class Match {
    int _id;
    String _datetime;
    String _venue;
    String _stadium;
    public Match(){}
    public Match(String datetime, String venue, String stadium){
        this._datetime =datetime;
        this._stadium = stadium;
        this._venue = venue;
    }
    public Match(int id, String datetime, String venue, String stadium){
        this._datetime =datetime;
        this._stadium = stadium;
        this._venue = venue;
        this._id = id;
    }
    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_datetime() {
        return _datetime;
    }

    public void set_datetime(String _datetime) {
        this._datetime = _datetime;
    }

    public String get_venue() {
        return _venue;
    }

    public void set_venue(String _venue) {
        this._venue = _venue;
    }

    public String get_stadium() {
        return _stadium;
    }

    public void set_stadium(String _stadium) {
        this._stadium = _stadium;
    }


}
