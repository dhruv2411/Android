package apps.shehryar.com.cricketscroingappPro.Tournament;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import apps.shehryar.com.cricketscroingapp.R;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class FragmentPointsTable_ViewBinding implements Unbinder {
    private FragmentPointsTable target;

    @UiThread
    public FragmentPointsTable_ViewBinding(FragmentPointsTable fragmentPointsTable, View view) {
        this.target = fragmentPointsTable;
        fragmentPointsTable.tabLayout = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tablayout, "field 'tabLayout'", TabLayout.class);
        fragmentPointsTable.viewPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.viewpager, "field 'viewPager'", ViewPager.class);
        fragmentPointsTable.btnChooseStages = (Button) Utils.findRequiredViewAsType(view, R.id.btn_choose_stages, "field 'btnChooseStages'", Button.class);
    }

    @CallSuper
    public void unbind() {
        FragmentPointsTable fragmentPointsTable = this.target;
        if (fragmentPointsTable == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        fragmentPointsTable.tabLayout = null;
        fragmentPointsTable.viewPager = null;
        fragmentPointsTable.btnChooseStages = null;
    }
}
