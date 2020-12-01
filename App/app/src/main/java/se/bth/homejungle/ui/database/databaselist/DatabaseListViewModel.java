package se.bth.homejungle.ui.database.databaselist;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import se.bth.homejungle.storage.entity.Plant;
import se.bth.homejungle.storage.entity.Species;
import se.bth.homejungle.storage.repository.PlantRepository;
import se.bth.homejungle.storage.repository.SpeciesRepository;

public class DatabaseListViewModel extends AndroidViewModel {

    private final SpeciesRepository speciesRepository;
    private final PlantRepository plantRepository;
    private final LiveData<List<Species>> species;

    public DatabaseListViewModel(Application application) {
        super(application);
        speciesRepository = new SpeciesRepository(application);
        plantRepository = new PlantRepository(application);
        species = speciesRepository.getSpecies();
    }

    public LiveData<List<Species>> getSpecies() {
        return species;
    }

    public void insert(Species species){
        speciesRepository.insert(species);
    }

    public void insertToOwnPlants(long speciesId, String description){
        Plant newPlant = new Plant(speciesId,  description);
        plantRepository.insert(newPlant);
    }

}
