package se.ifmo.ru.first_service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import se.ifmo.ru.first_service.models.Movie;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TicketResponseArray")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
@NoArgsConstructor
public class MovieResponseArray {
    @XmlElementWrapper(name = "movies")
    @XmlElement(name = "movie")
    private List<Movie> movies;

    @XmlElement
    private int totalPages;

    @XmlElement
    private long totalElements;

}
