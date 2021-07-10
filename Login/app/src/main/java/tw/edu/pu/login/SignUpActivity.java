package tw.edu.pu.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import tw.edu.pu.login.sqLite.DBHelper;

public class SignUpActivity extends AppCompatActivity {

    TextInputEditText etAcc_su, etPass_su, etRepass_su;
    MaterialButton btnSignUp, btnCancel;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        findView();
        db = new DBHelper(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpFunction();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void findView() {
        etAcc_su = findViewById(R.id.etAcc_su);
        etPass_su = findViewById(R.id.etPass_su);
        etRepass_su = findViewById(R.id.etRepass_su);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnCancel = findViewById(R.id.btnCancel);
    }

    private void signUpFunction() {
        if (etAcc_su.getText() != null && etPass_su.getText() != null && etRepass_su.getText() != null) {
            String user = etAcc_su.getText().toString();
            String pass = etPass_su.getText().toString();
            String repass = etRepass_su.getText().toString();

            if (user.equals("") || pass.equals("") || repass.equals("")) {
                Toast.makeText(this, "Please enter all the fields!", Toast.LENGTH_SHORT).show();

            } else {
                if (pass.equals(repass)) {
                    boolean checkUser = db.checkUsername(user);
                    if (!checkUser) {
                        boolean insert = db.insertData(user, pass);
                        if (insert) {
                            Toast.makeText(this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else {
                            Toast.makeText(this, "Registered failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(this, "User already exists! Please sign in!", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(this, "Passwords is not matching!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}