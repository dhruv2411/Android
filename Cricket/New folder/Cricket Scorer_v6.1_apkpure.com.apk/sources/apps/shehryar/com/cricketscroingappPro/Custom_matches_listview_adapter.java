package apps.shehryar.com.cricketscroingappPro;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import java.util.ArrayList;

public class Custom_matches_listview_adapter extends ArrayAdapter {
    DBHelper dbHelper;
    int i = 0;
    int j = 1;
    LinearLayout main_layout;
    ArrayList<Match> matches;
    TextView result;
    Team team1;
    ArrayList<Team> team1Stats;
    TextView team1name;
    TextView team1overs;
    TextView team1score;
    Team team2;
    TextView team2name;
    TextView team2overs;
    TextView team2score;
    ArrayList<Team> team2stats;
    ArrayList<Team> teamnames;
    TextView tvvenue;

    public Custom_matches_listview_adapter(Context context, int i2, ArrayList<Match> arrayList, ArrayList<Team> arrayList2, ArrayList<Team> arrayList3, ArrayList<Team> arrayList4) {
        super(context, i2, arrayList);
        this.matches = arrayList;
        this.teamnames = arrayList2;
        this.team1Stats = arrayList3;
        this.team2stats = arrayList4;
    }

    public View getView(int i2, View view, ViewGroup viewGroup) {
        this.dbHelper = new DBHelper(getContext());
        Typeface createFromAsset = Typeface.createFromAsset(getContext().getAssets(), "fonts/DroidSans.ttf");
        Typeface createFromAsset2 = Typeface.createFromAsset(getContext().getAssets(), "fonts/RobotoCondensed_Bold.ttf");
        this.team1 = this.dbHelper.getTeam1Stats(this.matches.get(i2).getMatchID(), this.matches.get(i2).getTeam_Yours_id());
        this.team2 = this.dbHelper.getTeam1Stats(this.matches.get(i2).getMatchID(), this.matches.get(i2).getTeam_opp_id());
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.previous_matches_list, viewGroup, false);
        this.main_layout = (LinearLayout) inflate.findViewById(R.id.main_layout);
        this.team1name = (TextView) inflate.findViewById(R.id.team1_name);
        this.team2name = (TextView) inflate.findViewById(R.id.team2_name);
        this.team1score = (TextView) inflate.findViewById(R.id.team1score);
        this.team2score = (TextView) inflate.findViewById(R.id.team2score);
        this.team1overs = (TextView) inflate.findViewById(R.id.team1overs);
        this.team2overs = (TextView) inflate.findViewById(R.id.team2overs);
        this.result = (TextView) inflate.findViewById(R.id.match_result);
        this.tvvenue = (TextView) inflate.findViewById(R.id.tvvenueanddate);
        this.tvvenue.setTypeface(createFromAsset);
        this.team1name.setTypeface(createFromAsset);
        this.team2name.setTypeface(createFromAsset);
        this.team1score.setTypeface(createFromAsset2);
        this.team2score.setTypeface(createFromAsset2);
        this.team1overs.setTypeface(createFromAsset);
        this.team2overs.setTypeface(createFromAsset);
        this.result.setTypeface(createFromAsset);
        this.team1name.setText(this.team1.getName());
        TextView textView = this.team1score;
        textView.setText(this.team1.getScore() + "/" + this.team1.getWickets());
        TextView textView2 = this.team1overs;
        textView2.setText("(" + this.team1.getOversPlayed() + ".0)");
        this.team2name.setText(this.team2.getName());
        TextView textView3 = this.team2score;
        textView3.setText(this.team2.getScore() + "/" + this.team2.getWickets());
        TextView textView4 = this.team2overs;
        textView4.setText("(" + this.team2.getOversPlayed() + ".0)");
        TextView textView5 = this.tvvenue;
        textView5.setText(this.matches.get(i2).getVenue() + " " + this.matches.get(i2).getDate());
        TextView textView6 = this.result;
        textView6.setText(this.matches.get(i2).getResult() + "");
        this.i = this.i + 1;
        this.j = this.j + 1;
        return inflate;
    }
}
