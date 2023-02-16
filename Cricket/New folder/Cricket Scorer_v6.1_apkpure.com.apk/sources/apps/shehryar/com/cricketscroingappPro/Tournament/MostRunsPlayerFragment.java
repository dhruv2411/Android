package apps.shehryar.com.cricketscroingappPro.Tournament;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;

public class MostRunsPlayerFragment extends Fragment {
    public static final String PLAYER_IMAGE = "player_image";
    public static final String PLAYER_NAME = "player_name";
    public static final String PLAYER_STATS = "player_stats";
    ImageView playerImage;
    TextView playerName;
    TextView playerStats;

    public static MostRunsPlayerFragment newInstance(String str, int i, String str2) {
        MostRunsPlayerFragment mostRunsPlayerFragment = new MostRunsPlayerFragment();
        Bundle bundle = new Bundle();
        bundle.putString(PLAYER_NAME, str);
        bundle.putInt(PLAYER_STATS, i);
        bundle.putString(PLAYER_IMAGE, str2);
        mostRunsPlayerFragment.setArguments(bundle);
        return mostRunsPlayerFragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.most_runs_player_fragment_layout, viewGroup, false);
        initializeviews(inflate);
        receiveArgumentsAndUpdateUI();
        return inflate;
    }

    private void initializeviews(View view) {
        this.playerName = (TextView) view.findViewById(R.id.tv_player_name);
        this.playerStats = (TextView) view.findViewById(R.id.tv_player_stats);
        this.playerImage = (ImageView) view.findViewById(R.id.iv_player_image);
    }

    private void receiveArgumentsAndUpdateUI() {
        String string = getArguments().getString(PLAYER_NAME);
        String string2 = getArguments().getString(PLAYER_IMAGE);
        int i = getArguments().getInt(PLAYER_STATS);
        this.playerName.setText(string);
        try {
            this.playerImage.setImageURI(Uri.parse(string2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        TextView textView = this.playerStats;
        textView.setText(i + "");
    }
}
