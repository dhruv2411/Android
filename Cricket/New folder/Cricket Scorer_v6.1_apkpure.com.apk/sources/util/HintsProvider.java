package util;

import android.content.Context;
import android.view.View;
import android.widget.AutoCompleteTextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Custom_Suggestions_Adapter;
import apps.shehryar.com.cricketscroingappPro.PlayersInputActivity;

public class HintsProvider {
    public static void setBatsmanHintsAdapter(final Context context, final AutoCompleteTextView autoCompleteTextView, final int i) {
        Custom_Suggestions_Adapter custom_Suggestions_Adapter;
        if (i == 99) {
            try {
                custom_Suggestions_Adapter = new Custom_Suggestions_Adapter(context, R.layout.custom_suggestions_layout, PlayersInputActivity.oppPlayers);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        } else {
            custom_Suggestions_Adapter = new Custom_Suggestions_Adapter(context, R.layout.custom_suggestions_layout, PlayersInputActivity.yourplayers);
        }
        autoCompleteTextView.setAdapter(custom_Suggestions_Adapter);
        autoCompleteTextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HintsProvider.showBattingHintsDropDown(context, autoCompleteTextView, i);
            }
        });
        autoCompleteTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    HintsProvider.showBattingHintsDropDown(context, autoCompleteTextView, i);
                }
            }
        });
    }

    public static void showBattingHintsDropDown(Context context, AutoCompleteTextView autoCompleteTextView, int i) {
        if (i == 99) {
            try {
                if (PlayersInputActivity.oppPlayers.size() > 0) {
                    autoCompleteTextView.showDropDown();
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        if (i != 99 && PlayersInputActivity.yourplayers.size() > 0) {
            autoCompleteTextView.showDropDown();
        }
    }

    public static void setBowlerHintsAdapter(final Context context, final AutoCompleteTextView autoCompleteTextView, final int i) {
        Custom_Suggestions_Adapter custom_Suggestions_Adapter;
        if (i == 99) {
            try {
                custom_Suggestions_Adapter = new Custom_Suggestions_Adapter(context, R.layout.custom_suggestions_layout, PlayersInputActivity.yourplayers);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        } else {
            custom_Suggestions_Adapter = new Custom_Suggestions_Adapter(context, R.layout.custom_suggestions_layout, PlayersInputActivity.oppPlayers);
        }
        autoCompleteTextView.setAdapter(custom_Suggestions_Adapter);
        autoCompleteTextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HintsProvider.showBowlingHintsDropDown(context, autoCompleteTextView, i);
            }
        });
        autoCompleteTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                HintsProvider.showBowlingHintsDropDown(context, autoCompleteTextView, i);
            }
        });
    }

    public static void showBowlingHintsDropDown(Context context, AutoCompleteTextView autoCompleteTextView, int i) {
        if (i == 99) {
            try {
                if (PlayersInputActivity.yourplayers.size() > 0) {
                    autoCompleteTextView.showDropDown();
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        if (i != 99 && PlayersInputActivity.oppPlayers.size() > 0) {
            autoCompleteTextView.showDropDown();
        }
    }
}
