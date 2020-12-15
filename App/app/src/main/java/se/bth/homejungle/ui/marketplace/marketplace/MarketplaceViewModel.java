package se.bth.homejungle.ui.marketplace.marketplace;

import android.graphics.Bitmap;
import android.location.Location;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import se.bth.homejungle.firestore.MarketplacePlantLiveData;
import se.bth.homejungle.firestore.MarketplacePlantRepository;
import se.bth.homejungle.ui.MarketplacePlant;

import static android.content.ContentValues.TAG;

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