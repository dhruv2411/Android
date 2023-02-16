package apps.shehryar.com.cricketscroingappPro.Bowler;

import android.app.Activity;
import android.os.Bundle;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Overs.Over;
import apps.shehryar.com.cricketscroingappPro.Ranking.DialogBowlerRanking;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BowlerHelper {
    public static void sortBowlersOnPoints(ArrayList<Bowler> arrayList) {
        Collections.sort(arrayList, new Comparator<Bowler>() {
            public int compare(Bowler bowler, Bowler bowler2) {
                return bowler2.getPoints() - bowler.getPoints();
            }
        });
    }

    public static void sortBowlerOnWickets(ArrayList<Bowler> arrayList) {
        Collections.sort(arrayList, new Comparator<Bowler>() {
            public int compare(Bowler bowler, Bowler bowler2) {
                return bowler2.getWickets() - bowler.getWickets();
            }
        });
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0056, code lost:
        r4.setWides(1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void setStatsToBowlerFromOver(apps.shehryar.com.cricketscroingappPro.Bowler.Bowler r4, java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.Overs.Over> r5) throws java.lang.Exception {
        /*
            r4.resetDots()
            r4.resetFours()
            r4.resetNos()
            r4.resetWides()
            r4.resetSixes()
            java.util.Iterator r5 = r5.iterator()
        L_0x0013:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L_0x006a
            java.lang.Object r0 = r5.next()
            apps.shehryar.com.cricketscroingappPro.Overs.Over r0 = (apps.shehryar.com.cricketscroingappPro.Overs.Over) r0
            java.lang.String r1 = r0.getBowler()
            java.lang.String r2 = r4.getName()
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0013
            java.util.ArrayList<java.lang.Integer> r0 = r0.perballScore
            java.util.Iterator r0 = r0.iterator()
        L_0x0033:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0013
            java.lang.Object r1 = r0.next()
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            r2 = 1
            if (r1 == 0) goto L_0x0066
            r3 = 4
            if (r1 == r3) goto L_0x0062
            r3 = 6
            if (r1 == r3) goto L_0x005e
            switch(r1) {
                case 11: goto L_0x005a;
                case 12: goto L_0x005a;
                case 13: goto L_0x005a;
                case 14: goto L_0x005a;
                case 15: goto L_0x005a;
                case 16: goto L_0x005a;
                case 17: goto L_0x005a;
                case 18: goto L_0x005a;
                default: goto L_0x004f;
            }
        L_0x004f:
            switch(r1) {
                case 21: goto L_0x0056;
                case 22: goto L_0x0056;
                case 23: goto L_0x0056;
                case 24: goto L_0x0056;
                case 25: goto L_0x0056;
                case 26: goto L_0x0056;
                case 27: goto L_0x0056;
                case 28: goto L_0x0056;
                default: goto L_0x0052;
            }
        L_0x0052:
            switch(r1) {
                case 61: goto L_0x0056;
                case 62: goto L_0x005a;
                default: goto L_0x0055;
            }
        L_0x0055:
            goto L_0x0033
        L_0x0056:
            r4.setWides(r2)
            goto L_0x0033
        L_0x005a:
            r4.setNos(r2)
            goto L_0x0033
        L_0x005e:
            r4.setSixes(r2)
            goto L_0x0033
        L_0x0062:
            r4.setFours(r2)
            goto L_0x0033
        L_0x0066:
            r4.setDots(r2)
            goto L_0x0033
        L_0x006a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: apps.shehryar.com.cricketscroingappPro.Bowler.BowlerHelper.setStatsToBowlerFromOver(apps.shehryar.com.cricketscroingappPro.Bowler.Bowler, java.util.ArrayList):void");
    }

    public static void showBowlerDetailsDialog(Activity activity, Bowler bowler, ArrayList<Over> arrayList, Match match) throws Exception {
        BowlerDetailsDialog bowlerDetailsDialog = new BowlerDetailsDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("bowler", bowler);
        bundle.putSerializable(DBHelper.TABLE_OVERS, arrayList);
        bundle.putSerializable(DBHelper.TABLE_MATCH, match);
        bowlerDetailsDialog.setArguments(bundle);
        bowlerDetailsDialog.show(activity.getFragmentManager(), "bowler");
    }

    public static void showBowlerRankingDialog(Bowler bowler, Activity activity, ArrayList<Long> arrayList) {
        DialogBowlerRanking dialogBowlerRanking = new DialogBowlerRanking();
        Bundle bundle = new Bundle();
        bundle.putSerializable("bowler", bowler);
        bundle.putSerializable("allTeamIds", arrayList);
        dialogBowlerRanking.setArguments(bundle);
        dialogBowlerRanking.show(activity.getFragmentManager(), "batsman rank dialog");
    }

    public static Bowler changeBowlerMaiderOvers(Bowler bowler, Over over) {
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= over.perballScore.size()) {
                z = true;
                break;
            }
            int intValue = over.perballScore.get(i).intValue();
            if ((intValue > 0 && intValue <= 8) || ((intValue >= 11 && intValue <= 18) || ((intValue >= 21 && intValue <= 28) || intValue == 61 || intValue == 62))) {
                break;
            }
            i++;
        }
        if (z) {
            bowler.setMaidens(1);
            over.maiden = true;
        }
        return bowler;
    }
}
