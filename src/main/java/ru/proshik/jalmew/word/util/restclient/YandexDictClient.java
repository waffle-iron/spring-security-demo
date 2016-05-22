package ru.proshik.jalmew.word.util.restclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;
import ru.proshik.jalmew.word.model.ydict.YTranslateWord;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.Objects;

/**
 * Created by proshik on 23.05.16.
 * <p/>
 * Вo not forget set yandex dict key.
 * <p/>
 * Example: For Intellij Idea will be need for Application.configuration in field "Program arguments" set value:
 * <p/>
 * --yandex.client.dict.key="<>you key received from yandex>"
 */
@Component
public class YandexDictClient extends AbstractRestClient {

    private static final Logger log = LoggerFactory.getLogger(YandexDictClient.class);

    @Value("${yandex.client.dict.root.url}")
    public String yandexRootUrl;

    @Value("${yandex.client.dict.key}")
    private String yandexKey;

    @Value("${yandex.client.dict.lang}")
    private String yandexLang;

    @PostConstruct
    public void init() {
        if (yandexKey == null || StringUtils.isEmpty(yandexKey))
            throw new NullPointerException("key for y.dict is not set");
    }

    public YTranslateWord lookup(String word) {
        log.debug("{YANDEX-CLIENT_LOOKUP} send request to y.dict for word=" + word);

        URI uri = UriComponentsBuilder.fromUriString(yandexRootUrl)
                .queryParam("key", yandexKey)
                .queryParam("lang", yandexLang)
                .queryParam("text", word)
                .build().toUri();

        return getTemplate().getForObject(uri, YTranslateWord.class);
    }

}
