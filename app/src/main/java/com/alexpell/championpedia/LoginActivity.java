package com.alexpell.championpedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alexpell.championpedia.DB.AppDataBase;
import com.alexpell.championpedia.DB.LoginLogDAO;
import com.alexpell.championpedia.databinding.ActivityLoginBinding;
import com.alexpell.championpedia.databinding.ActivityMainBinding;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    public static boolean createAccount = true;

    private ActivityLoginBinding binding;

    private LoginLogDAO loginLogDAO;
    private List<LoginLog> loginLogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Toast.makeText(this, "Starting...", Toast.LENGTH_SHORT).show();
        EditText email = binding.editTextEmailAddress;
        EditText password = binding.editTextPassword;
        Button button = binding.buttonSubmit;
        loginLogDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .loginLogDAO();


        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(emailText, passwordText);
            }
        });
        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {//when the user presses the enter key
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    Toast.makeText(LoginActivity.this, "test2", Toast.LENGTH_SHORT).show();
                    login(emailText, passwordText);
                    handled = true;
                }
                return handled;
            }
        });

    }

    public void login(String email, String password) {
        Toast.makeText(this, "test3", Toast.LENGTH_SHORT).show();

        if (createAccount) {
            loginLogDAO.insert(new LoginLog(email, password));
            binding.TextviewTitle.setText("Account Created");
        } else {
            loginLogs = loginLogDAO.getLoginLogs();
            for (LoginLog loginLog : loginLogs) {
                if (loginLog.getLogin().equals(email) && loginLog.getPassword().equals(password)) {
                    binding.TextviewTitle.setText("Login Successful");
                    return;
                }
            }
            binding.TextviewTitle.setText("Login Failed");
        }


        if (email.equals("admin") && password.equals("admin")) {
            binding.TextviewTitle.setText("Login Successful");
        }
    }
}