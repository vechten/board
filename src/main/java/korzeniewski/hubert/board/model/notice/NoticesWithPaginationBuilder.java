package korzeniewski.hubert.board.model.notice;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Builder of model class NoticesWithPagination.
 */
@Service
public class NoticesWithPaginationBuilder {

    private int totalNumberOfPages;
    private List<Notice> noticesFromPage;

    public NoticesWithPaginationBuilder withTotalNumberOfPages(int totalNumberOfPages) {
        this.totalNumberOfPages = totalNumberOfPages;
        return this;
    }

    public NoticesWithPaginationBuilder withNoticesFromPage(List<Notice> noticesFromPage) {
        this.noticesFromPage = noticesFromPage;
        return this;
    }

    public NoticesWithPagination buildNoticesWithPagination() {
        NoticesWithPagination noticesWithPagination = new NoticesWithPagination();
        noticesWithPagination.setTotalNumberOfPages(this.totalNumberOfPages);
        noticesWithPagination.setNoticesFromPage(this.noticesFromPage);
        return noticesWithPagination;
    }

}
