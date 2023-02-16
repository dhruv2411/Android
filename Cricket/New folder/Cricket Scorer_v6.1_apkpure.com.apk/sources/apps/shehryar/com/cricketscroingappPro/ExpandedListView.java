package apps.shehryar.com.cricketscroingappPro;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ListView;

public class ExpandedListView extends ListView {
    private int old_count = 0;
    private ViewGroup.LayoutParams params;

    public ExpandedListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (getCount() != this.old_count) {
            this.old_count = getCount();
            this.params = getLayoutParams();
            ViewGroup.LayoutParams layoutParams = this.params;
            int count = getCount();
            int i = 0;
            if (this.old_count > 0) {
                i = getChildAt(0).getHeight();
            }
            layoutParams.height = count * i;
            setLayoutParams(this.params);
        }
        super.onDraw(canvas);
    }
}
