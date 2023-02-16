package apps.shehryar.com.cricketscroingappPro.Model;

public class SharedData {
    private static final SharedData ourInstance = new SharedData();
    int selectedMatchType = 3;

    public static SharedData getInstance() {
        return ourInstance;
    }

    private SharedData() {
    }

    public static SharedData getOurInstance() {
        return ourInstance;
    }

    public int getSelectedMatchType() {
        return this.selectedMatchType;
    }

    public void setSelectedMatchType(int i) {
        this.selectedMatchType = i;
    }
}
