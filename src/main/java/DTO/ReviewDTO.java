package DTO;

import java.util.Date;

public class ReviewDTO {
    private int id;
    private int fish_id;
    private String name;
    private String title;
    private String content;
    private Date date;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ReviewDTO(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFish_id() {
        return fish_id;
    }

    public void setFish_id(int fish_id) {
        this.fish_id = fish_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
