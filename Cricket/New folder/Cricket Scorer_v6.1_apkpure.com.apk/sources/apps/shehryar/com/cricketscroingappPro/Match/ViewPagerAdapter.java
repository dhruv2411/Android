package apps.shehryar.com.cricketscroingappPro.Match;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import apps.shehryar.com.cricketscroingappPro.Team.PreviousMatchDetailTeamFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    Context context;
    Match match;

    public ViewPagerAdapter(FragmentManager fragmentManager, Context context2, Match match2) {
        super(fragmentManager);
        this.context = context2;
        this.match = match2;
    }

    public Fragment getItem(int i) {
        if (!this.match.isTestMatch) {
            switch (i) {
                case 0:
                    return new PreviousMatchDetailTeamFragment(this.context, this.match.getTeam1(), this.match.getTeam2(), this.match);
                case 1:
                    return new PreviousMatchDetailTeamFragment(this.context, this.match.getTeam2(), this.match.getTeam1(), this.match);
                default:
                    return null;
            }
        } else {
            switch (i) {
                case 0:
                    return new PreviousMatchDetailTeamFragment(this.context, this.match.getTeam1(), this.match.getTeam2(), this.match);
                case 1:
                    return new PreviousMatchDetailTeamFragment(this.context, this.match.getTeam2(), this.match.getTeam1(), this.match);
                case 2:
                    return new PreviousMatchDetailTeamFragment(this.context, this.match.getTeam3(), this.match.getTeam4(), this.match);
                case 3:
                    return new PreviousMatchDetailTeamFragment(this.context, this.match.getTeam4(), this.match.getTeam3(), this.match);
                default:
                    return null;
            }
        }
    }

    public int getCount() {
        return !this.match.isTestMatch ? 2 : 4;
    }

    public CharSequence getPageTitle(int i) {
        if (!this.match.isTestMatch) {
            switch (i) {
                case 0:
                    return this.match.getTeam1().getName();
                case 1:
                    return this.match.getTeam2().getName();
                default:
                    return null;
            }
        } else {
            switch (i) {
                case 0:
                    return this.match.getTeam1().getName();
                case 1:
                    return this.match.getTeam2().getName();
                case 2:
                    return this.match.getTeam3().getName();
                case 3:
                    return this.match.getTeam4().getName();
                default:
                    return null;
            }
        }
    }
}
