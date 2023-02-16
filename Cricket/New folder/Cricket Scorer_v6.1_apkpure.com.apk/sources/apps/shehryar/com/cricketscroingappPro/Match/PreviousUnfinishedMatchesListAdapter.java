package apps.shehryar.com.cricketscroingappPro.Match;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.PlayersInputActivity;
import apps.shehryar.com.cricketscroingappPro.StartFollowOnDialog;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.UtilityFunctions;
import java.util.ArrayList;

public class PreviousUnfinishedMatchesListAdapter extends RecyclerView.Adapter<MyViewHolder> implements StartFollowOnDialog.StartFollowOnDialogListener {
    DBHelper dbHelper;
    ArrayList<Match> matches;
    Context mcontext;
    ArrayList<Team> teams;
    Typeface typeface;
    Typeface typefacebold;

    public void onStartFollowOnTapped(int i) {
        Match match = this.matches.get(i);
        if (!match.followedOn) {
            match.setFollowedOn(1);
            UtilityFunctions.setMatchStatusAsFollowedOn(this.mcontext, match);
        }
        new LoadData().execute(new Match[]{match});
    }

    public void onStartNextInningsTapped(int i) {
        Match match = this.matches.get(i);
        if (match.followedOn) {
            match.setFollowedOn(0);
            UtilityFunctions.setMatchStatusAsFollowedOn(this.mcontext, match);
        }
        new LoadData().execute(new Match[]{match});
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout main_layout;
        TextView result;
        TextView team1name;
        TextView team1scores;
        TextView team1sovers;
        TextView team2name;
        TextView team2scores;
        TextView team2sovers;
        TextView tournament;
        TextView tvToss;
        TextView tvvenue;

        public MyViewHolder(View view) {
            super(view);
            PreviousUnfinishedMatchesListAdapter.this.dbHelper = new DBHelper(PreviousUnfinishedMatchesListAdapter.this.mcontext);
            this.main_layout = (LinearLayout) view.findViewById(R.id.main_layout);
            this.team1name = (TextView) view.findViewById(R.id.team1_name);
            this.team2name = (TextView) view.findViewById(R.id.team2_name);
            this.team1scores = (TextView) view.findViewById(R.id.team1score);
            this.team2scores = (TextView) view.findViewById(R.id.team2score);
            this.team1sovers = (TextView) view.findViewById(R.id.team1overs);
            this.team2sovers = (TextView) view.findViewById(R.id.team2overs);
            this.result = (TextView) view.findViewById(R.id.match_result);
            this.tvvenue = (TextView) view.findViewById(R.id.tvvenueanddate);
            this.tournament = (TextView) view.findViewById(R.id.tvtournament);
            this.tvToss = (TextView) view.findViewById(R.id.tvToss);
            view.setOnClickListener(this);
        }

        public void onClick(View view) {
            int layoutPosition = getLayoutPosition();
            Log.i("position", layoutPosition + "");
            Match match = PreviousUnfinishedMatchesListAdapter.this.matches.get(layoutPosition);
            if (match.getResumeMatch().getInnings() == 6) {
                new StartFollowOnDialog(layoutPosition, PreviousUnfinishedMatchesListAdapter.this).show(((Activity) PreviousUnfinishedMatchesListAdapter.this.mcontext).getFragmentManager(), "start Follow On Dialog");
                return;
            }
            new LoadData().execute(new Match[]{match});
        }
    }

    class LoadData extends AsyncTask<Match, Integer, Match> {
        LoadData() {
        }

        /* access modifiers changed from: protected */
        public Match doInBackground(Match... matchArr) {
            Match match = matchArr[0];
            match.getTeam1().setBatsmans_list(PreviousUnfinishedMatchesListAdapter.this.dbHelper.getBatsmans(match.getTeam_Yours_id(), match.getMatchID()));
            match.getTeam2().setBowlers_list(PreviousUnfinishedMatchesListAdapter.this.dbHelper.getBowlers(match.getTeam_opp_id(), match.getMatchID()));
            match.getTeam1().setFallOfWicketses(PreviousUnfinishedMatchesListAdapter.this.dbHelper.getFallOfWickets(match.getMatchID(), match.getTeam_Yours_id()));
            match.getTeam1().setOvers_list(PreviousUnfinishedMatchesListAdapter.this.dbHelper.getOvers(match.getMatchID(), match.getTeam1().getTeamID()));
            match.getTeam2().setBatsmans_list(PreviousUnfinishedMatchesListAdapter.this.dbHelper.getBatsmans(match.getTeam_opp_id(), match.getMatchID()));
            match.getTeam1().setBowlers_list(PreviousUnfinishedMatchesListAdapter.this.dbHelper.getBowlers(match.getTeam_Yours_id(), match.getMatchID()));
            match.getTeam2().setFallOfWicketses(PreviousUnfinishedMatchesListAdapter.this.dbHelper.getFallOfWickets(match.getMatchID(), match.getTeam_Yours_id()));
            match.getTeam2().setOvers_list(PreviousUnfinishedMatchesListAdapter.this.dbHelper.getOvers(match.getMatchID(), match.getTeam2().getTeamID()));
            if (match.isTestMatch) {
                match.getTeam3().setBatsmans_list(PreviousUnfinishedMatchesListAdapter.this.dbHelper.getBatsmans(match.getTeam3().getTeamID(), match.getMatchID()));
                match.getTeam4().setBowlers_list(PreviousUnfinishedMatchesListAdapter.this.dbHelper.getBowlers(match.getTeam4().getTeamID(), match.getMatchID()));
                match.getTeam3().setFallOfWicketses(PreviousUnfinishedMatchesListAdapter.this.dbHelper.getFallOfWickets(match.getMatchID(), match.getTeam3().getTeamID()));
                match.getTeam3().setOvers_list(PreviousUnfinishedMatchesListAdapter.this.dbHelper.getOvers(match.getMatchID(), match.getTeam3().getTeamID()));
                match.getTeam4().setBatsmans_list(PreviousUnfinishedMatchesListAdapter.this.dbHelper.getBatsmans(match.getTeam4().getTeamID(), match.getMatchID()));
                match.getTeam3().setBowlers_list(PreviousUnfinishedMatchesListAdapter.this.dbHelper.getBowlers(match.getTeam3().getTeamID(), match.getMatchID()));
                match.getTeam4().setFallOfWicketses(PreviousUnfinishedMatchesListAdapter.this.dbHelper.getFallOfWickets(match.getMatchID(), match.getTeam4().getTeamID()));
                match.getTeam4().setOvers_list(PreviousUnfinishedMatchesListAdapter.this.dbHelper.getOvers(match.getMatchID(), match.getTeam4().getTeamID()));
            }
            return match;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Match match) {
            super.onPostExecute(match);
            SharedPreferences sharedPreferences = PreviousUnfinishedMatchesListAdapter.this.mcontext.getSharedPreferences(DBHelper.TABLE_TEAM, 0);
            Log.i("bowler name", sharedPreferences.getString("bowler " + match.getMatchID(), ""));
            Intent intent = new Intent(PreviousUnfinishedMatchesListAdapter.this.mcontext, PlayersInputActivity.class);
            intent.putExtra(DBHelper.TABLE_MATCH, match);
            intent.putExtra("teams", PreviousUnfinishedMatchesListAdapter.this.teams);
            intent.putExtra("resumeMatch", true);
            PreviousUnfinishedMatchesListAdapter.this.mcontext.startActivity(intent);
        }
    }

    public PreviousUnfinishedMatchesListAdapter(Context context, ArrayList<Match> arrayList, ArrayList<Team> arrayList2) {
        this.mcontext = context;
        this.matches = arrayList;
        this.teams = arrayList2;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.previous_matches_list, viewGroup, false);
        this.typeface = Typeface.createFromAsset(this.mcontext.getAssets(), "fonts/DroidSans.ttf");
        this.typefacebold = Typeface.createFromAsset(this.mcontext.getAssets(), "fonts/RobotoCondensed_Bold.ttf");
        this.dbHelper = new DBHelper(this.mcontext);
        return new MyViewHolder(inflate);
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        Match match = this.matches.get(i);
        myViewHolder.team1name.setTypeface(this.typeface);
        myViewHolder.team2name.setTypeface(this.typeface);
        myViewHolder.team1scores.setTypeface(this.typefacebold);
        myViewHolder.team2scores.setTypeface(this.typefacebold);
        myViewHolder.team1sovers.setTypeface(this.typeface);
        myViewHolder.team2sovers.setTypeface(this.typeface);
        myViewHolder.result.setTypeface(this.typefacebold);
        myViewHolder.tournament.setTypeface(this.typefacebold);
        myViewHolder.team1name.setText(match.getYourteam());
        myViewHolder.team2name.setText(match.getOpponent());
        TextView textView = myViewHolder.tvvenue;
        textView.setText(match.getVenue() + " " + match.getDate() + " " + match.getTime());
        TextView textView2 = myViewHolder.result;
        StringBuilder sb = new StringBuilder();
        sb.append(match.getResult());
        sb.append("");
        textView2.setText(sb.toString());
        TextView textView3 = myViewHolder.tournament;
        textView3.setText(match.getTournament() + "");
        String[] split = match.getResult().split(":");
        myViewHolder.team1name.setText(match.getTeam1().getName());
        myViewHolder.team2name.setText(match.getTeam2().getName());
        TextView textView4 = myViewHolder.tvvenue;
        textView4.setText(match.getVenue() + " " + match.getDate() + " " + match.getTime());
        if (match.getTossResult().equals("")) {
            myViewHolder.tvToss.setVisibility(8);
        } else {
            myViewHolder.tvToss.setText(match.getTossResult());
        }
        TextView textView5 = myViewHolder.result;
        textView5.setText(split[0] + "");
        TextView textView6 = myViewHolder.tournament;
        textView6.setText(match.getTournament() + "");
        if (!match.isTestMatch) {
            TextView textView7 = myViewHolder.team1scores;
            textView7.setText(match.getTeam1().getScore() + "/" + match.getTeam1().getWickets());
            TextView textView8 = myViewHolder.team2scores;
            textView8.setText(match.getTeam2().getScore() + "/" + match.getTeam2().getWickets());
            TextView textView9 = myViewHolder.team1sovers;
            textView9.setText("(" + match.getTeam1().getOversPlayed() + "." + match.getTeam1().getOverballs() + "/" + match.getOvers() + ")");
            TextView textView10 = myViewHolder.team2sovers;
            textView10.setText("(" + match.getTeam2().getOversPlayed() + "." + match.getTeam2().getOverballs() + "/" + match.getOvers() + ")");
            return;
        }
        if (match.followedOn) {
            TextView textView11 = myViewHolder.team1scores;
            textView11.setText(match.getTeam1().getScore() + "/" + match.getTeam1().getWickets() + " & " + match.getTeam4().getScore() + "/" + match.getTeam4().getWickets());
            TextView textView12 = myViewHolder.team2scores;
            textView12.setText(match.getTeam2().getScore() + "/" + match.getTeam2().getWickets() + " & " + match.getTeam3().getScore() + "/" + match.getTeam3().getWickets());
        } else {
            TextView textView13 = myViewHolder.team1scores;
            textView13.setText(match.getTeam1().getScore() + "/" + match.getTeam1().getWickets() + " & " + match.getTeam3().getScore() + "/" + match.getTeam3().getWickets());
            TextView textView14 = myViewHolder.team2scores;
            textView14.setText(match.getTeam2().getScore() + "/" + match.getTeam2().getWickets() + " & " + match.getTeam4().getScore() + "/" + match.getTeam4().getWickets());
        }
        myViewHolder.team1sovers.setVisibility(8);
        myViewHolder.team2sovers.setVisibility(8);
    }

    public int getItemCount() {
        try {
            return this.matches.size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
