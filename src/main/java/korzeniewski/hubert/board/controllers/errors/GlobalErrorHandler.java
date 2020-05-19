package korzeniewski.hubert.board.controllers.errors;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Error handler for backend application.
 */
@ControllerAdvice
public class GlobalErrorHandler {

    private static final Logger logger = Logger.getLogger(GlobalErrorHandler.class.getName());

    /**
     * Log information about occured error.
     *
     * @param exception exception which launches this method
     */

    @ExceptionHandler(Exception.class)
    public void handleError(Exception exception) {
        logger.log(Level.WARNING, exception.getMessage(), exception);
    }

}
