package apps.shehryar.com.cricketscroingappPro;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Match.PreviousMatchesListAdapter;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import java.util.ArrayList;

public class FragmentYourMatches extends Fragment {
    Activity activity;
    Context context;
    DBHelper dbHelper;
    int index;
    Intent intent;
    ListView listView;
    ArrayList<Match> matches;
    ExpandedListView matcheslist;
    TextView noMatches;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    ArrayList<Team> team1scores;
    ArrayList<Team> team1stats;
    ArrayList<Team> team2scores;
    ArrayList<Team> team2stats;
    ArrayList<Team> teamNames;

    public FragmentYourMatches() {
    }

    @SuppressLint({"ValidFragment"})
    public FragmentYourMatches(Context context2, Activity activity2, ArrayList<Match> arrayList) {
        this.context = context2;
        this.activity = activity2;
        this.matches = arrayList;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_your_matches_list, viewGroup, false);
        this.recyclerView = (RecyclerView) inflate.findViewById(R.id.batsmanrecyclerview);
        this.noMatches = (TextView) inflate.findViewById(R.id.no_matches_text);
        this.dbHelper = new DBHelper(getContext());
        this.team1scores = new ArrayList<>();
        this.team1stats = new ArrayList<>();
        this.team2stats = new ArrayList<>();
        this.progressBar = (ProgressBar) inflate.findViewById(R.id.progressBar);
        new DataLoader().execute(new Integer[]{3});
        return inflate;
    }

    class DataLoader extends AsyncTask<Integer, Team, Boolean> {
        DataLoader() {
        }

        /* access modifiers changed from: protected */
        public Boolean doInBackground(Integer... numArr) {
            return true;
        }

        /* access modifiers changed from: protected */
        public void onProgressUpdate(Team... teamArr) {
            super.onProgressUpdate(teamArr);
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
            try {
                FragmentYourMatches.this.recyclerView.setLayoutManager(new LinearLayoutManager(FragmentYourMatches.this.getContext().getApplicationContext()));
                FragmentYourMatches.this.recyclerView.setItemAnimator(new DefaultItemAnimator());
                FragmentYourMatches.this.recyclerView.setItemViewCacheSize(20);
                PreviousMatchesListAdapter previousMatchesListAdapter = new PreviousMatchesListAdapter(FragmentYourMatches.this.getContext(), FragmentYourMatches.this.activity, FragmentYourMatches.this.matches);
                if (FragmentYourMatches.this.matches.isEmpty()) {
                    FragmentYourMatches.this.noMatches.setVisibility(0);
                    FragmentYourMatches.this.progressBar.setVisibility(8);
                } else {
                    FragmentYourMatches.this.noMatches.setVisibility(8);
                }
                FragmentYourMatches.this.recyclerView.setAdapter(previousMatchesListAdapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
