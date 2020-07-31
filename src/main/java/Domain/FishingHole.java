package Domain;

import java.sql.Date;

public class FishingHole {
    private Long id; // 낚시터 id
    private String name; // 낚시터 이름
    private String category; // 낚시터 유형
    private String address; // 낚시터 주소
    private Float latitude; // 낚시터 위도
    private Float longitude; // 낚시터 경도
    private String fish_species; // 주요 어종
    private Date date; // 데이터 입력 일자 ( 공공데이터 기준 )

    public FishingHole(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
