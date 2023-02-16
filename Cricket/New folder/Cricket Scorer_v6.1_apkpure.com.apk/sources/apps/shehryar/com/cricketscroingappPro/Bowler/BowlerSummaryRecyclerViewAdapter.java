package apps.shehryar.com.cricketscroingappPro.Bowler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Player.PlayerSummaryViewHolder;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.Formatter;
import java.util.ArrayList;

public class BowlerSummaryRecyclerViewAdapter extends RecyclerView.Adapter<PlayerSummaryViewHolder> {
    ArrayList<Bowler> bowlers;

    public BowlerSummaryRecyclerViewAdapter(ArrayList<Bowler> arrayList) {
        this.bowlers = arrayList;
    }

    public PlayerSummaryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new PlayerSummaryViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.player_summary_adapter_layout, viewGroup, false));
    }

    public void onBindViewHolder(PlayerSummaryViewHolder playerSummaryViewHolder, int i) {
        Bowler bowler = this.bowlers.get(i);
        try {
            String formattedName = Formatter.getFormattedName(bowler.getName());
            playerSummaryViewHolder.setData(formattedName, bowler.getTotalscore() + "-" + bowler.getWickets() + "(" + bowler.getBowlerovers() + "." + bowler.getBalls() + ")", i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getItemCount() {
        return this.bowlers.size();
    }
}
