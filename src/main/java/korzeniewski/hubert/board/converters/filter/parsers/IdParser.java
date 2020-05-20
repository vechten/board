package korzeniewski.hubert.board.converters.filter.parsers;

import korzeniewski.hubert.board.model.notice.Notice;
import korzeniewski.hubert.board.model.notice.NoticeToFilter;
import org.springframework.stereotype.Service;

import java.text.ParseException;

/**
 * Parser of id when converting from NoticeToFilter to Notice.
 */
@Service
public class IdParser implements Parser {

    /**
     * Parses id from noticeToFilter and adds that to notices.
     *
     * @param finalNotice         which field will be updated with value form noticeToFilter
     * @param finalNoticeToFilter which value has to be parsed
     * @return notices with value from noticeToFilter
     * @throws ParseException
     */
    @Override
    public Notice parse(Notice finalNotice, NoticeToFilter finalNoticeToFilter) {
        if (finalNoticeToFilter.getId() != null && finalNoticeToFilter.getId().matches("^[1-9]*$")) {
            finalNotice.setId(Integer.parseInt(finalNoticeToFilter.getId()));
        }
        return finalNotice;
    }

}
