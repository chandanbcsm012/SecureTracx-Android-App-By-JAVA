package in.royelectricals.securetracx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText txtEmail, txtPassword;

    // User Session Manager Class
    SessionMgt session;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // User Session Manager
        session = new SessionMgt(getApplicationContext());

        // get Email, Password input text
        txtEmail = (EditText) findViewById(R.id.input_email);
        txtPassword = (EditText) findViewById(R.id.input_password);

        Toast.makeText(getApplicationContext(),
                "User Login Status: " + session.isUserLoggedIn(),
                Toast.LENGTH_LONG).show();
        // User Login button
        btnLogin = (Button) findViewById(R.id.btn_login);

        // Login button click event
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // Get email, password from EditText
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();

                // Validate if username, password is filled
                if(email.trim().length() > 0 && password.trim().length() > 0){

                    // For testing puspose username, password is checked with static data
                    // username = admin
                    // password = admin

                    if(email.equals("admin@gmail.com") && password.equals("admin")){

                        // Creating user login session
                        // Statically storing name="Android Example"
                        // and email="androidexample84@gmail.com"
                        session.sessionStart("afmin@gmail.com",
                                "admin");

                        // Starting MainActivity
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        // Add new Flag to start new Activity
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        finish();

                    }else{

                        // username / password doesn't match&
                        Toast.makeText(getApplicationContext(),
                                "Username/Password is incorrect",
                                Toast.LENGTH_LONG).show();
                    }
                }else{
                    // user didn't entered username or password
                    Toast.makeText(getApplicationContext(),
                            "Please enter username and password",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}