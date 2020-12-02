package se.bth.homejungle.ui.calendar;

import android.app.Application;
import android.database.sqlite.SQLiteConstraintException;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import se.bth.homejungle.storage.entity.PlantWithSpecies;
import se.bth.homejungle.storage.repository.PlantRepository;

public class CalendarViewModel extends AndroidViewModel {

    private final PlantRepository plantRepository;
    private final LiveData<List<PlantWithSpecies>> plantsWithSpecies;

    public CalendarViewModel(Application application) {
        super(application);
        plantRepository = new PlantRepository(application);
        plantsWithSpecies = plantRepository.getNextWateredPlants();
    }

    public LiveData<List<PlantWithSpecies>> getPlantsWithSpecies() {
        return plantsWithSpecies;
    }

    public void waterPlant(long plantId){
        //TODO: water plant in database and update list
    }
}