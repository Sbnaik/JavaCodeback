package com.suhas.springboot.jsonconverter.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.suhas.springboot.jsonconverter.deserializer.TimestampDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimstampSerializer extends JsonSerializer<String> {

    DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sm.SSS'Z'");
    private static final Logger LOGGER = LoggerFactory.getLogger(TimestampDeserializer.class);

    {
        dateFormat1.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Override
    public void serialize(
            String outputString,
            JsonGenerator jsonGenerator,
            SerializerProvider serializerProvider) throws IOException {
        try {
            inputDate = jsonGenerator.get().trim();
            result = dateFormat1.parse(inputDate);
        } catch (ParseException exception) {
            LOGGER.error(String.format("Error while deserializing the input date : %s", inputDate), exception);
        }

        return result;
    }
}
