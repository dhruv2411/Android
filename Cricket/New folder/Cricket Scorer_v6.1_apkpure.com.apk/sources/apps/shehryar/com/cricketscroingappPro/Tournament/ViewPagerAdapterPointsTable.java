package apps.shehryar.com.cricketscroingappPro.Tournament;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import apps.shehryar.com.cricketscroingappPro.Fragment_tournament_Teams_Stats;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import java.util.ArrayList;

public class ViewPagerAdapterPointsTable extends FragmentPagerAdapter {
    ArrayList<ArrayList<Team>> allTeamsStats;
    ArrayList<String> stages;

    public ViewPagerAdapterPointsTable(FragmentManager fragmentManager, ArrayList<String> arrayList, ArrayList<ArrayList<Team>> arrayList2) {
        super(fragmentManager);
        this.allTeamsStats = arrayList2;
        this.stages = arrayList;
    }

    public Fragment getItem(int i) {
        return Fragment_tournament_Teams_Stats.newInstance(this.allTeamsStats.get(i));
    }

    public int getCount() {
        return this.stages.size();
    }

    public CharSequence getPageTitle(int i) {
        return this.stages.get(i);
    }
}
