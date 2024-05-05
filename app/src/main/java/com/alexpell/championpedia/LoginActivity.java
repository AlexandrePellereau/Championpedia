package com.alexpell.championpedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

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
import com.alexpell.championpedia.DB.User;
import com.alexpell.championpedia.DB.AllDAO;
import com.alexpell.championpedia.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    public static boolean createAccount = false;

    private ActivityLoginBinding binding;

    private AllDAO allDAO;

    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            createAccount = extras.getBoolean("createAccount");
        }

        Toast.makeText(this, "Starting...", Toast.LENGTH_SHORT).show();
        username = binding.editTextUsername;
        password = binding.editTextPassword;
        Button button = binding.buttonSubmit;
        allDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .getAllDAO();

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
        String usernameText = username.getText().toString();
        String passwordText = password.getText().toString();

        if (usernameText.isEmpty() || passwordText.isEmpty()) {
            showToast("Please fill in all fields");
            return;
        }

        if (createAccount) {
            createUserAndLogin(usernameText, passwordText, usernameText.contains("admin"));
            return;
        }

        loginExistingUser(usernameText, passwordText);
    }

    private void createUserAndLogin(String username, String password, boolean admin) {
        User newUser = new User(username, password, admin);
        allDAO.insert(newUser);
        User finalUser = allDAO.getUsers().get(allDAO.getUsers().size() - 1);
        loginSuccess(finalUser.getId(), admin);
    }

    private void loginExistingUser(String username, String password) {
        for (User user : allDAO.getUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loginSuccess(user.getId(), user.getAdmin());
                return;
            }
        }
        showToast(getString(R.string.login_failed));
    }

    private void loginSuccess(int userId, boolean isAdmin) {
        SharedPreferences.Editor editor = getSharedPreferences("com.alexpell.championpedia", MODE_PRIVATE).edit();
        editor.putBoolean("loggedIn", true);
        editor.putInt("userId", userId);
        editor.putBoolean("isAdmin", isAdmin);
        editor.apply();
        Log.d("AlexTest", "loginUserId: " + userId);
        startActivity(new Intent(getApplicationContext(), LandingPage.class));
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}