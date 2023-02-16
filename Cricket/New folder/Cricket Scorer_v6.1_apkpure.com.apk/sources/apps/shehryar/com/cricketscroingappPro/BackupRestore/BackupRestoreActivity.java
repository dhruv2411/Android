package apps.shehryar.com.cricketscroingappPro.BackupRestore;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.MyToast;
import apps.shehryar.com.cricketscroingappPro.PermissionAsker;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.AlertDialogBuilder;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.Dialog;

public class BackupRestoreActivity extends AppCompatActivity implements DialogInterface.OnClickListener {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_backup_restore);
    }

    public void buttonClick(View view) {
        switch (view.getId()) {
            case R.id.b_restore /*2131755185*/:
                if (!PermissionAsker.checkAPIVersion()) {
                    showDialogAndRestore();
                    return;
                } else if (PermissionAsker.checkIfAlreadyhavePermission(this)) {
                    showDialogAndRestore();
                    return;
                } else {
                    PermissionAsker.requestForSpecificPermission(102, this);
                    return;
                }
            case R.id.b_backup /*2131755186*/:
                if (!PermissionAsker.checkAPIVersion()) {
                    AlertDialogBuilder.showAlertDialog(new Dialog(this, "Confirm Backup", "Are you sure you want to backup. Your previous backup will be deleted", "Yes", "No", this));
                    return;
                } else if (PermissionAsker.checkIfAlreadyhavePermission(this)) {
                    AlertDialogBuilder.showAlertDialog(new Dialog(this, "Confirm Backup", "Are you sure you want to backup. Your previous backup will be deleted", "Yes", "No", this));
                    return;
                } else {
                    PermissionAsker.requestForSpecificPermission(101, this);
                    return;
                }
            default:
                return;
        }
    }

    private void showDialogAndRestore() {
        new AlertDialog.Builder(this).setTitle("Confirm Restore").setMessage("Upon Restoring, your current data will be delete. Do you want to continue?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                BackupAndRestore.importDB(BackupRestoreActivity.this);
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).setCancelable(true).show();
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        switch (i) {
            case 101:
                if (iArr[0] == 0) {
                    BackupAndRestore.exportDB(this);
                    return;
                } else {
                    MyToast.showToast(this, "Backup Failed. Permission denied.");
                    return;
                }
            case 102:
                if (iArr[0] == 0) {
                    showDialogAndRestore();
                    return;
                } else {
                    MyToast.showToast(this, "Restore Failed. Permission denied.");
                    return;
                }
            default:
                super.onRequestPermissionsResult(i, strArr, iArr);
                return;
        }
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == -1) {
            if (!PermissionAsker.checkAPIVersion()) {
                BackupAndRestore.exportDB(this);
            } else if (PermissionAsker.checkIfAlreadyhavePermission(this)) {
                BackupAndRestore.exportDB(this);
            } else {
                PermissionAsker.requestForSpecificPermission(101, this);
            }
        }
    }
}
