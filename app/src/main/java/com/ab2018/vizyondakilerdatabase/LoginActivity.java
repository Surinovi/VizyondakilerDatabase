package com.ab2018.vizyondakilerdatabase;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {
    EditText emailEt,passEt;
    TextView login;
    MovieDBAdapter mdbAdapter;
    String email,pass,name;
    int userid;
    Intent reservation,movieIntent;
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailEt = findViewById(R.id.email);
        passEt = findViewById(R.id.password);
        login = findViewById(R.id.login);
        mdbAdapter=new MovieDBAdapter(this);
        movieIntent = getIntent();
        movie = (Movie) movieIntent.getSerializableExtra("login");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = emailEt.getText().toString();
                pass = passEt.getText().toString();
                if(!TextUtils.isEmpty(email) || !TextUtils.isEmpty(pass))
                {
                    mdbAdapter.open();
                    Cursor c = mdbAdapter.getUser(email,pass);

                    if(c.moveToFirst())
                    {
                            userid=c.getInt(0);
                            name=c.getString(1);
                            reservation = new Intent(LoginActivity.this,ReservationActivity.class);
                            User u = new User(userid,email,pass,name);
                            reservation.putExtra("user",u);
                            reservation.putExtra("movie",movie);
                            startActivity(reservation);

                    }
                    else
                        Toast.makeText(LoginActivity.this, "Yanlış kullanıcı adı veya parola", Toast.LENGTH_SHORT).show();
                    mdbAdapter.close();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Lütfen alanları boş bırakmayınız",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
