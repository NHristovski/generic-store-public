//package hristovski.nikola.generic_store.message.domain.serialization;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.SneakyThrows;
//
//public class JsonSerialization {
//
//    private final static ObjectMapper objectMapper;
//
//    static {
//        objectMapper = new ObjectMapper();
//    }
//
//    @SneakyThrows
//    public static <E> E deserialize(String json, Class<E> cls) {
//        return objectMapper.readValue(json, cls);
//    }
//
//    @SneakyThrows
//    public static String serialize(Object object) {
//        return objectMapper.writeValueAsString(object);
//    }
//}
