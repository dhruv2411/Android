package apps.shehryar.com.cricketscroingappPro.Model;

import java.io.Serializable;

public interface SuggestionListItem extends Serializable {
    String getImage();

    SuggestionListItem getSuggestionListItem();

    String getSuggestionText();
}
