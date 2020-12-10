package se.bth.homejungle.ui;

import com.google.firebase.firestore.PropertyName;
import com.google.j2objc.annotations.Property;

import java.io.Serializable;

public class MarketplacePlant implements Serializable {
    private String username;
    private String contact;
    private String speciesName;
    private String id;
    private int speciesid;
    private double latitude;
    private double longitude;

    public MarketplacePlant(){

    }

   public MarketplacePlant(String username, String contact, String speciesName, double latitude, double longitude){
        this.username = username;
        this.contact = contact;
        this.speciesName = speciesName;
        this.latitude = latitude;
        this.longitude = longitude;
   }

   public void setId(String id){
        this.id = id;
   }

    public String getUsername(){ return this.username; }

    public String getContact(){ return this.contact; }

    public String getSpeciesName(){ return this.speciesName; }

    public int getDistance() {
        int distance = 0;
        //TODO: calculation of distance to current GPS
        return distance;
    }

    public String getId(){ return this.id; }

}
