package korzeniewski.hubert.board.matchers;

import korzeniewski.hubert.board.model.notice.Notice;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

/**
 * Creates example from given notices.
 */
@Service
public class NoticeMatcher {

    /**
     * Returns example from given notices with any matcher.
     *
     * @param notice notices to be converted
     * @return example from given notices
     */
    public Example<Notice> createExampleWithAnyMatcher(Notice notice) {
        ExampleMatcher matcher = ExampleMatcher.matchingAny().withIgnoreNullValues().withMatcher("title", contains()).withIgnoreCase();
        if(notice.getId() == 0){
            matcher = matcher.withIgnorePaths("id");
        }
        return Example.of(notice, matcher);
    }

    /**
     * Returns example from given notices with all matcher.
     *
     * @param notice notices to be converted
     * @return example from given notices
     */
    public Example<Notice> createExampleWithAllMatcher(Notice notice) {
        ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreNullValues().withMatcher("title", contains()).withIgnoreCase();
        if(notice.getId() == 0){
            matcher = matcher.withIgnorePaths("id");
        }
        return Example.of(notice, matcher);
    }

}

