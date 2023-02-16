package apps.shehryar.com.cricketscroingappPro;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Player.Player;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import apps.shehryar.com.cricketscroingappPro.Team.TeamEdiDeleteAndViewPlayersDialog;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.FontProvider;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class Custom_Teams_Suggestions_Adapter extends RecyclerView.Adapter<MyViewHolder> {
    Context context;
    ImageView imageView;
    View.OnClickListener onClickListener;
    ArrayList<Player> players;
    TeamEdiDeleteAndViewPlayersDialog.TeamEditDeleteCallBack teamEditDeleteCallBack;
    ArrayList<Team> teams;
    TextView textView;

    public Custom_Teams_Suggestions_Adapter(Context context2, ArrayList<Team> arrayList, ArrayList<Player> arrayList2, TeamEdiDeleteAndViewPlayersDialog.TeamEditDeleteCallBack teamEditDeleteCallBack2, View.OnClickListener onClickListener2) {
        this.context = context2;
        this.teams = arrayList;
        this.players = arrayList2;
        this.teamEditDeleteCallBack = teamEditDeleteCallBack2;
        this.onClickListener = onClickListener2;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_suggestions_layout_with_image, viewGroup, false);
        inflate.setOnClickListener(this.onClickListener);
        return new MyViewHolder(inflate);
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        try {
            FontProvider.setEuroStileFont(this.context, myViewHolder.tvName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.teams != null) {
            try {
                Picasso.with(this.context).load(Uri.parse(this.teams.get(i).getImage())).into(myViewHolder.imageView);
            } catch (Exception e2) {
                e2.printStackTrace();
                myViewHolder.imageView.setVisibility(4);
            }
            myViewHolder.tvName.setText(this.teams.get(i).getName());
        } else if (this.players != null) {
            try {
                Picasso.with(this.context).load(Uri.parse(this.players.get(i).getImage())).into(myViewHolder.imageView);
            } catch (Exception e3) {
                e3.printStackTrace();
                myViewHolder.imageView.setVisibility(4);
            }
            myViewHolder.tvName.setText(this.players.get(i).getName());
        }
    }

    public int getItemCount() {
        try {
            if (this.teams == null) {
                return this.players.size();
            }
            return this.teams.size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvName;

        public MyViewHolder(View view) {
            super(view);
            this.tvName = (TextView) view.findViewById(R.id.optionsbatname);
            this.imageView = (ImageView) view.findViewById(R.id.list_image_view);
        }
    }

    public void setTeams(ArrayList<Team> arrayList) {
        this.teams = arrayList;
        notifyDataSetChanged();
    }

    public void setPlayers(ArrayList<Player> arrayList) {
        this.players = arrayList;
        notifyDataSetChanged();
    }

    public void removeTeam(int i) {
        this.teams.remove(i);
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, this.teams.size());
    }
}
