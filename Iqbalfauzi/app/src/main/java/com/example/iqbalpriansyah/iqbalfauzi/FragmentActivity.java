package com.example.iqbalpriansyah.iqbalfauzi;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class FragmentActivity extends AppCompatActivity {

    Button btnLogout;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    CallbackManager callbackManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);


        //sebuah statment pengecekan agar fragment hanya dibuat sekali saja.
        if (savedInstanceState == null) {
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build();

            mGoogleSignInClient = GoogleSignIn.getClient(FragmentActivity.this, gso);

            mAuth = FirebaseAuth.getInstance();
            btnLogout = (Button)findViewById(R.id.btnLogout);


            BodyPartFragment bodyPart; // inisiasi memanggil class bodypartsfragment
            FragmentManager fm = getSupportFragmentManager();

            bodyPart = new BodyPartFragment();
            bodyPart.setImageid(BodyImageAsset.getCloths());
            bodyPart.setIndexImg(0);
            fm.beginTransaction().add(R.id.clotsContainer, bodyPart).commit();

//            sMapsFragment maps = new sMapsFragment();
//
//
//            fm.beginTransaction().add(R.id.mapsFragment, maps.onCreateView(),"")commit();
              btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signOut();
                }
            });



        }

    }

    private void signOut() {
        // Firebase sign out
        mAuth.signOut();
        LoginManager.getInstance().logOut();
        // Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(FragmentActivity.this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        startActivity(new Intent(FragmentActivity.this, Login.class));
                        finish();
                    }
                });



    }

}
