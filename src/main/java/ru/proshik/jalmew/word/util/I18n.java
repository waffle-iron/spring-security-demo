package ru.proshik.jalmew.word.util;

import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Created by proshiklunux on 03.05.16.
 */
@Component
public class I18n {

    private MessageSource messageSource;
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(I18n.class);

    public I18n() {
        this.messageSource = messageSource();
    }

    public I18n(ReloadableResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String message){
        return messageSource.getMessage(message, null, Locale.getDefault());
    }

    public String getMessage(String message, Object... params) {
        return messageSource.getMessage(message, params, Locale.getDefault());
    }

    private MessageSource messageSource() {
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

}
