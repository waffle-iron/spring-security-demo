package ru.proshik.jalmew.word.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.proshik.jalmew.word.model.UserWord;
import ru.proshik.jalmew.word.model.Word;

import java.util.List;
import java.util.Optional;

/**
 * Created by proshik on 06.05.16.
 */
@Repository
public interface UserWordsRepository extends JpaRepository<UserWord, Long> {

    Optional<UserWord> findByWord(Word word);

    Optional<UserWord> findByWordAndUsers_Id(Word word, Long id);

    List<UserWord> findByUsers_Id(Long id);

}
