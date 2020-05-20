package korzeniewski.hubert.board.repository.images.exceptions;

/**
 * Exception in case of wrong id of notices to search in database.
 */
public class NoticeNotFoundException extends Exception {

    public NoticeNotFoundException(String message) {
        super(message);
    }

}
