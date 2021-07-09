package tw.edu.pu.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText etAcc, etPass;
    private MaterialButton btnLogin, btnClear;

    private String acc, pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginFunction();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFunction();
            }
        });
    }

    private void findView() {
        etAcc = findViewById(R.id.etAcc);
        etPass = findViewById(R.id.etPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnClear = findViewById(R.id.btnClear);
    }

    private void loginFunction() {
        if (etAcc.getText() != null && etPass.getText() != null) {
            acc = etAcc.getText().toString();
            pw = etPass.getText().toString();

            if (acc.equals("yuuzu") && pw.equals("1234")) {
                Intent ii = new Intent(getApplicationContext(), scene2.class);
                startActivity(ii);
                clearFunction();
            }
            else
                Toast.makeText(this, acc + ": Login Failed!\nCheck your id and password!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Please enter your account and password!", Toast.LENGTH_SHORT).show();
            clearFunction();
        }
    }

    private void clearFunction() {
        etAcc.setText("");
        etPass.setText("");
    }
}