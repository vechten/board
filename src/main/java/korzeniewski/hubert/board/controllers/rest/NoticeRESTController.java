package korzeniewski.hubert.board.controllers.rest;
import korzeniewski.hubert.board.converters.filter.NoticeToFilterToNoticeConverter;
import korzeniewski.hubert.board.converters.image.ImageFileToStringConverter;
import korzeniewski.hubert.board.converters.page.PageToNoticesWithPaginationConverter;
import korzeniewski.hubert.board.converters.sorting.SortingSchemeToTupleConverter;
import korzeniewski.hubert.board.matchers.NoticeMatcher;
import korzeniewski.hubert.board.model.notice.Notice;
import korzeniewski.hubert.board.model.notice.NoticeToFilter;
import korzeniewski.hubert.board.model.notice.NoticesWithPagination;
import korzeniewski.hubert.board.repository.images.ImagesOfNoticeRepositoryWrapper;
import korzeniewski.hubert.board.repository.images.exceptions.NoticeNotFoundException;
import korzeniewski.hubert.board.repository.notices.NoticeRepository;
import korzeniewski.hubert.board.repository.notices.NoticeRepositoryWrapper;
import korzeniewski.hubert.board.repository.notices.exceptions.NotFoundException;
import korzeniewski.hubert.board.serialization.ImagesSerializer;
import korzeniewski.hubert.board.validators.NoticeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * REST controller to operate with model class Notice.
 */
@RestController
@RequestMapping("api/notices")
@CrossOrigin
public class NoticeRESTController {

    private static final Logger logger = Logger.getLogger(NoticeRESTController.class.getName());

    private NoticeRepository noticeRepository;
    private NoticeRepositoryWrapper noticeRepositoryWrapper;
    private ImagesOfNoticeRepositoryWrapper imagesOfNoticeRepositoryWrapper;
    private PageToNoticesWithPaginationConverter converter;
    private NoticeMatcher noticeMatcher;
    private NoticeValidator noticeValidator;
    private ImageFileToStringConverter imageFileToStringConverter;
    private SortingSchemeToTupleConverter sortingSchemeToTupleConverter;
    private NoticeToFilterToNoticeConverter noticeToFilterToNoticeConverter;

    @Autowired
    public NoticeRESTController(NoticeRepository noticeRepository, NoticeRepositoryWrapper noticeRepositoryWrapper, ImagesOfNoticeRepositoryWrapper imagesOfNoticeRepositoryWrapper, PageToNoticesWithPaginationConverter converter, NoticeMatcher noticeMatcher, NoticeValidator noticeValidator, ImagesSerializer imagesSerializer, ImageFileToStringConverter imageFileToStringConverter, SortingSchemeToTupleConverter sortingSchemeToTupleConverter, NoticeToFilterToNoticeConverter noticeToFilterToNoticeConverter) {
        this.noticeRepository = noticeRepository;
        this.noticeRepositoryWrapper = noticeRepositoryWrapper;
        this.imagesOfNoticeRepositoryWrapper = imagesOfNoticeRepositoryWrapper;
        this.converter = converter;
        this.noticeMatcher = noticeMatcher;
        this.noticeValidator = noticeValidator;
        this.imageFileToStringConverter = imageFileToStringConverter;
        this.sortingSchemeToTupleConverter = sortingSchemeToTupleConverter;
        this.noticeToFilterToNoticeConverter = noticeToFilterToNoticeConverter;
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
     * Returns requested notice.
     *
     * @param noticeId of requested one
     * @return requested notice
     * @throws NotFoundException in case if notice of requested id does not exist
     */
    @GetMapping(value = "/{noticeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getNotice(@PathVariable int noticeId) throws NotFoundException {
        Notice notice = noticeRepositoryWrapper.findNoticeById(noticeId);
        return new ResponseEntity<>(notice, HttpStatus.OK);
    }

    /**
     * Returns text content of requested notice.
     *
     * @param noticeId of requested text content
     * @return text content of notice
     * @throws NotFoundException
     */
    @GetMapping(value = "/{noticeId}/content", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getNoticeContent(@PathVariable int noticeId) throws NotFoundException {
        Notice notice = noticeRepositoryWrapper.findNoticeById(noticeId);
        List<String> noticeContent = new LinkedList<>();
        noticeContent.add(notice.getContent());
        return new ResponseEntity<>(noticeContent, HttpStatus.OK);
    }

    /**
     * Returns notices according to requested index of page, page size, sorting, filtering.
     *
     * @param noticeToFilter example notice with filters
     * @param pageIndex      of requested one
     * @param pageSize       of requested one
     * @param sorting        of requested one
     * @return notices according to requested index of page, page size, sorting, filtering.
     * @throws ParseException
     */
    @PostMapping(value = "/page", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getNoticesAfterFilteringWithExample(@RequestBody NoticeToFilter noticeToFilter, @RequestParam("pageIndex") int pageIndex, @RequestParam("pageSize") int pageSize, @RequestParam("sort") String sorting) throws ParseException {
        Pair<String, Sort.Direction> sortScheme = sortingSchemeToTupleConverter.convertSortingSchemeToTuple(sorting);
        Notice convertedNotice = noticeToFilterToNoticeConverter.convert(noticeToFilter);
        Example<Notice> exampleNotice = noticeMatcher.createExampleWithAllMatcher(convertedNotice);
        Page<Notice> notices = noticeRepository.findAll(exampleNotice, new PageRequest(pageIndex, pageSize, sortScheme.getSecond(), sortScheme.getFirst()));
        NoticesWithPagination noticesWithPagination = converter.convertPageToNoticesWithPagination(notices);
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
     * Add images to existing notice.
     *
     * @param noticeId       to update with images
     * @param uploadedImages images to be attached to notice
     * @return images after saving in database
     * @throws IOException
     * @throws NoticeNotFoundException
     * @throws ClassNotFoundException
     */
    @PostMapping(value = "/addWithImages/{noticeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addImagesToNotice(@PathVariable int noticeId, @RequestParam("uploadedImages") List<MultipartFile> uploadedImages) throws IOException, NoticeNotFoundException, ClassNotFoundException {
        List<String> imagesOfNotice = imageFileToStringConverter.convertImageFilesToListOfString(uploadedImages);
        noticeRepositoryWrapper.saveNoticeWithImages(noticeId, imagesOfNotice);
        List<String> imagesFromDatabase = imagesOfNoticeRepositoryWrapper.findImagesByNoticeId(noticeId);
        return new ResponseEntity<>(imagesFromDatabase, HttpStatus.OK);
    }

    /**
     * Sends back all notices of requested user.
     *
     * @param authorName
     * @return notices of requested user/author
     */
    @GetMapping(value = "/author/{authorName}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findNoticesByUserId(@PathVariable("authorName") String authorName) {
        List<Notice> notices = noticeRepository.findAllByAuthor_UserName(authorName);
        return new ResponseEntity<>(notices, HttpStatus.OK);
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
        String errorMessage = "Request: " + req.getRequestURL() + " raised " + ex.getClass().getSimpleName() + " with message: " + ex.getMessage();
        logger.log(Level.WARNING, errorMessage);
        HttpStatus httpStatus = ex instanceof NotFoundException ? HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(errorMessage, httpStatus);
    }

}
