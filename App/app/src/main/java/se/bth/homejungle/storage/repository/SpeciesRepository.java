package se.bth.homejungle.storage.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import se.bth.homejungle.storage.AppDatabase;
import se.bth.homejungle.storage.dao.SpeciesManager;
import se.bth.homejungle.storage.entity.Species;

public class SpeciesRepository {

    private SpeciesManager speciesManager;

    public SpeciesRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.speciesManager = db.getSpeciesManager();
    }

    public LiveData<List<Species>> getSpecies() {
        return speciesManager.getSpecies();
    }

    public LiveData<List<Species>> getAllByCategory(String category) {
        return speciesManager.getAllByCategory(category);
    }

    public LiveData<List<Species>> getAllByName(String name) {
        return speciesManager.getAllByName(name);
    }

    public LiveData<Species> findById(long id) {
        return speciesManager.findById(id);
    }

    public void insert(Species species) {
        AppDatabase.databaseWriteExecutor.execute(() -> speciesManager.insert(species));
    }

    public void delete(Species species) {
        AppDatabase.databaseWriteExecutor.execute(() -> speciesManager.delete(species));
    }
}
