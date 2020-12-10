package se.bth.homejungle.firestore;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MarketplacePlantRepository {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public MarketplacePlantLiveData getFirestoreLiveData() {
        CollectionReference collectionReference = db.collection("user");

        return new MarketplacePlantLiveData(collectionReference);
    }
}
