package se.bth.homejungle.ui.giveaways;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import se.bth.homejungle.R;
import se.bth.homejungle.adapter.CustomAdapter;
import se.bth.homejungle.storage.entity.Plant;

public class GiveawaysListItem extends CustomAdapter.ViewHolder {

    ImageView giveaways_img;
    TextView giveaways_name;

    public GiveawaysListItem(View view) {
        super(view);
        giveaways_img = view.findViewById(R.id.giveaway_img);
        giveaways_name = view.findViewById(R.id.species_name);
    }

    //TODO: change to giveaway-entity and add img
    public void bind(Plant plant){
         giveaways_name.setText(plant.getDescription());
    }

    public static GiveawaysListItem create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.giveaways_list_item, parent, false);
        return new GiveawaysListItem(view);
    }
}
