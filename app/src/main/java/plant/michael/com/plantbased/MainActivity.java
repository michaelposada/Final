package plant.michael.com.plantbased;


import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static final String EXTRA_PLANT_ID = "com.example.michaelposada.PlantBased.EXTRA_PLANT_ID";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    Button  btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mAuth = FirebaseAuth.getInstance();
        final String email = "michaelposada@mail.adelphi.edu";
        String pass = "123456";

        mAuth.signInWithEmailAndPassword(email, pass);
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid()); // prints out the tag for successful or not
                } else {
                    // User is signed out
                    System.out.print("did not worked");
                    Log.d(TAG, "onAuthStateChanged:signed_out"); // prints out if it fails or not.

                }
                // ...
            }

        };
    }

    // This is going to start when the app runs and when we call the listener. When buttons are in this will also be running in the background.
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

}
