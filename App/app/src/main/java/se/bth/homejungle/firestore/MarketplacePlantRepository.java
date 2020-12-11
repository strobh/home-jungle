package se.bth.homejungle.firestore;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import se.bth.homejungle.ui.MarketplacePlant;

public class MarketplacePlantRepository {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private MarketplacePlant currentPlant;
    private boolean deleted;

    public MarketplacePlantLiveData getFirestoreLiveData() {
        CollectionReference collectionReference = db.collection("giveaway");
        return new MarketplacePlantLiveData(collectionReference, "");
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


/*public LiveData<MarketplacePlant> getSinglePlant(String docName){
        DocumentReference documentReference = db.collection("user").document(docName);


        db.collection("user").document(docName)
    }

    public void setPlantByDocName(String docName) {
        DocumentReference documentReference = db.collection("user").document(docName);

        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                        currentPlant = document.toObject(MarketplacePlant.class);
                    }
                }
            }
        });
    }

    public MarketplacePlant getPlantByDocName(){
        return currentPlant;
    }*/
}
