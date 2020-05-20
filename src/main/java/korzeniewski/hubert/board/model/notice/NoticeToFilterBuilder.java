package korzeniewski.hubert.board.model.notice;

/**
 * Builder of model class NoticeToFilter.
 */
public class NoticeToFilterBuilder {

    private String id;
    private String author;
    private String date;
    private String title;

    public NoticeToFilterBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public NoticeToFilterBuilder withAuthor(String author) {
        this.author = author;
        return this;
    }

    public NoticeToFilterBuilder withDate(String date) {
        this.date = date;
        return this;
    }

    public NoticeToFilterBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public NoticeToFilter build() {
        NoticeToFilter noticeToFilter = new NoticeToFilter();
        noticeToFilter.setId(this.id);
        noticeToFilter.setAuthor(this.author);
        noticeToFilter.setDate(this.date);
        noticeToFilter.setTitle(this.title);
        return noticeToFilter;
    }

}

