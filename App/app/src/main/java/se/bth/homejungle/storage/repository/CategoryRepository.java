package se.bth.homejungle.storage.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import se.bth.homejungle.storage.AppDatabase;
import se.bth.homejungle.storage.dao.CategoryManager;
import se.bth.homejungle.storage.entity.SpeciesCategory;

/**
 * CategoryManager is an intermediate helper in between the Data Access Object (DAO) and the view
 * model that is used to manage plant species in the local database.
 *
 * The repository is used to decouple the view model from the real source of the data (database)
 * and to start write tasks in another thread than the UI thread.
 */
public class CategoryRepository {

    /**
     * The category manager that is used to access the database.
     */
    private final CategoryManager categoryManager;

    /**
     * Creates a new category repository.
     *
     * @param application Reference to the application which is needed to create the database.
     */
    public CategoryRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.categoryManager = db.getCategoryManager();
    }

    /**
     * Returns a list of all species categories.
     *
     * @return List of all species categories.
     */
    public LiveData<List<SpeciesCategory>> getCategories() {
        return categoryManager.getCategories();
    }

    /**
     * Searches and returns the species category with the given id.
     *
     * @param id The id of the species category to look up.
     * @return The species category with the given id.
     */
    public LiveData<SpeciesCategory> findById(long id) {
        return categoryManager.findById(id);
    }

    /**
     * Creates a new species category in the database.
     *
     * This task is executed in another thread in order to be able to call this inside of the UI.
     *
     * @param speciesCategory The species category entity object to store in the database.
     */
    public void insert(SpeciesCategory speciesCategory) {
        AppDatabase.databaseWriteExecutor.execute(() -> categoryManager.insert(speciesCategory));
    }

    /**
     * Deletes a species category from the database.
     *
     * This task is executed in another thread in order to be able to call this inside of the UI.
     *
     * @param speciesCategory The species category entity object to delete from the database.
     */
    public void delete(SpeciesCategory speciesCategory) {
        AppDatabase.databaseWriteExecutor.execute(() -> categoryManager.delete(speciesCategory));
    }
}
