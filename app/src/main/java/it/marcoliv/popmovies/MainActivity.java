package it.marcoliv.popmovies;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.marcoliv.popmovies.model.KMovies;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    String[] mockUrl = {
            "http://i.imgur.com/rFLNqWI.jpg",
            "http://i.imgur.com/C9pBVt7.jpg",
            "http://i.imgur.com/rT5vXE1.jpg",
            "http://i.imgur.com/aIy5R2k.jpg",
            "http://i.imgur.com/MoJs9pT.jpg",
            "http://i.imgur.com/S963yEM.jpg",
            "http://i.imgur.com/rLR2cyc.jpg",
            "http://i.imgur.com/SEPdUIx.jpg",
            "http://i.imgur.com/aC9OjaM.jpg",
            "http://i.imgur.com/76Jfv9b.jpg",
            "http://i.imgur.com/fUX7EIB.jpg",
            "http://i.imgur.com/syELajx.jpg",
            "http://i.imgur.com/COzBnru.jpg",
            "http://i.imgur.com/Z3QjilA.jpg",
    };

    @BindView(R.id.gridview) GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_view_layout);
        ButterKnife.bind(this);

        ImageAdapter mImageAdapter = new ImageAdapter(this,mockUrl);

        gridView.setAdapter(mImageAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Your selected position is: "+i, Toast.LENGTH_SHORT).show();
            }
        });

        Log.d(LOG_TAG, "my Api key: "+BuildConfig.THE_MOVIE_DB_API_KEY);

        testApiPopularMovies();
    }

    private void testApiPopularMovies(){
        Call<KMovies> call = MoviedbApi.getInstance().getService().getPopular(BuildConfig.THE_MOVIE_DB_API_KEY);
        Log.d(LOG_TAG, "call: " + call.request().toString());
        call.enqueue(new Callback<KMovies>() {
            @Override
            public void onResponse(Call<KMovies> call, Response<KMovies> response) {
                int statusCode = response.code();
                KMovies movies = response.body();

                Log.d(LOG_TAG, "onResponse statusCode: "+ statusCode);
                Log.d(LOG_TAG, "first movie: " + movies.getResults().get(0).getTitle());
            }

            @Override
            public void onFailure(Call<KMovies> call, Throwable t) {
                Log.d(LOG_TAG, "onFailure: "+ t.getMessage());
            }
        });
    }
}
