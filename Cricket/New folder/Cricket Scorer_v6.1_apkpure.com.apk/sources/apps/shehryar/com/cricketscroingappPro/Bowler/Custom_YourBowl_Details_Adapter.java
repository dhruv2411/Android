package apps.shehryar.com.cricketscroingappPro.Bowler;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import java.util.ArrayList;

public class Custom_YourBowl_Details_Adapter extends ArrayAdapter {
    ArrayList<Long> allTeamIds;
    ArrayList<Bowler> batsmen;
    boolean check = false;
    Context context;
    String team_name;
    Team yourteam;

    public int getItemViewType(int i) {
        return i;
    }

    public Custom_YourBowl_Details_Adapter(Context context2, int i, ArrayList arrayList, String str, Team team, ArrayList<Long> arrayList2) {
        super(context2, i, arrayList);
        this.batsmen = arrayList;
        this.context = context2;
        this.team_name = str;
        this.yourteam = team;
        this.allTeamIds = arrayList2;
    }

    public Custom_YourBowl_Details_Adapter(Context context2, int i, ArrayList arrayList, boolean z) {
        super(context2, i, arrayList);
        this.batsmen = arrayList;
        this.context = context2;
        this.check = z;
    }

    @NonNull
    public View getView(final int i, View view, ViewGroup viewGroup) {
        BowlersListViewHolder bowlersListViewHolder;
        LayoutInflater from = LayoutInflater.from(getContext());
        if (view == null) {
            view = from.inflate(R.layout.custom_yourbowl_details_adapter, viewGroup, false);
            bowlersListViewHolder = new BowlersListViewHolder(view);
            view.setTag(bowlersListViewHolder);
        } else {
            bowlersListViewHolder = (BowlersListViewHolder) view.getTag();
        }
        bowlersListViewHolder.mainlayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Custom_YourBowl_Details_Adapter.this.context, BowlerDetailsActiivty.class);
                intent.putExtra("bowler", Custom_YourBowl_Details_Adapter.this.batsmen.get(i));
                intent.putExtra("batname", Custom_YourBowl_Details_Adapter.this.batsmen.get(i).getName());
                intent.putExtra("allTeamIds", Custom_YourBowl_Details_Adapter.this.allTeamIds);
                if (Custom_YourBowl_Details_Adapter.this.check) {
                    intent.putExtra("teamname", Custom_YourBowl_Details_Adapter.this.batsmen.get(i).getTeamName());
                } else {
                    intent.putExtra("teamname", Custom_YourBowl_Details_Adapter.this.team_name);
                }
                if (Custom_YourBowl_Details_Adapter.this.yourteam != null && !Custom_YourBowl_Details_Adapter.this.yourteam.getPlayers().isEmpty()) {
                    intent.putExtra(DBHelper.TABLE_PLAYERS, Custom_YourBowl_Details_Adapter.this.yourteam.getPlayers().get(i));
                }
                Custom_YourBowl_Details_Adapter.this.context.startActivity(intent);
            }
        });
        TextView textView = bowlersListViewHolder.bowlerno;
        textView.setText((i + 1) + "");
        bowlersListViewHolder.bowler.setText(this.batsmen.get(i).getName());
        TextView textView2 = bowlersListViewHolder.wickets;
        textView2.setText(this.batsmen.get(i).getWickets() + "");
        TextView textView3 = bowlersListViewHolder.matches;
        textView3.setText(this.batsmen.get(i).getMatches() + "");
        bowlersListViewHolder.bowlerTeamName.setText(this.batsmen.get(i).getTeamName());
        TextView textView4 = bowlersListViewHolder.best;
        textView4.setText(this.batsmen.get(i).getBest() + "");
        if (i % 2 != 0) {
            bowlersListViewHolder.mainlayout.setBackgroundColor(ContextCompat.getColor(this.context, R.color.background_dark));
        }
        TextView textView5 = bowlersListViewHolder.average;
        textView5.setText(this.batsmen.get(i).getAverage() + "");
        bowlersListViewHolder.eco.setText(this.batsmen.get(i).getEco());
        return view;
    }

    public int getCount() {
        try {
            return this.batsmen.size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getViewTypeCount() {
        return getCount();
    }

    class BowlersListViewHolder {
        TextView average;
        TextView best;
        TextView bowler;
        TextView bowlerTeamName;
        TextView bowlerno;
        TextView eco;
        LinearLayout mainlayout;
        TextView matches;
        TextView wickets;

        public BowlersListViewHolder(View view) {
            this.bowler = (TextView) view.findViewById(R.id.tvbowlername);
            this.wickets = (TextView) view.findViewById(R.id.tvbowlerwickets);
            this.matches = (TextView) view.findViewById(R.id.tvyourbowlermatches);
            this.average = (TextView) view.findViewById(R.id.tvbowleravg);
            this.best = (TextView) view.findViewById(R.id.tvbowlerbest);
            this.eco = (TextView) view.findViewById(R.id.tvbowlereco);
            this.eco.setSingleLine(true);
            this.bowlerTeamName = (TextView) view.findViewById(R.id.tvbowlerTeamName);
            this.mainlayout = (LinearLayout) view.findViewById(R.id.yourbowldetails_mainbatlayout);
            this.bowlerno = (TextView) view.findViewById(R.id.tvbowlerno);
            this.bowler.setSelected(true);
            this.bowlerTeamName.setSelected(true);
        }
    }
}
