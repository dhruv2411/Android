package apps.shehryar.com.cricketscroingappPro.Batsman;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import com.itextpdf.text.pdf.PdfBoolean;
import java.util.ArrayList;

public class CustomBatHistoryAdapter extends RecyclerView.Adapter<BatsmanViewHolder> {
    Activity activity;
    Context context;
    ArrayList<Batsman> list = new ArrayList<>();
    Match match;

    public CustomBatHistoryAdapter(Context context2, ArrayList<Batsman> arrayList, Match match2) {
        this.list = arrayList;
        this.activity = (Activity) context2;
        this.context = context2;
        this.match = match2;
    }

    /* access modifiers changed from: private */
    public void showBatmsanDetailDialog(Batsman batsman) {
        BatsmanDetailsDialog batsmanDetailsDialog = new BatsmanDetailsDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("batsman", batsman);
        bundle.putSerializable(DBHelper.TABLE_MATCH, this.match);
        batsmanDetailsDialog.setArguments(bundle);
        try {
            batsmanDetailsDialog.show(this.activity.getFragmentManager(), "batsman");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BatsmanViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new BatsmanViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.custom_batsmanhistory_listview, viewGroup, false));
    }

    public void onBindViewHolder(BatsmanViewHolder batsmanViewHolder, final int i) {
        batsmanViewHolder.batsman.setText(this.list.get(i).getName());
        if (!this.list.get(i).isOut().equals("Did not Bat")) {
            TextView textView = batsmanViewHolder.score;
            textView.setText(this.list.get(i).getTotalScore() + "");
            TextView textView2 = batsmanViewHolder.balls;
            textView2.setText(this.list.get(i).getBallsfaced() + "");
            TextView textView3 = batsmanViewHolder.fours;
            textView3.setText(this.list.get(i).getFours() + "");
            TextView textView4 = batsmanViewHolder.sixes;
            textView4.setText(this.list.get(i).getSixs() + "");
            TextView textView5 = batsmanViewHolder.strikerate;
            textView5.setText(this.list.get(i).getStrikerate() + "");
        }
        if (this.list.get(i).isOut().equals(PdfBoolean.FALSE)) {
            batsmanViewHolder.outstatus.setText("not out");
        } else if (this.list.get(i).isOut().equals(PdfBoolean.TRUE)) {
            batsmanViewHolder.outstatus.setText("out");
        } else if (this.list.get(i).isOut().equals("Ret")) {
            batsmanViewHolder.outstatus.setText("Retired");
        } else {
            batsmanViewHolder.outstatus.setText(this.list.get(i).isOut());
        }
        if (i % 2 != 0) {
            batsmanViewHolder.mainlayout.setBackgroundColor(this.context.getResources().getColor(R.color.background_dark));
        }
        batsmanViewHolder.mainlayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!CustomBatHistoryAdapter.this.list.get(i).isOut().equals("Did not Bat")) {
                    CustomBatHistoryAdapter.this.showBatmsanDetailDialog(CustomBatHistoryAdapter.this.list.get(i));
                }
            }
        });
    }

    public int getItemCount() {
        try {
            return this.list.size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public class BatsmanViewHolder extends RecyclerView.ViewHolder {
        TextView balls;
        TextView batsman;
        TextView fours;
        LinearLayout mainlayout;
        TextView outstatus;
        TextView score;
        TextView sixes;
        TextView strikerate;

        public BatsmanViewHolder(View view) {
            super(view);
            this.batsman = (TextView) view.findViewById(R.id.tvbatname);
            this.score = (TextView) view.findViewById(R.id.tvbatscore);
            this.balls = (TextView) view.findViewById(R.id.tvbatballs);
            this.fours = (TextView) view.findViewById(R.id.tvbatfours);
            this.sixes = (TextView) view.findViewById(R.id.tvbatsixes);
            this.strikerate = (TextView) view.findViewById(R.id.tvstrikerate);
            this.mainlayout = (LinearLayout) view.findViewById(R.id.after_wicket_mainbatlayout);
            this.outstatus = (TextView) view.findViewById(R.id.tvoutstatus);
        }
    }
}
