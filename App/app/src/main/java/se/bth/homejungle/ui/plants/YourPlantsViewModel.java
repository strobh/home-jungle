package se.bth.homejungle.ui.plants;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import se.bth.homejungle.R;

public class YourPlantsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    String[] plantnames = {"Cactus", "Flower", "Plant"};
    String[] placedesc = {"kitchen", "kitchen", "livingroom"};
    String[] water_amount = {"1/4l", "1/2l", "1l"};
    String[] water_time = {"4 days left", "tomorrow", "2 days left"};
    Integer[] imgid = {R.drawable.plant1, R.drawable.plant2, R.drawable.plant3};

    public YourPlantsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
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