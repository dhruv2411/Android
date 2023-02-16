package apps.shehryar.com.cricketscroingappPro;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class FragmentChooseItems_ViewBinding implements Unbinder {
    private FragmentChooseItems target;

    @UiThread
    public FragmentChooseItems_ViewBinding(FragmentChooseItems fragmentChooseItems, View view) {
        this.target = fragmentChooseItems;
        fragmentChooseItems.tvTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title, "field 'tvTitle'", TextView.class);
        fragmentChooseItems.rvOptions = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.rv_options, "field 'rvOptions'", RecyclerView.class);
        fragmentChooseItems.bOk = (Button) Utils.findRequiredViewAsType(view, R.id.btn_ok, "field 'bOk'", Button.class);
        fragmentChooseItems.bCancel = (Button) Utils.findRequiredViewAsType(view, R.id.btn_cancel, "field 'bCancel'", Button.class);
    }

    @CallSuper
    public void unbind() {
        FragmentChooseItems fragmentChooseItems = this.target;
        if (fragmentChooseItems == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        fragmentChooseItems.tvTitle = null;
        fragmentChooseItems.rvOptions = null;
        fragmentChooseItems.bOk = null;
        fragmentChooseItems.bCancel = null;
    }
}
