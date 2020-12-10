package se.bth.homejungle.ui.marketplace;

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

import java.util.ArrayList;
import java.util.List;

import se.bth.homejungle.firestore.MarketplacePlantLiveData;
import se.bth.homejungle.firestore.MarketplacePlantRepository;
import se.bth.homejungle.ui.MarketplacePlant;

import static android.content.ContentValues.TAG;

public class MarketplaceViewModel extends ViewModel {
    MarketplacePlantRepository marketplacePlantRepository;
    MarketplacePlantLiveData liveData = new MarketplacePlantLiveData(null);

    public MarketplaceViewModel(){
        marketplacePlantRepository = new MarketplacePlantRepository();
    }

    public LiveData<List<MarketplacePlant>> getMarketplacePlantsLiveData(){
        liveData = marketplacePlantRepository.getFirestoreLiveData();
        return liveData;
    }

    public LiveData<List<MarketplacePlant>> getPlantList(){
        return liveData.marketplacePlantsList;
    }
}