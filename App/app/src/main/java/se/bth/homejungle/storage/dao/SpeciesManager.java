package se.bth.homejungle.storage.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import se.bth.homejungle.storage.entity.Species;

@Dao
public interface SpeciesManager {
    @Query("SELECT * FROM species")
    LiveData<List<Species>> getSpecies();

    @Query("SELECT * FROM species WHERE category LIKE :category")
    LiveData<List<Species>> getAllByCategory(String category);

    @Query("SELECT * FROM species WHERE name LIKE :name")
    LiveData<List<Species>> getAllByName(String name);

    @Query("SELECT * FROM species WHERE id = :id LIMIT 1")
    LiveData<Species> findById(long id);

    @Insert
    long insert(Species species);

    @Delete
    void delete(Species species);
}
