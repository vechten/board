package korzeniewski.hubert.board.repository;

import korzeniewski.hubert.board.matchers.NoticeMatcher;
import korzeniewski.hubert.board.model.notice.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Check database for existence of notice.
 */
@Service
public class NoticeRepositoryChecker {

    private NoticeRepository noticeRepository;
    private NoticeMatcher noticeMatcher;

    @Autowired
    public NoticeRepositoryChecker(NoticeRepository noticeRepository, NoticeMatcher noticeMatcher) {
        this.noticeRepository = noticeRepository;
        this.noticeMatcher = noticeMatcher;
    }

    /**
     * Check if given notice exists in database.
     * Do that by making example notice from given one.
     *
     * @param noticeToCheck notice to be checked for existence
     * @return optional of notice after searching with example mechanism
     */
    public Optional<Notice> checkDatabaseForNotice(Notice noticeToCheck) {
        Example<Notice> exampleNewNotice = noticeMatcher.createExampleWithAllMatcher(noticeToCheck);
        return noticeRepository.findOne(exampleNewNotice);
    }

}
