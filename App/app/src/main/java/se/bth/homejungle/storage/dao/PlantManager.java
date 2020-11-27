package se.bth.homejungle.storage.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import se.bth.homejungle.storage.entity.Plant;

@Dao
public interface PlantManager {
    @Query("SELECT * FROM plant ORDER BY name")
    LiveData<List<Plant>> getAll();

    @Query("SELECT * FROM plant WHERE id = :id LIMIT 1")
    LiveData<Plant> findById(int id);

    @Insert
    void insert(Plant plant);

    @Delete
    void delete(Plant plant);
}
