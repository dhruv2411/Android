package apps.shehryar.com.cricketscroingappPro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman;
import apps.shehryar.com.cricketscroingappPro.Bowler.Bowler;
import com.itextpdf.text.pdf.PdfBoolean;
import java.util.ArrayList;

public class After_Match_Players_list_adapter extends ArrayAdapter {
    ArrayList<Batsman> batsmans;
    ArrayList<Bowler> bowlers;
    boolean check;
    Context context;
    LinearLayout layout;
    TextView playername;
    TextView playerscore;
    TextView tvoutstatus;

    public After_Match_Players_list_adapter(Context context2, int i, ArrayList arrayList, boolean z) {
        super(context2, i, arrayList);
        this.context = context2;
        this.check = z;
        if (z) {
            this.batsmans = arrayList;
        } else {
            this.bowlers = arrayList;
        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.custom_match_details_list, viewGroup, false);
        this.playername = (TextView) inflate.findViewById(R.id.tvplayername);
        this.playerscore = (TextView) inflate.findViewById(R.id.playerscore);
        this.tvoutstatus = (TextView) inflate.findViewById(R.id.tvoutstatus);
        this.layout = (LinearLayout) inflate.findViewById(R.id.main_layout);
        if (i % 2 == 0) {
            this.layout.setBackgroundColor(getContext().getResources().getColor(17170432));
        } else {
            this.layout.setBackgroundColor(getContext().getResources().getColor(17170443));
        }
        if (this.check) {
            this.playername.setText(this.batsmans.get(i).getName());
            TextView textView = this.playerscore;
            textView.setText(this.batsmans.get(i).getTotalScore() + "(" + this.batsmans.get(i).getBallsfaced() + ")");
            if (this.batsmans.get(i).isOut().equals(PdfBoolean.TRUE)) {
                this.tvoutstatus.setText("Out");
            } else {
                this.tvoutstatus.setText("Not Out");
            }
        } else {
            TextView textView2 = this.playerscore;
            textView2.setText(this.bowlers.get(i).getWickets() + "/" + this.bowlers.get(i).getScore());
            this.playername.setText(this.bowlers.get(i).getName());
            this.tvoutstatus.setVisibility(4);
        }
        return inflate;
    }
}
