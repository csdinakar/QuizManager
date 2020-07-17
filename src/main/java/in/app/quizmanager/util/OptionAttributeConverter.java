package in.app.quizmanager.util;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.app.quizmanager.model.Option;

@Converter(autoApply = true)
public class OptionAttributeConverter implements AttributeConverter<ConcurrentHashMap<Long, Option>, String> {
    
    
    private static final Logger log = LoggerFactory.getLogger(OptionAttributeConverter.class);


    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(ConcurrentHashMap<Long, Option> attribute) {
        String optionData = "";
        try {
            optionData = objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            log.error("Exception in converting option map to json", e);
        }
        return optionData;
    }

    @Override
    public ConcurrentHashMap<Long, Option> convertToEntityAttribute(String dbData) {
        ConcurrentHashMap<Long, Option> optionData = null;
        try {
            // https://devua.co/2016/07/21/tricky-issue-on-jackson-serializationdeserialization/
            optionData = objectMapper.readValue(dbData, new TypeReference<ConcurrentHashMap<Long, Option>>() {});
        } catch (IOException e) {
            log.error("JSON reading error", e);
        }
        return optionData;
    }
}
