package apps.shehryar.com.cricketscroingappPro.Player;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
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
import apps.shehryar.com.cricketscroingappPro.Player.DialogWithButtons;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.BitmapUtility;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.FontProvider;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Iterator;

public class AddPlayerDialog extends MyDialogFragment implements View.OnClickListener, DialogWithButtons.DialogWithButtonsInteractionListener {
    Activity activity;
    ArrayList<Player> allPlayers;
    Button bAdd;
    Button bCancel;
    playerAddedCallBackInterface callBackInterface;
    ChooseImageOptionDialog.CameraGalleryCallBack cameraGalleryCallBack = new ChooseImageOptionDialog.CameraGalleryCallBack() {
        public void onCameraTapped() {
            AddPlayerDialog.this.cameraTapped = true;
            AddPlayerDialog.this.galleryTapped = false;
            if (!PermissionAsker.checkAPIVersion()) {
                AddPlayerDialog.this.cameraIntent();
            } else if (PermissionAsker.checkIfAlreadyhavePermission(AddPlayerDialog.this.context)) {
                AddPlayerDialog.this.cameraIntent();
            } else if (Build.VERSION.SDK_INT >= 23) {
                AddPlayerDialog.this.requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 103);
            }
        }

        public void onGalleryTapped() {
            AddPlayerDialog.this.cameraTapped = false;
            AddPlayerDialog.this.galleryTapped = true;
            if (!PermissionAsker.checkAPIVersion()) {
                AddPlayerDialog.this.galleryIntent();
            } else if (PermissionAsker.checkIfAlreadyhavePermission(AddPlayerDialog.this.context)) {
                AddPlayerDialog.this.galleryIntent();
            } else if (Build.VERSION.SDK_INT >= 23) {
                AddPlayerDialog.this.requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 104);
            }
        }
    };
    boolean cameraTapped;
    Context context;
    EditText edPlayerType;
    boolean galleryTapped;
    Player player;
    String playerImage;
    EditText playerName;
    int playerType = 1;
    ImageView profileImage;
    Team team;

    public interface playerAddedCallBackInterface {
        void onPlayerAdded(Player player);

        void onPlayerUpdated();
    }

    public AddPlayerDialog(Context context2, Team team2, Player player2, playerAddedCallBackInterface playeraddedcallbackinterface, ArrayList<Player> arrayList) {
        this.context = context2;
        this.team = team2;
        this.player = player2;
        this.activity = (Activity) context2;
        this.callBackInterface = playeraddedcallbackinterface;
        this.allPlayers = arrayList;
    }

    public AddPlayerDialog() {
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.add_player_dialog, viewGroup, false);
        this.profileImage = (ImageView) inflate.findViewById(R.id.profileImage);
        this.playerName = (EditText) inflate.findViewById(R.id.edPlayerName);
        this.bAdd = (Button) inflate.findViewById(R.id.bAddPlayer);
        this.bCancel = (Button) inflate.findViewById(R.id.bcancelPlayer);
        this.edPlayerType = (EditText) inflate.findViewById(R.id.ed_player_type);
        try {
            FontProvider.setEuroStileFont(getActivity(), this.bAdd);
            FontProvider.setEuroStileFont(getActivity(), this.bCancel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setUpListenerForPlayerTypeDialog();
        if (this.player != null) {
            this.playerName.setText(this.player.getName());
            this.bAdd.setText("Update Player");
            if (this.player.getPlayerType() != null) {
                this.edPlayerType.setText(this.player.getPlayerType());
            } else {
                this.edPlayerType.setText("Select Player Type");
            }
            if (this.player.getImage() != null) {
                Picasso.with(getActivity()).load(Uri.parse(this.player.getImage())).into(this.profileImage);
                this.playerImage = this.player.getImage();
            } else {
                this.profileImage.setImageResource(R.drawable.ic_photo_camera_black_24dp);
            }
        }
        this.profileImage.setOnClickListener(this);
        this.bAdd.setOnClickListener(this);
        this.bCancel.setOnClickListener(this);
        return inflate;
    }

    private void setUpListenerForPlayerTypeDialog() {
        this.edPlayerType.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @RequiresApi(api = 17)
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    AddPlayerDialog.this.showBatsmanTypeDialog();
                }
            }
        });
        this.edPlayerType.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = 17)
            public void onClick(View view) {
                AddPlayerDialog.this.showBatsmanTypeDialog();
            }
        });
    }

    /* access modifiers changed from: private */
    @RequiresApi(api = 17)
    public void showBatsmanTypeDialog() {
        if (Build.VERSION.SDK_INT >= 21) {
            this.edPlayerType.setShowSoftInputOnFocus(false);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add("Batsman");
        arrayList.add("Bowler");
        arrayList.add("Wicket Keeper");
        arrayList.add("Wicket Keeper+Batsman");
        arrayList.add("All Rounder");
        DialogWithButtons.newInstance(arrayList).show(getChildFragmentManager(), DialogWithButtons.class.toString());
    }

    private void setUpSpinner(View view) {
        MaterialSpinner materialSpinner = (MaterialSpinner) view.findViewById(R.id.spinner);
        materialSpinner.setItems((T[]) new String[]{"Batsman", "Bowler", "Wicket Keeper", "Wicket Keeper+Batsman", "All Rounder"});
        materialSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            public void onItemSelected(MaterialSpinner materialSpinner, int i, long j, String str) {
                AddPlayerDialog.this.playerType = i + 1;
            }
        });
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.profileImage) {
            ChooseImageOptionDialog chooseImageOptionDialog = new ChooseImageOptionDialog(this.cameraGalleryCallBack);
            chooseImageOptionDialog.setArguments(new Bundle());
            chooseImageOptionDialog.show(getFragmentManager(), "choose Profile Image Dialog");
        } else if (id == R.id.bAddPlayer) {
            addPlayer();
        } else if (id == R.id.bcancelPlayer) {
            dismiss();
        }
    }

    private void addPlayer() {
        if (this.playerName.getText().toString().isEmpty()) {
            MyToast.showToast(this.context, "Please Enter Player Name");
            return;
        }
        try {
            if (checkPlayerAlreadyAdded()) {
                MyToast.showToast(this.context, "This player is already added. Please change the name");
            } else {
                insertOrUpdatePlayerInDB(this.team);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertOrUpdatePlayerInDB(Team team2) {
        boolean z;
        if (this.player != null) {
            this.player.setOldName(this.player.getName());
            this.player.setName(this.playerName.getText().toString());
            this.player.setImage(this.playerImage);
            this.player.setTeamName(team2.getName());
            this.player.setPlayerType(this.playerType);
            if (this.player.getPlayerType() != null) {
                this.edPlayerType.setText(this.player.getPlayerType());
            } else {
                this.edPlayerType.setText("Select Player Type");
            }
            new DBHelper(this.context).updateUserAddedPlayer(this.player);
            z = true;
        } else {
            this.player = new Player();
            this.player.setName(this.playerName.getText().toString());
            this.player.setTeamId(team2.getTeamID());
            this.player.setImage(this.playerImage);
            this.player.setPlayerType(this.playerType);
            this.player.setPlayerId(new DBHelper(this.context).insertUserAddedPlayer(this.player));
            z = false;
        }
        dismiss();
        if (!z) {
            this.callBackInterface.onPlayerAdded(this.player);
        } else {
            this.callBackInterface.onPlayerUpdated();
        }
    }

    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(-1, -2);
    }

    public void onButton1Tapped() {
        this.playerType = 1;
        this.edPlayerType.setText("Batsman");
    }

    public void onButton2Tapped() {
        this.playerType = 2;
        this.edPlayerType.setText("Bowler");
    }

    public void onButton3Tapped() {
        this.playerType = 3;
        this.edPlayerType.setText("Wicket Keeper");
    }

    public void onButton4Tapped() {
        this.playerType = 4;
        this.edPlayerType.setText("Wicket Keeper/Batsman");
    }

    public void onButton5Tapped() {
        this.playerType = 5;
        this.edPlayerType.setText("All Rounder");
    }

    /* access modifiers changed from: private */
    public void cameraIntent() {
        startActivityForResult(new Intent("android.media.action.IMAGE_CAPTURE"), 100);
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

    /* access modifiers changed from: package-private */
    public boolean checkPlayerAlreadyAdded() throws Exception {
        if (this.player != null && this.player.getName().equals(this.playerName.getText().toString())) {
            return false;
        }
        Iterator<Player> it = this.allPlayers.iterator();
        while (it.hasNext()) {
            if (it.next().getName().equals(this.playerName.getText().toString())) {
                return true;
            }
        }
        return false;
    }
}
