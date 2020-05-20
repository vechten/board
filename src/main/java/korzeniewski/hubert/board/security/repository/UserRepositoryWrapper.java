package korzeniewski.hubert.board.security.repository;

import korzeniewski.hubert.board.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Wrapper of user repository. Made for security purposes.
 */
@Service
public class UserRepositoryWrapper {

    private UserRepository userRepository;

    @Autowired
    public UserRepositoryWrapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Looks for user in database and in case of failure throws error for security purposes.
     *
     * @param userName userName of requested user
     * @return found user
     */
    public User findUserByUsernameForSecurityPurposes(String userName) {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

}
