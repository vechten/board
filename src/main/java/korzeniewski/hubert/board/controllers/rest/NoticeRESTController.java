package korzeniewski.hubert.board.controllers;
import korzeniewski.hubert.board.model.notice.Notice;
import korzeniewski.hubert.board.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
@RequestMapping("api/notices")
@CrossOrigin
public class NoticeRESTController {

    private static final Logger logger = Logger.getLogger(NoticeRESTController.class.getName());

    private NoticeRepository noticeRepository;

    @Autowired
    public NoticeRESTController(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    /**
     * Returns list of all notices from database.
     *
     * @return all notices from database
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllNotices() {
        List<Notice> allNotices = noticeRepository.findAll();
        return new ResponseEntity<>(allNotices, HttpStatus.OK);
    }

    /**
     * Saves information about exception to logger and returns information about exception through ResponseEntity.
     *
     * @param req needed to take request url which takes place in case of error
     * @param ex  exception which called this handler
     * @return ResponseEntity with error message and http status
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleError(HttpServletRequest req, Exception ex) {
        String errorMessage = "Request: " + req.getRequestURL() + " raised " + ex;
        logger.log(Level.WARNING, errorMessage);
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

}
