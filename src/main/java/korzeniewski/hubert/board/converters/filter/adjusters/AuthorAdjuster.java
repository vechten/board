package korzeniewski.hubert.board.converters.filter.adjusters;

import korzeniewski.hubert.board.model.notice.NoticeToFilter;
import org.springframework.stereotype.Service;

/**
 * Adjuster of NoticeToFilter's author field.
 */
@Service
public class AuthorAdjuster implements Adjuster {

    /**
     * Adjust author field of given noticeToFilter. Leaves null value or not empty.
     *
     * @param noticeToFilter to be adjusted
     * @return adjusted noticeToFilter
     */
    @Override
    public NoticeToFilter adjust(NoticeToFilter noticeToFilter) {
        if (noticeToFilter.getAuthor() != null && noticeToFilter.getAuthor().equals("")) {
            noticeToFilter.setAuthor(null);
        }
        return noticeToFilter;
    }

}
