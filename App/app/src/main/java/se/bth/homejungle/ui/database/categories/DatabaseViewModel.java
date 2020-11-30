package se.bth.homejungle.ui.database.categories;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import se.bth.homejungle.storage.entity.Species;
import se.bth.homejungle.storage.repository.SpeciesRepository;

public class DatabaseViewModel extends AndroidViewModel {

    private final SpeciesRepository speciesRepository;
    private final LiveData<List<Species>> species;

    public DatabaseViewModel(Application application) {
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