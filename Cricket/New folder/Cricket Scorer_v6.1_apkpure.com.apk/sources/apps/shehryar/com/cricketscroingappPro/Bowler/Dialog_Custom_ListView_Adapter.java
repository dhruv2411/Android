package apps.shehryar.com.cricketscroingappPro.Bowler;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Overs.Over;
import java.util.ArrayList;

public class Dialog_Custom_ListView_Adapter extends RecyclerView.Adapter<BowlerViewHolder> {
    ArrayList<Bowler> bowlerArrayList;
    LinearLayoutClickLister clickListener;
    Context context;
    Match match;
    ArrayList<Over> overs;

    public interface LinearLayoutClickLister {
        void onBowlerNameTapped(int i);
    }

    public Dialog_Custom_ListView_Adapter(Context context2, ArrayList<Bowler> arrayList, LinearLayoutClickLister linearLayoutClickLister, ArrayList<Over> arrayList2, Match match2) {
        this.bowlerArrayList = arrayList;
        this.overs = arrayList2;
        this.context = context2;
        this.clickListener = linearLayoutClickLister;
        this.match = match2;
    }

    public BowlerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new BowlerViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_dialog_listview_layout, viewGroup, false));
    }

    public void onBindViewHolder(BowlerViewHolder bowlerViewHolder, final int i) {
        Typeface createFromAsset = Typeface.createFromAsset(this.context.getAssets(), "fonts/DroidSans.ttf");
        Typeface createFromAsset2 = Typeface.createFromAsset(this.context.getAssets(), "fonts/DroidSans_Bold.ttf");
        Typeface.createFromAsset(this.context.getAssets(), "fonts/RobotoCondensed_Bold.ttf");
        bowlerViewHolder.tvbowlername.setClickable(false);
        bowlerViewHolder.tvbowlerovers.setTypeface(createFromAsset2);
        bowlerViewHolder.tvbowlerscore.setTypeface(createFromAsset);
        bowlerViewHolder.tvbowlerwickets.setTypeface(createFromAsset);
        bowlerViewHolder.tvbowlermaidens.setTypeface(createFromAsset);
        bowlerViewHolder.tvbowlereco.setTypeface(createFromAsset);
        bowlerViewHolder.tvbowlername.setText(this.bowlerArrayList.get(i).getName());
        TextView textView = bowlerViewHolder.tvbowlerovers;
        textView.setText(this.bowlerArrayList.get(i).getBowlerovers() + "." + this.bowlerArrayList.get(i).getBalls());
        TextView textView2 = bowlerViewHolder.tvbowlerscore;
        textView2.setText(this.bowlerArrayList.get(i).getTotalscore() + "");
        TextView textView3 = bowlerViewHolder.tvbowlerwickets;
        textView3.setText(this.bowlerArrayList.get(i).getWickets() + "");
        String format = String.format("%.1f", new Object[]{Float.valueOf(((float) this.bowlerArrayList.get(i).getTotalscore()) / (((((float) this.bowlerArrayList.get(i).getBowlerovers()) * 6.0f) + ((float) this.bowlerArrayList.get(i).getBalls())) / 6.0f))});
        TextView textView4 = bowlerViewHolder.tvbowlereco;
        textView4.setText(format + "");
        bowlerViewHolder.tvbowlereco.setSingleLine(true);
        TextView textView5 = bowlerViewHolder.tvbowlermaidens;
        textView5.setText(this.bowlerArrayList.get(i).getMaidens() + "");
        if (i % 2 != 0) {
            bowlerViewHolder.linearLayout.setBackgroundColor(ContextCompat.getColor(this.context, R.color.background_dark));
        }
        bowlerViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Dialog_Custom_ListView_Adapter.this.clickListener != null) {
                    Dialog_Custom_ListView_Adapter.this.clickListener.onBowlerNameTapped(i);
                    return;
                }
                try {
                    BowlerHelper.showBowlerDetailsDialog((Activity) Dialog_Custom_ListView_Adapter.this.context, Dialog_Custom_ListView_Adapter.this.bowlerArrayList.get(i), Dialog_Custom_ListView_Adapter.this.overs, Dialog_Custom_ListView_Adapter.this.match);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public int getItemCount() {
        return this.bowlerArrayList.size();
    }

    public class BowlerViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        TextView tvbowlereco;
        TextView tvbowlermaidens;
        TextView tvbowlername;
        TextView tvbowlerovers;
        TextView tvbowlerscore;
        TextView tvbowlerwickets;

        public BowlerViewHolder(View view) {
            super(view);
            this.tvbowlername = (TextView) view.findViewById(R.id.tvbowlername);
            this.tvbowlerovers = (TextView) view.findViewById(R.id.tvbowlerovers);
            this.tvbowlerscore = (TextView) view.findViewById(R.id.tvbowlerruns);
            this.tvbowlerwickets = (TextView) view.findViewById(R.id.tvbowlerwickets);
            this.tvbowlermaidens = (TextView) view.findViewById(R.id.tvbowlermaidens);
            this.tvbowlereco = (TextView) view.findViewById(R.id.tvbowlereco);
            this.linearLayout = (LinearLayout) view.findViewById(R.id.after_over_linearlayout);
        }
    }
}
