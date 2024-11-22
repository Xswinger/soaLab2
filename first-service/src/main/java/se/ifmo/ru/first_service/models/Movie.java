package se.ifmo.ru.first_service.models;


import java.time.ZonedDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import se.ifmo.ru.first_service.utils.ZonedDateTimeConverter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    @Column(name = "name", nullable = false)
    private String name; //Поле не может быть null, Строка не может быть пустой

    @Embedded
    private Coordinates coordinates; //Поле не может быть null

    @Column(name = "creation_date", nullable = false)
    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    @Column(name = "oscar_count", nullable = true)
    private Integer oscarCount; //Значение поля должно быть больше 0, Поле может быть null

    @Column(name = "length", nullable = false)
    private int length;

    @Column(name = "budget", nullable = false)
    private int budget; //Значение поля должно быть больше 0

    @Column(name = "total_box_office", nullable = false)
    private int totalBoxOffice; //Значение поля должно быть больше 0

    @Enumerated(EnumType.STRING)
    @Column(name = "mpaa_rating", nullable = false)
    private MpaaRating mpaaRating; //Поле не может быть null

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Person director; //Поле может быть null
}
