package com.meghna125.firebasedemo;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "MainActivity";
    private EditText email;
    private EditText username;
    private EditText password;
    private Button signUp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        signUp = (Button) findViewById(R.id.signup);



        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("User");




        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                mUser = firebaseAuth.getCurrentUser();
                if (mUser != null) {
                    //user signed in
                    Log.d(TAG,"user signed in");
                } else {
                    //user signed out
                    Log.d(TAG, "user signed out");
                }

            }
        };


        /*login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String emailString = email.getText().toString();
                String pwd = password.getText().toString();

                if (!emailString.equals("") && !pwd.equals("")) {
                    mAuth.signInWithEmailAndPassword(emailString, pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Failed Sign in!", Toast.LENGTH_LONG).show();
                            } else {

                                Toast.makeText(MainActivity.this, "Signed in!!", Toast.LENGTH_LONG).show();

                                Customer customer = new Customer("Gina", "Arora", emailString, 25);
                                databaseReference.setValue(customer);

                            }

                        }
                    });



                }

            }
        });*/

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String emailString = email.getText().toString();
                final String pwd = password.getText().toString();
                final String user = username.getText().toString();

                if (!emailString.equals("") && !pwd.equals("") && !user.equals("")) {
                    mAuth.createUserWithEmailAndPassword(emailString, pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Failed to Create Account!", Toast.LENGTH_LONG).show();
                            } else {

                                Toast.makeText(MainActivity.this, "Account Created!!", Toast.LENGTH_LONG).show();
                                Customer customer = new Customer(user,emailString,pwd);
                                databaseReference.setValue(customer);


                            }

                        }
                    });
                }





            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener!=null){

            mAuth.removeAuthStateListener(mAuthListener);
        }


    }
}
