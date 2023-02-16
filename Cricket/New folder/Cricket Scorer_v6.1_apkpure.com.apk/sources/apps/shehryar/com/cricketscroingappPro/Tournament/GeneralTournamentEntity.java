package apps.shehryar.com.cricketscroingappPro.Tournament;

public class GeneralTournamentEntity implements TournamentEntity {
    String heading;
    String imgUrl;
    String name;
    String stats;
    int statsType;

    public GeneralTournamentEntity(String str, String str2, int i, String str3, String str4) {
        this.name = str;
        this.stats = str2;
        this.statsType = i;
        this.heading = str3;
        this.imgUrl = str4;
    }

    public String getName() {
        return this.name;
    }

    public String getStats(int i) {
        return this.stats;
    }

    public void setStatsType(int i) {
        this.statsType = i;
    }

    public int getStatsType() {
        return this.statsType;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setHeading(String str) {
        this.heading = str;
    }

    public String getHeading() {
        return this.heading;
    }
}
