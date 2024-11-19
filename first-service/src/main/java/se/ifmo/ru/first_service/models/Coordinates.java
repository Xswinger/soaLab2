package se.ifmo.ru.first_service.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Coordinates {
    @Column(name = "coordinates_x", nullable = false)
    private Double x; //Поле не может быть null

    @Column(name = "coordinates_y", nullable = false)
    private Double y; //Поле не может быть null
}
