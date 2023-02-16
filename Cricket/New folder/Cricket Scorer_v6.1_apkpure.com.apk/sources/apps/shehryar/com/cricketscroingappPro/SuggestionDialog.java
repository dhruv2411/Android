package apps.shehryar.com.cricketscroingappPro;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import java.util.ArrayList;

public class SuggestionDialog extends MyDialogFragment {
    Context context;
    EditText editText;
    SuggestionsClickCallBack listener;
    ArrayList<String> names;
    ListView suggestionsListView;
    Button typeYourOwn;

    public interface SuggestionsClickCallBack {
        void onSuggestionClicked(EditText editText, String str);

        void onTypeYourOwnClick(EditText editText) throws Exception;
    }

    public SuggestionDialog(Context context2, EditText editText2, ArrayList<String> arrayList, SuggestionsClickCallBack suggestionsClickCallBack) {
        this.editText = editText2;
        this.names = arrayList;
        this.context = context2;
        this.listener = suggestionsClickCallBack;
    }

    public SuggestionDialog() {
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        boolean z;
        boolean z2;
        View inflate = layoutInflater.inflate(R.layout.suggestion_dialog, viewGroup, false);
        this.typeYourOwn = (Button) inflate.findViewById(R.id.b_write_your_own);
        this.suggestionsListView = (ListView) inflate.findViewById(R.id.suggestionListView);
        this.suggestionsListView.setFooterDividersEnabled(true);
        try {
            this.suggestionsListView.setAdapter(new Custom_Suggestions_Adapter(this.context, R.layout.custom_suggestions_layout, this.names));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (getArguments() != null) {
            z = getArguments().getBoolean("toss", false);
            z2 = getArguments().getBoolean("matchType", false);
        } else {
            z = false;
            z2 = false;
        }
        if (z) {
            this.typeYourOwn.setVisibility(8);
            ((TextView) inflate.findViewById(R.id.tv_suggestion_title)).setText("TOSS WON BY");
        } else if (z2) {
            this.typeYourOwn.setVisibility(8);
            ((TextView) inflate.findViewById(R.id.tv_suggestion_title)).setText("SELECT MATCH TYPE");
        } else {
            this.typeYourOwn.setVisibility(0);
        }
        this.suggestionsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                SuggestionDialog.this.listener.onSuggestionClicked(SuggestionDialog.this.editText, SuggestionDialog.this.names.get(i));
                try {
                    SuggestionDialog.this.dismiss();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
            }
        });
        this.typeYourOwn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    SuggestionDialog.this.listener.onTypeYourOwnClick(SuggestionDialog.this.editText);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                SuggestionDialog.this.dismiss();
            }
        });
        return inflate;
    }
}
