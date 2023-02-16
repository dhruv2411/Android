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
//import android.widget.Button;
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
//import android.os.AsyncTask;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.w3c.dom.Text;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.lang.reflect.Array;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLConnection;
//import java.nio.Buffer;
//import java.util.ArrayList;
//
//
//public class Upcoming extends AllMatchesActivity {
//
//
//    TextView team1, team2, date;
//    String url = "";
//    String url2 = "";
//    ArrayList<matchesModel> jsonArray1 = new ArrayList<>();
//    ArrayList<matchesModel> adapterArray = new ArrayList<>();
//    ArrayList<Long> unique_id=new ArrayList<>();
//
//    Integer no_of_matches = 0;
//
//    RecyclerView upRecyclerView;
//    UpcomingRecyclerAdapter adapter1;
//    public static final String TAG = "MainActivity";
//    Button btnRefresh1;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.upcoming_xml);
//
//        team1 = (TextView) findViewById(R.id.team1);
//        team2 = (TextView) findViewById(R.id.team2);
//        date = (TextView) findViewById(R.id.date);
//        btnRefresh1=(Button)findViewById(R.id.btnRefresh1);
//
//
//
//        btnRefresh1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DownloadData downloadData = new DownloadData();
//                downloadData.execute(url);
//
//                upRecyclerView = (RecyclerView) findViewById(R.id.rvModelList);
//
//                adapter1 = new UpcomingRecyclerAdapter(adapterArray,Upcoming.this);
//                upRecyclerView.setLayoutManager(new LinearLayoutManager(Upcoming.this));
//                upRecyclerView.setAdapter(adapter1);
//            }
//        });
//
//
//    }
//
//
//    public class DownloadData extends AsyncTask<String, Void, ArrayList<matchesModel>> {
//        @Override
//        protected ArrayList<matchesModel> doInBackground(String... params) {
//            URL url1 = null;
//
//            Log.d("TAG", "doInBackground: ");
//            try {
//                url1 = new URL("http://cricapi.com/api/matches?apikey=CjCdXi1JuqVuqOPFGRGfG5RtGu23");
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            }
//
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
//                Log.d("TAG", "doInBackground: " + jsonArray1.get(0).getTeam1());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return jsonArray1;
//
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<matchesModel> jsonArray) {
//            super.onPostExecute(jsonArray);
//            int i;
//            no_of_matches = jsonArray.size();
//            Log.d("TAG", "onPostExecute: " + jsonArray.size());
//            for (i = 0; i < jsonArray.size(); i++) {
//                adapterArray.add(jsonArray.get(i));
//                adapter1.notifyDataSetChanged();
//            }
//        }
//
//        public ArrayList<matchesModel> getJson(String json) {
//            ArrayList<matchesModel> modelJson = new ArrayList<>();
//
//            try {
//                JSONObject thisJSONObject = new JSONObject(json);
//                JSONArray thisJSONArray = thisJSONObject.optJSONArray("matches");
//
//                for (int i = 0; i < thisJSONArray.length(); i++) {
//                    matchesModel model = new matchesModel(
//                            thisJSONArray.getJSONObject(i).getString("date"),
//                            thisJSONArray.getJSONObject(i).getString("team-1"),
//                            thisJSONArray.getJSONObject(i).getString("team-2"),
//                            thisJSONArray.getJSONObject(i).getBoolean("matchStarted"),
//                            thisJSONArray.getJSONObject(i).getLong("unique_id"));
//
//                    Log.d("SUK", "Date: " + i + "" + thisJSONArray.getJSONObject(i).getString("date"));
//                    Log.d("SUK", "Team-1 :   " + i + "    " + thisJSONArray.getJSONObject(i).getString("team-1"));
//                    Log.d("SUK", "Team-2 :   " + i + "    " + thisJSONArray.getJSONObject(i).getString("team-2"));
//                    Log.d("SUK", "MatchStarted:   " + i + "    " + thisJSONArray.getJSONObject(i).getBoolean("matchStarted"));
//                    if(thisJSONArray.getJSONObject(i).getBoolean("matchStarted")==true)
//                    {unique_id.add(thisJSONArray.getJSONObject(i).getLong("unique_id"));}
//                    //Log.e("unique_id",unique_id.get(i).toString());
//
//                    modelJson.add(model);
//                }
//                Log.d("UNI", "UNIQUE_ID SIZE neeche: "+unique_id.size());
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
//public class Upcoming extends AppCompatActivity {
//
//    Context ctx;
//    TextView team1, team2, date;
//    String url = "";
//    String url2 = "";
//    ArrayList<matchesModel> jsonArray1 = new ArrayList<>();
//    ArrayList<matchesModel> adapterArray = new ArrayList<>();
//    ArrayList<Long> unique_id=new ArrayList<>();
//
//    Integer no_of_matches = 0;
//
//    RecyclerView upRecyclerView;
//    UpcomingRecyclerAdapter adapter1;
//    public static final String TAG = "MainActivity";
//    ProgressBar progressBar;
//    TextView textViewBar;
//    LinearLayout linearProgress;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.upcoming_xml);
//
//        ctx = getApplicationContext();
//        linearProgress = new LinearLayout(this);
//        upRecyclerView = new RecyclerView(this);
//        team1 = (TextView) findViewById(R.id.team1);
//        team2 = (TextView) findViewById(R.id.team2);
//        date = (TextView) findViewById(R.id.date);
//
//        DownloadData downloadData = new DownloadData();
//        downloadData.execute(url);
//
//        upRecyclerView = (RecyclerView) findViewById(R.id.rvModelList);
//        linearProgress = (LinearLayout) findViewById(R.id.linearProgress);
//
//        adapter1 = new UpcomingRecyclerAdapter(adapterArray, Upcoming.this);
//        upRecyclerView.setLayoutManager(new LinearLayoutManager(Upcoming.this));
//        upRecyclerView.setAdapter(adapter1);
//    }
//        public class DownloadData extends AsyncTask<String, Void, ArrayList<matchesModel>> {
//        @Override
//        protected ArrayList<matchesModel> doInBackground(String... params) {
//            URL url1 = null;
//
////            Log.d("TAG", "doInBackground: ");
//            try {
//                url1 = new URL("http://cricapi.com/api/matches?apikey=CjCdXi1JuqVuqOPFGRGfG5RtGu23");
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            }
//
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
//            linearProgress.setVisibility(View.VISIBLE);
//            upRecyclerView.setVisibility(View.GONE);
//
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<matchesModel> jsonArray) {
//            super.onPostExecute(jsonArray);
//
//            int i;
//
//            linearProgress.setVisibility(View.GONE);
//            upRecyclerView.setVisibility(View.VISIBLE);
////            Log.d("TAG", "onPostExecute: " + jsonArray.size());
//            for (i = 0; i < jsonArray.size(); i++) {
//                adapterArray.add(jsonArray.get(i));
//                adapter1.notifyDataSetChanged();
//            }
//
//        }
//
//        public ArrayList<matchesModel> getJson(String json) {
//            ArrayList<matchesModel> modelJson = new ArrayList<>();
//
//            try {
//                JSONObject thisJSONObject = new JSONObject(json);
//                JSONArray thisJSONArray = thisJSONObject.getJSONArray("matches");
//
//                for (int i = 0; i < thisJSONArray.length(); i++) {
//
//
//                    matchesModel model = new matchesModel(
//                            (thisJSONArray.getJSONObject(i).getString("date")).substring(2,((thisJSONArray.getJSONObject(i).getString("date")).indexOf('T'))),
//                            thisJSONArray.getJSONObject(i).getString("team-1"),
//                            thisJSONArray.getJSONObject(i).getString("team-2"),
//                            thisJSONArray.getJSONObject(i).getBoolean("matchStarted"),
//                            thisJSONArray.getJSONObject(i).getLong("unique_id"));
//
////                    Log.d("SUKRIT", "Date: " + i + "" + (thisJSONArray.getJSONObject(i).getString("date")).substring(0,((thisJSONArray.getJSONObject(i).getString("date")).indexOf('T'))));
////                    Log.d("SUKRIT", "Team-1 :   " + i + "    " + thisJSONArray.getJSONObject(i).getString("team-1"));
////                    Log.d("SUKRIT", "Team-2 :   " + i + "    " + thisJSONArray.getJSONObject(i).getString("team-2"));
////                    Log.d("SUKRIT", "MatchStarted:   " + i + "    " + thisJSONArray.getJSONObject(i).getBoolean("matchStarted"));
//                    if(thisJSONArray.getJSONObject(i).getBoolean("matchStarted")==false)
//                    {unique_id.add(thisJSONArray.getJSONObject(i).getLong("unique_id"));
//                    //Log.e("unique_id",unique_id.get(i).toString());
//
//                    modelJson.add(model);}
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


public class Upcoming extends AppCompatActivity {

    Context ctx;
    TextView team1, team2, date;
    String url = "";
    String url2 = "";
    ArrayList<matchesModel> jsonArray1 = new ArrayList<>();
    ArrayList<matchesModel> adapterArray = new ArrayList<>();
    ArrayList<Long> unique_id=new ArrayList<>();

    Integer no_of_matches = 0;

    RecyclerView upRecyclerView;
    UpcomingRecyclerAdapter adapter1;
    public static final String TAG = "MainActivity";
    ProgressBar progressBar;
    TextView textViewBar;
    LinearLayout linearProgress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upcoming_xml);

        ctx = getApplicationContext();
        linearProgress = new LinearLayout(this);
        upRecyclerView = new RecyclerView(this);
        team1 = (TextView) findViewById(R.id.team1);
        team2 = (TextView) findViewById(R.id.team2);
        date = (TextView) findViewById(R.id.date);

        DownloadData downloadData = new DownloadData();
        downloadData.execute(url);

        upRecyclerView = (RecyclerView) findViewById(R.id.rvModelList);
        linearProgress = (LinearLayout) findViewById(R.id.linearProgress);

        adapter1 = new UpcomingRecyclerAdapter(adapterArray, Upcoming.this);
        upRecyclerView.setLayoutManager(new LinearLayoutManager(Upcoming.this));
        upRecyclerView.setAdapter(adapter1);
    }
    public class DownloadData extends AsyncTask<String, Void, ArrayList<matchesModel>> {
        @Override
        protected ArrayList<matchesModel> doInBackground(String... params) {
            URL url1 = null;

//            Log.d("TAG", "doInBackground: ");
            try {
                url1 = new URL("http://cricapi.com/api/matches?apikey=CjCdXi1JuqVuqOPFGRGfG5RtGu23");
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
            linearProgress.setVisibility(View.VISIBLE);
            upRecyclerView.setVisibility(View.GONE);

        }

        @Override
        protected void onPostExecute(ArrayList<matchesModel> jsonArray) {
            super.onPostExecute(jsonArray);

            int i;

            linearProgress.setVisibility(View.GONE);
            upRecyclerView.setVisibility(View.VISIBLE);
//            Log.d("TAG", "onPostExecute: " + jsonArray.size());
            for (i = 0; i < jsonArray.size(); i++) {
                adapterArray.add(jsonArray.get(i));
                adapter1.notifyDataSetChanged();
            }

        }

        public ArrayList<matchesModel> getJson(String json) {
            ArrayList<matchesModel> modelJson = new ArrayList<>();

            try {
                JSONObject thisJSONObject = new JSONObject(json);
                JSONArray thisJSONArray = thisJSONObject.getJSONArray("matches");
                for (int i = 0; i < thisJSONArray.length(); i++) {


                    matchesModel model = new matchesModel(
                            (thisJSONArray.getJSONObject(i).getString("date")).substring(2,((thisJSONArray.getJSONObject(i).getString("date")).indexOf('T'))),
                            thisJSONArray.getJSONObject(i).getString("team-1"),
                            thisJSONArray.getJSONObject(i).getString("team-2"),
                            thisJSONArray.getJSONObject(i).getBoolean("matchStarted"),
                            thisJSONArray.getJSONObject(i).getLong("unique_id"));

//                    Log.d("SUKRIT", "Date: " + i + "" + (thisJSONArray.getJSONObject(i).getString("date")).substring(0,((thisJSONArray.getJSONObject(i).getString("date")).indexOf('T'))));
//                    Log.d("SUKRIT", "Team-1 :   " + i + "    " + thisJSONArray.getJSONObject(i).getString("team-1"));
//                    Log.d("SUKRIT", "Team-2 :   " + i + "    " + thisJSONArray.getJSONObject(i).getString("team-2"));
//                    Log.d("SUKRIT", "MatchStarted:   " + i + "    " + thisJSONArray.getJSONObject(i).getBoolean("matchStarted"));
                    if(thisJSONArray.getJSONObject(i).getBoolean("matchStarted")==false)
                    {unique_id.add(thisJSONArray.getJSONObject(i).getLong("unique_id"));
                        //Log.e("unique_id",unique_id.get(i).toString());

                        modelJson.add(model);}
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
