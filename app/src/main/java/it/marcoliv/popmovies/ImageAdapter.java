package it.marcoliv.popmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by marcoliv on 9/22/2016.
 */

public class ImageAdapter extends ArrayAdapter {
    private Context context;
    private LayoutInflater inflater;
    private String[] imageUrls;

    public ImageAdapter(Context context, String[] imageUrls) {
        super(context, R.layout.grid_view_layout, imageUrls);

        this.context = context;
        this.imageUrls = imageUrls;
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

        Picasso
                .with(context)
                .load(imageUrls[position])
                .fit()
                .into((ImageView) convertView);

        return convertView;
    }
}
