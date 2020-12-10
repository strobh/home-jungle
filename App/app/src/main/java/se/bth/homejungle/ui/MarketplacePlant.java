package se.bth.homejungle.ui;

import com.google.firebase.firestore.PropertyName;
import com.google.j2objc.annotations.Property;

import java.io.Serializable;

public class MarketplacePlant implements Serializable {
    private String username;
    private String contact;
    private String speciesName;
    private int id;
    private int speciesid;
    private double latitude;
    private double longitude;

    public MarketplacePlant(){

    }

  /*  public MarketplacePlant(String username, String contactInformation, String speciesName, double latitude, double longitude, int id){
        this.username = username;
        this.contactInformation = contactInformation;
        this.speciesName = speciesName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.id = id;
    }*/

    public MarketplacePlant(String contact, String username){
        this.username = username;
        this.contact = contact;
    }

    public String getUsername(){ return this.username; }

    public String getContact(){ return this.contact; }

    public String getSpeciesName(){ return this.speciesName; }

    public int getDistance(){
        int distance = 0;
        //TODO: calculation of distance to current GPS
        return distance;
    }

    public int getSpeciesid(){
        return this.speciesid;
    }

    public int getId(){ return this.id; }

}
