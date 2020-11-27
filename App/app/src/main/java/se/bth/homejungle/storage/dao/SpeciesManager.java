package se.bth.homejungle.storage.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import se.bth.homejungle.storage.entity.Species;

@Dao
public interface SpeciesManager {
    @Query("SELECT * FROM species")
    List<Species> getAll();

    @Query("SELECT * FROM species WHERE category LIKE :category")
    List<Species> getAllByCategory(int category);

    @Query("SELECT * FROM species WHERE name LIKE :name")
    List<Species> getAllByName(int name);

    @Query("SELECT * FROM species WHERE id = :id LIMIT 1")
    Species findById(int id);

    @Insert
    void insert(Species species);

    @Delete
    void delete(Species species);
}
