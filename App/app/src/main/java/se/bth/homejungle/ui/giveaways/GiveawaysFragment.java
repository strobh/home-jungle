package se.bth.homejungle.ui.giveaways;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import se.bth.homejungle.R;
import se.bth.homejungle.adapter.GiveawaysAdapter;
import se.bth.homejungle.storage.entity.Plant;
import se.bth.homejungle.ui.Source;

public class GiveawaysFragment extends Fragment {

    private GiveawaysViewModel giveawaysViewModel;
    RecyclerView recyclerView;
    ImageButton add_button;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        giveawaysViewModel = new ViewModelProvider(this).get(GiveawaysViewModel.class);
        View root = inflater.inflate(R.layout.fragment_giveaways, container, false);
        recyclerView = root.findViewById(R.id.idRecyclerView);
        add_button = root.findViewById(R.id.btn_add);
        final GiveawaysAdapter adapter = new GiveawaysAdapter(new GiveawaysAdapter.PlantDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //TODO: get all own giveaways from viewmodel

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = GiveawaysFragmentDirections.giveawaysToDatabase(Source.GIVEAWAYS);
                Navigation.findNavController(root).navigate(action);
            }
        });

        return root;
    }


    //TODO: change entity plant to giveaway
    public void insertPlant(Plant plant){
        giveawaysViewModel.insert(plant);
    }

}