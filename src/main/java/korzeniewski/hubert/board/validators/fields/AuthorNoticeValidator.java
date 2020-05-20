package korzeniewski.hubert.board.validators.fields;

import korzeniewski.hubert.board.model.notice.Notice;
import korzeniewski.hubert.board.validators.NoticeValidatorType;
import korzeniewski.hubert.board.validators.exceptions.NoticeValidationException;
import korzeniewski.hubert.board.security.model.User;

import org.springframework.stereotype.Service;

/**
 * Validator of author field of notice.
 */
@Service
public class AuthorNoticeValidator implements NoticeValidatorType {

    /**
     * Validates author field of given notice.
     *
     * @param noticeToValidate notice which field will have been validated.
     * @return result of validation
     * @throws NoticeValidationException in case of not passing validation
     */
    @Override
    public boolean validate(Notice noticeToValidate) throws NoticeValidationException {
        User authorToValidate = (User) noticeToValidate.getAuthor();
        if (authorToValidate == null) {
            throw new NoticeValidationException("Incorrect value of author in new notice.");
        }
        return true;
    }

}

