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
    private  RecyclerView mRecyclerView;
    private String userID;
    private FirebaseUser user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);

    mFirebaseDatabase = FirebaseDatabase.getInstance();
    myRef = mFirebaseDatabase.getReference().child("plants");
    //userID = user.getUid();

        myRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            showData(dataSnapshot);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });

    }

    private void showData(DataSnapshot dataSnapshot) {
        ArrayList<Plant> plants = new ArrayList<Plant>();


        for(DataSnapshot ds : dataSnapshot.getChildren())
        {
            Plant plant  = new Plant();
            plant.setPlantName((String)ds.getValue(Plant.class).getPlantName());

            plant.setEnviorment(ds.getValue(Plant.class).getEnviorment());
            plant.setZone(ds.getValue(Plant.class).getZone());
            plants.add(plant);

            System.out.print(plant.getPlantName());

        }
        System.out.println(plants.size());
        PlantAdapter adapter = new PlantAdapter(plants);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(viewDatabase.this));
        mRecyclerView.setAdapter(adapter);

    }


}
