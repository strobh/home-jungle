package se.bth.homejungle.storage.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import se.bth.homejungle.storage.entity.FuturePlant;

@Dao
public interface FuturePlantManager {
    @Query("SELECT * FROM future_plant ORDER BY name")
    List<FuturePlant> getAll();

    @Query("SELECT * FROM future_plant WHERE id = :id LIMIT 1")
    FuturePlant findById(int id);

    @Insert
    void insert(FuturePlant plant);

    @Delete
    void delete(FuturePlant plant);
}
