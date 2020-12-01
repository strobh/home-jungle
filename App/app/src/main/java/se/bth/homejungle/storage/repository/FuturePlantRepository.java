package se.bth.homejungle.storage.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import se.bth.homejungle.storage.AppDatabase;
import se.bth.homejungle.storage.dao.FuturePlantManager;
import se.bth.homejungle.storage.entity.FuturePlant;
import se.bth.homejungle.storage.entity.FuturePlantWithSpecies;
import se.bth.homejungle.storage.entity.Plant;

public class FuturePlantRepository {

    private FuturePlantManager futurePlantManager;

    public FuturePlantRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.futurePlantManager = db.getFuturePlantManager();
    }

    public LiveData<List<FuturePlant>> getFuturePlants() {
        return futurePlantManager.getFuturePlants();
    }

    public LiveData<List<FuturePlantWithSpecies>> getFuturePlantsWithSpecies() {
        return futurePlantManager.getFuturePlantsWithSpecies();
    }

    public LiveData<FuturePlant> findById(long id) {
        return futurePlantManager.findById(id);
    }

    public void insert(FuturePlant plant) {
        AppDatabase.databaseWriteExecutor.execute(() -> futurePlantManager.insert(plant));
    }

    public void update(FuturePlant plant) {
        AppDatabase.databaseWriteExecutor.execute(() -> futurePlantManager.update(plant));
    }

    public void delete(FuturePlant plant) {
        AppDatabase.databaseWriteExecutor.execute(() -> futurePlantManager.delete(plant));
    }
}
