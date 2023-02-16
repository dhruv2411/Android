package apps.shehryar.com.cricketscroingappPro.Batsman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import com.itextpdf.text.pdf.PdfBoolean;
import java.util.ArrayList;

public class BatsmanDetailHistoryActivity extends AppCompatActivity {
    Batsman batsman;
    ArrayList<Batsman> batsmen;
    int code;
    TextView tv0s;
    TextView tv1s;
    TextView tv2s;
    TextView tv3s;
    TextView tv4s;
    TextView tv6s;
    TextView tvbatballs;
    TextView tvbatoutstatus;
    TextView tvbatscore;
    TextView tvbatsmanname;
    TextView tvbatsr;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_batsman_detail_history);
        Intent intent = getIntent();
        Team team = (Team) intent.getSerializableExtra(DBHelper.TABLE_TEAM);
        int intExtra = intent.getIntExtra("index", 0);
        Match match = (Match) intent.getSerializableExtra(DBHelper.TABLE_MATCH);
        this.code = intent.getIntExtra("code", 0);
        DBHelper dBHelper = new DBHelper(getApplicationContext());
        if (this.code == 99) {
            this.batsmen = dBHelper.getBatsmans(match.getTeam_opp_id(), match.getMatchID());
        } else {
            this.batsmen = dBHelper.getBatsmans(match.getTeam_Yours_id(), match.getMatchID());
        }
        if (match == null) {
            this.batsman = team.getBatsmans_list().get(intExtra);
        }
        this.tvbatsmanname = (TextView) findViewById(R.id.tvbatsmanname);
        this.tvbatscore = (TextView) findViewById(R.id.tvbatscore);
        this.tvbatballs = (TextView) findViewById(R.id.tvbatballs);
        this.tvbatoutstatus = (TextView) findViewById(R.id.tvoutstatus);
        this.tvbatsr = (TextView) findViewById(R.id.tvbatsr);
        this.tv0s = (TextView) findViewById(R.id.tvbatdots);
        this.tv1s = (TextView) findViewById(R.id.tvbat1s);
        this.tv2s = (TextView) findViewById(R.id.tvbat2s);
        this.tv3s = (TextView) findViewById(R.id.tvbat3s);
        this.tv4s = (TextView) findViewById(R.id.tvbat4s);
        this.tv6s = (TextView) findViewById(R.id.tvbat6s);
        if (match == null) {
            this.tvbatsmanname.setText(this.batsman.getName());
            if (this.batsman.isOut().equals(PdfBoolean.TRUE)) {
                this.tvbatoutstatus.setText("Out");
            } else {
                this.tvbatoutstatus.setText("Not Out");
            }
            TextView textView = this.tvbatscore;
            textView.setText(this.batsman.getTotalScore() + "");
            TextView textView2 = this.tvbatballs;
            textView2.setText(this.batsman.getBallsfaced() + "");
            if (this.batsman.getBallsfaced() != 0) {
                TextView textView3 = this.tvbatsr;
                textView3.setText(" " + (((float) (this.batsman.getTotalScore() / this.batsman.getBallsfaced())) * 100.0f) + " ");
            }
            TextView textView4 = this.tv0s;
            textView4.setText(" " + this.batsman.getDots() + " ");
            TextView textView5 = this.tv1s;
            textView5.setText(" " + this.batsman.getSingles() + " ");
            TextView textView6 = this.tv2s;
            textView6.setText(" " + this.batsman.getDoubles() + " ");
            TextView textView7 = this.tv3s;
            textView7.setText(" " + this.batsman.getThrees() + " ");
            TextView textView8 = this.tv4s;
            textView8.setText(" " + this.batsman.getFours() + " ");
            TextView textView9 = this.tv6s;
            textView9.setText(" " + this.batsman.getSixs() + " ");
            return;
        }
        this.tvbatsmanname.setText(this.batsmen.get(intExtra).getName());
        if (this.batsmen.get(intExtra).isOut().equals(PdfBoolean.TRUE)) {
            this.tvbatoutstatus.setText("Out");
        } else {
            this.tvbatoutstatus.setText("Not Out");
        }
        TextView textView10 = this.tvbatscore;
        textView10.setText(this.batsmen.get(intExtra).getTotalScore() + "");
        TextView textView11 = this.tvbatballs;
        textView11.setText(this.batsmen.get(intExtra).getBallsfaced() + "");
        if (this.batsmen.get(intExtra).getBallsfaced() != 0) {
            TextView textView12 = this.tvbatsr;
            textView12.setText(" " + (((float) (this.batsmen.get(intExtra).getTotalScore() / this.batsmen.get(intExtra).getBallsfaced())) * 100.0f) + " ");
        }
        TextView textView13 = this.tv0s;
        textView13.setText(" " + this.batsmen.get(intExtra).getDots() + " ");
        TextView textView14 = this.tv1s;
        textView14.setText(" " + this.batsmen.get(intExtra).getSingles() + " ");
        TextView textView15 = this.tv2s;
        textView15.setText(" " + this.batsmen.get(intExtra).getDoubles() + " ");
        TextView textView16 = this.tv3s;
        textView16.setText(" " + this.batsmen.get(intExtra).getThrees() + " ");
        TextView textView17 = this.tv4s;
        textView17.setText(" " + this.batsmen.get(intExtra).getFours() + " ");
        TextView textView18 = this.tv6s;
        textView18.setText(" " + this.batsmen.get(intExtra).getSixs() + " ");
    }
}
