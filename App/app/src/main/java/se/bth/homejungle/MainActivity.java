package se.bth.homejungle;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import se.bth.homejungle.storage.AppDatabase;
import se.bth.homejungle.storage.dao.PlantManager;
import se.bth.homejungle.storage.dao.SpeciesManager;
import se.bth.homejungle.storage.entity.Plant;
import se.bth.homejungle.storage.entity.Species;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
        PlantManager plantManager = db.getPlantManager();
        SpeciesManager speciesManager = db.getSpeciesManager();
        AppDatabase.databaseWriteExecutor.execute(() -> {
            Species species = new Species("Ficus", "Ficus category", "Nice tree for home", "Plant it", 0.5, 3, 5);
            long speciesId = speciesManager.insert(species);

            Plant newPlant = new Plant(speciesId,  "Ficus in living room");
            plantManager.insert(newPlant);
        });

        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_plant, R.id.navigation_database, R.id.navigation_calendar)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

}