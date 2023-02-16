package apps.shehryar.com.cricketscroingappPro;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import java.util.ArrayList;

public class Custom_Suggestions_Adapter extends ArrayAdapter {
    ArrayList<String> names;
    TextView textView;

    public Custom_Suggestions_Adapter(Context context, int i, ArrayList<String> arrayList) {
        super(context, i, arrayList);
        this.names = arrayList;
    }

    @NonNull
    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.custom_suggestions_layout, viewGroup, false);
        this.textView = (TextView) inflate.findViewById(R.id.optionsbatname);
        try {
            if (this.names.get(i) != null && !this.names.get(i).isEmpty()) {
                this.textView.setText(this.names.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inflate;
    }

    public void setTeams(ArrayList<String> arrayList) {
        this.names = arrayList;
        notifyDataSetChanged();
    }
}
