package apps.shehryar.com.cricketscroingappPro.Bowler;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Bowler.Dialog_Custom_ListView_Adapter;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Overs.Over;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.Formatter;
import java.util.ArrayList;

public class BowlerViewsUpdater {
    Activity activity;
    Context context;
    private TextView tvBowlerEco;
    private TextView tvBowlerMaidens;
    private TextView tvBowlerName;
    private TextView tvBowlerOvers;
    private TextView tvBowlerScore;
    private TextView tvBowlerWickets;

    public BowlerViewsUpdater(Context context2, Activity activity2) {
        this.context = context2;
        this.activity = activity2;
        initializeViews(context2, activity2);
    }

    private void initializeViews(Context context2, Activity activity2) {
        Typeface.createFromAsset(context2.getAssets(), "fonts/RobotoCondensed_Bold.ttf");
        this.tvBowlerScore = (TextView) activity2.findViewById(R.id.tv_bowler_score);
        this.tvBowlerOvers = (TextView) activity2.findViewById(R.id.tvbowlerovers);
        this.tvBowlerName = (TextView) activity2.findViewById(R.id.tvbowlername);
        this.tvBowlerWickets = (TextView) activity2.findViewById(R.id.tv_bowler_wickets);
        this.tvBowlerMaidens = (TextView) activity2.findViewById(R.id.tv_bowler_maidens);
        this.tvBowlerEco = (TextView) activity2.findViewById(R.id.tv_bowler_eco);
    }

    public void changeBowlerNameText(Bowler bowler) {
        this.tvBowlerName.setText(Formatter.cutNameHalf(bowler.getName()));
    }

    public void changeBowlerScoreText(Bowler bowler) {
        TextView textView = this.tvBowlerScore;
        textView.setText(bowler.getTotalscore() + "/" + bowler.getWickets());
    }

    public void changeBowlerOverText(Bowler bowler) {
        TextView textView = this.tvBowlerOvers;
        textView.setText("(" + bowler.getBowlerovers() + "." + bowler.getBalls() + ")");
    }

    public void changeBowlerNameScoreAndOversText(Bowler bowler) {
        changeBowlerNameText(bowler);
        changeBowlerScoreText(bowler);
        changeBowlerOverText(bowler);
    }

    public static void showBowlerRecyclerViewFragment(FragmentManager fragmentManager, ArrayList<Bowler> arrayList, Dialog_Custom_ListView_Adapter.LinearLayoutClickLister linearLayoutClickLister, ArrayList<Over> arrayList2, Match match) {
        FragmentBowlerRecyclerView fragmentBowlerRecyclerView = new FragmentBowlerRecyclerView(linearLayoutClickLister);
        Bundle bundle = new Bundle();
        bundle.putSerializable("bowlers", arrayList);
        bundle.putSerializable(DBHelper.TABLE_OVERS, arrayList2);
        bundle.putSerializable(DBHelper.TABLE_MATCH, match);
        fragmentBowlerRecyclerView.setArguments(bundle);
        fragmentManager.beginTransaction().add((int) R.id.bowler_recycler_view_fragment, (Fragment) fragmentBowlerRecyclerView).commit();
    }
}
