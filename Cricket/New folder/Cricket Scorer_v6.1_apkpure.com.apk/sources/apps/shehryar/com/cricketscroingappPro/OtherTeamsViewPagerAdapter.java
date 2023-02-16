package apps.shehryar.com.cricketscroingappPro;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import java.util.ArrayList;

public class OtherTeamsViewPagerAdapter extends FragmentPagerAdapter {
    ArrayList<Long> allTeamsIds;
    Context context;
    ArrayList<String> playernames;
    String team_name;
    ArrayList<String> team_names;

    public int getCount() {
        return 2;
    }

    public CharSequence getPageTitle(int i) {
        switch (i) {
            case 0:
                return "Batting";
            case 1:
                return "Bowling";
            default:
                return null;
        }
    }

    public OtherTeamsViewPagerAdapter(FragmentManager fragmentManager, Context context2, ArrayList<String> arrayList, ArrayList<String> arrayList2, String str, ArrayList<Long> arrayList3) {
        super(fragmentManager);
        this.context = context2;
        this.playernames = arrayList;
        this.team_names = arrayList2;
        this.team_name = str;
        this.allTeamsIds = arrayList3;
    }

    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new FragmentYourBatting(this.context, this.playernames, this.team_name, (Team) null, this.allTeamsIds);
            case 1:
                return new Fragment_YourBowling_List(this.context, this.playernames, this.team_name, (Team) null, this.allTeamsIds);
            default:
                return null;
        }
    }
}
