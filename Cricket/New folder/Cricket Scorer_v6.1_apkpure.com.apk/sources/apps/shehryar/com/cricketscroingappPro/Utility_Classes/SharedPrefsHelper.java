package apps.shehryar.com.cricketscroingappPro.Utility_Classes;

import android.content.Context;
import android.content.SharedPreferences;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Match.ResumeMatch;
import apps.shehryar.com.cricketscroingappPro.Overs.Over;
import apps.shehryar.com.cricketscroingappPro.Player.Player;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import java.util.ArrayList;

public class SharedPrefsHelper {
    public static final String MOM_COPIED = "mom_copied";

    public static void insertMatchTimeInSharedPrefs(Match match, Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0).edit();
        edit.putString("time " + match.getMatchID(), match.getTime());
        edit.commit();
    }

    public static String getMatchTime(Match match, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0);
        return sharedPreferences.getString("time " + match.getMatchID(), "");
    }

    public static void deleteMatchTime(Match match, Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0).edit();
        edit.putString("time " + match.getMatchID(), "");
        edit.commit();
    }

    public static void insertProFeatures(Context context, boolean z) {
        SharedPreferences.Editor edit = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0).edit();
        edit.putBoolean("isPro", z);
        edit.commit();
    }

    public static boolean isPro(Context context) {
        return context.getSharedPreferences(DBHelper.TABLE_TEAM, 0).getBoolean("isPro", false);
    }

    public static void insertOversString(Context context, long j, long j2, ArrayList<Over> arrayList) {
        SharedPreferences.Editor edit = context.getSharedPreferences(DBHelper.TABLE_OVERS, 0).edit();
        edit.putString(j + " " + j2, Formatter.convertOversToString(arrayList));
        edit.commit();
    }

    public static String getOversString(Context context, long j, long j2) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DBHelper.TABLE_OVERS, 0);
        return sharedPreferences.getString(j + " " + j2, "null");
    }

    public static void insertTeamsCopiedBoolean(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0).edit();
        edit.putBoolean("teams_copied", true);
        edit.commit();
    }

    public static void insertOversCopiedBoolean(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0).edit();
        edit.putBoolean("overs_copied", true);
        edit.commit();
    }

    public static boolean checkTeamsCopied(Context context) {
        return context.getSharedPreferences(DBHelper.TABLE_TEAM, 0).getBoolean("teams_copied", false);
    }

    public static void saveMatchResumeDatainSharedPrefs(Match match, Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0).edit();
        edit.putString("innings " + match.getMatchID(), match.getResumeInnings());
        edit.putString("bowler " + match.getMatchID(), match.getResumeBowler());
        edit.putString("batsman " + match.getMatchID(), match.getResumeBatNo());
        edit.putString("overScore " + match.getMatchID(), match.getResumeOverPerBallScore());
        edit.commit();
    }

    public static void insertMatchTossInShredPref(Context context, Match match) {
        SharedPreferences.Editor edit = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0).edit();
        edit.putString("matchToss " + match.getMatchID(), match.getTossResult());
        edit.commit();
    }

    public static void deleteMatchTossFromSharedPrefs(Context context, Match match) {
        SharedPreferences.Editor edit = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0).edit();
        edit.putString("matchToss " + match.getMatchID(), "");
        edit.commit();
    }

    public static void insertMatchTeamIdInSharedPrefs(Context context, long j, String str, Match match) {
        SharedPreferences.Editor edit = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0).edit();
        edit.putLong("match Team id " + match.getMatchID() + " " + str, j);
        edit.commit();
    }

    public static void insertTestMatchFlag(Context context, Match match) {
        SharedPreferences.Editor edit = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0).edit();
        edit.putBoolean("match test " + match.getMatchID(), match.isTestMatch);
        edit.commit();
    }

    public static boolean getMatchTestFlag(Context context, Match match) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0);
        return sharedPreferences.getBoolean("match test " + match.getMatchID(), false);
    }

    public static void insertTestMatchFollowOnFlag(Context context, Match match) {
        SharedPreferences.Editor edit = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0).edit();
        edit.putBoolean("match test follow On " + match.getMatchID(), match.followedOn);
        edit.commit();
    }

    public static void removeTestMatchFollowOnFlag(Context context, Match match) {
        SharedPreferences.Editor edit = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0).edit();
        edit.putBoolean("match test follow On " + match.getMatchID(), false);
        edit.commit();
    }

    public static boolean getTestMatchFollowOnFlag(Context context, Match match) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0);
        return sharedPreferences.getBoolean("match test follow on " + match.getMatchID(), false);
    }

    public static void setTeamIdsToMatchInTestMatch(Context context, Match match) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0);
        long j = sharedPreferences.getLong("match Team id " + match.getMatchID() + " yours", -1);
        SharedPreferences sharedPreferences2 = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0);
        long j2 = sharedPreferences2.getLong("match Team id " + match.getMatchID() + " opp", -1);
        match.setTeam_Yours_id2(j);
        match.setTeam_opp_id2(j2);
        if (match.followedOn) {
            match.getTeam3().setTeamID(j2);
            match.getTeam4().setTeamID(j);
            return;
        }
        match.getTeam4().setTeamID(j2);
        match.getTeam3().setTeamID(j);
    }

    public static void swapTeamIds(Context context, Match match) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0);
        long j = sharedPreferences.getLong("match Team id " + match.getMatchID() + " yours", -1);
        SharedPreferences sharedPreferences2 = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0);
        long j2 = sharedPreferences2.getLong("match Team id " + match.getMatchID() + " opp", -1);
        insertMatchTeamIdInSharedPrefs(context, j, "opp", match);
        insertMatchTeamIdInSharedPrefs(context, j2, "yours", match);
        match.getTeam3().setTeamID(j2);
        match.getTeam4().setTeamID(j);
    }

    public static String getSpecificMatchToss(Context context, long j) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0);
        return sharedPreferences.getString("matchToss " + j, " ");
    }

    public static void insertPlayerAsPlayingXI(Context context, Player player) {
        SharedPreferences.Editor edit = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0).edit();
        edit.putBoolean("playingXI " + player.getPlayerId() + ":" + player.getTeamId(), true);
        edit.commit();
    }

    public static boolean getPlayerInPlayingXI(Context context, Player player) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0);
        return sharedPreferences.getBoolean("playingXI " + player.getPlayerId() + ":" + player.getTeamId(), false);
    }

    public static void removePlayerFromPlayingXI(Context context, Player player) {
        SharedPreferences.Editor edit = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0).edit();
        edit.putBoolean("playingXI " + player.getPlayerId() + ":" + player.getTeamId(), false);
        edit.commit();
    }

    public static void insertBowlerTypeUpdated(Context context, boolean z) {
        SharedPreferences.Editor edit = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0).edit();
        edit.putBoolean("BowlerTypeUpdated2", z);
        edit.commit();
    }

    public static boolean checkBowlerTypeUpdated(Context context) {
        return context.getSharedPreferences(DBHelper.TABLE_TEAM, 0).getBoolean("BowlerTypeUpdated2", false);
    }

    public static void addPlayingXI(Context context, Match match, Team team, ArrayList<String> arrayList) {
        String str;
        try {
            str = UtilityFunctions.getStringFromStringArrayList(arrayList);
        } catch (Exception e) {
            e.printStackTrace();
            str = "";
        }
        SharedPreferences.Editor edit = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0).edit();
        edit.putString("playingXI players " + match.getMatchID() + ":" + team.getTeamID(), str);
        edit.commit();
    }

    public static String getPlayingXI(Context context, Match match, Team team) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0);
        return sharedPreferences.getString("playingXI players " + match.getMatchID() + ":" + team.getTeamID(), "");
    }

    public static void insertResumeMatchInSharedPrefs(Context context, ResumeMatch resumeMatch, Match match) {
        SharedPreferences.Editor edit = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0).edit();
        edit.putInt("innings " + match.getMatchID(), resumeMatch.getInnings());
        edit.putString("bowler " + match.getMatchID(), resumeMatch.getBowlerName());
        edit.putInt("batsman " + match.getMatchID(), resumeMatch.getBatFacing());
        edit.putString("overScore " + match.getMatchID(), resumeMatch.getOverScore());
        edit.commit();
    }

    public static ResumeMatch getResumeMatch(Context context, Match match) {
        ResumeMatch resumeMatch = new ResumeMatch();
        SharedPreferences sharedPreferences = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0);
        resumeMatch.setOverScore(sharedPreferences.getString("overScore " + match.getMatchID(), ""));
        try {
            SharedPreferences sharedPreferences2 = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0);
            resumeMatch.setBatFacing(sharedPreferences2.getInt("batsman " + match.getMatchID(), -1));
        } catch (Exception e) {
            e.printStackTrace();
            resumeMatch.setBatFacing(getBatFacingFromString(context, match));
        }
        try {
            SharedPreferences sharedPreferences3 = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0);
            resumeMatch.setInnings(sharedPreferences3.getInt("innings " + match.getMatchID(), -1));
        } catch (Exception e2) {
            e2.printStackTrace();
            resumeMatch.setInnings(getInningsFromString(context, match));
        }
        SharedPreferences sharedPreferences4 = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0);
        resumeMatch.setBowlerName(sharedPreferences4.getString("bowler " + match.getMatchID(), ""));
        return resumeMatch;
    }

    public static int getInningsFromString(Context context, Match match) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0);
        String string = sharedPreferences.getString("innings " + match.getMatchID(), "-1");
        if (string.equals("first")) {
            return 1;
        }
        if (string.equals("second")) {
            return 2;
        }
        return string.equals("mid") ? 5 : -1;
    }

    public static int getBatFacingFromString(Context context, Match match) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0);
        String string = sharedPreferences.getString("batsman " + match.getMatchID(), "-1");
        if (string.equals("first")) {
            return 0;
        }
        return string.equals("second") ? 1 : -1;
    }

    public static boolean checkOversCopied(Context context) {
        return context.getSharedPreferences(DBHelper.TABLE_TEAM, 0).getBoolean("overs_copied", false);
    }

    public static void insertTeamImage(Context context, Team team) {
        SharedPreferences.Editor edit = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0).edit();
        edit.putString("teamImage " + team.getName(), team.getImage());
        edit.commit();
    }

    public static String getTeamImage(Context context, Team team) throws Exception {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0);
        return sharedPreferences.getString("teamImage " + team.getName(), "");
    }

    public static boolean checkMOMCopied(Context context) {
        return context.getSharedPreferences(DBHelper.TABLE_TEAM, 0).getBoolean(MOM_COPIED, false);
    }

    public static void setMOmCopied(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences(DBHelper.TABLE_TEAM, 0).edit();
        edit.putBoolean(MOM_COPIED, true);
        edit.commit();
    }
}
