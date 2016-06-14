package ru.proshik.jalmew.word.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.proshik.jalmew.word.dto.WordRestIn;
import ru.proshik.jalmew.word.service.WordService;
import ru.proshik.jalmew.word.service.WordServiceImpl;
import ru.proshik.jalmew.word.service.dto.UserWordServiceOut;
import ru.proshik.jalmew.word.service.exception.UserWordAlreadyExistException;
import ru.proshik.jalmew.word.service.exception.WordNotFountException;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by proshik on 24.05.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WordControllerTestSpring.TestConfiguration.class)
public class WordControllerTestSpring {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void addWordTest() throws Exception {
        mockMvc.perform(post("/word").content("{\"word\":\"word\"}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Configuration
    @EnableWebMvc
    public static class TestConfiguration {

        @Bean
        public WordController wordController() {
            return new WordController();
        }

        @Bean
        public WordService getWordService() {
            return new WordService() {
                @Override
                public void saveWord(WordRestIn param) throws UserWordAlreadyExistException, WordNotFountException {

                }

                @Override
                public List<UserWordServiceOut> getUserWords() {
                    return null;
                }
            };
        }
    }

}
