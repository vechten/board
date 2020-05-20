package korzeniewski.hubert.board.validators;

import korzeniewski.hubert.board.model.notice.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class of notices validator.
 */
@Service
public class NoticeValidator {

    private List<NoticeValidatorType> validators;

    @Autowired
    public NoticeValidator(List<NoticeValidatorType> validators) {
        this.validators = validators;
    }

    /**
     * Validates notices by using all types of notices validators.
     *
     * @param noticeToValidate
     * @return boolean value of validation
     * @throws Exception in case of not passing validation
     */
    public boolean validateNotice(Notice noticeToValidate) throws Exception {
        for (NoticeValidatorType validator : validators) {
            validator.validate(noticeToValidate);
        }
        return true;
    }

}
