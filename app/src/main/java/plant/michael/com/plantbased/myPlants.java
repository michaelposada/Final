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
import android.widget.ViewFlipper;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class myPlants extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myUsername;
    private DatabaseReference myRef;
    private RecyclerView mRecyclerView;
    public static ArrayList<Plant> plants = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_plants_view);
        mRecyclerView = findViewById(R.id.recyclerView);
        Intent i = getIntent();
        final String userName = i.getStringExtra("user");
        BottomNavigationView ViewYourPlant = findViewById(R.id.navigation);
        int check = 1;
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        // I want to get the username of the perosn that logged in
        //String username = login name here

        myUsername = mFirebaseDatabase.getReference().child("User").child(userName).child("plantID");

        ViewYourPlant.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.action_all_plants:
                        Intent i = new Intent(myPlants.this, viewDatabase.class);
                        i.putExtra("user",userName);
                        startActivity(i);



                }
                return true;
            }
        });

    if(check == 1) {
        check++;
        myUsername.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //showAllData(dataSnapshot);
                String data = dataSnapshot.getValue(String.class);
                System.out.println(data);
                System.out.println("myPlants");
                data = data.replace(",", "");

                System.out.println(data.length());
                for (int i = 0; i < data.length(); i++) {
                    myRef = mFirebaseDatabase.getReference().child("plants").child(String.valueOf(data.charAt(i)));

                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            plants.add(showAllData(dataSnapshot));
                            System.out.println("In the second onDataChange Method Plants Size is " + plants.size());
                            System.out.println("Outside,at the end of bundle method " + plants.size());
                            SpecifiedRecyclerAdapter adapter = new SpecifiedRecyclerAdapter(myPlants.this, plants, userName);
                            mRecyclerView.setLayoutManager(new LinearLayoutManager(myPlants.this));
                            mRecyclerView.setAdapter(adapter);


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                plants.clear();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }
    else
        {

        }
    }

    private Plant showAllData(DataSnapshot dataSnapshot) {



        DataSnapshot ds = dataSnapshot;

        Plant plant  = new Plant();
        plant.setEnviorment(ds.child("enviorment").getValue(String.class));

        plant.setPlantName(ds.child("plantName").getValue(String.class));

        plant.setZone(ds.child("zone").getValue(String.class));


        return plant;
        //Test.setPlant(plant);
        //plants.add(plant);

    }
}
