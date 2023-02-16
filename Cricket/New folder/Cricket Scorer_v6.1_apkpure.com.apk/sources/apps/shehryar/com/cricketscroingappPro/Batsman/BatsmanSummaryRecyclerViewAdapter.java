package apps.shehryar.com.cricketscroingappPro.Batsman;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Player.PlayerSummaryViewHolder;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.Formatter;
import com.itextpdf.text.pdf.PdfBoolean;
import java.util.ArrayList;

public class BatsmanSummaryRecyclerViewAdapter extends RecyclerView.Adapter<PlayerSummaryViewHolder> {
    ArrayList<Batsman> batsmen;

    public BatsmanSummaryRecyclerViewAdapter(ArrayList<Batsman> arrayList) {
        this.batsmen = arrayList;
    }

    public PlayerSummaryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new PlayerSummaryViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.player_summary_adapter_layout, viewGroup, false));
    }

    public void onBindViewHolder(PlayerSummaryViewHolder playerSummaryViewHolder, int i) {
        if (this.batsmen.get(i).isOut() == null || this.batsmen.get(i).isOut().equals(PdfBoolean.FALSE)) {
            try {
                String formattedName = Formatter.getFormattedName(this.batsmen.get(i).getName());
                playerSummaryViewHolder.setData(formattedName, this.batsmen.get(i).getTotalScore() + Formatter.getSuperScriptStaric() + "(" + this.batsmen.get(i).getBallsfaced() + ")", i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                String formattedName2 = Formatter.getFormattedName(this.batsmen.get(i).getName());
                playerSummaryViewHolder.setData(formattedName2, this.batsmen.get(i).getTotalScore() + "(" + this.batsmen.get(i).getBallsfaced() + ")", i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public int getItemCount() {
        return this.batsmen.size();
    }
}
