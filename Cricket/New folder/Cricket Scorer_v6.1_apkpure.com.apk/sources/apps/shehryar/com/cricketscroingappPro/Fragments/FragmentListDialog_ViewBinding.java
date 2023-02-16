package apps.shehryar.com.cricketscroingappPro.Fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class FragmentListDialog_ViewBinding implements Unbinder {
    private FragmentListDialog target;

    @UiThread
    public FragmentListDialog_ViewBinding(FragmentListDialog fragmentListDialog, View view) {
        this.target = fragmentListDialog;
        fragmentListDialog.tvTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title, "field 'tvTitle'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        FragmentListDialog fragmentListDialog = this.target;
        if (fragmentListDialog == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        fragmentListDialog.tvTitle = null;
    }
}
