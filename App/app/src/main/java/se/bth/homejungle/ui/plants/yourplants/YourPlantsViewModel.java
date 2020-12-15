package se.bth.homejungle.ui.plants.yourplants;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import se.bth.homejungle.storage.entity.Plant;
import se.bth.homejungle.storage.entity.view.PlantWithSpecies;
import se.bth.homejungle.storage.repository.PlantRepository;

public class YourPlantsViewModel extends AndroidViewModel {

    private final PlantRepository plantRepository;
    private final LiveData<List<PlantWithSpecies>> plantsWithSpecies;

    public YourPlantsViewModel(Application application) {
        super(application);
        plantRepository = new PlantRepository(application);
        plantsWithSpecies = plantRepository.getPlantsWithSpecies();
    }

    public LiveData<List<PlantWithSpecies>> getPlantsWithSpecies() {
        return plantsWithSpecies;
    }

    public LiveData<List<Plant>> getPlants() { return plantRepository.getPlants(); }

    public void insert(Plant plant) {
        plantRepository.insert(plant);
    }

    public void delete(Plant plant) {
        plantRepository.delete(plant);
    }
}