package se.bth.homejungle.ui.giveaways;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import se.bth.homejungle.R;
import se.bth.homejungle.adapter.CustomAdapter;
import se.bth.homejungle.storage.entity.Plant;
import se.bth.homejungle.ui.MarketplacePlant;

public class GiveawaysListItem extends CustomAdapter.ViewHolder {

    ImageView giveaways_img;
    TextView giveaways_name;

    public GiveawaysListItem(View view) {
        super(view);
        giveaways_img = view.findViewById(R.id.giveaway_img);
        giveaways_name = view.findViewById(R.id.species_name);
    }

    public void bind(MarketplacePlant giveaway){
         giveaways_name.setText(giveaway.getSpeciesname());
    }

    public static GiveawaysListItem create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.giveaways_list_item, parent, false);
        return new GiveawaysListItem(view);
    }
}
