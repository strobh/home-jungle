package se.bth.homejungle.storage.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import se.bth.homejungle.storage.entity.SpeciesCategory;

/**
 * CategoryManager is the Data Access Object (DAO) used to manage plant species in the local
 * database.
 *
 * The Android Room Persistence Library automatically creates the implementation for this interface.
 */
@Dao
public interface CategoryManager {

    /**
     * Returns a list of all species categories.
     *
     * @return List of all species categories.
     */
    @Query("SELECT * FROM species_category ORDER BY name")
    LiveData<List<SpeciesCategory>> getCategories();

    /**
     * Searches and returns the species category with the given id.
     *
     * @param id The id of the species category to look up.
     * @return The species category with the given id.
     */
    @Query("SELECT * FROM species_category WHERE category_id = :id LIMIT 1")
    LiveData<SpeciesCategory> findById(long id);

    @Query("DELETE FROM species_category")
    void deleteCategories();

    /**
     * Creates a new species category in the database.
     *
     * @param speciesCategory The species category entity object to store in the database.
     * @return The id of the created entity.
     */
    @Insert
    long insert(SpeciesCategory speciesCategory);

    /**
     * Deletes a species category from the database.
     *
     * @param speciesCategory The species category entity object to delete from the database.
     */
    @Delete
    void delete(SpeciesCategory speciesCategory);
}
