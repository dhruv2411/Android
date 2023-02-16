package apps.shehryar.com.cricketscroingappPro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Match.Match;

public class UnsupportedFileDialog extends MyDialogFragment implements View.OnClickListener {
    TextView bshare;
    UnsupportedFileDialogHandler listener;
    Match match;

    public interface UnsupportedFileDialogHandler {
        void onOpenAgain();
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.unsupported_file_dialog, viewGroup, false);
        this.bshare = (TextView) inflate.findViewById(R.id.b_open_another);
        this.bshare.setOnClickListener(this);
        this.listener = (UnsupportedFileDialogHandler) getActivity();
        return inflate;
    }

    public void onClick(View view) {
        this.listener.onOpenAgain();
        dismiss();
    }
}
