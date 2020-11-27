package se.bth.homejungle.ui.plants.yourplants;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import se.bth.homejungle.storage.entity.Plant;
import se.bth.homejungle.storage.repository.PlantRepository;

public class YourPlantsViewModel extends AndroidViewModel {

    private final PlantRepository plantRepository;
    private final LiveData<List<Plant>> plants;

    public YourPlantsViewModel(Application application) {
        super(application);
        plantRepository = new PlantRepository(application);
        plants = plantRepository.getAll();
    }

    public LiveData<List<Plant>> getPlants() {
        return plants;
    }

    public void insert(Plant plant) {
        plantRepository.insert(plant);
    }
}