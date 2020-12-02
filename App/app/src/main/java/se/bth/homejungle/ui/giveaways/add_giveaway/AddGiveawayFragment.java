package se.bth.homejungle.ui.giveaways.add_giveaway;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import se.bth.homejungle.R;
import se.bth.homejungle.ui.giveaways.GiveawaysViewModel;

public class AddGiveawayFragment extends Fragment {
    EditText username;
    EditText contact;
    Button addButton;
    GiveawaysViewModel giveawaysViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        giveawaysViewModel = new ViewModelProvider(this).get(GiveawaysViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_giveaway, container, false);
        username = root.findViewById(R.id.edittext_username);
        contact = root.findViewById(R.id.edittext_contact);
        addButton = root.findViewById(R.id.button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameInput = username.getText().toString();
                String contactInput = contact.getText().toString();
                //TODO: create new giveaway with usernameInput, contactInput and Image
                //TODO: call insert on giveawaysviewmodel
            }
        });

        return root;
    }
}