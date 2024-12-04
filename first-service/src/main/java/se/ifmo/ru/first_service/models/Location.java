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
public class Location {
    @Column(name = "x")
    private long x;

    @Column(name = "y")
    private double y;

    @Column(name = "z")
    private float z;
}
