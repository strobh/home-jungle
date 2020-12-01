package se.bth.homejungle.storage.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import se.bth.homejungle.storage.entity.SpeciesCategory;

@Dao
public interface CategoryManager {
    @Query("SELECT * FROM species_category ORDER BY name")
    LiveData<List<SpeciesCategory>> getCategories();

    @Query("SELECT * FROM species_category WHERE id = :id LIMIT 1")
    LiveData<SpeciesCategory> findById(long id);

    @Insert
    long insert(SpeciesCategory speciesCategory);

    @Delete
    void delete(SpeciesCategory speciesCategory);
}
