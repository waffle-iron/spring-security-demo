package ru.proshik.jalmew.word.model;

import org.hibernate.annotations.*;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by proshik on 05.05.16.
 */
@Entity
@Table(name="user_word_statistic")
public class UserWordStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_word_statistic_seq")
    @GenericGenerator(name = "user_word_statistic_seq", strategy = "enhanced-sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "user_word_statistic_seq"),
                    @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.OPT_PARAM, value = "pooled-lo"),
                    @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1")})
    private Long id;

    @Column(name = "created_date", nullable = true, updatable = false, insertable = false)
    private Date createdDate;

    @Column
    @Enumerated(EnumType.STRING)
    private LearningState state;

    public UserWordStatistic() {
    }

    public UserWordStatistic(LearningState state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public LearningState getState() {
        return state;
    }

    public void setState(LearningState state) {
        this.state = state;
    }
}
