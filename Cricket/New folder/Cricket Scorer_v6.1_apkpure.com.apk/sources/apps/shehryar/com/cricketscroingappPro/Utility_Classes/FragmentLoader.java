package apps.shehryar.com.cricketscroingappPro.Utility_Classes;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;

public class FragmentLoader {
    public static void showDialogFragmet(Activity activity, DialogFragment dialogFragment, Bundle bundle, String str) {
        if (bundle != null) {
            dialogFragment.setArguments(bundle);
        }
        FragmentManager fragmentManager = activity.getFragmentManager();
        dialogFragment.show(fragmentManager, dialogFragment.getTag() + str);
    }
}
