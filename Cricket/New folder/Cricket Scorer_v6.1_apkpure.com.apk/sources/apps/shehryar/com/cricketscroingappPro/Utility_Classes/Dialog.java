package apps.shehryar.com.cricketscroingappPro.Utility_Classes;

import android.content.Context;
import android.content.DialogInterface;

public class Dialog {
    private DialogInterface.OnClickListener clickListener;
    private Context context;
    private String msg;
    private String noBtn;
    private String nuetralBtn;
    private String title;
    private String yesBtn;

    public Dialog(Context context2, String str, String str2, String str3, String str4, DialogInterface.OnClickListener onClickListener) {
        this.context = context2;
        this.title = str;
        this.msg = str2;
        this.yesBtn = str3;
        this.noBtn = str4;
        this.clickListener = onClickListener;
    }

    public DialogInterface.OnClickListener getClickListener() {
        return this.clickListener;
    }

    public void setClickListener(DialogInterface.OnClickListener onClickListener) {
        this.clickListener = onClickListener;
    }

    public Context getContext() {
        return this.context;
    }

    public void setContext(Context context2) {
        this.context = context2;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public String getYesBtn() {
        return this.yesBtn;
    }

    public void setYesBtn(String str) {
        this.yesBtn = str;
    }

    public String getNoBtn() {
        return this.noBtn;
    }

    public void setNoBtn(String str) {
        this.noBtn = str;
    }

    public String getNuetralBtn() {
        return this.nuetralBtn;
    }

    public void setNuetralBtn(String str) {
        this.nuetralBtn = str;
    }
}
