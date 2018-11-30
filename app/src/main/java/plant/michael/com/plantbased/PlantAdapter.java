package plant.michael.com.plantbased;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.UserHolder> {

    private List<Plant> mPlantList;

    public PlantAdapter(List<Plant> plants) {
        mPlantList = plants;
    }

    @NonNull
    public UserHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_view, viewGroup, false);

        UserHolder holder = new UserHolder(itemView);

        return holder;
    }

    public void onBindViewHolder(@NonNull UserHolder userHolder, int i) {
        Plant plant = mPlantList.get(i);
        TextView firstNameTextView = userHolder.titleTextView;
        TextView lastNameTextView = userHolder.dateTextView;

        firstNameTextView.setText(plant.plantName);
        lastNameTextView.setText(plant.zone);
    }

    public int getItemCount() {
        return mPlantList.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView dateTextView;

        public UserHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = (TextView) itemView.findViewById(R.id.plantName);
            dateTextView = (TextView) itemView.findViewById(R.id.plantZone);
        }
    }




}
