package se.bth.homejungle.storage.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import se.bth.homejungle.storage.entity.FuturePlant;
import se.bth.homejungle.storage.entity.FuturePlantWithSpecies;

/**
 * FuturePlantManager is the Data Access Object (DAO) used to manage future plants in the local
 * database.
 *
 * The Android Room Persistence Library automatically creates the implementation for this interface.
 */
@Dao
public interface FuturePlantManager {

    /**
     * Returns a list of all future plants.
     *
     * @return List of all future plants.
     */
    @Query("SELECT * FROM future_plant ORDER BY description")
    LiveData<List<FuturePlant>> getFuturePlants();

    /**
     * Returns a list of all future plants with their corresponding species.
     *
     * @return List of all future plants with their corresponding species.
     */
    @Transaction
    @Query("SELECT * FROM future_plant ORDER BY description")
    LiveData<List<FuturePlantWithSpecies>> getFuturePlantsWithSpecies();

    /**
     * Searches and returns the future plant with the given id.
     *
     * @param id The id of the future plant to look up.
     * @return The future plant with the given id.
     */
    @Query("SELECT * FROM future_plant WHERE future_plant_id = :id LIMIT 1")
    LiveData<FuturePlant> findById(long id);

    /**
     * Creates a new future plant in the database.
     *
     * @param futurePlant The future plant entity object to store in the database.
     * @return The id of the created entity.
     */
    @Insert
    long insert(FuturePlant futurePlant);

    /**
     * Updates an existing future plant object in the database with new values.
     *
     * @param futurePlant The updated future plant entity object with the new values.
     */
    @Update
    void update(FuturePlant futurePlant);

    /**
     * Deletes a future plant from the database.
     *
     * @param futurePlant The future plant entity object to delete from the database.
     */
    @Delete
    void delete(FuturePlant futurePlant);
}
