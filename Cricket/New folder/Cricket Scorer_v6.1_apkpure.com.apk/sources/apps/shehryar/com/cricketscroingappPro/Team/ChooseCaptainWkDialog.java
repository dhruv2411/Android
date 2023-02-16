package apps.shehryar.com.cricketscroingappPro.Team;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.MyDialogFragment;
import apps.shehryar.com.cricketscroingappPro.Player.PlayerRecyclerViewAdapter;

public class ChooseCaptainWkDialog extends MyDialogFragment {
    boolean chooseCaptain;
    RecyclerView rvPlayers;
    Team team;
    TextView tvChooseCaptain;

    public interface playersRecyclerViewClickListener {
        void onRecyclerViewItemClicked(int i);
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.choose_captian_wk_dialog_layouta, viewGroup, false);
        this.tvChooseCaptain = (TextView) inflate.findViewById(R.id.tv_choose_captain);
        this.rvPlayers = (RecyclerView) inflate.findViewById(R.id.players_recycler_view);
        this.team = (Team) getArguments().getSerializable(DBHelper.TABLE_TEAM);
        this.chooseCaptain = getArguments().getBoolean("chooseCaptain");
        if (!this.chooseCaptain) {
            this.tvChooseCaptain.setText("CHOOSE WICKET KEEPER");
        }
        setUpRecyclerView();
        return inflate;
    }

    private void setUpRecyclerView() {
        this.rvPlayers.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.rvPlayers.setItemAnimator(new DefaultItemAnimator());
        this.rvPlayers.setItemViewCacheSize(20);
        this.rvPlayers.setDrawingCacheEnabled(true);
        this.rvPlayers.setDrawingCacheQuality(1048576);
        this.rvPlayers.setAdapter(new PlayerRecyclerViewAdapter(getActivity(), this.team.getPlayers(), new View.OnClickListener() {
            public void onClick(View view) {
                int childAdapterPosition = ChooseCaptainWkDialog.this.rvPlayers.getChildAdapterPosition(view);
                if (ChooseCaptainWkDialog.this.chooseCaptain) {
                    ChooseCaptainWkDialog.this.team.setCaptainId(ChooseCaptainWkDialog.this.team.getPlayers().get(childAdapterPosition).getPlayerId());
                    Toast.makeText(ChooseCaptainWkDialog.this.getActivity(), "Captain Selected successfully", 0).show();
                } else {
                    ChooseCaptainWkDialog.this.team.setKeeperId(ChooseCaptainWkDialog.this.team.getPlayers().get(childAdapterPosition).getPlayerId());
                    Toast.makeText(ChooseCaptainWkDialog.this.getActivity(), "Wicket Keeper selected successfully", 0).show();
                }
                new DBHelper(ChooseCaptainWkDialog.this.getActivity()).updateUserAddedTeam(ChooseCaptainWkDialog.this.team);
                ChooseCaptainWkDialog.this.dismiss();
            }
        }));
    }
}
