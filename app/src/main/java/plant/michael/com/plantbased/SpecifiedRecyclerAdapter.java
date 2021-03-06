package plant.michael.com.plantbased;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;



public  class SpecifiedRecyclerAdapter extends RecyclerView.Adapter<SpecifiedRecyclerAdapter.RecyclerViewHolder>{
    private Context mContext;
    private List<Plant> plant;
    private String Name;
    private OnItemClickListener mListener;

    public SpecifiedRecyclerAdapter(Context context, ArrayList<Plant> mPlant, String name) {
        mContext = context;
        plant = mPlant;
        Name = name;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.recycler_row, parent, false);
        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        Plant currentPlant= plant.get(position);
        holder.nameTextView.setText(currentPlant.getPlantName());


        holder.nameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext,SpecifiedSinglePlant.class);
                i.putExtra("name", holder.nameTextView.getText().toString());
                i.putExtra("user",holder.name);
                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return plant.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView nameTextView;
        public String name = Name;


        public RecyclerViewHolder(View itemView) {
            super(itemView);
            nameTextView =itemView.findViewById ( R.id.nameTextView);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mListener.onItemClick(position);
                }

            }
        }

    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

}