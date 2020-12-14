package se.bth.homejungle.ui.marketplace.marketplace;

import android.graphics.Bitmap;
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

    public void getImages(){
        StorageReference storageReference = storage.getReference();
        StorageReference pathReference = storageReference.child("ficus.jpg");
        Bitmap image;
    }

    public void setCurrentPlant(MarketplacePlant plant){
        this.currentPlant = plant;
    }

    public MarketplacePlant getCurrentPlant(){
        return this.currentPlant;
    }
/*
    public LiveData<MarketplacePlant> getSinglePlant(String docName){
        return marketplacePlantRepository.getSinglePlant(docname);
    }

    public MarketplacePlant getPlantByDocName(String docName){
        marketplacePlantRepository.setPlantByDocName(docName);
        return marketplacePlantRepository.getPlantByDocName();
    }*/
}