package apps.shehryar.com.cricketscroingappPro;

import android.content.Context;
import android.util.Log;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import java.util.ArrayList;
import java.util.Iterator;

public class PlayersLoader {
    Context context;
    DBHelper dbHelper;

    public PlayersLoader(Context context2) {
        this.context = context2;
        this.dbHelper = new DBHelper(context2);
    }

    public ArrayList<Team> getAllTeamPlayers(int i) {
        ArrayList<Team> arrayList;
        try {
            arrayList = this.dbHelper.getAllTournamentTeams(i);
            Log.i("allTeams size", arrayList.size() + "");
        } catch (Exception e) {
            e.printStackTrace();
            arrayList = new ArrayList<>();
        }
        try {
            return this.dbHelper.getAllTeamsWithPlayers(arrayList);
        } catch (Exception e2) {
            e2.printStackTrace();
            return arrayList;
        }
    }

    public ArrayList<String> getTeamNames(ArrayList<Team> arrayList) {
        ArrayList<String> arrayList2 = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList2.add(arrayList.get(i).getName());
        }
        return arrayList2;
    }

    /* access modifiers changed from: package-private */
    public ArrayList<Team> getAddedTeamPlayers() {
        try {
            return this.dbHelper.getAddedTeamsAndPlayers();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /* access modifiers changed from: package-private */
    public ArrayList<String> getAddedTeamNmaes(ArrayList<Team> arrayList) {
        ArrayList<String> arrayList2 = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList2.add(arrayList.get(i).getName());
        }
        return arrayList2;
    }

    /* access modifiers changed from: package-private */
    public ArrayList<String> getSpecificTeamPlayers(ArrayList<Team> arrayList, String str) {
        Iterator<Team> it = arrayList.iterator();
        while (it.hasNext()) {
            Team next = it.next();
            if (next != null && next.getName().equals(str)) {
                return next.getAllPlayingXIPlayers();
            }
        }
        return new ArrayList<>();
    }
}
