package apps.shehryar.com.cricketscroingappPro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Overs.OverFragementLoader;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.FontProvider;

public class OverHistoryActivity extends AppCompatActivity {
    Team team;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_over_history);
        try {
            FontProvider.setEuroStileFont(this, (TextView) findViewById(R.id.title));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            getSupportActionBar().hide();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.team = (Team) getIntent().getExtras().getSerializable(DBHelper.TABLE_TEAM);
        OverFragementLoader.showOverRecyclerViewFragment(getSupportFragmentManager(), this.team.overs_list);
    }

    public void onBackPressed() {
        setResult(-1);
        finish();
    }
}
