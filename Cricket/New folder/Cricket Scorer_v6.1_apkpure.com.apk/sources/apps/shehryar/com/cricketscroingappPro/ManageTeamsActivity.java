package apps.shehryar.com.cricketscroingappPro;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Player.Player;
import apps.shehryar.com.cricketscroingappPro.Team.AddTeamDialog;
import apps.shehryar.com.cricketscroingappPro.Team.ChooseCaptainWkDialog;
import apps.shehryar.com.cricketscroingappPro.Team.OtherTeamPlayersActivity;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import apps.shehryar.com.cricketscroingappPro.Team.TeamEdiDeleteAndViewPlayersDialog;
import apps.shehryar.com.cricketscroingappPro.Team.Team_players;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.AlertDialogBuilder;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.Dialog;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.FontProvider;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.FragmentLoader;
import java.util.ArrayList;
import java.util.Iterator;

public class ManageTeamsActivity extends AppCompatActivity implements View.OnClickListener, TeamEdiDeleteAndViewPlayersDialog.TeamEditDeleteCallBack {
    Custom_Teams_Suggestions_Adapter adapter;
    Button button;
    Button buttonaddyourteam;
    DBHelper dbHelper;
    /* access modifiers changed from: private */
    public int index;
    View.OnClickListener onClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            TeamEdiDeleteAndViewPlayersDialog teamEdiDeleteAndViewPlayersDialog = new TeamEdiDeleteAndViewPlayersDialog();
            Bundle bundle = new Bundle();
            bundle.putInt("index", ManageTeamsActivity.this.recyclerView.getChildLayoutPosition(view));
            bundle.putBoolean("showChooseOptions", true);
            teamEdiDeleteAndViewPlayersDialog.setArguments(bundle);
            try {
                teamEdiDeleteAndViewPlayersDialog.show(ManageTeamsActivity.this.getFragmentManager(), "editTeamDialog");
                int unused = ManageTeamsActivity.this.index = ManageTeamsActivity.this.recyclerView.getChildLayoutPosition(view);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    RecyclerView recyclerView;
    AddTeamDialog.teamAddedCallBackInterface teamAddedCallBackInterface = new AddTeamDialog.teamAddedCallBackInterface() {
        public void onTeamAdded(Team team) {
            ManageTeamsActivity.this.teams.add(team);
            try {
                Log.i("team image", team.getImage());
            } catch (Exception e) {
                e.printStackTrace();
            }
            ManageTeamsActivity.this.teamnames.add(team.getName());
            ManageTeamsActivity.this.adapter.setTeams(ManageTeamsActivity.this.teams);
            ManageTeamsActivity.this.tvNoTeamsAdded.setVisibility(8);
        }

        public void onTeamUpdated(int i, String str) {
            ManageTeamsActivity.this.teams.get(i).setName(str);
            ManageTeamsActivity.this.teamnames.set(i, str);
            ManageTeamsActivity.this.adapter.setTeams(ManageTeamsActivity.this.teams);
        }
    };
    ArrayList<Team> team_players;
    ArrayList<String> teamnames;
    ArrayList<Team> teams;
    TextView tvNoTeamsAdded;

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResumeFragments() {
        super.onResumeFragments();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_manage_teams);
        this.button = (Button) findViewById(R.id.bsetupyourteam);
        this.buttonaddyourteam = (Button) findViewById(R.id.bsetupyourteam1);
        this.recyclerView = (RecyclerView) findViewById(R.id.teams_recycler_view);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.setItemViewCacheSize(20);
        this.recyclerView.setDrawingCacheEnabled(true);
        this.recyclerView.setDrawingCacheQuality(1048576);
        try {
            FontProvider.setEuroStileFont(this, this.button);
            FontProvider.setEuroStileFont(this, this.buttonaddyourteam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.tvNoTeamsAdded = (TextView) findViewById(R.id.tv_no_teams_added);
        this.dbHelper = new DBHelper(this);
        this.teams = (ArrayList) getIntent().getSerializableExtra("team_players");
        this.teamnames = getTeamNames();
        this.adapter = new Custom_Teams_Suggestions_Adapter(this, this.teams, (ArrayList<Player>) null, this, this.onClickListener);
        this.recyclerView.setAdapter(this.adapter);
        if (this.teams != null && this.teams.isEmpty()) {
            this.tvNoTeamsAdded.setVisibility(0);
        }
        this.button.setOnClickListener(this);
        this.buttonaddyourteam.setOnClickListener(this);
    }

    public void onBackPressed() {
        setResult(2);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1 && i2 == 2) {
            try {
                this.teams = this.dbHelper.getAddedTeamsAndPlayers();
            } catch (Exception e) {
                e.printStackTrace();
                this.teams = new ArrayList<>();
            }
            this.teamnames = getTeamNames();
            this.adapter.setTeams(this.teams);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.bsetupyourteam) {
            Team team = new Team();
            team.setTeamSide(1);
            new AddTeamDialog(this, team, this.teamAddedCallBackInterface, this.teams, this.index).show(getFragmentManager(), "add team dialog");
        } else if (id == R.id.bsetupyourteam1) {
            Team yourTeam = getYourTeam(this.teams);
            yourTeam.setTeamSide(0);
            this.index = getYourTeamIndexInArray();
            new AddTeamDialog(this, yourTeam, this.teamAddedCallBackInterface, this.teams, this.index).show(getFragmentManager(), "add Team Dialog");
        }
    }

    private int getYourTeamIndexInArray() {
        if (this.teams != null) {
            for (int i = 0; i < this.teams.size(); i++) {
                if (this.teams.get(i).getTeamSide() == 0) {
                    return i;
                }
            }
        }
        return 0;
    }

    private Team getYourTeam(ArrayList<Team> arrayList) {
        try {
            Iterator<Team> it = arrayList.iterator();
            while (it.hasNext()) {
                Team next = it.next();
                if (next.getTeamSide() == 0) {
                    return next;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Team();
    }

    /* access modifiers changed from: package-private */
    public ArrayList<Team_players> getAllTeamPlayers() {
        return this.dbHelper.getAllTeamPlayers();
    }

    /* access modifiers changed from: package-private */
    public ArrayList<String> getTeamNames() {
        ArrayList<String> arrayList = new ArrayList<>();
        int i = 0;
        while (i < this.teams.size()) {
            try {
                arrayList.add(this.teams.get(i).getName());
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public void onTeamEditTapped(int i) {
        new AddTeamDialog(this, this.teams.get(i), this.teamAddedCallBackInterface, this.teams, i).show(getFragmentManager(), "add Team Dialog");
    }

    public void onTeamDeleteTapped(final int i) {
        AlertDialogBuilder.showAlertDialog(new Dialog(this, "Delete Team", "Are you sure you want to delete this team", "Yes", "No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == -1) {
                    try {
                        ManageTeamsActivity.this.dbHelper.deleteUserAddedTeam(ManageTeamsActivity.this.teams.get(i));
                        for (int i2 = 0; i2 < ManageTeamsActivity.this.teams.get(i).getPlayers().size(); i2++) {
                            ManageTeamsActivity.this.dbHelper.deleteUserAddedPlayer(ManageTeamsActivity.this.teams.get(i).getPlayers().get(i2));
                        }
                        if (ManageTeamsActivity.this.teams.isEmpty()) {
                            ManageTeamsActivity.this.tvNoTeamsAdded.setVisibility(0);
                        }
                        ManageTeamsActivity.this.adapter.removeTeam(i);
                        MyToast.showToast(ManageTeamsActivity.this, "Team deleted successfully");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }));
    }

    public void onTeamViewPlayersTapped(int i) {
        Intent intent = new Intent(this, OtherTeamPlayersActivity.class);
        intent.putExtra("code", 99);
        intent.putExtra(DBHelper.TABLE_TEAM, this.teams.get(i));
        startActivityForResult(intent, 1);
    }

    public void onChooseCaptainTapped(int i) {
        if (this.teams.get(i).getPlayers().size() > 0) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("chooseCaptain", true);
            bundle.putSerializable(DBHelper.TABLE_TEAM, this.teams.get(i));
            FragmentLoader.showDialogFragmet(this, new ChooseCaptainWkDialog(), bundle, "Choose Captain Dialog");
            return;
        }
        MyToast.showLongToast(this, "No player found in the team. Please add players and then choose Captain.");
    }

    public void onChooseWicketKeeperTapped(int i) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("chooseCaptain", false);
        bundle.putSerializable(DBHelper.TABLE_TEAM, this.teams.get(i));
        FragmentLoader.showDialogFragmet(this, new ChooseCaptainWkDialog(), bundle, "Choose Captain Dialog");
    }
}
