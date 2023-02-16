package com.shreyaslakhe.cricin;

/**
 * Created by vishal on 10/23/2015.
 */
public class Over_info {
    int _over_id;
    int _batsman_id;
    int _match_id;
    int _run_By_Bat;
    int _out;
    int _inning;
    int _ball_num;
    int _extra;
    public Over_info(){}
    public Over_info(int match_id, int inning, int over_id,int ball_num, int batsman_id,int run_By_Bat, int out, int extra){
        this._over_id = over_id;
        this._batsman_id = batsman_id;
        this._match_id = match_id;
        this._run_By_Bat = run_By_Bat;
        this._out = out;
        this._inning = inning;
        this._ball_num = ball_num;
        this._extra = extra;
    }
    public int get_inning() {
        return _inning;
    }

    public void set_inning(int _inning) {
        this._inning = _inning;
    }

    public int get_over_id() {
        return _over_id;
    }

    public void set_over_id(int _over_id) {
        this._over_id = _over_id;
    }

    public int get_batsman_id() {
        return _batsman_id;
    }

    public void set_batsman_id(int _batsman_id) {
        this._batsman_id = _batsman_id;
    }

    public int get_match_id() {
        return _match_id;
    }

    public void set_match_id(int _match_id) {
        this._match_id = _match_id;
    }

    public int get_run_By_Bat() {
        return _run_By_Bat;
    }

    public void set_run_By_Bat(int _run_By_Bat) {
        this._run_By_Bat = _run_By_Bat;
    }

    public int get_out() {
        return _out;
    }

    public void set_out(int _out) {
        this._out = _out;
    }

    public int get_ball_num() {
        return _ball_num;
    }

    public void set_ball_num(int _ball_num) {
        this._ball_num = _ball_num;
    }

    public int get_extra() {
        return _extra;
    }

    public void set_extra(int _extra) {
        this._extra = _extra;
    }


}
