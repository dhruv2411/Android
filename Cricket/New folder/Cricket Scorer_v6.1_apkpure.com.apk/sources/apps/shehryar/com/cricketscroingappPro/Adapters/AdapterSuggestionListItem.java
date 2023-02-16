package apps.shehryar.com.cricketscroingappPro.Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Listeners.OnListItemClickListener;
import apps.shehryar.com.cricketscroingappPro.Model.SuggestionListItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.util.ArrayList;

public class AdapterSuggestionListItem extends RecyclerView.Adapter<SuggestionViewHolder> {
    Context context;
    OnListItemClickListener onListItemClickListener;
    ArrayList<SuggestionListItem> suggestionListItems;

    public class SuggestionViewHolder_ViewBinding implements Unbinder {
        private SuggestionViewHolder target;

        @UiThread
        public SuggestionViewHolder_ViewBinding(SuggestionViewHolder suggestionViewHolder, View view) {
            this.target = suggestionViewHolder;
            suggestionViewHolder.tvSuggestion = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_suggestion_text, "field 'tvSuggestion'", TextView.class);
            suggestionViewHolder.ivSuggestionImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_suggestion_image, "field 'ivSuggestionImage'", ImageView.class);
            suggestionViewHolder.mainLayout = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.main_layout, "field 'mainLayout'", ConstraintLayout.class);
        }

        @CallSuper
        public void unbind() {
            SuggestionViewHolder suggestionViewHolder = this.target;
            if (suggestionViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            suggestionViewHolder.tvSuggestion = null;
            suggestionViewHolder.ivSuggestionImage = null;
            suggestionViewHolder.mainLayout = null;
        }
    }

    public AdapterSuggestionListItem(Context context2, ArrayList<SuggestionListItem> arrayList, OnListItemClickListener onListItemClickListener2) {
        this.context = context2;
        this.suggestionListItems = arrayList;
        this.onListItemClickListener = onListItemClickListener2;
    }

    public SuggestionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SuggestionViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_custom_suggestion, viewGroup, false));
    }

    public void onBindViewHolder(SuggestionViewHolder suggestionViewHolder, final int i) {
        SuggestionListItem suggestionListItem = this.suggestionListItems.get(i);
        suggestionViewHolder.tvSuggestion.setText(suggestionListItem.getSuggestionText());
        if (suggestionListItem.getImage() != null) {
            suggestionViewHolder.ivSuggestionImage.setVisibility(0);
            suggestionViewHolder.ivSuggestionImage.setImageURI(Uri.parse(suggestionListItem.getImage()));
        } else {
            suggestionViewHolder.ivSuggestionImage.setVisibility(8);
        }
        suggestionViewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AdapterSuggestionListItem.this.onListItemClickListener.onListItemClicked(i);
            }
        });
    }

    public int getItemCount() {
        return this.suggestionListItems.size();
    }

    public class SuggestionViewHolder extends RecyclerView.ViewHolder {
        @BindView(2131755604)
        ImageView ivSuggestionImage;
        @BindView(2131755300)
        ConstraintLayout mainLayout;
        @BindView(2131755605)
        TextView tvSuggestion;

        public SuggestionViewHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }
}
