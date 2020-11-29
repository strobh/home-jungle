package se.bth.homejungle.ui.giveaways;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import se.bth.homejungle.R;

public class GiveawaysFragment extends Fragment {

    private GiveawaysViewModel mViewModel;

    public static GiveawaysFragment newInstance() {
        return new GiveawaysFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_giveaways, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(GiveawaysViewModel.class);
        // TODO: Use the ViewModel
    }

}