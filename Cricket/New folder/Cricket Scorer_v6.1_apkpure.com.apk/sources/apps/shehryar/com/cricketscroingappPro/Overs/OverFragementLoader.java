package apps.shehryar.com.cricketscroingappPro.Overs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import java.util.ArrayList;

public class OverFragementLoader {
    public static void showOverRecyclerViewFragment(FragmentManager fragmentManager, ArrayList<Over> arrayList) {
        FragmentOverHistoryRecyclerView fragmentOverHistoryRecyclerView = new FragmentOverHistoryRecyclerView();
        Bundle bundle = new Bundle();
        bundle.putSerializable(DBHelper.TABLE_OVERS, arrayList);
        fragmentOverHistoryRecyclerView.setArguments(bundle);
        fragmentManager.beginTransaction().add((int) R.id.over_recycler_view_fragment, (Fragment) fragmentOverHistoryRecyclerView).commit();
    }
}
