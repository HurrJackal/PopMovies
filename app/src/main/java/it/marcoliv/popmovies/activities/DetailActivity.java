package it.marcoliv.popmovies.activities;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.marcoliv.popmovies.Constants;
import it.marcoliv.popmovies.MyApplication;
import it.marcoliv.popmovies.R;
import it.marcoliv.popmovies.model.KMovie;

public class DetailActivity extends AppCompatActivity {
    private static final String LOG_TAG = DetailActivity.class.getSimpleName();
    private KMovie mMovie = null;
    Moshi moshi = null;
    JsonAdapter<KMovie> jsonAdapter = null;
    @BindView(R.id.tvTitle) TextView tvTitle;
    @BindView(R.id.tvYear) TextView tvYear;
    @BindView(R.id.tvDuration) TextView tvDuration;
    @BindView(R.id.tvVote) TextView tvVote;
    @BindView(R.id.tvDescription) TextView tvDescription;
    @BindView(R.id.imgThumb) ImageView imgThumb;
    @BindView(R.id.topPanel) RelativeLayout topPanel;


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

    @Override
    protected void onResume() {
        super.onResume();

        resize();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        resize();
    }

    private void resize(){

        int displayHeight = ((MyApplication) getApplication()).getDisplayHeight();
        int displayWidth =  ((MyApplication) getApplication()).getDisplayWidth();
        boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        RelativeLayout.LayoutParams topPanelLp = (RelativeLayout.LayoutParams) topPanel.getLayoutParams();
        RelativeLayout.LayoutParams imgLp = (RelativeLayout.LayoutParams) imgThumb.getLayoutParams();

        if(isLandscape){
            Log.d(getClass().getSimpleName(), "isLandscape");
            topPanelLp.height = (int) (displayHeight * 0.40);

            imgLp.width = (int) (displayWidth * 0.30);
            imgLp.height = (int) (imgLp.width * (3.0/2.0));

        } else{
            Log.d(getClass().getSimpleName(), "isPortrait");
        }
    }
}
