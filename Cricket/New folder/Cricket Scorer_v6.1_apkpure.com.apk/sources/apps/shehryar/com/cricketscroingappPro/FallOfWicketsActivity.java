package apps.shehryar.com.cricketscroingappPro;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.FontProvider;
import java.util.ArrayList;

public class FallOfWicketsActivity extends Activity {
    ListView listView;
    ArrayList<FallOfWickets> wicketses;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.wicketses = (ArrayList) getIntent().getSerializableExtra("wickets");
        setContentView(R.layout.activity_fall_of_wickets);
        try {
            FontProvider.setEuroStileFont(this, (TextView) findViewById(R.id.title));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.listView = (ListView) findViewById(R.id.fallofwicketslistview);
        this.listView.setAdapter(new Custom_Fall_of_Wickets_Adapter(this, R.layout.custom_fall_of_wicket_layout, this.wicketses));
    }
}
