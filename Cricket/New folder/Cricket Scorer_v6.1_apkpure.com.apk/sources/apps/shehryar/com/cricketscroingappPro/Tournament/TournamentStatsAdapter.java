package apps.shehryar.com.cricketscroingappPro.Tournament;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.util.List;

public class TournamentStatsAdapter extends RecyclerView.Adapter<TournamentStatsViewHolder> {
    /* access modifiers changed from: private */
    public Context context;
    private List<TournamentEntity> tournamentEntities;

    public TournamentStatsAdapter(Context context2, List<TournamentEntity> list) {
        this.tournamentEntities = list;
        this.context = context2;
    }

    public TournamentStatsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new TournamentStatsViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tournament_stats_adapter_layout, viewGroup, false));
    }

    public void onBindViewHolder(TournamentStatsViewHolder tournamentStatsViewHolder, int i) {
        tournamentStatsViewHolder.setData(this.tournamentEntities.get(i));
    }

    public int getItemCount() {
        return this.tournamentEntities.size();
    }

    public class TournamentStatsViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView ivProfile;
        public TextView tvStatsHeading;
        public TextView tvStatsName;
        public TextView tvStatsValue;

        public TournamentStatsViewHolder(View view) {
            super(view);
            this.tvStatsHeading = (TextView) view.findViewById(R.id.tv_stats_heading);
            this.tvStatsName = (TextView) view.findViewById(R.id.tv_stats_player_name);
            this.tvStatsValue = (TextView) view.findViewById(R.id.tv_stats_value);
            this.ivProfile = (CircleImageView) view.findViewById(R.id.iv_stats_image);
        }

        public void setData(TournamentEntity tournamentEntity) {
            this.tvStatsHeading.setText(tournamentEntity.getHeading());
            this.tvStatsValue.setText(tournamentEntity.getStats(tournamentEntity.getStatsType()));
            this.tvStatsName.setText(tournamentEntity.getName());
            if (tournamentEntity.getImgUrl() != null && !tournamentEntity.getImgUrl().isEmpty()) {
                this.ivProfile.setImageURI(Uri.parse(tournamentEntity.getImgUrl()));
            } else if (tournamentEntity.getImgUrl() == null) {
                this.ivProfile.setVisibility(4);
            } else {
                this.ivProfile.setImageDrawable(ContextCompat.getDrawable(TournamentStatsAdapter.this.context, R.drawable.ic_player_profile));
            }
        }
    }
}
