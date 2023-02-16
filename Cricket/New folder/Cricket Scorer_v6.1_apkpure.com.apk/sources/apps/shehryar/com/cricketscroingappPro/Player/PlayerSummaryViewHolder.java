package apps.shehryar.com.cricketscroingappPro.Player;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;

public class PlayerSummaryViewHolder extends RecyclerView.ViewHolder {
    Context context;
    ConstraintLayout mainLayout;
    TextView tvPlayerName;
    TextView tvPlayerValue;

    public PlayerSummaryViewHolder(View view) {
        super(view);
        this.tvPlayerName = (TextView) view.findViewById(R.id.player_name);
        this.tvPlayerValue = (TextView) view.findViewById(R.id.player_stats);
        this.mainLayout = (ConstraintLayout) view.findViewById(R.id.main_layout);
        this.context = view.getContext();
    }

    public void setData(String str, String str2, int i) {
        this.tvPlayerName.setText(str);
        this.tvPlayerValue.setText(str2);
        if ((i + 1) % 2 == 0) {
            this.mainLayout.setBackgroundColor(ContextCompat.getColor(this.context, R.color.background));
        } else {
            this.mainLayout.setBackgroundColor(ContextCompat.getColor(this.context, R.color.background_dark));
        }
    }
}
