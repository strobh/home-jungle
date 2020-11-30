package se.bth.homejungle.storage.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import se.bth.homejungle.storage.AppDatabase;
import se.bth.homejungle.storage.dao.PlantManager;
import se.bth.homejungle.storage.entity.Plant;
import se.bth.homejungle.storage.entity.PlantWithSpecies;

public class PlantRepository {

    private PlantManager plantManager;

    public PlantRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        this.plantManager = db.getPlantManager();
    }

    public LiveData<List<Plant>> getPlants() {
        return plantManager.getPlants();
    }

    public LiveData<List<PlantWithSpecies>> getPlantsWithSpecies() {
        return plantManager.getPlantsWithSpecies();
    }

    public LiveData<Plant> findById(long id) {
        return plantManager.findById(id);
    }

    public void insert(Plant plant) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            long id = plantManager.insert(plant);
            System.out.println("Plant was added: " + id);
            Log.v("PlantDatabase", "Plant was added: " + id);
        });
    }

    public void delete(Plant plant) {
        AppDatabase.databaseWriteExecutor.execute(() -> plantManager.delete(plant));
    }
}