package ru.proshik.jalmew.word.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.proshik.jalmew.word.dto.WordRestIn;

/**
 * Created by proshik on 01.05.16.
 */
@Controller
@RequestMapping("word")
public class WordController {

    @RequestMapping(method = RequestMethod.POST)
    public WordRestIn add(@RequestBody WordRestIn word){
        return word;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity get(){
        return ResponseEntity.ok().build();
    }

}