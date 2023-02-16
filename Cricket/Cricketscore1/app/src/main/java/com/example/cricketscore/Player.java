package com.example.cricketscore;

/**
 * Created by vishal on 10/23/2015.
 */
public class Player {
    String _name;
    String _date_of_birth;
    int _no_of_matches;
    int _catches;
    int _highest_score;
    float _average;
    int _fifty;
    int _hundred;
    int _runs;
    int _wickets;
    int _five_wickets;
    int _ten_wickets;
    int _id;
    float _bowling_average;
    public Player(){}
    public Player(int id, String name, String date_of_birth, int catches, int no_of_matches, int highest_score, float average
    , int fifty, int hundred, int runs, int wickets, int five_wickets, int ten_wickets , float bowling_average ){
        this._id = id;
        this._name = name;
        this._date_of_birth = date_of_birth;
        this._catches = catches;
        this._no_of_matches = no_of_matches;
        this._highest_score = highest_score;
        this._average = average;
        this._fifty = fifty;
        this._hundred = hundred;
        this._runs = runs;
        this._wickets = wickets;
        this._five_wickets = five_wickets;
        this._ten_wickets = ten_wickets;
        this._bowling_average = bowling_average;


    }
    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_date_of_birth() {
        return _date_of_birth;
    }

    public void set_date_of_birth(String _date_of_birth) {
        this._date_of_birth = _date_of_birth;
    }

    public int get_no_of_matches() {
        return _no_of_matches;
    }

    public void set_no_of_matches(int _no_of_matches) {
        this._no_of_matches = _no_of_matches;
    }

    public int get_catches() {
        return _catches;
    }

    public void set_catches(int _catches) {
        this._catches = _catches;
    }

    public int get_highest_score() {
        return _highest_score;
    }

    public void set_highest_score(int _highest_score) {
        this._highest_score = _highest_score;
    }

    public int get_fifty() {
        return _fifty;
    }

    public void set_fifty(int _fifty) {
        this._fifty = _fifty;
    }

    public float get_average() {
        return _average;
    }

    public void set_average(float _average) {
        this._average = _average;
    }

    public int get_hundred() {
        return _hundred;
    }

    public void set_hundred(int _hundred) {
        this._hundred = _hundred;
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

    public int get_five_wickets() {
        return _five_wickets;
    }

    public void set_five_wickets(int _five_wickets) {
        this._five_wickets = _five_wickets;
    }

    public int get_ten_wickets() {
        return _ten_wickets;
    }

    public void set_ten_wickets(int _ten_wickets) {
        this._ten_wickets = _ten_wickets;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public float get_bowling_average() {
        return _bowling_average;
    }

    public void set_bowling_average(float _bowling_average) {
        this._bowling_average = _bowling_average;
    }

}