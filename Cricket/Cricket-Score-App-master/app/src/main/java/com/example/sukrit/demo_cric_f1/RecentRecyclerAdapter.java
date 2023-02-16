////package com.example.sukrit.demo_cric_f1;
////
////import android.content.Context;
////import android.content.Intent;
////import android.support.v4.app.FragmentManager;
////import android.support.v4.app.FragmentTransaction;
////import android.support.v7.widget.RecyclerView;
////import android.util.Log;
////import android.view.LayoutInflater;
////import android.view.View;
////import android.view.ViewGroup;
////import android.widget.ImageView;
////import android.widget.TextView;
////
////import java.util.ArrayList;
////
/////**
//// * Created by Sukrit on 3/23/2017.
//// */
////
////public class RecentRecyclerAdapter extends RecyclerView.Adapter<RecentRecyclerAdapter.DetailsItemHolder> {
////
////    ArrayList<recentModel> details;
////    Context context;
////    int num;
////    int img1;
////    int img2;
////
////    public RecentRecyclerAdapter(ArrayList<recentModel> details, Context context) {
////        this.details = details;
////        this.context = context;
////    }
////
////    @Override
////    public DetailsItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
////        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
////
////        int layoutType;
////        layoutType = R.layout.list_item_match_detail_recent;
////
////        View viewItem = li.inflate(layoutType, parent, false);
////        return new DetailsItemHolder(viewItem);
////    }
////
////    @Override
////    public void onBindViewHolder(DetailsItemHolder holder, int position) {
////        final recentModel thisDetail = details.get(position);
////
////        holder.testView.setOnClickListener(new View.OnClickListener() {
////
////            @Override
////            public void onClick(View v) {
////                Intent i=new Intent(context,TestActivity.class);
////                i.putExtra("team1",thisDetail.getTeam1());
////                i.putExtra("team2",thisDetail.getTeam2());
////                Log.d("TEAM", "onClick: "+thisDetail.getTeam1());
////                Log.d("TEAM", "onClick: "+thisDetail.getTeam2());
////                context.startActivity(i);
////
////            }
////        });
////
////        holder.team1.setText(thisDetail.getTeam1());
////        holder.team2.setText(thisDetail.getTeam2());
////        holder.innings_requirement.setText(thisDetail.getInnings_requirement());
////
////    }
////
////    @Override
////    public int getItemCount() {
////        return details.size();
////    }
////
////    @Override
////    public int getItemViewType(int position) {
////        //return super.getItemViewType(position);
////        return 0;
////
////    }
////
////    class DetailsItemHolder extends RecyclerView.ViewHolder {
////
////        TextView team1,team2,date;
////        ImageView img1,img2;
////        TextView innings_requirement;
////        View testView;
////
////        public DetailsItemHolder(View itemView) {
////            super(itemView);
//////            linearLayout = (LinearLayout) itemView.findViewById(R.id.upcoming1);
////            team1 = (TextView) itemView.findViewById(R.id.team1);
////            team2 = (TextView) itemView.findViewById(R.id.team2);
////            img1=(ImageView)itemView.findViewById(R.id.img1);
////            img2=(ImageView)itemView.findViewById(R.id.img2);
////            innings_requirement=(TextView)itemView.findViewById(R.id.innings_requirement);
////            testView=itemView;
////
////        }
////    }
////}
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

public class RecentRecyclerAdapter extends RecyclerView.Adapter<RecentRecyclerAdapter.DetailsItemHolder> {

    ArrayList<recentModel> details;
    Context context;


    public RecentRecyclerAdapter(ArrayList<recentModel> details, Context context) {
        this.details = details;
        this.context = context;
    }

    @Override
    public DetailsItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        int layoutType;
        layoutType = R.layout.list_item_match_detail_recent;

        View viewItem = li.inflate(layoutType, parent, false);
        return new DetailsItemHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(DetailsItemHolder holder, int position) {
        final recentModel thisDetail = details.get(position);

        holder.testView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,TestActivity4.class);
                if(thisDetail.getScore()!=null) {
                    i.putExtra("team1", thisDetail.getTeam1());
                    i.putExtra("team2", thisDetail.getTeam2());
                    i.putExtra("score", thisDetail.getScore());
                }
//                Log.d("TEAM", "onClick: "+thisDetail.getTeam1());
//                Log.d("TEAM", "onClick: "+thisDetail.getTeam2());
                context.startActivity(i);

            }
        });

        holder.team1.setText(thisDetail.getTeam1());
        holder.team2.setText(thisDetail.getTeam2());
        holder.innings_requirement.setText(thisDetail.getInnings_requirement());

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

        TextView team1,team2,date;
        ImageView img1,img2;
        TextView innings_requirement;
        View testView;
        TextView score;

        public DetailsItemHolder(View itemView) {
            super(itemView);
            team1 = (TextView) itemView.findViewById(R.id.team1);
            team2 = (TextView) itemView.findViewById(R.id.team2);
            img1=(ImageView)itemView.findViewById(R.id.img1);
            img2=(ImageView)itemView.findViewById(R.id.img2);
            innings_requirement=(TextView)itemView.findViewById(R.id.innings_requirement);
            testView=itemView;

        }
    }
}

//RecentRecyclerAdapter
//
//package com.example.sukrit.demo_cric_f1;
//
//import android.content.Context;
//import android.content.Intent;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
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
//public class RecentRecyclerAdapter extends RecyclerView.Adapter<RecentRecyclerAdapter.DetailsItemHolder> {
//
//    ArrayList<recentModel> details;
//    Context context;
//    int num;
//    int img1;
//    int img2;
//
//    public RecentRecyclerAdapter(ArrayList<recentModel> details, Context context) {
//        this.details = details;
//        this.context = context;
//    }
//
//    @Override
//    public DetailsItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        int layoutType;
//        layoutType = R.layout.list_item_match_detail_recent;
//
//        View viewItem = li.inflate(layoutType, parent, false);
//        return new DetailsItemHolder(viewItem);
//    }
//
//    @Override
//    public void onBindViewHolder(DetailsItemHolder holder, int position) {
//        final recentModel thisDetail = details.get(position);
//
//        holder.testView.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(context, TestActivity.class);
//                i.putExtra("team1", thisDetail.getTeam1());
//                i.putExtra("team2", thisDetail.getTeam2());
//                Log.d("TEAM", "onClick: " + thisDetail.getTeam1());
//                Log.d("TEAM", "onClick: " + thisDetail.getTeam2());
//                context.startActivity(i);
//
//            }
//        });
//
//        holder.team1.setText(thisDetail.getTeam1());
//        holder.team2.setText(thisDetail.getTeam2());
//        holder.innings_requirement.setText(thisDetail.getInnings_requirement());
//
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
//        TextView team1, team2, date;
//        ImageView img1, img2;
//        TextView innings_requirement;
//        View testView;
//
//        public DetailsItemHolder(View itemView) {
//            super(itemView);
////            linearLayout = (LinearLayout) itemView.findViewById(R.id.upcoming1);
//            team1 = (TextView) itemView.findViewById(R.id.team1);
//            team2 = (TextView) itemView.findViewById(R.id.team2);
//            img1 = (ImageView) itemView.findViewById(R.id.img1);
//            img2 = (ImageView) itemView.findViewById(R.id.img2);
//            innings_requirement = (TextView) itemView.findViewById(R.id.innings_requirement);
//            testView = itemView;
//
//        }
//    }
//}
