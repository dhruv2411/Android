package apps.shehryar.com.cricketscroingappPro.Partenership;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.FontProvider;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.Formatter;
import java.util.ArrayList;

public class PartenershipsListAdapter extends RecyclerView.Adapter<MyViewHolder> {
    ArrayList<Partenership> allPartenerships;
    Context context;
    Typeface typeface;
    Typeface typefacebold;

    public PartenershipsListAdapter(Context context2, ArrayList<Partenership> arrayList) {
        this.context = context2;
        this.allPartenerships = arrayList;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.partenerships_single_item_layout, viewGroup, false);
        this.typeface = Typeface.createFromAsset(this.context.getAssets(), "fonts/DroidSans.ttf");
        this.typefacebold = Typeface.createFromAsset(this.context.getAssets(), "fonts/RobotoCondensed_Bold.ttf");
        return new MyViewHolder(inflate);
    }

    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        Partenership partenership = this.allPartenerships.get(i);
        FontProvider.setRobotoCondensedFont(this.context, myViewHolder.tvBat1Name);
        FontProvider.setRobotoCondensedFont(this.context, myViewHolder.tvBat2Name);
        myViewHolder.tvBat1Score.setTypeface(this.typefacebold);
        myViewHolder.tvBat2Score.setTypeface(this.typefacebold);
        myViewHolder.tvTotalScore.setTypeface(this.typefacebold);
        myViewHolder.tvBat1Name.setText(Formatter.cutNameHalf(partenership.getBatsman1().getName()));
        myViewHolder.tvBat2Name.setText(Formatter.cutNameHalf(partenership.getBatsman2().getName()));
        if (partenership.getTotalPartenership() <= 0) {
            try {
                Formatter.setFormattedPartenershipScores(myViewHolder.tvBat1Score, 0, 0, this.context);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Formatter.setFormattedPartenershipScores(myViewHolder.tvBat2Score, 0, 0, this.context);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            myViewHolder.tvTotalScore.setText("0");
            myViewHolder.tvPartBalls.setText("0");
            return;
        }
        if (partenership.getBatsman1().getScore() >= 0) {
            try {
                Formatter.setFormattedPartenershipScores(myViewHolder.tvBat1Score, partenership.getBatsman1().getScore(), partenership.getBatsman1().getBalls(), this.context);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        if (partenership.getBatsman2().getScore() >= 0) {
            try {
                Formatter.setFormattedPartenershipScores(myViewHolder.tvBat2Score, partenership.getBatsman2().getScore(), partenership.getBatsman2().getBalls(), this.context);
            } catch (Exception e4) {
                e4.printStackTrace();
            }
        }
        TextView textView = myViewHolder.tvTotalScore;
        textView.setText(partenership.getTotalPartenership() + "");
        try {
            myViewHolder.roundCornerProgressBar1.setProgress((int) Partenership.getPartPercentage(partenership.getBatsman1().getScore(), PartenershipHelper.getAllPartenershipsScores(this.allPartenerships)));
            myViewHolder.roundCornerProgressBar2.setProgress((int) Partenership.getPartPercentage(partenership.getBatsman2().getScore(), PartenershipHelper.getAllPartenershipsScores(this.allPartenerships)));
        } catch (Exception e5) {
            e5.printStackTrace();
        }
        myViewHolder.roundCornerProgressBar1.setRotation(180.0f);
        TextView textView2 = myViewHolder.tvPartBalls;
        textView2.setText(partenership.getTotalPartenershipBalls() + "");
    }

    public int getItemCount() {
        try {
            return this.allPartenerships.size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout mainLayout;
        ProgressBar roundCornerProgressBar1;
        ProgressBar roundCornerProgressBar2;
        TextView tvBat1Name;
        TextView tvBat1Score;
        TextView tvBat2Name;
        TextView tvBat2Score;
        TextView tvPartBalls;
        TextView tvTotalScore;

        public MyViewHolder(View view) {
            super(view);
            this.tvBat1Name = (TextView) view.findViewById(R.id.tvBat1Name);
            this.tvBat2Name = (TextView) view.findViewById(R.id.tvBat2Name);
            this.tvBat1Score = (TextView) view.findViewById(R.id.tvbat1Score);
            this.tvBat2Score = (TextView) view.findViewById(R.id.tvbat2Score);
            this.tvTotalScore = (TextView) view.findViewById(R.id.tvTotalPart);
            this.roundCornerProgressBar1 = (ProgressBar) view.findViewById(R.id.bat1Progress);
            this.roundCornerProgressBar2 = (ProgressBar) view.findViewById(R.id.bat2Progress);
            this.mainLayout = (LinearLayout) view.findViewById(R.id.main_layout);
            this.tvPartBalls = (TextView) view.findViewById(R.id.tvPartBalls);
        }
    }
}
