package apps.shehryar.com.cricketscroingappPro.Batsman;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Ranking.DialogBatsmanRanking;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.SharedPrefsHelper;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.UtilityFunctions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class BatsmanHelper {
    public static void sortBatsmanOnPoints(ArrayList<Batsman> arrayList) {
        Collections.sort(arrayList, new Comparator<Batsman>() {
            public int compare(Batsman batsman, Batsman batsman2) {
                return batsman2.getPoints() - batsman.getPoints();
            }
        });
    }

    public static void sortBatsmanOnScores(ArrayList<Batsman> arrayList) throws Exception {
        Collections.sort(arrayList, new Comparator<Batsman>() {
            public int compare(Batsman batsman, Batsman batsman2) {
                return batsman2.getTotalScore() - batsman.getTotalScore();
            }
        });
    }

    public static void showBatmsanRankingDialog(Activity activity, Batsman batsman, ArrayList<Long> arrayList) {
        DialogBatsmanRanking dialogBatsmanRanking = new DialogBatsmanRanking();
        Bundle bundle = new Bundle();
        bundle.putSerializable("batsman", batsman);
        bundle.putSerializable("allTeamIds", arrayList);
        dialogBatsmanRanking.setArguments(bundle);
        dialogBatsmanRanking.show(activity.getFragmentManager(), "batsman rank dialog");
    }

    public static void addNotBattedBatsmanFromPlayingXI(Context context, Team team, ArrayList<Batsman> arrayList, Match match) {
        ArrayList<String> arrayList2;
        try {
            arrayList2 = UtilityFunctions.getStringArrayListFromStrings(SharedPrefsHelper.getPlayingXI(context, match, team));
        } catch (Exception e) {
            e.printStackTrace();
            arrayList2 = new ArrayList<>();
        }
        ArrayList<String> misMatchedNames = getMisMatchedNames(arrayList, arrayList2);
        ArrayList arrayList3 = new ArrayList();
        Iterator<String> it = misMatchedNames.iterator();
        while (it.hasNext()) {
            Batsman batsman = new Batsman();
            batsman.setName(it.next());
            batsman.setOut("Did not Bat");
            arrayList3.add(batsman);
        }
        arrayList.addAll(arrayList3);
    }

    private static ArrayList<String> getMisMatchedNames(ArrayList<Batsman> arrayList, ArrayList<String> arrayList2) {
        ArrayList<String> arrayList3 = new ArrayList<>();
        ArrayList arrayList4 = new ArrayList();
        Iterator<Batsman> it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList4.add(it.next().getName());
        }
        Iterator<String> it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            String next = it2.next();
            if (!arrayList4.contains(next)) {
                arrayList3.add(next);
            }
        }
        return arrayList3;
    }

    public static boolean checkBatsmanAlreadyPlayed(String str, Team team) {
        boolean z = false;
        for (int i = 0; i < team.batsmans_list.size(); i++) {
            if (team.batsmans_list.get(i).getName().equals(str) && !team.batsmans_list.get(i).isOut().equals("Ret")) {
                z = true;
            }
        }
        return z;
    }
}
