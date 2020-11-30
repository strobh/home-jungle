package se.bth.homejungle.ui.database.databaselist;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import se.bth.homejungle.storage.entity.Species;
import se.bth.homejungle.storage.repository.SpeciesRepository;

public class DatabaseListViewModel extends AndroidViewModel {

    private final SpeciesRepository speciesRepository;
    private final LiveData<List<Species>> species;

    public DatabaseListViewModel(Application application) {
        super(application);
        speciesRepository = new SpeciesRepository(application);
        species = speciesRepository.getSpecies();
    }

    public LiveData<List<Species>> getSpecies() {
        return species;
    }

    public void insert(Species species){
        speciesRepository.insert(species);
    }

}
