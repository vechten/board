package korzeniewski.hubert.board.repository.notices;

import korzeniewski.hubert.board.matchers.NoticeMatcher;
import korzeniewski.hubert.board.model.images.ImagesOfNotice;
import korzeniewski.hubert.board.model.images.ImagesOfNoticeBuilder;
import korzeniewski.hubert.board.model.notice.Notice;
import korzeniewski.hubert.board.repository.notices.exceptions.NotFoundException;
import korzeniewski.hubert.board.serialization.ImagesSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Wrapper of notice repository.
 */
@Service
public class NoticeRepositoryWrapper {

    private static final Logger logger = Logger.getLogger(NoticeRepositoryWrapper.class.getName());

    private NoticeRepository noticeRepository;
    private NoticeMatcher noticeMatcher;
    private ImagesSerializer imagesSerializer;

    @Autowired
    public NoticeRepositoryWrapper(NoticeRepository noticeRepository, NoticeMatcher noticeMatcher, ImagesSerializer imagesSerializer) {
        this.noticeRepository = noticeRepository;
        this.noticeMatcher = noticeMatcher;
        this.imagesSerializer = imagesSerializer;
    }

    /**
     * Check if given notice exists in database.
     * Do that by making example notice from given one.
     *
     * @param noticeToCheck notice to be checked for existence
     * @return optional of notice after searching with example mechanism
     */
    public Optional<Notice> checkDatabaseForNotice(Notice noticeToCheck) {
        Example<Notice> exampleNewNotice = noticeMatcher.createExampleWithAllMatcher(noticeToCheck);
        return noticeRepository.findOne(exampleNewNotice);
    }

    /**
     * Sets time of notice and saves it to database.
     *
     * @param noticeToSave
     * @return saved notice
     */
    public Notice saveNotice(Notice noticeToSave) {
        noticeToSave.setCreationDate(new Date());
        return noticeRepository.save(noticeToSave);
    }

    /**
     * Adds images to existing notice and save those information to database.
     *
     * @param noticeId to be updated with images
     * @param images   images to be saved
     * @return notice which was updated with images
     * @throws IOException
     */
    public Notice saveNoticeWithImages(int noticeId, List<String> images) throws IOException {
        Notice notice = noticeRepository.findById(noticeId).get();
        byte[] serializedImages = imagesSerializer.serializeStringifiedImages(images);
        ImagesOfNotice imagesOfNotice = new ImagesOfNoticeBuilder().withRandomId().withImages(serializedImages).withNotice(notice).buildImagesOfNotice();
        notice.setImages(imagesOfNotice);
        saveNotice(notice);
        return notice;
    }

    /**
     * Finds notice of requested id or throws NotFoundException
     *
     * @param noticeId to be found
     * @return notice
     * @throws NotFoundException in case of not found notice of given id
     */
    public Notice findNoticeById(int noticeId) throws NotFoundException {
        Optional<Notice> notice = noticeRepository.findById(noticeId);
        return notice.orElseThrow(() -> new NotFoundException("Notice not found"));
    }

}
