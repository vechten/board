package korzeniewski.hubert.board.model.images;

import com.fasterxml.jackson.annotation.JsonIgnore;
import korzeniewski.hubert.board.model.notice.Notice;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import java.util.UUID;

/**
 * Entity class of images connected to proper notices.
 */
@Entity
public class ImagesOfNotice {

    @Id
    private UUID id;
    @Lob
    private byte[] images;
    @JsonIgnore
    @OneToOne
    private Notice notice;

    public ImagesOfNotice() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public byte[] getImages() {
        return images;
    }

    public void setImages(byte[] images) {
        this.images = images;
    }

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

}
