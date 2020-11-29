package se.bth.homejungle.storage.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import se.bth.homejungle.storage.entity.Plant;
import se.bth.homejungle.storage.entity.PlantWithSpecies;

@Dao
public interface PlantManager {
    @Query("SELECT * FROM plant ORDER BY description")
    LiveData<List<Plant>> getPlants();

    @Transaction
    @Query("SELECT * FROM plant ORDER BY description")
    LiveData<List<PlantWithSpecies>> getPlantsWithSpecies();

    @Query("SELECT * FROM plant WHERE id = :id LIMIT 1")
    LiveData<Plant> findById(long id);

    @Insert
    long insert(Plant plant);

    @Delete
    void delete(Plant plant);
}
