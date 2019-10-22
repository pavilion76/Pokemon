package com.example.perhapthisonewillwork;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
public class GetPokemonAsync extends AsyncTask<URL, Integer, String>{
    private String page = "";
    @Override
    protected String doInBackground(URL... urls) {
        try{
            OkHttpClient client = new OkHttpClient();
            String url ="https://pokeapi.co/api/v2/pokemon/ditto/";
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Call call = client.newCall(request);
            Response response = call.execute();
            ResponseBody body = response.body();
            page=body.toString();
        }catch (IOException e){
            e.getMessage();
        }finally {
            //cleanup
        }
        return null;
    }

    protected void onPostExecute(String result){

    }
}
