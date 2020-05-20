package korzeniewski.hubert.board.repository.images;

import korzeniewski.hubert.board.model.images.ImagesOfNotice;
import korzeniewski.hubert.board.model.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

/**
 * Repository of images connected to proper notices.
 */
@Repository
@Transactional
public interface ImagesOfNoticeRepository extends JpaRepository<ImagesOfNotice, UUID>, QueryByExampleExecutor<ImagesOfNotice> {

    public ImagesOfNotice findByNotice(Notice notice);

}
