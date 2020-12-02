package se.bth.homejungle.ui.database.databaselist;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.time.LocalDate;
import java.util.List;

import se.bth.homejungle.storage.entity.FuturePlant;
import se.bth.homejungle.storage.entity.Plant;
import se.bth.homejungle.storage.entity.Species;
import se.bth.homejungle.storage.repository.FuturePlantRepository;
import se.bth.homejungle.storage.repository.PlantRepository;
import se.bth.homejungle.storage.repository.SpeciesRepository;

public class DatabaseListViewModel extends AndroidViewModel {

    private final SpeciesRepository speciesRepository;
    private final PlantRepository plantRepository;
    private final FuturePlantRepository futurePlantRepository;
    private final LiveData<List<Species>> species;

    public DatabaseListViewModel(Application application) {
        super(application);
        speciesRepository = new SpeciesRepository(application);
        plantRepository = new PlantRepository(application);
        futurePlantRepository = new FuturePlantRepository(application);
        species = speciesRepository.getSpecies();
    }

    public LiveData<List<Species>> getSpecies() {
        return species;
    }

    public LiveData<List<Species>> getSpeciesByCategory(long categoryId){
        return speciesRepository.getSpeciesByCategory(categoryId);
    }

    public LiveData<List<Species>> getSpeciesByName(String searchTerm){
        return speciesRepository.getSpeciesByName(searchTerm);
    }

    public LiveData<List<Species>> getSpeciesByNameAndCategory(String name, long categoryId){
        return speciesRepository.getSpeciesByNameAndCategory(name, categoryId);
    }

    public void insert(Species species){
        speciesRepository.insert(species);
    }

    public void insertToOwnPlants(long speciesId, String description){
        Plant newPlant = new Plant(speciesId,  description);
        plantRepository.insert(newPlant);
    }

    public void insertToFuturePlants(long speciesId, String description, LocalDate plantDay){
        FuturePlant newPlant = new FuturePlant(speciesId, description, plantDay);
        futurePlantRepository.insert(newPlant);
    }

    public void insertToGiveaways(long speciesId, String description){
        //TODO: insert to database + image
    }

}
