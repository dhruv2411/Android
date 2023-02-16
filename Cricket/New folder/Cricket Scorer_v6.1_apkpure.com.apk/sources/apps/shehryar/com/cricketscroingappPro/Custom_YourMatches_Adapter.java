package apps.shehryar.com.cricketscroingappPro;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import java.util.ArrayList;

public class Custom_YourMatches_Adapter extends ArrayAdapter {
    LinearLayout layout;
    ArrayList<Match> matches;
    TextView matchresult;
    TextView teamagainst;

    public Custom_YourMatches_Adapter(Context context, int i, ArrayList<Match> arrayList) {
        super(context, i, arrayList);
        this.matches = arrayList;
    }

    @NonNull
    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.custom_yourmatches_listview, viewGroup, false);
        this.teamagainst = (TextView) inflate.findViewById(R.id.teamagainst);
        this.matchresult = (TextView) inflate.findViewById(R.id.match_result);
        this.teamagainst.setText(this.matches.get(i).getOpponent());
        this.matchresult.setText(this.matches.get(i).getResult());
        this.layout = (LinearLayout) inflate.findViewById(R.id.layout);
        if (i % 2 != 0) {
            this.layout.setBackgroundColor(getContext().getResources().getColor(R.color.background_dark));
        }
        return inflate;
    }

    public int getCount() {
        try {
            return this.matches.size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
