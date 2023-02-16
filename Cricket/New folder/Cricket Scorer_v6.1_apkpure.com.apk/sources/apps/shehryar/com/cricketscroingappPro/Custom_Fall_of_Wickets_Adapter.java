package apps.shehryar.com.cricketscroingappPro;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import java.util.ArrayList;

public class Custom_Fall_of_Wickets_Adapter extends ArrayAdapter {
    TextView batsmanname;
    TextView bowlername;
    LinearLayout layout;
    TextView wicketOver;
    TextView wicketno;
    TextView wicketscore;
    ArrayList<FallOfWickets> wicketses;

    public Custom_Fall_of_Wickets_Adapter(Context context, int i, ArrayList<FallOfWickets> arrayList) {
        super(context, i, arrayList);
        this.wicketses = arrayList;
        if (this.wicketses == null) {
            this.wicketses = new ArrayList<>();
        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        Typeface createFromAsset = Typeface.createFromAsset(getContext().getAssets(), "fonts/DroidSans.ttf");
        Typeface.createFromAsset(getContext().getAssets(), "fonts/RobotoCondensed_Bold.ttf");
        Typeface createFromAsset2 = Typeface.createFromAsset(getContext().getAssets(), "fonts/RobotoCondensed_Regular.ttf");
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.custom_fall_of_wicket_layout, viewGroup, false);
        this.wicketno = (TextView) inflate.findViewById(R.id.wicketno);
        this.wicketscore = (TextView) inflate.findViewById(R.id.wicketscore);
        this.batsmanname = (TextView) inflate.findViewById(R.id.batsmanname);
        this.bowlername = (TextView) inflate.findViewById(R.id.bowlername);
        this.wicketOver = (TextView) inflate.findViewById(R.id.tvwicketover);
        this.layout = (LinearLayout) inflate.findViewById(R.id.layout);
        this.wicketno.setTypeface(createFromAsset);
        this.wicketscore.setTypeface(createFromAsset);
        this.batsmanname.setTypeface(createFromAsset2);
        this.bowlername.setTypeface(createFromAsset2);
        this.wicketOver.setTypeface(createFromAsset);
        this.wicketOver.setTextColor(getContext().getResources().getColor(17170444));
        int lastIndexOf = this.wicketses.get(i).getBatsmanName().lastIndexOf(32);
        Log.e("name start", lastIndexOf + "");
        if (lastIndexOf <= 0) {
            this.batsmanname.setText(this.wicketses.get(i).getBatsmanName());
        } else {
            this.batsmanname.setText(this.wicketses.get(i).getBatsmanName().substring(0, lastIndexOf));
        }
        int lastIndexOf2 = this.wicketses.get(i).getBowlerName() != null ? this.wicketses.get(i).getBowlerName().lastIndexOf(32) : 0;
        Log.e("name start", lastIndexOf + "");
        if (lastIndexOf2 <= 0) {
            this.bowlername.setText(this.wicketses.get(i).getBowlerName());
        } else {
            this.bowlername.setText(this.wicketses.get(i).getBowlerName().substring(0, lastIndexOf2));
        }
        if (this.wicketses.get(i).getBowlerName().equals("RUN OUT")) {
            this.bowlername.setText("RUN OUT");
        }
        TextView textView = this.wicketno;
        textView.setText(this.wicketses.get(i).getWicketNo() + "");
        TextView textView2 = this.wicketscore;
        textView2.setText(this.wicketses.get(i).getScore() + "");
        TextView textView3 = this.wicketOver;
        textView3.setText(this.wicketses.get(i).getOverno() + "." + this.wicketses.get(i).getBall());
        if (i % 2 != 0) {
            this.layout.setBackgroundColor(getContext().getResources().getColor(R.color.background_dark));
        }
        return inflate;
    }

    public int getCount() {
        try {
            return this.wicketses.size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
