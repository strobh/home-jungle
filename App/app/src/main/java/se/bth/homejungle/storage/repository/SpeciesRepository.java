package se.bth.homejungle.storage.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import se.bth.homejungle.storage.AppDatabase;
import se.bth.homejungle.storage.dao.SpeciesManager;
import se.bth.homejungle.storage.entity.Species;
import se.bth.homejungle.storage.entity.SpeciesWithCategory;

public class SpeciesRepository {

    private SpeciesManager speciesManager;

    public SpeciesRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.speciesManager = db.getSpeciesManager();
    }

    public LiveData<List<Species>> getSpecies() {
        return speciesManager.getSpecies();
    }

    public LiveData<List<SpeciesWithCategory>> getSpeciesWithCategory() {
        return speciesManager.getSpeciesWithCategory();
    }

    public LiveData<List<Species>> getSpeciesByCategory(long categoryId) {
        return speciesManager.getSpeciesByCategory(categoryId);
    }

    public LiveData<List<Species>> getSpeciesByName(String name) {
        return speciesManager.getSpeciesByName(name);
    }

    public LiveData<Species> findById(long id) {
        return speciesManager.findById(id);
    }

    public LiveData<SpeciesWithCategory> findSpeciesWithCategoryById(long id) {
        return speciesManager.findSpeciesWithCategoryById(id);
    }

    public void insert(Species species) {
        AppDatabase.databaseWriteExecutor.execute(() -> speciesManager.insert(species));
    }

    public void delete(Species species) {
        AppDatabase.databaseWriteExecutor.execute(() -> speciesManager.delete(species));
    }
}
