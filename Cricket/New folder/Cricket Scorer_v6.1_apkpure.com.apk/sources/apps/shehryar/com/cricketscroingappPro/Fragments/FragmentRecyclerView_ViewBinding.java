package apps.shehryar.com.cricketscroingappPro.Fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import apps.shehryar.com.cricketscroingapp.R;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class FragmentRecyclerView_ViewBinding implements Unbinder {
    private FragmentRecyclerView target;

    @UiThread
    public FragmentRecyclerView_ViewBinding(FragmentRecyclerView fragmentRecyclerView, View view) {
        this.target = fragmentRecyclerView;
        fragmentRecyclerView.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recyclerview, "field 'recyclerView'", RecyclerView.class);
    }

    @CallSuper
    public void unbind() {
        FragmentRecyclerView fragmentRecyclerView = this.target;
        if (fragmentRecyclerView == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        fragmentRecyclerView.recyclerView = null;
    }
}
