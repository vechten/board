package korzeniewski.hubert.board.repository.notices.exceptions;

/**
 * Error which can be thrown when object of model class with requested id does not exist.
 */
public class NotFoundException extends Exception{

    public NotFoundException(String message) {
        super(message);
    }

}
