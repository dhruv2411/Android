package apps.shehryar.com.cricketscroingappPro.Match;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.MyToast;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.SharedPrefsHelper;

public class MatchLoader extends AsyncTask<Match, Void, Boolean> {
    Context context;
    ProgressDialog loading;

    public MatchLoader(Context context2) {
        this.context = context2;
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
        super.onPreExecute();
        this.loading = new ProgressDialog(this.context);
        this.loading.setCancelable(true);
        this.loading.setMessage("Loading Match. Please Wait.");
        this.loading.setProgressStyle(0);
        this.loading.show();
    }

    /* access modifiers changed from: protected */
    public Boolean doInBackground(Match... matchArr) {
        try {
            new DBHelper(this.context).insertFullMatchInDB(matchArr[0]);
            SharedPrefsHelper.insertResumeMatchInSharedPrefs(this.context, matchArr[0].getResumeMatch(), matchArr[0]);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("exception exporting match", e.toString());
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Boolean bool) {
        super.onPostExecute(bool);
        this.loading.dismiss();
        if (bool.booleanValue()) {
            MyToast.showToast(this.context, "Match Loaded Successfully");
        } else {
            MyToast.showToast(this.context, "Error Loading Match");
        }
    }
}
