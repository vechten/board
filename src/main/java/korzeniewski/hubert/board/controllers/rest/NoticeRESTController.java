package korzeniewski.hubert.board.controllers.rest;
import korzeniewski.hubert.board.converters.PageToNoticesWithPaginationConverter;
import korzeniewski.hubert.board.matchers.NoticeMatcher;
import korzeniewski.hubert.board.model.notice.Notice;
import korzeniewski.hubert.board.model.notice.NoticesWithPagination;
import korzeniewski.hubert.board.repository.NoticeRepository;
import korzeniewski.hubert.board.repository.NoticeRepositoryWrapper;
import korzeniewski.hubert.board.validators.NoticeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;



@RestController
@RequestMapping("api/notices")
@CrossOrigin
public class NoticeRESTController {

    private static final Logger logger = Logger.getLogger(NoticeRESTController.class.getName());

    private NoticeRepository noticeRepository;
    private PageToNoticesWithPaginationConverter converter;
    private NoticeMatcher noticeMatcher;
    private NoticeRepositoryWrapper noticeRepositoryWrapper;
    private NoticeValidator noticeValidator;


    public NoticeRESTController(NoticeRepository noticeRepository, PageToNoticesWithPaginationConverter converter, NoticeMatcher noticeMatcher, NoticeRepositoryWrapper noticeRepositoryWrapper, NoticeValidator noticeValidator) {
        this.noticeRepository = noticeRepository;
        this.converter = converter;
        this.noticeMatcher = noticeMatcher;
        this.noticeRepositoryWrapper = noticeRepositoryWrapper;
        this.noticeValidator = noticeValidator;
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
     * Returns notices from given page.
     *
     * @param pageNumber number of page with notices to return
     * @param pageLength amount of notices on page
     * @return requested page of notices
     */
    @GetMapping(value = "/page", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getNoticesFromGivenPage(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageLength") int pageLength) {
        Page<Notice> pageOfNotices = noticeRepository.findAll(new PageRequest(pageNumber, pageLength));
        NoticesWithPagination noticesWithPagination = converter.convertPageToNoticesWithPagination(pageOfNotices);
        return new ResponseEntity<>(noticesWithPagination, HttpStatus.OK);
    }

    /**
     * Returns notices which match example notice from request body
     *
     * @param noticeToFilter example notice to match
     * @return notices which match example notice
     */
    @PostMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getNoticesAfterFilteringWithExample(@RequestBody Notice noticeToFilter, @RequestParam("pageNumber") int pageNumber, @RequestParam("pageLength") int pageLength) {
        Example<Notice> exampleNotice = noticeMatcher.createExampleWithAnyMatcher(noticeToFilter);
        Page<Notice> pageOfNotices = noticeRepository.findAll(exampleNotice, new PageRequest(pageNumber, pageLength));
        NoticesWithPagination noticesWithPagination = converter.convertPageToNoticesWithPagination(pageOfNotices);
        return new ResponseEntity<>(noticesWithPagination, HttpStatus.OK);
    }
    /**
     * Post new notice to database and check the existence of that new notice in database.
     *
     * @param newNotice notice from request body to be added to database
     * @return added notice
     * @throws Exception in case if that post does not exist in database
     */
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addNewNotice(@RequestBody Notice newNotice) throws Exception {
        noticeValidator.validateNotice(newNotice);
        Notice postedNotice = noticeRepositoryWrapper.saveNotice(newNotice);
        return new ResponseEntity<>(postedNotice, HttpStatus.OK);
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
