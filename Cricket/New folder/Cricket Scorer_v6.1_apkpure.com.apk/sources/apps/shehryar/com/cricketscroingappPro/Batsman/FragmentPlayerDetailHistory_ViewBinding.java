package apps.shehryar.com.cricketscroingappPro.Batsman;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class FragmentPlayerDetailHistory_ViewBinding implements Unbinder {
    private FragmentPlayerDetailHistory target;

    @UiThread
    public FragmentPlayerDetailHistory_ViewBinding(FragmentPlayerDetailHistory fragmentPlayerDetailHistory, View view) {
        this.target = fragmentPlayerDetailHistory;
        fragmentPlayerDetailHistory.rvBatsmanDetails = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_batsman_details, "field 'rvBatsmanDetails'", RecyclerView.class);
        fragmentPlayerDetailHistory.tvNoMatches = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_no_matches, "field 'tvNoMatches'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        FragmentPlayerDetailHistory fragmentPlayerDetailHistory = this.target;
        if (fragmentPlayerDetailHistory == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        fragmentPlayerDetailHistory.rvBatsmanDetails = null;
        fragmentPlayerDetailHistory.tvNoMatches = null;
    }
}
