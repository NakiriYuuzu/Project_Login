package tw.edu.pu.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import tw.edu.pu.login.sqLite.DBHelper;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText etAcc, etPass;
    MaterialButton btnLogin, btnClear, btnRegister;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findView();
        db = new DBHelper(this);

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
            String user = etAcc.getText().toString();
            String pass = etPass.getText().toString();

            if (user.equals("") || pass.equals("")) {
                Toast.makeText(this, "Please enter all the fields!", Toast.LENGTH_SHORT).show();
            } else {

                if (db.checkUserPassword(user,pass)) {

                    if (db.checkUserStatus(user)) {
                        db.changeStatus(user, 1);
                        Toast.makeText(this, "Sign in Successfully!", Toast.LENGTH_SHORT).show();
                        Intent ii = new Intent(getApplicationContext(), SceneActivity.class);
                        ii.putExtra("ID", user);
                        startActivity(ii);
                    }
                    else {
                        Toast.makeText(this, "Account is login...", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
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