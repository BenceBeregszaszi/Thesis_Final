package ekke.spring.conversion;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import ekke.spring.common.Enum.Authority;

import java.io.IOException;

public class AuthorityDeserializer extends StdDeserializer<Authority> {

    public AuthorityDeserializer() {
        this(null);
    }

    protected AuthorityDeserializer(Class<?> clazz) {
        super(clazz);
    }

    @Override
    public Authority deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String authority = node.asText();
        if (authority.isEmpty()){
            return null;
        }
        return Authority.valueOf(authority.toUpperCase());
    }
}
