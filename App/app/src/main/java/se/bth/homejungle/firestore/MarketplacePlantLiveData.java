package se.bth.homejungle.firestore;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import se.bth.homejungle.ui.MarketplacePlant;


public class MarketplacePlantLiveData extends LiveData<List<MarketplacePlant>> implements EventListener<QuerySnapshot>{
    private static final String TAG = "MarketplacePlantLiveData";
    private CollectionReference collectionReference;
    private List<MarketplacePlant> plantsListTemp = new ArrayList<>();
    public MutableLiveData<List<MarketplacePlant>> marketplacePlantsList = new MutableLiveData<>();
    private ListenerRegistration listenerRegistration = () -> {};
    
    public MarketplacePlantLiveData(CollectionReference collectionReference){
        this.collectionReference = collectionReference;
    }

    @Override
    protected void onActive() {
        Log.w(TAG, "in listener registration");
        listenerRegistration = collectionReference.addSnapshotListener(this);
        super.onActive();
    }

    @Override
    protected void onInactive() {
        listenerRegistration.remove();
        super.onInactive();
    }

    @Override
    public void onEvent(@Nullable QuerySnapshot querySnapshot, @Nullable FirebaseFirestoreException error) {
        if(error != null){
            Log.w(TAG, "Listen to firestore failed", error);
            return;
        }
        List<DocumentSnapshot> list = querySnapshot.getDocuments();
        for(DocumentSnapshot doc : list){
            MarketplacePlant newPlant = doc.toObject(MarketplacePlant.class);
            newPlant.setId(doc.getId());
            plantsListTemp.add(newPlant);
        }
        marketplacePlantsList.setValue(plantsListTemp);
    }
}

/*
        db.collection("user")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
@Override
public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
        if(!queryDocumentSnapshots.isEmpty()){
        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
        for(DocumentSnapshot doc : list){
        MarketplacePlant newPlant = doc.toObject(MarketplacePlant.class);
        plantList.add(newPlant);
        }
        System.out.println("Size plantslist inside viewmodel: " + plantList.size());
        }
        }
        });*/