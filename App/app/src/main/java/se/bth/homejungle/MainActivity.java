package se.bth.homejungle;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.time.LocalDate;
import java.util.concurrent.Future;

import se.bth.homejungle.storage.AppDatabase;
import se.bth.homejungle.storage.dao.CategoryManager;
import se.bth.homejungle.storage.dao.FuturePlantManager;
import se.bth.homejungle.storage.dao.PlantManager;
import se.bth.homejungle.storage.dao.SpeciesManager;
import se.bth.homejungle.storage.entity.FuturePlant;
import se.bth.homejungle.storage.entity.Plant;
import se.bth.homejungle.storage.entity.Species;
import se.bth.homejungle.storage.entity.SpeciesCategory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
        CategoryManager categoryManager = db.getCategoryManager();
        SpeciesManager speciesManager = db.getSpeciesManager();
        PlantManager plantManager = db.getPlantManager();
        FuturePlantManager futurePlantManager = db.getFuturePlantManager();
        /*
        AppDatabase.databaseWriteExecutor.execute(() -> {
            SpeciesCategory category1 = new SpeciesCategory("Trees");
            long categoryTree = categoryManager.insert(category1);
            SpeciesCategory category2 = new SpeciesCategory("Herbs");
            categoryManager.insert(category2);
            SpeciesCategory category3 = new SpeciesCategory("Flowers");
            long categoryFlower = categoryManager.insert(category3);

            Species species1 = new Species(categoryTree, "Ficus", "Nice tree for home", "Plant it", 0.5, 3, 5, LocalDate.of(0, 3, 1));
            long ficusId = speciesManager.insert(species1);
            Plant newPlant1 = new Plant(ficusId,  "Living room");
            newPlant1.setLastWatered(LocalDate.now().plusDays(5));
            plantManager.insert(newPlant1);
            Plant newPlant2 = new Plant(ficusId,  "Kitchen");
            newPlant2.setLastWatered(LocalDate.now().plusDays(-4));
            plantManager.insert(newPlant2);
            Plant newPlant3 = new Plant(ficusId,  "Bathroom");
            plantManager.insert(newPlant3);
            FuturePlant futurePlant1 = new FuturePlant(ficusId, "For balcony", LocalDate.now().plusMonths(5));
            futurePlantManager.insert(futurePlant1);
            FuturePlant futurePlant2 = new FuturePlant(ficusId, "For friends", LocalDate.now().plusMonths(5));
            futurePlantManager.insert(futurePlant2);
            FuturePlant futurePlant3 = new FuturePlant(ficusId, "For test", LocalDate.now().plusDays(2));
            futurePlantManager.insert(futurePlant3);

            Species species2 = new Species(categoryFlower, "Orchid", "Nice flower for home", "Plant it", 0.2, 5, 2, LocalDate.of(0, 3, 15));
            long orchidId = speciesManager.insert(species2);
            Plant newPlant4 = new Plant(orchidId,  "Living room");
            plantManager.insert(newPlant4);
        });
*/
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_plant, R.id.navigation_database, R.id.navigation_calendar, R.id.navigation_giveaways)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    //https://stackoverflow.com/questions/5448653/how-to-implement-onbackpressed-in-fragments
    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }
}