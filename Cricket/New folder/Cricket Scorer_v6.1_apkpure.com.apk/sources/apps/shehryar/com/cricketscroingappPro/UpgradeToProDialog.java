package apps.shehryar.com.cricketscroingappPro;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.SharedPrefsHelper;
import util.IabHelper;
import util.IabResult;
import util.Purchase;

public class UpgradeToProDialog extends MyDialogFragment {
    String base64EncodedPublicKey;
    UpgradeToProCallBack listener;
    /* access modifiers changed from: private */
    public IabHelper mHelper;
    /* access modifiers changed from: private */
    public IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener;

    public interface UpgradeToProCallBack {
        void onPurchaseFailed();

        void onPurchaseSuccessfull();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof UpgradeToProCallBack) {
            this.listener = (UpgradeToProCallBack) context;
        }
    }

    public void setListener(UpgradeToProCallBack upgradeToProCallBack) {
        this.listener = upgradeToProCallBack;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7u5kRGfbQ2KgIHfyHPw4TRR1KPmNG+PEfaJuOe6ohNhqjCy1iQyfhgvRis3AY6Bs8osPDFxI76G2XzAycB9/v6G0PCVEG4ITVkZCnVtTOx2r8p2f24LW3OQNcZrTFCF0mW7cSA2XnWEe0DYCByC0Z5TG+95FhL5rO1TJECEMum3p54l2dYnOgmJTOrnWp0ry6Jv3xC+26T0D07PFxGQoeUxOGcuL0a+rvi4IyWrwUJCX66jVh5EYnNdZ7trtAjtLEjqyAWAzcc3yUbMPUZtw1Do0S+HpKtyyeruZzpJgCl0Hpgdrz4P+7x6dCLI6QkFkrhngqBQNnGIQcev0sB6UewIDAQAB";
        this.mHelper = new IabHelper(getActivity(), this.base64EncodedPublicKey);
        this.mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
            public void onIabPurchaseFinished(IabResult iabResult, Purchase purchase) {
                if (iabResult.getResponse() == 7) {
                    SharedPrefsHelper.insertProFeatures(UpgradeToProDialog.this.getActivity(), true);
                    MyToast.showToast(UpgradeToProDialog.this.getActivity(), "Item already purchased");
                    UpgradeToProDialog.this.listener.onPurchaseSuccessfull();
                    UpgradeToProDialog.this.dismiss();
                } else if (iabResult.isFailure()) {
                    Log.d("purchaising", "Error purchasing: " + iabResult);
                    MyToast.showToast(UpgradeToProDialog.this.getActivity(), "Billing unsuccessfull");
                } else if (purchase.getSku().equals("all_pro_features")) {
                    MyToast.showToast(UpgradeToProDialog.this.getActivity(), "App is upgraded to PRO");
                    SharedPrefsHelper.insertProFeatures(UpgradeToProDialog.this.getActivity(), true);
                }
            }
        };
    }

    public Dialog onCreateDialog(Bundle bundle) {
        return super.onCreateDialog(bundle);
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.upgrade_to_pro_layout, viewGroup, false);
        ((Button) inflate.findViewById(R.id.bupgrade)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!UpgradeToProDialog.this.mHelper.isSetupDone()) {
                    UpgradeToProDialog.this.mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
                        public void onIabSetupFinished(IabResult iabResult) {
                            if (iabResult.getResponse() == 7) {
                                SharedPrefsHelper.insertProFeatures(UpgradeToProDialog.this.getActivity(), true);
                                MyToast.showToast(UpgradeToProDialog.this.getActivity(), "Item already purchased");
                                UpgradeToProDialog.this.listener.onPurchaseSuccessfull();
                                UpgradeToProDialog.this.dismiss();
                            } else if (!iabResult.isSuccess()) {
                                Log.d("IAB PROBLEM", "Problem setting up In-app Billing: " + iabResult);
                                MyToast.showToast(UpgradeToProDialog.this.getActivity(), "Something went wrong. Please make sure that you have an internet connection and working Google Account set up on this phone and try again.");
                            } else {
                                try {
                                    if (UpgradeToProDialog.this.mHelper == null) {
                                        IabHelper unused = UpgradeToProDialog.this.mHelper = new IabHelper(UpgradeToProDialog.this.getActivity(), UpgradeToProDialog.this.base64EncodedPublicKey);
                                    }
                                    if (UpgradeToProDialog.this.mHelper != null) {
                                        UpgradeToProDialog.this.mHelper.launchPurchaseFlow(UpgradeToProDialog.this.getActivity(), "all_pro_features", 10001, UpgradeToProDialog.this.mPurchaseFinishedListener, "bGoa+V7g/yqDXvKRqq+JTFn4uQZbPiQJo4pf9RzJ");
                                    }
                                } catch (IabHelper.IabAsyncInProgressException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                    return;
                }
                try {
                    UpgradeToProDialog.this.mHelper.launchPurchaseFlow(UpgradeToProDialog.this.getActivity(), "all_pro_features", 10001, UpgradeToProDialog.this.mPurchaseFinishedListener, "bGoa+V7g/yqDXvKRqq+JTFn4uQZbPiQJo4pf9RzJ");
                } catch (IabHelper.IabAsyncInProgressException e) {
                    e.printStackTrace();
                }
            }
        });
        ((Button) inflate.findViewById(R.id.bcancel)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UpgradeToProDialog.this.dismiss();
            }
        });
        return inflate;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 10001 && i2 == -1) {
            SharedPrefsHelper.insertProFeatures(getActivity(), true);
            MyToast.showToast(getActivity(), "Congratulations on being a PRO Cricket Scorer");
            this.listener.onPurchaseSuccessfull();
            dismiss();
        } else if (i == 10001 && i2 == 0) {
            this.listener.onPurchaseFailed();
        }
    }
}
