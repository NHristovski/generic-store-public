package hristovski.nikola.rating.application.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import hristovski.nikola.generic_store.message.domain.rest.rating.response.RateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
@Slf4j
public class RateResponseSerializer extends StdSerializer<RateResponse> {

    public RateResponseSerializer() {
        this(null);
    }

    public RateResponseSerializer(Class<RateResponse> t) {
        super(t);
    }

    @Override
    public void serialize(RateResponse value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        log.info("Called serialization of rate response");
        gen.writeStartObject();
        gen.writeEndObject();
    }
}
