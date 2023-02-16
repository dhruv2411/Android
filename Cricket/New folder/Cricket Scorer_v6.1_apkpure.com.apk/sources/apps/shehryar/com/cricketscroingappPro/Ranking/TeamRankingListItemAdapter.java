package apps.shehryar.com.cricketscroingappPro.Ranking;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.FontProvider;
import java.util.ArrayList;

public class TeamRankingListItemAdapter extends RecyclerView.Adapter<BatsmanRankingListItemViewHolder> {
    Activity activity;
    FragmentManager fragmentManager;
    int position;
    ArrayList<Team> teams;

    public TeamRankingListItemAdapter(Activity activity2, ArrayList<Team> arrayList) {
        this.teams = arrayList;
        this.fragmentManager = activity2.getFragmentManager();
        this.activity = activity2;
    }

    public BatsmanRankingListItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new BatsmanRankingListItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_batsmen_ranking_single_item_layout, viewGroup, false));
    }

    public void onBindViewHolder(BatsmanRankingListItemViewHolder batsmanRankingListItemViewHolder, final int i) {
        batsmanRankingListItemViewHolder.tvBatName.setText(this.teams.get(i).getName());
        TextView textView = batsmanRankingListItemViewHolder.tvBatPoints;
        textView.setText(this.teams.get(i).getPoints() + "");
        TextView textView2 = batsmanRankingListItemViewHolder.tvBatRank;
        textView2.setText(this.teams.get(i).getRanking() + "");
        batsmanRankingListItemViewHolder.tvTeamName.setVisibility(8);
        FontProvider.setSeguUIFont(this.activity, batsmanRankingListItemViewHolder.tvBatName);
        if (this.teams.get(i).getRanking() > 999) {
            batsmanRankingListItemViewHolder.tvBatRank.setTextSize(12.0f);
        }
        this.position = i;
        if (i % 2 != 0) {
            batsmanRankingListItemViewHolder.mainLayout.setBackgroundColor(ContextCompat.getColor(this.activity, R.color.background_dark));
        }
        batsmanRankingListItemViewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DialogTeamRanking dialogTeamRanking = new DialogTeamRanking();
                Bundle bundle = new Bundle();
                bundle.putSerializable(DBHelper.TABLE_TEAM, TeamRankingListItemAdapter.this.teams.get(i));
                dialogTeamRanking.setArguments(bundle);
                dialogTeamRanking.show(TeamRankingListItemAdapter.this.fragmentManager, "team rank dialog");
            }
        });
    }

    public int getItemCount() {
        return this.teams.size();
    }

    public void updateItems(ArrayList<Team> arrayList) {
        this.teams = arrayList;
        notifyDataSetChanged();
    }
}
