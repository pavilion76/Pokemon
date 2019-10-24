package com.example.perhapthisonewillwork;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PokemonTypes extends AppCompatActivity {
    private URL url;
    private String result;
    private TextView resultText;
    PokemonTypes.myAsyncTask task = new PokemonTypes.myAsyncTask(this);
    private PokemonTypes myActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_types);
        Button normal = (Button) findViewById(R.id.normal);
        Button fighting= (Button) findViewById(R.id.fighting);
        Button flying = (Button) findViewById(R.id.flying);
        Button poison= (Button) findViewById(R.id.poison);
        Button ground = (Button) findViewById(R.id.ground);
        Button rock= (Button) findViewById(R.id.rock);
        Button bug = (Button) findViewById(R.id.bug);
        Button ghost = (Button) findViewById(R.id.ghost);
        Button steel= (Button) findViewById(R.id.steel);
        Button fire = (Button) findViewById(R.id.fire);
        Button water= (Button) findViewById(R.id.water);
        Button grass = (Button) findViewById(R.id.grass);
        Button electric= (Button) findViewById(R.id.electric);
        Button psychic = (Button) findViewById(R.id.psychic);
        Button ice= (Button) findViewById(R.id.ice);
        Button dragon = (Button) findViewById(R.id.dragon);
        Button dark= (Button) findViewById(R.id.dark);
        Button fairy = (Button) findViewById(R.id.fairy);
        Button unknown= (Button) findViewById(R.id.unknown);
        Button shadow = (Button) findViewById(R.id.shadow);
        myActivity = this;

        resultText = (TextView) findViewById(R.id.typeResults);
        //20 onclick listeners... one for each button. There is probably a better way to handle this, but I don't have the time to figure out what that method is.
        {
            normal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        url = new URL("https://pokeapi.co/api/v2/type/1");
                        myAsyncTask task = new myAsyncTask(myActivity);
                        task.execute(url);
                    } catch (IOException e) {
                    }

                }
            });
        }
        {
            fighting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        url = new URL("https://pokeapi.co/api/v2/type/2");
                        myAsyncTask task = new myAsyncTask(myActivity);
                        task.execute(url);
                    } catch (IOException e) {
                    }

                }
            });
        }
        {
            flying.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        url = new URL("https://pokeapi.co/api/v2/type/3");
                        myAsyncTask task = new myAsyncTask(myActivity);
                        task.execute(url);
                    } catch (IOException e) {
                    }

                }
            });
        }
        {
            poison.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        url = new URL("https://pokeapi.co/api/v2/type/4");
                        myAsyncTask task = new myAsyncTask(myActivity);
                        task.execute(url);
                    } catch (IOException e) {
                    }
                }
            });
        }
        {
            ground.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        url = new URL("https://pokeapi.co/api/v2/type/5");
                        myAsyncTask task = new myAsyncTask(myActivity);
                        task.execute(url);
                    } catch (IOException e) {
                    }
                }
            });
        }
        {
            rock.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        url = new URL("https://pokeapi.co/api/v2/type/6");
                        myAsyncTask task = new myAsyncTask(myActivity);
                        task.execute(url);
                    } catch (IOException e) {
                    }
                }
            });
        }
        {
            bug.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        url = new URL("https://pokeapi.co/api/v2/type/7");
                        myAsyncTask task = new myAsyncTask(myActivity);
                        task.execute(url);
                    } catch (IOException e) {
                    }
                }
            });
        }
        {
            ghost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        url = new URL("https://pokeapi.co/api/v2/type/8");
                        myAsyncTask task = new myAsyncTask(myActivity);
                        task.execute(url);
                    } catch (IOException e) {
                    }
                }
            });
        }
        {
            steel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        url = new URL("https://pokeapi.co/api/v2/type/9");
                        myAsyncTask task = new myAsyncTask(myActivity);
                        task.execute(url);
                    } catch (IOException e) {
                    }
                }
            });
        }
        {
            fire.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        url = new URL("https://pokeapi.co/api/v2/type/10");
                        myAsyncTask task = new myAsyncTask(myActivity);
                        task.execute(url);
                    } catch (IOException e) {
                    }
                }
            });
        }
        {
            water.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        url = new URL("https://pokeapi.co/api/v2/type/11");
                        myAsyncTask task = new myAsyncTask(myActivity);
                        task.execute(url);
                    } catch (IOException e) {
                    }
                }
            });
        }
        {
            grass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        url = new URL("https://pokeapi.co/api/v2/type/12");
                        myAsyncTask task = new myAsyncTask(myActivity);
                        task.execute(url);
                    } catch (IOException e) {
                    }
                }
            });
        }
        {
            electric.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        url = new URL("https://pokeapi.co/api/v2/type/13");
                        myAsyncTask task = new myAsyncTask(myActivity);
                        task.execute(url);
                    } catch (IOException e) {
                    }
                }
            });
        }
        {
            psychic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        url = new URL("https://pokeapi.co/api/v2/type/14");
                        myAsyncTask task = new myAsyncTask(myActivity);
                        task.execute(url);
                    } catch (IOException e) {
                    }
                }
            });
        }
        {
            ice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        url = new URL("https://pokeapi.co/api/v2/type/15");
                        myAsyncTask task = new myAsyncTask(myActivity);
                        task.execute(url);
                    } catch (IOException e) {
                    }
                }
            });
        }
        {
            dragon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        url = new URL("https://pokeapi.co/api/v2/type/16");
                        myAsyncTask task = new myAsyncTask(myActivity);
                        task.execute(url);
                    } catch (IOException e) {
                    }
                }
            });
        }
        {
            dark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        url = new URL("https://pokeapi.co/api/v2/type/17");
                        myAsyncTask task = new myAsyncTask(myActivity);
                        task.execute(url);
                    } catch (IOException e) {
                    }
                }
            });
        }
        {
            fairy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        url = new URL("https://pokeapi.co/api/v2/type/18");
                        myAsyncTask task = new myAsyncTask(myActivity);
                        task.execute(url);
                    } catch (IOException e) {
                    }
                }
            });
        }
        {
            unknown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        url = new URL("https://pokeapi.co/api/v2/type/10001");
                        myAsyncTask task = new myAsyncTask(myActivity);
                        task.execute(url);
                    } catch (IOException e) {
                    }
                }
            });
        }
        {
            shadow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        url = new URL("https://pokeapi.co/api/v2/type/10002");
                        myAsyncTask task = new myAsyncTask(myActivity);
                        task.execute(url);
                    } catch (IOException e) {
                    }
                }
            });
        }


    }

    //Just copied this from the main activity, just switched the weak referenec to this.
    private class myAsyncTask extends AsyncTask<URL, Integer, String> {
        private WeakReference<PokemonTypes> activityWeakReference;
        myAsyncTask(PokemonTypes activity){
            activityWeakReference = new WeakReference<>(activity);
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            PokemonTypes activity = activityWeakReference.get();
            //clear the previous entry.
            activity.resultText.setText("");
            if (activity == null || activity.isFinishing())
                return;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            PokemonTypes activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing())
                return;
        }
        @Override
        protected String doInBackground(URL... urls) {
            publishProgress(0);
            try{
                OkHttpClient client = new OkHttpClient();
                GetPokemonAsync thread = new GetPokemonAsync();
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                publishProgress(10);
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        e.printStackTrace();
                    }
                    @Override
                    public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
                        if(response.isSuccessful()){
                            final String myResponse = response.body().string();
                            PokemonTypes.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    jsonParse(myResponse);
                                }
                            });
                        }
                    }
                });
                throw new Exception("Why?");
            }catch (Exception e){
                //resultText.setText("died in doInBackground()"+ e.getMessage());
            }finally {
                //cleanup
            }
            return "finished";
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            PokemonTypes activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing())
                return;
            //activity.resultText.setText("Hey it actually made it to onPostExecute\n\n");
        }
    }
    private void jsonParse(String myResponse){
        try {
            //resultText.setText(myResponse);
            //damage_relations
            result = myResponse;
            JSONObject obj = new JSONObject(result);
            JSONObject damage_relations = obj.getJSONObject("damage_relations");
            JSONArray jsonArray = damage_relations.getJSONArray("double_damage_from");
            resultText.append("Double damage from\n");
            for(int i=0; i<jsonArray.length();i++){
                JSONObject ability = jsonArray.getJSONObject(i);
                String nameOfAbility = ability.getString("name");
                resultText.append(nameOfAbility + "\n");
            }
            resultText.append("\n\n");

            jsonArray = damage_relations.getJSONArray("half_damage_to");
            resultText.append("half damage to\n");
            for(int i=0; i<jsonArray.length();i++){
                JSONObject ability = jsonArray.getJSONObject(i);
                String nameOfAbility = ability.getString("name");
                resultText.append(nameOfAbility + "\n");
            }
            resultText.append("\n\n");

        }catch (JSONException e){
            resultText.setText(e.getMessage());
        }
    }


}
