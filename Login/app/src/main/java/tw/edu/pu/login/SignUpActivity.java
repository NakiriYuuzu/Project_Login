package tw.edu.pu.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends AppCompatActivity {

    TextInputEditText etAcc_su, etPass_su, etRepass_su;
    MaterialButton btnSignUp, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        findView();
    }

    private void findView() {
        etAcc_su = findViewById(R.id.etAcc_su);
        etPass_su = findViewById(R.id.etPass_su);
        etRepass_su = findViewById(R.id.etRepass_su);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnCancel = findViewById(R.id.btnCancel);
    }
}