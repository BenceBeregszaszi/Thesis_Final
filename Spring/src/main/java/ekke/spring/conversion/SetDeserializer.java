package ekke.spring.conversion;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class SetDeserializer extends StdDeserializer<HashSet<Long>> {

    public SetDeserializer() {
        this(null);
    }

    protected SetDeserializer(Class<?> clazz) {
        super(clazz);
    }

    @Override
    public HashSet<Long> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Set<Long> set = new HashSet<>();
        if (node.asText().isEmpty()){
            return new HashSet<>();
        }
        String[] parts = node.asText().trim().replace("[", "").replace("]", "").split(",");
        for (String part : parts) {
            set.add(Long.parseLong(part));
        }
        return (HashSet<Long>) set;
    }
}
