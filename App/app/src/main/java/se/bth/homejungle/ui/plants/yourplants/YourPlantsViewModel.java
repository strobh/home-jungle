package se.bth.homejungle.ui.plants.yourplants;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import se.bth.homejungle.R;
import se.bth.homejungle.storage.entity.Plant;
import se.bth.homejungle.storage.repository.PlantRepository;

public class YourPlantsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    private final PlantRepository plantRepository;
    private final LiveData<List<Plant>> plants;

    String[] plantnames = {"Cactus", "Flower", "Plant"};
    String[] placedesc = {"kitchen", "kitchen", "livingroom"};
    String[] water_amount = {"1/4l", "1/2l", "1l"};
    String[] water_time = {"4 days left", "tomorrow", "2 days left"};
    Integer[] imgid = {R.drawable.plant1, R.drawable.plant2, R.drawable.plant3};

    public YourPlantsViewModel(Application application) {
        plantRepository = new PlantRepository(application);
        plants = plantRepository.getAll();

        // TODO: remove
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    LiveData<List<Plant>> getPlants() {
        return plants;
    }

    public void insert(Plant plant) {
        plantRepository.insert(plant);
    }

    public String[] getPlantnames(){
        return plantnames;
    }

    public String[] getPlacedesc(){
        return placedesc;
    }

    public String[] getWater_amount(){ return water_amount; }

    public String[] getWater_time(){ return water_time; }

    public Integer[] getImgid(){
        return imgid;
    }

}