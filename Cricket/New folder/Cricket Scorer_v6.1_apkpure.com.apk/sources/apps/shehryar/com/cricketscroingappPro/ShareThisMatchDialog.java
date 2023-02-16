package apps.shehryar.com.cricketscroingappPro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.FileSharer;

public class ShareThisMatchDialog extends MyDialogFragment implements View.OnClickListener {
    TextView bshare;
    Match match;

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.share_match_dialog, viewGroup, false);
        this.match = (Match) getArguments().getSerializable(DBHelper.TABLE_MATCH);
        this.bshare = (TextView) inflate.findViewById(R.id.b_share);
        this.bshare.setOnClickListener(this);
        return inflate;
    }

    public void onClick(View view) {
        if (!PermissionAsker.checkAPIVersion()) {
            FileSharer.shareMatch(getActivity(), this.match);
            dismiss();
        } else if (PermissionAsker.checkIfAlreadyhavePermission(getActivity())) {
            FileSharer.shareMatch(getActivity(), this.match);
            dismiss();
        } else {
            PermissionAsker.requestForSpecificPermission(101, getActivity());
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 101 && iArr[0] == 0) {
            FileSharer.shareMatch(getActivity(), this.match);
            dismiss();
        }
    }
}
