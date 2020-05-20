package korzeniewski.hubert.board.model.notice;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import korzeniewski.hubert.board.model.images.ImagesOfNotice;
import korzeniewski.hubert.board.security.model.User;


import javax.persistence.*;
import java.util.Date;

/**
 * Model class for notices.
 */
@Entity
public class Notice {

    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private User author;
    @JsonIgnore
    private Date creationDate;
    @JsonIgnore
    private Date creationTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = JsonFormat.DEFAULT_LOCALE)
    private Date creationDateAndTime;
    private String title;
    @Lob
    @JsonIgnore
    private String content;
    @JsonIgnore
    @OneToOne(mappedBy = "notices", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private ImagesOfNotice images;

    public Notice() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getCreationDateAndTime() {
        return creationDateAndTime;
    }

    public void setCreationDateAndTime(Date creationDateAndTime) {
        this.creationDateAndTime = creationDateAndTime;
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

    public ImagesOfNotice getImages() {
        return images;
    }

    public void setImages(ImagesOfNotice images) {
        this.images = images;
    }

}
