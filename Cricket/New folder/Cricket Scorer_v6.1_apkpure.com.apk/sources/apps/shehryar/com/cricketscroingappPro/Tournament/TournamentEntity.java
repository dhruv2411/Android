package apps.shehryar.com.cricketscroingappPro.Tournament;

public interface TournamentEntity {
    String getHeading();

    String getImgUrl();

    String getName();

    String getStats(int i);

    int getStatsType();

    void setHeading(String str);

    void setStatsType(int i);
}
