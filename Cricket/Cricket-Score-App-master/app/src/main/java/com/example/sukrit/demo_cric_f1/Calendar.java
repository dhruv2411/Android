//package com.example.sukrit.demo_cric_f1;
//
///**
// * Created by Sukrit on 4/13/2017.
// */
//
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ArrayList;
//
//
//
//import android.content.Context;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//
//import org.json.JSONArray;
//
//
//public class Calendar extends AppCompatActivity {
//
//    Context ctx;
//    TextView team1, team2, date;
//    String url = "";
//    String url2 = "";
//    ArrayList<calendarModel> jsonArray1 = new ArrayList<>();
//    ArrayList<calendarModel> adapterArray = new ArrayList<>();
//    ArrayList<String> unique_id=new ArrayList<>();
//
//    Integer no_of_matches = 0;
//
//    RecyclerView upRecyclerView2;
//    CalendarRecyclerAdapter adapter1;
//    public static final String TAG = "MainActivity";
//    ProgressBar progressBar;
//    TextView textViewBar;
//    LinearLayout linearProgress2;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_calendar);
//
//        linearProgress2 = new LinearLayout(this);
//        upRecyclerView2= new RecyclerView(this);
//        upRecyclerView2 = (RecyclerView) findViewById(R.id.calModelList2);
//        team1 = (TextView) findViewById(R.id.team1);
//        team2 = (TextView) findViewById(R.id.team2);
//        date = (TextView) findViewById(R.id.date);
//
//        DownloadData downloadData = new DownloadData();
//        downloadData.execute(url);
//
//        linearProgress2 = (LinearLayout) findViewById(R.id.linearProgress2);
//
//        adapter1 = new CalendarRecyclerAdapter(adapterArray, Calendar.this);
//        upRecyclerView2.setLayoutManager(new LinearLayoutManager(Calendar.this));
//        upRecyclerView2.setAdapter(adapter1);
//    }
//    public class DownloadData extends AsyncTask<String, Void, ArrayList<calendarModel>> {
//        @Override
//        protected ArrayList<calendarModel> doInBackground(String... params) {
//            URL url1 = null;
//
////            Log.d("TAG", "doInBackground: ");
//            try {
//                url1 = new URL("http://cricapi.com/api/matchCalendar?apikey=CjCdXi1JuqVuqOPFGRGfG5RtGu23");
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            }
//
//            HttpURLConnection httpURLConnection1 = null;
//            try {
//                httpURLConnection1 = (HttpURLConnection) url1.openConnection();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            InputStream inputStream1 = null;
//            try {
//                inputStream1 = httpURLConnection1.getInputStream();
//
//                InputStreamReader isr = new InputStreamReader(inputStream1);
//                BufferedReader reader = new BufferedReader(isr);
//
//                StringBuilder sb = new StringBuilder();
//                String buffer;
//
//                while ((buffer = reader.readLine()) != null) {
//                    sb.append(buffer);
//                }
//                String json = sb.toString();
//                jsonArray1 = getJson(json);
////                Log.d("TAG", "doInBackground: " + jsonArray1.get(0).getTeam1());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return jsonArray1;
//
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            linearProgress2.setVisibility(View.VISIBLE);
//            upRecyclerView2.setVisibility(View.GONE);
//
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<calendarModel> jsonArray) {
//            super.onPostExecute(jsonArray);
//
//            int i;
//
//            linearProgress2.setVisibility(View.GONE);
//            upRecyclerView2.setVisibility(View.VISIBLE);
////            Log.d("TAG", "onPostExecute: " + jsonArray.size());
//            for (i = 0; i < jsonArray.size(); i++) {
//                adapterArray.add(jsonArray.get(i));
//                adapter1.notifyDataSetChanged();
//            }
//
//        }
//
//        public ArrayList<calendarModel> getJson(String json) {
//            ArrayList<calendarModel> modelJson = new ArrayList<>();
//
//            try {
//
//                JSONObject thisJSONObject = new JSONObject(json);
//                JSONArray thisJSONArray = thisJSONObject.getJSONArray("data");
//
//                for (int i = 0; i < thisJSONArray.length(); i++) {
//
//                    Log.d("TAG", "date: "+(thisJSONObject.getJSONArray("data").getJSONObject(i)).getString("date"));
//                    Log.d(TAG, "name: "+(thisJSONObject.getJSONArray("data").getJSONObject(i)).getString("name"));
//                    calendarModel model = new calendarModel(
//                            (thisJSONObject.getJSONArray("data").getJSONObject(i)).getString("date"),
//                            (thisJSONObject.getJSONArray("data").getJSONObject(i).getString("name"))
//                            );
//
////                    Log.d("SUKRIT", "Date: " + i + "" + (thisJSONArray.getJSONObject(i).getString("date")).substring(0,((thisJSONArray.getJSONObject(i).getString("date")).indexOf('T'))));
////                    Log.d("SUKRIT", "Team-1 :   " + i + "    " + thisJSONArray.getJSONObject(i).getString("team-1"));
////                    Log.d("SUKRIT", "Team-2 :   " + i + "    " + thisJSONArray.getJSONObject(i).getString("team-2"));
////                    Log.d("SUKRIT", "MatchStarted:   " + i + "    " + thisJSONArray.getJSONObject(i).getBoolean("matchStarted"));
//                    unique_id.add(thisJSONObject.getJSONArray("data").getJSONObject(i).getString("unique_id"));
//                        //Log.e("unique_id",unique_id.get(i).toString());
//
//                        modelJson.add(model);
//                }
////                Log.d("UNI", "UNIQUE_ID SIZE neeche: "+unique_id.size());
////                Log.d("INDEX", "LAST INDEX: "+(((thisJSONArray.getJSONObject(0).getString("date")).toString()).indexOf('T')));
//
//                for(int i=0;i<unique_id.size();i++)
//                {
//                    Log.d("UNI", "Unique ids::: "+unique_id.get(i));
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return modelJson;
//        }
//    }
//
//
//}

package com.example.sukrit.demo_cric_f1;

/**
 * Created by Sukrit on 4/13/2017.
 */

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;



import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;


public class Calendar extends AppCompatActivity {

    Context ctx;
    TextView team1, team2, date;
    String url = "";
    String url2 = "";
    ArrayList<calendarModel> jsonArray1 = new ArrayList<>();
    ArrayList<calendarModel> adapterArray = new ArrayList<>();
    ArrayList<String> unique_id=new ArrayList<>();

    Integer no_of_matches = 0;

    RecyclerView upRecyclerView2;
    CalendarRecyclerAdapter adapter1;
    public static final String TAG = "MainActivity";
    ProgressBar progressBar;
    TextView textViewBar;
    LinearLayout linearProgress2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        linearProgress2 = new LinearLayout(this);
        upRecyclerView2= new RecyclerView(this);
        upRecyclerView2 = (RecyclerView) findViewById(R.id.calModelList2);
//        team1 = (TextView) findViewById(R.id.team1);
//        team2 = (TextView) findViewById(R.id.team2);
//        date = (TextView) findViewById(R.id.date);

        DownloadData downloadData = new DownloadData();
        downloadData.execute(url);

        linearProgress2 = (LinearLayout) findViewById(R.id.linearProgress2);
        adapter1 = new CalendarRecyclerAdapter(adapterArray, Calendar.this);
        upRecyclerView2.setLayoutManager(new LinearLayoutManager(Calendar.this));
        upRecyclerView2.setAdapter(adapter1);
    }
    public class DownloadData extends AsyncTask<String, Void, ArrayList<calendarModel>> {
        @Override
        protected ArrayList<calendarModel> doInBackground(String... params) {
            URL url1 = null;

//            Log.d("TAG", "doInBackground: ");
            try {
                url1 = new URL("http://cricapi.com/api/matchCalendar?apikey=CjCdXi1JuqVuqOPFGRGfG5RtGu23");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            HttpURLConnection httpURLConnection1 = null;
            try {
                httpURLConnection1 = (HttpURLConnection) url1.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }

            InputStream inputStream1 = null;
            try {
                inputStream1 = httpURLConnection1.getInputStream();

                InputStreamReader isr = new InputStreamReader(inputStream1);
                BufferedReader reader = new BufferedReader(isr);

                StringBuilder sb = new StringBuilder();
                String buffer;

                while ((buffer = reader.readLine()) != null) {
                    sb.append(buffer);
                }
                String json = sb.toString();
                jsonArray1 = getJson(json);
//                Log.d("TAG", "doInBackground: " + jsonArray1.get(0).getTeam1());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonArray1;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            linearProgress2.setVisibility(View.VISIBLE);
            upRecyclerView2.setVisibility(View.GONE);

        }

        @Override
        protected void onPostExecute(ArrayList<calendarModel> jsonArray) {
            super.onPostExecute(jsonArray);

            int i;

            linearProgress2.setVisibility(View.GONE);
            upRecyclerView2.setVisibility(View.VISIBLE);
//            Log.d("TAG", "onPostExecute: " + jsonArray.size());
            for (i = 0; i < jsonArray.size(); i++) {
                adapterArray.add(jsonArray.get(i));
                adapter1.notifyDataSetChanged();
            }

        }

        public ArrayList<calendarModel> getJson(String json) {
            ArrayList<calendarModel> modelJson = new ArrayList<>();

            try {

                JSONObject thisJSONObject = new JSONObject(json);
                JSONArray thisJSONArray = thisJSONObject.getJSONArray("data");

                for (int i = 0; i < thisJSONArray.length(); i++) {

                    Log.d("TAG", "date: "+(thisJSONObject.getJSONArray("data").getJSONObject(i)).getString("date"));
                    Log.d(TAG, "name: "+(thisJSONObject.getJSONArray("data").getJSONObject(i)).getString("name"));
                    calendarModel model = new calendarModel(
                            (thisJSONObject.getJSONArray("data").getJSONObject(i)).getString("date"),
                            (thisJSONObject.getJSONArray("data").getJSONObject(i).getString("name"))
                    );

//                    Log.d("SUKRIT", "Date: " + i + "" + (thisJSONArray.getJSONObject(i).getString("date")).substring(0,((thisJSONArray.getJSONObject(i).getString("date")).indexOf('T'))));
//                    Log.d("SUKRIT", "Team-1 :   " + i + "    " + thisJSONArray.getJSONObject(i).getString("team-1"));
//                    Log.d("SUKRIT", "Team-2 :   " + i + "    " + thisJSONArray.getJSONObject(i).getString("team-2"));
//                    Log.d("SUKRIT", "MatchStarted:   " + i + "    " + thisJSONArray.getJSONObject(i).getBoolean("matchStarted"));
                    unique_id.add(thisJSONObject.getJSONArray("data").getJSONObject(i).getString("unique_id"));
                    //Log.e("unique_id",unique_id.get(i).toString());

                    modelJson.add(model);
                }
//                Log.d("UNI", "UNIQUE_ID SIZE neeche: "+unique_id.size());
//                Log.d("INDEX", "LAST INDEX: "+(((thisJSONArray.getJSONObject(0).getString("date")).toString()).indexOf('T')));

                for(int i=0;i<unique_id.size();i++)
                {
                    Log.d("UNI", "Unique ids::: "+unique_id.get(i));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return modelJson;
        }
    }


}
