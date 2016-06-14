package ru.proshik.jalmew.word.service;

import org.springframework.transaction.annotation.Transactional;
import ru.proshik.jalmew.word.dto.WordRestIn;
import ru.proshik.jalmew.word.service.dto.UserWordServiceOut;
import ru.proshik.jalmew.word.service.exception.UserWordAlreadyExistException;
import ru.proshik.jalmew.word.service.exception.WordNotFountException;

import java.util.List;

/**
 * Created by proshik on 11.06.16.
 */
public interface WordService {

    void saveWord(WordRestIn param) throws UserWordAlreadyExistException, WordNotFountException;

    List<UserWordServiceOut> getUserWords();
}
