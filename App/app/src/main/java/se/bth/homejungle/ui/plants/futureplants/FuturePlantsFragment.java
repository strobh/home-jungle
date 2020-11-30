package se.bth.homejungle.ui.plants.futureplants;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import se.bth.homejungle.R;
import se.bth.homejungle.adapter.CustomAdapter;
import se.bth.homejungle.adapter.FuturePlantsAdapter;
import se.bth.homejungle.adapter.YourPlantsListAdapter;
import se.bth.homejungle.storage.entity.FuturePlantWithSpecies;
import se.bth.homejungle.ui.plants.HomeFragmentDirections;
import se.bth.homejungle.ui.plants.yourplants.YourPlantsViewModel;

public class FuturePlantsFragment extends Fragment {

    RecyclerView recyclerView;
    ImageButton add_button;

    private FuturePlantsViewModel futurePlantsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        futurePlantsViewModel = new ViewModelProvider(this).get(FuturePlantsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_future_plants, container, false);

        recyclerView = root.findViewById(R.id.idRecyclerView);
        final FuturePlantsAdapter adapter = new FuturePlantsAdapter(new FuturePlantsAdapter.PlantDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        futurePlantsViewModel.getFuturePlantsWithSpecies().observe(getViewLifecycleOwner(), plants -> {
            Log.v("Database", "Future plants: " + plants.size());
            adapter.submitList(plants);
        });

        add_button = root.findViewById(R.id.btn_add);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = HomeFragmentDirections.homeToDatabase(2);
                Navigation.findNavController(root).navigate(action);
            }
        });


     //   ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeItemAdapter(customAdapter));
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            ColorDrawable background = new ColorDrawable(Color.RED);;

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                // Remove item from backing list here
                //futurePlantsViewModel.delete();


                int itemPosition = viewHolder.getAdapterPosition();
                futurePlantsViewModel.getFuturePlantsWithSpecies().observe(getViewLifecycleOwner(), plants -> {
                    Log.v("Delete: ", plants.get(itemPosition).getFuturePlant().getDescription());
                    long plant_id = plants.get(itemPosition).getFuturePlant().getId();
                    futurePlantsViewModel.delete(plant_id);
                });

             /*   View customView = inflater.inflate(R.layout.popup_window, null);
                PopupWindow mPopupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                mPopupWindow.showAtLocation(getView(), Gravity.CENTER,0,0);
                customAdapter.notifyDataSetChanged();*/
                showUndoSnackbar();
            }


            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX,
                        dY, actionState, isCurrentlyActive);
                View itemView = viewHolder.itemView;
                int backgroundCornerOffset = 20;
                if (dX > 0) { // Swiping to the right
                    background.setBounds(itemView.getLeft(), itemView.getTop(),
                            itemView.getLeft() + ((int) dX) + backgroundCornerOffset,
                            itemView.getBottom());

                } else if (dX < 0) { // Swiping to the left
                    background.setBounds(itemView.getRight() + ((int) dX) - backgroundCornerOffset,
                            itemView.getTop(), itemView.getRight(), itemView.getBottom());
                } else { // view is unSwiped
                    background.setBounds(0, 0, 0, 0);
                }
                background.draw(c);
            }

            private void showUndoSnackbar() {
                View view = getActivity().findViewById(R.id.idRecyclerView);
                Snackbar snackbar = Snackbar.make(view, R.string.snack_bar_text,
                        Snackbar.LENGTH_LONG);
                snackbar.setAction("Undo", v -> undoDelete());
      //          snackbar.setAction(R.string.snack_bar_undo, v -> undoDelete());
                snackbar.show();
            }

            private void undoDelete(){
                System.out.println("undo delete!");
            }

          /*  private void undoDelete() {
                mListItems.add(mRecentlyDeletedItemPosition,
                        mRecentlyDeletedItem);
                notifyItemInserted(mRecentlyDeletedItemPosition);
            }*/
        });

        itemTouchHelper.attachToRecyclerView(recyclerView);
        // Inflate the layout for this fragment
        return root;
    }

}