//////package com.example.sukrit.merge_api;
//////
//////
//////import android.content.Context;
//////import android.os.AsyncTask;
//////import android.support.v7.app.AppCompatActivity;
//////import android.os.Bundle;
//////import android.support.v7.widget.LinearLayoutManager;
//////import android.support.v7.widget.RecyclerView;
//////import android.util.Log;
//////import android.view.View;
//////import android.widget.Button;
//////import android.widget.EditText;
//////import android.widget.TextView;
//////
//////import org.json.JSONArray;
//////import org.json.JSONException;
//////import org.json.JSONObject;
//////import org.w3c.dom.Text;
//////
//////import java.io.BufferedReader;
//////import java.io.IOException;
//////import java.io.InputStream;
//////import java.io.InputStreamReader;
//////import java.lang.reflect.Array;
//////import java.net.HttpURLConnection;
//////import java.net.MalformedURLException;
//////import java.net.URL;
//////import java.net.URLConnection;
//////import java.nio.Buffer;
//////import java.util.ArrayList;
//////
//////import static android.support.v7.recyclerview.R.styleable.RecyclerView;
//////
//////public class MainActivity extends AppCompatActivity {
//////
//////
//////    TextView team1, team2, date;
//////    String url = "";
//////    ArrayList<matchesModel> jsonArray = new ArrayList<>();
//////    ArrayList<matchesModel> adapterArray = new ArrayList<>();
//////
//////    RecyclerView upRecyclerView;
//////    UpcomingRecyclerAdapter adapter1;
//////    public static final String TAG = "MainActivity";
//////    Button btnDownload;
//////
//////    @Override
//////    protected void onCreate(Bundle savedInstanceState) {
//////        super.onCreate(savedInstanceState);
//////        setContentView(R.layout.activity_main);
//////
//////        team1 = (TextView) findViewById(R.id.team1);
//////        team2 = (TextView) findViewById(R.id.team2);
//////        date = (TextView) findViewById(R.id.date);
//////        btnDownload=(Button)findViewById(R.id.btnDownload);
//////
//////
//////
//////        btnDownload.setOnClickListener(new View.OnClickListener() {
//////            @Override
//////            public void onClick(View v) {
//////                DownloadData downloadData = new DownloadData();
//////                downloadData.execute(url);
//////
//////                upRecyclerView = (RecyclerView) findViewById(R.id.rvModelList);
//////
//////                adapter1 = new UpcomingRecyclerAdapter(adapterArray,MainActivity.this);
//////                upRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//////                upRecyclerView.setAdapter(adapter1);
//////            }
//////        });
//////
//////    }
//////
//////
//////    public class DownloadData extends AsyncTask<String, Void, ArrayList<matchesModel>> {
//////        @Override
//////        protected ArrayList<matchesModel> doInBackground(String... params) {
//////            URL url = null;
//////            Log.d("TAG", "doInBackground: ");
//////            try {
//////                url = new URL("http://cricapi.com/api/matches?apikey=CjCdXi1JuqVuqOPFGRGfG5RtGu23");
//////            } catch (MalformedURLException e) {
//////                e.printStackTrace();
//////            }
//////
//////            HttpURLConnection httpURLConnection = null;
//////            try {
//////                httpURLConnection = (HttpURLConnection) url.openConnection();
//////            } catch (IOException e) {
//////                e.printStackTrace();
//////            }
//////
//////            InputStream inputStream = null;
//////            try {
//////                inputStream = httpURLConnection.getInputStream();
//////
//////                InputStreamReader isr = new InputStreamReader(inputStream);
//////                BufferedReader reader = new BufferedReader(isr);
//////
//////                StringBuilder sb = new StringBuilder();
//////                String buffer;
//////
//////                while ((buffer = reader.readLine()) != null) {
//////                    sb.append(buffer);
//////                }
//////                String json = sb.toString();
//////                jsonArray = getJson(json);
//////                Log.d("TAG", "doInBackground: " + jsonArray.get(0).getTeam1());
//////            } catch (IOException e) {
//////                e.printStackTrace();
//////            }
//////            return jsonArray;
//////
//////        }
//////
//////        @Override
//////        protected void onPostExecute(ArrayList<matchesModel> jsonArray) {
//////            super.onPostExecute(jsonArray);
//////            int i;
//////            Log.d("TAG", "onPostExecute: " + jsonArray.size());
//////            for (i = 0; i < jsonArray.size(); i++) {
//////                adapterArray.add(jsonArray.get(i));
//////                adapter1.notifyDataSetChanged();
//////            }
//////        }
//////
//////        public ArrayList<matchesModel> getJson(String json) {
//////            ArrayList<matchesModel> modelJson = new ArrayList<>();
//////
//////            try {
//////                JSONObject thisJSONObject = new JSONObject(json);
//////                JSONArray thisJSONArray = thisJSONObject.optJSONArray("matches");
//////
//////                for (int i = 0; i < thisJSONArray.length(); i++) {
//////                    matchesModel model = new matchesModel(
//////                            thisJSONArray.getJSONObject(i).getString("date"),
//////                            thisJSONArray.getJSONObject(i).getString("team-1"),
//////                            thisJSONArray.getJSONObject(i).getString("team-2"),
//////                            thisJSONArray.getJSONObject(i).getBoolean("matchStarted"));
//////
//////                    //log
//////                    if (thisJSONArray.getJSONObject(i).getBoolean("matchStarted") == true) {
//////                        Log.d("SUK", "Date: " + i + "" + thisJSONArray.getJSONObject(i).getString("date"));
//////                        Log.d("SUK", "Team-1 :   " + i + "    " + thisJSONArray.getJSONObject(i).getString("team-1"));
//////                        Log.d("SUK", "Team-2 :   " + i + "    " + thisJSONArray.getJSONObject(i).getString("team-2"));
//////                        Log.d("SUK", "MatchStarted:   " + i + "    " + thisJSONArray.getJSONObject(i).getBoolean("matchStarted"));
//////                    }
//////                    modelJson.add(model);
//////                }
//////            } catch (JSONException e) {
//////                e.printStackTrace();
//////            }
//////            return modelJson;
//////        }
//////    }
//////}
//////
//////package com.example.sukrit.demo_cric_f1;
//////
//////
//////import android.content.Context;
//////import android.os.AsyncTask;
//////import android.support.v7.app.AppCompatActivity;
//////import android.os.Bundle;
//////import android.support.v7.widget.LinearLayoutManager;
//////import android.support.v7.widget.RecyclerView;
//////import android.util.Log;
//////import android.view.View;
//////import android.widget.Button;
//////import android.widget.EditText;
//////import android.widget.TextView;
//////import android.widget.Toast;
//////
//////import org.json.JSONArray;
//////import org.json.JSONException;
//////import org.json.JSONObject;
//////import org.w3c.dom.Text;
//////
//////import java.io.BufferedReader;
//////import java.io.IOException;
//////import java.io.InputStream;
//////import java.io.InputStreamReader;
//////import java.lang.reflect.Array;
//////import java.net.HttpURLConnection;
//////import java.net.MalformedURLException;
//////import java.net.URL;
//////import java.net.URLConnection;
//////import java.nio.Buffer;
//////import java.util.ArrayList;
//////
//////import static android.support.v7.recyclerview.R.styleable.RecyclerView;
//////
//////public class Recent extends AppCompatActivity {
//////
//////
//////    TextView team1, team2, date;
//////    String url = "";
//////    String url2 = "";
//////    ArrayList<recentModel> jsonArray1 = new ArrayList<>();
//////    ArrayList<recentModel> adapterArray = new ArrayList<>();
//////    ArrayList<Long> unique_id=new ArrayList<>();
//////
//////    Integer no_of_matches = 0;
//////
//////    RecyclerView recentRecyclerView;
//////   RecentRecyclerAdapter adapter1;
//////    public static final String TAG = "MainActivity";
//////    Button btnDownload,btnDownload2;
//////
//////    @Override
//////    protected void onCreate(Bundle savedInstanceState) {
//////        super.onCreate(savedInstanceState);
//////        setContentView(R.layout.activity_recent);
//////
//////        team1 = (TextView) findViewById(R.id.team1);
//////        team2 = (TextView) findViewById(R.id.team2);
//////        date = (TextView) findViewById(R.id.date);
//////        btnDownload = (Button) findViewById(R.id.btnDownload);
//////
//////
//////        btnDownload.setOnClickListener(new View.OnClickListener() {
//////            @Override
//////            public void onClick(View v) {
//////                DownloadData downloadData = new DownloadData();
//////                downloadData.execute(url);
//////
//////                recentRecyclerView = (RecyclerView) findViewById(R.id.rvModelList2);
//////
//////                adapter1 = new RecentRecyclerAdapter(adapterArray, Recent.this);
//////                recentRecyclerView.setLayoutManager(new LinearLayoutManager(Recent.this));
//////                recentRecyclerView.setAdapter(adapter1);
//////
//////                Log.d(TAG, "btn2Unique Id: " + unique_id.size());
//////                for (int i = 0; i < unique_id.size() ; i++)
//////
//////                {
//////                    DownloadData2 downloadData2 = new DownloadData2();
//////                    downloadData2.execute("http://cricapi.com/api/cricketScore?apikey=CjCdXi1JuqVuqOPFGRGfG5RtGu23&unique_id=" + unique_id.get(i).toString());
//////                }
//////
//////            }
//////        });
//////    }
//////    public class DownloadData extends AsyncTask<String, Void, ArrayList<recentModel>> {
//////        @Override
//////        protected ArrayList<recentModel> doInBackground(String... params) {
//////            URL url1 = null;
//////
//////            //Log.d("TAG", "doInBackground: ");
//////            try {
//////                url1 = new URL("http://cricapi.com/api/matches?apikey=CjCdXi1JuqVuqOPFGRGfG5RtGu23");
//////            } catch (MalformedURLException e) {
//////                e.printStackTrace();
//////            }
//////
//////
//////            HttpURLConnection httpURLConnection1 = null;
//////            try {
//////                httpURLConnection1 = (HttpURLConnection) url1.openConnection();
//////            } catch (IOException e) {
//////                e.printStackTrace();
//////            }
//////
//////            InputStream inputStream1 = null;
//////            try {
//////                inputStream1 = httpURLConnection1.getInputStream();
//////
//////                InputStreamReader isr = new InputStreamReader(inputStream1);
//////                BufferedReader reader = new BufferedReader(isr);
//////
//////                StringBuilder sb = new StringBuilder();
//////                String buffer;
//////
//////                while ((buffer = reader.readLine()) != null) {
//////                    sb.append(buffer);
//////                }
//////                String json = sb.toString();
//////                jsonArray1 = getJson(json);
//////                Log.d("TAG", "doInBackground1: " + jsonArray1.get(0).getTeam1());
//////            } catch (IOException e) {
//////                e.printStackTrace();
//////            }
//////            return jsonArray1;
//////
//////        }
////////
////////        @Override
////////        protected void onPostExecute(ArrayList<matchesModel> jsonArray) {
////////            super.onPostExecute(jsonArray);
////////            int i;
////////            no_of_matches = jsonArray.size();
////////            Log.d("TAG", "onPostExecute: " + jsonArray.size());
////////            for (i = 0; i < jsonArray.size(); i++) {
////////                adapterArray.add(jsonArray.get(i));
////////                adapter1.notifyDataSetChanged();
////////            }
////////        }
//////
//////        public ArrayList<recentModel> getJson(String json) {
//////            ArrayList<recentModel> modelJson = new ArrayList<>();
//////
//////            try {
//////                JSONObject thisJSONObject = new JSONObject(json);
//////                JSONArray thisJSONArray = thisJSONObject.optJSONArray("matches");
//////
//////                for (int i = 0; i < thisJSONArray.length(); i++) {
//////                    recentModel model = new recentModel(
//////                            thisJSONArray.getJSONObject(i).getString("team-1"),
//////                            thisJSONArray.getJSONObject(i).getString("team-2"),
//////                            thisJSONArray.getJSONObject(i).getBoolean("matchStarted"),
//////                            thisJSONArray.getJSONObject(i).getLong("unique_id"),
//////                            thisJSONArray.getJSONObject(i).getString("date"));
//////
//////                    Log.d("SUK", "Date: " + i + "" + thisJSONArray.getJSONObject(i).getString("date"));
//////                    Log.d("SUK", "Team-1 :   " + i + "    " + thisJSONArray.getJSONObject(i).getString("team-1"));
//////                    Log.d("SUK", "Team-2 :   " + i + "    " + thisJSONArray.getJSONObject(i).getString("team-2"));
//////                    Log.d("SUK", "MatchStarted:   " + i + "    " + thisJSONArray.getJSONObject(i).getBoolean("matchStarted"));
//////
//////                    unique_id.add(thisJSONArray.getJSONObject(i).getLong("unique_id"));
//////                    Log.e("unique_id",unique_id.get(i).toString());
//////
//////                    modelJson.add(model);}
//////                Log.d("UNI1", "UNIQUE_ID SIZE: "+unique_id.size());
//////
//////                Log.d("UNI-1", "Unique ids::: "+unique_id.get(0));
//////
//////            } catch (JSONException e) {
//////                e.printStackTrace();
//////            }
//////            return modelJson;
//////        }
//////    }
//////
//////    public class DownloadData2 extends AsyncTask<String, Void, ArrayList<recentModel>> {
//////        @Override
//////        protected ArrayList<recentModel> doInBackground(String... params) {
//////            URL url1 = null;
//////
//////            try {
//////                url1 = new URL(params[0]);
//////            } catch (MalformedURLException e) {
//////                e.printStackTrace();
//////            }
//////
//////            HttpURLConnection httpURLConnection1 = null;
//////            try {
//////                httpURLConnection1 = (HttpURLConnection) url1.openConnection();
//////            } catch (IOException e) {
//////                e.printStackTrace();
//////            }
//////
//////            InputStream inputStream1 = null;
//////            try {
//////                inputStream1 = httpURLConnection1.getInputStream();
//////
//////                InputStreamReader isr = new InputStreamReader(inputStream1);
//////                BufferedReader reader = new BufferedReader(isr);
//////
//////                StringBuilder sb = new StringBuilder();
//////                String buffer;
//////
//////                while ((buffer = reader.readLine()) != null) {
//////                    sb.append(buffer);
//////                }
//////                String json = sb.toString();
//////                jsonArray1 = getJson(json);
//////                Log.d("TAG", "doInBackground2: " + json.toString());
//////            } catch (IOException e) {
//////                e.printStackTrace();
//////            }
//////            return jsonArray1;
//////
//////        }
//////
//////        @Override
//////        protected void onPostExecute(ArrayList<recentModel> jsonArray) {
//////            super.onPostExecute(jsonArray);
//////            int i;
//////            no_of_matches = jsonArray.size();
//////            Log.d("TAG", "onPostExecute: " + jsonArray.size());
//////            for (i = 0; i < jsonArray.size(); i++) {
//////                adapterArray.add(jsonArray.get(i));
//////                adapter1.notifyDataSetChanged();
//////            }
//////        }
//////
//////        public ArrayList<recentModel> getJson(String json) {
//////            ArrayList<recentModel> modelJson = new ArrayList<>();
//////
//////            try {
//////                JSONObject thisJSONObject = new JSONObject(json);
//////
//////
//////                recentModel model = new recentModel(
//////
//////                        thisJSONObject.getString("team-1"),
//////                        thisJSONObject.getString("team-2"),
//////                        thisJSONObject.getBoolean("matchStarted"),
//////                        thisJSONObject.getLong("ttl"),
//////                        thisJSONObject.getString("innings-requirement"));
//////
//////                Log.d("SUK", "innings-requirement: "  + "" + thisJSONObject.getString("innings-requirement"));
//////                Log.d("SUK", "Team-1 :   "  + "    " + thisJSONObject.getString("team-1"));
//////                Log.d("SUK", "Team-2 :   "  + "    " + thisJSONObject.getString("team-2"));
//////                Log.d("SUK", "MatchStarted:   "  + "    " + thisJSONObject.getBoolean("matchStarted"));
//////                unique_id.add(thisJSONObject.getLong("ttl"));
////////                Log.d(TAG, "substring: "+(( thisJSONObject.getString("innings-requirement")).substring(0,6)));
//////                Log.e("unique_id",unique_id.get(0).toString());
//////                if(!(((( thisJSONObject.getString("innings-requirement")).substring(0,6))
//////                ).equalsIgnoreCase("Match"))){
//////                    modelJson.add(model);}
//////                else
//////                {
//////                    Toast.makeText(Recent.this,"APPPPPPPPPPPPPP",Toast.LENGTH_SHORT).show();
//////                }
//////
//////                Log.d("UNI2", "UNIQUE_ID SIZE: "+unique_id.size());
//////
//////                Log.d("UNI-2", "Unique ids::: "+unique_id.get(0));
//////
//////            } catch (JSONException e) {
//////                e.printStackTrace();
//////            }
//////            return modelJson;
//////        }
//////    }
//////
//////}
////
////
////package com.example.sukrit.demo_cric_f1;
////
////
////import android.content.Context;
////import android.os.AsyncTask;
////import android.support.v7.app.AppCompatActivity;
////import android.os.Bundle;
////import android.support.v7.widget.LinearLayoutManager;
////import android.support.v7.widget.RecyclerView;
////import android.util.Log;
////import android.view.View;
////import android.widget.Button;
////import android.widget.EditText;
////import android.widget.TextView;
////import android.widget.Toast;
////
////import org.json.JSONArray;
////import org.json.JSONException;
////import org.json.JSONObject;
////import org.w3c.dom.Text;
////
////import java.io.BufferedReader;
////import java.io.IOException;
////import java.io.InputStream;
////import java.io.InputStreamReader;
////import java.lang.reflect.Array;
////import java.net.HttpURLConnection;
////import java.net.MalformedURLException;
////import java.net.URL;
////import java.net.URLConnection;
////import java.nio.Buffer;
////import java.util.ArrayList;
////
////import static android.support.v7.recyclerview.R.styleable.RecyclerView;
////
////public class Recent extends AppCompatActivity {
////
////
////    TextView team1, team2, date;
////    String url = "";
////    String url2 = "";
////    ArrayList<recentModel> jsonArray1 = new ArrayList<>();
////    ArrayList<recentModel> adapterArray = new ArrayList<>();
////    ArrayList<Long> unique_id=new ArrayList<>();
////
////    Integer no_of_matches = 0;
////
////    RecyclerView recentRecyclerView;
////    RecentRecyclerAdapter adapter1;
////    public static final String TAG = "MainActivity";
////    Button btnDownload,btnDownload2;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_recent);
////
////        team1 = (TextView) findViewById(R.id.team1);
////        team2 = (TextView) findViewById(R.id.team2);
////        date = (TextView) findViewById(R.id.date);
////        btnDownload = (Button) findViewById(R.id.btnDownload);
////
////
////        btnDownload.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                DownloadData downloadData = new DownloadData();
////                downloadData.execute(url);
////
////                recentRecyclerView = (RecyclerView) findViewById(R.id.rvModelList2);
////
////                adapter1 = new RecentRecyclerAdapter(adapterArray, Recent.this);
////                recentRecyclerView.setLayoutManager(new LinearLayoutManager(Recent.this));
////                recentRecyclerView.setAdapter(adapter1);
////
////                Log.d(TAG, "btn2Unique Id: " + unique_id.size());
////                for (int i = 0; i < unique_id.size() ; i++)
////
////                {
////                    DownloadData2 downloadData2 = new DownloadData2();
////                    downloadData2.execute("http://cricapi.com/api/cricketScore?apikey=CjCdXi1JuqVuqOPFGRGfG5RtGu23&unique_id=" + unique_id.get(i).toString());
////                }
////
////            }
////        });
////    }
////    public class DownloadData extends AsyncTask<String, Void, ArrayList<recentModel>> {
////        @Override
////        protected ArrayList<recentModel> doInBackground(String... params) {
////            URL url1 = null;
////
////            //Log.d("TAG", "doInBackground: ");
////            try {
////                url1 = new URL("http://cricapi.com/api/matches?apikey=CjCdXi1JuqVuqOPFGRGfG5RtGu23");
////            } catch (MalformedURLException e) {
////                e.printStackTrace();
////            }
////
////
////            HttpURLConnection httpURLConnection1 = null;
////            try {
////                httpURLConnection1 = (HttpURLConnection) url1.openConnection();
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////
////            InputStream inputStream1 = null;
////            try {
////                inputStream1 = httpURLConnection1.getInputStream();
////
////                InputStreamReader isr = new InputStreamReader(inputStream1);
////                BufferedReader reader = new BufferedReader(isr);
////
////                StringBuilder sb = new StringBuilder();
////                String buffer;
////
////                while ((buffer = reader.readLine()) != null) {
////                    sb.append(buffer);
////                }
////                String json = sb.toString();
////                jsonArray1 = getJson(json);
////                Log.d("TAG", "doInBackground1: " + jsonArray1.get(0).getTeam1());
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////            return jsonArray1;
////
////        }
//////
//////        @Override
//////        protected void onPostExecute(ArrayList<matchesModel> jsonArray) {
//////            super.onPostExecute(jsonArray);
//////            int i;
//////            no_of_matches = jsonArray.size();
//////            Log.d("TAG", "onPostExecute: " + jsonArray.size());
//////            for (i = 0; i < jsonArray.size(); i++) {
//////                adapterArray.add(jsonArray.get(i));
//////                adapter1.notifyDataSetChanged();
//////            }
//////        }
////
////        public ArrayList<recentModel> getJson(String json) {
////            ArrayList<recentModel> modelJson = new ArrayList<>();
////
////            try {
////                JSONObject thisJSONObject = new JSONObject(json);
////                JSONArray thisJSONArray = thisJSONObject.optJSONArray("matches");
////
////                for (int i = 0; i < thisJSONArray.length(); i++) {
////                    recentModel model = new recentModel(
////                            thisJSONArray.getJSONObject(i).getString("team-1"),
////                            thisJSONArray.getJSONObject(i).getString("team-2"),
////                            thisJSONArray.getJSONObject(i).getBoolean("matchStarted"),
////                            thisJSONArray.getJSONObject(i).getLong("unique_id"),
////                            thisJSONArray.getJSONObject(i).getString("date"));
////
////                    Log.d("SUK", "Date: " + i + "" + thisJSONArray.getJSONObject(i).getString("date"));
////                    Log.d("SUK", "Team-1 :   " + i + "    " + thisJSONArray.getJSONObject(i).getString("team-1"));
////                    Log.d("SUK", "Team-2 :   " + i + "    " + thisJSONArray.getJSONObject(i).getString("team-2"));
////                    Log.d("SUK", "MatchStarted:   " + i + "    " + thisJSONArray.getJSONObject(i).getBoolean("matchStarted"));
////
////                    unique_id.add(thisJSONArray.getJSONObject(i).getLong("unique_id"));
////                    Log.e("unique_id",unique_id.get(i).toString());
////
////                    modelJson.add(model);}
////                Log.d("UNI1", "UNIQUE_ID SIZE: "+unique_id.size());
////
////                Log.d("UNI-1", "Unique ids::: "+unique_id.get(0));
////
////            } catch (JSONException e) {
////                e.printStackTrace();
////            }
////            return modelJson;
////        }
////    }
////
////    public class DownloadData2 extends AsyncTask<String, Void, ArrayList<recentModel>> {
////        @Override
////        protected ArrayList<recentModel> doInBackground(String... params) {
////            URL url1 = null;
////
////            try {
////                url1 = new URL(params[0]);
////            } catch (MalformedURLException e) {
////                e.printStackTrace();
////            }
////
////            HttpURLConnection httpURLConnection1 = null;
////            try {
////                httpURLConnection1 = (HttpURLConnection) url1.openConnection();
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////
////            InputStream inputStream1 = null;
////            try {
////                inputStream1 = httpURLConnection1.getInputStream();
////
////                InputStreamReader isr = new InputStreamReader(inputStream1);
////                BufferedReader reader = new BufferedReader(isr);
////
////                StringBuilder sb = new StringBuilder();
////                String buffer;
////
////                while ((buffer = reader.readLine()) != null) {
////                    sb.append(buffer);
////                }
////                String json = sb.toString();
////                jsonArray1 = getJson(json);
////                Log.d("TAG", "doInBackground2: " + json.toString());
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////            return jsonArray1;
////
////        }
////
////        @Override
////        protected void onPostExecute(ArrayList<recentModel> jsonArray) {
////            super.onPostExecute(jsonArray);
////            int i;
////            no_of_matches = jsonArray.size();
////            Log.d("TAG", "onPostExecute: " + jsonArray.size());
////            for (i = 0; i < jsonArray.size(); i++) {
////                adapterArray.add(jsonArray.get(i));
////                adapter1.notifyDataSetChanged();
////            }
////        }
////
////        public ArrayList<recentModel> getJson(String json) {
////            ArrayList<recentModel> modelJson = new ArrayList<>();
////
////            try {
////                JSONObject thisJSONObject = new JSONObject(json);
////
////
////                recentModel model = new recentModel(
////
////                        thisJSONObject.getString("team-1"),
////                        thisJSONObject.getString("team-2"),
////                        thisJSONObject.getBoolean("matchStarted"),
////                        thisJSONObject.getLong("ttl"),
////                        thisJSONObject.getString("innings-requirement"));
////
////                Log.d("SUK", "innings-requirement: "  + "" + thisJSONObject.getString("innings-requirement"));
////                Log.d("SUK", "Team-1 :   "  + "    " + thisJSONObject.getString("team-1"));
////                Log.d("SUK", "Team-2 :   "  + "    " + thisJSONObject.getString("team-2"));
////                Log.d("SUK", "MatchStarted:   "  + "    " + thisJSONObject.getBoolean("matchStarted"));
////                unique_id.add(thisJSONObject.getLong("ttl"));
//////                Log.d(TAG, "substring: "+(( thisJSONObject.getString("innings-requirement")).substring(0,6)));
////                Log.e("unique_id",unique_id.get(0).toString());
////
////                if(thisJSONObject.getBoolean("matchStarted")){
////                    modelJson.add(model);}
////
////                Log.d("UNI2", "UNIQUE_ID SIZE: "+unique_id.size());
////
////                Log.d("UNI-2", "Unique ids::: "+unique_id.get(0));
////
////            } catch (JSONException e) {
////                e.printStackTrace();
////            }
////            return modelJson;
////        }
////    }
////
////}
////package com.example.sukrit.merge_api;
////
////
////import android.content.Context;
////import android.os.AsyncTask;
////import android.support.v7.app.AppCompatActivity;
////import android.os.Bundle;
////import android.support.v7.widget.LinearLayoutManager;
////import android.support.v7.widget.RecyclerView;
////import android.util.Log;
////import android.view.View;
////import android.widget.Button;
////import android.widget.EditText;
////import android.widget.TextView;
////
////import org.json.JSONArray;
////import org.json.JSONException;
////import org.json.JSONObject;
////import org.w3c.dom.Text;
////
////import java.io.BufferedReader;
////import java.io.IOException;
////import java.io.InputStream;
////import java.io.InputStreamReader;
////import java.lang.reflect.Array;
////import java.net.HttpURLConnection;
////import java.net.MalformedURLException;
////import java.net.URL;
////import java.net.URLConnection;
////import java.nio.Buffer;
////import java.util.ArrayList;
////
////import static android.support.v7.recyclerview.R.styleable.RecyclerView;
////
////public class MainActivity extends AppCompatActivity {
////
////
////    TextView team1, team2, date;
////    String url = "";
////    ArrayList<matchesModel> jsonArray = new ArrayList<>();
////    ArrayList<matchesModel> adapterArray = new ArrayList<>();
////
////    RecyclerView upRecyclerView;
////    UpcomingRecyclerAdapter adapter1;
////    public static final String TAG = "MainActivity";
////    Button btnDownload;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_main);
////
////        team1 = (TextView) findViewById(R.id.team1);
////        team2 = (TextView) findViewById(R.id.team2);
////        date = (TextView) findViewById(R.id.date);
////        btnDownload=(Button)findViewById(R.id.btnDownload);
////
////
////
////        btnDownload.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                DownloadData downloadData = new DownloadData();
////                downloadData.execute(url);
////
////                upRecyclerView = (RecyclerView) findViewById(R.id.rvModelList);
////
////                adapter1 = new UpcomingRecyclerAdapter(adapterArray,MainActivity.this);
////                upRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
////                upRecyclerView.setAdapter(adapter1);
////            }
////        });
////
////    }
////
////
////    public class DownloadData extends AsyncTask<String, Void, ArrayList<matchesModel>> {
////        @Override
////        protected ArrayList<matchesModel> doInBackground(String... params) {
////            URL url = null;
////            Log.d("TAG", "doInBackground: ");
////            try {
////                url = new URL("http://cricapi.com/api/matches?apikey=CjCdXi1JuqVuqOPFGRGfG5RtGu23");
////            } catch (MalformedURLException e) {
////                e.printStackTrace();
////            }
////
////            HttpURLConnection httpURLConnection = null;
////            try {
////                httpURLConnection = (HttpURLConnection) url.openConnection();
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////
////            InputStream inputStream = null;
////            try {
////                inputStream = httpURLConnection.getInputStream();
////
////                InputStreamReader isr = new InputStreamReader(inputStream);
////                BufferedReader reader = new BufferedReader(isr);
////
////                StringBuilder sb = new StringBuilder();
////                String buffer;
////
////                while ((buffer = reader.readLine()) != null) {
////                    sb.append(buffer);
////                }
////                String json = sb.toString();
////                jsonArray = getJson(json);
////                Log.d("TAG", "doInBackground: " + jsonArray.get(0).getTeam1());
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////            return jsonArray;
////
////        }
////
////        @Override
////        protected void onPostExecute(ArrayList<matchesModel> jsonArray) {
////            super.onPostExecute(jsonArray);
////            int i;
////            Log.d("TAG", "onPostExecute: " + jsonArray.size());
////            for (i = 0; i < jsonArray.size(); i++) {
////                adapterArray.add(jsonArray.get(i));
////                adapter1.notifyDataSetChanged();
////            }
////        }
////
////        public ArrayList<matchesModel> getJson(String json) {
////            ArrayList<matchesModel> modelJson = new ArrayList<>();
////
////            try {
////                JSONObject thisJSONObject = new JSONObject(json);
////                JSONArray thisJSONArray = thisJSONObject.optJSONArray("matches");
////
////                for (int i = 0; i < thisJSONArray.length(); i++) {
////                    matchesModel model = new matchesModel(
////                            thisJSONArray.getJSONObject(i).getString("date"),
////                            thisJSONArray.getJSONObject(i).getString("team-1"),
////                            thisJSONArray.getJSONObject(i).getString("team-2"),
////                            thisJSONArray.getJSONObject(i).getBoolean("matchStarted"));
////
////                    //log
////                    if (thisJSONArray.getJSONObject(i).getBoolean("matchStarted") == true) {
////                        Log.d("SUK", "Date: " + i + "" + thisJSONArray.getJSONObject(i).getString("date"));
////                        Log.d("SUK", "Team-1 :   " + i + "    " + thisJSONArray.getJSONObject(i).getString("team-1"));
////                        Log.d("SUK", "Team-2 :   " + i + "    " + thisJSONArray.getJSONObject(i).getString("team-2"));
////                        Log.d("SUK", "MatchStarted:   " + i + "    " + thisJSONArray.getJSONObject(i).getBoolean("matchStarted"));
////                    }
////                    modelJson.add(model);
////                }
////            } catch (JSONException e) {
////                e.printStackTrace();
////            }
////            return modelJson;
////        }
////    }
////}
////
////package com.example.sukrit.demo_cric_f1;
////
////
////import android.content.Context;
////import android.os.AsyncTask;
////import android.support.v7.app.AppCompatActivity;
////import android.os.Bundle;
////import android.support.v7.widget.LinearLayoutManager;
////import android.support.v7.widget.RecyclerView;
////import android.util.Log;
////import android.view.View;
////import android.widget.Button;
////import android.widget.EditText;
////import android.widget.TextView;
////import android.widget.Toast;
////
////import org.json.JSONArray;
////import org.json.JSONException;
////import org.json.JSONObject;
////import org.w3c.dom.Text;
////
////import java.io.BufferedReader;
////import java.io.IOException;
////import java.io.InputStream;
////import java.io.InputStreamReader;
////import java.lang.reflect.Array;
////import java.net.HttpURLConnection;
////import java.net.MalformedURLException;
////import java.net.URL;
////import java.net.URLConnection;
////import java.nio.Buffer;
////import java.util.ArrayList;
////
////import static android.support.v7.recyclerview.R.styleable.RecyclerView;
////
////public class Recent extends AppCompatActivity {
////
////
////    TextView team1, team2, date;
////    String url = "";
////    String url2 = "";
////    ArrayList<recentModel> jsonArray1 = new ArrayList<>();
////    ArrayList<recentModel> adapterArray = new ArrayList<>();
////    ArrayList<Long> unique_id=new ArrayList<>();
////
////    Integer no_of_matches = 0;
////
////    RecyclerView recentRecyclerView;
////   RecentRecyclerAdapter adapter1;
////    public static final String TAG = "MainActivity";
////    Button btnDownload,btnDownload2;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_recent);
////
////        team1 = (TextView) findViewById(R.id.team1);
////        team2 = (TextView) findViewById(R.id.team2);
////        date = (TextView) findViewById(R.id.date);
////        btnDownload = (Button) findViewById(R.id.btnDownload);
////
////
////        btnDownload.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                DownloadData downloadData = new DownloadData();
////                downloadData.execute(url);
////
////                recentRecyclerView = (RecyclerView) findViewById(R.id.rvModelList2);
////
////                adapter1 = new RecentRecyclerAdapter(adapterArray, Recent.this);
////                recentRecyclerView.setLayoutManager(new LinearLayoutManager(Recent.this));
////                recentRecyclerView.setAdapter(adapter1);
////
////                Log.d(TAG, "btn2Unique Id: " + unique_id.size());
////                for (int i = 0; i < unique_id.size() ; i++)
////
////                {
////                    DownloadData2 downloadData2 = new DownloadData2();
////                    downloadData2.execute("http://cricapi.com/api/cricketScore?apikey=CjCdXi1JuqVuqOPFGRGfG5RtGu23&unique_id=" + unique_id.get(i).toString());
////                }
////
////            }
////        });
////    }
////    public class DownloadData extends AsyncTask<String, Void, ArrayList<recentModel>> {
////        @Override
////        protected ArrayList<recentModel> doInBackground(String... params) {
////            URL url1 = null;
////
////            //Log.d("TAG", "doInBackground: ");
////            try {
////                url1 = new URL("http://cricapi.com/api/matches?apikey=CjCdXi1JuqVuqOPFGRGfG5RtGu23");
////            } catch (MalformedURLException e) {
////                e.printStackTrace();
////            }
////
////
////            HttpURLConnection httpURLConnection1 = null;
////            try {
////                httpURLConnection1 = (HttpURLConnection) url1.openConnection();
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////
////            InputStream inputStream1 = null;
////            try {
////                inputStream1 = httpURLConnection1.getInputStream();
////
////                InputStreamReader isr = new InputStreamReader(inputStream1);
////                BufferedReader reader = new BufferedReader(isr);
////
////                StringBuilder sb = new StringBuilder();
////                String buffer;
////
////                while ((buffer = reader.readLine()) != null) {
////                    sb.append(buffer);
////                }
////                String json = sb.toString();
////                jsonArray1 = getJson(json);
////                Log.d("TAG", "doInBackground1: " + jsonArray1.get(0).getTeam1());
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////            return jsonArray1;
////
////        }
//////
//////        @Override
//////        protected void onPostExecute(ArrayList<matchesModel> jsonArray) {
//////            super.onPostExecute(jsonArray);
//////            int i;
//////            no_of_matches = jsonArray.size();
//////            Log.d("TAG", "onPostExecute: " + jsonArray.size());
//////            for (i = 0; i < jsonArray.size(); i++) {
//////                adapterArray.add(jsonArray.get(i));
//////                adapter1.notifyDataSetChanged();
//////            }
//////        }
////
////        public ArrayList<recentModel> getJson(String json) {
////            ArrayList<recentModel> modelJson = new ArrayList<>();
////
////            try {
////                JSONObject thisJSONObject = new JSONObject(json);
////                JSONArray thisJSONArray = thisJSONObject.optJSONArray("matches");
////
////                for (int i = 0; i < thisJSONArray.length(); i++) {
////                    recentModel model = new recentModel(
////                            thisJSONArray.getJSONObject(i).getString("team-1"),
////                            thisJSONArray.getJSONObject(i).getString("team-2"),
////                            thisJSONArray.getJSONObject(i).getBoolean("matchStarted"),
////                            thisJSONArray.getJSONObject(i).getLong("unique_id"),
////                            thisJSONArray.getJSONObject(i).getString("date"));
////
////                    Log.d("SUK", "Date: " + i + "" + thisJSONArray.getJSONObject(i).getString("date"));
////                    Log.d("SUK", "Team-1 :   " + i + "    " + thisJSONArray.getJSONObject(i).getString("team-1"));
////                    Log.d("SUK", "Team-2 :   " + i + "    " + thisJSONArray.getJSONObject(i).getString("team-2"));
////                    Log.d("SUK", "MatchStarted:   " + i + "    " + thisJSONArray.getJSONObject(i).getBoolean("matchStarted"));
////
////                    unique_id.add(thisJSONArray.getJSONObject(i).getLong("unique_id"));
////                    Log.e("unique_id",unique_id.get(i).toString());
////
////                    modelJson.add(model);}
////                Log.d("UNI1", "UNIQUE_ID SIZE: "+unique_id.size());
////
////                Log.d("UNI-1", "Unique ids::: "+unique_id.get(0));
////
////            } catch (JSONException e) {
////                e.printStackTrace();
////            }
////            return modelJson;
////        }
////    }
////
////    public class DownloadData2 extends AsyncTask<String, Void, ArrayList<recentModel>> {
////        @Override
////        protected ArrayList<recentModel> doInBackground(String... params) {
////            URL url1 = null;
////
////            try {
////                url1 = new URL(params[0]);
////            } catch (MalformedURLException e) {
////                e.printStackTrace();
////            }
////
////            HttpURLConnection httpURLConnection1 = null;
////            try {
////                httpURLConnection1 = (HttpURLConnection) url1.openConnection();
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////
////            InputStream inputStream1 = null;
////            try {
////                inputStream1 = httpURLConnection1.getInputStream();
////
////                InputStreamReader isr = new InputStreamReader(inputStream1);
////                BufferedReader reader = new BufferedReader(isr);
////
////                StringBuilder sb = new StringBuilder();
////                String buffer;
////
////                while ((buffer = reader.readLine()) != null) {
////                    sb.append(buffer);
////                }
////                String json = sb.toString();
////                jsonArray1 = getJson(json);
////                Log.d("TAG", "doInBackground2: " + json.toString());
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////            return jsonArray1;
////
////        }
////
////        @Override
////        protected void onPostExecute(ArrayList<recentModel> jsonArray) {
////            super.onPostExecute(jsonArray);
////            int i;
////            no_of_matches = jsonArray.size();
////            Log.d("TAG", "onPostExecute: " + jsonArray.size());
////            for (i = 0; i < jsonArray.size(); i++) {
////                adapterArray.add(jsonArray.get(i));
////                adapter1.notifyDataSetChanged();
////            }
////        }
////
////        public ArrayList<recentModel> getJson(String json) {
////            ArrayList<recentModel> modelJson = new ArrayList<>();
////
////            try {
////                JSONObject thisJSONObject = new JSONObject(json);
////
////
////                recentModel model = new recentModel(
////
////                        thisJSONObject.getString("team-1"),
////                        thisJSONObject.getString("team-2"),
////                        thisJSONObject.getBoolean("matchStarted"),
////                        thisJSONObject.getLong("ttl"),
////                        thisJSONObject.getString("innings-requirement"));
////
////                Log.d("SUK", "innings-requirement: "  + "" + thisJSONObject.getString("innings-requirement"));
////                Log.d("SUK", "Team-1 :   "  + "    " + thisJSONObject.getString("team-1"));
////                Log.d("SUK", "Team-2 :   "  + "    " + thisJSONObject.getString("team-2"));
////                Log.d("SUK", "MatchStarted:   "  + "    " + thisJSONObject.getBoolean("matchStarted"));
////                unique_id.add(thisJSONObject.getLong("ttl"));
//////                Log.d(TAG, "substring: "+(( thisJSONObject.getString("innings-requirement")).substring(0,6)));
////                Log.e("unique_id",unique_id.get(0).toString());
////                if(!(((( thisJSONObject.getString("innings-requirement")).substring(0,6))
////                ).equalsIgnoreCase("Match"))){
////                    modelJson.add(model);}
////                else
////                {
////                    Toast.makeText(Recent.this,"APPPPPPPPPPPPPP",Toast.LENGTH_SHORT).show();
////                }
////
////                Log.d("UNI2", "UNIQUE_ID SIZE: "+unique_id.size());
////
////                Log.d("UNI-2", "Unique ids::: "+unique_id.get(0));
////
////            } catch (JSONException e) {
////                e.printStackTrace();
////            }
////            return modelJson;
////        }
////    }
////
////}
//
//
package com.example.sukrit.demo_cric_f1;


import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
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
/*
public class Recent extends AppCompatActivity {


    TextView team1, team2, date;
    String url = "";
    String url2 = "";
    ArrayList<matchesModel> jsonArray1 = new ArrayList<>();
    ArrayList<recentModel> jsonArray2 = new ArrayList<>();

    ArrayList<recentModel> adapterArray = new ArrayList<>();
    ArrayList<Long> unique_id=new ArrayList<>();

    Integer no_of_matches = 0;

    RecyclerView recentRecyclerView;
    RecentRecyclerAdapter adapter1;
    public static final String TAG = "MainActivity";
    Button btnDownload;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent);

        team1 = (TextView) findViewById(R.id.team1);
        team2 = (TextView) findViewById(R.id.team2);
        date = (TextView) findViewById(R.id.date);
        btnDownload = (Button) findViewById(R.id.btnDownload);


        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloadData downloadData = new DownloadData();
                downloadData.execute(url);

                recentRecyclerView = (RecyclerView) findViewById(R.id.rvModelList2);

                adapter1 = new RecentRecyclerAdapter(adapterArray, Recent.this);
                recentRecyclerView.setLayoutManager(new LinearLayoutManager(Recent.this));
                recentRecyclerView.setAdapter(adapter1);

                Log.d(TAG, "btn2Unique Id: " + unique_id.size());
                for (int i = 0; i < unique_id.size() ; i++)

                {
                    DownloadData2 downloadData2 = new DownloadData2();
                    downloadData2.execute("http://cricapi.com/api/cricketScore?apikey=CjCdXi1JuqVuqOPFGRGfG5RtGu23&unique_id=" + unique_id.get(i).toString());

                }

            }
        });
    }
    public class DownloadData extends AsyncTask<String, Void, ArrayList<matchesModel>> {
        @Override
        protected ArrayList<matchesModel> doInBackground(String... params) {
            URL url1 = null;

            //Log.d("TAG", "doInBackground: ");
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
                Log.d("TAG", "doInBackground1: " + jsonArray1.get(0).getTeam1());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonArray1;

        }

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

        public ArrayList<matchesModel> getJson(String json) {
            ArrayList<matchesModel> modelJson1 = new ArrayList<>();

            try {
                JSONObject thisJSONObject = new JSONObject(json);
                JSONArray thisJSONArray = thisJSONObject.optJSONArray("matches");

                for (int i = 0; i < thisJSONArray.length(); i++) {
                    matchesModel model = new matchesModel(
                            thisJSONArray.getJSONObject(i).getString("date"),
                            thisJSONArray.getJSONObject(i).getString("team-1"),
                            thisJSONArray.getJSONObject(i).getString("team-2"),
                            thisJSONArray.getJSONObject(i).getBoolean("matchStarted"),
                            thisJSONArray.getJSONObject(i).getLong("unique_id")
                            );

//                    Log.d("SUKR", "Date: " + i + "" + thisJSONArray.getJSONObject(i).getString("date"));
//                    Log.d("SUKR", "Team-1 :   " + i + "    " + thisJSONArray.getJSONObject(i).getString("team-1"));
//                    Log.d("SUKR", "Team-2 :   " + i + "    " + thisJSONArray.getJSONObject(i).getString("team-2"));
//                    Log.d("SUKR", "MatchStarted:   " + i + "    " + thisJSONArray.getJSONObject(i).getBoolean("matchStarted"));
//
                   unique_id.add(thisJSONArray.getJSONObject(i).getLong("unique_id"));
                    Log.e("unique_id",unique_id.get(i).toString());

                    modelJson1.add(model);}
                Log.d("UNI1", "UNIQUE_ID SIZE: "+unique_id.size());

                Log.d("UNI-1", "Unique ids::: "+unique_id.get(0));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return modelJson1;
        }
    }

    public class DownloadData2 extends AsyncTask<String, Void, ArrayList<recentModel>> {
        @Override
        protected ArrayList<recentModel> doInBackground(String... params) {
            URL url1 = null;

            try {
                url1 = new URL(params[0]);
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
                jsonArray2 = getJson2(json);
                Log.d("TAG", "doInBackground2: " + json.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonArray2;

        }

        @Override
        protected void onPostExecute(ArrayList<recentModel> jsonArray) {
            super.onPostExecute(jsonArray);
            int i;
            no_of_matches = jsonArray.size();
            Log.d("TAG", "onPostExecute: " + jsonArray.size());
            for (i = 0; i < jsonArray.size(); i++) {
                adapterArray.add(jsonArray.get(i));
                adapter1.notifyDataSetChanged();
            }
        }

        public ArrayList<recentModel> getJson2(String json) {
            ArrayList<recentModel> modelJson2 = new ArrayList<>();

            try {
                JSONObject thisJSONObject = new JSONObject(json);


                recentModel model2 = new recentModel(

                        thisJSONObject.getString("team-1"),
                        thisJSONObject.getString("team-2"),
                        thisJSONObject.getBoolean("matchStarted"),
                        thisJSONObject.getString("innings-requirement"),
                        thisJSONObject.getString("score"),
                        thisJSONObject.getString("type"));

                Log.d("SUK", "innings-requirement: "  + "" + thisJSONObject.getString("innings-requirement"));
                Log.d("SUK", "Team-1 :   "  + "    " + thisJSONObject.getString("team-1"));
                Log.d("SUK", "Team-2 :   "  + "    " + thisJSONObject.getString("team-2"));
                Log.d("SUK", "MatchStarted:   "  + "    " + thisJSONObject.getBoolean("matchStarted"));
                Log.d("SUK", "Score: "+thisJSONObject.getString("score"));
               // unique_id.add(thisJSONObject.getLong("ttl"));
//                Log.d(TAG, "substring: "+(( thisJSONObject.getString("innings-requirement")).substring(0,6)));
                Log.e("unique_id",unique_id.get(0).toString());

                if(thisJSONObject.getBoolean("matchStarted")){
                    modelJson2.add(model2);}

                Log.d("UNI2", "UNIQUE_ID SIZE: "+unique_id.size());

                Log.d("UNI-2", "Unique ids::: "+unique_id.get(0));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return modelJson2;
        }
    }

}
*/
/*
public class Recent extends AppCompatActivity {

    static ArrayList<recentModel> jsonArray1 = new ArrayList<>();
    static ArrayList<recentModel> adapterArray = new ArrayList<>();
    static RecyclerView recentRecyclerView;
    static RecentRecyclerAdapter adapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent);
        recentRecyclerView= (RecyclerView) findViewById(R.id.rvModelList2);
//        recentRecyclerView.setLayoutManager(new LinearLayoutManager(Recent.this));
//        adapter1 = new RecentRecyclerAdapter(adapterArray, Recent.this);
//        recentRecyclerView.setAdapter(adapter1);

        Log.i("TFT", "Yahan pahauch gya in on Create()");
    }


    public static class DownloadTask extends AsyncTask<String, Void, ArrayList<recentModel>> {
        ArrayList<String> arrayList = new ArrayList<>();

        String result = "", result1 = "";
        URL url = null, url1 = null;
        HttpURLConnection httpURLConnection, httpURLConnection1;
        Context ctx;
        public DownloadTask(Context ctx)
        {
            this.ctx=ctx;
        }

        @Override
        protected ArrayList<recentModel> doInBackground(String... params) {
            try {
                url = new URL(params[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader isr = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String buffer;
                while ((buffer = reader.readLine()) != null) {
                    sb.append(buffer);
                }
                result = sb.toString();
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("matches");
                for (int i = 0; i < jsonArray.length(); i++) {
                    arrayList.add(Long.toString(jsonArray.getJSONObject(i).getLong("unique_id")));
                }
                Log.i("TAGS", arrayList.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.i("TAG:", "ArrayLIST size:" + arrayList.size());
            for (int i = 0; i < arrayList.size(); i++) {
                try {
                    url1 = new URL("http://cricapi.com/api/cricketScore?apikey=CjCdXi1JuqVuqOPFGRGfG5RtGu23&unique_id=" + arrayList.get(i).toString());
                    httpURLConnection1 = (HttpURLConnection) url1.openConnection();
                    InputStream inputStream1 = httpURLConnection1.getInputStream();
                    InputStreamReader in = new InputStreamReader(inputStream1);
                    BufferedReader reader = new BufferedReader(in);
                    StringBuilder sb = new StringBuilder();
                    String buffer;
                    while ((buffer = reader.readLine()) != null) {
                        sb.append(buffer);
                    }
                    result1 = sb.toString();
                    jsonArray1 = getJson(result1);
                    adapterArray.add(jsonArray1.get(i));
                    adapter1.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.i("TAG", "RESULT 1 =" + result1);
                Log.i("TAG","JsonArray: "+jsonArray1.toString());
            }
            return jsonArray1;
        }

        @Override
        protected void onPreExecute() {

            recentRecyclerView.setLayoutManager(new LinearLayoutManager(ctx));
            adapter1 = new RecentRecyclerAdapter(adapterArray, ctx);
            recentRecyclerView.setAdapter(adapter1);

        }

        @Override
        protected void onPostExecute(ArrayList<recentModel> jsonArray) {
            super.onPostExecute(jsonArray);
            int i,no_of_matches;
            no_of_matches = jsonArray.size();
//            for (i = 0; i < jsonArray.size(); i++) {
//                adapterArray.add(jsonArray.get(i));
//                adapter1.notifyDataSetChanged();
//            }
            Log.d("TAG", "onPostExecute: ");

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        public ArrayList<recentModel> getJson(String json) {
            ArrayList<recentModel> modelArrayList = new ArrayList<>();
            try {
                JSONObject thisJSONObject = new JSONObject(json);
                recentModel model2 = new recentModel(thisJSONObject.getString("team-1"),
                        thisJSONObject.getString("team-2"),
                        thisJSONObject.getBoolean("matchStarted"),
                        thisJSONObject.getString("innings-requirement"),
                        thisJSONObject.getString("score"),
                        thisJSONObject.getString("type"));
                    modelArrayList.add(model2);


            } catch (Exception e) {
                e.printStackTrace();
            }
            return modelArrayList;
        }
    }
}

*/

public class Recent extends AppCompatActivity {

    static ArrayList<recentModel> jsonArray1 = new ArrayList<>();
    static ArrayList<recentModel> adapterArray = new ArrayList<>();
    static ListView listView;
    static ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent);
        listView= (ListView) findViewById(R.id.listView);
//        recentRecyclerView.setLayoutManager(new LinearLayoutManager(Recent.this));
//        adapter1 = new RecentRecyclerAdapter(adapterArray, Recent.this);
//        recentRecyclerView.setAdapter(adapter1);

        Log.i("TFT", "Yahan pahauch gya in on Create()");
    }


    public static class DownloadTask extends AsyncTask<String, Void, ArrayList<recentModel>> {
        ArrayList<String> arrayList = new ArrayList<>();

        String result = "", result1 = "";
        URL url = null, url1 = null;
        HttpURLConnection httpURLConnection, httpURLConnection1;
        Context ctx;
        public DownloadTask(Context ctx)
        {
            this.ctx=ctx;
        }

        @Override
        protected ArrayList<recentModel> doInBackground(String... params) {
            try {
                url = new URL(params[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader isr = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String buffer;
                while ((buffer = reader.readLine()) != null) {
                    sb.append(buffer);
                }
                result = sb.toString();
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("matches");
                for (int i = 0; i < jsonArray.length(); i++) {
                    arrayList.add(Long.toString(jsonArray.getJSONObject(i).getLong("unique_id")));
                }
                Log.i("TAGS", arrayList.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.i("TAG:", "ArrayLIST size:" + arrayList.size());
            for (int i = 0; i < arrayList.size(); i++) {
                try {
                    url1 = new URL("http://cricapi.com/api/cricketScore?apikey=CjCdXi1JuqVuqOPFGRGfG5RtGu23&unique_id=" + arrayList.get(i).toString());
                    httpURLConnection1 = (HttpURLConnection) url1.openConnection();
                    InputStream inputStream1 = httpURLConnection1.getInputStream();
                    InputStreamReader in = new InputStreamReader(inputStream1);
                    BufferedReader reader = new BufferedReader(in);
                    StringBuilder sb = new StringBuilder();
                    String buffer;
                    while ((buffer = reader.readLine()) != null) {
                        sb.append(buffer);
                    }
                    result1 = sb.toString();
                    jsonArray1 = getJson(result1);
                    adapterArray.add(jsonArray1.get(i));
                    arrayAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.i("TAG", "RESULT 1 =" + result1);
                Log.i("TAG","JsonArray: "+jsonArray1.toString());
            }
            return jsonArray1;
        }

        @Override
        protected void onPreExecute() {

            recentRecyclerView.setLayoutManager(new LinearLayoutManager(ctx));
            adapter1 = new RecentRecyclerAdapter(adapterArray, ctx);
            recentRecyclerView.setAdapter(adapter1);

        }

        @Override
        protected void onPostExecute(ArrayList<recentModel> jsonArray) {
            super.onPostExecute(jsonArray);
            int i,no_of_matches;
            no_of_matches = jsonArray.size();
//            for (i = 0; i < jsonArray.size(); i++) {
//                adapterArray.add(jsonArray.get(i));
//                adapter1.notifyDataSetChanged();
//            }
            Log.d("TAG", "onPostExecute: ");

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        public ArrayList<recentModel> getJson(String json) {
            ArrayList<recentModel> modelArrayList = new ArrayList<>();
            try {
                JSONObject thisJSONObject = new JSONObject(json);
                recentModel model2 = new recentModel(thisJSONObject.getString("team-1"),
                        thisJSONObject.getString("team-2"),
                        thisJSONObject.getBoolean("matchStarted"),
                        thisJSONObject.getString("innings-requirement"),
                        thisJSONObject.getString("score"),
                        thisJSONObject.getString("type"));
                modelArrayList.add(model2);


            } catch (Exception e) {
                e.printStackTrace();
            }
            return modelArrayList;
        }
    }
}

