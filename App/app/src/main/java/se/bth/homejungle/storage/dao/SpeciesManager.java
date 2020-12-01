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

@Dao
public interface SpeciesManager {
    @Query("SELECT * FROM species ORDER BY name")
    LiveData<List<Species>> getSpecies();

    @Transaction
    @Query("SELECT * FROM species ORDER BY name")
    LiveData<List<SpeciesWithCategory>> getSpeciesWithCategory();

    @Query("SELECT * FROM species WHERE category_id = :categoryId ORDER BY name")
    LiveData<List<Species>> getSpeciesByCategory(long categoryId);

    @Query("SELECT * FROM species WHERE name LIKE :name ORDER BY name")
    LiveData<List<Species>> getSpeciesByName(String name);

    @Query("SELECT * FROM species WHERE id = :id LIMIT 1")
    LiveData<Species> findById(long id);

    @Query("SELECT * FROM species WHERE id = :id LIMIT 1")
    LiveData<SpeciesWithCategory> findSpeciesWithCategoryById(long id);

    @Insert
    long insert(Species species);

    @Delete
    void delete(Species species);
}
