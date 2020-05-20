package korzeniewski.hubert.board.repository.images;


import korzeniewski.hubert.board.model.images.ImagesOfNotice;
import korzeniewski.hubert.board.model.notice.Notice;
import korzeniewski.hubert.board.repository.images.exceptions.NoticeNotFoundException;
import korzeniewski.hubert.board.repository.notices.NoticeRepository;
import korzeniewski.hubert.board.serialization.ImagesDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Wrapper of image of notices repository.
 */
@Service
public class ImagesOfNoticeRepositoryWrapper {

    private ImagesOfNoticeRepository imagesOfNoticeRepository;
    private NoticeRepository noticeRepository;
    private ImagesDeserializer imagesDeserializer;

    @Autowired
    public ImagesOfNoticeRepositoryWrapper(ImagesOfNoticeRepository imagesOfNoticeRepository, NoticeRepository noticeRepository, ImagesDeserializer imagesDeserializer) {
        this.imagesOfNoticeRepository = imagesOfNoticeRepository;
        this.noticeRepository = noticeRepository;
        this.imagesDeserializer = imagesDeserializer;
    }

    /**
     * Finds images of given notices id.
     *
     * @param noticeId which images have to be returned
     * @return images of requested notices
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws NoticeNotFoundException
     */
    public List<String> findImagesByNoticeId(int noticeId) throws IOException, ClassNotFoundException, NoticeNotFoundException {
        Optional<Notice> notice = noticeRepository.findById(noticeId);
        ImagesOfNotice imagesOfNotice;
        if (notice.isPresent()) {
            imagesOfNotice = imagesOfNoticeRepository.findByNotice(notice.get());
            List<String> requestedImages = imagesDeserializer.deserializeByteArrayToImages(imagesOfNotice.getImages());
            return requestedImages;
        }
        throw new NoticeNotFoundException("Given notices does not exist.");
    }

    /**
     * Looks for miniature of notices.
     *
     * @param noticeId of requested miniature
     * @return miniature as list of strings
     * @throws NoticeNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public List<String> findMiniatureOfNotice(int noticeId) throws NoticeNotFoundException, IOException, ClassNotFoundException {
        String miniature = findImagesByNoticeId(noticeId).stream().findFirst().get();
        List<String> miniatureToUpload = new ArrayList<>();
        miniatureToUpload.add(miniature);
        return miniatureToUpload;
    }

}
