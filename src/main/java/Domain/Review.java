package Domain;

import java.util.Date;

public class Review {
    private Long id;
    private Long fish_id;
    private Long user_id;
    private String writer;
    private String title;
    private String content;
    private Date created_at;
    private Date update_at;

    public String getWriter() {
        return writer;
    }

    public void setWritter(String writter) {
        this.writer = writter;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }

    public Review(){};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFish_id() {
        return fish_id;
    }

    public void setFish_id(Long fish_id) {
        this.fish_id = fish_id;
    }


    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
