package korzeniewski.hubert.board.validators.fields;

import korzeniewski.hubert.board.model.notice.Notice;
import korzeniewski.hubert.board.validators.NoticeValidatorType;
import org.springframework.stereotype.Service;

/**
 * Validator of title field of notice.
 */
@Service
public class TitleNoticeValidator implements NoticeValidatorType {

    /**
     * Validates title field of given notice.
     * @param noticeToValidate notice which field will have been validated.
     * @return validation result
     * @throws Exception in case of not passing validation
     */
    @Override
    public boolean validate(Notice noticeToValidate) throws Exception {
        String titleToValidate = noticeToValidate.getTitle();
        if (titleToValidate == null || titleToValidate.equals("")) {
            throw new Exception("Incorrect value of title in new notice.");
        }
        return true;
    }

}