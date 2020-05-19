package korzeniewski.hubert.board.validators.fields;

import korzeniewski.hubert.board.model.notice.Notice;
import korzeniewski.hubert.board.validators.NoticeValidatorType;
import org.springframework.stereotype.Service;

/**
 * Validator of content field of notice.
 */
@Service
public class ContentNoticeValidator implements NoticeValidatorType {

    /**
     * Validates content field of given notice.
     *
     * @param noticeToValidate notice which field will have been validated.
     * @return result of validation
     * @throws Exception in case of not passing validation
     */
    @Override
    public boolean validate(Notice noticeToValidate) throws Exception {
        String contentToValidate = noticeToValidate.getContent();
        if (contentToValidate == null || contentToValidate.equals("")) {
            throw new Exception("Incorrect value of content in new notice.");
        }
        return true;
    }

}
