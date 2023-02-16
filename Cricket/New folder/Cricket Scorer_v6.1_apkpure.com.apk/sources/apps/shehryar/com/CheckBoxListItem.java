package apps.shehryar.com;

import java.io.Serializable;

public interface CheckBoxListItem extends Serializable {
    public static final long serialVersionUID = 42;

    CheckBoxListItem getCheckBoxListItem();

    String getCheckBoxText();

    boolean isChecked();

    void setChecked(boolean z);
}
