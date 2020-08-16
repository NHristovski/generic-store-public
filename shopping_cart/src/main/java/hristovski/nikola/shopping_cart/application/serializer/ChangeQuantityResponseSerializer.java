package hristovski.nikola.shopping_cart.application.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import hristovski.nikola.generic_store.message.domain.rest.shopping_cart.response.ChangeQuantityResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
@Slf4j
public class ChangeQuantityResponseSerializer extends StdSerializer<ChangeQuantityResponse> {
    public ChangeQuantityResponseSerializer() {
        this(null);
    }

    public ChangeQuantityResponseSerializer(Class<ChangeQuantityResponse> t) {
        super(t);
    }

    @Override
    public void serialize(ChangeQuantityResponse value, JsonGenerator gen, SerializerProvider provider)
            throws IOException {
        gen.writeStartObject();
        gen.writeEndObject();
    }
}
