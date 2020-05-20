package korzeniewski.hubert.board.converters.filter.adjusters;

import korzeniewski.hubert.board.model.notice.NoticeToFilter;
import org.springframework.stereotype.Service;

/**
 * Adjuster of NoticeToFilter's title field.
 */
@Service
public class TitleAdjuster implements Adjuster {

    /**
     * Adjust title field of given noticeToFilter. Leaves null value or not empty.
     *
     * @param noticeToFilter to be adjusted
     * @return adjusted noticeToFilter
     */
    @Override
    public NoticeToFilter adjust(NoticeToFilter noticeToFilter) {
        if (noticeToFilter.getTitle() != null && noticeToFilter.getTitle().equals("")) {
            noticeToFilter.setTitle(null);
        }
        return noticeToFilter;
    }

}

