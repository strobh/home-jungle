package se.bth.homejungle.ui.plants;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import se.bth.homejungle.R;
import se.bth.homejungle.adapter.YourPlantsAdapter;

public class YourPlantsFragment extends Fragment {

    ListView listView;
    ImageButton add_button;


    private YourPlantsViewModel plantViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        plantViewModel = new ViewModelProvider(this).get(YourPlantsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_your_plants, container, false);

        /*final TextView textView = root.findViewById(R.id.text_home);
        plantViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        add_button = (ImageButton) root.findViewById(R.id.btn_add);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(root).navigate(R.id.navigation_database);
            }
        });

        listView = (ListView) root.findViewById(R.id.idRecyclerView);
        ListAdapter listAdapter = new YourPlantsAdapter(getActivity(), plantViewModel.getPlantnames(), plantViewModel.getPlacedesc(),
                plantViewModel.getWater_amount(), plantViewModel.getWater_time(),plantViewModel.getImgid());
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NavDirections action = YourPlantsFragmentDirections.openSinglePlantView();
             //   OpenSinglePlantViewAction action = YourPlantsFragmentDirections.OpenSinglePlantView();
//                HomeFragment parentFragment = (HomeFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.homeFragment);

            //    Navigation.findNavController(root).navigate(R.id.testPlantFragment);
                NavController navController = Navigation.findNavController(root);
                Navigation.findNavController(root).navigate(action);

                Toast.makeText(getActivity(), "Item clicked", Toast.LENGTH_SHORT).show();
            }
        });

        WriteBtn(root);
        ReadBtn(root);

        return root;
    }

    // write text to file
    public void WriteBtn(View v) {
        // add-write text into file
        try {
            FileOutputStream fileout = getActivity().openFileOutput("your_plants.txt", getActivity().MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write("name");
            outputWriter.close();

            //display file saved message
            /*Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ReadBtn(View v) {
     //   TextView textmsg = v.findViewById(R.id.textmsg);
        //reading text from file
        try {
            FileInputStream fileIn = getActivity().openFileInput("your_plants.txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);

            char[] inputBuffer = new char[100];
            String s = "";
            int charRead;

            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                // char to string conversion
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;
            }
            InputRead.close();
       //     textmsg.setText(s);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}