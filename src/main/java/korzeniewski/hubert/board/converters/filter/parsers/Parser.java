package korzeniewski.hubert.board.converters.filter.parsers;

import korzeniewski.hubert.board.model.notice.Notice;
import korzeniewski.hubert.board.model.notice.NoticeToFilter;
import org.springframework.stereotype.Component;

import java.text.ParseException;

/**
 * Parse interface when converting from NoticeToFilter to Notice.
 */
@Component
public interface Parser {

    /**
     * Parses from noticeToFilter's field to proper one and adds to notices.
     *
     * @param notice notices which field has to be parsed with value from noticeToFilter
     * @param noticeToFilter which field has to be parsed
     * @return notices with parsed field
     * @throws ParseException
     */
    public Notice parse(Notice notice, NoticeToFilter noticeToFilter) throws ParseException;

}

