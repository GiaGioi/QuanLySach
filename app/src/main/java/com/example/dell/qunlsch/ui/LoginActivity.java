package com.example.dell.qunlsch.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dell.qunlsch.Constant;
import com.example.dell.qunlsch.R;
import com.example.dell.qunlsch.model.User;
import com.example.dell.qunlsch.sqlite.DatabaseHelper;
import com.example.dell.qunlsch.sqlitedao.StatisticsDAO;
import com.example.dell.qunlsch.sqlitedao.UserDAO;

public class LoginActivity extends AppCompatActivity implements Constant {
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

        UserDAO userDAO = new UserDAO(databaseHelper);


        StatisticsDAO statisticsDAO = new StatisticsDAO(databaseHelper);
        double day = statisticsDAO.getStatisticsByDate(D_DAY);
        double month = statisticsDAO.getStatisticsByDate(M_MONTH);
        double year = statisticsDAO.getStatisticsByDate(Y_YEAR);


        Log.e("RESULT", day + " : " + month + " : " + year);

        User user = new User();
        user.setUsername("admin");
        user.setName("Huy Nguyen");
        user.setPassword("admin123");
        user.setSdt("0919030190");
        //userDAO.insertUser(user);

        initViews();

        loginDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edUserName.getText().toString().trim();
                String password = edPassWord.getText().toString().trim();
                if (password.length() < 6 || userName.isEmpty() || password.isEmpty()) {

                    if (userName.isEmpty())
                        edUserName.setError(getString(R.string.notify_empty_user));

                    if (password.length() < 6)
                        edPassWord.setError(getString(R.string.notify_length_pass));

                    if (password.isEmpty())
                        edPassWord.setError(getString(R.string.notify_empty_pass));


                } else {


                    UserDAO userDAO = new UserDAO(databaseHelper);
                    User user = userDAO.getUser(userName);

                    if (user == null) {
                        Toast.makeText(
                                LoginActivity.this,
                                getString(R.string.notify_wrong_username_password), Toast.LENGTH_SHORT).show();

                    } else {

                        String passwordOnDB = user.getPassword();

                        if (passwordOnDB.equals(password)) {
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            finish();
                        } else Toast.makeText(
                                LoginActivity.this,
                                getString(R.string.notify_wrong_username_password), Toast.LENGTH_SHORT).show();

                    }


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
