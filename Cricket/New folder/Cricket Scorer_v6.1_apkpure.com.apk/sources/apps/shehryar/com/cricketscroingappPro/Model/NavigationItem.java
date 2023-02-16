package apps.shehryar.com.cricketscroingappPro.Model;

import java.io.Serializable;

public class NavigationItem implements Serializable {
    private int id;
    private int imageId;
    private String title;

    public NavigationItem(int i, int i2, String str) {
        this.id = i;
        this.imageId = i2;
        this.title = str;
    }

    public NavigationItem() {
    }

    public int getId() {
        return this.id;
    }

    public int getImageId() {
        return this.imageId;
    }

    public void setImageId(int i) {
        this.imageId = i;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
