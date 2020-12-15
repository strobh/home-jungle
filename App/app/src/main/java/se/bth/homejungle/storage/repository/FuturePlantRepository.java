package se.bth.homejungle.storage.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import se.bth.homejungle.storage.AppDatabase;
import se.bth.homejungle.storage.dao.FuturePlantManager;
import se.bth.homejungle.storage.entity.FuturePlant;
import se.bth.homejungle.storage.entity.view.FuturePlantWithSpecies;

/**
 * FuturePlantRepository is an intermediate helper in between the Data Access Object (DAO) and the
 * view model that is used to manage plant species in the local database.
 *
 * The repository is used to decouple the view model from the real source of the data (database)
 * and to start write tasks in another thread than the UI thread.
 */
public class FuturePlantRepository {

    /**
     * The future plant manager that is used to access the database.
     */
    private final FuturePlantManager futurePlantManager;

    /**
     * Creates a new future plant repository.
     *
     * @param application Reference to the application which is needed to create the database.
     */
    public FuturePlantRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.futurePlantManager = db.getFuturePlantManager();
    }

    /**
     * Returns a list of all future plants.
     *
     * @return List of all future plants.
     */
    public LiveData<List<FuturePlant>> getFuturePlants() {
        return futurePlantManager.getFuturePlants();
    }

    /**
     * Returns a list of all future plants with their corresponding species.
     *
     * @return List of all future plants with their corresponding species.
     */
    public LiveData<List<FuturePlantWithSpecies>> getFuturePlantsWithSpecies() {
        return futurePlantManager.getFuturePlantsWithSpecies();
    }

    /**
     * Searches and returns the future plant with the given id.
     *
     * @param id The id of the future plant to look up.
     * @return The future plant with the given id.
     */
    public LiveData<FuturePlant> findById(long id) {
        return futurePlantManager.findById(id);
    }

    /**
     * Creates a new future plant in the database.
     *
     * This task is executed in another thread in order to be able to call this inside of the UI.
     *
     * @param futurePlant The future plant entity object to store in the database.
     */
    public void insert(FuturePlant futurePlant) {
        AppDatabase.databaseWriteExecutor.execute(() -> futurePlantManager.insert(futurePlant));
    }

    /**
     * Updates an existing future plant object in the database with new values.
     *
     * This task is executed in another thread in order to be able to call this inside of the UI.
     *
     * @param futurePlant The updated future plant entity object with the new values.
     */
    public void update(FuturePlant futurePlant) {
        AppDatabase.databaseWriteExecutor.execute(() -> futurePlantManager.update(futurePlant));
    }

    /**
     * Deletes a future plant from the database.
     *
     * This task is executed in another thread in order to be able to call this inside of the UI.
     *
     * @param futurePlant The future plant entity object to delete from the database.
     */
    public void delete(FuturePlant futurePlant) {
        AppDatabase.databaseWriteExecutor.execute(() -> futurePlantManager.delete(futurePlant));
    }

    /**
     * Deletes a future plant from the database.
     *
     * @param id The id of the future plant entity to delete from the database.
     */
    public void deleteById(long id) {
        AppDatabase.databaseWriteExecutor.execute(() -> futurePlantManager.deleteById(id));
    }
}
