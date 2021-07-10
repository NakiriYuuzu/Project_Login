package tw.edu.pu.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;

import tw.edu.pu.login.sqLite.DBHelper;

public class SceneActivity extends AppCompatActivity {

    MaterialButton btnSignOut;
    ImageView iv;
    DBHelper db;

    private String user;
    private int loopImg = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene);

        findView();
        db = new DBHelper(this);

        if (getIntent().hasExtra("ID"))
            user = getIntent().getStringExtra("ID");
        else
            throw new IllegalArgumentException("Activity cannot find extras");

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int img = getResources().getIdentifier("cute" + loopImg, "drawable", getPackageName());
                iv.setImageResource(img);
                loopImg++;
                if (loopImg >= 8) {
                    loopImg = 1;
                }
            }
        });
    }

    private void findView() {
        btnSignOut = findViewById(R.id.btn_SignOut);
        iv = findViewById(R.id.imageView);
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