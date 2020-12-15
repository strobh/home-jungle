package se.bth.homejungle.ui.marketplace.marketplace;

import android.location.Location;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.storage.FirebaseStorage;

import java.util.List;

import se.bth.homejungle.firestore.MarketplacePlantLiveData;
import se.bth.homejungle.firestore.MarketplacePlantRepository;
import se.bth.homejungle.ui.MarketplacePlant;

public class MarketplaceViewModel extends ViewModel {
    MarketplacePlantRepository marketplacePlantRepository;
    MarketplacePlantLiveData liveData = new MarketplacePlantLiveData(null);
    MarketplacePlant currentPlant;
    Location location;
    FirebaseStorage storage = FirebaseStorage.getInstance();

    public MarketplaceViewModel(){
        marketplacePlantRepository = new MarketplacePlantRepository();
    }

    public LiveData<List<MarketplacePlant>> getMarketplacePlantsLiveData(){
        liveData = marketplacePlantRepository.getFirestoreLiveData();
        return liveData;
    }

    public LiveData<List<MarketplacePlant>> getPlantList() {
        return liveData.marketplacePlantsList;
    }

    public LiveData<List<MarketplacePlant>> getOtherGiveawaysLiveData(String userid){
        liveData = marketplacePlantRepository.getOtherGiveawaysLiveData(userid);
        return liveData;
    }

    public void setCurrentPlant(MarketplacePlant plant){
        this.currentPlant = plant;
    }

    public MarketplacePlant getCurrentPlant(){
        return this.currentPlant;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation(){
        return this.location;
    }
}