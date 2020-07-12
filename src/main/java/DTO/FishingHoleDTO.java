package DTO;

import java.sql.Date;

public class FishingHoleDTO {
    private int id;
    private int fish_id;
    private String name;
    private String category;
    private String address;
    private Float latitude;
    private Float longitude;
    private String fish_species;
    private Date date;

    public int getFish_id() {
        return fish_id;
    }

    public void setFish_id(int fish_id) {
        this.fish_id = fish_id;
    }

    public FishingHoleDTO(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getFish_species() {
        return fish_species;
    }

    public void setFish_species(String fish_species) {
        this.fish_species = fish_species;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
