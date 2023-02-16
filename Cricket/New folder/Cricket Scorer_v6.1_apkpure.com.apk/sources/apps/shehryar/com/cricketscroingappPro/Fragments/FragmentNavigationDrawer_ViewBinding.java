package apps.shehryar.com.cricketscroingappPro.Fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import apps.shehryar.com.cricketscroingapp.R;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class FragmentNavigationDrawer_ViewBinding implements Unbinder {
    private FragmentNavigationDrawer target;

    @UiThread
    public FragmentNavigationDrawer_ViewBinding(FragmentNavigationDrawer fragmentNavigationDrawer, View view) {
        this.target = fragmentNavigationDrawer;
        fragmentNavigationDrawer.rvNavigation = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_navigation, "field 'rvNavigation'", RecyclerView.class);
    }

    @CallSuper
    public void unbind() {
        FragmentNavigationDrawer fragmentNavigationDrawer = this.target;
        if (fragmentNavigationDrawer == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        fragmentNavigationDrawer.rvNavigation = null;
    }
}
