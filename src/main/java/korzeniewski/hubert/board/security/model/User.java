package korzeniewski.hubert.board.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import korzeniewski.hubert.board.model.notice.Notice;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
/**
 * User model for security purposes.
 */

@Entity
@Table(name = "users")
public class User {

    @Id
    @NotNull
    private UUID id;
    @Column(name = "USERNAME", unique = true)
    @UniqueElements
    private String userName;
    @JsonIgnore
    private String password;
    @JsonIgnore
    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Notice> notices;

    public User(UUID id, String userName, String password, List<Notice> notices) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.notices = notices;
    }

    public User() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Notice> getNotices() {
        return notices;
    }

    public void setNotices(List<Notice> notices) {
        this.notices = notices;
    }

}
