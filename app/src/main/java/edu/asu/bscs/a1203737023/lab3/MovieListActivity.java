package edu.asu.bscs.a1203737023.lab3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by apsMac1 on 2/9/16.
 */
public class MovieListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movielist);
    }

    public void buttonClicked(View v){
        finish();
    }
}
