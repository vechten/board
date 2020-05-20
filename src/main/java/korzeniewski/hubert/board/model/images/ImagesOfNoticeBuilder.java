package korzeniewski.hubert.board.model.images;

import korzeniewski.hubert.board.model.notice.Notice;

import java.util.UUID;

/**
 * Builder for entity class of ImagesOfNotices
 */
public class ImagesOfNoticeBuilder {

    private UUID id;
    private byte[] images;
    private Notice notice;

    public ImagesOfNoticeBuilder withRandomId() {
        this.id = UUID.randomUUID();
        return this;
    }

    public ImagesOfNoticeBuilder withImages(byte[] images) {
        this.images = images;
        return this;
    }

    public ImagesOfNoticeBuilder withNotice(Notice notice) {
        this.notice = notice;
        return this;
    }

    public ImagesOfNotice buildImagesOfNotice() {
        ImagesOfNotice imagesOfNotice = new ImagesOfNotice();
        imagesOfNotice.setId(this.id);
        imagesOfNotice.setImages(this.images);
        imagesOfNotice.setNotice(notice);
        return imagesOfNotice;
    }

}
