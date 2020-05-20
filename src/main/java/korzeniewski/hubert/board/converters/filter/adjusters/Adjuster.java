package korzeniewski.hubert.board.converters.filter.adjusters;

import korzeniewski.hubert.board.model.notice.NoticeToFilter;
import org.springframework.stereotype.Component;

/**
 * Interface of adjusters of NoticeToFilter when converting to Notice.
 */
@Component
public interface Adjuster {

    /**
     * Adjusts fields of given noticeToFilter to proper one.
     *
     * @param noticeToFilter to be adjusted
     * @return adjusted noticeToFilter
     */
    public abstract NoticeToFilter adjust(NoticeToFilter noticeToFilter);

}
