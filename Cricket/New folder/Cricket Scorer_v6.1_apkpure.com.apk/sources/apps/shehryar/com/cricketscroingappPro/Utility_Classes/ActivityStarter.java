package apps.shehryar.com.cricketscroingappPro.Utility_Classes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import apps.shehryar.com.cricketscroingappPro.After_Innings_Activity;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.FirstPage;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Match.Match_Result_Activity;
import apps.shehryar.com.cricketscroingappPro.Match.Previous_Match_Result;
import apps.shehryar.com.cricketscroingappPro.PlayersLoader;
import apps.shehryar.com.cricketscroingappPro.Team.Team_players;
import java.util.ArrayList;

public class ActivityStarter {
    public static void startAfterInningsActivity(Context context, Match match) {
        Intent intent = new Intent(context, After_Innings_Activity.class);
        intent.putExtra(DBHelper.TABLE_MATCH, match);
        SharedPrefsHelper.insertOversString(context, match.getMatchID(), match.getBowlingTeam().getTeamID(), match.getBattingTeam().overs_list);
        context.startActivity(intent);
    }

    public static void startMatchResultActivity(Context context, Match match) {
        Intent intent = new Intent(context, Match_Result_Activity.class);
        intent.putExtra(DBHelper.TABLE_MATCH, match);
        SharedPrefsHelper.insertOversString(context, match.getMatchID(), match.getBowlingTeam().getTeamID(), match.getBattingTeam().overs_list);
        context.startActivity(intent);
    }

    public static void startPreviousMatchResultActivity(Context context, Activity activity, Match match, int i) {
        Intent intent = new Intent(context, Previous_Match_Result.class);
        intent.putExtra(DBHelper.TABLE_MATCH, match);
        intent.putExtra("index", i);
        try {
            activity.startActivityForResult(intent, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startFirstPageActivity(Context context, PlayersLoader playersLoader, ArrayList<Team_players> arrayList, ArrayList<String> arrayList2, ArrayList<Team_players> arrayList3, ArrayList<String> arrayList4, ArrayList<String> arrayList5, ArrayList<Team_players> arrayList6) {
        Intent intent = new Intent(context, FirstPage.class);
        intent.putExtra("teams", arrayList);
        intent.putExtra("teamnames", arrayList2);
        intent.putExtra("userAddedTeams", arrayList3);
        intent.putExtra("userAddedTeamNames", arrayList4);
        intent.putExtra("userAddedAndHistoryTeams", arrayList6);
        intent.putExtra("userAddedAndHistoryTeamsNames", arrayList5);
        try {
            ((Activity) context).startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
