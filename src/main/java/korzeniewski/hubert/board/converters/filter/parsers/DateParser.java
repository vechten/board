package korzeniewski.hubert.board.converters.filter.parsers;

import korzeniewski.hubert.board.model.notice.Notice;
import korzeniewski.hubert.board.model.notice.NoticeToFilter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Parser of date when converting from NoticeToFilter to Notice.
 */
@Service
public class DateParser implements Parser {

    /**
     * Parses date from noticeToFilter and adds that to notices.
     *
     * @param finalNotice         which field will be updated with value form noticeToFilter
     * @param finalNoticeToFilter which value has to be parsed
     * @return notices with value from noticeToFilter
     * @throws ParseException
     */
    @Override
    public Notice parse(Notice finalNotice, NoticeToFilter finalNoticeToFilter) throws ParseException {
        if (finalNoticeToFilter.getDate() != null && finalNoticeToFilter.getDate().matches("^([0-9]{4}([-][0-9]{2}){2})$")) {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(finalNoticeToFilter.getDate());
            finalNotice.setCreationDate(date);
        }
        return finalNotice;
    }

}
