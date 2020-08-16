package hristovski.nikola.shopping_cart.application.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import hristovski.nikola.generic_store.message.domain.rest.shopping_cart.response.DeleteShoppingCartItemResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
@Slf4j
public class DeleteShoppingCartItemResponseSerializer extends StdSerializer<DeleteShoppingCartItemResponse> {
    public DeleteShoppingCartItemResponseSerializer() {
        this(null);
    }

    public DeleteShoppingCartItemResponseSerializer(Class<DeleteShoppingCartItemResponse> t) {
        super(t);
    }

    @Override
    public void serialize(DeleteShoppingCartItemResponse value, JsonGenerator gen, SerializerProvider provider)
            throws IOException {
        gen.writeStartObject();
        gen.writeEndObject();
    }
}
