package se.ifmo.ru.first_service.models;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import se.ifmo.ru.first_service.utils.ZonedDateTimeConverter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @Column(name = "name")
    private String name; //Поле не может быть null, Строка не может быть пустой

    @Column(name = "birthday")
    @Convert(converter = ZonedDateTimeConverter.class)
    private java.time.ZonedDateTime birthday; //Поле не может быть null

    @Column(name = "weight")
    private Long weight; //Поле не может быть null, Значение поля должно быть больше 0

    @Embedded
    private Location location; //Поле не может быть null
}