package apps.shehryar.com.cricketscroingappPro.Player;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.MyToast;
import apps.shehryar.com.cricketscroingappPro.Player.PlayerEditDeleteDialog;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class PlayerRecyclerViewAdapter extends RecyclerView.Adapter<playerViewHolder> implements PlayerEditDeleteDialog.EditDeleteCallBack {
    Activity activity = ((Activity) this.context);
    Context context;
    View.OnClickListener onClickListener;
    ArrayList<Player> players;

    public int getItemViewType(int i) {
        return i;
    }

    public void onAddInPlayingXiTapped(boolean z) {
    }

    public void onChooseCaptainTapped() {
    }

    public PlayerRecyclerViewAdapter(Context context2, ArrayList<Player> arrayList, View.OnClickListener onClickListener2) {
        this.context = context2;
        this.players = arrayList;
        this.onClickListener = onClickListener2;
    }

    public playerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.player_layout, viewGroup, false);
        inflate.setOnClickListener(this.onClickListener);
        return new playerViewHolder(inflate);
    }

    public void onBindViewHolder(playerViewHolder playerviewholder, int i) {
        playerviewholder.playerName.setText(this.players.get(i).getName());
        if (this.players.get(i).getImage() != null) {
            try {
                Picasso.with(this.context).load(Uri.parse(this.players.get(i).getImage())).into(playerviewholder.profileImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                playerviewholder.profileImage.setImageDrawable(ContextCompat.getDrawable(this.context, R.drawable.ic_player_profile));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (this.players.get(i).isInPlayingXI()) {
            playerviewholder.imageChecked.setVisibility(0);
        } else {
            playerviewholder.imageChecked.setVisibility(8);
        }
        if (this.players.get(i).isCaptain()) {
            playerviewholder.ivCaptain.setVisibility(0);
        } else {
            playerviewholder.ivCaptain.setVisibility(8);
        }
        if (this.players.get(i).isBatsman()) {
            playerviewholder.ivBatsman.setVisibility(0);
        } else {
            playerviewholder.ivBatsman.setVisibility(4);
        }
        if (this.players.get(i).isBowler()) {
            playerviewholder.ivBowler.setVisibility(0);
        } else {
            playerviewholder.ivBowler.setVisibility(8);
        }
        if (this.players.get(i).isWicketKeeper()) {
            playerviewholder.ivKeeper.setVisibility(0);
        } else {
            playerviewholder.ivKeeper.setVisibility(8);
        }
    }

    public int getItemCount() {
        try {
            return this.players.size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void onEditTapped() {
        MyToast.showToast(this.context, "Edit was tapped");
    }

    public void onDeleteTapped() {
        MyToast.showToast(this.context, "Delete was tapped");
    }

    public class playerViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageChecked;
        public ImageView ivBatsman;
        public ImageView ivBowler;
        public ImageView ivCaptain;
        public ImageView ivKeeper;
        public TextView playerName;
        public ImageView profileImage;

        public playerViewHolder(View view) {
            super(view);
            this.profileImage = (ImageView) view.findViewById(R.id.profile_image);
            this.playerName = (TextView) view.findViewById(R.id.playerName);
            this.imageChecked = (ImageView) view.findViewById(R.id.imageChecked);
            this.ivBatsman = (ImageView) view.findViewById(R.id.iv_helmet);
            this.ivBowler = (ImageView) view.findViewById(R.id.iv_bowler);
            this.ivKeeper = (ImageView) view.findViewById(R.id.iv_keeper);
            this.ivCaptain = (ImageView) view.findViewById(R.id.iv_captain);
        }
    }

    public void setPlayers(ArrayList<Player> arrayList) {
        this.players = arrayList;
        notifyDataSetChanged();
    }

    public void updatePlayer(int i) {
        notifyItemChanged(i);
    }

    public void deletePlayer(int i) {
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, this.players.size());
    }
}
