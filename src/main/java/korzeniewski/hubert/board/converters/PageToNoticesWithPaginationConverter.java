package korzeniewski.hubert.board.converters;

import korzeniewski.hubert.board.model.notice.Notice;
import korzeniewski.hubert.board.model.notice.NoticesWithPagination;
import korzeniewski.hubert.board.model.notice.NoticesWithPaginationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Convert page with notices from repository to notices from page with information about amount of other pages with notices.
 */
@Service
public class PageToNoticesWithPaginationConverter {

    private NoticesWithPaginationBuilder noticesWithPaginationBuilder;

    @Autowired
    public PageToNoticesWithPaginationConverter(NoticesWithPaginationBuilder noticesWithPaginationBuilder) {
        this.noticesWithPaginationBuilder = noticesWithPaginationBuilder;
    }

    /**
     * Convert page with notices from repository to notices from page with information about amount of other pages with notices.
     *
     * @param page page with notices to be converted
     * @return notices and information amount of pages with notices
     */
    public NoticesWithPagination convertPageToNoticesWithPagination(Page<Notice> page) {
        int totalNumberOfPages = page.getTotalPages();
        List<Notice> noticesFromPage = page.getContent();
        return noticesWithPaginationBuilder.withTotalNumberOfPages(totalNumberOfPages).withNoticesFromPage(noticesFromPage).buildNoticesWithPagination();
    }

}
