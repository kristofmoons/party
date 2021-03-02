package model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Venue {
    @Id
    private int id;
    private String VENUE_NAME;
    private String LINK_MORE_INFO;
    private int CAPACITY;
    private boolean FOOD_PROVIDED;
    private boolean INDOOR;
    private boolean OUTDOOR;
    private boolean FREE_PARKING_AVAILABLE;
    private String CITY;
    private int DISTANCE_FROM_PUBLIC_TRANSPORT_IN_KM;


    public Venue(String VENUE_NAME, String linkMoreInfo, int capacity, boolean isFoodProvided, boolean isIndoor, boolean isOutdoor, boolean isFreeParkingAvailable, String city, int distanceFromPublicTransportInKm) {
        this.VENUE_NAME = VENUE_NAME;
        this.LINK_MORE_INFO = linkMoreInfo;
        this.CAPACITY = capacity;
        this.FOOD_PROVIDED = isFoodProvided;
        this.INDOOR = isIndoor;
        this.OUTDOOR = isOutdoor;
        this.FREE_PARKING_AVAILABLE = isFreeParkingAvailable;
        this.CITY = city;
        this.DISTANCE_FROM_PUBLIC_TRANSPORT_IN_KM = distanceFromPublicTransportInKm;
    }

    public Venue() {
    }

    public Venue(String VENUE_NAME) {
        this.VENUE_NAME = VENUE_NAME;
    }

    public String getVENUE_NAME() {
        return VENUE_NAME;
    }

    public void setVENUE_NAME(String venueName) {
        this.VENUE_NAME = venueName;
    }

    public String getLINK_MORE_INFO() {
        return LINK_MORE_INFO;
    }

    public void setLINK_MORE_INFO(String linkMoreInfo) {
        this.LINK_MORE_INFO = linkMoreInfo;
    }

    public int getCAPACITY() {
        return CAPACITY;
    }

    public void setCAPACITY(int capacity) {
        this.CAPACITY = capacity;
    }

    public boolean isFOOD_PROVIDED() {
        return FOOD_PROVIDED;
    }

    public void setFOOD_PROVIDED(boolean foodProvided) {
        foodProvided = foodProvided;
    }

    public boolean isINDOOR() {
        return INDOOR;
    }

    public void setINDOOR(boolean indoor) {
        this.INDOOR = indoor;
    }

    public boolean isOUTDOOR() {
        return OUTDOOR;
    }

    public void setOUTDOOR(boolean outdoor) {
        this.OUTDOOR = outdoor;
    }

    public boolean isFREE_PARKING_AVAILABLE() {
        return FREE_PARKING_AVAILABLE;
    }

    public void setFREE_PARKING_AVAILABLE(boolean freeParkingAvailable) {
        this.FREE_PARKING_AVAILABLE = freeParkingAvailable;
    }

    public String getCITY() {
        return CITY;
    }

    public void setCITY(String city) {
        this.CITY = city;
    }

    public int getDISTANCE_FROM_PUBLIC_TRANSPORT_IN_KM() {
        return DISTANCE_FROM_PUBLIC_TRANSPORT_IN_KM;
    }

    public void setDISTANCE_FROM_PUBLIC_TRANSPORT_IN_KM(int distanceFromPublicTransportInKm) {
        this.DISTANCE_FROM_PUBLIC_TRANSPORT_IN_KM = distanceFromPublicTransportInKm;
    }
}
