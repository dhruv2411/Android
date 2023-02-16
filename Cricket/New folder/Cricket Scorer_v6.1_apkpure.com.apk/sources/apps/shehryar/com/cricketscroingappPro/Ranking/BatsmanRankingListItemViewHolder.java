package apps.shehryar.com.cricketscroingappPro.Ranking;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;

public class BatsmanRankingListItemViewHolder extends RecyclerView.ViewHolder {
    LinearLayout mainLayout;
    ImageView profileImage;
    TextView tvBatAvg;
    TextView tvBatBest;
    TextView tvBatMatches;
    TextView tvBatName;
    TextView tvBatPoints;
    TextView tvBatRank;
    TextView tvBatRuns;
    TextView tvBatSr;
    TextView tvTeamName;

    public BatsmanRankingListItemViewHolder(View view) {
        super(view);
        this.tvBatName = (TextView) view.findViewById(R.id.batsman_name);
        this.tvTeamName = (TextView) view.findViewById(R.id.tv_team_name);
        this.tvBatRank = (TextView) view.findViewById(R.id.tv_bat_ranking);
        this.tvBatPoints = (TextView) view.findViewById(R.id.tv_bat_points);
        this.tvBatRuns = (TextView) view.findViewById(R.id.tv_bat_runs);
        this.tvBatMatches = (TextView) view.findViewById(R.id.tv_bat_matches);
        this.tvBatSr = (TextView) view.findViewById(R.id.tv_bat_sr);
        this.tvBatAvg = (TextView) view.findViewById(R.id.tv_bat_average);
        this.tvBatBest = (TextView) view.findViewById(R.id.tv_bat_best);
        this.mainLayout = (LinearLayout) view.findViewById(R.id.batsman_ranking_main_layout);
    }
}
