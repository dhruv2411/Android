package apps.shehryar.com.cricketscroingappPro.Interfaces;

import java.io.Serializable;

public interface AfterOverDialogButtonsListener extends Serializable {
    void onNextOverTapped(String str) throws Exception;

    void onUndoPreviousBallTapped() throws Exception;

    void onViewOversTapped() throws Exception;
}
