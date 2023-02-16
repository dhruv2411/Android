package apps.shehryar.com.cricketscroingappPro;

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
import apps.shehryar.com.cricketscroingappPro.Overs.Over;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.Formatter;
import java.util.ArrayList;

public class CustomOverHistoryListViewAdapter extends RecyclerView.Adapter<OverViewHolder> {
    Context context;
    ArrayList<Over> objects = new ArrayList<>();

    public CustomOverHistoryListViewAdapter(Context context2, ArrayList arrayList) {
        this.objects = arrayList;
        this.context = context2;
    }

    public class OverViewHolder extends RecyclerView.ViewHolder {
        LinearLayout mainLayout;
        TextView outstatus;
        TextView tvbowlername;
        TextView tvoverdetails;
        TextView tvoverno;
        TextView tvscore;
        TextView tvwickets;

        public OverViewHolder(View view) {
            super(view);
            this.tvscore = (TextView) view.findViewById(R.id.cvtvscores);
            this.tvwickets = (TextView) view.findViewById(R.id.cvtvwickets);
            this.tvbowlername = (TextView) view.findViewById(R.id.cvtvbowlername);
            this.tvoverno = (TextView) view.findViewById(R.id.cvtvoverno);
            this.tvoverdetails = (TextView) view.findViewById(R.id.cvtvoverdetails);
            this.outstatus = (TextView) view.findViewById(R.id.tvoutstatus);
            this.mainLayout = (LinearLayout) view.findViewById(R.id.linearlayoutover);
        }
    }

    public OverViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new OverViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_overshistory_listview, viewGroup, false));
    }

    public void onBindViewHolder(OverViewHolder overViewHolder, int i) {
        try {
            Typeface createFromAsset = Typeface.createFromAsset(this.context.getAssets(), "fonts/DroidSans.ttf");
            Typeface createFromAsset2 = Typeface.createFromAsset(this.context.getAssets(), "fonts/RobotoCondensed_Bold.ttf");
            overViewHolder.tvscore.setTypeface(createFromAsset);
            overViewHolder.tvwickets.setTypeface(createFromAsset);
            overViewHolder.tvbowlername.setTypeface(createFromAsset2);
            overViewHolder.tvoverno.setTypeface(createFromAsset);
            overViewHolder.tvoverdetails.setTypeface(createFromAsset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        TextView textView = overViewHolder.tvscore;
        textView.setText(this.objects.get(i).getOverscore() + "");
        TextView textView2 = overViewHolder.tvwickets;
        textView2.setText(this.objects.get(i).getWickets() + "");
        overViewHolder.tvbowlername.setText(Formatter.cutNameHalf(this.objects.get(i).getBowler()));
        if (i % 2 != 0) {
            overViewHolder.mainLayout.setBackgroundColor(ContextCompat.getColor(this.context, R.color.background_dark));
        }
        TextView textView3 = overViewHolder.tvoverno;
        textView3.setText((i + 1) + "");
        ViewsUpdater.changeOverPerBallScore(this.objects.get(i).getPerballScore(), overViewHolder.tvoverdetails);
    }

    public int getItemCount() {
        try {
            return this.objects.size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
