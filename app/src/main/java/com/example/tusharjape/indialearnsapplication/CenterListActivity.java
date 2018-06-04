package com.example.tusharjape.indialearnsapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.net.ssl.HttpsURLConnection;

public class CenterListActivity extends AppCompatActivity {

    ArrayList<CenterDetail> centerDetailArrayList;
    String beginLat, beginLong, endLat, endLong, centerPlaceName, startPlaceName, endPlaceName, city, name, contact, startTime;
    int i;
    URL url;
    InputStream input;
    JsonReader jsonReader;
    HttpURLConnection connection;
    String TAG = "CenterListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_list);

        centerDetailArrayList = new ArrayList<>();
        if(getIntent()!=null){
            beginLat = getIntent().getStringExtra("beginLat");
            beginLong = getIntent().getStringExtra("beginLong");
            endLat = getIntent().getStringExtra("endLat");
            endLong = getIntent().getStringExtra("endLong");
            city = getIntent().getStringExtra("city");
            name = getIntent().getStringExtra("name");
            contact = getIntent().getStringExtra("contact");
            startTime = getIntent().getStringExtra("starttime");
        }

        if(isConnectionAvailable()) {
            new AsyncFetch1().execute();
            Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();
            new AsyncFetch().execute();
            Toast.makeText(this, "Done 2!", Toast.LENGTH_SHORT).show();
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        RecyclerView_Adapter adapter = new RecyclerView_Adapter(centerDetailArrayList, beginLat, beginLong, endLat, endLong);
        recyclerView.setAdapter(adapter);

    }

    private void readData() {
        try {
            jsonReader.nextString();
            jsonReader.nextName();
            jsonReader.beginArray();

            while(jsonReader.hasNext()) {
                jsonReader.beginObject();

                String lat, longi, id;
                jsonReader.nextName();
                lat = jsonReader.nextString();

                jsonReader.nextName();
                longi = jsonReader.nextString();

                jsonReader.nextName();
                id = jsonReader.nextString();

                CenterDetail newCenter = new CenterDetail(lat, longi, id);
                centerDetailArrayList.add(newCenter);

                jsonReader.endObject();
            }

            jsonReader.endArray();
            jsonReader.endObject();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    private void sort() {

        int sz = centerDetailArrayList.size();
        for(i=0; i<sz; i++){

            for(int j=i+1; j<sz; j++){
                if(centerDetailArrayList.get(j).travelTime<centerDetailArrayList.get(i).travelTime){
                    CenterDetail temp = centerDetailArrayList.get(i);
                    centerDetailArrayList.remove(i);
                    centerDetailArrayList.add(i, centerDetailArrayList.get(j));
                    centerDetailArrayList.remove(j);
                    centerDetailArrayList.add(j, temp);
                }
            }

        }

    }

    private boolean isConnectionAvailable(){
        ConnectivityManager ConnectionManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private class AsyncFetch1 extends AsyncTask<Void, Void, Void>{
        /**
         * Runs on the UI thread before {@link #doInBackground}.
         *
         * @see #onPostExecute
         * @see #doInBackground
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * <p>Runs on the UI thread after {@link #doInBackground}. The
         * specified result is the value returned by {@link #doInBackground}.</p>
         * <p>
         * <p>This method won't be invoked if the task was cancelled.</p>
         *
         * @param aVoid The result of the operation computed by {@link #doInBackground}.
         * @see #onPreExecute
         * @see #doInBackground
         * @see #onCancelled(Object)
         */
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param voids The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected Void doInBackground(Void... voids) {

            try {
                url = new URL("http://172.17.21.248/and_connect/teacher_apply.php");
            }
            catch (MalformedURLException e){
                e.printStackTrace();
            }

            try{
                connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(15000);
                connection.setConnectTimeout(15000);
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);

                try {
                    if (input==null)
                        input = url.openStream();
                    else
                        Log.e(TAG, "InputStream problem");}
                catch (IOException e) {
                    Log.e(TAG, "IOException");
                    Log.e(TAG, e.toString());
                }

                int start = Integer.parseInt(startTime);

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("CITY_NAME", city)
                        .appendQueryParameter("STIME", startTime)
                        .appendQueryParameter("ETIME", Integer.toString(start+1));
                String query = builder.build().getEncodedQuery();

                OutputStream os = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                connection.connect();
            }
            catch(Exception e){
                e.printStackTrace();
            }
            try{
                int response_code = connection.getResponseCode();
                if(response_code == HttpsURLConnection.HTTP_OK){
                    input = connection.getInputStream();
                    try {
                        jsonReader = new JsonReader(new InputStreamReader(input, "UTF-8"));
                        jsonReader.setLenient(true);
                        readData();
                    }
                    catch (UnsupportedEncodingException e){
                        e.printStackTrace();
                    }
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
            finally {
                connection.disconnect();
            }

            return null;
        }
    }

    private class AsyncFetch extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            sort();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            int sz = centerDetailArrayList.size();
            for(int i=0; i<sz; i++) {
                establishConnection();
                processTimeAndDistance();
            }
            return null;
        }

        private void establishConnection() {
            try {
                String urlDirectionMatrix = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="
                        +beginLat+","+beginLong+"|"+endLat+","+endLong+"&destinations="+centerDetailArrayList.get(i).latitude+","
                        +centerDetailArrayList.get(i).longitude+"&key=AIzaSyDweq0YZLAwCeEZ3oc_yQ0Je2zUAsaVi4A";
                url = new URL(urlDirectionMatrix);}
            catch (MalformedURLException e) {
                Log.e(TAG, e.toString());}
            Log.i(TAG, "URL is ok");

            try {
                if (input==null)
                    input = url.openStream();
                else
                    Log.i(TAG, "InputStream problem");}
            catch (IOException e) {
                Log.e(TAG, "IOException");
                Log.e(TAG, e.toString());
            }

            Log.i(TAG, "Connection successful");

            try {jsonReader = new JsonReader(new InputStreamReader(input, "UTF-8"));
                jsonReader.setLenient(true);}
            catch (UnsupportedEncodingException e) {
                Log.e(TAG, "Error in json reader");
                Log.e(TAG, e.getMessage());}

            Log.i(TAG, "Json Reader made");
        }

        private void processTimeAndDistance(){

            try {
                jsonReader.beginObject();
                jsonReader.nextName();
                jsonReader.beginArray();
                centerPlaceName = jsonReader.nextString();
                jsonReader.endArray();
                jsonReader.nextName();
                jsonReader.beginArray();
                startPlaceName = jsonReader.nextString();
                endPlaceName = jsonReader.nextString();
                jsonReader.endArray();
                Log.i(TAG, centerPlaceName+"\t"+startPlaceName+"\t"+endPlaceName);
                jsonReader.nextName();
                int n=2;
                jsonReader.beginArray();
                while(n-->0){
                    jsonReader.beginObject();
                    jsonReader.nextName();
                    jsonReader.beginArray();
                    jsonReader.beginObject();
                    jsonReader.nextName();
                    jsonReader.beginObject();
                    jsonReader.nextName();
                    StringTokenizer tokenizer = new StringTokenizer(jsonReader.nextString());
                    centerDetailArrayList.get(i).distance += Double.parseDouble(tokenizer.nextToken());
                    //Log.i(TAG, jsonReader.nextString());//Distance 1
                    jsonReader.nextName();
                    jsonReader.nextInt();
                    jsonReader.endObject();

                    jsonReader.nextName();
                    jsonReader.beginObject();
                    jsonReader.nextName();
                    tokenizer = new StringTokenizer(jsonReader.nextString());
                    centerDetailArrayList.get(i).travelTime += Double.parseDouble(tokenizer.nextToken());
                    //Log.i(TAG, jsonReader.nextString());//Duration 1

                    jsonReader.nextName();
                    jsonReader.nextInt();
                    jsonReader.endObject();

                    jsonReader.nextName();
                    jsonReader.nextString();
                    jsonReader.endObject();
                    jsonReader.endArray();
                    jsonReader.endObject();

                }
                jsonReader.endArray();
                Log.i(TAG, Double.toString(centerDetailArrayList.get(i).travelTime));
                Log.i(TAG, Double.toString(centerDetailArrayList.get(i).distance));
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}