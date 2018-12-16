package plant.michael.com.plantbased;


import android.content.Intent;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static final String EXTRA_PLANT_ID = "com.example.michaelposada.PlantBased.EXTRA_PLANT_ID";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private boolean logged = false;
    public TextView email;
    public  TextView password;
    public Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_page);
        mAuth = FirebaseAuth.getInstance();
        email = (TextView) findViewById(R.id.Login);
        password = findViewById(R.id.Password);
        submit = findViewById(R.id.Submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  String user = email.getText().toString();
                String pass = password.getText().toString();
                mAuth = FirebaseAuth.getInstance();

                System.out.println("Here");

                mAuth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {

                            Toast.makeText(MainActivity.this, "Login Worked",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(MainActivity.this, viewDatabase.class);
                            i.putExtra("user",user);
                            startActivity(i);

                        }
                        else
                            {
                                Toast.makeText(MainActivity.this, "Login NO Worked",Toast.LENGTH_LONG).show();
                            }
                    }
                });


            }
        });





    }



}
