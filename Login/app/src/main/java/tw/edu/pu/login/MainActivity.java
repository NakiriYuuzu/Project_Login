package tw.edu.pu.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import tw.edu.pu.login.sqLite.DBHelper;

public class MainActivity extends AppCompatActivity {

    MaterialTextView tvUser;
    MaterialButton btnSignOut, btnEditProfile;
    LottieAnimationView lottieBike, lottiePu, lottieBeacon;
    DBHelper db;

    private String user;
    private int loopImg = 1;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //
        findView();
        db = new DBHelper(this);

        //
        if (getIntent().hasExtra("ID"))
            user = getIntent().getStringExtra("ID");
        else
            throw new IllegalArgumentException("Activity cannot find extras");

        //
        tvUser.setText("Welcome: " + user);

        //TODO
        lottieBike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(getApplicationContext(), UBikeActivity.class);
                startActivity(ii);
            }
        });

        lottiePu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(getApplicationContext(), TravelActivity.class);
                startActivity(ii);
            }
        });

        lottieBeacon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });

        //
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(getApplicationContext(), EditProfileActivity.class);
                ii.putExtra("ID", user);
                startActivity(ii);
            }
        });

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
    }

    private void findView() {
        btnSignOut = findViewById(R.id.btn_SignOut);
        btnEditProfile = findViewById(R.id.btn_EditProfile);
        tvUser = findViewById(R.id.tvUsername_main);
        lottieBike = findViewById(R.id.lottieBike);
        lottiePu = findViewById(R.id.lottiePu);
        lottieBeacon = findViewById(R.id.lottieBeacon);
    }

    private void signOut() {
        db.changeStatus(user, 0);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        db.changeStatus(user, 1);
    }

    @Override
    protected void onStop() {
        super.onStop();
        db.changeStatus(user, 0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        db.changeStatus(user, 1);
    }

    @Override
    protected void onPause() {
        super.onPause();
        db.changeStatus(user, 0);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        db.changeStatus(user, 1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.changeStatus(user, 0);
    }
}