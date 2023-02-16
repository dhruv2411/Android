package apps.shehryar.com.cricketscroingappPro;

import apps.shehryar.com.CheckBoxListItem;
import java.util.ArrayList;
import java.util.Iterator;

public class GeneralCheckBoxListItem implements CheckBoxListItem {
    String checkBoxText;
    boolean isChecked;

    public CheckBoxListItem getCheckBoxListItem() {
        return this;
    }

    public GeneralCheckBoxListItem(String str, boolean z) {
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

    public static ArrayList<GeneralCheckBoxListItem> getCheckBoxListItems(ArrayList<String> arrayList) {
        ArrayList<GeneralCheckBoxListItem> arrayList2 = new ArrayList<>();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(new GeneralCheckBoxListItem(it.next(), false));
        }
        return arrayList2;
    }
}
