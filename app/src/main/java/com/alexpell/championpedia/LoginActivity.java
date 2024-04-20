package com.alexpell.championpedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public static boolean createAccount = false;

    private ActivityLoginBinding binding;

    private LoginLogDAO loginLogDAO;
    private List<LoginLog> loginLogs;

    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferences  = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Toast.makeText(this, "Starting...", Toast.LENGTH_SHORT).show();
        email = binding.editTextEmailAddress;
        password = binding.editTextPassword;
        Button button = binding.buttonSubmit;
        loginLogDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .loginLogDAO();

        if (createAccount)
            binding.TextviewTitle.setText(R.string.create_account);
        else
            binding.TextviewTitle.setText(R.string.login);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {//when the user presses the enter key
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    Toast.makeText(LoginActivity.this, "test2", Toast.LENGTH_SHORT).show();
                    login();
                    handled = true;
                }
                return handled;
            }
        });

    }

    public void login() {
        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();

        if (emailText.isEmpty() || passwordText.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }



        if (createAccount) {
            loginLogDAO.insert(new LoginLog(emailText, passwordText));
            binding.TextviewTitle.setText(R.string.account_created);
        } else {
            if (email.equals("admin") && password.equals("admin")) {
                binding.TextviewTitle.setText(R.string.login_admin_successful);
                editor.putBoolean("is_logged_in",true);
                editor.putBoolean("is_admin",true);
                editor.apply();
                startActivity(new Intent(getApplicationContext(), LandingPage.class));
                return;
            }

            loginLogs = loginLogDAO.getLoginLogs();
            for (LoginLog loginLog : loginLogs) {
                if (loginLog.getLogin().equals(emailText) && loginLog.getPassword().equals(passwordText)) {
                    binding.TextviewTitle.setText(R.string.login_successful);
                    editor.putBoolean("is_logged_in",true);
                    editor.putBoolean("is_admin",false);
                    editor.apply();
                    startActivity(new Intent(getApplicationContext(), LandingPage.class));
                    return;
                }
            }

            Toast.makeText(this, R.string.login_failed, Toast.LENGTH_SHORT).show();
        }
    }
}