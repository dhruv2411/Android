package apps.shehryar.com.cricketscroingappPro.Batsman;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Bowler.Bowler_Details;
import apps.shehryar.com.cricketscroingappPro.Bowler.BowlersDetailsListAdapter;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.UtilityFunctions;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;

public class FragmentPlayerDetailHistory extends Fragment {
    private static final String IS_BATSMAN = "isBatsman";
    private static final String MATCHES = "matches";
    private static final String MATCH_TYPE = "matchType";
    private static final String PLAYER_NAME = "batsmanName";
    private static final String TEAM_NAME = "teamName";
    String batsmanName;
    ArrayList<Batsman_Details> batsman_details;
    ArrayList<Bowler_Details> bowler_details;
    boolean isBatsman;
    int matchType;
    ArrayList<Match> matches;
    @BindView(2131755547)
    RecyclerView rvBatsmanDetails;
    String teamName;
    @BindView(2131755546)
    TextView tvNoMatches;

    public static FragmentPlayerDetailHistory newInstance(String str, String str2, ArrayList<Match> arrayList, int i, boolean z) {
        FragmentPlayerDetailHistory fragmentPlayerDetailHistory = new FragmentPlayerDetailHistory();
        Bundle bundle = new Bundle();
        bundle.putString(PLAYER_NAME, str);
        bundle.putString(TEAM_NAME, str2);
        bundle.putSerializable(MATCHES, arrayList);
        bundle.putInt(MATCH_TYPE, i);
        bundle.putBoolean(IS_BATSMAN, z);
        fragmentPlayerDetailHistory.setArguments(bundle);
        return fragmentPlayerDetailHistory;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.batsmanName = getArguments().getString(PLAYER_NAME);
            this.teamName = getArguments().getString(TEAM_NAME);
            this.matches = (ArrayList) getArguments().getSerializable(MATCHES);
            this.matchType = getArguments().getInt(MATCH_TYPE);
            this.isBatsman = getArguments().getBoolean(IS_BATSMAN);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_batsman_detail_history, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        this.batsman_details = new ArrayList<>();
        this.bowler_details = new ArrayList<>();
        this.rvBatsmanDetails.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (this.isBatsman) {
            new LoadBatsmanDetails().execute(new Object[0]);
        } else {
            new LoadBowlerDetails().execute(new Object[0]);
        }
        return inflate;
    }

    public class LoadBatsmanDetails extends AsyncTask {
        ProgressDialog progressDialog;

        public LoadBatsmanDetails() {
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = new ProgressDialog(FragmentPlayerDetailHistory.this.getActivity());
            this.progressDialog.setMessage(FragmentPlayerDetailHistory.this.getResources().getString(R.string.loading));
            this.progressDialog.show();
        }

        /* access modifiers changed from: protected */
        public Object doInBackground(Object[] objArr) {
            ArrayList<Long> arrayList;
            try {
                arrayList = UtilityFunctions.getTeamIds(UtilityFunctions.getSpecificTeamMatches(FragmentPlayerDetailHistory.this.teamName, FragmentPlayerDetailHistory.this.matches), FragmentPlayerDetailHistory.this.matchType);
            } catch (Exception e) {
                e.printStackTrace();
                arrayList = new ArrayList<>();
            }
            try {
                FragmentPlayerDetailHistory.this.batsman_details = new DBHelper(FragmentPlayerDetailHistory.this.getActivity()).getBatsmanDetails(FragmentPlayerDetailHistory.this.teamName, FragmentPlayerDetailHistory.this.batsmanName, arrayList);
                return null;
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Object obj) {
            super.onPostExecute(obj);
            this.progressDialog.dismiss();
            FragmentPlayerDetailHistory.this.rvBatsmanDetails.setAdapter(new BatsmanDetailsListAdapter(FragmentPlayerDetailHistory.this.getActivity(), FragmentPlayerDetailHistory.this.batsman_details));
            if (FragmentPlayerDetailHistory.this.batsman_details.size() == 0) {
                FragmentPlayerDetailHistory.this.tvNoMatches.setVisibility(0);
            } else {
                FragmentPlayerDetailHistory.this.tvNoMatches.setVisibility(8);
            }
        }
    }

    public class LoadBowlerDetails extends AsyncTask {
        ProgressDialog progressDialog;

        public LoadBowlerDetails() {
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
            this.progressDialog = new ProgressDialog(FragmentPlayerDetailHistory.this.getActivity());
            this.progressDialog.setMessage(FragmentPlayerDetailHistory.this.getResources().getString(R.string.loading));
            this.progressDialog.show();
        }

        /* access modifiers changed from: protected */
        public Object doInBackground(Object[] objArr) {
            ArrayList<Long> arrayList;
            try {
                arrayList = UtilityFunctions.getTeamIds(UtilityFunctions.getSpecificTeamMatches(FragmentPlayerDetailHistory.this.teamName, FragmentPlayerDetailHistory.this.matches), FragmentPlayerDetailHistory.this.matchType);
            } catch (Exception e) {
                e.printStackTrace();
                arrayList = new ArrayList<>();
            }
            try {
                FragmentPlayerDetailHistory.this.bowler_details = new DBHelper(FragmentPlayerDetailHistory.this.getActivity()).getBowlerDetails(FragmentPlayerDetailHistory.this.teamName, FragmentPlayerDetailHistory.this.batsmanName, arrayList);
                return null;
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Object obj) {
            super.onPostExecute(obj);
            this.progressDialog.dismiss();
            FragmentPlayerDetailHistory.this.rvBatsmanDetails.setAdapter(new BowlersDetailsListAdapter(FragmentPlayerDetailHistory.this.getActivity(), FragmentPlayerDetailHistory.this.bowler_details));
            if (FragmentPlayerDetailHistory.this.batsman_details.size() == 0) {
                FragmentPlayerDetailHistory.this.tvNoMatches.setVisibility(0);
            } else {
                FragmentPlayerDetailHistory.this.tvNoMatches.setVisibility(8);
            }
        }
    }
}
