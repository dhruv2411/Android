package apps.shehryar.com.cricketscroingappPro.Adapters;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
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
import apps.shehryar.com.cricketscroingappPro.Model.NavigationItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.util.ArrayList;

public class AdapterListItemNavigation extends RecyclerView.Adapter<MyViewHolder> {
    ArrayList<NavigationItem> navigationItems;
    OnListItemClickListener onListItemClickListener;

    public class MyViewHolder_ViewBinding implements Unbinder {
        private MyViewHolder target;

        @UiThread
        public MyViewHolder_ViewBinding(MyViewHolder myViewHolder, View view) {
            this.target = myViewHolder;
            myViewHolder.tvItem = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_item, "field 'tvItem'", TextView.class);
            myViewHolder.mainLayout = (ConstraintLayout) Utils.findRequiredViewAsType(view, R.id.main_layout, "field 'mainLayout'", ConstraintLayout.class);
            myViewHolder.ivIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_icon, "field 'ivIcon'", ImageView.class);
        }

        @CallSuper
        public void unbind() {
            MyViewHolder myViewHolder = this.target;
            if (myViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            myViewHolder.tvItem = null;
            myViewHolder.mainLayout = null;
            myViewHolder.ivIcon = null;
        }
    }

    public AdapterListItemNavigation(ArrayList<NavigationItem> arrayList, OnListItemClickListener onListItemClickListener2) {
        this.navigationItems = arrayList;
        this.onListItemClickListener = onListItemClickListener2;
    }

    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_navigation_drawer, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.tvItem.setText(this.navigationItems.get(i).getTitle());
        myViewHolder.ivIcon.setImageResource(this.navigationItems.get(i).getImageId());
        myViewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AdapterListItemNavigation.this.onListItemClickListener.onListItemClicked(AdapterListItemNavigation.this.navigationItems.get(i).getId());
            }
        });
    }

    public int getItemCount() {
        return this.navigationItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(2131755607)
        ImageView ivIcon;
        @BindView(2131755300)
        ConstraintLayout mainLayout;
        @BindView(2131755606)
        TextView tvItem;

        public MyViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }
}
