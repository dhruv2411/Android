package apps.shehryar.com.cricketscroingappPro.Utility_Classes;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import apps.shehryar.com.cricketscroingapp.R;

public class CustomDiealogBuilder {
    public static void showUpgradeDialog(Context context, View.OnClickListener onClickListener) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.upgrade_to_pro_layout);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_2;
        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 17;
        attributes.width = -1;
        window.setAttributes(attributes);
        dialog.show();
        ((Button) dialog.findViewById(R.id.bupgrade)).setOnClickListener(onClickListener);
        ((Button) dialog.findViewById(R.id.bcancel)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
