package korzeniewski.hubert.board.converters.filter.adjusters;

import korzeniewski.hubert.board.model.notice.NoticeToFilter;
import org.springframework.stereotype.Service;

/**
 * Adjuster of NoticeToFilter's date field.
 */
@Service
public class DateAdjuster implements Adjuster {

    /**
     * Adjust date field of given noticeToFilter. Leaves null value or not empty.
     *
     * @param noticeToFilter to be adjusted
     * @return adjusted noticeToFilter
     */
    @Override
    public NoticeToFilter adjust(NoticeToFilter noticeToFilter) {
        if (noticeToFilter.getDate() != null && noticeToFilter.getDate().equals("")) {
            noticeToFilter.setDate(null);
        }
        return noticeToFilter;
    }

}
