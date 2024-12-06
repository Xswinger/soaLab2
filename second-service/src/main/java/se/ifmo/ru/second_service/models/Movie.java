package se.ifmo.ru.second_service.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Float creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer oscarCount; //Значение поля должно быть больше 0, Поле может быть null
    private int length;
    private int budget; //Значение поля должно быть больше 0
    private int totalBoxOffice; //Значение поля должно быть больше 0
    private MpaaRating mpaaRating; //Поле не может быть null
    private Person director; //Поле может быть null
}
