package se.bth.homejungle.ui.plants.singleplant;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import se.bth.homejungle.storage.entity.Plant;
import se.bth.homejungle.storage.entity.PlantWithSpecies;
import se.bth.homejungle.storage.repository.PlantRepository;

public class SinglePlantViewModel extends AndroidViewModel {
    private final PlantRepository plantRepository;
    private final LiveData<List<PlantWithSpecies>> plantsWithSpecies;

    public SinglePlantViewModel(@NonNull Application application) {
        super(application);
        plantRepository = new PlantRepository(application);
        plantsWithSpecies = plantRepository.getPlantsWithSpecies();
    }

    public LiveData<List<PlantWithSpecies>> getPlantsWithSpecies() {
        return plantsWithSpecies;
    }

    public LiveData<List<Plant>> getPlants() { return plantRepository.getPlants(); }

    public LiveData<Plant> getPlantById(long plantid){
        return plantRepository.findById(plantid);
    }

    public void delete(Plant plant) {
        plantRepository.delete(plant);
    }

}
