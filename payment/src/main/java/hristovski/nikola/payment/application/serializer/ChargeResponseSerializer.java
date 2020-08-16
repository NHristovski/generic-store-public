package hristovski.nikola.payment.application.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import hristovski.nikola.generic_store.message.domain.rest.payment.response.ChargeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
@Slf4j
public class ChargeResponseSerializer extends StdSerializer<ChargeResponse> {
    public ChargeResponseSerializer() {
        this(null);
    }

    public ChargeResponseSerializer(Class<ChargeResponse> t) {
        super(t);
    }

    @Override
    public void serialize(ChargeResponse value, JsonGenerator gen, SerializerProvider provider)
            throws IOException {
        gen.writeStartObject();
        gen.writeEndObject();
    }
}
