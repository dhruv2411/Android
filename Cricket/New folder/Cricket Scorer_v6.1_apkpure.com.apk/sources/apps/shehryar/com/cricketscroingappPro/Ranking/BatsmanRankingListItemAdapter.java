package apps.shehryar.com.cricketscroingappPro.Ranking;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.FontProvider;
import java.util.ArrayList;

public class BatsmanRankingListItemAdapter extends RecyclerView.Adapter<BatsmanRankingListItemViewHolder> {
    Activity activity;
    ArrayList<Batsman> batsmen;
    FragmentManager fragmentManager;
    int position;

    public int getItemViewType(int i) {
        return i;
    }

    public BatsmanRankingListItemAdapter(Activity activity2, ArrayList<Batsman> arrayList) {
        this.batsmen = arrayList;
        if (activity2 != null) {
            this.fragmentManager = activity2.getFragmentManager();
        }
        this.activity = activity2;
    }

    public BatsmanRankingListItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new BatsmanRankingListItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_batsmen_ranking_single_item_layout, viewGroup, false));
    }

    public void onBindViewHolder(BatsmanRankingListItemViewHolder batsmanRankingListItemViewHolder, final int i) {
        batsmanRankingListItemViewHolder.tvBatName.setText(this.batsmen.get(i).getName());
        FontProvider.setSeguUIFont(this.activity, batsmanRankingListItemViewHolder.tvBatName);
        Log.i("bat best", this.batsmen.get(i).getBest() + "");
        TextView textView = batsmanRankingListItemViewHolder.tvBatBest;
        textView.setText(this.batsmen.get(i).getBest() + "");
        Log.i("bat Sr", this.batsmen.get(i).getStrikerate() + "");
        batsmanRankingListItemViewHolder.tvBatSr.setText(this.batsmen.get(i).getStrikerate());
        Log.i("bat avg", this.batsmen.get(i).getAverage() + "");
        TextView textView2 = batsmanRankingListItemViewHolder.tvBatAvg;
        textView2.setText(this.batsmen.get(i).getAverage() + "");
        Log.i("bat matches", this.batsmen.get(i).getInnings() + "");
        TextView textView3 = batsmanRankingListItemViewHolder.tvBatMatches;
        textView3.setText(this.batsmen.get(i).getInnings() + "");
        Log.i("bat points", this.batsmen.get(i).getPoints() + "");
        TextView textView4 = batsmanRankingListItemViewHolder.tvBatPoints;
        textView4.setText(this.batsmen.get(i).getPoints() + "");
        Log.i("bat ranking", this.batsmen.get(i).getRanking() + "");
        TextView textView5 = batsmanRankingListItemViewHolder.tvBatRank;
        textView5.setText(this.batsmen.get(i).getRanking() + "");
        Log.i("bat runs", this.batsmen.get(i).getTotalScore() + "");
        TextView textView6 = batsmanRankingListItemViewHolder.tvBatRuns;
        textView6.setText(this.batsmen.get(i).getTotalScore() + "");
        batsmanRankingListItemViewHolder.tvTeamName.setText(this.batsmen.get(i).getTeam_Name());
        Log.i("team name", this.batsmen.get(i).getTeam_Name());
        if (this.batsmen.get(i).getRanking() > 999) {
            batsmanRankingListItemViewHolder.tvBatRank.setTextSize(12.0f);
        }
        this.position = i;
        if (i % 2 != 0) {
            batsmanRankingListItemViewHolder.mainLayout.setBackgroundColor(ContextCompat.getColor(this.activity, R.color.background_dark));
        }
        batsmanRankingListItemViewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DialogBatsmanRanking dialogBatsmanRanking = new DialogBatsmanRanking();
                Bundle bundle = new Bundle();
                bundle.putSerializable("batsman", BatsmanRankingListItemAdapter.this.batsmen.get(i));
                dialogBatsmanRanking.setArguments(bundle);
                dialogBatsmanRanking.show(BatsmanRankingListItemAdapter.this.fragmentManager, "batsman rank dialog");
            }
        });
    }

    public int getItemCount() {
        return this.batsmen.size();
    }

    public void updateItems(ArrayList<Batsman> arrayList) {
        this.batsmen = arrayList;
        notifyDataSetChanged();
    }
}
