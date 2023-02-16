package apps.shehryar.com.cricketscroingappPro;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import io.fabric.sdk.android.services.common.IdManager;
import java.util.ArrayList;

public class Custom_all_teams_stats_adapter extends ArrayAdapter {
    ArrayList<Team> list = new ArrayList<>();
    LinearLayout mainlayout;
    TextView tvmatcheslost;
    TextView tvmatchesplayed;
    TextView tvmatchestied;
    TextView tvmatcheswon;
    TextView tvnetrunrate;
    TextView tvteamPoints;
    TextView tvteamname;

    public Custom_all_teams_stats_adapter(Context context, int i, ArrayList arrayList) {
        super(context, i, arrayList);
        this.list = arrayList;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.all_team_stats_adapter_layout, viewGroup, false);
        this.tvteamname = (TextView) inflate.findViewById(R.id.tvteamname);
        this.tvmatcheslost = (TextView) inflate.findViewById(R.id.tvteamLost);
        this.tvmatcheswon = (TextView) inflate.findViewById(R.id.tvteamwon);
        this.tvmatchestied = (TextView) inflate.findViewById(R.id.tvteamDraw);
        this.tvmatchesplayed = (TextView) inflate.findViewById(R.id.tvteamMatches);
        this.tvnetrunrate = (TextView) inflate.findViewById(R.id.tvteamNRR);
        this.mainlayout = (LinearLayout) inflate.findViewById(R.id.main_layout);
        this.tvteamPoints = (TextView) inflate.findViewById(R.id.tvteamPoints);
        Typeface createFromAsset = Typeface.createFromAsset(getContext().getAssets(), "fonts/DroidSans.ttf");
        Typeface createFromAsset2 = Typeface.createFromAsset(getContext().getAssets(), "fonts/DroidSans_Bold.ttf");
        this.tvteamname.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/RobotoCondensed_Bold.ttf"));
        this.tvteamPoints.setTypeface(createFromAsset2);
        this.tvmatcheslost.setTypeface(createFromAsset);
        this.tvmatchesplayed.setTypeface(createFromAsset);
        this.tvmatcheswon.setTypeface(createFromAsset);
        this.tvmatchestied.setTypeface(createFromAsset);
        this.tvnetrunrate.setTypeface(createFromAsset);
        this.tvteamname.setSingleLine(true);
        this.tvteamname.setText(this.list.get(i).getName());
        TextView textView = this.tvmatcheslost;
        textView.setText(this.list.get(i).getMatches_lost() + "");
        TextView textView2 = this.tvmatcheswon;
        textView2.setText(this.list.get(i).getMatches_won() + "");
        TextView textView3 = this.tvmatchestied;
        textView3.setText(this.list.get(i).getMatches_tied() + "");
        TextView textView4 = this.tvmatchesplayed;
        textView4.setText(this.list.get(i).getMatches_Played() + "");
        TextView textView5 = this.tvteamPoints;
        textView5.setText(this.list.get(i).getPoints() + "");
        float net_Run_Rate = (float) this.list.get(i).getNet_Run_Rate();
        String format = String.format("%.2f", new Object[]{Float.valueOf(net_Run_Rate)});
        if (Float.isNaN(net_Run_Rate)) {
            format = IdManager.DEFAULT_VERSION_NAME;
        }
        TextView textView6 = this.tvnetrunrate;
        textView6.setText(format + "");
        if (i % 2 != 0) {
            this.mainlayout.setBackgroundColor(getContext().getResources().getColor(R.color.background_dark));
        }
        return inflate;
    }

    static String cutNameHalf(String str) {
        int lastIndexOf = str.lastIndexOf(32);
        if (!str.equals("run out") && lastIndexOf > 0) {
            return str.substring(0, lastIndexOf);
        }
        return str;
    }

    public int getCount() {
        try {
            return this.list.size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
