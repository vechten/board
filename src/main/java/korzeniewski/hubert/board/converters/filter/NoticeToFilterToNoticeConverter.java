package korzeniewski.hubert.board.converters.filter;

import korzeniewski.hubert.board.converters.filter.adjusters.Adjuster;
import korzeniewski.hubert.board.converters.filter.parsers.Parser;
import korzeniewski.hubert.board.model.notice.Notice;
import korzeniewski.hubert.board.model.notice.NoticeBuilder;
import korzeniewski.hubert.board.model.notice.NoticeToFilter;
import korzeniewski.hubert.board.security.model.User;
import korzeniewski.hubert.board.security.model.UserBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class NoticeToFilterToNoticeConverter {

    private static final Logger logger = Logger.getLogger(NoticeToFilterToNoticeConverter.class.getName());

    private List<Adjuster> adjusters;
    private List<Parser> parsers;

    @Autowired
    public NoticeToFilterToNoticeConverter(List<Adjuster> adjusters, List<Parser> parsers) {
        this.adjusters = adjusters;
        this.parsers = parsers;
    }

    /**
     * Converts from NoticeToFilter to Notice.
     *
     * @param noticeToFilter which has to be converted to Notice to allow repository to filter notices.
     * @return converted notices
     * @throws ParseException
     */
    public Notice convert(NoticeToFilter noticeToFilter) throws ParseException {
        NoticeToFilter finalNoticeToFilter = noticeToFilter;
        adjusters.stream().forEach(adjuster -> adjuster.adjust(finalNoticeToFilter));

        Notice finalNotice = createFinalNotice(finalNoticeToFilter);

        parsers.forEach(parser -> {
            try {
                parser.parse(finalNotice, finalNoticeToFilter);
            } catch (ParseException parseException) {
                logger.log(Level.WARNING, parseException.getMessage(), parseException);
            }
        });

        return finalNotice;
    }

    /**
     * Creates notices with user. Notice's fields will be updated with values from noticeToFilter
     *
     * @param finalNoticeToFilter which user will be given to notices
     * @return notices with fields has to be updated with values from noticeToFilter
     */
    public Notice createFinalNotice(NoticeToFilter finalNoticeToFilter) {
        User user = new UserBuilder().withUserName(finalNoticeToFilter.getAuthor()).build();
        return new NoticeBuilder().withAuthor(user).withTitle(finalNoticeToFilter.getTitle()).buildNotice();
    }

}

