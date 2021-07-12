package tw.edu.pu.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import tw.edu.pu.login.sqLite.DBHelper;

public class EditProfileActivity extends AppCompatActivity {

    TextInputEditText etPass, etRepass;
    MaterialTextView tvUser;
    MaterialButton btnConform, btnCancel;

    DBHelper db;

    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        findView();
        db = new DBHelper(this);

        if (getIntent().hasExtra("ID"))
            user = getIntent().getStringExtra("ID");
        else
            throw new IllegalArgumentException("Activity cannot find extras");

        tvUser.setText(user);

        btnConform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPassword();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelFunction();
            }
        });
    }

    private void findView() {
        etPass = findViewById(R.id.etPass_ep);
        etRepass = findViewById(R.id.etRepass_ep);
        tvUser = findViewById(R.id.tvUsername_ep);
        btnConform = findViewById(R.id.btnConform);
        btnCancel = findViewById(R.id.btnCancel_ep);
    }

    private void editPassword() {
        if (etPass.getText() != null && etRepass.getText() != null) {
            String pass = etPass.getText().toString();
            String repass = etRepass.getText().toString();

            if (repass.equals("") || pass.equals("")) {
                Toast.makeText(this, "Please enter all the fields!", Toast.LENGTH_SHORT).show();
            }
            else {

                if (pass.equals(repass)) {

                    if (db.checkUserPassword(user, pass)) {
                        Toast.makeText(this, "Password same as last time, please change it!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        db.changePassword(user, pass);
                        db.changeStatus(user, 0);
                        finish();
                    }
                }
                else {
                    Toast.makeText(this, "Passwords is not matching!", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else
            Toast.makeText(this, "Please enter your account and password!", Toast.LENGTH_SHORT).show();
    }

    private void cancelFunction() {
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