package ru.proshik.jalmew.word.model;

import org.hibernate.annotations.*;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import ru.proshik.jalmew.word.configuration.hibernate.types.JSONBUserType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * Created by proshik on 03.05.16.
 */
@Entity
@Table(name = "word")
@TypeDef(
        name = "jsonb",
        typeClass = JSONBUserType.class,
        parameters = {@org.hibernate.annotations.Parameter(
                name = JSONBUserType.CLASS,
                value = "ru.proshik.jalmew.word.model.Translate")})
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

    @Column
    @Type(type = JSONBUserType.JSONB_TYPE)
    private Translate translate;

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
//    @Fetch(FetchMode.SELECT) // TODO: 06.05.16 происследовать, что лучше выбрать в качестве FetchMode
    private List<Theme> theme;


    public Word() {
        
    }


    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public Translate getTranslate() {
        return translate;
    }

    public List<Theme> getTheme() {
        return theme;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setTranslate(Translate translate) {
        this.translate = translate;
    }

    public void setTheme(List<Theme> theme) {
        this.theme = theme;
    }
}
