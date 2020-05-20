package korzeniewski.hubert.board.security.repository;

import korzeniewski.hubert.board.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Repository of entity class of user.
 */

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUserName(String userName);

}

