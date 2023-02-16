package apps.shehryar.com.cricketscroingappPro.Batsman;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import apps.shehryar.com.cricketscroingapp.R;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class BatsmanDetailActivity_ViewBinding implements Unbinder {
    private BatsmanDetailActivity target;

    @UiThread
    public BatsmanDetailActivity_ViewBinding(BatsmanDetailActivity batsmanDetailActivity) {
        this(batsmanDetailActivity, batsmanDetailActivity.getWindow().getDecorView());
    }

    @UiThread
    public BatsmanDetailActivity_ViewBinding(BatsmanDetailActivity batsmanDetailActivity, View view) {
        this.target = batsmanDetailActivity;
        batsmanDetailActivity.tabLayout = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tablayout, "field 'tabLayout'", TabLayout.class);
        batsmanDetailActivity.viewPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.viewpager, "field 'viewPager'", ViewPager.class);
        batsmanDetailActivity.bChooseTeams = (Button) Utils.findRequiredViewAsType(view, R.id.btn_choose_teams, "field 'bChooseTeams'", Button.class);
    }

    @CallSuper
    public void unbind() {
        BatsmanDetailActivity batsmanDetailActivity = this.target;
        if (batsmanDetailActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        batsmanDetailActivity.tabLayout = null;
        batsmanDetailActivity.viewPager = null;
        batsmanDetailActivity.bChooseTeams = null;
    }
}
