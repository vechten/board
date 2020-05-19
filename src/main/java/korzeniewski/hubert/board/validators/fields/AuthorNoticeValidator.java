package korzeniewski.hubert.board.validators.fields;

import korzeniewski.hubert.board.model.notice.Notice;
import korzeniewski.hubert.board.validators.NoticeValidatorType;
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
     * @throws Exception in case of not passing validation
     */
    @Override
    public boolean validate(Notice noticeToValidate) throws Exception {
        String authorToValidate = noticeToValidate.getAuthor();
        if (authorToValidate == null || authorToValidate.equals("")) {
            throw new Exception("Incorrect value of author in new notice.");
        }
        return true;
    }

}
