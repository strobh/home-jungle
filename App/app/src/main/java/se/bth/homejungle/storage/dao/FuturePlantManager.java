package se.bth.homejungle.storage.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import se.bth.homejungle.storage.entity.FuturePlant;
import se.bth.homejungle.storage.entity.FuturePlantWithSpecies;

@Dao
public interface FuturePlantManager {
    @Query("SELECT * FROM future_plant")
    LiveData<List<FuturePlant>> getFuturePlants();

    @Transaction
    @Query("SELECT * FROM future_plant")
    LiveData<List<FuturePlantWithSpecies>> getFuturePlantsWithSpecies();

    @Query("SELECT * FROM future_plant WHERE id = :id LIMIT 1")
    LiveData<FuturePlant> findById(long id);

    @Insert
    long insert(FuturePlant plant);

    @Delete
    void delete(FuturePlant plant);
}
