package korzeniewski.hubert.board.security;

import korzeniewski.hubert.board.security.model.User;
import korzeniewski.hubert.board.security.repository.UserRepositoryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * For authorization and authentication purposes.
 */
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private UserRepositoryWrapper userRepositoryWrapper;

    @Autowired
    public UserDetailsService(UserRepositoryWrapper userRepository) {
        this.userRepositoryWrapper = userRepository;
    }

    /**
     * Look for user in database according to username got during spring security validation.
     *
     * @param userName username from user
     * @return instance of class to get user login data and grant authority
     * @throws UsernameNotFoundException in case of wrong input of username
     */
    @Override
    public UserDetails loadUserByUsername(String userName) {
        User user = userRepositoryWrapper.findUserByUsernameForSecurityPurposes(userName);
        return new UserPrincipal(user);
    }

}
