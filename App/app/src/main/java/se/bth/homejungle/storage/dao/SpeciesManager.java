package se.bth.homejungle.storage.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import se.bth.homejungle.storage.entity.Species;
import se.bth.homejungle.storage.entity.SpeciesWithCategory;

/**
 * SpeciesManager is the Data Access Object (DAO) used to manage plant species in the local database.
 *
 * The Android Room Persistence Library automatically creates the implementation for this interface.
 */
@Dao
public interface SpeciesManager {

    /**
     * Returns a list of all plant species.
     *
     * @return List of all plant species.
     */
    @Query("SELECT * FROM species ORDER BY name")
    LiveData<List<Species>> getSpecies();

    /**
     * Returns a list of all plant species with their corresponding categories.
     *
     * @return List of all plant species with their corresponding categories.
     */
    @Transaction
    @Query("SELECT * FROM species ORDER BY name")
    LiveData<List<SpeciesWithCategory>> getSpeciesWithCategory();

    /**
     * Returns a list of all plant species with a given category.
     *
     * @param categoryId The id of the category to filter for.
     * @return List of all plant species with a given category.
     */
    @Query("SELECT * FROM species WHERE category_id = :categoryId ORDER BY name")
    LiveData<List<Species>> getSpeciesByCategory(long categoryId);

    /**
     * Returns a list of all plant species with a given name.
     *
     * @param name The name (or part of it) to search for.
     * @return List of all plant species with a given name.
     */
    @Query("SELECT * FROM species WHERE name LIKE '%'||:name||'%' ORDER BY name")
    LiveData<List<Species>> getSpeciesByName(String name);

    /**
     * Returns a list of all plant species with a given name in a given category.
     *
     * @param name The name (or part of it) to search for.
     * @param categoryId The id of the category to filter for.
     * @return List of all plant species with a given name in a given category.
     */
    @Query("SELECT * FROM species WHERE name LIKE '%'||:name||'%' and category_id = :categoryId ORDER BY name")
    LiveData<List<Species>> getSpeciesByNameAndCategory(String name, long categoryId);

    /**
     * Searches and returns the plant species with the given id.
     *
     * @param id The id of the plant species to look up.
     * @return The plant species with the given id.
     */
    @Query("SELECT * FROM species WHERE species_id = :id LIMIT 1")
    LiveData<Species> findById(long id);

    /**
     * Searches and returns the plant species with the given id and its corresponding category.
     *
     * @param id The id of the plant species to look up.
     * @return The plant species with the given id and its corresponding category.
     */
    @Transaction
    @Query("SELECT * FROM species WHERE species_id = :id LIMIT 1")
    LiveData<SpeciesWithCategory> findSpeciesWithCategoryById(long id);

    @Query("DELETE FROM species")
    void deleteSpecies();
    /**
     * Creates a new plant species in the database.
     *
     * @param species The plant species entity object to store in the database.
     * @return The id of the created entity.
     */
    @Insert
    long insert(Species species);

    /**
     * Deletes a plant species from the database.
     *
     * @param species The plant species entity object to delete from the database.
     */
    @Delete
    void delete(Species species);

}
