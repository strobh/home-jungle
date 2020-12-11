package se.bth.homejungle.ui.giveaways;

import android.content.SharedPreferences;
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
import androidx.annotation.Nullable;
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
import se.bth.homejungle.adapter.GiveawaysAdapter;
import se.bth.homejungle.ui.Source;
import se.bth.homejungle.ui.MarketplacePlant;

import static android.content.Context.MODE_PRIVATE;

public class GiveawaysFragment extends Fragment {

    private GiveawaysViewModel giveawaysViewModel;
    RecyclerView recyclerView;
    ImageButton add_button;
    Button no_giveaway_button;
    TextView no_giveaway_tv;
    String userid;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        giveawaysViewModel = new ViewModelProvider(this).get(GiveawaysViewModel.class);
        View root = inflater.inflate(R.layout.fragment_giveaways, container, false);
        recyclerView = root.findViewById(R.id.idRecyclerView);
        add_button = root.findViewById(R.id.btn_add);
        no_giveaway_button = root.findViewById(R.id.btn_no_giveaway);
        no_giveaway_tv = root.findViewById(R.id.tv_no_giveaway);
        final GiveawaysAdapter adapter = new GiveawaysAdapter(new GiveawaysAdapter.PlantDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        SharedPreferences sp = getActivity().getSharedPreferences("userdata", MODE_PRIVATE);
        if (sp.contains("userid")) {
            userid = sp.getString("userid", null);
            giveawaysViewModel.getOwnGiveawaysLiveData(userid).observe(getViewLifecycleOwner(), Observable -> {
                System.out.println("Changes in first method");
            });
            giveawaysViewModel.getOwnGiveaways().observe(getViewLifecycleOwner(), giveaways -> {
                System.out.println("Changes detected");
                Log.w("Giveaways", "Own giveaways: " + giveaways.size());
                if (giveaways.size() > 0) {
                    no_giveaway_tv.setVisibility(View.INVISIBLE);
                    no_giveaway_button.setVisibility(View.INVISIBLE);
                }
                adapter.submitList(giveaways);
            });
        }

        no_giveaway_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = GiveawaysFragmentDirections.giveawaysToDatabase().setSource(Source.GIVEAWAYS);
                Navigation.findNavController(root).navigate(action);
            }
        });
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavDirections action = GiveawaysFragmentDirections.giveawaysToDatabase().setSource(Source.GIVEAWAYS);
                Navigation.findNavController(root).navigate(action);
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            MarketplacePlant deleteItem;

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int itemPosition = viewHolder.getAdapterPosition();
                deleteItem = adapter.getByPosition(itemPosition);
                giveawaysViewModel.delete(deleteItem.getId());
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

            public void showUndoSnackbar(){
                Snackbar snackbar = Snackbar.make(recyclerView, R.string.snack_bar_text, Snackbar.LENGTH_SHORT);
                snackbar.setAction(R.string.snack_bar_undo, v->undoDelete());
                snackbar.show();
            }

            public void undoDelete(){
         //       plantViewModel.insert(deleteItem.getPlant());
            }
        });

        itemTouchHelper.attachToRecyclerView(recyclerView);

        return root;
    }

    public void getData(){

    }

}