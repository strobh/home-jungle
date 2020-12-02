package se.bth.homejungle.storage.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Query;

import java.util.List;

import se.bth.homejungle.storage.AppDatabase;
import se.bth.homejungle.storage.dao.PlantManager;
import se.bth.homejungle.storage.entity.Plant;
import se.bth.homejungle.storage.entity.PlantWithSpecies;

/**
 * PlantRepository is an intermediate helper in between the Data Access Object (DAO) and the view
 * model that is used to manage plant species in the local database.
 *
 * The repository is used to decouple the view model from the real source of the data (database)
 * and to start write tasks in another thread than the UI thread.
 */
public class PlantRepository {

    /**
     * The plant manager that is used to access the database.
     */
    private final PlantManager plantManager;

    /**
     * Creates a new plant repository.
     *
     * @param application Reference to the application which is needed to create the database.
     */
    public PlantRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.plantManager = db.getPlantManager();
    }

    /**
     * Returns a list of all plants.
     *
     * @return List of all plants.
     */
    public LiveData<List<Plant>> getPlants() {
        return plantManager.getPlants();
    }

    /**
     * Returns a list of all plants with their corresponding species.
     *
     * @return List of all plants with their corresponding species.
     */
    public LiveData<List<PlantWithSpecies>> getPlantsWithSpecies() {
        return plantManager.getPlantsWithSpecies();
    }

    /**
     * Searches and returns the plant with the given id.
     *
     * @param id The id of the plant to look up.
     * @return The plant with the given id.
     */
    public LiveData<Plant> findById(long id) {
        return plantManager.findById(id);
    }

    /**
     * Sets the date when the plant was last watered to today.
     *
     * This task is executed in another thread in order to be able to call this inside of the UI.
     *
     * @param id The id of the plant.
     */
    public void setLastWateredOfPlantToToday(long id) {
        AppDatabase.databaseWriteExecutor.execute(() -> plantManager.setLastWateredOfPlantToToday(id));
    }

    /**
     * Creates a new plant in the database.
     *
     * This task is executed in another thread in order to be able to call this inside of the UI.
     *
     * @param plant The plant entity object to store in the database.
     */
    public void insert(Plant plant) {
        AppDatabase.databaseWriteExecutor.execute(() -> plantManager.insert(plant));
    }

    /**
     * Updates an existing plant object in the database with new values.
     *
     * This task is executed in another thread in order to be able to call this inside of the UI.
     *
     * @param plant The updated plant entity object with the new values.
     */
    public void update(Plant plant) {
        AppDatabase.databaseWriteExecutor.execute(() -> plantManager.update(plant));
    }

    /**
     * Deletes a plant from the database.
     *
     * This task is executed in another thread in order to be able to call this inside of the UI.
     *
     * @param plant The plant entity object to delete from the database.
     */
    public void delete(Plant plant) {
        AppDatabase.databaseWriteExecutor.execute(() -> plantManager.delete(plant));
    }
}
