package apps.shehryar.com.cricketscroingappPro.Team;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.MyToast;
import apps.shehryar.com.cricketscroingappPro.Player.AddPlayerDialog;
import apps.shehryar.com.cricketscroingappPro.Player.DialogWithButtons;
import apps.shehryar.com.cricketscroingappPro.Player.Player;
import apps.shehryar.com.cricketscroingappPro.Player.PlayerEditDeleteDialog;
import apps.shehryar.com.cricketscroingappPro.Player.PlayerRecyclerViewAdapter;
import apps.shehryar.com.cricketscroingappPro.Ranking.DialogBatsmanRanking;
import apps.shehryar.com.cricketscroingappPro.Ranking.DialogBowlerRanking;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.AlertDialogBuilder;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.Dialog;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.FontProvider;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.SharedPrefsHelper;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.UtilityFunctions;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class OtherTeamPlayersActivity extends AppCompatActivity implements View.OnClickListener, AddPlayerDialog.playerAddedCallBackInterface, DialogWithButtons.DialogWithButtonsInteractionListener, PlayerEditDeleteDialog.EditDeleteCallBack {
    PlayerRecyclerViewAdapter adapter;
    Button bAddPlayer;
    Button bsubmit;
    int code;
    String currentTeamName;
    DBHelper dbhelper;
    PlayerEditDeleteDialog.EditDeleteCallBack editDeleteCallBack = new PlayerEditDeleteDialog.EditDeleteCallBack() {
        public void onEditTapped() {
            new AddPlayerDialog(OtherTeamPlayersActivity.this, OtherTeamPlayersActivity.this.team, OtherTeamPlayersActivity.this.team.getPlayers().get(OtherTeamPlayersActivity.this.position), OtherTeamPlayersActivity.this, OtherTeamPlayersActivity.this.team.getPlayers()).show(OtherTeamPlayersActivity.this.getFragmentManager(), "add Player Dialog");
        }

        public void onDeleteTapped() {
            AlertDialogBuilder.showAlertDialog(new Dialog(OtherTeamPlayersActivity.this, "Delete Player", "Are you sure you want to delete this Player", "Yes", "No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    switch (i) {
                        case -2:
                            dialogInterface.dismiss();
                            return;
                        case -1:
                            OtherTeamPlayersActivity.this.dbhelper.deleteUserAddedPlayer(OtherTeamPlayersActivity.this.team.getPlayers().get(OtherTeamPlayersActivity.this.position));
                            OtherTeamPlayersActivity.this.team.getPlayers().remove(OtherTeamPlayersActivity.this.position);
                            OtherTeamPlayersActivity.this.adapter.deletePlayer(OtherTeamPlayersActivity.this.position);
                            if (OtherTeamPlayersActivity.this.team.getPlayers().isEmpty()) {
                                OtherTeamPlayersActivity.this.tvNoPlayersAdded.setVisibility(0);
                            }
                            dialogInterface.dismiss();
                            return;
                        default:
                            return;
                    }
                }
            }));
        }

        public void onAddInPlayingXiTapped(boolean z) throws Exception {
            int noOfPlayersWithPlayingXI = UtilityFunctions.getNoOfPlayersWithPlayingXI(OtherTeamPlayersActivity.this.team);
            Player player = OtherTeamPlayersActivity.this.team.getPlayers().get(OtherTeamPlayersActivity.this.position);
            if (z) {
                SharedPrefsHelper.removePlayerFromPlayingXI(OtherTeamPlayersActivity.this, player);
                OtherTeamPlayersActivity.this.team.getPlayers().get(OtherTeamPlayersActivity.this.position).setInPlayingXI(false);
                if (OtherTeamPlayersActivity.this.adapter != null) {
                    OtherTeamPlayersActivity.this.adapter.updatePlayer(OtherTeamPlayersActivity.this.position);
                }
            } else if (noOfPlayersWithPlayingXI == 11) {
                MyToast.showLongToast(OtherTeamPlayersActivity.this, "Error. Player could not be added because you have already selected all XI players in playing XI. Please remove one from playing XI and try again");
            } else {
                SharedPrefsHelper.insertPlayerAsPlayingXI(OtherTeamPlayersActivity.this, player);
                OtherTeamPlayersActivity.this.team.getPlayers().get(OtherTeamPlayersActivity.this.position).setInPlayingXI(true);
                if (OtherTeamPlayersActivity.this.adapter != null) {
                    OtherTeamPlayersActivity.this.adapter.updatePlayer(OtherTeamPlayersActivity.this.position);
                }
            }
        }

        public void onChooseCaptainTapped() {
            final ArrayList arrayList = new ArrayList();
            arrayList.add("View Limited Overs Stats");
            arrayList.add("View Test Matches Stats");
            arrayList.add("View All Matches Stats");
            arrayList.add((Object) null);
            arrayList.add((Object) null);
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add("View Batting Stats");
            arrayList2.add("View Bowling Stats");
            arrayList2.add((Object) null);
            arrayList2.add((Object) null);
            arrayList2.add((Object) null);
            AnonymousClass2 r1 = new DialogWithButtons.DialogWithButtonsInteractionListener() {
                public void onButton3Tapped() {
                }

                public void onButton4Tapped() {
                }

                public void onButton5Tapped() {
                }

                public void onButton1Tapped() {
                    OtherTeamPlayersActivity.this.showBattingStats = true;
                    OtherTeamPlayersActivity.this.showBowlingStats = false;
                    DialogWithButtons.newInstance(arrayList).show(OtherTeamPlayersActivity.this.getFragmentManager(), DialogWithButtons.class.toString());
                }

                public void onButton2Tapped() {
                    OtherTeamPlayersActivity.this.showBattingStats = false;
                    OtherTeamPlayersActivity.this.showBowlingStats = true;
                    DialogWithButtons.newInstance(arrayList).show(OtherTeamPlayersActivity.this.getFragmentManager(), DialogWithButtons.class.toString());
                }
            };
            DialogWithButtons newInstance = DialogWithButtons.newInstance(arrayList2);
            newInstance.setDialogWithButtonsInteractionListener(r1);
            newInstance.show(OtherTeamPlayersActivity.this.getFragmentManager(), DialogWithButtons.class.toString());
        }
    };
    EditText[] edplayers;
    EditText edyourteam;
    Team_players obj;
    int position;
    /* access modifiers changed from: private */
    public RecyclerView recyclerView;
    boolean showBattingStats;
    boolean showBowlingStats;
    String side;
    Team team;
    String teamname;
    ArrayList<String> teamplayers;
    TextView tvNoPlayersAdded;
    TextView tvTeamName;

    public void onButton4Tapped() {
    }

    public void onButton5Tapped() {
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResumeFragments() {
        super.onResumeFragments();
    }

    public void onButton1Tapped() {
        if (this.showBattingStats) {
            try {
                DialogBatsmanRanking.newInstance(this.dbhelper.getYourBatsmanStats(this.team.getName(), this.team.getPlayers().get(this.position).getName(), UtilityFunctions.getTeamIds(this.dbhelper.getMatches(2), 2))).show(getFragmentManager(), DialogBatsmanRanking.class.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                DialogBowlerRanking.newInstance(this.dbhelper.getYourBowlerStats(this.team.getName(), this.team.getPlayers().get(this.position).getName(), UtilityFunctions.getTeamIds(this.dbhelper.getMatches(2), 2))).show(getFragmentManager(), DialogBowlerRanking.class.toString());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void onButton2Tapped() {
        if (this.showBattingStats) {
            try {
                DialogBatsmanRanking.newInstance(this.dbhelper.getYourBatsmanStats(this.team.getName(), this.team.getPlayers().get(this.position).getName(), UtilityFunctions.getTeamIds(this.dbhelper.getMatches(1), 1))).show(getFragmentManager(), DialogBatsmanRanking.class.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                DialogBowlerRanking.newInstance(this.dbhelper.getYourBowlerStats(this.team.getName(), this.team.getPlayers().get(this.position).getName(), UtilityFunctions.getTeamIds(this.dbhelper.getMatches(1), 1))).show(getFragmentManager(), DialogBowlerRanking.class.toString());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void onButton3Tapped() {
        if (this.showBattingStats) {
            try {
                DialogBatsmanRanking.newInstance(this.dbhelper.getYourBatsmanStats(this.team.getName(), this.team.getPlayers().get(this.position).getName(), UtilityFunctions.getTeamIds(this.dbhelper.getMatches(3), 3))).show(getFragmentManager(), DialogBatsmanRanking.class.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                DialogBowlerRanking.newInstance(this.dbhelper.getYourBowlerStats(this.team.getName(), this.team.getPlayers().get(this.position).getName(), UtilityFunctions.getTeamIds(this.dbhelper.getMatches(3), 3))).show(getFragmentManager(), DialogBowlerRanking.class.toString());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public class MyOnClickListener implements View.OnClickListener, Serializable {
        public MyOnClickListener() {
        }

        public void onClick(View view) {
            OtherTeamPlayersActivity.this.position = OtherTeamPlayersActivity.this.recyclerView.getChildLayoutPosition(view);
            PlayerEditDeleteDialog playerEditDeleteDialog = new PlayerEditDeleteDialog();
            Bundle bundle = new Bundle();
            bundle.putBoolean("inPlayingXI", OtherTeamPlayersActivity.this.team.getPlayers().get(OtherTeamPlayersActivity.this.position).isInPlayingXI());
            playerEditDeleteDialog.setArguments(bundle);
            playerEditDeleteDialog.show(OtherTeamPlayersActivity.this.getSupportFragmentManager(), "Edit Delete Dialog");
        }
    }

    public void onEditTapped() {
        new AddPlayerDialog(this, this.team, this.team.getPlayers().get(this.position), this, this.team.getPlayers()).show(getFragmentManager(), "add Player Dialog");
    }

    public void onDeleteTapped() {
        AlertDialogBuilder.showAlertDialog(new Dialog(this, "Delete Player", "Are you sure you want to delete this Player", "Yes", "No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case -2:
                        dialogInterface.dismiss();
                        return;
                    case -1:
                        OtherTeamPlayersActivity.this.dbhelper.deleteUserAddedPlayer(OtherTeamPlayersActivity.this.team.getPlayers().get(OtherTeamPlayersActivity.this.position));
                        OtherTeamPlayersActivity.this.team.getPlayers().remove(OtherTeamPlayersActivity.this.position);
                        OtherTeamPlayersActivity.this.adapter.deletePlayer(OtherTeamPlayersActivity.this.position);
                        if (OtherTeamPlayersActivity.this.team.getPlayers().isEmpty()) {
                            OtherTeamPlayersActivity.this.tvNoPlayersAdded.setVisibility(0);
                        }
                        dialogInterface.dismiss();
                        return;
                    default:
                        return;
                }
            }
        }));
    }

    public void onAddInPlayingXiTapped(boolean z) throws Exception {
        int noOfPlayersWithPlayingXI = UtilityFunctions.getNoOfPlayersWithPlayingXI(this.team);
        Player player = this.team.getPlayers().get(this.position);
        if (z) {
            SharedPrefsHelper.removePlayerFromPlayingXI(this, player);
            this.team.getPlayers().get(this.position).setInPlayingXI(false);
            if (this.adapter != null) {
                this.adapter.updatePlayer(this.position);
            }
        } else if (noOfPlayersWithPlayingXI == 11) {
            MyToast.showLongToast(this, "Error. Player could not be added because you have already selected all XI players in playing XI. Please remove one from playing XI and try again");
        } else {
            SharedPrefsHelper.insertPlayerAsPlayingXI(this, player);
            this.team.getPlayers().get(this.position).setInPlayingXI(true);
            if (this.adapter != null) {
                this.adapter.updatePlayer(this.position);
            }
        }
    }

    public void onChooseCaptainTapped() {
        final ArrayList arrayList = new ArrayList();
        arrayList.add("View Limited Overs Stats");
        arrayList.add("View Test Matches Stats");
        arrayList.add("View All Matches Stats");
        arrayList.add((Object) null);
        arrayList.add((Object) null);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add("View Batting Stats");
        arrayList2.add("View Bowling Stats");
        arrayList2.add((Object) null);
        arrayList2.add((Object) null);
        arrayList2.add((Object) null);
        AnonymousClass2 r1 = new DialogWithButtons.DialogWithButtonsInteractionListener() {
            public void onButton3Tapped() {
            }

            public void onButton4Tapped() {
            }

            public void onButton5Tapped() {
            }

            public void onButton1Tapped() {
                OtherTeamPlayersActivity.this.showBattingStats = true;
                OtherTeamPlayersActivity.this.showBowlingStats = false;
                DialogWithButtons.newInstance(arrayList).show(OtherTeamPlayersActivity.this.getFragmentManager(), DialogWithButtons.class.toString());
            }

            public void onButton2Tapped() {
                OtherTeamPlayersActivity.this.showBattingStats = false;
                OtherTeamPlayersActivity.this.showBowlingStats = true;
                DialogWithButtons.newInstance(arrayList).show(OtherTeamPlayersActivity.this.getFragmentManager(), DialogWithButtons.class.toString());
            }
        };
        DialogWithButtons newInstance = DialogWithButtons.newInstance(arrayList2);
        newInstance.setDialogWithButtonsInteractionListener(r1);
        newInstance.show(getFragmentManager(), DialogWithButtons.class.toString());
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_other_team_players);
        this.dbhelper = new DBHelper(this);
        this.edplayers = new EditText[15];
        this.team = (Team) getIntent().getSerializableExtra(DBHelper.TABLE_TEAM);
        this.code = getIntent().getIntExtra("code", 0);
        initializeViews();
        if (this.team != null) {
            this.currentTeamName = this.team.getName();
        }
        if (this.team.getTeamID() > 0) {
            this.bsubmit.setText("Update Team");
        }
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.setItemViewCacheSize(20);
        this.recyclerView.setDrawingCacheEnabled(true);
        this.recyclerView.setDrawingCacheQuality(1048576);
        showValues();
    }

    private void showValues() {
        setAPlayerAsCaptain();
        this.adapter = new PlayerRecyclerViewAdapter(this, this.team.getPlayers(), new MyOnClickListener());
        this.recyclerView.setAdapter(this.adapter);
    }

    private void setAPlayerAsCaptain() {
        Iterator<Player> it = this.team.getPlayers().iterator();
        while (it.hasNext()) {
            Player next = it.next();
            if (this.team.getCaptainId() == next.getPlayerId()) {
                next.setCaptain(true);
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void initializeViews() {
        this.recyclerView = (RecyclerView) findViewById(R.id.players_recycler_view);
        this.bsubmit = (Button) findViewById(R.id.bupdatedetails);
        this.bAddPlayer = (Button) findViewById(R.id.bAddNewPlayer);
        try {
            FontProvider.setEuroStileFont(this, this.bsubmit);
            FontProvider.setEuroStileFont(this, this.bAddPlayer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.tvTeamName = (TextView) findViewById(R.id.tv_team_name);
        this.tvNoPlayersAdded = (TextView) findViewById(R.id.tv_no_player_added);
        this.bsubmit.setOnClickListener(this);
        this.bAddPlayer.setOnClickListener(this);
        TextView textView = this.tvTeamName;
        textView.setText(this.team.getName() + " PLAYERS");
        this.tvTeamName.setVisibility(8);
        getSupportActionBar().setElevation(0.0f);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle((CharSequence) this.team.getName().toUpperCase() + " Players");
        if (this.team.getPlayers().isEmpty()) {
            this.tvNoPlayersAdded.setVisibility(0);
        }
    }

    /* access modifiers changed from: package-private */
    public void handleSubmit() {
        if (this.edyourteam.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Enter the name of team", 0).show();
        } else if (this.code != 99 && getTeamNames().contains(this.edyourteam.getText().toString())) {
            Toast.makeText(this, "This Team already exists. Please change the name", 0).show();
        } else if (this.code == 99) {
            this.dbhelper.UpdateTeamPlayers(this.teamname, this.edyourteam.getText().toString(), getYourplayers(), this.side);
            Toast.makeText(this, "Team Updated", 0).show();
            setResult(2, new Intent());
            finish();
        } else {
            this.dbhelper.insertTeamPlayers(this.edyourteam.getText().toString(), getYourplayers(), "opp");
            Toast.makeText(this, "Team Added", 0).show();
            setResult(2, new Intent());
            finish();
        }
    }

    /* access modifiers changed from: package-private */
    public ArrayList<String> getYourplayers() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            if (this.edplayers[i].getText().toString().isEmpty()) {
                arrayList.add(" ");
            } else {
                arrayList.add(this.edplayers[i].getText().toString());
            }
        }
        return arrayList;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bAddNewPlayer /*2131755366*/:
                new AddPlayerDialog(this, this.team, (Player) null, this, this.team.getPlayers()).show(getFragmentManager(), "add Player Dialog");
                return;
            case R.id.bupdatedetails /*2131755367*/:
                setResult(2, new Intent());
                finish();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: package-private */
    public ArrayList<Team> getAllTeamPlayers() {
        try {
            return this.dbhelper.getAddedTeamsAndPlayers();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /* access modifiers changed from: package-private */
    public ArrayList<String> getTeamNames() {
        ArrayList<Team> allTeamPlayers = getAllTeamPlayers();
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < allTeamPlayers.size(); i++) {
            arrayList.add(allTeamPlayers.get(i).getName());
        }
        return arrayList;
    }

    public void addPlayer(Player player) {
        if (this.team.getPlayers() != null) {
            this.team.getPlayers().add(player);
        } else {
            ArrayList arrayList = new ArrayList();
            arrayList.add(player);
            this.team.setPlayers(arrayList);
        }
        try {
            this.adapter.setPlayers(this.team.getPlayers());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onPlayerAdded(Player player) {
        this.currentTeamName = this.team.getName();
        addPlayer(player);
        this.tvNoPlayersAdded.setVisibility(8);
    }

    public void onPlayerUpdated() {
        this.adapter.setPlayers(this.team.getPlayers());
    }

    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(DBHelper.TABLE_TEAM, this.team);
        setResult(2, intent);
        finish();
    }
}
