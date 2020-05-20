package korzeniewski.hubert.board.converters.page;

import korzeniewski.hubert.board.model.notice.Notice;
import korzeniewski.hubert.board.model.notice.NoticesWithPagination;
import korzeniewski.hubert.board.model.notice.NoticesWithPaginationBuilder;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Convert page with notices from repository to notices from page with information about amount of other pages with notices.
 */
@Service
public class PageToNoticesWithPaginationConverter {

    /**
     * Convert page with notices from repository to notices from page with information about amount of other pages with notices.
     *
     * @param page page with notices to be converted
     * @return notices and information amount of pages with notices
     */
    public NoticesWithPagination convertPageToNoticesWithPagination(Page<Notice> page) {
        long totalNumberOfAllNotices = page.getTotalElements();
        String sortingScheme = convertSortingSchemeToAcceptableModel(page);
        List<Notice> noticesFromPage = page.getContent();
        return new NoticesWithPaginationBuilder()
                .withTotalNumberOfAllNotices(totalNumberOfAllNotices)
                .withNoticesFromPage(noticesFromPage)
                .withSortingScheme(sortingScheme)
                .buildNoticesWithPagination();
    }

    /**
     * Converts sorting scheme to model which accepts front-end application.
     * @param page with sorting scheme
     * @return converted sorting scheme
     */
    public String convertSortingSchemeToAcceptableModel(Page<Notice> page){
        return page.getSort().toString().replace(" ", "");
    }

}

