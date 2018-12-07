package plant.michael.com.plantbased;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

 public abstract class SIngletonPlantActivity extends AppCompatActivity {
        protected abstract Fragment createFragment();


 }
