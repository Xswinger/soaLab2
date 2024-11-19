package se.ifmo.ru.second_service.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coordinates {
    private Double x; //Поле не может быть null
    private Double y; //Поле не может быть null
}
