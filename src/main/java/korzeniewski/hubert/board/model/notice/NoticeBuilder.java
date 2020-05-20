package korzeniewski.hubert.board.model.notice;

import korzeniewski.hubert.board.security.model.User;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Builder of model class notices.
 */
public class NoticeBuilder {

    private User author;
    private Date creationDate;
    private Date creationTime;
    private Date creationDateAndTime;
    private String title;
    private String content;

    public NoticeBuilder withAuthor(User author) {
        this.author = author;
        return this;
    }

    public NoticeBuilder withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public NoticeBuilder withCreationTime(Date time) {
        this.creationTime = time;
        return this;
    }

    public NoticeBuilder withCreationDateAndTime(Date time) {
        this.creationDateAndTime = time;
        return this;
    }

    public NoticeBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public NoticeBuilder withContent(String content) {
        this.content = content;
        return this;
    }

    public Notice buildNotice() {
        Notice notice = new Notice();
        notice.setAuthor(this.author);
        notice.setCreationDate(this.creationDate);
        notice.setCreationTime(this.creationTime);
        notice.setCreationDateAndTime(this.creationDateAndTime);
        notice.setTitle(this.title);
        notice.setContent(this.content);
        return notice;
    }

}
