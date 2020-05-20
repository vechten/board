package korzeniewski.hubert.board.security.model;

import korzeniewski.hubert.board.model.notice.Notice;

import java.util.List;
import java.util.UUID;

/**
 * Builder of model class User.
 */
public class UserBuilder {

    private UUID id;
    private String userName;
    private String password;
    private List<Notice> notices;

    public UserBuilder() {
    }

    public UserBuilder withRandomId() {
        this.id = UUID.randomUUID();
        return this;
    }

    public UserBuilder withUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withNotices(List<Notice> notices) {
        this.notices = notices;
        return this;
    }

    public User build() {
        User user = new User();
        user.setId(this.id);
        user.setUserName(this.userName);
        user.setPassword(this.password);
        user.setNotices(this.notices);
        return user;
    }

}
