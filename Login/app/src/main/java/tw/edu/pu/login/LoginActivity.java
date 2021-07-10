package tw.edu.pu.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText etAcc, etPass;
    MaterialButton btnLogin, btnClear, btnRegister;

    private String acc, pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerFunction();
            }
        });
    }

    private void findView() {
        etAcc = findViewById(R.id.etAcc);
        etPass = findViewById(R.id.etPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnClear = findViewById(R.id.btnClear);
        btnRegister = findViewById(R.id.btn_Register);
    }

    private void loginFunction() {
        if (etAcc.getText() != null && etPass.getText() != null) {
            acc = etAcc.getText().toString();
            pw = etPass.getText().toString();

            if (acc.equals("yuuzu") && pw.equals("1234")) {
                Intent ii = new Intent(getApplicationContext(), SceneActivity.class);
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

    private void registerFunction() {
        Intent ii = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(ii);
    }
}