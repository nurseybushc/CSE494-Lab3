package edu.asu.bscs.a1203737023.lab3;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by Chance Nursey-Bush on 2/8/16.
 * @author   Chance Nursey-Bush    mailto:cnurseyb@asu.edu.
 * @version Feb 8, 2016
 * Copyright 2016 Chance Nursey-Bush
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class Movie {

    public String title;
    public String year;
    public String rated;
    public String released;
    public String runtime;
    public String genre;
    public String actors;
    public String plot;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }


    public Movie(String jsonString) {
        try {
            JSONObject jo = new JSONObject(jsonString);
            title = jo.getString("Title");
            year = jo.getString("Year");
            rated = jo.getString("Rated");
            released = jo.getString("Released");
            runtime = jo.getString("Runtime");
            genre = jo.getString("Genre");
            actors = jo.getString("Actors");
            plot = jo.getString("Plot");
        } catch(Exception e) {
            Log.w(this.getClass().getSimpleName(), "error converting to/from json");
        }
    }

    public Movie(String title, String year, String rated, String released, String runtime, String genre, String actors, String plot){
        this.title = title;
        this.year = year;
        this.rated = rated;
        this.released = released;
        this.runtime = runtime;
        this.genre = genre;
        this.actors = actors;
        this.plot = plot;
    }

    public String toJsonString() {
        String ret  = "";
        try {
            JSONObject jo = new JSONObject();
            jo.put("title", title);
            jo.put("year", year);
            jo.put("rated", rated);
            jo.put("released", released);
            jo.put("runtime", runtime);
            jo.put("genre", genre);
            jo.put("actors", actors);
            jo.put("plot", plot);

            ret = jo.toString();
        } catch (Exception e){
            Log.w(this.getClass().getSimpleName(), "error converting to/from json");
        }
        return ret;
    }
    public Movie() {

    }
}
