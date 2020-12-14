package se.bth.homejungle.ui.plants.singleplant;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import se.bth.homejungle.storage.entity.Plant;
import se.bth.homejungle.storage.entity.PlantWithSpecies;
import se.bth.homejungle.storage.entity.Species;
import se.bth.homejungle.storage.repository.PlantRepository;
import se.bth.homejungle.storage.repository.SpeciesRepository;

public class SinglePlantViewModel extends AndroidViewModel {
    private final SpeciesRepository speciesRepository;

    public SinglePlantViewModel(@NonNull Application application) {
        super(application);
        speciesRepository = new SpeciesRepository(application);
    }

    public LiveData<Species> getSpeciesById(long speciesId){
        return speciesRepository.findById(speciesId);
    }

    /*public void delete(Plant plant) {
        plantRepository.delete(plant);
    }
*/
}
