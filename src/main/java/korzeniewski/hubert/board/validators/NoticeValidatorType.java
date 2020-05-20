package korzeniewski.hubert.board.validators;

import korzeniewski.hubert.board.model.notice.Notice;
import org.springframework.stereotype.Component;

/**
 * Interface to gather all types of notices validators
 */
@Component
public interface NoticeValidatorType {

    /**
     * Validates field of given notices.
     *
     * @param noticeToValidate notices which field will have been validated.
     * @return boolean value of validation
     * @throws Exception in case of not passing validation
     */
    boolean validate(Notice noticeToValidate) throws Exception;

}
