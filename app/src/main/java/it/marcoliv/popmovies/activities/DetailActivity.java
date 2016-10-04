package it.marcoliv.popmovies.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import it.marcoliv.popmovies.Constants;
import it.marcoliv.popmovies.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }

    @Override
    protected void onStart() {
        super.onStart();

        String mMovie = getIntent().getStringExtra(Constants.EXTRA_MESSAGE);

        Toast.makeText(this, mMovie, Toast.LENGTH_SHORT).show();
    }
}
