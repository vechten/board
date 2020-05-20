package korzeniewski.hubert.board.repository.notices;

import korzeniewski.hubert.board.model.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Repository of model class Notice.
 */
@Repository
@Transactional
public interface NoticeRepository extends JpaRepository<Notice, Integer>, QueryByExampleExecutor<Notice> {

    public Page<Notice> findAll(Pageable page);

    public List<Notice> findAllByAuthor_UserName(String authorName);

    public List<Notice> findAllById(int id);

    public List<Notice> findAllByTitle(String title);

    public Page<Notice> findAllByIdAndTitleAndAuthor_UserName(Pageable page, int id, String title, String userName);

    public Page<Notice> findAllByIdOrTitleOrAuthor_UserName(Pageable page, int id, String title, String userName);

}

