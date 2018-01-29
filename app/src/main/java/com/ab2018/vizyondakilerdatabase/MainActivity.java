package com.ab2018.vizyondakilerdatabase;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Movie> movies;
    Intent intent,login;
    ListView movieList = null;
    TextView txt;
    Movie tmp;
    int id;
    String name;
    String overview;
    int imageCode;
    String originalLanguage;
    double voteAverage;
    MovieDBAdapter movieDBAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            String destPath = "/data/data/" + getPackageName()
                    + "/databases/MovieDB";
            File file = new File(destPath);
            File path = new File("/data/data/" + getPackageName()
                    + "/databases/");
            if (!file.exists()) {
                path.mkdirs();
                CopyDB(getBaseContext().getAssets().open("MovieDB"),
                        new FileOutputStream(destPath));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        movieList = findViewById(R.id.movie_listView);
        movies = new ArrayList<>();
        movieDBAdapter = new MovieDBAdapter(this);
        movieDBAdapter.open();
        Cursor cursor = movieDBAdapter.getAllMovies();
        if(cursor.moveToFirst())
        {
            Log.d("log","movetofirst");
            while (cursor.moveToNext())
            {
                id = cursor.getInt(0);
                name = cursor.getString(1);
                overview = cursor.getString(2);
                imageCode = cursor.getInt(3);
                originalLanguage = cursor.getString(4);
                voteAverage = cursor.getDouble(5);
                tmp = new Movie(id,name,overview,imageCode,originalLanguage,voteAverage);
                movies.add(tmp);
            }
        }
        else
        {
            Toast.makeText(this, "Veritabanında film yoktur", Toast.LENGTH_SHORT).show();
        }
        movieDBAdapter.close();

        final MovieAdapter movieAdapter = new MovieAdapter(getApplicationContext(), 0, movies);
        movieList.setAdapter(movieAdapter);


        movieList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Movie movie = (Movie) movieList.getItemAtPosition(i);
                intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
                intent.putExtra("movieDetails", movie);
                startActivity(intent);
            }
        });



        movieList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                login = new Intent(MainActivity.this,LoginActivity.class);
                Movie movie = (Movie) movieList.getItemAtPosition(i);
                login.putExtra("login", movie);
                startActivity(login);
                return true;
            }
        });


    }

    public void CopyDB(InputStream inputStream, OutputStream outputStream)
            throws IOException {
        // Copy 1K bytes at a time
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        inputStream.close();
        outputStream.close();
    }

}

