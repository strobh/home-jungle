package se.bth.homejungle.ui.plants.futureplants;

import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;
import se.bth.homejungle.R;
import se.bth.homejungle.adapter.FuturePlantsAdapter;
import se.bth.homejungle.storage.entity.view.FuturePlantWithSpecies;
import se.bth.homejungle.ui.Source;
import se.bth.homejungle.ui.plants.HomeFragmentDirections;

/**
 * FuturePlantsFragment contains a RecyclerView which contains all plants that are as future plants
 * in the local database. The recyclerview is attached with the FuturePlantsAdapter.
 *
 * Furthermore, the recyclerview has an itemToucher, so that a swipe to left will call the
 * delete-function for the listitem.
 */

public class FuturePlantsFragment extends Fragment {

    RecyclerView recyclerView;
    ImageButton add_button;
    TextView noPlant;
    Button noPlantBtn;

    private FuturePlantsViewModel futurePlantsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        futurePlantsViewModel = new ViewModelProvider(this).get(FuturePlantsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_future_plants, container, false);

        noPlant = root.findViewById(R.id.tv_no_plant);
        noPlantBtn = root.findViewById(R.id.btn_no_plant);
        recyclerView = root.findViewById(R.id.idRecyclerView);
        final FuturePlantsAdapter adapter = new FuturePlantsAdapter(new FuturePlantsAdapter.PlantDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        futurePlantsViewModel.getFuturePlantsWithSpecies().observe(getViewLifecycleOwner(), plants -> {
            if(plants.size() < 1){
                noPlant.setVisibility(View.VISIBLE);
                noPlantBtn.setVisibility(View.VISIBLE);
            } else {
                noPlantBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        NavDirections action = HomeFragmentDirections.homeToDatabase().setSource(Source.FUTUREPLANTS);
                        Navigation.findNavController(root).navigate(action);
                    }
                });
            }
            Log.v("Database", "Future plants: " + plants.size());
            adapter.submitList(plants);
        });

        add_button = root.findViewById(R.id.btn_add);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = HomeFragmentDirections.homeToDatabase().setSource(Source.FUTUREPLANTS);
                Navigation.findNavController(root).navigate(action);
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            FuturePlantWithSpecies deleteItem;

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int itemPosition = viewHolder.getAdapterPosition();
                deleteItem = adapter.getByPosition(itemPosition);
                futurePlantsViewModel.delete(deleteItem.getFuturePlant());
                showUndoSnackbar();
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                       // .addBackgroundColor(ContextCompat.getColor(getContext(), R.color.red))
                        .addActionIcon(R.drawable.ic_delete)
                        .create()
                        .decorate();

                super.onChildDraw(c, recyclerView, viewHolder, dX,
                        dY, actionState, isCurrentlyActive);
            }

            private void showUndoSnackbar() {
                View view = getActivity().findViewById(R.id.idRecyclerView);
                Snackbar snackbar = Snackbar.make(view, R.string.snack_bar_text, Snackbar.LENGTH_LONG);
                snackbar.setAction(R.string.snack_bar_undo, v -> undoDelete());
                snackbar.show();
            }

            private void undoDelete(){
                futurePlantsViewModel.insert(deleteItem.getFuturePlant());
                System.out.println("undo delete!");
            }
        });

        itemTouchHelper.attachToRecyclerView(recyclerView);
        return root;
    }

}