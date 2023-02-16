package apps.shehryar.com.cricketscroingappPro.Batsman;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import com.itextpdf.text.pdf.PdfBoolean;
import java.util.ArrayList;

public class BatsmanDetailsListAdapter extends RecyclerView.Adapter<MyViewHolder> {
    ArrayList<Batsman_Details> batsmen;
    Context context;
    Typeface typeface;
    Typeface typefacebold;

    BatsmanDetailsListAdapter(Context context2, ArrayList<Batsman_Details> arrayList) {
        this.context = context2;
        this.batsmen = arrayList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView batballs;
        TextView batscore;
        TextView dots;
        TextView doubles;
        TextView fours;
        TextView singles;
        TextView sixes;
        TextView teamname;
        TextView triples;
        TextView venuedate;

        public MyViewHolder(View view) {
            super(view);
            this.teamname = (TextView) view.findViewById(R.id.tvteamname);
            this.venuedate = (TextView) view.findViewById(R.id.tvdatevenue);
            this.dots = (TextView) view.findViewById(R.id.tvbatdots);
            this.singles = (TextView) view.findViewById(R.id.tvbat1s);
            this.doubles = (TextView) view.findViewById(R.id.tvbat2s);
            this.triples = (TextView) view.findViewById(R.id.tvbat3s);
            this.fours = (TextView) view.findViewById(R.id.tvbat4s);
            this.sixes = (TextView) view.findViewById(R.id.tvbat6s);
            this.batscore = (TextView) view.findViewById(R.id.tvbatscore);
            this.batballs = (TextView) view.findViewById(R.id.tvbatballs);
        }
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_batsman_detail_layout, viewGroup, false);
        this.typeface = Typeface.createFromAsset(this.context.getAssets(), "fonts/DroidSans.ttf");
        this.typefacebold = Typeface.createFromAsset(this.context.getAssets(), "fonts/RobotoCondensed_Bold.ttf");
        return new MyViewHolder(inflate);
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.teamname.setTypeface(this.typefacebold);
        myViewHolder.teamname.setText(this.batsmen.get(i).getTeamoppname());
        String valueOf = String.valueOf(Html.fromHtml("<sup>*</sup>"));
        if (this.batsmen.get(i).getIsout() != null) {
            if (this.batsmen.get(i).getIsout().equals(PdfBoolean.FALSE)) {
                TextView textView = myViewHolder.batscore;
                StringBuilder sb = new StringBuilder();
                sb.append(this.batsmen.get(i).getScore());
                sb.append("");
                sb.append(Html.fromHtml("<small>" + valueOf + "</small>"));
                textView.setText(sb.toString());
            } else {
                TextView textView2 = myViewHolder.batscore;
                textView2.setText(this.batsmen.get(i).getScore() + "");
            }
        }
        TextView textView3 = myViewHolder.batballs;
        textView3.setText(this.batsmen.get(i).getBalls() + "");
        TextView textView4 = myViewHolder.venuedate;
        textView4.setText(this.batsmen.get(i).getVenue() + " " + this.batsmen.get(i).getDate());
        TextView textView5 = myViewHolder.dots;
        textView5.setText(this.batsmen.get(i).getDots() + "  ");
        TextView textView6 = myViewHolder.singles;
        textView6.setText(this.batsmen.get(i).getSingles() + "  ");
        TextView textView7 = myViewHolder.doubles;
        textView7.setText(this.batsmen.get(i).getDoubles() + "  ");
        TextView textView8 = myViewHolder.triples;
        textView8.setText(this.batsmen.get(i).getTriples() + "  ");
        TextView textView9 = myViewHolder.fours;
        textView9.setText(this.batsmen.get(i).getFours() + "  ");
        TextView textView10 = myViewHolder.sixes;
        textView10.setText(this.batsmen.get(i).getSixes() + "  ");
    }

    public int getItemCount() {
        try {
            return this.batsmen.size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
