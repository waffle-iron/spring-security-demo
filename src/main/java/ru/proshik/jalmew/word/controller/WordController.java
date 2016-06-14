package ru.proshik.jalmew.word.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClientException;
import ru.proshik.jalmew.word.dto.ErrorBean;
import ru.proshik.jalmew.word.dto.WordRestIn;
import ru.proshik.jalmew.word.service.WordService;
import ru.proshik.jalmew.word.service.WordServiceImpl;
import ru.proshik.jalmew.word.service.dto.UserWordServiceOut;
import ru.proshik.jalmew.word.service.exception.UserWordAlreadyExistException;
import ru.proshik.jalmew.word.service.exception.WordNotFountException;

import java.util.List;

/**
 * Created by proshik on 01.05.16.
 */
@Controller
@RequestMapping("word")
public class WordController {

    private static final Logger log = LoggerFactory.getLogger(WordController.class);

    @Autowired
    private WordService wordService;

    @RequestMapping(method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity add(@RequestBody @Validated WordRestIn param) {
        try {
            wordService.saveWord(param);
            return ResponseEntity.ok().build();
        } catch (UserWordAlreadyExistException e) {
            log.error("{WORD-CONTROLLER_ADD} word for user already exists", e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorBean(e.getMessage()));
        } catch (WordNotFountException e) {
            log.error("{WORD-CONTROLLER_ADD} translate for word not found", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorBean(e.getMessage()));
        } catch (RestClientException e) {
            log.error("{WORD-CONTROLLER_ADD} external system exception on rest request", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorBean(e.getMessage()));
        }

    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserWordServiceOut>> getUserWords() {
        return ResponseEntity.ok(wordService.getUserWords());
    }

}