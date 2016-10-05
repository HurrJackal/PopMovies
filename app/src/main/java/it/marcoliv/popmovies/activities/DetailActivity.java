package it.marcoliv.popmovies.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.marcoliv.popmovies.Constants;
import it.marcoliv.popmovies.R;
import it.marcoliv.popmovies.model.KMovie;

public class DetailActivity extends AppCompatActivity {
    private static final String LOG_TAG = DetailActivity.class.getSimpleName();
    private KMovie mMovie = null;
    Moshi moshi = null;
    JsonAdapter<KMovie> jsonAdapter = null;
    @BindView(R.id.tvTitle) TextView tvTitle;
    @BindView(R.id.tvYear) TextView tvYear;
    @BindView(R.id.tvVote) TextView tvVote;
    @BindView(R.id.tvDescription) TextView tvDescription;
    @BindView(R.id.tvDuration) TextView tvDuration;
    @BindView(R.id.imgThumb) ImageView imgThumb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        moshi = new Moshi.Builder().build();
        jsonAdapter = moshi.adapter(KMovie.class);
    }

    @Override
    protected void onStart() {
        super.onStart();

        String strMovie = getIntent().getStringExtra(Constants.EXTRA_MESSAGE);

        try {
            mMovie = jsonAdapter.fromJson(strMovie);
            tvTitle.setText(mMovie.getTitle());
            tvYear.setText(mMovie.getRelease_date());
            tvVote.setText(mMovie.getVote_average() + "/ 10");
            tvDuration.setText("Missed value");
            tvDescription.setText(mMovie.getOverview());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        // inflate image
        String url =    Constants.BASE_URL_IMAGE + Constants.RESOLUTION_W500 + mMovie.getPoster_path();

        Log.d(LOG_TAG, "url: " + url);

        Picasso
                .with(getApplicationContext())
                .load(url)
                .into(imgThumb);


    }
}
