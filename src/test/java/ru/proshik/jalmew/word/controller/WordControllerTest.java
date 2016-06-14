package ru.proshik.jalmew.word.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.proshik.jalmew.word.service.WordServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by proshik on 24.05.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class WordControllerTest {

    private MockMvc mockMvc;

    @Mock
    public WordServiceImpl wordService;

    @InjectMocks
    public WordController wordController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(wordController).build();
    }

    @Test
    public void addWordTest() throws Exception {

        mockMvc.perform(post("/word").content("{\"word\":\"word\"}").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

//        this.mockMvc.perform(post("/word").con.accept(MediaType.parseMediaType("application/json;charset=UTF-8")).)
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("application/json"))
//                .andExpect(jsonPath("$.name").value("Lee"));
    }

//    @Test
//    public void givenCsrf_whenAddFoo_thenCreated() throws Exception {
//        mockMvc.perform(
//                post("/foos").contentType(MediaType.APPLICATION_JSON)
//                        .content("")
//                        .with(csrf())
//        ).andExpect(status().isCreated());
//    }

}
