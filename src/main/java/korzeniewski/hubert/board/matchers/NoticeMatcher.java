package korzeniewski.hubert.board.matchers;

import korzeniewski.hubert.board.model.notice.Notice;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

/**
 * Creates example from given notice.
 */
@Service
public class NoticeMatcher {

    /**
     * Returns example from given notice with any matcher.
     *
     * @param notice notice to be converted
     * @return example from given notice
     */


    public Example<Notice> createExampleWithAnyMatcher(Notice notice) {
        ExampleMatcher matcher = ExampleMatcher.matchingAny().withIgnoreNullValues();
        return Example.of(notice, matcher);
    }

    /**
     * Returns example from given notice with all matcher.
     *
     * @param notice notice to be converted
     * @return example from given notice
     */
    public Example<Notice> createExampleWithAllMatcher(Notice notice) {
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreNullValues();
        return Example.of(notice, matcher);
    }

}
