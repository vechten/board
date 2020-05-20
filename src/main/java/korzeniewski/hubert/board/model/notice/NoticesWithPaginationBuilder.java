package korzeniewski.hubert.board.model.notice;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder of model class NoticesWithPagination.
 */

/**
 * Builder of model class NoticesWithPagination.
 */
public class NoticesWithPaginationBuilder {

    private long totalNumberOfAllNotices;
    private List<Notice> noticesFromPage = new ArrayList<>();
    private String sortingScheme;

    public NoticesWithPaginationBuilder withTotalNumberOfAllNotices(long totalNumberOfAllNotices) {
        this.totalNumberOfAllNotices = totalNumberOfAllNotices;
        return this;
    }

    public NoticesWithPaginationBuilder withNoticesFromPage(List<Notice> noticesFromPage) {
        this.noticesFromPage = noticesFromPage;
        return this;
    }

    public NoticesWithPaginationBuilder withSortingScheme(String sortingScheme) {
        this.sortingScheme = sortingScheme;
        return this;
    }

    public NoticesWithPagination buildNoticesWithPagination() {
        NoticesWithPagination noticesWithPagination = new NoticesWithPagination();
        noticesWithPagination.setTotalNumberOfAllNotices(this.totalNumberOfAllNotices);
        noticesWithPagination.setNoticesFromPage(this.noticesFromPage);
        noticesWithPagination.setSortingScheme(this.sortingScheme);
        return noticesWithPagination;
    }

}
