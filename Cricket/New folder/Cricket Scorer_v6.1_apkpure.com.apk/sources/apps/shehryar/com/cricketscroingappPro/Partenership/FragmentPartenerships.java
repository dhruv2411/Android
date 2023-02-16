package apps.shehryar.com.cricketscroingappPro.Partenership;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.AlertDialogBuilder;
import java.util.ArrayList;

public class FragmentPartenerships extends Fragment {
    Context context;
    TextView partTitle;
    ArrayList<Partenership> partenerships;
    RecyclerView recyclerView;
    Typeface typeface;

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.partenerships_fragment_layout, viewGroup, false);
        this.partTitle = (TextView) inflate.findViewById(R.id.tvPartenerships);
        this.recyclerView = (RecyclerView) inflate.findViewById(R.id.partenerships_recycler_view);
        try {
            this.partenerships = (ArrayList) getArguments().getSerializable("parts");
        } catch (Exception e) {
            e.printStackTrace();
            this.partenerships = new ArrayList<>();
        }
        this.typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/eurostile_next_lt_pro_semibold.otf");
        this.context = getContext();
        AlertDialogBuilder.showParteneshipDisclaimerDialog(getContext());
        this.partTitle.setTypeface(this.typeface);
        this.partTitle.setText("PARTENERSHIPS");
        PartenershipsListAdapter partenershipsListAdapter = new PartenershipsListAdapter(this.context, this.partenerships);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this.context.getApplicationContext()));
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.setNestedScrollingEnabled(false);
        this.recyclerView.setAdapter(partenershipsListAdapter);
        this.recyclerView.setItemViewCacheSize(20);
        this.recyclerView.setDrawingCacheEnabled(true);
        this.recyclerView.setDrawingCacheQuality(1048576);
        return inflate;
    }
}
