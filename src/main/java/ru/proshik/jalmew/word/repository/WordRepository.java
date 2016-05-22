package ru.proshik.jalmew.word.repository;

import org.springframework.stereotype.Repository;
import ru.proshik.jalmew.word.model.Word;

import java.util.Optional;

/**
 * Created by proshik on 06.05.16.
 */
@Repository
public interface WordRepository extends org.springframework.data.repository.Repository<Word, Long> {

    Optional<Word> findByValue(String value);

    Word save(Word toBeSave);
}
