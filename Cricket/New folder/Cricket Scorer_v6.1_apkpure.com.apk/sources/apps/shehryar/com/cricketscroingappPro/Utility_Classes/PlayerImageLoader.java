package apps.shehryar.com.cricketscroingappPro.Utility_Classes;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.Player.Player;

public class PlayerImageLoader extends AsyncTask {
    Context context;
    ImageView imageView;
    String imgUri;
    Player player;

    public PlayerImageLoader(Context context2, ImageView imageView2, Player player2) {
        this.player = player2;
        this.imageView = imageView2;
        this.context = context2;
    }

    /* access modifiers changed from: protected */
    public Object doInBackground(Object[] objArr) {
        DBHelper dBHelper = new DBHelper(this.context);
        if (this.player == null) {
            return null;
        }
        try {
            this.imgUri = dBHelper.getPlayerImage(this.player.getName(), dBHelper.getUserAddedTeamId(this.player.getTeamName()));
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(Object obj) {
        super.onPostExecute(obj);
        if (this.imgUri != null) {
            if (this.imageView != null) {
                Log.i("image uri", this.imgUri);
            }
            this.imageView.setImageURI(Uri.parse(this.imgUri));
            return;
        }
        this.imageView.setImageResource(R.drawable.ic_player_profile);
    }
}
