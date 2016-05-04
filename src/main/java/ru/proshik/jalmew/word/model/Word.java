package ru.proshik.jalmew.word.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by proshik on 03.05.16.
 */
@Entity
@Table(name = "word")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "word_seq")
    @GenericGenerator(name = "word_seq", strategy = "enhanced-sequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "word_seq"),
                    @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.OPT_PARAM, value = "pooled-lo"),
                    @org.hibernate.annotations.Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM, value = "1")})
    private Long id;

    @Column(name = "created_date", nullable = true, updatable = false, insertable = false)
    private Date createdDate;

    @Column(name = "value")
    private String value;

//    @Column(name = "translate")
//    private String translate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "word_theme",
            joinColumns = {
                    @JoinColumn(name = "word_id")},
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "theme_id",
                            referencedColumnName = "id")})
    @Cascade(value = {
            org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.PERSIST})
//    @Fetch(FetchMode.SELECT)
    private List<Theme> theme;


    public Word() {
    }

//    public Word(String value, String translate) {
//        this.value = value;
//        this.translate = translate;
//    }

    public Long getId() {
        return id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getValue() {
        return value;
    }

//    public String getTranslate() {
//        return translate;
//    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Theme> getTheme() {
        return theme;
    }

    public void setTheme(List<Theme> theme) {
        this.theme = theme;
    }

//    public void setTranslate(String translate) {
//        this.translate = translate;
//    }
}
