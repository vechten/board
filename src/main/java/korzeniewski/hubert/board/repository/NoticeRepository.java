package korzeniewski.hubert.board.repository;

import korzeniewski.hubert.board.model.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public interface NoticeRepository extends JpaRepository<Notice, Integer>, QueryByExampleExecutor<Notice> {
}

