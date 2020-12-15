package se.bth.homejungle.ui.plants.futureplants;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import se.bth.homejungle.storage.entity.FuturePlant;
import se.bth.homejungle.storage.entity.view.FuturePlantWithSpecies;
import se.bth.homejungle.storage.repository.FuturePlantRepository;

public class FuturePlantsViewModel extends AndroidViewModel {

    private final FuturePlantRepository futurePlantRepository;
    private final LiveData<List<FuturePlantWithSpecies>> futurePlantsWithSpecies;

    public FuturePlantsViewModel(Application application) {
        super(application);
        futurePlantRepository = new FuturePlantRepository(application);
        futurePlantsWithSpecies = futurePlantRepository.getFuturePlantsWithSpecies();
    }

    public LiveData<List<FuturePlantWithSpecies>> getFuturePlantsWithSpecies() {
        return futurePlantsWithSpecies;
    }

    public void insert(FuturePlant plant) {
        futurePlantRepository.insert(plant);
    }

    public void delete(FuturePlant plant) {
        futurePlantRepository.delete(plant);
    }
}