package se.bth.homejungle.firestore;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.HashMap;
import java.util.Map;

import se.bth.homejungle.ui.MarketplacePlant;

import static android.content.ContentValues.TAG;

public class MarketplacePlantRepository {
    private static final String USERNAME_KEY = "username";
    private static final String CONTACT_KEY = "contact";
    private static final String SPECIESNAME_KEY = "speciesname";
    private static final String USERID = "userid";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private boolean deleted;

    public MarketplacePlantLiveData getFirestoreLiveData() {
        CollectionReference collectionReference = db.collection("giveaway");
        return new MarketplacePlantLiveData(collectionReference, " ");
    }

    public MarketplacePlantLiveData getOwnGiveawaysLiveData(String userid){
        Query query = db.collection("giveaway").whereEqualTo("userid", userid);
        return new MarketplacePlantLiveData(query);
    }

    public MarketplacePlantLiveData getOtherGiveawaysLiveData(String userid){
        CollectionReference collectionReference = db.collection("giveaway");
        return new MarketplacePlantLiveData(collectionReference, userid);
    }

    public boolean deleteGiveaway(String docid) {
        db.collection("giveaway").document(docid)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        deleted = true;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        deleted = false;
                    }
                });
        return deleted;
    }

    public void insert(MarketplacePlant newItem) {
        Map<String, Object> giveawayData = new HashMap<String, Object>();
        giveawayData.put(USERNAME_KEY, newItem.getUsername());
        giveawayData.put(CONTACT_KEY, newItem.getContact());
        giveawayData.put(SPECIESNAME_KEY, newItem.getSpeciesname());
        giveawayData.put(USERID, newItem.getUserid());

        db.collection("giveaway")
                .add(giveawayData)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                });
    }
}
