package hristovski.nikola.generic_store.base.domain.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;

public class JsonSerialization {

    private final static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @SneakyThrows
    public static <E> E deserialize(String json, Class<E> cls) {
        return objectMapper.readValue(json, cls);
    }

    @SneakyThrows
    public static String serialize(Object object) {
        return objectMapper.writeValueAsString(object);
    }
}
