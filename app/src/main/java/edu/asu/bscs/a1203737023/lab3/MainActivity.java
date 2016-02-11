package edu.asu.bscs.a1203737023.lab3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText et1, et2, et3, et4, et5, et6, et7, et8;
    String movieJson;
    Movie m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.et_title);
        et2 = (EditText) findViewById(R.id.et_year);
        et3 = (EditText) findViewById(R.id.et_rated);
        et4 = (EditText) findViewById(R.id.et_released);
        et5 = (EditText) findViewById(R.id.et_runtime);
        et6 = (EditText) findViewById(R.id.et_genre);
        et7 = (EditText) findViewById(R.id.et_actors);
        et8 = (EditText) findViewById(R.id.et_plot);

        movieJson = "[" +
                "{\"Title\":\"Memento\",\"Year\":\"2000\",\"Rated\":\"R\",\"Released\":\"25 May 2001\",\"Runtime\":\"113 min\",\"Genre\":\"Mystery, Thriller\", \"Actors\":\"Guy Pearce, Carrie-Anne Moss, Joe Pantoliano, Mark Boone Junior\",\"Plot\":\"A man creates a strange system to help him remember things; so he can hunt for the murderer of his wife without his short-term memory loss being an obstacle.\"}" +
                ",{\"Title\":\"Memento\",\"Year\":\"2000\",\"Rated\":\"R\",\"Released\":\"25 May 2001\",\"Runtime\":\"113 min\",\"Genre\":\"Mystery, Thriller\", \"Actors\":\"Guy Pearce, Carrie-Anne Moss, Joe Pantoliano, Mark Boone Junior\",\"Plot\":\"A man creates a strange system to help him remember things; so he can hunt for the murderer of his wife without his short-term memory loss being an obstacle.\"}" +
                ",{\"Title\":\"Memento\",\"Year\":\"2000\",\"Rated\":\"R\",\"Released\":\"25 May 2001\",\"Runtime\":\"113 min\",\"Genre\":\"Mystery, Thriller\", \"Actors\":\"Guy Pearce, Carrie-Anne Moss, Joe Pantoliano, Mark Boone Junior\",\"Plot\":\"A man creates a strange system to help him remember things; so he can hunt for the murderer of his wife without his short-term memory loss being an obstacle.\"}" +
                "]";
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        android.util.Log.d(this.getClass().getSimpleName(), "called onCreateOptionsMenu()");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(this.getClass().getSimpleName(), "called onOptionsItemSelected()");

        Intent myIntent = new Intent(MainActivity.this, MovieListActivity.class);
        MainActivity.this.startActivity(myIntent);
        return true;
    }


    public void createFile(View v) {
        m = new Movie(et1.getText().toString(),
                et2.getText().toString(), et3.getText().toString(),
                et4.getText().toString(), et5.getText().toString(), et6.getText().toString(),
                et7.getText().toString(), et8.getText().toString());

        String filename = "movie.json";
        String string = m.toJsonString();

        try {

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(filename, Context.MODE_PRIVATE));
            outputStreamWriter.write(string);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public void readFile(View v) {
        /*try {
            BufferedReader buf = new BufferedReader(new FileReader("movie.json"));
            Log.w("readFile", buf.readLine());
        } catch (Exception ex) {
            Log.e("readFile", "error reading file");
        }*/
        String ret = "";

        try {
            InputStream inputStream = openFileInput("newmovies.json");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        Log.w("ReadFile", ret);

    }

    public void clearEditTexts(View v){
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        et5.setText("");
        et6.setText("");
        et7.setText("");
        et8.setText("");
    }

    public boolean checkIfFileExists(View v) {
        File file1 = new File("movie.json");
        if(file1.exists()) {
            Log.w("CheckIfFileExists", "movie.json Exists");
            return true;
        }
        //Do something
        else {
            Log.w("CheckIfFileExists", "movie.json Doesn't Exist");
            return false;
        }
    }

    public MovieLibrary loadRawMovieJson(View v){
        InputStream raw =  getResources().openRawResource(R.raw.movies);
        BufferedReader rd = new BufferedReader(new InputStreamReader(raw));

        //GsonBuilder gsonBuilder = new GsonBuilder();
        //gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        //Gson gson = gsonBuilder.create();
        MovieLibrary movieLib = new MovieLibrary();
        //movieLib.setMovieList(Arrays.asList(gson.fromJson(rd, Movie[].class)));
        //Movie[] movieList = gson.fromJson(rd, Movie[].class);
        /*
        String title;
        String year;
        String rated;
        String released;
        String runtime;
        String genre;
        String actors;
        String plot;
        try {
            JSONArray array = new JSONArray(getResources().openRawResource(R.raw.movies));
            for (int i = 0; i < array.length(); i++) {
                JSONObject row = array.getJSONObject(i);
                title = row.getString("Title");
                year = row.getString("Year");
                rated = row.getString("Rated");
                released = row.getString("Released");
                runtime = row.getString("Runtime");
                genre = row.getString("Genre");
                actors = row.getString("Actors");
                plot = row.getString("Plot");
                movieLib.addMovie(new Movie(title,year, rated, released, runtime, genre, actors, plot));
            }
        } catch (JSONException ex) {
            Log.e("readJSON", "EXCEPTION");
        }


        //logger.info(movieList);
        //Log.w("loadRawMovieJson", movieList.toJsonString());
        Log.w("loadRawMovieJson", "" + movieLib.getSize());
        //for(int i = 0; i < movieList.getSize; ++i) {
        //    movieLib.addMovie(movieList[i]);
        //}
        return movieLib;
        */
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(raw, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException ex) {
            Log.e("loadJson", "unsupported something");
        } catch (IOException ex) {
            Log.e("loadJson", "trying to read buffer");
        } finally {
            try {
                raw.close();
            } catch (IOException ex) {
                Log.e("loadJson", "trying to close file");
            }
        }

        String jsonString = writer.toString();
        Log.w("loadJson", jsonString);
        Gson gson = new Gson();
        //movieLib.setMovieList(Arrays.asList(gson.fromJson(jsonString, Movie[].class)));
        Movie[] movieList2 = gson.fromJson(movieJson, Movie[].class);
        JSONArray jarray;
        try {
            jarray = new JSONArray(movieJson);
            for(int i = 0; i < jarray.length(); ++i)
                movieLib.addMovie(new Movie(jarray.get(i).toString()));
            System.out.println("me here");
        } catch (JSONException ex){
            Log.e("loadJson", "unsupported something");
        }
        return movieLib;
    }
}
