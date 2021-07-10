package tw.edu.pu.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class SceneActivity extends AppCompatActivity {

    private MaterialButton btnSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene);

        findView();

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void findView() {
        btnSignOut = findViewById(R.id.btn_SignOut);
    }

    private void signOut() {

    }
}