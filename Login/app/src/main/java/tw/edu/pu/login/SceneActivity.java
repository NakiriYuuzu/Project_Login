package tw.edu.pu.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

import tw.edu.pu.login.sqLite.DBHelper;

public class SceneActivity extends AppCompatActivity {

    MaterialButton btnSignOut;
    DBHelper db;

    private String user;

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
    }

    private void findView() {
        btnSignOut = findViewById(R.id.btn_SignOut);
    }

    private void signOut() {
        finish();
    }
}