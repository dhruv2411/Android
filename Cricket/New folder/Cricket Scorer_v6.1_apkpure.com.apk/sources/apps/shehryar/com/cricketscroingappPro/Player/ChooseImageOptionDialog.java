package apps.shehryar.com.cricketscroingappPro.Player;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import apps.shehryar.com.cricketscroingapp.R;

public class ChooseImageOptionDialog extends DialogFragment implements View.OnClickListener {
    Button bCamera;
    Button bGallery;
    Button bNone;
    CameraGalleryCallBack cameraGalleryCallBack;
    int extra;

    public interface CameraGalleryCallBack {
        void onCameraTapped();

        void onGalleryTapped();
    }

    public ChooseImageOptionDialog(CameraGalleryCallBack cameraGalleryCallBack2) {
        this.cameraGalleryCallBack = cameraGalleryCallBack2;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Dialog onCreateDialog = super.onCreateDialog(bundle);
        onCreateDialog.getWindow().requestFeature(1);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(onCreateDialog.getWindow().getAttributes());
        layoutParams.width = -1;
        onCreateDialog.getWindow().setAttributes(layoutParams);
        onCreateDialog.setCancelable(true);
        onCreateDialog.setCanceledOnTouchOutside(false);
        return onCreateDialog;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.no_ball_bye_legbye, viewGroup, false);
        initializeViews(inflate);
        return inflate;
    }

    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(-1, -2);
    }

    /* access modifiers changed from: package-private */
    public void initializeViews(View view) {
        this.bCamera = (Button) view.findViewById(R.id.bbyes);
        this.bGallery = (Button) view.findViewById(R.id.blegbyes);
        this.bNone = (Button) view.findViewById(R.id.bnone);
        this.bCamera.setText("Take photo from Camera");
        this.bGallery.setText("Choose from Gallery");
        this.bNone.setVisibility(8);
        this.bCamera.setOnClickListener(this);
        this.bGallery.setOnClickListener(this);
        this.bNone.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.blegbyes /*2131755294*/:
                this.cameraGalleryCallBack.onGalleryTapped();
                dismiss();
                return;
            case R.id.bbyes /*2131755295*/:
                this.cameraGalleryCallBack.onCameraTapped();
                dismiss();
                return;
            default:
                return;
        }
    }
}
