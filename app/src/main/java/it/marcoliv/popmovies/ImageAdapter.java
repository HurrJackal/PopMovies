package it.marcoliv.popmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import it.marcoliv.popmovies.model.KMovie;
import it.marcoliv.popmovies.model.KMovies;

/**
 * Created by marcoliv on 9/22/2016.
 */

public class ImageAdapter extends ArrayAdapter {
    private static final String  LOG_TAG = ImageAdapter.class.getSimpleName();
    private Context context;
    private LayoutInflater inflater;
    private List<KMovie> mMovies;

    public ImageAdapter(Context context, List<KMovie> mMovies) {
        super(context, R.layout.grid_view_layout, mMovies);

        this.context = context;
        this.mMovies = mMovies;
        inflater = LayoutInflater.from(context);
    }

    /**
     * Create a new ImageView for each item referenced by the Adapter
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
           convertView = inflater.inflate(R.layout.grid_view_image, parent, false);
        }

        String url =    Constants.BASE_URL_IMAGE + Constants.RESOLUTION_W500 +
                        mMovies.get(position).getPoster_path();

        Log.d(LOG_TAG, "url: " + url);

        Picasso
                .with(context)
                .load(url)
                .into((ImageView)convertView);

        return convertView;
    }
}
