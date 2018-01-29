package com.ab2018.vizyondakilerdatabase;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.ab2018.vizyondakilerdatabase.databinding.ActivityMovieDetailsBinding;


public class MovieDetailsActivity extends AppCompatActivity {

    Intent intent;
    TextView detail_txt;
    ActivityMovieDetailsBinding binding;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_movie_details);
        intent = getIntent();
        Movie movie = (Movie) intent.getSerializableExtra("movieDetails");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);
        binding.setMovie(movie);
        imageView = findViewById(R.id.imageViewCover);
        imageView.setImageResource(movie.getImageCode());

    }


}
