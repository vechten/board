package korzeniewski.hubert.board.repository;

import korzeniewski.hubert.board.model.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface NoticeRepository extends JpaRepository<Notice, Integer>, QueryByExampleExecutor<Notice> {
    /**
     * When there is given request from frontend to find notice with using pagination.
     *
     * @param page page of notices to be found
     * @return page of notices with information about further pages
     */
    Page<Notice> findAll(Pageable page);
}

