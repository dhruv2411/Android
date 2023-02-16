package apps.shehryar.com.cricketscroingappPro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;

public class LoadMatchDialog extends MyDialogFragment implements View.OnClickListener {
    TextView bchoose;
    LoadMatchDialogHandler listener;

    public interface LoadMatchDialogHandler {
        void onChooseFilePressed();
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.load_match_dialog, viewGroup, false);
        this.bchoose = (TextView) inflate.findViewById(R.id.b_load_from_storage);
        this.listener = (LoadMatchDialogHandler) getActivity();
        this.bchoose.setOnClickListener(this);
        return inflate;
    }

    public void onClick(View view) {
        this.listener.onChooseFilePressed();
        try {
            dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
