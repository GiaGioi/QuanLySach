package com.example.dell.qunlsch.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dell.qunlsch.R;
import com.example.dell.qunlsch.model.User;
import com.example.dell.qunlsch.sqlite.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {
    private ImageView imgLogo;
    private EditText edUserName;
    private EditText edPassWord;
    private CheckBox chkRememberPass;
    private Button loginDangnhap;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this);


        // kiem tra user da ton tai trong DB chua !!!
        User user2;

        user2 = databaseHelper.getUser("admin");

        if (user2 == null) {
            Toast.makeText(this, "User chua ton tai!!!", Toast.LENGTH_LONG).show();
            User user = new User("admin", "admin", "Huy Nguyen", "0913456789");
            databaseHelper.insertUser(user);

        } else {
            Toast.makeText(this, "User da ton tai !!!", Toast.LENGTH_LONG).show();
        }


        initViews();

        loginDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edUserName.getText().toString().trim();
                String pass = edPassWord.getText().toString().trim();
                if (pass.length() < 6 || user.isEmpty() || pass.isEmpty()) {

                    if (user.isEmpty()) edUserName.setError(getString(R.string.notify_empty_user));

                    if (pass.length() < 6)
                        edPassWord.setError(getString(R.string.notify_length_pass));

                    if (pass.isEmpty()) edPassWord.setError(getString(R.string.notify_empty_pass));


                } else {


                    final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                    final int[] a = {0};

                    progressDialog.setTitle("Đang đăng nhập");
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);


                    CountDownTimer countDownTimer = new CountDownTimer(1500, 40) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            a[0] = a[0] + 4;
                            progressDialog.show();
                            progressDialog.setProgress(a[0]);
                        }

                        @Override
                        public void onFinish() {
                            progressDialog.dismiss();
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        }
                    }.start();
                }
            }
        });


    }

    public void initViews() {
        imgLogo = findViewById(R.id.imgLogo);
        edUserName = findViewById(R.id.edUserName);
        edPassWord = findViewById(R.id.edPassWord);
        chkRememberPass = findViewById(R.id.chkRememberPass);
        loginDangnhap = findViewById(R.id.login_dangnhap);

    }
}
