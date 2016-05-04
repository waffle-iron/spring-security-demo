package ru.proshik.jalmew.word.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by proshik on 05.05.16.
 */
@Entity
@Table(name = "user_words")
public class UserWords {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_words_seq")
    @GenericGenerator(name = "user_words_seq", strategy = "enhanced-sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "user_words_seq"),
                    @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.OPT_PARAM, value = "pooled-lo"),
                    @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1")})
    private Long id;

    @Column(name = "created_date", nullable = true, updatable = false, insertable = false)
    private Date createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "word_id", updatable = false)
    private Word word;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_word_statistic_id")
    private UserWordStatistic statistic;


    public UserWords() {
    }

    public Long getId() {
        return id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public void setStatistic(UserWordStatistic statistic) {
        this.statistic = statistic;
    }

    public User getUser() {
        return user;
    }

    public Word getWord() {
        return word;
    }

    public UserWordStatistic getStatistic() {
        return statistic;
    }
}
