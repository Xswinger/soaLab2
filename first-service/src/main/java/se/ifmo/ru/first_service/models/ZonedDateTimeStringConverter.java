package se.ifmo.ru.first_service.models;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
@Converter
public class ZonedDateTimeStringConverter implements AttributeConverter<ZonedDateTime, String> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public ZonedDateTime convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        // Преобразуем строку в Timestamp
        Timestamp timestamp = Timestamp.valueOf(dbData);
        // Преобразуем Timestamp в ZonedDateTime
        return timestamp.toInstant().atZone(ZoneId.systemDefault());
    }

    @Override
    public String convertToDatabaseColumn(ZonedDateTime attribute) {
        if (attribute == null) {
            return null;
        }
        // Преобразуем ZonedDateTime в строку с нужным форматом
        return attribute.format(formatter);
    }
}

