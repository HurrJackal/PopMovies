package it.marcoliv.popmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import it.marcoliv.popmovies.model.KMovie;

/**
 * Created by marcoliv on 9/22/2016.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    // Provide a reference to the views for each data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgThumb;

        public ViewHolder(View v) {
            super(v);
            imgThumb = (ImageView) v.findViewById(R.id.thumbnail);
        }
    }

    private Context mContext;
    private List<KMovie> mMovies;


    public void add(int position, KMovie item){
        mMovies.add(position, item);
        notifyItemInserted(position);
    }

    public void addAll(List<KMovie> movies){
        for(KMovie elem: movies){
            mMovies.add(elem);
        }

        notifyDataSetChanged();
    }

    public void remove(String item){
        int position = mMovies.indexOf(item);
        mMovies.remove(position);
        notifyItemInserted(position);
    }

    public ImageAdapter(Context context, List<KMovie> mMovies) {
        this.mMovies = mMovies;
        this.mContext = context;
    }

    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // inflate the custom layout
        View contactView = inflater.inflate(R.layout.grid_view_image, parent, false);

        // return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Get the data model based on position
        String url = Constants.BASE_URL_IMAGE + Constants.RESOLUTION_W500 +
                mMovies.get(position).getPoster_path();

        Log.d(getClass().getSimpleName(), "url: " + url);

        Picasso
                .with(mContext)
                .load(url)
                .into(holder.imgThumb);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mMovies.size();
    }


}
