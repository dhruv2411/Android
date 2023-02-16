package apps.shehryar.com.cricketscroingappPro.Overs;

import apps.shehryar.com.cricketscroingappPro.Match.Match;
import java.util.Iterator;

public class OverHelper {
    public static boolean checkRunExtra(int i) {
        if (i < 21 || i > 28) {
            return (i >= 11 && i <= 18) || i == 61 || i == 62;
        }
        return true;
    }

    public static void addOverPerBallScore(Match match, Over over, int i, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        if (z5) {
            if (z) {
                over.perballScore.add(61);
            } else if (z2) {
                over.perballScore.add(62);
            } else {
                over.perballScore.add(60);
            }
        } else if (z2) {
            switch (i) {
                case 1:
                    if (match.isNoRunForNo()) {
                        over.perballScore.add(12);
                        return;
                    } else {
                        over.perballScore.add(11);
                        return;
                    }
                case 2:
                    if (match.isNoRunForNo()) {
                        over.perballScore.add(13);
                        return;
                    } else {
                        over.perballScore.add(12);
                        return;
                    }
                case 3:
                    if (match.isNoRunForNo()) {
                        over.perballScore.add(14);
                        return;
                    } else {
                        over.perballScore.add(13);
                        return;
                    }
                case 4:
                    if (match.isNoRunForNo()) {
                        over.perballScore.add(15);
                        return;
                    } else {
                        over.perballScore.add(14);
                        return;
                    }
                case 5:
                    if (match.isNoRunForNo()) {
                        over.perballScore.add(16);
                        return;
                    } else {
                        over.perballScore.add(15);
                        return;
                    }
                case 6:
                    if (match.isNoRunForNo()) {
                        over.perballScore.add(17);
                        return;
                    } else {
                        over.perballScore.add(16);
                        return;
                    }
                case 7:
                    over.perballScore.add(17);
                    return;
                default:
                    return;
            }
        } else if (z) {
            switch (i) {
                case 1:
                    if (match.isNoRunForWide()) {
                        over.perballScore.add(22);
                        return;
                    } else {
                        over.perballScore.add(21);
                        return;
                    }
                case 2:
                    if (match.isNoRunForWide()) {
                        over.perballScore.add(23);
                        return;
                    } else {
                        over.perballScore.add(22);
                        return;
                    }
                case 3:
                    if (match.isNoRunForWide()) {
                        over.perballScore.add(24);
                        return;
                    } else {
                        over.perballScore.add(23);
                        return;
                    }
                case 4:
                    if (match.isNoRunForWide()) {
                        over.perballScore.add(25);
                        return;
                    } else {
                        over.perballScore.add(24);
                        return;
                    }
                case 5:
                    if (match.isNoRunForWide()) {
                        over.perballScore.add(26);
                        return;
                    } else {
                        over.perballScore.add(25);
                        return;
                    }
                case 6:
                    if (match.isNoRunForWide()) {
                        over.perballScore.add(27);
                        return;
                    } else {
                        over.perballScore.add(26);
                        return;
                    }
                case 7:
                    over.perballScore.add(27);
                    return;
                default:
                    return;
            }
        } else if (z3) {
            switch (i) {
                case 1:
                    over.perballScore.add(41);
                    return;
                case 2:
                    over.perballScore.add(42);
                    return;
                case 3:
                    over.perballScore.add(43);
                    return;
                case 4:
                    over.perballScore.add(44);
                    return;
                case 5:
                    over.perballScore.add(45);
                    return;
                case 6:
                    over.perballScore.add(46);
                    return;
                case 7:
                    over.perballScore.add(47);
                    return;
                default:
                    return;
            }
        } else if (z4) {
            switch (i) {
                case 1:
                    over.perballScore.add(51);
                    return;
                case 2:
                    over.perballScore.add(52);
                    return;
                case 3:
                    over.perballScore.add(53);
                    return;
                case 4:
                    over.perballScore.add(54);
                    return;
                case 5:
                    over.perballScore.add(55);
                    return;
                case 6:
                    over.perballScore.add(56);
                    return;
                case 7:
                    over.perballScore.add(57);
                    return;
                default:
                    return;
            }
        } else {
            over.perballScore.add(Integer.valueOf(i));
        }
    }

    public static Over getCurrentOver(Match match) throws Exception {
        Over over = new Over();
        if (match.getBattingTeam().getOvers_list().size() == 0) {
            match.getBattingTeam().overs_list.add(over);
            return over;
        }
        Over over2 = match.getBattingTeam().getOvers_list().size() > 0 ? match.getBattingTeam().getOvers_list().get(match.getBattingTeam().getOvers_list().size() - 1) : null;
        if ((match.getBattingTeam().getOversPlayed() == 0 && match.getBattingTeam().getOverballs() == 0) || match.getBattingTeam().getOverballs() > 0) {
            return over2;
        }
        if (match.getBattingTeam().getOversPlayed() <= 0 || match.getBattingTeam().getOverballs() != 0) {
            match.getBattingTeam().overs_list.add(over);
            return over;
        } else if (getNoOfLegalBallsInOver(over2) == 0) {
            return over2;
        } else {
            Over over3 = new Over();
            match.getBattingTeam().overs_list.add(over3);
            return over3;
        }
    }

    public static int getNoOfLegalBallsInOver(Over over) {
        Iterator<Integer> it = over.perballScore.iterator();
        int i = 0;
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            if ((intValue <= 10 || intValue >= 19) && !((intValue > 21 && intValue < 29) || intValue == 61 || intValue == 62)) {
                i++;
            }
        }
        return i;
    }

    public static int getValidBallsInAnOver(Over over) {
        Iterator<Integer> it = over.getPerballScore().iterator();
        int i = 0;
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            if ((intValue < 21 || intValue > 28) && !((intValue >= 11 && intValue <= 18) || intValue == 61 || intValue == 62)) {
                i++;
            }
        }
        return i;
    }
}
