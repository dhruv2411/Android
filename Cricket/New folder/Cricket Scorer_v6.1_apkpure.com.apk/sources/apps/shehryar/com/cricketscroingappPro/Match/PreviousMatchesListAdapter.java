package apps.shehryar.com.cricketscroingappPro.Match;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.FontProvider;
import java.util.ArrayList;

public class PreviousMatchesListAdapter extends RecyclerView.Adapter<MyViewHolder> {
    Activity activity;
    DBHelper dbHelper;
    ArrayList<Match> matches;
    Context mcontext;
    Team team1;
    Team team2;
    Typeface typeface;
    Typeface typefacebold;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout main_layout;
        LinearLayout momlayout;
        TextView result;
        TextView team1name;
        TextView team1scores;
        TextView team1sovers;
        TextView team2name;
        TextView team2scores;
        TextView team2sovers;
        TextView tournament;
        TextView tvToss;
        TextView tvTournamentStage;
        TextView tvmom;
        TextView tvvenue;

        public MyViewHolder(View view) {
            super(view);
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
            this.tvmom = (TextView) view.findViewById(R.id.mom);
            this.tvToss = (TextView) view.findViewById(R.id.tvToss);
            this.momlayout = (LinearLayout) view.findViewById(R.id.momLayout);
            this.tvTournamentStage = (TextView) view.findViewById(R.id.tvtournamentStage);
            view.setOnClickListener(this);
        }

        public void onClick(View view) {
            int layoutPosition = getLayoutPosition();
            Intent intent = new Intent(PreviousMatchesListAdapter.this.mcontext, Previous_Match_Result.class);
            intent.putExtra(DBHelper.TABLE_MATCH, PreviousMatchesListAdapter.this.matches.get(layoutPosition));
            intent.putExtra("index", layoutPosition);
            try {
                PreviousMatchesListAdapter.this.activity.startActivityForResult(intent, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public PreviousMatchesListAdapter(Context context, Activity activity2, ArrayList<Match> arrayList) {
        this.mcontext = context;
        this.matches = arrayList;
        this.activity = activity2;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.previous_matches_list, viewGroup, false);
        this.typeface = Typeface.createFromAsset(this.mcontext.getAssets(), "fonts/DroidSans.ttf");
        this.typefacebold = Typeface.createFromAsset(this.mcontext.getAssets(), "fonts/RobotoCondensed_Bold.ttf");
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
        myViewHolder.tvTournamentStage.setTypeface(this.typeface);
        myViewHolder.tvmom.setTypeface(this.typefacebold);
        FontProvider.setRobotoCondensedFont(this.mcontext, myViewHolder.team1sovers);
        FontProvider.setRobotoCondensedFont(this.mcontext, myViewHolder.team2sovers);
        String[] split = match.getResult().split(":");
        myViewHolder.team1name.setText(match.getTeam1().getName());
        myViewHolder.team2name.setText(match.getTeam2().getName());
        TextView textView = myViewHolder.tvvenue;
        textView.setText(match.getVenue() + " " + match.getDate() + " " + match.getTime());
        if (match.getTossResult().equals("")) {
            myViewHolder.tvToss.setVisibility(8);
        } else {
            myViewHolder.tvToss.setText(match.getTossResult());
        }
        TextView textView2 = myViewHolder.result;
        textView2.setText(split[0] + "");
        TextView textView3 = myViewHolder.tournament;
        textView3.setText(match.getTournament() + "");
        myViewHolder.tvTournamentStage.setText(match.getTournamentStage());
        if (match.getManOfTheMatchModel() != null) {
            try {
                myViewHolder.tvmom.setText(match.getManOfTheMatchModel().getManOfTheMatchString((ManOfTheMatchModel) null));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (split.length > 1) {
            TextView textView4 = myViewHolder.tvmom;
            textView4.setText("MOTM: " + split[1]);
        } else {
            myViewHolder.tvmom.setText("");
        }
        if (!match.isTestMatch) {
            TextView textView5 = myViewHolder.team1scores;
            textView5.setText(match.getTeam1().getScore() + "/" + match.getTeam1().getWickets());
            TextView textView6 = myViewHolder.team2scores;
            textView6.setText(match.getTeam2().getScore() + "/" + match.getTeam2().getWickets());
            TextView textView7 = myViewHolder.team1sovers;
            textView7.setText("(" + match.getTeam1().getOversPlayed() + "." + match.getTeam1().getOverballs() + "/" + match.getOvers() + ")");
            TextView textView8 = myViewHolder.team2sovers;
            textView8.setText("(" + match.getTeam2().getOversPlayed() + "." + match.getTeam2().getOverballs() + "/" + match.getOvers() + ")");
            return;
        }
        if (match.followedOn) {
            TextView textView9 = myViewHolder.team1scores;
            textView9.setText(match.getTeam1().getScore() + "/" + match.getTeam1().getWickets() + " & " + match.getTeam4().getScore() + "/" + match.getTeam4().getWickets());
            TextView textView10 = myViewHolder.team2scores;
            textView10.setText(match.getTeam2().getScore() + "/" + match.getTeam2().getWickets() + " & " + match.getTeam3().getScore() + "/" + match.getTeam3().getWickets());
        } else {
            TextView textView11 = myViewHolder.team1scores;
            textView11.setText(match.getTeam1().getScore() + "/" + match.getTeam1().getWickets() + " & " + match.getTeam3().getScore() + "/" + match.getTeam3().getWickets());
            TextView textView12 = myViewHolder.team2scores;
            textView12.setText(match.getTeam2().getScore() + "/" + match.getTeam2().getWickets() + " & " + match.getTeam4().getScore() + "/" + match.getTeam4().getWickets());
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
