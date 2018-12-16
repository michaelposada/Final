package plant.michael.com.plantbased;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SinglePlant extends AppCompatActivity {
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
    FloatingActionButton add;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_view);
        plantName = findViewById(R.id.plantName);
        plantZone = findViewById(R.id.plantZone);
        plantWatering = findViewById(R.id.textView9);
        fertlize = findViewById(R.id.textView10);
        light = findViewById(R.id.textView11);
        pests = findViewById(R.id.textView12);
        funFact = findViewById(R.id.textview13);
        add = findViewById(R.id.fab);

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

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent moving = new Intent(SinglePlant.this,viewDatabase.class);
                Intent old = getIntent();
                final String name = old.getStringExtra("user");
                String plant = old.getStringExtra("name");
                moving.putExtra("user",name);
                // moving.putExtra("plant",plant);



                mFirebaseDatabase = FirebaseDatabase.getInstance();

                for(int i =0; i<100; i++) {
                    final String t = String.valueOf(i);

                    myRef = mFirebaseDatabase.getReference().child("plants").child(t);
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            boolean found =showTableAdd(dataSnapshot);
                            if(found == true)
                            {
                                myRef = mFirebaseDatabase.getReference().child("User").child(name).child("plantID");
                                myRef.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        String list = dataSnapshot.getValue(String.class);
                                        System.out.println(t);
                                        System.out.println(list);
                                        if(list.contains(t))
                                        {
                                            Toast.makeText(SinglePlant.this,"You already Have That Plant",Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            Toast.makeText(SinglePlant.this,"Added!",Toast.LENGTH_SHORT).show();
                                            list = list + "," + t;
                                            System.out.println(list);
                                            System.out.println("SinglePlantClass");
                                            myRef.setValue(list);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });


                                startActivity(moving);
                            }
                            else
                                {

                                }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }

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

    private boolean showTableAdd(DataSnapshot dataSnapshot) {
        ArrayList<Plant> plants = new ArrayList<Plant>();

        String l = dataSnapshot.child("plantName").getValue(String.class);
        System.out.println(l);


        Intent j = getIntent();
        String wanted = " ";
        wanted = j.getStringExtra("name");
        Plant plant  = new Plant();




        if (wanted.equals(l))
        {
            return true;
        }
        else
        {

        }

        return false;
    }




}
