package plant.michael.com.plantbased;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
    private FirebaseUser user;
    public Plant Test = new Plant();
    public static ArrayList<Plant> plants = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerView);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        // I want to get the username of the perosn that logged in
        //String username = login name here
        myUsername = mFirebaseDatabase.getReference().child("User").child("michaelposada").child("plantID");

        //Button ViewYourPlant;
        //Button ViewAllPlants;
        //Button AddPlants;
        //This will be for clicking on  a button of List all Plants or Your plants


        //ONCLICK EVENT FOR VIEWYOUPLANTS
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        myUsername.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //showAllData(dataSnapshot);
                String data = dataSnapshot.getValue(String.class);
                System.out.println(data);
                data = data.replace(",","");

                System.out.println(data.length());
                for(int i=0; i<data.length(); i++) {
                    myRef = mFirebaseDatabase.getReference().child("plants").child(String.valueOf(data.charAt(i)));

                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            plants.add(showAllData(dataSnapshot));
                            System.out.println("In the second onDataChange Method Plants Size is "+plants.size());
                            System.out.println("Outside,at the end of bundle method " + plants.size());
                            PlantAdapter adapter = new PlantAdapter(plants);
                            mRecyclerView.setLayoutManager(new LinearLayoutManager(viewDatabase.this));
                            mRecyclerView.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ END OF ON CLICK EVENT FOR VIEW ALL YOUR PLANTS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ON CLICK EVENT FOR VIEW ALL PLANTS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        //myRef = mFirebaseDatabase.getReference().child("plants");
        //myRef.addValueEventListener(new ValueEventListener() {
           // @Override
            //public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
             //   showTable(dataSnapshot);
            //}

            //@Override
            //public void onCancelled(@NonNull DatabaseError databaseError) {

           // }
        //});

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ END OF ON CLICK EVENT FOR VIEW ALL PLANTS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~



        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ON CLICK FOR ADDING PLANTS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


        //this should go to a new page to show it off the add features

        //Now we need to add a new Plant for the User
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        test = mFirebaseDatabase.getReference().child("User").child("michaelposada").child("plantID");
        test.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                value = value + "," + "2";
                test.setValue(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //That should have updated the database. Need to be tested though



        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~END OF ON CLICK EVENT FOR ADDING~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~



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
        PlantAdapter adapter = new PlantAdapter(plants);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(viewDatabase.this));
        mRecyclerView.setAdapter(adapter);

    }




}
