package hristovski.nikola.product.application.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import hristovski.nikola.generic_store.message.domain.rest.common.response.DeletionSuccessfulResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
@Slf4j
public class DeletionSuccessfulResponseSerializer extends StdSerializer<DeletionSuccessfulResponse> {

    public DeletionSuccessfulResponseSerializer() {
        this(null);
    }

    public DeletionSuccessfulResponseSerializer(Class<DeletionSuccessfulResponse> t) {
        super(t);
    }

    @Override
    public void serialize(DeletionSuccessfulResponse value, JsonGenerator gen, SerializerProvider provider)
            throws IOException {
        gen.writeStartObject();
        gen.writeEndObject();
    }
}
