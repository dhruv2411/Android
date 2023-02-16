package apps.shehryar.com.cricketscroingappPro.Utility_Classes;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class AdsLoader {
    public static void loadBannerAd(Context context, Activity activity, String str, int i) {
        AdView adView = (AdView) activity.findViewById(i);
        if (!SharedPrefsHelper.isPro(context)) {
            MobileAds.initialize(context, str);
            try {
                adView.loadAd(new AdRequest.Builder().build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            adView.setVisibility(8);
        }
    }

    public static void hideBannerAd(Activity activity, int i) {
        try {
            ((AdView) activity.findViewById(i)).setVisibility(8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static InterstitialAd initializeInterstitialAd(Context context, InterstitialAd interstitialAd, AdListener adListener, String str) {
        InterstitialAd interstitialAd2 = new InterstitialAd(context);
        interstitialAd2.setAdUnitId(str);
        if (!interstitialAd2.isLoading() && !interstitialAd2.isLoaded()) {
            interstitialAd2.loadAd(new AdRequest.Builder().build());
        }
        requestNewInterstitial(interstitialAd2);
        if (adListener == null) {
            adListener = new AdListener() {
                public void onAdClosed() {
                    super.onAdClosed();
                }
            };
        }
        interstitialAd2.setAdListener(adListener);
        return interstitialAd2;
    }

    public static void requestNewInterstitial(InterstitialAd interstitialAd) {
        interstitialAd.loadAd(new AdRequest.Builder().build());
    }

    public static void showInterstitalAd(InterstitialAd interstitialAd, Context context) {
        if (interstitialAd.isLoaded() && !SharedPrefsHelper.isPro(context)) {
            interstitialAd.show();
        }
    }
}
