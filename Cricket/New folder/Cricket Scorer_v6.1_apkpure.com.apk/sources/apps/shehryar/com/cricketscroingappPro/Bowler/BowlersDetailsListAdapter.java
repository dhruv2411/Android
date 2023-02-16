package apps.shehryar.com.cricketscroingappPro.Bowler;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import java.util.ArrayList;

public class BowlersDetailsListAdapter extends RecyclerView.Adapter<MyViewHolder> {
    ArrayList<Bowler_Details> bowlers;
    Context context;
    Typeface typeface;
    Typeface typefacebold;

    public BowlersDetailsListAdapter(Context context2, ArrayList<Bowler_Details> arrayList) {
        this.context = context2;
        this.bowlers = arrayList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView teamname;
        TextView tvovers;
        TextView tvscore;
        TextView venuedate;

        public MyViewHolder(View view) {
            super(view);
            this.teamname = (TextView) view.findViewById(R.id.tvteamname);
            this.venuedate = (TextView) view.findViewById(R.id.tvdatevenue);
            this.tvovers = (TextView) view.findViewById(R.id.tvovers);
            this.tvscore = (TextView) view.findViewById(R.id.tvbowlerscorewickets);
        }
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_bowler_detail_layout, viewGroup, false);
        this.typeface = Typeface.createFromAsset(this.context.getAssets(), "fonts/DroidSans.ttf");
        this.typefacebold = Typeface.createFromAsset(this.context.getAssets(), "fonts/RobotoCondensed_Bold.ttf");
        return new MyViewHolder(inflate);
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.tvscore.setTypeface(this.typeface);
        myViewHolder.teamname.setTypeface(this.typefacebold);
        myViewHolder.teamname.setText(this.bowlers.get(i).getOppname());
        TextView textView = myViewHolder.venuedate;
        textView.setText(this.bowlers.get(i).getVenue() + " " + this.bowlers.get(i).getDate());
        TextView textView2 = myViewHolder.tvscore;
        textView2.setText(this.bowlers.get(i).getScore() + "/" + this.bowlers.get(i).getWickets());
        TextView textView3 = myViewHolder.tvovers;
        textView3.setText("Overs: " + this.bowlers.get(i).getOver() + "." + this.bowlers.get(i).getBall());
    }

    public int getItemCount() {
        try {
            return this.bowlers.size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
