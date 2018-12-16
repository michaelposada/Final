package plant.michael.com.plantbased;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SpecifiedSinglePlant extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myUsername;
    private DatabaseReference myRef;
    private RecyclerView mRecyclerView;
    TextView plantName;
    TextView plantZone;
    TextView plantWatering;
    TextView fertlize;
    TextView light;
    TextView pests;
    TextView funFact;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specific_plant_view);
        plantName = findViewById(R.id.plantName);
        plantZone = findViewById(R.id.plantZone);
        plantWatering = findViewById(R.id.textView9);
        fertlize = findViewById(R.id.textView10);
        light = findViewById(R.id.textView11);
        pests = findViewById(R.id.textView12);
        funFact = findViewById(R.id.textview13);
        BottomNavigationView ViewYourPlant = findViewById(R.id.navigation);
        System.out.println("IN SPECIFIED SINGLE PLANT!");
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        for(int i =0; i<100; i++) {
            String t = String.valueOf(i);

            myRef = mFirebaseDatabase.getReference().child("plants").child(t);
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
        ViewYourPlant.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.action_my_plants:
                        Intent i = new Intent(SpecifiedSinglePlant.this, myPlants.class);
                        Intent j = getIntent();
                        String user1 = j.getStringExtra("user");
                        System.out.println("UserName is "+user1);
                        i.putExtra("user",user1);
                        startActivity(i);


                    case R.id.action_all_plants:
                        Intent t = new Intent(SpecifiedSinglePlant.this,viewDatabase.class);
                        Intent l = getIntent();
                        String user2 = l.getStringExtra("user");
                        System.out.println("Username is "+user2);
                        t.putExtra("user",user2);
                        startActivity(t);

                }
                return true;
            }
        });
    }

    private void showTable(DataSnapshot dataSnapshot) {
        ArrayList<Plant> plants = new ArrayList<Plant>();

        String l = dataSnapshot.child("plantName").getValue(String.class);
        System.out.println(l);


        Intent j = getIntent();
        String wanted = " ";
        wanted = j.getStringExtra("name");
        Plant plant  = new Plant();




        if (wanted.equals(l))
        {
            System.out.println("Did i make it?");
            plantName.setText("Name: "+wanted);
            plant.setZone(dataSnapshot.child("zone").getValue(String.class));
            plantZone.setText("Zone: "+plant.getZone());
            plant.setH20Cycle(dataSnapshot.child("watering").getValue(String.class));
            plantWatering.setText("Water Cycle: "+plant.getH20Cycle());
            plant.setSoil(dataSnapshot.child("fertilizing").getValue(String.class));
            fertlize.setText("Soil Type: "+plant.getSoil());
            plant.setLight(dataSnapshot.child("light").getValue(String.class));
            light.setText("Lighting: "+plant.getLight());
            plant.setPests(dataSnapshot.child("pests").getValue(String.class));
            pests.setText("Pests: "+plant.getPests());
            plant.setFunFact(dataSnapshot.child("funfact").getValue(String.class));
            funFact.setText("Fun Fact: "+plant.getFunFact());
            //pests.setText((String)ds.getValue(Plant.class).getPests());
            //funFact.setText((String)ds.getValue(Plant.class).getFunFact());
        }
        else
        {

        }


        //plant.setPlantName((String)ds.getValue(Plant.class).getPlantName());

        //plant.setEnviorment(ds.getValue(Plant.class).getEnviorment());
        //plant.setZone(ds.getValue(Plant.class).getZone());
        //plants.add(plant);

        //System.out.print(plant.getPlantName());

    }







}
