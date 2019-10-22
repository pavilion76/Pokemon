package com.example.perhapthisonewillwork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;



public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private String result;
    private URL url;
    private TextView resultText;
    private TextView pokemonName;
    myAsyncTask task = new myAsyncTask(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button getPokedex = (Button) findViewById(R.id.submit);
        pokemonName = (TextView) findViewById(R.id.pokemonName);
        resultText = (TextView) findViewById(R.id.resultText);
        progressBar = findViewById(R.id.progress_bar);

        getPokedex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetPokemonAsync thread = new GetPokemonAsync();
                String result = "";
                try {
                    url = new URL("https://pokeapi.co/api/v2/pokemon/ditto/");
                    //thread2.doInBackground(url);
                    //thread.execute(url);

                    task.execute(url);
                } catch (IOException e) {
                    e.getMessage();
                }
            }
        });
    }


    //<input, progress, result>
    private class myAsyncTask extends AsyncTask<URL, Integer, String>{
        private WeakReference<MainActivity> activityWeakReference;
        myAsyncTask(MainActivity activity){
            activityWeakReference = new WeakReference<>(activity);
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            MainActivity activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing())
                return;

            activity.progressBar.setVisibility(View.VISIBLE);
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            MainActivity activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing())
                return;

            progressBar.setProgress(values[0]);
        }
        @Override
        protected String doInBackground(URL... urls) {
            publishProgress(0);
            try{
                OkHttpClient client = new OkHttpClient();
                String url ="https://pokeapi.co/api/v2/pokemon/ditto/";
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                publishProgress(10);

                Call call = client.newCall(request);
                Response response = call.execute();
                ResponseBody body = response.body();
                String page=body.toString();
            }catch (IOException e){
                e.getMessage();
            }finally {
                //cleanup
            }
            return "finished";
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            MainActivity activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing())
                return;

            activity.progressBar.setVisibility(View.INVISIBLE);
            activity.resultText.setText("Hey it actually made it to onPostExecute");
            activity.progressBar.setProgress(0);
        }
    }
}

/*
List Adapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, [array]);

 */
