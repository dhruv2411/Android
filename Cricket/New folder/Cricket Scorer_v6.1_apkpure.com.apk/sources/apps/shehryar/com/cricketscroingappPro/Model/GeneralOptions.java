package apps.shehryar.com.cricketscroingappPro.Model;

import apps.shehryar.com.CheckBoxListItem;

public class GeneralOptions implements CheckBoxListItem {
    String checkBoxText;
    boolean isChecked;

    public CheckBoxListItem getCheckBoxListItem() {
        return this;
    }

    public GeneralOptions(String str, boolean z) {
        this.checkBoxText = str;
        this.isChecked = z;
    }

    public String getCheckBoxText() {
        return this.checkBoxText;
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void setChecked(boolean z) {
        this.isChecked = z;
    }
}
