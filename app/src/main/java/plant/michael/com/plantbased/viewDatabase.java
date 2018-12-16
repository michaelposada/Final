package plant.michael.com.plantbased;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.widget.ViewFlipper;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class viewDatabase extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private DatabaseReference test;
    private DatabaseReference myUser;
    private DatabaseReference myUsername;
    private  RecyclerView mRecyclerView;
    private String userID;
    private String names;
    private FirebaseUser user;
    public Plant Test = new Plant();
    public static ArrayList<Plant> plants = new ArrayList<>();
    ViewFlipper VF;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        Intent i = getIntent();


        String userName = i.getStringExtra("user");
        final String newUserName = userName.replace("@mail.adelphi.edu", "");
        names = newUserName;


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerView);
        BottomNavigationView ViewYourPlant = findViewById(R.id.navigation);
        mFirebaseDatabase = FirebaseDatabase.getInstance();


        ViewYourPlant.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.action_my_plants:
                            Intent i = new Intent(viewDatabase.this, myPlants.class);
                            i.putExtra("user",newUserName);
                            startActivity(i);


                        case R.id.action_all_plants:
                            plants.clear();
                            mRecyclerView.setAdapter(null);
                            myRef = mFirebaseDatabase.getReference().child("plants");
                            myRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    showTable(dataSnapshot);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                }
                            });
                    }
                    return true;
                }
        });

    }


    private void showTable(DataSnapshot dataSnapshot) {
        ArrayList<Plant> plants = new ArrayList<Plant>();


        for(DataSnapshot ds : dataSnapshot.getChildren())
        {
            Plant plant  = new Plant();
            plant.setPlantName((String)ds.getValue(Plant.class).getPlantName());

            plant.setEnviorment(ds.getValue(Plant.class).getEnviorment());
            plant.setZone(ds.getValue(Plant.class).getZone());
            plants.add(plant);

            //System.out.print(plant.getPlantName());

        }
        System.out.println(plants.size());
        RecyclerAdapter adapter = new  RecyclerAdapter(this, plants, names);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(viewDatabase.this));
        mRecyclerView.setAdapter(adapter);

    }

    public void onStart() {
        super.onStart();

        myRef = mFirebaseDatabase.getReference().child("plants");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showTable(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
