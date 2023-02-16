package apps.shehryar.com.cricketscroingappPro;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman;
import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman_Details;
import apps.shehryar.com.cricketscroingappPro.Bowler.Bowler;
import apps.shehryar.com.cricketscroingappPro.Bowler.BowlerDataUpdater;
import apps.shehryar.com.cricketscroingappPro.Bowler.Bowler_Details;
import apps.shehryar.com.cricketscroingappPro.Match.ManOfTheMatchModel;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Overs.Over;
import apps.shehryar.com.cricketscroingappPro.Partenership.Partenership;
import apps.shehryar.com.cricketscroingappPro.Player.Player;
import apps.shehryar.com.cricketscroingappPro.Team.HighestTotal;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import apps.shehryar.com.cricketscroingappPro.Team.Team_players;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.Formatter;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.ListHelper;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.SharedPrefsHelper;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.UtilityFunctions;
import com.itextpdf.text.pdf.PdfBoolean;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class DBHelper extends SQLiteOpenHelper {
    private static final String CREATE_TABLE_BATSMAN_SCORE = "CREATE TABLE batsmanscores(id INTEGER PRIMARY KEY,match_id INTEGER,team_id INTEGER,player_id INTEGER,batsman_score INTEGER,batsman_balls_faced INTEGER,batsman_0s INTEGER,batsman_1s INTEGER,batsman_2s INTEGER,batsman_3s INTEGER,batsman_4s INTEGER,batsman_6s INTEGER,out_status TEXT,batsman_wicket_type TEXT)";
    private static final String CREATE_TABLE_BOWELER_SCORE = "CREATE TABLE bowlerscore(id INTEGER PRIMARY KEY,match_id INTEGER,team_id INTEGER,player_id INTEGER,bowler_score INTEGER,bowler_overs INTEGER,bowler_wickets INTEGER,bowler_maidens INTEGER,bowler_balls INTEGER)";
    private static final String CREATE_TABLE_FALL_OF_WICKETS = "CREATE TABLE fallofwickets(id INTEGER PRIMARY KEY,match_id INTEGER,team_id INTEGER,wicket_score INTEGER,wicket_no INTEGER,wicket_over INTEGER,wicket_ball INTEGER,wicket_batsman INTEGER,wicket_bowler INTEGER)";
    private static final String CREATE_TABLE_MAN_OF_THE_MATCH = "CREATE TABLE man_of_the_match(id INTEGER PRIMARY KEY,match_id INTEGER,team_id INTEGER,batsman_id INTEGER,bowler_id INTEGER)";
    private static final String CREATE_TABLE_MATCH = "CREATE TABLE match(id INTEGER PRIMARY KEY,venue TEXT,team_yours_id INTEGER,team_opp_id INTEGER,team_yours_id_2 INTEGER,team_opp_id_2 INTEGER,is_test_match INTEGER,is_followed_on INTEGER,tournament TEXT,tournament_stage TEXT,toss_result TEXT,match_time TEXT,match_overs INTEGER,result TEXT,date DATETIME)";
    private static final String CREATE_TABLE_MATCH_SETTINGS = "CREATE TABLE match_setting(id INTEGER PRIMARY KEY,match_id INTEGER,match_per_over_balls INTEGER,match_per_match_wickets INTEGER,select_mom_manually INTEGER,no_run_for_no INTEGER,no_run_for_wide INTEGER,max_balls INTEGER)";
    private static final String CREATE_TABLE_OVERS = "CREATE TABLE overs(id INTEGER PRIMARY KEY,match_id INTEGER,team_id INTEGER,over_score INTEGER,over_wickets INTEGER,over_no INTEGER,over_bowler INTEGER,over_per_ball_score TEXT)";
    private static final String CREATE_TABLE_PARTNERSHIP = "CREATE TABLE partnerships(id INTEGER PRIMARY KEY,match_id INTEGER,team_id INTEGER,total_partnership INTEGER,total_partnership_balls INTEGER,part_bat1_name TEXT,part_bat1_score INTEGER,part_bat1_balls INTEGER,part_bat2_name TEXT,part_bat2_score INTEGER,part_bat2_balls INTEGER)";
    private static final String CREATE_TABLE_PLAYERS = "CREATE TABLE player(id INTEGER PRIMARY KEY,player_name TEXT,player_type TEXT,team_id INTEGER)";
    private static final String CREATE_TABLE_PLAYERS_PROFILE = "CREATE TABLE player_profiles(id INTEGER PRIMARY KEY,player_id INTEGER,is_vice_captain INTEGER)";
    private static final String CREATE_TABLE_TEAM = "CREATE TABLE team(id INTEGER PRIMARY KEY,team_name TEXT)";
    private static final String CREATE_TABLE_TEAM_PLAYERS = "CREATE TABLE teamplayers(id INTEGER PRIMARY KEY,team_name TEXT,team_side TEXT,player_1 TEXT,player_2 TEXT,player_3 TEXT,player_4 TEXT,player_5 TEXT,player_6 TEXT,player_7 TEXT,player_8 TEXT,player_9 TEXT,player_10 TEXT,player_11 TEXT,player_12 TEXT,player_13 TEXT,player_14 TEXT,player_15 TEXT)";
    private static final String CREATE_TABLE_TEAM_SCORE = "CREATE TABLE teamscores(id INTEGER PRIMARY KEY,match_id INTEGER,team_id INTEGER,team_total_score INTEGER,team_total_wickets INTEGER,team_extras INTEGER,team_wides INTEGER,team_nos INTEGER,team_overs_played INTEGER,team_over_balls INTEGER)";
    private static final String CREATE_TEMPORARY_TABLE_BATSMAN_SCORE = "CREATE TEMPORARY TABLE temporary_batsmanscores(id INTEGER PRIMARY KEY,match_id INTEGER,team_id INTEGER,player_id INTEGER,batsman_score INTEGER,batsman_balls_faced INTEGER,batsman_0s INTEGER,batsman_1s INTEGER,batsman_2s INTEGER,batsman_3s INTEGER,batsman_4s INTEGER,batsman_6s INTEGER,out_status TEXT,batsman_wicket_type TEXT)";
    private static final String CREATE_TEMPORARY_TABLE_BOWELER_SCORE = "CREATE TEMPORARY TABLE temporary_bowlerscore(id INTEGER PRIMARY KEY,match_id INTEGER,team_id INTEGER,player_id INTEGER,bowler_score INTEGER,bowler_overs INTEGER,bowler_wickets INTEGER,bowler_maidens INTEGER,bowler_balls INTEGER)";
    private static final String CREATE_TEMPORARY_TABLE_FALL_OF_WICKETS = "CREATE TEMPORARY TABLE temporary_fallofwickets(id INTEGER PRIMARY KEY,match_id INTEGER,team_id INTEGER,wicket_score INTEGER,wicket_no INTEGER,wicket_over INTEGER,wicket_ball INTEGER,wicket_batsman INTEGER,wicket_bowler INTEGER)";
    private static final String CREATE_TEMPORARY_TABLE_MATCH = "CREATE TEMPORARY TABLE temporary_match(id INTEGER PRIMARY KEY,venue TEXT,team_yours_id INTEGER,team_opp_id INTEGER,team_yours_id_2 INTEGER,team_opp_id_2 INTEGER,is_test_match INTEGER,is_followed_on INTEGER,tournament TEXT,tournament_stage TEXT,toss_result TEXT,match_time TEXT,match_overs INTEGER,result TEXT,date DATETIME)";
    private static final String CREATE_TEMPORARY_TABLE_MATCH_SETTINGS = "CREATE TEMPORARY TABLE temporary_table_match_settings(id INTEGER PRIMARY KEY,match_id INTEGER,match_per_over_balls INTEGER,match_per_match_wickets INTEGER,select_mom_manually INTEGER,no_run_for_no INTEGER,no_run_for_wide INTEGER,max_balls INTEGER)";
    private static final String CREATE_TEMPORARY_TABLE_OVERS = "CREATE TEMPORARY TABLE temporary_overs(id INTEGER PRIMARY KEY,match_id INTEGER,team_id INTEGER,over_score INTEGER,over_wickets INTEGER,over_no INTEGER,over_bowler INTEGER,over_per_ball_score TEXT)";
    private static final String CREATE_TEMPORARY_TABLE_PLAYERS = "CREATE TEMPORARY TABLE temporary_player(id INTEGER PRIMARY KEY,player_name TEXT,player_type TEXT,team_id INTEGER)";
    private static final String CREATE_TEMPORARY_TABLE_TEAM = "CREATE TEMPORARY TABLE temporary_team(id INTEGER PRIMARY KEY,team_name TEXT)";
    private static final String CREATE_TEMPORARY_TABLE_TEAM_PLAYERS = "CREATE TEMPORARY TABLE temporary_teamplayers(id INTEGER PRIMARY KEY,team_name TEXT,team_side TEXT,player_1 TEXT,player_2 TEXT,player_3 TEXT,player_4 TEXT,player_5 TEXT,player_6 TEXT,player_7 TEXT,player_8 TEXT,player_9 TEXT,player_10 TEXT,player_11 TEXT,player_12 TEXT,player_13 TEXT,player_14 TEXT,player_15 TEXT)";
    private static final String CREATE_TEMPORARY_TABLE_TEAM_SCORE = "CREATE TEMPORARY TABLE temporary_teamscores(id INTEGER PRIMARY KEY,match_id INTEGER,team_id INTEGER,team_total_score INTEGER,team_total_wickets INTEGER,team_extras INTEGER,team_wides INTEGER,team_nos INTEGER,team_overs_played INTEGER,team_over_balls INTEGER)";
    private static final String CREATE_TEMPORARY_UA_TABLE_PLAYERS = "CREATE TEMPORARY TABLE temporary_user_added_players(id INTEGER PRIMARY KEY,player_name TEXT,player_type TEXT,player_image TEXT,team_id INTEGER,is_batsman INTEGER,is_bowler INTEGER,is_alrounder INTEGER,is_wk_keeper INTEGER)";
    private static final String CREATE_TEMPORARY_UA_TABLE_TEAM = "CREATE TEMPORARY TABLE temporary_user_added_teams(id INTEGER PRIMARY KEY,team_side INTEGER,captain_id INTEGER,wicket_keeper_id INTEGER,team_name TEXT)";
    private static final String CREATE_UA_TABLE_PLAYERS = "CREATE TABLE user_added_players(id INTEGER PRIMARY KEY,player_name TEXT,player_type TEXT,player_image TEXT,team_id INTEGER,is_batsman INTEGER,is_bowler INTEGER,is_alrounder INTEGER,is_wk_keeper INTEGER)";
    private static final String CREATE_UA_TABLE_TEAM = "CREATE TABLE user_added_teams(id INTEGER PRIMARY KEY,team_side INTEGER,captain_id INTEGER,wicket_keeper_id INTEGER,team_name TEXT)";
    public static final String DATABASE_NAME = "cricketscores";
    public static final int DATABASE_VERSION = 5;
    private static final String KEY_BATSMAN_0S = "batsman_0s";
    private static final String KEY_BATSMAN_1S = "batsman_1s";
    private static final String KEY_BATSMAN_2S = "batsman_2s";
    private static final String KEY_BATSMAN_3S = "batsman_3s";
    private static final String KEY_BATSMAN_4S = "batsman_4s";
    private static final String KEY_BATSMAN_6S = "batsman_6s";
    private static final String KEY_BATSMAN_BALLS = "batsman_balls_faced";
    private static final String KEY_BATSMAN_ID = "batsman_id";
    private static final String KEY_BATSMAN_SCORE = "batsman_score";
    private static final String KEY_BATSMAN_WICKET_TYPE = "batsman_wicket_type";
    private static final String KEY_BOWELR_WICKETS = "bowler_wickets";
    private static final String KEY_BOWLER_BALLS = "bowler_balls";
    private static final String KEY_BOWLER_ID = "bowler_id";
    private static final String KEY_BOWLER_MAIDENS = "bowler_maidens";
    private static final String KEY_BOWLER_OVERS = "bowler_overs";
    private static final String KEY_BOWLER_SCORE = "bowler_score";
    private static final String KEY_CAPTAIN_ID = "captain_id";
    private static final String KEY_DATE = "date";
    private static final String KEY_ID = "id";
    private static final String KEY_IS_ALROUNDER = "is_alrounder";
    private static final String KEY_IS_BATSMAN = "is_batsman";
    private static final String KEY_IS_BOWLER = "is_bowler";
    private static final String KEY_IS_CAPTAIN = "is_captain";
    private static final String KEY_IS_FOLLOWED_ON = "is_followed_on";
    private static final String KEY_IS_KEEPER = "is_wk_keeper";
    private static final String KEY_IS_TEST_MATCH = "is_test_match";
    private static final String KEY_IS_VICE_CAPTAIN = "is_vice_captain";
    private static final String KEY_MATCH_ID = "match_id";
    private static final String KEY_MATCH_OVERS = "match_overs";
    private static final String KEY_MATCH_OVER_BALLS = "match_per_over_balls";
    private static final String KEY_MATCH_SIXES = "team_sixes";
    private static final String KEY_MATCH_TIME = "match_time";
    private static final String KEY_MATCH_WICKETS = "match_per_match_wickets";
    private static final String KEY_MAX_BALLS = "max_balls";
    private static final String KEY_NO_RUN_FOR_NO = "no_run_for_no";
    private static final String KEY_NO_RUN_FOR_WIDE = "no_run_for_wide";
    private static final String KEY_OUTSTATUS = "out_status";
    private static final String KEY_OVER_BOWLER = "over_bowler";
    private static final String KEY_OVER_NO = "over_no";
    private static final String KEY_OVER_PER_BALL_SCORE = "over_per_ball_score";
    private static final String KEY_OVER_SCORE = "over_score";
    private static final String KEY_OVER_WICKETS = "over_wickets";
    private static final String KEY_PART_BATSMAN1_BALLS = "part_bat1_balls";
    private static final String KEY_PART_BATSMAN1_NAME = "part_bat1_name";
    private static final String KEY_PART_BATSMAN1_SCORE = "part_bat1_score";
    private static final String KEY_PART_BATSMAN2_BALLS = "part_bat2_balls";
    private static final String KEY_PART_BATSMAN2_NAME = "part_bat2_name";
    private static final String KEY_PART_BATSMAN2_SCORE = "part_bat2_score";
    private static final String KEY_PLAYER_1 = "player_1";
    private static final String KEY_PLAYER_10 = "player_10";
    private static final String KEY_PLAYER_11 = "player_11";
    private static final String KEY_PLAYER_12 = "player_12";
    private static final String KEY_PLAYER_13 = "player_13";
    private static final String KEY_PLAYER_14 = "player_14";
    private static final String KEY_PLAYER_15 = "player_15";
    private static final String KEY_PLAYER_16 = "player_16";
    private static final String KEY_PLAYER_2 = "player_2";
    private static final String KEY_PLAYER_3 = "player_3";
    private static final String KEY_PLAYER_4 = "player_4";
    private static final String KEY_PLAYER_5 = "player_5";
    private static final String KEY_PLAYER_6 = "player_6";
    private static final String KEY_PLAYER_7 = "player_7";
    private static final String KEY_PLAYER_8 = "player_8";
    private static final String KEY_PLAYER_9 = "player_9";
    private static final String KEY_PLAYER_ID = "player_id";
    private static final String KEY_PLAYER_IMAGE = "player_image";
    private static final String KEY_PLAYER_NAME = "player_name";
    private static final String KEY_PLAYER_TYPE = "player_type";
    private static final String KEY_RESULT = "result";
    private static final String KEY_SELECT_MOM_MANUALLY = "select_mom_manually";
    private static final String KEY_TEAM_EXTRAS = "team_extras";
    private static final String KEY_TEAM_FOURS = "team_fours";
    private static final String KEY_TEAM_ID = "team_id";
    private static final String KEY_TEAM_NAME = "team_name";
    private static final String KEY_TEAM_NOS = "team_nos";
    private static final String KEY_TEAM_OPP_ID = "team_opp_id";
    private static final String KEY_TEAM_OPP_ID_2 = "team_opp_id_2";
    private static final String KEY_TEAM_OVERS_PLAYED = "team_overs_played";
    private static final String KEY_TEAM_OVER_BALLS = "team_over_balls";
    private static final String KEY_TEAM_SCORE = "team_total_score";
    private static final String KEY_TEAM_SIDE = "team_side";
    private static final String KEY_TEAM_WICKETS = "team_total_wickets";
    private static final String KEY_TEAM_WIDES = "team_wides";
    private static final String KEY_TEAM_YOURS_ID = "team_yours_id";
    private static final String KEY_TEAM_YOURS_ID_2 = "team_yours_id_2";
    private static final String KEY_TOSS_RESULT = "toss_result";
    private static final String KEY_TOTAL_PART = "total_partnership";
    private static final String KEY_TOTAL_PART_BALLS = "total_partnership_balls";
    private static final String KEY_TOURNAMENT_NAME = "tournament";
    private static final String KEY_TOURNAMENT_STAGE = "tournament_stage";
    private static final String KEY_UA_PLAYER_IMAGE = "user_added_player_image";
    private static final String KEY_UA_PLAYER_NAME = "user_added_player_name";
    private static final String KEY_UA_PLAYER_TYPE = "user_added_player_type";
    private static final String KEY_UA_TEAM_NAME = "user_added_team_name";
    private static final String KEY_VENUE = "venue";
    private static final String KEY_WICKET_BALL = "wicket_ball";
    private static final String KEY_WICKET_BATSMAN = "wicket_batsman";
    private static final String KEY_WICKET_BOWLER = "wicket_bowler";
    private static final String KEY_WICKET_NO = "wicket_no";
    private static final String KEY_WICKET_OVER = "wicket_over";
    private static final String KEY_WICKET_SCORE = "wicket_score";
    private static final String KEY_WK_ID = "wicket_keeper_id";
    private static final String LOG = "DatabaseHelper";
    public static final String TABLE_BATSMAN_SCORE = "batsmanscores";
    public static final String TABLE_BOWLER_SCORE = "bowlerscore";
    public static final String TABLE_FALL_OF_WICKETS = "fallofwickets";
    public static final String TABLE_MAN_OF_THE_MATCH = "man_of_the_match";
    public static final String TABLE_MATCH = "match";
    public static final String TABLE_MATCH_SETTING = "match_setting";
    public static final String TABLE_OVERS = "overs";
    public static final String TABLE_PARTNERSHIPS = "partnerships";
    public static final String TABLE_PLAYERS = "player";
    public static final String TABLE_PLAYERS_PROFILES = "player_profiles";
    public static final String TABLE_TEAM = "team";
    public static final String TABLE_TEAM_PLAYERS = "teamplayers";
    public static final String TABLE_TEAM_SCORE = "teamscores";
    public static final String TABLE_USER_ADDED_PLAYERS = "user_added_players";
    public static final String TABLE_USER_ADDED_TEAMS = "user_added_teams";
    public static final String TEMPORARY_TABLE_BATSMAN_SCORE = "temporary_batsmanscores";
    public static final String TEMPORARY_TABLE_BOWLER_SCORE = "temporary_bowlerscore";
    public static final String TEMPORARY_TABLE_FALL_OF_WICKETS = "temporary_fallofwickets";
    public static final String TEMPORARY_TABLE_MATCH = "temporary_match";
    public static final String TEMPORARY_TABLE_MATCH_SETTINGS = "temporary_table_match_settings";
    public static final String TEMPORARY_TABLE_OVERS = "temporary_overs";
    public static final String TEMPORARY_TABLE_PLAYERS = "temporary_player";
    public static final String TEMPORARY_TABLE_TEAM = "temporary_team";
    public static final String TEMPORARY_TABLE_TEAM_PLAYERS = "temporary_teamplayers";
    public static final String TEMPORARY_TABLE_TEAM_SCORE = "temporary_teamscores";
    public static final String TEMPORARY_TABLE_USER_ADDED_PLAYERS = "temporary_user_added_players";
    public static final String TEMPORARY_TABLE_USER_ADDED_TEAMS = "temporary_user_added_teams";
    public static boolean db_Upgraded = false;
    Context context;
    private SQLiteDatabase currentDBInstance;

    public DBHelper(Context context2) {
        super(context2, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 5);
        this.context = context2;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(CREATE_TABLE_MATCH);
        sQLiteDatabase.execSQL(CREATE_TABLE_PLAYERS);
        sQLiteDatabase.execSQL(CREATE_TABLE_TEAM);
        sQLiteDatabase.execSQL(CREATE_TABLE_TEAM_SCORE);
        sQLiteDatabase.execSQL(CREATE_TABLE_BATSMAN_SCORE);
        sQLiteDatabase.execSQL(CREATE_TABLE_BOWELER_SCORE);
        sQLiteDatabase.execSQL(CREATE_TABLE_FALL_OF_WICKETS);
        sQLiteDatabase.execSQL(CREATE_TABLE_TEAM_PLAYERS);
        sQLiteDatabase.execSQL(CREATE_UA_TABLE_PLAYERS);
        sQLiteDatabase.execSQL(CREATE_UA_TABLE_TEAM);
        sQLiteDatabase.execSQL(CREATE_TABLE_OVERS);
        sQLiteDatabase.execSQL(CREATE_TABLE_MATCH_SETTINGS);
        sQLiteDatabase.execSQL(CREATE_TABLE_PARTNERSHIP);
        sQLiteDatabase.execSQL(CREATE_TABLE_MAN_OF_THE_MATCH);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        this.currentDBInstance = sQLiteDatabase;
        try {
            createCopyAndDropTable(sQLiteDatabase, TABLE_PLAYERS, TEMPORARY_TABLE_PLAYERS, CREATE_TABLE_PLAYERS, CREATE_TEMPORARY_TABLE_PLAYERS);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        try {
            createCopyAndDropTable(sQLiteDatabase, TABLE_TEAM, TEMPORARY_TABLE_TEAM, CREATE_TABLE_TEAM, CREATE_TEMPORARY_TABLE_TEAM);
        } catch (SQLiteException e2) {
            e2.printStackTrace();
        }
        try {
            createCopyAndDropTable(sQLiteDatabase, TABLE_TEAM_SCORE, TEMPORARY_TABLE_TEAM_SCORE, CREATE_TABLE_TEAM_SCORE, CREATE_TEMPORARY_TABLE_TEAM_SCORE);
        } catch (SQLiteException e3) {
            e3.printStackTrace();
        }
        try {
            createCopyAndDropTable(sQLiteDatabase, TABLE_BATSMAN_SCORE, TEMPORARY_TABLE_BATSMAN_SCORE, CREATE_TABLE_BATSMAN_SCORE, CREATE_TEMPORARY_TABLE_BATSMAN_SCORE);
        } catch (SQLiteException e4) {
            e4.printStackTrace();
        }
        try {
            createCopyAndDropTable(sQLiteDatabase, TABLE_BOWLER_SCORE, TEMPORARY_TABLE_BOWLER_SCORE, CREATE_TABLE_BOWELER_SCORE, CREATE_TEMPORARY_TABLE_BOWELER_SCORE);
        } catch (SQLiteException e5) {
            e5.printStackTrace();
        }
        try {
            createCopyAndDropTable(sQLiteDatabase, TABLE_FALL_OF_WICKETS, TEMPORARY_TABLE_FALL_OF_WICKETS, CREATE_TABLE_FALL_OF_WICKETS, CREATE_TEMPORARY_TABLE_FALL_OF_WICKETS);
        } catch (SQLiteException e6) {
            e6.printStackTrace();
        }
        try {
            createCopyAndDropTable(sQLiteDatabase, TABLE_TEAM_PLAYERS, TEMPORARY_TABLE_TEAM_PLAYERS, CREATE_TABLE_TEAM_PLAYERS, CREATE_TEMPORARY_TABLE_TEAM_PLAYERS);
        } catch (SQLiteException e7) {
            e7.printStackTrace();
        }
        if (i == 3 && i2 == 5) {
            try {
                createCopyAndDropTable(sQLiteDatabase, TABLE_OVERS, TEMPORARY_TABLE_OVERS, CREATE_TABLE_OVERS, CREATE_TEMPORARY_TABLE_OVERS);
            } catch (SQLiteException e8) {
                e8.printStackTrace();
            }
            try {
                createCopyAndDropTable(sQLiteDatabase, TABLE_MATCH_SETTING, TEMPORARY_TABLE_MATCH_SETTINGS, CREATE_TABLE_MATCH_SETTINGS, CREATE_TEMPORARY_TABLE_MATCH_SETTINGS);
            } catch (SQLiteException e9) {
                e9.printStackTrace();
            }
        }
        if ((i == 2 || i == 3) && i2 == 5) {
            try {
                createCopyAndDropTable(sQLiteDatabase, TABLE_MATCH, TEMPORARY_TABLE_MATCH, CREATE_TABLE_MATCH, CREATE_TEMPORARY_TABLE_MATCH);
            } catch (SQLiteException e10) {
                e10.printStackTrace();
            }
        }
        if (i == 1 && i2 == 3) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS user_added_players");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS user_added_teams");
            sQLiteDatabase.execSQL(CREATE_UA_TABLE_TEAM);
            sQLiteDatabase.execSQL(CREATE_UA_TABLE_PLAYERS);
        } else if ((i == 3 || i == 2) && i2 == 5) {
            try {
                sQLiteDatabase.execSQL(CREATE_TEMPORARY_UA_TABLE_TEAM);
                sQLiteDatabase.execSQL("INSERT INTO temporary_user_added_teams SELECT id, team_side, -1, -1, team_name FROM user_added_teams");
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS user_added_teams");
                sQLiteDatabase.execSQL(CREATE_UA_TABLE_TEAM);
                sQLiteDatabase.execSQL("INSERT INTO user_added_teams SELECT * FROM temporary_user_added_teams");
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS temporary_user_added_teams");
                sQLiteDatabase.execSQL(CREATE_TEMPORARY_UA_TABLE_PLAYERS);
                sQLiteDatabase.execSQL("INSERT INTO temporary_user_added_players SELECT id, player_name, player_type, player_image, team_id, 0, 0, 0, 0 FROM user_added_players");
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS user_added_players");
                sQLiteDatabase.execSQL(CREATE_UA_TABLE_PLAYERS);
                sQLiteDatabase.execSQL("INSERT INTO user_added_players SELECT * FROM temporary_user_added_players");
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS temporary_user_added_players");
            } catch (SQLException e11) {
                e11.printStackTrace();
                e11.toString();
            }
        } else if (i == 2 && i2 == 3) {
            try {
                createCopyAndDropTable(sQLiteDatabase, TABLE_USER_ADDED_TEAMS, TEMPORARY_TABLE_USER_ADDED_TEAMS, CREATE_UA_TABLE_TEAM, CREATE_TEMPORARY_UA_TABLE_TEAM);
            } catch (SQLiteException e12) {
                e12.printStackTrace();
            }
            try {
                createCopyAndDropTable(sQLiteDatabase, TABLE_USER_ADDED_PLAYERS, TEMPORARY_TABLE_USER_ADDED_PLAYERS, CREATE_UA_TABLE_PLAYERS, CREATE_TEMPORARY_UA_TABLE_PLAYERS);
            } catch (SQLiteException e13) {
                e13.printStackTrace();
            }
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS match_setting");
            sQLiteDatabase.execSQL(CREATE_TABLE_MATCH_SETTINGS);
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS overs");
            sQLiteDatabase.execSQL(CREATE_TABLE_OVERS);
            sQLiteDatabase.execSQL(CREATE_TEMPORARY_TABLE_MATCH);
            sQLiteDatabase.execSQL("INSERT INTO temporary_match SELECT id, venue, team_yours_id, team_opp_id, -1, -1, 0, 0, tournament, null, null, match_overs, result, date FROM match");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS match");
            sQLiteDatabase.execSQL(CREATE_TABLE_MATCH);
            sQLiteDatabase.execSQL("INSERT INTO match SELECT * FROM temporary_match");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS temporary_match");
        }
        if (i2 == 5) {
            try {
                sQLiteDatabase.execSQL(CREATE_TEMPORARY_TABLE_MATCH);
            } catch (SQLException e14) {
                e14.printStackTrace();
            }
            sQLiteDatabase.execSQL("INSERT INTO temporary_match SELECT id, venue, team_yours_id, team_opp_id, team_yours_id_2, team_opp_id_2, is_test_match, is_followed_on, tournament, null, toss_result, match_time, match_overs, result, date FROM match");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS match");
            sQLiteDatabase.execSQL(CREATE_TABLE_MATCH);
            sQLiteDatabase.execSQL("INSERT INTO match SELECT * FROM temporary_match");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS temporary_match");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS man_of_the_match");
            sQLiteDatabase.execSQL(CREATE_TABLE_MAN_OF_THE_MATCH);
        }
        db_Upgraded = true;
    }

    public ArrayList<String> getTournamentAllStages(String str) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor rawQuery = readableDatabase.rawQuery("Select * from match where tournament='" + Formatter.replaceSingleQuoteWithTwoSingleQuotes(str) + "'", (String[]) null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            try {
                String string = rawQuery.getString(rawQuery.getColumnIndex(KEY_TOURNAMENT_STAGE));
                if (!arrayList.contains(string) && !string.equalsIgnoreCase("Single Match")) {
                    arrayList.add(string);
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
            rawQuery.moveToNext();
        }
        rawQuery.close();
        return arrayList;
    }

    public ArrayList<Cursor> getData(String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        String[] strArr = {SettingsJsonConstants.PROMPT_MESSAGE_KEY};
        ArrayList<Cursor> arrayList = new ArrayList<>(2);
        MatrixCursor matrixCursor = new MatrixCursor(strArr);
        arrayList.add((Object) null);
        arrayList.add((Object) null);
        try {
            Cursor rawQuery = writableDatabase.rawQuery(str, (String[]) null);
            matrixCursor.addRow(new Object[]{"Success"});
            arrayList.set(1, matrixCursor);
            if (rawQuery == null || rawQuery.getCount() <= 0) {
                return arrayList;
            }
            arrayList.set(0, rawQuery);
            rawQuery.moveToFirst();
            return arrayList;
        } catch (SQLException e) {
            Log.d("printing exception", e.getMessage());
            matrixCursor.addRow(new Object[]{"" + e.getMessage()});
            arrayList.set(1, matrixCursor);
            return arrayList;
        } catch (Exception e2) {
            Log.d("printing exception", e2.getMessage());
            matrixCursor.addRow(new Object[]{"" + e2.getMessage()});
            arrayList.set(1, matrixCursor);
            return arrayList;
        }
    }

    private void createCopyAndDropTable(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, String str4) throws SQLiteException {
        sQLiteDatabase.execSQL(str4);
        sQLiteDatabase.execSQL("INSERT INTO " + str2 + " SELECT * FROM " + str);
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(str);
        sQLiteDatabase.execSQL(sb.toString());
        sQLiteDatabase.execSQL(str3);
        sQLiteDatabase.execSQL("INSERT INTO " + str + " SELECT * FROM " + str2);
        StringBuilder sb2 = new StringBuilder();
        sb2.append("DROP TABLE ");
        sb2.append(str2);
        sQLiteDatabase.execSQL(sb2.toString());
    }

    public void insertManofTheMatch(long j, String str, String str2) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_RESULT, str2 + ":" + str);
        StringBuilder sb = new StringBuilder();
        sb.append("id=");
        sb.append(j);
        writableDatabase.update(TABLE_MATCH, contentValues, sb.toString(), (String[]) null);
    }

    /* access modifiers changed from: package-private */
    public void updateMOMdueToMistake() {
        int i;
        try {
            ArrayList arrayList = new ArrayList();
            Cursor rawQuery = getReadableDatabase().rawQuery("Select * from match", (String[]) null);
            rawQuery.moveToFirst();
            while (true) {
                if (rawQuery.isAfterLast()) {
                    break;
                }
                Match match = new Match();
                match.setVenue(rawQuery.getString(rawQuery.getColumnIndex(KEY_VENUE)));
                match.setTeam_Yours_id(rawQuery.getLong(rawQuery.getColumnIndex(KEY_TEAM_YOURS_ID)));
                String[] split = rawQuery.getString(rawQuery.getColumnIndex(KEY_TOURNAMENT_NAME)).split(":");
                match.setTournament(split[0]);
                match.setTeam_opp_id(rawQuery.getLong(rawQuery.getColumnIndex(KEY_TEAM_OPP_ID)));
                if (split.length > 1) {
                    match.setResult(rawQuery.getString(rawQuery.getColumnIndex(KEY_RESULT)) + ":" + split[1]);
                } else {
                    match.setResult(rawQuery.getString(rawQuery.getColumnIndex(KEY_RESULT)));
                }
                match.setDate(rawQuery.getString(rawQuery.getColumnIndex("date")));
                match.setMatchID(rawQuery.getLong(rawQuery.getColumnIndex("id")));
                match.setOvers(rawQuery.getInt(rawQuery.getColumnIndex(KEY_MATCH_OVERS)));
                arrayList.add(match);
                rawQuery.moveToNext();
            }
            rawQuery.close();
            SQLiteDatabase writableDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            for (i = 0; i < arrayList.size(); i++) {
                contentValues.put(KEY_TOURNAMENT_NAME, ((Match) arrayList.get(i)).getTournament());
                contentValues.put(KEY_RESULT, ((Match) arrayList.get(i)).getResult());
                writableDatabase.update(TABLE_MATCH, contentValues, "id=" + ((Match) arrayList.get(i)).getMatchID(), (String[]) null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long insertManOfTheMatch(ManOfTheMatchModel manOfTheMatchModel) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_MATCH_ID, Long.valueOf(manOfTheMatchModel.getMatchId()));
        contentValues.put(KEY_TEAM_ID, Long.valueOf(manOfTheMatchModel.getTeamId()));
        contentValues.put(KEY_BATSMAN_ID, Long.valueOf(manOfTheMatchModel.getBatsmanId()));
        contentValues.put(KEY_BOWLER_ID, Long.valueOf(manOfTheMatchModel.getBowlerId()));
        return writableDatabase.insert(TABLE_MAN_OF_THE_MATCH, (String) null, contentValues);
    }

    public long updateManOfTheMatch(ManOfTheMatchModel manOfTheMatchModel) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_MATCH_ID, Long.valueOf(manOfTheMatchModel.getMatchId()));
        contentValues.put(KEY_TEAM_ID, Long.valueOf(manOfTheMatchModel.getTeamId()));
        contentValues.put(KEY_BATSMAN_ID, Long.valueOf(manOfTheMatchModel.getBatsmanId()));
        contentValues.put(KEY_BOWLER_ID, Long.valueOf(manOfTheMatchModel.getBowlerId()));
        return (long) writableDatabase.update(TABLE_MAN_OF_THE_MATCH, contentValues, "id=" + manOfTheMatchModel.getId(), (String[]) null);
    }

    public ManOfTheMatchModel getManOfTheMatch(long j, long j2) {
        ManOfTheMatchModel manOfTheMatchModel = null;
        Cursor rawQuery = getReadableDatabase().rawQuery("Select * from man_of_the_match where team_id=" + j2 + " and " + KEY_MATCH_ID + "=" + j, (String[]) null);
        rawQuery.moveToFirst();
        if (!rawQuery.isAfterLast()) {
            manOfTheMatchModel = new ManOfTheMatchModel();
            manOfTheMatchModel.setMatchId(j);
            manOfTheMatchModel.setTeamId(j2);
            manOfTheMatchModel.setBatsmanId(rawQuery.getLong(rawQuery.getColumnIndex(KEY_BATSMAN_ID)));
            manOfTheMatchModel.setBowlerId(rawQuery.getLong(rawQuery.getColumnIndex(KEY_BOWLER_ID)));
        }
        rawQuery.close();
        if (manOfTheMatchModel != null) {
            long j3 = j2;
            long j4 = j;
            manOfTheMatchModel.setBatsman(getBatsman(j3, j4, manOfTheMatchModel.getBatsmanId()));
            manOfTheMatchModel.setBowler(getBowler(j3, j4, manOfTheMatchModel.getBowlerId()));
        }
        return manOfTheMatchModel;
    }

    public long insertTeamPlayers(String str, ArrayList<String> arrayList, String str2) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TEAM_NAME, str);
        contentValues.put(KEY_TEAM_SIDE, str2);
        contentValues.put(KEY_PLAYER_1, arrayList.get(0).toString());
        contentValues.put(KEY_PLAYER_2, arrayList.get(1).toString());
        contentValues.put(KEY_PLAYER_3, arrayList.get(2).toString());
        contentValues.put(KEY_PLAYER_4, arrayList.get(3).toString());
        contentValues.put(KEY_PLAYER_5, arrayList.get(4).toString());
        contentValues.put(KEY_PLAYER_6, arrayList.get(5).toString());
        contentValues.put(KEY_PLAYER_7, arrayList.get(6).toString());
        contentValues.put(KEY_PLAYER_8, arrayList.get(7).toString());
        contentValues.put(KEY_PLAYER_9, arrayList.get(8).toString());
        contentValues.put(KEY_PLAYER_10, arrayList.get(9).toString());
        contentValues.put(KEY_PLAYER_11, arrayList.get(10).toString());
        contentValues.put(KEY_PLAYER_12, arrayList.get(11).toString());
        contentValues.put(KEY_PLAYER_13, arrayList.get(12).toString());
        contentValues.put(KEY_PLAYER_14, arrayList.get(13).toString());
        contentValues.put(KEY_PLAYER_15, arrayList.get(14).toString());
        return writableDatabase.insert(TABLE_TEAM_PLAYERS, (String) null, contentValues);
    }

    /* access modifiers changed from: package-private */
    public ArrayList<String> getOtherTeamNames() {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        String string = this.context.getSharedPreferences(TABLE_TEAM, 0).getString("teamname", "");
        Cursor rawQuery = readableDatabase.rawQuery("Select * from team", (String[]) null);
        ArrayList<String> arrayList = new ArrayList<>();
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            String string2 = rawQuery.getString(rawQuery.getColumnIndex(KEY_TEAM_NAME));
            if (!arrayList.contains(string2) && !arrayList.contains(string)) {
                arrayList.add(string2);
            }
            rawQuery.moveToNext();
        }
        rawQuery.close();
        return arrayList;
    }

    public ArrayList<Team> get_tournament_Teams_Stats(String str, String str2) throws Exception {
        String str3;
        Team team;
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        if (str2 == null || !str2.equals("All Stages")) {
            str3 = "SELECT * FROM match WHERE tournament = '" + Formatter.replaceSingleQuoteWithTwoSingleQuotes(str) + "' and " + KEY_TOURNAMENT_STAGE + "='" + Formatter.replaceSingleQuoteWithTwoSingleQuotes(str2) + "'";
        } else {
            str3 = "SELECT * FROM match WHERE tournament = '" + Formatter.replaceSingleQuoteWithTwoSingleQuotes(str) + "'";
        }
        Cursor rawQuery = readableDatabase.rawQuery(str3, (String[]) null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            Match match = new Match();
            match.setMatchID(rawQuery.getLong(rawQuery.getColumnIndex("id")));
            match.setTeam_Yours_id(rawQuery.getLong(rawQuery.getColumnIndex(KEY_TEAM_YOURS_ID)));
            match.setTeam_opp_id(rawQuery.getLong(rawQuery.getColumnIndex(KEY_TEAM_OPP_ID)));
            match.setYourteam(getTeamName(match.getTeam_Yours_id()));
            match.setOpponent(getTeamName(match.getTeam_opp_id()));
            match.setResult(rawQuery.getString(rawQuery.getColumnIndex(KEY_RESULT)));
            match.setOvers(rawQuery.getInt(rawQuery.getColumnIndex(KEY_MATCH_OVERS)));
            arrayList.add(match);
            rawQuery.moveToNext();
        }
        rawQuery.close();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Match match2 = (Match) it.next();
            Team team2 = new Team();
            team2.setTeamID(match2.getTeam_Yours_id());
            team2.setName(match2.getYourteam());
            Team team1Stats = getTeam1Stats(match2.getMatchID(), team2.getTeamID());
            team1Stats.setMatchTotalOvers(match2.getOvers());
            match2.setTeam1(team1Stats);
            Team team3 = new Team();
            team3.setTeamID(match2.getTeam_opp_id());
            team3.setName(match2.getOpponent());
            Team team1Stats2 = getTeam1Stats(match2.getMatchID(), team3.getTeamID());
            team1Stats2.setMatchTotalOvers(match2.getOvers());
            match2.setTeam2(team1Stats2);
            match2.getTeam1().setOpponentTeam(new Team(match2.getTeam2()));
            match2.getTeam2().setOpponentTeam(new Team(match2.getTeam1()));
            match2.getTeam1().setMatch(match2);
            match2.getTeam2().setMatch(match2);
            if (!match2.getResult().equals("No result")) {
                if (match2.getTeam1().getScore() > match2.getTeam2().getScore()) {
                    match2.getTeam1().setMatches_won(1);
                    match2.getTeam1().setPoints(2);
                    match2.getTeam2().setMatches_lost(1);
                    match2.getTeam2().setPoints(0);
                } else if (match2.getTeam1().getScore() < match2.getTeam2().getScore()) {
                    match2.getTeam2().setMatches_won(1);
                    match2.getTeam2().setPoints(2);
                    match2.getTeam1().setMatches_lost(1);
                    match2.getTeam1().setPoints(0);
                } else if (match2.getTeam1().getScore() == match2.getTeam2().getScore()) {
                    match2.getTeam1().setMatches_tied(1);
                    match2.getTeam2().setMatches_tied(1);
                    match2.getTeam1().setPoints(1);
                    match2.getTeam2().setPoints(1);
                }
            }
        }
        ArrayList<String> allTeamsNames = UtilityFunctions.getAllTeamsNames(arrayList);
        ArrayList<Team> arrayList2 = new ArrayList<>();
        for (int i = 0; i < allTeamsNames.size(); i++) {
            ArrayList arrayList3 = new ArrayList();
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                Match match3 = (Match) it2.next();
                if (match3.getYourteam().equals(allTeamsNames.get(i))) {
                    arrayList3.add(match3.getTeam1());
                } else if (match3.getOpponent().equals(allTeamsNames.get(i))) {
                    arrayList3.add(match3.getTeam2());
                }
            }
            if (arrayList3.size() > 0) {
                try {
                    team = new Team((Team) arrayList3.get(0));
                } catch (Exception e) {
                    e.printStackTrace();
                    team = null;
                }
                team.setMatches_Played(1);
                if (arrayList3.size() > 1) {
                    for (int i2 = 1; i2 < arrayList3.size(); i2++) {
                        team.combineTeamStats((Team) arrayList3.get(i2));
                        team.getOpponentTeam().combineTeamStats(((Team) arrayList3.get(i2)).getOpponentTeam());
                        team.setMatches_Played(1);
                    }
                }
                arrayList2.add(team);
            }
        }
        ListHelper.sortTeamsOnPoints(arrayList2);
        return arrayList2;
    }

    public int getTotalSixesInTournament(String str) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = readableDatabase.rawQuery("SELECT * FROM match WHERE tournament = '" + Formatter.replaceSingleQuoteWithTwoSingleQuotes(str) + "'", (String[]) null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            arrayList.add(Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndex("id"))));
            rawQuery.moveToNext();
        }
        rawQuery.close();
        int i = 0;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            Cursor rawQuery2 = readableDatabase.rawQuery("Select * from batsmanscores where match_id=" + arrayList.get(i2) + "", (String[]) null);
            rawQuery2.moveToFirst();
            while (!rawQuery2.isAfterLast()) {
                i += rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_BATSMAN_6S));
                rawQuery2.moveToNext();
            }
            rawQuery2.close();
        }
        return i;
    }

    public int getTotalFoursInTournament(String str) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = readableDatabase.rawQuery("SELECT * FROM match WHERE tournament = '" + Formatter.replaceSingleQuoteWithTwoSingleQuotes(str) + "'", (String[]) null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            arrayList.add(Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndex("id"))));
            rawQuery.moveToNext();
        }
        rawQuery.close();
        int i = 0;
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            Cursor rawQuery2 = readableDatabase.rawQuery("Select * from batsmanscores where match_id=" + arrayList.get(i2) + "", (String[]) null);
            rawQuery2.moveToFirst();
            while (!rawQuery2.isAfterLast()) {
                i += rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_BATSMAN_4S));
                rawQuery2.moveToNext();
            }
            rawQuery2.close();
        }
        return i;
    }

    public ArrayList<Team> get_All_Teams_Stats(int i, String str) throws Exception {
        String str2;
        Team team;
        String str3;
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        String str4 = "";
        if (str != null) {
            str4 = "tournament='" + str + "'";
        }
        if (i == 3) {
            StringBuilder sb = new StringBuilder();
            sb.append("Select * from match");
            if (str != null) {
                str3 = " where " + str4;
            } else {
                str3 = "";
            }
            sb.append(String.valueOf(str3));
            str2 = sb.toString();
        } else if (i == 1) {
            if (("Select * from match where is_test_match=1" + str) != null) {
                str2 = " and " + str4;
            } else {
                str2 = "";
            }
        } else if (i == 2) {
            if (("Select * from match where is_test_match=0" + str) != null) {
                str2 = " and " + str4;
            } else {
                str2 = "";
            }
        } else if (i == 4) {
            if (("Select * from match where result='No result'" + str) != null) {
                str2 = " and " + str4;
            } else {
                str2 = "";
            }
        } else {
            str2 = null;
        }
        if (str2 == null) {
            return new ArrayList<>();
        }
        Cursor rawQuery = readableDatabase.rawQuery(str2, (String[]) null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            Match match = new Match();
            match.setMatchID(rawQuery.getLong(rawQuery.getColumnIndex("id")));
            match.setTeam_Yours_id(rawQuery.getLong(rawQuery.getColumnIndex(KEY_TEAM_YOURS_ID)));
            match.setTeam_opp_id(rawQuery.getLong(rawQuery.getColumnIndex(KEY_TEAM_OPP_ID)));
            match.setYourteam(getTeamName(match.getTeam_Yours_id()));
            match.setOpponent(getTeamName(match.getTeam_opp_id()));
            match.setResult(rawQuery.getString(rawQuery.getColumnIndex(KEY_RESULT)));
            match.setOvers(rawQuery.getInt(rawQuery.getColumnIndex(KEY_MATCH_OVERS)));
            arrayList.add(match);
            rawQuery.moveToNext();
        }
        rawQuery.close();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Match match2 = (Match) it.next();
            Team team2 = new Team();
            team2.setTeamID(match2.getTeam_Yours_id());
            team2.setName(match2.getYourteam());
            HighestTotal highestTotal = new HighestTotal();
            highestTotal.setOpponent(match2.getOpponent());
            team2.setHighestTotal(highestTotal);
            Team team1Stats = getTeam1Stats(match2.getMatchID(), team2.getTeamID(), team2);
            team1Stats.getHighestTotal().setScore(team1Stats.getScore());
            team1Stats.setMatchTotalOvers(match2.getOvers());
            match2.setTeam1(team1Stats);
            Team team3 = new Team();
            team3.setTeamID(match2.getTeam_opp_id());
            team3.setName(match2.getOpponent());
            HighestTotal highestTotal2 = new HighestTotal();
            highestTotal2.setOpponent(match2.getYourteam());
            team3.setHighestTotal(highestTotal2);
            Team team1Stats2 = getTeam1Stats(match2.getMatchID(), team3.getTeamID(), team3);
            team1Stats2.getHighestTotal().setScore(team1Stats2.getScore());
            team1Stats2.setMatchTotalOvers(match2.getOvers());
            match2.setTeam2(team1Stats2);
            match2.getTeam1().setOpponentTeam(new Team(match2.getTeam2()));
            match2.getTeam2().setOpponentTeam(new Team(match2.getTeam1()));
            match2.getTeam1().setMatch(match2);
            match2.getTeam2().setMatch(match2);
            if (!match2.getResult().equals("No result")) {
                if (match2.getTeam1().getScore() > match2.getTeam2().getScore()) {
                    match2.getTeam1().setMatches_won(1);
                    match2.getTeam1().setPoints(2);
                    match2.getTeam2().setMatches_lost(1);
                    match2.getTeam2().setPoints(-2);
                    HighestTotal highestTotal3 = new HighestTotal();
                    highestTotal3.setOpponent(match2.getOpponent());
                    highestTotal3.setScore(match2.getTeam1().getScore());
                    match2.getTeam1().setHighestDefended(highestTotal3);
                } else if (match2.getTeam1().getScore() < match2.getTeam2().getScore()) {
                    match2.getTeam2().setMatches_won(1);
                    match2.getTeam2().setPoints(2);
                    match2.getTeam1().setMatches_lost(1);
                    match2.getTeam1().setPoints(-2);
                    HighestTotal highestTotal4 = new HighestTotal();
                    highestTotal4.setOpponent(match2.getYourteam());
                    highestTotal4.setScore(match2.getTeam1().getScore());
                    match2.getTeam2().setHighestChased(highestTotal4);
                } else if (match2.getTeam1().getScore() == match2.getTeam2().getScore()) {
                    match2.getTeam1().setMatches_tied(1);
                    match2.getTeam2().setMatches_tied(1);
                    match2.getTeam1().setPoints(1);
                    match2.getTeam2().setPoints(1);
                }
            }
        }
        ArrayList<String> allTeamsNames = UtilityFunctions.getAllTeamsNames(arrayList);
        ArrayList<Team> arrayList2 = new ArrayList<>();
        for (int i2 = 0; i2 < allTeamsNames.size(); i2++) {
            ArrayList arrayList3 = new ArrayList();
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                Match match3 = (Match) it2.next();
                if (match3.getYourteam().equals(allTeamsNames.get(i2))) {
                    arrayList3.add(match3.getTeam1());
                } else if (match3.getOpponent().equals(allTeamsNames.get(i2))) {
                    arrayList3.add(match3.getTeam2());
                }
            }
            if (arrayList3.size() > 0) {
                try {
                    team = new Team((Team) arrayList3.get(0));
                } catch (Exception e) {
                    e.printStackTrace();
                    team = null;
                }
                team.setMatches_Played(1);
                if (arrayList3.size() > 1) {
                    for (int i3 = 1; i3 < arrayList3.size(); i3++) {
                        team.combineTeamStats((Team) arrayList3.get(i3));
                        team.getOpponentTeam().combineTeamStats(((Team) arrayList3.get(i3)).getOpponentTeam());
                        team.setMatches_Played(1);
                    }
                }
                arrayList2.add(team);
            }
        }
        ListHelper.sortTeamsOnPoints(arrayList2);
        return arrayList2;
    }

    private ArrayList<Team> getAllTeamNames(ArrayList<Match> arrayList) {
        ArrayList<Team> arrayList2 = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            Team existingTeam = getExistingTeam(arrayList.get(i).getYourteam(), arrayList2);
            existingTeam.setName(arrayList.get(i).getYourteam());
            existingTeam.setTeamID(arrayList.get(i).getTeam_Yours_id());
            existingTeam.setMatches_Played(1);
            existingTeam.setNet_Run_Rate(getNetRunRate(existingTeam.getName(), arrayList));
            String result = arrayList.get(i).getResult();
            String[] split = result.split(" ");
            int wordIndex = getWordIndex(split);
            StringBuffer stringBuffer = new StringBuffer();
            if (wordIndex != -1) {
                for (int i2 = 0; i2 < wordIndex; i2++) {
                    stringBuffer.append(split[i2] + " ");
                }
            }
            if (wordIndex != -1) {
                if (!stringBuffer.toString().equals(existingTeam.getName() + " ") || !split[wordIndex].equals("won")) {
                    if (stringBuffer.equals(existingTeam.getName() + " ") && split[wordIndex].equals("lost")) {
                        existingTeam.setMatches_lost(1);
                        existingTeam.setPoints(-2);
                    } else if (split[wordIndex].equals("lost")) {
                        existingTeam.setMatches_won(1);
                        existingTeam.setPoints(2);
                    } else if (split[wordIndex].equals("won")) {
                        existingTeam.setMatches_lost(1);
                        existingTeam.setPoints(-2);
                    }
                } else {
                    existingTeam.setMatches_won(1);
                    existingTeam.setPoints(2);
                }
            } else if (result.split(":")[0].equals("Match Drawn")) {
                existingTeam.setMatches_tied(1);
                existingTeam.setPoints(1);
            } else if (result.split(":")[0].equals("No result")) {
                existingTeam.setMatches_tied(1);
                existingTeam.setPoints(0);
            }
            if (!checkExistingTeam(existingTeam.getName(), arrayList2)) {
                arrayList2.add(existingTeam);
            }
            Team existingTeam2 = getExistingTeam(arrayList.get(i).getOpponent(), arrayList2);
            existingTeam2.setName(arrayList.get(i).getOpponent());
            existingTeam2.setTeamID(arrayList.get(i).getTeam_opp_id());
            existingTeam2.setNet_Run_Rate(getNetRunRate(existingTeam2.getName(), arrayList));
            existingTeam2.setMatches_Played(1);
            String result2 = arrayList.get(i).getResult();
            String[] split2 = result2.split(" ");
            int wordIndex2 = getWordIndex(split2);
            if (wordIndex2 != -1) {
                if (!stringBuffer.toString().equals(existingTeam2.getName() + " ") || !split2[wordIndex2].equals("won")) {
                    if (stringBuffer.toString().equals(existingTeam2.getName() + " ") && split2[wordIndex2].equals("lost")) {
                        existingTeam2.setMatches_lost(1);
                        existingTeam2.setPoints(-2);
                    } else if (split2[wordIndex2].equals("lost")) {
                        existingTeam2.setMatches_lost(1);
                        existingTeam2.setPoints(-2);
                    } else if (split2[wordIndex2].equals("won")) {
                        existingTeam2.setMatches_won(1);
                        existingTeam2.setPoints(2);
                    }
                } else {
                    existingTeam2.setMatches_won(1);
                    existingTeam2.setPoints(2);
                }
            } else if (result2.split(":")[0].equals("Match Drawn")) {
                existingTeam2.setMatches_tied(1);
                existingTeam2.setPoints(1);
            } else if (result2.split(":")[0].equals("No result")) {
                existingTeam2.setMatches_tied(1);
                existingTeam2.setPoints(0);
            }
            if (!checkExistingTeam(existingTeam2.getName(), arrayList2)) {
                arrayList2.add(existingTeam2);
            }
        }
        Collections.sort(arrayList2, new Comparator<Team>() {
            public int compare(Team team, Team team2) {
                return Double.compare(team2.getNet_Run_Rate(), team.getNet_Run_Rate());
            }
        });
        return arrayList2;
    }

    /* access modifiers changed from: package-private */
    public int getWordIndex(String[] strArr) {
        for (int i = 0; i < strArr.length - 1; i++) {
            if (strArr[i].equals("won") || strArr[i].equals("lost")) {
                return i;
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public Team getExistingTeam(String str, ArrayList<Team> arrayList) {
        Team team = new Team();
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getName().equals(str)) {
                return arrayList.get(i);
            }
        }
        return team;
    }

    /* access modifiers changed from: package-private */
    public boolean checkExistingTeam(String str, ArrayList<Team> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getName().equals(str)) {
                return true;
            }
        }
        return false;
    }

    private String getTeamName(long j) {
        SQLiteDatabase sQLiteDatabase;
        try {
            sQLiteDatabase = getReadableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
            sQLiteDatabase = this.currentDBInstance;
        }
        String str = null;
        Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT * FROM team WHERE id=" + j, (String[]) null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            str = rawQuery.getString(rawQuery.getColumnIndex(KEY_TEAM_NAME));
            rawQuery.moveToNext();
        }
        rawQuery.close();
        return str;
    }

    public void UpdateTeamPlayers(String str, String str2, ArrayList<String> arrayList, String str3) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TEAM_NAME, str2);
        contentValues.put(KEY_TEAM_SIDE, str3);
        contentValues.put(KEY_PLAYER_1, arrayList.get(0).toString());
        contentValues.put(KEY_PLAYER_2, arrayList.get(1).toString());
        contentValues.put(KEY_PLAYER_3, arrayList.get(2).toString());
        contentValues.put(KEY_PLAYER_4, arrayList.get(3).toString());
        contentValues.put(KEY_PLAYER_5, arrayList.get(4).toString());
        contentValues.put(KEY_PLAYER_6, arrayList.get(5).toString());
        contentValues.put(KEY_PLAYER_7, arrayList.get(6).toString());
        contentValues.put(KEY_PLAYER_8, arrayList.get(7).toString());
        contentValues.put(KEY_PLAYER_9, arrayList.get(8).toString());
        contentValues.put(KEY_PLAYER_10, arrayList.get(9).toString());
        contentValues.put(KEY_PLAYER_11, arrayList.get(10).toString());
        contentValues.put(KEY_PLAYER_12, arrayList.get(11).toString());
        contentValues.put(KEY_PLAYER_13, arrayList.get(12).toString());
        contentValues.put(KEY_PLAYER_14, arrayList.get(13).toString());
        contentValues.put(KEY_PLAYER_15, arrayList.get(14).toString());
        try {
            writableDatabase.update(TABLE_TEAM_PLAYERS, contentValues, "team_name='" + str + "'", (String[]) null);
        } catch (Exception unused) {
            Toast.makeText(this.context, "Error while updating team. Please Try again updating", 1).show();
        }
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(KEY_TEAM_NAME, str2);
        try {
            writableDatabase.update(TABLE_TEAM, contentValues2, "team_name='" + str + "'", (String[]) null);
        } catch (Exception unused2) {
            Toast.makeText(this.context, "Error while updating team. Please Try again updating", 1).show();
        }
    }

    /* access modifiers changed from: package-private */
    public long insertFallofWicket(long j, long j2, FallOfWickets fallOfWickets) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TEAM_ID, Long.valueOf(j2));
        contentValues.put(KEY_MATCH_ID, Long.valueOf(j));
        contentValues.put(KEY_WICKET_SCORE, Integer.valueOf(fallOfWickets.getScore()));
        contentValues.put(KEY_WICKET_BALL, Integer.valueOf(fallOfWickets.getBall()));
        contentValues.put(KEY_WICKET_NO, Integer.valueOf(fallOfWickets.getWicketNo()));
        contentValues.put(KEY_WICKET_BATSMAN, fallOfWickets.getBatsmanName());
        contentValues.put(KEY_WICKET_BOWLER, fallOfWickets.getBowlerName());
        contentValues.put(KEY_WICKET_OVER, Integer.valueOf(fallOfWickets.getOverno()));
        return writableDatabase.insert(TABLE_FALL_OF_WICKETS, (String) null, contentValues);
    }

    /* access modifiers changed from: package-private */
    public long deleteFallOfWicket(long j) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        return (long) writableDatabase.delete(TABLE_FALL_OF_WICKETS, "id=" + j, (String[]) null);
    }

    public ArrayList<Team> getAddedTeamsAndPlayers() throws Exception {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        ArrayList<Team> arrayList = new ArrayList<>();
        Cursor rawQuery = readableDatabase.rawQuery("Select * from user_added_teams", (String[]) null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            Team team = new Team();
            team.setName(rawQuery.getString(rawQuery.getColumnIndex(KEY_TEAM_NAME)));
            team.setTeamID(rawQuery.getLong(rawQuery.getColumnIndex("id")));
            team.setTeamSide(rawQuery.getInt(rawQuery.getColumnIndex(KEY_TEAM_SIDE)));
            team.setCaptainId((long) rawQuery.getInt(rawQuery.getColumnIndex(KEY_CAPTAIN_ID)));
            team.setKeeperId((long) rawQuery.getInt(rawQuery.getColumnIndex(KEY_WK_ID)));
            try {
                team.setImage(SharedPrefsHelper.getTeamImage(this.context, team));
            } catch (Exception e) {
                e.printStackTrace();
                team.setImage((String) null);
            }
            arrayList.add(team);
            rawQuery.moveToNext();
        }
        rawQuery.close();
        Iterator<Team> it = arrayList.iterator();
        while (it.hasNext()) {
            Team next = it.next();
            Cursor rawQuery2 = readableDatabase.rawQuery("SELECT * FROM user_added_players WHERE team_id=" + next.getTeamID() + "", (String[]) null);
            ArrayList arrayList2 = new ArrayList();
            rawQuery2.moveToFirst();
            while (!rawQuery2.isAfterLast()) {
                Player player = new Player();
                player.setPlayerId(rawQuery2.getLong(rawQuery2.getColumnIndex("id")));
                player.setName(rawQuery2.getString(rawQuery2.getColumnIndex("player_name")));
                try {
                    player.setImage(rawQuery2.getString(rawQuery2.getColumnIndex("player_image")));
                } catch (Exception e2) {
                    e2.printStackTrace();
                    player.setImage((String) null);
                }
                player.setTeamId(next.getTeamID());
                try {
                    player.setInPlayingXI(SharedPrefsHelper.getPlayerInPlayingXI(this.context, player));
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                int i = rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_IS_BATSMAN));
                int i2 = rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_IS_BOWLER));
                int i3 = rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_IS_KEEPER));
                int i4 = rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_IS_ALROUNDER));
                if (i == 1) {
                    player.setBatsman(true);
                } else {
                    player.setBatsman(false);
                }
                if (i2 == 1) {
                    player.setBowler(true);
                } else {
                    player.setBowler(false);
                }
                if (i3 == 1) {
                    player.setWicketKeeper(true);
                } else {
                    player.setWicketKeeper(false);
                }
                if (i4 == 1) {
                    player.setAlrounder(true);
                } else {
                    player.setAlrounder(false);
                }
                arrayList2.add(player);
                rawQuery2.moveToNext();
            }
            next.setPlayers(arrayList2);
        }
        return arrayList;
    }

    public Team getAddedTeamsAndPlayers(int i) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Team team = new Team();
        Cursor rawQuery = readableDatabase.rawQuery("Select * from user_added_teams WHERE team_side=" + i, (String[]) null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            team.setName(rawQuery.getString(rawQuery.getColumnIndex(KEY_TEAM_NAME)));
            team.setTeamID(rawQuery.getLong(rawQuery.getColumnIndex("id")));
            team.setTeamSide(rawQuery.getInt(rawQuery.getColumnIndex(KEY_TEAM_SIDE)));
            rawQuery.moveToNext();
        }
        rawQuery.close();
        Cursor rawQuery2 = readableDatabase.rawQuery("SELECT * FROM user_added_players WHERE team_id=" + team.getTeamID() + "", (String[]) null);
        ArrayList arrayList = new ArrayList();
        rawQuery2.moveToFirst();
        while (!rawQuery2.isAfterLast()) {
            Player player = new Player();
            player.setPlayerId(rawQuery2.getLong(rawQuery2.getColumnIndex("id")));
            player.setName(rawQuery2.getString(rawQuery2.getColumnIndex("player_name")));
            try {
                player.setImage(rawQuery2.getString(rawQuery2.getColumnIndex("player_image")));
            } catch (Exception e) {
                e.printStackTrace();
                player.setImage((String) null);
            }
            player.setTeamId(team.getTeamID());
            arrayList.add(player);
            rawQuery2.moveToNext();
        }
        team.setPlayers(arrayList);
        return team;
    }

    public void changeBowlerTypeOnce() throws Exception {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_PLAYER_TYPE, "bowler");
        writableDatabase.update(TABLE_PLAYERS, contentValues, "player_type='edBowler'", (String[]) null);
        SharedPrefsHelper.insertBowlerTypeUpdated(this.context, true);
    }

    public ArrayList<Team_players> getAllTeamPlayers() {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        ArrayList<Team_players> arrayList = new ArrayList<>();
        Cursor rawQuery = readableDatabase.rawQuery("Select * from teamplayers", (String[]) null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            Team_players team_players = new Team_players();
            ArrayList arrayList2 = new ArrayList();
            rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_1));
            team_players.setTeamname(rawQuery.getString(rawQuery.getColumnIndex(KEY_TEAM_NAME)));
            team_players.setTeamside(rawQuery.getString(rawQuery.getColumnIndex(KEY_TEAM_SIDE)));
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_1)).isEmpty() && !rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_1)).equals(" ")) {
                arrayList2.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_1)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_2)).isEmpty() && !rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_2)).equals(" ")) {
                arrayList2.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_2)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_3)).isEmpty() && !rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_3)).equals(" ")) {
                arrayList2.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_3)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_4)).isEmpty() && !rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_4)).equals(" ")) {
                arrayList2.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_4)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_5)).isEmpty() && !rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_5)).equals(" ")) {
                arrayList2.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_5)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_6)).isEmpty() && !rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_6)).equals(" ")) {
                arrayList2.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_6)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_7)).isEmpty() && !rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_7)).equals(" ")) {
                arrayList2.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_7)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_8)).isEmpty() && !rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_8)).equals(" ")) {
                arrayList2.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_8)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_9)).isEmpty() && !rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_9)).equals(" ")) {
                arrayList2.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_9)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_10)).isEmpty() && !rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_10)).equals(" ")) {
                arrayList2.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_10)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_11)).isEmpty() && !rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_11)).equals(" ")) {
                arrayList2.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_11)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_12)).isEmpty() && !rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_12)).equals(" ")) {
                arrayList2.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_12)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_13)).isEmpty() && !rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_13)).equals(" ")) {
                arrayList2.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_13)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_14)).isEmpty() && !rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_14)).equals(" ")) {
                arrayList2.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_14)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_15)).isEmpty() && !rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_15)).equals(" ")) {
                arrayList2.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_15)));
            }
            team_players.setPlayers(arrayList2);
            arrayList.add(team_players);
            rawQuery.moveToNext();
        }
        rawQuery.close();
        return arrayList;
    }

    public Team_players getYourTeamPlayers(String str) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Team_players team_players = new Team_players();
        Cursor rawQuery = readableDatabase.rawQuery("Select * from teamplayers where team_side='yours'", (String[]) null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            ArrayList arrayList = new ArrayList();
            team_players.setTeamname(rawQuery.getString(rawQuery.getColumnIndex(KEY_TEAM_NAME)));
            team_players.setTeamside(rawQuery.getString(rawQuery.getColumnIndex(KEY_TEAM_SIDE)));
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_1)).equals(" ")) {
                arrayList.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_1)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_2)).equals(" ")) {
                arrayList.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_2)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_3)).equals(" ")) {
                arrayList.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_3)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_4)).equals(" ")) {
                arrayList.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_4)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_5)).equals(" ")) {
                arrayList.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_5)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_6)).equals(" ")) {
                arrayList.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_6)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_7)).equals(" ")) {
                arrayList.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_7)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_8)).equals(" ")) {
                arrayList.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_8)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_9)).equals(" ")) {
                arrayList.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_9)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_10)).equals(" ")) {
                arrayList.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_10)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_11)).equals(" ")) {
                arrayList.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_11)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_12)).equals(" ")) {
                arrayList.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_12)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_13)).equals(" ")) {
                arrayList.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_13)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_14)).equals(" ")) {
                arrayList.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_14)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_15)).equals(" ")) {
                arrayList.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_15)));
            }
            team_players.setPlayers(arrayList);
            rawQuery.moveToNext();
        }
        rawQuery.close();
        return team_players;
    }

    /* access modifiers changed from: package-private */
    public void UpdateMatchDetails(long j, String str, String str2, int i) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TOURNAMENT_NAME, str);
        contentValues.put(KEY_VENUE, str2);
        contentValues.put(KEY_MATCH_OVERS, Integer.valueOf(i));
        writableDatabase.update(TABLE_MATCH, contentValues, "id=" + j, (String[]) null);
    }

    /* access modifiers changed from: package-private */
    public void UpdateBatsmanNames(long j, long j2, long j3, String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("player_name", str);
        writableDatabase.update(TABLE_PLAYERS, contentValues, "team_id=" + j2 + " and " + "id" + "=" + j3, (String[]) null);
    }

    /* access modifiers changed from: package-private */
    public Team_players getOtherTeamPlayers(String str) {
        Team_players team_players = new Team_players();
        Cursor rawQuery = getReadableDatabase().rawQuery("Select * from teamplayers where team_side='opp' and team_name='" + str + "'", (String[]) null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            ArrayList arrayList = new ArrayList();
            team_players.setTeamname(rawQuery.getString(rawQuery.getColumnIndex(KEY_TEAM_NAME)));
            team_players.setTeamside(rawQuery.getString(rawQuery.getColumnIndex(KEY_TEAM_SIDE)));
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_1)).equals(" ")) {
                arrayList.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_1)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_2)).equals(" ")) {
                arrayList.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_2)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_3)).equals(" ")) {
                arrayList.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_3)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_4)).equals(" ")) {
                arrayList.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_4)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_5)).equals(" ")) {
                arrayList.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_5)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_6)).equals(" ")) {
                arrayList.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_6)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_7)).equals(" ")) {
                arrayList.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_7)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_8)).equals(" ")) {
                arrayList.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_8)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_9)).equals(" ")) {
                arrayList.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_9)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_10)).equals(" ")) {
                arrayList.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_10)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_11)).equals(" ")) {
                arrayList.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_11)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_12)).equals(" ")) {
                arrayList.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_12)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_13)).equals(" ")) {
                arrayList.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_13)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_14)).equals(" ")) {
                arrayList.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_14)));
            }
            if (!rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_15)).equals(" ")) {
                arrayList.add(rawQuery.getString(rawQuery.getColumnIndex(KEY_PLAYER_15)));
            }
            team_players.setPlayers(arrayList);
            rawQuery.moveToNext();
        }
        rawQuery.close();
        return team_players;
    }

    /* access modifiers changed from: package-private */
    public ArrayList<Match> getYourTeamMatches(String str) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        ArrayList<Match> arrayList = new ArrayList<>();
        Cursor rawQuery = readableDatabase.rawQuery("Select * from team where team_name='" + str + "'", (String[]) null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            Cursor rawQuery2 = readableDatabase.rawQuery("Select * from match where team_yours_id=" + rawQuery.getLong(rawQuery.getColumnIndex("id")) + "", (String[]) null);
            rawQuery2.moveToFirst();
            while (!rawQuery2.isAfterLast()) {
                rawQuery2.moveToFirst();
                Match match = new Match();
                match.setVenue(rawQuery2.getString(rawQuery2.getColumnIndex(KEY_VENUE)));
                match.setTeam_Yours_id(rawQuery2.getLong(rawQuery2.getColumnIndex(KEY_TEAM_YOURS_ID)));
                match.setTeam_opp_id(rawQuery2.getLong(rawQuery2.getColumnIndex(KEY_TEAM_OPP_ID)));
                match.setResult(rawQuery2.getString(rawQuery2.getColumnIndex(KEY_RESULT)));
                match.setDate(rawQuery2.getString(rawQuery2.getColumnIndex("date")));
                match.setMatchID(rawQuery2.getLong(rawQuery2.getColumnIndex("id")));
                Cursor rawQuery3 = readableDatabase.rawQuery("Select * from team where id=" + rawQuery2.getLong(rawQuery2.getColumnIndex("id")) + "", (String[]) null);
                rawQuery3.moveToFirst();
                match.setOpponent(rawQuery3.getString(rawQuery3.getColumnIndex(KEY_TEAM_NAME)));
                rawQuery3.close();
                arrayList.add(match);
                rawQuery2.moveToNext();
            }
            rawQuery2.close();
            rawQuery.moveToNext();
        }
        rawQuery.close();
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public ArrayList<Long> getYourBatsmansIDs(ArrayList<Long> arrayList, String str) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        String str2 = "Select * from player where player_name=" + Formatter.wrapStringWithDoubleQuotes(str) + " and " + KEY_PLAYER_TYPE + "= 'batsman'";
        ArrayList<Long> arrayList2 = new ArrayList<>();
        try {
            Cursor rawQuery = readableDatabase.rawQuery(str2, (String[]) null);
            rawQuery.moveToFirst();
            while (!rawQuery.isAfterLast()) {
                if (arrayList.contains(Long.valueOf(rawQuery.getLong(rawQuery.getColumnIndex(KEY_TEAM_ID))))) {
                    arrayList2.add(Long.valueOf(rawQuery.getLong(rawQuery.getColumnIndex("id"))));
                }
                rawQuery.moveToNext();
            }
            rawQuery.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:46:0x019a  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x01a1  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x01ac  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public apps.shehryar.com.cricketscroingappPro.Batsman.Batsman getYourBatsmanStats(java.lang.String r21, java.lang.String r22, java.util.ArrayList<java.lang.Long> r23) {
        /*
            r20 = this;
            r1 = r20
            r2 = r21
            r3 = r22
            r4 = r23
            java.util.ArrayList r4 = r1.getYourTeamIDs((java.lang.String) r2, (java.util.ArrayList<java.lang.Long>) r4)
            java.util.ArrayList r4 = r1.getYourBatsmansIDs(r4, r3)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r5 = new apps.shehryar.com.cricketscroingappPro.Batsman.Batsman
            r5.<init>()
            android.database.sqlite.SQLiteDatabase r6 = r20.getReadableDatabase()
            r7 = 0
            r8 = r7
            r9 = r8
            r10 = r9
            r11 = r10
            r12 = r11
        L_0x001f:
            int r13 = r4.size()
            if (r7 >= r13) goto L_0x0168
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r15 = "Select * from batsmanscores where player_id="
            r13.append(r15)
            java.lang.Object r15 = r4.get(r7)
            r13.append(r15)
            java.lang.String r15 = ""
            r13.append(r15)
            java.lang.String r13 = r13.toString()
            r15 = 0
            android.database.Cursor r13 = r6.rawQuery(r13, r15)
            r13.moveToFirst()
        L_0x0047:
            boolean r15 = r13.isAfterLast()
            if (r15 != 0) goto L_0x0159
            java.lang.String r15 = "batsman_score"
            int r15 = r13.getColumnIndex(r15)
            int r15 = r13.getInt(r15)
            java.lang.String r14 = "batsman_4s"
            int r14 = r13.getColumnIndex(r14)
            int r14 = r13.getInt(r14)
            r16 = r4
            java.lang.String r4 = "batsman_6s"
            int r4 = r13.getColumnIndex(r4)
            int r4 = r13.getInt(r4)
            r17 = r6
            java.lang.String r6 = "batsman_balls_faced"
            int r6 = r13.getColumnIndex(r6)
            int r6 = r13.getInt(r6)
            int r9 = r9 + 1
            int r10 = r10 + 1
            r18 = r10
            java.lang.String r10 = "batsman_0s"
            int r10 = r13.getColumnIndex(r10)
            int r10 = r13.getInt(r10)
            r5.setDots(r10)
            java.lang.String r10 = "batsman_1s"
            int r10 = r13.getColumnIndex(r10)
            int r10 = r13.getInt(r10)
            r5.setSingles(r10)
            java.lang.String r10 = "batsman_2s"
            int r10 = r13.getColumnIndex(r10)
            int r10 = r13.getInt(r10)
            r5.setDoubles(r10)
            java.lang.String r10 = "batsman_3s"
            int r10 = r13.getColumnIndex(r10)
            int r10 = r13.getInt(r10)
            r5.setThrees(r10)
            r5.setFours(r14)
            r5.setSixs(r4)
            java.lang.String r10 = "match_id"
            int r10 = r13.getColumnIndex(r10)
            int r10 = r13.getInt(r10)
            long r1 = (long) r10
            r5.setMatchid(r1)
            r5.setTotalScore(r15)
            java.lang.String r1 = "out_status"
            int r1 = r13.getColumnIndex(r1)
            java.lang.String r1 = r13.getString(r1)
            r5.setOut(r1)
            r5.setBallsfaced(r6)
            r5.setSingleMatchRuns(r15)
            r5.setSingleMatchBalls(r6)
            r5.setSingleMatchFours(r14)
            r5.setSingleMatchSixes(r4)
            apps.shehryar.com.cricketscroingappPro.Batsman.BatsmanDataUpdater.setPointsToBatsman(r5)
            r1 = 30
            r2 = 50
            if (r15 < r1) goto L_0x00f6
            if (r15 >= r2) goto L_0x00f6
            r1 = 1
            r5.setThirties(r1)
            goto L_0x0106
        L_0x00f6:
            r1 = 1
            r4 = 100
            if (r15 < r2) goto L_0x0101
            if (r15 >= r4) goto L_0x0101
            r5.setFifties(r1)
            goto L_0x0106
        L_0x0101:
            if (r15 < r4) goto L_0x0106
            r5.setHundreds(r1)
        L_0x0106:
            r5.setInnings(r9)
            java.lang.String r2 = r5.isOut()
            if (r2 == 0) goto L_0x011e
            java.lang.String r2 = r5.isOut()
            java.lang.String r4 = "false"
            boolean r2 = r2.equals(r4)
            if (r2 != 0) goto L_0x011e
            int r11 = r11 + 1
            goto L_0x0120
        L_0x011e:
            int r12 = r12 + 1
        L_0x0120:
            java.lang.String r2 = r5.isOut()
            if (r2 == 0) goto L_0x0132
            java.lang.String r2 = r5.isOut()
            java.lang.String r4 = "false"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0134
        L_0x0132:
            int r9 = r9 + -1
        L_0x0134:
            java.lang.String r2 = "batsman_score"
            int r2 = r13.getColumnIndex(r2)
            int r2 = r13.getInt(r2)
            if (r2 <= r8) goto L_0x014a
            java.lang.String r2 = "batsman_score"
            int r2 = r13.getColumnIndex(r2)
            int r8 = r13.getInt(r2)
        L_0x014a:
            r13.moveToNext()
            r4 = r16
            r6 = r17
            r10 = r18
            r1 = r20
            r2 = r21
            goto L_0x0047
        L_0x0159:
            r16 = r4
            r17 = r6
            r13.close()
            int r7 = r7 + 1
            r1 = r20
            r2 = r21
            goto L_0x001f
        L_0x0168:
            r1 = 1
            r5.setBest(r8)
            r2 = r21
            r5.setTeam_Name(r2)
            r5.setTeamName(r2)
            if (r9 > 0) goto L_0x0177
            r9 = r1
        L_0x0177:
            r5.setInnings(r9)
            r5.setMatches(r10)
            java.lang.String r2 = r5.getTeam_Name()     // Catch:{ Exception -> 0x0191 }
            r4 = r20
            long r6 = r4.getUserAddedTeamId(r2)     // Catch:{ Exception -> 0x018f }
            java.lang.String r2 = r4.getPlayerImage(r3, r6)     // Catch:{ Exception -> 0x018f }
            r5.setImage(r2)     // Catch:{ Exception -> 0x018f }
            goto L_0x0198
        L_0x018f:
            r0 = move-exception
            goto L_0x0194
        L_0x0191:
            r0 = move-exception
            r4 = r20
        L_0x0194:
            r2 = r0
            r2.printStackTrace()
        L_0x0198:
            if (r11 > 0) goto L_0x019b
            r11 = r1
        L_0x019b:
            int r1 = r5.getMatches()
            if (r1 == 0) goto L_0x01ac
            int r1 = r5.getTotalScore()
            double r1 = (double) r1
            double r6 = (double) r11
            double r1 = r1 / r6
            r5.setAverage(r1)
            goto L_0x01b1
        L_0x01ac:
            r1 = 0
            r5.setAverage(r1)
        L_0x01b1:
            r5.setName(r3)
            r5.setNoOfNotOuts(r12)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: apps.shehryar.com.cricketscroingappPro.DBHelper.getYourBatsmanStats(java.lang.String, java.lang.String, java.util.ArrayList):apps.shehryar.com.cricketscroingappPro.Batsman.Batsman");
    }

    public ArrayList<Batsman> getTournamentTopScorers(String str, ArrayList<Team> arrayList) {
        ArrayList<Batsman> arrayList2 = new ArrayList<>();
        ArrayList arrayList3 = new ArrayList();
        Iterator<Team_players> it = getAllTeamsWithBatsmen(arrayList).iterator();
        while (it.hasNext()) {
            Team_players next = it.next();
            Iterator<String> it2 = next.getPlayers().iterator();
            while (it2.hasNext()) {
                arrayList3.add(getYourBatsmanStats(next.getTeam().getName(), it2.next(), next.getTeam().teamAllIDs));
            }
        }
        Collections.sort(arrayList3, new Comparator<Batsman>() {
            public int compare(Batsman batsman, Batsman batsman2) {
                return batsman2.getTotalScore() - batsman.getTotalScore();
            }
        });
        for (int i = 0; i < arrayList3.size(); i++) {
            arrayList2.add(arrayList3.get(i));
        }
        return arrayList2;
    }

    private ArrayList<Team_players> getAllTeamsWithBatsmen(ArrayList<Team> arrayList) {
        ArrayList<Team_players> arrayList2 = new ArrayList<>();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Iterator<Team> it = arrayList.iterator();
        while (it.hasNext()) {
            Team next = it.next();
            Team_players team_players = new Team_players();
            team_players.setTeamname(next.getName());
            team_players.setTeam(next);
            Cursor rawQuery = readableDatabase.rawQuery("Select * from batsmanscores", (String[]) null);
            ArrayList arrayList3 = new ArrayList();
            rawQuery.moveToFirst();
            while (!rawQuery.isAfterLast()) {
                if (next.teamAllIDs.contains(Long.valueOf((long) rawQuery.getInt(rawQuery.getColumnIndex(KEY_TEAM_ID))))) {
                    String playerName = getPlayerName((long) rawQuery.getInt(rawQuery.getColumnIndex(KEY_PLAYER_ID)));
                    if (!arrayList3.contains(playerName)) {
                        arrayList3.add(playerName);
                    }
                }
                rawQuery.moveToNext();
            }
            rawQuery.close();
            team_players.setPlayers(arrayList3);
            arrayList2.add(team_players);
        }
        return arrayList2;
    }

    /* access modifiers changed from: package-private */
    public ArrayList<Team> getAllTeamsWithPlayers(ArrayList<Team> arrayList) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Iterator<Team> it = arrayList.iterator();
        while (it.hasNext()) {
            Team next = it.next();
            Cursor rawQuery = readableDatabase.rawQuery("Select * from player", (String[]) null);
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            rawQuery.moveToFirst();
            while (!rawQuery.isAfterLast()) {
                long j = (long) rawQuery.getInt(rawQuery.getColumnIndex(KEY_TEAM_ID));
                try {
                    if (next.teamAllIDs.contains(Long.valueOf(j))) {
                        long j2 = (long) rawQuery.getInt(rawQuery.getColumnIndex("id"));
                        String playerName = getPlayerName(j2);
                        if (!arrayList2.contains(playerName)) {
                            arrayList2.add(playerName);
                            Player player = new Player();
                            player.setPlayerId(j2);
                            player.setName(playerName);
                            player.setTeamId(j);
                            arrayList3.add(player);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                rawQuery.moveToNext();
            }
            rawQuery.close();
            next.setPlayers(arrayList3);
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public ArrayList<Team> getTeamPlayerNames(ArrayList<Team> arrayList) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Iterator<Team> it = arrayList.iterator();
        while (it.hasNext()) {
            Team next = it.next();
            Cursor rawQuery = readableDatabase.rawQuery("Select * from player", (String[]) null);
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            rawQuery.moveToFirst();
            while (!rawQuery.isAfterLast()) {
                long j = (long) rawQuery.getInt(rawQuery.getColumnIndex(KEY_TEAM_ID));
                try {
                    if (next.teamAllIDs.contains(Long.valueOf(j))) {
                        long j2 = (long) rawQuery.getInt(rawQuery.getColumnIndex("id"));
                        String playerName = getPlayerName(j2);
                        if (!arrayList2.contains(playerName)) {
                            arrayList2.add(playerName);
                            Player player = new Player();
                            player.setPlayerId(j2);
                            player.setName(playerName);
                            player.setTeamId(j);
                            arrayList3.add(player);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                rawQuery.moveToNext();
            }
            rawQuery.close();
            next.setPlayers(arrayList3);
        }
        return arrayList;
    }

    private ArrayList<Team_players> getAllTeamsWithBowlers(ArrayList<Team> arrayList) {
        ArrayList<Team_players> arrayList2 = new ArrayList<>();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Iterator<Team> it = arrayList.iterator();
        while (it.hasNext()) {
            Team next = it.next();
            Team_players team_players = new Team_players();
            team_players.setTeamname(next.getName());
            team_players.setTeam(next);
            Cursor rawQuery = readableDatabase.rawQuery("Select * from bowlerscore", (String[]) null);
            ArrayList arrayList3 = new ArrayList();
            rawQuery.moveToFirst();
            while (!rawQuery.isAfterLast()) {
                try {
                    if (next.teamAllIDs.contains(Long.valueOf((long) rawQuery.getInt(rawQuery.getColumnIndex(KEY_TEAM_ID))))) {
                        String playerName = getPlayerName((long) rawQuery.getInt(rawQuery.getColumnIndex(KEY_PLAYER_ID)));
                        if (!arrayList3.contains(playerName)) {
                            arrayList3.add(playerName);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                rawQuery.moveToNext();
            }
            rawQuery.close();
            team_players.setPlayers(arrayList3);
            arrayList2.add(team_players);
        }
        return arrayList2;
    }

    public ArrayList<Bowler> getTournamentTopBowlers(String str, ArrayList<Team> arrayList) {
        new ArrayList();
        ArrayList<Bowler> arrayList2 = new ArrayList<>();
        Iterator<Team_players> it = getAllTeamsWithBowlers(arrayList).iterator();
        while (it.hasNext()) {
            Team_players next = it.next();
            Iterator<String> it2 = next.getPlayers().iterator();
            while (it2.hasNext()) {
                arrayList2.add(getYourBowlerStats(next.getTeam(), next.getTeamname(), it2.next(), str));
            }
        }
        Collections.sort(arrayList2, new Comparator<Bowler>() {
            public int compare(Bowler bowler, Bowler bowler2) {
                return bowler2.getWickets() - bowler.getWickets();
            }
        });
        int i = 0;
        while (i < arrayList2.size()) {
            if (arrayList2.get(i).getWickets() == 0) {
                arrayList2.remove(i);
                i--;
            }
            i++;
        }
        return arrayList2;
    }

    public ArrayList<Team> getTournamentTeams(String str) {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery("Select * from match where tournament = '" + Formatter.replaceSingleQuoteWithTwoSingleQuotes(Formatter.replaceForwardSlashWithBackSlash(str)) + "'", (String[]) null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            try {
                Team team = new Team();
                team.setTeamID(rawQuery.getLong(rawQuery.getColumnIndex(KEY_TEAM_YOURS_ID)));
                team.setName(getTeamName(team.getTeamID()));
                team.teamAllIDs.add(Long.valueOf(team.getTeamID()));
                arrayList.add(team);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Team team2 = new Team();
                team2.setTeamID(rawQuery.getLong(rawQuery.getColumnIndex(KEY_TEAM_OPP_ID)));
                team2.setName(getTeamName(team2.getTeamID()));
                team2.teamAllIDs.add(Long.valueOf(team2.getTeamID()));
                arrayList.add(team2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            rawQuery.moveToNext();
        }
        rawQuery.close();
        return getFinalTeamList(arrayList);
    }

    /* access modifiers changed from: package-private */
    public ArrayList<Team> getFinalTeamList(ArrayList<Team> arrayList) {
        ArrayList<Team> arrayList2 = new ArrayList<>();
        Iterator<Team> it = arrayList.iterator();
        while (it.hasNext()) {
            Team next = it.next();
            try {
                if (!getTeamNames(arrayList2).contains(next.getName())) {
                    arrayList2.add(next);
                } else {
                    addIdtoCorrespondingTeam(arrayList2, next);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return arrayList2;
    }

    private void addIdtoCorrespondingTeam(ArrayList<Team> arrayList, Team team) {
        Iterator<Team> it = arrayList.iterator();
        while (it.hasNext()) {
            Team next = it.next();
            if (next.getName().equals(team.getName())) {
                next.teamAllIDs.add(Long.valueOf(team.getTeamID()));
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public ArrayList<String> getTeamNames(ArrayList<Team> arrayList) {
        ArrayList<String> arrayList2 = new ArrayList<>();
        Iterator<Team> it = arrayList.iterator();
        while (it.hasNext()) {
            try {
                arrayList2.add(it.next().getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return arrayList2;
    }

    /* access modifiers changed from: package-private */
    public ArrayList<Team> getAllTournamentTeams(int i) {
        SQLiteDatabase sQLiteDatabase;
        ArrayList arrayList = new ArrayList();
        try {
            sQLiteDatabase = getReadableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
            sQLiteDatabase = getReadableDatabase();
        }
        String str = i == 3 ? "Select * from match" : i == 1 ? "Select * from match where is_test_match=1" : i == 2 ? "Select * from match where is_test_match=0" : null;
        if (str == null) {
            return new ArrayList<>();
        }
        Cursor rawQuery = sQLiteDatabase.rawQuery(str, (String[]) null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            try {
                Team team = new Team();
                team.setTeamID(rawQuery.getLong(rawQuery.getColumnIndex(KEY_TEAM_YOURS_ID)));
                team.setName(getTeamName(team.getTeamID()));
                team.teamAllIDs.add(Long.valueOf(team.getTeamID()));
                arrayList.add(team);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                Team team2 = new Team();
                team2.setTeamID(rawQuery.getLong(rawQuery.getColumnIndex(KEY_TEAM_OPP_ID)));
                team2.setName(getTeamName(team2.getTeamID()));
                team2.teamAllIDs.add(Long.valueOf(team2.getTeamID()));
                arrayList.add(team2);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            rawQuery.moveToNext();
        }
        rawQuery.close();
        return getFinalTeamList(arrayList);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0162  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0171  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public apps.shehryar.com.cricketscroingappPro.Batsman.Batsman getYourBatsmanStats(apps.shehryar.com.cricketscroingappPro.Team.Team r18, java.lang.String r19, java.lang.String r20, java.lang.String r21) {
        /*
            r17 = this;
            r1 = r17
            r2 = r20
            r3 = r18
            java.util.ArrayList<java.lang.Long> r3 = r3.teamAllIDs
            java.util.ArrayList r3 = r1.getYourBatsmansIDs(r3, r2)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r4 = new apps.shehryar.com.cricketscroingappPro.Batsman.Batsman
            r4.<init>()
            android.database.sqlite.SQLiteDatabase r5 = r17.getReadableDatabase()
            r6 = 0
            r7 = r6
            r8 = r7
            r9 = r8
        L_0x0019:
            int r10 = r3.size()
            if (r6 >= r10) goto L_0x0133
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "Select * from batsmanscores where player_id="
            r10.append(r11)
            java.lang.Object r11 = r3.get(r6)
            r10.append(r11)
            java.lang.String r11 = ""
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            r11 = 0
            android.database.Cursor r10 = r5.rawQuery(r10, r11)
            r10.moveToFirst()
        L_0x0041:
            boolean r11 = r10.isAfterLast()
            if (r11 != 0) goto L_0x0128
            java.lang.String r11 = "batsman_score"
            int r11 = r10.getColumnIndex(r11)
            int r11 = r10.getInt(r11)
            java.lang.String r12 = "batsman_4s"
            int r12 = r10.getColumnIndex(r12)
            int r12 = r10.getInt(r12)
            java.lang.String r13 = "batsman_6s"
            int r13 = r10.getColumnIndex(r13)
            int r13 = r10.getInt(r13)
            java.lang.String r14 = "batsman_balls_faced"
            int r14 = r10.getColumnIndex(r14)
            int r14 = r10.getInt(r14)
            java.lang.String r15 = "batsman_0s"
            int r15 = r10.getColumnIndex(r15)
            int r15 = r10.getInt(r15)
            r4.setDots(r15)
            java.lang.String r15 = "batsman_1s"
            int r15 = r10.getColumnIndex(r15)
            int r15 = r10.getInt(r15)
            r4.setSingles(r15)
            java.lang.String r15 = "batsman_2s"
            int r15 = r10.getColumnIndex(r15)
            int r15 = r10.getInt(r15)
            r4.setDoubles(r15)
            java.lang.String r15 = "batsman_3s"
            int r15 = r10.getColumnIndex(r15)
            int r15 = r10.getInt(r15)
            r4.setThrees(r15)
            r4.setFours(r12)
            r4.setSixs(r13)
            java.lang.String r15 = "match_id"
            int r15 = r10.getColumnIndex(r15)
            int r15 = r10.getInt(r15)
            r16 = r3
            long r2 = (long) r15
            r4.setMatchid(r2)
            r4.setTotalScore(r11)
            java.lang.String r2 = "out_status"
            int r2 = r10.getColumnIndex(r2)
            java.lang.String r2 = r10.getString(r2)
            r4.setOut(r2)
            r4.setBallsfaced(r14)
            int r8 = r8 + 1
            int r9 = r9 + 1
            r4.setSingleMatchRuns(r11)
            r4.setSingleMatchBalls(r14)
            r4.setSingleMatchFours(r12)
            r4.setSingleMatchSixes(r13)
            apps.shehryar.com.cricketscroingappPro.Batsman.BatsmanDataUpdater.setPointsToBatsman(r4)
            r2 = 30
            r3 = 50
            r12 = 1
            if (r11 < r2) goto L_0x00ec
            if (r11 >= r3) goto L_0x00ec
            r4.setThirties(r12)
            goto L_0x00fb
        L_0x00ec:
            r2 = 100
            if (r11 < r3) goto L_0x00f6
            if (r11 >= r2) goto L_0x00f6
            r4.setFifties(r12)
            goto L_0x00fb
        L_0x00f6:
            if (r11 < r2) goto L_0x00fb
            r4.setHundreds(r12)
        L_0x00fb:
            java.lang.String r2 = r4.isOut()
            java.lang.String r3 = "false"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0109
            int r8 = r8 + -1
        L_0x0109:
            java.lang.String r2 = "batsman_score"
            int r2 = r10.getColumnIndex(r2)
            int r2 = r10.getInt(r2)
            if (r2 <= r7) goto L_0x011f
            java.lang.String r2 = "batsman_score"
            int r2 = r10.getColumnIndex(r2)
            int r7 = r10.getInt(r2)
        L_0x011f:
            r10.moveToNext()
            r3 = r16
            r2 = r20
            goto L_0x0041
        L_0x0128:
            r16 = r3
            r10.close()
            int r6 = r6 + 1
            r2 = r20
            goto L_0x0019
        L_0x0133:
            r4.setBest(r7)
            r2 = r19
            r4.setTeam_Name(r2)
            r4.setInnings(r8)
            r4.setMatches(r9)
            java.lang.String r2 = r4.getTeam_Name()     // Catch:{ Exception -> 0x0155 }
            long r2 = r1.getUserAddedTeamId(r2)     // Catch:{ Exception -> 0x0155 }
            r5 = r20
            java.lang.String r2 = r1.getPlayerImage(r5, r2)     // Catch:{ Exception -> 0x0153 }
            r4.setImage(r2)     // Catch:{ Exception -> 0x0153 }
            goto L_0x015c
        L_0x0153:
            r0 = move-exception
            goto L_0x0158
        L_0x0155:
            r0 = move-exception
            r5 = r20
        L_0x0158:
            r2 = r0
            r2.printStackTrace()
        L_0x015c:
            int r2 = r4.getMatches()
            if (r2 <= 0) goto L_0x0171
            int r2 = r4.getTotalScore()
            double r2 = (double) r2
            int r6 = r4.getInnings()
            double r6 = (double) r6
            double r2 = r2 / r6
            r4.setAverage(r2)
            goto L_0x0176
        L_0x0171:
            r2 = 0
            r4.setAverage(r2)
        L_0x0176:
            r4.setName(r5)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: apps.shehryar.com.cricketscroingappPro.DBHelper.getYourBatsmanStats(apps.shehryar.com.cricketscroingappPro.Team.Team, java.lang.String, java.lang.String, java.lang.String):apps.shehryar.com.cricketscroingappPro.Batsman.Batsman");
    }

    /* access modifiers changed from: package-private */
    public ArrayList<Long> getYourBowlerIDs(ArrayList<Long> arrayList, String str) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        String str2 = "Select * from player where player_name=" + Formatter.wrapStringWithDoubleQuotes(Formatter.replaceSingleQuoteWithTwoSingleQuotes(Formatter.replaceForwardSlashWithBackSlash(str))) + " and " + KEY_PLAYER_TYPE + "= 'bowler'";
        ArrayList<Long> arrayList2 = new ArrayList<>();
        try {
            Cursor rawQuery = readableDatabase.rawQuery(str2, (String[]) null);
            rawQuery.moveToFirst();
            while (!rawQuery.isAfterLast()) {
                if (arrayList.contains(Long.valueOf(rawQuery.getLong(rawQuery.getColumnIndex(KEY_TEAM_ID))))) {
                    arrayList2.add(Long.valueOf(rawQuery.getLong(rawQuery.getColumnIndex("id"))));
                }
                rawQuery.moveToNext();
            }
            rawQuery.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList2;
    }

    public Bowler getYourBowlerStats(String str, String str2, ArrayList<Long> arrayList) throws Exception {
        ArrayList<Long> arrayList2;
        try {
            arrayList2 = getYourBowlerIDs(getYourTeamIDs(str, arrayList), str2);
        } catch (Exception e) {
            e.printStackTrace();
            arrayList2 = new ArrayList<>();
        }
        Bowler bowler = new Bowler();
        bowler.setTeamName(str);
        SQLiteDatabase readableDatabase = getReadableDatabase();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < arrayList2.size(); i4++) {
            Cursor rawQuery = readableDatabase.rawQuery("Select * from bowlerscore where player_id=" + arrayList2.get(i4) + "", (String[]) null);
            rawQuery.moveToFirst();
            while (!rawQuery.isAfterLast()) {
                i3++;
                int i5 = rawQuery.getInt(rawQuery.getColumnIndex(KEY_BOWLER_SCORE));
                bowler.setScore(i5);
                bowler.setTotalscore(i5);
                bowler.setSingleMatchRuns(i5);
                int i6 = rawQuery.getInt(rawQuery.getColumnIndex(KEY_BOWELR_WICKETS));
                bowler.setWickets(i6);
                bowler.setSingleMatchWickets(i6);
                BowlerDataUpdater.setThreeFourAndFiveWicektsToTheBowler(bowler, i6);
                int i7 = rawQuery.getInt(rawQuery.getColumnIndex(KEY_BOWLER_OVERS));
                bowler.setBowlerovers(i7);
                bowler.setSingleMatchOvers(i7);
                int i8 = rawQuery.getInt(rawQuery.getColumnIndex(KEY_BOWLER_BALLS));
                bowler.setBalls(i8);
                bowler.setSingleMatchBalls(i8);
                int i9 = rawQuery.getInt(rawQuery.getColumnIndex(KEY_BOWLER_MAIDENS));
                bowler.setMaidens(i9);
                bowler.setSingleMatchMaidens(i9);
                if (rawQuery.getInt(rawQuery.getColumnIndex(KEY_BOWELR_WICKETS)) > i) {
                    i = rawQuery.getInt(rawQuery.getColumnIndex(KEY_BOWELR_WICKETS));
                    i2 = rawQuery.getInt(rawQuery.getColumnIndex(KEY_BOWLER_SCORE));
                }
                rawQuery.moveToNext();
            }
            rawQuery.close();
        }
        bowler.setBest(i + "/" + i2);
        bowler.setTeamName(str);
        bowler.setMatches(i3);
        if (bowler.getWickets() != 0) {
            bowler.setAverage(((double) bowler.getScore()) / ((double) bowler.getWickets()));
        }
        Log.i("edBowler stats", bowler.getBowlerovers() + "." + bowler.getBalls() + " " + bowler.getScore());
        bowler.setName(str2);
        try {
            bowler.setImage(getPlayerImage(str2, getUserAddedTeamId(bowler.getTeamName())));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        BowlerDataUpdater.setPointsToBowler(bowler);
        return bowler;
    }

    /* access modifiers changed from: package-private */
    public double getNetRunRate(String str, ArrayList<Match> arrayList) {
        String str2 = str;
        ArrayList<Match> arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList2.get(i).getYourteam().equals(str2)) {
                Team team1Stats = getTeam1Stats(arrayList2.get(i).getMatchID(), arrayList2.get(i).getTeam_Yours_id());
                Team team1Stats2 = getTeam1Stats(arrayList2.get(i).getMatchID(), arrayList2.get(i).getTeam_opp_id());
                arrayList3.add(team1Stats);
                arrayList4.add(team1Stats2);
            } else if (arrayList2.get(i).getOpponent().equals(str2)) {
                Team team1Stats3 = getTeam1Stats(arrayList2.get(i).getMatchID(), arrayList2.get(i).getTeam_opp_id());
                Team team1Stats4 = getTeam1Stats(arrayList2.get(i).getMatchID(), arrayList2.get(i).getTeam_Yours_id());
                arrayList3.add(team1Stats3);
                arrayList4.add(team1Stats4);
            }
        }
        double d = 0.0d;
        double d2 = 0.0d;
        for (int i2 = 0; i2 < arrayList3.size(); i2++) {
            d += (double) ((Team) arrayList3.get(i2)).getScore();
            if (((Team) arrayList3.get(i2)).getWickets() == 10) {
                d2 += getTotalOvers((Team) arrayList3.get(i2), arrayList2);
            } else {
                d2 += (double) ((Team) arrayList3.get(i2)).getOversPlayed();
                if (((Team) arrayList3.get(i2)).getOverballs() != 6) {
                    d2 += ((double) ((Team) arrayList3.get(i2)).getOverballs()) / 6.0d;
                }
            }
        }
        double d3 = d / d2;
        double d4 = 0.0d;
        double d5 = 0.0d;
        for (int i3 = 0; i3 < arrayList4.size(); i3++) {
            d5 += (double) ((Team) arrayList4.get(i3)).getScore();
            if (((Team) arrayList4.get(i3)).getWickets() == 10) {
                d4 += getTotalOvers((Team) arrayList4.get(i3), arrayList2);
            } else {
                d4 += (double) ((Team) arrayList4.get(i3)).getOversPlayed();
                if (((Team) arrayList3.get(i3)).getOverballs() != 6) {
                    d4 += ((double) ((Team) arrayList4.get(i3)).getOverballs()) / 6.0d;
                }
            }
        }
        return d3 - (d5 / d4);
    }

    private double getTotalOvers(Team team, ArrayList<Match> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getYourteam().equals(team.getName()) || arrayList.get(i).getOpponent().equals(team.getName())) {
                Log.i("match Overs", arrayList.get(i).getOvers() + "");
                return (double) arrayList.get(i).getOvers();
            }
        }
        return 0.0d;
    }

    public Bowler getYourBowlerStats(Team team, String str, String str2, String str3) {
        ArrayList<Long> yourBowlerIDs = getYourBowlerIDs(team.teamAllIDs, str2);
        Bowler bowler = new Bowler();
        bowler.setTeamName(str);
        SQLiteDatabase readableDatabase = getReadableDatabase();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < yourBowlerIDs.size(); i4++) {
            Cursor rawQuery = readableDatabase.rawQuery("Select * from bowlerscore where player_id=" + yourBowlerIDs.get(i4) + "", (String[]) null);
            rawQuery.moveToFirst();
            while (!rawQuery.isAfterLast()) {
                int i5 = rawQuery.getInt(rawQuery.getColumnIndex(KEY_BOWLER_SCORE));
                i3++;
                bowler.setScore(i5);
                bowler.setTotalscore(i5);
                bowler.setSingleMatchRuns(i5);
                int i6 = rawQuery.getInt(rawQuery.getColumnIndex(KEY_BOWELR_WICKETS));
                bowler.setWickets(i6);
                bowler.setSingleMatchWickets(i6);
                int i7 = rawQuery.getInt(rawQuery.getColumnIndex(KEY_BOWLER_OVERS));
                bowler.setBowlerovers(i7);
                bowler.setSingleMatchOvers(i7);
                int i8 = rawQuery.getInt(rawQuery.getColumnIndex(KEY_BOWLER_BALLS));
                bowler.setBalls(i8);
                bowler.setSingleMatchBalls(i8);
                int i9 = rawQuery.getInt(rawQuery.getColumnIndex(KEY_BOWLER_MAIDENS));
                bowler.setMaidens(i9);
                bowler.setSingleMatchMaidens(i9);
                if (i5 == 0 && i7 == 0 && i8 == 0 && i9 == 0) {
                    bowler.setMatches(bowler.getMatches() - 1);
                }
                if (rawQuery.getInt(rawQuery.getColumnIndex(KEY_BOWELR_WICKETS)) > i) {
                    i = rawQuery.getInt(rawQuery.getColumnIndex(KEY_BOWELR_WICKETS));
                    i2 = rawQuery.getInt(rawQuery.getColumnIndex(KEY_BOWLER_SCORE));
                }
                rawQuery.moveToNext();
            }
            rawQuery.close();
        }
        bowler.setBest(i + "/" + i2);
        bowler.setTeamName(str);
        bowler.setMatches(i3);
        if (bowler.getWickets() != 0) {
            bowler.setAverage(((double) bowler.getScore()) / ((double) bowler.getWickets()));
        }
        Log.i("edBowler stats", bowler.getBowlerovers() + "." + bowler.getBalls() + " " + bowler.getScore());
        bowler.setName(str2);
        try {
            bowler.setImage(getPlayerImage(str, getUserAddedTeamId(bowler.getTeamName())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        BowlerDataUpdater.setPointsToBowler(bowler);
        return bowler;
    }

    public long insertTeam(String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TEAM_NAME, str);
        return writableDatabase.insert(TABLE_TEAM, (String) null, contentValues);
    }

    public long insertUserAddedTeam(Team team) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TEAM_NAME, team.getName());
        contentValues.put(KEY_TEAM_SIDE, Integer.valueOf(team.getTeamSide()));
        contentValues.put(KEY_CAPTAIN_ID, Long.valueOf(team.getCaptainId()));
        contentValues.put(KEY_WK_ID, Long.valueOf(team.getKeeperId()));
        return writableDatabase.insert(TABLE_USER_ADDED_TEAMS, (String) null, contentValues);
    }

    public void updateUserAddedTeam(Team team) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TEAM_NAME, team.getName());
        contentValues.put(KEY_TEAM_SIDE, Integer.valueOf(team.getTeamSide()));
        contentValues.put(KEY_CAPTAIN_ID, Long.valueOf(team.getCaptainId()));
        contentValues.put(KEY_WK_ID, Long.valueOf(team.getKeeperId()));
        updateTeamNameInAllTheMatches(team);
        writableDatabase.update(TABLE_USER_ADDED_TEAMS, contentValues, "id=" + team.getTeamID(), (String[]) null);
    }

    private void updateTeamNameInAllTheMatches(Team team) {
        ArrayList<Long> yourTeamIDs = getYourTeamIDs(team.getOldName(), (ArrayList<Long>) null);
        for (int i = 0; i < yourTeamIDs.size(); i++) {
            updateTeamName(yourTeamIDs.get(i).longValue(), team.getName());
        }
    }

    /* access modifiers changed from: package-private */
    public ArrayList<Team> getUserAddedTeams() {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        ArrayList<Team> arrayList = new ArrayList<>();
        Cursor rawQuery = readableDatabase.rawQuery("SELECT * FROM user_added_teams", (String[]) null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            Team team = new Team();
            team.setTeamID((long) rawQuery.getInt(rawQuery.getColumnIndex("id")));
            team.setName(rawQuery.getString(rawQuery.getColumnIndex(KEY_TEAM_NAME)));
            team.setTeamSide(rawQuery.getInt(rawQuery.getColumnIndex(KEY_TEAM_SIDE)));
            team.setCaptainId((long) rawQuery.getInt(rawQuery.getColumnIndex(KEY_CAPTAIN_ID)));
            team.setKeeperId((long) rawQuery.getInt(rawQuery.getColumnIndex(KEY_WK_ID)));
            arrayList.add(team);
            rawQuery.moveToNext();
        }
        rawQuery.close();
        return arrayList;
    }

    public long getUserAddedTeamId(String str) throws Exception {
        Cursor rawQuery = getReadableDatabase().rawQuery("Select id from user_added_teams where team_name='" + str + "'", (String[]) null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            if (!rawQuery.isAfterLast()) {
                return rawQuery.getLong(rawQuery.getColumnIndex("id"));
            }
        }
        rawQuery.close();
        return -1;
    }

    public String getPlayerImage(String str, long j) throws Exception {
        Cursor rawQuery = getReadableDatabase().rawQuery("Select player_image from user_added_players where player_name = " + Formatter.wrapStringWithDoubleQuotes(str) + " and " + KEY_TEAM_ID + "=" + j, (String[]) null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            if (!rawQuery.isAfterLast()) {
                return rawQuery.getString(rawQuery.getColumnIndex("player_image"));
            }
        }
        rawQuery.close();
        return null;
    }

    public long insertUserAddedPlayer(Player player) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("player_name", player.getName());
        contentValues.put(KEY_TEAM_ID, Long.valueOf(player.getTeamId()));
        contentValues.put("player_image", player.getImage());
        contentValues.put(KEY_IS_BATSMAN, Boolean.valueOf(player.isBatsman()));
        contentValues.put(KEY_IS_BOWLER, Boolean.valueOf(player.isBowler()));
        contentValues.put(KEY_IS_KEEPER, Boolean.valueOf(player.isWicketKeeper()));
        contentValues.put(KEY_IS_ALROUNDER, Boolean.valueOf(player.isAlrounder()));
        return writableDatabase.insert(TABLE_USER_ADDED_PLAYERS, (String) null, contentValues);
    }

    public void updateUserAddedPlayer(Player player) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("player_name", player.getName());
        contentValues.put(KEY_TEAM_ID, Long.valueOf(player.getTeamId()));
        contentValues.put("player_image", player.getImage());
        contentValues.put(KEY_IS_BATSMAN, Boolean.valueOf(player.isBatsman()));
        contentValues.put(KEY_IS_BOWLER, Boolean.valueOf(player.isBowler()));
        contentValues.put(KEY_IS_KEEPER, Boolean.valueOf(player.isWicketKeeper()));
        contentValues.put(KEY_IS_ALROUNDER, Boolean.valueOf(player.isAlrounder()));
        updateUserAddedPlayerNameInAllTheMatches(player);
        writableDatabase.update(TABLE_USER_ADDED_PLAYERS, contentValues, "id=" + player.getPlayerId(), (String[]) null);
    }

    private void updateUserAddedPlayerNameInAllTheMatches(Player player) {
        ArrayList<Long> arrayList;
        ArrayList<Long> yourTeamIDs = getYourTeamIDs(player.getTeamName(), (ArrayList<Long>) null);
        ArrayList<Long> yourBatsmansIDs = getYourBatsmansIDs(yourTeamIDs, player.getOldName());
        ArrayList<Long> yourBowlerIDs = getYourBowlerIDs(yourTeamIDs, player.getOldName());
        try {
            arrayList = getFallOfWicketsIds(yourTeamIDs, player.getOldName());
        } catch (Exception e) {
            e.printStackTrace();
            arrayList = new ArrayList<>();
        }
        for (int i = 0; i < yourBatsmansIDs.size(); i++) {
            updatePlayerName(yourBatsmansIDs.get(i).longValue(), player.getName());
        }
        for (int i2 = 0; i2 < yourBowlerIDs.size(); i2++) {
            updatePlayerName(yourBowlerIDs.get(i2).longValue(), player.getName());
        }
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            try {
                updateNameInFallOfWickets(arrayList.get(i3), player.getName());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void updateNameInFallOfWickets(Long l, String str) throws Exception {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_WICKET_BATSMAN, str);
        writableDatabase.update(TABLE_FALL_OF_WICKETS, contentValues, "id=" + l, (String[]) null);
    }

    private ArrayList<Long> getFallOfWicketsIds(ArrayList<Long> arrayList, String str) throws Exception {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        String str2 = "Select * from fallofwickets where wicket_batsman=" + Formatter.wrapStringWithDoubleQuotes(str);
        ArrayList<Long> arrayList2 = new ArrayList<>();
        try {
            Cursor rawQuery = readableDatabase.rawQuery(str2, (String[]) null);
            rawQuery.moveToFirst();
            while (!rawQuery.isAfterLast()) {
                if (arrayList.contains(Long.valueOf(rawQuery.getLong(rawQuery.getColumnIndex(KEY_TEAM_ID))))) {
                    arrayList2.add(Long.valueOf(rawQuery.getLong(rawQuery.getColumnIndex("id"))));
                }
                rawQuery.moveToNext();
            }
            rawQuery.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList2;
    }

    public void deleteUserAddedPlayer(Player player) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete(TABLE_USER_ADDED_PLAYERS, "team_id=" + player.getTeamId() + " and " + "id" + "=" + player.getPlayerId(), (String[]) null);
    }

    public void deleteUserAddedTeam(Team team) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete(TABLE_USER_ADDED_TEAMS, "id=" + team.getTeamID(), (String[]) null);
    }

    public String getPlayerImage(long j) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        String str = null;
        Cursor rawQuery = readableDatabase.rawQuery("Select player_image from user_added_players where id =" + j, (String[]) null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            str = rawQuery.getString(rawQuery.getColumnIndex("player_image"));
            rawQuery.moveToNext();
        }
        rawQuery.close();
        return str;
    }

    public long insertMatch(Match match) {
        SQLiteDatabase sQLiteDatabase;
        try {
            sQLiteDatabase = getWritableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
            sQLiteDatabase = this.currentDBInstance;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_VENUE, match.getVenue());
        contentValues.put(KEY_TEAM_YOURS_ID, Long.valueOf(match.getTeam_Yours_id()));
        contentValues.put(KEY_TEAM_OPP_ID, Long.valueOf(match.getTeam_opp_id()));
        contentValues.put(KEY_TOURNAMENT_STAGE, match.getTournamentStage());
        if (match.isTestMatch) {
            contentValues.put(KEY_TEAM_YOURS_ID_2, Long.valueOf(match.getTeam3().getTeamID()));
            contentValues.put(KEY_TEAM_OPP_ID_2, Long.valueOf(match.getTeam4().getTeamID()));
        }
        contentValues.put(KEY_IS_TEST_MATCH, Integer.valueOf(match.isTestMatch()));
        contentValues.put(KEY_IS_FOLLOWED_ON, Integer.valueOf(match.isFollowedOn()));
        contentValues.put(KEY_RESULT, match.getResult());
        contentValues.put("date", match.getDate());
        contentValues.put(KEY_MATCH_OVERS, Integer.valueOf(match.getOvers()));
        contentValues.put(KEY_TOURNAMENT_NAME, match.getTournament());
        contentValues.put(KEY_TOSS_RESULT, match.getTossResult());
        contentValues.put(KEY_MATCH_TIME, match.getTime());
        match.setMatchID(sQLiteDatabase.insert(TABLE_MATCH, (String) null, contentValues));
        return match.getMatchID();
    }

    public long insertMatchSettings(Match match) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_MATCH_ID, Long.valueOf(match.getMatchID()));
        contentValues.put(KEY_MATCH_OVER_BALLS, Integer.valueOf(match.getPerOverBalls()));
        contentValues.put(KEY_MATCH_WICKETS, Integer.valueOf(match.getPerMatchWickets()));
        if (match.isNoRunForNo()) {
            contentValues.put(KEY_NO_RUN_FOR_NO, 1);
        } else {
            contentValues.put(KEY_NO_RUN_FOR_NO, 0);
        }
        if (match.isNoRunForWide()) {
            contentValues.put(KEY_NO_RUN_FOR_WIDE, 1);
        } else {
            contentValues.put(KEY_NO_RUN_FOR_WIDE, 0);
        }
        if (match.isSelectMOMmanually()) {
            contentValues.put(KEY_SELECT_MOM_MANUALLY, 1);
        } else {
            contentValues.put(KEY_SELECT_MOM_MANUALLY, 0);
        }
        if (match.isMaxBallsFeature()) {
            contentValues.put(KEY_MAX_BALLS, 1);
        } else {
            contentValues.put(KEY_MAX_BALLS, 0);
        }
        return writableDatabase.insert(TABLE_MATCH_SETTING, (String) null, contentValues);
    }

    public void deleteMatchSettings(Match match) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete(TABLE_MATCH_SETTING, "match_id=" + match.getMatchID(), (String[]) null);
    }

    public void insertOvers(long j, long j2, ArrayList<Over> arrayList) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        deleteOvers(j, j2);
        Iterator<Over> it = arrayList.iterator();
        while (it.hasNext()) {
            Over next = it.next();
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_MATCH_ID, Long.valueOf(j));
            contentValues.put(KEY_TEAM_ID, Long.valueOf(j2));
            contentValues.put(KEY_OVER_BOWLER, next.getBowler());
            contentValues.put(KEY_OVER_NO, Integer.valueOf(next.getOver_no()));
            contentValues.put(KEY_OVER_SCORE, Integer.valueOf(next.getOverscore()));
            contentValues.put(KEY_OVER_WICKETS, Integer.valueOf(next.getWickets()));
            contentValues.put(KEY_OVER_PER_BALL_SCORE, Formatter.convertPerBallScoreToString(next.getPerballScore()));
            writableDatabase.insert(TABLE_OVERS, (String) null, contentValues);
        }
    }

    public ArrayList<Over> getOvers(long j, long j2) {
        ArrayList<Over> arrayList = new ArrayList<>();
        Cursor rawQuery = getWritableDatabase().rawQuery("SELECT * FROM overs WHERE match_id=" + j + " AND " + KEY_TEAM_ID + "=" + j2, (String[]) null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            Over over = new Over();
            over.setOver_no(rawQuery.getInt(rawQuery.getColumnIndex(KEY_OVER_NO)));
            over.setWickets(rawQuery.getInt(rawQuery.getColumnIndex(KEY_OVER_WICKETS)));
            over.setOverscore(rawQuery.getInt(rawQuery.getColumnIndex(KEY_OVER_SCORE)));
            over.setBowler(rawQuery.getString(rawQuery.getColumnIndex(KEY_OVER_BOWLER)));
            over.setPerballScore(Formatter.convertStringToPerBallScore(rawQuery.getString(rawQuery.getColumnIndex(KEY_OVER_PER_BALL_SCORE))));
            arrayList.add(over);
            rawQuery.moveToNext();
        }
        rawQuery.close();
        return arrayList;
    }

    public void deleteOvers(long j, long j2) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete(TABLE_OVERS, "match_id=" + j + " AND " + KEY_TEAM_ID + "=" + j2, (String[]) null);
    }

    /* access modifiers changed from: package-private */
    public long insertPartnership(Partenership partenership) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_MATCH_ID, Long.valueOf(partenership.getMatch().getMatchID()));
        contentValues.put(KEY_TEAM_ID, Long.valueOf(partenership.getTeam().getTeamID()));
        contentValues.put(KEY_TOTAL_PART, Integer.valueOf(partenership.getTotalPartenership()));
        contentValues.put(KEY_TOTAL_PART_BALLS, Integer.valueOf(partenership.getTotalPartenershipBalls()));
        contentValues.put(KEY_PART_BATSMAN1_NAME, partenership.getBatsman1().getName());
        contentValues.put(KEY_PART_BATSMAN1_SCORE, Integer.valueOf(partenership.getBatsman1().getScore()));
        contentValues.put(KEY_PART_BATSMAN1_BALLS, Integer.valueOf(partenership.getBatsman1().getBallsfaced()));
        contentValues.put(KEY_PART_BATSMAN2_NAME, partenership.getBatsman2().getName());
        contentValues.put(KEY_PART_BATSMAN2_SCORE, Integer.valueOf(partenership.getBatsman2().getScore()));
        contentValues.put(KEY_PART_BATSMAN2_BALLS, Integer.valueOf(partenership.getBatsman2().getBallsfaced()));
        return writableDatabase.insert(TABLE_PARTNERSHIPS, (String) null, contentValues);
    }

    public void updatePartnership(Partenership partenership) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_MATCH_ID, Long.valueOf(partenership.getMatch().getMatchID()));
        contentValues.put(KEY_TEAM_ID, Long.valueOf(partenership.getTeam().getTeamID()));
        contentValues.put(KEY_TOTAL_PART, Integer.valueOf(partenership.getTotalPartenership()));
        contentValues.put(KEY_TOTAL_PART_BALLS, Integer.valueOf(partenership.getTotalPartenershipBalls()));
        contentValues.put(KEY_PART_BATSMAN1_NAME, partenership.getBatsman1().getName());
        contentValues.put(KEY_PART_BATSMAN1_SCORE, Integer.valueOf(partenership.getBatsman1().getScore()));
        contentValues.put(KEY_PART_BATSMAN1_BALLS, Integer.valueOf(partenership.getBatsman1().getBallsfaced()));
        contentValues.put(KEY_PART_BATSMAN2_NAME, partenership.getBatsman2().getName());
        contentValues.put(KEY_PART_BATSMAN2_SCORE, Integer.valueOf(partenership.getBatsman2().getScore()));
        contentValues.put(KEY_PART_BATSMAN2_BALLS, Integer.valueOf(partenership.getBatsman2().getBallsfaced()));
        writableDatabase.update(TABLE_MATCH, contentValues, "id=" + partenership.getId(), (String[]) null);
    }

    public ArrayList<Partenership> getAllPartenerships(Match match, Team team) {
        ArrayList<Partenership> arrayList = new ArrayList<>();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery("Select * from partnerships where match_id=" + match.getMatchID() + " and " + KEY_TEAM_ID + "=" + team.getTeamID(), (String[]) null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            Partenership partenership = new Partenership();
            partenership.setId(rawQuery.getLong(rawQuery.getColumnIndex("id")));
            partenership.setMatch(match);
            partenership.setTeam(team);
            partenership.setTotalPartenership(rawQuery.getInt(rawQuery.getColumnIndex(KEY_TOTAL_PART)));
            partenership.setTotalPartenershipBalls(rawQuery.getInt(rawQuery.getColumnIndex(KEY_TOTAL_PART_BALLS)));
            Batsman batsman = new Batsman();
            batsman.setName(rawQuery.getString(rawQuery.getColumnIndex(KEY_PART_BATSMAN1_NAME)));
            batsman.setScore(rawQuery.getInt(rawQuery.getColumnIndex(KEY_PART_BATSMAN1_SCORE)));
            batsman.setBallsfaced(rawQuery.getInt(rawQuery.getColumnIndex(KEY_PART_BATSMAN1_BALLS)));
            partenership.setBatsman1(batsman);
            Batsman batsman2 = new Batsman();
            batsman2.setName(rawQuery.getString(rawQuery.getColumnIndex(KEY_PART_BATSMAN2_NAME)));
            batsman2.setScore(rawQuery.getInt(rawQuery.getColumnIndex(KEY_PART_BATSMAN2_SCORE)));
            batsman2.setBallsfaced(rawQuery.getInt(rawQuery.getColumnIndex(KEY_PART_BATSMAN2_BALLS)));
            partenership.setBatsman2(batsman2);
            arrayList.add(partenership);
            rawQuery.moveToNext();
        }
        rawQuery.close();
        return arrayList;
    }

    public void deletePartenership(Partenership partenership) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete(TABLE_PARTNERSHIPS, "id=" + partenership.getId(), (String[]) null);
    }

    public long insertPlayer(String str, long j, String str2) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("player_name", str);
        contentValues.put(KEY_TEAM_ID, Long.valueOf(j));
        contentValues.put(KEY_PLAYER_TYPE, str2);
        return writableDatabase.insert(TABLE_PLAYERS, (String) null, contentValues);
    }

    public long insertPlayerProfile(Player player) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_PLAYER_ID, Long.valueOf(player.getPlayerId()));
        contentValues.put(KEY_IS_BATSMAN, Boolean.valueOf(player.isBatsman()));
        contentValues.put(KEY_IS_BOWLER, Boolean.valueOf(player.isBowler()));
        contentValues.put(KEY_IS_ALROUNDER, Boolean.valueOf(player.isAlrounder()));
        contentValues.put(KEY_IS_KEEPER, Boolean.valueOf(player.isWicketKeeper()));
        contentValues.put(KEY_IS_CAPTAIN, Boolean.valueOf(player.isCaptain()));
        contentValues.put(KEY_IS_VICE_CAPTAIN, Boolean.valueOf(player.isViceCaptain()));
        return writableDatabase.insert(TABLE_PLAYERS_PROFILES, (String) null, contentValues);
    }

    public void updatePlayerProfile(Player player) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_IS_BATSMAN, Boolean.valueOf(player.isBatsman()));
        contentValues.put(KEY_IS_BOWLER, Boolean.valueOf(player.isBowler()));
        contentValues.put(KEY_IS_ALROUNDER, Boolean.valueOf(player.isAlrounder()));
        contentValues.put(KEY_IS_KEEPER, Boolean.valueOf(player.isWicketKeeper()));
        contentValues.put(KEY_IS_CAPTAIN, Boolean.valueOf(player.isCaptain()));
        contentValues.put(KEY_IS_VICE_CAPTAIN, Boolean.valueOf(player.isViceCaptain()));
        writableDatabase.update(TABLE_PLAYERS_PROFILES, contentValues, "player_id=" + player.getPlayerId(), (String[]) null);
    }

    public void deletePlayerProfile(Player player) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete(TABLE_PLAYERS_PROFILES, "player_id=" + player.getPlayerId(), (String[]) null);
    }

    /* access modifiers changed from: package-private */
    public int UpdatePlayer(long j, long j2, String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("player_name", str);
        return writableDatabase.update(TABLE_PLAYERS, contentValues, "team_id=" + j + " and " + "id" + "=" + j2, (String[]) null);
    }

    /* access modifiers changed from: package-private */
    public int updatePlayerName(long j, String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("player_name", str);
        return writableDatabase.update(TABLE_PLAYERS, contentValues, "id=" + j, (String[]) null);
    }

    /* access modifiers changed from: package-private */
    public int updateTeamName(long j, String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TEAM_NAME, str);
        return writableDatabase.update(TABLE_TEAM, contentValues, "id=" + j, (String[]) null);
    }

    /* access modifiers changed from: package-private */
    public int DeletePlayer(long j, long j2) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        return writableDatabase.delete(TABLE_PLAYERS, "team_id=" + j + " and " + "id" + "=" + j2, (String[]) null);
    }

    public long insertTeamScore(long j, long j2, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TEAM_ID, Long.valueOf(j));
        contentValues.put(KEY_MATCH_ID, Long.valueOf(j2));
        contentValues.put(KEY_TEAM_SCORE, Integer.valueOf(i));
        contentValues.put(KEY_TEAM_WICKETS, Integer.valueOf(i2));
        contentValues.put(KEY_TEAM_WIDES, Integer.valueOf(i3));
        contentValues.put(KEY_TEAM_NOS, Integer.valueOf(i4));
        contentValues.put(KEY_TEAM_EXTRAS, Integer.valueOf(i6));
        contentValues.put(KEY_TEAM_OVERS_PLAYED, Integer.valueOf(i5));
        contentValues.put(KEY_TEAM_OVER_BALLS, Integer.valueOf(i7));
        return writableDatabase.insert(TABLE_TEAM_SCORE, (String) null, contentValues);
    }

    public long insertBatsmanScore(long j, long j2, long j3, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TEAM_ID, Long.valueOf(j));
        contentValues.put(KEY_MATCH_ID, Long.valueOf(j2));
        contentValues.put(KEY_PLAYER_ID, Long.valueOf(j3));
        contentValues.put(KEY_BATSMAN_SCORE, Integer.valueOf(i));
        contentValues.put(KEY_BATSMAN_BALLS, Integer.valueOf(i2));
        contentValues.put(KEY_BATSMAN_0S, Integer.valueOf(i3));
        contentValues.put(KEY_BATSMAN_1S, Integer.valueOf(i4));
        contentValues.put(KEY_BATSMAN_2S, Integer.valueOf(i5));
        contentValues.put(KEY_BATSMAN_3S, Integer.valueOf(i6));
        contentValues.put(KEY_BATSMAN_4S, Integer.valueOf(i7));
        contentValues.put(KEY_OUTSTATUS, str);
        contentValues.put(KEY_BATSMAN_6S, Integer.valueOf(i8));
        return writableDatabase.insert(TABLE_BATSMAN_SCORE, (String) null, contentValues);
    }

    /* access modifiers changed from: package-private */
    public void deleteBatsmanScore(long j, long j2, long j3) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete(TABLE_BATSMAN_SCORE, "match_id=" + j + " and " + KEY_TEAM_ID + "=" + j2 + " and " + KEY_PLAYER_ID + "=" + j3, (String[]) null);
    }

    /* access modifiers changed from: package-private */
    public long insertBowlerScore(long j, long j2, long j3, int i, int i2, int i3, int i4, int i5) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TEAM_ID, Long.valueOf(j));
        contentValues.put(KEY_MATCH_ID, Long.valueOf(j2));
        contentValues.put(KEY_PLAYER_ID, Long.valueOf(j3));
        contentValues.put(KEY_BOWLER_SCORE, Integer.valueOf(i));
        contentValues.put(KEY_BOWELR_WICKETS, Integer.valueOf(i2));
        contentValues.put(KEY_BOWLER_OVERS, Integer.valueOf(i4));
        contentValues.put(KEY_BOWLER_MAIDENS, Integer.valueOf(i3));
        contentValues.put(KEY_BOWLER_BALLS, Integer.valueOf(i5));
        return writableDatabase.insert(TABLE_BOWLER_SCORE, (String) null, contentValues);
    }

    /* access modifiers changed from: package-private */
    public int DeleteBowlerScore(long j, long j2, long j3) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        return writableDatabase.delete(TABLE_BOWLER_SCORE, "match_id=" + j + " and " + KEY_TEAM_ID + "=" + j2 + " and " + KEY_PLAYER_ID + "=" + j3, (String[]) null);
    }

    /* access modifiers changed from: package-private */
    public int UpdateTeamScore(long j, long j2, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TEAM_ID, Long.valueOf(j));
        contentValues.put(KEY_MATCH_ID, Long.valueOf(j2));
        contentValues.put(KEY_TEAM_SCORE, Integer.valueOf(i));
        contentValues.put(KEY_TEAM_WICKETS, Integer.valueOf(i2));
        contentValues.put(KEY_TEAM_WIDES, Integer.valueOf(i3));
        contentValues.put(KEY_TEAM_NOS, Integer.valueOf(i4));
        contentValues.put(KEY_TEAM_EXTRAS, Integer.valueOf(i6));
        contentValues.put(KEY_TEAM_OVERS_PLAYED, Integer.valueOf(i5));
        contentValues.put(KEY_TEAM_OVER_BALLS, Integer.valueOf(i7));
        return writableDatabase.update(TABLE_TEAM_SCORE, contentValues, "team_id=" + j + " and " + KEY_MATCH_ID + "=" + j2, (String[]) null);
    }

    public ArrayList<Batsman_Details> getBatsmanDetails(String str, String str2, ArrayList<Long> arrayList) throws Exception {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        ArrayList<Long> yourBatsmansIDs = getYourBatsmansIDs(getYourTeamIDs(str, arrayList), str2);
        ArrayList<Batsman_Details> arrayList2 = new ArrayList<>();
        for (int i = 0; i < yourBatsmansIDs.size(); i++) {
            Batsman_Details batsman_Details = new Batsman_Details();
            batsman_Details.setTeamName(str);
            Cursor rawQuery = readableDatabase.rawQuery("Select * from batsmanscores where player_id=" + yourBatsmansIDs.get(i) + "", (String[]) null);
            rawQuery.moveToFirst();
            while (!rawQuery.isAfterLast()) {
                batsman_Details.setScore(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BATSMAN_SCORE)));
                batsman_Details.setBalls(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BATSMAN_BALLS)));
                batsman_Details.setDots(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BATSMAN_0S)));
                batsman_Details.setSingles(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BATSMAN_1S)));
                batsman_Details.setDoubles(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BATSMAN_2S)));
                batsman_Details.setTriples(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BATSMAN_3S)));
                batsman_Details.setFours(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BATSMAN_4S)));
                batsman_Details.setSixes(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BATSMAN_6S)));
                batsman_Details.setTeamid(rawQuery.getLong(rawQuery.getColumnIndex(KEY_TEAM_ID)));
                batsman_Details.setIsout(rawQuery.getString(rawQuery.getColumnIndex(KEY_OUTSTATUS)));
                int i2 = rawQuery.getInt(rawQuery.getColumnIndex(KEY_BATSMAN_SCORE));
                if (i2 >= 30 && i2 < 50) {
                    batsman_Details.setThirties(1);
                } else if (i2 >= 50 && i2 < 100) {
                    batsman_Details.setFifties(1);
                } else if (i2 >= 100) {
                    batsman_Details.setHundreds(1);
                }
                Cursor rawQuery2 = readableDatabase.rawQuery("Select * from match where id=" + rawQuery.getLong(rawQuery.getColumnIndex(KEY_MATCH_ID)) + "", (String[]) null);
                rawQuery2.moveToFirst();
                while (!rawQuery2.isAfterLast()) {
                    if (rawQuery2.getLong(rawQuery2.getColumnIndex(KEY_TEAM_YOURS_ID)) == batsman_Details.getTeamid()) {
                        batsman_Details.setTeamoppid(rawQuery2.getLong(rawQuery2.getColumnIndex(KEY_TEAM_OPP_ID)));
                    } else if (rawQuery2.getLong(rawQuery2.getColumnIndex(KEY_TEAM_OPP_ID)) == batsman_Details.getTeamid()) {
                        batsman_Details.setTeamoppid(rawQuery2.getLong(rawQuery2.getColumnIndex(KEY_TEAM_YOURS_ID)));
                    } else if (rawQuery2.getLong(rawQuery2.getColumnIndex(KEY_TEAM_YOURS_ID_2)) == batsman_Details.getTeamid()) {
                        batsman_Details.setTeamoppid(rawQuery2.getLong(rawQuery2.getColumnIndex(KEY_TEAM_OPP_ID_2)));
                    } else if (rawQuery2.getLong(rawQuery2.getColumnIndex(KEY_TEAM_OPP_ID_2)) == batsman_Details.getTeamid()) {
                        batsman_Details.setTeamoppid(rawQuery2.getLong(rawQuery2.getColumnIndex(KEY_TEAM_YOURS_ID_2)));
                    }
                    batsman_Details.setDate(rawQuery2.getString(rawQuery2.getColumnIndex("date")));
                    batsman_Details.setVenue(rawQuery2.getString(rawQuery2.getColumnIndex(KEY_VENUE)));
                    rawQuery2.close();
                    Cursor rawQuery3 = readableDatabase.rawQuery("Select * from team where id=" + batsman_Details.getTeamoppid() + "", (String[]) null);
                    rawQuery3.moveToFirst();
                    while (!rawQuery3.isAfterLast()) {
                        batsman_Details.setTeamoppname(rawQuery3.getString(rawQuery3.getColumnIndex(KEY_TEAM_NAME)));
                        rawQuery3.moveToNext();
                    }
                    rawQuery3.close();
                    rawQuery2.moveToNext();
                }
                rawQuery.close();
                try {
                    batsman_Details.setImage(getPlayerImage(str2, getUserAddedTeamId(batsman_Details.getTeamName())));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!(batsman_Details.getVenue() == null || batsman_Details.getDate() == null)) {
                    arrayList2.add(batsman_Details);
                }
                rawQuery.close();
                rawQuery.moveToNext();
            }
        }
        return arrayList2;
    }

    public ArrayList<Bowler_Details> getBowlerDetails(String str, String str2, ArrayList<Long> arrayList) throws Exception, CursorIndexOutOfBoundsException {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        ArrayList<Long> yourBowlerIDs = getYourBowlerIDs(getYourTeamIDs(str, arrayList), str2);
        ArrayList<Bowler_Details> arrayList2 = new ArrayList<>();
        for (int i = 0; i < yourBowlerIDs.size(); i++) {
            Bowler_Details bowler_Details = new Bowler_Details();
            bowler_Details.setTeamName(str);
            Cursor rawQuery = readableDatabase.rawQuery("Select * from bowlerscore where player_id=" + yourBowlerIDs.get(i) + "", (String[]) null);
            rawQuery.moveToFirst();
            while (!rawQuery.isAfterLast()) {
                bowler_Details.setScore(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BOWLER_SCORE)));
                bowler_Details.setWickets(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BOWELR_WICKETS)));
                bowler_Details.setYourteamid(rawQuery.getLong(rawQuery.getColumnIndex(KEY_TEAM_ID)));
                bowler_Details.setOver(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BOWLER_OVERS)));
                bowler_Details.setBall(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BOWLER_BALLS)));
                Cursor rawQuery2 = readableDatabase.rawQuery("Select * from match where id=" + rawQuery.getLong(rawQuery.getColumnIndex(KEY_MATCH_ID)) + "", (String[]) null);
                rawQuery2.moveToFirst();
                while (!rawQuery2.isAfterLast()) {
                    if (rawQuery2.getLong(rawQuery2.getColumnIndex(KEY_TEAM_YOURS_ID)) == bowler_Details.getYourteamid()) {
                        bowler_Details.setOppteamid(rawQuery2.getLong(rawQuery2.getColumnIndex(KEY_TEAM_OPP_ID)));
                    } else if (rawQuery2.getLong(rawQuery2.getColumnIndex(KEY_TEAM_OPP_ID)) == bowler_Details.getYourteamid()) {
                        bowler_Details.setOppteamid(rawQuery2.getLong(rawQuery2.getColumnIndex(KEY_TEAM_YOURS_ID)));
                    } else if (rawQuery2.getLong(rawQuery2.getColumnIndex(KEY_TEAM_YOURS_ID_2)) == bowler_Details.getYourteamid()) {
                        bowler_Details.setOppteamid(rawQuery2.getLong(rawQuery2.getColumnIndex(KEY_TEAM_OPP_ID_2)));
                    } else if (rawQuery2.getLong(rawQuery2.getColumnIndex(KEY_TEAM_OPP_ID_2)) == bowler_Details.getYourteamid()) {
                        bowler_Details.setOppteamid(rawQuery2.getLong(rawQuery2.getColumnIndex(KEY_TEAM_YOURS_ID_2)));
                    }
                    bowler_Details.setDate(rawQuery2.getString(rawQuery2.getColumnIndex("date")));
                    bowler_Details.setVenue(rawQuery2.getString(rawQuery2.getColumnIndex(KEY_VENUE)));
                    rawQuery2.moveToNext();
                }
                rawQuery2.close();
                Cursor rawQuery3 = readableDatabase.rawQuery("Select * from team where id=" + bowler_Details.getOppteamid() + "", (String[]) null);
                rawQuery3.moveToFirst();
                while (!rawQuery3.isAfterLast()) {
                    bowler_Details.setOppname(rawQuery3.getString(rawQuery3.getColumnIndex(KEY_TEAM_NAME)));
                    rawQuery3.moveToNext();
                }
                rawQuery3.close();
                rawQuery.moveToNext();
            }
            try {
                bowler_Details.setImage(getPlayerImage(bowler_Details.getName(), getUserAddedTeamId(bowler_Details.getTeamName())));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!(bowler_Details.getVenue() == null || bowler_Details.getDate() == null)) {
                arrayList2.add(bowler_Details);
            }
            rawQuery.close();
        }
        return arrayList2;
    }

    public int UpdateBatsmanScore(long j, long j2, Batsman batsman) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TEAM_ID, Long.valueOf(j));
        contentValues.put(KEY_MATCH_ID, Long.valueOf(j2));
        contentValues.put(KEY_PLAYER_ID, Long.valueOf(batsman.getBatsmanID()));
        contentValues.put(KEY_BATSMAN_SCORE, Integer.valueOf(batsman.getTotalScore()));
        contentValues.put(KEY_BATSMAN_BALLS, Integer.valueOf(batsman.getBallsfaced()));
        contentValues.put(KEY_BATSMAN_0S, Integer.valueOf(batsman.getDots()));
        contentValues.put(KEY_BATSMAN_1S, Integer.valueOf(batsman.getSingles()));
        contentValues.put(KEY_BATSMAN_2S, Integer.valueOf(batsman.getDoubles()));
        contentValues.put(KEY_BATSMAN_3S, Integer.valueOf(batsman.getThrees()));
        contentValues.put(KEY_BATSMAN_4S, Integer.valueOf(batsman.getFours()));
        contentValues.put(KEY_OUTSTATUS, batsman.isOut());
        contentValues.put(KEY_BATSMAN_6S, Integer.valueOf(batsman.getSixs()));
        contentValues.put(KEY_BATSMAN_WICKET_TYPE, batsman.getOutType());
        return writableDatabase.update(TABLE_BATSMAN_SCORE, contentValues, "team_id=" + j + " and " + KEY_MATCH_ID + "=" + j2 + " and " + KEY_PLAYER_ID + "=" + batsman.getBatsmanID(), (String[]) null);
    }

    public void DeleteMatch(Match match) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        readableDatabase.delete(TABLE_TEAM, "id=" + match.getTeam_Yours_id(), (String[]) null);
        readableDatabase.delete(TABLE_TEAM, "id=" + match.getTeam_opp_id(), (String[]) null);
        readableDatabase.delete(TABLE_MATCH, "id=" + match.getMatchID(), (String[]) null);
        readableDatabase.delete(TABLE_TEAM_SCORE, "match_id=" + match.getMatchID() + " AND " + KEY_TEAM_ID + "=" + match.getTeam_Yours_id(), (String[]) null);
        readableDatabase.delete(TABLE_TEAM_SCORE, "match_id=" + match.getMatchID() + " AND " + KEY_TEAM_ID + "=" + match.getTeam_opp_id(), (String[]) null);
        readableDatabase.delete(TABLE_BATSMAN_SCORE, "match_id=" + match.getMatchID() + " AND " + KEY_TEAM_ID + "=" + match.getTeam_Yours_id(), (String[]) null);
        readableDatabase.delete(TABLE_BATSMAN_SCORE, "match_id=" + match.getMatchID() + " AND " + KEY_TEAM_ID + "=" + match.getTeam_opp_id(), (String[]) null);
        readableDatabase.delete(TABLE_BOWLER_SCORE, "match_id=" + match.getMatchID() + " AND " + KEY_TEAM_ID + "=" + match.getTeam_Yours_id(), (String[]) null);
        readableDatabase.delete(TABLE_BOWLER_SCORE, "match_id=" + match.getMatchID() + " AND " + KEY_TEAM_ID + "=" + match.getTeam_opp_id(), (String[]) null);
        readableDatabase.delete(TABLE_FALL_OF_WICKETS, "match_id=" + match.getMatchID() + " AND " + KEY_TEAM_ID + "=" + match.getTeam_Yours_id(), (String[]) null);
        readableDatabase.delete(TABLE_FALL_OF_WICKETS, "match_id=" + match.getMatchID() + " AND " + KEY_TEAM_ID + "=" + match.getTeam_opp_id(), (String[]) null);
        SharedPrefsHelper.deleteMatchTossFromSharedPrefs(this.context, match);
        SharedPrefsHelper.deleteMatchTime(match, this.context);
        deleteMatchSettings(match);
        MyToast.showToast(this.context, "Match deleted successfully");
    }

    /* access modifiers changed from: package-private */
    public int UpdateBowlerScore(long j, long j2, long j3, int i, int i2, int i3, int i4, int i5) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_BOWLER_SCORE, Integer.valueOf(i));
        contentValues.put(KEY_BOWELR_WICKETS, Integer.valueOf(i2));
        contentValues.put(KEY_BOWLER_MAIDENS, Integer.valueOf(i4));
        contentValues.put(KEY_BOWLER_OVERS, Integer.valueOf(i3));
        contentValues.put(KEY_BOWLER_BALLS, Integer.valueOf(i5));
        return writableDatabase.update(TABLE_BOWLER_SCORE, contentValues, "team_id=" + j + " and " + KEY_MATCH_ID + "=" + j2 + " and " + KEY_PLAYER_ID + "=" + j3, (String[]) null);
    }

    public ArrayList<Match> getMatches(int i) {
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor;
        ArrayList<Match> arrayList = new ArrayList<>();
        try {
            sQLiteDatabase = getReadableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
            sQLiteDatabase = this.currentDBInstance;
        }
        String str = i == 3 ? "Select * from match" : i == 1 ? "Select * from match where is_test_match=1" : i == 2 ? "Select * from match where is_test_match=0" : i == 4 ? "Select * from match where result='No result'" : null;
        if (str == null) {
            return new ArrayList<>();
        }
        try {
            cursor = sQLiteDatabase.rawQuery(str, (String[]) null);
        } catch (Exception e2) {
            e2.printStackTrace();
            cursor = null;
        }
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Match match = new Match();
                match.setMatchID(cursor.getLong(cursor.getColumnIndex("id")));
                match.setTossResult(cursor.getString(cursor.getColumnIndex(KEY_TOSS_RESULT)));
                match.setTime(cursor.getString(cursor.getColumnIndex(KEY_MATCH_TIME)));
                match.setVenue(cursor.getString(cursor.getColumnIndex(KEY_VENUE)));
                match.setTeam_Yours_id(cursor.getLong(cursor.getColumnIndex(KEY_TEAM_YOURS_ID)));
                match.setTeam_opp_id(cursor.getLong(cursor.getColumnIndex(KEY_TEAM_OPP_ID)));
                match.setYourteam(getTeamName(match.getTeam_Yours_id()));
                match.setOpponent(getTeamName(match.getTeam_opp_id()));
                match.setResult(cursor.getString(cursor.getColumnIndex(KEY_RESULT)));
                match.setDate(cursor.getString(cursor.getColumnIndex("date")));
                match.setMatchID(cursor.getLong(cursor.getColumnIndex("id")));
                match.setTournament(cursor.getString(cursor.getColumnIndex(KEY_TOURNAMENT_NAME)));
                match.setTournamentStage(cursor.getString(cursor.getColumnIndex(KEY_TOURNAMENT_STAGE)));
                match.setOvers(cursor.getInt(cursor.getColumnIndex(KEY_MATCH_OVERS)));
                match.setTestMatch(cursor.getInt(cursor.getColumnIndex(KEY_IS_TEST_MATCH)));
                match.setFollowedOn(cursor.getInt(cursor.getColumnIndex(KEY_IS_FOLLOWED_ON)));
                match.setTeam_Yours_id2(cursor.getLong(cursor.getColumnIndex(KEY_TEAM_YOURS_ID_2)));
                match.setTeam_opp_id2(cursor.getLong(cursor.getColumnIndex(KEY_TEAM_OPP_ID_2)));
                match.getTeam1().setTeamID(match.getTeam_Yours_id());
                match.getTeam2().setTeamID(match.getTeam_opp_id());
                try {
                    match.getTeam1().setImage(SharedPrefsHelper.getTeamImage(this.context, match.getTeam1()));
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                try {
                    match.getTeam2().setImage(SharedPrefsHelper.getTeamImage(this.context, match.getTeam2()));
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
                try {
                    match.getTeam3().setImage(SharedPrefsHelper.getTeamImage(this.context, match.getTeam3()));
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
                try {
                    match.getTeam4().setImage(SharedPrefsHelper.getTeamImage(this.context, match.getTeam4()));
                } catch (Exception e6) {
                    e6.printStackTrace();
                }
                match.getTeam4().setTeamID(match.getTeam_opp_id2());
                match.getTeam3().setTeamID(match.getTeam_Yours_id2());
                setMatchSetting(match);
                arrayList.add(match);
                cursor.moveToNext();
            }
            cursor.close();
        }
        Iterator<Match> it = arrayList.iterator();
        while (it.hasNext()) {
            Match next = it.next();
            next.setResumeMatch(SharedPrefsHelper.getResumeMatch(this.context, next));
            next.setTeam1(getTeamStats(next.getTeam1().getTeamID(), next));
            next.setTeam2(getTeamStats(next.getTeam2().getTeamID(), next));
            if (next.isTestMatch) {
                next.setTeam3(getTeamStats(next.getTeam3().getTeamID(), next));
                next.setTeam4(getTeamStats(next.getTeam4().getTeamID(), next));
            }
            next.setYourteam(next.getTeam1().getName());
            next.setOpponent(next.getTeam2().getName());
            next.setManOfTheMatchModel(getManOfTheMatch(next.getMatchID(), next.getWinningTeam().getTeamID()));
        }
        Collections.reverse(arrayList);
        try {
            Log.i("match Team ids", arrayList.get(0).getTeam_Yours_id() + " " + arrayList.get(0).getTeam_opp_id() + " " + arrayList.get(0).getTeam_Yours_id2() + " " + arrayList.get(0).getTeam_opp_id2());
        } catch (Exception e7) {
            e7.printStackTrace();
        }
        return arrayList;
    }

    public void setMatchSetting(Match match) {
        SQLiteDatabase sQLiteDatabase;
        try {
            sQLiteDatabase = getReadableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
            sQLiteDatabase = this.currentDBInstance;
        }
        Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT * FROM match_setting WHERE match_id=" + match.getMatchID(), (String[]) null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            match.setPerMatchWickets(rawQuery.getInt(rawQuery.getColumnIndex(KEY_MATCH_WICKETS)));
            match.setPerOverBalls(rawQuery.getInt(rawQuery.getColumnIndex(KEY_MATCH_OVER_BALLS)));
            match.setNoRunForNo(rawQuery.getInt(rawQuery.getColumnIndex(KEY_NO_RUN_FOR_NO)));
            match.setNoRunForWide(rawQuery.getInt(rawQuery.getColumnIndex(KEY_NO_RUN_FOR_WIDE)));
            match.setSelectMOMmanually(rawQuery.getInt(rawQuery.getColumnIndex(KEY_SELECT_MOM_MANUALLY)));
            match.setMaxBallsFeature(rawQuery.getInt(rawQuery.getColumnIndex(KEY_MAX_BALLS)));
            rawQuery.moveToNext();
        }
        rawQuery.close();
    }

    public ArrayList<Match> getSpecificTournamentMatches(String str) {
        ArrayList<Match> arrayList = new ArrayList<>();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery("Select * from match where tournament = '" + Formatter.replaceSingleQuoteWithTwoSingleQuotes(Formatter.replaceForwardSlashWithBackSlash(str)) + "'", (String[]) null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            Match match = new Match();
            match.setMatchID(rawQuery.getLong(rawQuery.getColumnIndex("id")));
            match.setTossResult(rawQuery.getString(rawQuery.getColumnIndex(KEY_TOSS_RESULT)));
            match.setTime(rawQuery.getString(rawQuery.getColumnIndex(KEY_MATCH_TIME)));
            match.setVenue(rawQuery.getString(rawQuery.getColumnIndex(KEY_VENUE)));
            match.setTeam_Yours_id(rawQuery.getLong(rawQuery.getColumnIndex(KEY_TEAM_YOURS_ID)));
            match.setTeam_opp_id(rawQuery.getLong(rawQuery.getColumnIndex(KEY_TEAM_OPP_ID)));
            match.setResult(rawQuery.getString(rawQuery.getColumnIndex(KEY_RESULT)));
            match.setDate(rawQuery.getString(rawQuery.getColumnIndex("date")));
            match.setMatchID(rawQuery.getLong(rawQuery.getColumnIndex("id")));
            match.setTournament(rawQuery.getString(rawQuery.getColumnIndex(KEY_TOURNAMENT_NAME)));
            match.setTournamentStage(rawQuery.getString(rawQuery.getColumnIndex(KEY_TOURNAMENT_STAGE)));
            match.setOvers(rawQuery.getInt(rawQuery.getColumnIndex(KEY_MATCH_OVERS)));
            match.setTestMatch(rawQuery.getInt(rawQuery.getColumnIndex(KEY_IS_TEST_MATCH)));
            match.setFollowedOn(rawQuery.getInt(rawQuery.getColumnIndex(KEY_IS_FOLLOWED_ON)));
            match.setTeam_Yours_id2(rawQuery.getLong(rawQuery.getColumnIndex(KEY_TEAM_YOURS_ID_2)));
            match.setTeam_opp_id2(rawQuery.getLong(rawQuery.getColumnIndex(KEY_TEAM_OPP_ID_2)));
            match.getTeam1().setTeamID(match.getTeam_Yours_id());
            match.getTeam2().setTeamID(match.getTeam_opp_id());
            match.getTeam4().setTeamID(match.getTeam_opp_id2());
            match.getTeam3().setTeamID(match.getTeam_Yours_id2());
            setMatchSetting(match);
            arrayList.add(match);
            rawQuery.moveToNext();
        }
        rawQuery.close();
        Iterator<Match> it = arrayList.iterator();
        while (it.hasNext()) {
            Match next = it.next();
            next.setTeam1(getTeamStats(next.getTeam1().getTeamID(), next));
            next.setTeam2(getTeamStats(next.getTeam2().getTeamID(), next));
            if (next.isTestMatch) {
                next.setTeam3(getTeamStats(next.getTeam3().getTeamID(), next));
                next.setTeam4(getTeamStats(next.getTeam4().getTeamID(), next));
            }
            next.setYourteam(next.getTeam1().getName());
            next.setOpponent(next.getTeam2().getName());
        }
        Collections.reverse(arrayList);
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public ArrayList<Match> getUnfinishedMatches() {
        ArrayList<Match> arrayList = new ArrayList<>();
        Cursor rawQuery = getReadableDatabase().rawQuery("Select * from match where result='No result'", (String[]) null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            Match match = new Match();
            match.setMatchID(rawQuery.getLong(rawQuery.getColumnIndex("id")));
            match.setTossResult(SharedPrefsHelper.getSpecificMatchToss(this.context, match.getMatchID()));
            match.setTime(SharedPrefsHelper.getMatchTime(match, this.context));
            match.setVenue(rawQuery.getString(rawQuery.getColumnIndex(KEY_VENUE)));
            match.setTeam_Yours_id(rawQuery.getLong(rawQuery.getColumnIndex(KEY_TEAM_YOURS_ID)));
            match.setTeam_opp_id(rawQuery.getLong(rawQuery.getColumnIndex(KEY_TEAM_OPP_ID)));
            match.setResult(rawQuery.getString(rawQuery.getColumnIndex(KEY_RESULT)));
            match.setDate(rawQuery.getString(rawQuery.getColumnIndex("date")));
            match.setMatchID(rawQuery.getLong(rawQuery.getColumnIndex("id")));
            match.setTournament(rawQuery.getString(rawQuery.getColumnIndex(KEY_TOURNAMENT_NAME)));
            match.setOvers(rawQuery.getInt(rawQuery.getColumnIndex(KEY_MATCH_OVERS)));
            match.isTestMatch = SharedPrefsHelper.getMatchTestFlag(this.context, match);
            if (match.isTestMatch) {
                SharedPrefsHelper.setTeamIdsToMatchInTestMatch(this.context, match);
                match.followedOn = SharedPrefsHelper.getTestMatchFollowOnFlag(this.context, match);
            }
            match.getTeam1().setTeamID(match.getTeam_Yours_id());
            match.getTeam2().setTeamID(match.getTeam_opp_id());
            if (this.context.getSharedPreferences(TABLE_TEAM, 0).getBoolean("dont_show_final_settings", false)) {
                match.setPerOverBalls(6);
                match.setPerMatchWickets(10);
                match.setSelectMOMmanually(false);
                match.setNoRunForWide(false);
                match.setNoRunForNo(false);
            } else {
                SharedPreferences sharedPreferences = this.context.getSharedPreferences(TABLE_TEAM, 0);
                if (sharedPreferences.getBoolean("match_settings " + match.getMatchID(), false)) {
                    SharedPreferences sharedPreferences2 = this.context.getSharedPreferences(TABLE_TEAM, 0);
                    int i = sharedPreferences2.getInt("match_balls " + match.getMatchID(), 6);
                    SharedPreferences sharedPreferences3 = this.context.getSharedPreferences(TABLE_TEAM, 0);
                    int i2 = sharedPreferences3.getInt("match_wickets " + match.getMatchID(), 10);
                    SharedPreferences sharedPreferences4 = this.context.getSharedPreferences(TABLE_TEAM, 0);
                    boolean z = sharedPreferences4.getBoolean("select_mom " + match.getMatchID(), false);
                    SharedPreferences sharedPreferences5 = this.context.getSharedPreferences(TABLE_TEAM, 0);
                    boolean z2 = sharedPreferences5.getBoolean("no_run_for_wide " + match.getMatchID(), false);
                    SharedPreferences sharedPreferences6 = this.context.getSharedPreferences(TABLE_TEAM, 0);
                    boolean z3 = sharedPreferences6.getBoolean("no_run_for_no " + match.getMatchID(), false);
                    match.setPerMatchWickets(i2);
                    match.setPerOverBalls(i);
                    match.setSelectMOMmanually(z);
                    match.setNoRunForWide(z2);
                    match.setNoRunForNo(z3);
                } else {
                    match.setPerOverBalls(6);
                    match.setPerMatchWickets(10);
                    match.setSelectMOMmanually(false);
                    match.setNoRunForWide(false);
                    match.setNoRunForNo(false);
                }
            }
            arrayList.add(match);
            Log.i("match overs", match.getOvers() + "");
            rawQuery.moveToNext();
        }
        rawQuery.close();
        Iterator<Match> it = arrayList.iterator();
        while (it.hasNext()) {
            Match next = it.next();
            next.setResumeMatch(SharedPrefsHelper.getResumeMatch(this.context, next));
            next.setTeam1(getTeamStats(next.getTeam1().getTeamID(), next));
            next.setTeam2(getTeamStats(next.getTeam2().getTeamID(), next));
            if (next.isTestMatch) {
                next.setTeam3(getTeamStats(next.getTeam3().getTeamID(), next));
                next.setTeam4(getTeamStats(next.getTeam4().getTeamID(), next));
            }
            next.setYourteam(next.getTeam1().getName());
            next.setOpponent(next.getTeam2().getName());
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public Team getUnfinishedMatchTeamStats(long j, long j2) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        String str = "Select * from teamscores where match_id=" + j + " and " + KEY_TEAM_ID + "=" + j2 + "";
        String str2 = "Select * from team where id=" + j2 + "";
        Team team = new Team();
        Cursor rawQuery = readableDatabase.rawQuery(str2, (String[]) null);
        rawQuery.moveToFirst();
        team.setName(rawQuery.getString(rawQuery.getColumnIndex(KEY_TEAM_NAME)));
        rawQuery.close();
        Cursor rawQuery2 = readableDatabase.rawQuery(str, (String[]) null);
        rawQuery2.moveToFirst();
        team.setScore(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_SCORE)));
        team.setWickets(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_WICKETS)));
        team.setOversPlayed(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_OVERS_PLAYED)));
        team.setExtras(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_EXTRAS)));
        team.setOverballs(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_OVER_BALLS)));
        rawQuery2.close();
        return team;
    }

    /* access modifiers changed from: package-private */
    public Batsman getUnfinishedMatchBatsmanStats(String str, String str2) {
        ArrayList<Long> yourBatsmansIDs = getYourBatsmansIDs(getYourTeamIDs(str, (ArrayList<Long>) new ArrayList()), str2);
        Batsman batsman = new Batsman();
        batsman.setMatches(yourBatsmansIDs.size());
        SQLiteDatabase readableDatabase = getReadableDatabase();
        int i = 0;
        for (int i2 = 0; i2 < yourBatsmansIDs.size(); i2++) {
            Cursor rawQuery = readableDatabase.rawQuery("Select * from batsmanscores where player_id=" + yourBatsmansIDs.get(i2) + "", (String[]) null);
            rawQuery.moveToFirst();
            while (!rawQuery.isAfterLast()) {
                batsman.setDots(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BATSMAN_0S)));
                batsman.setSingles(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BATSMAN_1S)));
                batsman.setDoubles(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BATSMAN_2S)));
                batsman.setThrees(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BATSMAN_3S)));
                batsman.setFours(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BATSMAN_4S)));
                batsman.setSixs(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BATSMAN_6S)));
                batsman.setMatchid((long) rawQuery.getInt(rawQuery.getColumnIndex(KEY_MATCH_ID)));
                batsman.setTotalScore(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BATSMAN_SCORE)));
                batsman.setOut(rawQuery.getString(rawQuery.getColumnIndex(KEY_OUTSTATUS)));
                if (batsman.isOut().equals(PdfBoolean.TRUE)) {
                    batsman.setMatches(yourBatsmansIDs.size() - 1);
                }
                if (rawQuery.getInt(rawQuery.getColumnIndex(KEY_BATSMAN_SCORE)) > i) {
                    i = rawQuery.getInt(rawQuery.getColumnIndex(KEY_BATSMAN_SCORE));
                }
                rawQuery.moveToNext();
            }
            rawQuery.close();
        }
        batsman.setBest(i);
        if (batsman.getMatches() != 0) {
            batsman.setAverage(((double) batsman.getTotalScore()) / ((double) batsman.getMatches()));
        } else {
            batsman.setAverage(0.0d);
        }
        batsman.setName(str2);
        return batsman;
    }

    /* access modifiers changed from: package-private */
    public ArrayList<Match> getYourMatches(String str) {
        ArrayList<Match> arrayList = new ArrayList<>();
        Cursor rawQuery = getReadableDatabase().rawQuery("Select * from match where ", (String[]) null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            Match match = new Match();
            match.setVenue(rawQuery.getString(rawQuery.getColumnIndex(KEY_VENUE)));
            match.setTeam_Yours_id(rawQuery.getLong(rawQuery.getColumnIndex(KEY_TEAM_YOURS_ID)));
            match.setTeam_opp_id(rawQuery.getLong(rawQuery.getColumnIndex(KEY_TEAM_OPP_ID)));
            match.setResult(rawQuery.getString(rawQuery.getColumnIndex(KEY_RESULT)));
            match.setDate(rawQuery.getString(rawQuery.getColumnIndex("date")));
            match.setMatchID(rawQuery.getLong(rawQuery.getColumnIndex("id")));
            match.setOvers(rawQuery.getInt(rawQuery.getColumnIndex(KEY_MATCH_OVERS)));
            arrayList.add(match);
            rawQuery.moveToNext();
        }
        rawQuery.close();
        return arrayList;
    }

    public ArrayList<FallOfWickets> getFallOfWickets(long j, long j2) {
        ArrayList<FallOfWickets> arrayList = new ArrayList<>();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery("Select * from fallofwickets where match_id=" + j + " and " + KEY_TEAM_ID + "=" + j2 + "", (String[]) null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            FallOfWickets fallOfWickets = new FallOfWickets();
            fallOfWickets.setBall(rawQuery.getInt(rawQuery.getColumnIndex(KEY_WICKET_BALL)));
            fallOfWickets.setOverno(rawQuery.getInt(rawQuery.getColumnIndex(KEY_WICKET_OVER)));
            fallOfWickets.setBatsmanName(rawQuery.getString(rawQuery.getColumnIndex(KEY_WICKET_BATSMAN)));
            fallOfWickets.setBowlerName(rawQuery.getString(rawQuery.getColumnIndex(KEY_WICKET_BOWLER)));
            fallOfWickets.setScore(rawQuery.getInt(rawQuery.getColumnIndex(KEY_WICKET_SCORE)));
            fallOfWickets.setWicketNo(rawQuery.getInt(rawQuery.getColumnIndex(KEY_WICKET_NO)));
            arrayList.add(fallOfWickets);
            rawQuery.moveToNext();
        }
        rawQuery.close();
        return arrayList;
    }

    public Team getTeam1Stats(long j, long j2) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        String str = "Select * from teamscores where match_id=" + j + " and " + KEY_TEAM_ID + "=" + j2 + "";
        Team team = new Team();
        Cursor rawQuery = readableDatabase.rawQuery("Select * from team where id=" + j2 + "", (String[]) null);
        if (rawQuery.getCount() > 0) {
            rawQuery.moveToFirst();
            team.setName(rawQuery.getString(rawQuery.getColumnIndex(KEY_TEAM_NAME)));
        }
        rawQuery.close();
        Cursor rawQuery2 = readableDatabase.rawQuery(str, (String[]) null);
        if (rawQuery2.getCount() > 0) {
            rawQuery2.moveToFirst();
            team.setScore(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_SCORE)));
            team.setWickets(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_WICKETS)));
            team.setOversPlayed(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_OVERS_PLAYED)));
            team.setExtras(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_EXTRAS)));
            team.setOverballs(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_OVER_BALLS)));
            team.setTeamID(j2);
        }
        rawQuery2.close();
        return team;
    }

    public Team getTeam1Stats(long j, long j2, Team team) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery("Select * from team where id=" + j2 + "", (String[]) null);
        rawQuery.moveToFirst();
        team.setName(rawQuery.getString(rawQuery.getColumnIndex(KEY_TEAM_NAME)));
        rawQuery.close();
        Cursor rawQuery2 = readableDatabase.rawQuery("Select * from teamscores where match_id=" + j + " and " + KEY_TEAM_ID + "=" + j2 + "", (String[]) null);
        rawQuery2.moveToFirst();
        team.setScore(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_SCORE)));
        team.setWickets(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_WICKETS)));
        team.setOversPlayed(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_OVERS_PLAYED)));
        team.setExtras(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_EXTRAS)));
        team.setOverballs(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_OVER_BALLS)));
        rawQuery2.close();
        return team;
    }

    public Team getTeamStats(long j, Match match) {
        SQLiteDatabase sQLiteDatabase;
        String str;
        try {
            sQLiteDatabase = getReadableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
            sQLiteDatabase = this.currentDBInstance;
        }
        if (match != null) {
            str = "Select * from teamscores where match_id=" + match.getMatchID() + " and " + KEY_TEAM_ID + "=" + j + "";
        } else {
            str = "Select * from teamscores where team_id=" + j + "";
        }
        Team team = new Team();
        team.setTeamID(j);
        Cursor rawQuery = sQLiteDatabase.rawQuery("Select * from team where id=" + j + "", (String[]) null);
        rawQuery.moveToFirst();
        if (rawQuery.getCount() > 0) {
            team.setName(rawQuery.getString(rawQuery.getColumnIndex(KEY_TEAM_NAME)));
        }
        rawQuery.close();
        Cursor rawQuery2 = sQLiteDatabase.rawQuery(str, (String[]) null);
        rawQuery2.moveToFirst();
        if (rawQuery2.getCount() > 0) {
            team.setScore(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_SCORE)));
            team.setWickets(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_WICKETS)));
            team.setOversPlayed(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_OVERS_PLAYED)));
            team.setExtras(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_EXTRAS)));
            team.setOverballs(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_OVER_BALLS)));
        }
        rawQuery2.close();
        return team;
    }

    public ArrayList<Team> getTeam1Stats(ArrayList<Match> arrayList) {
        ArrayList<Team> arrayList2 = new ArrayList<>();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        for (int i = 0; i < arrayList.size(); i++) {
            Team team = new Team();
            Cursor rawQuery = readableDatabase.rawQuery("Select * from team where id=" + arrayList.get(i).getTeam_Yours_id() + "", (String[]) null);
            rawQuery.moveToFirst();
            team.setName(rawQuery.getString(rawQuery.getColumnIndex(KEY_TEAM_NAME)));
            rawQuery.close();
            Cursor rawQuery2 = readableDatabase.rawQuery("Select * from teamscores where match_id=" + arrayList.get(i).getMatchID() + " and " + KEY_TEAM_ID + "=" + arrayList.get(i).getTeam_Yours_id() + "", (String[]) null);
            rawQuery2.moveToFirst();
            if (rawQuery2.getCount() > 0) {
                team.setScore(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_SCORE)));
                team.setWickets(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_WICKETS)));
                team.setOversPlayed(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_OVERS_PLAYED)));
                team.setExtras(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_EXTRAS)));
                team.setOverballs(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_OVER_BALLS)));
            }
            arrayList2.add(team);
            rawQuery2.close();
        }
        return arrayList2;
    }

    public ArrayList<Team> getTeam2Stats(ArrayList<Match> arrayList) {
        ArrayList<Team> arrayList2 = new ArrayList<>();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        for (int i = 0; i < arrayList.size(); i++) {
            String str = "Select * from teamscores where match_id=" + arrayList.get(i).getMatchID() + " and " + KEY_TEAM_ID + "=" + arrayList.get(i).getTeam_opp_id() + "";
            Team team = new Team();
            Cursor rawQuery = readableDatabase.rawQuery("Select * from team where id=" + arrayList.get(i).getTeam_opp_id() + "", (String[]) null);
            rawQuery.moveToFirst();
            team.setName(rawQuery.getString(rawQuery.getColumnIndex(KEY_TEAM_NAME)));
            rawQuery.close();
            try {
                Cursor rawQuery2 = readableDatabase.rawQuery(str, (String[]) null);
                rawQuery2.moveToFirst();
                team.setScore(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_SCORE)));
                team.setWickets(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_WICKETS)));
                team.setOversPlayed(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_OVERS_PLAYED)));
                team.setExtras(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_EXTRAS)));
                team.setOverballs(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_OVER_BALLS)));
                arrayList2.add(team);
                rawQuery2.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return arrayList2;
    }

    public ArrayList<Team> getTeam3Stats(ArrayList<Match> arrayList) {
        ArrayList<Team> arrayList2 = new ArrayList<>();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        for (int i = 0; i < arrayList.size(); i++) {
            String str = "Select * from teamscores where match_id=" + arrayList.get(i).getMatchID() + " and " + KEY_TEAM_ID + "=" + arrayList.get(i).getTeam_Yours_id2() + "";
            Team team = new Team();
            Cursor rawQuery = readableDatabase.rawQuery("Select * from team where id=" + arrayList.get(i).getTeam_Yours_id2() + "", (String[]) null);
            rawQuery.moveToFirst();
            team.setName(rawQuery.getString(rawQuery.getColumnIndex(KEY_TEAM_NAME)));
            rawQuery.close();
            try {
                Cursor rawQuery2 = readableDatabase.rawQuery(str, (String[]) null);
                rawQuery2.moveToFirst();
                team.setScore(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_SCORE)));
                team.setWickets(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_WICKETS)));
                team.setOversPlayed(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_OVERS_PLAYED)));
                team.setExtras(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_EXTRAS)));
                team.setOverballs(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_OVER_BALLS)));
                arrayList2.add(team);
                rawQuery2.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return arrayList2;
    }

    public ArrayList<Team> getTeam4Stats(ArrayList<Match> arrayList) {
        ArrayList<Team> arrayList2 = new ArrayList<>();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        for (int i = 0; i < arrayList.size(); i++) {
            String str = "Select * from teamscores where match_id=" + arrayList.get(i).getMatchID() + " and " + KEY_TEAM_ID + "=" + arrayList.get(i).getTeam_opp_id2() + "";
            Team team = new Team();
            Cursor rawQuery = readableDatabase.rawQuery("Select * from team where id=" + arrayList.get(i).getTeam_opp_id2() + "", (String[]) null);
            rawQuery.moveToFirst();
            team.setName(rawQuery.getString(rawQuery.getColumnIndex(KEY_TEAM_NAME)));
            rawQuery.close();
            try {
                Cursor rawQuery2 = readableDatabase.rawQuery(str, (String[]) null);
                rawQuery2.moveToFirst();
                team.setScore(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_SCORE)));
                team.setWickets(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_WICKETS)));
                team.setOversPlayed(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_OVERS_PLAYED)));
                team.setExtras(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_EXTRAS)));
                team.setOverballs(rawQuery2.getInt(rawQuery2.getColumnIndex(KEY_TEAM_OVER_BALLS)));
                arrayList2.add(team);
                rawQuery2.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return arrayList2;
    }

    /* access modifiers changed from: package-private */
    public ArrayList<Long> getYourTeamIDs(String str, ArrayList<Long> arrayList) {
        try {
            ArrayList<Long> arrayList2 = new ArrayList<>();
            Cursor rawQuery = getReadableDatabase().rawQuery("Select * from team where team_name=" + Formatter.replaceSingleQuoteWithTwoSingleQuotes(Formatter.wrapStringWithDoubleQuotes(str)) + "", (String[]) null);
            rawQuery.moveToFirst();
            while (!rawQuery.isAfterLast()) {
                try {
                    long j = rawQuery.getLong(rawQuery.getColumnIndex("id"));
                    if (arrayList == null) {
                        arrayList2.add(Long.valueOf(rawQuery.getLong(rawQuery.getColumnIndex("id"))));
                    } else if (arrayList.contains(Long.valueOf(j))) {
                        arrayList2.add(Long.valueOf(rawQuery.getLong(rawQuery.getColumnIndex("id"))));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                rawQuery.moveToNext();
            }
            rawQuery.close();
            Log.e(" team ids size", arrayList2.size() + "");
            return arrayList2;
        } catch (Exception e2) {
            e2.printStackTrace();
            return new ArrayList<>();
        }
    }

    /* access modifiers changed from: package-private */
    public ArrayList<Long> getYourTeamIDs(String str, String str2) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = readableDatabase.rawQuery("Select * from match where tournament=" + Formatter.wrapStringWithDoubleQuotes(Formatter.replaceForwardSlashWithBackSlash(str2)) + "", (String[]) null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            arrayList.add(Long.valueOf(rawQuery.getLong(rawQuery.getColumnIndex(KEY_TEAM_YOURS_ID))));
            arrayList.add(Long.valueOf(rawQuery.getLong(rawQuery.getColumnIndex(KEY_TEAM_OPP_ID))));
            rawQuery.moveToNext();
        }
        rawQuery.close();
        ArrayList<Long> arrayList2 = new ArrayList<>();
        Cursor rawQuery2 = readableDatabase.rawQuery("Select * from team where team_name=" + Formatter.wrapStringWithDoubleQuotes(Formatter.replaceSingleQuoteWithTwoSingleQuotes(Formatter.replaceForwardSlashWithBackSlash(str))) + "", (String[]) null);
        rawQuery2.moveToFirst();
        while (!rawQuery2.isAfterLast()) {
            try {
                long j = rawQuery2.getLong(rawQuery2.getColumnIndex("id"));
                if (arrayList.contains(Long.valueOf(j))) {
                    arrayList2.add(Long.valueOf(j));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            rawQuery2.moveToNext();
        }
        rawQuery2.close();
        Log.e(" team ids size", arrayList2.size() + "");
        return arrayList2;
    }

    public ArrayList<Batsman> getBatsmans(long j, long j2) {
        ArrayList<Batsman> arrayList = new ArrayList<>();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery("Select * from batsmanscores where team_id=" + j + " and " + KEY_MATCH_ID + "=" + j2 + "", (String[]) null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            long j3 = rawQuery.getLong(rawQuery.getColumnIndex(KEY_PLAYER_ID));
            Cursor rawQuery2 = readableDatabase.rawQuery("Select player_name from player where id=" + j3 + "", (String[]) null);
            rawQuery2.moveToFirst();
            if (getPlayerName(j3).equals("All Out")) {
                break;
            }
            Batsman batsman = new Batsman();
            batsman.setName(getPlayerName(j3));
            batsman.setTotalScore(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BATSMAN_SCORE)));
            batsman.setBallsfaced(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BATSMAN_BALLS)));
            batsman.setBatsmanID((long) rawQuery.getInt(rawQuery.getColumnIndex(KEY_PLAYER_ID)));
            batsman.setDots(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BATSMAN_0S)));
            batsman.setSingles(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BATSMAN_1S)));
            batsman.setDoubles(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BATSMAN_2S)));
            batsman.setThrees(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BATSMAN_3S)));
            batsman.setFours(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BATSMAN_4S)));
            batsman.setSixs(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BATSMAN_6S)));
            batsman.setOut(rawQuery.getString(rawQuery.getColumnIndex(KEY_OUTSTATUS)));
            batsman.setTeam_Name(getTeamName(j));
            arrayList.add(batsman);
            rawQuery2.close();
            rawQuery.moveToNext();
        }
        rawQuery.close();
        Log.e("array list size", arrayList.size() + "");
        return arrayList;
    }

    public Batsman getBatsman(long j, long j2, long j3) {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery("Select * from batsmanscores where team_id=" + j + " and " + KEY_MATCH_ID + "=" + j2 + " and " + KEY_PLAYER_ID + "=" + j3 + "", (String[]) null);
        rawQuery.moveToFirst();
        Batsman batsman = null;
        while (!rawQuery.isAfterLast()) {
            long j4 = rawQuery.getLong(rawQuery.getColumnIndex(KEY_PLAYER_ID));
            Cursor rawQuery2 = readableDatabase.rawQuery("Select player_name from player where id=" + j4 + "", (String[]) null);
            rawQuery2.moveToFirst();
            if (getPlayerName(j4).equals("All Out")) {
                break;
            }
            batsman = new Batsman();
            batsman.setName(getPlayerName(j4));
            batsman.setTotalScore(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BATSMAN_SCORE)));
            batsman.setBallsfaced(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BATSMAN_BALLS)));
            batsman.setBatsmanID((long) rawQuery.getInt(rawQuery.getColumnIndex(KEY_PLAYER_ID)));
            batsman.setDots(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BATSMAN_0S)));
            batsman.setSingles(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BATSMAN_1S)));
            batsman.setDoubles(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BATSMAN_2S)));
            batsman.setThrees(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BATSMAN_3S)));
            batsman.setFours(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BATSMAN_4S)));
            batsman.setSixs(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BATSMAN_6S)));
            batsman.setOut(rawQuery.getString(rawQuery.getColumnIndex(KEY_OUTSTATUS)));
            batsman.setTeam_Name(getTeamName(j));
            rawQuery2.close();
            rawQuery.moveToNext();
        }
        rawQuery.close();
        Log.e("array list size", arrayList.size() + "");
        return batsman;
    }

    public ArrayList<Bowler> getBowlers(long j, long j2) {
        ArrayList<Bowler> arrayList = new ArrayList<>();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery("Select * from bowlerscore where team_id=" + j + " and " + KEY_MATCH_ID + "=" + j2 + "", (String[]) null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            long j3 = rawQuery.getLong(rawQuery.getColumnIndex(KEY_PLAYER_ID));
            Cursor rawQuery2 = readableDatabase.rawQuery("Select player_name from player where id=" + j3 + "", (String[]) null);
            rawQuery2.moveToFirst();
            Bowler bowler = new Bowler();
            if (rawQuery2.getCount() > 0) {
                bowler.setName(rawQuery2.getString(rawQuery2.getColumnIndex("player_name")));
            } else {
                bowler.setName(" ");
            }
            bowler.setTotalscore(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BOWLER_SCORE)));
            bowler.setWickets(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BOWELR_WICKETS)));
            bowler.setMaidens(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BOWLER_MAIDENS)));
            bowler.setBowlerovers(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BOWLER_OVERS)));
            bowler.setBalls(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BOWLER_BALLS)));
            bowler.setBowlerID(rawQuery.getLong(rawQuery.getColumnIndex(KEY_PLAYER_ID)));
            bowler.setTeamName(getTeamName(j));
            arrayList.add(bowler);
            rawQuery2.close();
            rawQuery.moveToNext();
        }
        rawQuery.close();
        return arrayList;
    }

    public Bowler getBowler(long j, long j2, long j3) {
        new ArrayList();
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Bowler bowler = null;
        Cursor rawQuery = readableDatabase.rawQuery("Select * from bowlerscore where team_id=" + j + " and " + KEY_MATCH_ID + "=" + j2 + " and " + KEY_PLAYER_ID + "=" + j3 + "", (String[]) null);
        rawQuery.moveToFirst();
        if (!rawQuery.isAfterLast()) {
            long j4 = rawQuery.getLong(rawQuery.getColumnIndex(KEY_PLAYER_ID));
            Cursor rawQuery2 = readableDatabase.rawQuery("Select player_name from player where id=" + j4 + "", (String[]) null);
            rawQuery2.moveToFirst();
            Bowler bowler2 = new Bowler();
            if (rawQuery2.getCount() > 0) {
                bowler2.setName(rawQuery2.getString(rawQuery2.getColumnIndex("player_name")));
            } else {
                bowler2.setName(" ");
            }
            bowler2.setTotalscore(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BOWLER_SCORE)));
            bowler2.setWickets(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BOWELR_WICKETS)));
            bowler2.setMaidens(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BOWLER_MAIDENS)));
            bowler2.setBowlerovers(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BOWLER_OVERS)));
            bowler2.setBalls(rawQuery.getInt(rawQuery.getColumnIndex(KEY_BOWLER_BALLS)));
            bowler2.setBowlerID(rawQuery.getLong(rawQuery.getColumnIndex(KEY_PLAYER_ID)));
            bowler2.setTeamName(getTeamName(j));
            rawQuery2.close();
            bowler = bowler2;
        }
        rawQuery.close();
        return bowler;
    }

    /* access modifiers changed from: package-private */
    public String getPlayerName(long j) {
        String str = "";
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor rawQuery = readableDatabase.rawQuery("SELECT * FROM player WHERE id=" + j + "", (String[]) null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            str = rawQuery.getString(rawQuery.getColumnIndex("player_name"));
            rawQuery.moveToNext();
        }
        rawQuery.close();
        return str;
    }

    public ArrayList<Team> getTeam() {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        ArrayList<Team> arrayList = new ArrayList<>();
        Cursor rawQuery = readableDatabase.rawQuery("SELECT * FROM team", (String[]) null);
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            Team team = new Team();
            team.setName(rawQuery.getString(rawQuery.getColumnIndex(KEY_TEAM_NAME)));
            arrayList.add(team);
            rawQuery.moveToNext();
        }
        rawQuery.close();
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public ArrayList<Team> getUnfinishedMatchesTeams(ArrayList<Match> arrayList) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Log.i("NO OF MATCHES", arrayList.size() + "");
        ArrayList<Team> arrayList2 = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            Cursor rawQuery = readableDatabase.rawQuery("SELECT * FROM team WHERE id=" + arrayList.get(i).getTeam_Yours_id(), (String[]) null);
            Cursor rawQuery2 = readableDatabase.rawQuery("SELECT * FROM team WHERE id=" + arrayList.get(i).getTeam_opp_id(), (String[]) null);
            rawQuery.moveToFirst();
            while (!rawQuery.isAfterLast()) {
                Team team = new Team();
                team.setName(rawQuery.getString(rawQuery.getColumnIndex(KEY_TEAM_NAME)));
                arrayList2.add(team);
                rawQuery.moveToNext();
            }
            rawQuery.close();
            rawQuery2.moveToFirst();
            while (!rawQuery2.isAfterLast()) {
                Team team2 = new Team();
                team2.setName(rawQuery2.getString(rawQuery2.getColumnIndex(KEY_TEAM_NAME)));
                arrayList2.add(team2);
                rawQuery2.moveToNext();
            }
            rawQuery2.close();
        }
        return arrayList2;
    }

    public ArrayList<Team> getSpecificTournamentTeams(ArrayList<Match> arrayList) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        ArrayList<Team> arrayList2 = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            Cursor rawQuery = readableDatabase.rawQuery("SELECT * FROM team WHERE id=" + arrayList.get(i).getTeam_Yours_id(), (String[]) null);
            Cursor rawQuery2 = readableDatabase.rawQuery("SELECT * FROM team WHERE id=" + arrayList.get(i).getTeam_opp_id(), (String[]) null);
            rawQuery.moveToFirst();
            while (!rawQuery.isAfterLast()) {
                Team team = new Team();
                team.setName(rawQuery.getString(rawQuery.getColumnIndex(KEY_TEAM_NAME)));
                arrayList2.add(team);
                rawQuery.moveToNext();
            }
            rawQuery.close();
            rawQuery2.moveToFirst();
            while (!rawQuery2.isAfterLast()) {
                Team team2 = new Team();
                team2.setName(rawQuery2.getString(rawQuery2.getColumnIndex(KEY_TEAM_NAME)));
                arrayList2.add(team2);
                rawQuery2.moveToNext();
            }
            rawQuery2.close();
        }
        return arrayList2;
    }

    public ArrayList<String> getTournament(int i) {
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor = null;
        if (this != null) {
            sQLiteDatabase = getReadableDatabase();
        } else {
            sQLiteDatabase = null;
        }
        String str = i == 3 ? "SELECT * FROM match" : i == 1 ? "SELECT * FROM match where is_test_match=1" : i == 2 ? "SELECT * FROM match where is_test_match=0" : null;
        if (str == null) {
            return new ArrayList<>();
        }
        if (sQLiteDatabase != null) {
            cursor = sQLiteDatabase.rawQuery(str, (String[]) null);
        }
        if (cursor != null) {
            cursor.moveToFirst();
        }
        ArrayList<String> arrayList = new ArrayList<>();
        while (cursor != null && !cursor.isAfterLast()) {
            try {
                String[] split = cursor.getString(cursor.getColumnIndex(KEY_TOURNAMENT_NAME)).split(":");
                if (!arrayList.contains(split[0])) {
                    arrayList.add(split[0]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            cursor.moveToNext();
        }
        cursor.close();
        return arrayList;
    }

    public void UpdateMatchResult(long j, String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_RESULT, str);
        writableDatabase.update(TABLE_MATCH, contentValues, "id=" + j, (String[]) null);
    }

    public ArrayList<String> getAllVenues() {
        new ArrayList();
        Cursor rawQuery = getReadableDatabase().rawQuery("Select * from match", (String[]) null);
        ArrayList<String> arrayList = new ArrayList<>();
        rawQuery.moveToFirst();
        while (!rawQuery.isAfterLast()) {
            String string = rawQuery.getString(rawQuery.getColumnIndex(KEY_VENUE));
            if (!checkVenueAlreadyExistence(arrayList, string)) {
                arrayList.add(string);
            }
            rawQuery.moveToNext();
        }
        rawQuery.close();
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public boolean checkVenueAlreadyExistence(ArrayList<String> arrayList, String str) {
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            if (str.equals(it.next())) {
                return true;
            }
        }
        return false;
    }

    public String getYourTeamName() {
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT * FROM teamplayers WHERE team_side ='yours'", (String[]) null);
        rawQuery.moveToFirst();
        String string = rawQuery.getCount() > 0 ? rawQuery.getString(rawQuery.getColumnIndex(KEY_TEAM_NAME)) : " ";
        rawQuery.close();
        return string;
    }

    public void insertFullMatchInDB(Match match) {
        match.setTeam_Yours_id(insertTeam(match.getYourteam()));
        match.setTeam_opp_id(insertTeam(match.getOpponent()));
        match.getTeam1().setTeamID(match.getTeam_Yours_id());
        match.getTeam2().setTeamID(match.getTeam_opp_id());
        if (match.isTestMatch) {
            match.setTeam_Yours_id2(insertTeam(match.getYourteam()));
            match.setTeam_opp_id2(insertTeam(match.getOpponent()));
            match.getTeam3().setTeamID(match.getTeam_Yours_id2());
            match.getTeam4().setTeamID(match.getTeam_opp_id2());
        }
        match.setMatchID(insertMatch(match));
        insertTeamScore(match.getTeam_Yours_id(), match.getMatchID(), match.getTeam1().getScore(), match.getTeam1().getWickets(), match.getTeam1().getWides(), match.getTeam1().getNos(), match.getTeam1().getOversPlayed(), match.getTeam1().getExtras(), match.getTeam1().getOverballs());
        insertTeamScore(match.getTeam_opp_id(), match.getMatchID(), match.getTeam2().getScore(), match.getTeam2().getWickets(), match.getTeam2().getWides(), match.getTeam2().getNos(), match.getTeam2().getOversPlayed(), match.getTeam2().getExtras(), match.getTeam2().getOverballs());
        if (match.isTestMatch) {
            insertTeamScore(match.getTeam_Yours_id2(), match.getMatchID(), match.getTeam3().getScore(), match.getTeam3().getWickets(), match.getTeam3().getWides(), match.getTeam3().getNos(), match.getTeam3().getOversPlayed(), match.getTeam3().getExtras(), match.getTeam3().getOverballs());
            insertTeamScore(match.getTeam_opp_id2(), match.getMatchID(), match.getTeam4().getScore(), match.getTeam4().getWickets(), match.getTeam4().getWides(), match.getTeam4().getNos(), match.getTeam4().getOversPlayed(), match.getTeam4().getExtras(), match.getTeam4().getOverballs());
        }
        insertBatsmenAndTheirScoresInDB(match.getTeam1(), match);
        insertBatsmenAndTheirScoresInDB(match.getTeam2(), match);
        match.getTeam1().getBowlers_list();
        ArrayList<Bowler> bowlers_list = match.getTeam2().getBowlers_list();
        ArrayList<Over> overs_list = match.getTeam1().getOvers_list();
        match.getTeam2().getOvers_list();
        if (overs_list.size() > 0 && bowlers_list.size() > 0) {
            if (overs_list.get(0).getBowler().equals(bowlers_list.get(0).getName())) {
                insertBowlersAndTheirScoresInDB(match.getTeam2(), match.getTeam2(), match);
                insertBowlersAndTheirScoresInDB(match.getTeam1(), match.getTeam1(), match);
            } else {
                insertBowlersAndTheirScoresInDB(match.getTeam1(), match.getTeam2(), match);
                insertBowlersAndTheirScoresInDB(match.getTeam2(), match.getTeam1(), match);
            }
        }
        insertAllFallOfWicketsInDB(match.getTeam1(), match);
        insertAllFallOfWicketsInDB(match.getTeam2(), match);
        insertOvers(match.getMatchID(), match.getTeam1().getTeamID(), match.getTeam1().getOvers_list());
        insertOvers(match.getMatchID(), match.getTeam2().getTeamID(), match.getTeam2().getOvers_list());
        if (match.isTestMatch) {
            insertBatsmenAndTheirScoresInDB(match.getTeam3(), match);
            insertBatsmenAndTheirScoresInDB(match.getTeam4(), match);
            insertBowlersAndTheirScoresInDB(match.getTeam3(), match.getTeam4(), match);
            insertBowlersAndTheirScoresInDB(match.getTeam4(), match.getTeam3(), match);
            insertAllFallOfWicketsInDB(match.getTeam3(), match);
            insertAllFallOfWicketsInDB(match.getTeam4(), match);
            insertOvers(match.getMatchID(), match.getTeam3().getTeamID(), match.getTeam3().getOvers_list());
            insertOvers(match.getMatchID(), match.getTeam4().getTeamID(), match.getTeam4().getOvers_list());
        }
        insertMatchSettings(match);
        if (match.getManOfTheMatchModel() != null) {
            insertManOfTheMatch(match.getManOfTheMatchModel());
        }
    }

    private void insertBatsmenAndTheirScoresInDB(Team team, Match match) {
        Iterator<Batsman> it = team.getBatsmans_list().iterator();
        while (it.hasNext()) {
            Batsman next = it.next();
            next.setBatsmanID(insertPlayer(next.getName(), team.getTeamID(), "batsman"));
            insertBatsmanScore(team.getTeamID(), match.getMatchID(), next.getBatsmanID(), next.getTotalScore(), next.getBallsfaced(), next.getDots(), next.getSingles(), next.getDoubles(), next.getThrees(), next.getFours(), next.getSixs(), next.isOut());
        }
    }

    private void insertBowlersAndTheirScoresInDB(Team team, Team team2, Match match) {
        Iterator<Bowler> it = team.getBowlers_list().iterator();
        while (it.hasNext()) {
            Bowler next = it.next();
            next.setBowlerID(insertPlayer(next.getName(), team2.getTeamID(), "bowler"));
            insertBowlerScore(team2.getTeamID(), match.getMatchID(), next.getBowlerID(), next.getTotalscore(), next.getWickets(), next.getMaidens(), next.getBowlerovers(), next.getBalls());
        }
    }

    private void insertAllFallOfWicketsInDB(Team team, Match match) {
        Iterator<FallOfWickets> it = team.fallOfWicketses.iterator();
        while (it.hasNext()) {
            insertFallofWicket(match.getMatchID(), team.getTeamID(), it.next());
        }
    }

    public void updateMatch(Match match) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TEAM_YOURS_ID_2, Long.valueOf(match.getTeam_Yours_id2()));
        contentValues.put(KEY_TEAM_OPP_ID_2, Long.valueOf(match.getTeam_opp_id2()));
        contentValues.put(KEY_IS_FOLLOWED_ON, Integer.valueOf(match.isFollowedOn()));
        contentValues.put(KEY_MATCH_TIME, match.getTime());
        writableDatabase.update(TABLE_MATCH, contentValues, "id=" + match.getMatchID(), (String[]) null);
    }

    public void updateMatchTossAndTime(Match match) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TOSS_RESULT, match.getTossResult());
        contentValues.put(KEY_MATCH_TIME, match.getTime());
        writableDatabase.update(TABLE_MATCH, contentValues, "id=" + match.getMatchID(), (String[]) null);
    }
}
