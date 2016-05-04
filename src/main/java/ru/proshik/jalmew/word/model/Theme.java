package ru.proshik.jalmew.word.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by proshiklunux on 03.05.16.
 */
@Entity
@Table(name = "theme")
public class Theme {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "theme_seq")
    @GenericGenerator(name = "theme_seq", strategy = "enhanced-sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "theme_seq"),
                    @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.OPT_PARAM, value = "pooled-lo"),
                    @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1")})
    private Long id;

    @Column(name = "created_date", nullable = true, updatable = false, insertable = false)
    private Date createdDate;

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private HolderTheme holder;

    public Theme() {
    }

    public Long getId() {
        return id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HolderTheme getHolder() {
        return holder;
    }

    public void setHolder(HolderTheme holder) {
        this.holder = holder;
    }
}
