package ru.proshik.jalmew.word.util.restclient;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.proshik.jalmew.word.model.ydict.YTranslateWord;

/**
 * Created by proshik on 22.05.16.
 */
@Component
public abstract class AbstractRestClient {

    private RestTemplate template = new RestTemplate();

    public RestTemplate getTemplate() {
        return template;
    }
}
