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
    TextView maintanence;
    TextView light;
;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specific_plant_view);
        plantName = findViewById(R.id.plantName);
        plantWatering = findViewById(R.id.textView9);
        maintanence = findViewById(R.id.textView12);
        light = findViewById(R.id.textView11);

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

            plant.setH20Cycle(dataSnapshot.child("watering").getValue(String.class));
            plantWatering.setText("Water Cycle: "+plant.getH20Cycle());

            plant.setLight(dataSnapshot.child("light").getValue(String.class));
            light.setText("Lighting: "+plant.getLight());
            plant.setPests(dataSnapshot.child("pests").getValue(String.class));
            plant.setMaintanence(dataSnapshot.child("maintanence").getValue(String.class));
            maintanence.setText("Maintanence: "+plant.getMaintanence());

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
