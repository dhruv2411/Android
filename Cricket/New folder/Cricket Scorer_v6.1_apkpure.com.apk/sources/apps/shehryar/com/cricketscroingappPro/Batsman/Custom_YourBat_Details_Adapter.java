package apps.shehryar.com.cricketscroingappPro.Batsman;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.Player.Player;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import java.util.ArrayList;
import java.util.Iterator;

public class Custom_YourBat_Details_Adapter extends ArrayAdapter {
    ArrayList<Long> allTeamIds;
    ArrayList<Batsman> batsmen;
    boolean check = false;
    Context context;
    String team_Name;
    Team yourTeam;

    public int getItemViewType(int i) {
        return i;
    }

    public Custom_YourBat_Details_Adapter(Context context2, int i, ArrayList arrayList, String str, Team team, ArrayList<Long> arrayList2) {
        super(context2, i, arrayList);
        this.batsmen = arrayList;
        this.context = context2;
        this.team_Name = str;
        this.yourTeam = team;
        this.allTeamIds = arrayList2;
    }

    public Custom_YourBat_Details_Adapter(Context context2, int i, ArrayList arrayList, boolean z, ArrayList<Long> arrayList2) {
        super(context2, i, arrayList);
        this.batsmen = arrayList;
        this.check = z;
        this.context = context2;
    }

    @NonNull
    public View getView(final int i, View view, ViewGroup viewGroup) {
        BatsmanListViewHolder batsmanListViewHolder;
        LayoutInflater from = LayoutInflater.from(getContext());
        if (view == null) {
            view = from.inflate(R.layout.custom_yourbat_details_adapter, viewGroup, false);
            batsmanListViewHolder = new BatsmanListViewHolder(view);
            view.setTag(batsmanListViewHolder);
        } else {
            batsmanListViewHolder = (BatsmanListViewHolder) view.getTag();
        }
        batsmanListViewHolder.mainlayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Custom_YourBat_Details_Adapter.this.context, BatsmanDetailActivity.class);
                intent.putExtra("batsman", Custom_YourBat_Details_Adapter.this.batsmen.get(i));
                if (Custom_YourBat_Details_Adapter.this.check) {
                    intent.putExtra("teamname", Custom_YourBat_Details_Adapter.this.batsmen.get(i).getTeam_Name());
                } else {
                    intent.putExtra("teamname", Custom_YourBat_Details_Adapter.this.team_Name);
                }
                if (Custom_YourBat_Details_Adapter.this.yourTeam != null && !Custom_YourBat_Details_Adapter.this.yourTeam.getPlayers().isEmpty()) {
                    intent.putExtra(DBHelper.TABLE_PLAYERS, Custom_YourBat_Details_Adapter.this.getPlayer(Custom_YourBat_Details_Adapter.this.yourTeam, Custom_YourBat_Details_Adapter.this.batsmen.get(i)));
                }
                intent.putExtra("allTeamIds", Custom_YourBat_Details_Adapter.this.allTeamIds);
                Custom_YourBat_Details_Adapter.this.context.startActivity(intent);
            }
        });
        batsmanListViewHolder.batsman.setText(this.batsmen.get(i).getName());
        TextView textView = batsmanListViewHolder.score;
        textView.setText(this.batsmen.get(i).getTotalScore() + "");
        TextView textView2 = batsmanListViewHolder.batNo;
        textView2.setText((i + 1) + "");
        batsmanListViewHolder.batsmasnTeamName.setText(this.batsmen.get(i).getTeam_Name());
        TextView textView3 = batsmanListViewHolder.matches;
        textView3.setText(this.batsmen.get(i).getMatches() + "");
        TextView textView4 = batsmanListViewHolder.sr;
        textView4.setText(this.batsmen.get(i).getStrikerate() + "");
        TextView textView5 = batsmanListViewHolder.best;
        textView5.setText(this.batsmen.get(i).getBest() + "");
        if (i % 2 != 0) {
            batsmanListViewHolder.mainlayout.setBackgroundColor(getContext().getResources().getColor(R.color.background_dark));
        }
        TextView textView6 = batsmanListViewHolder.average;
        textView6.setText(this.batsmen.get(i).getAverage() + "");
        return view;
    }

    /* access modifiers changed from: private */
    public Player getPlayer(Team team, Batsman batsman) {
        Iterator<Player> it = team.getPlayers().iterator();
        while (it.hasNext()) {
            Player next = it.next();
            if (next.getName().equals(batsman.getName())) {
                return next;
            }
        }
        return null;
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

    class BatsmanListViewHolder {
        TextView average;
        TextView batNo;
        TextView batsman;
        TextView batsmasnTeamName;
        TextView best;
        LinearLayout mainlayout;
        TextView matches;
        TextView score;
        TextView sr;

        public BatsmanListViewHolder(View view) {
            this.batsman = (TextView) view.findViewById(R.id.tvbatname);
            this.score = (TextView) view.findViewById(R.id.tvbatscore);
            this.matches = (TextView) view.findViewById(R.id.tvyourbatmatches);
            this.average = (TextView) view.findViewById(R.id.tvbatavg);
            this.best = (TextView) view.findViewById(R.id.tvbatbest);
            this.batsmasnTeamName = (TextView) view.findViewById(R.id.tvbatTeamName);
            this.mainlayout = (LinearLayout) view.findViewById(R.id.yourbatdetails_mainbatlayout);
            this.sr = (TextView) view.findViewById(R.id.tvbatsr);
            this.sr.setSingleLine(true);
            this.batNo = (TextView) view.findViewById(R.id.tvbatno);
            this.batsman.setSelected(true);
            this.batsmasnTeamName.setSelected(true);
        }
    }
}
