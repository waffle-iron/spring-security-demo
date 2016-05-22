package ru.proshik.jalmew.word.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.proshik.jalmew.word.dto.WordRestIn;
import ru.proshik.jalmew.word.model.Users;
import ru.proshik.jalmew.word.model.ydict.YTranslateWord;
import ru.proshik.jalmew.word.model.UserWord;
import ru.proshik.jalmew.word.model.Word;
import ru.proshik.jalmew.word.repository.UserWordsRepository;
import ru.proshik.jalmew.word.repository.WordRepository;
import ru.proshik.jalmew.word.service.dto.UserWordServiceOut;
import ru.proshik.jalmew.word.service.exception.UserWordAlreadyExistException;
import ru.proshik.jalmew.word.service.exception.WordNotFountException;
import ru.proshik.jalmew.word.util.restclient.YandexDictClient;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by proshik on 06.05.16.
 */
@Service
public class WordService {

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private UserWordsRepository userWordsRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private YandexDictClient yandexClient;

    @Transactional
    public void saveWord(WordRestIn param) throws UserWordAlreadyExistException, WordNotFountException {

        Optional<Word> word = wordRepository.findByValue(param.getWord());
        Users user = userService.getUser();

        if (!word.isPresent()) {
            YTranslateWord translate = yandexClient.lookup(param.getWord());

            if (translate == null || (translate != null && translate.getDef().length == 0)) {
                throw new WordNotFountException("Translate from 'en' to 'ru' for word=" + param.getWord() + " not found");
            }
            Optional<Word> savedWord = Optional.of(wordRepository.save(new Word(param.getWord(), translate)));
            userWordsRepository.save(new UserWord(user, savedWord.get()));
        } else {
            Optional<UserWord> userWord = userWordsRepository.findByWordAndUsers_Id(word.get(), user.getId());

            if (!userWord.isPresent()) {
                userWordsRepository.save(new UserWord(userService.getUser(), word.get()));
            } else {
                throw new UserWordAlreadyExistException("Word for user already exist");
            }
        }
    }

    public List<UserWordServiceOut> getUserWords() {
        Users user = userService.getUser();

        return userWordsRepository.findByUsers_Id(user.getId()).stream()
                .map(uw -> new UserWordServiceOut(uw.getWord().getValue(), uw.getStatistic().getState()))
                .collect(Collectors.toList());
    }
}
