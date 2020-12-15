package se.bth.homejungle.ui.plants.singleplant;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import se.bth.homejungle.storage.entity.Species;
import se.bth.homejungle.storage.repository.FuturePlantRepository;
import se.bth.homejungle.storage.repository.PlantRepository;
import se.bth.homejungle.storage.repository.SpeciesRepository;

/**
 * The SinglePlantViewModel is used to get information about a single plant.
 * It is used by the SinglePlantFragment, the PlantInfoFragment and the
 * PlantStartFragment.
 */

public class SinglePlantViewModel extends AndroidViewModel {
    private final SpeciesRepository speciesRepository;
    private final PlantRepository plantRepository;
    private final FuturePlantRepository futurePlantRepository;

    public SinglePlantViewModel(@NonNull Application application) {
        super(application);
        speciesRepository = new SpeciesRepository(application);
        plantRepository = new PlantRepository(application);
        futurePlantRepository = new FuturePlantRepository(application);
    }

    public LiveData<Species> getSpeciesById(long speciesId){
        return speciesRepository.findById(speciesId);
    }

    public void deletePlant(long id) {
        plantRepository.deleteById(id);
    }

    public void deleteFuturePlant(long id) {
        futurePlantRepository.deleteById(id);
    }

}
