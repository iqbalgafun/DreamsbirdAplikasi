package com.example.iqbalpriansyah.iqbalfauzi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {

    EditText email;
    EditText password;
    Button button;
    String TAG;
    String email1, password2;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Mendaftarkan value
        email = (EditText)findViewById(R.id.emailreg);
        password = (EditText)findViewById(R.id.passwordreg);
        button = (Button)findViewById(R.id.button2);

        mAuth = FirebaseAuth.getInstance();

        //Button untuk login
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //menset tempat variable email
                email1 = email.getText().toString().trim();
                //menset tempat variable password
                password2 = password.getText().toString().trim();

                //memanggil method sign in
                signUp(email1,password2);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        //FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }
    private void updateUI(FirebaseUser currentUser){
        // perpindahan ke halaman yg dituju
        Intent i = new Intent(getApplicationContext(), Login.class);
        startActivity(i);
    }
    // melakukan login dengan username dan password
    private void signUp(String email1, String password2){
        mAuth.createUserWithEmailAndPassword(email1, password2)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signUpWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(SignUp.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                    }
                });
    }

}
