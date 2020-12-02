package se.bth.homejungle.storage.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import se.bth.homejungle.storage.AppDatabase;
import se.bth.homejungle.storage.dao.SpeciesManager;
import se.bth.homejungle.storage.entity.Species;
import se.bth.homejungle.storage.entity.SpeciesWithCategory;

/**
 * SpeciesRepository is an intermediate helper in between the Data Access Object (DAO) and the
 * view model that is used to manage plant species in the local database.
 *
 * The repository is used to decouple the view model from the real source of the data (database)
 * and to start write tasks in another thread than the UI thread.
 */
public class SpeciesRepository {

    /**
     * The species manager that is used to access the database.
     */
    private final SpeciesManager speciesManager;

    /**
     * Creates a new plant species repository.
     *
     * @param application Reference to the application which is needed to create the database.
     */
    public SpeciesRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.speciesManager = db.getSpeciesManager();
    }

    /**
     * Returns a list of all plant species.
     *
     * @return List of all plant species.
     */
    public LiveData<List<Species>> getSpecies() {
        return speciesManager.getSpecies();
    }

    /**
     * Returns a list of all plant species with their corresponding categories.
     *
     * @return List of all plant species with their corresponding categories.
     */
    public LiveData<List<SpeciesWithCategory>> getSpeciesWithCategory() {
        return speciesManager.getSpeciesWithCategory();
    }

    /**
     * Returns a list of all plant species with a given category.
     *
     * @param categoryId The id of the category to filter for.
     * @return List of all plant species with a given category.
     */
    public LiveData<List<Species>> getSpeciesByCategory(long categoryId) {
        return speciesManager.getSpeciesByCategory(categoryId);
    }

    /**
     * Returns a list of all plant species with a given name.
     *
     * @param name The name (or part of it) to search for.
     * @return List of all plant species with a given name.
     */
    public LiveData<List<Species>> getSpeciesByName(String name) {
        return speciesManager.getSpeciesByName(name);
    }

    /**
     * Searches and returns the plant species with the given id.
     *
     * @param id The id of the plant species to look up.
     * @return The plant species with the given id.
     */
    public LiveData<Species> findById(long id) {
        return speciesManager.findById(id);
    }

    /**
     * Searches and returns the plant species with the given id and its corresponding category.
     *
     * @param id The id of the plant species to look up.
     * @return The plant species with the given id and its corresponding category.
     */
    public LiveData<SpeciesWithCategory> findSpeciesWithCategoryById(long id) {
        return speciesManager.findSpeciesWithCategoryById(id);
    }

    /**
     * Creates a new plant species in the database.
     *
     * This task is executed in another thread in order to be able to call this inside of the UI.
     *
     * @param species The plant species entity object to store in the database.
     */
    public void insert(Species species) {
        AppDatabase.databaseWriteExecutor.execute(() -> speciesManager.insert(species));
    }

    /**
     * Deletes a plant species from the database.
     *
     * This task is executed in another thread in order to be able to call this inside of the UI.
     *
     * @param species The plant species entity object to delete from the database.
     */
    public void delete(Species species) {
        AppDatabase.databaseWriteExecutor.execute(() -> speciesManager.delete(species));
    }
}
