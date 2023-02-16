package apps.shehryar.com.cricketscroingappPro.Bowler;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import apps.shehryar.com.cricketscroingapp.R;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class BowlerDetailsActiivty_ViewBinding implements Unbinder {
    private BowlerDetailsActiivty target;

    @UiThread
    public BowlerDetailsActiivty_ViewBinding(BowlerDetailsActiivty bowlerDetailsActiivty) {
        this(bowlerDetailsActiivty, bowlerDetailsActiivty.getWindow().getDecorView());
    }

    @UiThread
    public BowlerDetailsActiivty_ViewBinding(BowlerDetailsActiivty bowlerDetailsActiivty, View view) {
        this.target = bowlerDetailsActiivty;
        bowlerDetailsActiivty.tabLayout = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tablayout, "field 'tabLayout'", TabLayout.class);
        bowlerDetailsActiivty.viewPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.viewpager, "field 'viewPager'", ViewPager.class);
        bowlerDetailsActiivty.bChooseTeams = (Button) Utils.findRequiredViewAsType(view, R.id.btn_choose_teams, "field 'bChooseTeams'", Button.class);
    }

    @CallSuper
    public void unbind() {
        BowlerDetailsActiivty bowlerDetailsActiivty = this.target;
        if (bowlerDetailsActiivty == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        bowlerDetailsActiivty.tabLayout = null;
        bowlerDetailsActiivty.viewPager = null;
        bowlerDetailsActiivty.bChooseTeams = null;
    }
}
