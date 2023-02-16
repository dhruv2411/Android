package apps.shehryar.com.cricketscroingappPro.Fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import apps.shehryar.com.cricketscroingapp.R;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class FragmentPrivacyPolicy_ViewBinding implements Unbinder {
    private FragmentPrivacyPolicy target;

    @UiThread
    public FragmentPrivacyPolicy_ViewBinding(FragmentPrivacyPolicy fragmentPrivacyPolicy, View view) {
        this.target = fragmentPrivacyPolicy;
        fragmentPrivacyPolicy.webView = (WebView) Utils.findRequiredViewAsType(view, R.id.web_view, "field 'webView'", WebView.class);
        fragmentPrivacyPolicy.bClose = (Button) Utils.findRequiredViewAsType(view, R.id.btn_close, "field 'bClose'", Button.class);
    }

    @CallSuper
    public void unbind() {
        FragmentPrivacyPolicy fragmentPrivacyPolicy = this.target;
        if (fragmentPrivacyPolicy == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        fragmentPrivacyPolicy.webView = null;
        fragmentPrivacyPolicy.bClose = null;
    }
}
