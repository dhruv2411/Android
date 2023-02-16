package com.example.cricketscore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Cricket";

    // Contacts Table Columns names


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PLAYER = "CREATE TABLE player (name VARCHAR, date_of_birth DATE, no_of_matches INTEGER, " +
                "catches INTEGER, highest_score INTEGER, average REAL, fifty INTEGER, hundred INTEGER, " +
                "bowling_average REAL, runs INTEGER, wickets INTEGER, five_wickets INTEGER, ten_wickets INTEGER, id INTEGER PRIMARY KEY " +
                "AUTOINCREMENT DEFAULT 1);";
        String CREATE_MATCH = "CREATE TABLE match (id INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 1, date_time DATETIME, venue VARCHAR, stadium VARCHAR);";
        String CREATE_TEAM = "CREATE TABLE team (name VARCHAR, won INTEGER, lost INTEGER, matches INTEGER, no_result INTEGER, id INTEGER PRIMARY KEY  AUTOINCREMENT DEFAULT 1);";
        String CREATE_MATCH_PLAYED = "CREATE TABLE match_played (match_id INTEGER REFERENCES match (id), player_id INTEGER REFERENCES player (id)," +
                " runs_scored INTEGER, catches INTEGER, wickets INTEGER, PRIMARY KEY (player_id, match_id));";

        String CREATE_OVER = "CREATE TABLE over (id INTEGER , runs INTEGER, wickets INTEGER, bowler_id INTEGER REFERENCES player (id), " +
                "match_id INTEGER REFERENCES match (id), inning CHAR, PRIMARY KEY (id, match_id, inning));";
        String CREATE_TOURNAMENT = "CREATE TABLE tournament (id INTEGER PRIMARY KEY  AUTOINCREMENT DEFAULT 1, name VARCHAR, venue VARCHAR, matches INTEGER," +
                " start_date DATE, end_date DATE);";
        String CREATE_PLAYER_AT = "CREATE TABLE player_at (joining_date DATE, current_status BOOLEAN, p_id INTEGER REFERENCES player (id)," +
                " t_id INTEGER REFERENCES team (id), PRIMARY KEY (p_id, t_id));";
        String CREATE_PARTICIPATE = "CREATE TABLE participate (rank INTEGER, team_id INTEGER REFERENCES team (id), " +
                "tournament_id INTEGER REFERENCES tournament (id), PRIMARY KEY (team_id, tournament_id));";
        String CREATE_OVER_INFO = "CREATE TABLE over_info (batsman_id INTEGER REFERENCES player (id), over_id INTEGER," +
                " match_id INTEGER, run_By_Bat INTEGER, out INTEGER, inning CHAR, ball_num INTEGER, extra INTEGER, FOREIGN KEY " +
                "(over_id, match_id, inning) REFERENCES over (id, match_id, inning), PRIMARY KEY (batsman_id, over_id, match_id, inning, ball_num));";

        String CREATE_PLAYED = "CREATE TABLE played (result VARCHAR, team_id INTEGER REFERENCES team (id), " +
                "match_id INTEGER REFERENCES match (id), toss BOOLEAN, opted_bat BOOLEAN, PRIMARY KEY (team_id, match_id));";
        db.execSQL(CREATE_PLAYER);
        db.execSQL(CREATE_MATCH);
        db.execSQL(CREATE_TEAM);
        db.execSQL(CREATE_MATCH_PLAYED);
        db.execSQL(CREATE_OVER);
        db.execSQL(CREATE_TOURNAMENT);
        db.execSQL(CREATE_PLAYER_AT);
        db.execSQL(CREATE_PARTICIPATE);
        db.execSQL(CREATE_OVER_INFO);
        db.execSQL(CREATE_PLAYED);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        String DROP_COMMAND = "DROP TABLE IF EXISTS played, match, over_info, participate, player, tournament, team, player_at, over, match_played";
        db.execSQL(DROP_COMMAND);

        // Create tables again
        onCreate(db);
    }

    public void addMatch(com.shreyaslakhe.cricin.Match match) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("date_time", match.get_datetime());
        values.put("venue", match.get_venue());
        values.put("stadium", match.get_stadium());

        db.insert("match", null, values);
        db.close();
    }

    // Getting single contact
    public com.shreyaslakhe.cricin.Match getMatch(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String SELECT_MATCH = "SELECT * FROM " + "match " + "WHERE id = " + id;
        Cursor cursor = db.rawQuery(SELECT_MATCH, null);
        if (cursor != null)
            cursor.moveToFirst();

        com.shreyaslakhe.cricin.Match match = new com.shreyaslakhe.cricin.Match(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3));
        // return contact
        return match;
    }

    public void addMatch_played(Match_played match_played) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("catches", match_played.get_catches());
        values.put("wickets", match_played.get_wickets());
        values.put("match_id", match_played.get_match_id());
        values.put("player_id", match_played.get_player_id());
        values.put("run_scored", match_played.get_run_scored());

        db.insert("match_played", null, values);
        db.close();
    }

    public Match_played getMatch_played(int match_id, int player_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String SELECT_MATCH_PLAYED = "SELECT * FROM " + "match_played " + "WHERE match_id = " + match_id + "and player_id = " + player_id;
        Cursor cursor = db.rawQuery(SELECT_MATCH_PLAYED, null);
        if (cursor != null)
            cursor.moveToFirst();

        Match_played match_played = new Match_played(Integer.parseInt(cursor.getString(0)),
                Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)),
                Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(1)));
        // return contact
        return match_played;
    }

    public Over getOver(int match_id, int id, String inning) {
        SQLiteDatabase db = this.getReadableDatabase();
        String SELECT_OVER = "SELECT * FROM " + "over " + "WHERE match_id = " + match_id + "and id = " + id + "and inning = " + inning;
        Cursor cursor = db.rawQuery(SELECT_OVER, null);
        if (cursor != null)
            cursor.moveToFirst();

        Over over = new Over(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)),
                Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)),
                Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)));
        // return contact
        return over;
    }

    public void addOver(Over over) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("match_id", over.get_match_id());
        values.put("inning", over.get_inning());
        values.put("id", over.get_id());
        values.put("runs", over.get_runs());
        values.put("wickets", over.get_wickets());
        values.put("bowler_id", over.get_bowler_id());
        db.insert("over", null, values);
        db.close();
    }

    public void addOver_info(com.shreyaslakhe.cricin.Over_info over_info) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("match_id", over_info.get_match_id());
        values.put("inning", over_info.get_inning());
        values.put("over_id", over_info.get_over_id());
        values.put("runs", over_info.get_run_By_Bat());
        values.put("out", over_info.get_out());
        values.put("batsman_id", over_info.get_batsman_id());
        values.put("extra", over_info.get_extra());
        values.put("ball_num", over_info.get_ball_num());

        db.insert("over_info", null, values);
        db.close();
    }

    public com.shreyaslakhe.cricin.Over_info getOver_info(int match_id, int inning, int over_id, int ball_num) {
        SQLiteDatabase db = this.getReadableDatabase();

        String SELECT_OVER_INFO = "SELECT * FROM " + "over_info " + "WHERE match_id = " + match_id +
                " and inning = "+inning+" and over_id = " + over_id + " and ball_num = " + ball_num;
        Cursor cursor = db.rawQuery(SELECT_OVER_INFO, null);

        if(( cursor != null)&& cursor.moveToFirst()) {


            com.shreyaslakhe.cricin.Over_info over_info = new com.shreyaslakhe.cricin.Over_info(200, Integer.parseInt(cursor.getString(cursor.getColumnIndex("inning"))),
                    Integer.parseInt(cursor.getString(cursor.getColumnIndex("over_id"))), Integer.parseInt(cursor.getString(cursor.getColumnIndex("ball_num"))),
                    Integer.parseInt(cursor.getString(cursor.getColumnIndex("batsman_id"))), Integer.parseInt(cursor.getString(cursor.getColumnIndex("run_By_Bat"))),
                    Integer.parseInt(cursor.getString(cursor.getColumnIndex("out"))), Integer.parseInt(cursor.getString(cursor.getColumnIndex("extra"))));
            cursor.close();
            return over_info;
        }
        return null;

    }

    public Participate getParticipate(int tournament_id, int team_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String SELECT_PARTICIPATE = "SELECT * FROM " + "participate " + "WHERE match_id = " + tournament_id + "and team_id = " + team_id;
        Cursor cursor = db.rawQuery(SELECT_PARTICIPATE, null);
        if (cursor != null)
            cursor.moveToFirst();

        Participate participate = new Participate(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), Integer.parseInt(cursor.getString(2)));
        // return contact
        return participate;
    }

    public void addParticipate(Participate participate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("tournament_id", participate.getTournament_id());
        values.put("team_id", participate.getTeam_id());
        values.put("rank", participate.get_rank());

        db.insert("participate", null, values);

    }

    public void addPlayed(Played played) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("match_id", played.get_match_id());
        values.put("team_id", played.get_team_id());
        values.put("opted_bat", played.get_opted_bat());
        values.put("result", played.get_result());
        values.put("toss", played.get_toss());

        db.insert("played", null, values);

    }

    public void addPlayer(Player player) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", player.get_name());
        values.put("date_of_birth", player.get_date_of_birth());
        values.put("catches", player.get_catches());
        values.put("no_of_matches", player.get_no_of_matches());
        values.put("highest_score", player.get_highest_score());
        values.put("average", player.get_average());
        values.put("hundred", player.get_hundred());
        values.put("runs", player.get_runs());
        values.put("wickets", player.get_wickets());
        values.put("five_wickets", player.get_five_wickets());
        values.put("ten_wickets", player.get_ten_wickets());
        values.put("bowling_average", player.get_bowling_average());
        values.put("fifty", player.get_fifty());

        db.insert("player", null, values);

    }

    public Player getPlayer(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String SELECT_PLAYER = "SELECT * FROM " + "player";
        Cursor cursor = db.rawQuery(SELECT_PLAYER, null);
        if (cursor != null)
            cursor.moveToFirst();

        Player player = new Player();
        player.set_name(cursor.getString(0));
        // return contact
        return player;
    }

    public Played getPlayed(int match_id, int team_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String SELECT_PLAYED = "SELECT * FROM " + "played " + "WHERE match_id = " + match_id + "and team_id = " + team_id;
        Cursor cursor = db.rawQuery(SELECT_PLAYED, null);
        if (cursor != null)
            cursor.moveToFirst();

        Played played = new Played(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), cursor.getString(2),
                Boolean.parseBoolean(cursor.getString(3)), Boolean.parseBoolean(cursor.getString(4)));
        return played;
    }

    public void addTeam(Team team) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", team.get_name());
        values.put("no_result", team.get_no_result());
        values.put("matches", team.get_matches());
        values.put("won", team.get_won());
        values.put("lost", team.get_lost());

        db.insert("team", null, values);
    }

    public void addTournament(Tournament tournament) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put("name", tournament.get_name());
        values.put("venue", tournament.get_venue());
        values.put("matches", tournament.get_matches());
        values.put("start_date", tournament.get_start_date());
        values.put("end_date", tournament.get_end_date());

        db.insert("tournament", null, values);
    }

    public Team getTeam(int team_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String SELECT_TEAM = "SELECT * FROM " + "team " + "WHERE team_id = " + team_id;
        Cursor cursor = db.rawQuery(SELECT_TEAM, null);
        if (cursor != null)
            cursor.moveToFirst();

        Team team = new Team(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)),
                Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)));
        return team;
    }

    public void addtoParticipate(int rank, String tournament_name, String team_name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String t = "select * from tournament where name = '" + tournament_name + "'";
        String l = "select * from team where name = '" + team_name + "'";
        Cursor cursor = db.rawQuery(t, null);
        Cursor cursor1 = db.rawQuery(l, null);
        System.out.println(t + "\n" + l);

        if (cursor != null)
            cursor.moveToFirst();
        else
            System.out.println("Error");

        if (cursor1 != null)
            cursor1.moveToFirst();

//        System.out.println("working " + Integer.parseInt(cursor.getString(0)) + " " + Integer.parseInt(cursor1.getString(0)));
        if(cursor != null && cursor1 != null) {
            Participate participate = new Participate(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))), Integer.parseInt(cursor1.getString(cursor1.getColumnIndex("id"))), rank);
            addParticipate(participate);
            System.out.println("Inserted");
        }
    }

    public Tournament getTournament(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String SELECT_TEAM = "SELECT * FROM " + "participate " + "WHERE id = " + id;
        Cursor cursor = db.rawQuery(SELECT_TEAM, null);
        if (cursor != null)
            cursor.moveToFirst();

        Tournament tournament = new Tournament(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4), Integer.parseInt(cursor.getString(5)));
        return tournament;
    }

    public List<String> getAllTournament() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> list = new ArrayList<String>();
        String SELECT_TOURNAMENT = "SELECT * FROM tournament";
        Cursor cursor = db.rawQuery(SELECT_TOURNAMENT, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    list.add(cursor.getString(cursor.getColumnIndex("name")));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        db.close();
        return list;
    }

    public List<String> getTournamentTeams(String tname) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> list = new ArrayList<String>();
        String query = "select c.name as name from ((select * from tournament where name = '" + tname + "')a natural join (participate natural join team)b)c";
        String query2 = "select name from team where id in (select team_id from participate where tournament_id = (select id from tournament where name = '" +
                        tname + "'))";
        System.out.println(query2);
        Cursor cursor = db.rawQuery(query2, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    list.add(cursor.getString(cursor.getColumnIndex("name")));
                    System.out.println("love it");
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        db.close();
        return list;
    }

    public List<String> getAllTeams() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> list = new ArrayList<String>();
        String query = "select * from team";
        Cursor cursor = db.rawQuery(query, null);
        System.out.println("fuck");
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    list.add(cursor.getString(cursor.getColumnIndex("name")));
                    System.out.println(cursor.getColumnIndex("name"));
                    System.out.println("fuck2");
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        db.close();
        return list;
    }
    public void sumOver(int match_id, int inning, int over_id, int bowler_id){

        String q = "select sum(run_By_Bat)+sum(extra) from over_info where match_id = " + match_id +
                " and inning = " + inning +
                " and over_id = " + over_id +
                " group by match_id, inning, over_id";
        SQLiteDatabase db = this.getReadableDatabase();
        System.out.println(q);
        Cursor cursor = db.rawQuery(q, null);
        if((cursor != null)&& cursor.moveToFirst()) {

            System.out.println(cursor.getString(0));
            Over over = new Over(match_id, inning, over_id, Integer.parseInt(cursor.getString(0)), 0, bowler_id);
            this.addOver(over);
        }
    }

    public ArrayList<String> teamRankings(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> list = new ArrayList<String>();
        String SELECT_TOURNAMENT = "SELECT * FROM team order by won/matches desc";
        Cursor cursor = db.rawQuery(SELECT_TOURNAMENT, null);
        int i = 1;
        if(cursor != null) {
            if(cursor.moveToFirst()) {
                do {
                    list.add(i++ +". "+cursor.getString(cursor.getColumnIndex("name"))+" - "+cursor.getString(cursor.getColumnIndex("matches"))
                            +" - "+cursor.getString(cursor.getColumnIndex("won")));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        db.close();
        return list;
    }

    public void updateplayedmatch(int match_id, int player_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String q = "SELECT sum(wickets) FROM over where bowler_id = "+ player_id+" and match_id = "+ match_id+
                " group by match_id, bowler_id";
        Cursor cursor = db.rawQuery(q, null);
        int i = 1;
        int wicks = 0, runs = 0;
        if(cursor != null) {
            if(cursor.moveToFirst()) {
                wicks = Integer.parseInt(cursor.getString(0));
            }
            cursor.close();
        }
        q = "SELECT sum(run_By_Bat) FROM over_info where batsman_id = "+ player_id+" and match_id = "+ match_id+
                " group by match_id, batsman_id";
        cursor = db.rawQuery(q, null);

        if(cursor != null) {
            if(cursor.moveToFirst()) {
                runs = Integer.parseInt(cursor.getString(0));
            }
            cursor.close();
        }

        Match_played match_played = new Match_played();
        match_played.set_match_id(match_id);
        match_played.set_player_id(player_id);
        match_played.set_run_scored(runs);
        match_played.set_wickets(wicks);

        this.addMatch_played(match_played);
        db.close();
    }
    public ArrayList<String> getPlayerStat(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> list = new ArrayList<String>();
        String SELECT_TOURNAMENT = "SELECT * FROM player";
        Cursor cursor = db.rawQuery(SELECT_TOURNAMENT, null);
        int i = 1;
        if(cursor != null) {
            if(cursor.moveToFirst()) {
                do {
                    list.add(i++ +". "+cursor.getString(cursor.getColumnIndex("name"))+" - "+cursor.getString(cursor.getColumnIndex("no_of_matches"))
                            +" - "+cursor.getString(cursor.getColumnIndex("runs"))+" - "+cursor.getString(cursor.getColumnIndex("wickets")));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        db.close();
        return list;
    }
    public List<String> teamInfo(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> list = new ArrayList<String>();
        String SELECT_TOURNAMENT = "SELECT * FROM team ";
        Cursor cursor = db.rawQuery(SELECT_TOURNAMENT, null);
        int i = 1;
        if(cursor != null) {
            if(cursor.moveToFirst()) {
                do {
                    list.add(i++ +". "+cursor.getString(cursor.getColumnIndex("name"))+" - "+cursor.getString(cursor.getColumnIndex("won"))
                            +" - "+cursor.getString(cursor.getColumnIndex("lost")));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        db.close();
        return list;
    }

    public List<String> getTournamentInfo(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> list = new ArrayList<String>();
        String SELECT_TOURNAMENT = "SELECT * FROM tournament";
        Cursor cursor = db.rawQuery(SELECT_TOURNAMENT, null);
        int i = 1;
        if(cursor != null) {
            if(cursor.moveToFirst()) {
                do {
                    list.add(i++ +". "+cursor.getString(cursor.getColumnIndex("name"))+" - "+cursor.getString(cursor.getColumnIndex("venue"))
                            +" - "+cursor.getString(cursor.getColumnIndex("matches")));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        db.close();
        return list;
    }
    public List<String> getOver(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> list = new ArrayList<String>();
        String SELECT_TOURNAMENT = "SELECT * FROM over";
        Cursor cursor = db.rawQuery(SELECT_TOURNAMENT, null);
        int i = 1;
        if(cursor != null) {
            if(cursor.moveToFirst()) {
                do {
                    list.add(cursor.getString(cursor.getColumnIndex("id"))+" - "+cursor.getString(cursor.getColumnIndex("runs"))+" - "+cursor.getString(cursor.getColumnIndex("wickets"))
                    );
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        db.close();
        return list;
    }



}

