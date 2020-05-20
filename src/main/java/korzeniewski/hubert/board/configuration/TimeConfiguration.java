package korzeniewski.hubert.board.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

/**
 * Configures time zone to synchronize back-end with front-end of application.
 */
@Configuration
public class TimeConfiguration {

    /**
     * Proper time zone can be sent by using JSON.
     *
     * @param objectMapper of serializing/deserializing JSON <-> Java object
     */
    @Autowired
    public void configureJackson(ObjectMapper objectMapper) {
        objectMapper.setTimeZone(TimeZone.getDefault());
    }

}
