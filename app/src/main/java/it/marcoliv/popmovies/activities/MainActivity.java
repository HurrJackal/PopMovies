package it.marcoliv.popmovies.activities;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import it.marcoliv.popmovies.Constants;
import it.marcoliv.popmovies.network.ApiController;
import it.marcoliv.popmovies.ImageAdapter;
import it.marcoliv.popmovies.R;
import it.marcoliv.popmovies.model.KMovie;
import it.marcoliv.popmovies.model.KMovies;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.gridview) GridView gridView;
    ImageAdapter mImageAdapter = null;
    List<KMovie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_view_layout);
        ButterKnife.bind(this);

        Moshi moshi = new Moshi.Builder().build();
        final JsonAdapter<KMovie> jsonAdapter = moshi.adapter(KMovie.class);

        mImageAdapter = new ImageAdapter(this, movies);

        gridView.setAdapter(mImageAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(MainActivity.this, "Your selected position is: "+ position, Toast.LENGTH_SHORT).show();

                // circularPosition mantain position counter inside the array range, like a circular array
                int circularPosition = position % movies.size();

                String mMovie = jsonAdapter.toJson(movies.get(circularPosition));

                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra(Constants.EXTRA_MESSAGE, mMovie);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ApiController.getPopularMovies();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    // This method will be called when a KMessageEvent is posted
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(KMovies messageMovies){
        Log.d(LOG_TAG, "onMessageEvent - messageEmpty? " + movies.isEmpty());

        // load data locally
        movies = messageMovies.getResults();

        // dynamically update adapter
        mImageAdapter.addAll(movies);
    }


}
