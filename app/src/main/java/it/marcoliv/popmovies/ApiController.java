package it.marcoliv.popmovies;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import it.marcoliv.popmovies.model.KMovies;
import it.marcoliv.popmovies.model.MessageEvent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by marcoliv on 10/3/2016.
 */

public class ApiController {
    private static final String LOG_TAG = ApiController.class.getSimpleName();

    public static void getPopularMovies(){
        Call<KMovies> call = MoviedbApi.getInstance().getService().getPopular(BuildConfig.THE_MOVIE_DB_API_KEY);
        Log.d(LOG_TAG, "call: " + call.request().toString());
        call.enqueue(new Callback<KMovies>() {
            @Override
            public void onResponse(Call<KMovies> call, Response<KMovies> response) {
                int statusCode = response.code();
                KMovies movies = response.body();

                EventBus.getDefault().post(new MessageEvent(movies.getResults().get(0).getTitle()));

                Log.d(LOG_TAG, "onResponse - statusCode: "+ statusCode);
                Log.d(LOG_TAG, "first movie: " + movies.getResults().get(0).getTitle());
            }

            @Override
            public void onFailure(Call<KMovies> call, Throwable t) {
                Log.d(LOG_TAG, "onFailure - message: "+ t.getMessage());
            }
        });
    }
}
