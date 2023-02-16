package apps.shehryar.com.cricketscroingappPro.Partenership;

import android.util.Log;
import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman;
import apps.shehryar.com.cricketscroingappPro.Overs.Over;
import java.util.ArrayList;
import java.util.Iterator;

public class PartenershipHelper {
    private static boolean checkPerBallScoreExtraOrNot(int i) {
        return (i >= 11 && i <= 18) || i >= 21 || i <= 28;
    }

    private static int getScoreFromOverPerBallValue(int i) {
        if (i == 11 || i == 21 || i == 51 || i == 41) {
            return 1;
        }
        if (i == 12 || i == 22 || i == 52 || i == 42) {
            return 2;
        }
        if (i == 13 || i == 23 || i == 53 || i == 43) {
            return 3;
        }
        if (i == 14 || i == 24 || i == 54 || i == 44) {
            return 4;
        }
        if (i == 15 || i == 25 || i == 55 || i == 45) {
            return 5;
        }
        if (i == 16 || i == 26 || i == 56 || i == 46) {
            return 6;
        }
        if (i == 17 || i == 27 || i == 57 || i == 47) {
            return 7;
        }
        if (i == 18 || i == 28 || i == 58 || i == 48) {
            return 8;
        }
        if (i == 20) {
            return 0;
        }
        return i;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x01a7  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x025b A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0266  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x026a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.Partenership.Partenership> getAllPartenerships(java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.Partenership.Partenership> r8, java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.FallOfWickets> r9, java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.Batsman.Batsman> r10, int r11) {
        /*
            apps.shehryar.com.cricketscroingappPro.Partenership.Partenership r0 = new apps.shehryar.com.cricketscroingappPro.Partenership.Partenership
            r0.<init>()
            boolean r1 = r9.isEmpty()
            r2 = 1
            if (r1 == 0) goto L_0x0019
            boolean r1 = r10.isEmpty()
            if (r1 != 0) goto L_0x0018
            int r1 = r10.size()
            if (r1 != r2) goto L_0x0019
        L_0x0018:
            return r8
        L_0x0019:
            boolean r1 = r9.isEmpty()
            r3 = 0
            if (r1 == 0) goto L_0x0285
            boolean r1 = r8.isEmpty()
            if (r1 == 0) goto L_0x00cd
            r0.setTotalPartenership(r11)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r1 = r0.getBatsman1()
            java.lang.Object r4 = r10.get(r3)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r4 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r4
            java.lang.String r4 = r4.getName()
            r1.setName(r4)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r1 = r0.getBatsman1()
            java.lang.Object r4 = r10.get(r3)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r4 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r4
            int r4 = r4.getTotalScore()
            r1.setScore(r4)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r1 = r0.getBatsman1()
            java.lang.Object r4 = r10.get(r3)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r4 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r4
            int r4 = r4.getTotalScore()
            r1.setTotalScore(r4)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r1 = r0.getBatsman1()
            java.lang.Object r4 = r10.get(r3)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r4 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r4
            int r4 = r4.getBallsfaced()
            r1.setBalls(r4)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r1 = r0.getBatsman2()
            java.lang.Object r4 = r10.get(r2)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r4 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r4
            java.lang.String r4 = r4.getName()
            r1.setName(r4)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r1 = r0.getBatsman2()
            java.lang.Object r4 = r10.get(r2)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r4 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r4
            int r4 = r4.getTotalScore()
            r1.setScore(r4)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r1 = r0.getBatsman2()
            java.lang.Object r4 = r10.get(r2)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r4 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r4
            int r4 = r4.getTotalScore()
            r1.setTotalScore(r4)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r1 = r0.getBatsman2()
            java.lang.Object r2 = r10.get(r2)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r2 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r2
            int r2 = r2.getBallsfaced()
            r1.setBalls(r2)
            r8.add(r0)
            r10.remove(r3)
            r10.remove(r3)
            getAllPartenerships(r8, r9, r10, r11)     // Catch:{ Exception -> 0x00bf }
            goto L_0x045e
        L_0x00bf:
            r9 = move-exception
            r9.printStackTrace()
            if (r8 == 0) goto L_0x00c6
            return r8
        L_0x00c6:
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            goto L_0x045e
        L_0x00cd:
            int r1 = r8.size()
            int r1 = r1 - r2
            java.lang.Object r1 = r8.get(r1)
            apps.shehryar.com.cricketscroingappPro.Partenership.Partenership r1 = (apps.shehryar.com.cricketscroingappPro.Partenership.Partenership) r1
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r4 = r1.getBatsman1()
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r1 = r1.getBatsman2()
            int r5 = getAllPartenershipsScores(r8)
            int r5 = r11 - r5
            r0.setTotalPartenership(r5)
            int r5 = r10.size()
            if (r5 <= 0) goto L_0x01a0
            java.lang.Object r5 = r10.get(r3)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r5 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r5
            java.lang.String r5 = r5.getName()
            java.lang.String r6 = r4.getName()
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x0148
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r5 = r0.getBatsman1()
            java.lang.String r6 = r4.getName()
            r5.setName(r6)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r5 = r0.getBatsman1()
            java.lang.Object r6 = r10.get(r3)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r6 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r6
            int r6 = r6.getTotalScore()
            int r7 = r4.getTotalScore()
            int r6 = r6 - r7
            r5.setScore(r6)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r5 = r0.getBatsman2()
            java.lang.Object r6 = r10.get(r2)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r6 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r6
            java.lang.String r6 = r6.getName()
            r5.setName(r6)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r5 = r0.getBatsman2()
            java.lang.Object r6 = r10.get(r2)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r6 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r6
            int r6 = r6.getTotalScore()
            r5.setScore(r6)
        L_0x0146:
            r5 = r2
            goto L_0x01a1
        L_0x0148:
            java.lang.Object r5 = r10.get(r3)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r5 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r5
            java.lang.String r5 = r5.getName()
            java.lang.String r6 = r1.getName()
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x01a0
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r5 = r0.getBatsman1()
            java.lang.String r6 = r1.getName()
            r5.setName(r6)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r5 = r0.getBatsman1()
            java.lang.Object r6 = r10.get(r3)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r6 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r6
            int r6 = r6.getTotalScore()
            int r7 = r1.getTotalScore()
            int r6 = r6 - r7
            r5.setScore(r6)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r5 = r0.getBatsman2()
            java.lang.Object r6 = r10.get(r2)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r6 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r6
            java.lang.String r6 = r6.getName()
            r5.setName(r6)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r5 = r0.getBatsman2()
            java.lang.Object r6 = r10.get(r2)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r6 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r6
            int r6 = r6.getTotalScore()
            r5.setScore(r6)
            goto L_0x0146
        L_0x01a0:
            r5 = r3
        L_0x01a1:
            int r6 = r10.size()
            if (r6 <= r2) goto L_0x0258
            java.lang.Object r6 = r10.get(r2)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r6 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r6
            java.lang.String r6 = r6.getName()
            java.lang.String r7 = r4.getName()
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x0200
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r1 = r0.getBatsman1()
            java.lang.String r6 = r4.getName()
            r1.setName(r6)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r1 = r0.getBatsman1()
            java.lang.Object r6 = r10.get(r2)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r6 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r6
            int r6 = r6.getTotalScore()
            int r4 = r4.getTotalScore()
            int r6 = r6 - r4
            r1.setScore(r6)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r1 = r0.getBatsman2()
            java.lang.Object r4 = r10.get(r3)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r4 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r4
            java.lang.String r4 = r4.getName()
            r1.setName(r4)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r1 = r0.getBatsman2()
            java.lang.Object r4 = r10.get(r3)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r4 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r4
            int r4 = r4.getTotalScore()
            r1.setScore(r4)
        L_0x01fe:
            r1 = r2
            goto L_0x0259
        L_0x0200:
            java.lang.Object r6 = r10.get(r2)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r6 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r6
            java.lang.String r6 = r6.getName()
            java.lang.String r7 = r1.getName()
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x0258
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r6 = r0.getBatsman1()
            java.lang.String r1 = r1.getName()
            r6.setName(r1)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r1 = r0.getBatsman1()
            java.lang.Object r6 = r10.get(r2)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r6 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r6
            int r6 = r6.getTotalScore()
            int r4 = r4.getTotalScore()
            int r6 = r6 - r4
            r1.setScore(r6)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r1 = r0.getBatsman2()
            java.lang.Object r4 = r10.get(r3)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r4 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r4
            java.lang.String r4 = r4.getName()
            r1.setName(r4)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r1 = r0.getBatsman2()
            java.lang.Object r4 = r10.get(r3)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r4 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r4
            int r4 = r4.getTotalScore()
            r1.setScore(r4)
            goto L_0x01fe
        L_0x0258:
            r1 = r3
        L_0x0259:
            if (r5 == 0) goto L_0x0264
            if (r1 == 0) goto L_0x0264
            r10.remove(r3)
            r10.remove(r3)
            goto L_0x026f
        L_0x0264:
            if (r5 == 0) goto L_0x026a
            r10.remove(r3)
            goto L_0x026f
        L_0x026a:
            if (r1 == 0) goto L_0x026f
            r10.remove(r2)
        L_0x026f:
            r8.add(r0)
            getAllPartenerships(r8, r9, r10, r11)     // Catch:{ Exception -> 0x0277 }
            goto L_0x045e
        L_0x0277:
            r9 = move-exception
            r9.printStackTrace()
            if (r8 == 0) goto L_0x027e
            return r8
        L_0x027e:
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            goto L_0x045e
        L_0x0285:
            java.lang.Object r1 = r9.get(r3)
            apps.shehryar.com.cricketscroingappPro.FallOfWickets r1 = (apps.shehryar.com.cricketscroingappPro.FallOfWickets) r1
            boolean r4 = r8.isEmpty()
            if (r4 == 0) goto L_0x03ca
            int r4 = r1.getScore()
            r0.setTotalPartenership(r4)
            java.lang.Object r4 = r10.get(r3)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r4 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r4
            java.lang.String r4 = r4.getName()
            java.lang.String r5 = r1.getBatsmanName()
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x031e
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r4 = r0.getBatsman2()
            java.lang.Object r5 = r10.get(r3)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r5 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r5
            java.lang.String r5 = r5.getName()
            r4.setName(r5)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r4 = r0.getBatsman2()
            java.lang.Object r5 = r10.get(r3)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r5 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r5
            int r5 = r5.getTotalScore()
            r4.setScore(r5)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r4 = r0.getBatsman2()
            java.lang.Object r5 = r10.get(r3)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r5 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r5
            int r5 = r5.getTotalScore()
            r4.setTotalScore(r5)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r4 = r0.getBatsman1()
            java.lang.Object r5 = r10.get(r2)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r5 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r5
            java.lang.String r5 = r5.getName()
            r4.setName(r5)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r4 = r0.getBatsman1()
            int r5 = r0.getTotalPartenership()
            java.lang.Object r6 = r10.get(r3)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r6 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r6
            int r6 = r6.getTotalScore()
            int r5 = r5 - r6
            r4.setScore(r5)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r4 = r0.getBatsman1()
            int r5 = r0.getTotalPartenership()
            java.lang.Object r6 = r10.get(r3)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r6 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r6
            int r6 = r6.getTotalScore()
            int r5 = r5 - r6
            r4.setTotalScore(r5)
            r4 = r2
            goto L_0x031f
        L_0x031e:
            r4 = r3
        L_0x031f:
            java.lang.Object r5 = r10.get(r2)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r5 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r5
            java.lang.String r5 = r5.getName()
            java.lang.String r1 = r1.getBatsmanName()
            boolean r1 = r5.equals(r1)
            if (r1 == 0) goto L_0x03a5
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r1 = r0.getBatsman2()
            java.lang.Object r5 = r10.get(r2)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r5 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r5
            java.lang.String r5 = r5.getName()
            r1.setName(r5)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r1 = r0.getBatsman2()
            java.lang.Object r5 = r10.get(r2)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r5 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r5
            int r5 = r5.getTotalScore()
            r1.setScore(r5)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r1 = r0.getBatsman2()
            java.lang.Object r5 = r10.get(r2)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r5 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r5
            int r5 = r5.getTotalScore()
            r1.setTotalScore(r5)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r1 = r0.getBatsman1()
            java.lang.Object r5 = r10.get(r3)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r5 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r5
            java.lang.String r5 = r5.getName()
            r1.setName(r5)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r1 = r0.getBatsman1()
            int r5 = r0.getTotalPartenership()
            java.lang.Object r6 = r10.get(r2)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r6 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r6
            int r6 = r6.getTotalScore()
            int r5 = r5 - r6
            r1.setScore(r5)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r1 = r0.getBatsman1()
            int r5 = r0.getTotalPartenership()
            java.lang.Object r6 = r10.get(r2)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r6 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r6
            int r6 = r6.getTotalScore()
            int r5 = r5 - r6
            r1.setTotalScore(r5)
            r1 = r2
            goto L_0x03a6
        L_0x03a5:
            r1 = r3
        L_0x03a6:
            if (r4 == 0) goto L_0x03ac
            r10.remove(r3)
            goto L_0x03b1
        L_0x03ac:
            if (r1 == 0) goto L_0x03b1
            r10.remove(r2)
        L_0x03b1:
            r9.remove(r3)
            r8.add(r0)
            getAllPartenerships(r8, r9, r10, r11)     // Catch:{ Exception -> 0x03bc }
            goto L_0x045e
        L_0x03bc:
            r9 = move-exception
            r9.printStackTrace()
            if (r8 == 0) goto L_0x03c3
            return r8
        L_0x03c3:
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            goto L_0x045e
        L_0x03ca:
            int r4 = r8.size()
            int r4 = r4 - r2
            java.lang.Object r4 = r8.get(r4)
            apps.shehryar.com.cricketscroingappPro.Partenership.Partenership r4 = (apps.shehryar.com.cricketscroingappPro.Partenership.Partenership) r4
            int r4 = r1.getScore()
            int r5 = getAllPartenershipsScores(r8)
            int r4 = r4 - r5
            r0.setTotalPartenership(r4)
            java.lang.String r4 = "partenership"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            int r6 = r0.getTotalPartenership()
            r5.append(r6)
            java.lang.String r6 = ""
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            android.util.Log.i(r4, r5)
            java.lang.Object r4 = r10.get(r3)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r4 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r4
            java.lang.String r4 = r4.getName()
            java.lang.String r5 = r1.getBatsmanName()
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x0422
            java.lang.String r1 = "batsman out name"
            java.lang.Object r2 = r10.get(r3)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r2 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r2
            java.lang.String r2 = r2.getName()
            android.util.Log.i(r1, r2)
            r10.remove(r3)
            goto L_0x0448
        L_0x0422:
            java.lang.Object r4 = r10.get(r2)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r4 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r4
            java.lang.String r4 = r4.getName()
            java.lang.String r1 = r1.getBatsmanName()
            boolean r1 = r4.equals(r1)
            if (r1 == 0) goto L_0x0448
            java.lang.String r1 = "batsman out name"
            java.lang.Object r4 = r10.get(r2)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r4 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r4
            java.lang.String r4 = r4.getName()
            android.util.Log.i(r1, r4)
            r10.remove(r2)
        L_0x0448:
            r9.remove(r3)
            r8.add(r0)
            getAllPartenerships(r8, r9, r10, r11)     // Catch:{ Exception -> 0x0452 }
            goto L_0x045e
        L_0x0452:
            r9 = move-exception
            r9.printStackTrace()
            if (r8 == 0) goto L_0x0459
            return r8
        L_0x0459:
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
        L_0x045e:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: apps.shehryar.com.cricketscroingappPro.Partenership.PartenershipHelper.getAllPartenerships(java.util.ArrayList, java.util.ArrayList, java.util.ArrayList, int):java.util.ArrayList");
    }

    /* JADX WARNING: Removed duplicated region for block: B:74:0x031c  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0368 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0373  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0377  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.Partenership.Partenership> getAllPartenerships2(java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.Partenership.Partenership> r13, apps.shehryar.com.cricketscroingappPro.Match.Match r14, apps.shehryar.com.cricketscroingappPro.Team.Team r15) {
        /*
            java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.Overs.Over> r0 = r15.overs_list
            java.util.ArrayList r0 = getExtrasInAPartenershipFromOvers(r0)
            java.util.Iterator r1 = r0.iterator()
        L_0x000a:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0031
            java.lang.Object r2 = r1.next()
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            java.lang.String r3 = "extra"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r2)
            java.lang.String r2 = ""
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            android.util.Log.i(r3, r2)
            goto L_0x000a
        L_0x0031:
            java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.FallOfWickets> r1 = r15.fallOfWicketses
            java.util.ArrayList<apps.shehryar.com.cricketscroingappPro.Batsman.Batsman> r2 = r15.batsmans_list
            int r3 = r15.getScore()
            int r15 = apps.shehryar.com.cricketscroingappPro.Utility_Classes.UtilityFunctions.getTotalBallsFromOvers(r15, r14)
            r4 = 0
            r5 = r4
        L_0x003f:
            int r6 = r1.size()
            r7 = 1
            int r6 = r6 + r7
            if (r5 >= r6) goto L_0x0383
            apps.shehryar.com.cricketscroingappPro.Partenership.Partenership r6 = new apps.shehryar.com.cricketscroingappPro.Partenership.Partenership
            r6.<init>()
            boolean r8 = r1.isEmpty()
            if (r8 != 0) goto L_0x01ed
            int r8 = r1.size()
            if (r5 < r8) goto L_0x005a
            goto L_0x01ed
        L_0x005a:
            java.lang.Object r8 = r1.get(r5)
            apps.shehryar.com.cricketscroingappPro.FallOfWickets r8 = (apps.shehryar.com.cricketscroingappPro.FallOfWickets) r8
            boolean r9 = r13.isEmpty()
            if (r9 == 0) goto L_0x0103
            int r9 = r8.getScore()
            r6.setTotalPartenership(r9)
            apps.shehryar.com.cricketscroingappPro.Team.Team r9 = new apps.shehryar.com.cricketscroingappPro.Team.Team
            r9.<init>()
            int r10 = r8.getOverno()
            r9.setOversPlayed(r10)
            int r10 = r8.getBall()
            r9.setOverballs(r10)
            int r9 = apps.shehryar.com.cricketscroingappPro.Utility_Classes.UtilityFunctions.getTotalBallsFromOvers(r9, r14)
            r6.setTotalPartenershipBalls(r9)
            java.lang.Object r9 = r2.get(r4)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r9 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r9
            java.lang.String r9 = r9.getName()
            java.lang.String r10 = r8.getBatsmanName()
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x00bc
            java.lang.Object r9 = r0.get(r5)     // Catch:{ Exception -> 0x00a6 }
            java.lang.Integer r9 = (java.lang.Integer) r9     // Catch:{ Exception -> 0x00a6 }
            int r9 = r9.intValue()     // Catch:{ Exception -> 0x00a6 }
            goto L_0x00ab
        L_0x00a6:
            r9 = move-exception
            r9.printStackTrace()
            r9 = r4
        L_0x00ab:
            java.lang.Object r10 = r2.get(r4)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r10 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r10
            java.lang.Object r11 = r2.get(r7)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r11 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r11
            assignbatsmanScoresInPartenershipFromFOW(r10, r11, r6, r9)
            r9 = r7
            goto L_0x00bd
        L_0x00bc:
            r9 = r4
        L_0x00bd:
            java.lang.Object r10 = r2.get(r7)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r10 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r10
            java.lang.String r10 = r10.getName()
            java.lang.String r8 = r8.getBatsmanName()
            boolean r8 = r10.equals(r8)
            if (r8 == 0) goto L_0x00f2
            java.lang.Object r8 = r0.get(r5)     // Catch:{ Exception -> 0x00dc }
            java.lang.Integer r8 = (java.lang.Integer) r8     // Catch:{ Exception -> 0x00dc }
            int r8 = r8.intValue()     // Catch:{ Exception -> 0x00dc }
            goto L_0x00e1
        L_0x00dc:
            r8 = move-exception
            r8.printStackTrace()
            r8 = r4
        L_0x00e1:
            java.lang.Object r10 = r2.get(r7)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r10 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r10
            java.lang.Object r11 = r2.get(r4)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r11 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r11
            assignbatsmanScoresInPartenershipFromFOW(r10, r11, r6, r8)
            r8 = r7
            goto L_0x00f3
        L_0x00f2:
            r8 = r4
        L_0x00f3:
            if (r9 == 0) goto L_0x00f9
            r2.remove(r4)
            goto L_0x00fe
        L_0x00f9:
            if (r8 == 0) goto L_0x00fe
            r2.remove(r7)
        L_0x00fe:
            r13.add(r6)
            goto L_0x037f
        L_0x0103:
            int r9 = r13.size()
            int r9 = r9 - r7
            java.lang.Object r9 = r13.get(r9)
            apps.shehryar.com.cricketscroingappPro.Partenership.Partenership r9 = (apps.shehryar.com.cricketscroingappPro.Partenership.Partenership) r9
            int r10 = r8.getScore()
            int r11 = getAllPartenershipsScores(r13)
            int r10 = r10 - r11
            r6.setTotalPartenership(r10)
            apps.shehryar.com.cricketscroingappPro.Team.Team r10 = new apps.shehryar.com.cricketscroingappPro.Team.Team
            r10.<init>()
            int r11 = r8.getOverno()
            r10.setOversPlayed(r11)
            int r11 = r8.getBall()
            r10.setOverballs(r11)
            int r10 = apps.shehryar.com.cricketscroingappPro.Utility_Classes.UtilityFunctions.getTotalBallsFromOvers(r10, r14)
            int r11 = getAllPartenershipBalls(r13)
            int r10 = r10 - r11
            r6.setTotalPartenershipBalls(r10)
            java.lang.String r10 = "partenership"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            int r12 = r6.getTotalPartenership()
            r11.append(r12)
            java.lang.String r12 = ""
            r11.append(r12)
            java.lang.String r11 = r11.toString()
            android.util.Log.i(r10, r11)
            java.lang.Object r10 = r2.get(r4)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r10 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r10
            java.lang.String r10 = r10.getName()
            java.lang.String r11 = r8.getBatsmanName()
            boolean r10 = r10.equals(r11)
            if (r10 == 0) goto L_0x019e
            java.lang.String r8 = "batsman out name"
            java.lang.Object r10 = r2.get(r4)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r10 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r10
            java.lang.String r10 = r10.getName()
            android.util.Log.i(r8, r10)
            java.lang.Object r8 = r0.get(r5)     // Catch:{ Exception -> 0x0181 }
            java.lang.Integer r8 = (java.lang.Integer) r8     // Catch:{ Exception -> 0x0181 }
            int r8 = r8.intValue()     // Catch:{ Exception -> 0x0181 }
            goto L_0x0186
        L_0x0181:
            r8 = move-exception
            r8.printStackTrace()
            r8 = r4
        L_0x0186:
            java.lang.Object r10 = r2.get(r4)     // Catch:{ Exception -> 0x0196 }
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r10 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r10     // Catch:{ Exception -> 0x0196 }
            java.lang.Object r7 = r2.get(r7)     // Catch:{ Exception -> 0x0196 }
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r7 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r7     // Catch:{ Exception -> 0x0196 }
            setBatsmanContribution(r10, r7, r9, r6, r8)     // Catch:{ Exception -> 0x0196 }
            goto L_0x019a
        L_0x0196:
            r7 = move-exception
            r7.printStackTrace()
        L_0x019a:
            r2.remove(r4)
            goto L_0x01e8
        L_0x019e:
            java.lang.Object r10 = r2.get(r7)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r10 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r10
            java.lang.String r10 = r10.getName()
            java.lang.String r8 = r8.getBatsmanName()
            boolean r8 = r10.equals(r8)
            if (r8 == 0) goto L_0x01e8
            java.lang.String r8 = "batsman out name"
            java.lang.Object r10 = r2.get(r7)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r10 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r10
            java.lang.String r10 = r10.getName()
            android.util.Log.i(r8, r10)
            java.lang.Object r8 = r0.get(r5)     // Catch:{ Exception -> 0x01cc }
            java.lang.Integer r8 = (java.lang.Integer) r8     // Catch:{ Exception -> 0x01cc }
            int r8 = r8.intValue()     // Catch:{ Exception -> 0x01cc }
            goto L_0x01d1
        L_0x01cc:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ Exception -> 0x01e1 }
            r8 = r4
        L_0x01d1:
            java.lang.Object r10 = r2.get(r7)     // Catch:{ Exception -> 0x01e1 }
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r10 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r10     // Catch:{ Exception -> 0x01e1 }
            java.lang.Object r11 = r2.get(r4)     // Catch:{ Exception -> 0x01e1 }
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r11 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r11     // Catch:{ Exception -> 0x01e1 }
            setBatsmanContribution(r10, r11, r9, r6, r8)     // Catch:{ Exception -> 0x01e1 }
            goto L_0x01e5
        L_0x01e1:
            r8 = move-exception
            r8.printStackTrace()
        L_0x01e5:
            r2.remove(r7)
        L_0x01e8:
            r13.add(r6)
            goto L_0x037f
        L_0x01ed:
            boolean r8 = r13.isEmpty()
            if (r8 == 0) goto L_0x02a1
            r6.setTotalPartenership(r3)
            java.lang.Object r8 = r2.get(r4)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r8 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r8
            int r8 = r8.getBallsfaced()
            java.lang.Object r9 = r2.get(r7)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r9 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r9
            int r9 = r9.getBallsfaced()
            int r8 = r8 + r9
            r6.setTotalPartenershipBalls(r8)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r8 = r6.getBatsman1()
            java.lang.Object r9 = r2.get(r4)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r9 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r9
            java.lang.String r9 = r9.getName()
            r8.setName(r9)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r8 = r6.getBatsman1()
            java.lang.Object r9 = r2.get(r4)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r9 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r9
            int r9 = r9.getTotalScore()
            r8.setScore(r9)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r8 = r6.getBatsman1()
            java.lang.Object r9 = r2.get(r4)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r9 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r9
            int r9 = r9.getTotalScore()
            r8.setTotalScore(r9)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r8 = r6.getBatsman1()
            java.lang.Object r9 = r2.get(r4)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r9 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r9
            int r9 = r9.getBallsfaced()
            r8.setBalls(r9)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r8 = r6.getBatsman2()
            java.lang.Object r9 = r2.get(r7)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r9 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r9
            java.lang.String r9 = r9.getName()
            r8.setName(r9)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r8 = r6.getBatsman2()
            java.lang.Object r9 = r2.get(r7)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r9 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r9
            int r9 = r9.getTotalScore()
            r8.setScore(r9)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r8 = r6.getBatsman2()
            java.lang.Object r9 = r2.get(r7)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r9 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r9
            int r9 = r9.getTotalScore()
            r8.setTotalScore(r9)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r8 = r6.getBatsman2()
            java.lang.Object r7 = r2.get(r7)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r7 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r7
            int r7 = r7.getBallsfaced()
            r8.setBalls(r7)
            r13.add(r6)
            r2.remove(r4)
            r2.remove(r4)
            goto L_0x037f
        L_0x02a1:
            int r8 = r13.size()
            int r8 = r8 - r7
            java.lang.Object r8 = r13.get(r8)
            apps.shehryar.com.cricketscroingappPro.Partenership.Partenership r8 = (apps.shehryar.com.cricketscroingappPro.Partenership.Partenership) r8
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r9 = r8.getBatsman1()
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r8 = r8.getBatsman2()
            int r10 = getAllPartenershipsScores(r13)
            int r10 = r3 - r10
            r6.setTotalPartenership(r10)
            int r10 = getAllPartenershipBalls(r13)
            int r10 = r15 - r10
            r6.setTotalPartenershipBalls(r10)
            int r10 = r2.size()
            if (r10 <= 0) goto L_0x0315
            java.lang.Object r10 = r2.get(r4)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r10 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r10
            java.lang.String r10 = r10.getName()
            java.lang.String r11 = r9.getName()
            boolean r10 = r10.equals(r11)
            if (r10 == 0) goto L_0x02f1
            java.lang.Object r10 = r2.get(r4)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r10 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r10
            java.lang.Object r11 = r2.get(r7)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r11 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r11
            assignbatsmanScoresInPartenershipFromPrevPartenership(r9, r10, r11, r6)
        L_0x02ef:
            r10 = r7
            goto L_0x0316
        L_0x02f1:
            java.lang.Object r10 = r2.get(r4)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r10 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r10
            java.lang.String r10 = r10.getName()
            java.lang.String r11 = r8.getName()
            boolean r10 = r10.equals(r11)
            if (r10 == 0) goto L_0x0315
            java.lang.Object r10 = r2.get(r4)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r10 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r10
            java.lang.Object r11 = r2.get(r7)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r11 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r11
            assignbatsmanScoresInPartenershipFromPrevPartenership(r8, r10, r11, r6)
            goto L_0x02ef
        L_0x0315:
            r10 = r4
        L_0x0316:
            int r11 = r2.size()
            if (r11 <= r7) goto L_0x0365
            java.lang.Object r11 = r2.get(r7)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r11 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r11
            java.lang.String r11 = r11.getName()
            java.lang.String r12 = r9.getName()
            boolean r11 = r11.equals(r12)
            if (r11 == 0) goto L_0x0341
            java.lang.Object r8 = r2.get(r7)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r8 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r8
            java.lang.Object r11 = r2.get(r4)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r11 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r11
            assignbatsmanScoresInPartenershipFromPrevPartenership(r9, r8, r11, r6)
        L_0x033f:
            r8 = r7
            goto L_0x0366
        L_0x0341:
            java.lang.Object r9 = r2.get(r7)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r9 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r9
            java.lang.String r9 = r9.getName()
            java.lang.String r11 = r8.getName()
            boolean r9 = r9.equals(r11)
            if (r9 == 0) goto L_0x0365
            java.lang.Object r9 = r2.get(r7)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r9 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r9
            java.lang.Object r11 = r2.get(r4)
            apps.shehryar.com.cricketscroingappPro.Batsman.Batsman r11 = (apps.shehryar.com.cricketscroingappPro.Batsman.Batsman) r11
            assignbatsmanScoresInPartenershipFromPrevPartenership(r8, r9, r11, r6)
            goto L_0x033f
        L_0x0365:
            r8 = r4
        L_0x0366:
            if (r10 == 0) goto L_0x0371
            if (r8 == 0) goto L_0x0371
            r2.remove(r4)
            r2.remove(r4)
            goto L_0x037c
        L_0x0371:
            if (r10 == 0) goto L_0x0377
            r2.remove(r4)
            goto L_0x037c
        L_0x0377:
            if (r8 == 0) goto L_0x037c
            r2.remove(r7)
        L_0x037c:
            r13.add(r6)
        L_0x037f:
            int r5 = r5 + 1
            goto L_0x003f
        L_0x0383:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: apps.shehryar.com.cricketscroingappPro.Partenership.PartenershipHelper.getAllPartenerships2(java.util.ArrayList, apps.shehryar.com.cricketscroingappPro.Match.Match, apps.shehryar.com.cricketscroingappPro.Team.Team):java.util.ArrayList");
    }

    private static void assignbatsmanScoresInPartenershipFromPrevPartenership(Batsman batsman, Batsman batsman2, Batsman batsman3, Partenership partenership) {
        partenership.getBatsman1().setName(batsman.getName());
        partenership.getBatsman1().setScore(batsman2.getTotalScore() - batsman.getTotalScore());
        partenership.getBatsman1().setBalls(batsman2.getBallsfaced() - batsman.getBallsfaced());
        partenership.getBatsman1().setBallsfaced(partenership.getBatsman1().getBalls());
        partenership.getBatsman2().setName(batsman3.getName());
        partenership.getBatsman2().setScore(partenership.getTotalPartenership() - partenership.getBatsman1().getScore());
        partenership.getBatsman2().setBalls(partenership.getTotalPartenershipBalls() - partenership.getBatsman1().getBallsfaced());
        partenership.getBatsman2().setBallsfaced(partenership.getBatsman2().getBalls());
    }

    private static void assignbatsmanScoresInPartenershipFromFOW(Batsman batsman, Batsman batsman2, Partenership partenership, int i) {
        partenership.getBatsman2().setName(batsman.getName());
        partenership.getBatsman2().setScore(batsman.getTotalScore());
        partenership.getBatsman2().setTotalScore(batsman.getTotalScore());
        partenership.getBatsman2().setBalls(batsman.getBallsfaced());
        partenership.getBatsman2().setBallsfaced(partenership.getBatsman2().getBalls());
        partenership.getBatsman1().setName(batsman2.getName());
        partenership.getBatsman1().setScore((partenership.getTotalPartenership() - partenership.getBatsman2().getScore()) - i);
        partenership.getBatsman1().setTotalScore((partenership.getTotalPartenership() - partenership.getBatsman2().getScore()) - i);
        partenership.getBatsman1().setBalls(partenership.getTotalPartenershipBalls() - partenership.getBatsman2().getBalls());
        partenership.getBatsman1().setBallsfaced(partenership.getTotalPartenershipBalls() - partenership.getBatsman2().getBalls());
    }

    private static int getAllPartenershipBalls(ArrayList<Partenership> arrayList) {
        try {
            Iterator<Partenership> it = arrayList.iterator();
            int i = 0;
            while (it.hasNext()) {
                i += it.next().getTotalPartenershipBalls();
            }
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static ArrayList<Integer> getExtrasInAPartenershipFromOvers(ArrayList<Over> arrayList) {
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        new Partenership();
        Iterator<Over> it = arrayList.iterator();
        boolean z = false;
        int i = 0;
        while (it.hasNext()) {
            Iterator<Integer> it2 = it.next().getPerballScore().iterator();
            while (it2.hasNext()) {
                int intValue = it2.next().intValue();
                if (intValue == 20 || intValue == 61 || intValue == 62) {
                    if (intValue == 61 || intValue == 62) {
                        i++;
                    }
                    arrayList2.add(Integer.valueOf(i));
                    z = true;
                    i = 0;
                } else {
                    i = getExtraFromPerBallScore(intValue) + i;
                    z = false;
                }
            }
        }
        if (!z) {
            arrayList2.add(Integer.valueOf(i));
        }
        return arrayList2;
    }

    private static ArrayList<Partenership> getPartnershipsFromOvers(ArrayList<Over> arrayList) {
        ArrayList<Partenership> arrayList2 = new ArrayList<>();
        Partenership partenership = new Partenership();
        Iterator<Over> it = arrayList.iterator();
        Partenership partenership2 = partenership;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (it.hasNext()) {
            Iterator<Integer> it2 = it.next().getPerballScore().iterator();
            while (it2.hasNext()) {
                int intValue = it2.next().intValue();
                if (intValue == 20 || intValue == 61 || intValue == 62) {
                    partenership2.setTotalPartenership(i);
                    partenership2.setTotalPartenershipBalls(i2);
                    partenership2.setTotalPartenershipWithOutExtras(i - i3);
                    arrayList2.add(partenership2);
                    partenership2 = new Partenership();
                    i = 0;
                    i2 = 0;
                    i3 = 0;
                }
                i += getScoreFromOverPerBallValue(intValue);
                if (checkPerBallScoreExtraOrNot(intValue)) {
                    i2++;
                }
                i3 += getExtraFromPerBallScore(intValue);
            }
        }
        if (arrayList.size() > 0) {
            Partenership partenership3 = new Partenership();
            partenership3.setTotalPartenership(i);
            partenership3.setTotalPartenershipBalls(i2);
            partenership3.setTotalPartenershipWithOutExtras(i - i3);
            arrayList2.add(partenership3);
        }
        return arrayList2;
    }

    private static int getExtraFromPerBallScore(int i) {
        if ((i < 11 || i > 18) && ((i < 51 || i > 58) && ((i < 41 || i > 48) && (i < 21 || i > 28)))) {
            return 0;
        }
        return i % 10;
    }

    private static void setBatsmanContribution(Batsman batsman, Batsman batsman2, Partenership partenership, Partenership partenership2, int i) throws Exception {
        partenership2.getBatsman2().setName(batsman.getName());
        if (partenership.getBatsman1().getName().equals(batsman.getName())) {
            Log.i("prevpart bat name", partenership.getBatsman1().getName());
            partenership2.getBatsman2().setScore((batsman.getTotalScore() - partenership.getBatsman1().getTotalScore()) - i);
            partenership2.getBatsman2().setBalls(batsman.getBallsfaced() - partenership.getBatsman1().getBallsfaced());
            Log.i("bTS", batsman.getTotalScore() + "");
            Log.i("pbTS", partenership.getBatsman1().getTotalScore() + "");
            partenership2.getBatsman2().setTotalScore(batsman.getTotalScore());
        } else if (partenership.getBatsman2().getName().equals(batsman.getName())) {
            partenership2.getBatsman2().setScore((batsman.getTotalScore() - partenership.getBatsman2().getTotalScore()) - i);
            partenership2.getBatsman2().setBalls(batsman.getBallsfaced() - partenership.getBatsman2().getBallsfaced());
            partenership2.getBatsman2().setTotalScore(batsman.getTotalScore());
            Log.i("bTS", batsman.getTotalScore() + "");
            Log.i("pbTS", partenership.getBatsman1().getTotalScore() + "");
        } else {
            partenership2.getBatsman2().setScore(batsman.getTotalScore());
            partenership2.getBatsman2().setBalls(batsman.getBallsfaced());
            partenership2.getBatsman2().setTotalScore(batsman.getTotalScore());
            Log.i("bTS", batsman.getTotalScore() + "");
            Log.i("pbTS", partenership.getBatsman1().getTotalScore() + "");
        }
        partenership2.getBatsman1().setName(batsman2.getName());
        partenership2.getBatsman1().setScore((partenership2.getTotalPartenership() - partenership2.getBatsman2().getScore()) - i);
        partenership2.getBatsman1().setBalls(partenership2.getTotalPartenershipBalls() - partenership2.getBatsman2().getBalls());
        Log.i("bTS", partenership2.getBatsman1().getScore() + "");
        if (partenership.getBatsman1().getName().equals(batsman2.getName())) {
            partenership2.getBatsman1().setTotalScore(partenership.getBatsman1().getTotalScore() + partenership2.getBatsman1().getScore());
            partenership2.getBatsman1().setBallsfaced(partenership.getBatsman1().getBallsfaced() + partenership2.getBatsman1().getBalls());
            Log.i("bTS", partenership2.getBatsman1().getTotalScore() + "");
        } else if (partenership.getBatsman2().getName().equals(batsman2.getName())) {
            partenership2.getBatsman1().setTotalScore(partenership.getBatsman2().getTotalScore() + partenership2.getBatsman1().getScore());
            partenership2.getBatsman1().setBallsfaced(partenership.getBatsman2().getBallsfaced() + partenership2.getBatsman1().getBalls());
            Log.i("bTS", partenership2.getBatsman1().getTotalScore() + "");
        } else {
            partenership2.getBatsman1().setTotalScore(partenership2.getBatsman1().getScore());
            partenership2.getBatsman1().setBallsfaced(partenership2.getBatsman1().getBalls());
            Log.i("bTS", partenership2.getBatsman1().getScore() + "");
        }
    }

    public static int getAllPartenershipsScores(ArrayList<Partenership> arrayList) {
        try {
            Iterator<Partenership> it = arrayList.iterator();
            int i = 0;
            while (it.hasNext()) {
                i += it.next().getTotalPartenership();
            }
            return i;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
