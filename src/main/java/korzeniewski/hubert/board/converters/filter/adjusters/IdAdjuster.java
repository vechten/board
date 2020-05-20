package korzeniewski.hubert.board.converters.filter.adjusters;

import korzeniewski.hubert.board.model.notice.NoticeToFilter;
import org.springframework.stereotype.Service;

/**
 * Adjuster of NoticeToFilter's id field.
 */
@Service
public class IdAdjuster implements Adjuster {

    /**
     * Adjust id field of given noticeToFilter. Leaves null value or not empty.
     *
     * @param noticeToFilter to be adjusted
     * @return adjusted noticeToFilter
     */
    @Override
    public NoticeToFilter adjust(NoticeToFilter noticeToFilter) {
        if (noticeToFilter.getId() != null && noticeToFilter.getId().equals("")) {
            noticeToFilter.setId(null);
        }
        return noticeToFilter;
    }

}
