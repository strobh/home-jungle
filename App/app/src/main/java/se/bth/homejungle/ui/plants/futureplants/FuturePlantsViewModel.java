package se.bth.homejungle.ui.plants.futureplants;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import se.bth.homejungle.R;

public class FuturePlantsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    String[] plantnames = {"Cactus", "Flower", "Plant"};
    String[] plantdate = {"May-April", "June", "September"};
    Integer[] imgid = {R.drawable.plant1, R.drawable.plant2, R.drawable.plant3};

    public FuturePlantsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public String[] getPlantnames(){
        return plantnames;
    }

    public String[] getPlantdate(){
        return plantdate;
    }

    public Integer[] getImgid(){
        return imgid;
    }
}