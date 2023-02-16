package apps.shehryar.com.cricketscroingappPro.Batsman;

import android.util.Log;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import com.itextpdf.text.pdf.PdfBoolean;

public class BatsmanUtilityFunctionsClass {
    public static boolean checkBatsman(String str, int i, Team team, Team team2) {
        boolean z;
        int i2 = 0;
        if (i == 99) {
            z = false;
            while (i2 < team2.batsmans_list.size()) {
                if (team2.batsmans_list.get(i2).getName().equals(str) && !team2.batsmans_list.get(i2).isOut().equals("Ret")) {
                    z = true;
                }
                i2++;
            }
        } else {
            z = false;
            while (i2 < team.batsmans_list.size()) {
                if (team.batsmans_list.get(i2).getName().equals(str) && !team.batsmans_list.get(i2).isOut().equals("Ret")) {
                    z = true;
                }
                i2++;
            }
        }
        return z;
    }

    public static Batsman getNewOrRetiredBatsman(String str, Team team, DBHelper dBHelper, Match match) {
        DBHelper dBHelper2;
        String str2 = str;
        Team team2 = team;
        DBHelper dBHelper3 = dBHelper;
        Batsman batsman = null;
        boolean z = false;
        for (int i = 0; i < team2.batsmans_list.size(); i++) {
            if (team2.batsmans_list.get(i).getName().equals(str2) && team2.batsmans_list.get(i).isOut().equals("Ret")) {
                batsman = team2.batsmans_list.get(i);
                z = true;
            }
        }
        if (!z) {
            Batsman batsman2 = new Batsman();
            batsman2.setName(str2);
            batsman2.setTeam_Name(team.getName());
            batsman2.setBatsmanID(dBHelper3.insertPlayer(batsman2.getName(), team.getTeamID(), "batsman"));
            dBHelper2 = dBHelper3;
            dBHelper3.insertBatsmanScore(team.getTeamID(), match.getMatchID(), batsman2.getBatsmanID(), 0, 0, 0, 0, 0, 0, 0, 0, PdfBoolean.FALSE);
            batsman = batsman2;
            team2.batsmans_list.add(batsman);
        } else {
            dBHelper2 = dBHelper3;
        }
        Log.e("batsman name", batsman.getName() + batsman.getTotalScore());
        return z ? setBatsmanOutStatusAsNotOut(batsman, dBHelper2, team2, match) : batsman;
    }

    public static Batsman setBatsmanOutStatusAsNotOut(Batsman batsman, DBHelper dBHelper, Team team, Match match) {
        batsman.setOut(PdfBoolean.FALSE);
        batsman.setOutType("");
        dBHelper.UpdateBatsmanScore(team.getTeamID(), match.getMatchID(), batsman);
        return batsman;
    }
}
