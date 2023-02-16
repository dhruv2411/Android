package apps.shehryar.com.cricketscroingappPro.Tournament;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.FragmentChooseItems;
import apps.shehryar.com.cricketscroingappPro.GeneralCheckBoxListItem;
import apps.shehryar.com.cricketscroingappPro.Listeners.FragmentChooseItemListener;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.Iterator;

public class FragmentPointsTable extends Fragment implements FragmentChooseItemListener, View.OnClickListener {
    public static final String TOURNAMENT_NAME = "tournament_name";
    ArrayList<ArrayList<Team>> allTeamsStats = new ArrayList<>();
    private ArrayList<String> allTournamentStages;
    @BindView(2131755563)
    Button btnChooseStages;
    DBHelper dbHelper;
    @BindView(2131755206)
    TabLayout tabLayout;
    String tournamentName;
    ArrayList<String> tournamentStages;
    @BindView(2131755207)
    ViewPager viewPager;

    public static FragmentPointsTable newInstance(String str) {
        FragmentPointsTable fragmentPointsTable = new FragmentPointsTable();
        Bundle bundle = new Bundle();
        bundle.putString(TOURNAMENT_NAME, str);
        fragmentPointsTable.setArguments(bundle);
        return fragmentPointsTable;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.tournamentName = getArguments().getString(TOURNAMENT_NAME);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_points_table, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        this.dbHelper = new DBHelper(getActivity());
        this.tabLayout.setTabTextColors(ContextCompat.getColor(getActivity(), R.color.white), ContextCompat.getColor(getActivity(), R.color.white));
        this.allTournamentStages = this.dbHelper.getTournamentAllStages(this.tournamentName);
        this.tournamentStages = new ArrayList<>();
        this.btnChooseStages.setOnClickListener(this);
        new LoadAllStagesStats().execute(new Object[0]);
        return inflate;
    }

    public void onItemsChosen(ArrayList<Integer> arrayList) {
        this.tournamentStages.clear();
        Iterator<Integer> it = arrayList.iterator();
        while (it.hasNext()) {
            this.tournamentStages.add(this.allTournamentStages.get(it.next().intValue()));
        }
        new LoadAllStagesStats().execute(new Object[0]);
    }

    class LoadAllStagesStats extends AsyncTask {
        LoadAllStagesStats() {
        }

        /* access modifiers changed from: protected */
        public Object doInBackground(Object[] objArr) {
            FragmentPointsTable.this.tournamentStages.add("All Stages");
            FragmentPointsTable.this.allTeamsStats.clear();
            Iterator<String> it = FragmentPointsTable.this.tournamentStages.iterator();
            while (it.hasNext()) {
                try {
                    FragmentPointsTable.this.allTeamsStats.add(FragmentPointsTable.this.dbHelper.get_tournament_Teams_Stats(FragmentPointsTable.this.tournamentName, it.next()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Object obj) {
            super.onPostExecute(obj);
            try {
                FragmentPointsTable.this.viewPager.setAdapter(new ViewPagerAdapterPointsTable(FragmentPointsTable.this.getChildFragmentManager(), FragmentPointsTable.this.tournamentStages, FragmentPointsTable.this.allTeamsStats));
                FragmentPointsTable.this.tabLayout.setupWithViewPager(FragmentPointsTable.this.viewPager);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn_choose_stages) {
            FragmentChooseItems.newInstance(getResources().getString(R.string.choose_tournament_stages), GeneralCheckBoxListItem.getCheckBoxListItems(this.allTournamentStages)).show(getChildFragmentManager());
        }
    }
}
