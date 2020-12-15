package se.bth.homejungle.ui;

import android.location.Location;

import com.google.firebase.firestore.PropertyName;
import com.google.j2objc.annotations.Property;

import java.io.Serializable;

import se.bth.homejungle.helper.DistanceCalculator;

public class MarketplacePlant implements Serializable {
    private String username;
    private String contact;
    private String speciesname;
    private String id;
    private String userid;
//    private int speciesid;
    private double latitude;
    private double longitude;

    public MarketplacePlant(){

    }

   public MarketplacePlant(String username, String contact, String speciesName, double latitude, double longitude){
        this.username = username;
        this.contact = contact;
        this.speciesname = speciesName;
        this.latitude = latitude;
        this.longitude = longitude;
   }

   public void setId(String id){
        this.id = id;
   }

    public String getUsername(){ return this.username; }

    public String getUserid(){ return this.userid; }

    public String getContact(){ return this.contact; }

    public String getSpeciesname(){ return this.speciesname; }

    public double getLongitude(){
        return this.longitude;
    }

    public double getLatitude(){
        return this.latitude;
    }

    public int getDistance(Location location) {
        System.out.println("Longitude: " + this.longitude + ", latitude: " + this.latitude);
        return (int) Math.round(DistanceCalculator.calculateDistance(location.getLatitude(), location.getLongitude(), latitude, longitude) / 1000);
    }

    public String getId(){ return this.id; }

}
