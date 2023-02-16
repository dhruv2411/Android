//
//package com.example.sukrit.demo_cric_f1;
//
//import android.content.Context;
//import android.content.Intent;
//import android.media.Image;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
///**
// * Created by Sukrit on 3/23/2017.
// */
//
//public class CalendarRecyclerAdapter extends RecyclerView.Adapter<CalendarRecyclerAdapter.DetailsItemHolder> {
//
//    ArrayList<calendarModel> details;
//    Context context;
//
//    public CalendarRecyclerAdapter(ArrayList<calendarModel> details, Context context) {
//        this.details = details;
//        this.context = context;
//    }
//
//    @Override
//    public DetailsItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        int layoutType;
//        layoutType = R.layout.list_item_match_detail_calendar;
//
//        View viewItem = li.inflate(layoutType, parent, false);
//        return new DetailsItemHolder(viewItem);
//    }
//
//    @Override
//    public void onBindViewHolder(DetailsItemHolder holder, int position) {
//        final calendarModel thisDetail = details.get(position);
//
//
//
//       holder.date_cal.setText(thisDetail.getDate());
//       holder.name_cal.setText(thisDetail.getName());
//    }
//
//    @Override
//    public int getItemCount() {
//        return details.size();
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        //return super.getItemViewType(position);
//        return 0;
//
//    }
//
//    class DetailsItemHolder extends RecyclerView.ViewHolder {
//
//        View testView;
//        TextView date_cal;
//        TextView name_cal;
//        View itemview;
//
//        public DetailsItemHolder(View itemView) {
//            super(itemView);
//             date_cal=(TextView)itemView.findViewById(R.id.date_calendar);
//             name_cal=(TextView)itemView.findViewById(R.id.name_calendar);
//             itemview=itemView;
//            testView=itemView;
//
//
//
//        }
//    }
//}


package com.example.sukrit.demo_cric_f1;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sukrit on 3/23/2017.
 */

public class CalendarRecyclerAdapter extends RecyclerView.Adapter<CalendarRecyclerAdapter.DetailsItemHolder> {

    ArrayList<calendarModel> details;
    Context context;

    public CalendarRecyclerAdapter(ArrayList<calendarModel> details, Context context) {
        this.details = details;
        this.context = context;
    }

    @Override
    public DetailsItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        int layoutType;
        layoutType = R.layout.list_item_match_detail_calendar;

        View viewItem = li.inflate(layoutType, parent, false);
        return new DetailsItemHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(DetailsItemHolder holder, int position) {
        final calendarModel thisDetail = details.get(position);

        holder.itemview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,TestActivity3.class);
                i.putExtra("name","The Match:"+thisDetail.getName()+" is scheduled on:");
                i.putExtra("date",thisDetail.getDate());
                context.startActivity(i);
            }
        });
        holder.date_cal.setText(thisDetail.getDate());
        holder.name_cal.setText(thisDetail.getName());
    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    @Override
    public int getItemViewType(int position) {
        //return super.getItemViewType(position);
        return 0;

    }

    class DetailsItemHolder extends RecyclerView.ViewHolder {

        View testView;
        TextView date_cal;
        TextView name_cal;
        View itemview;

        public DetailsItemHolder(View itemView) {
            super(itemView);
            date_cal=(TextView)itemView.findViewById(R.id.date_calendar);
            name_cal=(TextView)itemView.findViewById(R.id.name_calendar);
            itemview=itemView;
        }
    }
}
