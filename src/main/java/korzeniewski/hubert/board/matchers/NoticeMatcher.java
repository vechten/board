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
     * Returns example from given notice.
     *
     * @param notice notice to be converted
     * @return example from given notice
     */
    public Example<Notice> createExample(Notice notice) {
        ExampleMatcher matcher = ExampleMatcher.matchingAny().withIgnoreNullValues();
        return Example.of(notice, matcher);
    }

}
