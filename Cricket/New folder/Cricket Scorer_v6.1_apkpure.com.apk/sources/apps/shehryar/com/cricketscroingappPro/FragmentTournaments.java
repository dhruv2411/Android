package apps.shehryar.com.cricketscroingappPro;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Match.Tournament_Matches;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import apps.shehryar.com.cricketscroingappPro.Tournament.FragmentPointsTable;
import java.util.ArrayList;

public class FragmentTournaments extends Fragment {
    Activity activity;
    ArrayList<Team> all_team_players;
    Context context;
    DBHelper dbHelper;
    int matchType;
    ArrayList<String> playerNames;
    ArrayList<String> teamnames;
    ListView teamslist;

    public FragmentTournaments() {
    }

    @SuppressLint({"ValidFragment"})
    public FragmentTournaments(Context context2, Activity activity2, ArrayList<String> arrayList, ArrayList<String> arrayList2, ArrayList<Team> arrayList3, int i) {
        this.context = context2;
        this.playerNames = arrayList;
        this.teamnames = arrayList2;
        this.all_team_players = arrayList3;
        this.matchType = i;
        this.activity = activity2;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_tournaments, viewGroup, false);
        this.teamslist = (ListView) inflate.findViewById(R.id.listviewtournaments);
        this.dbHelper = new DBHelper(getContext());
        new Loadtournaments().execute(new Integer[]{Integer.valueOf(this.matchType)});
        return inflate;
    }

    private class Loadtournaments extends AsyncTask<Integer, Void, Void> {
        /* access modifiers changed from: private */
        public ArrayList<String> tournament_names;

        private Loadtournaments() {
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Integer[] numArr) {
            this.tournament_names = FragmentTournaments.this.dbHelper.getTournament(numArr[0].intValue());
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Void voidR) {
            super.onPostExecute(voidR);
            try {
                if (this.tournament_names.isEmpty()) {
                    FragmentTournaments.this.getActivity().findViewById(R.id.tvNoData).setVisibility(0);
                    FragmentTournaments.this.getActivity().findViewById(R.id.tvTapInfo).setVisibility(8);
                    return;
                }
                FragmentTournaments.this.teamslist.setAdapter(new Custom_Suggestions_Adapter(FragmentTournaments.this.getContext(), R.layout.custom_suggestions_layout, this.tournament_names));
                FragmentTournaments.this.teamslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Intent intent = new Intent(FragmentTournaments.this.getContext(), Tournament_Matches.class);
                        intent.putExtra("index", i);
                        intent.putExtra(FragmentPointsTable.TOURNAMENT_NAME, (String) Loadtournaments.this.tournament_names.get(i));
                        intent.putExtra("teamNames", FragmentTournaments.this.teamnames);
                        intent.putExtra("playerNames", FragmentTournaments.this.playerNames);
                        intent.putExtra("all_team_players", FragmentTournaments.this.all_team_players);
                        FragmentTournaments.this.startActivityForResult(intent, 0);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
