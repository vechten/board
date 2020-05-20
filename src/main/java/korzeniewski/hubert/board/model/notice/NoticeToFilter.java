package korzeniewski.hubert.board.model.notice;
/**
 * Model class of notices to filter repository.
 */
public class NoticeToFilter {

    private String id;
    private String author;
    private String date;
    private String title;

    public NoticeToFilter() {
    }

    public NoticeToFilter(String id, String author, String date, String title) {
        this.id = id;
        this.author = author;
        this.date = date;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
