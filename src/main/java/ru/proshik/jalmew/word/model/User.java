package ru.proshik.jalmew.word.model;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by proshik on 05.05.16.
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @GenericGenerator(name = "user_seq", strategy = "enhanced-sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "user_seq"),
                    @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.OPT_PARAM, value = "pooled-lo"),
                    @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1")})
    private Long id;

    @Column(name = "created_date", nullable = true, updatable = false, insertable = false)
    private Date createdDate;

    @Column(name = "login", updatable = false, nullable = false, unique = true)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "confirm_password")
    private Boolean confirmPassword;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "user")
    private Set<UserWords> userWords;

    public User() {

    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(Boolean confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getConfirmPassword() {
        return confirmPassword;
    }

    public String getEmail() {
        return email;
    }
}
