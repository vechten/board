package korzeniewski.hubert.board.model.notice;

import org.springframework.stereotype.Service;

@Service
public class NoticeBuilder {

    private String author;
    private String date;
    private String title;
    private String content;

    public NoticeBuilder withAuthor(String author) {
        this.author = author;
        return this;
    }

    public NoticeBuilder withDate(String date) {
        this.date = date;
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

    public Notice buildMessage() {
        Notice notice = new Notice();
        notice.setAuthor(this.author);
        notice.setDate(this.date);
        notice.setTitle(this.title);
        notice.setContent(this.content);
        return notice;
    }

}
