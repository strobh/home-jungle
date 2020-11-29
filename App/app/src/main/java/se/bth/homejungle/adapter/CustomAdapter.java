package se.bth.homejungle.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import se.bth.homejungle.R;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private String[] plantname;
    private String[] plantdate;
    private Integer[] imgid;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView plant_name;
        private final TextView plant_date;
        private final ImageView plant_img;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            plant_name = view.findViewById(R.id.tv_plant_name);
            plant_date = view.findViewById(R.id.plant_date);
            plant_img = view.findViewById(R.id.plant_img);
        }

        public TextView getPlant_name() {
            return plant_name;
        }

        public TextView getPlant_date(){
            return plant_date;
        }

        public ImageView getPlant_img(){
            return plant_img;
        }
    }

    public CustomAdapter(String[] plantname, String[] plantdate, Integer[] imgid) {
        this.plantname = plantname;
        this.plantdate = plantdate;
        this.imgid = imgid;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.future_plants_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getPlant_date().setText(plantdate[position]);
        viewHolder.getPlant_name().setText(plantname[position]);
        viewHolder.getPlant_img().setImageResource(imgid[position]);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return plantdate.length;
    }
}
