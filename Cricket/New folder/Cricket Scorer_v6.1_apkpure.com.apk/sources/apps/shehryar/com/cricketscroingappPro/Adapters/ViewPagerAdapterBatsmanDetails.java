package apps.shehryar.com.cricketscroingappPro.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import apps.shehryar.com.cricketscroingappPro.Batsman.FragmentPlayerDetailHistory;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Model.SharedData;
import java.util.ArrayList;

public class ViewPagerAdapterBatsmanDetails extends FragmentStatePagerAdapter {
    String batsmanName;
    boolean isBatsman;
    ArrayList<Match> matches;
    ArrayList<String> teamNames;

    public int getItemPosition(Object obj) {
        return -2;
    }

    public void setTeamNames(ArrayList<String> arrayList) {
        this.teamNames = arrayList;
        notifyDataSetChanged();
    }

    public ViewPagerAdapterBatsmanDetails(FragmentManager fragmentManager, String str, ArrayList<String> arrayList, ArrayList<Match> arrayList2, boolean z) {
        super(fragmentManager);
        this.batsmanName = str;
        this.teamNames = arrayList;
        this.matches = arrayList2;
        this.isBatsman = z;
    }

    public Fragment getItem(int i) {
        return FragmentPlayerDetailHistory.newInstance(this.batsmanName, this.teamNames.get(i), this.matches, SharedData.getInstance().getSelectedMatchType(), this.isBatsman);
    }

    public int getCount() {
        return this.teamNames.size();
    }

    public CharSequence getPageTitle(int i) {
        return this.teamNames.get(i);
    }
}
