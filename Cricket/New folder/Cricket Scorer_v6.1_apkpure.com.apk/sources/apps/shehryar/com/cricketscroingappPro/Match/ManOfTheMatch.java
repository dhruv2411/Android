package apps.shehryar.com.cricketscroingappPro.Match;

import android.content.Context;
import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman;
import apps.shehryar.com.cricketscroingappPro.Batsman.BatsmanDataUpdater;
import apps.shehryar.com.cricketscroingappPro.Batsman.BatsmanHelper;
import apps.shehryar.com.cricketscroingappPro.Bowler.Bowler;
import apps.shehryar.com.cricketscroingappPro.Bowler.BowlerDataUpdater;
import apps.shehryar.com.cricketscroingappPro.Bowler.BowlerHelper;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.Player.Player;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.Formatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class ManOfTheMatch {
    static Player performAllOperations(Context context, String str, Team team, Team team2, long j) {
        return decideTeamAndGetManOfTheMat(str, team, team2);
    }

    static Player decideTeamAndGetManOfTheMat(String str, Team team, Team team2) {
        if (str.equals("No result")) {
            return null;
        }
        if (team2.getScore() < team.getScore()) {
            return selectManOfTheMatch(team);
        }
        if (team2.getScore() == team.getScore()) {
            return selectManOfTheMatch(team, team2);
        }
        if (team2.getScore() > team.getScore()) {
            return selectManOfTheMatch(team2);
        }
        return null;
    }

    public static Player selectManOfTheMatch(Team team) {
        Batsman batsman;
        Bowler bowler;
        Iterator<Batsman> it = team.batsmans_list.iterator();
        while (it.hasNext()) {
            Batsman next = it.next();
            Iterator<Bowler> it2 = team.getBowlers_list().iterator();
            while (it2.hasNext()) {
                Bowler next2 = it2.next();
                if (next.getName().equals(next2.getName())) {
                    next.setWickets(next2.getWickets());
                }
            }
        }
        Iterator<Batsman> it3 = team.getBatsmans_list().iterator();
        while (it3.hasNext()) {
            BatsmanDataUpdater.setPointsToBatsman(it3.next());
        }
        Iterator<Bowler> it4 = team.getBowlers_list().iterator();
        while (it4.hasNext()) {
            BowlerDataUpdater.setPointsToBowler(it4.next());
        }
        BatsmanHelper.sortBatsmanOnPoints(team.getBatsmans_list());
        BowlerHelper.sortBowlersOnPoints(team.getBowlers_list());
        try {
            batsman = team.batsmans_list.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            batsman = null;
        }
        try {
            bowler = team.getBowlers_list().get(0);
        } catch (Exception e2) {
            e2.printStackTrace();
            bowler = null;
        }
        if (bowler != null) {
            if (bowler.getWickets() >= 5) {
                try {
                    return new Player(bowler);
                } catch (Exception e3) {
                    e3.printStackTrace();
                    return null;
                }
            } else if (batsman.getPoints() > bowler.getPoints()) {
                try {
                    return new Player(batsman);
                } catch (Exception e4) {
                    e4.printStackTrace();
                    return null;
                }
            } else {
                try {
                    return new Player(bowler);
                } catch (Exception e5) {
                    e5.printStackTrace();
                    return null;
                }
            }
        } else if (batsman != null) {
            return new Player(batsman);
        } else {
            return null;
        }
    }

    private static Player selectManOfTheMatch(Team team, Team team2) {
        Team maximumRunsScorer = getMaximumRunsScorer(team, team2);
        Team maximumWicketTakers = getMaximumWicketTakers(team, team2);
        if (maximumRunsScorer.getBatsmans_list().get(0).getTotalScore() >= 100 && maximumWicketTakers.getBowlers_list().get(0).getWickets() < 5) {
            return new Player(maximumRunsScorer.getBatsmans_list().get(0));
        }
        if (maximumRunsScorer.getBatsmans_list().get(0).getTotalScore() >= 100 || maximumWicketTakers.getBowlers_list().get(0).getWickets() <= 5) {
            return new Player(maximumRunsScorer.getBatsmans_list().get(0));
        }
        return new Player(maximumWicketTakers.getBowlers_list().get(0));
    }

    private static Batsman getMaximumRunsScorer(ArrayList<Batsman> arrayList) {
        Collections.sort(arrayList, new Comparator<Batsman>() {
            public int compare(Batsman batsman, Batsman batsman2) {
                return batsman2.getTotalScore() - batsman.getTotalScore();
            }
        });
        if (arrayList.size() > 0) {
            return arrayList.get(0);
        }
        return null;
    }

    private static Team getMaximumRunsScorer(Team team, Team team2) {
        Collections.sort(team.getBatsmans_list(), new Comparator<Batsman>() {
            public int compare(Batsman batsman, Batsman batsman2) {
                return batsman2.getTotalScore() - batsman.getTotalScore();
            }
        });
        Collections.sort(team2.getBatsmans_list(), new Comparator<Batsman>() {
            public int compare(Batsman batsman, Batsman batsman2) {
                return batsman2.getTotalScore() - batsman.getTotalScore();
            }
        });
        return team.getBatsmans_list().get(0).getTotalScore() > team2.getBatsmans_list().get(0).getTotalScore() ? team : team2;
    }

    private static Bowler getMaximumWicketTakers(ArrayList<Bowler> arrayList) {
        Collections.sort(arrayList, new Comparator<Bowler>() {
            public int compare(Bowler bowler, Bowler bowler2) {
                return bowler2.getWickets() - bowler.getWickets();
            }
        });
        if (arrayList.size() > 0) {
            return arrayList.get(0);
        }
        return null;
    }

    private static Team getMaximumWicketTakers(Team team, Team team2) {
        Collections.sort(team.getBowlers_list(), new Comparator<Bowler>() {
            public int compare(Bowler bowler, Bowler bowler2) {
                return bowler2.getWickets() - bowler.getWickets();
            }
        });
        Collections.sort(team2.getBowlers_list(), new Comparator<Bowler>() {
            public int compare(Bowler bowler, Bowler bowler2) {
                return bowler2.getWickets() - bowler.getWickets();
            }
        });
        return team.getBowlers_list().get(0).getWickets() > team2.getBowlers_list().get(0).getWickets() ? team : team2;
    }

    public static void insertManofTheMatchInDB(Context context, long j, String str, String str2) {
        final Context context2 = context;
        final long j2 = j;
        final String str3 = str;
        final String str4 = str2;
        new Thread(new Runnable() {
            public void run() {
                new DBHelper(context2).insertManofTheMatch(j2, str3, str4);
            }
        }).start();
    }

    public static ArrayList<ManOfTheMatchModel> getAllCandidates(Team team) {
        ManOfTheMatchModel manOfTheMatchModel;
        ArrayList<ManOfTheMatchModel> arrayList = new ArrayList<>();
        Iterator<Batsman> it = team.batsmans_list.iterator();
        while (it.hasNext()) {
            Batsman next = it.next();
            if (next.getTotalScore() > 0) {
                ManOfTheMatchModel manOfTheMatchModel2 = new ManOfTheMatchModel();
                manOfTheMatchModel2.setBatsman(next);
                manOfTheMatchModel2.setBatsmanId(next.getBatsmanID());
                arrayList.add(manOfTheMatchModel2);
            }
        }
        Iterator<Bowler> it2 = team.bowlers_list.iterator();
        while (it2.hasNext()) {
            Bowler next2 = it2.next();
            try {
                manOfTheMatchModel = checkIfManOfTheMatchIsABowlerAlso(next2, arrayList);
            } catch (Exception e) {
                e.printStackTrace();
                manOfTheMatchModel = null;
            }
            if (next2.getWickets() > 0) {
                if (manOfTheMatchModel != null) {
                    manOfTheMatchModel.setBowler(next2);
                    manOfTheMatchModel.setBowlerId(next2.getBowlerID());
                } else {
                    ManOfTheMatchModel manOfTheMatchModel3 = new ManOfTheMatchModel();
                    manOfTheMatchModel3.setBowlerId(next2.getBowlerID());
                    manOfTheMatchModel3.setBowler(next2);
                    arrayList.add(manOfTheMatchModel3);
                }
            }
        }
        return arrayList;
    }

    private static ManOfTheMatchModel checkIfManOfTheMatchIsABowlerAlso(Bowler bowler, ArrayList<ManOfTheMatchModel> arrayList) throws Exception {
        Iterator<ManOfTheMatchModel> it = arrayList.iterator();
        while (it.hasNext()) {
            ManOfTheMatchModel next = it.next();
            if (bowler.getName().equalsIgnoreCase(next.getBatsman().getName())) {
                return next;
            }
        }
        return null;
    }

    public static String getMotmString(Batsman batsman) throws Exception {
        if (batsman.getWickets() > 0) {
            return batsman.getName() + "(" + Formatter.cutNameHalf(batsman.getTeamName().toUpperCase()) + ") for his " + batsman.getTotalScore() + " runs and " + batsman.getWickets() + " wickets";
        }
        return batsman.getName() + "(" + Formatter.cutNameHalf(batsman.getTeamName().toUpperCase()) + ") for his " + batsman.getTotalScore() + " runs ";
    }

    public static String getMotmString(Bowler bowler) throws Exception {
        return bowler.getName() + "(" + Formatter.cutNameHalf(bowler.getTeamName().toUpperCase()) + ") for his " + bowler.getWickets() + " wickets";
    }
}
