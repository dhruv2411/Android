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
import apps.shehryar.com.cricketscroingappPro.Bowler.Bowler;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.FontProvider;
import java.util.ArrayList;

public class BowlerRankingListItemAdapter extends RecyclerView.Adapter<BatsmanRankingListItemViewHolder> {
    Activity activity;
    ArrayList<Bowler> bowlers;
    FragmentManager fragmentManager;
    int position;

    public int getItemViewType(int i) {
        return i;
    }

    public BowlerRankingListItemAdapter(Activity activity2, ArrayList<Bowler> arrayList) {
        this.bowlers = arrayList;
        try {
            this.fragmentManager = activity2.getFragmentManager();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.activity = activity2;
    }

    public BatsmanRankingListItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new BatsmanRankingListItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_batsmen_ranking_single_item_layout, viewGroup, false));
    }

    public void onBindViewHolder(BatsmanRankingListItemViewHolder batsmanRankingListItemViewHolder, final int i) {
        batsmanRankingListItemViewHolder.tvBatName.setText(this.bowlers.get(i).getName());
        FontProvider.setSeguUIFont(this.activity, batsmanRankingListItemViewHolder.tvBatName);
        TextView textView = batsmanRankingListItemViewHolder.tvBatPoints;
        textView.setText(this.bowlers.get(i).getPoints() + "");
        TextView textView2 = batsmanRankingListItemViewHolder.tvBatRank;
        textView2.setText(this.bowlers.get(i).getRanking() + "");
        batsmanRankingListItemViewHolder.tvTeamName.setText(this.bowlers.get(i).getTeamName());
        if (this.bowlers.get(i).getRanking() > 999) {
            batsmanRankingListItemViewHolder.tvBatRank.setTextSize(12.0f);
        }
        this.position = i;
        if (i % 2 != 0) {
            batsmanRankingListItemViewHolder.mainLayout.setBackgroundColor(ContextCompat.getColor(this.activity, R.color.background_dark));
        }
        batsmanRankingListItemViewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DialogBowlerRanking dialogBowlerRanking = new DialogBowlerRanking();
                Bundle bundle = new Bundle();
                bundle.putSerializable("bowler", BowlerRankingListItemAdapter.this.bowlers.get(i));
                dialogBowlerRanking.setArguments(bundle);
                dialogBowlerRanking.show(BowlerRankingListItemAdapter.this.fragmentManager, "batsman rank dialog");
            }
        });
    }

    public int getItemCount() {
        return this.bowlers.size();
    }
}
