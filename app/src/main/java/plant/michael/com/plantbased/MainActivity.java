package plant.michael.com.plantbased;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);

        List<Plant> plant = new ArrayList<Plant>();

        Plant plant1 = new Plant("Spider", "9-11", "warm, tropical climate");
        Plant plant2 = new Plant("Pothos", "10-11", "Most Enviorments");

        plant.add(plant1);
        plant.add(plant2);
         // Write a message to the database
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("plants");

        String id = database.push().getKey();
        //database.setValue(plant.get(0));
        //database.setValue(plant.get(1));
        database.setValue(plant);

        PlantAdapter adapter = new PlantAdapter(plant);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mRecyclerView.setAdapter(adapter);
    }
}
