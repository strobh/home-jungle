package se.bth.homejungle.storage.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import se.bth.homejungle.storage.AppDatabase;
import se.bth.homejungle.storage.dao.PlantManager;
import se.bth.homejungle.storage.entity.Plant;

public class PlantRepository {

    private PlantManager plantManager;

    public PlantRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.plantManager = db.getPlantManager();
    }

    public LiveData<List<Plant>> getAll() {
        return plantManager.getAll();
    }

    public LiveData<Plant> findById(int id) {
        return plantManager.findById(id);
    }

    public void insert(Plant plant) {
        AppDatabase.databaseWriteExecutor.execute(() -> plantManager.insert(plant));
    }

    public void delete(Plant plant) {
        AppDatabase.databaseWriteExecutor.execute(() -> plantManager.delete(plant));
    }
}
