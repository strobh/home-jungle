package se.bth.homejungle.storage.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import se.bth.homejungle.storage.entity.Plant;
import se.bth.homejungle.storage.entity.PlantWithSpecies;

/**
 * PlantManager is the Data Access Object (DAO) used to manage plants in the local database.
 *
 * The Android Room Persistence Library automatically creates the implementation for this interface.
 */
@Dao
public interface PlantManager {

    /**
     * Returns a list of all plants.
     *
     * @return List of all plants.
     */
    @Query("SELECT * FROM plant ORDER BY description")
    LiveData<List<Plant>> getPlants();

    /**
     * Returns a list of all plants with their corresponding species.
     *
     * @return List of all plants with their corresponding species.
     */
    @Transaction
    @Query("SELECT * FROM plant ORDER BY description")
    LiveData<List<PlantWithSpecies>> getPlantsWithSpecies();

    /**
     * Searches and returns the plant with the given id.
     *
     * @param id The id of the plant to look up.
     * @return The plant with the given id.
     */
    @Query("SELECT * FROM plant WHERE plant_id = :id LIMIT 1")
    LiveData<Plant> findById(long id);

    /**
     * Creates a new plant in the database.
     *
     * @param plant The plant entity object to store in the database.
     * @return The id of the created entity.
     */
    @Insert
    long insert(Plant plant);

    /**
     * Updates an existing plant object in the database with new values.
     *
     * @param plant The updated plant entity object with the new values.
     */
    @Update
    void update(Plant plant);

    /**
     * Deletes a plant from the database.
     *
     * @param plant The plant entity object to delete from the database.
     */
    @Delete
    void delete(Plant plant);
}
