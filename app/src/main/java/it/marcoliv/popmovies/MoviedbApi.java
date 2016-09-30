package it.marcoliv.popmovies;

import it.marcoliv.popmovies.model.KMovies;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by marcoliv on 9/23/2016.
 *
 */

public class MoviedbApi {
    private static final String BASE_URL = "https://api.themoviedb.org/";
    private static MoviedbApi instance =  null;
    private MoviedbService service;

    public interface MoviedbService {
        @GET("3/movie/popular?")
        Call<KMovies> getPopular(@Query("api_key") String api_key);
    }

    /**
     * Private constructor
     */
    private MoviedbApi(){
        // Retrofit setup
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        // Service setup
        service = retrofit.create(MoviedbService.class);

    }

    /**
     * Get the MoviedbApi singleton instance
     */
    public static MoviedbApi getInstance(){
        if(instance == null){
            instance = new MoviedbApi();
        }
        return instance;
    }

    /**
     * Get the API service to execute calls with
     */
    public MoviedbService getService(){
        return service;
    }
}

