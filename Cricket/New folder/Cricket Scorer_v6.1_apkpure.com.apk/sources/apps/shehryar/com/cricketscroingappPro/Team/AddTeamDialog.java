package apps.shehryar.com.cricketscroingappPro.Team;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.MyDialogFragment;
import apps.shehryar.com.cricketscroingappPro.MyToast;
import apps.shehryar.com.cricketscroingappPro.PermissionAsker;
import apps.shehryar.com.cricketscroingappPro.Player.ChooseImageOptionDialog;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.BitmapUtility;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.FontProvider;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.SharedPrefsHelper;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Iterator;

public class AddTeamDialog extends MyDialogFragment implements View.OnClickListener {
    OtherTeamPlayersActivity activity;
    ArrayList<Team> allTeams;
    Button bAdd;
    Button bCancel;
    ChooseImageOptionDialog.CameraGalleryCallBack cameraGalleryCallBack = new ChooseImageOptionDialog.CameraGalleryCallBack() {
        public void onCameraTapped() {
            if (!PermissionAsker.checkAPIVersion()) {
                AddTeamDialog.this.cameraIntent();
            } else if (PermissionAsker.checkIfAlreadyhavePermission(AddTeamDialog.this.context)) {
                AddTeamDialog.this.cameraIntent();
            } else if (Build.VERSION.SDK_INT >= 23) {
                AddTeamDialog.this.requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 103);
            }
        }

        public void onGalleryTapped() {
            if (!PermissionAsker.checkAPIVersion()) {
                AddTeamDialog.this.galleryIntent();
            } else if (PermissionAsker.checkIfAlreadyhavePermission(AddTeamDialog.this.context)) {
                AddTeamDialog.this.galleryIntent();
            } else if (Build.VERSION.SDK_INT >= 23) {
                AddTeamDialog.this.requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 104);
            }
        }
    };
    Context context;
    private int index;
    private String playerImage;
    EditText playerName;
    ImageView profileImage;
    Team team;
    boolean teamAdded;
    teamAddedCallBackInterface teamAddedCallBackInterface;
    boolean teamUpdated;

    public interface teamAddedCallBackInterface {
        void onTeamAdded(Team team);

        void onTeamUpdated(int i, String str);
    }

    public AddTeamDialog(Context context2, Team team2, teamAddedCallBackInterface teamaddedcallbackinterface, ArrayList<Team> arrayList, int i) {
        this.context = context2;
        this.team = team2;
        this.teamAddedCallBackInterface = teamaddedcallbackinterface;
        this.allTeams = arrayList;
        this.index = i;
    }

    public AddTeamDialog() {
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.add_team_dialog_layout, viewGroup, false);
        this.playerName = (EditText) inflate.findViewById(R.id.edTeamName);
        this.bAdd = (Button) inflate.findViewById(R.id.bAddTeam);
        this.bCancel = (Button) inflate.findViewById(R.id.bcancelTeam);
        try {
            FontProvider.setEuroStileFont(getActivity(), this.bAdd);
            FontProvider.setEuroStileFont(getActivity(), this.bCancel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.profileImage = (ImageView) inflate.findViewById(R.id.teamProfileImage);
        try {
            this.team.setImage(SharedPrefsHelper.getTeamImage(getActivity(), this.team));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (this.team != null) {
            this.playerName.setText(this.team.getName());
            if (!this.team.getImage().isEmpty()) {
                Picasso.with(getActivity()).load(Uri.parse(this.team.getImage())).into(this.profileImage);
            }
        }
        try {
            if (this.team.getTeamID() > 0) {
                this.bAdd.setText("Update Team");
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        this.bAdd.setOnClickListener(this);
        this.bCancel.setOnClickListener(this);
        this.profileImage.setOnClickListener(this);
        return inflate;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.teamProfileImage /*2131755415*/:
                ChooseImageOptionDialog chooseImageOptionDialog = new ChooseImageOptionDialog(this.cameraGalleryCallBack);
                chooseImageOptionDialog.setArguments(new Bundle());
                chooseImageOptionDialog.show(getFragmentManager(), "choose Profile Image Dialog");
                return;
            case R.id.bAddTeam /*2131755417*/:
                addTeam();
                return;
            case R.id.bcancelTeam /*2131755418*/:
                dismiss();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public void cameraIntent() {
        try {
            startActivityForResult(new Intent("android.media.action.IMAGE_CAPTURE"), 100);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction("android.intent.action.GET_CONTENT");
        startActivityForResult(Intent.createChooser(intent, "Select File"), 101);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (iArr[0] == 0) {
            switch (i) {
                case 103:
                    cameraIntent();
                    return;
                case 104:
                    galleryIntent();
                    return;
                default:
                    return;
            }
        } else {
            MyToast.showToast(this.context, "Permission denied. Cannot pick image");
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        Bitmap bitmap;
        Bitmap bitmap2;
        Uri imageUri;
        super.onActivityResult(i, i2, intent);
        if (i2 != -1) {
            return;
        }
        if (i == 100) {
            try {
                bitmap2 = BitmapUtility.getCapturedImageBitmap(intent);
            } catch (Exception e) {
                e.printStackTrace();
                bitmap2 = null;
            }
            if (bitmap2 != null && (imageUri = BitmapUtility.getImageUri(this.context, bitmap2)) != null) {
                this.playerImage = imageUri.toString();
                Picasso.with(getActivity()).load(imageUri).into(this.profileImage);
            }
        } else if (i == 101) {
            try {
                bitmap = BitmapUtility.getSelectImageFromGallery(intent, this.context);
            } catch (Exception e2) {
                e2.printStackTrace();
                bitmap = null;
            }
            Uri imageUri2 = BitmapUtility.getImageUri(this.context, bitmap);
            if (imageUri2 != null) {
                this.playerImage = imageUri2.toString();
                Picasso.with(getActivity()).load(imageUri2).into(this.profileImage);
            }
        }
    }

    private void addTeam() {
        if (this.playerName.getText().toString().isEmpty()) {
            MyToast.showToast(this.context, "Please Enter Team Name");
            return;
        }
        try {
            if (checkTeamAlreadyAdded()) {
                MyToast.showToast(this.context, "This team is already added. Please change team name");
                return;
            }
            if (this.team.getTeamID() > 0) {
                updateTeaminDB(this.team);
                this.teamAddedCallBackInterface.onTeamUpdated(this.index, this.playerName.getText().toString());
            } else {
                insertTeamInDB(this.team);
                this.teamAddedCallBackInterface.onTeamAdded(this.team);
            }
            dismiss();
        } catch (Exception e) {
            e.printStackTrace();
            dismiss();
            MyToast.showToast(getActivity(), "Some thing went wrong while Adding Team. Please try again");
        }
    }

    private void insertTeamInDB(Team team2) {
        team2.setName(this.playerName.getText().toString());
        team2.setImage(this.playerImage);
        team2.setTeamID(new DBHelper(this.context).insertUserAddedTeam(team2));
        SharedPrefsHelper.insertTeamImage(this.context, team2);
        this.teamAdded = true;
        this.teamUpdated = false;
    }

    private void updateTeaminDB(Team team2) {
        team2.setOldName(team2.getName());
        team2.setName(this.playerName.getText().toString());
        team2.setImage(this.playerImage);
        new DBHelper(this.context).updateUserAddedTeam(team2);
        SharedPrefsHelper.insertTeamImage(this.context, team2);
        this.teamUpdated = true;
        this.teamAdded = false;
    }

    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(-1, -2);
    }

    /* access modifiers changed from: package-private */
    public boolean checkTeamAlreadyAdded() throws Exception {
        Iterator<Team> it = this.allTeams.iterator();
        while (it.hasNext()) {
            Team next = it.next();
            if (next.getName() != null && next.getName().equals(this.playerName.getText().toString()) && this.team.getTeamID() <= 0) {
                return true;
            }
        }
        return false;
    }
}
