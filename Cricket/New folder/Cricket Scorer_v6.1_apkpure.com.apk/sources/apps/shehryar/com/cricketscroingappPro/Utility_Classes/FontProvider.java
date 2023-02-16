package apps.shehryar.com.cricketscroingappPro.Utility_Classes;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

public class FontProvider {
    public static void setDroidSansBoldFont(Context context, TextView textView) {
        textView.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/DroidSans_Bold.ttf"));
    }

    public static void setRobotoCondensedFont(Context context, TextView textView) {
        textView.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/RobotoCondensed_Bold.ttf"));
    }

    public static void setSeguUIFont(Context context, TextView textView) {
        textView.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/segoeuib.ttf"));
    }

    public static void setEuroStileFont(Context context, TextView textView) throws Exception {
        textView.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/eurostile_next_lt_pro_semibold.otf"));
    }

    public static void setFiraSansFont(Context context, TextView textView) throws Exception {
        textView.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/FiraSans_Bold.ttf"));
    }
}
