package apps.shehryar.com.cricketscroingappPro.Team;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman;
import apps.shehryar.com.cricketscroingappPro.Batsman.BatsmanDetailsDialog;
import apps.shehryar.com.cricketscroingappPro.Batsman.BatsmanHelper;
import apps.shehryar.com.cricketscroingappPro.Batsman.FragmentBatsmanHistoryRecyclerView;
import apps.shehryar.com.cricketscroingappPro.Bowler.Dialog_Custom_ListView_Adapter;
import apps.shehryar.com.cricketscroingappPro.Bowler.FragmentBowlerRecyclerView;
import apps.shehryar.com.cricketscroingappPro.Custom_Fall_of_Wickets_Adapter;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Overs.FragmentOverHistoryRecyclerView;
import apps.shehryar.com.cricketscroingappPro.Partenership.FragmentPartenerships;
import apps.shehryar.com.cricketscroingappPro.Partenership.Partenership;
import apps.shehryar.com.cricketscroingappPro.Partenership.PartenershipHelper;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.AlertDialogBuilder;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.Formatter;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.UtilityFunctions;
import java.util.ArrayList;

public class PreviousMatchDetailTeamFragment extends Fragment implements View.OnClickListener {
    Button btnLoadOvers;
    Button btnLoadPartnerships;
    Context context;
    DBHelper dbHelper;
    LinearLayout fowLayout;
    private ProgressDialog loading;
    Match match;
    LinearLayout oversLayout;
    ArrayList<Partenership> partenerships;
    Team team;
    ListView team1fow;
    Team teamOpp;
    TextView tvFow;
    TextView tvoversHeading;
    TextView tvteam1extras;
    TextView tvteam1name;
    TextView tvteam1overs;
    TextView tvteam1score;

    public PreviousMatchDetailTeamFragment() {
    }

    @SuppressLint({"ValidFragment"})
    public PreviousMatchDetailTeamFragment(Context context2, Team team2, Team team3, Match match2) {
        this.match = match2;
        this.context = context2;
        this.team = team2;
        this.teamOpp = team3;
        this.partenerships = new ArrayList<>();
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_previous_match_detail_team1, viewGroup, false);
        AlertDialogBuilder.showTipDialog2(getContext());
        this.dbHelper = new DBHelper(this.context);
        Typeface createFromAsset = Typeface.createFromAsset(getContext().getAssets(), "fonts/eurostile_next_lt_pro_semibold.otf");
        this.btnLoadPartnerships = (Button) inflate.findViewById(R.id.btn_load_partnerships);
        this.btnLoadOvers = (Button) inflate.findViewById(R.id.btn_load_overs);
        this.btnLoadOvers.setVisibility(8);
        this.btnLoadPartnerships.setVisibility(8);
        this.tvteam1name = (TextView) inflate.findViewById(R.id.team1_name);
        this.tvteam1score = (TextView) inflate.findViewById(R.id.team1score);
        this.tvteam1overs = (TextView) inflate.findViewById(R.id.team1overs);
        this.tvteam1extras = (TextView) inflate.findViewById(R.id.team1extras);
        this.team1fow = (ListView) inflate.findViewById(R.id.fallofwicketslistview);
        this.tvoversHeading = (TextView) inflate.findViewById(R.id.tvOversHeading);
        this.oversLayout = (LinearLayout) inflate.findViewById(R.id.overs_layout);
        this.fowLayout = (LinearLayout) inflate.findViewById(R.id.fall_of_wickets_layout);
        this.tvFow = (TextView) inflate.findViewById(R.id.tvFow);
        this.tvFow.setTypeface(createFromAsset);
        this.tvFow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (PreviousMatchDetailTeamFragment.this.fowLayout.getVisibility() == 0) {
                    PreviousMatchDetailTeamFragment.this.fowLayout.setVisibility(8);
                    PreviousMatchDetailTeamFragment.this.tvFow.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_drop_down_white_24px, 0);
                    return;
                }
                PreviousMatchDetailTeamFragment.this.fowLayout.setVisibility(0);
                PreviousMatchDetailTeamFragment.this.tvFow.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_drop_up_white_24px, 0);
            }
        });
        this.tvoversHeading.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (PreviousMatchDetailTeamFragment.this.oversLayout.getVisibility() == 0) {
                    PreviousMatchDetailTeamFragment.this.oversLayout.setVisibility(8);
                    PreviousMatchDetailTeamFragment.this.tvoversHeading.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_drop_down_white_24px, 0);
                    return;
                }
                PreviousMatchDetailTeamFragment.this.oversLayout.setVisibility(0);
                PreviousMatchDetailTeamFragment.this.tvoversHeading.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_drop_up_white_24px, 0);
            }
        });
        this.tvoversHeading.setTypeface(createFromAsset);
        this.tvteam1name.setTypeface(createFromAsset);
        this.tvFow.setText("FALL OF WICKETS");
        this.tvoversHeading.setText("OVERS");
        this.tvteam1extras.setSingleLine(false);
        if (this.team != null) {
            this.tvteam1name.setText(Formatter.cutNameHalf(this.team.getName()).toUpperCase());
            TextView textView = this.tvteam1score;
            textView.setText(this.team.getScore() + "/" + this.team.getWickets());
            TextView textView2 = this.tvteam1overs;
            textView2.setText(this.team.getOversPlayed() + "." + this.team.getOverballs());
            TextView textView3 = this.tvteam1extras;
            textView3.setText("Extras: " + this.team.getExtras());
        }
        new LoadTeamStats().execute(new Void[0]);
        this.btnLoadOvers.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FragmentOverHistoryRecyclerView fragmentOverHistoryRecyclerView = new FragmentOverHistoryRecyclerView();
                Bundle bundle = new Bundle();
                try {
                    bundle.putSerializable(DBHelper.TABLE_OVERS, PreviousMatchDetailTeamFragment.this.team.getOvers_list());
                    fragmentOverHistoryRecyclerView.setArguments(bundle);
                    PreviousMatchDetailTeamFragment.this.getChildFragmentManager().beginTransaction().add((int) R.id.over_recycler_view_fragment, (Fragment) fragmentOverHistoryRecyclerView).addToBackStack((String) null).commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                PreviousMatchDetailTeamFragment.this.btnLoadOvers.setVisibility(8);
            }
        });
        this.tvteam1name.setOnClickListener(this);
        this.tvteam1score.setOnClickListener(this);
        return inflate;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.team1_name /*2131755564*/:
            case R.id.team1score /*2131755565*/:
                try {
                    showTeamDetailsDialog(this.team);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            default:
                return;
        }
    }

    private void showTeamDetailsDialog(Team team2) throws Exception {
        TeamDetailsDialog teamDetailsDialog = new TeamDetailsDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable(DBHelper.TABLE_MATCH, this.match);
        bundle.putSerializable(DBHelper.TABLE_TEAM, team2);
        teamDetailsDialog.setArguments(bundle);
        teamDetailsDialog.show(((Activity) this.context).getFragmentManager(), DBHelper.TABLE_TEAM);
    }

    class LoadTeamStats extends AsyncTask<Void, Void, ArrayList<Batsman>> {
        LoadTeamStats() {
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
        }

        /* access modifiers changed from: protected */
        public ArrayList<Batsman> doInBackground(Void... voidArr) {
            try {
                UtilityFunctions.setTeamExtras(PreviousMatchDetailTeamFragment.this.team, PreviousMatchDetailTeamFragment.this.team.overs_list);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Team team = new Team(PreviousMatchDetailTeamFragment.this.team);
                PreviousMatchDetailTeamFragment.this.partenerships = PartenershipHelper.getAllPartenerships2(PreviousMatchDetailTeamFragment.this.partenerships, PreviousMatchDetailTeamFragment.this.match, team);
            } catch (Exception e2) {
                e2.printStackTrace();
                if (PreviousMatchDetailTeamFragment.this.partenerships == null) {
                    PreviousMatchDetailTeamFragment.this.partenerships = new ArrayList<>();
                }
            }
            return new ArrayList<>();
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(ArrayList<Batsman> arrayList) {
            super.onPostExecute(arrayList);
            try {
                PreviousMatchDetailTeamFragment.this.btnLoadOvers.setVisibility(0);
                PreviousMatchDetailTeamFragment.this.btnLoadPartnerships.setVisibility(0);
                BatsmanHelper.addNotBattedBatsmanFromPlayingXI(PreviousMatchDetailTeamFragment.this.context, PreviousMatchDetailTeamFragment.this.team, PreviousMatchDetailTeamFragment.this.team.getBatsmans_list(), PreviousMatchDetailTeamFragment.this.match);
                FragmentTransaction beginTransaction = PreviousMatchDetailTeamFragment.this.getChildFragmentManager().beginTransaction();
                BatsmanHelper.addNotBattedBatsmanFromPlayingXI(PreviousMatchDetailTeamFragment.this.context, PreviousMatchDetailTeamFragment.this.team, PreviousMatchDetailTeamFragment.this.team.getBatsmans_list(), PreviousMatchDetailTeamFragment.this.match);
                FragmentBatsmanHistoryRecyclerView fragmentBatsmanHistoryRecyclerView = new FragmentBatsmanHistoryRecyclerView();
                Bundle bundle = new Bundle();
                bundle.putSerializable(DBHelper.TABLE_MATCH, PreviousMatchDetailTeamFragment.this.match);
                bundle.putSerializable("batsmen", PreviousMatchDetailTeamFragment.this.team.getBatsmans_list());
                Log.i("batsman list size", PreviousMatchDetailTeamFragment.this.team.getBatsmans_list().size() + "");
                fragmentBatsmanHistoryRecyclerView.setArguments(bundle);
                beginTransaction.add((int) R.id.batsman_recycler_view_fragment, (Fragment) fragmentBatsmanHistoryRecyclerView).addToBackStack((String) null);
                FragmentBowlerRecyclerView fragmentBowlerRecyclerView = new FragmentBowlerRecyclerView((Dialog_Custom_ListView_Adapter.LinearLayoutClickLister) null);
                Bundle bundle2 = new Bundle();
                bundle2.putSerializable("bowlers", PreviousMatchDetailTeamFragment.this.team.getBowlers_list());
                bundle2.putSerializable(DBHelper.TABLE_OVERS, PreviousMatchDetailTeamFragment.this.team.getOvers_list());
                bundle2.putSerializable(DBHelper.TABLE_MATCH, PreviousMatchDetailTeamFragment.this.match);
                fragmentBowlerRecyclerView.setArguments(bundle2);
                beginTransaction.add(R.id.bowler_recycler_view_fragment, fragmentBowlerRecyclerView, FragmentBowlerRecyclerView.class.toString()).addToBackStack((String) null);
                PreviousMatchDetailTeamFragment.this.team1fow.setAdapter(new Custom_Fall_of_Wickets_Adapter(PreviousMatchDetailTeamFragment.this.context, R.layout.fragment_previous_match_detail_team1, PreviousMatchDetailTeamFragment.this.team.getFallOfWicketses()));
                beginTransaction.commit();
                PreviousMatchDetailTeamFragment.this.btnLoadPartnerships.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        FragmentPartenerships fragmentPartenerships = new FragmentPartenerships();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("parts", PreviousMatchDetailTeamFragment.this.partenerships);
                        fragmentPartenerships.setArguments(bundle);
                        PreviousMatchDetailTeamFragment.this.getChildFragmentManager().beginTransaction().add((int) R.id.fragment_container, (Fragment) fragmentPartenerships).addToBackStack((String) null).commit();
                        PreviousMatchDetailTeamFragment.this.btnLoadPartnerships.setVisibility(8);
                    }
                });
            } catch (Exception unused) {
            }
        }
    }

    private void showBatsmanDetailDialog(Batsman batsman) {
        BatsmanDetailsDialog batsmanDetailsDialog = new BatsmanDetailsDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("batsman", batsman);
        batsmanDetailsDialog.setArguments(bundle);
        batsmanDetailsDialog.show(getActivity().getFragmentManager(), "batsman");
    }
}
